/* ==========================================================================
   统一外壳（车控 18766 / 云音乐车机版 18765 / 记录仪 18767 共用同一份）
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
  // 记录仪页在老入口下本来就用绝对地址 18767，日志/状态同理；网关入口用相对路径走 18766 转发。
  // 注意：必须在 GATEWAY 赋值之后算，否则网关入口也会退化成绝对地址。
  var DVR_BASE = GATEWAY ? '' : ('http://' + HOST + ':' + DVR_PORT);

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
  var dvrSvc = null;      // 18767 服务在不在：只在进 tab / 手动重试时查一次，后台永不探
  var dvrDirect = null;   // 记录仪页探测到手机能直连 80 端口时为 true

  // ---------- 状态行 ----------

  function setStatus(text, kind) {
    el.sTxt.textContent = text;
    el.sDot.className = 'sdot' + (kind ? ' ' + kind : '');
  }

  function paintDots() {
    el.sDotCar.className = 'sn' + (carOk === true ? ' on' : (carOk === false ? ' off' : ''));
    el.sDotMusic.className = 'sn' + (musOk === true ? ' on' : (musOk === false ? ' off' : ''));
    if (el.sDotDvr) el.sDotDvr.className = 'sn'
      + (dvrOk === true ? ' on'
        : (dvrOk === false || dvrSvc === false ? ' off' : ''));
  }

  function paintStatus() {
    if (tab === 'car') {
      if (carOk === null) setStatus('正在连接车机助手…');
      else if (carOk) setStatus('已连接 · 车机助手', 'on');
      else setStatus('车机助手未就绪 · 到车机上打开一次桌面助手', 'warn');
    } else if (tab === 'dvr') {
      if (dvrSvc === null) setStatus('正在检测记录仪服务…');
      else if (dvrSvc === false) setStatus('记录仪服务没起 · 到车机上重启一次车机助手', 'warn');
      else if (dvrOk === null) setStatus('记录仪未连接 · 点「连接」手动读取');
      else if (dvrOk) setStatus('记录仪已连接' + (dvrDirect ? ' · 手机直连' : ' · 经车机中转'), 'on');
      else setStatus('记录仪已断开', 'warn');
    } else {
      if (musOk === null) setStatus('正在找音乐端…');
      else if (musOk) setStatus(GATEWAY ? '音乐端在跑' : '音乐端在跑 · ' + musPort, 'on');
      else setStatus('音乐端没响应 · 打开云音乐车机版', 'warn');
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

  /* 记录仪只查「服务在不在」，不代为连接。
     /dvr/state 只是读一下 18767 的状态，不会让记录仪开始连；
     调用时机只有三处：进 tab、手动重试、开机时停在记录仪页。后台心跳永不碰它。 */
  function probeDvr(cb, retried) {
    var done = false;
    var t = setTimeout(function () { finish(false); }, 1500);
    function finish(v) {
      if (done) return;
      done = true;
      clearTimeout(t);
      dvrSvc = v;
      if (v || retried) { cb(v); return; }
      // 18767 没在监听：让车机助手（18766，常驻）把记录仪代理叫起来，等端口上来再探一次。
      // 直接访问 18767 的页面不会走到这里 —— 服务没起时浏览器连页面都打不开，
      // 端口内没有任何代码能执行，所以「访问时自动拉起」只能由 18766 这一侧做。
      wakeDvr(function () { probeDvr(cb, true); });
    }
    fetch(DVR_BASE + '/dvr/state?_=' + Date.now(), withTimeout(1500))
      .then(function (r) { return r.json(); })
      .then(function (d) { finish(!!(d && d.ok === 1)); })
      .catch(function () { finish(false); });
  }

  /* 记录仪 18767 就在车机助手自己的进程里，这个端点只是幂等「确保在跑」，
     不再去拉起别的 App —— 车机助手活着它就活着，bind 是毫秒级。 */
  function wakeDvr(after) {
    setStatus('记录仪服务没起 · 正在重连…', 'warn');
    var done = false;
    var t = setTimeout(function () { if (!done) { done = true; after(); } }, 4000);
    fetch(CAR_BASE + '/dvrwake?_=' + Date.now(), withTimeout(4000))
      .then(function (r) { return r.json(); })
      .then(function (d) {
        if (done) return; done = true; clearTimeout(t);
        if (d && d.now === 1) toast('记录仪服务已就绪');
        else toast('记录仪端口没起来，到车机上重启记录仪代理');
        after();
      })
      .catch(function () {
        if (done) return; done = true; clearTimeout(t);
        after();
      });
  }

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
        ? '确认车机上的记录仪代理在跑（记录仪服务 18767），<br>再点右上角的刷新。'
        : '打开云音乐车机版（18765）就行，<br>它不是常驻服务，点一下等它起来。');
    // 按钮跟着当前这一路走：点一下只重探自己，不把人甩到别的 tab
    el.sFailGo.textContent = t === 'dvr' ? '重新检测' : '重试';
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
      // 已经确认这一路没在跑：别贴一个白框，直接给失败提示。
      // 三路各判各的，谁的端口死了只影响谁。
      // 记录仪看的是「服务在不在」（dvrSvc），不看连没连 —— 没连是常态，页面自己会摆「点连接」。
      var dead = (t === 'music' && musOk === false)
        || (t === 'car' && carOk === false)
        || (t === 'dvr' && dvrSvc === false);
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

  el.sTabCar.addEventListener('click', function () {
    if (carOk === false) {
      apply('car');              // 先把失败提示摆出来，别点了像没反应
      probeCar(function (ok) {
        paintDots();
        paintStatus();
        if (ok) apply('car');
      });
      return;
    }
    apply('car');
  });
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
    // 进 tab 只查一次「18767 服务在不在」，不预连接、不代连；
    // 连不连仍由记录仪页面上的「连接 / 断开」说了算。
    apply('dvr');
    probeDvr(function () {
      paintDots();
      paintStatus();
      if (tab === 'dvr') apply('dvr', true);
    });
  });

  el.sFailGo.addEventListener('click', function () {
    // 重试当前这一路，三路互不牵连
    if (tab === 'dvr') {
      probeDvr(function () { paintDots(); paintStatus(); apply('dvr'); });
      return;
    }
    if (tab === 'music') {
      probeMusic(function () { paintDots(); paintStatus(); apply('music'); });
      return;
    }
    probeCar(function () { paintDots(); paintStatus(); apply('car'); });
  });

  el.sReload.addEventListener('click', function () { location.reload(); });

  /* 记录仪页自己报状态：只有它在页面上真的点了「连接」拿到数据，或者点了「断开」，
     才把点亮成绿/灰。外壳全程不碰 18767，用户断开后就一直是断开。 */
  window.addEventListener('message', function (ev) {
    var d = ev.data;
    if (!d || d.src !== 'dvr' || typeof d.connected !== 'boolean') return;
    dvrOk = d.connected;
    dvrDirect = !!d.direct;
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
        if (!d || (d.ok !== 1 && d.ok !== true)) return '音乐端没响应，打开云音乐车机版';
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
      base: DVR_BASE,
      kvUrl: '/dvr/state',
      kv: function (d) {
        if (!d || d.ok !== 1) return '记录仪服务（18767）没响应';
        return '记录仪　' + (d.ip ? ('http://' + d.ip + ':80') : '未连接') + '\n'
          + '状态　' + (d.off ? '已断开' : '就绪') + ' · 日志 ' + (d.log || 0) + ' 条'
          + (d.cacheN ? ('\n缓存　' + d.cacheN + ' 个文件 / ' + (d.cacheMB || 0) + ' MB') : '');
      },
      segs: [
        { key: 'log', label: '运行日志', url: '/dvr/logs', dl: '/dvr/logs', file: '行车记录仪日志', slow: 8000,
          tip: '探测、抓目录页、缓存命中和失败原因，最新在最下面。' }
      ],
      dump: false, rec: false,
      help: '这一路是行车记录仪服务（18767）的连接与抓目录日志，跟另外两路无关。<br>'
        + '日志只在本机内存里，进程重启会清空；「下载」给的是服务器直出的 txt。'
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

  /* 原生剪贴板：http 页面拿不到 navigator.clipboard，execCommand 在 WebView 里也不稳。
     安卓走 CarRemoteNative.copy，iOS 走 WKWebView 的 carRemote 消息。 */
  function nativeCopy(text) {
    try {
      var N = window.CarRemoteNative;
      if (N && typeof N.copy === 'function') {
        if (N.copy(text) !== false) { toast('已复制'); return true; }
      }
      var ios = window.webkit && window.webkit.messageHandlers
        && window.webkit.messageHandlers.carRemote;
      if (ios) {
        ios.postMessage({ action: 'copy', text: text });
        toast('已复制');
        return true;
      }
    } catch (e) {}
    return false;
  }

  function copyText(text) {
    if (nativeCopy(text)) return;
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

  /* 原生存盘：安卓写系统下载目录，iOS 写临时文件后弹系统分享面板（可存到「文件」）。
     iOS 的 WKWebView 既不支持 a[download]，直接跳 txt 地址还会弹一个预览大框，所以必须走原生。 */
  function nativeSave(name, text) {
    try {
      var N = window.CarRemoteNative;
      if (N && typeof N.saveText === 'function') {
        if (N.saveText(name, text) !== false) {
          toast('已存到手机下载目录：' + name);
          return true;
        }
      }
      var ios = window.webkit && window.webkit.messageHandlers
        && window.webkit.messageHandlers.carRemote;
      if (ios) {
        ios.postMessage({ action: 'save', name: name, text: text });
        toast('已导出 ' + name + ' · 在分享面板里存到「文件」');
        return true;
      }
    } catch (e) {}
    return false;
  }

  /* 存盘三选一：手机端原生通道 → 取回文本走 Blob → 浏览器 Blob。
     三路都带的是当前 tab 那一份内容，不再混到别的服务上去。 */
  function deliver(name, text, fallbackUrl) {
    if (nativeSave(name, text)) return;
    if (fallbackUrl) {
      // 不直接跳地址：WKWebView 会把 txt 当预览弹出来，取回文本再存反而干净
      toast('正在准备 ' + name + '…');
      fetch(fallbackUrl, withTimeout(15000))
        .then(function (r) { return r.text(); })
        .then(function (t) { saveText(t, name); })
        .catch(function () { saveText(text, name); });
      return;
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
    if (tab === 'dvr') {
      var dname = '行车记录仪全量_' + stamp() + '.txt';
      toast('正在打包…');
      fetch(DVR_BASE + '/dvr/state?_=' + Date.now(), withTimeout(6000)).then(function (r) { return r.json(); })
        .then(function (st) {
          return fetch(DVR_BASE + '/dvr/logs?_=' + Date.now(), withTimeout(8000))
            .then(function (r) { return r.text(); })
            .then(function (lg) {
              var head = '# 行车记录仪全量导出\n导出时间: ' + new Date().toLocaleString() + '\n\n'
                + '连接状态\n'
                + '  地址　' + (st.ip ? ('http://' + st.ip + ':80') : '未连接') + '\n'
                + '  状态　' + (st.off ? '已断开' : '就绪') + '\n\n';
              deliver(dname, head + lg, '');
            });
        })
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
      .catch(function () { toast('连不上车控服务'); });
  });

  // ---------- 起 ----------

  window.addEventListener('resize', paneTop);

  apply(tab, true);
  D.classList.add('ready');
  paneTop();

  // 上次停在记录仪：开机补探一次服务，别一直卡在「正在检测记录仪服务…」
  if (tab === 'dvr') {
    probeDvr(function () {
      paintDots();
      paintStatus();
      if (tab === 'dvr') apply('dvr', true);
    });
  }

  probeCar(function () {
    paintDots();
    paintStatus();
    // 车控自己没起来：把死框换成提示，别停在一个白框上
    if (tab === 'car' && carOk === false) apply('car', true);
    // 记录仪不在这里探：它连不连由记录仪页面上的按钮决定，外壳不代为连接
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
      // 开局那次 apply 可能还没探到端口：探完按结果重摆一次（在线贴框，掉线给提示）
      if (tab !== NATIVE) apply(tab, true);
    });
  });

  setInterval(function () {
    if (document.hidden) return;
    // 只巡检已经在线的模块：掉线由用户点 tab 或点刷新触发重探，
    // 离线/未检测到的绝不自动重试。记录仪任何情况都不在后台探 ——
    // 免得用户在记录仪页点过「断开」又被自己拉回去。
    if (carOk === true) probeCar(function () { paintDots(); paintStatus(); });
    if (musOk === true) {
      if (GATEWAY) probeGateway();   // 统一入口：只问音乐后端在哪个端口
      else probeMusic(function () { paintDots(); paintStatus(); });
    }
  }, 15000);
})();
