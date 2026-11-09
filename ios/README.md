# iOS 遥控客户端（未签名 IPA）

给车机遥控做一个能装到 iPhone 上的独立 App。**发现逻辑照搬安卓遥控 App**：服务发现 + 网段扫描，拿到车机的纯 IPv4 直连，不依赖 `carremote.local` 域名解析。

## 为什么不是继续用网页

车机端自带网页遥控（`http://carremote.local:18765/`），但那个域名靠车机自己实现的 mDNS 应答器活着：iOS 对组播应答的校验很严，查询包丢一次、缓存 120 秒过期没刷上，页面就打不开，而同一时刻用 IP 访问完全正常。

安卓 App 早就不吃这套了，它的做法是：

| 安卓 | iOS 这里 | 说明 |
|---|---|---|
| `Discovery.kt` 搜 `_cloudmusic-car._tcp`（NSD） | `BonjourFinder` + `NetServiceBrowser` | 从 `NetService.addresses` 里取**纯 IPv4**，不用 `xxx.local` 主机名 |
| `Scanner.kt` 扫本机所有 site-local IPv4 的 /24，TCP 探 18765 | `LanScanner` | 64 并发、0.5 秒超时，用 HTTP 探测确认对面真是遥控页面 |
| 启动先连 `Prefs.lastHost`，2.5 秒没连上就扫网段 | `CarLink.start()` | 顺序执行，避免两条连接并存来回跳 |
| 连上 `Discovery.stop()`，断开重启 | `finder.stop() / start()` | 一样 |
| `ConnectPanel`：发现列表 + 扫描结果 + 手动连接 | `ConnectPanel.swift` | 一样，另外未连接时会自动采用第一个发现结果 |

再补一点自愈：页面加载失败 / 进程被系统回收会重新发现，每 5 秒重试上次地址、每 15 秒全量扫一次网段，回到前台也会自检。

界面本身是 WKWebView 壳，直接加载车机端 `remote.html`；页面里的 WebSocket 按 `location.host` 连同一个 IP，所以歌词、收藏、搜索、歌单、音量等所有功能和网页端 **100% 一致**，没有协议翻译、没有原生重写 UI。

## 云构建

`.github/workflows/ios-ipa.yml` 在 GitHub 的 macOS runner 上跑：

1. `xcodegen` 由 `project.yml` 生成 Xcode 工程（工程文件不入库）；
2. `xcodebuild ... -sdk iphoneos clean archive`，`CODE_SIGNING_ALLOWED=NO` 出未签名 `.app`；
3. 打包前把仓库里的 `Info.plist` 强制合并进 `.app/Info.plist` 并逐键校验 —— Xcode 16 配 XcodeGen 时用自动生成的 plist，会丢掉 ATS 明文放行、本地网络权限说明和 `NSBonjourServices`，不补上 App 就发现不了车机；
4. 打成 `Payload/CarRemote.app` → `CarRemote-unsigned.ipa`，附 SHA-256；
5. 上传到 Actions artifact `CarRemote-unsigned-ipa`（保留 90 天）。

手动触发（Actions → iOS IPA (unsigned) → Run workflow）填 `release_tag` 就能顺手把 IPA 发到 GitHub Release，方便直接下载。推送 `ios/**` 或该 workflow 文件也会自动构建。仓库是公开的，macOS runner 对本仓库不额外收费。

产物是 **未签名 IPA**，不能双击直接装到 iPhone —— iOS 只允许装经过签名的包。下面三种方式任选。

## 装到 iPhone

### A. 免费 Apple ID + Sideloadly（Windows，最省事）

1. 电脑装 [Sideloadly](https://sideloadly.io/) 和 **官方 iTunes**（不是 Microsoft Store 版，Sideloadly 需要 Apple Mobile Device 驱动）；
2. 数据线连 iPhone，解锁并点「信任此电脑」；
3. 把 `CarRemote-unsigned.ipa` 拖进 Sideloadly，填 Apple ID（建议用小号），点 Start，按提示输入密码；
4. 装完第一次打开若提示「不受信任的开发者」：设置 → 通用 → VPN 与设备管理 → 点开发者 → 信任；
5. 免费账号签名 **7 天过期**，到期重签一次即可，Sideloadly 可以勾选自动续签。

### B. AltStore / AltServer

电脑上常开 AltServer，手机装 AltStore 后导入 IPA，同一 WiFi 下会自动后台续签，比手动重签省心。同样是免费 Apple ID、7 天周期。

### C. TrollStore / 自有开发者证书

- 设备装了 TrollStore 的话，未签名 IPA 可以直接永久安装，不用 Apple ID、不过期（能不能装取决于该设备的系统版本和 TrollStore 支持范围）；
- 有付费开发者账号：提供 `.p12` 证书 + `.mobileprovision` 描述文件放进仓库 Secrets，就能让 workflow 直接产出可安装的签名 IPA，不用本机重签。

## 装好之后

1. 打开 App，iOS 问「允许访问本地网络」时点**允许**（Info.plist 里已写明用途，并声明了 `NSBonjourServices`）；
2. App 自动搜同一 Wi-Fi / 热点下的车机，发现到就直连；没发现就自动扫当前网段找开着的 18765 端口；
3. 连过一次会记住 IP，下次启动先直连，快；IP 变了会重新发现 / 扫描，不用手动改；
4. 列表里也能手点连接，或手动填 `IP:端口`（车机端「设置 → 手机遥控」页显示当前 IP）。

连不上时按这个顺序查：手机和车机在不在同一个热点/WiFi → 车机「手机遥控」开关开了没 → 车机设置页的 mDNS 自检不用看（本 App 不依赖域名）→ 点「重新搜索 / 扫网段」。

## 权限与边界

- `NSLocalNetworkUsageDescription` + `NSBonjourServices`：发现并连接局域网车机；
- ATS 放行明文 HTTP：车机服务是车内明文，没有 TLS；
- 遥控服务无 TLS、无 PIN，**不要把 18765 映射到公网**；
- 支持 iOS 15+，arm64 真机；未做 iPad 横屏适配。

## 和「Safari 添加到主屏幕」比

Safari 打开网页再「添加到主屏幕」也能得到独立图标，而且不用签名、不过期，代价是地址栏里那个域名仍然要解析。只是偶尔用，网页版足够；想要跟安卓 App 一样的局域网自动发现、IP 变了能自己找回来、掉线自愈，再用这个 IPA。

## 目录

```
ios/
├── project.yml                 # XcodeGen 工程描述
├── CarRemote/
│   ├── App.swift               # @main + 连接状态机（上次地址 / 2.5s 兜底扫描 / 掉线自愈）
│   ├── Discovery.swift         # BonjourFinder（NSD）+ LanScanner（/24 网段扫描）
│   ├── ConnectPanel.swift      # 发现列表 / 扫描结果 / 手动连接
│   ├── RemoteWebView.swift     # WKWebView 壳
│   ├── Info.plist              # ATS / 本地网络 / NSBonjourServices
│   └── Assets.xcassets/        # AppIcon
└── README.md
```
