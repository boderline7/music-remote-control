package com.cloudmusic.car.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue

/**
 * 外部拉起内部页面的请求位。
 *
 * 歌词大屏（[com.cloudmusic.car.ui.screens.NowPlayingScreen]）是 Compose 里的一层浮层，
 * 由 [Nav.nowPlayingOpen] 控制，没有独立 Activity，本来没有外部入口。车机助手是另一个进程，
 * 拿不到这里的 Nav，所以只能靠一次跨进程的「请求」：
 *
 * ```
 * 车机助手 Intent(com.cloudmusic.car.OPEN_LYRICS) → MainActivity.onNewIntent
 *   → UiIntent.requestLyrics()  → seq+1
 *   → AppRoot 的 LaunchedEffect 看到 seq 变了 → nav.nowPlayingOpen = true
 * ```
 *
 * 用「自增序号 + 已消费序号」而不是布尔位，是为了连续点两次也能各自生效：布尔位在第一次
 * 被消费后归位，第二次点击若和消费落在同一帧就丢了。序号天然幂等，重进 App 也不会残留。
 */
object UiIntent {

    /** 外部拉起歌词大屏。清单里同名 intent-filter 与 [com.cloudmusic.car.MainActivity] 都认这个 action。 */
    const val ACTION_OPEN_LYRICS = "com.cloudmusic.car.OPEN_LYRICS"

    /** 累计请求次数。 */
    var lyricsSeq by mutableLongStateOf(0L)
        private set

    /** AppRoot 已经消费到的次数。 */
    var consumedLyricsSeq by mutableLongStateOf(0L)
        private set

    fun requestLyrics() {
        lyricsSeq++
    }

    fun consumeLyrics(seq: Long) {
        if (seq > consumedLyricsSeq) consumedLyricsSeq = seq
    }
}
