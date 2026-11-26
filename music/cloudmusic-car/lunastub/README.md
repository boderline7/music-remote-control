# lunastub —— 已停用（记录仪已并进车机助手）

> **2026-11-25 起不再出包。** 行车记录仪代理（18767）整包搬进了车机助手
> （`src/bridge/src/com/navitool/bridge/dvr/`），和 18766 同进程。
> 文件传输改用原版小绿传 APK，不再需要「车控互联」这个合并包。
> 本目录代码仅作历史参考；`dist/` 不再产出 `车控互联_*.apk`。
> **装新车机助手前必须卸掉 `com.yuhan.dvrcar`**，否则两个包抢 18767。


> **2026-11-25 起：改名 + 去音乐桥接。**
> 包名从 `com.luna.music.car` 改成 `com.yuhan.dvrcar`，桌面名「车控互联」。
> 原来给官方网易云做桥接的那套（MediaBrowser / 方控 / 歌词爱心 / 歌单 / 18764 服务）
> 已整份删除，`src/parked` 也删了，仓库里不再保留。
> 音乐这块现在由车机助手的酷我槽位直连第三方网易云（`com.cloudmusic.car`）完成，
> 跟本包没有关系。

本包只剩两件事：

1. **文件传输**（小绿传 `com.yuhan.xiaolvchuan`，编译期合并进来，见第 2 节）
2. **行车记录仪**：`DvrSvc` 常驻 18767，列表走设备 7878 控制口，文件走 80 端口中转缓存

外加一个设置页 `.Main`（车控 18766 状态、日志开关、退出）。

## 1. 端口线

| 端口 | 谁 | 说明 |
|---|---|---|
| 18766 | 车机助手 `com.navi.tool` | 车控 + 统一入口，常驻 |
| 18767 | 本包 `DvrSvc` | 记录仪页面与文件 |
| 18765 | 第三方网易云 `com.cloudmusic.car` | 它的遥控页 |
| ~~18764~~ | — | 原音乐桥接口，已随桥接代码删除 |

## 2. 与小绿传合并

车机上不再单独装「小绿传」，并进本包一起装：入口是小绿传的 `MainActivity`（文件收发），
左上品牌区的「设置」进本包 `.Main`。

```bash
python scripts/merge_xiaolv.py            # 用现有 Gradle 产物合并
python scripts/merge_xiaolv.py --gradle   # 先跑一次 Gradle 再合并
```

链路：`apktool d` 本包产物 → 注入小绿传的 `smali`（落 `smali_classes2`）/`res`/`assets`
→ R 引用改指 `com.yuhan.dvrcar.R` → 改清单（入口、权限、FileProvider）→ 布局加「设置」
+ `MainActivity.openSettings()` → `apktool b` → 补 `classes3.dex`（`Browse.java` 重新
javac+d8）→ `zipalign` → `apksigner`（`keystore/carremote.jks`，与原包同证书才能覆盖安装）。

- 小绿传自己的类包名不动，只把 `FileProvider` authority 与本包对齐（`com.yuhan.dvrcar.files`），
  否则和真正的独立小绿传同机共存会 `INSTALL_FAILED_CONFLICTING_PROVIDER`。
- 资源 ID 由小绿传的 `public.xml` 固定，`apktool.yml` 的 `forcedPackageId` 必须是 127。
- 「退出」原来最后一步是 `Process.killProcess(myPid)`，已去掉：本进程还扛着 18767 常驻服务。
- 产出：`dist/车控互联_含文件传输_v2.10.29.apk`。

## 3. 构建

```bash
cd music/cloudmusic-car
./gradlew :lunastub:assembleRelease --offline
```

签名复用 `keystore.properties`（与主应用同一套，方便一次 `adb install` 多个包）。
`assets/dvr.html` 由 `scripts/build_remote.sh` 从 `web/pages/dvr.html` 同步；运行时
`DvrServer` 直接读 assets 提供。

手工打包（无 Gradle 时）：`javac -source 8 -target 8` → `d8 --min-api 28` →
`aapt2 link -A assets` → `jar uf classes.dex` → `zipalign -4` → `apksigner`。

## 4. 老包怎么处理

`com.luna.music.car`（旧的「汽水音乐_桥接包」）与本包**不共用安装位**，升级不会覆盖。
车机上要手动卸载旧包，否则桌面并排两个图标、旧包的 18767 还会和白占端口。

## 5. 定版与校验

版本 `2.10.29` / `versionCode 72` 固定不动（改内容重出同名包，方便随时回退）。

合并包自检（`merge_xiaolv.py` 的 `verify`）：包名、`versionCode/name`、label
「车控互联」、launchable = 小绿传 `MainActivity`、三个 dex、`assets/web.html`
与 `assets/dvr.html` 都在。

音乐残留自检（dex 字符串里应当 0 处）：`MediaBrowser`、`AutomotiveService`、
`MediaController`、`PLAYER_CONTROL`、`PLAYER_SYNC`、`star_action`、`playlist/detail`、
`18764`、`com.netease.cloudmusic.iot`。
