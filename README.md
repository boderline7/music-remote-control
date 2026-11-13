# 音乐车机遥控器

车机端跑 WebSocket 服务（18765），手机用 App 或浏览器连上即可播控。

入口 `http://<车机IP>:18765`。手机端靠 Bonjour / 网段扫描发现车机，车机设置页的「域名发现」默认关闭。三端 UI 由车机端 `assets/remote.html` 一份网页提供：改网页只需重装车机端，手机端刷新即生效。

安装包见 [Releases](https://github.com/boderline7/music-remote-control/releases)。

## 能做什么

- 播控：播放 / 暂停 / 上一首 / 下一首 / 拖动进度 / 音量 / 随机与循环
- 内容：我喜欢的音乐、歌单（含官方榜单）、搜索、按歌名点播
- 显示：封面、歌名歌手、逐行歌词（含翻译）、播放队列
- 连接：手机 App 自动发现（Bonjour / 网段扫描），或浏览器直接打开 `http://<车机IP>:18765`
- 音源：云音乐车机包（默认）或奇瑞车机上的官方网易云，二选一

## 车机端的两个包

车机上装两个独立 APK，一个放音乐，一个做桥接：

| 包名 | 桌面名 | 是什么 |
|---|---|---|
| `com.cloudmusic.car` | 云音乐 | **云音乐车机包**。完整客户端，自带播放内核与曲库，默认由它提供服务 |
| `com.luna.music.car` | 汽水音乐 | **桥接包**（模块 `lunastub`）。不放音乐，借用车机助手音源表里空着的包名，把助手、官方网易云和车机播放器接到一起 |

桥接包干三件事：

- 让车机助手的音源列表出现这一项
- 针对奇瑞车机适配车机助手：语音点歌、方控、歌词与封面
- 适配奇瑞车机上的官方网易云（`com.netease.cloudmusic.iot`）

桌面名「汽水音乐」只是车机助手音源表里的槽位名，跟字节的汽水音乐无关。

### 默认与切换

默认走云音乐车机包，桥接包不启动、不占 18765。点「官方网易云」后桥接包才连官方并起服务；切回来它主动停服、释放端口。

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
  cloudmusic-car/app       云音乐车机包
  cloudmusic-car/lunastub  桥接包 + 遥控服务
手机端/   安卓 WebView 壳
ios/      iOS 客户端
proto/    协议定义与自检
docs/     本地留档，不入库（08-占位包 已过期，仅作协议依据）
```

无 TLS、无 PIN，仅限车内可信局域网，不要把 18765 映射到公网。

## 许可

GNU LGPL-3.0。

- 云音乐车机包派生自 [xuqian-git/cloudmusic-car](https://github.com/xuqian-git/cloudmusic-car)（LGPL-3.0）。
- 接口加密、接口清单与应用图标来自它的上游 [missuo/kumone](https://github.com/missuo/kumone)（LGPL-3.0）。
- 派生作品沿用 LGPL-3.0；逐条改动、依赖许可、商标与免责声明见 [NOTICE.md](./NOTICE.md)。
