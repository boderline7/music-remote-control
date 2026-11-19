# 构建

## 链路

| 脚本 | 产物 | 依赖 |
|---|---|---|
| `python web/stitch.py` | 把 `web/shell/*` 拼进 `web/pages/` 三个页面 | 无 |
| `bash scripts/compile.sh` | `src/bridge/build/dex/classes.dex` | JDK 17、`android.jar`、`d8` |
| `python make.py build` | `dist/车机助手_*.apk` | `apk/navitool-19.00-protected.apk`、`dex/V19.00/dex`（两者不入库）、`patch/apktool.jar`、`patch/debug.keystore` |
| `bash scripts/build_remote.sh` | `dist/汽水音乐_桥接包_v*.apk`、`dist/网易云音乐车机版_v*.apk` | Gradle 离线缓存、`keystore.properties` |
| `bash scripts/build_phone.sh` | `dist/车机遥控_手机端_v3.2.1.apk` | 同上 |
| `bash scripts/build_ios.sh` | 推 `phone-ios` 分支，GitHub 云构建出 IPA | git |
| `bash scripts/install.sh` | — | adb + root |

`make.py` 是车机助手唯一的构建入口，`inspect / build / verify` 三个子命令。旧的 `scripts/build.py`（读 `car/base` 解包树那条路径）已退役，存在 `archive/_legacy/build.py`。

脚本统一从工程根启动（自己 `cd "$(dirname "$0")/.."`），所以 `bash scripts/xxx.sh` 和 `cd scripts && bash xxx.sh` 都行。

**但根目录不再有转发脚本**：旧的 `bash build_remote.sh` 这种写法已经失效，改成 `bash scripts/build_remote.sh`。

## 外部路径

JDK、build-tools、android.jar、ASCII 构建目录都走环境变量，顺序是：
环境变量 → `scripts/config.local.sh` → `scripts/config.example.sh` 里的默认值。

```bash
cp scripts/config.example.sh scripts/config.local.sh   # 然后按需改
```

| 变量 | 默认值 | 说明 |
|---|---|---|
| `JDK_HOME` | `<jdk-17 目录>` | 要带 `bin/javac` |
| `BUILD_TOOLS` | `<build-tools 目录>` | `d8.bat`、`zipalign.exe`、`lib/apksigner.jar` |
| `ANDROID_JAR` | `<sdk>/platforms/android-35/android.jar` | 编译与 d8 的 bootclasspath |
| `PLATFORM_TOOLS` | `<sdk>/platform-tools` | `adb` |
| `BRIDGE_BUILD_DIR` | `C:/Temp/bridge_build` | **必须纯 ASCII** |

`config.example.sh` 里的默认值是本机实际在用的那几个路径，换机器基本只动前三项；
`config.local.sh` 不入库。

## make.py build 做了什么

1. `apktool d` 解包 `apk/navitool-19.00-protected.apk` 到 `C:/Temp/mod_build/build_<ver>` —— **apktool 在中文路径下编 res 会失败**，所以构建目录永远在 ASCII 路径
2. 清掉原包里由加固壳解出的 smali，用 `DexKit dis` 把 `dex/V19.00/dex` 反汇编进 `smali/`、`smali_classes2/`
3. 按 `patches.py` 的声明式补丁表定位并改写（22 处，命中数必须为 1，否则报错不动文件）
4. 按开关去 so / 免费天气 / 去座椅确认 / 悬浮地图 / 停用天气 / 预置配置
5. 清单里加 provider / 前台服务 / 开机广播声明（幂等，已存在就跳过），`versionCode: 25`、`versionName: 18.73-mod`
6. 现场 `javac` + `d8` 编译 `src/bridge` 注入源码，并把 `web/pages/car.html` 拷成 `assets/carbridge.html` —— 18766 首页就是它
7. `apktool b` → 追加 `classes3.dex`（不压缩）→ `zipalign -f -p 4` → `apksigner`
8. 自校验：补丁落地数、dex 个数、壳残留、签名、对齐，任一项不过就报错

`build_remote.sh` 先把 `web/pages/*.html` 同步进 `music/cloudmusic-car/*/src/main/assets/`，
再 `gradlew :lunastub:assembleRelease --offline`、`:app:assembleRelease --offline`。Gradle 与 JDK 17 都走本机缓存，不需要联网。

## 包名、版本、签名

| | 包名 | 版本 | 签名 |
|---|---|---|---|
| 车机助手 | `com.navi.tool` | 25 / 18.73-mod | `patch/debug.keystore` |
| 桥接包 | `com.luna.music.car` | 72 / 2.10.29 | `keystore/carremote.jks` |
| 手机端（安卓） | `com.carremote.client` | 28 / 3.2.1 | `keystore/carremote.jks` |
| 网易云车机版 | `com.cloudmusic.car` | 55 / 2.5.3 | `keystore/carremote.jks` |
| 手机端（iOS） | `com.carremote.ios` | 2.1.1 (21) | 未签名，自签 |

- **版本号默认不动。** 改内容就重出同名包覆盖，任何时候都能退回上一个包，不会被版本号卡住安装。
- **签名库只允许移动，不允许重新生成。** 换一份就覆盖安装不上，车机上的图标和授权都要重来。
- 三个 CarRemote 包的证书指纹必须一致：`0d0b4563…`（SHA-256）。车机助手是 `Navitool Mod` 的 `2125e975…`。

```bash
# 校验
java -jar "$BUILD_TOOLS/lib/apksigner.jar" verify --print-certs dist/xxx.apk
```

`music/cloudmusic-car/keystore.properties` 与 `phone/android/keystore.properties` 都指向根 `keystore/carremote.jks`
（`storeFile=../../keystore/carremote.jks`），两个工程深度一样，移动目录后不用改。

## iOS 云构建

Windows 上编不了 iOS。`scripts/build_ios.sh` 用临时 index 把 `phone/ios/**` 和
`.github/workflows/ios-phone.yml` 单独做成一棵树，推到 `phone-ios` 分支，GitHub 在 macOS runner 上跑
`ios-phone.yml`，出**未签名 IPA** 挂在 Artifacts 里（`CarRemote-unsigned-ipa`）。

路径调整后必须重推一次，否则分支里还是旧路径，workflow 找不到工程。

## 原厂包升级：先核对锚点

车控靠反射绑原厂桌面的 `carControlHelper`，锚点全是混淆过的类名 / 字段名 / 方法签名 / 命令号。
原厂一升级，任何一条变了车控就哑。拿到新包先跑：

```bash
python scripts/verify_host.py                   # 默认查 dex/V19.00/dex（脱壳业务码）
python scripts/verify_host.py <脱壳dex目录>      # 换版本时指到新的脱壳 dex
python scripts/verify_host.py <解包目录>          # 或已解包并经脱壳合并的树
```

⚠️ **别直接喂原厂 .apk**：原包是百度加固的，apktool 解出来只有壳，业务类一个都看不到，
会 9 条致命全红。必须先脱壳。

分两级：`致命`（活动类、helper 字段与类型、`sendSeatCommand`、`actionSendCommand`、卡片链）
失败则车控整体不可用；`警告`（座椅命令号、总线通道、prefs 键、动作名）失败只影响单项。
退出码 0 = 能接，1 = 有致命项失败。锚点出处见 `docs/reverse-findings.md`。

## 只改页面：不用重装注入包

`Page.java` 先读外部覆盖文件，读到就用它，没有才回落到包内 `assets/carbridge.html`：

```bash
bash scripts/push_ui.sh            # 拼页 + 推到车机，约 3 秒
bash scripts/push_ui.sh --status   # 看现在有没有覆盖
bash scripts/push_ui.sh --clear    # 回到包内版本
```

推完在手机上刷新页面即可，不用重启车机助手。**改 UI 不该为它重出重装原厂包** ——
重签、重装那个包是全流程里风险最高的一步，只在原厂升级后做一次。

## 入库策略

进库：`src/bridge/`、`music/`、`phone/`、`web/`、`scripts/`、`docs/`、`keystore/`、`preview/mock.js`、workflow、`archive/`。

不进库：`dist/*.apk`、`*.ipa`、`archive/_legacy/base_18.62-mod`、`tools/apktool.jar`、`patch/apktool.jar`、各类 `build/`、`local.properties`、
`scripts/config.local.sh`、`phone/ios/out`、`*.xcodeproj`、`_trash/`。
都能从源码或原厂包重新生成。

仓库是私有的，两个 `keystore.properties`（含明文口令）和两个签名库都在库里 —— 私有仓库下这是有意的取舍。
**唯一的红线是保持 private**：签名库和口令同时在库，一旦公开或被 fork 到公开仓库，任何人都能签同名包覆盖安装。
将来真要公开，先换签名（车机需卸载重装），再移出 `keystore.properties`，且注意历史里仍有。

## 版本记录

提交历史就是版本记录：

```bash
git log --oneline     # 改过什么
git tag               # 早期版本的 tag，不是每版都有
```

tag 只在装车验证通过时打；产物走 GitHub Release，只留「备案版 + 最新版」。
