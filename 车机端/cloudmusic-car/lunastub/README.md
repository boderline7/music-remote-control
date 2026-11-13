# lunastub —— 桥接层（车机助手里的「汽水音乐车机版」）

包名 `com.luna.music.car`。车机助手（`com.navi.tool`）按包名硬编码识别音源，这个包名在它的表里对应「汽水音乐车机版」（source 5），所以装上本包，助手音源列表才会出现这一项。**本包不放音乐**，只做三件事：撑住列表项、在「官方」模式下把**官方网易云车机版**（`com.netease.cloudmusic.iot`）包装成可遥控的播放后端、在 18765 提供遥控网页。它跟字节的汽水音乐无关。

## 1. 运行角色：先看模式，端口只做兜底

默认（两个包都装）走第三方自研播放器 `com.cloudmusic.car`，本包不启动、不绑端口，只在用户手动切「官方网易云」后才被拉起。

| 场景 | 判定 | 行为 |
|---|---|---|
| 第三方模式（默认） | 用户没主动切官方 | 本包不服务；18765 归 `com.cloudmusic.car` |
| 官方模式 | 用户点了「官方网易云」 | 连接官方 MediaBrowser，绑定 18765，进入前台服务 |
| 官方模式但端口已被占 | 第三方还没退干净 | **让位**，不抢端口，`stopSelf()` |

让位路径必须先 `startForeground()` 再 `stopSelf()`：`startForegroundService()` 之后 5 秒内没进前台会 ANR 掉进程，`stopSelf()` 本身不是原因。

启动日志分三段记录，便于区分「端口交接成功」和「服务交接成功」：

```
PORT 18765 空闲
SERVER 已启动，端口 18765，accept 线程已拉起
SVC 进入前台，本包开始提供服务（端口 18765）
```

## 2. 单曲点播状态机

官方车机版**没有**「按 songId 播放」的入口（`onPlayFromMediaId` / `onSkipToQueueItem` 是空实现），单曲只能走 IOT 语音搜索通道，因此必须用状态机防竞态。

```
IDLE ──playOne──> REQUESTING ──指令下发成功──> CONFIRMING ──歌名对上──> MONITORING ──版本变化/播完──> TAKEOVER
                        │                        │                              │
                        └─失败/5s 超时───────────┴─15s 超时──────────────┘
                                                  （回 IDLE）
```

| 状态 | 进入条件 | 退出条件 | 超时 |
|---|---|---|---|
| `IDLE` | 初始 / 任意失败 | 收到 playOne / playTracks | — |
| `REQUESTING` | `playOne` 分配新 reqId | 指令下发成功 → CONFIRMING；失败 → IDLE | 5s |
| `CONFIRMING` | 语音指令已发出 | metadata 歌名匹配 → MONITORING | 15s |
| `MONITORING` | 已确认起播，记录初始 mediaId | mediaId 变 / 歌名变 / 自然播完 → TAKEOVER | — |
| `TAKEOVER` | 点播队列下一首 | 点播完成 → REQUESTING | 3s 兜底 |

关键防竞态设计：

- **旧状态残留**：REQUESTING 阶段完全不看 metadata，避免把上一首的 mediaId 当成新目标。
- **状态更新延迟**：确认只看歌名，不看 artist（合唱拼接会永远对不上）；版本由 mediaId 判定。
- **事件重复**：所有迁移走 `poTo()`，同一把锁内改状态；`reqId` 不符直接丢弃。
- **接管后回推**：TAKEOVER 期间 `poMetadata` 直接返回，物理上不可能二次接管。
- **超时不依赖回调**：`Server.Tick` 每秒调一次 `poTick()`，一条回调都不来也不会卡死在 CONFIRMING。
- **手动停止不误判**：`STOPPED` 必须同时满足「上一状态是 PLAYING」「连续播放 ≥30s」「过程中没暂停过」才认定自然播完。

歌名匹配做了括号归一（`唐僧骑马` ↔ `唐僧骑马 (唐僧骑马咚那个咚)`），否则儿歌全都会确认超时。

## 3. 歌单与队列

歌单来源三档，顺序即网页显示顺序：

1. 内置自建歌单：`Cfg.DEFAULT_PLAYLISTS`（《Borderline 或许》《嘟嘟妙妙崽崽滴》）
2. 官方榜：`Cfg.OFFICIAL_PLAYLISTS`（热歌 / 飙升 / 网易云民谣 / 网易云国风，id 固定、官方每天更新）
3. 设备配置：`/sdcard/Android/data/com.luna.music.car/files/playlists.txt`，一行 `歌单名|公开页 id`，可只写名字（运行时搜 id 并写回）

`Cfg.TRACK_LIMIT = 60`：网页列表与随机队列取同一份截断数据（榜单前 60 名再洗牌），避免两边条数不一致，也避免随机池过大点到冷门歌。

### 换成自己的歌单

内置的两个自建歌单只是示例，换成自己的歌单不用改代码：

1. 在网易云里打开歌单 → 分享 → 复制链接，形如 `https://music.163.com/playlist?id=723825351` 或 `https://163cn.tv/xxxx`
2. 取其中的数字 id：上面这个就是 `723825351`
3. 写进车机上的 `playlists.txt`，一行一个：

   ```
   我的歌单|723825351
   ```

   路径：`/sdcard/Android/data/com.luna.music.car/files/playlists.txt`（文件不存在就自己新建）
4. 回网页点一次刷新即可

也可以直接改 `Cfg.DEFAULT_PLAYLISTS` 重新打包，内置进 APK。只支持**公开**歌单（分享出来的那种），私密歌单抓不到。

「随机播放全部」是**假队列**：抓歌单 → 洗牌 → 逐首语音点播 → 播完自动接下一首。

> 为什么不用 `orpheus://playlist_play`：该 scheme 只存在于网易云主 App，车机版没有对应页面，`sendCustomAction` 永远只返回成功、不会切歌。

## 4. 日志

默认**关闭**（正式使用不记录点播细节）。需要排查时在网页里手动打开「日志采样」，状态写入 `SharedPreferences`，重启后保持。

不受开关影响的 `logAlways`：App 启动、端口判定、服务前台、HTTP/WS 启停、崩溃栈——保证最低可观测性。

点播链路每行都带可还原时序的字段：

```
PO req=3 IDLE→REQUESTING song=晴天 artist=周杰伦 list=Borderline 或许 tid=4211
PO req=3 REQUESTING→CONFIRMING 指令已发出，等官方起播 mid= tid=4211
PO req=3 CONFIRMING→MONITORING mid=001abc song=晴天 tid=4233
PO req=3 MONITORING→TAKEOVER 检测到版本切换 list=Borderline 或许 tid=4233
QUEUE 就绪 name=Borderline 或许 n=37
QUEUE 12/37 点播 某歌 - 某歌手
```

`uncaughtExceptionHandler` 抓不到 ANR / Native Crash / 系统杀进程，这类现场靠的就是崩溃前最后几条主动状态日志。

## 5. 构建

```bash
./gradlew :lunastub:assembleRelease
```

- 签名复用 `keystore.properties`（与主应用同一套，方便一次 `adb install` 多个包）
- `assets/remote.html` 是遥控网页，改完需重新打包；运行时由 `Server` 直接读 assets 提供
- 手工打包流程（无 Gradle 环境时）：`javac -source 8 -target 8` → `d8 --min-api 28` → `aapt2 link -A assets` → `jar uf classes.dex` → `zipalign -4` → `apksigner`

## 6. 已知限制

- **「我喜欢的音乐」只能整单播放**：真实收藏内容存在官方登录态里，抓不到列表；并且点它时官方 `VoiceService` 会主动给车机发一条 directive，车机助手必然播报「好的」，这一点无法从本包消除。
- **语音搜索可能返回相似歌名**（`黄昏` → `黄昏泛舟`），状态机只能超时放弃，无法纠正搜索结果。
- **同名不同版本的歌**：队列只存歌名+歌手，语音通道可能点错版本。
