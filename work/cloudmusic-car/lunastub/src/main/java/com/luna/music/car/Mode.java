package com.luna.music.car;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 播放引擎模式 —— 车上到底谁在放歌、谁占 18765。
 *
 * 按包名认两种可能：
 *
 * <pre>
 *   THIRD_PARTY  com.cloudmusic.car       第三方自研播放器（完整 App，自己登录自己播）
 *   OFFICIAL     com.netease.cloudmusic.iot 官方网易云车机版，本包桥接它的 MediaSession
 * </pre>
 *
 * 只装了一个就直接用那一个，界面不给选；两个都在才让用户在入口页选。默认 THIRD_PARTY ——
 * 两个都装时把 18765 让给第三方自研播放器，本包默认不绑端口（想桥接官方再手动切）。
 */
final class Mode {

    static final int NONE = 0;
    static final int THIRD_PARTY = 1;
    static final int OFFICIAL = 2;

    static final String THIRD_PARTY_PKG = "com.cloudmusic.car";
    static final String OFFICIAL_PKG = "com.netease.cloudmusic.iot";

    /** 跨包同步用的广播：app_src 切模式时发给我们，我们切时发给它。 */
    static final String SYNC_ACTION = "com.cloudmusic.car.remote.MODE_SYNC";
    static final String SYNC_EXTRA = "mode";

    private static final String SP = "source-mode";
    private static final String KEY = "source";

    private Mode() {
    }

    static boolean installed(Context c, String pkg) {
        try {
            PackageManager pm = c.getPackageManager();
            pm.getPackageInfo(pkg, 0);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    static boolean hasThirdParty(Context c) {
        return installed(c, THIRD_PARTY_PKG);
    }

    static boolean hasOfficial(Context c) {
        return installed(c, OFFICIAL_PKG);
    }

    /** 两个引擎都在，需要用户自己挑。 */
    static boolean both(Context c) {
        return hasThirdParty(c) && hasOfficial(c);
    }

    /** 用户存过的选择；0 表示还没选过。 */
    static int saved(Context c) {
        try {
            SharedPreferences sp = c.getSharedPreferences(SP, Context.MODE_PRIVATE);
            return sp.getInt(KEY, 0);
        } catch (Throwable t) {
            return 0;
        }
    }

    static void save(Context c, int v) {
        try {
            SharedPreferences.Editor e = c.getSharedPreferences(SP, Context.MODE_PRIVATE).edit();
            e.putInt(KEY, v);
            e.apply();
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "save mode failed", t);
        }
    }

    /** 当前生效的模式。只有一个可用时直接就是它，忽略历史选择。 */
    static int current(Context c) {
        boolean t = hasThirdParty(c);
        boolean o = hasOfficial(c);
        if (t && o) {
            // 默认 THIRD_PARTY：没选过 / 旧值无效都落到第三方自研播放器，本包不占 18765。
            // 只有用户明确存过 OFFICIAL 才切回官方桥接。
            return saved(c) == OFFICIAL ? OFFICIAL : THIRD_PARTY;
        }
        if (o) return OFFICIAL;
        if (t) return THIRD_PARTY;
        return NONE;
    }

    static boolean isOfficial(Context c) {
        return current(c) == OFFICIAL;
    }

    static boolean isThirdParty(Context c) {
        return current(c) == THIRD_PARTY;
    }

    static String label(int v) {
        if (v == OFFICIAL) return "官方网易云车机版";
        if (v == THIRD_PARTY) return "第三方自研播放器";
        return "未找到引擎";
    }

    static String name(Context c) {
        return label(current(c));
    }
}
