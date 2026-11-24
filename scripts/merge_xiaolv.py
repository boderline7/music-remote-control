#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""把小绿传（com.yuhan.xiaolvchuan）并进桥接包（com.luna.music.car）。

为什么不做成两个 APK 各装一次：车机上多一个包就多一个自启、多一份签名升级负担，
而小绿传的 HTTP 服务天然该跟桥接包同生共死。包名必须保持 com.luna.music.car——
车机助手的音源表按包名认「汽水音乐车机版」（source 5），改了就掉出候选列表。

链路：
    Gradle 出 lunastub-release.apk → apktool 解包 → 注入小绿传的 smali/res/assets
    → 改清单（入口换成小绿传，设置跳桥接页）→ apktool 回编译 → zipalign → apksigner

不重写小绿传的代码：367 个 smali 类原样搬过去，只把 R 引用从自己的包名指到
com.luna.music.car.R（同一个 APK 里只有一个 R）。这样它的功能一行没变。

用法：
    python scripts/merge_xiaolv.py            # 用现有 Gradle 产物合并
    python scripts/merge_xiaolv.py --gradle   # 先跑一次 Gradle 再合并
"""
from __future__ import annotations

import os
import re
import shutil
import subprocess
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent

# ---- 输入 ----
BASE_APK = ROOT / "music/cloudmusic-car/lunastub/build/outputs/apk/release/lunastub-release.apk"
XIAOLV = Path(os.environ.get(
    "XIAOLV", r"C:/Users/Administrator/WorkBuddy/2026-11-02-15-17-13/xiaolvzhuan"))
XSRC = XIAOLV / "02-src/app"

# ---- 工具 ----
JB = Path(os.environ.get("JB", "D:/开发/tools/jdk/jdk-17.0.2"))
BT = Path(os.environ.get("BT", "D:/开发/tools/android-sdk/build-tools/34.0.0"))
JAVA = JB / "bin/java.exe"
APKTOOL = ROOT / "patch/apktool.jar"
ZIPALIGN = BT / "zipalign.exe"
APKSIGNER = BT / "lib/apksigner.jar"

# ---- 输出 ----
# apktool 在中文路径下编资源会报 Illegal byte sequence，工作目录必须是纯 ASCII
WORK = Path(os.environ.get("MERGE_WORK", "C:/Temp/xiaolv_merge"))
DIST = ROOT / "dist"
OUT_NAME = "汽水音乐_桥接包_含文件传输_v2.10.29.apk"

# ---- 签名（与 Gradle 同一把钥匙，才能覆盖安装） ----
KEYSTORE = ROOT / "keystore/carremote.jks"
KS_ALIAS = "carremote"
KS_PASS = "CarRemote2026"

# 桥接包自己的包名，不能动
PKG = "com.luna.music.car"
# 小绿传的包名：类包名保留原样，只改 R 引用
XLV_PKG = "com.yuhan.xiaolvchuan"


def log(step: str, msg: str = "") -> None:
    print(f"[{step}] {msg}" if msg else f"[{step}]")


def run(cmd: list[str], quiet: bool = True) -> subprocess.CompletedProcess:
    p = subprocess.run([str(c) for c in cmd], capture_output=True, text=True,
                       encoding="utf-8", errors="replace")
    if p.returncode != 0:
        print(p.stdout[-4000:] if p.stdout else "")
        print(p.stderr[-4000:] if p.stderr else "")
        raise SystemExit(f"命令失败({p.returncode})：{' '.join(str(c) for c in cmd)}")
    if not quiet and p.stdout:
        print(p.stdout[-1500:])
    return p


def java(*args: str) -> None:
    run([JAVA, *args])


# ------------------------------------------------------------------ 注入
def inject_smali(dec: Path) -> int:
    """小绿传的 367 个 smali 类 → smali_classes2，避免和桥接包自己的 dex 混在一起。"""
    src = XSRC / "smali"
    dst = dec / "smali_classes2"
    if dst.exists():
        shutil.rmtree(dst)
    shutil.copytree(src, dst)

    # 同一个 APK 只有一个 R，小绿传编译时写死的 R 引用要指到桥接包的 R 上
    old = "L" + XLV_PKG.replace(".", "/") + "/R$"
    new = "L" + PKG.replace(".", "/") + "/R$"
    changed = 0
    for f in dst.rglob("*.smali"):
        s = f.read_text(encoding="utf-8", errors="surrogateescape")
        if old in s:
            f.write_text(s.replace(old, new), encoding="utf-8", errors="surrogateescape")
            changed += 1

    # 冲突自检：桥接包自己的类不能被覆盖
    own = {p.relative_to(dec / "smali").as_posix()
           for p in (dec / "smali").rglob("*.smali")}
    dup = [p.relative_to(dst).as_posix() for p in dst.rglob("*.smali")
           if p.relative_to(dst).as_posix() in own]
    if dup:
        raise SystemExit(f"类冲突（{len(dup)} 个）：{dup[:5]}")
    log("smali", f"{len(list(dst.rglob('*.smali')))} 个类，{changed} 个文件改了 R 引用")
    return changed


def inject_res(dec: Path) -> None:
    """资源整棵树搬过去。桥接包自己没有资源（只有空的 public.xml），不存在 ID 撞车。"""
    dst_res = dec / "res"
    copied = 0
    for f in (XSRC / "res").rglob("*"):
        if not f.is_file():
            continue
        rel = f.relative_to(XSRC / "res")
        out = dst_res / rel
        if out.exists() and out.name != "public.xml":
            raise SystemExit(f"资源重名，合并会互相覆盖：{rel}")
        out.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(f, out)
        copied += 1
    log("res", f"{copied} 个资源文件（public.xml 固定了原来的 0x7f.. ID）")


def inject_assets(dec: Path) -> None:
    dst = dec / "assets"
    dst.mkdir(parents=True, exist_ok=True)
    copied = 0
    for f in (XSRC / "assets").rglob("*"):
        if not f.is_file():
            continue
        rel = f.relative_to(XSRC / "assets")
        out = dst / rel
        if out.exists():
            raise SystemExit(f"assets 重名：{rel}")
        out.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(f, out)
        copied += 1
    log("assets", f"{copied} 个（web.html + 字体）")


# ------------------------------------------------------------------ 清单
PERMS = [
    "android.permission.ACCESS_WIFI_STATE",
    "android.permission.WAKE_LOCK",
    "android.permission.READ_EXTERNAL_STORAGE",
    "android.permission.WRITE_EXTERNAL_STORAGE",
    "android.permission.REQUEST_INSTALL_PACKAGES",
    "android.permission.QUERY_ALL_PACKAGES",
]

XLV_TAIL = f"""
        <!-- 小绿传：车机端提供 HTTP 收发文件的入口，右上角「设置」跳 .Main -->
        <activity android:name="{XLV_PKG}.MainActivity" android:configChanges="keyboardHidden|orientation|screenSize" android:exported="true" android:screenOrientation="landscape">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.LAUNCHER"/>
                <category android:name="android.intent.category.LEANBACK_LAUNCHER"/>
            </intent-filter>
        </activity>
        <provider android:name="{XLV_PKG}.FileProviderLite" android:authorities="{XLV_PKG}.files" android:exported="false" android:grantUriPermissions="true"/>
"""


def patch_manifest(dec: Path) -> None:
    p = dec / "AndroidManifest.xml"
    s = p.read_text(encoding="utf-8")

    # 1) 权限：小绿传原来那套（读写外置存储、装 APK、枚举已装应用）
    add = "".join(f'    <uses-permission android:name="{x}"/>\n' for x in PERMS
                  if x not in s)
    s = s.replace("    <application", add + "\n    <application", 1)

    # 2) application：图标 / 主题
    #    label 与 icon 都保持桥接包原样：车机助手按包名认音源，桌面上也该是「汽水音乐」
    #    和桥接包那颗图标（@android:drawable/ic_media_play）。小绿传的 mipmap/ic_launcher
    #    照样打进去给它的界面用，但桌面图标不用它。
    assert 'android:label="汽水音乐"' in s, "application label 不匹配"
    assert 'android:icon="@android:drawable/ic_media_play"' in s, "application icon 不匹配"
    s = re.sub(r'android:icon="[^"]*"',
               'android:icon="@android:drawable/ic_media_play"', s, count=1)
    s = s.replace("<application ", '<application android:theme="@style/AppTheme" ', 1)

    # 3) 桥接页不再是入口：去掉它的 LAUNCHER，只留「设置」这条显式跳转
    m = re.search(r'(<activity[^>]*android:name="' + re.escape(PKG) + r'\.Main"[^>]*>)'
                  r'\s*<intent-filter>.*?</intent-filter>\s*</activity>', s, re.S)
    assert m, "没找到桥接页的 LAUNCHER 入口"
    head = m.group(1)
    if not head.endswith("/>"):
        head = head[:-1].rstrip() + "/>"
    s = s[:m.start()] + head + s[m.end():]

    # 4) 小绿传的 Activity / FileProvider
    assert "    </application>" in s
    s = s.replace("    </application>", XLV_TAIL + "    </application>", 1)

    p.write_text(s, encoding="utf-8")
    log("manifest", "入口 = 小绿传 MainActivity，设置 = com.luna.music.car.Main")


# ------------------------------------------------------------------ 设置入口
SETTINGS_BTN = ('            <TextView android:textSize="@dimen/btn_text" '
                'android:textStyle="bold" android:textColor="@color/accent_hi" '
                'android:gravity="center" android:background="@drawable/btn_plain" '
                'android:paddingLeft="10.0dip" android:paddingRight="10.0dip" '
                'android:clickable="true" android:minWidth="56.0dip" android:layout_width="wrap_content" '
                'android:layout_height="@dimen/btn_h" android:layout_marginLeft="8.0dip" '
                'android:onClick="openSettings" android:text="设置" />')


def patch_layout(dec: Path) -> None:
    """右上角加「设置」：跟在「换端口」「dpi」后面，同一行的最右侧。"""
    p = dec / "res/layout/act_main.xml"
    s = p.read_text(encoding="utf-8")
    m = re.search(r'<TextView[^>]*android:id="@id/btn_dpi"[^>]*/>', s)
    assert m, "没找到 dpi 按钮，布局结构变了"
    s = s[:m.end()] + "\n" + SETTINGS_BTN + s[m.end():]
    p.write_text(s, encoding="utf-8")
    log("layout", "act_main.xml 右上角加了「设置」")


OPEN_SETTINGS = """
.method public openSettings(Landroid/view/View;)V
    .locals 2

    :try_start_0
    new-instance v0, Landroid/content/Intent;

    const-class v1, Lcom/luna/music/car/Main;

    invoke-direct {v0, p0, v1}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Landroid/app/Activity;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    invoke-virtual {v0}, Ljava/lang/Throwable;->printStackTrace()V

    return-void
.end method
"""


def patch_activity(dec: Path) -> None:
    p = dec / "smali_classes2" / XLV_PKG.replace(".", "/") / "MainActivity.smali"
    s = p.read_text(encoding="utf-8", errors="surrogateescape")
    if "openSettings(Landroid/view/View;)V" in s:
        log("activity", "已经有 openSettings，跳过")
        return
    assert s.rstrip().endswith(".end method"), "MainActivity.smali 结尾不是 .end method"
    p.write_text(s.rstrip("\n") + "\n" + OPEN_SETTINGS,
                 encoding="utf-8", errors="surrogateescape")
    log("activity", "MainActivity 追加 openSettings()，跳 com.luna.music.car.Main")


# ------------------------------------------------------------------ 构建
def patch_exit(dec: Path) -> None:
    """小绿传的「退出」最后一步是 Process.killProcess(myPid)。合并后这个进程还扛着
    18764（音乐遥控）和 18767（记录仪）两个常驻服务，杀了等于把桥接一起关掉。
    这里只去掉那一下杀进程，Hub.stop() 和 finishAndRemoveTask() 保留——界面照旧退干净。"""
    p = dec / "smali_classes2" / XLV_PKG.replace(".", "/") / "MainActivity$21.smali"
    s = p.read_text(encoding="utf-8", errors="surrogateescape")
    old = ("    invoke-static {}, Landroid/os/Process;->myPid()I\n\n"
           "    move-result v0\n\n"
           "    invoke-static {v0}, Landroid/os/Process;->killProcess(I)V\n")
    if old in s:
        s = s.replace(old, "    # 合并包：不杀进程，桥接服务要接着跑\n")
        p.write_text(s, encoding="utf-8", errors="surrogateescape")
        log("exit", "「退出」不再 killProcess，只退界面")
    elif "killProcess" not in s:
        log("exit", "已经没有 killProcess，跳过")
    else:
        raise SystemExit("退出逻辑变了，先看一眼 MainActivity$21.smali")


# ------------------------------------------------------------------ 服务开关
XLV_DIR = XLV_PKG.replace(".", "/")


def _u(s: str) -> str:
    """smali 里的中文统一写成 \\uXXXX，跨工具链不依赖文件编码。"""
    return "".join(c if ord(c) < 128 else "\\u%04x" % ord(c) for c in s)


SVC_READ = """    # SVC_TOGGLE_V1：文件传输服务开关，状态存 SharedPreferences（默认开）
    invoke-direct {p0}, Lcom/yuhan/xiaolvchuan/MainActivity;->prefs()Landroid/content/SharedPreferences;
    move-result-object v0

    const-string v1, "svc_on"

    const/4 v2, 0x1

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z
    move-result v0

"""

START_GUARD = SVC_READ + """    if-nez v0, :svc_on

    # 关着：不启动 socket，只把界面刷成关闭态
    invoke-direct {p0}, Lcom/yuhan/xiaolvchuan/MainActivity;->showAddress()V

    return-void

    :svc_on
"""

SHOW_GUARD = SVC_READ + """    if-nez v0, :addr_on

    iget-object v0, p0, Lcom/yuhan/xiaolvchuan/MainActivity;->tvUrl:Landroid/widget/TextView;

    const-string v1, "@OFF_TITLE@"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Lcom/yuhan/xiaolvchuan/MainActivity;->tvStatus:Landroid/widget/TextView;

    const-string v1, "@OFF_HINT@"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Lcom/yuhan/xiaolvchuan/MainActivity;->tvStatus:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    iget-object v0, p0, Lcom/yuhan/xiaolvchuan/MainActivity;->ivQr:Landroid/widget/ImageView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setImageBitmap(Landroid/graphics/Bitmap;)V

    iget-object v0, p0, Lcom/yuhan/xiaolvchuan/MainActivity;->btnExit:Landroid/widget/TextView;

    const-string v1, "@BTN_OFF@"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void

    :addr_on
    iget-object v0, p0, Lcom/yuhan/xiaolvchuan/MainActivity;->btnExit:Landroid/widget/TextView;

    const-string v1, "@BTN_ON@"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

"""

NEW_EXIT = """\
.method private exitApp()V
    .locals 4

    # SVC_TOGGLE_V1：这个按钮不再退出 App（进程还扛着 18764/18767 两个常驻服务），
    # 只开关小绿传自己的文件传输服务；状态写 svc_on，下次进来照旧保持。
    invoke-direct {p0}, Lcom/yuhan/xiaolvchuan/MainActivity;->prefs()Landroid/content/SharedPreferences;
    move-result-object v0

    const-string v1, "svc_on"

    const/4 v2, 0x1

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z
    move-result v3

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;
    move-result-object v0

    const/4 v2, 0x0

    if-eqz v3, :svc_open

    # ---- 当前开着 → 关闭 ----
    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;
    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    invoke-static {}, Lcom/yuhan/xiaolvchuan/Hub;->stop()V

    invoke-direct {p0}, Lcom/yuhan/xiaolvchuan/MainActivity;->showAddress()V

    const-string v0, "@TOAST_OFF@"

    invoke-direct {p0, v0}, Lcom/yuhan/xiaolvchuan/MainActivity;->toast(Ljava/lang/String;)V

    return-void

    # ---- 当前关着 → 开启 ----
    :svc_open
    const/4 v2, 0x1

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;
    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    invoke-direct {p0}, Lcom/yuhan/xiaolvchuan/MainActivity;->startServer()V

    invoke-direct {p0}, Lcom/yuhan/xiaolvchuan/MainActivity;->showAddress()V

    const-string v0, "@TOAST_ON@"

    invoke-direct {p0, v0}, Lcom/yuhan/xiaolvchuan/MainActivity;->toast(Ljava/lang/String;)V

    return-void
.end method
"""

NEW_CLICK = """\
.method public onClick(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/yuhan/xiaolvchuan/MainActivity$10;->this$0:Lcom/yuhan/xiaolvchuan/MainActivity;

    # SVC_TOGGLE_V1：不再「hint + 退出」，只切服务开关
    invoke-static {p1}, Lcom/yuhan/xiaolvchuan/MainActivity;->access$1500(Lcom/yuhan/xiaolvchuan/MainActivity;)V

    return-void
.end method
"""


def patch_service_toggle(dec: Path) -> None:
    """小绿传原「退出」= Hub.stop() + finishAndRemoveTask() + killProcess。
    合并后这个进程还要长期扛着 18764/18767，所以按钮改成「关闭服务 / 开启服务」：
    只停它自己的文件传输 HTTP 服务，界面留着；状态记在 svc_on，默认开。"""
    d = dec / "smali_classes2" / XLV_DIR
    act = d / "MainActivity.smali"
    s = act.read_text(encoding="utf-8", errors="surrogateescape")

    if "SVC_TOGGLE_V1" in s:
        log("svc", "已有服务开关补丁，跳过")
        return

    fill = lambda t: (t.replace("@OFF_TITLE@", _u("服务已关闭"))
                       .replace("@OFF_HINT@", _u("点「开启」恢复文件传输"))
                       .replace("@BTN_ON@", _u("关闭"))
                       .replace("@BTN_OFF@", _u("开启"))
                       .replace("@TOAST_ON@", _u("已开启文件传输服务"))
                       .replace("@TOAST_OFF@", _u("已关闭文件传输服务")))

    old = ".method private startServer()V\n    .locals 2\n"
    assert old in s, "startServer 头不匹配"
    s = s.replace(old, ".method private startServer()V\n    .locals 3\n\n" + fill(START_GUARD), 1)

    old = ".method private showAddress()V\n    .locals 2\n"
    assert old in s, "showAddress 头不匹配"
    s = s.replace(old, ".method private showAddress()V\n    .locals 3\n\n" + fill(SHOW_GUARD), 1)

    m = re.search(r"\.method private exitApp\(\)V\n.*?\n\.end method\n", s, re.S)
    assert m, "exitApp 没找到"
    s = s[:m.start()] + fill(NEW_EXIT) + s[m.end():]
    act.write_text(s, encoding="utf-8", errors="surrogateescape")

    b = d / "MainActivity$10.smali"
    t = b.read_text(encoding="utf-8", errors="surrogateescape")
    m = re.search(r"\.method public onClick\(Landroid/view/View;\)V\n.*?\n\.end method\n", t, re.S)
    assert m, "$10 onClick 没找到"
    b.write_text(t[:m.start()] + NEW_CLICK + t[m.end():],
                 encoding="utf-8", errors="surrogateescape")

    p = dec / "res/values/strings.xml"
    x = p.read_text(encoding="utf-8")
    assert '<string name="btn_exit">退出</string>' in x, "btn_exit 文案变了"
    x = x.replace('<string name="btn_exit">退出</string>',
                  '<string name="btn_exit">关闭</string>')
    p.write_text(x, encoding="utf-8")
    log("svc", "「退出」→ 关闭/开启文件传输服务（svc_on，默认开，记住状态）")


def patch_apktool_yml(dec: Path) -> None:
    """public.xml 把 ID 钉在 0x7f01..，apktool 必须用同一个包 ID，否则 aapt2 报
    「can't assign ID 0x7f010000 ... package already has ID 7f」。小绿传原工程是 127。"""
    p = dec / "apktool.yml"
    s = p.read_text(encoding="utf-8")
    s2 = re.sub(r"forcedPackageId:\s*\d+", "forcedPackageId: 127", s, count=1)
    if s2 == s and "forcedPackageId" not in s:
        s2 = s.replace("packageInfo:", "packageInfo:\n  forcedPackageId: 127", 1)
    p.write_text(s2, encoding="utf-8")
    log("apktool.yml", "forcedPackageId = 127")
def build_browse_dex() -> Path:
    """小绿传的 Browse.java 是后期加的 Java 源码，原本单独编成 classes2.dex。
    smali 树里没有它，必须重新编译，否则文件浏览/下载那几条调用会 NoClassDefFoundError。
    这里编成 classes3.dex（apktool 已经占了 classes.dex 和 classes2.dex）。"""
    src_dir = XIAOLV / "02-src/java-src"
    src = src_dir / (XLV_PKG.replace(".", "/") + "/Browse.java")
    if not src.exists():
        raise SystemExit(f"缺少 Browse.java：{src}")

    cls = WORK / "browse_cls"
    dex = WORK / "browse_dex"
    shutil.rmtree(cls, ignore_errors=True)
    shutil.rmtree(dex, ignore_errors=True)
    cls.mkdir(parents=True)
    dex.mkdir(parents=True)

    dj = Path(os.environ.get("ANDROID_JAR",
                             "D:/开发/tools/android-sdk/platforms/android-35/android.jar"))
    run([JB / "bin/javac.exe", "-encoding", "UTF-8", "-source", "8", "-target", "8",
         "-nowarn", "-bootclasspath", dj, "-classpath", dj,
         "-d", cls, src])

    env = dict(os.environ)
    env["JAVA_HOME"] = str(JB)
    env["PATH"] = str(JB / "bin") + os.pathsep + env.get("PATH", "")
    # d8 8.2.2 不吃目录（报 Unsupported source file type），逐个文件传
    classes = sorted(cls.rglob("*.class"))
    if not classes:
        raise SystemExit("javac 没有产出 class，Browse.java 没编过")
    p = subprocess.run([str(BT / "d8.bat"), "--min-api", "28", "--lib", str(dj),
                        "--output", str(dex)] + [str(c) for c in classes],
                       capture_output=True, text=True, encoding="utf-8",
                       errors="replace", env=env)
    if p.returncode != 0:
        print(p.stdout or "", p.stderr or "")
        raise SystemExit("d8 编译 Browse 失败")

    out = dex / "classes.dex"
    if not out.exists():
        raise SystemExit("d8 没有产出 classes.dex")
    log("dex", f"Browse.java → classes3.dex（{out.stat().st_size:,} 字节）")
    return out


def add_dex(apk: Path, dex: Path, name: str = "classes3.dex") -> None:
    """把额外 dex 塞进 APK：逐条复制原条目，最后补一条新 dex。"""
    tmp = apk.with_suffix(".tmp.apk")
    with zipfile.ZipFile(apk) as zin, zipfile.ZipFile(tmp, "w") as zout:
        for zi in zin.infolist():
            if zi.filename == name:
                continue
            data = zin.read(zi.filename)
            ni = zipfile.ZipInfo(zi.filename, date_time=zi.date_time)
            ni.compress_type = zi.compress_type
            ni.external_attr = zi.external_attr
            zout.writestr(ni, data)
        ni = zipfile.ZipInfo(name, date_time=(2026, 1, 1, 0, 0, 0))
        ni.compress_type = zipfile.ZIP_DEFLATED
        zout.writestr(ni, dex.read_bytes())
    tmp.replace(apk)


def build(dec: Path, out: Path) -> None:
    java("-jar", APKTOOL, "b", dec, "-o", out)


def sign(raw: Path, final: Path) -> None:
    aligned = WORK / "aligned.apk"
    if aligned.exists():
        aligned.unlink()
    run([ZIPALIGN, "-p", "-f", "4", raw, aligned])
    if final.exists():
        final.unlink()
    # --min-sdk-version 必须显式给低值：apksigner 会按 APK 里的 minSdkVersion(28) 判断
    # 「v3 已覆盖 28+」，于是只写 v3、v1/v2 全跳过。v3-only 在不少车机（Android 9 及更早
    # 的解析器、部分国产 ROM）直接报「解析包出错 / 没有证书」，app 根本装不进去。
    # 显式按 23 生成，就是 v1 + v2 + v3 三件套齐全，任何机器都能验。
    java("-jar", APKSIGNER, "sign",
         "--ks", KEYSTORE, "--ks-key-alias", KS_ALIAS,
         "--ks-pass", f"pass:{KS_PASS}", "--key-pass", f"pass:{KS_PASS}",
         "--min-sdk-version", "23",
         "--v1-signing-enabled", "true", "--v2-signing-enabled", "true",
         "--v3-signing-enabled", "true", "--out", final, aligned)


def verify(final: Path) -> None:
    # 注意：apksigner verify 按 APK 里的 minSdkVersion(28) 选「最合适的那一个」方案，
    # 所以默认输出里 v1/v2 会显示 false——那只代表「本次验证没用到它」，不代表签名块不存在。
    # 要确认三件套都在，必须分段指定 --min-sdk-version / --max-sdk-version 去验。
    def scheme_ok(lo: int, hi: int, needle: str) -> bool:
        r = run([JAVA, "-jar", APKSIGNER, "verify",
                 "--min-sdk-version", str(lo), "--max-sdk-version", str(hi),
                 "--verbose", final])
        line = next((l for l in (r.stdout or "").splitlines() if needle in l), "")
        return "true" in line

    checks = [(18, 23, "v1 scheme (JAR signing)", "v1"),
              (24, 27, "v2 scheme (APK Signature Scheme v2)", "v2"),
              (28, 35, "v3 scheme (APK Signature Scheme v3)", "v3")]
    missing = [name for lo, hi, needle, name in checks if not scheme_ok(lo, hi, needle)]
    if missing:
        raise SystemExit(f"签名方案缺失：{'、'.join(missing)}——"
                         f"v3-only 的包在 Android 9 以下和部分车机上装不上")
    run([JAVA, "-jar", APKSIGNER, "verify", "--print-certs", final])
    log("sign", "v1 + v2 + v3 三件套齐全")
    p = run([BT / "aapt2.exe", "dump", "badging", final])
    bad = p.stdout or ""
    want = [f"package: name='{PKG}'", "versionCode='72'", "versionName='2.10.29'",
            "application-label:'汽水音乐'",
            f"launchable-activity: name='{XLV_PKG}.MainActivity'"]
    for w in want:
        if w not in bad:
            raise SystemExit(f"校验失败，badging 里没有：{w}")
    with zipfile.ZipFile(final) as z:
        names = z.namelist()
        for n in ("classes.dex", "classes2.dex", "classes3.dex", "assets/web.html",
                  "assets/remote.html", "res/layout/act_main.xml"):
            if n not in names:
                raise SystemExit(f"校验失败，包里没有 {n}")
    log("verify", "包名 2.10.29 / 入口小绿传 / 三个 dex / 两套页面都在")


def main() -> None:
    if "--gradle" in sys.argv:
        log("gradle", "先跑一次 Gradle 出包")
        run([ROOT / "music/cloudmusic-car/gradlew.bat", "-p",
             ROOT / "music/cloudmusic-car", ":lunastub:assembleRelease",
             "--offline", "-q"], quiet=False)

    for f in (BASE_APK, XSRC / "smali", XSRC / "res", APKTOOL, ZIPALIGN, APKSIGNER):
        if not Path(f).exists():
            raise SystemExit(f"缺少输入：{f}")

    if WORK.exists():
        shutil.rmtree(WORK)
    WORK.mkdir(parents=True)
    dec = WORK / "dec"

    log("decode", str(BASE_APK.name))
    java("-jar", APKTOOL, "d", "-f", "-o", dec, BASE_APK)

    inject_smali(dec)
    inject_res(dec)
    inject_assets(dec)
    patch_manifest(dec)
    patch_layout(dec)
    patch_activity(dec)
    patch_exit(dec)
    patch_service_toggle(dec)
    patch_apktool_yml(dec)

    raw = WORK / "unsigned.apk"
    log("build", "apktool 回编译")
    build(dec, raw)

    add_dex(raw, build_browse_dex())

    DIST.mkdir(parents=True, exist_ok=True)
    final = DIST / OUT_NAME
    log("sign", KEYSTORE.name)
    sign(raw, final)
    verify(final)
    log("done", f"{final}  {final.stat().st_size:,} 字节")


if __name__ == "__main__":
    main()
