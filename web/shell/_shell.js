/* ==========================================================================
   统一外壳（车机助手 18766 / 网易云车机版 18765 / 桥接包 18764 共用同一份）
   - 顶上一条状态行 + 一行中文切换「车机控制 / 音乐控制 / 行车记录仪」
   - 从 18766 进来是统一入口：三路全在本机，音乐/记录仪由网关转发，地址只记 18766
   - 从 18765 / 18764 进来是老入口：本端自带的音乐 + iframe 车控 / 记录仪，tab 与网关入口完全一致
   - 对端用 iframe 贴进来，不跳转、不闪
   ========================================================================== */
(function () {
  'use strict';

  var D = document.documentElement;
  if (D.className.indexOf('pane') >= 0) return;   // 内容页：外壳不参与

  var HOST = location.hostname || '127.0.0.1';
  var CAR_PORT = 18766;
  var DVR_PORT = 18767;
  var MUSIC_PORTS = [18765, 18764];
  var K_TAB = 'jb.tab';
  var K_MUS = 'jb.musPort';

  var CAR_BASE = 'http://' + HOST + ':' + CAR_PORT;
  // 拼接时写进来的构建号：并进 iframe 地址，页面一改 URL 就变，
  // 装新包后不用手动清缓存也一定拿到新页面；同一个包重复打开仍走缓存。
  var BUILD = '@SHELL_BUILD@';
  // 哪个端口发下来的页，就自带哪一路，不用 iframe 套自己
  var NATIVE = String(location.port) === String(CAR_PORT) ? 'car' : 'music';
  // 统一入口：从 18766 进来，三路都走本机 + 网关转发，地址只有一个
  var GATEWAY = NATIVE === 'car';

  var el = {};
  ['shellRoot', 'sBar', 'sDot', 'sTxt', 'sSet', 'sReload', 'sTabCar', 'sTabMusic',
   'sTabDvr', 'sDotCar', 'sDotMusic', 'sDotDvr', 'sPane', 'sFrame', 'sFail',
   'sFailT', 'sFailS', 'sFailGo',
   'sMask', 'sSheet', 'sClose', 'sTitle', 'sKv', 'sSegs', 'sGenRow', 'sGen', 'sCopy',
   'sSave', 'sTxt2', 'sTip', 'sHelp', 'sDump', 'sRecRow', 'sRec', 'sClear'
  ].forEach(function (id) { el[id] = document.getElementById(id); });

  var stored = localStorage.getItem(K_TAB);
  var tab = stored === 'car' || stored === 'music' || stored === 'dvr' ? stored : 'music';
  var musPort = parseInt(localStorage.getItem(K_MUS) || '0', 10) || 0;
  var carOk = null;      // null 还没探到 / true / false
  var musOk = null;
  // 记录仪只在用户点「连接」后才连，外壳绝不主动碰 18767。
  // 这里的状态由记录仪页 iframe 用 postMessage 报上来（on/off），null = 这一页还没说话。
  var dvrOk = null;

  // ---------- 状态行 ----------

  function setStatus(text, kind) {
    el.sTxt.textContent = text;
    el.sDot.className = 'sdot' + (kind ? ' ' + kind : '');
  }

  function paintDots() {
    el.sDotCar.className = 'sn' + (carOk === true ? ' on' : (carOk === false ? ' off' : ''));
    el.sDotMusic.className = 'sn' + (musOk === true ? ' on' : (musOk === false ? ' off' : ''));
    if (el.sDotDvr) el.sDotDvr.className = 'sn'
      + (dvrOk === true ? ' on' : (dvrOk === false ? ' off' : ''));
  }

  function paintStatus() {
    if (tab === 'car') {
      if (carOk === null) setStatus('正在连接车机助手…');
      else if (carOk) setStatus('已连接 · 车机助手', 'on');
      else setStatus('车机助手未就绪 · 到车机上打开一次桌面助手', 'warn');
    } else if (tab === 'dvr') {
      if (dvrOk === null) setStatus('记录仪未连接 · 点「连接」手动读取');
      else if (dvrOk) setStatus('记录仪已连接 · 18767', 'on');
      else setStatus('记录仪已断开', 'warn');
    } else {
      if (musOk === null) setStatus('正在找音乐端…');
      else if (musOk) setStatus(GATEWAY ? '音乐端在跑' : '音乐端在跑 · ' + musPort, 'on');
      else setStatus('音乐端没响应 · 打开汽水音乐或桥接包', 'warn');
    }
  }

  // ---------- 探活 ----------

  function withTimeout(ms) {
    var ctl = window.AbortController ? new AbortController() : null;
    var o = { cache: 'no-store' };
    if (ctl) { o.signal = ctl.signal; setTimeout(function () { ctl.abort(); }, ms); }
    return o;
  }

  function probeCar(cb) {
    var done = false;
    var t = setTimeout(function () { if (!done) { done = true; carOk = false; cb(false); } }, 2500);
    fetch(CAR_BASE + '/ping?_=' + Date.now(), withTimeout(2500))
      .then(function (r) { return r.json(); })
      .then(function (d) {
        if (done) return; done = true; clearTimeout(t);
        carOk = !!(d && d.ok === 1); cb(carOk);
      })
      .catch(function () {
        if (done) return; done = true; clearTimeout(t);
        carOk = false; cb(false);
      });
  }

  /* no-cors 只关心「连不连得上」：音乐端可能是别人的页，没有 /ping 也无所谓 */
  function probeOne(port, cb) {
    var done = false;
    var t = setTimeout(function () { if (!done) { done = true; cb(false); } }, 1400);
    fetch('http://' + HOST + ':' + port + '/?_=' + Date.now(),
          { mode: 'no-cors', cache: 'no-store' })
      .then(function () { if (done) return; done = true; clearTimeout(t); cb(true); })
      .catch(function () { if (done) return; done = true; clearTimeout(t); cb(false); });
  }

  /* 记录仪不探。18767 只在用户自己点开这一页、并且点了「连接」之后才有流量：
     外壳不做任何后台探测/预连接，避免用户明明断了还被后台重新连上。 */

  function probeMusic(cb) {
    if (GATEWAY) { probeGateway(cb); return; }
    var order = [];
    // 自己就是音乐服务端时，先认自己 —— 18764 的页不该报成 18765
    if (NATIVE === 'music' && location.port) order.push(location.port);
    if (musPort) order.push(musPort);
    MUSIC_PORTS.forEach(function (p) { if (order.indexOf(p) < 0) order.push(p); });
    var i = 0;
    (function step() {
      if (i >= order.length) {
        musOk = false; musPort = 0;
        localStorage.removeItem(K_MUS);
        cb(false);
        return;
      }
      var p = order[i++];
      probeOne(p, function (ok) {
        if (!ok) { step(); return; }
        musOk = true; musPort = p;
        localStorage.setItem(K_MUS, String(p));
        cb(true);
      });
    })();
  }

  /* 统一入口：问网关音乐后端在哪个端口。记录仪不在这里问 —— 网关一旦顺手探 18767，
     用户在记录仪页点过「断开」之后又会被后台连上，那正是要避免的。 */
  function probeGateway(cb) {
    fetch('/gateway?_=' + Date.now(), withTimeout(2500))
      .then(function (r) { return r.json(); })
      .then(function (d) {
        musOk = !!(d && d.music);
        musPort = d && d.music ? d.music : 0;
        if (cb) cb(musOk);
        paintDots();
      })
      .catch(function () {
        musOk = false;
        if (cb) cb(false);
        paintDots();
      });
  }

  // ---------- 切换 ----------

  function paneTop() {
    if (!el.sPane) return;
    var r = el.shellRoot ? el.shellRoot.getBoundingClientRect() : null;
    el.sPane.style.top = Math.max(0, Math.round(r ? r.bottom + 1 : 0)) + 'px';
    // 状态行固定在底部，内容区要按它的实际高度让位（含安全区）
    if (el.sBar) D.style.setProperty('--sbar-h', el.sBar.offsetHeight + 'px');
  }

  function paneUrl(t) {
    // 音乐直连音乐端自己的端口，不走 /music 反代。
    // 反代能取到页面，但 WebSocket 会被网关接管：实测车上表现为
    // 「握手成功 → 立刻断开 → 两秒后重连」，一直是「与车机断开连接」。
    // 直连 18765/18764 是验证过正常的那条路，WS 不经过任何中转。
    if (GATEWAY) {
      if (t === 'music') {
        var mp = musPort || MUSIC_PORTS[0];
        return 'http://' + HOST + ':' + mp + '/?pane=music';
      }
      if (t === 'dvr') return '/dvr?pane=dvr';
      return '/?pane=car';
    }
    // 从 18765 / 18764 进来：三路都用绝对地址，端口各归各的，tab 表现和网关入口一致
    if (t === 'car') return CAR_BASE + '/?pane=car';
    if (t === 'dvr') return 'http://' + HOST + ':' + DVR_PORT + '/dvr?pane=dvr';
    return musPort ? 'http://' + HOST + ':' + musPort + '/?pane=music' : '';
  }

  function showFail(t) {
    el.sFailT.textContent = t === 'car' ? '车机助手没响应'
      : (t === 'dvr' ? '行车记录仪没响应' : '音乐端没响应');
    el.sFailS.innerHTML = t === 'car'
      ? '到车机上确认「车机助手」在跑（18766），<br>再点右上角的刷新。'
      : (t === 'dvr'
        ? '确认车机上的桥接包在跑（记录仪服务 18767），<br>再点右上角的刷新。'
        : '车上跑的是第三方播放器就打开它（18765），<br>跑的是官方网易云就开桥接包（18764）。');
    el.sFail.className = 'sfail on';
  }

  function hideFail() { el.sFail.className = 'sfail'; }

  /* 两条路的宽度不一样（车控宽屏是两列、音乐是单列），
     外壳跟着当前这一路走，顶栏才跟下面的内容对得齐。 */
  function fitWidth(t) {
    var cs = getComputedStyle(D);
    var key = t === 'car' ? '--w-car' : (t === 'dvr' ? '--w-dvr' : '--w-music');
    var v = cs.getPropertyValue(key).trim();
    D.style.setProperty('--shell-w', v || '560px');
  }

  function apply(t, silent) {
    tab = t;
    localStorage.setItem(K_TAB, t);
    fitWidth(t);
    el.sTabCar.className = 'stab' + (t === 'car' ? ' on' : '');
    el.sTabMusic.className = 'stab' + (t === 'music' ? ' on' : '');
    if (el.sTabDvr) el.sTabDvr.className = 'stab' + (t === 'dvr' ? ' on' : '');

    if (t === NATIVE) {
      D.classList.remove('foreign');
      el.sPane.className = 'spane';
      el.sPane.style.top = '';
      hideFail();
    } else {
      D.classList.add('foreign');
      var url = paneUrl(t);
      // 已经确认这一路没在跑：别贴一个白框，直接给失败提示（和音乐那一路一致）。
      // 记录仪不参与判断 —— 它没连是常态，页面自己会摆「点连接」。
      var dead = (t === 'music' && GATEWAY && musOk === false);
      if (!url || dead) {
        // 清掉旧 src：等这一路恢复时可以重新拉一次，不会停在空白框上
        if (el.sFrame.getAttribute('src')) el.sFrame.removeAttribute('src');
        el.sPane.className = 'spane on';
        paneTop();
        showFail(t);
      } else {
        if (el.sFrame.getAttribute('src') !== url) el.sFrame.setAttribute('src', url);
        el.sPane.className = 'spane on';
        hideFail();
        paneTop();
      }
    }
    paintStatus();
    if (!silent) closeSheet();
  }

  el.sTabCar.addEventListener('click', function () { apply('car'); });
  el.sTabMusic.addEventListener('click', function () {
    if (musOk === false) {
      apply('music');            // 先把失败提示摆出来，别点了像没反应
      probeMusic(function (ok) {
        paintDots();
        if (ok) apply('music');
        else paintStatus();
      });
      return;
    }
    apply('music');
  });
  if (el.sTabDvr) el.sTabDvr.addEventListener('click', function () {
    // 记录仪只贴页面，不探端口、不预连接：连不连由页面上的「连接 / 断开」说了算
    apply('dvr');
  });

  el.sFailGo.addEventListener('click', function () { apply('car'); });

  el.sReload.addEventListener('click', function () { location.reload(); });

  /* 记录仪页自己报状态：只有它在页面上真的点了「连接」拿到数据，或者点了「断开」，
     才把点亮成绿/灰。外壳全程不碰 18767，用户断开后就一直是断开。 */
  window.addEventListener('message', function (ev) {
    var d = ev.data;
    if (!d || d.src !== 'dvr' || typeof d.connected !== 'boolean') return;
    dvrOk = d.connected;
    paintDots();
    if (tab === 'dvr') paintStatus();
  });

  // ---------- 设置 / 诊断：按当前 tab 分源，不再三路共用 18766 那一份 ----------
  // car 走 18766 自己的接口；music 走 /api/*（18766 网关会转给音乐后端，
  // 从 18765 / 18764 进来则是本端接口）；dvr 没有独立接口，只给说明。

  var SOURCES = {
    car: {
      name: '车机助手',
      base: CAR_BASE,
      kvUrl: CAR_BASE + '/ping',
      kv: function (d) {
        if (!d || d.ok !== 1) return '18766 没响应';
        return '车控　' + CAR_BASE + '\n'
          + '音乐　' + (musPort ? ('http://' + HOST + ':' + musPort) : '未探到') + '\n'
          + '记录仪　' + (dvrOk ? ('http://' + HOST + ':' + DVR_PORT) : '未连接（手动）') + '\n'
          + '服务　端口 ' + d.port + (d.bound ? ' 已绑定' : ' 未绑定') + ' · 接口 v' + d.v + '\n'
          + '引擎　' + (d.helper ? '已绑定' : '未绑定 ← 打开一次桌面助手')
          + ' · 界面 ' + (d.activity ? '已出现' : '未出现') + '\n'
          + '操作　' + (d.ops == null ? 0 : d.ops) + ' 次调用'
          + (d.opsFail ? ' · 失败 ' + d.opsFail : '')
          + (d.err ? '\n错误　' + d.err : '');
      },
      segs: [
        { key: 'diag', label: '诊断', url: '/diag', dl: '/diag.txt', file: '车机助手诊断', slow: 18000,
          tip: '端口、引擎绑定、安装包版本、动作项数、进程日志和 logcat 摘要。' },
        { key: 'log', label: '操作记录', url: '/acts.log', dl: '/acts.log.txt', file: '车机助手操作记录', slow: 6000,
          tip: '每次点车控按钮的结果和耗时，最新的在最上面。' },
        { key: 'cards', label: '车况', url: '/cards.txt', dl: '/cards.txt', file: '车机助手车况', slow: 6000,
          tip: '每张卡、每一项的名字和值。' },
        { key: 'acts', label: '动作表', url: '/acts.txt', dl: '/acts.txt', file: '车机助手动作表', slow: 6000,
          tip: '当前能执行的车控项和方法名。' }
      ],
      dump: true, rec: true,
      help: '这一路是车机助手（18766）的诊断：车控接口、车辆状态、动作表和操作记录。<br>'
        + '「下载」给的是服务器直出的 txt（UTF-8）。「停止记录」会钉住现场，删掉记录前先下载。'
    },
    music: {
      name: '音乐控制',
      base: '',
      kvUrl: '/api/status',
      kv: function (d) {
        if (!d || (d.ok !== 1 && d.ok !== true)) return '音乐端没响应，打开汽水音乐或网易云车机版';
        var s = '音乐　端口 ' + (d.port || '—') + ' · ' + (d.running ? '服务在跑' : '服务已停') + '\n'
          + '在线　' + (d.clients || 0) + (d.maxClients ? ' / ' + d.maxClients : '') + '\n'
          + '地址　' + (d.ip || '—');
        if (d.modeLabel) s += '\n音源　' + d.modeLabel;
        if (d.version) s += '\n版本　' + d.version;
        if (d.mdnsIp) s += '\n域名　' + d.mdnsIp;
        return s;
      },
      segs: [
        { key: 'diag', label: '诊断', url: '/api/diag', dl: '/api/diag', file: '音乐控制诊断', slow: 18000,
          tip: '音乐端运行状态、连接、mDNS、媒体请求耗时和最近错误。' },
        { key: 'log', label: '运行日志', url: '/api/logs', dl: '/api/logs', file: '音乐控制日志', slow: 6000,
          tip: '手机下发的播放指令与音乐端链路时序，最新在最下面。' }
      ],
      dump: false, rec: false,
      help: '这一路是音乐端的诊断和日志，跟车机助手那份不是一回事。<br>'
        + '「合并导出」把诊断和运行日志打在一个 txt 里；下载没反应时点「复制」。'
    },
    dvr: {
      name: '行车记录仪',
      base: '',
      kvUrl: null,
      kv: function () { return '记录仪没有独立的诊断接口。\n连接状态和设置请在「行车记录仪」页面里操作。'; },
      segs: [], dump: false, rec: false,
      help: '记录仪的日志由它自己的页面管理，这里不代取，也不跟另外两路混在一起。'
    }
  };

  var segCache = {};   // key = '源:段'，切 tab 不会串内容
  var segBusy = false;
  var curSegBySrc = { car: 'diag', music: 'diag', dvr: '' };

  function src() { return SOURCES[tab] || SOURCES.car; }
  function segCfg(seg) {
    var s = src();
    for (var i = 0; i < s.segs.length; i++) if (s.segs[i].key === seg) return s.segs[i];
    return null;
  }
  function cacheKey(which, seg) { return which + ':' + seg; }

  function renderSegs(s) {
    el.sSegs.innerHTML = '';
    s.segs.forEach(function (cfg) {
      var b = document.createElement('button');
      b.className = 'sstab';
      b.setAttribute('data-seg', cfg.key);
      b.textContent = cfg.label;
      el.sSegs.appendChild(b);
    });
    el.sSegs.style.display = s.segs.length ? '' : 'none';
  }

  function openSheet() {
    var s = src();
    el.sSheet.className = 'ssheet show';
    el.sMask.className = 'smask show';
    el.sTitle.textContent = s.name + ' · 设置与诊断';
    el.sKv.textContent = s.kvUrl ? '读取中…' : s.kv(null);
    if (s.kvUrl) {
      var which = tab;
      fetch(s.kvUrl + (s.kvUrl.indexOf('?') >= 0 ? '&' : '?') + '_=' + Date.now(), withTimeout(3500))
        .then(function (r) { return r.json(); })
        .then(function (d) { if (tab === which) el.sKv.textContent = s.kv(d); })
        .catch(function () {
          if (tab === which) el.sKv.textContent = s.name + '没响应，这一段的状态取不到。';
        });
    }
    renderSegs(s);
    el.sGenRow.style.display = s.segs.length ? '' : 'none';
    el.sTxt2.style.display = s.segs.length ? '' : 'none';
    el.sTip.style.display = s.segs.length ? '' : 'none';
    el.sDump.style.display = (s.dump || s.segs.length) ? '' : 'none';
    el.sDump.textContent = s.dump ? '全部导出' : (s.segs.length ? '合并导出' : '');
    el.sRec.style.display = s.rec ? '' : 'none';
    el.sClear.style.display = s.rec ? '' : 'none';
    el.sHelp.innerHTML = s.help;
    if (s.rec) refreshRec();
    var first = s.segs.length ? (curSegBySrc[tab] || s.segs[0].key) : '';
    if (s.segs.length && !segCfg(first)) first = s.segs[0].key;
    pickSeg(first);
  }

  function closeSheet() {
    el.sSheet.className = 'ssheet';
    el.sMask.className = 'smask';
  }

  function pickSeg(seg) {
    var s = src();
    var cfg = segCfg(seg);
    if (!cfg) return;
    curSegBySrc[tab] = seg;
    Array.prototype.forEach.call(el.sSegs.children, function (b) {
      b.classList.toggle('on', b.getAttribute('data-seg') === seg);
    });
    el.sTip.textContent = cfg.tip;
    var key = cacheKey(tab, seg);
    el.sTxt2.textContent = segCache[key] || ('点「生成」取' + cfg.file + '。');
    el.sTxt2.scrollTop = 0;
    if (!segCache[key] && !segBusy) loadSeg();
  }

  function loadSeg() {
    if (segBusy) return;
    var which = tab;
    var s = src();
    var seg = curSegBySrc[which];
    var cfg = segCfg(seg);
    if (!cfg) return;
    var key = cacheKey(which, seg);
    var url = s.base + cfg.url;
    segBusy = true;
    el.sTxt2.textContent = '读取中…';
    fetch(url + (url.indexOf('?') >= 0 ? '&' : '?') + '_=' + Date.now(), withTimeout(cfg.slow))
      .then(function (r) { return r.text(); })
      .then(function (t) {
        segBusy = false; segCache[key] = t;
        if (tab === which && curSegBySrc[which] === seg) {
          el.sTxt2.textContent = t; el.sTxt2.scrollTop = 0;
        }
      })
      .catch(function () {
        segBusy = false;
        if (tab === which && curSegBySrc[which] === seg) {
          el.sTxt2.textContent = '取不到：' + s.name + '没响应。\n'
            + '浏览器里直接打开 ' + url + ' 看看。';
        }
      });
  }

  function toast(text) {
    var t = document.getElementById('stoast');
    if (!t) {
      t = document.createElement('div');
      t.id = 'stoast';
      t.style.cssText = 'position:fixed;left:50%;bottom:34px;transform:translate(-50%,10px);'
        + 'max-width:80%;padding:10px 18px;border-radius:999px;z-index:200;'
        + 'background:rgba(28,28,30,.96);border:1px solid rgba(255,255,255,.12);'
        + 'font-size:13px;color:#fff;opacity:0;pointer-events:none;'
        + 'transition:opacity .18s,transform .18s;font-family:inherit';
      document.body.appendChild(t);
    }
    t.textContent = text;
    t.style.opacity = '1';
    t.style.transform = 'translate(-50%,0)';
    clearTimeout(toast._t);
    toast._t = setTimeout(function () {
      t.style.opacity = '0';
      t.style.transform = 'translate(-50%,10px)';
    }, 1600);
  }

  function copyText(text) {
    function fallback() {
      var ta = document.createElement('textarea');
      ta.value = text;
      ta.setAttribute('readonly', '');
      ta.style.cssText = 'position:fixed;left:-9999px';
      document.body.appendChild(ta);
      ta.select();
      ta.setSelectionRange(0, text.length);
      var ok = false;
      try { ok = document.execCommand('copy'); } catch (e) { ok = false; }
      document.body.removeChild(ta);
      toast(ok ? '已复制' : '复制被拦，长按文本全选');
    }
    if (navigator.clipboard && navigator.clipboard.writeText) {
      navigator.clipboard.writeText(text).then(function () { toast('已复制'); }, fallback);
    } else {
      fallback();
    }
  }

  function stamp() {
    var d = new Date();
    var p = function (n) { return (n < 10 ? '0' : '') + n; };
    return d.getFullYear() + p(d.getMonth() + 1) + p(d.getDate())
      + '-' + p(d.getHours()) + p(d.getMinutes());
  }

  function saveText(text, name) {
    try {
      var blob = new Blob([text], { type: 'text/plain;charset=utf-8' });
      var url = URL.createObjectURL(blob);
      var a = document.createElement('a');
      a.href = url;
      a.download = name;
      a.style.display = 'none';
      document.body.appendChild(a);
      a.click();
      setTimeout(function () {
        if (a.parentNode) a.parentNode.removeChild(a);
        URL.revokeObjectURL(url);
      }, 1500);
      toast('已保存 ' + name);
    } catch (e) {
      toast('下载不支持，长按文本复制');
    }
  }

  el.sSet.addEventListener('click', openSheet);
  el.sClose.addEventListener('click', closeSheet);
  el.sMask.addEventListener('click', closeSheet);
  el.sSegs.addEventListener('click', function (e) {
    var b = e.target.closest ? e.target.closest('.sstab') : null;
    if (b) pickSeg(b.getAttribute('data-seg'));
  });
  el.sGen.addEventListener('click', loadSeg);

  function currentKey() {
    var seg = curSegBySrc[tab];
    return seg ? cacheKey(tab, seg) : '';
  }

  /* 存盘三选一：手机端 JS 接口 → 服务端直出 txt → 浏览器 Blob。
     三路都带的是当前 tab 那一份内容，不再混到别的服务上去。 */
  function deliver(name, text, fallbackUrl) {
    var N = window.CarRemoteNative;
    if (N && typeof N.saveText === 'function') {
      try {
        if (N.saveText(name, text) !== false) {
          toast('已存到手机下载目录：' + name);
          return;
        }
      } catch (e) { /* 退回服务器下载 */ }
    }
    if (fallbackUrl) {
      toast('开始下载 ' + name);
      try { location.href = fallbackUrl; return; } catch (e) { /* 退回 Blob */ }
    }
    saveText(text, name);
  }

  el.sCopy.addEventListener('click', function () {
    var key = currentKey();
    if (!segCache[key]) { toast('先点「生成」'); return; }
    copyText(segCache[key]);
  });
  el.sSave.addEventListener('click', function () {
    var key = currentKey();
    if (!segCache[key]) { toast('先点「生成」'); return; }
    var s = src();
    var cfg = segCfg(curSegBySrc[tab]);
    var name = cfg.file + '_' + stamp() + '.txt';
    deliver(name, segCache[key], s.base + (cfg.dl || cfg.url) + '?_=' + Date.now());
  });
  el.sDump.addEventListener('click', function () {
    var s = src();
    if (!s.segs.length) return;
    if (s.dump) {
      var name = s.name + '全量_' + stamp() + '.txt';
      toast('正在打包…');
      fetch(CAR_BASE + '/dump?_=' + Date.now(), withTimeout(25000))
        .then(function (r) { return r.text(); })
        .then(function (t) { deliver(name, t, '/dump.txt'); })
        .catch(function () { toast('打包失败，先试单个「下载」'); });
      return;
    }
    if (tab !== 'music') return;
    // 音乐端没有服务端 dump：诊断 + 运行日志在前端拼成一份，来源仍是音乐那一路。
    var mname = '音乐控制全量_' + stamp() + '.txt';
    toast('正在打包…');
    Promise.all([
      fetch('/api/diag?_=' + Date.now(), withTimeout(18000)).then(function (r) { return r.text(); }),
      fetch('/api/logs?_=' + Date.now(), withTimeout(6000)).then(function (r) { return r.text(); }),
    ])
      .then(function (parts) {
        var t = '# 音乐控制全量导出\n导出时间: ' + new Date().toLocaleString() + '\n\n'
          + parts[0] + '\n\n--- 运行日志 ---\n' + parts[1];
        deliver(mname, t, '');
      })
      .catch(function () { toast('打包失败，先试单个「下载」'); });
  });

  /* 日志开关：停止之后 18766 不再往缓冲里写新行，已有的留着，
     正好把出问题那一段钉住，不会被后面的正常日志冲掉。 */
  function paintRec(on, lines) {
    if (!el.sRec) return;
    el.sRec.textContent = on ? '停止记录' : '开始记录';
    if (typeof lines === 'number') el.sRec.setAttribute('data-lines', String(lines));
  }

  function refreshRec() {
    fetch(CAR_BASE + '/log/rec?_=' + Date.now(), withTimeout(3000))
      .then(function (r) { return r.json(); })
      .then(function (d) { if (d && d.ok === 1) paintRec(d.rec === 1, d.lines); })
      .catch(function () { if (el.sRec) el.sRec.textContent = '记录开关'; });
  }

  el.sRec.addEventListener('click', function () {
    var turnOn = el.sRec.textContent.indexOf('停止') >= 0 ? false : true;
    fetch(CAR_BASE + '/log/rec?on=' + (turnOn ? 1 : 0) + '&_=' + Date.now(),
          withTimeout(3000))
      .then(function (r) { return r.json(); })
      .then(function (d) {
        if (!d || d.ok !== 1) { toast('开关失败'); return; }
        paintRec(d.rec === 1, d.lines);
        toast(d.rec === 1 ? '开始记录' : '已停止记录，现场保留 ' + d.lines + ' 行');
      })
      .catch(function () { toast('连不上 18766'); });
  });
  el.sClear.addEventListener('click', function () {
    fetch(CAR_BASE + '/log/clear', { cache: 'no-store' })
      .then(function (r) { return r.json(); })
      .then(function (r) {
        if (r && r.ok === 1) { delete segCache[cacheKey(tab, 'log')]; toast('操作记录已清空'); }
        else toast('清空失败');
      })
      .catch(function () { toast('连不上桥接服务'); });
  });

  // ---------- 起 ----------

  window.addEventListener('resize', paneTop);

  apply(tab, true);
  D.classList.add('ready');
  paneTop();

  probeCar(function () {
    paintDots();
    paintStatus();
    // 记录仪不探：它连不连由记录仪页面上的按钮决定，外壳不代为连接
    // 第一次进来默认落音乐；音乐端没有就退回车控，别让人对着空框
    if (tab === 'music' && !stored) {
      probeMusic(function (ok) {
        paintDots();
        if (!ok) apply('car');
        else apply('music', true);   // 探到了就把框补上，别停在「没响应」上
      });
      return;
    }
    probeMusic(function (ok) {
      paintDots();
      paintStatus();
      // 开局那次 apply 可能还没探到端口，补一次
      if (ok && tab !== NATIVE) apply(tab, true);
    });
  });

  setInterval(function () {
    if (document.hidden) return;
    probeCar(function () { paintDots(); paintStatus(); });
    if (GATEWAY) probeGateway();   // 统一入口：只问音乐后端在哪个端口
    // 老入口的音乐页自己探自己的端口；记录仪两路都不探
  }, 15000);
})();
