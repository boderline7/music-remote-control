# iOS 遥控客户端（未签名 IPA）

给车机遥控做一个能装到 iPhone 上的独立 App。**只解决一件事：绕开 `carremote.local` 域名发现的不稳定，用固定 IP 直连车机。**

## 为什么不是继续用网页

车机端自带网页遥控（`http://carremote.local:18765/`），但那个域名靠 mDNS 活着：iOS 对组播应答的校验很严，查询包丢一次、缓存过期 120 秒没刷上，页面就打不开，而同一时刻用 IP 访问完全正常。IP 是稳定的，那就别再让域名掺和。

这个 App 里：

- **没有** mDNS、`carremote.local`、Bonjour / NSD 发现、网段扫描；
- **只有** 一个记住的 `IP:端口`，直接 `http://IP:18765/` 加载车机端 `remote.html`。页面里的 WebSocket 按 `location.host` 连同一个 IP，所以歌词、收藏、搜索、歌单、音量等所有功能和网页端 **100% 一致**，没有协议翻译；
- 原生部分只补连通性：启动前短超时探测、连不上时每 2 秒自动重试、回到前台自检、WKWebView 页面进程被系统回收后自动重载、右上角齿轮改地址。

换句话说，界面不是原生重写的，是 WKWebView 壳；换来的是零协议改动和风险最低。

## 云构建

`.github/workflows/ios-ipa.yml` 在 GitHub 的 macOS runner 上跑：

1. `xcodegen` 由 `project.yml` 生成 Xcode 工程（工程文件不入库）；
2. `xcodebuild ... -sdk iphoneos clean archive`，`CODE_SIGNING_ALLOWED=NO` 出未签名 `.app`；
3. 打成 `Payload/CarRemote.app` → `CarRemote-unsigned.ipa`，附 SHA-256；
4. 上传到 Actions artifact `CarRemote-unsigned-ipa`（保留 90 天）。

手动触发（Actions → iOS IPA (unsigned) → Run workflow）可以填：

| 输入 | 作用 |
|---|---|
| `default_host` | 编译进包的默认车机 IP，**装上即用**；App 内改过的地址优先 |
| `release_tag` | 填了就顺手把 IPA 发到 GitHub Release，方便直接下载 |

推送 `ios/**` 或该 workflow 文件也会自动构建。仓库是公开的，macOS runner 对本仓库不额外收费。

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

1. 打开 App，iOS 问「允许访问本地网络」时点**允许**（Info.plist 里已写明用途）；
2. 右上角齿轮 → 填车机固定 IP（车机端「设置 → 手机遥控」页显示当前 IP）→ 端口默认 `18765` → 保存并连接；
3. 车机没开机或服务没起时不会白屏，会显示原因并自动重试，开机后自己连上。

连不上时按这个顺序查：手机和车机在不在同一个热点/WiFi → 车机「手机遥控」开关开了没 → IP 和端口对不对 → 车机设置页的 mDNS 自检不用看（本 App 不依赖它）。

## 权限与边界

- `NSLocalNetworkUsageDescription`：连局域网车机；
- ATS 放行明文 HTTP：车机服务是车内明文，没有 TLS；
- 遥控服务无 TLS、无 PIN，**不要把 18765 映射到公网**；
- 支持 iOS 15+，arm64 真机；未做 iPad 横屏适配。

## 和「Safari 添加到主屏幕」比

Safari 打开网页再「添加到主屏幕」也能得到独立图标，而且不用签名、不过期，代价是地址栏里那个域名仍然要解析。如果只是偶尔用，继续用网页版足够；想要固定 IP、断线自愈、独立 App 不受 Safari 本地网络开关影响，再用这个 IPA。

## 目录

```
ios/
├── project.yml                 # XcodeGen 工程描述
├── CarRemote/
│   ├── App.swift               # @main + 地址状态机（探测 / 重试 / 前台自检）
│   ├── RemoteWebView.swift     # WKWebView 壳
│   ├── SetupView.swift         # 地址设置面板
│   ├── Info.plist              # ATS / 本地网络 / 默认 IP 占位符
│   └── Assets.xcassets/        # AppIcon
└── README.md
```
