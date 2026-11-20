#!/usr/bin/env bash
# 出手机端（安卓）包：
#   phone/android -> dist/车机遥控_手机端_v<版本>.apk
#
# iOS 没法在 Windows 上编：工程在 phone/ios，用 Mac 打开 xcodegen 生成后 xcodebuild，
# 或者跑 scripts/build_ios.sh 推到 GitHub 走云构建出未签名 IPA。
set -e
HERE=$(cd "$(dirname "$0")" && pwd)
ROOT=$(dirname "$HERE")
cd "$ROOT"
. "$HERE/config.example.sh"
if [ -f "$HERE/config.local.sh" ]; then . "$HERE/config.local.sh"; fi
. "$HERE/_check_env.sh"
require_env JDK_HOME || exit 1
export JAVA_HOME="$JDK_HOME"

PROJ="phone/android"
[ -d "$PROJ" ] || { echo "缺少 $PROJ（工程内副本丢了，从 git 恢复：git checkout -- $PROJ）"; exit 1; }

echo ">> 构建安卓手机端"
cd "$PROJ"
./gradlew :app:assembleRelease --offline
cd "$ROOT"

VER=$(grep -m1 'versionName' "$PROJ/app/build.gradle.kts" | sed 's/.*"\(.*\)".*/\1/')

mkdir -p dist
cp -f "$PROJ/app/build/outputs/apk/release/app-release.apk" \
      "dist/车机遥控_手机端_v${VER}.apk"

ls -la dist/*.apk
