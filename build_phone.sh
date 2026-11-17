#!/usr/bin/env bash
# 出手机端（安卓）包：
#   work/phone/手机端/cloudmusic-remote -> dist/车机遥控_手机端_v<版本>.apk
#
# iOS 没法在 Windows 上编：工程在 work/phone/ios，用 Mac 打开 xcodegen 生成后 xcodebuild，
# 或者推到 GitHub 走 .github/workflows/ios-phone.yml 云构建出未签名 IPA。
set -e
cd "$(dirname "$0")"

export JAVA_HOME="C:/Users/Administrator/.workbuddy/tools/rev/jdk-17.0.2"

PROJ="work/phone/手机端/cloudmusic-remote"
[ -d "$PROJ" ] || { echo "缺少 $PROJ（工程内副本丢了，从 git 恢复：git checkout -- work/phone）"; exit 1; }

echo ">> 构建安卓手机端"
cd "$PROJ"
./gradlew :app:assembleRelease --offline
cd - >/dev/null

VER=$(grep -m1 'versionName' "$PROJ/app/build.gradle.kts" | sed 's/.*"\(.*\)".*/\1/')

mkdir -p dist
cp -f "$PROJ/app/build/outputs/apk/release/app-release.apk" \
      "dist/车机遥控_手机端_v${VER}.apk"

ls -la dist/*.apk
