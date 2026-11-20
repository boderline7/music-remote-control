#!/usr/bin/env bash
# 构建用到的外部路径。所有脚本启动时先 source 本文件取默认值，
# 再 source scripts/config.local.sh 覆盖（那份不入库）。
#
# 换机器：cp scripts/config.example.sh scripts/config.local.sh，然后只改前三项。
# 也可以不建文件，直接 export 同名变量。

: "${JDK_HOME:=}"
: "${BUILD_TOOLS:=}"
: "${ANDROID_JAR:=}"
: "${PLATFORM_TOOLS:=}"

# apktool 在中文路径下编 res 会失败，这个目录必须是纯 ASCII
: "${BRIDGE_BUILD_DIR:=C:/Temp/bridge_build}"

export JDK_HOME BUILD_TOOLS ANDROID_JAR PLATFORM_TOOLS BRIDGE_BUILD_DIR