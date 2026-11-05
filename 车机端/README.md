# 车机端 · cloudmusic-car（含遥控模块）

`cloudmusic-car/` 是上游 `xuqian-git/cloudmusic-car` 的工作副本（origin `c881d7d`，网易 1.0.17）。
原件在 `D:\车机\cloudmusic-car_源码\`，**未改动**，随时可 diff。

## 相对上游的改动

### 1. 裁剪到只剩网易云

`settings.gradle.kts` 只 `include(":app")`，`qqmusic` / `kugoumusic` / `plugin/` / `tools/package-*.sh` 全部不参与构建。
（`shared/nowplaying` 仍被 `app` 引用，保留。）

### 2. 摘掉跑跑桌面宿主校验

`CloudMusicApp.kt` 原第一行：

```kotlin
check(ContainerGate.isPaopaoGuest(this)) { "CloudMusic must run inside Paopao Desktop" }
```

它通过调用栈里的 `top.niunaijun.blackbox.app.BActivityThread` 和 `/proc/self/maps` 里的宿主 APK 判断，
不在跑跑桌面里就直接崩。独立 APK 必须删掉，`ContainerGate` 整个类一并移除。

### 3. 新增遥控模块 `com.cloudmusic.car.remote`

| 文件 | 职责 |
|---|---|
| `RemoteConfig.kt` | 端口 18765、PIN 120s/5 次、tick 1s、错误码，集中配置 |
| `WsServer.kt` | 手写 RFC 6455 子集：握手、单帧文本、掩码解包、ping/pong、close；8 KB 上限 |
| `RemoteServer.kt` | 协议分发、PIN 配对、Token 认证、1s 状态推送（内容不变则不发）、5s 看门狗、连接数上限 |
| `PlayerBridge.kt` | **唯一**直接调 `PlayerHub` 的类；音量走系统 `STREAM_MUSIC` |
| `PairingStore.kt` | Token 只存 SHA-256；设备列表存 SharedPreferences |
| `NsdRegistrar.kt` | 注册 `_cloudmusic-car._tcp`，失败只记日志 |

为什么能直调：`PlayerHub` 是 `object`（进程级单例），遥控服务和播放器同进程，
不需要 Binder / MediaController，也不需要新建 Service。详见 `../docs/03-阶段0-勘察报告.md`。

### 4. UI

- `ui/screens/RemoteScreen.kt`：开关、`IP:端口`、6 位 PIN（1s 倒计时）、已连接数、已配对设备与移除。
- `ui/screens/AccountScreens.kt`：设置页新增「手机遥控」入口。
- `ui/Nav.kt` / `ui/AppRoot.kt`：新增 `Route.Remote`。
- `data/Stores.kt`：`Settings.remoteEnabled` 持久化，车机重启自动恢复遥控服务。

### 5. 构建适配

```diff
- compileSdk = 36
+ compileSdk = 35      # AGP 8.7.3 上限就是 35，本机 SDK 也只到 35
- versionCode = 18 / 1.0.17
+ versionCode = 19 / 1.1.0
```

`gradle.properties` 增加 `android.overridePathCheck=true`（工程在中文路径下，AGP 默认拦截；本工程无 NDK，不受影响）
和代理 `systemProp`；新增 `local.properties` 指向本机 Android SDK。

## 构建

```bash
export JAVA_HOME="<你的 JDK 目录>"        # 必须含 javac
./gradlew assembleDebug
# app/build/outputs/apk/debug/app-debug.apk
```

## 回退

```bash
diff -r /path/to/cloudmusic-car_源码 ./ \
     --exclude=.git --exclude=build
```

要回到纯净上游：删掉本目录，从你自己保存的原包目录重新拷一份。
