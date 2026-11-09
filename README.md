# 音乐车机遥控器

车机端跑 WebSocket 服务（18765），手机用 App 或浏览器连上即可播控。

入口 `http://<车机IP>:18765`。手机端靠 Bonjour / 网段扫描发现车机，车机设置页的「域名发现」默认关闭。三端 UI 由车机端 `assets/remote.html` 一份网页提供：改网页只需重装车机端，手机端刷新即生效。

安装包见 [Releases](https://github.com/boderline7/music-remote-control/releases)。

## 构建

```bash
python proto/test_protocol.py
cd 车机端/cloudmusic-car && ./gradlew assembleDebug
cd 手机端/cloudmusic-remote && ./gradlew assembleDebug
```

需要 JDK 17 与 Android SDK（platform 35、build-tools 34），路径写在不入库的 `local.properties`。iOS 客户端见 [ios/README.md](./ios/README.md)。`dist/` 不入库。

## 目录

```
车机端/   播放器与遥控服务
手机端/   安卓 WebView 壳
ios/      iOS 客户端
proto/    协议定义与自检
docs/     本地留档，不入库
```

无 TLS、无 PIN，仅限车内可信局域网，不要把 18765 映射到公网。

## 许可

GNU LGPL-3.0。车机端派生自 [xuqian-git/cloudmusic-car](https://github.com/xuqian-git/cloudmusic-car)（同为 LGPL-3.0），派生作品沿用同一许可证。改动逐条见 [NOTICE.md](./NOTICE.md)。
