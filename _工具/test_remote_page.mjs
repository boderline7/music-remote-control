/**
 * 网页遥控器的黑盒测试：用最小 DOM + WebSocket 模拟跑 remote.html 里的脚本，
 * 只验证「发出什么指令」和「界面上出现了什么」，不验证样式。
 *
 * 跑法： node _工具/test_remote_page.mjs
 */
import fs from 'node:fs';

const HTML_PATH = new URL('../车机端/cloudmusic-car/app/src/main/assets/remote.html', import.meta.url);
const html = fs.readFileSync(HTML_PATH, 'utf8');
const script = html.match(/<script>([\s\S]*?)<\/script>/)[1];

let failures = 0;
function eq(actual, expected, label) {
  const a = JSON.stringify(actual);
  const b = JSON.stringify(expected);
  if (a !== b) {
    failures++;
    console.log(`  x ${label}\n      实际 ${a}\n      期望 ${b}`);
  } else {
    console.log(`  ok ${label}`);
  }
}
function ok(cond, label) {
  if (!cond) { failures++; console.log(`  x ${label}`); }
  else console.log(`  ok ${label}`);
}

// ---------------- 最小 DOM ----------------
class El {
  constructor(tag) {
    this.tagName = String(tag).toUpperCase();
    this.children = [];
    this.parent = null;
    this._text = '';
    this._html = '';
    this.listeners = {};
    this.className = '';
    this.value = '';
    this.max = 100;
    this.disabled = false;
    this.scrollTop = 0;
    this.id = '';
    this.style = { props: {}, setProperty: (k, v) => { this.style.props[k] = v; } };
    const self = this;
    const set = () => new Set(String(self.className).split(/\s+/).filter(Boolean));
    this.classList = {
      add(c) { const s = set(); s.add(c); self.className = [...s].join(' '); },
      remove(c) { const s = set(); s.delete(c); self.className = [...s].join(' '); },
      contains(c) { return set().has(c); },
    };
  }
  get firstChild() { return this.children[0] || null; }
  appendChild(c) { c.parent = this; this.children.push(c); return c; }
  removeChild(c) {
    const i = this.children.indexOf(c);
    if (i >= 0) this.children.splice(i, 1);
    c.parent = null;
    return c;
  }
  setAttribute(k, v) { (this.attrs = this.attrs || {})[k] = String(v); }
  getAttribute(k) { return (this.attrs || {})[k] ?? null; }
  addEventListener(t, f) { (this.listeners[t] = this.listeners[t] || []).push(f); }
  dispatch(t, ev) {
    const e = ev || { preventDefault() {}, stopPropagation() {} };
    (this.listeners[t] || []).forEach((f) => f(e));
  }
  click() { this.dispatch('click'); }
  blur() { doc.activeElement = null; }
  set textContent(v) { this._text = String(v); this.children.length = 0; }
  get textContent() { return this._text + this.children.map((c) => c.textContent).join(''); }
  set innerHTML(v) { this._html = String(v); this.children.length = 0; }
  get innerHTML() { return this._html; }
}

const ids = [...html.matchAll(/id="([^"]+)"/g)].map((m) => m[1]);
const store = new Map(ids.map((id) => [id, new El(id === 'prog' || id === 'vol' ? 'input' : 'div')]));

const doc = {
  activeElement: null,
  hidden: false,
  visibilityListeners: [],
  getElementById(id) { return store.get(id) || null; },
  createElement(tag) { return new El(tag); },
  addEventListener(t, f) { if (t === 'visibilitychange') this.visibilityListeners.push(f); },
};

const byId = (id) => store.get(id);
const textOf = (n) => (n ? n.textContent : '');

/**
 * 模拟真实冒泡：从节点往上逐层触发 click，直到有人 stopPropagation。
 * 用来验证「点行内播放钮不会顺带把列表也打开」——真实浏览器里就是这样。
 */
function clickBubbling(node) {
  const ev = { stopped: false, preventDefault() {}, stopPropagation() { this.stopped = true; } };
  let cur = node;
  while (cur) {
    cur.dispatch('click', ev);
    if (ev.stopped) break;
    cur = cur.parent;
  }
}

const ls = {
  _m: new Map(),
  getItem(k) { return this._m.has(k) ? this._m.get(k) : null; },
  setItem(k, v) { this._m.set(k, String(v)); },
  removeItem(k) { this._m.delete(k); },
};

// ---------------- WebSocket ----------------
let wsInstance = null;
class WS {
  constructor(url) {
    this.url = url;
    this.readyState = 0;
    this.sent = [];
    wsInstance = this;
  }
  send(s) { this.sent.push(JSON.parse(s)); }
  close() { this.readyState = 3; if (this.onclose) this.onclose(); }
  open() { this.readyState = 1; if (this.onopen) this.onopen(); }
  push(type, data, id) {
    if (this.onmessage) this.onmessage({ data: JSON.stringify({ type, data: data || {}, id }) });
  }
  last(type) {
    const hit = this.sent.filter((m) => (type ? m.type === type : true));
    return hit[hit.length - 1];
  }
  count(type, action) {
    return this.sent.filter((m) => m.type === type && (!action || m.data.action === action)).length;
  }
}

// 定时器换成假的：进度插值和心跳不能真的跑
const intervals = [];
const timeouts = [];
const fakeSetInterval = (f, ms) => { intervals.push({ f, ms }); return intervals.length; };
const fakeSetTimeout = (f, ms) => { timeouts.push({ f, ms }); return timeouts.length; };

const run = new Function(
  'window', 'document', 'localStorage', 'location', 'WebSocket',
  'setInterval', 'setTimeout', 'clearTimeout', 'clearInterval',
  script,
);
run(
  {}, doc, ls, { host: 'carremote.local:18765', protocol: 'http:' }, WS,
  fakeSetInterval, fakeSetTimeout, () => {}, () => {},
);

const ws = wsInstance;

// ---------------- 用例 ----------------
console.log('\n[1] 连接与配对');
ok(!!ws, '页面一加载就发起 WebSocket 连接');
eq(ws.url, 'ws://carremote.local:18765/', 'WS 地址取当前 host');
ws.open();
eq(ws.last('pair').data.deviceId.slice(0, 4), 'web-', '首次连接发 pair，带 web- 设备号');
eq(ws.last('pair').data.pin, '', '免 PIN 配对');

ws.push('pair_result', { ok: true, token: 'tok-1', carName: '车机' });
eq(ls.getItem('carremote.token'), 'tok-1', '配对成功后保存 token');
ok(ws.count('command', 'getState') === 1, '配对后立刻拉状态');
ok(ws.count('command', 'library') === 1 && ws.count('command', 'catalog') === 1, '配对后自动拉资料库和榜单');
ok(textOf(byId('status')).includes('车机'), `状态栏显示车机名（${textOf(byId('status'))}）`);

console.log('\n[2] 播放状态与爱心');
ws.push('state', {
  seq: 1, playing: true, title: '晴天', artist: '周杰伦',
  positionMs: 1000, durationMs: 200000, liked: false, volume: 10, volumeMax: 30,
});
eq(textOf(byId('title')), '晴天', '曲名');
eq(textOf(byId('artist')), '周杰伦', '歌手');
ok(byId('playIcon').innerHTML.includes('M6.5 5h4v14'), '播放中显示暂停图标');
eq(byId('dur').textContent, '3:20', '总时长格式化');
ok(!byId('volumeRow').classList.contains('hidden'), '音量为数值时显示音量行');
eq(byId('vol').max, 30, '音量上限来自车机');
ok(!byId('like').classList.contains('on'), '未喜欢时是空心');

byId('like').click();
eq(ws.last('command').data.action, 'like', '点爱心发 like');
ok(byId('like').classList.contains('on'), '点完立刻变实心（乐观更新）');
ws.push('state', { seq: 2, playing: true, title: '晴天', artist: '周杰伦', positionMs: 2000, durationMs: 200000, liked: true, volume: 10, volumeMax: 30 });
ok(byId('like').classList.contains('on'), '车机回报 liked=true 后保持实心');

console.log('\n[3] 播控 / 进度 / 音量');
byId('play').click();
eq(ws.last('command').data.action, 'pause', '播放中点主键发 pause');
byId('next').click();
eq(ws.last('command').data.action, 'next', '下一首');
byId('prev').click();
eq(ws.last('command').data.action, 'prev', '上一首');

byId('prog').value = 60000;
byId('prog').dispatch('input');
byId('prog').dispatch('change');
eq(ws.last('command').data, { action: 'seek', args: { positionMs: 60000 } }, '拖进度条发 seek');

byId('volUp').click();
eq(ws.last('command').data.action, 'setVolume', '音量 + 发 setVolume');
const up = ws.last('command').data.args.volume;
ok(up > 10, `音量按 1/15 步进往上（${up}）`);
byId('volDown').click();
byId('volDown').click();
ok(ws.last('command').data.args.volume < 10, '连点两下减得更多');

console.log('\n[4] 资料库');
ws.push('library', {
  ok: true,
  liked: { id: 'liked', name: '我喜欢的音乐', count: 128 },
  cloud: { id: 'cloud', name: '音乐云盘', count: 7 },
  playlists: [
    { id: '111', name: '通勤', count: 30 },
    { id: '222', name: '深夜', count: 12 },
  ],
});
eq(textOf(byId('likedCount')), '128 首', '喜欢数');
eq(textOf(byId('cloudCount')), '7 首', '云盘数');
eq(byId('playlists').children.length, 2, '自建歌单渲染成 2 行');

byId('tileLiked').click();
eq(ws.last('command').data, { action: 'getTracks', args: { id: 'liked', offset: 0 } }, '点「我喜欢的音乐」拉第一页');
ok(byId('sheet').classList.contains('open'), '曲目面板打开');
eq(textOf(byId('sheetTitle')), '我喜欢的音乐', '面板标题');

byId('back').click();
ok(!byId('sheet').classList.contains('open'), '返回后关闭面板');

byId('playlists').children[1].click();
eq(ws.last('command').data, { action: 'getTracks', args: { id: '222', offset: 0 } }, '点自建歌单拉这个歌单');

console.log('\n[5] 分页与播放');
const pageData = (from, n) => Array.from({ length: n }, (_, i) => ({
  index: from + i, name: '歌' + (from + i), artist: '歌手', durationMs: 200000, playable: true,
}));
ws.push('tracks', { ok: true, id: '222', total: 45, offset: 0, count: 20, tracks: pageData(0, 20) });
eq(byId('sheetBody').children.length, 21, '20 首 + 加载更多按钮');
eq(textOf(byId('sheetBody').children[20]), '加载更多（20/45）', '加载更多文案');
eq(textOf(byId('sheetSub')), '20 / 45 首', '副标题进度');

byId('sheetBody').children[20].click();
eq(ws.last('command').data, { action: 'getTracks', args: { id: '222', offset: 20 } }, '加载更多用已收到条数做 offset');

ws.push('tracks', { ok: true, id: '222', total: 45, offset: 20, count: 20, tracks: pageData(20, 20) });
eq(byId('sheetBody').children.length, 41, '追加到 40 首 + 按钮');
eq(textOf(byId('sheetBody').children[40]), '加载更多（40/45）', '第二页文案');

ws.push('tracks', { ok: true, id: '222', total: 45, offset: 40, count: 5, tracks: pageData(40, 5) });
eq(byId('sheetBody').children.length, 45, '最后一页没有多余按钮');

byId('sheetBody').children[3].click();
eq(ws.last('command').data, { action: 'playTracks', args: { id: '222', index: 3 } }, '点歌发 playTracks（绝对序号）');
ok(!byId('sheet').classList.contains('open'), '点播后收起面板');
eq(byId('scroll').scrollTop, 0, '点播后回到顶部');

console.log('\n[6] 过期列表不串台');
byId('tileCloud').click();
ws.push('tracks', { ok: true, id: 'liked', total: 1, offset: 0, count: 1, tracks: pageData(0, 1) });
eq(textOf(byId('sheetBody')), '加载中…', '旧列表的回包被丢掉（面板仍停在加载中）');

console.log('\n[7] 搜索');
byId('q').value = '  晴天  ';
byId('go').click();
eq(ws.last('command').data, { action: 'search', args: { q: '晴天' } }, '搜索前 trim');
eq(textOf(byId('sheetTitle')), '搜索：晴天', '面板标题是搜索词');
ws.push('tracks', { ok: true, id: 'search:晴天', total: 3, offset: 0, count: 3, tracks: pageData(0, 3) });
eq(byId('sheetBody').children.length, 3, '搜索结果渲染 3 条');
byId('sheetBody').children[2].click();
eq(ws.last('command').data, { action: 'playTracks', args: { id: 'search:晴天', index: 2 } }, '搜索结果可直接点播');

console.log('\n[8] 榜单与推荐');
const tops7 = Array.from({ length: 7 }, (_, i) => ({ id: 'top:' + i, name: 'TOP' + i, count: 100 }));
ws.push('catalog', {
  ok: true,
  top: tops7,
  recommend: [{ id: 'rec:9', name: '每日推荐', count: 30 }],
});
ok(textOf(byId('tops').children[0]).startsWith('TOP0'), '排行榜行');
ok(textOf(byId('recs').children[0]).startsWith('每日推荐'), '推荐行');
byId('recs').children[0].click();
eq(ws.last('command').data, { action: 'getTracks', args: { id: 'rec:9', offset: 0 } }, '推荐歌单 id 原样回传');

console.log('\n[8b] 排行榜折叠 + 行内顺序播放');
eq(byId('tops').children.length, 6, '7 个榜单默认只铺 5 个 + 一行展开');
eq(textOf(byId('tops').children[5]), '展开全部（7 个）', '排行榜展开按钮文案');
byId('tops').children[5].click();
eq(byId('tops').children.length, 8, '排行榜展开后铺满 7 个 + 一行收起');
eq(textOf(byId('tops').children[7]), '收起', '排行榜收起按钮文案');
byId('tops').children[7].click();
eq(byId('tops').children.length, 6, '排行榜收起后回到 5 个');

const top0 = byId('tops').children[0];
eq(top0.children.length, 4, '入口行 = 名称 + 首数 + 播放钮 + 箭头');
const titleBefore = textOf(byId('sheetTitle'));
clickBubbling(top0.children[2]);
eq(ws.last('command').data, { action: 'playTracks', args: { id: 'top:0', index: 0 } }, '点排行榜播放钮直接整列顺序播');
eq(textOf(byId('sheetTitle')), titleBefore, '顺序播放不打开列表面板');
top0.click();
eq(ws.last('command').data, { action: 'getTracks', args: { id: 'top:0', offset: 0 } }, '点行本身才进列表');

console.log('\n[9] 空列表 / 失败 / 指令错误');
byId('reload').click();
ok(ws.count('command', 'library') === 2 && ws.count('command', 'catalog') === 2, '刷新重新拉资料库和榜单');
// 上一段最后停在 top:0 的列表上，这里退回 rec:9 —— 下面要验「这个列表取歌失败」的提示
byId('recs').children[0].click();
ws.push('library', { ok: false, message: '车机还没登录网易云' });
eq(textOf(byId('libHint')), '车机还没登录网易云', '资料库失败原因直接显示');
ws.push('tracks', { ok: false, id: 'rec:9', message: '歌单读取失败' });
ok(textOf(byId('sheetBody')).startsWith('歌单读取失败'), '取歌失败显示原因');
ws.push('ack', { ok: false, action: 'playTracks', message: '这个列表是空的' });
eq(textOf(byId('toast')), '指令失败：这个列表是空的', 'ack 失败弹提示');

console.log('\n[10] 歌单折叠与「为你推荐」');
const many = Array.from({ length: 7 }, (_, i) => ({ id: String(300 + i), name: 'PL' + i, count: 10 }));
ws.push('library', {
  ok: true,
  liked: { id: 'liked', name: '我喜欢的音乐', count: 128 },
  cloud: { id: 'cloud', name: '音乐云盘', count: 7 },
  playlists: many,
});
eq(byId('playlists').children.length, 6, '7 个歌单默认只铺 5 个 + 一行展开');
eq(textOf(byId('playlists').children[5]), '展开全部（7 个）', '展开按钮文案');
clickBubbling(byId('playlists').children[0].children[2]);
eq(ws.last('command').data, { action: 'playTracks', args: { id: '300', index: 0 } }, '歌单行播放钮顺序播放整列');
byId('playlists').children[5].click();
eq(byId('playlists').children.length, 8, '展开后铺满 7 个 + 一行收起');
eq(textOf(byId('playlists').children[7]), '收起', '收起按钮文案');
byId('playlists').children[7].click();
eq(byId('playlists').children.length, 6, '收起后回到 5 个');

// 这两行是写死在 HTML 里的静态入口，测试用的最小 DOM 不解析 HTML 结构，
// 只能验「元素在、点了发对指令」，文案得靠肉眼看页面。
ok(!!byId('dailyRow') && !!byId('hbRow'), '资料库下面有每日推荐 + 心动模式两个入口');
ok(html.indexOf('id="dailyRow"') < html.indexOf('id="playlists"'), '每日推荐排在自建歌单上面');
byId('dailyRow').click();
eq(ws.last('command').data, { action: 'getTracks', args: { id: 'daily', offset: 0 } }, '点每日推荐拉 daily 列表');
eq(textOf(byId('sheetTitle')), '每日推荐', '每日推荐面板标题');
byId('back').click();
byId('hbRow').click();
eq(ws.last('command').data, { action: 'getTracks', args: { id: 'heartbeat', offset: 0 } }, '点心动模式拉 heartbeat 列表');

const recs7 = Array.from({ length: 7 }, (_, i) => ({ id: 'rec:' + (900 + i), name: 'RC' + i, count: 30 }));
ws.push('catalog', { ok: true, top: [], recommend: recs7 });
eq(byId('recs').children.length, 6, '推荐歌单默认也只铺 5 个 + 一行展开');

// 每日推荐 / 心动模式的播放钮：静态写在 HTML 里，这里只验点了发什么
clickBubbling(byId('dailyPlay'));
eq(ws.last('command').data, { action: 'playTracks', args: { id: 'daily', index: 0 } }, '每日推荐可直接顺序播');
clickBubbling(byId('hbPlay'));
eq(ws.last('command').data, { action: 'playTracks', args: { id: 'heartbeat', index: 0 } }, '心动模式可直接顺序播');

console.log('\n[11] 断线重连与授权失效');
ws.readyState = 3;
ws.onclose();
ok(textOf(byId('status')).includes('重连'), '断线后状态栏提示重连');
ok(timeouts.length > 0, '安排了重连定时器');

ls.setItem('carremote.token', 'tok-old');
ws.push('auth_result', { ok: false });
eq(ls.getItem('carremote.token'), null, '授权失效后清掉 token');
eq(ws.last('pair').data.deviceId.slice(0, 4), 'web-', '自动重新配对');

console.log(`\n${failures === 0 ? 'ALL WEB REMOTE CHECKS PASSED' : failures + ' CHECK(S) FAILED'}`);
process.exit(failures === 0 ? 0 : 1);
