#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
把统一外壳拼进三个页面。

为什么要有这一步：18766 / 18765 / 18764 是三个不同的服务端（车机助手、
网易云车机版、我们的桥接包），各自下发自己那份 HTML。外壳（状态行 +
中文切换 + 设置面板）只应该有一份源码，否则三个端口又会慢慢长歪。

用法：
    python web/stitch.py            # 拼进三个页面
    python web/stitch.py --check    # 只检查标记在不在

标记（页面里保留，重复运行幂等）：
    <!--@shell:head-->   ... <!--@/shell:head-->     <head> 里的样式 + 启动脚本
    <!--@shell:body-->   ... <!--@/shell:body-->     状态行 / 切换 / 设置面板
    <!--@shell:script--> ... <!--@/shell:script-->   外壳逻辑
"""
import re
import sys
from pathlib import Path

WEB = Path(__file__).resolve().parent
ROOT = WEB.parent
PROJ = ROOT / "work" / "cloudmusic-car"

TARGETS = [
    (WEB / "car.html", "18.72"),
    (WEB / "remote.html", None),
    (WEB / "remote_lunastub.html", None),
]

GRADLE = {
    WEB / "remote.html": PROJ / "app" / "build.gradle.kts",
    WEB / "remote_lunastub.html": PROJ / "lunastub" / "build.gradle.kts",
}

BOOT_WRAP = "<script>\n%s\n</script>\n"
CSS_WRAP = "<style>\n%s</style>\n"


def read(p):
    return p.read_text(encoding="utf-8")


def gradle_version(p, fallback):
    try:
        m = re.search(r'versionName\s*=\s*"([^"]+)"', read(p))
        if m:
            return m.group(1)
    except OSError:
        pass
    return fallback


def replace_between(text, start, end, payload, where):
    i = text.find(start)
    j = text.find(end)
    if i < 0 or j < 0 or j < i:
        raise SystemExit("!! %s 里找不到 %s / %s 标记" % (where, start, end))
    return text[:i + len(start)] + "\n" + payload + text[j:]


def main():
    css = read(WEB / "_shell.css")
    boot = read(WEB / "_shell.boot.js")
    body = read(WEB / "_shell.body.html")
    js = read(WEB / "_shell.js")

    check_only = "--check" in sys.argv
    for page, ver in TARGETS:
        if not page.exists():
            print("-- 跳过（不存在）", page.name)
            continue
        v = ver or gradle_version(GRADLE.get(page), "0.0.0")
        html = read(page)
        if check_only:
            ok = all(k in html for k in
                     ("<!--@shell:head-->", "<!--@/shell:head-->",
                      "<!--@shell:body-->", "<!--@/shell:body-->",
                      "<!--@shell:script-->", "<!--@/shell:script-->"))
            print(("OK  " if ok else "缺  ") + page.name + "  v" + v)
            continue

        html = replace_between(html, "<!--@shell:head-->", "<!--@/shell:head-->",
                               (CSS_WRAP % css) + (BOOT_WRAP % boot), page.name)
        html = replace_between(html, "<!--@shell:body-->", "<!--@/shell:body-->",
                               body.replace("{{VER}}", v), page.name)
        html = replace_between(html, "<!--@shell:script-->", "<!--@/shell:script-->",
                               BOOT_WRAP % js, page.name)
        page.write_text(html, encoding="utf-8")
        print(">> %-24s v%-10s %5.1f KB" % (page.name, v, page.stat().st_size / 1024.0))


if __name__ == "__main__":
    main()
