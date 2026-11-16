/* 仅用于本地预览车控页：模拟 18765（网页）与 18766（车机助手） */
const http = require('http');
const fs = require('fs');

const PAGE = process.argv[2] || 'D:/车机/车机桥接空调/work/cloudmusic-car/app/src/main/assets/remote.html';

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
    { g: '车身', items: [
      { id: 'handleTrunkClick', t: '后备箱', v: -1 }, { id: 'handleWirelessChargingClick', t: '无线充电', v: -1 },
      { id: 'handlePedestrianAlertClick', t: '行人提示音', v: -1 },
      { id: 'actionSetChildLock', t: '儿童锁 开', v: 1 }, { id: 'actionSetChildLock', t: '儿童锁 关', v: 0 }] },
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
    if (u === '/ping') { res.writeHead(200, h); res.end(JSON.stringify({ ok: 1, v: '1.1', port: 18766, bound: 1, helper: 1, activity: 1, err: '' })); return; }
    if (u === '/diag') {
      res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8', 'Access-Control-Allow-Origin': '*' });
      res.end(diag); return;
    }
    if (u === '/act') { res.writeHead(200, h); res.end(JSON.stringify({ ok: 1, act: (JSON.parse(body || '{}').a), via: 'mock' })); return; }
    res.writeHead(404, h); res.end('{}');
  });
}).listen(18766, '127.0.0.1');

console.log('preview up: http://127.0.0.1:18765/');
