"""按 MdnsResponder.kt 的新逻辑逐字节复刻，检查应答报文是否合规。

Kotlin 侧改了什么，这里就跟着改什么；跑通它只能说明报文布局对，
真机能否被 iOS 接受还得看设置页的「收/回」计数。
"""
import struct
import socket

A_RECORD_BYTES = 16
NSEC_RECORD_BYTES = 17


def enc(name):
    out = b""
    for part in name.split("."):
        out += bytes([len(part)]) + part.encode()
    return out + b"\x00"


def u16(buf, at):
    return (buf[at] << 8) or buf[at + 1]


class Question:
    def __init__(self, name, qtype, start, end, unicast):
        self.name = name
        self.type = qtype
        self.start = start
        self.end = end
        self.unicast = unicast


def parse_questions(data, length):
    if length < 12:
        return None
    flags = u16(data, 2)
    if flags & 0x8000:
        return None
    count = u16(data, 4)
    if count < 1:
        return None
    pos = 12
    out = []
    for _ in range(count):
        start = pos
        labels = []
        while pos < length:
            ln = data[pos]
            if ln == 0:
                pos += 1
                break
            if ln & 0xC0:
                return None
            pos += 1
            if pos + ln > length:
                return None
            labels.append(data[pos:pos + ln].decode())
            pos += ln
        if pos + 4 > length:
            return None
        qtype = u16(data, pos)
        qclass = u16(data, pos + 2)
        pos += 4
        out.append(Question(".".join(labels).lower().rstrip("."), qtype, start, pos, bool(qclass & 0x8000)))
    return out


def write_record(out, at, addr, ttl, name_at, negative, pointer=True):
    p = at
    if pointer:
        out[p] = 0xC0 | ((name_at >> 8) & 0x3F)
        out[p + 1] = name_at & 0xFF
        p += 2
    rtype = 47 if negative else 1
    out[p] = (rtype >> 8) & 0xFF
    out[p + 1] = rtype & 0xFF
    p += 2
    out[p] = 0x80
    out[p + 1] = 0x01
    p += 2
    out[p] = 0
    out[p + 1] = 0
    p += 2
    out[p] = (ttl >> 8) & 0xFF
    out[p + 1] = ttl & 0xFF
    p += 2
    if not negative:
        out[p] = 0
        out[p + 1] = 4
        p += 2
        out[p:p + 4] = addr
        p += 4
        return p
    # NSEC: rdata = 名字指针 + 类型位图（只列存在的类型：A）
    out[p] = 0
    out[p + 1] = 5
    p += 2
    out[p] = 0xC0 | ((name_at >> 8) & 0x3F)
    out[p + 1] = name_at & 0xFF
    p += 2
    out[p] = 0x00
    out[p + 1] = 0x01
    out[p + 2] = 0x40
    p += 3
    return p


def build_answer(data, questions, ip, negative, ttl=120):
    addr = socket.inet_aton(ip)
    rec = NSEC_RECORD_BYTES if negative else A_RECORD_BYTES
    out = bytearray(12 + sum(q.end - q.start for q in questions) + len(questions) * rec)
    out[2] = 0x84
    n = len(questions)
    out[4] = (n >> 8) & 0xFF
    out[5] = n & 0xFF
    out[6] = (n >> 8) & 0xFF
    out[7] = n & 0xFF

    offsets = []
    p = 12
    for q in questions:
        ln = q.end - q.start
        offsets.append(p)
        out[p:p + ln] = data[q.start:q.end]
        out[p + ln - 2] = 0
        out[p + ln - 1] = 1
        p += ln
    for off in offsets:
        p = write_record(out, p, addr, ttl, off, negative)
    assert p == len(out), "size mismatch %d vs %d" % (p, len(out))
    return bytes(out)


def read_name(pkt, p):
    parts = []
    while True:
        ln = pkt[p]
        if ln == 0:
            p += 1
            break
        if ln & 0xC0 == 0xC0:
            ptr = ((ln & 0x3F) << 8) | pkt[p + 1]
            sub, _ = read_name(pkt, ptr)
            parts.append(sub)
            p += 2
            break
        p += 1
        parts.append(pkt[p:p + ln].decode())
        p += ln
    return ".".join(parts), p


def parse(pkt):
    _tid, flags, qd, an, _ns, _ar = struct.unpack(">HHHHHH", pkt[:12])
    pos = 12
    qs = []
    for _ in range(qd):
        nm, pos = read_name(pkt, pos)
        qtype, qclass = struct.unpack(">HH", pkt[pos:pos + 4])
        pos += 4
        qs.append((nm, qtype, hex(qclass)))
    answers = []
    for _ in range(an):
        nm, pos = read_name(pkt, pos)
        rtype, rclass, ttl, rdlen = struct.unpack(">HHIH", pkt[pos:pos + 10])
        pos += 10
        rdata = pkt[pos:pos + rdlen]
        pos += rdlen
        if rtype == 1:
            answers.append(("A", nm, socket.inet_ntoa(rdata), hex(rclass), ttl))
        elif rtype == 47:
            next_name, _ = read_name(pkt, pos - rdlen)
            bitmap_at = rdlen - 3
            answers.append(("NSEC", nm, next_name, rdata[bitmap_at:].hex(), hex(rclass), ttl))
        else:
            answers.append((rtype, nm))
    assert pos == len(pkt), "trailing bytes %d/%d" % (pos, len(pkt))
    return dict(flags=hex(flags), qd=qd, an=an, qs=qs, answers=answers, size=len(pkt))


def make_query(pairs):
    body = b""
    for name, qtype in pairs:
        body += enc(name) + struct.pack(">HH", qtype, 1)
    return struct.pack(">HHHHHH", 0, 0, len(pairs), 0, 0, 0) + body


def handle(pkt, ip="192.168.1.50"):
    questions = parse_questions(pkt, len(pkt))
    mine = [q for q in questions if q.name in ("carremote", "carremote.local")]
    if not mine:
        return None, "no-match"
    forward = [q for q in mine if q.type in (1, 255)]
    negative = (not forward) and any(q.type == 28 for q in mine)
    answering = forward if forward else [q for q in mine if q.type == 28]
    if not answering:
        return None, "no-answer"
    return build_answer(pkt, answering, ip, negative), ("NSEC" if negative else "A")


IP = "192.168.1.50"   # 样例地址，随便一个同网段 IPv4 即可

# [1] iOS 常见形态：AAAA 打头、A 在第二条
r, kind = handle(make_query([("carremote.local", 28), ("carremote.local", 1)]))
p = parse(r)
print("[1] AAAA+A ->", kind, p)
assert kind == "A"
assert p["qd"] == 1 and p["qs"][0][1] == 1, p["qs"]
assert p["answers"][0][:3] == ("A", "carremote.local", IP)
assert p["answers"][0][3] == "0x8001"

# [2] 纯 AAAA -> NSEC 负应答
r, kind = handle(make_query([("carremote.local", 28)]))
p = parse(r)
print("[2] AAAA  ->", kind, p)
assert kind == "NSEC"
assert p["answers"][0][0] == "NSEC"
assert p["answers"][0][2] == "carremote.local", p["answers"][0]
assert p["answers"][0][3] == "000140", p["answers"][0][3]  # window0/len1/位图=0x40(A)

# [3] 纯 A
r, kind = handle(make_query([("carremote.local", 1)]))
p = parse(r)
print("[3] A     ->", kind, p)
assert kind == "A" and p["answers"][0][2] == IP

# [4] A 在前 AAAA 在后 —— 只答 A，不掺 NSEC
r, kind = handle(make_query([("carremote.local", 1), ("carremote.local", 28)]))
p = parse(r)
print("[4] A+AAAA->", kind, p)
assert p["an"] == 1 and p["answers"][0][0] == "A"

# [5] 大小写不敏感 / 别的域名不接
assert handle(make_query([("CarRemote.Local", 28), ("CarRemote.Local", 1)]))[1] == "A"
assert handle(make_query([("other.local", 1)]))[1] == "no-match"

# [6] QR=1 的响应包不能被当成查询
resp = bytearray(make_query([("carremote.local", 1)]))
resp[2] = 0x84
assert parse_questions(bytes(resp), len(resp)) is None

# [7] 三条 question 的极端包
r, kind = handle(make_query([("carremote.local", 28), ("carremote.local", 28), ("carremote.local", 1)]))
p = parse(r)
print("[7] x3    ->", kind, p)
assert p["qd"] == 1 and p["an"] == 1 and p["answers"][0][2] == IP

# [8] 主动宣告：QDCOUNT=0, ANCOUNT=1, 名字在 12
name = enc("carremote.local")
ann = bytearray(12 + len(name) + A_RECORD_BYTES - 2)  # 名字写全时记录体少一个 2 字节指针
ann[2] = 0x84
ann[7] = 1
ann[12:12 + len(name)] = name
write_record(ann, 12 + len(name), socket.inet_aton(IP), 120, 12, False, pointer=False)
p = parse(bytes(ann))
print("[8] 宣告  ->", p)
assert p["qd"] == 0 and p["an"] == 1 and p["answers"][0][2] == IP

print("\nALL MDNS PACKET CHECKS PASSED")

# [9] 保活节奏：间隔必须明显小于 TTL，否则对端缓存会先过期再去查询，
#     而查询这条路在车机热点下是不可靠的（这正是 v1.9 域名「过一会就失效」的根因）。
#     这里直接读 Kotlin 源码里的常量，改坏了就让自检红。
import io, os, re
_kt = os.path.join(os.path.dirname(os.path.abspath(__file__)),
                   "..", "车机端/cloudmusic-car/app/src/main/java/com/cloudmusic/car/remote/MdnsResponder.kt")
_src = io.open(_kt, encoding="utf-8").read()

def const(name):
    # Kotlin 的数字字面量可以带下划线（45_000L），直接 \d+ 只会吃到 45
    m = re.search(r"const val %s = ([\d_]+)" % name, _src)
    assert m, "找不到常量 %s" % name
    return int(m.group(1).replace("_", ""))

ttl = const("TTL_ANSWER")                      # 秒
interval = const("ANNOUNCE_INTERVAL_MS") // 1000  # 毫秒 → 秒
tick = const("ANNOUNCE_TICK_MS") // 1000
print("[9] 保活   -> TTL=%ss 间隔=%ss tick=%ss" % (ttl, interval, tick))
assert interval < ttl, "重播间隔必须小于 TTL"
assert interval * 2 <= ttl, "一个 TTL 窗口内至少要能刷两次，丢一个包还有下一次"
assert tick < interval, "tick 必须明显小于宣告间隔，否则到点后要拖很久才发"
# v2.1 起宣告从收包循环里拆成独立线程（宣告失败不能连带拆掉回包 socket），
# 所以这里断言的是新结构：独立线程 + 收包带超时。
assert "announceLoop(gen)" in _src, "宣告必须是独立线程，不能塞在收包循环里"
assert "soTimeout = RECV_TIMEOUT_MS" in _src, "receive 必须带超时，否则收包线程永远醒不过来"

# [10] IP 变化时的两个反面用例（v2.2 修）：
#      a. 重建时若不清 boundIp，独立的宣告线程会在空窗里把**旧地址**再喊一遍，
#         手机刚拿到新 IP 又被改回去，表现为「域名一会儿能进一会儿不能」。
#      b. refresh() 必须拿 boundIp 当基准；拿 hostIp 的话它会收包时被实时改写，永远相等。
assert "boundIp = \"\"" in _src and "announcedIp = \"\"" in _src, "forceRebuild 必须清掉旧地址，否则会喊回旧 IP"

refresh_body = _src[_src.index("fun refresh()"):]
refresh_body = refresh_body[:refresh_body.index("\n    }")]
assert "boundIp" in refresh_body, "refresh 必须用 boundIp 判断地址变化"
assert "hostIp" not in refresh_body.replace("hostIp = ip", ""), "refresh 不能拿 hostIp 当重建基准"

# [11] 本地地址与 mDNS 答案必须同源：设置页显示的 IP 若和 A 记录不一致，
#      用户照着页面手输反而连不上（v2.2 的真实 case：同时连着 WiFi 又开热点）。
_rs = io.open(os.path.join(os.path.dirname(_kt), "RemoteServer.kt"), encoding="utf-8").read()
assert "MdnsResponder.scoreOf" in _rs, "本机地址必须复用 mDNS 的网卡打分，不能自己另写一套先后顺序"
assert "mdns?.hostIp" in _rs, "本机地址应优先取 mDNS 已选定的热点地址"

print("\nALL MDNS PACKET CHECKS PASSED")

