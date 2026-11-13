package com.luna.music.car;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONObject;

/**
 * 车机侧的开关与选择，从原生入口页搬到网页。
 *
 * <p>走普通 HTTP，不占 WS 协议：{@code GET /api/status} 读状态，{@code POST /api/mode}
 * {@code /api/service} {@code /api/keepalive} {@code /api/open} 做操作。手机浏览器连上来
 * 就能切音源、重启服务、申请后台白名单，不必回到车机点那个原生页面。
 *
 * <p>切换音源会停掉服务（本包自己），所以响应先写完再延迟重启，避免客户端拿不到结果。
 */
final class Api {

    private static final Handler UI = new Handler(Looper.getMainLooper());

    private Api() {
    }

    static byte[] handle(Server s, String method, String path, String body) {
        Context c = s.context();
        try {
            if (c == null) return bytes(fail("服务还没起来"));
            if ("/api/status".equals(path)) return bytes(status(c));
            if ("/api/logs".equals(path)) {
                if ("GET".equals(method)) return logsText();
                return bytes(setLogs(body));
            }
            JSONObject in = parse(body);
            if ("/api/mode".equals(path)) return bytes(setMode(c, in.optString("mode", "")));
            if ("/api/service".equals(path)) return bytes(service(c, in.optString("op", "restart")));
            if ("/api/open".equals(path)) return bytes(open(c));
            return bytes(fail("未知接口：" + path));
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "api " + path + " failed", t);
            LogCapture.logAlways("API " + path + " 异常：" + t);
            return bytes(fail("接口异常：" + t));
        }
    }

    // ---------- 日志采样 ----------

    /** 开 / 关日志采样；打开后桥接层把播放链路时序写进内存环形缓冲。 */
    private static JSONObject setLogs(String body) {
        JSONObject in = parse(body);
        boolean want = in.optBoolean("on", false);
        LogCapture.setOn(want);
        if (want) LogCapture.log("采样开启");
        JSONObject d = J.o();
        J.p(d, "ok", true);
        J.p(d, "on", want);
        J.p(d, "message", want ? "日志采样已开启，复现问题后点下载" : "日志采样已关闭");
        return d;
    }

    /** 导出日志文本：Content-Type 设为 text/plain 方便浏览器直接下载。 */
    private static byte[] logsText() {
        try {
            return LogCapture.dump().getBytes("UTF-8");
        } catch (Throwable t) {
            return new byte[]{};
        }
    }

    // ---------- 读 ----------

    static JSONObject status(Context c) {
        JSONObject d = J.o();
        int m = Mode.current(c);
        J.p(d, "ok", true);
        J.p(d, "mode", m == Mode.OFFICIAL ? "official" : (m == Mode.THIRD_PARTY ? "third_party" : "none"));
        J.p(d, "modeLabel", Mode.label(m));
        J.p(d, "hasOfficial", Mode.hasOfficial(c));
        J.p(d, "hasThirdParty", Mode.hasThirdParty(c));
        J.p(d, "both", Mode.both(c));
        J.p(d, "running", Server.I.isRunning());
        J.p(d, "clients", Server.I.clientCount());
        J.p(d, "bridge", Mode.isOfficial(c) && Bridge.I.connected());
        J.p(d, "ip", Ip.local());
        J.p(d, "port", Cfg.PORT);
        // 不要在这里真去 bind 探测：那一瞬间会占住端口，第三方正好启动就会失败。
        // 直接读 Svc 的判定结果即可。
        J.p(d, "portTaken", Server.I.yielded && !Server.I.isRunning());
        J.p(d, "screen", screen(c));
        J.p(d, "version", version(c));
        J.p(d, "playlists", Ncm.I.playlists() == null ? 0 : Ncm.I.playlists().length);
        J.p(d, "oldProbe", Mode.installed(c, "com.wb.ncmremote"));
        J.p(d, "logOn", LogCapture.isOn());
        return d;
    }

    // ---------- 写 ----------

    /** 切音源：存选择 → 回响应 → 停服 → 按新模式拉起。 */
    private static JSONObject setMode(Context c, String want) {
        JSONObject d = J.o();
        int target;
        if ("official".equals(want)) target = Mode.OFFICIAL;
        else if ("third_party".equals(want)) target = Mode.THIRD_PARTY;
        else return fail("mode 只能是 official 或 third_party");
        if (target == Mode.OFFICIAL && !Mode.hasOfficial(c)) return fail("车上没装官方网易云车机版");
        if (target == Mode.THIRD_PARTY && !Mode.hasThirdParty(c)) return fail("车上没装第三方自研播放器");
        boolean changed = Mode.current(c) != target;
        if (changed) {
            Mode.save(c, target);
            // 同步给第三方 App：它按新模式自己启停 RemoteServer
            ModeSync.notify(c, target);
        }
        J.p(d, "ok", true);
        J.p(d, "mode", target == Mode.OFFICIAL ? "official" : "third_party");
        J.p(d, "modeLabel", Mode.label(target));
        J.p(d, "changed", changed);
        if (changed) {
            J.p(d, "message", target == Mode.OFFICIAL
                    ? "已切到官方网易云，服务 1 秒后重启"
                    : "已切到第三方自研播放器，18765 交给它提供");
            restartLater(c, 700L);
        } else {
            J.p(d, "message", "已经是这个音源");
        }
        return d;
    }

    /** 重启服务。等于「关掉再开」，用来解卡而不是长停。 */
    private static JSONObject service(Context c, String op) {
        JSONObject d = J.o();
        boolean official = Mode.isOfficial(c);
        J.p(d, "ok", true);
        J.p(d, "op", op);
        if (!official) {
            J.p(d, "message", "当前音源由第三方自研播放器提供，本包不跑服务");
            return d;
        }
        if ("stop".equals(op)) {
            J.p(d, "message", "服务已停，心跳 15 分钟内会拉回");
            UI.postDelayed(new StopTask(c), 600L);
            return d;
        }
        J.p(d, "message", "服务正在重启，几秒后自动重连");
        restartLater(c, 500L);
        return d;
    }

    /** 打开当前音源对应的音乐 App。 */
    private static JSONObject open(Context c) {
        JSONObject d = J.o();
        String pkg = Mode.isOfficial(c) ? Mode.OFFICIAL_PKG : Mode.THIRD_PARTY_PKG;
        Intent i = c.getPackageManager().getLaunchIntentForPackage(pkg);
        if (i == null) return fail("打不开：" + pkg);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        try {
            c.startActivity(i);
            J.p(d, "ok", true);
            J.p(d, "message", "已打开 " + Mode.label(Mode.current(c)));
            return d;
        } catch (Throwable t) {
            return fail("打不开：" + pkg + "（" + t.getClass().getSimpleName() + "）");
        }
    }

    // ---------- 工具 ----------

    /** 先让 HTTP 响应落地，再停服；停服会关掉当前这条连接。 */
    private static void restartLater(final Context c, long delay) {
        UI.postDelayed(new RestartTask(c), delay);
    }

    private static String screen(Context c) {
        try {
            DisplayMetrics dm = c.getResources().getDisplayMetrics();
            return dm.widthPixels + "×" + dm.heightPixels;
        } catch (Throwable t) {
            return "";
        }
    }

    private static String version(Context c) {
        try {
            PackageManager pm = c.getPackageManager();
            return pm.getPackageInfo(c.getPackageName(), 0).versionName;
        } catch (Throwable t) {
            return "";
        }
    }

    private static JSONObject parse(String body) {
        if (body == null || body.length() == 0) return J.o();
        try {
            JSONObject o = new JSONObject(body);
            return o == null ? J.o() : o;
        } catch (Throwable t) {
            return J.o();
        }
    }

    private static JSONObject fail(String msg) {
        JSONObject d = J.o();
        J.p(d, "ok", false);
        J.p(d, "message", msg);
        return d;
    }

    private static byte[] bytes(JSONObject d) {
        try {
            return d.toString().getBytes("UTF-8");
        } catch (Throwable t) {
            return new byte[]{'{', '}'};
        }
    }
}

/** 停服再拉起：切音源与「重启服务」都走这一条。 */
class RestartTask implements Runnable {
    private final Context c;
    RestartTask(Context c) { this.c = c; }
    @Override public void run() {
        Svc.stop(c);
        Api2.restartSoon(c);
    }
}

class StopTask implements Runnable {
    private final Context c;
    StopTask(Context c) { this.c = c; }
    @Override public void run() {
        Svc.stop(c);
    }
}

/** 停服后隔一会儿再 start，等 Svc.onDestroy 把端口和桥都放干净。 */
final class Api2 {
    private static final Handler UI = new Handler(Looper.getMainLooper());
    static void restartSoon(final Context c) {
        UI.postDelayed(new Runnable() {
            @Override public void run() {
                if (Mode.isOfficial(c)) Svc.start(c);
            }
        }, 900L);
    }
    private Api2() {}
}
