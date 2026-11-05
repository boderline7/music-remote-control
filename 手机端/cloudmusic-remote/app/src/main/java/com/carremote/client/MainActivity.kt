package com.carremote.client

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material.icons.rounded.Speaker
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

internal val Bg = Color(0xFF0B0B0C)
internal val Card = Color(0xFF1C1C1E)
internal val Accent = Color(0xFFFA2D48)
internal val Secondary = Color(0x99EBEBF5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Prefs.init(this)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(primary = Accent, background = Bg, surface = Bg),
            ) {
                Surface(Modifier.fillMaxSize(), color = Bg) {
                    RemoteApp(this)
                }
            }
        }
    }
}

@Composable
private fun RemoteApp(context: Context) {
    val state by RemoteClient.state.collectAsState()
    val found by Discovery.found.collectAsState()
    val hint by RemoteClient.hint.collectAsState()
    val scanning by Scanner.scanning.collectAsState()
    val scanned by Scanner.results.collectAsState()
    val scope = rememberCoroutineScope()

    // 网络变化监听：断网/换 Wi-Fi 时不等退避定时器，立刻重连（NetWatcher → RemoteClient）
    DisposableEffect(Unit) {
        NetWatcher.start(context)
        onDispose { NetWatcher.stop() }
    }

    LaunchedEffect(Unit) {
        Discovery.start(context)
        val host = Prefs.lastHost
        if (host.isNotEmpty()) RemoteClient.connect(host, Prefs.lastPort)
        // 热点每次开机 IP 都可能变，直连旧地址失败后再扫网段。
        //
        // 这里必须先等一会儿：上一版是「连上次地址」和「扫网段」同时开跑，扫描一结束
        // 又 connect 一次，于是同一时间两条 socket 并存，界面在两种连接状态之间来回跳。
        // 等到直连真的失败了再去扫，只有一条连接在跑。
        delay(2_500)
        val now = RemoteClient.state.value
        if (now is RemoteClient.Conn.Ready || now is RemoteClient.Conn.NeedPin) return@LaunchedEffect
        val hits = Scanner.scan()
        if (hits.isEmpty()) return@LaunchedEffect
        val after = RemoteClient.state.value
        if (after !is RemoteClient.Conn.Ready && after !is RemoteClient.Conn.NeedPin) {
            RemoteClient.connect(hits.first(), Protocol.PORT)
        }
    }
    LaunchedEffect(state) {
        if (state is RemoteClient.Conn.Ready) {
            Discovery.stop()
        } else {
            Discovery.start(context)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "车机遥控",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
            )
            Icon(Icons.Rounded.Wifi, null, tint = Accent, modifier = Modifier.size(26.dp))
        }
        Spacer(Modifier.height(14.dp))

        when (val s = state) {
            is RemoteClient.Conn.Ready -> ReadyPanel(s.carName, hint)

            else -> ConnectPanel(state, found, hint, context, scanning, scanned) {
                scope.launch { Scanner.scan() }
            }
        }
    }
}

// ---------- 连接 / 配对 ----------

@Composable
private fun ConnectPanel(
    state: RemoteClient.Conn,
    found: List<Discovery.Found>,
    hint: String,
    context: Context,
    scanning: Boolean,
    scanned: List<String>,
    onScan: () -> Unit,
) {
    var host by remember { mutableStateOf(Prefs.lastHost) }
    var port by remember { mutableStateOf(Prefs.lastPort.toString()) }

    StatusLine(state)

    Spacer(Modifier.height(16.dp))
    SectionTitle("发现的车机")
    if (found.isEmpty() && scanned.isEmpty()) {
        Text(
            if (scanning) "正在扫描本网段…" else "正在搜索同一 Wi-Fi 下的车机…",
            color = Secondary, fontSize = 14.sp,
        )
    } else {
        found.forEach { item ->
            HostRow(item.name.ifEmpty { "车机" }, "${item.host}:${item.port}") {
                RemoteClient.connect(item.host, item.port)
            }
        }
        scanned.filter { ip -> found.none { it.host == ip } }.forEach { ip ->
            HostRow("扫描到车机", "$ip:${Protocol.PORT}") {
                RemoteClient.connect(ip, Protocol.PORT)
            }
        }
    }
    Spacer(Modifier.height(8.dp))
    Row {
        TextButton(onClick = {
            Discovery.stop()
            Discovery.start(context)
            onScan()
        }) {
            Icon(Icons.Rounded.Refresh, null, tint = Accent, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
            Text(if (scanning) "扫描中…" else "重新搜索 / 扫网段", color = Accent)
        }
    }
    Text(
        "车机 IP 变了也没关系：会自动扫当前网段寻找开着的 18765 端口。",
        color = Secondary, fontSize = 12.sp,
    )

    Spacer(Modifier.height(20.dp))
    SectionTitle("手动连接")
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = host,
            onValueChange = { host = it },
            label = { Text("车机 IP") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.weight(1f),
        )
        Spacer(Modifier.width(10.dp))
        OutlinedTextField(
            value = port,
            onValueChange = { port = it.filter(Char::isDigit).take(5) },
            label = { Text("端口") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(96.dp),
        )
    }
    Spacer(Modifier.height(10.dp))
    Button(
        onClick = { RemoteClient.connect(host, port.toIntOrNull() ?: Protocol.PORT) },
        colors = ButtonDefaults.buttonColors(containerColor = Accent),
        modifier = Modifier.fillMaxWidth(),
    ) { Text("连接") }

    // v2.0 车机端取消了 PIN：连上就直接可控。这里只在连到**旧版车机**（还会要求 PIN）时
    // 给一句提示，不再渲染输入框 —— 用户要做的只有升级车机端。
    if (state is RemoteClient.Conn.NeedPin) {
        Spacer(Modifier.height(22.dp))
        SectionTitle("车机要求配对")
        Text(
            "这台车机还是旧版本，需要 PIN。升级车机端后连上就能直接控制。",
            color = Color(0xFFFF9F0A), fontSize = 13.sp,
        )
    }

    if (hint.isNotEmpty()) {
        Spacer(Modifier.height(14.dp))
        Text(hint, color = Color(0xFFFF9F0A), fontSize = 14.sp)
    }
    Spacer(Modifier.height(30.dp))
}

@Composable
private fun HostRow(title: String, subtitle: String, onConnect: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Card, RoundedCornerShape(14.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, color = Color.White, fontSize = 16.sp)
            Text(subtitle, color = Secondary, fontSize = 13.sp)
        }
        Button(
            onClick = onConnect,
            colors = ButtonDefaults.buttonColors(containerColor = Accent),
        ) { Text("连接") }
    }
}

@Composable
private fun StatusLine(state: RemoteClient.Conn) {
    val (text, color) = when (state) {
        is RemoteClient.Conn.Idle -> "未连接" to Secondary
        is RemoteClient.Conn.Connecting -> "正在连接 ${state.host}:${state.port}…" to Color(0xFFFF9F0A)
        is RemoteClient.Conn.NeedPin -> "已连接车机，等待配对" to Color(0xFFFF9F0A)
        is RemoteClient.Conn.Ready -> "已连接：${state.carName}" to Color(0xFF34C759)
        is RemoteClient.Conn.Failed -> state.message to Color(0xFFFF453A)
    }
    Box(
        Modifier
            .fillMaxWidth()
            .background(Card, RoundedCornerShape(14.dp))
            .padding(16.dp),
    ) {
        Text(text, color = color, fontSize = 14.sp)
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text,
        color = Color.White,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 8.dp),
    )
}

// ---------- 遥控 ----------

@Composable
private fun ReadyPanel(carName: String, hint: String) {
    val now by RemoteClient.now.collectAsState()
    var dragValue by remember { mutableFloatStateOf(-1f) }
    var volumeValue by remember { mutableFloatStateOf(-1f) }
    val scope = rememberCoroutineScope()

    // 断线重连期间面板不切走，只在角标上变个颜色。这样连接抖一下用户几乎无感，
    // 而不是「面板消失 → 又出现」那种跳。
    val reconnecting = hint.contains("重连")
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(8.dp)
                .background(if (reconnecting) Color(0xFFFF9F0A) else Color(0xFF34C759), RoundedCornerShape(4.dp)),
        )
        Spacer(Modifier.width(8.dp))
        Text(
            if (reconnecting) "$carName · 重连中" else carName,
            color = Secondary,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
        )
        TextButton(onClick = { RemoteClient.disconnect() }) {
            Text("断开", color = Secondary)
        }
    }

    Spacer(Modifier.height(40.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(
                now.title.ifEmpty { "未在播放" },
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                now.artist.ifEmpty { "—" },
                color = Secondary,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.width(10.dp))
        IconButton(
            onClick = { RemoteClient.like() },
            enabled = now.title.isNotEmpty(),
            modifier = Modifier.size(52.dp),
        ) {
            Icon(
                if (now.liked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = if (now.liked) "取消喜欢" else "添加到我喜欢",
                tint = if (now.liked) Accent else Secondary,
                modifier = Modifier.size(32.dp),
            )
        }
    }

    Spacer(Modifier.height(34.dp))

    val duration = now.durationMs.coerceAtLeast(1L)
    val progress = if (dragValue >= 0f) dragValue else now.positionMs.toFloat().coerceIn(0f, duration.toFloat())
    Slider(
        value = progress / duration,
        onValueChange = { dragValue = it * duration },
        onValueChangeFinished = {
            if (dragValue >= 0f) RemoteClient.seek(dragValue.toLong())
            dragValue = -1f
        },
        colors = SliderDefaults.colors(
            thumbColor = Accent,
            activeTrackColor = Accent,
            inactiveTrackColor = Color(0x3DEBEBF5),
        ),
    )
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(format(progress.toLong()), color = Secondary, fontSize = 12.sp)
        Text(format(duration), color = Secondary, fontSize = 12.sp)
    }

    Spacer(Modifier.height(26.dp))
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { RemoteClient.command(Protocol.ACTION_PREV) }) {
            Icon(Icons.Rounded.SkipPrevious, null, tint = Color.White, modifier = Modifier.size(44.dp))
        }
        Box(
            Modifier
                .size(84.dp)
                .background(Accent, RoundedCornerShape(42.dp)),
            contentAlignment = Alignment.Center,
        ) {
            IconButton(onClick = {
                RemoteClient.command(if (now.playing) Protocol.ACTION_PAUSE else Protocol.ACTION_PLAY)
            }) {
                Icon(
                    if (now.playing) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                    null,
                    tint = Color.White,
                    modifier = Modifier.size(46.dp),
                )
            }
        }
        IconButton(onClick = { RemoteClient.command(Protocol.ACTION_NEXT) }) {
            Icon(Icons.Rounded.SkipNext, null, tint = Color.White, modifier = Modifier.size(44.dp))
        }
    }

    Spacer(Modifier.height(34.dp))

    if (now.volumeMax > 0 && now.volume >= 0) {
        // 本地乐观值：点加减后先按预期显示，等车机推回真实音量再交还
        val current = if (volumeValue >= 0f) volumeValue else now.volume.toFloat()
        // 一步大约 1/15 音量，点几下有明显变化又不至于一下拉满
        val step = (now.volumeMax / 15f).roundToInt().coerceAtLeast(1)

        fun nudge(delta: Int) {
            val next = (current.toInt() + delta).coerceIn(0, now.volumeMax)
            if (next == current.toInt()) return
            volumeValue = next.toFloat()
            RemoteClient.setVolume(next)
            scope.launch {
                delay(1200)
                volumeValue = -1f
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Rounded.Speaker, null, tint = Secondary, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(10.dp))
            Slider(
                value = current / now.volumeMax,
                onValueChange = { volumeValue = it * now.volumeMax },
                onValueChangeFinished = {
                    if (volumeValue >= 0f) RemoteClient.setVolume(volumeValue.toInt())
                    volumeValue = -1f
                },
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color(0xFFEBEBF5),
                    inactiveTrackColor = Color(0x3DEBEBF5),
                ),
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(10.dp))
            Text("${current.toInt()}", color = Secondary, fontSize = 13.sp)
        }

        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            VolumeStepButton(Icons.Rounded.Remove, "音量减", Modifier.weight(1f)) { nudge(-step) }
            VolumeStepButton(Icons.Rounded.Add, "音量加", Modifier.weight(1f)) { nudge(step) }
        }
    }

    if (hint.isNotEmpty()) {
        Spacer(Modifier.height(12.dp))
        Text(hint, color = Color(0xFFFF9F0A), fontSize = 13.sp)
    }

    // 音量条之后一路往下排：搜索 → 资料库（喜欢 / 云盘 / 自建歌单）→ 排行榜 / 推荐
    Spacer(Modifier.height(26.dp))
    Box(Modifier.fillMaxWidth().height(1.dp).background(Color(0x1FEBEBF5)))
    Spacer(Modifier.height(18.dp))
    SearchSection()
    Spacer(Modifier.height(26.dp))
    Box(Modifier.fillMaxWidth().height(1.dp).background(Color(0x1FEBEBF5)))
    Spacer(Modifier.height(18.dp))
    LibrarySection()
    Spacer(Modifier.height(30.dp))
}

/** 音量条下面的 − / + 按钮，一步一格，长按不加速（车机音量走 setStreamVolume，没必要刷太快）。 */
@Composable
private fun VolumeStepButton(
    icon: ImageVector,
    desc: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier
            .height(48.dp)
            .background(Color(0xFF232326), RoundedCornerShape(24.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, desc, tint = Color.White, modifier = Modifier.size(24.dp))
    }
}

private fun format(ms: Long): String {
    val total = (ms / 1000).coerceAtLeast(0)
    val m = total / 60
    val s = total % 60
    return "%d:%02d".format(m, s)
}
