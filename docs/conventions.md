# 约定

历次定下的规矩，改代码前先看这里。

## 构建硬约束

- **构建目录必须纯 ASCII**。apktool 在中文路径下编 res 会失败，解包树落在 `C:/Temp/mod_build/build_<版本>/`，工程内的中文路径只作源码位置。
- **补丁先全部预检**。22 处补丁任何一处对不上就停，不会改一半。
- **4K 对齐必须做**。清单是 `extractNativeLibs=false`。
- 中间产物不在工程内，随时可由 `make.py` 重建。

## 交互

- 不做「先点同意/确认」的弹窗：首启使用说明、座椅应用配置确认框都已去掉，点按钮直接执行。
- 例外：「保存配置」这类不可逆动作的确认框保留。

## 18766 不设鉴权

- 局域网自用，不做登录、令牌、IP 白名单。手机连上就能控。
- 代价是同一网段任何人都能控车机，接受这个前提。

## 悬浮地图：切走即结束

- 原厂 `cycleFloatingMapDisplayMode()` 切到日历只发 `closemap` 收起悬浮窗，地图进程还在后台跑。
- 这里补进程级处理：切走时冻结（Device Owner 的 `setApplicationHidden`）或 `force-stop`，切回才唤醒。用 `--floating-map` 开。

## 功能扩展

- 新功能优先做进 18766 注入层：加 HTTP 端点 + 车控页往下滑的新区块，不改原厂 smali。
- 复用应用已有权限实现系统级能力（`KILL_BACKGROUND_PROCESSES`、`QUERY_ALL_PACKAGES`、`MANAGE_EXTERNAL_STORAGE`），不额外申请。
- 车控/配置优先本地预置与网关下发，不依赖云盘现下现用。

## 只改页面不用重装

- `Page.java` 先读 `/sdcard/Android/data/com.navi.tool/files/web/index.html`，读到且非空就用，没有再回落到包内 `assets/carbridge.html`。
- 改标题、改按钮不值得重签重装：`bash scripts/push_ui.sh`，推完手机端刷新。
- 重签重装是全流程里风险最高的一步，只在原厂升级后做一次。

## 原厂升级

- 车控靠反射绑原厂 `carControlHelper`，锚点全是混淆过的类名 / 字段名 / 方法签名 / 命令号，原厂一升级就可能哑。
- `python scripts/verify_host.py` 默认核对 `dex\V19.00\dex`，15 条锚点，分致命（车控整体不可用）与警告（单项失效）两级，退出码 0 表示能接。
- 拿到新包**不能直接喂 .apk**：原包加固，apktool 只解得开壳。先脱壳再核对。

## 交付：只出真正有变化的那一端

- 纯网页改动（样式 / 文案 / 交互 / 已有协议命令）只影响车机端；手机端 APK 与 iOS IPA 不用重编、不用重装。
- 只有新增原生能力、改发现 / 端口 / 鉴权、改图标权限版本号时才重出手机端包。
- 不为了「三端配套」把没变的包再编一遍。

## 业务代码来源

只有一个来源：V19.00 脱壳 dex。工程内不混 18.62 的 smali 或解包树，原 `apk\base_18.62-mod` 已移入 `archive\_legacy\`。

V19 比 18.62 多 55 个顶层类（胎压 `TireFloatingView` / `TirePollingService`、日志引擎 `LogEngine` 一族、导航 API `NaviEngine` 一族、场景 `SceneDecision` 等），这 55 个只有 V19 有。
