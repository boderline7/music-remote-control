#!/usr/bin/env bash
# 检查必需的环境变量是否配齐，缺了就明确说缺哪个。
# 用法：. "$HERE/_check_env.sh"; require_env JDK_HOME BUILD_TOOLS ANDROID_JAR

require_env() {
    _miss=""
    for _v in "$@"; do
        eval "_val=\${$_v}"
        [ -n "$_val" ] || _miss="$_miss $_v"
    done
    if [ -n "$_miss" ]; then
        echo "缺少构建配置:$_miss" >&2
        echo "在 scripts/config.local.sh 里填，或 export 同名变量。示例：" >&2
        echo '  JDK_HOME=<jdk-17 目录>' >&2
        echo '  BUILD_TOOLS=<build-tools 目录>' >&2
        echo '  ANDROID_JAR=<sdk>/platforms/android-35/android.jar' >&2
        echo '  PLATFORM_TOOLS=<sdk>/platform-tools' >&2
        unset _miss _v _val
        return 1
    fi
    unset _miss _v _val
    return 0
}