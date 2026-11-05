package com.carremote.client

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Leaderboard
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.QueueMusic
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Today
import androidx.compose.material.icons.rounded.Whatshot
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 遥控页下半部分：搜索、资料库、排行榜 / 推荐。
 *
 * 歌单内容全部由车机用已登录的账号去拉，手机只负责展示和选歌，
 * 所以手机不用登录网易云，也不存缓存；每次进入都现拉，网易云那边改了这里就是新的。
 */

private val Warn = Color(0xFFFF9F0A)
private val Faint = Color(0x66EBEBF5)

/**
 * 歌单默认只铺 5 个。自建歌单可能有几十个，全铺出来会把排行榜、推荐歌单顶到很远，
 * 每次进资料库都得先划两屏；默认收着，想看全的点「展开全部」。
 */
private const val COLLAPSE_MAX = 5

// ---------- 搜索 ----------

@Composable
fun SearchSection() {
    val result by RemoteClient.search.collectAsState()
    var keyword by remember { mutableStateOf("") }

    fun submit() = RemoteClient.searchTracks(keyword)

    Column(Modifier.fillMaxWidth()) {
        SubTitle("搜索歌曲")
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = keyword,
                onValueChange = { keyword = it },
                placeholder = { Text("歌名 / 歌手", color = Secondary) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { submit() }),
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { submit() },
                enabled = keyword.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = Accent),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            ) {
                Icon(Icons.Rounded.Search, null, modifier = Modifier.size(18.dp))
            }
        }

        Spacer(Modifier.height(8.dp))

        val error = result.error
        when {
            result.items.isEmpty() && result.loading -> Loading()
            result.items.isEmpty() && error != null -> Hint(error) {
                TextButton(onClick = { submit() }) { Text("重试", color = Accent) }
            }
            result.items.isNotEmpty() -> {
                Text(
                    "「${result.keyword}」共 ${result.total} 首",
                    color = Secondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
                result.items.forEach { track ->
                    TrackRow(track) { RemoteClient.playFromLibrary(result.id, track.index) }
                }
                when {
                    error != null -> Text(error, color = Warn, fontSize = 12.sp)
                    result.loading -> Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 10.dp),
                    ) {
                        CircularProgressIndicator(color = Accent, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("加载中…", color = Secondary, fontSize = 13.sp)
                    }
                    result.items.size < result.total -> TextButton(
                        onClick = { RemoteClient.requestTracks(result.id, result.items.size) },
                    ) {
                        Text("加载更多（${result.items.size}/${result.total}）", color = Accent, fontSize = 13.sp)
                    }
                }
            }
            result.keyword.isNotEmpty() -> Text("没有找到「${result.keyword}」", color = Secondary, fontSize = 13.sp)
        }
        Spacer(Modifier.height(4.dp))
    }
}

// ---------- 资料库 ----------

@Composable
fun LibrarySection() {
    val lib by RemoteClient.library.collectAsState()
    val list by RemoteClient.trackList.collectAsState()
    // 当前打开的歌单；null 表示资料库首页
    var opened by remember { mutableStateOf<RemoteClient.LibEntry?>(null) }

    LaunchedEffect(Unit) { RemoteClient.requestLibrary() }

    val current = opened
    if (current == null) {
        LibHome(lib) { entry ->
            opened = entry
            RemoteClient.requestTracks(entry.id, 0)
        }
    } else {
        TrackListInline(
            entry = current,
            list = list,
            onBack = { opened = null },
            onReload = { RemoteClient.requestTracks(current.id, 0) },
            onMore = { RemoteClient.requestTracks(current.id, list.items.size) },
            onPlay = { index -> RemoteClient.playFromLibrary(current.id, index) },
        )
    }
}

@Composable
private fun LibHome(lib: RemoteClient.LibData, onOpen: (RemoteClient.LibEntry) -> Unit) {
    // 外层（RemoteApp）已经是滚动容器，这里不能再套 verticalScroll，
    // 否则会被无限高度约束打崩：IllegalStateException: infinity maximum height constraints
    var showAllPlaylists by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "资料库",
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
            )
            if (lib.loading) {
                CircularProgressIndicator(color = Accent, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
            }
            IconButton(onClick = { RemoteClient.requestLibrary() }) {
                Icon(Icons.Rounded.Refresh, null, tint = Secondary, modifier = Modifier.size(18.dp))
            }
        }
        Spacer(Modifier.height(10.dp))

        lib.error?.let {
            Text(it, color = Warn, fontSize = 13.sp)
            Spacer(Modifier.height(10.dp))
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
            lib.liked?.let {
                BigCard(it.name, it.count, Icons.Rounded.Favorite, Color(0xFFE0334B), Modifier.weight(1f)) { onOpen(it) }
            }
            lib.cloud?.let {
                BigCard(it.name, it.count, Icons.Rounded.Cloud, Color(0xFF2F6FEB), Modifier.weight(1f)) { onOpen(it) }
            }
        }

        // 每日推荐 / 心动模式：和车机首页那两张卡一个意思，点进去现拉队列。
        // 位置按需求放在资料库卡片下面、自建歌单上面。
        Spacer(Modifier.height(18.dp))
        SubTitle("为你推荐")
        QuickRow(
            "每日推荐", "根据你的口味 · 每天 6:00 更新",
            Icons.Rounded.Today, Color(0xFFFF6A3D),
            onClick = { onOpen(RemoteClient.LibEntry("daily", "每日推荐", 0)) },
            onPlay = { RemoteClient.playFromLibrary("daily", 0) },
        )
        QuickRow(
            "心动模式", "根据红心推荐 · 边放边续",
            Icons.Rounded.Favorite, Color(0xFFD4145A),
            onClick = { onOpen(RemoteClient.LibEntry("heartbeat", "心动模式", 0)) },
            onPlay = { RemoteClient.playFromLibrary("heartbeat", 0) },
        )

        Spacer(Modifier.height(20.dp))
        SubTitle("我创建的歌单（${lib.playlists.size}）")
        if (lib.playlists.isEmpty() && !lib.loading) {
            Text("还没有自己创建的歌单", color = Secondary, fontSize = 13.sp)
        }
        val shown = if (showAllPlaylists) lib.playlists else lib.playlists.take(COLLAPSE_MAX)
        shown.forEach { p ->
            EntryRow(p, Icons.Rounded.QueueMusic, Accent, { onOpen(p) }) {
                RemoteClient.playFromLibrary(p.id, 0)
            }
        }
        if (lib.playlists.size > COLLAPSE_MAX) {
            MoreToggle(showAllPlaylists, lib.playlists.size) { showAllPlaylists = !showAllPlaylists }
        }
        Spacer(Modifier.height(12.dp))

        CatalogSection(onOpen)
    }
}

/** 排行榜 + 推荐歌单，id 直接带 `top:` / `rec:` 前缀回传给车机。 */
@Composable
private fun CatalogSection(onOpen: (RemoteClient.LibEntry) -> Unit) {
    val cat by RemoteClient.catalog.collectAsState()
    var showAllRecs by remember { mutableStateOf(false) }
    var showAllTops by remember { mutableStateOf(false) }
    // 首次进资料库才拉一次，来回切歌单不重复请求
    LaunchedEffect(Unit) {
        if (RemoteClient.catalog.value.top.isEmpty()) RemoteClient.requestCatalog()
    }

    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SubTitle("排行榜", Modifier.weight(1f))
            if (cat.loading) {
                CircularProgressIndicator(color = Accent, strokeWidth = 2.dp, modifier = Modifier.size(14.dp))
            }
        }
        cat.error?.let { Text(it, color = Warn, fontSize = 13.sp) }
        val tops = if (showAllTops) cat.top else cat.top.take(COLLAPSE_MAX)
        tops.forEach { t ->
            EntryRow(t, Icons.Rounded.Leaderboard, Color(0xFFE0334B), { onOpen(t) }) {
                RemoteClient.playFromLibrary(t.id, 0)
            }
        }
        if (cat.top.size > COLLAPSE_MAX) {
            MoreToggle(showAllTops, cat.top.size) { showAllTops = !showAllTops }
        }

        Spacer(Modifier.height(14.dp))
        SubTitle("推荐歌单")
        val recs = if (showAllRecs) cat.recommend else cat.recommend.take(COLLAPSE_MAX)
        recs.forEach { r ->
            EntryRow(r, Icons.Rounded.Whatshot, Color(0xFFFF9F0A), { onOpen(r) }) {
                RemoteClient.playFromLibrary(r.id, 0)
            }
        }
        if (cat.recommend.size > COLLAPSE_MAX) {
            MoreToggle(showAllRecs, cat.recommend.size) { showAllRecs = !showAllRecs }
        }
        if (!cat.loading && cat.top.isEmpty() && cat.recommend.isEmpty() && cat.error == null) {
            Text("车机那边没返回榜单，下拉重进试试", color = Secondary, fontSize = 13.sp)
        }
    }
}

// ---------- 公共小件 ----------

@Composable
private fun SubTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        color = Color.White,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier.padding(bottom = 8.dp),
    )
}

/** 折叠区底部那行「展开全部／收起」，歌单、榜单、推荐三处共用。 */
@Composable
private fun MoreToggle(expanded: Boolean, total: Int, onToggle: () -> Unit) {
    TextButton(
        onClick = onToggle,
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 4.dp),
    ) {
        Text(
            if (expanded) "收起" else "展开全部（$total 个）",
            color = Accent,
            fontSize = 13.sp,
        )
    }
}

/**
 * 每日推荐 / 心动模式的入口行：没有固定首数（点进去现拉），所以副标题放说明文字而不是「N 首」。
 */
@Composable
private fun QuickRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    tint: Color,
    onClick: () -> Unit,
    onPlay: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Card, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(start = 16.dp, end = 8.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .size(36.dp)
                .background(tint.copy(alpha = 0.18f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(icon, null, tint = tint, modifier = Modifier.size(19.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Color.White, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(subtitle, color = Secondary, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        PlayDot(onPlay)
        Spacer(Modifier.width(2.dp))
        Text("›", color = Secondary, fontSize = 17.sp)
    }
}

/**
 * 一个入口行：点整行进列表看曲目，点右边的小圆钮直接从头顺序播。
 * Compose 里内层 IconButton 会消费掉点击，不会冒泡到外层的 clickable，不用手动拦。
 */
@Composable
private fun EntryRow(
    entry: RemoteClient.LibEntry,
    icon: ImageVector,
    tint: Color,
    onClick: () -> Unit,
    onPlay: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Card, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(start = 16.dp, end = 8.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier
                .size(36.dp)
                .background(tint.copy(alpha = 0.18f), RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(icon, null, tint = tint, modifier = Modifier.size(19.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(entry.name, color = Color.White, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text("${entry.count} 首", color = Secondary, fontSize = 12.sp)
        }
        PlayDot(onPlay)
        Spacer(Modifier.width(2.dp))
        Text("›", color = Secondary, fontSize = 17.sp)
    }
}

/** 行内的「顺序播放」圆钮，和网页那个 .pl 保持一致。 */
@Composable
private fun PlayDot(onPlay: () -> Unit) {
    IconButton(onClick = onPlay, modifier = Modifier.size(36.dp)) {
        Box(
            Modifier
                .size(32.dp)
                .background(Color.White.copy(alpha = 0.08f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Rounded.PlayArrow, "顺序播放", tint = Secondary, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
private fun BigCard(
    title: String,
    count: Int,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier
            .background(color, RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
    ) {
        Box(
            Modifier
                .size(32.dp)
                .background(Color.White.copy(alpha = 0.22f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(icon, null, tint = Color.White, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.height(22.dp))
        Text(
            title,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            if (count > 0) "$count 首" else "—",
            color = Color.White.copy(alpha = 0.75f),
            fontSize = 12.sp,
        )
    }
}

@Composable
private fun TrackRow(track: RemoteClient.TrackItem, onPlay: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(enabled = track.playable, onClick = onPlay)
            .padding(horizontal = 4.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            // 序号从 1 数，跟车机界面和网页端一致；
            // 但 track.index 本身是 0 起的（playTracks 用它定位），所以只在显示这里 +1
            (track.index + 1).toString(),
            color = if (track.playable) Secondary else Faint,
            fontSize = 13.sp,
            modifier = Modifier.width(28.dp),
        )
        Column(Modifier.weight(1f)) {
            Text(
                track.name,
                color = if (track.playable) Color.White else Faint,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                if (track.playable) track.artist else "${track.artist} · 不可播放",
                color = Secondary,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(formatDuration(track.durationMs), color = Secondary, fontSize = 12.sp)
    }
}

@Composable
private fun TrackListInline(
    entry: RemoteClient.LibEntry,
    list: RemoteClient.TrackList,
    onBack: () -> Unit,
    onReload: () -> Unit,
    onMore: () -> Unit,
    onPlay: (Int) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = onBack, contentPadding = PaddingValues(0.dp)) {
                Text("‹ 返回", color = Accent, fontSize = 14.sp)
            }
            Spacer(Modifier.weight(1f))
            Text("${list.items.size} / ${list.total} 首", color = Secondary, fontSize = 12.sp)
            Spacer(Modifier.width(10.dp))
            Button(
                onClick = { onPlay(0) },
                enabled = list.items.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(containerColor = Accent),
            ) {
                Icon(Icons.Rounded.PlayArrow, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text("播放")
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(entry.name, color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)

        list.error?.let {
            Spacer(Modifier.height(8.dp))
            Hint(it) { TextButton(onClick = onReload) { Text("重试", color = Accent) } }
        }

        if (list.loading && list.items.isEmpty()) {
            Spacer(Modifier.height(10.dp))
            Loading()
        }

        Spacer(Modifier.height(6.dp))
        list.items.forEach { track -> TrackRow(track) { onPlay(track.index) } }

        if (list.items.isNotEmpty() && list.items.size < list.total) {
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = onMore,
                enabled = !list.loading,
                colors = ButtonDefaults.buttonColors(containerColor = Card),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(if (list.loading) "加载中…" else "加载更多", color = Color.White)
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
private fun Loading() {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Accent, strokeWidth = 2.dp, modifier = Modifier.size(22.dp))
    }
}

@Composable
private fun Hint(text: String, action: (@Composable () -> Unit)? = null) {
    Column {
        Text(text, color = Warn, fontSize = 13.sp)
        action?.invoke()
    }
}

private fun formatDuration(ms: Long): String {
    val total = (ms / 1000).coerceAtLeast(0)
    return "%d:%02d".format(total / 60, total % 60)
}
