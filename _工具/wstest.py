# -*- coding: utf-8 -*-
"""车机遥控 · 电脑端联调脚本（阶段 1 验证用，不依赖任何第三方库）。

不需要手机就能验证车机端：握手、PIN 配对、Token 认证、指令 ack、状态推送。

用法：
    # 首次配对（车机设置 → 手机遥控 → 看 PIN）
    python _工具/wstest.py --host 192.168.1.23 --pin 123456

    # 之后用 token 认证并发指令
    python _工具/wstest.py --host 192.168.1.23 --token <token> --cmd play --cmd getState --watch 4

    # 只测端口通不通 / 握手
    python _工具/wstest.py --host 192.168.1.23

参数：
    --host      车机 IP（必填）
    --port      默认 18765
    --pin       走配对流程
    --token     走认证流程
    --cmd       要发的指令（可重复），如 play / pause / next / prev
    --seek      配合 command 用：seek 到指定毫秒
    --volume    配合 command 用：setVolume 到指定值
    --watch     发完指令后再等几秒收状态推送
"""

import argparse
import base64
import json
import os
import socket
import struct
import sys
import threading
import time

PORT_DEFAULT = 18765


class WS:
    """RFC 6455 最小客户端：只做文本帧。"""

    def __init__(self, host, port, timeout=8.0):
        self.sock = socket.create_connection((host, port), timeout=timeout)
        self.sock.settimeout(None)
        self._lock = threading.Lock()
        self._handshake(host, port)

    def _handshake(self, host, port):
        key = base64.b64encode(os.urandom(16)).decode()
        req = (
            "GET / HTTP/1.1\r\n"
            "Host: %s:%d\r\n"
            "Upgrade: websocket\r\n"
            "Connection: Upgrade\r\n"
            "Sec-WebSocket-Key: %s\r\n"
            "Sec-WebSocket-Version: 13\r\n\r\n" % (host, port, key)
        )
        self.sock.sendall(req.encode())
        resp = b""
        while b"\r\n\r\n" not in resp:
            chunk = self.sock.recv(1024)
            if not chunk:
                raise RuntimeError("握手失败：连接被关闭")
            resp += chunk
        line = resp.split(b"\r\n")[0].decode(errors="replace")
        if "101" not in line:
            raise RuntimeError("握手失败：%s" % line)

    def send(self, obj):
        payload = json.dumps(obj, ensure_ascii=False).encode()
        header = bytearray([0x81])
        n = len(payload)
        if n < 126:
            header.append(0x80 | n)
        elif n <= 0xFFFF:
            header.append(0x80 | 126)
            header += struct.pack(">H", n)
        else:
            header.append(0x80 | 127)
            header += struct.pack(">Q", n)
        mask = os.urandom(4)
        header += mask
        masked = bytes(b ^ mask[i % 4] for i, b in enumerate(payload))
        with self._lock:
            self.sock.sendall(bytes(header) + masked)

    def _read_exact(self, n):
        buf = b""
        while len(buf) < n:
            chunk = self.sock.recv(n - len(buf))
            if not chunk:
                raise ConnectionError("连接已断开")
            buf += chunk
        return buf

    def recv(self):
        first, second = self._read_exact(2)
        opcode = first & 0x0F
        masked = (second & 0x80) != 0
        length = second & 0x7F
        if length == 126:
            length = struct.unpack(">H", self._read_exact(2))[0]
        elif length == 127:
            length = struct.unpack(">Q", self._read_exact(8))[0]
        mask = self._read_exact(4) if masked else b""
        payload = self._read_exact(length)
        if masked:
            payload = bytes(b ^ mask[i % 4] for i, b in enumerate(payload))
        if opcode == 0x8:
            raise ConnectionError("服务端关闭了连接")
        if opcode == 0x9:
            return None
        return payload.decode(errors="replace")


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--host", required=True)
    ap.add_argument("--port", type=int, default=PORT_DEFAULT)
    ap.add_argument("--pin")
    ap.add_argument("--token")
    ap.add_argument("--cmd", action="append", default=[])
    ap.add_argument("--seek", type=int)
    ap.add_argument("--volume", type=int)
    ap.add_argument("--watch", type=float, default=2.0)
    args = ap.parse_args()

    print("连接 %s:%d ..." % (args.host, args.port))
    ws = WS(args.host, args.port)
    print("握手成功")

    received = []
    stop = threading.Event()

    def reader():
        while not stop.is_set():
            try:
                text = ws.recv()
            except Exception as e:
                if not stop.is_set():
                    print("[连接结束] %s" % e)
                return
            if text is None:
                continue
            received.append(text)
            try:
                msg = json.loads(text)
            except ValueError:
                print("[非 JSON] %s" % text)
                continue
            kind = msg.get("type")
            if kind == "state":
                d = msg.get("data", {})
                print("[state] %s %s-%s %dms/%dms%s seq=%s" % (
                    "播放中" if d.get("playing") else "已暂停",
                    d.get("title", ""), d.get("artist", ""),
                    d.get("positionMs", 0), d.get("durationMs", 0),
                    (" 音量=%s/%s" % (d.get("volume"), d.get("volumeMax"))) if "volume" in d else "",
                    d.get("seq"),
                ))
            else:
                print("[%s] %s" % (kind, json.dumps(msg, ensure_ascii=False)))

    threading.Thread(target=reader, daemon=True).start()

    if args.pin:
        print("→ 配对（PIN %s）" % args.pin)
        ws.send({
            "v": 1, "type": "pair", "id": "pair-1",
            "data": {"pin": args.pin, "deviceId": "pc-wstest", "deviceName": "电脑联调", "clientVersion": "0.1"},
        })
    elif args.token:
        print("→ 认证")
        ws.send({
            "v": 1, "type": "auth", "id": "auth-1",
            "data": {"token": args.token, "deviceId": "pc-wstest"},
        })
    else:
        print("→ 只测握手，不认证")

    time.sleep(1.0)

    for index, cmd in enumerate(args.cmd):
        data = {"action": cmd}
        if cmd == "seek" and args.seek is not None:
            data["args"] = {"positionMs": args.seek}
        if cmd == "setVolume" and args.volume is not None:
            data["args"] = {"volume": args.volume}
        print("→ 指令 %s" % cmd)
        ws.send({"v": 1, "type": "command", "id": "cmd-%d" % index, "data": data})
        time.sleep(0.4)

    time.sleep(args.watch)
    stop.set()
    try:
        ws.sock.close()
    except Exception:
        pass
    print("收到 %d 条消息" % len(received))
    return 0


if __name__ == "__main__":
    sys.exit(main())
