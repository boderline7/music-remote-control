#!/usr/bin/env bash
# 出网易云音乐车机版：
#   :app -> dist/网易云音乐车机版_v<版本>.apk（com.cloudmusic.car，18765 页面在它的 assets 里）
set -e
HERE=$(cd "$(dirname "$0")" && pwd)
ROOT=$(dirname "$HERE")
cd "$ROOT"
. "$HERE/config.example.sh"
if [ -f "$HERE/config.local.sh" ]; then . "$HERE/config.local.sh"; fi
. "$HERE/_check_env.sh"
require_env JDK_HOME || exit 1
export JAVA_HOME="$JDK_HOME"

PROJ="music/cloudmusic-car"
[ -d "$PROJ" ] || { echo "缺少 $PROJ"; exit 1; }

echo ">> 同步网页"
cp -f web/pages/remote.html          "$PROJ/app/src/main/assets/remote.html"

echo ">> 构建"
cd "$PROJ"
# 网易云车机版（com.cloudmusic.car）也要出：18765 那个页面就打在它的 assets 里，
# 不重出这个包，车上 18765 还是旧页面
./gradlew :app:assembleRelease --offline
cd "$ROOT"

mkdir -p dist



APP_VER=$(grep -m1 'versionName' "$PROJ/app/build.gradle.kts" | sed 's/.*"\(.*\)".*/\1/')
cp -f "$PROJ/app/build/outputs/apk/release/app-release.apk" \
      "dist/网易云音乐车机版_v${APP_VER}.apk"

ls -la dist/*.apk
