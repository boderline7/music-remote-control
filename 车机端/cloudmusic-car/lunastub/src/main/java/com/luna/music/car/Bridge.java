package com.luna.music.car;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

/**
 * 车机助手（com.navi.tool）与车上的音乐来源之间的桥。
 *
 * ## 为什么要这个包
 *
 * 助手的音源候选表是硬编码的，按包名判断「是否已安装」：
 *
 * <pre>
 *   cn.kuwo.kwmusiccar        → source 1 酷我音乐车机版
 *   com.kugou.android.auto    → source 2 酷狗音乐车机版
 *   com.netease.cloudmusic.iot→ source 3 网易云音乐车机版
 *   com.luna.music.car        → source 5 汽水音乐车机版
 *   其余                      → source 6 通用音源（拿歌名去酷我搜，会串词、费流量）
 * </pre>
 *
 * 前三个包名都被真实 App 占着，装不上第二个；只有 {@code com.luna.music.car} 是空的，
 * 所以这个包必须存在，助手才会出现「汽水音乐车机版」这一项。
 *
 * 而 source 5 是四条通道里最开放的一条：纯广播、字符串传参，助手侧注册时是
 * {@code RECEIVER_EXPORTED}，既没有签名校验也没有包名校验，谁发都收。
 *
 * ## 两种模式
 *
 * - **THIRD_PARTY**：车上有第三方自研播放器 {@code com.cloudmusic.car}。此时维持老规则——
 *   状态/歌词全部由它自己的 LunaCompat 发，本包一行广播都不发，只负责把助手列表里
 *   这一项撑起来。两边同时发会让卡片来回跳。
 * - **OFFICIAL**：只有官方车机版 {@code com.netease.cloudmusic.iot}。本包自己当 player，
 *   用 MediaBrowser 连它的 {@code AutomotiveService} 拿 MediaController，把它的
 *   MediaSession 翻译成助手的五条广播，并把助手的控制指令转回去。
 *
 * 判定由 {@link Mode#current} 统一负责；默认 THIRD_PARTY（第三方自研播放器，本包不占端口）。
 */
class Bridge {

    private static final String TAG = "LunaBridge";

    /** 车机助手。广播定向投递用，避免惊动其它注册了同名 action 的进程。 */
    static final String NAVI = "com.navi.tool";
    /** 第三方自研播放器（自带 LunaCompat）。 */
    static final String THIRD_PARTY = "com.cloudmusic.car";
    /** 官方网易云车机版。 */
    static final String OFFICIAL = "com.netease.cloudmusic.iot";
    /** 官方 MediaBrowserService。注意类名在 .service 包下，不是 .iot 下。 */
    private static final String OFFICIAL_SERVICE = "com.netease.cloudmusic.service.AutomotiveService";

    private static final String A_STATE = "com.luna.music.car.PLAYER_STATE";
    private static final String A_LINE = "com.luna.music.car.LYRIC_LINE";
    private static final String A_FULL = "com.luna.music.car.LYRIC_FULL";
    private static final String A_CONTROL = "com.luna.music.car.PLAYER_CONTROL";
    private static final String A_SYNC = "com.luna.music.car.PLAYER_SYNC";

    /** 官方 App 把整首带时间轴的 LRC 放在这个自定义 key 里（不是标准字段）。 */
    private static final String K_LYRIC = "android.media.metadata.LYRIC";

    /** 上报间隔。必须小于助手看门狗的 1800ms，否则它会以为暂停了。 */
    private static final long TICK_MS = 1_000L;

    /** 断开重连的退避。 */
    private static final long RECONNECT_MS = 5_000L;

    static final int MODE_IDLE = 0;
    static final int MODE_OFFICIAL = Mode.OFFICIAL;

    static final Bridge I = new Bridge();

    private Context ctx;
    private int mode = MODE_IDLE;

    private MediaBrowser browser;
    private MediaController ctl;
    private MediaSession.Token token;

    private boolean registered;
    private boolean ticking;

    /** 上一次推送的「歌名|歌手」，用来发现换歌。 */
    private String lastKey = "";
    /** 上一次的播放态，用来决定暂停时要不要补一条。 */
    private boolean lastPlaying;
    /** 暂停期间的位置，用来发现用户拖了进度条。 */
    private long pausedPos = -1L;
    /** 上一次推送的歌词指纹，避免每秒重发整首 LRC。 */
    private String lastLyricKey = "";

    /**
     * 最近一次爱心请求的归属与期望值；真实状态统一存在 {@link Star}（网页端共用）。
     * 官方 App 收到 star_action 不会回头改 USER_RATING，只能靠乐观值 + 回执码落定。
     */
    private String starId = "";
    private boolean starWanted;

    private final Handler handler = new Handler(Looper.getMainLooper());

    private Bridge() {
    }

    // ---------- 启动 ----------

    /** 装没装只看包名，助手也是这么判的。 */
    int detect(Context context) {
        return Mode.current(context);
    }

    /** 桥接模式才需要常驻；THIRD_PARTY 模式下本包什么都不做。 */
    boolean shouldRun(Context context) {
        return Mode.isOfficial(context);
    }

    void start(Context context) {
        if (ctx != null) return;
        ctx = context.getApplicationContext();
        mode = Mode.current(ctx);
        Log.i(TAG, "音源=" + Mode.label(mode));
        if (mode != MODE_OFFICIAL) return;
        register();
        connect();
        startTick();
    }

    /** 停掉桥：切音源或服务销毁时调用，之后再 start 能干净重来。 */
    void stopAll() {
        ticking = false;
        try { handler.removeCallbacks(tick); } catch (Throwable ignored) {}
        if (registered && ctx != null) {
            try { ctx.unregisterReceiver(receiver); } catch (Throwable ignored) {}
        }
        registered = false;
        teardown();
        ctx = null;
        mode = MODE_IDLE;
    }

    int mode() {
        return mode;
    }

    /** 官方 MediaSession 是否连上了，入口页与网页设置都显示这个。 */
    boolean connected() {
        return ctl != null;
    }

    // ---------- 连官方 App ----------

    private void connect() {
        if (ctx == null || browser != null) return;
        try {
            browser = new MediaBrowser(ctx, new ComponentName(OFFICIAL, OFFICIAL_SERVICE), conn, null);
            browser.connect();
            Log.i(TAG, "正在连 " + OFFICIAL_SERVICE);
        } catch (Throwable t) {
            Log.w(TAG, "连接失败", t);
            browser = null;
            scheduleReconnect();
        }
    }

    private void scheduleReconnect() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mode == MODE_OFFICIAL) connect();
            }
        }, RECONNECT_MS);
    }

    private void teardown() {
        if (ctl != null) {
            try { ctl.unregisterCallback(cb); } catch (Throwable ignored) {}
            ctl = null;
        }
        if (browser != null) {
            try { browser.disconnect(); } catch (Throwable ignored) {}
            browser = null;
        }
        token = null;
    }

    private final MediaBrowser.ConnectionCallback conn = new MediaBrowser.ConnectionCallback() {
        @Override
        public void onConnected() {
            try {
                token = browser.getSessionToken();
                ctl = new MediaController(ctx, token);
                ctl.registerCallback(cb, handler);
                Log.i(TAG, "已接管官方 App 的 MediaSession");
                // 助手可能刚发过 SYNC 而我们还连不上，这里补一次完整状态。
                lastKey = "";
                pushState();
                pushFull(true);
            } catch (Throwable t) {
                Log.w(TAG, "接管失败", t);
                scheduleReconnect();
            }
        }

        @Override
        public void onConnectionSuspended() {
            Log.w(TAG, "连接被挂起");
            teardown();
            scheduleReconnect();
        }

        @Override
        public void onConnectionFailed() {
            Log.w(TAG, "连接失败，稍后重试");
            teardown();
            scheduleReconnect();
        }
    };

    private final MediaController.Callback cb = new MediaController.Callback() {
        @Override
        public void onMetadataChanged(MediaMetadata metadata) {
            // 换歌要立刻推，不能等下一个 tick（助手换歌时会清锚点）。
            lastKey = "";
            pushState();
            pushFull(true);
        }

        @Override
        public void onPlaybackStateChanged(PlaybackState state) {
            pushState();
        }

        @Override
        public void onSessionDestroyed() {
            Log.w(TAG, "官方 App 的会话没了，重连");
            teardown();
            scheduleReconnect();
        }

        @Override
        public void onSessionEvent(String action, Bundle extras) {
            // 爱心回执：code 0 成功 / -2 已经是该状态 / -3 当前内容不支持收藏
            if (!"star_action".equals(action)) return;
            int code = extras == null ? 0 : extras.getInt("code", 0);
            String msg = extras == null ? "" : extras.getString("message", "");
            Log.i(TAG, "star_action 回执 code=" + code + " msg=" + msg);
            boolean ok = code == 0 || code == -2;
            Star.I.set(starId, ok ? starWanted : !starWanted);
            Liked.I.mark(starId, ok ? starWanted : !starWanted);
            pushState();
        }
    };

    // ---------- 收助手指令 ----------

    private void register() {
        if (registered) return;
        IntentFilter f = new IntentFilter();
        f.addAction(A_SYNC);
        f.addAction(A_CONTROL);
        try {
            // 助手是隐式广播 + 无权限，必须导出才能收到。
            ctx.registerReceiver(receiver, f, Context.RECEIVER_EXPORTED);
            registered = true;
            Log.i(TAG, "已监听助手的 PLAYER_SYNC / PLAYER_CONTROL");
        } catch (Throwable t) {
            Log.w(TAG, "注册广播失败", t);
        }
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) return;
            String action = intent.getAction();
            Log.i(TAG, "收到广播 " + action);
            if (A_SYNC.equals(action)) {
                pushState();
                pushFull(true);
            } else if (A_CONTROL.equals(action)) {
                int type = intent.getIntExtra("controlType", -1);
                Log.i(TAG, "控制指令 controlType=" + type);
                control(type);
            }
        }
    };

    private void control(int type) {
        MediaController.TransportControls tc = controls();
        if (tc == null) return;
        try {
            if (type == 0) {
                if (isPlaying()) tc.pause();
                else tc.play();
            } else if (type == 1) {
                // 整单播已交给官方 playlist_play，上一首直接让官方切
                LogCapture.log("助手 PREV → 官方 skipToPrevious");
                tc.skipToPrevious();
            } else if (type == 2) {
                // 走 Ncm 的统一入口：单曲点播期间按下一首必须接管到随机歌单，
                // 直接 skipToNext 会停在「同一首歌不同版本」的点播队列里。
                int code = Ncm.I.execute("next", J.o());
                if (code != 0) {
                    LogCapture.log("助手 NEXT → 官方 skipToNext（Ncm 不可用）");
                    tc.skipToNext();
                } else {
                    LogCapture.log("助手 NEXT → Ncm 统一入口");
                }
            } else if (type == 3 || type == 4) {
                // 助手的爱心只认 controlType 3/4。官方 App 没实现 setRating，
                // 真正的通道是 star_action + is_star，内部走 addToFavorite / unDoFavorite。
                star(type == 3);
            }
        } catch (Throwable t) {
            Log.w(TAG, "控制失败 controlType=" + type, t);
        }
    }

    /**
     * 爱心：官方只认 {@code star_action} + {@code is_star}（true 收藏 / false 取消），
     * 内部走 IIotClient.addToFavorite / unDoFavorite。
     * 先乐观置状态让助手卡片立刻变红，回执到了再校正（见 {@link #cb} 的 onSessionEvent）。
     */
    private void star(boolean on) {
        MediaController.TransportControls tc = controls();
        MediaMetadata md = meta();
        if (tc == null || md == null || str(md, MediaMetadata.METADATA_KEY_TITLE).length() == 0) return;
        try {
            Bundle b = new Bundle();
            b.putBoolean("is_star", on);
            starWanted = on;
            starId = str(md, MediaMetadata.METADATA_KEY_MEDIA_ID);
            Star.I.set(starId, on);
            Liked.I.mark(starId, on);
            tc.sendCustomAction("star_action", b);
            Log.i(TAG, "star_action is_star=" + on + " id=" + starId);
            pushState();
        } catch (Throwable t) {
            Log.w(TAG, "star_action 失败", t);
        }
    }

    private MediaController.TransportControls controls() {
        try {
            return ctl == null ? null : ctl.getTransportControls();
        } catch (Throwable t) {
            return null;
        }
    }

    // ---------- 定时上报 ----------

    private void startTick() {
        if (ticking) return;
        ticking = true;
        handler.postDelayed(tick, TICK_MS);
    }

    private final Runnable tick = new Runnable() {
        @Override
        public void run() {
            if (mode != MODE_OFFICIAL) {
                ticking = false;
                return;
            }
            try {
                tickOnce();
            } catch (Throwable t) {
                Log.w(TAG, "tick 异常", t);
            }
            handler.postDelayed(this, TICK_MS);
        }
    };

    private void tickOnce() {
        MediaMetadata md = meta();
        if (md == null) return;
        String title = str(md, MediaMetadata.METADATA_KEY_TITLE);
        if (title.length() == 0) return;

        String key = title + "|" + str(md, MediaMetadata.METADATA_KEY_ARTIST);
        boolean playing = isPlaying();

        if (!key.equals(lastKey)) {
            lastKey = key;
            lastPlaying = playing;
            pausedPos = playing ? -1L : position();
            lastLyricKey = "";
            pushState();
            pushFull(true);
            return;
        }

        if (!playing) {
            // 暂停时助手的看门狗直接 return，不需要心跳；只在刚翻转或拖了进度条时补一条。
            long p = position();
            if (lastPlaying || (pausedPos >= 0 && Math.abs(p - pausedPos) > 1_000L)) {
                lastPlaying = false;
                pausedPos = p;
                pushState();
            }
            return;
        }

        lastPlaying = true;
        pausedPos = -1L;
        // 播放中每秒一条 LYRIC_LINE：它同时写助手的两个锚点（q=position, r=now），
        // 只发 PLAYER_STATE 会让 r 停在换歌那一刻，越唱越飘。
        pushLine();
        pushFull(false);
    }

    // ---------- 上报内容 ----------

    private void pushState() {
        MediaMetadata md = meta();
        if (md == null || str(md, MediaMetadata.METADATA_KEY_TITLE).length() == 0) return;
        Intent i = base(A_STATE, md);
        i.putExtra("duration", duration(md));
        i.putExtra("position", position());
        i.putExtra("isPlaying", isPlaying());
        i.putExtra("isCollected", collected(md));
        i.putExtra("loopMode", "LIST");
        send(i);
    }

    private void pushLine() {
        MediaMetadata md = meta();
        if (md == null || str(md, MediaMetadata.METADATA_KEY_TITLE).length() == 0) return;
        Intent i = base(A_LINE, md);
        i.putExtra("duration", duration(md));
        i.putExtra("position", position());
        i.putExtra("isCollected", collected(md));
        i.putExtra("loopMode", "LIST");
        // 不传 currentLine：助手会自己按校准后的位置在歌词表里取行。
        send(i);
    }

    private void pushFull(boolean force) {
        MediaMetadata md = meta();
        if (md == null) return;
        String lrc = str(md, K_LYRIC);
        if (lrc.length() == 0) return;
        String key = lastKey + "#" + lrc.hashCode();
        if (!force && key.equals(lastLyricKey)) return;
        lastLyricKey = key;
        Intent i = base(A_FULL, md);
        i.putExtra("lrc", lrc);
        send(i);
    }

    /** title / artist / coverUrl 三个字段助手每条都要读，缺一个会被当成空歌曲丢掉。 */
    private Intent base(String action, MediaMetadata md) {
        Intent i = new Intent(action);
        i.putExtra("title", str(md, MediaMetadata.METADATA_KEY_TITLE));
        i.putExtra("artist", str(md, MediaMetadata.METADATA_KEY_ARTIST));
        i.putExtra("coverUrl", str(md, MediaMetadata.METADATA_KEY_ALBUM_ART_URI));
        return i;
    }

    private void send(Intent intent) {
        if (ctx == null) return;
        intent.setPackage(NAVI);
        try {
            ctx.sendBroadcast(intent);
        } catch (Throwable t) {
            Log.w(TAG, "发送失败 " + intent.getAction(), t);
        }
    }

    // ---------- 读官方 App 的状态 ----------

    private MediaMetadata meta() {
        try {
            return ctl == null ? null : ctl.getMetadata();
        } catch (Throwable t) {
            return null;
        }
    }

    private boolean isPlaying() {
        try {
            return isPlaying(ctl == null ? null : ctl.getPlaybackState());
        } catch (Throwable t) {
            return false;
        }
    }

    /**
     * 缓冲也算在播。
     *
     * 直接用 {@code STATE_PLAYING} 判会让缓冲瞬间变成「暂停」发出去，助手的锚点被清掉，
     * 网络差的时候卡片会一直闪暂停。
     */
    private static boolean isPlaying(PlaybackState ps) {
        if (ps == null) return false;
        int s = ps.getState();
        return s == PlaybackState.STATE_PLAYING
                || s == PlaybackState.STATE_BUFFERING
                || s == PlaybackState.STATE_CONNECTING
                || s == PlaybackState.STATE_FAST_FORWARDING
                || s == PlaybackState.STATE_REWINDING;
    }

    /** PlaybackState 里的 position 是上次更新那一刻的值，要按经过时间补。 */
    private long position() {
        try {
            PlaybackState ps = ctl == null ? null : ctl.getPlaybackState();
            if (ps == null) return 0L;
            long p = ps.getPosition();
            if (isPlaying(ps) && ps.getLastPositionUpdateTime() > 0) {
                p += SystemClock.elapsedRealtime() - ps.getLastPositionUpdateTime();
            }
            return Math.max(0L, p);
        } catch (Throwable t) {
            return 0L;
        }
    }

    private static long duration(MediaMetadata md) {
        try {
            return md.getLong(MediaMetadata.METADATA_KEY_DURATION);
        } catch (Throwable t) {
            return 0L;
        }
    }

    private boolean collected(MediaMetadata md) {
        String id = str(md, MediaMetadata.METADATA_KEY_MEDIA_ID);
        int star = Star.I.get(id);
        if (star >= 0) return star == 1;
        // 官方不写 USER_RATING：真正收藏过的歌靠喜欢歌单查询表认出来
        int cached = Liked.I.state(id);
        if (cached >= 0) return cached == 1;
        try {
            Rating r = md.getRating(MediaMetadata.METADATA_KEY_USER_RATING);
            return r != null && r.isThumbUp();
        } catch (Throwable t) {
            return false;
        }
    }

    private static String str(MediaMetadata md, String key) {
        if (md == null) return "";
        try {
            String s = md.getString(key);
            return s == null ? "" : s;
        } catch (Throwable t) {
            return "";
        }
    }
}
