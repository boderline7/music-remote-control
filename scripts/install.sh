#!/usr/bin/env bash
# 推到车机并安装。设备已 root，用 -d 允许同版本覆盖。
# 装哪些包自动取 dist/ 里最新的，不用手工改版本号：
#   车机助手    com.navi.tool       18766 车控 + 统一入口
#   桥接包      com.luna.music.car  18764 桥接 + 18767 记录仪
set -e
HERE=$(cd "$(dirname "$0")" && pwd)
ROOT=$(dirname "$HERE")
cd "$ROOT"
. "$HERE/config.example.sh"
if [ -f "$HERE/config.local.sh" ]; then . "$HERE/config.local.sh"; fi
export PATH="$PLATFORM_TOOLS:$PATH"
export MSYS_NO_PATHCONV=1

# 同类包按修改时间取最新的一个
pick() { ls -t "$@" 2>/dev/null | head -1; }

NAVI=$(pick dist/车机助手_V*.apk)
LUNA=$(pick dist/汽水音乐_桥接包_v*.apk)

[ -n "$NAVI" ] || { echo "dist/ 里没有车机助手包，先跑 python make.py build（见 README 第二节）"; exit 1; }
[ -n "$LUNA" ] || { echo "dist/ 里没有桥接包，先跑 scripts/build_remote.sh"; exit 1; }

echo ">> 待装"
echo "   $NAVI"
echo "   $LUNA"

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
echo "  车机/手机   http://<车机IP>:18766/      三个 tab：车控 / 音乐 / 记录仪"
