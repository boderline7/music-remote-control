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

页面在 web/pages/，外壳四件在 web/shell/。记录仪页 web/pages/dvr.html 不参与
拼接（它是独立页，没有三个 tab）。

标记（页面里保留，重复运行幂等）：
    <!--@shell:head-->   ... <!--@/shell:head-->     <head> 里的样式 + 启动脚本
    <!--@shell:body-->   ... <!--@/shell:body-->     状态行 / 切换 / 设置面板
    <!--@shell:script--> ... <!--@/shell:script-->   外壳逻辑
"""
import hashlib
import re
import sys
from pathlib import Path

WEB = Path(__file__).resolve().parent
ROOT = WEB.parent
PAGES = WEB / "pages"
SHELL = WEB / "shell"
PROJ = ROOT / "music" / "cloudmusic-car"

TARGETS = [
    (PAGES / "car.html", "18.73"),
    (PAGES / "remote.html", None),
    (PAGES / "remote_lunastub.html", None),
]

GRADLE = {
    PAGES / "remote.html": PROJ / "app" / "build.gradle.kts",
    PAGES / "remote_lunastub.html": PROJ / "lunastub" / "build.gradle.kts",
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


def build_stamp(*parts):
    """外壳构建号：内容或版本一变就变，用来给 iframe 地址去缓存。"""
    h = hashlib.md5()
    for p in parts:
        h.update(p.encode("utf-8"))
    return h.hexdigest()[:8]


def main():
    css = read(SHELL / "_shell.css")
    boot = read(SHELL / "_shell.boot.js")
    body = read(SHELL / "_shell.body.html")
    js = read(SHELL / "_shell.js")

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
                               BOOT_WRAP % js.replace("'@SHELL_BUILD@'",
                                                      "'%s'" % build_stamp(css, boot, body, js, v)),
                               page.name)
        page.write_text(html, encoding="utf-8")
        print(">> %-24s v%-10s %5.1f KB  b=%s"
              % (page.name, v, page.stat().st_size / 1024.0,
                 build_stamp(css, boot, body, js, v)))


if __name__ == "__main__":
    main()
