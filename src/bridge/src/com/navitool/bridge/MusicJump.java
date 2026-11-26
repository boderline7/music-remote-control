package com.navitool.bridge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 悬浮音乐卡片 →「网易云音乐」（com.cloudmusic.car）的歌词大屏。
 *
 * 第三方网易云的歌词大屏不是独立 Activity，而是 Compose 里的一层浮层
 * （Nav.nowPlayingOpen），外面没法直接 startActivity。它自己在 MainActivity 里留了
 * 一个外部入口：发 {@link #ACTION_OPEN_LYRICS} 过去，onCreate / onNewIntent 收到就
 * 把浮层打开。
 *
 * 三个细节：
 * 1. **显式指定组件**，不依赖对方清单里的 intent-filter 有没有被改坏。
 * 2. **带 NEW_TASK**：卡片可能在悬浮窗 / 非 Activity 上下文里被点到。
 * 3. **不做 getPackageInfo 前置检查**：Android 11+ 的包可见性在部分 ROM 上会把
 *    这个调用判成 NameNotFound，白白回落。直接 startActivity，抛异常再退。
 *
 * 返回 false 表示没跳成（没装 / 被后台启动限制拦下 / 组件名变了），调用方回落到
 * 助手自己的内置歌词页，不至于点了没反应。
 *
 * 注入点：DesktopAssistantActivity.openLyricsFxPage 入口——点歌名、点歌词行、广播
 * 唤起三条路径都经过它。
 */
public final class MusicJump {

    public static final String PKG = "com.cloudmusic.car";
    public static final String ACTION_OPEN_LYRICS = "com.cloudmusic.car.OPEN_LYRICS";
    private static final String TAG = "NavitoolMusicJump";

    private MusicJump() {
    }

    /** @return 是否已经成功把意图发出去。 */
    public static boolean openCloudLyrics(Context ctx) {
        if (ctx == null) {
            return false;
        }
        // 路径一：显式组件，等价于 am start -n com.cloudmusic.car/.MainActivity -a ...
        if (send(ctx, explicit())) {
            return true;
        }
        // 路径二：对方清单里也声明了同名 action，显式组件被挡时用隐式再试一次。
        return send(ctx, implicit());
    }

    private static Intent explicit() {
        Intent i = new Intent(ACTION_OPEN_LYRICS);
        i.setComponent(new ComponentName(PKG, PKG + ".MainActivity"));
        return i;
    }

    private static Intent implicit() {
        Intent i = new Intent(ACTION_OPEN_LYRICS);
        i.setPackage(PKG);
        return i;
    }

    private static boolean send(Context ctx, Intent intent) {
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            ctx.startActivity(intent);
            Log.i(TAG, "已拉起网易云歌词大屏: " + intent);
            return true;
        } catch (Throwable t) {
            Log.w(TAG, "拉起失败(" + intent + "): " + t);
            return false;
        }
    }
}
