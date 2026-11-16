#!/usr/bin/env bash
# 出车机端包：
#   :lunastub -> 汽水音乐_桥接包_v<版本>.apk（桥接包，官方网易云模式用）
# 云音乐车机包（:app）暂不出，需要时在下面把 :app:assembleRelease 加回来。
set -e
cd "$(dirname "$0")"

export JAVA_HOME="C:/Users/Administrator/.workbuddy/tools/rev/jdk-17.0.2"

PROJ="work/cloudmusic-car"
[ -d "$PROJ" ] || { echo "缺少 $PROJ，先复制一份车机端工程"; exit 1; }

echo ">> 同步网页"
cp -f web/remote.html          "$PROJ/app/src/main/assets/remote.html"
cp -f web/remote_lunastub.html "$PROJ/lunastub/src/main/assets/remote.html"

echo ">> 构建"
cd "$PROJ"
./gradlew :lunastub:assembleRelease --offline
cd ../..

mkdir -p dist

LUNA_VER=$(grep -m1 'versionName' "$PROJ/lunastub/build.gradle.kts" | sed 's/.*"\(.*\)".*/\1/')

cp -f "$PROJ/lunastub/build/outputs/apk/release/lunastub-release.apk" \
      "dist/汽水音乐_桥接包_v${LUNA_VER}.apk"

ls -la dist/*.apk
