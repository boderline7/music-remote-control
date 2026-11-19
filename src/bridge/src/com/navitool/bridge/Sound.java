package com.navitool.bridge;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 联动播报音量控制。
 *
 * 原厂 LogLinkageManager.lambda$playMediaFile$12 每次播报前读
 * SharedPreferencesUtils.getLogMediaVolume(ctx)，它落在 navi_tool_prefs 的
 * log_media_volume（默认 40，范围 0-100），再 MediaPlayer.setVolume(v/100*2)：
 * 50 即满音量，50-100 都会被截到 1.0。
 *
 * 之前写错到 com.navi.tool_preferences，所以调了没反应。
 * 这里直接写 navi_tool_prefs，并统一按 0-50 量程，网页滑块 0-100 映射到 0-50。
 */
public final class Sound {
    private static final String PREFS = "navi_tool_prefs";
    private static final String KEY = "log_media_volume";
    private static final int MAX = 50;

    private Sound() {
    }

    private static SharedPreferences prefs(Context ctx) {
        return ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    /** 网页显示值 0-100 -> 原厂存储值 0-50。 */
    private static int toStored(int display) {
        int v = display / 2;
        if (v < 0) v = 0;
        if (v > MAX) v = MAX;
        return v;
    }

    private static int toDisplay(int stored) {
        int v = stored * 2;
        if (v < 0) v = 0;
        if (v > 100) v = 100;
        return v;
    }

    public static String json(Context ctx) {
        int stored = prefs(ctx).getInt(KEY, 40);
        return "{\"ok\":1,\"volume\":" + toDisplay(stored) + ",\"stored\":" + stored + "}";
    }

    /** display: 网页滑块 0-100；写原厂 0-50，下一次播报立即生效。 */
    public static String setJson(Context ctx, int display) {
        int stored = toStored(display);
        try {
            prefs(ctx).edit().putInt(KEY, stored).apply();
        } catch (Throwable t) {
            return "{\"ok\":0,\"err\":\"" + Bridge.esc(Bridge.root(t)) + "\"}";
        }
        return "{\"ok\":1,\"volume\":" + toDisplay(stored) + ",\"stored\":" + stored + "}";
    }
}
