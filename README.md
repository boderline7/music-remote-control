# 音乐车机遥控器

车机端跑 WebSocket 服务（18765），手机用 App 或浏览器连上即可播控。

入口 `http://<车机IP>:18765`。手机端靠 Bonjour / 网段扫描发现车机，车机设置页的「域名发现」默认关闭。三端 UI 由车机端 `assets/remote.html` 一份网页提供：改网页只需重装车机端，手机端刷新即生效。

安装包见 [Releases](https://github.com/boderline7/music-remote-control/releases)。

## 能做什么

- 播控：播放 / 暂停 / 上一首 / 下一首 / 拖动进度 / 音量 / 随机与循环
- 内容：我喜欢的音乐、歌单（含官方榜单）、搜索、按歌名点播
- 显示：封面、歌名歌手、逐行歌词（含翻译）、播放队列
- 连接：手机 App 自动发现（Bonjour / 网段扫描），或浏览器直接打开 `http://<车机IP>:18765`
- 音源：第三方自研播放器（默认）或官方网易云车机版（桥接层），二选一

## 车机端的两个包

车机上装的是**两个独立 APK**，都是网易云音乐的第三方客户端，都不是官方包：

| 包名 | 桌面名 | 角色 |
|---|---|---|
| `com.cloudmusic.car` | 云音乐 | 第三方自研播放器（模块 `app`）。自带播放内核与曲库，默认由它提供服务：在 18765 起遥控服务，同时自己向车机助手发「汽水音乐」通道的广播 |
| `com.luna.music.car` | 汽水音乐 | 桥接层（模块 `lunastub`）。**不放音乐**，只做三件事：撑住助手音源列表里的「汽水音乐车机版」、把官方网易云车机版（`com.netease.cloudmusic.iot`）包成可遥控后端、在「官方」模式下起 18765 服务 |

### 桥接包为什么叫「汽水音乐」

车机助手（`com.navi.tool`）的音源表按包名硬编码：`cn.kuwo.kwmusiccar` → 酷我、`com.kugou.android.auto` → 酷狗、`com.netease.cloudmusic.iot` → 网易云、`com.luna.music.car` → **汽水音乐**。前三个包名都被真实 App 占着，只有最后一个是空的；而「汽水音乐」通道又是四条里最开放的（纯广播、不校验签名和包名）。所以借这个包名把歌词、封面、控制直连进助手。它跟字节的汽水音乐没有任何关系，也不含汽水音乐的代码。

### 默认与切换

默认走第三方自研：桥接包不占 18765、不发广播，只让助手列表里有这一项。手动切到「官方网易云」后，桥接包才连官方并起服务；切回第三方时桥接包主动停服、释放端口。

官方模式下的单曲点播走「语音搜索 + 显式状态机」，因为官方车机版没有按 songId 播放的入口。状态机、歌单来源、日志口径见 [`车机端/cloudmusic-car/lunastub/README.md`](./车机端/cloudmusic-car/lunastub/README.md)。

## 构建

```bash
python proto/test_protocol.py
cd 车机端/cloudmusic-car && ./gradlew assembleRelease   # app + lunastub
cd 手机端/cloudmusic-remote && ./gradlew assembleDebug
```

需要 JDK 17 与 Android SDK（platform 35、build-tools 34），路径写在不入库的 `local.properties`。iOS 客户端见 [ios/README.md](./ios/README.md)。`dist/` 不入库。

`lunastub` 的两个变体：

- `assembleRelease` → `debuggable=false`，发给车机用这个
- `assembleDebug` → `debuggable=true`，需要 adb / `run-as` 时用

`debuggable` 是编译期属性，装到设备后无法运行时切换。

## 目录

```
车机端/   播放器与遥控服务
  cloudmusic-car/app       第三方自研播放器（桌面名「云音乐」）
  cloudmusic-car/lunastub  桥接层（助手里的「汽水音乐车机版」）+ 遥控服务
手机端/   安卓 WebView 壳
ios/      iOS 客户端
proto/    协议定义与自检
docs/     本地留档，不入库（08-占位包 已过期，仅作协议依据）
```

无 TLS、无 PIN，仅限车内可信局域网，不要把 18765 映射到公网。

## 许可

GNU LGPL-3.0。

- 车机端第三方播放器派生自 [xuqian-git/cloudmusic-car](https://github.com/xuqian-git/cloudmusic-car)（LGPL-3.0）。
- 接口加密、接口清单与应用图标来自它的上游 [missuo/kumone](https://github.com/missuo/kumone)（LGPL-3.0）。
- 派生作品沿用 LGPL-3.0；逐条改动、依赖许可、商标与免责声明见 [NOTICE.md](./NOTICE.md)。
