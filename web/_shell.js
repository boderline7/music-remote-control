/* ==========================================================================
   统一外壳（18766 / 18765 / 18764 共用同一份）
   - 顶上只有一条状态行 + 一行中文切换「车机控制 / 音乐控制」
   - 车机控制在 18766，音乐控制是 18765（第三方）或 18764（桥接包）
   - 自己这一端就能提供的直接显示；对端用 iframe 贴进来，不跳转、不闪
   ========================================================================== */
(function () {
  'use strict';

  var D = document.documentElement;
  if (D.className.indexOf('pane') >= 0) return;   // 内容页：外壳不参与

  var HOST = location.hostname || '127.0.0.1';
  var CAR_PORT = 18766;
  var MUSIC_PORTS = [18765, 18764];
  var K_TAB = 'jb.tab';
  var K_MUS = 'jb.musPort';

  var CAR_BASE = 'http://' + HOST + ':' + CAR_PORT;
  // 哪个端口发下来的页，就自带哪一路，不用 iframe 套自己
  var NATIVE = String(location.port) === String(CAR_PORT) ? 'car' : 'music';

  var el = {};
  ['shellRoot', 'sBar', 'sDot', 'sTxt', 'sSet', 'sReload', 'sTabCar', 'sTabMusic',
   'sDotCar', 'sDotMusic', 'sPane', 'sFrame', 'sFail', 'sFailT', 'sFailS', 'sFailGo',
   'sMask', 'sSheet', 'sClose', 'sKv', 'sSegs', 'sGen', 'sCopy', 'sSave', 'sTxt2',
   'sTip', 'sDump', 'sClear'
  ].forEach(function (id) { el[id] = document.getElementById(id); });

  var stored = localStorage.getItem(K_TAB);
  var tab = stored === 'car' || stored === 'music' ? stored : 'music';
  var musPort = parseInt(localStorage.getItem(K_MUS) || '0', 10) || 0;
  var carOk = null;      // null 还没探到 / true / false
  var musOk = null;

  // ---------- 状态行 ----------

  function setStatus(text, kind) {
    el.sTxt.textContent = text;
    el.sDot.className = 'sdot' + (kind ? ' ' + kind : '');
  }

  function paintDots() {
    el.sDotCar.className = 'sn' + (carOk === true ? ' on' : (carOk === false ? ' off' : ''));
    el.sDotMusic.className = 'sn' + (musOk === true ? ' on' : (musOk === false ? ' off' : ''));
  }

  function paintStatus() {
    if (tab === 'car') {
      if (carOk === null) setStatus('正在连接车机助手…');
      else if (carOk) setStatus('已连接 · 车机助手', 'on');
      else setStatus('车机助手未就绪 · 到车机上打开一次桌面助手', 'warn');
    } else {
      if (musOk === null) setStatus('正在找音乐端…');
      else if (musOk) setStatus('音乐端在跑 · ' + musPort, 'on');
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

  function probeMusic(cb) {
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

  // ---------- 切换 ----------

  function paneTop() {
    if (!el.sPane) return;
    var r = el.shellRoot ? el.shellRoot.getBoundingClientRect() : null;
    el.sPane.style.top = Math.max(0, Math.round(r ? r.bottom + 1 : 0)) + 'px';
    // 状态行固定在底部，内容区要按它的实际高度让位（含安全区）
    if (el.sBar) D.style.setProperty('--sbar-h', el.sBar.offsetHeight + 'px');
  }

  function paneUrl(t) {
    if (t === 'car') return 'http://' + HOST + ':' + CAR_PORT + '/?pane=car';
    return musPort ? 'http://' + HOST + ':' + musPort + '/?pane=music' : '';
  }

  function showFail(t) {
    el.sFailT.textContent = t === 'car' ? '车机助手没响应' : '音乐端没响应';
    el.sFailS.innerHTML = t === 'car'
      ? '到车机上确认「车机助手」在跑（18766），<br>再点右上角的刷新。'
      : '车上跑的是第三方播放器就打开它（18765），<br>跑的是官方网易云就开桥接包（18764）。';
    el.sFail.className = 'sfail on';
  }

  function hideFail() { el.sFail.className = 'sfail'; }

  /* 两条路的宽度不一样（车控宽屏是两列、音乐是单列），
     外壳跟着当前这一路走，顶栏才跟下面的内容对得齐。 */
  function fitWidth(t) {
    var cs = getComputedStyle(D);
    var v = cs.getPropertyValue(t === 'car' ? '--w-car' : '--w-music').trim();
    D.style.setProperty('--shell-w', v || '560px');
  }

  function apply(t, silent) {
    tab = t;
    localStorage.setItem(K_TAB, t);
    fitWidth(t);
    el.sTabCar.className = 'stab' + (t === 'car' ? ' on' : '');
    el.sTabMusic.className = 'stab' + (t === 'music' ? ' on' : '');

    if (t === NATIVE) {
      D.classList.remove('foreign');
      el.sPane.className = 'spane';
      el.sPane.style.top = '';
      hideFail();
    } else {
      D.classList.add('foreign');
      var url = paneUrl(t);
      if (!url) {
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

  el.sFailGo.addEventListener('click', function () { apply('car'); });

  el.sReload.addEventListener('click', function () { location.reload(); });

  // ---------- 设置 / 诊断（数据永远取 18766） ----------

  var SEGS = {
    diag: { url: '/diag', file: '车机诊断', slow: 18000,
      tip: '端口、引擎绑定、安装包版本、动作项数、进程日志和 logcat 摘要。' },
    log: { url: '/acts.log', file: '操作记录', slow: 6000,
      tip: '每次点按钮的结果和耗时，最新的在最上面。' },
    cards: { url: '/cards.txt', file: '车况快照', slow: 6000,
      tip: '每张卡、每一项的名字和值。' },
    acts: { url: '/acts.txt', file: '动作表', slow: 6000,
      tip: '当前能执行的项和方法名。' }
  };
  var curSeg = 'diag';
  var segCache = {};
  var segBusy = false;

  function openSheet() {
    el.sSheet.className = 'ssheet show';
    el.sMask.className = 'smask show';
    el.sKv.textContent = '读取中…';
    fetch(CAR_BASE + '/ping?_=' + Date.now(), withTimeout(3500))
      .then(function (r) { return r.json(); })
      .then(function (d) {
        if (!d || d.ok !== 1) { el.sKv.textContent = '18766 没响应'; return; }
        el.sKv.textContent = '车控　' + CAR_BASE + '\n'
          + '音乐　' + (musPort ? ('http://' + HOST + ':' + musPort) : '未探到') + '\n'
          + '服务　端口 ' + d.port + (d.bound ? ' 已绑定' : ' 未绑定') + ' · 接口 v' + d.v + '\n'
          + '引擎　' + (d.helper ? '已绑定' : '未绑定 ← 打开一次桌面助手')
          + ' · 界面 ' + (d.activity ? '已出现' : '未出现') + '\n'
          + '操作　' + (d.ops == null ? 0 : d.ops) + ' 次调用'
          + (d.opsFail ? ' · 失败 ' + d.opsFail : '')
          + (d.err ? '\n错误　' + d.err : '');
      })
      .catch(function () { el.sKv.textContent = '连不上 18766，诊断也取不到。'; });
    pickSeg(curSeg);
  }

  function closeSheet() {
    el.sSheet.className = 'ssheet';
    el.sMask.className = 'smask';
  }

  function pickSeg(seg) {
    if (!SEGS[seg]) return;
    curSeg = seg;
    Array.prototype.forEach.call(el.sSegs.children, function (b) {
      b.classList.toggle('on', b.getAttribute('data-seg') === seg);
    });
    el.sTip.textContent = SEGS[seg].tip;
    el.sTxt2.textContent = segCache[seg] || ('点「生成」取' + SEGS[seg].file + '。');
    el.sTxt2.scrollTop = 0;
    if (!segCache[seg] && !segBusy) loadSeg();
  }

  function loadSeg() {
    if (segBusy) return;
    var seg = curSeg;
    var cfg = SEGS[seg];
    segBusy = true;
    el.sTxt2.textContent = '读取中…';
    fetch(CAR_BASE + cfg.url + '?_=' + Date.now(), withTimeout(cfg.slow))
      .then(function (r) { return r.text(); })
      .then(function (t) {
        segBusy = false; segCache[seg] = t;
        if (curSeg === seg) { el.sTxt2.textContent = t; el.sTxt2.scrollTop = 0; }
      })
      .catch(function () {
        segBusy = false;
        if (curSeg === seg) {
          el.sTxt2.textContent = '取不到：18766 没响应。\n'
            + '浏览器里直接打开 ' + CAR_BASE + cfg.url + ' 看看。';
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
  el.sCopy.addEventListener('click', function () {
    if (!segCache[curSeg]) { toast('先点「生成」'); return; }
    copyText(segCache[curSeg]);
  });
  el.sSave.addEventListener('click', function () {
    if (!segCache[curSeg]) { toast('先点「生成」'); return; }
    saveText(segCache[curSeg], SEGS[curSeg].file + '_' + stamp() + '.txt');
  });
  el.sDump.addEventListener('click', function () {
    toast('正在打包…');
    fetch(CAR_BASE + '/dump?_=' + Date.now(), withTimeout(25000))
      .then(function (r) { return r.text(); })
      .then(function (t) { saveText(t, '车机全量_' + stamp() + '.txt'); })
      .catch(function () { toast('打包失败，先试单个「下载」'); });
  });
  el.sClear.addEventListener('click', function () {
    fetch(CAR_BASE + '/log/clear', { cache: 'no-store' })
      .then(function (r) { return r.json(); })
      .then(function (r) {
        if (r && r.ok === 1) { delete segCache.log; toast('操作记录已清空'); }
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
  }, 15000);
})();
