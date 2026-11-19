package com.cloudmusic.car.compat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.media3.common.Player
import com.cloudmusic.car.api.LyricsParser
import com.cloudmusic.car.api.sized
import com.cloudmusic.car.data.AccountStore
import com.cloudmusic.car.player.PlayerHub
import com.paopao.music.nowplaying.PlayModes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 车机助手（`com.navi.tool`）「汽水音乐」通道的主动适配。
 *
 * ## 为什么需要它
 *
 * 车机助手对包名有一张写死的音源表，认不出来的包一律当「通用音源」。通用音源**不读
 * MediaSession 的 metadata**，而是拿「歌名 空格 歌手」去 `search.kuwo.cn` 搜第一条歌词
 * （`pn=0&rn=1`），封面再按名字搜一次图。所以每播一首歌都要联网搜两轮，搜到同名不同版本
 * 就显示别人的词——这是歌词会错的根因，也是白花流量的来源。
 *
 * 它的「汽水音乐」通道（`k4/a` + `LunaMusicLyricReceiver`）反而是三条专用通道里最开放的：
 * 纯广播、字符串传参、**没有签名校验也没有包名校验**，谁发都收。而且它本身支持逐字歌词。
 *
 * 所以只要让它走这条通道，歌词/封面/时长就全部直连，一次搜索都不会发生。
 *
 * ## 前提
 *
 * 车机助手的音源选择列表只列「已安装」的包，`com.luna.music.car` 要出现在列表里才能选中。
 * 见 `docs/车机助手适配.md` 里对包名的处理。
 *
 * ## 协议（对照 `LunaMusicLyricReceiver` / `k4/a` 反编译确认）
 *
 * 车机助手 → 本应用
 * - `com.luna.music.car.PLAYER_SYNC`                 无参数，请求立刻回一次完整状态
 * - `com.luna.music.car.PLAYER_CONTROL`              `controlType`：
 *                                                    0 播放/暂停、1 上一首、2 下一首、3 收藏、4 取消收藏
 *
 * 本应用 → 车机助手
 * - `com.luna.music.car.PLAYER_STATE`                title / artist / coverUrl / duration(long) /
 *                                                    position(long) / isPlaying(bool) /
 *                                                    isCollected(bool) / loopMode(LIST|SINGLE|SHUFFLE)
 * - `com.luna.music.car.LYRIC_LINE`                  播放中每秒一次，同上但**不带** isPlaying。
 *                                                    助手在这条里才会同时校准位置锚点 q 和时刻锚点 r，
 *                                                    只发 STATE 会让歌词随播放时长越飘越远。
 * - `com.luna.music.car.LYRIC_FULL`                  title / artist / coverUrl / lrc
 *
 * 注意 `LYRIC_FULL` 也必须带 title/artist，车机助手收到歌词时会顺手解析这三个字段来判断是不是
 * 换了歌（`LunaMusicLyricReceiver.d()`），只发 `lrc` 会被当成空歌曲丢掉。
 *
 * ## 助手内部的时间轴（这是整件事的关键）
 *
 * 助手不直接用我们报的 position，而是维护两个锚点，按 `q + (now - r)` 插值：
 *
 * ```
 * a() = t ? min(q + (elapsedRealtime - r), duration) : q
 * ```
 *
 * `t` 是它自己的播放态。三个锚点各由谁写：
 *
 * | 位置 | 什么时候写 |
 * |---|---|
 * | `r` | `e()` 里只有 `isPlaying` 从 false 变 true 的那一瞬间写；LYRIC_LINE 分支写 |
 * | `q` | PLAYER_STATE / LYRIC_LINE / LYRIC_FULL 分支都会写（有 `now - k > 3000` 节流） |
 * | `k` | 只在换歌时写（`d()`），用来做换歌后 3 秒的校准保护 |
 *
 * **只发 `PLAYER_STATE` 会出事**：它写 `q` 但不写 `r`，于是每次上报都让 `a()` 变成
 * `position + (now - r旧)`，r 越老偏得越远，歌词整段跑到声音前面，而 App 内一直正常。
 * 这就是"唱着唱着套不上"的原因。
 *
 * `LYRIC_LINE` 分支是唯一把 `q` 和 `r` **成对**写的入口，所以播放中只能发它。
 *
 * ## 两条不可绕过的硬约束
 *
 * 1. **心跳必须快于 1.8 秒**。`e()` 每次 `postDelayed(x, 1800)`，到点还没新广播就把 `t`
 *    置 false 并刷 UI（`y0/p`）。界面显示暂停、声音照常。
 * 2. **不能"只喂心跳不报位置"**。不传 `position` 时 `getLongExtra` 返回 -1，代码会退到
 *    `getIntExtra(..., 0)` 拿到 0，直接把 `q` 写成 0。所以每条心跳都必须带真实 position。
 *
 * 好在 `q`/`r` 是成对写的，插值本身不累积误差，每秒一条的误差只有广播延迟（毫秒级）。
 *
 * ## 暂停时不用发
 *
 * 看门狗第一句是 `if (!t) return`，暂停时它什么都不做。所以暂停期间只在状态翻转、或用户
 * 拖了进度条时补一条 `PLAYER_STATE` 就够（那里 `e()` 会把 `q = a()` 固化、`r = 0`）。
 */
object LunaCompat {

    private const val TAG = "LunaCompat"

    /** 车机助手的包名。广播定向投递用。 */
    private const val NAVI_PACKAGE = "com.navi.tool"

    private const val ACTION_STATE = "com.luna.music.car.PLAYER_STATE"
    private const val ACTION_LYRIC_FULL = "com.luna.music.car.LYRIC_FULL"
    private const val ACTION_LYRIC_LINE = "com.luna.music.car.LYRIC_LINE"
    private const val ACTION_CONTROL = "com.luna.music.car.PLAYER_CONTROL"
    private const val ACTION_SYNC = "com.luna.music.car.PLAYER_SYNC"

    private const val EXTRA_CONTROL = "controlType"
    private const val CONTROL_TOGGLE = 0
    private const val CONTROL_PREV = 1
    private const val CONTROL_NEXT = 2
    private const val CONTROL_LIKE = 3
    private const val CONTROL_UNLIKE = 4

    /** 上报间隔。必须 < 助手看门狗的 1800ms，见类注释。 */
    private const val TICK_MS = 1_000L

    private var appContext: Context? = null
    private var scope: CoroutineScope? = null
    private var ticker: Job? = null
    private var registered = false

    /** 上一次推送的歌曲指纹，用来判断要不要补发一次歌词。 */
    private var lastLyricKey: String? = null

    private var lastTrackId: Long? = null

    /** 上一次的播放态，用来判断要不要发「暂停」那一条。 */
    private var lastPlaying = false

    /** 暂停期间的位置，用来发现用户拖动进度条。 */
    private var pausedPosition = -1L

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION_SYNC -> {
                    pushState()
                    pushLyric(force = true)
                }

                ACTION_CONTROL -> {
                    val type = intent.getIntExtra(EXTRA_CONTROL, -1)
                    // 方向盘 / 桌面卡片若走的是助手的 PLAYER_CONTROL 广播，这里能一眼看到有没有送达。
                    // 系统媒体按键（KEYCODE_MEDIA_NEXT/PREVIOUS）不经这里，直接到 MediaSession。
                    Log.i(TAG, "收到播控 controlType=$type")
                    when (type) {
                        CONTROL_TOGGLE -> PlayerHub.togglePlay()
                        CONTROL_PREV -> PlayerHub.previous()
                        CONTROL_NEXT -> PlayerHub.next()
                        CONTROL_LIKE -> setLiked(true)
                        CONTROL_UNLIKE -> setLiked(false)
                    }
                }

                else -> Unit
            }
        }
    }

    fun start(context: Context) {
        if (registered) return
        val app = context.applicationContext
        appContext = app

        // 重新挂载时清掉上一轮的记忆，保证第一个 tick 就会推一次完整状态。
        lastTrackId = null
        lastLyricKey = null
        lastPlaying = false
        pausedPosition = -1L

        val filter = IntentFilter().apply {
            addAction(ACTION_SYNC)
            addAction(ACTION_CONTROL)
        }
        runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                app.registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED)
            } else {
                @Suppress("UnspecifiedRegisterReceiverFlag")
                app.registerReceiver(receiver, filter)
            }
            registered = true
            Log.i(TAG, "车机助手通道已挂载")
        }.onFailure { Log.w(TAG, "注册广播失败", it) }

        scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        ticker = scope?.launch {
            while (true) {
                delay(TICK_MS)

                val id = PlayerHub.current.value?.id
                val playing = isTrulyPlaying()

                if (id != lastTrackId) {
                    // 换歌：先把状态推过去，再把歌词清空。
                    // 此刻 PlayerHub 已经清掉了 _lyrics，所以发出去的是空 lrc，
                    // 车机助手会清表——正好避免上一首的歌词挂着。歌词到位后再补一次。
                    //
                    // 换歌后助手会把锚点清零，并在 3 秒内拒绝任何位置校准（见 `d()` 里的
                    // `now - k > 3000` 节流）。这 3 秒里 a() 返回 0，也就是"位置停在开头"，
                    // 正好对上 LRC 第一行通常在前奏之后的实际情况，不用管它。
                    lastTrackId = id
                    lastPlaying = playing
                    // 播放中清掉暂停基准；暂停中记下当前值，避免下面重复发一条。
                    pausedPosition = if (playing) -1L else currentPosition()
                    pushState()
                    pushLyric(force = true)
                    continue
                }

                if (!playing) {
                    // 暂停时助手的看门狗直接 return（`if (!t) return`），不会自己判暂停，
                    // 所以这里不需要心跳。只在状态刚翻转、或用户拖了进度条时补一条，
                    // 免得助手还挂着暂停前的旧位置。
                    val pos = currentPosition()
                    if (lastPlaying || (pausedPosition >= 0 && kotlin.math.abs(pos - pausedPosition) > 1_000L)) {
                        lastPlaying = false
                        pausedPosition = pos
                        pushState()
                    }
                    // 歌词可能异步到位，仍然补一次（内部按 key 判重，不会重复发）。
                    pushLyric(force = false)
                    continue
                }

                lastPlaying = true
                pausedPosition = -1L

                // 播放中每秒一条 LYRIC_LINE：它同时写助手的两个锚点（q = position，r = now），
                // 顺带把 1.8 秒看门狗喂了。只发 PLAYER_STATE 会让 q 变而 r 不变，越唱越飘。
                pushLine()

                // 歌词是异步拉回来的，切歌后 0 → N 行会变一次，靠 key 判重自动补发。
                pushLyric(force = false)
            }
        }
    }

    fun stop() {
        ticker?.cancel()
        ticker = null
        scope?.cancel()
        scope = null
        val app = appContext
        if (registered && app != null) {
            runCatching { app.unregisterReceiver(receiver) }
        }
        registered = false
        appContext = null
        lastLyricKey = null
    }

    // ---------- 推送 ----------

    private fun pushState() {
        val ctx = appContext ?: return
        val track = PlayerHub.current.value ?: return
        val intent = baseIntent(ACTION_STATE, track).apply {
            putExtra("duration", track.durationMs)
            putExtra("position", currentPosition())
            putExtra("isPlaying", isTrulyPlaying())
            // 助手的爱心图标只认这个字段，不发就一直灭着（它是 v 字段，缺省 false）。
            putExtra("isCollected", track.id in AccountStore.likedIds.value)
            putExtra("loopMode", loopMode())
        }
        send(ctx, intent)
    }

    /**
     * 播放中的每秒心跳，带 position。
     *
     * ## 为什么不能只发 PLAYER_STATE
     *
     * 助手内部维护一对锚点 `q`（位置）和 `r`（校准时刻），当前时间轴是 `q + (now - r)`。
     * 反编译对照两条分支：
     * - `PLAYER_STATE` 分支只写 `q = position`，**不写 `r`**（`sput-wide v5, q` 之后直接跳走）；
     * - `LYRIC_LINE` 分支两条都写：`q = position; r = now`。
     *
     * 所以只发 STATE 时，`r` 一直停在换歌那一刻，`now - r` 越跑越大，助手算出来的位置比实际
     * 越来越超前——歌词一开始对，唱几分钟就整体跑到声音前面去，而且 App 内是好的（应用内直接用
     * `player.currentPosition`）。这就是"唱着唱着套不上"的原因。
     *
     * `LYRIC_LINE` 的另一层好处：它的 `e(intent, true)` 是硬编码 true，顺带把 1.8 秒看门狗喂了。
     * 代价是它不看 isPlaying，所以只在真正播放时发，暂停仍然走 PLAYER_STATE。
     */
    private fun pushLine() {
        val ctx = appContext ?: return
        val track = PlayerHub.current.value ?: return
        val intent = baseIntent(ACTION_LYRIC_LINE, track).apply {
            putExtra("duration", track.durationMs)
            putExtra("position", currentPosition())
            putExtra("isCollected", track.id in AccountStore.likedIds.value)
            putExtra("loopMode", loopMode())
            // 不传 currentLine：助手会自己用校准后的位置在歌词表里 floorEntry 取行。
        }
        send(ctx, intent)
    }

    /**
     * 助手的收藏按钮：`controlType` 3 = 设为已收藏、4 = 设为未收藏
     * （`DesktopAssistantMusicUiHelper` 里 source==5 分支：3 原样下发、4 走 `v3=4`）。
     * 这里按目标状态对齐，避免出现"点一下反而取消"的翻转错位。
     */
    private fun setLiked(like: Boolean) {
        val track = PlayerHub.current.value ?: return
        val currentlyLiked = track.id in AccountStore.likedIds.value
        if (currentlyLiked == like) {
            // 状态本来就对，仍然补推一次，让助手图标立刻跟上
            pushState()
            return
        }
        AccountStore.toggleLike(track) { message ->
            if (message.isNotBlank()) PlayerHub.toast(message)
        }
        pushState()
    }

    private fun pushLyric(force: Boolean) {
        val ctx = appContext ?: return
        val track = PlayerHub.current.value ?: return
        val key = "${track.id}|${PlayerHub.lyrics.value.size}"
        if (!force && key == lastLyricKey) return
        lastLyricKey = key

        val lrc = LyricsParser.toLrc(PlayerHub.lyrics.value)
        val intent = baseIntent(ACTION_LYRIC_FULL, track).apply {
            putExtra("lrc", lrc)
        }
        send(ctx, intent)
    }

    /**
     * title / artist / coverUrl 三个字段，
     * 车机助手 `LunaMusicLyricReceiver.d()` 会读，两条广播都要带。
     */
    private fun baseIntent(action: String, track: com.cloudmusic.car.api.Track): Intent =
        Intent(action).apply {
            putExtra("title", track.name)
            putExtra("artist", track.artistNames)
            putExtra("coverUrl", track.album.picUrl.sized(512) ?: "")
        }

    private fun send(context: Context, intent: Intent) {
        // 只投给车机助手，不惊动其它注册了同名 action 的进程。
        intent.setPackage(NAVI_PACKAGE)
        runCatching { context.sendBroadcast(intent) }
            .onFailure { Log.w(TAG, "发送失败: ${intent.action}", it) }
    }

    /**
     * 真正在出声的判定。
     *
     * 不能用 `PlayerHub.isPlaying`：ExoPlayer 的 `isPlaying` 在 buffering 时会变成 false，
     * 一缓冲就被当成暂停发出去，助手那边会把锚点清掉、图标闪一下暂停。网络差的时候会一直闪。
     *
     * 这里用「打算播 && 不在 IDLE/ENDED」，缓冲期间仍然算在播，位置照常上报。
     */
    private fun isTrulyPlaying(): Boolean = runCatching {
        val p = PlayerHub.player
        p.playWhenReady &&
            p.playbackState != Player.STATE_IDLE &&
            p.playbackState != Player.STATE_ENDED
    }.getOrDefault(PlayerHub.isPlaying.value)

    private fun currentPosition(): Long = runCatching {
        PlayerHub.player.currentPosition.coerceAtLeast(0L)
    }.getOrDefault(0L)

    private fun loopMode(): String = when (PlayerHub.playMode.value) {
        PlayModes.REPEAT_ONE -> "SINGLE"
        PlayModes.SHUFFLE -> "SHUFFLE"
        else -> "LIST"
    }
}
