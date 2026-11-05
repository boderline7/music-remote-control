package com.cloudmusic.car.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Smartphone
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cloudmusic.car.data.Settings
import com.cloudmusic.car.remote.OnlineDevice
import com.cloudmusic.car.remote.RemoteHealth
import com.cloudmusic.car.remote.RemotePrefs
import com.cloudmusic.car.remote.RemoteServer
import com.cloudmusic.car.remote.RemoteUiState
import com.cloudmusic.car.ui.Nav
import com.cloudmusic.car.ui.components.Label
import com.cloudmusic.car.ui.components.LargeTitle
import com.cloudmusic.car.ui.components.PillButton
import com.cloudmusic.car.ui.components.pressable
import com.cloudmusic.car.ui.pagePadding
import com.cloudmusic.car.ui.theme.K
import com.cloudmusic.car.ui.theme.LocalLandscape
import kotlin.concurrent.thread

/**
 * 手机遥控页：开关、本机地址、网页域名、在线设备与上限。
 *
 * v2.0 起**没有 PIN、没有配对表**：车机上打开遥控，手机连上同一个 Wi-Fi 就能直接控制。
 * 页面因此只剩三件事要说清楚 —— 有没有开、地址是多少、现在有几台手机连着。
 * 1920×720 车机走左右两栏；手机竖屏 / 窄屏改为上下堆叠。
 */
@Composable
fun RemoteScreen(nav: Nav) {
    val c = K.colors
    val context = LocalContext.current
    val state by RemoteServer.ui.collectAsState()
    val landscape = LocalLandscape.current
    val stacked = !landscape
    val compact = !landscape || LocalConfiguration.current.screenWidthDp < 900

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(pagePadding()),
    ) {
        if (nav.stack.isNotEmpty() || !landscape) {
            Row(
                Modifier.padding(bottom = 16.dp).pressable { nav.back() },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Rounded.ArrowBackIosNew, null, tint = c.accent, modifier = Modifier.size(30.dp))
                Spacer(Modifier.width(6.dp))
                Label("返回", 28.sp, c.accent)
            }
        }
        LargeTitle("手机遥控", Modifier.padding(bottom = 10.dp))
        Label(
            "手机连同一个 Wi-Fi 后打开「车机遥控」App 或浏览器，直接就能控制 —— 不用输 PIN，也不用配对。" +
                "只在局域网内可用。",
            22.sp,
            c.secondary,
            modifier = Modifier.padding(bottom = 26.dp),
            maxLines = 3,
        )

        val onToggle: (Boolean) -> Unit = { running ->
            if (running) {
                RemoteServer.stop()
                Settings.setRemoteEnabled(false)
            } else if (RemoteServer.start(context)) {
                Settings.setRemoteEnabled(true)
            }
        }
        if (stacked) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()) {
                LeftCard(state, compact, Modifier.fillMaxWidth(), onToggle)
                RightCard(state, Modifier.fillMaxWidth())
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp), modifier = Modifier.fillMaxWidth()) {
                LeftCard(state, compact, Modifier.weight(1f), onToggle)
                RightCard(state, Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(20.dp))
        DiagCard(state)

        Spacer(Modifier.height(26.dp))
        Label(
            "安全提示：没有 PIN 也没有加密，同一个 Wi-Fi 下的任何人连上 18765 端口都能控制播放。" +
                "默认只允许 4 台同时在线，人多的地方可以调小到 1 台。不要把这个端口映射到公网。",
            20.sp,
            c.secondary,
            maxLines = 3,
        )
    }
}

/** 左栏：服务开关、地址、网页域名、mDNS 自检。 */
@Composable
private fun LeftCard(
    state: RemoteUiState,
    compact: Boolean,
    modifier: Modifier,
    onToggle: (Boolean) -> Unit,
) {
    val c = K.colors
    Column(
        modifier
            .background(c.fill, RoundedCornerShape(22.dp))
            .padding(28.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.Wifi,
                null,
                tint = if (state.running) Color(0xFF34C759) else c.secondary,
                modifier = Modifier.size(34.dp),
            )
            Spacer(Modifier.width(12.dp))
            Label(
                if (state.running) "遥控服务已开启" else "遥控服务已关闭",
                if (compact) 26.sp else 30.sp, c.label, FontWeight.Bold,
            )
            if (!compact) {
                Spacer(Modifier.weight(1f))
                PillButton(if (state.running) "关闭" else "开启", null) { onToggle(state.running) }
            }
        }
        if (compact) {
            Spacer(Modifier.height(14.dp))
            PillButton(if (state.running) "关闭" else "开启", null) { onToggle(state.running) }
        }

        Spacer(Modifier.height(18.dp))
        Label(
            if (state.running) "车机地址  ${state.ip.ifEmpty { "获取中…" }} : ${state.port}" else "开启后手机才能发现车机",
            24.sp, c.secondary,
        )
        state.error?.let {
            Spacer(Modifier.height(8.dp))
            Label(it, 22.sp, Color(0xFFFF9500))
        }

        Spacer(Modifier.height(26.dp))
        Label("手机浏览器遥控", 24.sp, c.label, FontWeight.Medium)
        Spacer(Modifier.height(6.dp))
        // 网页端靠 mDNS 固定域名，不用管热点 IP 变成什么
        Label(state.webUrl, if (compact) 26.sp else 30.sp, c.accent, FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        val mdnsOk = state.mdnsRunning && state.mdnsLocked && state.mdnsInterface.isNotEmpty()
        // 收到了查询却一次都没回，说明问的类型不对，这时候绿的会骗人
        val notifyStuck = mdnsOk && state.mdnsQueries > 0 && state.mdnsAnswers == 0L
        // 宣告是「域名能不能长期用」的唯一指标：超过 60 秒没发，缓存一过期就解析失败
        val announceStale = mdnsOk && (state.mdnsSinceAnnounceSec < 0 || state.mdnsSinceAnnounceSec > 60)
        val mdnsText = when {
            !state.running -> "遥控服务未开启"
            mdnsOk -> "mDNS 就绪 · ${state.mdnsInterface} · 收 ${state.mdnsQueries} / 回 ${state.mdnsAnswers}"
            state.mdnsError.isNotEmpty() -> "mDNS 异常：${state.mdnsError}"
            else -> "mDNS 启动中…"
        }
        Label(mdnsText, 20.sp, if (mdnsOk && !notifyStuck) Color(0xFF34C759) else Color(0xFFFF9500))
        if (mdnsOk) {
            // 这一行是长期可用性的主指标：次数应该一直缓慢增长，秒数应该在 45 上下
            val age = if (state.mdnsSinceAnnounceSec < 0) "—" else "${state.mdnsSinceAnnounceSec}s 前"
            Label(
                "域名保活：已宣告 ${state.mdnsAnnounces} 次 · 上次 $age",
                17.sp,
                if (announceStale) Color(0xFFFF9500) else c.secondary,
            )
            if (announceStale) {
                Label("保活停了，域名过一会会解析不出来；点上方开关重开一次遥控", 17.sp, Color(0xFFFF9500))
            }
        }
        if (mdnsOk && state.mdnsLastQuery.isNotEmpty()) {
            Label("最近查询：${state.mdnsLastQuery}", 17.sp, c.secondary)
        }
        if (notifyStuck) {
            Label("收到查询但没回包，看下行「最近查询」里的类型", 17.sp, Color(0xFFFF9500))
        }
        if (state.ip.isNotEmpty()) {
            Label("打不开时改用 http://${state.ip}:${state.port}", 18.sp, c.secondary)
        }
    }
}

/** 右栏：同时在线设备 + 上限调节。 */
@Composable
private fun RightCard(state: RemoteUiState, modifier: Modifier) {
    val c = K.colors
    Column(
        modifier
            .background(c.fill, RoundedCornerShape(22.dp))
            .padding(28.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Label("同时在线", 30.sp, c.label, FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Label("${state.online.size} / ${state.maxClients} 台", 24.sp, c.secondary)
        }
        Spacer(Modifier.height(6.dp))
        Label(
            if (state.online.size >= state.maxClients) "已达上限，新手机连不上；调大上限或先断开一台" else "多台手机可以同时控制",
            20.sp, if (state.online.size >= state.maxClients) Color(0xFFFF9500) else c.secondary,
        )

        Spacer(Modifier.height(18.dp))
        if (state.online.isEmpty()) {
            Label("还没有手机连上。", 24.sp, c.secondary)
        } else {
            state.online.forEach { device ->
                Box(Modifier.fillMaxWidth().padding(vertical = 6.dp)) { OnlineRow(device) }
            }
        }

        Spacer(Modifier.height(24.dp))
        Label("同时在线上限", 24.sp, c.label, FontWeight.Medium)
        Spacer(Modifier.height(4.dp))
        Label(
            "只有新连接会被这条限制挡住，正在连着的不受影响。",
            19.sp, c.secondary,
        )
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            StepButton(Icons.Rounded.Remove, enabled = state.maxClients > RemotePrefs.MIN_CLIENTS) {
                RemoteServer.setMaxClients(state.maxClients - 1)
            }
            Label(
                "${state.maxClients} 台",
                40.sp,
                c.label,
                FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 28.dp),
            )
            StepButton(Icons.Rounded.Add, enabled = state.maxClients < RemotePrefs.MAX_CLIENTS) {
                RemoteServer.setMaxClients(state.maxClients + 1)
            }
        }

        Spacer(Modifier.height(24.dp))
        Label("歌单缓存上限", 24.sp, c.label, FontWeight.Medium)
        Spacer(Modifier.height(4.dp))
        Label(
            "打开过的歌单留在车机内存里，翻回来不用重新拉。调小立即回收最久没用的那几份，正在播的歌不受影响。",
            19.sp, c.secondary,
        )
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            StepButton(Icons.Rounded.Remove, enabled = state.cacheLimit > RemotePrefs.MIN_CACHE) {
                RemoteServer.setCacheLimit(state.cacheLimit - 1)
            }
            Label(
                "${state.cacheLimit} 份",
                40.sp,
                c.label,
                FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 28.dp),
            )
            StepButton(Icons.Rounded.Add, enabled = state.cacheLimit < RemotePrefs.MAX_CACHE) {
                RemoteServer.setCacheLimit(state.cacheLimit + 1)
            }
            Spacer(Modifier.weight(1f))
            PillButton("清空", null) { RemoteServer.clearLibraryCache() }
        }
        Spacer(Modifier.height(10.dp))
        Label(
            "已缓存 ${state.cacheEntries} 份 · 约 ${fmtMb(state.cacheBytes)} MB",
            19.sp,
            if (state.cacheEntries > state.cacheLimit) Color(0xFFFF9500) else c.secondary,
        )
    }
}

/** 字节转 MB，一位小数。诊断卡和缓存行都用它，别在两处各写一套格式。 */
private fun fmtMb(bytes: Long): String = "%.1f".format(bytes / 1048576.0)

@Composable
private fun StepButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val c = K.colors
    Box(
        Modifier
            .size(64.dp)
            .background(if (enabled) c.fill else c.fill.copy(alpha = 0.4f), RoundedCornerShape(18.dp))
            .pressable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, null, tint = if (enabled) c.accent else c.secondary, modifier = Modifier.size(34.dp))
    }
}

@Composable
private fun OnlineRow(device: OnlineDevice) {
    val c = K.colors
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Rounded.Smartphone, null, tint = c.accent, modifier = Modifier.size(32.dp))
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Label(device.name.ifEmpty { "未命名手机" }, 24.sp, c.label, FontWeight.Medium)
            Label(device.remote.ifEmpty { "—" }, 19.sp, c.secondary)
        }
    }
}

/**
 * 诊断卡：长期运行时唯一能「事后定位」的东西。
 *
 * 不做每秒落盘 —— 数字全在内存，只有点「导出日志」时才写一个文件，
 * 放到 `Android/data/<包名>/files/diag/`，路径直接显示在卡片里。
 */
@Composable
private fun DiagCard(state: RemoteUiState) {
    val c = K.colors
    val context = LocalContext.current
    var exporting by remember { mutableStateOf(false) }
    var exported by remember { mutableStateOf("") }
    val h = state.health

    Column(
        Modifier
            .fillMaxWidth()
            .background(c.fill, RoundedCornerShape(22.dp))
            .padding(28.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Label("运行诊断", 28.sp, c.label, FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            PillButton(if (exporting) "导出中…" else "导出日志", null) {
                if (exporting) return@PillButton
                exporting = true
                thread(name = "diag-export", isDaemon = true) {
                    val file = RemoteHealth.export(
                        context,
                        state.connected,
                        h?.pendingFrames ?: 0,
                        h?.pendingBytes ?: 0L,
                    )
                    exported = file?.absolutePath ?: "导出失败，看 logcat RemoteDiag"
                    exporting = false
                }
            }
        }
        Spacer(Modifier.height(14.dp))
        if (h == null) {
            Label("还没有数据（服务开启后 2 秒内出现）", 20.sp, c.secondary)
            return@Column
        }
        Label(
            "运行 ${RemoteHealth.fmtDuration(h.uptimeMs)} · 在线 ${h.clients}/${state.maxClients} · 峰值 ${h.peakClients}",
            20.sp, c.secondary,
        )
        Label(
            "线程 ${h.threads}（遥控相关 ${h.remoteThreads}）· 累计连接 ${h.connects} / 断开 ${h.disconnects} / 拒绝 ${h.rejected}",
            20.sp, c.secondary,
        )
        Label(
            "消息 入 ${h.messagesIn} 出 ${h.messagesOut} · 队列 ${h.pendingFrames} 帧 / ${h.pendingBytes / 1024} KB",
            20.sp,
            if (h.pendingFrames > 80) Color(0xFFFF9500) else c.secondary,
        )
        Label(
            "状态合并 ${h.stateCoalesced} · 丢状态 ${h.stateDropped} · 慢客户端断开 ${h.slowClientCloses}",
            20.sp, c.secondary,
        )
        val netease = when {
            h.neteaseCount == 0L -> "网易云请求：暂无"
            else -> "网易云请求：最近 ${h.neteaseLastMs}ms · 平均 ${h.neteaseAvgMs}ms · 最慢 ${h.neteaseMaxMs}ms · 失败 ${h.neteaseFailures}"
        }
        Label(netease, 20.sp, c.secondary)
        Label(
            "歌单缓存 ${state.cacheEntries} / ${state.cacheLimit} 份 · ${fmtMb(state.cacheBytes)} MB",
            20.sp,
            if (state.cacheEntries > state.cacheLimit) Color(0xFFFF9500) else c.secondary,
        )
        Label("网络变化 ${h.networkChanges} 次 · mDNS 重建 ${h.mdnsRebuilds} 次", 20.sp, c.secondary)
        h.errors.takeLast(2).forEach { Label(it, 17.sp, c.secondary) }
        if (exported.isNotEmpty()) {
            Spacer(Modifier.height(6.dp))
            Label(exported, 17.sp, c.accent, maxLines = 2)
        }
    }
}
