#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
车机桥接 构建脚本

流程：
  1. 把 work/apk_decoded 复制到 ASCII 临时目录（apktool 在中文路径下编 res 会失败）
  2. 清单里加 provider 声明（幂等，已存在就跳过）
  3. versionCode 固定写 25，versionName 固定 18.73-mod
  4. apktool b
  5. 追加 classes3.dex（我们的桥接代码，不压缩）
  6. zipalign -f -p 4
  7. apksigner 签名（固定 debug.keystore）
  8. 产物落到 dist/

  构建只依赖本目录与 tools/；JDK、build-tools 在 ~/.workbuddy/tools/rev。
  """
import os
import re
import shutil
import subprocess
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent
SRC = ROOT / "work" / "apk_decoded"
DEX = ROOT / "dex" / "classes.dex"
DIST = ROOT / "dist"
BUILD = Path("D:/cbwork/bridge_build")
OUT_APK = DIST / "车机助手_V18.73_bridge.apk"

JB = Path("C:/Users/Administrator/.workbuddy/tools/rev/jdk-17.0.2/bin")
BT = Path("C:/Users/Administrator/.workbuddy/tools/rev/android-14")
TOOLS = ROOT / "tools"
APKTOOL = TOOLS / "apktool.jar"
KEYSTORE = TOOLS / "debug.keystore"

PROVIDER = ('<provider android:authorities="com.navitool.bridge" '
            'android:exported="false" '
            'android:name="com.navitool.bridge.BridgeProvider" />')

# 前台服务：18766 的容器。START_STICKY，进程被回收也能自己回来
SERVICE = ('<service android:name="com.navitool.bridge.BridgeService" '
           'android:exported="false" />')

# 开机广播：车机一启动就顶起服务，不依赖桌面助手有没有被打开
RECEIVER = ('<receiver android:name="com.navitool.bridge.BridgeBoot" android:exported="true">\n'
            '            <intent-filter>\n'
            '                <action android:name="android.intent.action.BOOT_COMPLETED" />\n'
            '                <action android:name="android.intent.action.QUICKBOOT_POWERON" />\n'
            '            </intent-filter>\n'
            '        </receiver>')


def run(cmd, cwd=None, tag=""):
    print(">>", tag or " ".join(str(c) for c in cmd[:3]))
    r = subprocess.run([str(c) for c in cmd], cwd=str(cwd) if cwd else None,
                       capture_output=True, text=True, encoding="utf-8", errors="replace")
    if r.stdout.strip():
        print(r.stdout.strip()[:2000])
    if r.returncode != 0:
        print("!! 失败 rc=%d" % r.returncode)
        if r.stderr.strip():
            print(r.stderr.strip()[:3000])
        sys.exit(1)
    return r


def main():
    if not SRC.exists():
        print("缺少 work/apk_decoded，先把解包目录复制过来")
        return 1
    if not DEX.exists():
        print("缺少 dex/classes.dex，先跑 compile.sh")
        return 1

    DIST.mkdir(parents=True, exist_ok=True)

    # 1. 复制到 ASCII 路径（已存在就覆盖写，不整目录删——大批量删除会被安全策略拦下）
    BUILD.parent.mkdir(parents=True, exist_ok=True)
    print(">> 复制到 ASCII 构建目录", BUILD)
    if BUILD.exists():
        shutil.copytree(SRC, BUILD, dirs_exist_ok=True)
    else:
        shutil.copytree(SRC, BUILD)

    # 2. 清单加 provider / 前台服务 / 开机广播
    mf = BUILD / "AndroidManifest.xml"
    s = mf.read_text(encoding="utf-8")
    mo = re.search(r"<application\b[^>]*>", s)
    if not mo:
        print("!! 找不到 <application> 标签")
        return 1
    add = []
    if "com.navitool.bridge.BridgeProvider" not in s:
        add.append(PROVIDER)
    if "com.navitool.bridge.BridgeService" not in s:
        add.append(SERVICE)
    if "com.navitool.bridge.BridgeBoot" not in s:
        add.append(RECEIVER)
    if add:
        s = s[:mo.end()] + "\n        " + "\n        ".join(add) + s[mo.end():]
        mf.write_text(s, encoding="utf-8")
        print(">> 清单已加入 %d 项声明" % len(add))
    else:
        print(">> 清单声明已齐，跳过")

    # 3. 版本号
    y = BUILD / "apktool.yml"
    t = y.read_text(encoding="utf-8")
    t = re.sub(r"versionCode:\s*\d+", "versionCode: 25", t)
    t = re.sub(r"versionName:\s*\S+", "versionName: 18.73-mod", t)
    y.write_text(t, encoding="utf-8")
    print(">> versionCode=25 versionName=18.73-mod")

    # 3.5 把车控页塞进 assets（改前端只改 web/car.html，重跑本脚本即可）
    page_src = ROOT / "web" / "car.html"
    if page_src.exists():
        assets = BUILD / "assets"
        assets.mkdir(parents=True, exist_ok=True)
        shutil.copyfile(page_src, assets / "carbridge.html")
        print(">> assets/carbridge.html %.1f KB" % (page_src.stat().st_size / 1024.0))
    else:
        print("!! 缺少 web/car.html，18766 首页会退回内置自检页")

    # 4. apktool b
    unsigned = BUILD.parent / "unsigned.apk"
    if unsigned.exists():
        unsigned.unlink()
    run([JB / "java", "-Xmx4g", "-jar", APKTOOL, "b", BUILD.name, "-o", unsigned.name],
        cwd=BUILD.parent, tag="apktool b")

    # 5. 追加 classes3.dex（不压缩）
    with_dex = BUILD.parent / "withdex.apk"
    if with_dex.exists():
        with_dex.unlink()
    dex_bytes = DEX.read_bytes()
    with zipfile.ZipFile(unsigned) as zin, zipfile.ZipFile(with_dex, "w", zipfile.ZIP_DEFLATED) as zout:
        existing = {n for n in zin.namelist()}
        idx = 1
        name = "classes.dex"
        while name in existing:
            idx += 1
            name = "classes%d.dex" % idx
        for info in zin.infolist():
            zout.writestr(info, zin.read(info.filename), compress_type=info.compress_type)
        zi = zipfile.ZipInfo(name)
        zi.compress_type = zipfile.ZIP_STORED
        zout.writestr(zi, dex_bytes)
    print(">> 已追加 %s（%d 字节）" % (name, len(dex_bytes)))

    # 6. zipalign
    aligned = BUILD.parent / "aligned.apk"
    if aligned.exists():
        aligned.unlink()
    run([BT / "zipalign.exe", "-f", "-p", "4", with_dex, aligned], tag="zipalign")
    r = run([BT / "zipalign.exe", "-c", "-p", "-v", "4", aligned], tag="zipalign -c")
    if "Verification succes" not in r.stdout and "Verification success" not in r.stdout:
        print("!! 对齐校验未通过")
        return 1

    # 7. 签名
    if OUT_APK.exists():
        OUT_APK.unlink()
    run([JB / "java", "-jar", BT / "lib" / "apksigner.jar", "sign",
         "--ks", KEYSTORE, "--ks-pass", "pass:android",
         "--ks-key-alias", "debugkey", "--key-pass", "pass:android",
         "--min-sdk-version", "26",
         "--v1-signing-enabled", "true", "--v2-signing-enabled", "true",
         "--out", OUT_APK, aligned], tag="apksigner")
    run([JB / "java", "-jar", BT / "lib" / "apksigner.jar", "verify", "--verbose", OUT_APK],
        tag="apksigner verify")

    print()
    print("完成：", OUT_APK)
    print("大小：%.2f MB" % (OUT_APK.stat().st_size / 1048576.0))
    return 0


if __name__ == "__main__":
    sys.exit(main())
