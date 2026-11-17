#!/usr/bin/env bash
# 出车机端包：
#   :lunastub -> 汽水音乐_桥接包_v<版本>.apk（桥接包，官方网易云模式用，18764）
#   :app      -> 网易云音乐车机版_v<版本>.apk（com.cloudmusic.car，18765 的页面就在它的 assets 里）
set -e
cd "$(dirname "$0")"

export JAVA_HOME="C:/Users/Administrator/.workbuddy/tools/rev/jdk-17.0.2"

PROJ="work/cloudmusic-car"
[ -d "$PROJ" ] || { echo "缺少 $PROJ，先复制一份车机端工程"; exit 1; }

echo ">> 同步网页"
cp -f web/remote.html          "$PROJ/app/src/main/assets/remote.html"
cp -f web/remote_lunastub.html "$PROJ/lunastub/src/main/assets/remote.html"
# 行车记录仪独立页（18767 首页），跟音乐页分开，两个都不受影响
cp -f web/dvr.html             "$PROJ/lunastub/src/main/assets/dvr.html"

echo ">> 构建"
cd "$PROJ"
./gradlew :lunastub:assembleRelease --offline
# 网易云车机版（com.cloudmusic.car）也要出：18765 那个页面就打在它的 assets 里，
# 不重出这个包，车上 18765 还是旧页面
./gradlew :app:assembleRelease --offline
cd ../..

mkdir -p dist

LUNA_VER=$(grep -m1 'versionName' "$PROJ/lunastub/build.gradle.kts" | sed 's/.*"\(.*\)".*/\1/')

cp -f "$PROJ/lunastub/build/outputs/apk/release/lunastub-release.apk" \
      "dist/汽水音乐_桥接包_v${LUNA_VER}.apk"

APP_VER=$(grep -m1 'versionName' "$PROJ/app/build.gradle.kts" | sed 's/.*"\(.*\)".*/\1/')
cp -f "$PROJ/app/build/outputs/apk/release/app-release.apk" \
      "dist/网易云音乐车机版_v${APP_VER}.apk"

ls -la dist/*.apk
