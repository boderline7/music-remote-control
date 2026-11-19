# -*- coding: utf-8 -*-
"""
车机助手 com.navi.tool 百度加固脱壳启动器（attach + send 回传）
流程：force-stop -> monkey 启动 -> 1.5s 内 attach -> 注入 unpack.js ->
JS 用 Memory.scanSync 抓 dex 通过 send() 回传 -> 本端接收并写入本地目录。

用法：
    python attach_unpack.py <apk路径> [本地输出目录]
    例：python scripts/unpack/attach_unpack.py apk/navitool-19.00-protected.apk

依赖：python frida 库；设备已 root 且 frida-server 已运行。
环境变量：ADB 指定 adb 路径，ADB_OUT 指定默认输出目录（默认工程根 dump/）。
"""
import sys, time, os, subprocess
import frida

PKG = 'com.navi.tool'
SKILL_DIR = os.path.dirname(__file__)
ROOT = os.path.dirname(os.path.dirname(SKILL_DIR))
DUMP_JS = os.path.join(SKILL_DIR, 'unpack.js')
ADB = os.environ.get('ADB') or r'C:\Program Files\i4Tools9\files\adb\adb.exe'
DEFAULT_LOCAL_OUT = os.environ.get('ADB_OUT') or os.path.join(ROOT, 'dump')

def adb(*args):
    return subprocess.run([ADB] + list(args), capture_output=True, text=True)

def main():
    if len(sys.argv) < 2:
        print(__doc__)
        sys.exit(1)
    apk = sys.argv[1]
    local_out = sys.argv[2] if len(sys.argv) > 2 else DEFAULT_LOCAL_OUT
    os.makedirs(local_out, exist_ok=True)

    # 1. 打印信息并 push + root 安装（绕过 MIUI USER_RESTRICTED）
    print('[+] target:', apk)
    adb('shell', 'am', 'force-stop', PKG)
    time.sleep(1)
    adb('push', apk, '/data/local/tmp/navi.apk') if False else None
    # push 走 stdio 可能有差异，改用显式调用
    r = adb('push', apk, '/data/local/tmp/navi.apk')
    if r.returncode != 0 or 'pushed' not in r.stdout:
        print('[!] push failed:', r.stdout, r.stderr)
    r = adb('shell', 'su', '-c', 'pm install -r /data/local/tmp/navi.apk')
    print('[+] install:', (r.stdout or '').strip())
    r = adb('shell', 'dumpsys', 'package', PKG)
    for line in r.stdout.splitlines():
        if 'versionName' in line or 'versionCode' in line:
            print('[+]', line.strip())

    # 2. 启动并快速 attach（应用 5-12s 内会 VIN 退出，需尽快注入）
    adb('shell', 'monkey', '-p', PKG, '-c', 'android.intent.category.LAUNCHER', '1')
    time.sleep(1.5)
    pid_s = adb('shell', 'pidof', PKG).stdout.strip()
    if not pid_s:
        print('[x] app not running; last log:')
        print(adb('logcat', '-d', '-t', '50').stdout[-2000:])
        sys.exit(1)
    pid = int(pid_s)
    print('[+] pid', pid)

    dev = frida.get_usb_device(timeout=10)
    session = dev.attach(pid)
    print('[+] attached')

    # 3. 注入 dump 脚本，接收 send 回传的 dex
    def on_message(msg, data):
        payload = msg.get('payload')
        if msg['type'] == 'send' and isinstance(payload, dict):
            if payload.get('type') == 'dex':
                path = os.path.join(local_out, payload['name'])
                with open(path, 'wb') as f:
                    f.write(data)
                print('[RCV] %s (%d bytes)' % (payload['name'], len(data)))
            elif payload.get('type') == 'done':
                print('[DONE] total=%s' % payload.get('total'))
            return
        if msg['type'] in ('send', 'log'):
            print('[JS]', payload)

    with open(DUMP_JS, 'r', encoding='utf-8') as f:
        code = f.read()
    script = session.create_script(code)
    script.on('message', on_message)
    script.load()
    print('[+] injected, dumping...')
    time.sleep(35)
    print('[+] done. dumped in', local_out)

if __name__ == '__main__':
    main()