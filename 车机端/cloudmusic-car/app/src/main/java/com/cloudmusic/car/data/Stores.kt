package com.cloudmusic.car.data

import android.content.Context
import android.content.SharedPreferences
import com.cloudmusic.car.api.NeteaseApi
import com.cloudmusic.car.api.NeteaseClient
import com.cloudmusic.car.api.Playlist
import com.cloudmusic.car.api.Profile
import com.cloudmusic.car.api.Track
import com.cloudmusic.car.player.PlayerHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class AudioQuality(val level: String, val label: String, val detail: String) {
    STANDARD("standard", "标准", "128k，最省流量"),
    EXHIGH("exhigh", "极高", "320k，推荐"),
    LOSSLESS("lossless", "无损", "FLAC，需 VIP"),
    HIRES("hires", "Hi-Res", "高解析，需 SVIP"),
}

object Settings {
    /** 离线缓存预算的可选档位（GB）。给三档就够，再多是让用户纠结。 */
    val CACHE_GB_OPTIONS = listOf(2, 5, 10)
    const val DEFAULT_CACHE_GB = 5
    const val MIN_CACHE_GB = 2
    const val MAX_CACHE_GB = 10

    private lateinit var prefs: SharedPreferences
    private val _quality = MutableStateFlow(AudioQuality.EXHIGH)
    val quality: StateFlow<AudioQuality> = _quality.asStateFlow()

    /** 手机遥控开关，持久化：车机重启后按上次状态自动恢复服务。默认开（装完就能连）。 */
    private val _remoteEnabled = MutableStateFlow(true)
    val remoteEnabled: StateFlow<Boolean> = _remoteEnabled.asStateFlow()

    /** 全屏显示：隐藏状态栏与导航栏，持久化。关掉就保持系统默认显示。 */
    private val _fullscreen = MutableStateFlow(false)
    val fullscreen: StateFlow<Boolean> = _fullscreen.asStateFlow()

    /**
     * 歌曲 + 歌词的离线缓存总预算（GB）。默认 5 GB。
     *
     * 拆成「音乐 9 份、歌词 1 份」用：一首无损 FLAC 三四十兆，歌词一份才几 KB，
     * 按同一个数字给两边会浪费；按 9:1 分，5 GB 预算下歌词能留 512 MB（约 4000 首），
     * 实际很难压到上限。
     *
     * 改这个值**下次启动才生效**：音频缓存是 Media3 的 `SimpleCache`，
     * 上限在创建时固定，换它必须整个释放重建 —— 播放中重建会让正在放的歌断掉，
     * 不值得为了立刻生效去冒这个险。歌词上限是逻辑删除，改完立即生效。
     */
    private val _cacheBudgetGb = MutableStateFlow(DEFAULT_CACHE_GB)
    val cacheBudgetGb: StateFlow<Int> = _cacheBudgetGb.asStateFlow()

    fun init(context: Context) {
        prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        _quality.value = AudioQuality.entries.firstOrNull { it.level == prefs.getString("quality", null) }
            ?: AudioQuality.EXHIGH
        _remoteEnabled.value = prefs.getBoolean("remote_enabled", true)
        _fullscreen.value = prefs.getBoolean("fullscreen", false)
        _cacheBudgetGb.value = prefs.getInt("cache_budget_gb", DEFAULT_CACHE_GB)
            .coerceIn(MIN_CACHE_GB, MAX_CACHE_GB)
    }

    fun setQuality(q: AudioQuality) {
        _quality.value = q
        prefs.edit().putString("quality", q.level).apply()
    }

    fun setRemoteEnabled(enabled: Boolean) {
        _remoteEnabled.value = enabled
        prefs.edit().putBoolean("remote_enabled", enabled).apply()
    }

    fun setFullscreen(enabled: Boolean) {
        _fullscreen.value = enabled
        prefs.edit().putBoolean("fullscreen", enabled).apply()
    }

    /** 只写偏好；音频那半要等下次启动由 [MusicCache.init] 读走才真的生效。 */
    fun setCacheBudgetGb(gb: Int) {
        val v = gb.coerceIn(MIN_CACHE_GB, MAX_CACHE_GB)
        _cacheBudgetGb.value = v
        prefs.edit().putInt("cache_budget_gb", v).apply()
    }
}

/** 登录状态、用户信息、红心列表与歌单。 */
object AccountStore {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _loggedIn = MutableStateFlow(false)
    val loggedIn: StateFlow<Boolean> = _loggedIn.asStateFlow()

    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: StateFlow<Profile?> = _profile.asStateFlow()

    private val _likedIds = MutableStateFlow<Set<Long>>(emptySet())
    val likedIds: StateFlow<Set<Long>> = _likedIds.asStateFlow()

    private val _playlists = MutableStateFlow<List<Playlist>>(emptyList())
    val playlists: StateFlow<List<Playlist>> = _playlists.asStateFlow()

    val likedPlaylist: Playlist? get() = _playlists.value.firstOrNull { it.isLikedSongs }
    val vipType: Int get() = _profile.value?.vipType ?: 0

    fun init() {
        _loggedIn.value = NeteaseClient.isLoggedIn
        NeteaseClient.onSessionExpired = {
            scope.launch {
                clearAccount()
                PlayerHub.toast("网易云登录已过期，请重新扫码登录")
            }
        }
        if (_loggedIn.value) {
            scope.launch {
                NeteaseClient.refreshIfDue()
                refresh()
            }
        }
        // 进程常驻多日：每 10 分钟看一眼是否满 12 小时（休眠时 delay 不走，所以取地址前还会再查一次）
        scope.launch {
            while (true) {
                delay(10 * 60 * 1000L)
                NeteaseClient.refreshIfDue()
            }
        }
    }

    fun onLoginSucceeded() {
        NeteaseClient.markLoginFresh()
        _loggedIn.value = NeteaseClient.isLoggedIn
        scope.launch { refresh() }
    }

    private fun clearAccount() {
        _profile.value = null
        _playlists.value = emptyList()
        _likedIds.value = emptySet()
        _loggedIn.value = false
    }

    suspend fun refresh() {
        val profile = runCatching { NeteaseApi.userAccount() }.getOrNull() ?: return
        _profile.value = profile
        runCatching { NeteaseApi.userPlaylists(profile.userId) }.onSuccess { _playlists.value = it }
        runCatching { NeteaseApi.likedTrackIds(profile.userId) }.onSuccess { _likedIds.value = it }
    }

    fun logout() {
        scope.launch {
            NeteaseApi.logout()
            clearAccount()
        }
    }

    fun unplayableReason(track: Track): String? = track.unplayableReason(_loggedIn.value, vipType)

    /** 切换红心；先乐观更新，失败回滚。 */
    fun toggleLike(track: Track, onError: (String) -> Unit) {
        val like = track.id !in _likedIds.value
        _likedIds.update { if (like) it + track.id else it - track.id }
        scope.launch {
            runCatching { NeteaseApi.likeTrack(track.id, like) }.onFailure {
                _likedIds.update { ids -> if (like) ids - track.id else ids + track.id }
                onError(it.message ?: "操作失败")
            }
        }
    }
}
