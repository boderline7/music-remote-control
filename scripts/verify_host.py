#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
原厂包锚点核对 —— 宿主升级后，五分钟内知道「还能不能接」。

为什么要这个脚本：
    这套系统靠反射绑原厂桌面助手的 carControlHelper，锚点全是混淆过的
    类名 / 字段名 / 方法签名 / 命令号。原厂一升级，任何一条变了车控就哑，
    而在此之前只能人肉翻 smali 才知道。这里把锚点逐条核对，分两级：

    fatal  车控整体不可用（类、字段、helper、两条下发方法、卡片链）
    warn   单项功能或样式失效（座椅命令号、总线通道、prefs 键、动作名）

用法：
    python scripts/verify_host.py                 默认检查 dex/V19.00/dex（脱壳业务码）
    python scripts/verify_host.py <解包目录>
    python scripts/verify_host.py <脱壳dex目录>
    python scripts/verify_host.py <原厂包.apk>     用 tools/apktool.jar 自动解包（注意：加固包解不出业务类）
    python scripts/verify_host.py --json

退出码：0 = 没有 fatal 失败；1 = 有 fatal 失败；2 = 用法错误。
"""

import json
import os
import re
import shutil
import subprocess
import sys
import tempfile
import unicodedata
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
APKTOOL = ROOT / "tools" / "apktool.jar"
DEXKIT = ROOT / "patch" / "dexkit_classes"
DEFAULT_DEX = ROOT / "dex" / "V19.00" / "dex"

ACTIVITY = "com/navi/tool/ui/extra/DesktopAssistantActivity"
HELPER = "com/navi/tool/ui/extra/DesktopAssistantCarControlHelper"
ADAPTER = "com/navi/tool/ui/extra/CarControlAdapter"

SEAT_CMDS = ["0x5f", "0x60", "0x8b", "0x8d", "0x5d", "0x5e"]
VD_CHANNELS = ["0x5000a", "0x50001"]
PREFS_KEYS = [
    "desktop_seat_compat_mode",
    "lion_sync_service_enabled",
    "desktop_round_corner_enabled",
    "car_control_card_corner_radius",
    "desktop_card_margin",
]
SAMPLE_ACTS = ["handleHvacAutoClick", "handleHvacCirculationClick"]


# ---------------------------------------------------------------- 宽度对齐

def wide(s):
    return sum(2 if unicodedata.east_asian_width(c) in "WF" else 1 for c in s)


def pad(s, n):
    return s + " " * max(0, n - wide(s))


# ---------------------------------------------------------------- smali 索引

class Host:
    def __init__(self, base):
        self.base = Path(base)
        self.roots = sorted(p for p in self.base.glob("smali*") if p.is_dir())
        self._cache = {}

    def cls_path(self, dotted):
        return Path(*dotted.split(".")).with_suffix(".smali")

    def find_class(self, dotted):
        rel = self.cls_path(dotted)
        for r in self.roots:
            f = r / rel
            if f.is_file():
                return f
        return None

    def text(self, path):
        if path is None:
            return ""
        if path not in self._cache:
            try:
                self._cache[path] = path.read_text(encoding="utf-8", errors="replace")
            except OSError:
                self._cache[path] = ""
        return self._cache[path]

    def all_smali(self):
        for r in self.roots:
            for f in r.rglob("*.smali"):
                yield f

    def meta(self):
        info = {}
        yml = self.base / "apktool.yml"
        if yml.is_file():
            m = re.search(r"apkFileName:\s*(\S+)", self.text(yml))
            if m:
                info["apk"] = m.group(1)
        man = self.base / "AndroidManifest.xml"
        if man.is_file():
            t = self.text(man)
            m = re.search(r'versionName="([^"]*)"', t)
            if m:
                info["versionName"] = m.group(1)
            m = re.search(r'versionCode="([^"]*)"', t)
            if m:
                info["versionCode"] = m.group(1)
        return info


# ---------------------------------------------------------------- 工具函数

def count_args(sig):
    """把 smali 参数串数成个数：IIZZ=4，Landroid/content/Context;III=4，[I=1。"""
    n, i = 0, 0
    while i < len(sig):
        c = sig[i]
        if c == "[":
            i += 1
            continue
        if c == "L":
            j = sig.find(";", i)
            if j < 0:
                break
            i = j + 1
        else:
            i += 1
        n += 1
    return n


def find_method(text, name, argc=None):
    """返回 (是否存在, 行号, 实际参数串)。"""
    for m in re.finditer(r"\.method[^\r\n]*?\b%s\(([^)]*)\)" % re.escape(name), text):
        args = m.group(1)
        if argc is None or count_args(args) == argc:
            line = text.count("\n", 0, m.start()) + 1
            return True, line, args
    return False, 0, ""


def find_field(text, name):
    for m in re.finditer(r"\.field[^\r\n]*?\b%s\b[^\r\n]*?:(L[^;\r\n]+;)" % re.escape(name), text):
        line = text.count("\n", 0, m.start()) + 1
        return True, line, m.group(1)
    return False, 0, ""


def has_token(text, token):
    return token in text


# ---------------------------------------------------------------- 检查项

def run_checks(h):
    out = []

    def add(cid, title, severity, ok, detail, where=""):
        out.append({
            "id": cid, "title": title, "severity": severity,
            "ok": bool(ok), "detail": detail, "where": where,
        })

    act_f = h.find_class(ACTIVITY)
    act_t = h.text(act_f)
    add("activity_class", "活动类 DesktopAssistantActivity", "fatal",
        act_f is not None, "找到" if act_f else "找不到类文件",
        str(act_f.relative_to(h.base)) if act_f else "")

    ok, line, typ = find_field(act_t, "carControlHelper")
    add("helper_field", "字段 carControlHelper", "fatal", ok,
        ("声明于第 %d 行" % line) if ok else "活动类里没有这个字段",
        "%s:%d" % (act_f.relative_to(h.base), line) if ok and act_f else "")

    ok_type = ok and typ == "L%s;" % HELPER.replace(".", "/")
    add("helper_type", "字段类型 = DesktopAssistantCarControlHelper", "fatal", ok_type,
        ("类型 %s" % typ) if ok else "字段缺失，无法判断类型", "")

    helper_f = h.find_class(HELPER)
    helper_t = h.text(helper_f)
    add("helper_class", "引擎类 DesktopAssistantCarControlHelper", "fatal",
        helper_f is not None, "找到" if helper_f else "找不到类文件",
        str(helper_f.relative_to(h.base)) if helper_f else "")

    ok, line, args = find_method(helper_t, "sendSeatCommand", 4)
    add("send_seat", "座椅下发 sendSeatCommand(4 参)", "fatal", ok,
        ("签名 (%s)" % args) if ok else "4 参数版本不存在",
        "%s:%d" % (helper_f.relative_to(h.base), line) if ok and helper_f else "")

    ok, line, args = find_method(helper_t, "actionSendCommand", 4)
    add("send_temp", "空调下发 actionSendCommand(4 参)", "fatal", ok,
        ("签名 (%s)" % args) if ok else "4 参数版本不存在",
        "%s:%d" % (helper_f.relative_to(h.base), line) if ok and helper_f else "")

    ok, line, typ = find_field(helper_t, "carControlAdapter")
    add("adapter_field", "字段 carControlAdapter", "fatal", ok,
        ("声明于第 %d 行" % line) if ok else "helper 里没有卡片适配器字段",
        "%s:%d" % (helper_f.relative_to(h.base), line) if ok and helper_f else "")

    adapter_f = h.find_class(ADAPTER)
    adapter_t = h.text(adapter_f)
    add("adapter_class", "卡片类 CarControlAdapter", "fatal",
        adapter_f is not None, "找到" if adapter_f else "找不到类文件",
        str(adapter_f.relative_to(h.base)) if adapter_f else "")

    ok, line, _ = find_method(adapter_t, "getData", 0)
    add("adapter_getdata", "卡片数据 getData()", "fatal", ok,
        ("声明于第 %d 行" % line) if ok else "取不到卡片数据",
        "%s:%d" % (adapter_f.relative_to(h.base), line) if ok and adapter_f else "")

    ok, line, _ = find_method(helper_t, "updateCarControlCards", 0)
    add("update_cards", "刷新卡片 updateCarControlCards()", "warn", ok,
        ("声明于第 %d 行" % line) if ok else "只能读缓存，卡片不会自更新",
        "%s:%d" % (helper_f.relative_to(h.base), line) if ok and helper_f else "")

    missing = [c for c in SEAT_CMDS if not has_token(helper_t, c)]
    add("seat_cmds", "座椅 6 个命令号", "warn", not missing,
        "全部在位" if not missing else "缺 " + " / ".join(missing),
        str(helper_f.relative_to(h.base)) if helper_f else "")

    blob = "\n".join(h.text(f) for f in h.all_smali())
    missing = [c for c in VD_CHANNELS if not has_token(blob, c)]
    add("vd_channels", "总线通道 0x5000A / 0x50001", "warn", not missing,
        "全部在位" if not missing else "缺 " + " / ".join(missing), "全树")

    missing = [k for k in PREFS_KEYS if not has_token(blob, k)]
    add("prefs_keys", "prefs 开关键", "warn", not missing,
        "全部在位" if not missing else "缺 " + " / ".join(missing), "全树")

    clicks = re.findall(r"\.method[^\r\n]*?\bhandle[A-Za-z0-9_]*Click\(", helper_t)
    add("act_naming", "动作命名 handle*Click", "warn", len(clicks) >= 5,
        "helper 里 %d 个" % len(clicks), "")

    missing = [a for a in SAMPLE_ACTS if not find_method(helper_t, a, None)[0]]
    add("act_sample", "抽样动作（空调 auto / 循环）", "warn", not missing,
        "都在" if not missing else "缺 " + " / ".join(missing), "")

    return out


# ---------------------------------------------------------------- 渲染

def render(results, meta, target):
    fatal_bad = [r for r in results if r["severity"] == "fatal" and not r["ok"]]
    warn_bad = [r for r in results if r["severity"] == "warn" and not r["ok"]]

    print("原厂包锚点核对")
    print("  目标  %s" % target)
    if meta.get("apk"):
        print("  来源  %s" % meta["apk"])
    if meta.get("versionName"):
        print("  版本  %s (%s)" % (meta["versionName"], meta.get("versionCode", "?")))
    print()

    tw = max([wide("检查项")] + [wide(r["title"]) for r in results]) + 2
    hdr = "  " + pad("级别", 6) + pad("检查项", tw) + pad("结果", 8) + "证据"
    print(hdr)
    print("  " + "-" * (wide(hdr) - 2))
    for r in results:
        lv = "致命" if r["severity"] == "fatal" else "警告"
        res = "通过" if r["ok"] else "失败"
        line = "  " + pad(lv, 6) + pad(r["title"], tw) + pad(res, 8) + r["detail"]
        if r["where"] and not r["ok"]:
            line += "  [" + r["where"] + "]"
        print(line)

    print()
    if not fatal_bad:
        print("结论  可以接。致命项 %d/%d 通过" % (
            sum(1 for r in results if r["severity"] == "fatal"), 
            sum(1 for r in results if r["severity"] == "fatal")))
    else:
        print("结论  不能直接接，%d 条致命项失败：" % len(fatal_bad))
        for r in fatal_bad:
            print("      · %s —— %s" % (r["title"], r["detail"]))
    if warn_bad:
        print("      另有 %d 条警告，对应单项功能会失效，不阻断车控。" % len(warn_bad))
    return 0 if not fatal_bad else 1


# ---------------------------------------------------------------- 入口

def unpack(apk):
    if not APKTOOL.is_file():
        print("!! 找不到 %s" % APKTOOL)
        sys.exit(2)
    tmp = tempfile.mkdtemp(prefix="host_")
    dst = os.path.join(tmp, "unpack")
    print("正在解包 %s …" % Path(apk).name)
    r = subprocess.run(["java", "-jar", str(APKTOOL), "d", "-f", "-o", dst, str(apk)],
                       capture_output=True, text=True, encoding="utf-8", errors="replace")
    if r.returncode != 0 or not Path(dst).is_dir():
        print("!! 解包失败")
        print((r.stderr or r.stdout or "")[-800:])
        sys.exit(2)
    return dst, tmp


def disassemble(dex_target):
    """用 DexKit 把脱壳 dex 反汇编到临时目录。原包是加固的，只有脱壳 dex 里才有业务类。"""
    if not DEXKIT.is_dir():
        print("!! 找不到 %s" % DEXKIT)
        sys.exit(2)
    dexes = sorted(dex_target.glob("*.dex")) if dex_target.is_dir() else [dex_target]
    if not dexes:
        print("!! %s 下没有 .dex" % dex_target)
        sys.exit(2)

    tmp = tempfile.mkdtemp(prefix="host_dex_")
    cp = os.pathsep.join([str(DEXKIT), str(APKTOOL)])
    print("正在反汇编 %d 个脱壳 dex …" % len(dexes))
    for i, d in enumerate(dexes):
        name = "smali" if i == 0 else "smali_classes%d" % (i + 1)
        out = Path(tmp) / name
        out.mkdir(parents=True, exist_ok=True)
        r = subprocess.run(["java", "-Xmx4g", "-cp", cp, "DexKit", "dis", str(d), str(out)],
                           capture_output=True, text=True, encoding="utf-8", errors="replace")
        if r.returncode != 0:
            print("!! 反汇编失败 %s" % d.name)
            print((r.stderr or r.stdout or "")[-800:])
            sys.exit(2)
    return str(tmp), tmp


def main():
    args = [a for a in sys.argv[1:] if not a.startswith("--")]
    as_json = "--json" in sys.argv

    target = Path(args[0]) if args else DEFAULT_DEX
    tmp = None
    try:
        if target.is_file() and target.suffix.lower() == ".apk":
            unpacked, tmp = unpack(target)
            base = Path(unpacked)
        elif target.suffix.lower() == ".dex" or (target.is_dir() and any(target.glob("*.dex"))):
            unpacked, tmp = disassemble(target)
            base = Path(unpacked)
        elif target.is_dir():
            base = target
        else:
            print("!! 路径不存在：%s" % target)
            sys.exit(2)

        h = Host(base)
        if not h.roots:
            print("!! %s 里没有 smali 目录，不是 apktool 解包目录" % base)
            sys.exit(2)

        results = run_checks(h)
        meta = h.meta()

        if as_json:
            print(json.dumps({
                "target": str(target), "meta": meta,
                "ok": not any(r["severity"] == "fatal" and not r["ok"] for r in results),
                "results": results,
            }, ensure_ascii=False, indent=1))
            sys.exit(0 if not any(r["severity"] == "fatal" and not r["ok"] for r in results) else 1)

        sys.exit(render(results, meta, str(target)))
    finally:
        if tmp:
            shutil.rmtree(tmp, ignore_errors=True)


if __name__ == "__main__":
    main()
