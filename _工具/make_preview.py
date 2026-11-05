"""生成带假数据的预览页（仅本地调试用）。

Headless 的 Windows 窗口有最小宽度（约 492），所以这里把 body 固定成 390×844，
并且把固定定位的面板也压到 390，截图后裁左侧 390px 才是真机上的样子。
"""
import io
import os
import tempfile

# 路径全部相对本文件推出来，别写死本机目录
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
SRC = os.path.join(ROOT, "车机端", "cloudmusic-car", "app", "src", "main", "assets", "remote.html")
OUT = os.path.join(tempfile.gettempdir(), "carremote-preview")
os.makedirs(OUT, exist_ok=True)
src = io.open(SRC, encoding="utf-8").read()

FRAME = """
<style>
  html { background: #000; }
  body { width: 390px !important; height: 844px !important; margin: 0 !important; }
  .sheet { transition: none !important; width: 390px; right: auto; }
</style>
"""

FAKE = """
<script>
(function () {
  var state = {seq:1,playing:true,title:"晴天",artist:"周杰伦",positionMs:96000,durationMs:269000,liked:true,volume:18,volumeMax:30};
  var lib = {ok:true,liked:{id:"liked",name:"我喜欢的音乐",count:128},cloud:{id:"cloud",name:"音乐云盘",count:7},
    playlists:[{id:"1",name:"通勤路上",count:30},{id:"2",name:"深夜电台",count:12},{id:"3",name:"雨天钢琴",count:9}]};
  var cat = {ok:true,top:[{id:"top:1",name:"飙升榜",count:100},{id:"top:2",name:"新歌榜",count:100}],
    recommend:[{id:"rec:1",name:"每日推荐",count:30},{id:"rec:2",name:"私人雷达",count:30}]};
  function Fake() {
    var self = this;
    this.readyState = 0;
    this.sent = [];
    window.__ws = this;
    setTimeout(function () {
      self.readyState = 1;
      if (self.onopen) self.onopen();
      setTimeout(function () { self.push("pair_result", {ok:true,token:"t",carName:"车机"}); }, 40);
      setTimeout(function () { self.push("state", state); }, 90);
      setTimeout(function () { self.push("library", lib); }, 140);
      setTimeout(function () { self.push("catalog", cat); }, 190);
    }, 10);
  }
  Fake.prototype.push = function (t, d) { if (this.onmessage) this.onmessage({data: JSON.stringify({type: t, data: d})}); };
  Fake.prototype.send = function (s) { this.sent.push(JSON.parse(s)); };
  Fake.prototype.close = function () {};
  window.WebSocket = Fake;
})();
</script>
"""

OPEN_JS = """
<script>
setTimeout(function () {
  document.getElementById('tileLiked').click();
  var rows = [];
  for (var i = 0; i < 20; i++) {
    rows.push({index: i, name: "第 " + (i + 1) + " 首测试歌曲的名字要够长才看得出来省略号",
               artist: "周杰伦 / 五月天", durationMs: 269000, playable: (i % 7) !== 3});
  }
  window.__ws.push('tracks', {ok:true,id:'liked',total:128,offset:0,count:20,tracks:rows});
}, 400);
</script>
"""

BOTTOM_JS = """
<script>
setTimeout(function () { document.getElementById('scroll').scrollTop = 99999; }, 500);
</script>
"""


def write(name, extra=""):
    page = src.replace("<head>", "<head>" + FRAME).replace("<body>", "<body>\n" + FAKE + extra)
    io.open(os.path.join(OUT, name), "w", encoding="utf-8", newline="\n").write(page)


write("main.html")
write("sheet.html", OPEN_JS)
write("bottom.html", BOTTOM_JS)
print("written")
