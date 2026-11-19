package com.luna.music.car;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 「我喜欢的音乐」的歌曲 id 集合——用来回答「这首歌本来收藏过没有」。
 *
 * <p>官方 App 从不把收藏态写进 MediaSession 的 {@code USER_RATING}，所以只看 metadata
 * 永远是未收藏：用户明明在官方 App 里点过红心，卡片和网页却都是灰的。这里把喜欢歌单的
 * {@code trackIds} 拉一份下来当查询表，播放中的 MEDIA_ID 在里面就是红的。
 *
 * <p>数据源是公开的 {@code /api/v6/playlist/detail}（必须带 {@code os=pc} 与 Referer，
 * 旧接口会回 20001）。缓存 10 分钟；点爱心时先改本地，不等服务端。
 */
final class Liked {
    static final Liked I = new Liked();

    /** 成功拉取后的有效期；过期后台重拉，期间继续用旧表。 */
    private static final long TTL = 10 * 60 * 1000L;
    /** 失败后多久才重试，避免没网时每秒都去连。 */
    private static final long RETRY = 60 * 1000L;

    private final Set<String> ids = new HashSet<String>();
    private volatile boolean ready;
    private volatile long at;
    private volatile boolean loading;

    private Liked() {
    }

    /** -1 未知（还没拉到）/ 0 不在喜欢列表 / 1 在。 */
    int state(String mediaId) {
        if (!ready || mediaId == null || mediaId.length() == 0) return -1;
        return ids.contains(mediaId) ? 1 : 0;
    }

    /** 点了爱心立刻改内存表，换歌再切回来也是对的。 */
    synchronized void mark(String mediaId, boolean on) {
        if (!ready || mediaId == null || mediaId.length() == 0) return;
        if (on) ids.add(mediaId);
        else ids.remove(mediaId);
    }

    boolean ready() {
        return ready;
    }

    /** 喜欢歌单的曲目数；还没拉到就是 0。 */
    int size() {
        return ready ? ids.size() : 0;
    }

    /** 异步预热：过期、没拉过、或上次失败超过 RETRY 才真的去拉。 */
    void warm() {
        if (loading) return;
        long now = System.currentTimeMillis();
        if (ready) {
            if (now - at < TTL) return;
        } else if (at > 0 && now - at < RETRY) {
            return;
        }
        loading = true;
        try {
            new Thread(new LikedLoad(), "ncm-liked").start();
        } catch (Throwable t) {
            loading = false;
            Log.w(Cfg.TAG, "喜欢列表线程起不来", t);
        }
    }

    void load() {
        InputStream in = null;
        HttpURLConnection c = null;
        try {
            c = (HttpURLConnection) new URL(Cfg.API_LIKED).openConnection();
            c.setConnectTimeout(6000);
            c.setReadTimeout(8000);
            c.setRequestProperty("User-Agent", Cfg.WEB_UA);
            c.setRequestProperty("Cookie", Cfg.WEB_COOKIE);
            c.setRequestProperty("Referer", "https://music.163.com/");
            int code = c.getResponseCode();
            if (code != 200) throw new Exception("http " + code);
            in = c.getInputStream();
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            byte[] b = new byte[8192];
            int n;
            while ((n = in.read(b)) > 0) buf.write(b, 0, n);
            JSONObject d = new JSONObject(new String(buf.toByteArray(), "UTF-8"));
            JSONObject p = d.optJSONObject("playlist");
            if (p == null) throw new Exception("no playlist");
            Set<String> next = new HashSet<String>();
            collect(next, p.optJSONArray("trackIds"));
            collect(next, p.optJSONArray("tracks"));
            if (next.isEmpty()) throw new Exception("empty");
            synchronized (this) {
                ids.clear();
                ids.addAll(next);
                ready = true;
                at = System.currentTimeMillis();
            }
            Log.i(Cfg.TAG, "喜欢列表已就绪：" + next.size() + " 首");
            // 表刚就绪，把页面推一次，别等下一次换歌才亮
            Server.I.pushState("event");
        } catch (Throwable t) {
            at = System.currentTimeMillis();
            Log.w(Cfg.TAG, "喜欢列表拉取失败：" + t);
        } finally {
            loading = false;
            if (in != null) try { in.close(); } catch (Throwable ignored) {}
            if (c != null) try { c.disconnect(); } catch (Throwable ignored) {}
        }
    }

    private static void collect(Set<String> out, JSONArray arr) {
        if (arr == null) return;
        for (int i = 0; i < arr.length(); i++) {
            JSONObject o = arr.optJSONObject(i);
            if (o == null) continue;
            long id = o.optLong("id", 0);
            if (id > 0) out.add(String.valueOf(id));
        }
    }
}

class LikedLoad implements Runnable {
    @Override public void run() {
        Liked.I.load();
    }
}
