# NOTICE

## 上游来源

- `车机端/cloudmusic-car` 派生自 **[xuqian-git/cloudmusic-car](https://github.com/xuqian-git/cloudmusic-car)**（网易云音乐非官方车机客户端），
  以 GNU LGPL-3.0 发布。原始许可证保留在 `车机端/cloudmusic-car/LICENSE`（LGPL-3.0）与 `车机端/cloudmusic-car/COPYING.GPL`（GPL-3.0）。
- 其中的 eapi 接口加密移植自 **[missuo/kumone](https://github.com/missuo/kumone)**（LGPL-3.0）。
  现行实现与上游 `RequestCrypto.swift` 一致（AES-128-ECB + MD5，大写 hex）。上游自 2026-10-28 起已全量转 eapi 并删掉 weapi，本工程 2.3.0 起同步：旧 weapi（双 AES-CBC + 预置 RSA 密文）已移除，不再保留回退路径，接口层对应上游 `MusicAPI.swift` / `MusicClient.swift`。
  应用图标取自该仓库 `ios/KumoneIOS/Assets.xcassets/AppIcon.appiconset/AppIcon.png`，未作重绘。
- 车机端里的 `qqmusic/`、`kugoumusic/`、`shared/` 是上游自带的多应用变体，本工程没有使用（`settings.gradle.kts` 只 `include(":app")`），原样保留以方便对照上游。

## 本仓库对上游做的修改

LGPL-3.0 要求分发派生作品时说明改动。以下按动机归类，时间范围 2026-10 ~ 2026-11：

1. **裁剪**：`settings.gradle.kts` 只保留 `:app`；删除跑跑桌面宿主校验（`ContainerGate.isPaopaoGuest()`），否则装到普通车机会直接崩。
2. **新增遥控模块** `com.cloudmusic.car.remote`：`WsServer`（手写 RFC6455）、`RemoteServer`（协议与状态推送）、`PlayerBridge`（唯一碰 `PlayerHub` 的适配层）、`RemoteWeb`（同端口静态页）、`MdnsResponder`（手写 mDNS，`carremote.local`）、`RemotePrefs`、`RemoteLibrary`、`OutQueue`、`RemoteHealth`、`NetworkMonitor`、`RemoteService`、`BootReceiver`。
3. **新增网页资源** `app/src/main/assets/remote.html`（单文件遥控页）。
4. **改动既有文件**：设置页（新增「手机遥控」开关与本机地址自检）、`PlayerHub`（显式 `setPlaying` 幂等接口）、`Stores`（歌曲 / 歌词缓存档位）、`MusicCache`（歌词按预算淘汰）、`AccountScreens` / `RemoteScreen`（界面与缓存控件）。
5. **构建改动**：Gradle 配置、签名走本地 `keystore.properties`（不入库）。

## 不属于上游的部分

`手机端/cloudmusic-remote`、`proto/`、`_工具/`、`docs/` 为本仓库新写，未派生自其它项目。

## 许可证

除各子目录内已有的许可证文件外，本仓库整体以 **GNU LGPL-3.0** 发布，全文见根目录 [`LICENSE`](./LICENSE)，
其引用的 GPL-3.0 条款见 [`COPYING.GPL`](./COPYING.GPL)。

> 注意：车机端是 LGPL 派生作品，不能改用 MIT / Apache 等更宽松的许可证；手机端虽然是自己写的，为了不让人误解边界，这里也一并以 LGPL-3.0 发布。
