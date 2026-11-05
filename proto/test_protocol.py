# -*- coding: utf-8 -*-
"""协议自检：用 protocol.json 校验样例报文与两端常量文件是否自洽。

用法:
    python proto/test_protocol.py

检查项:
  1. protocol.json 能被解析，版本/端口存在
  2. 样例报文都通过 required/类型/正则/枚举校验
  3. 反例（缺字段、错类型、错枚举）必须被判错
  4. proto/Protocol.java 里的类型名、动作名、错误码与 json 完全一致
"""
import json
import os
import re
import sys

HERE = os.path.dirname(os.path.abspath(__file__))
JSON_PATH = os.path.join(HERE, "protocol.json")
JAVA_PATH = os.path.join(HERE, "Protocol.java")

failures = []


def fail(msg):
    failures.append(msg)


def load_spec():
    with open(JSON_PATH, "r", encoding="utf-8") as f:
        return json.load(f)


TYPE_MAP = {
    "array": list,
    "string": str,
    "integer": int,
    "boolean": bool,
    "object": dict,
}


def check_field(name, rule, value):
    """返回错误列表。"""
    errs = []
    t = rule.get("type")
    py = TYPE_MAP.get(t)
    if py is None:
        return ["%s: 未知类型 %s" % (name, t)]
    if py is int and isinstance(value, bool):
        errs.append("%s: 期望 integer，得到 bool" % name)
        return errs
    if not isinstance(value, py):
        errs.append("%s: 期望 %s，得到 %s" % (name, t, type(value).__name__))
        return errs
    if t == "string":
        pat = rule.get("pattern")
        if pat and not re.match(pat, value):
            errs.append("%s: 不匹配 %s" % (name, pat))
        ml = rule.get("maxLength")
        if ml is not None and len(value) > ml:
            errs.append("%s: 长度 %d 超过 %d" % (name, len(value), ml))
    if t == "integer":
        if "min" in rule and value < rule["min"]:
            errs.append("%s: %d < min %d" % (name, value, rule["min"]))
    enum = rule.get("enum")
    if enum and value not in enum:
        errs.append("%s: %r 不在枚举内" % (name, value))
    return errs


def validate(spec, msg):
    """按信封 + 类型规则校验一条报文。"""
    errs = []
    if not isinstance(msg, dict):
        return ["报文不是对象"]
    env = spec["envelope"]["required"]
    for k in env:
        if k not in msg:
            errs.append("缺少信封字段 %s" % k)
    if errs:
        return errs
    if msg["v"] != spec["protocolVersion"]:
        errs.append("版本不符: %r" % msg["v"])
    mt = spec["messages"].get(msg["type"])
    if mt is None:
        return ["未知 type %r" % msg["type"]]
    if mt.get("idRequired") and not msg.get("id"):
        errs.append("type=%s 要求 id" % msg["type"])
    data = msg.get("data")
    fields = mt.get("data", {})
    if not fields:
        return errs
    if not isinstance(data, dict):
        return errs + ["type=%s 要求 data 对象" % msg["type"]]
    for fname, rule in fields.items():
        if fname not in data:
            if rule.get("required"):
                errs.append("缺少 data.%s" % fname)
            continue
        errs += check_field("data." + fname, rule, data[fname])
    # 指令的 args 逐 action 校验
    if msg["type"] == "command":
        action = data.get("action")
        args = data.get("args") or {}
        arg_rules = spec["messages"]["command"].get("argsByAction", {}).get(action, {})
        for aname, arule in arg_rules.items():
            if aname not in args:
                if arule.get("required"):
                    errs.append("action=%s 缺少 args.%s" % (action, aname))
                continue
            errs += check_field("args." + aname, arule, args[aname])
        for aname in args:
            if arg_rules and aname not in arg_rules:
                errs.append("action=%s 不认识 args.%s" % (action, aname))
    return errs


GOOD = [
    {"v": 1, "type": "pair", "id": "p-1", "data": {
        "pin": "123456", "deviceId": "a1b2c3d4e5f6", "deviceName": "后排水手机",
        "clientVersion": "1.0.0"}},
    # 免 PIN 自动通过：pin 允许空串
    {"v": 1, "type": "pair", "id": "p-2", "data": {"pin": "", "deviceId": "a1b2c3d4e5f6"}},
    {"v": 1, "type": "pair_result", "id": "p-1", "data": {
        "ok": True, "token": "A" * 43, "carId": "car-01", "carName": "车机",
        "protocolVersion": 1, "autoApproved": True}},
    {"v": 1, "type": "auth", "id": "a-1", "data": {
        "token": "A" * 43, "deviceId": "a1b2c3d4e5f6"}},
    {"v": 1, "type": "auth_result", "id": "a-1", "data": {
        "ok": False, "protocolVersion": 1, "error": 2004, "message": "token invalid"}},
    {"v": 1, "type": "command", "id": "c-1", "data": {"action": "play"}},
    {"v": 1, "type": "command", "id": "c-2", "data": {
        "action": "seek", "args": {"positionMs": 65000}}},
    {"v": 1, "type": "command", "id": "c-3", "data": {
        "action": "setVolume", "args": {"volume": 15}}},
    {"v": 1, "type": "ack", "id": "c-1", "data": {"ok": True, "action": "play"}},
    # 资料库：我喜欢的音乐 / 云盘 / 我创建的歌单
    {"v": 1, "type": "command", "id": "c-4", "data": {"action": "library"}},
    {"v": 1, "type": "command", "id": "c-5", "data": {
        "action": "getTracks", "args": {"id": "liked", "offset": 40}}},
    {"v": 1, "type": "command", "id": "c-6", "data": {
        "action": "playTracks", "args": {"id": "123456", "index": 3}}},
    {"v": 1, "type": "library", "id": "c-4", "data": {
        "ok": True, "liked": {"id": "liked", "name": "我喜欢的音乐", "count": 12},
        "cloud": {"id": "cloud", "name": "音乐云盘", "count": 3},
        "playlists": [{"id": "887", "name": "夜间", "count": 20}]}},
    {"v": 1, "type": "tracks", "id": "c-5", "data": {
        "ok": True, "id": "liked", "total": 12, "offset": 0, "tracks": []}},
    {"v": 1, "type": "state", "data": {
        "seq": 42, "playing": True, "title": "南方姑娘", "artist": "赵雷",
        "album": "赵小雷", "positionMs": 12000, "durationMs": 320000,
        "volume": 15, "volumeMax": 30, "reason": "tick"}},
    {"v": 1, "type": "state", "data": {
        "seq": 43, "playing": False, "title": "", "artist": "",
        "positionMs": 0, "durationMs": 0, "reason": "event"}},
    {"v": 1, "type": "ping", "data": {"t": 1730000000000}},
    {"v": 1, "type": "pong", "data": {"t": 1730000000000, "serverTs": 1730000000012}},
    {"v": 1, "type": "revoked", "data": {"reason": "removed_by_car"}},
    {"v": 1, "type": "error", "id": "c-1", "data": {"error": 2000, "message": "auth required"}},
]

BAD = [
    # 版本不符
    ({"v": 2, "type": "command", "id": "c-1", "data": {"action": "play"}}, "版本不符"),
    # 未知类型
    ({"v": 1, "type": "explode", "id": "x"}, "未知 type"),
    # command 缺 id
    ({"v": 1, "type": "command", "data": {"action": "play"}}, "要求 id"),
    # 未知 action
    ({"v": 1, "type": "command", "id": "c-1", "data": {"action": "rewind"}}, "不在枚举内"),
    # seek 缺 positionMs
    ({"v": 1, "type": "command", "id": "c-9", "data": {"action": "seek", "args": {}}},
     "缺少 args.positionMs"),
    # positionMs 负数
    ({"v": 1, "type": "command", "id": "c-9", "data": {
        "action": "seek", "args": {"positionMs": -1}}}, "< min 0"),
    # pin 位数不对
    ({"v": 1, "type": "pair", "id": "p-1", "data": {
        "pin": "12ab", "deviceId": "d"}}, "不匹配"),
    # token 长度不对
    ({"v": 1, "type": "auth", "id": "a-1", "data": {"token": "abc", "deviceId": "d"}},
     None),
    # state 缺 title
    ({"v": 1, "type": "state", "data": {
        "seq": 1, "playing": True, "artist": "x", "positionMs": 0,
        "durationMs": 0}}, "缺少 data.title"),
    # 布尔冒充整数
    ({"v": 1, "type": "state", "data": {
        "seq": True, "playing": True, "title": "", "artist": "",
        "positionMs": 0, "durationMs": 0}}, "得到 bool"),
]


def check_java(spec):
    if not os.path.exists(JAVA_PATH):
        fail("缺少 Protocol.java")
        return
    src = open(JAVA_PATH, "r", encoding="utf-8").read()
    # 消息类型
    for t in spec["messages"]:
        if ('"%s"' % t) not in src:
            fail("Protocol.java 缺少消息类型常量 %s" % t)
    # 动作
    for a in spec["messages"]["command"]["data"]["action"]["enum"]:
        if ('"%s"' % a) not in src:
            fail("Protocol.java 缺少 action 常量 %s" % a)
    # 错误码
    for code in spec["errors"]:
        if code not in src:
            fail("Protocol.java 缺少错误码 %s" % code)
    # 端口
    if str(spec["defaultPort"]) not in src:
        fail("Protocol.java 端口与 json 不一致")


def main():
    spec = load_spec()
    print("协议版本 %s，默认端口 %s，消息类型 %d 个"
          % (spec["protocolVersion"], spec["defaultPort"], len(spec["messages"])))

    for msg in GOOD:
        errs = validate(spec, msg)
        if errs:
            fail("正例被判错 %s: %s" % (msg["type"], "; ".join(errs)))

    for msg, want in BAD:
        errs = validate(spec, msg)
        if not errs:
            fail("反例未被拦住: %s" % json.dumps(msg, ensure_ascii=False))
        elif want and not any(want in e for e in errs):
            fail("反例错误信息不含 %r: %s" % (want, errs))

    check_java(spec)

    print("正例 %d 条，反例 %d 条" % (len(GOOD), len(BAD)))
    if failures:
        print("\nFAIL %d 项:" % len(failures))
        for f in failures:
            print("  -", f)
        return 1
    print("PASS 协议定义与两端常量自洽")
    return 0


if __name__ == "__main__":
    sys.exit(main())
