package com.luna.music.car;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 与官方网易云车机版绑定的桥接层。
 *
 * 只走两条已实机验证的稳定通道：
 *   1. MediaBrowser(AutomotiveService) → sessionToken → MediaController：状态 / 歌词 / 队列 / 播控 / custom action
 *   2. VoiceService 显式 Intent：vr_play_favour_music（我喜欢的音乐）
 * 不碰 weapi/eapi、不碰第三方登录、不需要通知使用权。
 */
final class Ncm {
    static final Ncm I = new Ncm();

    private Context ctx;
    private AudioManager am;
    private Handler main;
    private MediaBrowser browser;
    private MediaController ctl;
    /** 回调只能注册一次：重连时先注销旧的，否则同一个 Media ID 变化会触发多次接管。 */
    private NcmSess sess;
    private volatile boolean connected;
    private String[] playlists;
    /** 歌单名 → 公开页 id；曲目列表走公开 Web API，不碰登录态、不碰 weapi/eapi。 */
    private final ConcurrentHashMap<String, String> playlistIds = new ConcurrentHashMap<String, String>();
    private final ConcurrentHashMap<String, JSONArray> trackCache = new ConcurrentHashMap<String, JSONArray>();
    /** 每张歌单的抓取时刻：官方 App 里改过歌单后，旧列表最多留 10 分钟。 */
    private final ConcurrentHashMap<String, Long> trackAt = new ConcurrentHashMap<String, Long>();
    private static final long TRACK_TTL = 10 * 60 * 1000L;
    /** 歌单归属人 id，只用于过滤公开搜索结果，避免把别人的同名歌单当成自己的。 */
    private volatile long selfUid = 0;
    /** 抓取失败原因按线程隔离：多台设备同时点不同歌单时，A 的失败原因不能串到 B 的提示里。 */
    private final ThreadLocal<String> fetchErr = new ThreadLocal<String>();

    /**
     * 爱心真实状态：-1 未知 / 0 未收藏 / 1 已收藏。
     *
     * 官方 App 收到 star_action 后不会回头改 MediaMetadata 的 USER_RATING，
     * 所以光读 metadata 会永远停在「未收藏」。真实状态存在 {@link Star}（与助手卡片共用），
     * 这里只留最近一次请求的期望值与归属曲目，供回执落定。
     */
    /** 最近一次请求的期望值，回执 code=0 时用它落定。 */
    private volatile boolean likeWanted;
    /** 最近一次请求归属的曲目（MEDIA_ID）；换歌就作废，避免上一首的红心跟着跑到下一首。 */
    private volatile String likedId = "";

    // ---- 单曲点播接管：官方版本循环的出路 ----
    // ---------- 单曲点播状态机 ----------
    // 官方没有「按歌曲 ID 点播」入口，单曲只能走 IOT 语音搜索通道；代价是官方生成
    // 「只含当前歌曲不同版本」的队列无限循环版本。对策：点播后进入状态机监视，
    // 检测到官方切到别的版本 / 目标歌播完 → 接管成随机歌单整单播放，把循环打断。
    //
    // 用显式状态而不是几个 boolean：语音点播的回调存在四类竞态（旧状态残留、
    // 状态更新延迟、重复事件、接管后旧队列回推），散落的布尔无法保证「先确认再监视」
    // 这个顺序，也无法在接管后彻底屏蔽旧事件。每次点播递增 reqId，日志按 reqId 串起来。
    static final int PO_IDLE = 0;        // 没有点播在跟踪
    static final int PO_REQUESTING = 1;  // 正在下发语音点播指令
    static final int PO_CONFIRMING = 2;  // 指令已发，等官方真正播到目标曲目
    static final int PO_MONITORING = 3;  // 已确认起播，监视版本切换 / 播完
    static final int PO_TAKEOVER = 4;    // 已接管，期间屏蔽一切旧队列事件
    /**
     * 确认起播后至少听这么久才允许「因版本切换」接管。
     * 官方点播后两三秒就会切到伴奏/别的版本，没有这道门槛会让每首歌只响两三秒。
     */
    static final long PO_MIN_PLAY_MS = 15000L;

    volatile int poState = PO_IDLE;
    /** 点播请求序号：回调处理时会核对，防止上一轮点播的迟到事件污染这一轮。 */
    volatile long poReq = 0;
    /** 进入当前状态的时间，超时与耗时统计都用它。 */
    volatile long poStateAt = 0;
    volatile String poTitle = "";
    volatile String poArtist = "";
    volatile String poMediaId = "";
    volatile String poListId = "";
    volatile String poListName = "";
    /** 上一次记进日志的播放状态，用来去重（官方会高频重复推同一状态）。 */
    volatile int lastLogState = -1;
    /** 上一次的播放状态：判断「播放/缓冲 → 停止」这种由播放到结束的边沿。 */
    volatile int lastPlayState = -1;
    /**
     * 自建歌单的「假队列」。官方没有整单播放入口（orpheus://playlist_play 在主 App 里，
     * 车机版没有对应页面，发出去只回成功不切歌），所以只能逐首语音点播：
     * 每首歌起播后由状态机跟上，播完/被官方换版本时自动点下一首。
     */
    final ArrayList<String[]> poQueue = new ArrayList<String[]>();
    volatile int poQueueIdx = -1;
    /** 队列属于哪个歌单（id）：单曲点播带歌单时按它判断要不要重建队列。 */
    volatile String poQueueOwner = "";
    /** 已打过「队列未就绪」日志的 req，避免每次 metadata 都刷一条。 */
    volatile long poQueueWaitReq = -1;
    /** metadata 去重键：官方对同一首会连推 4-6 次完全相同的 META，不去重会把日志刷爆。 */
    volatile String lastMetaKey = "";
    /** 本首开始持续播放的时刻：STOPPED 时用它区分「自然播完」和「用户手动停止」。 */
    volatile long poPlayingSince = 0;
    /** 本首播放过程中出现过暂停：暂停后立刻 STOPPED 基本都是用户手动停的，不该接管。 */
    volatile boolean poSawPause = false;
    /** 正在后台抓取的歌单名：两个线程同时点播时，避免同一歌单被抓两遍、洗牌互相覆盖。 */
    volatile String poBuilding = "";

    private static String poName(int s) {
        switch (s) {
            case PO_REQUESTING: return "REQUESTING";
            case PO_CONFIRMING: return "CONFIRMING";
            case PO_MONITORING: return "MONITORING";
            case PO_TAKEOVER: return "TAKEOVER";
            default: return "IDLE";
        }
    }

    /** 状态迁移统一入口：所有字段在同一把锁里改，日志带 reqId/from/to/mediaId/线程。 */
    private void poTo(long req, int to, String why, String mid) {
        synchronized (this) {
            if (req != 0 && req != poReq) return;   // 过期请求的迁移一律丢弃（含回 IDLE）
            int from = poState;
            poState = to;
            poStateAt = System.currentTimeMillis();
            LogCapture.log("PO req=" + poReq + " " + poName(from) + "→" + poName(to)
                    + (why == null || why.length() == 0 ? "" : " " + why)
                    + " mid=" + (mid == null ? poMediaId : mid)
                    + " song=" + poTitle
                    + " tid=" + Thread.currentThread().getId());
        }
    }

    /** 播放状态数字转可读名字，日志里看 STATE PLAYING 比看 3 直观。 */
    static String stateName(int s) {
        switch (s) {
            case PlaybackState.STATE_NONE: return "NONE";
            case PlaybackState.STATE_STOPPED: return "STOPPED";
            case PlaybackState.STATE_PAUSED: return "PAUSED";
            case PlaybackState.STATE_PLAYING: return "PLAYING";
            case PlaybackState.STATE_FAST_FORWARDING: return "FF";
            case PlaybackState.STATE_REWINDING: return "REW";
            case PlaybackState.STATE_BUFFERING: return "BUFFERING";
            case PlaybackState.STATE_ERROR: return "ERROR";
            case PlaybackState.STATE_CONNECTING: return "CONNECTING";
            case PlaybackState.STATE_SKIPPING_TO_PREVIOUS: return "SKIP_PREV";
            case PlaybackState.STATE_SKIPPING_TO_NEXT: return "SKIP_NEXT";
            case PlaybackState.STATE_SKIPPING_TO_QUEUE_ITEM: return "SKIP_ITEM";
            default: return "S" + s;
        }
    }

    private Ncm() { playlists = Cfg.DEFAULT_PLAYLISTS; }

    synchronized void init(Context c) {
        if (ctx != null) return;
        ctx = c.getApplicationContext();
        am = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
        main = new Handler(Looper.getMainLooper());
        loadPlaylists();
        Liked.I.warm();
        connect();
    }

    boolean isConnected() { return connected && ctl != null; }

    String[] playlists() { return playlists; }

    /**
     * 歌单配置：一行「歌单名|公开页 id」。可改而不必重打包：
     * /sdcard/Android/data/&lt;pkg&gt;/files/playlists.txt
     *
     * 内置清单先垫底，外部文件追加并覆盖同名项。早先是「有外部文件就完全不管内置」，
     * 结果设备上留着一份旧文件时，没写进去的歌单会整张从列表里消失。
     */
    private void loadPlaylists() {
        // 内置歌单先垫底：车主确认过的歌单，名字和公开页 id 一一对应，不依赖名字搜索；
        // 外部 playlists.txt 追加并覆盖同名项。
        java.util.LinkedHashSet<String> names = new java.util.LinkedHashSet<String>();
        String[] builtin = Cfg.DEFAULT_PLAYLISTS;
        String[] builtinIds = Cfg.DEFAULT_PLAYLIST_IDS;
        if (builtin != null) {
            for (int i = 0; i < builtin.length; i++) {
                if (builtin[i] == null || builtin[i].length() == 0) continue;
                names.add(builtin[i]);
                if (builtinIds != null && i < builtinIds.length
                        && builtinIds[i] != null && builtinIds[i].length() > 0) {
                    playlistIds.put(builtin[i], builtinIds[i]);
                }
            }
        }
        // 官方榜排在自己歌单之后：id 固定、官方持续更新，车主不用维护也能一直有内容
        String[] official = Cfg.OFFICIAL_PLAYLISTS;
        String[] officialIds = Cfg.OFFICIAL_PLAYLIST_IDS;
        if (official != null) {
            for (int i = 0; i < official.length; i++) {
                if (official[i] == null || official[i].length() == 0) continue;
                names.add(official[i]);
                if (officialIds != null && i < officialIds.length
                        && officialIds[i] != null && officialIds[i].length() > 0) {
                    playlistIds.put(official[i], officialIds[i]);
                }
            }
        }
        ArrayList<String> lines = new ArrayList<String>();
        try {
            readLines(new InputStreamReader(ctx.getAssets().open("playlists.txt"), "UTF-8"), lines);
        } catch (Throwable ignored) {
        }
        try {
            File dir = ctx.getExternalFilesDir(null);
            File f = dir == null ? null : new File(dir, "playlists.txt");
            if (f != null && f.exists()) readLines(new FileReader(f), lines);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "external playlists.txt unreadable", t);
        }
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int bar = line.indexOf('|');
            String name = (bar > 0 ? line.substring(0, bar) : line).trim();
            String pid = bar > 0 ? line.substring(bar + 1).trim() : "";
            if (name.length() == 0) continue;
            // 歌单在网易云里改过名，旧配置行跟着改名，避免同一个歌单出现两遍
            if ("Borderline 不在".equals(name)) name = "Borderline 或许";
            // 纯数字行是旧配置遗留的「只有 id 没有名字」：显示出来只是一串数字，
            // 用户看到的歌单就变成「不是我的歌单」，还直接播了不认识的东西。
            // 没有名字没法认领，跳过不给用户看。
            if (pid.length() == 0 && isNumeric(name)) {
                Log.i(Cfg.TAG, "playlists.txt 忽略纯数字行（无歌单名）: " + line);
                continue;
            }
            // 早期版本内置过 demo 歌单（妙妙 / Borderline），不少车机的 playlists.txt
            // 至今留着这些行。它们不是车主的歌单，播出来是别人的内容，直接忽略。
            if ("妙妙".equals(name) || "Borderline".equals(name)) {
                Log.i(Cfg.TAG, "playlists.txt 忽略旧 demo 歌单: " + line);
                continue;
            }
            // 没有 id 的名字只能靠搜索碰运气，搜到的很可能是同名别人的歌单——
            // 这正是「歌单不是我的」的来源。只放行内置清单里的名字（已带 id）。
            if (pid.length() == 0 && !names.contains(name)) {
                Log.i(Cfg.TAG, "playlists.txt 忽略无 id 歌单: " + line);
                continue;
            }
            names.add(name);
            if (pid.length() > 0) playlistIds.put(name, pid);
        }
        if (!names.isEmpty()) playlists = names.toArray(new String[names.size()]);
        playlistIds.put("我喜欢的音乐", Cfg.LIKED_PLAYLIST_ID);
        Log.i(Cfg.TAG, "playlists=" + java.util.Arrays.toString(playlists));
    }

    private static void readLines(java.io.Reader r, ArrayList<String> out) throws Exception {
        BufferedReader br = new BufferedReader(r);
        try {
            String line;
            while ((line = br.readLine()) != null) {
                String s = line.trim();
                if (s.length() > 0 && !s.startsWith("#")) out.add(s);
            }
        } finally {
            br.close();
        }
    }

    private void connect() {
        if (browser != null) return;
        try {
            browser = new MediaBrowser(ctx, new ComponentName(Cfg.PKG, Cfg.AUTO_SVC), new NcmConn(), null);
            browser.connect();
            Log.i(Cfg.TAG, "MediaBrowser connecting " + Cfg.PKG + "/" + Cfg.AUTO_SVC);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "MediaBrowser connect failed", t);
            LogCapture.error("NCM 连接官方 MediaBrowser 失败：" + t, t);
        }
    }

    void onConnected() {
        try {
            ctl = new MediaController(ctx, browser.getSessionToken());
            // 重连/服务重启会再次走到这里，旧回调不注销就会叠加：
            // 一次 metadata 变化被回调多次 → 重复接管。先注销再注册。
            if (sess != null) {
                try { ctl.unregisterCallback(sess); } catch (Throwable ignored) {}
            }
            sess = new NcmSess();
            ctl.registerCallback(sess, main);
            LogCapture.log("NCM 注册 MediaController 回调，tid=" + Thread.currentThread().getId());
            connected = true;
            Log.i(Cfg.TAG, "CONNECTED root=" + browser.getRoot() + " ctl=" + ctl);
            LogCapture.log("NCM 已连接官方 App，root=" + browser.getRoot());
            Server.I.onBridgeChanged();
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "MediaController failed", t);
            LogCapture.error("NCM 创建 MediaController 失败：" + t, t);
        }
    }

    void onLost() {
        connected = false;
        Log.w(Cfg.TAG, "MediaBrowser suspended/failed");
        LogCapture.logAlways("NCM 与官方 App 的连接断开");
        Server.I.onBridgeChanged();
    }

    /** 供 UI / 自检读的摘要。 */
    String describe() {
        MediaMetadata m = metadata();
        PlaybackState s = state();
        String title = m == null ? "" : safeStr(m, MediaMetadata.METADATA_KEY_DISPLAY_TITLE);
        return "官方 App=" + (isConnected() ? "已连接" : "未连接")
                + "  曲目=" + (title.length() > 0 ? title : "—")
                + "  状态=" + (s == null ? "—" : String.valueOf(s.getState()));
    }

    private MediaMetadata metadata() {
        if (ctl == null) return null;
        try { return ctl.getMetadata(); } catch (Throwable t) { return null; }
    }

    private PlaybackState state() {
        if (ctl == null) return null;
        try { return ctl.getPlaybackState(); } catch (Throwable t) { return null; }
    }

    private static String safeStr(MediaMetadata m, String key) {
        try {
            String s = m.getString(key);
            return s == null ? "" : s;
        } catch (Throwable t) { return ""; }
    }

    private static long safeLong(MediaMetadata m, String key) {
        try { return m.getLong(key); } catch (Throwable t) { return 0; }
    }

    /** state.data 的业务字段；seq / reason 由 Server 补。 */
    JSONObject snapshot() {
        Liked.I.warm();   // 内部有节流：没就绪 / 过期才真去拉
        JSONObject d = J.o();
        MediaMetadata m = metadata();
        PlaybackState s = state();
        String title = "", artist = "", album = "";
        long duration = 0;
        if (m != null) {
            title = safeStr(m, MediaMetadata.METADATA_KEY_DISPLAY_TITLE);
            if (title.length() == 0) title = safeStr(m, MediaMetadata.METADATA_KEY_TITLE);
            artist = safeStr(m, MediaMetadata.METADATA_KEY_ARTIST);
            album = safeStr(m, MediaMetadata.METADATA_KEY_ALBUM);
            duration = safeLong(m, MediaMetadata.METADATA_KEY_DURATION);
        }
        boolean playing = s != null && s.getState() == PlaybackState.STATE_PLAYING;
        long position = s == null ? 0 : s.getPosition();
        J.p(d, "playing", playing);
        J.p(d, "title", title);
        J.p(d, "artist", artist);
        J.p(d, "album", album);
        J.p(d, "positionMs", position);
        J.p(d, "durationMs", duration);
        String mid = m == null ? "" : safeStr(m, Cfg.K_MEDIA_ID);
        J.p(d, "liked", likedOf(m, mid));
        int vol = -1, max = -1;
        try {
            vol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
            max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        } catch (Throwable ignored) {}
        if (vol >= 0 && max > 0) {
            J.p(d, "volume", vol);
            J.p(d, "volumeMax", max);
        }
        return d;
    }

    /**
     * 收藏态三级判定：本地刚点过的（{@link Star}）→ 喜欢歌单查询表（{@link Liked}）→ 官方元数据。
     * 官方从不回写 USER_RATING，前两级才是真用得上的；最后一级只在两者都没数据时兜底。
     */
    private static boolean likedOf(MediaMetadata m, String mid) {
        int star = Star.I.get(mid);
        if (star >= 0) return star == 1;
        int cached = Liked.I.state(mid);
        if (cached >= 0) return cached == 1;
        return m != null && safeLong(m, Cfg.K_RATING) > 0;
    }

    /** lyrics.data；trackId 用官方 MEDIA_ID，页面据此判断是否换歌。 */
    JSONObject lyricsData() {
        JSONObject d = J.o();
        MediaMetadata m = metadata();
        String tid = m == null ? "" : safeStr(m, Cfg.K_MEDIA_ID);
        String raw = m == null ? null : safeStr(m, Cfg.K_LYRIC);
        JSONArray lines = Lrc.parse(raw);
        J.p(d, "ok", true);
        J.p(d, "trackId", tid);
        J.p(d, "lines", lines);
        return d;
    }

    /** 歌词指纹：trackId + 行数 + 首行文字。 */
    String lyricKey() {
        JSONObject d = lyricsData();
        JSONArray a = d.optJSONArray("lines");
        String first = "";
        if (a != null && a.length() > 0) {
            JSONObject o = a.optJSONObject(0);
            if (o != null) first = o.optString("text", "");
        }
        return (d.optString("trackId", "") + "|" + (a == null ? 0 : a.length()) + "|" + first);
    }

    /** @return 0 成功，否则协议错误码。 */
    int execute(String action, JSONObject args) {
        if ("getState".equals(action) || "getLyrics".equals(action) || "refresh".equals(action)) return 0;
        if ("setVolume".equals(action)) {
            int v = args.optInt("volume", -1);
            if (v < 0) return Cfg.ERR_VALUE_OUT_OF_RANGE;
            try {
                am.setStreamVolume(AudioManager.STREAM_MUSIC, v, 0);
                return 0;
            } catch (Throwable t) { return Cfg.ERR_ACTION_FAILED; }
        }
        if ("playTracks".equals(action)) {
            return playTracks(args.optString("id"), args.optString("name"));
        }
        if ("playOne".equals(action)) {
            return playOne(args.optString("song"), args.optString("artist"),
                    args.optString("listId"), args.optString("listName"));
        }
        if ("getTracks".equals(action)) return 0;   // Server 异步取曲目
        if ("like".equals(action)) return like(args.optBoolean("on", false));
        if ("search".equals(action)
                || "addToPlaylist".equals(action) || "setPlayMode".equals(action)) {
            return Cfg.ERR_UNSUPPORTED_ACTION;
        }
        MediaController.TransportControls c = controls();
        if (c == null && !mediaKeys(action)) return Cfg.ERR_NOT_AVAILABLE;
        try {
            if ("play".equals(action)) { if (c != null) c.play(); else mediaKey(KeyEvent.KEYCODE_MEDIA_PLAY); }
            else if ("pause".equals(action)) { if (c != null) c.pause(); else mediaKey(KeyEvent.KEYCODE_MEDIA_PAUSE); }
            else if ("next".equals(action)) {
                // 假队列优先：点过歌单后，「下一首」必须是歌单里的下一首，
                // 而不是官方为单曲生成的「同一首歌不同版本」循环。
                if (!playNextInQueue("下一首")) {
                    // 用户明确不想听这首了，别让状态机继续跟踪到超时后误接管
                    if (poState != PO_IDLE && poState != PO_TAKEOVER) {
                        poTo(poReq, PO_IDLE, "队列为空，手动下一首放弃跟踪", null);
                    }
                    LogCapture.log("NEXT → 官方 skipToNext");
                    if (c != null) c.skipToNext(); else mediaKey(KeyEvent.KEYCODE_MEDIA_NEXT);
                }
            }
            else if ("prev".equals(action)) {
                LogCapture.log("PREV → 官方 skipToPrevious");
                if (c != null) c.skipToPrevious(); else mediaKey(KeyEvent.KEYCODE_MEDIA_PREVIOUS);
            }
            else if ("seek".equals(action)) {
                long p = args.optLong("positionMs", -1);
                if (p < 0) return Cfg.ERR_VALUE_OUT_OF_RANGE;
                if (c != null) c.seekTo(p); else return Cfg.ERR_NOT_AVAILABLE;
            } else return Cfg.ERR_UNSUPPORTED_ACTION;
            return 0;
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "execute " + action + " failed", t);
            return mediaKeys(action) && mediaKey(keyOf(action)) ? 0 : Cfg.ERR_ACTION_FAILED;
        }
    }

    private static boolean mediaKeys(String a) {
        return "play".equals(a) || "pause".equals(a) || "next".equals(a) || "prev".equals(a);
    }

    private static int keyOf(String a) {
        if ("play".equals(a)) return KeyEvent.KEYCODE_MEDIA_PLAY;
        if ("pause".equals(a)) return KeyEvent.KEYCODE_MEDIA_PAUSE;
        if ("next".equals(a)) return KeyEvent.KEYCODE_MEDIA_NEXT;
        return KeyEvent.KEYCODE_MEDIA_PREVIOUS;
    }

    private boolean mediaKey(int code) {
        try {
            am.dispatchMediaKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, code));
            am.dispatchMediaKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, code));
            return true;
        } catch (Throwable t) { return false; }
    }

    private MediaController.TransportControls controls() {
        if (ctl == null) return null;
        try { return ctl.getTransportControls(); } catch (Throwable t) { return null; }
    }

    /** 官方 App 注册的零参数 custom action（play_daily_recommend_action 等）。 */
    private boolean custom(String action) {
        MediaController.TransportControls c = controls();
        if (c == null) return false;
        try {
            c.sendCustomAction(action, new Bundle());
            Log.i(Cfg.TAG, "custom action " + action);
            LogCapture.log("CUSTOM " + action);
            return true;
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "custom action failed: " + action, t);
            return false;
        }
    }

    /**
     * 爱心：官方只认 {@code star_action} + {@code is_star}，内部走 IIotClient 的
     * addToFavorite / unDoFavorite。
     *
     * 这里先乐观置状态再发命令（页面点了立刻变红），官方用
     * sendSessionEvent("star_action", {code,message}) 回执，见 {@link NcmSess#onSessionEvent}。
     * code 0 落定，-2 说明本来就是该状态，-3 是播客/电台这类不支持收藏的内容。
     */
    int like(boolean on) {
        MediaController.TransportControls c = controls();
        if (c == null) return Cfg.ERR_NOT_AVAILABLE;
        MediaMetadata m = metadata();
        if (m == null || safeStr(m, MediaMetadata.METADATA_KEY_TITLE).length() == 0) {
            return Cfg.ERR_NOT_AVAILABLE;
        }
        try {
            Bundle b = new Bundle();
            b.putBoolean("is_star", on);
            likeWanted = on;
            likedId = safeStr(m, Cfg.K_MEDIA_ID);
            Star.I.set(likedId, on);
            Liked.I.mark(likedId, on);
            c.sendCustomAction("star_action", b);
            Log.i(Cfg.TAG, "star_action is_star=" + on + " id=" + likedId);
            return 0;
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "star_action failed", t);
            return Cfg.ERR_ACTION_FAILED;
        }
    }

    private boolean sendVoice(JSONObject payload) {
        try {
            Intent i = new Intent();
            i.setComponent(new ComponentName(Cfg.PKG, Cfg.VOICE_SVC));
            i.putExtra(Cfg.VOICE_EXTRA, payload.toString());
            ComponentName r = ctx.startService(i);
            Log.i(Cfg.TAG, "VoiceService start -> " + r + " json=" + payload);
            return r != null;
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "VoiceService start failed", t);
            LogCapture.error("VOICE 指令下发失败（官方语音服务不可达）：", t);
            return false;
        }
    }

    /** 回执落定状态；失败就回滚成相反的确定性值，别把红色留在页面上骗人。 */
    void onLikeReply(int code, String msg) {
        if (code == 0 || code == -2) {
            if (code == -2) Log.i(Cfg.TAG, "star 状态已是 " + likeWanted + "：" + msg);
            Star.I.set(likedId, likeWanted);
            Liked.I.mark(likedId, likeWanted);
        } else {
            Log.w(Cfg.TAG, "star 被拒 code=" + code + " msg=" + msg);
            Star.I.set(likedId, !likeWanted);
            Liked.I.mark(likedId, !likeWanted);
        }
    }

    /**
     * 播整单：网页/API 的唯一入口。
     *
     * - liked：官方原生入口 vr_play_favour_music，官方自己维护完整队列。
     * - daily：官方自带的「每日推荐」入口，个性化数据只存在于官方 App 内。
     * - 其余自建歌单：车机版没有整单播放入口（orpheus://playlist_play 属于主 App，
     *   车机版发 redirect_action 只回成功、不切队列，已实测确认），所以退化为
     *   「抓曲目 → 洗牌 → 逐首语音点播」的假队列，见 {@link #startQueue}。
     */
    private int playTracks(String id, String name) {
        if (id == null || id.length() == 0) return Cfg.ERR_VALUE_OUT_OF_RANGE;
        LogCapture.log("playTracks id=" + id);
        // 整单播放必须先把单曲跟踪停掉：旧的 req 会在官方起播后几秒「检测到切换」，
        // 把用户刚点的整单劫持回上一首单曲所属的假队列。
        synchronized (this) {
            int from = poState;
            poState = PO_IDLE;
            poStateAt = System.currentTimeMillis();
            poReq++;
            poMediaId = "";
            // 整单播放由官方接管队列：清掉上一个歌单的假队列，NEXT 才不会串到旧歌单
            poQueue.clear();
            poQueueIdx = -1;
            poQueueOwner = "";
            LogCapture.log("PO req=" + poReq + " " + poName(from) + "→IDLE 整单播放，停止单曲跟踪");
        }
        if ("liked".equals(id)) {
            LogCapture.log("PLAY liked → 官方 favorite");
            return voice("favorite", "", "") ? 0 : Cfg.ERR_ACTION_FAILED;
        }
        if ("daily".equals(id)) {
            LogCapture.log("PLAY daily → 官方 daily_action");
            return custom("play_daily_recommend_action") ? 0 : Cfg.ERR_ACTION_FAILED;
        }
        if ("cloud".equals(id)) return Cfg.ERR_UNSUPPORTED_ACTION;
        // 自建歌单：车机版没有整单播放入口（orpheus://playlist_play 属于主 App，
        // 车机版发出去只回成功、不切队列），改成「抓曲目 → 洗牌 → 逐首语音点播」的假队列。
        // 抓曲目要走网络，绝不能占着 WS 线程，所以放后台线程。
        final String pid = isNumeric(id) ? id : resolvePid(displayName(id));
        final String fname = name == null ? "" : name;
        new Thread(new Runnable() {
            public void run() {
                startQueue(pid, fname);
            }
        }, "ncm-queue").start();
        LogCapture.log("PLAY queue 启动 pid=" + pid + " name=" + fname);
        return 0;
    }

    /** 抓歌单曲目、洗牌，然后播第一首；后续靠状态机逐首续上。 */
    private void startQueue(String pid, String name) {
        if (buildQueue(pid, name)) playNextInQueue("歌单开始");
    }

    /** 只建队列不点播：单曲点播时提前备好，等这首播完/被换版本时接上。 */
    private boolean buildQueue(String pid, String name) {
        String key = name == null ? "" : name;
        synchronized (this) {
            // 连续快速点两首歌时，两个后台线程会同时判定「需要建队列」，
            // 各自抓取并各自洗牌，后完成的覆盖先完成的、还把索引重置回 0。
            if (key.length() > 0) {
                if (key.equals(poQueueOwner)) return true;
                if (key.equals(poBuilding)) {
                    LogCapture.log("QUEUE 已在构建，跳过 name=" + key);
                    return false;
                }
                poBuilding = key;
            }
        }
        try {
            JSONArray a = loadTracks(pid);
            if (a == null || a.length() == 0) {
                LogCapture.log("QUEUE 抓取失败 pid=" + pid + " name=" + name);
                return false;
            }
            ArrayList<String[]> list = new ArrayList<String[]>();
            for (int i = 0; i < a.length(); i++) {
                JSONObject t = a.optJSONObject(i);
                if (t == null) continue;
                String title = t.optString("name", "");
                if (title.length() == 0) continue;
                list.add(new String[]{title, t.optString("artist", "")});
            }
            if (list.isEmpty()) {
                LogCapture.log("QUEUE 空歌单 pid=" + pid);
                return false;
            }
            java.util.Collections.shuffle(list);
            synchronized (this) {
                poQueue.clear();
                poQueue.addAll(list);
                poQueueIdx = -1;
                poListId = pid;
                poListName = name;
                // 用歌单名做归属键：playTracks 传数字 pid、playOne 传歌单名，用 id 比对会永远不相等、反复重建队列
                poQueueOwner = name;
            }
            LogCapture.log("QUEUE 就绪 name=" + name + " n=" + list.size());
            return true;
        } finally {
            synchronized (this) {
                if (key.length() > 0 && key.equals(poBuilding)) poBuilding = "";
            }
        }
    }

    /** 播假队列的下一首；队列为空返回 false，调用方退回官方原生切歌。 */
    private boolean playNextInQueue(String why) {
        String title, artist, listId, listName;
        int idx, total;
        synchronized (this) {
            if (poQueue.isEmpty()) return false;
            poQueueIdx = (poQueueIdx + 1) % poQueue.size();
            idx = poQueueIdx;
            total = poQueue.size();
            String[] t = poQueue.get(idx);
            title = t[0];
            artist = t[1];
            listId = poListId;
            listName = poListName;
        }
        LogCapture.log("QUEUE " + (idx + 1) + "/" + total + " 点播 " + title
                + " - " + artist + " (" + why + ")");
        return playOne(title, artist, listId, listName) == 0;
    }

    /**
     * 单曲点播（搜索通道）：官方没有「按歌曲 ID 点播」的公开入口，只能按
     * 「歌名 + 歌手」走 IOT 语音点播。代价是官方会生成「只含当前歌曲不同版本」
     * 的队列无限循环版本——对策就是武装接管，见 {@link #takeOver()}。
     *
     * @param listId / listName 接管时要播的随机歌单（id 支持歌单名或数字 pid）
     */
    int playOne(String song, String artist, String listId, String listName) {
        if (song == null || song.length() == 0) return Cfg.ERR_VALUE_OUT_OF_RANGE;
        long req;
        boolean needQueue;
        String qid, qname;
        synchronized (this) {
            poReq++;
            req = poReq;
            poTitle = song;
            poArtist = artist == null ? "" : artist;
            poListId = listId == null ? "" : listId;
            poListName = listName == null ? "" : listName;
            poMediaId = "";
            // 单曲点播也要把「接管歌单」的队列备好，否则播完/换版本时队列为空，
            // 接管只能放弃、下一首退化成官方 skipToNext。
            // 归属键用歌单名：playTracks 走数字 pid、playOne 走歌单名，用 id 比会反复重建。
            needQueue = poListName.length() > 0 && !poListName.equals(poQueueOwner);
            qid = poListId;
            qname = poListName;
            int from = poState;
            poState = PO_REQUESTING;
            poStateAt = System.currentTimeMillis();
            LogCapture.log("PO req=" + req + " " + poName(from) + "→REQUESTING song=" + song
                    + " artist=" + poArtist + " list=" + poListName
                    + " tid=" + Thread.currentThread().getId());
        }
        if (needQueue) {
            LogCapture.log("QUEUE 异步构建 pid=" + qid + " name=" + qname);
            new Thread(new Runnable() {
                public void run() {
                    buildQueue(qid, qname);
                }
            }, "ncm-queue").start();
        }
        if (!voice("", song, artist)) {
            poTo(req, PO_IDLE, "语音指令下发失败", null);
            return Cfg.ERR_ACTION_FAILED;
        }
        // 指令已发出：从这里开始才允许核对「官方是不是播到了目标曲目」
        poTo(req, PO_CONFIRMING, "指令已发出，等官方起播", null);
        return 0;
    }

    /** 接管：锁内切到 TAKEOVER（重复事件从此被挡），锁外点播假队列的下一首。 */
    private void poTakeOver(long req, String why) {
        String name;
        long myReq;
        synchronized (this) {
            if (poState == PO_IDLE || poState == PO_TAKEOVER) return;
            if (req != 0 && req != poReq) return;
            int from = poState;
            name = poListName;
            if (poQueue.isEmpty()) {
                // 队列还在异步构建（要抓歌单、走网络）。此时不能回 IDLE 丢掉跟踪：
                // 保持 MONITORING，等下一条 metadata 再试，队列一就绪立刻接上。
                if (poQueueWaitReq != poReq) {
                    poQueueWaitReq = poReq;
                    LogCapture.log("PO req=" + poReq + " " + poName(from)
                            + " 队列未就绪，保持跟踪等队列");
                }
                return;
            }
            poState = PO_TAKEOVER;
            poStateAt = System.currentTimeMillis();
            // 锁外点播会把 poReq 加 1，先把它记下来，日志才不会出现「同一个接管两个 req」
            myReq = poReq;
            LogCapture.log("PO req=" + myReq + " " + poName(from) + "→TAKEOVER " + why
                    + " list=" + name + " mid=" + poMediaId
                    + " tid=" + Thread.currentThread().getId());
        }
        // 锁外执行：点播要走语音通道，绝不能占着状态机的锁
        boolean ok = playNextInQueue(why);
        LogCapture.log("PO req=" + myReq + " TAKEOVER 点播下一首 " + (ok ? "成功" : "失败")
                + " list=" + name);
        Server.I.onBridgeChanged();
    }

    /** 由 Server 每秒 Tick 驱动：确认阶段可能一直没有 metadata 推送，超时不能只靠回调。 */
    void poTick() {
        int s = poState;
        if (s == PO_IDLE) return;
        long age = System.currentTimeMillis() - poStateAt;
        if (s == PO_REQUESTING && age > 5000L) {
            poTo(poReq, PO_IDLE, "指令 5s 无响应", null);
        } else if (s == PO_CONFIRMING && age > 15000L) {
            poTo(poReq, PO_IDLE, "确认超时 15s，放弃跟踪", null);
        } else if (s == PO_TAKEOVER && age > 3000L) {
            // 接管后立刻会点播下一首并把状态推回 REQUESTING；这里只是兜底，
            // 万一队列点播失败，别把状态永远卡在 TAKEOVER。
            poTo(poReq, PO_IDLE, "接管后 3s 无新点播，恢复常态", null);
        }
    }

    /** 去掉括号后缀/空白：官方语音搜索常把标题写成「蓝精灵 (蓝精灵之歌)」。 */
    static String normTitle(String s) {
        if (s == null) return "";
        String r = s.replaceAll("[（(\\[【].*?[)）\\]】]", "");
        return r.replace(" ", "").replace("\u3000", "").trim();
    }

    /**
     * 目标歌名与官方返回标题是否同一首。
     * 用「去括号后互相包含」而不是完全相等：官方会给标题加后缀，
     * 完全相等会让确认永远失败、15 秒超时后整首歌都不再接管。
     */
    static boolean titleMatch(String want, String got) {
        String a = normTitle(want);
        String b = normTitle(got);
        if (a.length() == 0 || b.length() == 0) return false;
        return a.equals(b) || a.startsWith(b) || b.startsWith(a);
    }

    /** metadata 回调：状态机的唯一推进点。 */
    void poMetadata(String title, String artist, String mid) {
        long req = poReq;
        boolean take = false;
        synchronized (this) {
            // IDLE 不跟踪；TAKEOVER 期间一律屏蔽，防止官方回推旧队列触发二次接管
            if (poState == PO_IDLE || poState == PO_TAKEOVER) return;
            String t = title == null ? "" : title;
            boolean sameTitle = titleMatch(poTitle, t);
            if (poState == PO_CONFIRMING) {
                // 确认只看歌名：metadata 的 artist 常是「A/B」拼接，歌单接口只取第一个歌手，
                // 比 artist 会让合唱歌曲永远确认不了、白白超时。
                if (sameTitle && t.length() > 0) {
                    poMediaId = mid == null ? "" : mid;
                    poState = PO_MONITORING;
                    poStateAt = System.currentTimeMillis();
                    // 从这里开始算「已播放时长」，供播完判定使用
                    poPlayingSince = System.currentTimeMillis();
                    poSawPause = false;
                    LogCapture.log("PO req=" + req + " CONFIRMING→MONITORING mid=" + poMediaId
                            + " song=" + t + " tid=" + Thread.currentThread().getId());
                }
                return;
            }
            // MONITORING：版本切换以媒体 id 为准（标题可能只差一个「（伴奏）」后缀）
            boolean switched = poMediaId.length() > 0 && mid != null && !poMediaId.equals(mid);
            if (switched || (!sameTitle && t.length() > 0)) {
                long played = System.currentTimeMillis() - poStateAt;
                if (played < PO_MIN_PLAY_MS) {
                    // 刚起播就换版本（官方常在两三秒内切到伴奏版）：跟着走，别跳歌
                    poMediaId = mid == null ? poMediaId : mid;
                    LogCapture.log("PO req=" + req + " 版本切换但只播了 " + played
                            + "ms，继续收听 mid=" + mid + " title=" + t);
                } else {
                    LogCapture.log("PO req=" + req + " 检测到切换 mid=" + mid + " title=" + t);
                    take = true;
                }
            }
        }
        if (take) poTakeOver(req, "检测到版本切换");
    }

    /** 播放状态回调：目标歌真正播完才接管；暂停、未确认时的停顿都不算。 */
    void poPlayback(int cur) {
        long req = poReq;
        boolean take = false;
        synchronized (this) {
            int prev = lastPlayState;
            lastPlayState = cur;
            if (cur == PlaybackState.STATE_PLAYING) {
                if (prev != PlaybackState.STATE_PLAYING) poPlayingSince = System.currentTimeMillis();
                poSawPause = false;
            } else if (cur == PlaybackState.STATE_PAUSED) {
                poSawPause = true;
            }
            if (poState == PO_MONITORING
                    && prev == PlaybackState.STATE_PLAYING
                    && cur == PlaybackState.STATE_STOPPED) {
                // 不能只凭 STOPPED 就判定播完：语音说「停止播放」、官方里点停止、
                // 音频焦点被抢都会走 STOPPED，直接接管会把用户刚停的歌换成别的。
                // 自然播完的两个特征：连续播放够久，且过程中没暂停过。
                long played = System.currentTimeMillis() - poPlayingSince;
                if (played >= 30000L && !poSawPause) {
                    LogCapture.log("PO req=" + req + " 目标曲目播完（已播 " + (played / 1000) + "s），准备接管");
                    take = true;
                } else {
                    LogCapture.log("PO req=" + req + " 停止但只播了 " + played + "ms"
                            + (poSawPause ? "、中途暂停过" : "") + "，判定手动停止，不接管");
                }
            }
        }
        if (take) poTakeOver(req, "目标曲目播完");
    }

    // ---------- 歌单曲目（点歌单 → 看列表，不自动播放） ----------

    /** tracks 消息体。公开接口整张歌单 → 私密歌单按 id 补齐 → 网页 SSR。失败带具体原因。 */
    JSONObject tracksData(String id) {
        JSONObject d = J.o();
        JSONArray a = loadTracks(id);
        J.p(d, "ok", a != null);
        J.p(d, "id", id);
        if (a == null) {
            String why = fetchErr.get();
            if (why == null || why.length() == 0) why = "接口无响应，检查车机网络";
            J.p(d, "message", "歌单曲目读取失败：" + why
                    + "（可在车机 /sdcard/Android/data/com.luna.music.car/files/playlists.txt 里写：歌单名|公开页id）");
        } else {
            J.p(d, "total", a.length());
            J.p(d, "offset", 0);
            J.p(d, "tracks", a);
        }
        return d;
    }

    private JSONArray loadTracks(String id) {
        LogCapture.log("getTracks id=" + id);
        if ("liked".equals(id)) {
            // 收藏列表的真实内容只存在官方登录态里，公开接口拿到的是别人的示例歌单，
            // 展示出来就是「歌单不是我的」。网页上这张卡只负责直接播放收藏。
            fail("我喜欢的音乐", "收藏列表只能在官方 App 里看，这里点卡片就是直接播放你的收藏");
            return null;
        }
        JSONArray cached = fresh(id);
        if (cached != null) return cached;
        // 多设备可能同时点开同一张歌单：双检，只让一个线程真正去抓，结果所有人共用。
        // 注意网络请求必须放在锁外——锁内做 HTTP 会把所有歌单读取串成一条队，
        // 网络一慢就表现为「点了歌单很久没反应」。
        synchronized (trackCache) {
            cached = fresh(id);
            if (cached != null) return cached;
            trackCache.remove(id);
            trackAt.remove(id);
        }
        fetchErr.set(null);
        // 榜单 id 本身就是歌单 id，直接抓；自建歌单才查名字映射
        String pid = isNumeric(id) ? id : resolvePid(displayName(id));
        JSONArray a = null;
        if (pid != null) {
            a = fetchApiPlaylist(pid);                                  // 整张歌单
            if (a == null || a.length() == 0) a = fetchByIds(pid);      // 私密 / 喜欢的音乐
            if (a == null || a.length() == 0) a = fetchHtmlPlaylist(pid); // 网页 SSR 兜底
        } else {
            fail("歌单解析", "找不到「" + displayName(id) + "」的公开页 id（可在 playlists.txt 写：歌单名|公开页id）");
        }
        // 网页列表与随机队列用同一份数据：都只保留前 TRACK_LIMIT 首，避免两边条数不一致
        if (a != null && a.length() > Cfg.TRACK_LIMIT) {
            int all = a.length();
            JSONArray cut = new JSONArray();
            for (int i = 0; i < Cfg.TRACK_LIMIT; i++) {
                JSONObject o = a.optJSONObject(i);
                if (o != null) cut.put(o);
            }
            a = cut;
            LogCapture.log("tracks 截断 id=" + id + " " + all + "→" + cut.length() + " 首");
        }
        if (a != null && a.length() > 0) {
            synchronized (trackCache) {
                trackCache.put(id, a);
                trackAt.put(id, Long.valueOf(System.currentTimeMillis()));
            }
            LogCapture.log("tracks ok id=" + id + " n=" + a.length());
            return a;
        }
        // 抓不到就返回 null，由 tracksData 带具体原因提示；
        // 不再回退到官方播放队列（±10 与歌单顺序对不上，已废弃）。
        return null;
    }

    /** 缓存还在有效期内就返回，过期当没有。 */
    private JSONArray fresh(String id) {
        JSONArray cached = trackCache.get(id);
        if (cached == null) return null;
        Long at = trackAt.get(id);
        if (at == null) return null;
        if (System.currentTimeMillis() - at.longValue() >= TRACK_TTL) return null;
        return cached;
    }

    /**
     * 歌单名 → 公开页 id：先看 playlists.txt 写死的映射，没有再按名字去公开搜索里找，
     * 找到就顺手写回配置文件，下次直连。
     *
     * 之前只认配置文件，新建的歌单（或配置没带上的）一律落到播放队列兜底，
     * 于是列表里只剩当前播放的那一首。
     */
    private String resolvePid(String name) {
        if (name == null || name.length() == 0) return null;
        String known = playlistIds.get(name);
        if (known != null && known.length() > 0) return known;
        String found = searchPlaylistId(name);
        if (found != null) {
            playlistIds.put(name, found);
            persistPlaylist(name, found);
            Log.i(Cfg.TAG, "playlist resolved " + name + " -> " + found);
        }
        return found;
    }

    /** 公开搜索（type=1000 歌单）按名字找 id，只认自己名下的歌单。 */
    private String searchPlaylistId(String name) {
        long uid = selfUid();
        try {
            String body = httpGet(Cfg.API_SEARCH + URLEncoder.encode(name, "UTF-8")
                    + "&type=1000&offset=0&total=true&limit=20");
            if (body == null) return null;
            JSONObject res = new JSONObject(body).optJSONObject("result");
            JSONArray list = res == null ? null : res.optJSONArray("playlists");
            if (list == null || list.length() == 0) {
                fail("歌单搜索", "搜不到「" + name + "」");
                return null;
            }
            String owned = null;
            String exactName = null;
            for (int i = 0; i < list.length(); i++) {
                JSONObject o = list.optJSONObject(i);
                if (o == null) continue;
                long pid = o.optLong("id", 0);
                if (pid <= 0) continue;
                JSONObject creator = o.optJSONObject("creator");
                long owner = o.optLong("userId", creator == null ? 0 : creator.optLong("userId", 0));
                boolean exact = name.equals(o.optString("name", ""));
                boolean mine = uid > 0 && owner == uid;
                // uid 可能拿错（配置里全是没带 id 的歌单时，喜欢列表那首 demo 歌单的 uid
                // 并不是车主自己的），如果只按 uid 过滤，自己名字完全一致的歌单也被挡掉，
                // 一张都读不出来。所以无论 uid 对不对，都保留「完全同名」兜底。
                if (exact && !mine) exactName = exactName == null ? String.valueOf(pid) : exactName;
                if (mine) {
                    if (exact) return String.valueOf(pid);
                    owned = owned == null ? String.valueOf(pid) : owned;
                }
            }
            if (owned != null) return owned;
            if (exactName != null) return exactName;
            fail("歌单搜索", "没有匹配到「" + name + "」");
            return null;
        } catch (Throwable t) {
            fail("歌单搜索", "解析失败 " + t);
            return null;
        }
    }

    /**
     * 歌单归属人：从任一已知歌单的 v6 详情里读 userId 并记住。
     * 只用来过滤搜索结果，不参与取曲目。
     */
    private long selfUid() {
        if (selfUid > 0) return selfUid;
        for (String pid : playlistIds.values()) {
            if (!isNumeric(pid)) continue;
            try {
                String body = httpGet(Cfg.API_PLAYLIST_V6 + pid + "&n=1&s=0");
                if (body == null) continue;
                JSONObject pl = new JSONObject(body).optJSONObject("playlist");
                long uid = pl == null ? 0 : pl.optLong("userId", 0);
                if (uid > 0) {
                    selfUid = uid;
                    Log.i(Cfg.TAG, "self uid=" + uid + " (from playlist " + pid + ")");
                    return uid;
                }
            } catch (Throwable ignored) {
            }
        }
        return 0;
    }

    /** 把解析到的映射追加到外部配置文件，重启后不用再搜。 */
    private void persistPlaylist(String name, String pid) {
        try {
            File dir = ctx.getExternalFilesDir(null);
            if (dir == null) return;
            File f = new File(dir, "playlists.txt");
            java.io.FileWriter w = new java.io.FileWriter(f, true);
            try {
                w.write("\n" + name + "|" + pid + "\n");
            } finally {
                w.close();
            }
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "persist playlist failed", t);
        }
    }

    /** 给歌单读取链路记一句失败原因（根因），网页上能直接看到卡在哪一步。 */
    private void fail(String stage, String why) {
        String msg = stage + (why == null || why.length() == 0 ? "" : "：" + why);
        fetchErr.set(msg);
        LogCapture.log("tracks fail " + msg);
    }

    private String httpGet(String url) throws Exception {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("User-Agent", Cfg.WEB_UA);
            conn.setRequestProperty("Referer", "https://music.163.com/");
            conn.setRequestProperty("Cookie", Cfg.WEB_COOKIE);
            int code = conn.getResponseCode();
            if (code != 200) {
                fetchErr.set("HTTP " + code);
                return null;
            }
            InputStream in = conn.getInputStream();
            try {
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                byte[] tmp = new byte[8192];
                int n;
                while ((n = in.read(tmp)) > 0) buf.write(tmp, 0, n);
                return new String(buf.toByteArray(), "UTF-8");
            } finally {
                try { in.close(); } catch (Throwable ignored) {}
            }
        } catch (Throwable t) {
            // 连接不上 / 超时 / 域名解析失败都在这里留根因，网页能看到具体是哪种
            String why = t.getClass().getSimpleName()
                    + (t.getMessage() == null ? "" : " " + t.getMessage());
            fetchErr.set(why);
            LogCapture.error("HTTP 抓取失败 " + host(url) + "：" + why, t);
            return null;
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /** 只保留 host + 路径，日志里不铺完整 URL（查询参数很长）。 */
    private static String host(String url) {
        try {
            java.net.URL u = new java.net.URL(url);
            return u.getHost() + u.getPath();
        } catch (Throwable t) {
            return url.length() > 80 ? url.substring(0, 80) + "…" : url;
        }
    }

    private static JSONObject trackRow(JSONObject src, int index) {
        if (src == null) return null;
        String name = src.optString("name", "");
        if (name.length() == 0) return null;
        String artist = "";
        JSONArray ar = src.optJSONArray("artists");
        if (ar != null && ar.length() > 0) {
            JSONObject a0 = ar.optJSONObject(0);
            if (a0 != null) artist = a0.optString("name", "");
        }
        JSONObject t = J.o();
        J.p(t, "index", index);
        J.p(t, "name", name);
        J.p(t, "artist", artist);
        J.p(t, "durationMs", src.optLong("duration", 0));
        J.p(t, "playable", true);
        return t;
    }

    /** 公开 Web API：Cookie 里带 os=pc 时返回整张歌单（实测 Borderline 37 首 / 妙妙 30 首）。 */
    private JSONArray fetchApiPlaylist(String pid) {
        try {
            String body = httpGet(Cfg.API_PLAYLIST + pid);
            if (body == null) return null;   // httpGet 已记录根因
            JSONObject root = new JSONObject(body);
            int code = root.optInt("code", 0);
            if (code != 200) {
                fail("公开接口", "code=" + code + "（通常 20001 需登录，转 v6 重试）");
                return null;
            }
            JSONObject res = root.optJSONObject("result");
            JSONArray tracks = res == null ? null : res.optJSONArray("tracks");
            if (tracks == null || tracks.length() == 0) return null;   // 空 → 交给 v6 / 网页兜底
            JSONArray out = new JSONArray();
            for (int i = 0; i < tracks.length() && out.length() < Cfg.MAX_TRACKS; i++) {
                JSONObject t = trackRow(tracks.optJSONObject(i), out.length());
                if (t != null) out.put(t);
            }
            Log.i(Cfg.TAG, "api playlist " + pid + " tracks=" + out.length());
            return out.length() > 0 ? out : null;
        } catch (Throwable t) {
            fail("公开接口", "解析失败 " + t);
            return null;
        }
    }

    /** 私密 / 喜欢的音乐：v6 给全量 trackIds 但只给前 10 首详情，按 id 分批补齐歌名。 */
    private JSONArray fetchByIds(String pid) {
        try {
            String body = httpGet(Cfg.API_PLAYLIST_V6 + pid);
            if (body == null) return null;
            JSONObject pl = new JSONObject(body).optJSONObject("playlist");
            JSONArray ids = pl == null ? null : pl.optJSONArray("trackIds");
            if (ids == null || ids.length() == 0) {
                fail("v6 接口", "没有 trackIds（歌单私密或不存在？）");
                return null;
            }
            JSONArray out = new JSONArray();
            for (int start = 0; start < ids.length() && out.length() < Cfg.MAX_TRACKS;
                 start += Cfg.TRACK_BATCH) {
                int end = Math.min(start + Cfg.TRACK_BATCH, ids.length());
                StringBuilder sb = new StringBuilder("[");
                for (int i = start; i < end; i++) {
                    if (i > start) sb.append(',');
                    JSONObject o = ids.optJSONObject(i);
                    sb.append(o == null ? ids.optLong(i, 0) : o.optLong("id", 0));
                }
                sb.append(']');
                String songs = httpGet(Cfg.API_SONG_DETAIL + URLEncoder.encode(sb.toString(), "UTF-8"));
                if (songs == null) {
                    fail("v6 接口", "曲目详情无响应");
                    break;
                }
                JSONArray arr = new JSONObject(songs).optJSONArray("songs");
                if (arr == null || arr.length() == 0) {
                    fail("v6 接口", "曲目详情为空");
                    break;
                }
                for (int i = 0; i < arr.length() && out.length() < Cfg.MAX_TRACKS; i++) {
                    JSONObject t = trackRow(arr.optJSONObject(i), out.length());
                    if (t != null) out.put(t);
                }
                Log.i(Cfg.TAG, "song detail " + start + "-" + end + " got=" + arr.length());
            }
            if (out.length() == 0) fail("v6 接口", "曲目详情全部为空");
            Log.i(Cfg.TAG, "v6 playlist " + pid + " ids=" + ids.length() + " tracks=" + out.length());
            return out.length() > 0 ? out : null;
        } catch (Throwable t) {
            fail("v6 接口", "解析失败 " + t);
            return null;
        }
    }

    /** 最后兜底：网页 SSR 只给前 10 首，无需登录、无需 weapi/eapi。 */
    private JSONArray fetchHtmlPlaylist(String pid) {
        try {
            String html = httpGet("https://music.163.com/playlist?id=" + pid);
            if (html == null) return null;
            Matcher m = Pattern.compile("<a href=\"/song\\?id=(\\d+)\">(.*?)</a>").matcher(html);
            JSONArray out = new JSONArray();
            while (m.find() && out.length() < 20) {
                String name = unescape(m.group(2).replaceAll("<[^>]+>", "").trim());
                if (name.length() == 0) continue;
                JSONObject t = J.o();
                J.p(t, "index", out.length());
                J.p(t, "name", name);
                J.p(t, "artist", "");
                J.p(t, "durationMs", 0);
                J.p(t, "playable", true);
                out.put(t);
            }
            if (out.length() == 0) fail("网页兜底", "页面里没解析到曲目（歌单私密或需登录）");
            Log.i(Cfg.TAG, "html playlist " + pid + " tracks=" + out.length());
            return out.length() > 0 ? out : null;
        } catch (Throwable t) {
            fail("网页兜底", "解析失败 " + t);
            return null;
        }
    }

    private static String displayName(String id) {
        if ("liked".equals(id)) return "我喜欢的音乐";
        if ("daily".equals(id)) return "每日推荐";
        return id;
    }

    private static boolean isNumeric(String s) {
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    /** 只读缓存，供 UI 显示用；不触发网络。 */

    private static String unescape(String s) {
        return s.replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">")
                .replace("&quot;", "\"").replace("&#39;", "'").replace("&nbsp;", " ");
    }

    void clearTrackCache() {
        trackCache.clear();
        trackAt.clear();
    }

    /** VoiceService 语音指令：mediaSource=favorite 播「我喜欢的音乐」，或按歌名点播。 */
    private boolean voice(String mediaSource, String song, String artist) {
        JSONObject slots = J.o();
        J.p(slots, "song", song);
        J.p(slots, "artist", artist);
        J.p(slots, "genre", "");
        J.p(slots, "tags", "");
        J.p(slots, "lang", "");
        J.p(slots, "source", "");
        J.p(slots, "sourceType", "");
        J.p(slots, "mediaSource", mediaSource);
        JSONObject semantic = J.o();
        J.p(semantic, "slots", slots);
        JSONObject payload = J.o();
        J.p(payload, "operation", "PLAY");
        J.p(payload, "semantic", semantic);
        LogCapture.log("VOICE src=" + (mediaSource.length() == 0 ? "-" : mediaSource)
                + " song=" + song + " artist=" + artist);
        return sendVoice(payload);
    }

    /** 用于 adb logcat 自检：只读，不改变播放状态。 */
    void selfTest() {
        Log.i(Cfg.TAG, "SELFTEST " + describe());
        MediaMetadata m = metadata();
        if (m == null) { Log.w(Cfg.TAG, "SELFTEST metadata=null"); return; }
        java.util.Set<String> keys = m.keySet();
        Log.i(Cfg.TAG, "SELFTEST metadata keys=" + keys);
        Log.i(Cfg.TAG, "SELFTEST lyric lines=" + Lrc.parse(safeStr(m, Cfg.K_LYRIC)).length()
                + " rating=" + safeLong(m, Cfg.K_RATING));
        PlaybackState s = state();
        Log.i(Cfg.TAG, "SELFTEST playback=" + s + " actions=" + (s == null ? -1 : s.getActions()));
        Log.i(Cfg.TAG, "SELFTEST volume=" + am.getStreamVolume(AudioManager.STREAM_MUSIC)
                + "/" + am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
    }
}

class NcmConn extends MediaBrowser.ConnectionCallback {
    @Override public void onConnected() { Ncm.I.onConnected(); }
    @Override public void onConnectionSuspended() { Ncm.I.onLost(); }
    @Override public void onConnectionFailed() { Ncm.I.onLost(); }
}

class NcmSess extends MediaController.Callback {
    @Override public void onMetadataChanged(MediaMetadata m) {
        Log.i(Cfg.TAG, "metadata changed");
        // 记录当前播的曲目（含 title），用于判断官方切歌切到哪一首
        if (m != null) {
            String title = m.getString(MediaMetadata.METADATA_KEY_TITLE);
            String artist = m.getString(MediaMetadata.METADATA_KEY_ARTIST);
            String mid = m.getDescription() == null ? null : m.getDescription().getMediaId();
            // 官方对同一首会连推 4-6 次完全相同的 metadata，去重否则几首歌就把日志刷爆
            String key = (title == null ? "" : title) + "|" + (artist == null ? "" : artist)
                    + "|" + mid;
            if (!key.equals(Ncm.I.lastMetaKey)) {
                Ncm.I.lastMetaKey = key;
                LogCapture.log("META title=" + (title == null ? "" : title)
                        + " artist=" + (artist == null ? "" : artist)
                        + " mid=" + mid);
            }
            // 全部交给状态机：确认段忽略语音助手启动过程的旧曲目/中间态，
            // 监视段才把 mediaId 变化当作「官方切到了别的版本」。
            Ncm.I.poMetadata(title, artist, mid);
        }
        Server.I.onBridgeChanged();
    }
    @Override public void onPlaybackStateChanged(PlaybackState s) {
        // 播放状态也交给状态机：只有「已确认起播 → 播放/缓冲 → 停止」才算目标歌播完。
        if (s != null) Ncm.I.poPlayback(s.getState());
        // 状态变化是排查「点了没反应」「自动暂停」最直接的证据，每条都记
        if (s != null && s.getState() != Ncm.I.lastLogState) {
            Ncm.I.lastLogState = s.getState();
            LogCapture.log("STATE " + Ncm.stateName(s.getState()) + " pos=" + s.getPosition());
        }
        Server.I.onBridgeChanged();
    }
    @Override public void onSessionEvent(String action, Bundle extras) {
        // star_action 的回执：code 0 成功 / -2 已经是该状态 / -3 当前内容不支持收藏
        if (!"star_action".equals(action)) return;
        int code = extras == null ? 0 : extras.getInt("code", 0);
        String msg = extras == null ? "" : extras.getString("message", "");
        Log.i(Cfg.TAG, "star_action reply code=" + code + " msg=" + msg);
        Ncm.I.onLikeReply(code, msg);
        Server.I.onBridgeChanged();
    }
    @Override public void onSessionDestroyed() { Ncm.I.onLost(); }
}
