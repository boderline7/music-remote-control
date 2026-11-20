#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""车机助手改版 · 补丁表

每条补丁按「类全名 + 方法签名」定位，与 APK 版本号无关。
原厂升级后只要方法签名没变，这张表原样可用；签名变了 make.py 会报出是哪一条、命中了几个。

    cls   类全名，点分（不要 L...; 前缀）
    sig   方法签名，如 "getSystemVin()Ljava/lang/String;"
    ret   返回类型描述符
    val   返回的常量，按 ret 类型解释：
            "Z"                    -> "true" / "false"
            "I"                    -> "0x7ff" 等整数
            "Ljava/lang/String;"   -> 字符串字面量
            "[B"                   -> "null"
            "V"                    -> 忽略，方法体为空
          字符串里出现 {VIN} 时替换成 --vin 参数
    tag   报告里显示的中文名

方法体的修饰符、是否 static 都从原方法继承（只去掉 native），所以不需要在表里写。
"""

# 瑞虎9 的 17 位 VIN，改版后所有设备共用这一个值（授权网已断，暂无副作用）
VIN_DEFAULT = "LVVDB21B2ND000001"

# 离线授权应答：OK | full=1 | block=0 | 到期日
FAKE_LICENSE = "OK|1|0|2099-12-31"

PATCHES = [
    # ---------------- 1) 机型 / VIN ----------------
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="getSystemVin()Ljava/lang/String;", ret="Ljava/lang/String;",
         val="{VIN}", tag="机型/VIN 固定"),

    # ---------------- 2) VIP / 授权 ----------------
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="getLicenseMask(Landroid/content/Context;)I", ret="I",
         val="0x1fff", tag="授权掩码全开"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="fetchLicenseStatus(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
         # 19.00 起多一个 String 参数；两个签名都认，命中哪个用哪个
         sig_alt="fetchLicenseStatus(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
         ret="Ljava/lang/String;", val=FAKE_LICENSE, tag="授权状态离线应答"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="verifySignedLicense(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
         ret="Ljava/lang/String;", val=FAKE_LICENSE, tag="授权串校验离线应答"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="requestTrial(Ljava/lang/String;)Ljava/lang/String;", ret="Ljava/lang/String;",
         val="ERR|offline_mode", tag="试用请求离线拒绝"),
    dict(cls="com.navi.tool.MainActivity",
         sig="handleStartTrial(Ljava/lang/String;)V", ret="V",
         val=None, tag="试用入口短路"),

    # ---------------- 3) 联网门控 ----------------
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="requireInternet()Z", ret="Z", val="false", tag="联网要求关闭"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="requireVerify()Z", ret="Z", val="false", tag="校验要求关闭"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="checkAppUpdate(Landroid/content/Context;)V", ret="V",
         val=None, tag="更新检查关闭"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="uidSyncOnce(Ljava/lang/String;)Z", ret="Z", val="true", tag="UID 同步关闭"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="uploadLicense(Ljava/lang/String;Ljava/lang/String;)Z", ret="Z",
         val="true", tag="授权上传关闭"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="uploadPrefsData(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z",
         ret="Z", val="true", tag="配置上传关闭"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="downloadPrefsData(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)[B",
         ret="[B", val="null", tag="配置下载关闭"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="persistLicenseToken(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",
         ret="Ljava/lang/String;", val="OK", tag="token 不落库"),
    dict(cls="com.navi.tool.utils.SharedPreferencesUtils",
         sig="isLicenseUploaded(Landroid/content/Context;)Z", ret="Z",
         val="true", tag="跳过启动上传线程"),
    dict(cls="com.navi.tool.MainActivity",
         sig="showNetworkToastOnce()V", ret="V", val=None, tag="网络异常提示关闭"),
    dict(cls="com.navi.tool.MainActivity",
         sig="isNetworkAvailable()Z", ret="Z", val="true", tag="网络判断恒真"),
    # 首次启动的「使用说明 / 同意 / 不同意」弹窗，改版不需要点确认
    dict(cls="com.navi.tool.MainActivity",
         sig="showUsageInstructionDialog()V", ret="V", val=None,
         tag="首启使用说明弹窗关闭"),

    # ---------------- 4) 重签名后的自校验 ----------------
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="verifySignature(Landroid/content/Context;)Z", ret="Z",
         val="true", tag="签名自校验绕过"),
    dict(cls="com.navi.tool.utils.NativeBridge",
         sig="verifyTotp(Ljava/lang/String;J)Z", ret="Z",
         val="true", tag="OTP 自校验绕过"),

    # ---------------- 5) V19 新增启动门禁（仅 V19 生效） ----------------
    # V19 在 MainActivity.onCreate 早期调 initAccessGate()，起后台线程做
    # OTP/版本/网络校验，真机上表现为卡第一屏。空掉入口 + OTP 弹窗兜底。
    # only_if="v19_gate"：仅当脱壳源码含 V19 独有的 getCachedLicenseMask 时应用，
    # 18.62 没有该方法会自动跳过，保证 18.62 回归基准逐条目不变。
    dict(cls="com.navi.tool.MainActivity",
         sig="initAccessGate()V", ret="V", val=None,
         tag="V19 启动门禁空掉", only_if="v19_gate"),
    dict(cls="com.navi.tool.MainActivity",
         sig="showOtpDialog()V", ret="V", val=None,
         tag="V19 OTP弹窗空掉", only_if="v19_gate"),
]

# 去壳要删的文件（相对解包根目录），不存在的跳过。
# 注意：不要删 unknown/META-INF —— 那里是 48 个 androidx 的 *.version 标记，
# 运行时不读但会让产物和线上包不一致；壳自己的签名在 original/META-INF，apktool b 不会收。
SHELL_FILES = [
    "assets/baiduprotect-sec.dex",
    "assets/baiduprotect.md",
    "assets/baiduprotect1.i.dex",
    "assets/baiduprotect1.jar",
    "assets/baiduprotect2.i.dex",
    "assets/baiduprotect2.jar",
    "assets/libbaiduprotect_x86_64",
    "lib/arm64-v8a/libbaiduprotect.so",
]

# 清单里要换掉的 Application（原 -> 新）
APP_CLASS_FROM = "com.sagittarius.v6.StubApplication"
APP_CLASS_TO = "com.navi.tool.NaviApplication"
