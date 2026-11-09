# 音乐车机遥控器

给车机上的音乐播放器加一个局域网遥控：车机跑一个 WebSocket 服务（18765），手机装 APK 或直接用浏览器打开，连上就能播控 —— 不用 PIN，也不用配对。

2.2.10 起三端 UI 统一：安卓 / iOS 客户端都只是「局域网发现 + WebView 壳」，界面、协议、功能全由车机端 `assets/remote.html` 一份代码决定，改一处三端同时生效。

浏览器入口：`http://<车机IP>:18765`。车机设置页另有「域名发现」开关（`carremote.local`），**默认关闭**；安卓 / iOS App 不依赖它，靠 Bonjour + /24 网段扫描照样能找到车机。

## 产物

| 文件 | 说明 |
|---|---|
| `cloudmusic-car-v2.2.10-遥控-debug.apk` | 车机端（播放器 + 遥控模块 + 内置网页），versionCode 41 |
| `占位包.apk` | 占位包，7.8 KB，只为让车机助手认出「汽水音乐车机版」 |
| `车机遥控-v2.0.0.apk` | 安卓遥控 WebView 壳（约 1.3 MB），versionCode 18 |
| `CarRemote-unsigned-1.2.0.ipa` | iOS 遥控客户端 1.2.0（未签名），GitHub Actions 云构建，见 [ios/README.md](./ios/README.md) |

另外 `dist/` 里还留着 v2.2.2 的车机端 APK，对应 GitHub 上的备份版本（备案），便于回退。

都是本地构建产物，`dist/` 不入库。现成的安装包见 [Releases](https://github.com/boderline7/music-remote-control/releases)，装哪端看你的场景。

## 2.2.10 这一版

- **三端统一 UI**：安卓遥控改成 WebView 壳，删掉 Compose 原生界面和原生 WebSocket 客户端，APK 从 16 MB 降到 1.3 MB；保留 Bonjour / 网段发现、物理音量键、上次地址记忆与失效自愈。
- **App 内刷新**：协议加 `refresh`，车机端清资料库缓存后重推 library / catalog / lyrics / state；网页右上角按钮 + 下拉手势（iOS 用原生 `UIRefreshControl`）。
- **域名发现开关**：车机设置页可开可关，默认关闭，省掉常驻组播与 MulticastLock；关闭时 App 端发现不受影响。
- **修复「添加到歌单」**：网易云 `/weapi/playlist/tracks` 已下线（返回 404「接口未找到」），改为现行的 `/weapi/playlist/manipulate/tracks`，并加 `/v1/` 同族路径回退。
- **网页 UI**：取消歌单/歌词双页切换；歌词方框移到页面最顶部，约 5 行自动跟随且默认不可手动滑；「添加到歌单」缩小排到爱心下方；资料库卡片方框化；音量加减收成低调小钮。

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
手机端/cloudmusic-remote/  安卓遥控（WebView 壳 + Bonjour/网段发现，Kotlin）
proto/                     协议定义与 Java / Python 实现
docs/                      协议全文、源码勘察、设计与构建说明（本地留档，不入库）
ios/                       iOS 遥控客户端源码与云构建配置
_工具/                      env.sh、联调脚本、自检脚本
```

## 安全边界

无 TLS、无 PIN，局域网内明文传输，同网段任何人都能连上 18765 发指令。唯一的闸门是「同时在线设备数上限」（默认 4，可调 1~8），它挡的是数量，不是身份。**只适用于车内可信网络，不要把 18765 映射到公网。**

## 许可

GNU LGPL-3.0。车机端派生自 [xuqian-git/cloudmusic-car](https://github.com/xuqian-git/cloudmusic-car)（同为 LGPL-3.0），派生作品必须沿用同一许可证，故整个仓库统一 LGPL。改了上游哪些地方，逐条列在 [NOTICE.md](./NOTICE.md)。
