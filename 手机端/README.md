# 手机端 · 车机遥控 APK

`cloudmusic-remote/`，包名 `com.carremote.client`，独立安装、独立运行，只依赖 `proto/` 里的协议。

## 技术选型

| 项 | 选择 | 说明 |
|---|---|---|
| 语言 | Kotlin 2.0.21 | 车机端工程已在用，工具链现成 |
| UI | Compose（Material 3） | 单页即可 |
| 网络 | **OkHttp 4.12 WebSocket** | 手机端是独立工程，加依赖没有负担；车机端不引是因为不想给宿主 APK 增依赖 |
| 发现 | `NsdManager`（`_cloudmusic-car._tcp`） | 系统自带，手动 IP 永远兜底 |
| 存储 | SharedPreferences | Token + 上次地址 + deviceId |
| minSdk | 26 | 覆盖老手机 |
| 签名 | debug（release 也回退 debug） | 没有正式签名库 |

## 代码

```
app/src/main/java/com/carremote/client/
├─ MainActivity.kt   单页 UI：连接 / 配对 / 遥控三种状态
├─ RemoteClient.kt   连接状态机 + 协议解析 + 指数退避重连（1s→2s→…→15s）
├─ Discovery.kt      NsdManager 发现与解析（串行 resolve，规避部分机型并发崩溃）
├─ Prefs.kt          Token / deviceId / 上次车机地址
└─ Protocol.kt       协议常量（与 proto/protocol.json 同步）
```

## 行为要点

- **有 Token 就 auth**，只有收到 `2004 TOKEN_INVALID` / `2005 DEVICE_REVOKED` 才清 Token 回到 PIN 配对 —— 不会每次重连都让用户跑去车上看 PIN。
- `state.seq` 小于已收到的直接丢弃，进度条不会倒跳。
- 拖动进度条时用本地值，松手才发 `seek`，避免和车机推送打架。
- 音量条只在车机推了 `volume` 且 `volumeMax > 0` 时才出现（读不到就不假装能调）。
- 车机推 `revoked` 时清 Token 并提示重新配对。

## 构建

```bash
export JAVA_HOME="<你的 JDK 目录>"        # 必须含 javac
./gradlew assembleDebug
# app/build/outputs/apk/debug/app-debug.apk → dist/车机遥控-v<版本>.apk
```

## 局限（如实记录）

- 无 TLS，Token 明文存 SharedPreferences，root 设备可读。
- 只在局域网可用，不支持外网穿透。
- 首次连接必须能找到车机（mDNS 或手动 IP）。
- `NsdManager.resolveService` 与 `NsdServiceInfo.host` 在 API 34 标记为 deprecated，目前仍可用，编译只是警告。
