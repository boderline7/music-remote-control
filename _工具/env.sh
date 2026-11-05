#!/usr/bin/env bash
# 车机遥控工程 · 工具链环境
# 用法：source _工具/env.sh
# 说明见 说明.md
#
# 这里只是「早期手工打包那套」的备用环境；两端现在都走 Gradle。
# 所有路径都能用同名环境变量覆盖，换机器时不用改文件。

CR_HOME="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

CR_JDK="${CR_JDK:-$HOME/.workbuddy/binaries/jdk/jdk-17.0.20.1+1}"
CR_JAVAC="$CR_JDK/bin/javac"
CR_JAVA="$CR_JDK/bin/java"
CR_AJAR="${CR_AJAR:-$HOME/.workbuddy/binaries/android/android-35/android.jar}"
CR_BT="${CR_BT:-$HOME/.workbuddy/binaries/android/android-14}"

# 签名库：仓库里不放 .jks，也不放口令。
# 口令只从环境变量读（export CR_KS_PASS=... 或用 keystore.properties），别写死在这个文件里。
CR_KS="${CR_KS:-$CR_HOME/keystore/carremote.jks}"
CR_KS_PASS="${CR_KS_PASS:-}"
CR_KS_ALIAS="${CR_KS_ALIAS:-carremote}"

export CR_HOME CR_JDK CR_JAVAC CR_JAVA CR_AJAR CR_BT CR_KS CR_KS_PASS CR_KS_ALIAS
export PATH="$CR_JDK/bin:$CR_BT:$PATH"

# 自检：缺什么直接说清楚，别让后面构建报一堆看不懂的错
_cr_check() {
    local ok=0
    for f in "$CR_JAVAC" "$CR_AJAR" "$CR_BT/aapt2.exe" "$CR_BT/d8.bat"; do
        [ -e "$f" ] || { echo "[env] 缺失: $f" >&2; ok=1; }
    done
    [ -n "$CR_KS_PASS" ] || echo "[env] 提示: 未设置 CR_KS_PASS，手工签名步骤会跳过" >&2
    [ "$ok" = 0 ] || echo "[env] 工具链不完整，见 _工具/说明.md" >&2
    return $ok
}
_cr_check && echo "[env] OK  javac=$("$CR_JAVAC" -version 2>&1 | head -1)"
