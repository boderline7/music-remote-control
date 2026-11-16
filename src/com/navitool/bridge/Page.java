package com.navitool.bridge;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 18766 的首页：车机控制页。
 *
 * 三级来源，越靠近开发越方便：
 *   1. 外部覆盖  /sdcard/Android/data/com.navi.tool/files/web/index.html（adb push 就生效，不重装）
 *   2. APK 资源  assets/carbridge.html（构建时从 web/car.html 拷进去）
 *   3. 内置兜底  自检页（老版本，只在上面两级都读不到时出现）
 */
final class Page {

    private static final String ASSET = "carbridge.html";

    private Page() {
    }

    static String html(Context ctx) {
        try {
            File dir = ctx.getExternalFilesDir(null);
            if (dir != null) {
                File f = new File(new File(dir, "web"), "index.html");
                if (f.exists() && f.length() > 0) {
                    return read(f);
                }
            }
        } catch (Throwable t) {
            Bridge.note("page: " + Bridge.root(t));
        }
        try {
            InputStream in = ctx.getAssets().open(ASSET);
            try {
                return readStream(in, 64 * 1024);
            } finally {
                in.close();
            }
        } catch (Throwable t) {
            Bridge.note("page asset: " + Bridge.root(t));
        }
        return builtin();
    }

    private static String read(File f) throws Exception {
        InputStream in = new FileInputStream(f);
        try {
            return readStream(in, (int) f.length());
        } finally {
            in.close();
        }
    }

    private static String readStream(InputStream in, int hint) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream(hint > 0 ? hint : 8192);
        byte[] buf = new byte[8192];
        int n;
        while ((n = in.read(buf)) > 0) {
            out.write(buf, 0, n);
        }
        return new String(out.toByteArray(), "UTF-8");
    }

    private static String builtin() {
        StringBuilder b = new StringBuilder(12000);
        for (String line : LINES) {
            b.append(line).append('\n');
        }
        return b.toString();
    }

    private static final String[] LINES = {
            "<!doctype html>",
            "<html lang='zh-CN'><head><meta charset='utf-8'>",
            "<meta name='viewport' content='width=device-width,initial-scale=1,viewport-fit=cover'>",
            "<title>车机桥接 · 自检</title>",
            "<style>",
            "*{box-sizing:border-box;-webkit-tap-highlight-color:transparent}",
            "body{margin:0;padding:16px 14px 40px;background:#0B0D10;color:#E8EAED;",
            "font:14px/1.55 -apple-system,'PingFang SC','Microsoft YaHei',sans-serif}",
            "h1{font-size:16px;font-weight:500;margin:0 0 2px}",
            ".sub{color:#8A8F98;font-size:12px;margin-bottom:14px}",
            ".dot{display:inline-block;width:7px;height:7px;border-radius:50%;margin-right:6px;vertical-align:middle}",
            ".ok{background:#35C759}.bad{background:#FF453A}.wait{background:#FF9F0A}",
            ".box{background:#16191F;border:1px solid #23272E;border-radius:12px;padding:12px 14px;margin-bottom:12px}",
            ".k{color:#8A8F98;font-size:12px}",
            ".row{display:flex;gap:18px;flex-wrap:wrap}",
            ".v{font-size:20px;font-weight:500}",
            "table{width:100%;border-collapse:collapse;font-size:13px}",
            "th,td{text-align:left;padding:7px 6px;border-bottom:1px solid #1E222A}",
            "th{color:#8A8F98;font-weight:400;font-size:12px}",
            "td.m{color:#6E7681;font-family:ui-monospace,Consolas,monospace;font-size:11px;word-break:break-all}",
            ".tag{font-size:11px;padding:1px 6px;border-radius:5px}",
            ".t1{background:rgba(53,199,89,.15);color:#35C759}",
            ".t0{background:rgba(255,159,10,.15);color:#FF9F0A}",
            "#cards{margin-top:4px}",
            ".card{border:1px solid rgba(255,255,255,.07);padding:10px 12px;margin-bottom:8px}",
            ".ct{font-size:13px;font-weight:500;margin-bottom:6px;opacity:.85}",
            ".grid{display:grid;gap:4px 8px}",
            ".it{white-space:nowrap;overflow:hidden;text-overflow:ellipsis}",
            "pre{white-space:pre-wrap;word-break:break-all;color:#8A8F98;font-size:11px;margin:0;max-height:220px;overflow:auto}",
            "</style></head><body>",
            "<h1>车机桥接 · 自检</h1>",
            "<div class='sub'><span id='dot' class='dot wait'></span><span id='state'>连接中…</span></div>",
            "<div class='box'><div class='row'>",
            "<div><div class='k'>卡着的能力</div><div class='v' id='sTotal'>-</div></div>",
            "<div><div class='k'>自动对上</div><div class='v' id='sAuto' style='color:#35C759'>-</div></div>",
            "<div><div class='k'>需兜底</div><div class='v' id='sManual' style='color:#FF9F0A'>-</div></div>",
            "</div></div>",
            "<div class='box'><div class='k' style='margin-bottom:6px'>能力匹配</div>",
            "<table id='match'><tbody></tbody></table></div>",
            "<div class='box'><div class='k' style='margin-bottom:8px'>卡片预览（数据来自车机助手）</div>",
            "<div id='cards'></div></div>",
            "<div class='box'><div class='k' style='margin-bottom:6px'>日志</div><pre id='log'></pre></div>",
            "<script>",
            "function css(c){if(!c)return '#fff';c=c.replace('#','');",
            "if(c.length===8){var a=parseInt(c.slice(0,2),16)/255,r=parseInt(c.slice(2,4),16),g=parseInt(c.slice(4,6),16),b=parseInt(c.slice(6,8),16);",
            "return 'rgba('+r+','+g+','+b+','+a.toFixed(3)+')'}return '#'+c}",
            "function el(id){return document.getElementById(id)}",
            "function setState(ok,txt){el('dot').className='dot '+(ok?'ok':'bad');el('state').textContent=txt}",
            "fetch('/probe').then(function(r){return r.json()}).then(function(p){",
            "  if(!p.helper){setState(false,'未绑定：桌面助手还没出现（'+ (p.err||'') +'）');",
            "    el('log').textContent=(p.log||[]).join('\\n');return}",
            "  setState(true,'已连接 '+(p.cls||''));",
            "  var s=p.summary||{};el('sTotal').textContent=s.total||0;",
            "  el('sAuto').textContent=s.auto||0;el('sManual').textContent=s.manual||0;",
            "  var h='';",
            "  (p.match||[]).forEach(function(m){",
            "    h+='<tr><td>'+m.name+'</td><td class=m>'+m.via+'</td><td>'",
            "      +(m.ok?'<span class=\"tag t1\">自动</span>':'<span class=\"tag t0\">兜底</span>')+'</td></tr>'}),",
            "  el('match').querySelector('tbody').innerHTML=h;",
            "  el('log').textContent=(p.log||[]).join('\\n');",
            "  return fetch('/cards').then(function(r){return r.json()})",
            "}).then(function(c){",
            "  if(!c||!c.ok||!c.cards)return;",
            "  var st=c.style||{},al=(st.alpha||90)/100;",
            "  el('cards').innerHTML=c.cards.map(function(card){",
            "    return '<div class=\"card\" style=\"border-radius:'+(st.radius||12)+'px;background:rgba(0,0,0,'+al+')'\">'",
            "      +'<div class=\"ct\">'+card.title+'</div>'",
            "      +'<div class=\"grid\" style=\"grid-template-columns:repeat('+(card.cols||2)+',1fr)\">'",
            "      +(card.items||[]).map(function(it){",
            "        return '<div class=\"it\" style=\"grid-column:span '+(it.s||1)+';font-size:'+(st.fontSize||24)/1.6+'px\">'",
            "          +'<span style=\"color:'+css(st.propColor)+'\">'+it.k+': </span>'",
            "          +'<span style=\"color:'+css(st.valueColor)+'\">'+it.v+'</span></div>'}).join('')",
            "      +'</div></div>'}).join('')",
            "}).catch(function(e){",
            "  if(!el('state').textContent.startsWith('已连接'))setState(false,'请求失败：'+e)",
            "});",
            "</script></body></html>"
    };
}
