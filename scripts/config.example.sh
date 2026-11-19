#!/usr/bin/env bash
# 构建用到的外部路径。所有脚本启动时自动 source 本文件取默认值。
# 要改就在 scripts/config.local.sh 里覆盖（那个文件不入库），例如：
#   export JDK_HOME="D:/jdk-17"
# 换机器一般只动前三项。

export JDK_HOME="${JDK_HOME:-C:/Users/Administrator/.workbuddy/tools/rev/jdk-17.0.2}"
export BUILD_TOOLS="${BUILD_TOOLS:-C:/Users/Administrator/.workbuddy/tools/rev/android-14}"
export ANDROID_JAR="${ANDROID_JAR:-D:/android-sdk/platforms/android-35/android.jar}"
export PLATFORM_TOOLS="${PLATFORM_TOOLS:-C:/Users/Administrator/.workbuddy/tools/rev/platform-tools}"

# apktool 在中文路径下编 res 会失败，这个目录必须是纯 ASCII
export BRIDGE_BUILD_DIR="${BRIDGE_BUILD_DIR:-C:/Temp/bridge_build}"
