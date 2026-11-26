#!/usr/bin/env bash
# 下载构建依赖到 patch/ 下（体积大，不入库）。
#   用法：bash scripts/fetch_deps.sh
#   走代理：HTTP_PROXY=http://ip:port bash scripts/fetch_deps.sh
# 目前只有 apktool；缺什么补什么。
set -e

HERE=$(cd "$(dirname "$0")" && pwd)
ROOT=$(dirname "$HERE")
PATCH="$ROOT/patch"
mkdir -p "$PATCH"

APKTOOL_VER=2.11.1
APKTOOL_JAR="$PATCH/apktool.jar"

fetch() {
    url="$1"; dest="$2"; name="$3"
    if [ -f "$dest" ]; then
        echo ">> 已存在，跳过：$name（$(du -h "$dest" | cut -f1)）"
        return 0
    fi
    echo ">> 下载 $name"
    echo "   $url"
    if command -v curl >/dev/null 2>&1; then
        curl -L --fail --retry 3 --connect-timeout 15 -o "$dest.part" "$url"
    elif command -v wget >/dev/null 2>&1; then
        wget -O "$dest.part" "$url"
    else
        echo "缺少 curl 或 wget" >&2
        exit 1
    fi
    mv "$dest.part" "$dest"
    echo "   OK：$dest（$(du -h "$dest" | cut -f1)）"
}

fetch \
    "https://github.com/iBotPeaches/Apktool/releases/download/v${APKTOOL_VER}/apktool_${APKTOOL_VER}.jar" \
    "$APKTOOL_JAR" \
    "apktool ${APKTOOL_VER}"

echo "依赖就绪。"
