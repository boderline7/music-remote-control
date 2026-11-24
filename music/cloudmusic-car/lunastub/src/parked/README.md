# parked —— 已剥离的官方桥接代码

这个目录**不参与编译**（不在 `src/main/java` 下），内容不进 APK，也不会被任何启动链路引用。
留着是为了以后要恢复「操作官方网易云」的能力时，整组拷回去即可。

## 文件

| 文件 | 作用 |
|---|---|
| `Ncm.java` | MediaBrowser 连官方 `com.netease.cloudmusic.iot`，播控 / 歌单 / 歌词 / 点播状态机 / 爱心 |
| `Bridge.java` | 接车机助手（`com.navi.tool`）的 `PLAYER_CONTROL` / `PLAYER_SYNC`，翻译成助手卡片状态 |
| `Keys.java` | 方控收口 MediaSession：官方在播时让位，否则接管按键转成 `Ncm.execute` |
| `Star.java` | 爱心状态（网页与助手卡片共用） |
| `Liked.java` | 拉「我喜欢的音乐」公开歌单，判断当前曲目是否已收藏 |
| `MusicCfg.java` | 官方包名 / 服务名 / 公开 API / 歌单 id / metadata key，以及 `Lrc` 解析 |
| `Mode.java` | 音源检测（官方 / 第三方 / 都没有）与选择持久化 |
| `ModeSync.java` | 与第三方播放器互发 `MODE_SYNC`，让它按模式启停服务 |
| `Svc.java` | 18764 常驻服务（含 `Nsd` / `RegListener`，`Ip` 已拆到主源码） |
| `Server.java` | 18764 的 WebSocket / HTTP 外壳（`ServerIo` 已拆到主源码） |
| `Api.java` | `/api/*` 路由（状态、模式切换、日志、诊断） |
| `Alive.java` | 15 分钟保活心跳 |
| `LaunchStub.java` | 早期占位入口，已停用 |

## 怎么接回

1. 把这 13 个文件拷回 `src/main/java/com/luna/music/car/`（`MusicCfg.java` 一并拷，`Ncm/Bridge/Liked` 里引用的常量都在它里面）。
2. `AndroidManifest.xml` 补回服务与接收器：

   ```xml
   <service android:name=".Svc" android:exported="false"
            android:foregroundServiceType="mediaPlayback" />

   <receiver android:name=".ModeSync$Receiver" android:exported="true">
       <intent-filter>
           <action android:name="com.cloudmusic.car.remote.MODE_SYNC" />
       </intent-filter>
   </receiver>
   ```

   `queries` 里按需补 `com.netease.cloudmusic.iot` / `com.cloudmusic.car`。

3. `App.onCreate` 恢复：`Svc.start(this)`、`ModeSync.notify(this, Mode.current(this))`、`if (Svc.autoAllowed(this)) Alive.schedule(this)`。
4. `Boot` 恢复：开机分支里 `Svc.start(context)`、`ModeSync.notify(...)`、`Alive.schedule(...)`；心跳分支 `Svc.start(context)`。
5. 设置页 `Main` 的按钮与状态文案，可从提交历史里取回（本目录剥离前的版本）。
6. 编译前确认主源码里没有同名类冲突：`Ip.java` 与 `ServerIo.java` 是剥离后拆出来的公共文件，parked 里的 `Svc` / `Server` 已经不再自带这两个类。

整组一起拷，别单拷某一个：`Ncm` 依赖 `MusicCfg` / `Star` / `Liked`，`Bridge` 依赖 `Ncm` / `Keys` / `Mode`。
