# -*- coding: utf-8 -*-
"""把 lunastub 车控页里刚加的设置面板，原样搬到 remote.html（云音乐包网页）。"""
import io

luna = io.open('web/remote_lunastub.html', encoding='utf-8').read()

i = luna.index(u'  /* ---------- 车控页里的设置 / 诊断 ---------- */')
j = luna.index(u'  .carlog {', i)
CSS = luna[i:j]

i = luna.index(u'  <!-- 设置 / 诊断')
j = luna.index(u'  <div id="toast"></div>', i)
HTML = luna[i:j]

i = luna.index(u'  // ---------- 设置 / 诊断')
k = luna.index(u"document.getElementById('csetSave').addEventListener", i)
k = luna.index(u'\n', k) + 1
JS = luna[i:k]

i = luna.index(u'      <button class="icon-btn" id="carSet"')
j = luna.index(u'      <button class="icon-btn" id="carRefresh"', i)
GEAR = luna[i:j]

p = 'web/remote.html'
s = io.open(p, encoding='utf-8').read()
assert 'csetSheet' not in s, 'remote.html already patched'

assert u'  .carlog {' in s
s = s.replace(u'  .carlog {', CSS + u'  .carlog {', 1)

assert u'  <div id="toast"></div>' in s
s = s.replace(u'  <div id="toast"></div>', HTML + u'  <div id="toast"></div>', 1)

assert u"  foot.href = BASE + '/';" in s
s = s.replace(u"  foot.href = BASE + '/';", u"  foot.href = BASE + '/';" + u'\n' + JS, 1)

assert u'      <button class="icon-btn" id="carRefresh" aria-label="刷新车控">' in s
s = s.replace(u'      <button class="icon-btn" id="carRefresh" aria-label="刷新车控">',
              GEAR + u'      <button class="icon-btn" id="carRefresh" aria-label="刷新车控">', 1)

io.open(p, 'w', encoding='utf-8', newline='\n').write(s)
print('remote.html ok; css %d, html %d, js %d bytes' % (len(CSS), len(HTML), len(JS)))
