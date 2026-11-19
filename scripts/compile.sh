#!/usr/bin/env bash
# 编译桥接代码：Java -> class -> dex
#   src/bridge/src/com/navitool/bridge/*.java  ->  src/bridge/build/{out,dex}
set -e
HERE=$(cd "$(dirname "$0")" && pwd)
ROOT=$(dirname "$HERE")
cd "$ROOT"
. "$HERE/config.example.sh"
if [ -f "$HERE/config.local.sh" ]; then . "$HERE/config.local.sh"; fi

JB="$JDK_HOME/bin"
BUILD="src/bridge/build"

rm -rf "$BUILD/out"
mkdir -p "$BUILD/out" "$BUILD/dex"

echo ">> javac"
"$JB/javac" -encoding UTF-8 -nowarn -source 8 -target 8 \
    -bootclasspath "$ANDROID_JAR" -classpath "$ANDROID_JAR" \
    -d "$BUILD/out" src/bridge/src/com/navitool/bridge/*.java

echo ">> d8"
rm -f "$BUILD"/dex/*.dex
"$BUILD_TOOLS/d8.bat" --min-api 26 --lib "$ANDROID_JAR" \
    --output "$BUILD/dex" "$BUILD"/out/com/navitool/bridge/*.class

echo ">> 产物"
ls -la "$BUILD/dex/"
