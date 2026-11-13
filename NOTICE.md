# NOTICE / 来源、修改与第三方许可

本仓库包含四个部分：车机端第三方播放器（`车机端/cloudmusic-car/app`）、车机端桥接层
（`车机端/cloudmusic-car/lunastub`）、安卓手机端（`手机端/cloudmusic-remote`）、iOS 客户端（`ios/`）。
其中只有第三方播放器派生自其它项目，路径与义务如下。

---

## 1. 上游链

### 1.1 直接上游：xuqian-git/cloudmusic-car

- 仓库：<https://github.com/xuqian-git/cloudmusic-car>
- 许可证：**GNU LGPL-3.0**（`LGPL-3.0`，许可证原文见 `车机端/cloudmusic-car/LICENSE`，其引用的
  GPL-3.0 条款见 `车机端/cloudmusic-car/COPYING.GPL`）
- 派生范围：`车机端/cloudmusic-car/app` 整个模块（Kotlin + Jetpack Compose 的网易云车机客户端），
  以及同目录下的 `qqmusic/`、`kugoumusic/`、`shared/`、`tools/`、`gradle/` 等原样文件。
- 本工程只构建 `:app`（`settings.gradle.kts` 仅 `include(":app")`）。
  `qqmusic/`、`kugoumusic/`、`shared/` 是上游自带的多应用变体，**本工程没有编译、也没有使用**，
  原样保留只为方便与上游对照。

### 1.2 上游所引用的上游：missuo/kumone

即「上上游」。直接上游的接口加密是从这个项目移植的，本次 2.3.0 改造也以它为准。

- 仓库：<https://github.com/missuo/kumone>（iOS 端网易云客户端）
- 许可证：**GNU LGPL-3.0**（`LGPL-3.0-only`）
- 使用范围：
  1. **eapi 加密**：`app/src/main/java/com/cloudmusic/car/api/NeteaseCrypto.kt` 按其
     `RequestCrypto.swift` 实现（AES-128-ECB + MD5，大写 hex，
     `nobody{apiPath}use{json}md5forencrypt`）。
  2. **接口路径与参数**：`api/NeteaseApi.kt` 与 `api/NeteaseClient.kt` 对其 `MusicAPI.swift` /
     `MusicClient.swift` 的接口清单、参数名、分页与错误码约定。
  3. **应用图标**：`app/src/main/res/mipmap-*/ic_launcher.png` 与
     `app/src/main/res/drawable-nodpi/ic_cloudmusic_app.png` 取自其
     `ios/KumoneIOS/Assets.xcassets/AppIcon.appiconset/AppIcon.png`，只做尺寸缩放，未重绘。
- 未使用：kumone 的 Swift 源码、UI、播放内核、登录页等实现均未复制进本仓库。
- 上游自身的致谢（**不是**本工程的上游，仅作来源链完整记录，本工程未包含其任何源码）：
  - [YesPlayMusic](https://github.com/qier222/YesPlayMusic)（MIT）—— 功能设计与 API 用法参考
  - [kaset](https://github.com/sozercan/kaset)（MIT）—— UI 设计参考
  - [LyricsX](https://github.com/ddddxxx/LyricsX)（MPL-2.0）—— 桌面歌词交互参考

### 1.3 未随仓库分发的官方应用

桥接层 `lunastub` 运行在车机上，通过 Android `MediaBrowser` / `MediaSession` 连接**用户自行安装**的
官方网易云音乐车机版（`com.netease.cloudmusic.iot`）。本仓库**不包含、也不分发**该官方应用的
任何 APK、代码或资源；未安装官方应用时桥接层只记录日志并让位，不会崩溃。

---

## 2. 本仓库对上游的修改（LGPL-3.0 §3(d) 要求的改动说明）

时间范围 2026-10 ~ 2026-11，按动机归类：

1. **裁剪**：`settings.gradle.kts` 只保留 `:app`；删除跑跑桌面宿主校验
   （`ContainerGate.isPaopaoGuest()`），否则装到普通车机会直接崩溃。
2. **新增遥控模块** `com.cloudmusic.car.remote`：`WsServer`（手写 RFC6455）、`RemoteServer`
   （协议与状态推送）、`PlayerBridge`（唯一访问 `PlayerHub` 的适配层）、`RemoteWeb`
   （同端口静态页）、`MdnsResponder`（手写 mDNS，`carremote.local`）、`RemotePrefs`、
   `RemoteLibrary`、`OutQueue`、`RemoteHealth`、`NetworkMonitor`、`RemoteService`、`BootReceiver`。
3. **新增网页资源** `app/src/main/assets/remote.html`（单文件遥控页）。
4. **改动既有文件**：设置页（新增「手机遥控」开关与本机地址自检）、`PlayerHub`
   （显式 `setPlaying` 幂等接口）、`Stores`（歌曲 / 歌词缓存档位）、`MusicCache`
   （歌词按预算淘汰）、`AccountScreens` / `RemoteScreen`（界面与缓存控件）。
5. **构建改动**：Gradle 配置、签名走本地 `keystore.properties`（不入库）。
6. **2.3.0 接口层改造**：删除 weapi 与回退路径，统一改走 eapi（与 1.2 所述的 kumone 一致）；
   替换应用图标与启动底色。
7. **新增桥接层** `lunastub`（`com.luna.music.car`）：不派生自上游，见第 3 节。

---

## 3. 不派生自上游的部分

以下为本仓库新写，未复制任何第三方项目源码：

- `车机端/cloudmusic-car/lunastub`（桥接层 + 18765 遥控服务）
- `手机端/cloudmusic-remote`（Android WebView 壳）
- `ios/`（iOS 客户端）
- `proto/`（协议定义与自检脚本）
- `_工具/`（构建与签名辅助脚本）
- `docs/`（本地留档；其中 `08-占位包.md` 已过期，仅作协议依据）

---

## 4. 第三方依赖与许可

运行时依赖均为宽松许可证，与 LGPL-3.0 兼容；未修改、未内联其源码。

| 组件 | 使用位置 | 许可证 |
|---|---|---|
| Kotlin 标准库、kotlinx-coroutines | 车机端 / 手机端 | Apache-2.0 |
| AndroidX（Core、Activity、Lifecycle 等） | 车机端 / 手机端 | Apache-2.0 |
| Jetpack Compose（UI、Material 3） | 车机端 app | Apache-2.0 |
| Media3 / ExoPlayer（1.4.1） | 车机端 app | Apache-2.0 |
| OkHttp（4.12） | 车机端 app | Apache-2.0 |
| Coil（2.7） | 车机端 app | Apache-2.0 |
| ZXing core（扫码登录） | 车机端 app | Apache-2.0 |
| AndroidX Palette（封面取色） | 车机端 app | Apache-2.0 |
| `org.json` | 车机端 lunastub | Android 平台内置（Apache-2.0） |

`lunastub` 不引入任何第三方库，只用 Android Framework 与平台自带的 `org.json`。
iOS 客户端只使用 Apple 系统框架（SwiftUI / AVFoundation 等）。

---

## 5. 许可证与合规

- 本仓库整体以 **GNU LGPL-3.0** 发布，全文见根目录 [`LICENSE`](./LICENSE)，
  其引用的 GPL-3.0 条款见 [`COPYING.GPL`](./COPYING.GPL)。
- 车机端 app 是 LGPL-3.0 派生作品，**不能**改用 MIT / Apache 等更宽松的许可证；
  手机端、桥接层、iOS 端虽为新写，为不让人误解边界，也一并以 LGPL-3.0 发布。
- 源码获取：本仓库即对应源码。构建方式见根目录 [`README.md`](./README.md)。
  若只分发 APK，必须同时提供本仓库地址与上述构建说明（LGPL-3.0 §4、§6）。
- 无担保：软件按「现状」提供，不附带任何明示或暗示的担保（LGPL-3.0 §15、§16）。

---

## 6. 商标与免责声明

- 「网易云音乐」及相关名称、图标、界面元素的商标与著作权归**网易公司**所有。
  本仓库与网易公司**没有任何隶属、合作或授权关系**，是第三方非官方客户端。
- 本工程仅用于个人学习与研究，**未获商业授权**，请勿用于销售、预装或其它商业用途。
  该声明不改变、也不限制你在 LGPL-3.0 下已获得的任何权利。
- 遥控服务无 TLS、无 PIN，仅限车内可信局域网使用；不要把 18765 端口暴露到公网。
  因使用本工程造成的账号风险、数据丢失或车辆问题，由使用者自行承担。
