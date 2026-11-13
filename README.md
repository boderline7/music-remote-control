# 音乐车机遥控器

车机端跑 WebSocket 服务（18765），手机用 App 或浏览器连上即可播控。

入口 `http://<车机IP>:18765`。手机端靠 Bonjour / 网段扫描发现车机，车机设置页的「域名发现」默认关闭。三端 UI 由车机端 `assets/remote.html` 一份网页提供：改网页只需重装车机端，手机端刷新即生效。

安装包见 [Releases](https://github.com/boderline7/music-remote-control/releases)。

## 车机端的两个包

车机上装的是**两个独立 APK**，分工不同，靠 **18765 端口**决定当下谁在服务：

| 包名 | 名称 | 角色 |
|---|---|---|
| `com.cloudmusic.car` | 汽水音乐车机版（第三方自研播放器） | 自带播放内核。若它已占用 18765，由它提供服务 |
| `com.luna.music.car` | 汽水音乐车机版（桥接层，模块 `lunastub`） | 连接官方网易云车机版（`com.netease.cloudmusic.iot`），在 18765 提供遥控服务 |

**端口即角色**：启动时 18765 被占 → 说明第三方在服务，桥接层让位、不抢端口；空闲 → 桥接层连官方并起服务。

桥接层的单曲点播走「语音搜索 + 显式状态机」，因为官方车机版没有按 songId 播放的入口。状态机、歌单来源、日志口径见 [`车机端/cloudmusic-car/lunastub/README.md`](./车机端/cloudmusic-car/lunastub/README.md)。

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
  cloudmusic-car/app       第三方自研播放器
  cloudmusic-car/lunastub  官方车机版桥接层 + 遥控服务
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
