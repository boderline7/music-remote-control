#!/usr/bin/env bash
# 编译桥接代码：Java -> class -> dex/classes.dex
set -e
cd "$(dirname "$0")"

JB="C:/Users/Administrator/.workbuddy/tools/rev/jdk-17.0.2/bin"
BT="C:/Users/Administrator/.workbuddy/tools/rev/android-14"
AJ="D:/android-sdk/platforms/android-35/android.jar"

rm -rf out/*
mkdir -p out dex

echo ">> javac"
"$JB/javac" -encoding UTF-8 -nowarn -source 8 -target 8 \
    -bootclasspath "$AJ" -classpath "$AJ" \
    -d out src/com/navitool/bridge/*.java

echo ">> d8"
rm -f dex/*.dex
"$BT/d8.bat" --min-api 26 --lib "$AJ" --output dex out/com/navitool/bridge/*.class

echo ">> 产物"
ls -la dex/
