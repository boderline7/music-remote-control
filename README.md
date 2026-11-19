# 车机助手 · 合并工程

原厂车机助手（`com.navi.tool`，百度加固）→ 脱壳 → 打补丁去掉授权与联网 → 注入 18766 网关 → 连同音乐端与手机端一起出包。

**一个目录走完全程。** 从 2026-11-19 起，原本分在 `D:\模块开发\脱壳` 和 `D:\车机\车机桥接空调` 的两套东西并到这里，`make.py` 不再依赖任何外部工程路径。

---

## 一、目录

```
车机助手\
├─ make.py               ★ 唯一构建入口（inspect / build / verify / sync）
├─ patches.py            22 处 smali 补丁的声明式表
│
├─ apk\                  原厂包（不入库，自己备）
│   ├─ navitool-19.00-protected.apk       V19 原厂加固包（唯一构建输入）
│   └─ navitool-18.62-original.apk        18.62 原厂包（回退用）
│
├─ dex\V19.00\           脱壳产物（不入库，见 docs/unpack.md）
│   ├─ dex\              dex_1 / dex_2（构建输入，也是锚点核对的默认目标）
│   └─ src\              jadx 反编译的 3436 个 java（查阅用）
│
├─ src\bridge\           18766 网关与车控的注入源码（17 个 Java，包名 com.navitool.bridge）
├─ web\                  页面与外壳
│   ├─ pages\            car / remote / remote_lunastub / dvr 四个页面
│   ├─ shell\            外壳四件（状态行 / 切换 / 设置面板）
│   └─ stitch.py         把外壳拼进三个页面
│
├─ music\cloudmusic-car\ 音乐端（Gradle 工程）
│   ├─ app\              第三方网易云车机版 com.cloudmusic.car（18765）
│   └─ lunastub\         桥接包（18764 桥接 + 18767 记录仪）
│
├─ phone\                手机端（android / ios）
├─ patch\                补丁工具与素材
│   ├─ ConfigAssets.java         配置预置 ContentProvider（--assets-config）
│   ├─ FreeWeather.java          目的地播报天气改 Open-Meteo（--free-weather）
│   ├─ FreeWeatherDesk.java      桌面卡片天气改 Open-Meteo（--free-desk-weather）
│   ├─ navi_configs\*.zip        8 个配置包，首启解压到 /sdcard/navitool/
│   ├─ apktool.jar               2.11.1
│   └─ debug.keystore            签名（storepass/keypass=android，alias=debugkey）
│
├─ scripts\              构建脚本（compile.sh / build_remote.sh / build_phone.sh /
│                        build_ios.sh / install.sh / push_ui.sh / verify_host.py）
│   └─ unpack\           脱壳工具（attach_unpack.py + unpack.js，frida 抓 dex）
├─ tools\                apktool.jar（手机端脚本用）+ 其它
├─ keystore\             carremote.jks（手机端）+ debug.keystore
├─ dist\                 产物（当前交付那版 + history\ 历史包）
├─ dump\                 脱壳输出（gitignore，脱一次就有）
├─ docs\                 架构 / 接口 / 网页 / 构建 / 脱壳 / 排障 / 逆向笔记
│   ├─ unpack.md          脱壳怎么在本工程跑
│   ├─ unpack-skill.md    脱壳完整技能文档（五个坑）
│   └─ history\           V18.49 重打包说明、闪退修复说明（踩坑记录）
├─ analysis\             原厂 so（18.49 / 18.62 / 19.00）、网关交接资料、配置包盘点
├─ archive\              早期协议草案（只读）；_legacy\ 存退役脚本与 18.62 解包树
└─ preview\              本地假数据服务（mock.js）
```

---

## 二、出包

### 当前交付那一版（一条命令）

```bash
cd 车机助手          # 工程根目录

# apk/ 与 dex/ 不入库，先自备：原厂包装到 apk/，脱壳产物按 docs/unpack.md 放到 dex/<版本>/
python make.py build \
  --apk "apk/navitool-19.00-protected.apk" \
  --dex "dex/V19.00/dex" \
  --inject --no-so --assets-config --free-weather --no-seat-confirm --floating-map --no-weather \
  --out "dist/车机助手_V18.73_bridge_源19.00_去so_带配置_无天气.apk"
```

用时约 1 分半，产物 9,586,182 字节，自校验 22/22 补丁落地。包名里的 `无天气` 指的是**桌面天气卡片已停用**；`--free-weather` 只负责目的地播报那条链，不要因为带了 `--free-weather` 就叫成「免费天气」。

### 其它用法

```bash
# 拿到新脱壳 dex 后先诊断能不能直接用
python make.py inspect <dex目录>

# 纯 mod 包（去授权 / 去联网，不带网关）
python make.py build --dex <dex目录>

# 校验任意产物
python make.py verify <apk>
```

### 开关

| 开关 | 作用 |
|---|---|
| `--inject` | 注入 18766 网关 + 车控页 + classes3.dex |
| `--no-so` | 整块去 so，破 V19 反篡改自杀（27 个 native 桩化） |
| `--assets-config` | 预置瑞虎9+通用配置（8 包 / 162 文件 / 1.57MB） |
| `--free-weather` | 目的地播报天气改 Open-Meteo（免 key） |
| `--free-desk-weather` | 桌面时钟卡片天气也改 Open-Meteo（与 `--no-weather` 互斥） |
| `--no-seat-confirm` | 去掉座椅「应用配置」确认弹窗 |
| `--floating-map` | 悬浮地图切日历结束进程、切回唤醒 |
| `--no-weather` | 停用桌面天气（不定位、不定时、不发请求，保留目的地播报） |

默认都不开。当前交付那版是七个一起开，但**不含 `--free-desk-weather`**——桌面天气既然已经停用，再改它的 URL 和 JSON 解析就是死代码，`make.py` 会拒绝这两个开关同时出现。

### 音乐端与手机端

```bash
python web/stitch.py           # 改过 web/shell/* 必须先跑，否则包里还是旧外壳
bash scripts/build_remote.sh   # 出桥接包 + 第三方网易云
bash scripts/build_phone.sh    # 出安卓手机端
bash scripts/build_ios.sh      # 推 phone-ios，GitHub 云构建出 IPA
bash scripts/install.sh        # 自动取 dist/ 最新包推到车机安装
node preview/mock.js           # 不上车看网页效果：http://127.0.0.1:18765/
```

---

## 三、版本与签名

**版本号一律不动，方便回退。**

- 车机助手：`18.73-mod` / versionCode `25`，`CN=Navitool Mod`
- 改内容就重出同名包覆盖，随时能退回上一版
- 手机端 App 不用换包，刷新页面即可；纯网页改动只影响车机端

产物命名备注来源版本：`源19.00` 表示 dex 来自 V19.00 原包；`无天气` 表示桌面天气已停用（目的地播报仍在）。

**业务代码只有一个来源：V19.00 脱壳 dex。** 工程内不混 18.62 的 smali 或解包树，原 `apk\base_18.62-mod` 已移入 `archive\_legacy\`。V19 比 18.62 多 55 个顶层类（胎压 `TireFloatingView` / `TirePollingService`、日志引擎 `LogEngine` 一族、导航 API `NaviEngine` 一族、场景 `SceneDecision` 等），这 55 个只有 V19 有。

---

## 四、三条端口线

| 端口 | 谁提供 | 什么时候在 |
|---|---|---|
| **18766** | 车机助手（本工程注入的 dex） | 常驻。进程起来 + 前台服务 START_STICKY + 开机广播 |
| **18765** | 第三方网易云车机版（`music/cloudmusic-car/app`） | 这个 App 在跑时 |
| **18764** | 桥接包（`music/cloudmusic-car/lunastub`） | 官方网易云模式在跑时 |
| **18767** | 桥接包 `DvrSvc` | 常驻，跟音源无关 |

18764 与 18765 是同一个东西的两条来源，**同一时刻只有一个在跑**。

**统一入口**：手机和浏览器只连 **18766**，页面顶部三个 tab —— 车机控制 / 音乐控制 / 行车记录仪。注入 dex 兼做网关（`Gateway.java`）：`/music/*` 与 `/api/*` 转到在跑的音乐后端，`/dvr/*` 转到 18767，车控 API 仍是本地。

---

## 五、构建的硬约束

- **构建目录必须纯 ASCII**。apktool 在中文路径下编 res 会失败，所以解包树落在 `C:/Temp/mod_build/build_<版本>/`，工程内的中文路径只作为源码位置。
- **补丁先全部预检**。22 处补丁任何一处对不上就停，不会改一半。
- **4K 对齐必须做**。清单是 `extractNativeLibs=false`。
- 中间产物不在工程内，可以随时由 `make.py` 重建。

---

## 六、修改取向（历次定下的规矩）

**交互**
- 反感「要先点同意/确认」的弹窗：首启使用说明、座椅应用配置确认框都已去掉，点按钮直接执行。以后遇到类似确认框默认直接执行。
- 例外：「保存配置」这类不可逆动作的确认框保留。

**架构：18766 不设鉴权**
- 局域网自用，不做登录、令牌、IP 白名单。手机连上就能控，图的是打开即用。
- 代价是同一网段任何人都能控车机，接受这个前提。

**悬浮地图：切走即结束，不是只收起**
- 原厂 `cycleFloatingMapDisplayMode()` 切到日历只发 `closemap` 把悬浮窗收起来，地图进程还在后台跑。
- 这里补上进程级处理：切走时冻结（Device Owner 的 `setApplicationHidden`）或 `force-stop`，切回才唤醒。用 `--floating-map` 开启。

**功能扩展**
- 新功能优先做进 **18766 注入层**：加 HTTP 端点 + 车控页往下滑的新区块，而不是改原厂 smali。
- 复用应用已有权限实现系统级能力（`KILL_BACKGROUND_PROCESSES`、`QUERY_ALL_PACKAGES`、`MANAGE_EXTERNAL_STORAGE`），不额外申请。
- 车控/配置优先本地预置与网关下发，不依赖云盘现下现用。

**只改页面不用重装**
- `Page.java` 先读 `/sdcard/Android/data/com.navi.tool/files/web/index.html`，读到且非空就用它，没有才回落到包内 `assets/carbridge.html`。
- 改一个标题、一个按钮不值得为它重签重装一次原厂包：`bash scripts/push_ui.sh`，推完手机端刷新即可。
- 重签重装那个包是全流程里风险最高的一步，**只在原厂升级后做一次**。

**原厂升级**
- 车控靠反射绑原厂 `carControlHelper`，锚点全是混淆过的类名 / 字段名 / 方法签名 / 命令号，原厂一升级就可能哑。
- `python scripts/verify_host.py` 默认核对当前 V19 脱壳 dex（`dex\V19.00\dex`），15 条锚点，分致命（车控整体不可用）与警告（单项失效）两级，退出码 0 表示能接。

**交付：只出真正有变化的那一端**
- 纯网页改动（样式 / 文案 / 交互 / 已有协议命令）只影响车机端；手机端 APK 与 iOS IPA 不用重编、不用重装，刷新页面即可。
- 只有新增原生能力、改发现 / 端口 / 鉴权、改图标权限版本号时才重出手机端包。
- 不为了「三端配套」把没变的包再编一遍。
- 拿到新包时**不能直接喂 .apk**：原包是百度加固的，apktool 解出来只有壳，查不到业务类（会 9 条致命全红）。先脱壳，再用 `python scripts/verify_host.py <脱壳dex目录>` 或指向解包树。

---

## 七、这个工程是合并来的

2026-11-19 之前，这套东西分在两处：

| 原位置 | 原用途 |
|---|---|
| `D:\模块开发\脱壳` | 脱壳与打补丁（`make.py` / `patches.py` / V19 源码 / 补丁工具） |
| `D:\车机\车机桥接空调` | 注入源码、页面、音乐端、手机端（git 仓库，42 个提交） |

两边原本靠 `make.py` 实时读 `D:\车机\车机桥接空调\car\bridge` 打通的。合并后源码进了本工程的 `src\bridge` 与 `web\pages`，`BRIDGE_ROOT` 指向工程自身，**不再依赖任何外部路径**。

合并当天验证：新工程独立出包 9,590,278 字节，与旧工程产物逐条目一致，`classes.dex` / `classes3.dex` / `carbridge.html` 三份内容 MD5 完全相同（整包 MD5 差异仅来自 ZIP 时间戳）。

旧的 `04_交付` 与 `dist` 里那些同名不同 MD5 的包，是上面那次并存期留下的，以本工程 `dist\` 为准。

**旧目录保留未动**，确认无误后自行删除。

---

## 八、已知问题

- **18.62 脱壳 dex 没有第二份**。`D:\模块开发\脱壳\车机.7z` 已不存在；18.62 的 `dex\` + `src\`（3 dex + 3546 java）只在 `D:\车机\_trash\20261117-2200\车机助手_V18.62_脱壳源码\` 有一份。
  兜底：18.62 **原厂包**已收进 `apk\navitool-18.62-original.apk`（vc13，全盘唯一一份），真丢了还能按 `docs/unpack.md` 重新脱。
  原 `apk\base_18.62-mod`（已打补丁的改版树，非原厂解包树）在 `archive\_legacy\`，构建与锚点核对都不依赖它。
- **`music\cloudmusic-car` 派生自 LGPL-3.0 项目**（`xuqian-git/cloudmusic-car`），`README.md` 里引用的根目录 `NOTICE.md` 目前不存在。若将来开源，需补来源链与改动声明。
- **新装的车机助手要先手动打开一次**，之后才由服务接管（安卓 stopped 状态不投递开机广播）。
- 装新车机助手 APK 必须 `am force-stop com.navi.tool` 再启动（进程内缓存）。