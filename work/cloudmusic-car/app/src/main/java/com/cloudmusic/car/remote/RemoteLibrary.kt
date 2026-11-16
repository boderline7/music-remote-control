package com.cloudmusic.car.remote

import android.util.Log
import com.cloudmusic.car.api.NeteaseApi
import com.cloudmusic.car.api.Playlist
import com.cloudmusic.car.api.Track
import com.cloudmusic.car.data.AccountStore
import com.cloudmusic.car.player.PlayMode
import com.cloudmusic.car.player.PlayerHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.ConcurrentHashMap

/**
 * 手机端的资料库：我喜欢的音乐、音乐云盘、我创建的歌单，再加上排行榜、推荐歌单和搜索。
 *
 * 走的是车机已登录的账号（AccountStore），手机端不需要自己登录网易云。
 * 拉取放在 IO 线程；播放必须回主线程，ExoPlayer 只能在主线程碰。
 *
 * 列表按页返回（[PAGE_SIZE] 首），因为单帧上限 8 KB，整张歌单塞不进一帧。
 * 列表 id 用前缀区分来源：`liked` / `cloud` / 数字歌单 id / `top:<id>` / `rec:<id>` / `search:<关键词>`
 * / `daily` / `heartbeat`，手机端原样回传即可，不用自己拼参数。
 */
object RemoteLibrary {

    private const val TAG = RemoteConfig.TAG

    /**
     * 一页最多 40 首（v2.2 从 20 提到 40：手机上一屏大约 7 行，20 首要划两次才够看）。
     *
     * 这是上限，实际还会按序列化后的字节数再裁一次（见 [pageJson]）——
     * 中文歌名长度差别很大，40 个长名字塞不进一帧 8 KB，只能按字节兜底。
     * 常见长度（歌名 + 歌手 20 来字）下就是整整 40 首。
     */
    const val PAGE_SIZE = 40

    /**
     * 一页 JSON 的字节预算。单帧上限 8192，留 1 KB 给 type/id/外层信封字段。
     * 超过这个预算就少放几首 —— 宁可少几首，也不能整帧被发送层丢掉（那会变成永远转圈）。
     */
    private const val PAGE_BUDGET = 7000

    private const val SEARCH_PREFIX = "search:"
    private const val TOP_PREFIX = "top:"
    private const val REC_PREFIX = "rec:"

    /**
     * 两个「不是歌单、但也要当成列表打开」的入口。
     *
     * 每日推荐和心动模式在车机上都是动态生成的队列，没有网易云的歌单 id，
     * 但它们的用法和歌单完全一样（先看列表、再点某一首），所以挂成虚拟 id 走 [tracks] / [play]，
     * 手机端和网页端一个字都不用改，原样回传即可。
     */
    private const val DAILY_ID = "daily"
    private const val HEARTBEAT_ID = "heartbeat"

    // ---------- 请求去重与缓存 ----------

    /**
     * 缓存 TTL。列表 3 分钟：翻页、回头再点同一张歌单都不该重新拉；
     * library/catalog 这种聚合页 15 秒：既挡连点，又不会让「刚加的歌单」看不见。
     */
    private const val LIST_TTL_MS = 3 * 60_000L
    private const val AGG_TTL_MS = 15_000L
    private const val SEARCH_TTL_MS = 10_000L

    /**
     * 每份缓存的字节预算。份数上限挡不住内存 —— 一份「我喜欢」可能两千首、
     * 另一份只有二十首，光数份数会让内存随用户歌单规模飘。所以再加一道字节闸门：
     * 总量 = min(份数 × 512 KB, 8 MB)，超了就从最久没用的开始回收。
     */
    private const val CACHE_BYTES_PER_ENTRY = 512 * 1024L
    private const val CACHE_BYTES_CAP = 8 * 1024 * 1024L

    private class Entry(val value: Any, val at: Long, val weight: Int)

    /** 缓存状态快照，设置页显示「N 份 / 约 X MB」用。 */
    data class CacheStats(val entries: Int, val bytes: Int, val limit: Int)

    /** 访问顺序的 LRU：`get`/`put` 都会把 key 移到队尾，队首就是最久没用的。 */
    private val cache = LinkedHashMap<String, Entry>(8, 0.75f, true)
    private val cacheLock = Any()
    private var cachedBytes = 0
    private val keyLocks = ConcurrentHashMap<String, Mutex>()

    private fun peek(key: String, ttlMs: Long): Any? = synchronized(cacheLock) {
        cache[key]?.takeIf { System.currentTimeMillis() - it.at < ttlMs }?.value
    }

    fun stats(): CacheStats = synchronized(cacheLock) {
        CacheStats(cache.size, cachedBytes, RemotePrefs.currentCacheLimit)
    }

    /**
     * 用户把上限调小、或点了「清空缓存」时调用。
     *
     * 调小不立刻回收的话，界面上会显示「12 份 / 上限 6」，而实际要等到下次拉列表才瘦下来。
     */
    fun trim() {
        synchronized(cacheLock) { evictLocked(keep = null) }
    }

    fun clearCache() {
        synchronized(cacheLock) {
            cache.clear()
            cachedBytes = 0
        }
    }

    /**
     * 按「份数 + 字节」双闸门回收，直到双双回到预算内。
     *
     * [keep] 是刚放进来的 key：哪怕它一个人就超了预算也留着，
     * 否则用户会陷入「刚拉完就被回收、翻页又重拉」的循环。
     */
    private fun evictLocked(keep: String?) {
        val limit = RemotePrefs.currentCacheLimit
            .coerceIn(RemotePrefs.MIN_CACHE, RemotePrefs.MAX_CACHE)
        val budget = minOf(limit * CACHE_BYTES_PER_ENTRY, CACHE_BYTES_CAP)
        while ((cache.size > limit || cachedBytes > budget) && cache.size > 1) {
            val eldest = cache.entries.firstOrNull { it.key != keep }?.key ?: break
            val removed = cache.remove(eldest) ?: break
            cachedBytes -= removed.weight
            Log.i(TAG, "cache evict $eldest (${removed.weight / 1024} KB)")
        }
    }

    /** 估算一份缓存的占用。Track 里真正占地方的是三个字符串，其余是基本类型。 */
    private fun weightOf(value: Any): Int = when (value) {
        is List<*> -> value.sumOf { t ->
            if (t is Track) 96 + (t.name.length + t.artistNames.length + t.album.name.length) * 2 else 256
        }
        is JSONObject -> value.toString().toByteArray(Charsets.UTF_8).size
        is JSONArray -> value.toString().toByteArray(Charsets.UTF_8).size
        else -> 256
    }

    /**
     * 同一 key 同时只跑一次网络请求：4 台手机同时点「资料库」只发一轮，
     * 后面的人等锁、拿同结果。不同 key 各有各的锁，所以不同列表互不阻塞。
     */
    private suspend fun <T : Any> cached(key: String, ttlMs: Long, loader: suspend () -> T): T {
        @Suppress("UNCHECKED_CAST")
        peek(key, ttlMs)?.let { return it as T }

        val mutex = keyLocks.getOrPut(key) { Mutex() }
        return mutex.withLock {
            @Suppress("UNCHECKED_CAST")
            peek(key, ttlMs)?.let { return@withLock it as T }

            val started = System.currentTimeMillis()
            val value = try {
                loader()
            } catch (t: Throwable) {
                RemoteHealth.noteNetease(System.currentTimeMillis() - started, false)
                throw t
            }
            RemoteHealth.noteNetease(System.currentTimeMillis() - started, true)

            synchronized(cacheLock) {
                // 先减掉旧的：同一个 key 重新拉会把重量翻倍算
                cache.remove(key)?.let { cachedBytes -= it.weight }
                val weight = weightOf(value)
                cache[key] = Entry(value, System.currentTimeMillis(), weight)
                cachedBytes += weight
                evictLocked(keep = key)
            }
            value
        }.also {
            // 关键词搜索每换一个词就多一把锁，而 key 是永久字符串 —— 不清的话会随使用时长慢慢涨。
            // 只在没人持有的时候摘掉；万一撞上并发，最坏结果是同一个 key 多发一次请求。
            if (!mutex.isLocked) keyLocks.remove(key, mutex)
        }
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /** 榜单 / 推荐歌单的显示名，播放时拿来当播放队列的来源名。 */
    private val names = HashMap<String, String>()

    private fun uid(): Long = AccountStore.profile.value?.userId ?: 0L

    /** 资料库首页：喜欢的音乐、云盘、我创建的歌单。 */
    fun library(done: (JSONObject) -> Unit) {
        scope.launch {
            val out = try {
                cached("json:library", AGG_TTL_MS) { buildLibrary() }
            } catch (t: Throwable) {
                Log.w(TAG, "library failed", t)
                JSONObject().put("ok", false).put("message", t.message ?: "资料库读取失败")
            }
            done(out)
        }
    }

    /** 真正干活的部分。失败直接抛，让 cached 不要缓存一个错误结果。 */
    private suspend fun buildLibrary(): JSONObject = coroutineScope {
        val id = uid()
        if (id == 0L) error("车机还没登录网易云")
        val playlistsDeferred = async { NeteaseApi.userPlaylists(id) }
        val cloudDeferred = async {
            runCatching { NeteaseApi.cloudDrive().tracks.size }.getOrDefault(0)
        }
        val mine = playlistsDeferred.await().filter { it.creatorId == id }
        val liked = mine.firstOrNull { it.isLikedSongs }
        val array = JSONArray()
        mine.filterNot { it.isLikedSongs }.forEach { p ->
            array.put(
                JSONObject()
                    .put("id", p.id.toString())
                    .put("name", p.name)
                    .put("count", p.trackCount)
                    .put("cover", p.coverUrl.orEmpty()),
            )
        }
        JSONObject()
            .put("ok", true)
            .put(
                "liked",
                JSONObject()
                    .put("id", "liked")
                    .put("name", liked?.name ?: "我喜欢的音乐")
                    .put("count", liked?.trackCount ?: 0),
            )
            .put(
                "cloud",
                JSONObject()
                    .put("id", "cloud")
                    .put("name", "音乐云盘")
                    .put("count", cloudDeferred.await()),
            )
            .put("playlists", array)
    }

    /** 页面最下面的两块：排行榜 + 推荐歌单。id 已带前缀，手机原样回传给 getTracks。 */
    fun catalog(done: (JSONObject) -> Unit) {
        scope.launch {
            val out = try {
                cached("json:catalog", AGG_TTL_MS) { buildCatalog() }
            } catch (t: Throwable) {
                Log.w(TAG, "catalog failed", t)
                JSONObject().put("ok", false).put("message", t.message ?: "榜单读取失败")
            }
            done(out)
        }
    }

    private suspend fun buildCatalog(): JSONObject = coroutineScope {
        val tops = async { NeteaseApi.toplists() }
        val recommend = async { NeteaseApi.personalizedPlaylists(12) }
        JSONObject()
            .put("ok", true)
            .put("top", entryJson(tops.await(), TOP_PREFIX))
            .put("recommend", entryJson(recommend.await(), REC_PREFIX))
    }

    /**
     * 搜索歌曲，结果按 `search:<关键词>` 这个 id 进缓存，后面 playTracks 直接引用。
     *
     * 只回第一页（连同 total）。以前一次回 60 首，JSON 有 9～11 KB，
     * 超过 8 KB 的单帧上限被发送层整个丢掉，手机上就是永远转圈。
     */
    fun search(keyword: String, done: (JSONObject) -> Unit) {
        val q = keyword.trim()
        val id = SEARCH_PREFIX + q
        scope.launch {
            val out = try {
                val tracks = cached("list:$id", SEARCH_TTL_MS) { NeteaseApi.searchSongs(q, 60) }
                val page = pageJson(tracks, 0)
                JSONObject()
                    .put("ok", true)
                    .put("id", id)
                    .put("total", tracks.size)
                    .put("offset", 0)
                    .put("count", page.length())
                    .put("tracks", page)
            } catch (t: Throwable) {
                Log.w(TAG, "search($q) failed", t)
                JSONObject().put("ok", false).put("id", id).put("message", t.message ?: "搜索失败")
            }
            done(out)
        }
    }

    /** 取一页歌曲。id 取值：`liked` / `cloud` / 歌单数字 id / `top:…` / `rec:…` / `search:…` / `daily` / `heartbeat`。 */
    fun tracks(id: String, offset: Int, done: (JSONObject) -> Unit) {
        scope.launch {
            val out = JSONObject()
            runCatching {
                val all = cached("list:$id", LIST_TTL_MS) { load(id) }
                val start = offset.coerceAtLeast(0)
                val page = pageJson(all, start)
                out.put("ok", true)
                    .put("id", id)
                    .put("total", all.size)
                    .put("offset", start)
                    .put("count", page.length())
                    .put("tracks", page)
            }.onFailure {
                Log.w(TAG, "tracks($id) failed", it)
                out.put("ok", false).put("id", id).put("message", it.message ?: "读取失败")
            }
            done(out)
        }
    }

    /**
     * 从 [start] 起打一页，按实际序列化字节数裁剪，保证这一页一定装得进一帧。
     * 条数是变量，客户端用 `offset = 已收到的条数` 请求下一页，不会漏也不会重。
     */
    private fun pageJson(all: List<Track>, start: Int): JSONArray {
        val array = JSONArray()
        var bytes = 0
        var i = start
        while (i < all.size && array.length() < PAGE_SIZE) {
            val item = trackJson(all[i], i)
            val size = item.toString().toByteArray(Charsets.UTF_8).size + 1
            if (array.length() > 0 && bytes + size > PAGE_BUDGET) break
            array.put(item)
            bytes += size
            i++
        }
        return array
    }

    /** 播这个列表的第 [index] 首（0 起）。 */
    fun play(id: String, index: Int, done: (Boolean, String) -> Unit) {
        scope.launch {
            runCatching {
                val all = cached("list:$id", LIST_TTL_MS) { load(id) }
                if (all.isEmpty()) error("这个列表是空的")
                val start = all.getOrNull(index) ?: all.first()
                withContext(Dispatchers.Main) {
                    PlayerHub.play(
                        all,
                        start = start,
                        source = nameOf(id),
                        sourceId = id.substringAfter(':').toLongOrNull() ?: 0L,
                        // 心动模式是「以红心为种子」的电台语义，车机那边也带这个标记，
                        // 不带的话播放模式会按普通队列处理，行为和车机上点卡片不一致
                        mode = if (id == HEARTBEAT_ID) PlayMode.HEARTBEAT else PlayMode.NORMAL,
                    )
                }
            }.onSuccess { done(true, "") }
                .onFailure {
                    Log.w(TAG, "play($id,$index) failed", it)
                    done(false, it.message ?: "播放失败")
                }
        }
    }

    // ---------- 收藏到自建歌单 ----------

    /**
     * 把一首歌收藏到自建歌单。[trackId] 为空时取当前播放歌曲（经 [PlayerBridge] 拿，不直接碰 PlayerHub）。
     * 走 IO 线程；成功后让资料库相关缓存失效，下次拉库能拿到新的歌曲数。
     */
    fun addTrackToPlaylist(playlistId: String, trackId: Long?, done: (Boolean, String) -> Unit) {
        scope.launch {
            runCatching {
                val pid = playlistId.toLongOrNull() ?: error("歌单 id 无效：$playlistId")
                val tid = trackId ?: PlayerBridge.currentTrackId() ?: error("当前没有播放的歌曲")
                NeteaseApi.addTrackToPlaylist(pid, tid)
                invalidateLibraryCache(playlistId)
            }.onSuccess { done(true, "") }
                .onFailure {
                    Log.w(TAG, "addTrackToPlaylist($playlistId,$trackId) failed", it)
                    done(false, it.message ?: "收藏失败")
                }
        }
    }

    /** 让资料库首页与该歌单的列表缓存失效：下次请求重新拉，看到新的歌曲数和新增歌曲。 */
    private fun invalidateLibraryCache(playlistId: String) {
        synchronized(cacheLock) {
            listOf("json:library", "list:$playlistId").forEach { key ->
                cache.remove(key)?.let { cachedBytes -= it.weight }
            }
        }
    }

    // ---------- 内部 ----------

    private suspend fun load(id: String): List<Track> = when {
        id == "liked" -> {
            val liked = AccountStore.likedPlaylist ?: error("没有找到「我喜欢的音乐」")
            NeteaseApi.playlistDetail(liked.id).tracks
        }

        id == "cloud" -> NeteaseApi.cloudDrive().tracks

        // 每日推荐没有歌单 id，直接问网易云要当天的推荐列表（和车机首页那张卡片同一个接口）
        id == DAILY_ID -> NeteaseApi.dailySongs()

        // 心动模式：随便挑一首红心当种子，以「我喜欢的音乐」为池子生成队列。
        // 种子随机是刻意的 —— 每次进都换一批，跟车机首页点卡片的效果一致。
        id == HEARTBEAT_ID -> {
            val liked = AccountStore.likedPlaylist
                ?: error("先去红心几首歌，才能开启心动模式")
            val seed = AccountStore.likedIds.value.randomOrNull()
                ?: error("先去红心几首歌，才能开启心动模式")
            NeteaseApi.intelligenceList(seed, liked.id)
        }

        id.startsWith(SEARCH_PREFIX) -> {
            // 搜索页刚拉过就在缓存里，直接复用；没有才重新查（此时 tracks() 已持锁，peek 不加锁）
            @Suppress("UNCHECKED_CAST")
            (peek("list:$id", LIST_TTL_MS) as? List<Track>)
                ?: NeteaseApi.searchSongs(id.removePrefix(SEARCH_PREFIX), 60)
        }

        id.startsWith(TOP_PREFIX) || id.startsWith(REC_PREFIX) ->
            NeteaseApi.playlistDetail(playlistId(id)).tracks

        else -> NeteaseApi.playlistDetail(playlistId(id)).tracks
    }

    private fun playlistId(id: String): Long {
        val raw = id.substringAfter(':')
        return raw.toLongOrNull() ?: error("歌单 id 无效：$id")
    }

    private fun nameOf(id: String): String = when {
        id == "liked" -> "我喜欢的音乐"
        id == "cloud" -> "音乐云盘"
        id == DAILY_ID -> "每日推荐"
        id == HEARTBEAT_ID -> "心动模式"
        id.startsWith(SEARCH_PREFIX) -> "搜索：${id.removePrefix(SEARCH_PREFIX)}"
        id.startsWith(TOP_PREFIX) -> names[id] ?: "排行榜"
        id.startsWith(REC_PREFIX) -> names[id] ?: "推荐歌单"
        else -> AccountStore.playlists.value.firstOrNull { it.id.toString() == id }?.name ?: "远程歌单"
    }

    private fun entryJson(list: List<Playlist>, prefix: String): JSONArray {
        val array = JSONArray()
        list.filter { it.trackCount > 0 }.forEach {
            val id = prefix + it.id
            names[id] = it.name
            array.put(JSONObject().put("id", id).put("name", it.name).put("count", it.trackCount))
        }
        return array
    }

    /** 只发手机真正会显示的字段。id 和 album 手机不用，去掉后每首省 40 多字节。 */
    private fun trackJson(t: Track, index: Int): JSONObject = JSONObject()
        .put("index", index)
        .put("name", t.name)
        .put("artist", t.artists.joinToString("/") { it.name })
        .put("durationMs", t.durationMs)
        .put("playable", AccountStore.unplayableReason(t) == null)
}
