#!/usr/bin/env bash
# 只改页面时用它：不重出包，也不重装那个重打包过的原厂包。
#
# 原理：Page.java 先读 /sdcard/Android/data/com.navi.tool/files/web/index.html，
#       读到且非空就用它，没有才回落到 APK 里的 assets/carbridge.html。
#       改一个标题、一个按钮，不值得为它重签、重装一次原厂包。
#
#   scripts/push_ui.sh             拼页 + 推到车机（大约 3 秒）
#   scripts/push_ui.sh --clear     删掉覆盖文件，回到包内版本
#   scripts/push_ui.sh --status    看当前有没有覆盖
#   scripts/push_ui.sh -s <序列号>  指定设备
#
# 推完在手机上刷新页面（状态行右侧的刷新按钮）就能看到，不用重启车机助手。
set -e
HERE=$(cd "$(dirname "$0")" && pwd)
ROOT=$(dirname "$HERE")
cd "$ROOT"
. "$HERE/config.example.sh"
if [ -f "$HERE/config.local.sh" ]; then . "$HERE/config.local.sh"; fi
export PATH="$PLATFORM_TOOLS:$PATH"
export MSYS_NO_PATHCONV=1

PKG=com.navi.tool
DIR="/sdcard/Android/data/$PKG/files/web"
WANT="$DIR/index.html"
TMP=/data/local/tmp/carbridge.html

MODE=push
ADB="adb"
while [ $# -gt 0 ]; do
  case "$1" in
    --clear) MODE=clear ;;
    --status) MODE=status ;;
    -s) ADB="adb -s $2"; shift ;;
    *) echo "!! 不认识的参数：$1"; exit 1 ;;
  esac
  shift
done

$ADB get-state >/dev/null 2>&1 || { echo "!! 没有设备：adb devices 看一下"; exit 1; }

case "$MODE" in
  status)
    echo ">> 覆盖文件"
    $ADB shell "su -c 'ls -l $WANT 2>/dev/null || echo 没有，用的是包内 assets/carbridge.html'"
    ;;

  clear)
    echo ">> 删除覆盖文件，回到包内版本"
    $ADB shell "su -c 'rm -f $WANT'" || true
    echo "   手机刷新页面即可"
    ;;

  push)
    echo ">> 拼页（把统一外壳写进三个页面）"
    python web/stitch.py

    echo
    echo ">> 推车控页 → $WANT"
    $ADB push web/pages/car.html "$TMP" >/dev/null
    $ADB shell "su -c 'mkdir -p $DIR && cp $TMP $WANT && chmod 644 $WANT'"
    $ADB shell "su -c 'ls -l $WANT'"

    echo
    echo ">> 手机刷新页面即可（不用重启车机助手）"
    echo "   想撤回：scripts/push_ui.sh --clear"
    ;;
esac
