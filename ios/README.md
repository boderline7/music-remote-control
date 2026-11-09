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
3. 打包前把仓库里的 `Info.plist` 强制合并进 `.app/Info.plist` 并逐键校验 —— XcodeGen 的 `info.path` 会把该文件覆写成自己生成的版本（实测只剩几个 `CFBundle*` 键），ATS 明文放行、本地网络权限说明和 `NSBonjourServices` 会全部丢失；因此生成工程前先备份权威 plist，打包时以备份为准合并。不补上 App 就发现不了车机、也加载不了明文 HTTP；
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

连不上时按这个顺序查：手机和车机在不在同一个热点/WiFi → 车机「手机遥控」开关开了没 → 车机设置页的「域名发现」默认关闭是正常的（本 App 不依赖域名）→ 点「重新搜索 / 扫网段」。

## 1.2.1 修复（与安卓 2.0.1 / 车机端 2.2.11 同步）

- **物理音量键无法控制车机**：这是这一版的主修项，详见下面重写过的实现说明；
- 安卓图标换成与 iOS 同款（深色底 + 正红圆 + 白色播放三角）；
- 歌词方框固定 3 行、每行等高，换歌/歌词刚到位时锁在第一句，不再开场乱跑；
- 「添加到歌单」移到爱心旁边同一行，两个圆钮同为 44×44。

## 1.2.0 新增（与安卓 2.0.0 / 车机端 2.2.10 同步）

这一版三端 UI 完全一致：安卓也改成 WebView 壳，界面全部来自车机端 `remote.html`，改一处三端同时生效。

- 歌词方框移到页面最顶部，约 5 行自动跟随播放进度，默认不可手动滑动；
- 取消歌单 / 歌词双页切换与左右滑动；
- 「添加到歌单」缩小并排到右上角爱心下方；修复其调用的下线接口（404「接口未找到」→ 现行 `/playlist/manipulate/tracks`）；
- 音量加减按钮收成低调小钮，不再是大红方块；
- 新增 App 内刷新：网页右上角按钮 + 下拉 `UIRefreshControl`，车机端 `refresh` 命令会清缓存后重推资料库 / 榜单 / 歌词 / 播放状态；
- 车机端设置页新增「域名发现」开关，默认关闭；iOS 靠 Bonjour + /24 扫描发现，不依赖域名；
- 壳层在页面 URL 上带 `?client=ios&cv=<版本>`，车机侧能区分是哪个客户端连进来的；
- 上一版（1.1.0）已取消点不中的右上角设置齿轮，连接设置仍在未连接页 `ConnectPanel`；硬件音量键转发保持不变。

### iOS 硬件音量键的实现与限制（1.2.1 重写）

iOS **不开放**物理音量键事件，`WKWebView` 也收不到，只能间接识别。1.2.0 只有一条 KVO 通道且 `MPVolumeView` 放在屏幕外、只找一层 subviews，在部分 iOS 版本上既找不到滑杆也收不到回调，表现为「按了没反应」。1.2.1 改成双通道 + 桥接兜底：

1. 激活 `AVAudioSession`（`.playback` + `.mixWithOthers`），KVO 监听 `outputVolume`（主通道）；
2. 把 `MPVolumeView` 挂到 `keyWindow`（`alpha = 0.01`，不能是 0，否则系统不渲染、内部滑杆可能不创建），**递归**找出 `UISlider` 并监听它的 `valueChanged`（备用通道，KVO 不派发时滑杆仍会动）；
3. 两条通道统一在去重器里判断加 / 减（120ms 冷却），回锚点的回声按数值直接忽略，不占用冷却；随后用 `UISlider.setValue(0.5)` 把系统音量拉回锚点，保证下一次按键仍能被识别，同时吃掉系统音量 HUD；回拉找不到滑杆会退避重试 8 次，即使回拉失效，备用通道仍能继续识别；
4. `WebViewBridge.webView` 由 `weak` 改成强引用，`updateUIView` 每次重新指向当前 `WKWebView`，并在 `documentStart` 注入「先排队」的 `window.carRemoteStepVolume`——网页没加载完时按下的键进队列，页面加载完由 `remote.html` 补放，命令不会丢；
5. 网页侧走的仍是和手动音量按钮完全相同的 `setVolume` 协议命令。

真机上重点验这几条（Windows 编不了 Swift，只能靠云构建 + 真机确认）：

- **系统音量已在 0 或 100、或开了静音键**：启动时会尝试拉回 0.5；若该机型禁止程序改音量，按键仍可通过滑杆通道识别，但可能识别不到同方向的连续按压；
- **App 未连接车机时**：WebView 尚未创建，音量键不转发，属预期行为；
- **冷启动 / 后台切回**：`attachVolumeView()` 最多退避重试 8 次（约 2.4 秒），`didBecomeActive` 会重新激活音频会话并回锚点；
- **抢音频会话**：`.playback` + `.mixWithOthers` 尽量避免影响别的 App，仍建议确认导航播报、蓝牙通话正常；
- 若一次按键触发两格音量，把 `lastHandled` 的 0.12 秒冷却调大。

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
│   ├── RemoteWebView.swift     # WKWebView 壳 + 下拉刷新
│   ├── Info.plist              # ATS / 本地网络 / NSBonjourServices
│   └── Assets.xcassets/        # AppIcon
└── README.md
```
