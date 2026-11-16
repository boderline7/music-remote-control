# 把车控页（CSS / 标记 / 脚本）从云音乐包网页移植到桥接包网页
import io, sys

APP = r'D:/车机/车机桥接空调/work/cloudmusic-car/app/src/main/assets/remote.html'
LUN = r'D:/车机/车机桥接空调/work/cloudmusic-car/lunastub/src/main/assets/remote.html'

app = io.open(APP, encoding='utf-8').read()
lun = io.open(LUN, encoding='utf-8').read()

css_start = app.index("  /* ==================================================================\n     车机控制页")
css_end = app.index("</style>", css_start)
css = app[css_start:css_end]

m_start = app.index("  <!-- ===================== 车机控制页 ===================== -->")
m_end = app.index('  <div id="toast"></div>', m_start)
markup = app[m_start:m_end]

hb_start = app.index('    <button class="icon-btn" id="openCar"')
hb_end = app.index('    <button class="icon-btn" id="refresh"', hb_start)
header_btn = app[hb_start:hb_end]

js_start = app.index('<script>\n/* ==========================================================================\n   车机控制页')
js_end = app.index('</script>', js_start) + len('</script>')
js = app[js_start:js_end]

# 大屏（车机自己的 1920 屏）也要好看：卡片两列、能力四列
wide = '''
  /* 车机自带的宽屏：卡片排两列，能力排四列，别把 560 的手机版式拉成一条细缝 */
  @media (min-width: 1000px) {
    #carPage .car-head, #carScroll { max-width: 1180px; }
    #carCards { display: grid; grid-template-columns: 1fr 1fr; align-items: start; }
    .caps { grid-template-columns: repeat(4, minmax(0, 1fr)); }
    .ccard-t { font-size: 15px; }
  }
'''

assert css and markup and header_btn and js
assert '</style>' in lun and '    <button class="icon-btn" id="refresh"' in lun
assert '  <div id="toast"></div>' in lun
assert '  connect();\n})();\n</script>\n</body>' in lun

lun = lun.replace('</style>', css + wide + '</style>', 1)
lun = lun.replace('    <button class="icon-btn" id="refresh"', header_btn + '    <button class="icon-btn" id="refresh"', 1)
lun = lun.replace('  <div id="toast"></div>', markup + '  <div id="toast"></div>', 1)
lun = lun.replace('  connect();\n})();\n</script>\n</body>',
                  '  connect();\n})();\n</script>\n\n' + js + '\n</body>', 1)

old_skip = """        var n = nodes[i];
        if (n.disabled || n.id === 'offlineNow') continue;
        n.disabled = true;"""
new_skip = """        var n = nodes[i];
        if (n.disabled || n.id === 'offlineNow') continue;
        // 车控页和它的入口走 18766，跟音乐这条 WebSocket 不是一条线，不该被一起冻住
        if (n.id === 'openCar' || (n.closest && n.closest('#carPage'))) continue;
        n.disabled = true;"""
assert old_skip in lun
lun = lun.replace(old_skip, new_skip, 1)

io.open(LUN, 'w', encoding='utf-8', newline='\n').write(lun)
print('ok: len', len(lun), 'carPage', lun.count('id="carPage"'), 'openCar', lun.count('id="openCar"'))
