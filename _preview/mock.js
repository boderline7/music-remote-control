/* 仅用于本地预览车控页：模拟 18765（网页）与 18766（车机助手） */
const http = require('http');
const fs = require('fs');

const PAGE = process.argv[2] || 'D:/车机/车机桥接空调/web/remote_lunastub.html';

http.createServer((req, res) => {
  const u = req.url.split('?')[0];
  if (u === '/' || u === '/index.html') {
    res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
    res.end(fs.readFileSync(PAGE));
  } else {
    res.writeHead(404); res.end();
  }
}).listen(18765, '127.0.0.1');

const cards = {
  ok: 1, at: Date.now(),
  style: { propColor: '#B3FFFFFF', valueColor: '#FFFFFFFF', alpha: 90, fontSize: 20, round: 1, radius: 14, margin: 8 },
  cards: [
    { t: 'HVAC', title: '空调信息', cols: 2, items: [
      { k: '主驾温度', v: '22.0℃', s: 1 }, { k: '副驾温度', v: '22.0℃', s: 1 },
      { k: '风量', v: '2 档', s: 1 }, { k: '吹风模式', v: '面部', s: 1 },
      { k: 'AUTO', v: '开', s: 1 }, { k: '循环', v: '内循环', s: 1 }
    ] },
    { t: 'SEAT', title: '座椅舒适', cols: 2, items: [
      { k: '主驾加热', v: '关', s: 1 }, { k: '副驾加热', v: '1 档', s: 1 },
      { k: '主驾通风', v: '关', s: 1 }, { k: '副驾通风', v: '关', s: 1 }
    ] },
    { t: 'DOOR', title: '门窗信息', cols: 2, items: [
      { k: '主驾门', v: '已关', s: 1 }, { k: '后备箱', v: '已关', s: 1 },
      { k: '天窗', v: '已关', s: 1 }, { k: '遮阳帘', v: '已开', s: 1 }
    ] },
    { t: 'STATUS', title: '状态信息', cols: 2, items: [
      { k: '续航', v: '420 km', s: 1 }, { k: '总里程', v: '12034 km', s: 1 }
    ] }
  ]
};

const topIds = ['steering_heat', 'hvac_auto', 'hvac_circ', 'hvac_mode', 'trunk_switch',
  'wireless_charging', 'fragrance', 'massage_fl', 'wiper_sensitivity', 'light_height',
  'pedestrian_alert', 'seat_heat_fl'];
const names = ['方向盘加热', '空调 AUTO', '内外循环', '吹风模式', '后备箱', '无线充电',
  '香氛', '座椅按摩', '雨刷灵敏度', '大灯高度', '行人提醒', '主驾加热'];
const acts = {
  ok: 1,
  groups: [
    { g: '空调', items: [{ id: 'handleHvacAutoClick', t: 'AUTO', v: -1 }, { id: 'handleHvacCirculationClick', t: '内外循环', v: -1 }] },
    { g: '底盘', items: [
      { id: 'actionSetSuspensionModeComfort', t: '悬架 舒适', v: -1 }, { id: 'actionSetSuspensionModeStandard', t: '悬架 标准', v: -1 },
      { id: 'actionSetSuspensionModeSport', t: '悬架 运动', v: -1 },
      { id: 'actionSetSuspensionLinkEnabled', t: '悬架联动 开', v: 1 }, { id: 'actionSetSuspensionLinkEnabled', t: '悬架联动 关', v: 0 },
      { id: 'actionSetBrakeFeelModeComfort', t: '刹车 舒适', v: -1 }, { id: 'actionSetBrakeFeelModeSport', t: '刹车 运动', v: -1 }] }
  ]
};

const probe = {
  ok: 1, helper: 1, cls: 'com.navi.tool.ui.extra.DesktopAssistantCarControlHelper',
  topIds, names,
  match: topIds.map((id, i) => ({ id, name: names[i], via: 'handle' + id, ok: id === 'seat_heat_fl' ? 0 : 1 })),
  summary: { total: 12, auto: 11, manual: 1 },
  fields: { carControlAdapter: 1, prefs: 1, carControlContainer: 1 }, log: []
};

const diag = `==== 车机桥接诊断 ====
时间   2026-11-16 11:58:03
设备   BYD DiLink · Android 13 (SDK 33)
访问   http://192.168.1.23:18766/

-- 服务 --
端口   18766  已绑定
自测   127.0.0.1 不通 ← 本机回环可用，跨应用连接被拦，改用局域网地址
自测   192.168.1.23 通
进程   pid 8412 · 已运行 2 小时 13 分

-- 助手引擎 --
界面   已抓到 DesktopAssistantActivity
引擎   已绑定 DesktopAssistantCarControlHelper（3 分 12 秒前）
动作   29 项可执行
最近错误 无

-- 安装包 --
  com.navi.tool  18.62 (vC 14)  2026-11-16 10:41
  com.luna.music.car  未安装

-- 桥接日志（进程内存，新→旧倒序不了，按发生顺序）--
install ok, watching com.navi.tool.ui.extra.DesktopAssistantActivity
http start requested
http listening on 18766 (try 1)
helper bound: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper

-- 系统日志（本应用相关，最近 60 行）--
11-16 11:44:02.113 I/NavitoolBridge( 8412): http listening on 18766 (try 1)
`;
const ops = [];
let fanFront = 3;
let fanRear = 0;
const pad2 = (n) => (n < 10 ? '0' : '') + n;
const hhmmss = (d) => `${pad2(d.getHours())}:${pad2(d.getMinutes())}:${pad2(d.getSeconds())}`;
const ymdhms = (d) => `${d.getFullYear()}-${pad2(d.getMonth() + 1)}-${pad2(d.getDate())} ${hhmmss(d)}`;

function opsText() {
  const okN = ops.filter((o) => o.ok).length;
  let s = '==== 操作记录 ====\n';
  s += `进程启动  ${ymdhms(new Date(Date.now() - 3600e3))}\n`;
  s += `调用统计  ${ops.length} 次 · 成功 ${okN} · 失败 ${ops.length - okN}\n`;
  s += `保留      ${ops.length}/150 条，最新的在上面\n`;
  if (!ops.length) return s + '\n(还没有操作记录)\n';
  s += '\n时间      结果  耗时    动作\n' + '-'.repeat(56) + '\n';
  for (const o of ops) {
    s += `${hhmmss(o.at)}  ${o.ok ? '成功' : '失败'}  ${(o.ms + 'ms').padEnd(6)} ${o.label}  (${o.id}`
      + (o.v >= 0 ? ` v=${o.v}` : '') + ')' + (o.err ? `  ← ${o.err}` : '') + '\n';
  }
  return s;
}

function cardsText() {
  let s = '==== 车况快照 ====\n';
  s += `时间   ${ymdhms(new Date())}\n`;
  s += '样式   透明度 90% · 字号 20px · 属性色 #B3FFFFFF · 数值色 #FFFFFFFF\n';
  let n = 0;
  for (const c of cards.cards) {
    s += `\n[${c.title}]  ${c.t} · ${c.cols} 列\n`;
    for (const it of c.items) {
      n++;
      const kw = [...it.k].reduce((a, ch) => a + (ch.charCodeAt(0) > 0x2e80 ? 2 : 1), 0);
      s += `  ${it.k}${' '.repeat(Math.max(1, 16 - kw))} ${it.v || '(空)'}\n`;
    }
  }
  return s + `\n合计   ${cards.cards.length} 张卡 · ${n} 项\n`;
}

function actsText() {
  let s = '==== 动作表 ====\n';
  const live = acts.groups.reduce((a, g) => a + g.items.length, 0);
  s += `可执行  ${live} 项 / 表内 ${live} 条\n`;
  s += '说明    只列车机助手里真实存在的方法，运行时不存在的整条不出现\n';
  for (const g of acts.groups) {
    s += `\n[${g.g}]\n`;
    for (const it of g.items) {
      const kw = [...it.t].reduce((a, ch) => a + (ch.charCodeAt(0) > 0x2e80 ? 2 : 1), 0);
      s += `  ${it.t}${' '.repeat(Math.max(1, 14 - kw))} ${it.id}` + (it.v >= 0 ? `  v=${it.v}` : '') + '\n';
    }
  }
  return s;
}

const txt = (res, body) => {
  res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8', 'Access-Control-Allow-Origin': '*' });
  res.end(body);
};

http.createServer((req, res) => {
  const u = req.url.split('?')[0];
  const h = {
    'Content-Type': 'application/json; charset=utf-8',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS',
    'Cache-Control': 'no-store'
  };
  if (req.method === 'OPTIONS') { res.writeHead(204, h); res.end(); return; }
  let body = '';
  req.on('data', (c) => body += c);
  req.on('end', () => {
    if (u === '/' || u === '/index.html') {
      res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8', 'Access-Control-Allow-Origin': '*' });
      res.end(fs.readFileSync('D:/车机/车机桥接空调/web/car.html')); return;
    }
    if (u === '/cards') { res.writeHead(200, h); res.end(JSON.stringify(cards)); return; }
    if (u === '/acts') { res.writeHead(200, h); res.end(JSON.stringify(acts)); return; }
    if (u === '/probe') { res.writeHead(200, h); res.end(JSON.stringify(probe)); return; }
    if (u === '/' || u === '/index.html') {
      res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
      res.end(fs.readFileSync('D:/车机/车机桥接空调/web/car.html'));
      return;
    }
    if (u === '/ping') {
      const okN = ops.filter((o) => o.ok).length;
      res.writeHead(200, h);
      res.end(JSON.stringify({
        ok: 1, v: '1.2', port: 18766, bound: 1, helper: 1, activity: 1,
        ops: ops.length, opsFail: ops.length - okN, err: ''
      }));
      return;
    }
    if (u === '/diag') { txt(res, `${diag}\n-- 操作 --\n操作   ${ops.length} 次调用\n`); return; }
    if (u === '/acts.log' || u === '/acts.log.txt') { txt(res, opsText()); return; }
    if (u === '/cards.txt') { txt(res, cardsText()); return; }
    if (u === '/acts.txt') { txt(res, actsText()); return; }
    if (u === '/dump' || u === '/dump.txt') {
      txt(res, `${diag}\n${cardsText()}\n${actsText()}\n${opsText()}\n==== 结束 ====\n`); return;
    }
    if (u === '/log/clear') { ops.length = 0; res.writeHead(200, h); res.end('{"ok":1}'); return; }
    if (u === '/act') {
      let q = {};
      try { q = JSON.parse(body || '{}'); } catch (e) { q = {}; }
      const id = q.a || '';
      const v = q.v == null ? -1 : q.v;
      let label = id;
      for (const g of acts.groups) for (const it of g.items) if (it.id === id) label = it.t;
      // 模拟几种动作的效果，用来验证「执行后数值闪一下」
      const flip = { 'handleHvacAutoClick': ['空调信息', 'AUTO'], 'handleHvacCirculationClick': ['空调信息', '循环'],
        'handleTrunkClick': ['门窗信息', '后备箱'] };
      const f = flip[id];
      if (f) {
        const c = cards.cards.find((x) => x.title === f[0]);
        const it = c && c.items.find((x) => x.k === f[1]);
        if (it) it.v = it.v === '开' ? '关' : it.v === '关' ? '开' : (it.v === '内循环' ? '外循环' : '内循环');
      }
      ops.unshift({ at: new Date(), id, label, v, ok: true, ms: 18 + Math.floor(Math.random() * 40), err: '' });
      res.writeHead(200, h);
      res.end(JSON.stringify({ ok: 1, via: id }));
      return;
    }
    if (u === '/fan') {
      let q = {};
      try { q = JSON.parse(body || '{}'); } catch (e) { q = {}; }
      if (q.v != null) fanFront = Math.max(1, Math.min(7, q.v));
      if (q.r != null) fanRear = Math.max(0, Math.min(4, q.r));
      if (q.v != null || q.r != null) {
        ops.unshift({ at: new Date(), id: 'fan', label: `风量 ${q.v != null ? '前' + q.v : ''}${q.v != null && q.r != null ? ' /' : ''}${q.r != null ? ' 后' + q.r : ''}`, v: q.r != null ? q.r : q.v, ok: true, ms: 12, err: '' });
      }
      res.writeHead(200, h);
      res.end(JSON.stringify({ ok: 1, front: fanFront, rear: fanRear }));
      return;
    }
    res.writeHead(404, h); res.end('{}');
  });
}).listen(18766, '127.0.0.1');

console.log('preview up: http://127.0.0.1:18765/');
