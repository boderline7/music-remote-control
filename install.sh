#!/usr/bin/env bash
# 推到车机并安装两个包。设备已 root，用 -d 允许同版本覆盖。
#   1) 车机助手        18766 车控，常驻
#   2) 汽水音乐桥接包  18765 音乐（官方网易云模式）+ 车控页入口
set -e
cd "$(dirname "$0")"

export PATH="C:/Users/Administrator/.workbuddy/tools/rev/platform-tools:$PATH"
export MSYS_NO_PATHCONV=1

NAVI="dist/车机助手_V18.62_bridge.apk"
LUNA="dist/汽水音乐_桥接包_v2.10.6.apk"

for f in "$NAVI" "$LUNA"; do
  [ -f "$f" ] || { echo "缺少 $f"; exit 1; }
done

echo ">> 设备"
adb devices -l

install_one() {
  local apk="$1" pkg="$2"
  echo
  echo ">> 安装 $pkg"
  adb push "$apk" /data/local/tmp/pkg.apk >/dev/null
  adb shell "su -c 'pm install -r -d -g /data/local/tmp/pkg.apk'" || echo "!! $pkg 安装失败"
  adb shell "dumpsys package $pkg | grep -m1 versionCode" 2>/dev/null || true
}

install_one "$NAVI" com.navi.tool
install_one "$LUNA" com.luna.music.car

echo
echo ">> 重启车机助手，让 18766 重新绑定"
adb shell "su -c 'am force-stop com.navi.tool'" || true
adb shell "su -c 'monkey -p com.navi.tool -c android.intent.category.LAUNCHER 1'" >/dev/null 2>&1 || true

echo
echo "装完后："
echo "  车机浏览器  http://127.0.0.1:18766/     车控页（常驻，不看音乐模式）"
echo "  车机/手机   http://<车机IP>:18765/      音乐遥控 + 车图标/左滑进车控"
