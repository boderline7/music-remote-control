# 音乐车机遥控器

给车机上的音乐播放器加一个局域网遥控：车机跑一个 WebSocket 服务（18765），手机装 APK 或直接用浏览器打开，连上就能播控 —— 不用 PIN，也不用配对。

浏览器入口：`http://<车机IP>:18765`，或 `http://carremote.local:18765`（mDNS，车机 IP 变了也能解析）。

## 两个产物

| 文件 | 说明 |
|---|---|
| `cloudmusic-car-v2.2.1-遥控-debug.apk` | 车机端（播放器 + 遥控模块），versionCode 32 |
| `车机遥控-v1.8.6.apk` | 手机端遥控（可选，网页也能用），versionCode 15 |

都是本地构建产物，`dist/` 不入库。现成的安装包见 [Releases](https://github.com/boderline7/music-remote-control/releases)，装哪端看你的场景。

## 构建

```bash
source _工具/env.sh                                # 检查工具链
python proto/test_protocol.py                      # 协议自检

cd 车机端/cloudmusic-car && ./gradlew assembleDebug
cd 手机端/cloudmusic-remote && ./gradlew assembleDebug
```

需要一个完整 JDK（含 javac）和本机 Android SDK（platform 35 + build-tools 34），路径写在不入库的 `local.properties` 里。
签名照 `keystore.properties.example` 填，不填就走 debug 签名，照样能编译。

## 目录

```
车机端/cloudmusic-car/     播放器工程，遥控模块在 com.cloudmusic.car.remote
手机端/cloudmusic-remote/  遥控 APK（Kotlin + Compose）
proto/                     协议定义与 Java / Python 实现
docs/                      协议全文、源码勘察、设计与构建说明
_工具/                      env.sh、联调脚本、自检脚本
```

## 安全边界

无 TLS、无 PIN，局域网内明文传输，同网段任何人都能连上 18765 发指令。唯一的闸门是「同时在线设备数上限」（默认 4，可调 1~8），它挡的是数量，不是身份。**只适用于车内可信网络，不要把 18765 映射到公网。**

## 许可

GNU LGPL-3.0。车机端派生自 [xuqian-git/cloudmusic-car](https://github.com/xuqian-git/cloudmusic-car)（同为 LGPL-3.0），派生作品必须沿用同一许可证，故整个仓库统一 LGPL。改了上游哪些地方，逐条列在 [NOTICE.md](./NOTICE.md)。
