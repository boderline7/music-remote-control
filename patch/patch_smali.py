#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
车机助手 V18.49 重打包补丁脚本
对已 baksmali 的 smali 树做定点修改：
  1. 机型/VIN 验证  -> getSystemVin 返回固定 VIN（目标车型）
  2. VIP/授权验证   -> getLicenseStatus 恒真 + checkLicenseToken 恒返回全功能
  3. 联网门控       -> requireInternet / requireVerify / fetchLicenseStatus 全部离线化
  4. 签名校验 / OTP -> verifySignature / verifyTotp 恒真，启动门控与试用流程直接短路
"""
import re, sys, hashlib, pathlib

ROOT = pathlib.Path(sys.argv[1] if len(sys.argv) > 1 else "apk_decoded")
VIN  = sys.argv[2] if len(sys.argv) > 2 else "LVVDB21B2ND000001"

NB   = ROOT / "smali/com/navi/tool/utils/NativeBridge.smali"
SPU  = ROOT / "smali/com/navi/tool/utils/SharedPreferencesUtils.smali"
MAIN = ROOT / "smali/com/navi/tool/MainActivity.smali"

report = []

def method_regex(sig):
    # 精确匹配 .method ... name(sig) ... .end method（非贪婪，跨块）
    return re.compile(r"^\.method[^\n]*?" + re.escape(sig) + r"\n.*?^\.end method\n",
                      re.S | re.M)

def replace_method(path, sig, new_body, tag):
    p = pathlib.Path(path)
    src = p.read_text(encoding="utf-8")
    rx = method_regex(sig)
    hits = rx.findall(src)
    if len(hits) != 1:
        report.append(f"[FAIL] {tag}: 命中 {len(hits)} 处（应为1） sig={sig}")
        return False
    old = hits[0]
    old_hash = hashlib.md5(old.encode()).hexdigest()[:8]
    src2 = rx.sub(lambda m: new_body, src, count=1)
    p.write_text(src2, encoding="utf-8")
    report.append(f"[OK]   {tag}: {sig}  (old md5 {old_hash} -> new md5 {hashlib.md5(new_body.encode()).hexdigest()[:8]})")
    return True

def stub_static(sig, ret_type, body_lines, tag):
    """把 native 方法改成普通 Java 方法"""
    ret = {"Z":"return v0","I":"return v0","V":"return-void",
           "Ljava/lang/String;":"return-object v0","[B":"return-object v0"}[ret_type]
    # 精确统计参数个数 -> .registers = params + 1(局部 v0)
    param_desc = sig[sig.index("(") + 1: sig.index(")")]
    np = 0
    i = 0
    while i < len(param_desc):
        if param_desc[i] == "[":
            i += 1
            continue
        if param_desc[i] == "L":
            i = param_desc.index(";", i) + 1
        else:
            i += 1
        np += 1
    regs = np + 1
    lines = [f".method public static {sig}", f"    .registers {regs}", ""]
    lines += body_lines
    lines += ["", f"    {ret}", ".end method", ""]
    return replace_method(NB, sig, "\n".join(lines), tag)

# ---------------------------------------------------------------- 1) NativeBridge stubs
stub_static("getSystemVin()Ljava/lang/String;", "Ljava/lang/String;",
            [f'    const-string v0, "{VIN}"', ""], "机型VIN: getSystemVin -> 固定 " + VIN)

stub_static("requireInternet()Z", "Z",
            ["    const/4 v0, 0x0", ""], "联网: requireInternet -> false")

stub_static("requireVerify()Z", "Z",
            ["    const/4 v0, 0x0", ""], "验证门: requireVerify -> false")

stub_static("verifySignature(Landroid/content/Context;)Z", "Z",
            ["    const/4 v0, 0x1", ""], "签名: verifySignature -> true")

stub_static("verifyTotp(Ljava/lang/String;J)Z", "Z",
            ["    const/4 v0, 0x1", ""], "OTP: verifyTotp -> true")

stub_static("checkAppUpdate(Landroid/content/Context;)V", "V",
            ["    # 已移除联网更新检查", ""], "联网: checkAppUpdate -> no-op")

stub_static("uidSyncOnce(Ljava/lang/String;)Z", "Z",
            ["    const/4 v0, 0x1", ""], "联网: uidSyncOnce -> true(不联网)")

stub_static("fetchLicenseStatus(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
            "Ljava/lang/String;",
            ["    const/4 v0, 0x0", ""], "联网: fetchLicenseStatus -> null")

stub_static("checkLicenseToken(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
            "Ljava/lang/String;",
            ['    const-string v0, "OK|1|0|2099-12-31"', ""],
            "VIP: checkLicenseToken -> OK|1|0|2099-12-31")

stub_static("requestTrial(Ljava/lang/String;)Ljava/lang/String;", "Ljava/lang/String;",
            ['    const-string v0, "ERR|offline_mode"', ""],
            "VIP: requestTrial -> 离线拒绝(不再请求服务器)")

stub_static("uploadLicense(Ljava/lang/String;Ljava/lang/String;)Z", "Z",
            ["    const/4 v0, 0x1", ""], "联网: uploadLicense -> true(不上传)")

stub_static("uploadPrefsData(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z",
            "Z", ["    const/4 v0, 0x1", ""], "联网: uploadPrefsData -> true(不上传)")

stub_static("downloadPrefsData(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)[B",
            "[B", ["    const/4 v0, 0x0", ""], "联网: downloadPrefsData -> null(不下载)")

# ---------------------------------------------------------------- 2) getLicenseStatus 恒真
replace_method(SPU, "getLicenseStatus(Landroid/content/Context;)Z",
""".method public static getLicenseStatus(Landroid/content/Context;)Z
    .registers 4

    # [MOD] 强制全功能/VIP，不再依赖本地 token 与原生校验
    const/4 v0, 0x1

    return v0
.end method
""", "VIP: getLicenseStatus -> true")

# ---------------------------------------------------------------- 2b) 许可不上传 / 不同步云端
replace_method(SPU, "isLicenseUploaded(Landroid/content/Context;)Z",
""".method public static isLicenseUploaded(Landroid/content/Context;)Z
    .registers 3

    # [MOD] 视为已上传，避免启动时发起联网上传线程
    const/4 v0, 0x1

    return v0
.end method
""", "联网: isLicenseUploaded -> true(跳过上传线程)")

# ---------------------------------------------------------------- 3) MainActivity 门控短路
MA = str(MAIN)
replace_method(MA, "initAccessGate()V",
""".method private initAccessGate()V
    .registers 4

    # [MOD] 跳过 OTP / requireVerify 启动门控
    return-void
.end method
""", "门控: initAccessGate -> no-op")

replace_method(MA, "showNetworkToastOnce()V",
""".method private showNetworkToastOnce()V
    .registers 3

    # [MOD] 离线模式下不再提示网络异常
    return-void
.end method
""", "联网: showNetworkToastOnce -> no-op")

replace_method(MA, "lambda$onCreate$9(Ljava/lang/String;)V",
""".method private synthetic lambda$onCreate$9(Ljava/lang/String;)V
    .registers 12

    # [MOD] 原本: 联网拉取 license -> 校验 -> block 则杀进程 / 云端配置同步
    #       离线版整段短路，避免任何联网与自毁
    return-void
.end method
""", "联网: 授权拉取线程 lambda$onCreate$9 -> no-op")

replace_method(MA, "handleStartTrial(Ljava/lang/String;)V",
""".method private handleStartTrial(Ljava/lang/String;)V
    .registers 5

    # [MOD] 已是全功能版，试用入口短路（不再联网请求试用）
    return-void
.end method
""", "VIP: handleStartTrial -> no-op")

# ---------------------------------------------------------------- 4) 标题栏直接显示 VIP 永久
src = MAIN.read_text(encoding="utf-8")
old_blk = """    invoke-direct {p0, v4, v6, p1}, Lcom/navi/tool/MainActivity;->updateToolbarTitle(Ljava/lang/String;ZLjava/lang/String;)V

    invoke-direct {p0, v6}, Lcom/navi/tool/MainActivity;->setupTabsAndPager(Z)V"""
new_blk = """    const-string p1, "2099-12-31"

    invoke-direct {p0, v4, v6, p1}, Lcom/navi/tool/MainActivity;->updateToolbarTitle(Ljava/lang/String;ZLjava/lang/String;)V

    invoke-direct {p0, v6}, Lcom/navi/tool/MainActivity;->setupTabsAndPager(Z)V"""
n = src.count(old_blk)
if n == 1:
    MAIN.write_text(src.replace(old_blk, new_blk), encoding="utf-8")
    report.append("[OK]   VIP: onCreate 标题栏到期时间 -> 2099-12-31（显示“永久”）")
else:
    report.append(f"[FAIL] VIP: 标题栏补丁命中 {n} 处")

print("\n".join(report))
fails = [r for r in report if r.startswith("[FAIL]")]
print(f"\n合计 {len(report)} 项，失败 {len(fails)} 项")
sys.exit(1 if fails else 0)
