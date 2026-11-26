#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""车机助手 (com.navi.tool) 改版工程 · 统一构建入口

原来是 7 步手工命令（见 README.md 第五节），现在收敛成 4 个子命令：

    python make.py inspect <dex目录>
        脱壳产物能不能直接用？看类分布、库类齐不齐、MainActivity 的父类在不在。
        这一步就是当年 18.49「点图标闪退且无日志」那个坑的自动化检查。

    python make.py build --dex <dex目录>
        解包 -> 反汇编 -> 打补丁 -> 去壳 -> 打包 -> 4K 对齐 -> 签名 -> 自校验。
        补丁先全部预检，任何一处对不上就停，不会改一半。

    python make.py verify <apk>
        产物校验：19 处补丁是否真落地、dex 结构、壳残留、签名、对齐。

    python make.py sync --to <目标解包目录>
        把打好的解包树导出到指定目录（旧工作流给桥接工程 build.py 用，现已退役）。

工具路径可用环境变量覆盖：JB / BT / APKTOOL / KEYSTORE
补丁内容在 patches.py，改补丁不用动这个文件。
"""
import argparse
import json
import os
import re
import shutil
import struct
import subprocess
import sys
import time
import zipfile
from pathlib import Path

import patches
import musicpatches

ROOT = Path(__file__).resolve().parent
SRC_APK = ROOT / "apk"
OUT = ROOT / "dist"
TOOLS = ROOT / "patch"

# 工具路径：优先环境变量，其次构建目录下的 local.env（不入库），最后才是本机默认值
def _local_env(name, default=""):
    """从 <工程根>/local.env 读一行 KEY=VALUE，读不到返回 default。"""
    f = ROOT / "local.env"
    try:
        for line in f.read_text(encoding="utf-8").splitlines():
            line = line.strip()
            if not line or line.startswith("#") or "=" not in line:
                continue
            k, v = line.split("=", 1)
            if k.strip() == name:
                return v.strip().strip('"').strip("'")
    except OSError:
        pass
    return default


def _raw(name, default=""):
    """环境变量 > local.env > default，返回原始字符串（可能为空）。"""
    return os.environ.get(name) or _local_env(name, default)


def _tool(name, default=""):
    v = _raw(name, default)
    return Path(v) if v else None


JB = _tool("JB", "")
BT = _tool("BT", "")
APKTOOL = _tool("APKTOOL", str(TOOLS / "apktool.jar"))
KEYSTORE = _tool("KEYSTORE", str(TOOLS / "debug.keystore"))
DEXKIT = TOOLS / "dexkit_classes"
# ---- 注入部分（18766 网关 + 车控页）的源码位置 ----
# 自包含：本工程内的 src/bridge 与 web/pages/car.html 就是唯一真相。
# 环境变量 BRIDGE_ROOT 可覆盖，供临时对照旧工程用。
BRIDGE_ROOT = Path(os.environ.get("BRIDGE_ROOT", str(ROOT)))
SNAPSHOT = ROOT / "src"
ANDROID_JAR = _tool("ANDROID_JAR", "")
# apktool 在中文路径下编 res 会失败，构建目录必须纯 ASCII
ASCII = _tool("ASCII_BUILD", "C:/Temp/mod_build")

if JB is None or BT is None:
    JAVA = JAVAC = D8 = ZIPALIGN = AAPT2 = APKSIGNER = None
else:
    JAVA = (JB / "bin" / "java.exe") if (JB / "bin" / "java.exe").exists() else (JB / "java.exe")
    JAVAC = JAVA.with_name("javac.exe")
    D8 = BT / "d8.bat"
    ZIPALIGN = BT / "zipalign.exe"
    AAPT2 = BT / "aapt2.exe"
    APKSIGNER = BT / "lib" / "apksigner.jar"
KS_PASS = "android"
KS_ALIAS = "debugkey"

OK = "[OK]  "
BAD = "[FAIL]"
WARN = "[WARN]"


# ------------------------------------------------------------------ 基础设施
def child_env():
    """子进程环境：JAVA_HOME 跟着 JB 走。

    d8.bat / apktool 这类批处理靠 JAVA_HOME 找 java，系统里那个可能指向别处
    或者已经失效，所以每次调用都显式覆盖，不依赖外部环境。
    """
    env = os.environ.copy()
    if JB is not None and (JB / "bin").is_dir():
        env["JAVA_HOME"] = str(JB)
        env["PATH"] = str(JB / "bin") + os.pathsep + env.get("PATH", "")
    return env


def run(cmd, **kw):
    """跑外部命令，失败抛异常并带上最后几行输出。"""
    kw.setdefault("env", child_env())
    p = subprocess.run([str(c) for c in cmd], capture_output=True, text=True,
                       encoding="utf-8", errors="replace", **kw)
    if p.returncode != 0:
        tail = "\n".join((p.stdout or "").strip().splitlines()[-12:]
                         + (p.stderr or "").strip().splitlines()[-8:])
        raise RuntimeError(f"命令失败({p.returncode}): {' '.join(str(c) for c in cmd)}\n{tail}")
    return p


def need_tools():
    if JB is None or BT is None or ANDROID_JAR is None:
        raise SystemExit(
            "缺工具路径配置。在工程根建一个 local.env（不入库），内容形如：\n"
            "  JB=<jdk-17 目录>\n"
            "  BT=<build-tools 目录>\n"
            "  ANDROID_JAR=<sdk>/platforms/android-35/android.jar\n"
            "也可以用同名环境变量临时指定。")
    miss = [p for p in (JAVA, ZIPALIGN, AAPT2, APKSIGNER, APKTOOL, KEYSTORE)
            if not Path(p).exists()]
    if miss:
        raise SystemExit("工具缺失:\n  " + "\n  ".join(str(m) for m in miss))


def apk_badging(apk):
    """读包名 / versionName / versionCode。"""
    out = run([AAPT2, "dump", "badging", apk]).stdout
    m = re.search(r"package: name='([^']+)' versionCode='([^']+)' versionName='([^']*)'", out)
    if not m:
        raise RuntimeError(f"读不出 badging: {apk}")
    return {"pkg": m.group(1), "code": m.group(2), "name": m.group(3)}


def manifest_app_class(apk):
    """从 Manifest 里取 application 的 android:name（没有就返回空）。"""
    out = run([AAPT2, "dump", "xmltree", "--file", "AndroidManifest.xml", apk]).stdout
    lines = out.splitlines()
    for i, ln in enumerate(lines):
        if re.search(r"E: application", ln):
            for sub in lines[i + 1:i + 25]:
                if re.search(r"E: ", sub) and "application" not in sub:
                    break
                m = re.search(r'A: http://schemas.android.com/apk/res/android:name\S*=.*?"([^"]+)"', sub)
                if m:
                    return m.group(1)
    return ""


# ------------------------------------------------------------------ dex 解析
class DexFile:
    """只读 dex 的字符串池、类型表、类定义（含父类），够做依赖诊断。"""

    def __init__(self, path):
        self.path = Path(path)
        self.d = self.path.read_bytes()
        if self.d[:4] != b"dex\n":
            raise ValueError(f"{self.path.name} 不是 dex（魔数 {self.d[:4]!r}）")
        self.size = len(self.d)
        self.strings = self._strings()
        self.types = self._types()
        self.classes = self._classes()

    def _u4(self, off):
        return struct.unpack_from("<I", self.d, off)[0]

    def _strings(self):
        n, off = self._u4(0x38), self._u4(0x3C)
        out = []
        for i in range(n):
            p = self._u4(off + i * 4)
            while self.d[p] & 0x80:      # 跳过 uleb128 长度
                p += 1
            p += 1
            end = self.d.index(b"\x00", p)
            out.append(self.d[p:end].decode("utf-8", "replace"))
        return out

    def _types(self):
        n, off = self._u4(0x40), self._u4(0x44)
        return [self.strings[self._u4(off + i * 4)] for i in range(n)]

    def _classes(self):
        n, off = self._u4(0x60), self._u4(0x64)
        out = []
        for i in range(n):
            base = off + i * 32
            cidx = self._u4(base)
            sidx = self._u4(base + 8)
            sup = self.types[sidx] if sidx != 0xFFFFFFFF else None
            out.append((self.types[cidx], sup))
        return out

    def has_prefix(self, prefix):
        return any(c[1:].startswith(prefix) for c, _ in self.classes)


def classify(dex_paths):
    """把一批 dex 分成 business / lib / shell，返回 (保留列表, 丢弃列表, 详细)。"""
    infos = []
    for p in dex_paths:
        d = DexFile(p)
        is_navi = d.has_prefix("com/navi/")
        is_lib = d.has_prefix("androidx/") or d.has_prefix("com/google/")
        is_shell = d.has_prefix("com/baidu/") or d.has_prefix("com/sagittarius/")
        infos.append({"dex": d, "navi": is_navi, "lib": is_lib, "shell": is_shell})

    keep, drop = [], []
    for it in infos:
        # 判据按「谁是主角」：带业务类的留，带库类的留，两边都不沾的才算壳。
        # 壳类零星散落在库 dex 里是正常的（V18 的 dex_2 就有），不能因此丢掉整份。
        if it["navi"] or it["lib"]:
            keep.append(it)
        else:
            drop.append(it)
    # 业务 dex 排第一位（apktool 把它当 classes.dex）
    keep.sort(key=lambda x: (0 if x["navi"] else 1, -x["dex"].size))
    return keep, drop, infos


def check_chain(keep):
    """MainActivity 的父类必须落在被保留的 dex 里，否则装上就闪退且无日志。"""
    types = set()
    for it in keep:
        types.update(t for t, _ in it["dex"].classes)
    main = "Lcom/navi/tool/MainActivity;"
    for it in keep:
        for cls, sup in it["dex"].classes:
            if cls == main:
                if sup is None:
                    return main, None, True
                return main, sup, (sup in types)
    return main, None, False


# ------------------------------------------------------------------ 补丁
METHOD_RX = lambda sig: re.compile(
    r"^\.method[^\n]*?" + re.escape(sig) + r"\n.*?^\.end method\n", re.S | re.M)


def param_width(desc):
    """参数占几个寄存器（long/double 算 2）。"""
    w, i = 0, 0
    while i < len(desc):
        c = desc[i]
        if c == "[":
            while desc[i] == "[":
                i += 1
            if desc[i] == "L":
                i = desc.index(";", i) + 1
            else:
                i += 1
            w += 1
        elif c == "L":
            i = desc.index(";", i) + 1
            w += 1
        elif c in "JD":
            i += 1
            w += 2
        else:
            i += 1
            w += 1
    return w


def active_patches(decoded):
    """按脱壳源码特征筛选补丁。

    带 only_if="v19_gate" 的补丁仅当源码含 V19 独有的
    getCachedLicenseMask（NativeBridge）时才应用；V18 没有该方法，
    自动跳过，保证 V18 回归基准逐条目不变。
    """
    out = []
    nb = Path(decoded) / "smali" / "com/navi/tool/utils/NativeBridge.smali"
    v19_gate = nb.exists() and "getCachedLicenseMask" in nb.read_text(
        encoding="utf-8", errors="ignore")
    skip = {x.strip() for x in os.environ.get("SKIP_PATCH", "").split(",") if x.strip()}
    for p in patches.PATCHES:
        gate = p.get("only_if")
        if gate == "v19_gate" and not v19_gate:
            continue
        if p["tag"] in skip:
            continue
        out.append(p)
    return out


def sig_variants(p):
    """一条补丁可能兼容多个版本的方法签名：优先 sig，再 sig_alt。"""
    out = [p["sig"]]
    if p.get("sig_alt") and p["sig_alt"] not in out:
        out.append(p["sig_alt"])
    return out


def resolve_sig(p, src):
    """返回 (实际签名, 命中数, match)；按 sig_variants 顺序取第一个命中的。"""
    for sig in sig_variants(p):
        hits = METHOD_RX(sig).findall(src)
        if hits:
            return sig, len(hits), METHOD_RX(sig).search(src)
    return None, 0, None


def render(p, vin, sig=None):
    """按补丁描述生成完整方法文本。sig 不传用主签名。"""
    sig = sig or p["sig"]
    # body 补丁：整方法体替换（smali 行列表），regs 用补丁指定值
    if p.get("body") is not None:
        desc = sig[sig.index("(") + 1:sig.index(")")]
        return sig, p.get("regs", param_width(desc) + 1), list(p["body"]), None
    ret, val = p["ret"], p["val"]
    desc = sig[sig.index("(") + 1:sig.index(")")]
    regs = param_width(desc) + 1
    if val is not None and isinstance(val, str):
        val = val.replace("{VIN}", vin)

    body, ret_ins = [], "return-void"
    if ret == "V":
        pass
    elif ret == "Z":
        body = [f"const/4 v0, 0x{1 if val == 'true' else 0}"]
        ret_ins = "return v0"
    elif ret == "I":
        n = int(val, 0)
        body = [f"const/4 v0, 0x{n:x}" if -8 <= n <= 7 else f"const/16 v0, 0x{n:x}"]
        ret_ins = "return v0"
    elif ret == "[B":
        body = ["const/4 v0, 0x0"]
        ret_ins = "return-object v0"
    elif ret.startswith("L"):
        body = ['const-string v0, "' + str(val).replace('"', '\\"') + '"']
        ret_ins = "return-object v0"
    else:
        raise ValueError(f"不认识的返回类型 {ret} ({p['tag']})")

    return sig, regs, body, ret_ins


def find_method(path, p):
    """兼容多签名：返回 (源码, match, 实际签名)。"""
    src = path.read_text(encoding="utf-8")
    sig, _, m = resolve_sig(p, src)
    return src, m, sig


def flags_of(block, sig):
    first = block.split("\n", 1)[0]
    head = first[len(".method"):first.index(sig)]
    return " ".join(w for w in head.split() if w != "native") or "public static"


def precheck(decoded, vin):
    """只查不改：每条补丁必须恰好命中 1 处。"""
    rows = []
    for p in active_patches(decoded):
        path = decoded / "smali" / (p["cls"].replace(".", "/") + ".smali")
        if not path.exists():
            rows.append((p, None, 0, "类文件不存在"))
            continue
        src = path.read_text(encoding="utf-8")
        sig, n, m = resolve_sig(p, src)
        if m is None:
            rows.append((p, path, 0, "方法签名没找到（可能改名或改签名）"))
        elif n != 1:
            rows.append((p, path, n, "命中多处"))
        else:
            rows.append((p, path, 1, ""))
    return rows


def apply(decoded, vin, rows):
    for p, path, hits, why in rows:
        if hits != 1:
            continue
        src, m, sig = find_method(path, p)
        block = m.group(0)
        sig, regs, body, ret_ins = render(p, vin, sig)
        flags = flags_of(block, sig)
        lines = [f".method {flags} {sig}", f"    .registers {regs}", "",
                 f"    # [MOD] {p['tag']}"]
        lines += [f"    {b}" for b in body]
        if ret_ins:
            lines += ["", f"    {ret_ins}"]
        lines += [".end method", ""]
        path.write_text(src[:m.start()] + "\n".join(lines) + src[m.end():], encoding="utf-8")


def stub_all_native(decoded):
    """整块去除 so：把 dex 里所有 native 方法换成 Java 桩，去掉 loadLibrary。

    背景：V19 的 libnavitool.so 用 JNI_OnLoad 动态注册 native 方法并做
    反篡改，去壳/重签后加载即 SIGKILL。逐个把 native 改成 Java 会被
    RegisterNatives 检测到；所以反过来——让 so 根本不加载：
      1) 所有 native 方法去掉 native 修饰符、填安全返回值；
      2) 删掉 System.loadLibrary("navitool") 调用（否则加载 so 触发校验）。
    so 文件留在包里不影响（没人 load 就不会执行 JNI_OnLoad）。
    只处理 navitool 相关的 4 个类；sagittarius 壳类去壳时已删除。
    """
    import re as _re
    files = [
        "com/navi/tool/utils/NativeBridge.smali",
        "com/navi/tool/amapflying/util/kugou/NativeKugouEngine.smali",
        "com/navi/tool/amapflying/util/kuwo/NativeKuwoEngine.smali",
        "com/navi/tool/amapflying/util/netease/NativeNeteaseEngine.smali",
    ]
    # 特定方法的安全桩值（返回类型 -> 默认值；布尔按语义给 true/false）
    TRUE_RET = {"checkRuntimeSafe", "isFeatureEnabled", "requireVerify",
                "verifySignature", "verifyTotp", "uidSyncOnce", "uploadLicense",
                "uploadPrefsData", "savePrefsXml", "applyPreferencesFromXml"}
    INT_NONZERO = {"computePermissionState"}
    STRING_OK = {"signStatusRequest", "persistLicenseToken"}
    STRING_UID = {"computeLicenseUid"}
    total, skipped = 0, []

    def default_body(ret, name):
        """按返回类型生成桩体，返回 (body_lines, ret_ins, regs_needed)。"""
        if ret == "V":
            return [], "return-void", 0
        if ret == "Z":
            v = "0x1" if name in TRUE_RET else "0x0"
            return [f"const/4 v0, {v}"], "return v0", 1
        if ret == "I":
            v = "0x1" if name in INT_NONZERO else "0x0"
            ins = f"const/4 v0, {v}" if v == "0x0" else "const/4 v0, 0x1"
            return [ins], "return v0", 1
        if ret in ("J", "D"):
            return ["const-wide/16 v0, 0x0"], "return-wide v0", 2
        if ret == "F":
            return ["const/4 v0, 0x0"], "return v0", 1
        if ret == "[B" or ret.startswith("["):
            return ["const/4 v0, 0x0"], "return-object v0", 1
        if ret.startswith("L"):
            if name in STRING_OK:
                s = "OK"
            elif name in STRING_UID:
                s = "offline-uid"
            elif name == "getSystemVin":
                s = "{VIN}"
            else:
                s = ""
            return ['const-string v0, "' + s + '"'], "return-object v0", 1
        return ["const/4 v0, 0x0"], "return-object v0", 1

    # 匹配独立的 native 修饰符（前后是空白），避免把 nativeInit 这类方法名误判
    native_hdr = _re.compile(r"^(\s*)\.method\s+([^\n]*)$", _re.M)

    for rel in files:
        path = decoded / "smali" / rel
        if not path.exists():
            skipped.append(rel + "(文件不存在)")
            continue
        src = path.read_text(encoding="utf-8")
        out, last, n = [], 0, 0
        for m in native_hdr.finditer(src):
            head_rest = m.group(2)
            # 解析 NAME(SIG)RET（在行尾）
            sig_m = _re.search(r"([\w$<>]+)\(([^)]*)\)([^\s]+)\s*$", head_rest)
            if not sig_m:
                continue
            name, desc, ret = sig_m.group(1), sig_m.group(2), sig_m.group(3)
            # 方法声明头 = .method 与 NAME( 之间的修饰符区
            prefix = head_rest[:sig_m.start()]
            # 只处理把 native 当独立修饰符的方法（nativeInit 这类名字不算）
            if not _re.search(r"(^|\s)native(\s|$)", prefix):
                continue
            flags = _re.sub(r"(^|\s)native(\s|$)", " ", prefix).strip()
            flags = " ".join(flags.split())
            # 完整方法块：从 .method 到配对的 .end method
            end = src.find("\n.end method", m.end())
            if end < 0:
                continue
            from patches import VIN_DEFAULT
            body, ret_ins, _ = default_body(ret, name)
            body = [b.replace("{VIN}", VIN_DEFAULT) for b in body]
            width = param_width(desc) + 1
            new_lines = [f"{m.group(1)}.method {flags} {name}({desc}){ret}",
                         f"{m.group(1)}    .registers {width}",
                         f"{m.group(1)}    # [MOD] 去 so：native 桩化 {name}"]
            new_lines += [f"{m.group(1)}    {b}" for b in body]
            new_lines += [f"{m.group(1)}    {ret_ins}", f"{m.group(1)}.end method"]
            out.append(src[last:m.start()])
            out.append("\n".join(new_lines))
            last = end + len("\n.end method")
            n += 1
        out.append(src[last:])
        path.write_text("".join(out), encoding="utf-8")
        total += n

    # 去掉 loadLibrary("navitool")：把整行 invoke-static 改成 nop（保留栈平衡无关，静态块无返回）
    stripped = 0
    for smali in decoded.rglob("*.smali"):
        try:
            s = smali.read_text(encoding="utf-8")
        except Exception:
            continue
        if 'loadLibrary' not in s:
            continue
        lines = s.split("\n")
        changed = False
        for i, ln in enumerate(lines):
            if "Ljava/lang/System;->loadLibrary(Ljava/lang/String;)V" in ln:
                # 去掉参数加载行 + invoke；简单起见把 invoke 替换成 nop（v0 残留无害）
                lines[i] = "    nop"
                changed = True
                stripped += 1
        if changed:
            smali.write_text("\n".join(lines), encoding="utf-8")
    return total, stripped, skipped


def apply_free_weather(decoded):
    """目的地天气改用 Open-Meteo（无需 key）。

    原链路：requestWeatherApi 拼 QWeather URL -> lambda 异步 HTTP ->
    parseWeatherResponse 解析 QWeather JSON -> 原有 TTS/广播。
    这里只换两处数据入口，TTS 与异步调度全部保留：
      1) URL 构建段改为 FreeWeather.url(lon, lat)
      2) parseWeatherResponse 入口先把 Open-Meteo JSON 转成 QWeather 形状
      3) QWeather 授权头方法返回占位串，否则原 lambda 会直接跳过请求
    只管导航结束时的目的地天气播报（事件触发，独立 Receiver）。
    桌面时钟卡片那条常驻链路在 apply_free_desk_weather，与 --no-weather 二选一。
    """
    path = decoded / "smali/com/navi/tool/protocol/DestinationWeatherBroadcastReceiver.smali"
    if not path.exists():
        raise RuntimeError(f"免费天气补丁找不到类文件: {path}")
    src = path.read_text(encoding="utf-8")

    def patch_method(sig, transform, label, required=True):
        nonlocal src
        m = METHOD_RX(sig).search(src)
        if not m:
            if required:
                raise RuntimeError(f"免费天气补丁找不到方法 {label}: {sig}")
            return False
        old = m.group(0)
        new = transform(old)
        if new == old:
            raise RuntimeError(f"免费天气补丁没改到内容 {label}: {sig}")
        src = src[:m.start()] + new + src[m.end():]
        return True

    def swap_url(block):
        # V18 用 StringBuilder 拼 QWeather 域名；19.00 先 parseDouble 再走
        # n1/z.N0 代理。两者的共同结尾都是把 executorService 取出来起线程，
        # 所以按这个锚点整段替换 URL 构造，兼容两版。
        return re.sub(
            r"(^    \.registers \d+\n).*?(?=^    iget-object p\d, p0, "
            r"Lcom/navi/tool/protocol/DestinationWeatherBroadcastReceiver;"
            r"->executorService)",
            lambda mm: mm.group(1)
            + "\n"
            + "    invoke-static {p1, p2}, "
              "Lcom/navitool/bridge/FreeWeather;->url"
              "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;\n"
            + "\n"
            + "    move-result-object p1\n\n",
            block, count=1, flags=re.S | re.M)

    patch_method("requestWeatherApi(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V",
                 swap_url, "URL")

    # V18 的 lambda 会先取 QWeather 授权头，空串就不发请求；19.00 已无此步。
    sig_hdr = "getQWeatherAuthorizationHeader()Ljava/lang/String;"
    def dummy_header(block):
        flags = flags_of(block, sig_hdr)
        return (f".method {flags} {sig_hdr}\n"
                "    .registers 1\n"
                "\n"
                "    # [MOD] 免费天气：Open-Meteo 不需要 QWeather 凭据\n"
                '    const-string v0, "Bearer open-meteo"\n'
                "\n"
                "    return-object v0\n"
                ".end method\n")
    header_done = patch_method(sig_hdr, dummy_header, "授权头", required=False)

    sig_parse = "parseWeatherResponse(Ljava/lang/String;Ljava/lang/String;)V"
    conv = ("\n"
            "    invoke-static {p1}, "
            "Lcom/navitool/bridge/FreeWeather;->parseNow"
            "(Ljava/lang/String;)Ljava/lang/String;\n"
            "\n"
            "    move-result-object p1\n")
    def convert_body(block):
        return re.sub(r"^(\s*\.registers \d+\n)",
                      lambda mm: mm.group(1) + conv, block, count=1, flags=re.M)

    patch_method(sig_parse, convert_body, "响应转换")
    path.write_text(src, encoding="utf-8")

    return ("Open-Meteo：目的地天气(URL+响应转换"
            + ("+授权头占位" if header_done else "，V19 无需授权头")
            + ")")


def apply_free_desk_weather(decoded):
    """桌面时钟卡片天气改走 Open-Meteo。

    与 --no-weather 互斥：那边把刷新链整条空掉后，这里改的 URL / JSON 解析
    永远不会被调用，补了也是死代码。所以单独一个开关，默认不做。

    链路：DesktopAssistantClockWeatherController -> s4/g.a(DD)
          -> n1/z.N0 拼和风 wxproxy URL -> okhttp -> s4/g.b 解析和风 JSON。
    去 so 后 wxproxy 拿不到凭据，一并改走 Open-Meteo；顺带处理无 GPS 时的
    坐标 / 地名兜底，坐标和城市名读 /sdcard/navitool/weather.txt 第一行。
    """
    # ---- 桌面时钟天气卡片（另一条链，之前漏改）----
    # DesktopAssistantClockWeatherController -> s4/g.a(lat,lon) ->
    # n1/z.N0 拼和风 wxproxy URL -> okhttp -> s4/g.b 解析和风 JSON。
    # 去 so 后 wxproxy 同样拿不到凭据，一并改走 Open-Meteo。
    def patch_file(rel, sig, transform, label):
        p = decoded / rel
        if not p.exists():
            raise RuntimeError(f"桌面天气补丁找不到类文件: {p}")
        text = p.read_text(encoding="utf-8")
        m = METHOD_RX(sig).search(text)
        if not m:
            raise RuntimeError(f"桌面天气补丁找不到方法 {label}: {rel} {sig}")
        new = transform(m.group(0))
        if new == m.group(0):
            raise RuntimeError(f"桌面天气补丁没改到内容 {label}: {sig}")
        p.write_text(text[:m.start()] + new + text[m.end():], encoding="utf-8")

    sig_n0 = "N0(DD)Ljava/lang/String;"
    def desk_url(block):
        flags = flags_of(block, sig_n0)
        return (f".method {flags} {sig_n0}\n"
                "    .registers 5\n"
                "\n"
                "    # [MOD] 桌面天气：改走 Open-Meteo（去 so 后和风 wxproxy 不可用）\n"
                "    # 入参 p0p1=纬度, p2p3=经度（原 N0 location 拼的是 经,纬）\n"
                "    invoke-static {p0, p1, p2, p3}, "
                "Lcom/navitool/bridge/FreeWeatherDesk;->deskUrl(DD)Ljava/lang/String;\n"
                "\n"
                "    move-result-object v0\n"
                "\n"
                "    return-object v0\n"
                ".end method\n")
    patch_file("smali/n1/z.smali", sig_n0, desk_url, "桌面URL")

    sig_gb = "b(Ljava/lang/String;)Ls4/f;"
    def desk_parse(block):
        # N0 换 URL 后，s4/g.b 收到的是 Open-Meteo JSON；入口先转成和风 now 形状。
        return re.sub(r"^(\s*\.registers \d+\n)",
                      lambda mm: mm.group(1)
                      + "\n    invoke-static {p0}, "
                        "Lcom/navitool/bridge/FreeWeatherDesk;->deskParse"
                        "(Ljava/lang/String;)Ljava/lang/String;\n"
                      + "    move-result-object p0\n",
                      block, count=1, flags=re.M)
    patch_file("smali/s4/g.smali", sig_gb, desk_parse, "桌面响应转换")

    # ---- 桌面卡片不再卡「正在定位」----
    # 原控制器强依赖系统定位；车机/测试机拿不到 GPS fix 就一直停在占位文案。
    # 三处小手术：占位文案、权限判断、最后一次已知位置，都走 FreeWeather 兜底坐标。
    rel_ctl = "smali/com/navi/tool/ui/extra/DesktopAssistantClockWeatherController.smali"
    ctl_path = decoded / rel_ctl
    ctl = ctl_path.read_text(encoding="utf-8")
    ctl = ctl.replace('"\\u6b63\\u5728\\u5b9a\\u4f4d"', '"\\u5f53\\u524d\\u4f4d\\u7f6e"')

    def patch_ctl(sig, transform, label):
        nonlocal ctl
        m = METHOD_RX(sig).search(ctl)
        if not m:
            raise RuntimeError(f"天气兜底补丁找不到方法 {label}: {sig}")
        new = transform(m.group(0))
        if new == m.group(0):
            raise RuntimeError(f"天气兜底补丁没改到内容 {label}: {sig}")
        ctl = ctl[:m.start()] + new + ctl[m.end():]

    sig_perm = "hasLocationPermission()Z"
    def always_permit(block):
        flags = flags_of(block, sig_perm)
        return (f".method {flags} {sig_perm}\n"
                "    .registers 1\n\n"
                "    const/4 v0, 0x1\n\n"
                "    return v0\n"
                ".end method\n")
    patch_ctl(sig_perm, always_permit, "定位权限")

    sig_last = "getBestLastKnownLocation()Landroid/location/Location;"
    def fallback_last(block):
        flags = flags_of(block, sig_last)
        return (f".method {flags} {sig_last}\n"
                "    .registers 1\n\n"
                "    invoke-static {}, "
                "Lcom/navitool/bridge/FreeWeatherDesk;->fallbackLocation"
                "()Landroid/location/Location;\n\n"
                "    move-result-object v0\n\n"
                "    return-object v0\n"
                ".end method\n")
    patch_ctl(sig_last, fallback_last, "兜底坐标")

    sig_name = "resolveUsableLocationName(Ljava/lang/String;)Ljava/lang/String;"
    def usable_name(block):
        return re.sub(r"^(\s*\.registers \d+\n)",
                      lambda mm: mm.group(1)
                      + "\n    invoke-static {p1}, "
                        "Lcom/navitool/bridge/FreeWeatherDesk;->usableName"
                        "(Ljava/lang/String;)Ljava/lang/String;\n\n"
                      + "    move-result-object p1\n",
                      block, count=1, flags=re.M)
    patch_ctl(sig_name, usable_name, "地名兜底")
    ctl_path.write_text(ctl, encoding="utf-8")

    # ---- 桌面卡片地名反查：绕开天地图 geoproxy ----
    # resolveGeoAreaInfo -> s4/i.a(经纬度) 走天地图 geoproxy.php，需要
    # getLicenseUid/getLicenseToken（来自 libnavitool.so）；去 so 后凭据为空，
    # 反查必失败，位置名永远停在 DEFAULT_LOCATION_TEXT。整个 a(DD) 短路成
    # 用 weather.txt 第三字段的城市名直接构造 s4/h 返回，不联网、不鉴权。
    geo = decoded / "smali/s4/i.smali"
    geo_text = geo.read_text(encoding="utf-8")
    sig_geo = "a(DD)Ls4/h;"
    m_geo = METHOD_RX(sig_geo).search(geo_text)
    if not m_geo:
        raise RuntimeError(f"地名反查补丁找不到方法: {sig_geo}")
    geo_flags = flags_of(m_geo.group(0), sig_geo)
    geo_new = (f".method {geo_flags} {sig_geo}\n"
               "    .registers 5\n\n"
               "    # [MOD] 去 so 后天地图无凭据，直接用配置城市名当反查结果\n"
               "    new-instance v0, Ls4/h;\n"
               "    invoke-direct {v0}, Ljava/lang/Object;-><init>()V\n\n"
               "    invoke-static {}, Lcom/navitool/bridge/FreeWeatherDesk;->geoName()"
               "Ljava/lang/String;\n"
               "    move-result-object v1\n\n"
               "    # a=formatted_address, c=county, f=city 都填城市名，\n"
               "    # b/county_code 给个固定码避免 hasCountyCodeChanged 反复触发\n"
               "    const-string v2, \"custom\"\n"
               "    iput-object v2, v0, Ls4/h;->b:Ljava/lang/String;\n"
               "    iput-object v1, v0, Ls4/h;->a:Ljava/lang/String;\n"
               "    iput-object v1, v0, Ls4/h;->c:Ljava/lang/String;\n"
               "    iput-object v1, v0, Ls4/h;->f:Ljava/lang/String;\n\n"
               "    return-object v0\n"
               ".end method\n")
    geo_text = geo_text[:m_geo.start()] + geo_new + geo_text[m_geo.end():]
    geo.write_text(geo_text, encoding="utf-8")


    return "桌面天气改走 Open-Meteo：N0 URL + s4/g JSON 转换 + 坐标/地名兜底 + 绕开天地图反查"


def apply_no_weather(decoded):
    """彻底停用桌面时钟卡片天气：不定位、不定时、不发网络请求。

    用户不再需要桌面天气。原控制器 onResume/setEnabled 会启动两个 20 分钟循环
    定时器(periodicRefresh/periodicAlertRefresh) + 持续定位，refresh() -> 
    lambda$handleLocation$2 -> 网络请求，导致天气即使显示「不可用」也在后台一直请求。
    这里把 6 个方法空实现，从触发源到网络出口全断：
      refresh(Z)V                        刷新主链入口
      requestCurrentLocation()V          定位请求 + 18s 定位超时
      lambda$handleLocation$2(DDZ)V      真正的天气 HTTP 请求出口
      schedulePeriodicRefresh()V         20 分钟定时器 1
      schedulePeriodicAlertRefresh()V    20 分钟定时器 2
      startContinuousLocationUpdates()V  持续定位
    不影响目的地天气播报(独立 Receiver，事件触发)，也不动时钟显示。
    """
    rel = "smali/com/navi/tool/ui/extra/DesktopAssistantClockWeatherController.smali"
    path = decoded / rel
    if not path.exists():
        raise RuntimeError(f"停用天气补丁找不到类文件: {path}")
    src = path.read_text(encoding="utf-8")

    targets = [
        "refresh(Z)V",
        "requestCurrentLocation()V",
        "lambda$handleLocation$2(DDZ)V",
        "schedulePeriodicRefresh()V",
        "schedulePeriodicAlertRefresh()V",
        "startContinuousLocationUpdates()V",
    ]
    # 空方法：保留原访问标志，无参数占用寄存器，直接返回。
    # 带 double 参数的方法签名里参数宽，但空实现用 .registers 1 即可。
    import re as _re
    done = []
    for sig in targets:
        m = METHOD_RX(sig).search(src)
        if not m:
            raise RuntimeError(f"停用天气补丁找不到方法: {sig}")
        block = m.group(0)
        flags = flags_of(block, sig)
        # 保留原 .registers / .locals 声明：带 double 参数的方法需要足够的
        # 参数寄存器（pN），改成 1 会让 smali 校验失败。
        rm = _re.search(r"^\s*\.(registers|locals)\s+\d+\s*$", block, _re.M)
        reg_line = rm.group(0).strip() if rm else "    .registers 1"
        empty = (f".method {flags} {sig}\n"
                 f"    {reg_line}\n\n"
                 "    return-void\n"
                 ".end method\n")
        src = src[:m.start()] + empty + src[m.end():]
        done.append(sig)
    path.write_text(src, encoding="utf-8")
    return "停用桌面天气：刷新链/定位/网络出口/2 个定时器全空（%d 处）" % len(done)


def apply_no_seat_confirm(decoded):
    """去掉桌面座椅卡片「应用配置1/2/3」三个按钮的确认弹窗，并把按钮文本改成「座位1/2/3」。

    这三个按钮（btn_seat_memory_1/2/3 与 inline 版）点击后走 showSeatMemoryApplyConfirm(I)，
    弹「确定应用配置 N 吗？」-> 点确认才调 sendSeatMemoryApply(N)。
    这里把 showSeatMemoryApplyConfirm 方法体换成「直接调 sendSeatMemoryApply」，跳过弹窗。
    「保存配置」的确认框保留不动（用户没要求改）。
    同时把 res/layout/view_car_control_cards.xml 里三处「应用配置N」TextView 文本改成「座位N」。
    """
    cls = "Lcom/navi/tool/ui/extra/DesktopAssistantCarControlHelper;"
    # 1) 去掉「应用配置」确认弹窗（只动 apply，不动 save）
    rel = "smali/com/navi/tool/ui/extra/DesktopAssistantCarControlHelper.smali"
    path = decoded / rel
    if not path.exists():
        raise RuntimeError(f"关座椅弹窗补丁找不到类文件: {path}")
    src = path.read_text(encoding="utf-8")
    sig = "showSeatMemoryApplyConfirm(I)V"
    m = METHOD_RX(sig).search(src)
    if not m:
        raise RuntimeError(f"关座椅弹窗补丁找不到方法: {sig}")
    flags = flags_of(m.group(0), sig)
    new = (f".method {flags} {sig}\n"
           "    .registers 2\n"
           "\n"
           "    # [MOD] 跳过应用座椅配置确认弹窗，直接应用（座位 1/2/3）\n"
           f"    invoke-direct {{p0, p1}}, {cls}->sendSeatMemoryApply(I)V\n"
           "\n"
           "    return-void\n"
           ".end method\n")
    if new == m.group(0):
        raise RuntimeError(f"关座椅弹窗补丁未改变: {sig}")
    src = src[:m.start()] + new + src[m.end():]
    path.write_text(src, encoding="utf-8")

    # 2) 布局文本「应用配置1/2/3」->「座位1/2/3」（apply 与 inline 两组共 6 处）
    lay = decoded / "res/layout/view_car_control_cards.xml"
    n_text = 0
    if lay.exists():
        x = lay.read_text(encoding="utf-8")
        for i in (1, 2, 3):
            x, c = re.subn(r'android:text="应用配置%d"' % i,
                           'android:text="座位%d"' % i, x)
            n_text += c
        lay.write_text(x, encoding="utf-8")
    return "去掉「应用配置1/2/3」确认弹窗（直接应用）；按钮文本改「座位1/2/3」（%d 处）" % n_text


def apply_floating_map(decoded):
    """悬浮地图切到日历时 force-stop 当前地图包，切回时重新唤醒贴窗。

    注入点：DesktopAssistantActivity.cycleFloatingMapDisplayMode() 里
    isFloatingMapMode() 的 move-result 之后。此时 v0 是「切换前」的状态：
      v0=1(当前地图) -> 本次切到日历 -> 传入 true -> MapFloating 结束地图进程
      v0=0(当前日历) -> 本次切回地图 -> 传入 false -> MapFloating 延迟唤醒
    只动这一处，不改原厂的 hide/show 广播链；双地图(hasSecondFloatingMap)分支
    在 cond_1d 之前就 return，不受影响。
    """
    rel = "smali/com/navi/tool/ui/extra/DesktopAssistantActivity.smali"
    path = decoded / rel
    if not path.exists():
        raise RuntimeError(f"悬浮地图补丁找不到类文件: {path}")
    src = path.read_text(encoding="utf-8")
    sig = "cycleFloatingMapDisplayMode()V"
    m = METHOD_RX(sig).search(src)
    if not m:
        raise RuntimeError(f"悬浮地图补丁找不到方法: {sig}")
    block = m.group(0)

    # 锚点：方法内 isFloatingMapMode() 的 move-result v0，紧跟着是取 prefs。
    # 在这两行之间插入 helper 调用，v0 保持原样供后续写 prefs 用。
    anchor = ("invoke-direct {p0}, Lcom/navi/tool/ui/extra/DesktopAssistantActivity;"
              "->isFloatingMapMode()Z\n\n"
              "    move-result v0\n")
    inject = (anchor
              + "\n"
              + "    # [MOD] 悬浮切换：切日历结束地图进程，切回地图重新唤醒\n"
              + "    invoke-static {p0, v0}, Lcom/navitool/bridge/MapFloating;"
                "->onCycle(Landroid/content/Context;Z)V\n"
              + "\n")
    if block.count(anchor) != 1:
        raise RuntimeError(f"悬浮地图补丁锚点不唯一: {block.count(anchor)} 处")
    new_block = block.replace(anchor, inject, 1)
    if new_block == block:
        raise RuntimeError(f"悬浮地图补丁未改变: {sig}")
    src = src[:m.start()] + new_block + src[m.end():]
    path.write_text(src, encoding="utf-8")
    return "悬浮切日历结束地图进程 / 切回重新唤醒（cycleFloatingMapDisplayMode 注入 1 处）"


# ------------------------------------------------------------------ 流程步骤
def unpack(apk, dest):
    if dest.exists():
        shutil.rmtree(dest)
    dest.parent.mkdir(parents=True, exist_ok=True)
    run([JAVA, "-Xmx4g", "-jar", APKTOOL, "d", "-f", "-o", dest, apk])
    return dest


def disassemble(dex_list, decoded):
    """清掉 apktool 从原包解出的库 smali，再按顺序反汇编脱壳 dex。"""
    for d in decoded.glob("smali*"):
        if d.is_dir():
            shutil.rmtree(d)
    cp = f"{DEXKIT}{os.pathsep}{APKTOOL}"
    made = []
    for i, it in enumerate(dex_list):
        name = "smali" if i == 0 else f"smali_classes{i + 1}"
        target = decoded / name
        run([JAVA, "-Xmx4g", "-cp", cp, "DexKit", "dis", it["dex"].path, target])
        made.append((name, len(list(target.rglob("*.smali")))))
    return made


def strip_shell(decoded):
    removed = []
    for rel in patches.SHELL_FILES:
        p = decoded / rel
        if p.exists():
            if p.is_dir():
                shutil.rmtree(p)
            else:
                p.unlink()
            removed.append(rel)
    # 兜底：assets 下任何 baiduprotect 残留
    for p in (decoded / "assets").glob("baiduprotect*"):
        if p.exists():
            p.unlink()
            removed.append(str(p.relative_to(decoded)))
    return removed


def fix_manifest(decoded):
    p = decoded / "AndroidManifest.xml"
    s = p.read_text(encoding="utf-8")
    if patches.APP_CLASS_TO in s:
        return "已经是 " + patches.APP_CLASS_TO
    if patches.APP_CLASS_FROM not in s:
        raise RuntimeError(f"清单里既没有 {patches.APP_CLASS_FROM} 也没有 "
                           f"{patches.APP_CLASS_TO}，Application 名变了，需要人工确认")
    p.write_text(s.replace(patches.APP_CLASS_FROM, patches.APP_CLASS_TO), encoding="utf-8")
    return f"{patches.APP_CLASS_FROM} -> {patches.APP_CLASS_TO}"


def retheme(decoded, name="车控"):
    """应用名与图标：名字统一成「车控」，图标换成原厂风格（青绿底色 + 白色方向盘）。

    原厂「本地设置 / 车辆设置」都是 #008476 纯色圆角方形 + 白色扁平线稿，没有渐变、
    没有阴影。这里照着做一套：自适应图标改 background/foreground，方形与圆形各一份
    兜底，全部是 vector，不依赖 png 密度。
    """
    icons = ROOT / "patch" / "icons"
    sp = decoded / "res" / "values" / "strings.xml"
    s = sp.read_text(encoding="utf-8")
    m = re.search(r'<string name="app_name">([^<]*)</string>', s)
    if not m:
        raise RuntimeError("strings.xml 里找不到 app_name，应用名改不了")
    old = m.group(1)
    if old != name:
        s = s[:m.start()] + f'<string name="app_name">{name}</string>' + s[m.end():]
        sp.write_text(s, encoding="utf-8")

    pairs = [
        ("ic_launcher_background.xml", "res/drawable/ic_launcher_background.xml"),
        ("ic_launcher_foreground.xml", "res/drawable/ic_launcher_foreground.xml"),
        ("ic_launcher_legacy.xml", "res/mipmap/ic_launcher.xml"),
        ("ic_launcher_round.xml", "res/mipmap/ic_launcher_round.xml"),
    ]
    for src_name, rel in pairs:
        src = icons / src_name
        if not src.exists():
            raise RuntimeError(f"图标素材缺失: {src}")
        dst = decoded / rel
        dst.parent.mkdir(parents=True, exist_ok=True)
        dst.write_text(src.read_text(encoding="utf-8"), encoding="utf-8")
    return f"{old} -> {name}，图标 {len(pairs)} 个"


def fix_yml(decoded, ver, suffix, vcode=None, vname=None):
    p = decoded / "apktool.yml"
    s = p.read_text(encoding="utf-8")
    m = re.search(r"versionName:\s*(\S+)", s)
    orig = m.group(1) if m else ver
    s = s.replace("- assets/baiduprotect1.jar\n", "").replace("- assets/baiduprotect2.jar\n", "")
    final_name = vname or f"{ver}{suffix}"
    s = re.sub(r"versionName:\s*\S+", f"versionName: {final_name}", s, count=1)
    if vcode is not None:
        s = re.sub(r"versionCode:\s*\d+", f"versionCode: {vcode}", s, count=1)
    p.write_text(s, encoding="utf-8")
    return orig, final_name


def build_apk(decoded, out_apk):
    if out_apk.exists():
        out_apk.unlink()
    run([JAVA, "-Xmx4g", "-jar", APKTOOL, "b", decoded, "-o", out_apk])


def align_sign(src_apk, out_apk):
    aligned = Path(src_apk).parent / "aligned.apk"
    if aligned.exists():
        aligned.unlink()
    if out_apk.exists():
        out_apk.unlink()
    run([ZIPALIGN, "-f", "-p", "4", src_apk, aligned])
    run([JAVA, "-jar", APKSIGNER, "sign",
         "--ks", KEYSTORE, "--ks-pass", f"pass:{KS_PASS}",
         "--ks-key-alias", KS_ALIAS, "--key-pass", f"pass:{KS_PASS}",
         "--min-sdk-version", "26",
         "--v1-signing-enabled", "true", "--v2-signing-enabled", "true",
         "--v4-signing-enabled", "false",
         "--out", out_apk, aligned])


# ------------------------------------------------------------------ 注入（18766 网关 + 车控页）
PROVIDER = ('<provider android:authorities="com.navitool.bridge" '
            'android:exported="false" '
            'android:name="com.navitool.bridge.BridgeProvider" />')
SERVICE = ('<service android:name="com.navitool.bridge.BridgeService" '
           'android:exported="false" />')
RECEIVER = ('<receiver android:name="com.navitool.bridge.BridgeBoot" android:exported="true">\n'
            '            <intent-filter>\n'
            '                <action android:name="android.intent.action.BOOT_COMPLETED" />\n'
            '                <action android:name="android.intent.action.QUICKBOOT_POWERON" />\n'
            '            </intent-filter>\n'
            '        </receiver>')
# 配置预置：独立 ContentProvider 启动钩子，首启把 assets 里的配置包解压到 /sdcard/navitool/
CONFIG_PROVIDER = ('<provider android:authorities="com.navitool.bridge.config" '
                   'android:exported="false" '
                   'android:name="com.navitool.bridge.ConfigAssets" />')
CONFIG_ASSETS_DIR = TOOLS / "navi_configs"
# 免费天气适配：Open-Meteo 替换掉 so 里取凭据的和风天气
FREE_WEATHER_JAVA = TOOLS / "FreeWeather.java"
# 桌面卡片那份，只有 --free-desk-weather 才编进去，避免 --no-weather 下带一堆死方法
FREE_WEATHER_DESK_JAVA = TOOLS / "FreeWeatherDesk.java"


def resolve_inject():
    """找注入源码：本工程 src/bridge 与 web/pages/car.html 就是唯一真相。"""
    live_src = BRIDGE_ROOT / "src" / "bridge" / "src" / "com" / "navitool" / "bridge"
    live_page = BRIDGE_ROOT / "web" / "pages" / "car.html"
    snap_src = SNAPSHOT / "bridge"
    snap_page = SNAPSHOT / "car.html"
    if live_src.is_dir() and any(live_src.glob("*.java")):
        return live_src, (live_page if live_page.exists() else None), f"本工程 {live_src}"
    if snap_src.is_dir() and any(snap_src.glob("*.java")):
        return snap_src, (snap_page if snap_page.exists() else None), f"本工程快照 {SNAPSHOT}"
    raise RuntimeError(
        "找不到注入源码。应在 "
        f"{live_src}，或快照 {snap_src}")


def build_bridge_dex(work, extra_java=None):
    """javac -> d8，产出 classes.dex。extra_java 可追加额外 .java 文件（如 ConfigAssets）。"""
    src, _, _ = resolve_inject()
    if not ANDROID_JAR.exists():
        raise RuntimeError(f"缺 android.jar: {ANDROID_JAR}（用环境变量 ANDROID_JAR 指定）")
    out, dexdir = work / "bridge_out", work / "bridge_dex"
    for d in (out, dexdir):
        if d.exists():
            shutil.rmtree(d)
        d.mkdir(parents=True)

    # rglob：dvr/ 子包里有记录仪代理（2026-11-25 从车控互联搬过来）
    javas = sorted(str(p) for p in src.rglob("*.java"))
    if extra_java:
        javas += [str(p) for p in extra_java if Path(p).exists()]
    run([JAVAC, "-encoding", "UTF-8", "-nowarn", "-source", "8", "-target", "8",
         "-bootclasspath", ANDROID_JAR, "-classpath", ANDROID_JAR,
         "-d", out] + javas)
    classes = sorted(str(p) for p in out.rglob("*.class"))
    run([D8, "--min-api", "26", "--lib", ANDROID_JAR, "--output", dexdir] + classes)
    dex = dexdir / "classes.dex"
    if not dex.exists():
        raise RuntimeError("d8 没产出 classes.dex")
    return dex, len(javas)


def inject_manifest(decoded, with_config=False):
    mf = decoded / "AndroidManifest.xml"
    s = mf.read_text(encoding="utf-8")
    m = re.search(r"<application\b[^>]*>", s)
    if not m:
        raise RuntimeError("清单里找不到 <application> 标签")
    add = []
    if "com.navitool.bridge.BridgeProvider" not in s:
        add.append(PROVIDER)
    if "com.navitool.bridge.BridgeService" not in s:
        add.append(SERVICE)
    if "com.navitool.bridge.BridgeBoot" not in s:
        add.append(RECEIVER)
    if with_config and "com.navitool.bridge.ConfigAssets" not in s:
        add.append(CONFIG_PROVIDER)
    if add:
        s = s[:m.end()] + "\n        " + "\n        ".join(add) + s[m.end():]
        mf.write_text(s, encoding="utf-8")
    return len(add)


def inject_page(decoded):
    _, page, _ = resolve_inject()
    assets = decoded / "assets"
    assets.mkdir(parents=True, exist_ok=True)
    # 记录仪页：18767 的 GET / 直接吐这份（dvr.DvrServer 读 assets/dvr.html）。
    # 原来跟着车控互联走，2026-11-25 记录仪代理并进车机助手后跟着搬过来。
    dvr = BRIDGE_ROOT / "web" / "pages" / "dvr.html"
    if dvr.exists():
        shutil.copyfile(dvr, assets / "dvr.html")
    if page is None:
        return None
    dst = assets / "carbridge.html"
    shutil.copyfile(page, dst)
    return dst.stat().st_size


def inject_store_json(decoded):
    """把商店清单放进 assets/store.json。

    商店页的清单原本要靠 native 取地址再联网拉，去 so 后这条链断了。清单本身只有
    几 KB，直接随包走；里面每条的 download_url 仍是 123 网盘直链，下载/解压不变。
    """
    src = ROOT / "analysis" / "0919configs_商店配置清单.json"
    if not src.exists():
        raise RuntimeError(f"商店清单不存在: {src}")
    assets = decoded / "assets"
    assets.mkdir(parents=True, exist_ok=True)
    dst = assets / "store.json"
    # 原厂清单里个别地址带尾部 #（CDN 分享链接的 fragment），下载器会原样拼请求，
    # 这里统一清掉，其余字段保持原样。
    data = json.loads(src.read_text(encoding="utf-8"))
    for it in data:
        u = (it.get("download_url") or "").strip()
        it["download_url"] = u.rstrip("#").strip()
    dst.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")
    return dst.stat().st_size


def inject_config_assets(decoded):
    """把 navi_configs/*.zip 塞进 assets/navi_configs/，供 ConfigAssets 首启解压。"""
    if not CONFIG_ASSETS_DIR.is_dir():
        raise RuntimeError(f"配置包目录不存在: {CONFIG_ASSETS_DIR}")
    srcs = sorted(CONFIG_ASSETS_DIR.glob("*.zip"))
    if not srcs:
        raise RuntimeError(f"配置包目录里没有 .zip: {CONFIG_ASSETS_DIR}")
    dst_dir = decoded / "assets" / "navi_configs"
    dst_dir.mkdir(parents=True, exist_ok=True)
    total = 0
    for p in srcs:
        shutil.copyfile(p, dst_dir / p.name)
        total += p.stat().st_size
    return len(srcs), total


def append_dex(apk_in, dex_path, apk_out):
    """把桥接 dex 作为下一个 classesN.dex 塞进去，不压缩。"""
    dex_bytes = Path(dex_path).read_bytes()
    with zipfile.ZipFile(apk_in) as zin, zipfile.ZipFile(apk_out, "w", zipfile.ZIP_DEFLATED) as zout:
        existing = set(zin.namelist())
        idx, name = 1, "classes.dex"
        while name in existing:
            idx += 1
            name = f"classes{idx}.dex"
        for info in zin.infolist():
            zout.writestr(info, zin.read(info.filename), compress_type=info.compress_type)
        zi = zipfile.ZipInfo(name)
        zi.compress_type = zipfile.ZIP_STORED
        zout.writestr(zi, dex_bytes)
    return name


# ------------------------------------------------------------------ 命令
def cmd_inspect(args):
    dex_dir = Path(args.dex_dir)
    dex_paths = sorted(dex_dir.glob("*.dex"))
    if not dex_paths:
        raise SystemExit(f"{dex_dir} 下没有 .dex")

    print(f"脱壳产物 {dex_dir}")
    print(f"{'文件':<28}{'大小':>10}{'类数':>7}  包前缀分布")
    print("-" * 78)
    keep, drop, infos = classify(dex_paths)
    for it in infos:
        d = it["dex"]
        buckets = {"com/navi": 0, "androidx": 0, "com/google": 0, "com/baidu": 0, "其它": 0}
        for cls, _ in d.classes:
            n = cls[1:]
            if n.startswith("com/navi/"):
                buckets["com/navi"] += 1
            elif n.startswith("androidx/"):
                buckets["androidx"] += 1
            elif n.startswith("com/google/"):
                buckets["com/google"] += 1
            elif n.startswith("com/baidu/"):
                buckets["com/baidu"] += 1
            else:
                buckets["其它"] += 1
        dist = " ".join(f"{k}={v}" for k, v in buckets.items() if v)
        role = "业务" if it["navi"] else ("库" if it["lib"] else "壳(丢弃)")
        print(f"{d.path.name:<28}{d.size:>10,}{len(d.classes):>7}  {role}  {dist}")

    print()
    print(f"保留 {len(keep)} 个 dex，丢弃 {len(drop)} 个")
    for i, it in enumerate(keep):
        name = "smali" if i == 0 else f"smali_classes{i + 1}"
        print(f"  {name:<18} <- {it['dex'].path.name}")

    main, sup, ok = check_chain(keep)
    print()
    if sup is None:
        print(f"{BAD} 没找到 {main}，这不是车机助手的业务 dex")
        return 1
    if ok:
        print(f"{OK} 继承链完整：{main} 的父类 {sup} 在被保留的 dex 里")
    else:
        print(f"{BAD} 缺库 dex：{main} 的父类 {sup} 不在保留的 dex 里")
        print("     直接打包会『点图标闪退且无日志』。补一个含该父类的库 dex 再来。")
        return 1

    print()
    print(f"{OK} 可以直接 build：python make.py build --dex \"{dex_dir}\"")
    return 0


def find_apk(ver):
    cands = sorted(SRC_APK.glob("*.apk"))
    if not cands:
        raise SystemExit(f"{SRC_APK} 里没有 .apk")
    for p in cands:
        try:
            b = apk_badging(p)
        except Exception:
            continue
        if b["name"] == ver:
            return p
    listing = []
    for p in cands:
        try:
            listing.append(f"  {p.name}  versionName={apk_badging(p)['name']}")
        except Exception:
            listing.append(f"  {p.name}  (读不出)")
    raise SystemExit(f"没找到 versionName={ver} 的原版包，可用的是:\n" + "\n".join(listing))


def cmd_build(args):
    # 这两个开关改的是同一条链路的两头：--no-weather 把刷新链整条空掉，
    # --free-desk-weather 改的是链上的 URL 和 JSON 解析。同时开会得到一堆
    # 永远不会被调用的补丁，白做还让人误以为桌面天气能显示。
    if args.no_weather and args.free_desk_weather:
        raise SystemExit(
            f"{BAD} --no-weather 与 --free-desk-weather 互斥\n"
            "    要桌面天气卡片：去掉 --no-weather，加 --free-desk-weather\n"
            "    不要桌面天气：只留 --no-weather（目的地播报不受影响）")
    need_tools()
    dex_dir = Path(args.dex_dir)
    dex_paths = sorted(dex_dir.glob("*.dex"))
    if not dex_paths:
        raise SystemExit(f"{dex_dir} 下没有 .dex")

    keep, drop, _ = classify(dex_paths)
    main, sup, ok = check_chain(keep)
    if not ok:
        print(f"{BAD} 继承链不完整，先跑 inspect 看清楚")
        return 1

    apk = Path(args.apk) if args.apk else find_apk(args.version)
    badging = apk_badging(apk)
    ver = args.version or badging["name"]

    # 构建目录必须 ASCII：apktool 在中文路径下编 res 会失败
    work = ASCII / f"build_{ver}"
    decoded = work / "apk_decoded"
    steps = str(8 + (0 if args.no_music_patch else 1) + (2 if args.inject else 0) + (1 if args.no_so else 0)
                + (1 if args.free_weather else 0)
                + (1 if args.free_desk_weather else 0)
                + (1 if args.no_seat_confirm else 0)
                + (1 if args.floating_map else 0)
                + (1 if args.no_weather else 0)
                + (1 if (args.inject and args.assets_config) else 0))

    inject_src = None
    if args.inject:
        src_dir, page, label = resolve_inject()
        inject_src = label
        if args.vcode is None:
            args.vcode = 25
        if args.vname is None:
            # 跟随上游源版本：19.10-m25，拉新后自动变（右上角标题显示这个值）
            args.vname = f"{ver}-m{args.vcode}"

    t0 = time.time()
    print(f"== 版本 {ver}  原包 {apk.name}  versionCode {badging['code']}")
    print(f"== 构建目录 {work}")
    if inject_src:
        print(f"== 注入源 {inject_src}")

    n = 1
    print(f"\n[{n}/{steps}] 解包")
    unpack(apk, decoded)

    n += 1
    print(f"[{n}/{steps}] 反汇编脱壳 dex")
    for name, cnt in disassemble(keep, decoded):
        print(f"      {name}: {cnt} 个 .smali")

    n += 1
    print(f"[{n}/{steps}] 补丁预检")
    rows = precheck(decoded, args.vin)
    fails = [(p, why) for p, _, hits, why in rows if hits != 1]
    for p, _, hits, why in rows:
        print(f"  {OK if hits == 1 else BAD} {p['tag']:<22} "
              f"{p['cls'].split('.')[-1]}.{p['sig'].split('(')[0]}")
    if fails:
        print(f"\n{BAD} {len(fails)} 处对不上，未改动任何文件：")
        for p, why in fails:
            print(f"      {p['tag']}: {why}")
        print("\n      去 smali 里确认方法是否改名/改签名，改 patches.py 后重跑。")
        if not args.force:
            return 1
        print(f"{WARN} --force，继续")

    n += 1
    print(f"[{n}/{steps}] 打补丁")
    apply(decoded, args.vin, rows)
    print(f"      生效 {len([r for r in rows if r[2] == 1])} 处")

    if not args.no_music_patch:
        n += 1
        print(f"[{n}/{steps}] 音源槽位：酷我 1/4 -> 云音乐（musicpatches.py）")
        for tag, detail in musicpatches.apply_music_patches(decoded, inject=args.inject):
            print(f"      {tag}  ({detail})")

    if args.no_so:
        n += 1
        print(f"[{n}/{steps}] 整块去除 so（native 全桩化 + 去 loadLibrary）")
        ns, nload, nskip = stub_all_native(decoded)
        print(f"      桩化 {ns} 个 native 方法，去掉 {nload} 处 loadLibrary")
        if nskip:
            print(f"      {WARN} 跳过 {len(nskip)} 项: {nskip[:3]}")

    if args.free_weather:
        n += 1
        print(f"[{n}/{steps}] 免费天气：目的地天气改走 Open-Meteo")
        print(f"      {apply_free_weather(decoded)}")

    if args.free_desk_weather:
        n += 1
        print(f"[{n}/{steps}] 免费天气（桌面卡片）：改走 Open-Meteo")
        print(f"      {apply_free_desk_weather(decoded)}")

    if args.no_seat_confirm:
        n += 1
        print(f"[{n}/{steps}] 去座椅应用配置弹窗：应用配置1/2/3 点击直接应用，文本改座位1/2/3")
        print(f"      {apply_no_seat_confirm(decoded)}")

    if args.floating_map:
        n += 1
        print(f"[{n}/{steps}] 悬浮地图切日历结束进程 / 切回唤醒")
        print(f"      {apply_floating_map(decoded)}")

    if args.no_weather:
        n += 1
        print(f"[{n}/{steps}] 停用桌面天气：不定位、不定时、不发网络请求")
        print(f"      {apply_no_weather(decoded)}")

    n += 1
    print(f"[{n}/{steps}] 去壳 + 清单 + 版本号")
    removed = strip_shell(decoded)
    print(f"      删除壳文件 {len(removed)} 项")
    print(f"      Application: {fix_manifest(decoded)}")
    orig, final_name = fix_yml(decoded, ver, args.suffix, args.vcode, args.vname)
    code_note = f" / code {args.vcode}" if args.vcode is not None else ""
    print(f"      versionName: {orig} -> {final_name}{code_note}")
    print(f"      名称/图标: {retheme(decoded)}")

    if args.inject:
        n += 1
        print(f"[{n}/{steps}] 注入 18766 网关")
        extra = []
        if args.assets_config:
            extra.append(TOOLS / "ConfigAssets.java")
        if args.free_weather:
            extra.append(FREE_WEATHER_JAVA)
        if args.free_desk_weather:
            extra.append(FREE_WEATHER_DESK_JAVA)
        dex, njava = build_bridge_dex(work, extra or None)
        print(f"      编译 {njava} 个 .java -> classes.dex {dex.stat().st_size:,} 字节")
        print(f"      清单加入 {inject_manifest(decoded, with_config=args.assets_config)} 项组件声明")
        size = inject_page(decoded)
        print(f"      assets/carbridge.html {size:,} 字节" if size else "      !! 没找到车控页")
        sj = inject_store_json(decoded)
        print(f"      assets/store.json {sj:,} 字节（商店清单，下载仍走 123 网盘）")
        if args.assets_config:
            n += 1
            print(f"[{n}/{steps}] 预置配置包到 assets")
            npkg, nsize = inject_config_assets(decoded)
            print(f"      {npkg} 个配置包 / {nsize:,} 字节 -> assets/navi_configs/")

    n += 1
    print(f"[{n}/{steps}] 打包")
    raw = work / "raw.apk"
    build_apk(decoded, raw)
    src_apk = raw
    if args.inject:
        n += 1
        print(f"[{n}/{steps}] 追加桥接 dex")
        withdex = work / "withdex.apk"
        name = append_dex(raw, dex, withdex)
        print(f"      已追加 {name}（不压缩）")
        src_apk = withdex

    n += 1
    print(f"[{n}/{steps}] 对齐 + 签名")
    suffix = "_bridge" if args.inject else args.suffix
    label = ver
    if args.inject and args.vname:
        label = re.sub(r"-(mod|bridge)$", "", args.vname)
    # 默认产物名不带原厂版本号/车型（命名约定见 README.local.md「出包命名约定」）
    default_name = (f"车控_m{args.vcode}.apk" if args.inject
                    else f"车控_mod_{label}{suffix}.apk")
    out_apk = Path(args.out) if args.out else (OUT / default_name)
    out_apk.parent.mkdir(parents=True, exist_ok=True)
    align_sign(src_apk, out_apk)
    print(f"      {out_apk}  {out_apk.stat().st_size:,} 字节")

    n += 1
    print(f"[{n}/{steps}] 自校验")
    rc = verify_apk(out_apk, decoded, quiet=False)
    print(f"\n用时 {time.time() - t0:.0f}s")
    if rc == 0:
        print(f"{OK} 出包完成：{out_apk}")
        print(f"      解包树留在 {decoded}")
    return rc


def verify_apk(apk, decoded=None, quiet=False):
    apk = Path(apk)
    bad = apk_badging(apk)
    n_dex = 0
    baidu = []
    with zipfile.ZipFile(apk) as z:
        names = z.namelist()
        n_dex = len([n for n in names if re.fullmatch(r"classes\d*\.dex", n)])
        baidu = [n for n in names if "baidu" in n.lower() or "baiduprotect" in n.lower()]
    app_cls = manifest_app_class(apk)
    xml_all = run([AAPT2, "dump", "xmltree", "--file", "AndroidManifest.xml", apk]).stdout
    injected = "com.navitool.bridge" in xml_all
    has_page = any(n == "assets/carbridge.html" for n in names)

    try:
        run([ZIPALIGN, "-c", "-p", "4", apk])
        align_ok = True
    except RuntimeError:
        align_ok = False

    try:
        run([JAVA, "-jar", APKSIGNER, "verify", apk])
        sign_ok = True
    except RuntimeError:
        sign_ok = False

    # 从产物 dex 反查补丁是否落地（自己抽 dex，不依赖解包树）
    landed = total = 0
    if True:
        tmp = Path(os.environ.get("TEMP", "/tmp")) / f"_verify_{int(time.time())}"
        try:
            with zipfile.ZipFile(apk) as z:
                for n in sorted(x for x in z.namelist() if re.fullmatch(r"classes\d*\.dex", x)):
                    (tmp / n).parent.mkdir(parents=True, exist_ok=True)
                    (tmp / n).write_bytes(z.read(n))
            dis = tmp / "smali"
            cp = f"{DEXKIT}{os.pathsep}{APKTOOL}"
            run([JAVA, "-Xmx2g", "-cp", cp, "DexKit", "dis", tmp / "classes.dex", dis])
            nb = dis / "com/navi/tool/utils/NativeBridge.smali"
            v19_gate = nb.exists() and "getCachedLicenseMask" in nb.read_text(
                encoding="utf-8", errors="ignore")
            for p in patches.PATCHES:
                if p.get("only_if") == "v19_gate" and not v19_gate:
                    continue
                f = dis / (p["cls"].replace(".", "/") + ".smali")
                if not f.exists():
                    continue
                total += 1
                src = f.read_text(encoding="utf-8")
                _, _, m = resolve_sig(p, src)
                if not m:
                    continue
                body = m.group(0)
                # apktool 打包会把 smali 注释丢掉，所以不能靠 [MOD] 标记判断。
                # stub 后的方法体特征：没有 invoke-、以 return 结尾、指令不超过 2 条。
                if p.get("body") is not None:
                    # body 补丁：重写后原方法特征串必不存在（如 Arrays;->asList）
                    if p.get("verify_absent") and p["verify_absent"] in body:
                        continue
                    landed += 1
                    continue
                ins = [ln.strip() for ln in body.splitlines()]
                ins = [ln for ln in ins if ln and not ln.startswith(
                    (".method", ".registers", ".locals", ".end", ".param", ".prologue", ".line", "#"))]
                if (not any(x.startswith("invoke-") for x in ins)
                        and len(ins) <= 2
                        and ins and ins[-1].startswith("return")):
                    landed += 1
        finally:
            shutil.rmtree(tmp, ignore_errors=True)

    if not quiet:
        print(f"      包名        {bad['pkg']}")
        print(f"      版本        {bad['name']} / code {bad['code']}")
        print(f"      Application {app_cls or '(清单里没写，用默认)'}")
        print(f"      dex         {n_dex} 个")
        print(f"      网关注入    {'是' if injected else '否'}"
              + (f"（清单组件 + 车控页{' + classes3.dex' if n_dex >= 3 else ''}）" if injected else ""))
        print(f"      壳残留      {len(baidu)} 项" + (f" {baidu[:3]}" if baidu else ""))
        print(f"      对齐        {'通过' if align_ok else '失败'}")
        print(f"      签名        {'通过' if sign_ok else '失败'}")
        if total:
            print(f"      补丁落地    {landed}/{total}")

    bad_issues = []
    if baidu:
        bad_issues.append(f"壳残留 {len(baidu)} 项")
    if app_cls and app_cls != patches.APP_CLASS_TO:
        bad_issues.append(f"Application 还是 {app_cls}")
    if not align_ok:
        bad_issues.append("4K 对齐失败")
    if not sign_ok:
        bad_issues.append("签名校验失败")
    if total and landed != total:
        bad_issues.append(f"补丁 {landed}/{total} 落地")
    if bad_issues:
        print(f"{BAD} " + "；".join(bad_issues))
        return 1
    return 0


def cmd_verify(args):
    need_tools()
    rc = verify_apk(args.apk)
    print(f"{OK} 校验通过" if rc == 0 else "")
    return rc


def cmd_sync(args):
    src = Path(args.decoded) if args.decoded else (ASCII / f"build_{args.version}" / "apk_decoded")
    dst = Path(args.to)
    if not src.exists():
        raise SystemExit(f"源目录不存在: {src}\n先跑 build")
    if not (src / "apktool.yml").exists():
        raise SystemExit(f"{src} 不像解包目录（没有 apktool.yml）")

    print(f"源 {src}")
    print(f"目标 {dst}")
    sub = ["smali", "smali_classes2", "smali_classes3", "res", "assets", "lib",
           "unknown", "build", "original", "AndroidManifest.xml", "apktool.yml"]
    plan = []
    for name in sub:
        s = src / name
        if s.exists():
            plan.append(name)
    print("将覆盖：" + ", ".join(plan))
    if not args.dry_run:
        dst.mkdir(parents=True, exist_ok=True)
        for name in plan:
            s, d = src / name, dst / name
            if s.is_dir():
                if d.exists():
                    shutil.rmtree(d)
                shutil.copytree(s, d)
            else:
                shutil.copy2(s, d)
        print(f"{OK} 已同步，注意桥接工程的 versionCode/versionName 由它自己的 build.py 决定")
    else:
        print("(dry-run，未写入)")
    return 0


def cmd_sync_inject(args):
    """把注入源码和车控页存一份快照到 src/。

    合并工程后源码已在 src/bridge 与 web/pages/car.html，正常不需要跑这一步。
    保留该子命令，供从外部工程（--from）临时导入时用。
    """
    root = Path(args.src_root) if args.src_root else BRIDGE_ROOT
    # 兼容两种布局：合并后的 src/bridge/... 与旧工程的 car/bridge/...
    cands = [
        root / "src" / "bridge" / "src" / "com" / "navitool" / "bridge",
        root / "car" / "bridge" / "src" / "com" / "navitool" / "bridge",
    ]
    live_src = next((p for p in cands if p.is_dir()), None)
    if live_src is None:
        raise SystemExit(f"找不到注入源码，试过：\n  " + "\n  ".join(str(p) for p in cands))
    live_page = root / "web" / "pages" / "car.html"
    if not live_page.exists():
        raise SystemExit(f"找不到 {live_page}")

    dst = SNAPSHOT / "bridge"
    dst.mkdir(parents=True, exist_ok=True)
    for old in dst.glob("*.java"):
        old.unlink()
    n = 0
    for f in sorted(live_src.glob("*.java")):
        shutil.copy2(f, dst / f.name)
        n += 1
    print(f"{OK} {n} 个 .java -> {dst}")

    shutil.copy2(live_page, SNAPSHOT / "car.html")
    print(f"{OK} car.html {live_page.stat().st_size:,} 字节 -> {SNAPSHOT / 'car.html'}")
    return 0


def main():
    ap = argparse.ArgumentParser(description="车机助手改版工程 · 统一构建入口")
    sub = ap.add_subparsers(dest="cmd", required=True)

    p = sub.add_parser("inspect", help="诊断脱壳 dex 能不能直接用")
    p.add_argument("dex_dir")
    p.set_defaults(func=cmd_inspect)

    p = sub.add_parser("build", help="出包（可带 18766 注入）")
    p.add_argument("--dex", dest="dex_dir", required=True, help="脱壳 dex 所在目录")
    p.add_argument("--apk", help="原版 APK，不给就按 --version 在 apk/ 里找")
    p.add_argument("--version", help="版本号，如 V18；不给就从原版包读")
    p.add_argument("--vin", default=patches.VIN_DEFAULT)
    p.add_argument("--suffix", default="-mod")
    p.add_argument("--out", help="产物路径")
    p.add_argument("--force", action="store_true", help="补丁有失败也继续")
    p.add_argument("--no-so", action="store_true",
                   help="整块去除 so：所有 native 方法桩化并去掉 loadLibrary，"
                        "用于 V19 这类 so 反篡改导致去壳即 SIGKILL 的版本")
    p.add_argument("--no-music-patch", action="store_true",
                   help="跳过 musicpatches.py（音源槽位换云音乐）。"
                        "V18 的汽水控制接口签名不同，用这个开关出纯车控包")
    p.add_argument("--inject", action="store_true",
                   help="注入 18766 网关：编译 bridge、加清单组件、塞车控页、追加 classes3.dex")
    p.add_argument("--assets-config", action="store_true",
                   help="随 --inject 打进配置预置（目标车型+通用 8 包，首启解压到 /sdcard/navitool/）")
    p.add_argument("--free-weather", action="store_true",
                   help="目的地天气改用 Open-Meteo 免费接口（无需 key，替代 so 里的和风天气凭据）")
    p.add_argument("--free-desk-weather", action="store_true",
                   help="桌面时钟卡片天气也改走 Open-Meteo（与 --no-weather 互斥）")
    p.add_argument("--no-seat-confirm", action="store_true",
                   help="关掉座椅记忆调用/保存的确认弹窗（点按钮直接执行，不再弹「确定应用配置」）")
    p.add_argument("--floating-map", action="store_true",
                   help="悬浮切到日历时冻结当前地图包，切回地图时重新唤醒（需配合 --inject）")
    p.add_argument("--no-weather", action="store_true",
                   help="彻底停用桌面天气：不定位、不定时、不发网络请求（保留目的地播报天气）")
    p.add_argument("--vcode", type=int, help="versionCode（默认保持原包；--inject 时 25）")
    p.add_argument("--vname", help="versionName（默认 <ver>-mod；--inject 时 18.73-mod）")
    p.set_defaults(func=cmd_build)

    p = sub.add_parser("verify", help="校验产物")
    p.add_argument("apk")
    p.set_defaults(func=cmd_verify)

    p = sub.add_parser("sync", help="把打好的解包树导出到指定目录")
    p.add_argument("--to", required=True, help="目标解包目录（会覆盖同名子目录）")
    p.add_argument("--decoded", help="默认 C:/Temp/mod_build/build_<version>/apk_decoded")
    p.add_argument("--version")
    p.add_argument("--dry-run", action="store_true")
    p.set_defaults(func=cmd_sync)

    p = sub.add_parser("sync-inject", help="把桥接工程注入源码存成本工程快照")
    p.add_argument("--from", dest="src_root", help="桥接工程根目录，默认 BRIDGE_ROOT")
    p.set_defaults(func=cmd_sync_inject)

    a = ap.parse_args()
    if a.cmd == "sync" and not a.decoded and not a.version:
        ap.error("sync 需要 --decoded 或 --version")
    try:
        sys.exit(a.func(a) or 0)
    except RuntimeError as e:
        print(f"{BAD} {e}")
        sys.exit(1)


if __name__ == "__main__":
    main()
