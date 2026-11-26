package com.cloudmusic.car

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.cloudmusic.car.data.Settings
import com.cloudmusic.car.player.PlaybackService
import com.cloudmusic.car.ui.AppRoot
import com.cloudmusic.car.ui.UiIntent
import com.cloudmusic.car.ui.theme.CloudMusicTheme

class MainActivity : ComponentActivity() {
    private var controller: ListenableFuture<MediaController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 冷启动也可能是外部拉起来的：savedInstanceState 为空说明是全新意图，
        // 有值说明只是进程被回收后的重建，不能把用户已收起的歌词大屏又弹出来。
        if (savedInstanceState == null) handleExternalIntent(intent)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
        }
        setContent {
            val fullscreen by Settings.fullscreen.collectAsState()
            // 设置里一改就立刻生效，不用重启：隐藏 / 恢复系统状态栏与导航栏
            LaunchedEffect(fullscreen) { applyFullscreen(fullscreen) }
            CloudMusicTheme { AppRoot() }
        }
    }

    override fun onResume() {
        super.onResume()
        // 从后台回来时系统栏可能被系统重新显示，按当前设置再压一次
        applyFullscreen(Settings.fullscreen.value)
    }

    /** 全屏 = 隐藏状态栏与导航栏（下拉可临时呼出）；关闭 = 保持系统默认显示。 */
    private fun applyFullscreen(hide: Boolean) {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        if (hide) {
            controller.hide(WindowInsetsCompat.Type.systemBars())
        } else {
            controller.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // singleTask：App 已在栈里时反复点击只会走到这里，不会再 onCreate。
        setIntent(intent)
        handleExternalIntent(intent)
    }

    /**
     * 外部入口。目前只有车机助手的「点悬浮音乐卡片 → 直接进歌词大屏」。
     *
     * 车机助手不认包内页面，只会发 action，所以这里按 action 分发；页面状态交给
     * [UiIntent]，由 AppRoot 在 Compose 里消费（Nav 只存在于组合树中）。
     */
    private fun handleExternalIntent(intent: Intent?) {
        when (intent?.action) {
            UiIntent.ACTION_OPEN_LYRICS -> UiIntent.requestLyrics()
        }
    }

    override fun onStart() {
        super.onStart()
        // 绑定媒体会话服务，使播放时能进入前台并显示系统通知栏控制。
        controller = MediaController.Builder(this, SessionToken(this, ComponentName(this, PlaybackService::class.java)))
            .buildAsync()
    }

    override fun onStop() {
        controller?.let { MediaController.releaseFuture(it) }
        controller = null
        super.onStop()
    }
}
