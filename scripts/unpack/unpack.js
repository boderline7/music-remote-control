/*
 * 车机助手 (com.navi.tool) 百度加固 DEX dump 脚本
 * 通过 Memory.scanSync 扫描进程内存中的 dex 魔数头，完整 dump 后用 send()
 * 回传到 Python 端（frida 的 File 写设备在 v17 不可靠，故用 send 回传）。
 *
 * 用法：由 scripts/attach_unpack.py 注入执行。
 */
'use strict';

var seen = {};
var dumped = 0;

var PATTERNS = [
    '64 65 78 0a 30 33 35 00', // dex\n035\0
    '64 65 78 0a 30 33 36 00', // dex\n036\0
    '64 65 78 0a 30 33 37 00', // dex\n037\0
    '64 65 78 0a 30 33 38 00'  // dex\n038\0
];

function tryDumpAt(base) {
    try {
        var fileSize = base.add(0x20).readU32();
        if (fileSize < 0x70 || fileSize > 200 * 1024 * 1024) return;
        var dataOff = base.add(0x24).readU32();
        if (dataOff >= fileSize) return;
        // 前 64 字节指纹去重
        var arr = new Uint8Array(64);
        for (var j = 0; j < 64; j++) arr[j] = base.add(j).readU8();
        var keyArr = [];
        for (var x = 0; x < 64; x += 4) {
            var v = (arr[x] | (arr[x + 1] << 8) | (arr[x + 2] << 16) | (arr[x + 3] << 24)) >>> 0;
            keyArr.push(v.toString(16));
        }
        var key = fileSize + '_' + keyArr.join('');
        if (seen[key] !== undefined) return;
        seen[key] = 1;
        var full = base.readByteArray(fileSize);
        dumped++;
        send({ type: 'dex', name: 'dex_' + dumped + '_' + fileSize + '.dex', size: fileSize }, full);
        console.log('[*] DUMPED[' + dumped + '] ' + fileSize + ' @ ' + base);
    } catch (e) {}
}

function scanAll() {
    try {
        var ranges = Process.enumerateRanges('r--');
        ranges.push.apply(ranges, Process.enumerateRanges('rw-'));
        for (var i = 0; i < ranges.length; i++) {
            var r = ranges[i];
            if (r.size < 0x70) continue;
            for (var p = 0; p < PATTERNS.length; p++) {
                try {
                    var m = Memory.scanSync(r.base, r.size, PATTERNS[p]);
                    for (var k = 0; k < m.length; k++) tryDumpAt(m[k].address);
                } catch (e) {}
            }
        }
    } catch (e) {}
}

function run() {
    // 多轮扫描，兼容 dex 稍晚解密
    for (var round = 0; round < 8; round++) {
        scanAll();
        if (round < 7) Thread.sleep(0.3);
    }
    send({ type: 'done', total: dumped });
    console.log('[*] FINAL dumped=' + dumped);
}

run();