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
    body  可选，smali 行列表：整方法体替换（逻辑补丁用），此时 ret 仅作占位
    regs  可选，body 补丁的 .registers 值（默认按参数宽度算，不够时必须指定）
    verify_absent  可选，body 补丁打包后自校验用的特征串（重写后必不存在）
    tag   报告里显示的中文名

方法体的修饰符、是否 static 都从原方法继承（只去掉 native），所以不需要在表里写。
"""

# 目标车型 的 17 位 VIN，改版后所有设备共用这一个值（授权网已断，暂无副作用）
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
    # V18 没有该方法会自动跳过，保证 V18 回归基准逐条目不变。
    dict(cls="com.navi.tool.MainActivity",
         sig="initAccessGate()V", ret="V", val=None,
         tag="V19 启动门禁空掉", only_if="v19_gate"),
    dict(cls="com.navi.tool.MainActivity",
         sig="showOtpDialog()V", ret="V", val=None,
         tag="V19 OTP弹窗空掉", only_if="v19_gate"),

    # ---------------- 6) 挂挡音效兼容裸 D 挡（原厂适配缺陷） ----------------
    # 原厂 handleGearChange 的音效白名单只认 P/R/N/D1；目标车型 前进挡统一上报裸 "D"
    # （carinfo/c.java: value 4->"D"，5 才是 "D1"），导致 D→R 不响、挂 D 不响、
    # P 挡自动解童锁失效。整方法体重写：
    #   旧挡判断放宽为 P/R/N 或 startsWith("D")；
    #   新挡 D 与 D1 都播 D1.mp3；童锁解锁的旧挡判断同步放宽。
    # verify_absent：原方法体含 Arrays;->asList，重写后必无，用于打包后自校验。
    dict(cls="com.navi.tool.service.CarSoundManager",
         sig="handleGearChange(Ljava/lang/String;Ljava/lang/String;)V", ret="V",
         regs=10, verify_absent="Arrays;->asList",
         body=[
             "iget-object v0, p0, Lcom/navi/tool/service/CarSoundManager;->prefs:Landroid/content/SharedPreferences;",
             "const-string v1, \"child_lock_reminder_enabled\"",
             "const/4 v2, 0x0",
             "invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z",
             "move-result v0",
             "if-eqz v0, :cond_master",
             "iget-object v0, p0, Lcom/navi/tool/service/CarSoundManager;->prefs:Landroid/content/SharedPreferences;",
             "const-string v1, \"auto_child_unlock_by_p\"",
             "invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z",
             "move-result v0",
             "if-eqz v0, :cond_master",
             "iget-object v0, p0, Lcom/navi/tool/service/CarSoundManager;->carInfoRepo:Lcom/navi/tool/carinfo/c;",
             "iget-object v0, v0, Lcom/navi/tool/carinfo/c;->s:Landroidx/lifecycle/g0;",
             "invoke-virtual {v0}, Landroidx/lifecycle/f0;->d()Ljava/lang/Object;",
             "move-result-object v0",
             "check-cast v0, Ljava/lang/String;",
             "const-string v1, \"\\u9501\\u5b9a\"",
             "invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :cond_master",
             "const-string v1, \"P\"",
             "invoke-virtual {v1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :cond_master",
             "if-eqz p1, :cond_master",
             "const-string v1, \"D\"",
             "invoke-virtual {p1, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z",
             "move-result v0",
             "if-nez v0, :cond_unlock",
             "const-string v1, \"N\"",
             "invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-nez v0, :cond_unlock",
             "const-string v1, \"R\"",
             "invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :cond_master",
             ":cond_unlock",
             "invoke-direct {p0}, Lcom/navi/tool/service/CarSoundManager;->toggleChildLock()V",
             ":cond_master",
             "iget-object v0, p0, Lcom/navi/tool/service/CarSoundManager;->prefs:Landroid/content/SharedPreferences;",
             "const-string v6, \"sound_gear_master_enabled\"",
             "invoke-interface {v0, v6, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z",
             "move-result v0",
             "if-nez v0, :cond_sound",
             "return-void",
             ":cond_sound",
             "const-string v1, \"P\"",
             "invoke-virtual {v1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :cond_r",
             "if-eqz p1, :cond_r",
             "const-string v1, \"D\"",
             "invoke-virtual {p1, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z",
             "move-result v0",
             "if-nez v0, :play_p",
             "const-string v1, \"R\"",
             "invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-nez v0, :play_p",
             "const-string v1, \"N\"",
             "invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :cond_r",
             ":play_p",
             "const-string p1, \"sound_gear_p_enabled\"",
             "const-string p2, \"P.mp3\"",
             "invoke-direct {p0, p1, p2}, Lcom/navi/tool/service/CarSoundManager;->playSound(Ljava/lang/String;Ljava/lang/String;)V",
             "return-void",
             ":cond_r",
             "if-eqz p1, :ret_end",
             "const-string v1, \"P\"",
             "invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-nez v0, :old_ok",
             "const-string v1, \"R\"",
             "invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-nez v0, :old_ok",
             "const-string v1, \"N\"",
             "invoke-virtual {v1, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-nez v0, :old_ok",
             "const-string v1, \"D\"",
             "invoke-virtual {p1, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z",
             "move-result v0",
             "if-eqz v0, :ret_end",
             ":old_ok",
             "const-string v1, \"R\"",
             "invoke-virtual {v1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :cond_n",
             "const-string p1, \"sound_gear_r_enabled\"",
             "const-string p2, \"R.mp3\"",
             "invoke-direct {p0, p1, p2}, Lcom/navi/tool/service/CarSoundManager;->playSound(Ljava/lang/String;Ljava/lang/String;)V",
             "return-void",
             ":cond_n",
             "const-string v1, \"N\"",
             "invoke-virtual {v1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :cond_d",
             "const-string p1, \"sound_gear_n_enabled\"",
             "const-string p2, \"N.mp3\"",
             "invoke-direct {p0, p1, p2}, Lcom/navi/tool/service/CarSoundManager;->playSound(Ljava/lang/String;Ljava/lang/String;)V",
             "return-void",
             ":cond_d",
             "const-string v1, \"D\"",
             "invoke-virtual {v1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-nez v0, :play_d",
             "const-string v1, \"D1\"",
             "invoke-virtual {v1, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z",
             "move-result v0",
             "if-eqz v0, :ret_end",
             ":play_d",
             "const-string p1, \"sound_gear_d1_enabled\"",
             "const-string p2, \"D1.mp3\"",
             "invoke-direct {p0, p1, p2}, Lcom/navi/tool/service/CarSoundManager;->playSound(Ljava/lang/String;Ljava/lang/String;)V",
             ":ret_end",
             "return-void",
         ],
         tag="挂挡音效兼容裸D挡"),

    # ---------------- 7) 助手不再往桌面塞快捷方式 ----------------
    # 桌面助手 / 权限助手 / 文件助手三个入口本来是「开启后在桌面多一个图标」，
    # 图标即 com.navi.tool.FileAssistantActivity / PermissionAssistantActivity /
    # DesktopAssistantStubActivity 这三个跳板 Activity（点开转真实页面后 finish）。
    # 用户要求：开关照旧，但别在桌面留图标 —— 三个方法体统一重写成「永久禁用该组件」，
    # 只做一次（已经是 DISABLED 就不再调 PackageManager），不弹任何提示。
    # 功能本身不受影响：车机助手内部的入口直接开 DesktopAssistantActivity /
    # FileManagerActivity / PermissionManagerActivity，不经过这三个跳板。
    dict(cls="com.navi.tool.ui.extra.AdditionalFeaturesFragment",
         sig="setFileAssistantComponentEnabled(Z)V", ret="V",
         regs=7, verify_absent="ToastHelper;->showLong",
         body=[
             ":try_start_0",
             "new-instance v0, Landroid/content/ComponentName;",
             "invoke-virtual {p0}, Landroidx/fragment/app/z;->requireContext()Landroid/content/Context;",
             "move-result-object v1",
             "const-class v2, Lcom/navi/tool/FileAssistantActivity;",
             "invoke-direct {v0, v1, v2}, Landroid/content/ComponentName;-><init>(Landroid/content/Context;Ljava/lang/Class;)V",
             "invoke-virtual {p0}, Landroidx/fragment/app/z;->requireContext()Landroid/content/Context;",
             "move-result-object v1",
             "invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;",
             "move-result-object v1",
             "invoke-virtual {v1, v0}, Landroid/content/pm/PackageManager;->getComponentEnabledSetting(Landroid/content/ComponentName;)I",
             "move-result v2",
             "const/4 v3, 0x2",
             "if-eq v2, v3, :cond_hidden",
             "const/4 v4, 0x1",
             "invoke-virtual {v1, v0, v3, v4}, Landroid/content/pm/PackageManager;->setComponentEnabledSetting(Landroid/content/ComponentName;II)V",
             ":cond_hidden",
             ":try_end_0",
             ".catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0",
             "return-void",
             ":catch_0",
             "move-exception v0",
             "invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V",
             "return-void",
         ],
         tag="文件助手不留桌面图标"),
    dict(cls="com.navi.tool.ui.extra.AdditionalFeaturesFragment",
         sig="setPermissionAssistantComponentEnabled(Z)V", ret="V",
         regs=7, verify_absent="ToastHelper;->showLong",
         body=[
             ":try_start_0",
             "new-instance v0, Landroid/content/ComponentName;",
             "invoke-virtual {p0}, Landroidx/fragment/app/z;->requireContext()Landroid/content/Context;",
             "move-result-object v1",
             "const-class v2, Lcom/navi/tool/PermissionAssistantActivity;",
             "invoke-direct {v0, v1, v2}, Landroid/content/ComponentName;-><init>(Landroid/content/Context;Ljava/lang/Class;)V",
             "invoke-virtual {p0}, Landroidx/fragment/app/z;->requireContext()Landroid/content/Context;",
             "move-result-object v1",
             "invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;",
             "move-result-object v1",
             "invoke-virtual {v1, v0}, Landroid/content/pm/PackageManager;->getComponentEnabledSetting(Landroid/content/ComponentName;)I",
             "move-result v2",
             "const/4 v3, 0x2",
             "if-eq v2, v3, :cond_hidden",
             "const/4 v4, 0x1",
             "invoke-virtual {v1, v0, v3, v4}, Landroid/content/pm/PackageManager;->setComponentEnabledSetting(Landroid/content/ComponentName;II)V",
             ":cond_hidden",
             ":try_end_0",
             ".catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0",
             "return-void",
             ":catch_0",
             "move-exception v0",
             "invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V",
             "return-void",
         ],
         tag="权限助手不留桌面图标"),
    dict(cls="com.navi.tool.ui.extra.DesktopAssistantFragment",
         sig="toggleDesktopIcon(Z)V", ret="V",
         regs=7, verify_absent="ToastHelper;->showLong",
         body=[
             ":try_start_0",
             "new-instance v0, Landroid/content/ComponentName;",
             "invoke-virtual {p0}, Landroidx/fragment/app/z;->requireContext()Landroid/content/Context;",
             "move-result-object v1",
             "const-class v2, Lcom/navi/tool/DesktopAssistantStubActivity;",
             "invoke-direct {v0, v1, v2}, Landroid/content/ComponentName;-><init>(Landroid/content/Context;Ljava/lang/Class;)V",
             "invoke-virtual {p0}, Landroidx/fragment/app/z;->requireContext()Landroid/content/Context;",
             "move-result-object v1",
             "invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;",
             "move-result-object v1",
             "invoke-virtual {v1, v0}, Landroid/content/pm/PackageManager;->getComponentEnabledSetting(Landroid/content/ComponentName;)I",
             "move-result v2",
             "const/4 v3, 0x2",
             "if-eq v2, v3, :cond_hidden",
             "const/4 v4, 0x1",
             "invoke-virtual {v1, v0, v3, v4}, Landroid/content/pm/PackageManager;->setComponentEnabledSetting(Landroid/content/ComponentName;II)V",
             ":cond_hidden",
             ":try_end_0",
             ".catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0",
             "return-void",
             ":catch_0",
             "move-exception v0",
             "invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V",
             "return-void",
         ],
         tag="桌面助手不留桌面图标"),

    # ---------------- 11) 商店清单改读本地（下载仍走 123 网盘） ----------------
    # 原厂 fetchStoreConfigs() 两次跳转：getStoreUrl（native）→ 返回 JSON 地址 → 拉 JSON。
    # 去 so 后 getStoreUrl 被桩成空串，商店页直接「商城地址不可用」。
    # 这里跳过两次网络，直接从 assets/store.json 取清单交给 parseAndDisplayJson()；
    # 清单里的 download_url 还是 123 网盘 CDN 直链，点「下载并解压」照旧走原厂下载器。
    # parseAndDisplayJson 是同类 private 方法，DEX 层必须用 invoke-direct。
    dict(cls="com.navi.tool.ui.main.StoreServiceFragment",
         sig="fetchStoreConfigs()V", ret="V",
         regs=7,
         verify_absent="getStoreUrl",
         body=[
             "invoke-virtual {p0}, Landroidx/fragment/app/z;->getContext()Landroid/content/Context;",
             "move-result-object v0",
             "invoke-static {v0}, Lcom/navitool/bridge/StoreData;->json(Landroid/content/Context;)Ljava/lang/String;",
             "move-result-object v0",
             "invoke-direct {p0, v0}, Lcom/navi/tool/ui/main/StoreServiceFragment;->parseAndDisplayJson(Ljava/lang/String;)V",
             "return-void",
         ],
         tag="商店清单改读本地 123 网盘直链"),

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
