package com.luna.music.car;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 官方网易云桥接专用的常量与 LRC 解析（随桥接代码一起剥离）。
 *
 * <p>本文件在 {@code src/parked/java} 下，默认不参与编译、不进 APK。
 * 要恢复桥接：把本目录的 MusicCfg / Ncm / Bridge / Keys / Star / Liked 一起拷回
 * {@code src/main/java/com/luna/music/car/}，重新编译即可。
 */
final class MusicCfg {
    static final String PKG = "com.netease.cloudmusic.iot";
    static final String AUTO_SVC = "com.netease.cloudmusic.service.AutomotiveService";
    static final String VOICE_SVC = "com.netease.cloudmusic.iot.chery.VoiceService";
    static final String VOICE_EXTRA = "com.netease.voicedata";

    /** 「我喜欢的音乐」公开页 id（关掉私密后可见）；播放本身走 vr_play_favour_music。 */
    static final String LIKED_PLAYLIST_ID = "154852987";
    static final String WEB_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120 Safari/537.36";
    /** os=pc 是关键：带它公开接口才返回整张歌单，否则只给前 10 首。 */
    static final String WEB_COOKIE = "appver=2.0.2; os=pc; NMTID=00O";
    static final String API_PLAYLIST = "https://music.163.com/api/playlist/detail?id=";
    static final String API_PLAYLIST_V6 = "https://music.163.com/api/v6/playlist/detail?id=";
    /** 喜欢歌单的全量 id：判定「本来就收藏过」用，v6 接口才给 trackIds。 */
    static final String API_LIKED = API_PLAYLIST_V6 + LIKED_PLAYLIST_ID + "&n=1000&s=0";
    static final String API_SONG_DETAIL = "https://music.163.com/api/song/detail?ids=";
    /** 歌单搜索（type=1000）：配置文件里没写 id 的歌单按名字在这里找。 */
    static final String API_SEARCH = "https://music.163.com/api/search/get/web?s=";
    static final int TRACK_BATCH = 150;
    static final int MAX_TRACKS = 500;

    static final String K_LYRIC = "android.media.metadata.LYRIC";
    static final String K_RATING = "android.media.metadata.USER_RATING";
    static final String K_MEDIA_ID = "android.media.metadata.MEDIA_ID";

    /** 内置兜底歌单名；运行时优先读私有目录 playlists.txt（一行一个歌单名）。 */
    static final String[] DEFAULT_PLAYLISTS = {"Borderline 或许", "嘟嘟妙妙崽崽滴"};
    static final String[] DEFAULT_PLAYLIST_IDS = {"723825351", "17429136336"};

    /** 官方榜：id 固定、由官方每天更新，不依赖车主配置。 */
    static final String[] OFFICIAL_PLAYLISTS = {"热歌榜", "飙升榜", "网易云民谣榜", "网易云国风榜"};
    static final String[] OFFICIAL_PLAYLIST_IDS = {"3778678", "19723756", "5059661515", "5059642708"};

    /** 单张歌单最多取多少首（网页列表与随机队列一致）。 */
    static final int TRACK_LIMIT = 60;

    private MusicCfg() {}
}

/** 把官方 metadata 里的整份 LRC 解析成协议要求的 [{t,text,trans}]，按时间升序。 */
final class Lrc {
    static JSONArray parse(String raw) {
        ArrayList<JSONObject> rows = new ArrayList<JSONObject>();
        if (raw == null) return new JSONArray();
        String[] lines = raw.split("\\r?\\n");
        for (int n = 0; n < lines.length; n++) {
            String line = lines[n];
            ArrayList<Long> times = new ArrayList<Long>();
            int i = 0;
            while (i < line.length() && line.charAt(i) == '[') {
                int close = line.indexOf(']', i);
                if (close < 0) break;
                long t = parseTime(line.substring(i + 1, close));
                if (t < 0) break;
                times.add(Long.valueOf(t));
                i = close + 1;
            }
            if (times.isEmpty()) continue;
            String text = line.substring(i).trim();
            if (text.length() == 0) continue;
            for (int k = 0; k < times.size(); k++) {
                long t = times.get(k).longValue();
                JSONObject row = J.o();
                J.p(row, "t", (int) t);
                J.p(row, "text", text);
                J.p(row, "trans", "");
                rows.add(row);
            }
        }
        // 插入排序：行数一般几十到几百，避免 Comparator/匿名类
        for (int a = 1; a < rows.size(); a++) {
            JSONObject cur = rows.get(a);
            int b = a - 1;
            while (b >= 0 && rows.get(b).optInt("t", 0) > cur.optInt("t", 0)) {
                rows.set(b + 1, rows.get(b));
                b--;
            }
            rows.set(b + 1, cur);
        }
        JSONArray out = new JSONArray();
        for (int n = 0; n < rows.size(); n++) out.put(rows.get(n));
        return out;
    }

    /** [mm:ss.xx] / [mm:ss:xx] / [mm:ss] → 毫秒；元数据标签（ti:/ar: 等）返回 -1。 */
    private static long parseTime(String tag) {
        String[] parts = tag.split("[:.]");
        if (parts.length < 2) return -1;
        try {
            long min = Long.parseLong(parts[0].trim());
            long sec = Long.parseLong(parts[1].trim());
            long frac = parts.length > 2 ? Long.parseLong(parts[2].trim()) : 0;
            if (parts.length > 2 && parts[2].trim().length() == 2) frac *= 10;
            return min * 60000L + sec * 1000L + frac;
        } catch (Throwable t) {
            return -1;
        }
    }

    private Lrc() {}
}
