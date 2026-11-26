#!/usr/bin/env bash
# 推到车机并安装。设备已 root，用 -d 允许同版本覆盖。
# 装哪些包自动取 dist/ 里最新的，不用手工改版本号：
#   车机助手    com.navi.tool       18766 车控 + 18767 记录仪 + 统一入口
#   网易云车机版 com.cloudmusic.car  音乐播放，车机助手的酷我槽位直连它
# 文件传输（小绿传）用原版 APK，单独装，不归这里管。
set -e
HERE=$(cd "$(dirname "$0")" && pwd)
ROOT=$(dirname "$HERE")
cd "$ROOT"
. "$HERE/config.example.sh"
if [ -f "$HERE/config.local.sh" ]; then . "$HERE/config.local.sh"; fi
. "$HERE/_check_env.sh"
require_env PLATFORM_TOOLS || exit 1
export PATH="$PLATFORM_TOOLS:$PATH"
export MSYS_NO_PATHCONV=1

# 同类包按修改时间取最新的一个
pick() { ls -t "$@" 2>/dev/null | head -1; }

NAVI=$(pick dist/车机助手_V*.apk)
NETEASE=$(pick dist/网易云音乐车机版_v*.apk)

[ -n "$NAVI" ] || { echo "dist/ 里没有车机助手包，先跑 python make.py build（见 README 第二节）"; exit 1; }

echo ">> 待装"
echo "   $NAVI"
if [ -n "$NETEASE" ]; then echo "   $NETEASE"; fi

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

# 18767 已经并进车机助手，旧记录仪端留着会抢同一个端口，先卸掉
echo
echo ">> 卸载旧的记录仪端（18767 已并进车机助手）"

install_one "$NAVI" com.navi.tool
if [ -n "$NETEASE" ]; then install_one "$NETEASE" com.cloudmusic.car; fi

echo
echo ">> 重启车机助手，让 18766 重新绑定"
adb shell "su -c 'am force-stop com.navi.tool'" || true
adb shell "su -c 'monkey -p com.navi.tool -c android.intent.category.LAUNCHER 1'" >/dev/null 2>&1 || true

echo
echo "装完后："
echo "  车机浏览器  http://127.0.0.1:18766/     车控页（常驻，不看音乐模式）"
echo "  车机/手机   http://<车机IP>:18766/      三个 tab：车控 / 音乐 / 记录仪"
