package com.luna.music.car;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 内存环形日志：排查车机端「点播 / 续播 / 下一首」时序问题用。
 *
 * <p>车机上抓 logcat 不便，这里在关键链路（点歌、续播、状态回调、切上下首）手动打点，
 * 存进一个固定容量的环形缓冲。用户从网页打开「日志采样」开关复现问题，再点下载，
 * 手机浏览器直接拿到一份带时间戳的文本，转给开发者定位。
 *
 * <p>全顶层类，无匿名类（避开 d8 8.2.2 匿名类 NPE 的历史坑）。
 */
final class LogCapture {

    private static final int CAPACITY = 3000;
    private static final String[] BUF = new String[CAPACITY];
    private static int head = 0;      // 下一条要写的位置
    private static int count = 0;     // 已写条数（用于判断是否绕了一圈）
    /**
     * 采样开关默认关闭：正式使用时不记录点播细节，只在崩溃/启动/端口这类关键点留痕。
     * 需要排查时在网页里手动打开，状态写入 SharedPreferences，重启后保持。
     */
    private static volatile boolean on = false;
    private static final SimpleDateFormat FMT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);

    private static final String SP = "log_pref";
    private static final String KEY_ON = "sample_on";
    private static Context appCtx;

    private LogCapture() {}

    /** 进程启动时恢复上次的采样开关；没存过就是关闭。 */
    static void init(Context c) {
        if (c == null) return;
        try {
            appCtx = c.getApplicationContext();
            on = appCtx.getSharedPreferences(SP, Context.MODE_PRIVATE).getBoolean(KEY_ON, false);
        } catch (Throwable ignored) {
        }
    }

    static void setOn(boolean enabled) {
        on = enabled;
        try {
            if (appCtx != null) {
                appCtx.getSharedPreferences(SP, Context.MODE_PRIVATE).edit()
                        .putBoolean(KEY_ON, enabled).apply();
            }
        } catch (Throwable ignored) {
        }
    }

    static boolean isOn() { return on; }

    /** 打一条日志；开关关闭时直接丢弃，不占缓冲。 */
    static synchronized void log(String msg) {
        if (!on) return;
        write(msg);
    }

    /** 打一条日志，不受采样开关限制：崩溃这种要命的记录必须留下来。 */
    static synchronized void logAlways(String msg) {
        write(msg);
    }

    /** 异常：把类名、message 和前几帧栈一起记下来，比只记一句 toString 有用得多。 */
    static synchronized void error(String where, Throwable t) {
        if (t == null) { write(where + "  null"); return; }
        StringBuilder sb = new StringBuilder(where);
        sb.append(' ').append(t.getClass().getSimpleName());
        if (t.getMessage() != null) sb.append(": ").append(t.getMessage());
        StackTraceElement[] st = t.getStackTrace();
        int n = Math.min(st == null ? 0 : st.length, 6);
        for (int i = 0; i < n; i++) sb.append("\n    at ").append(st[i]);
        write(sb.toString());
    }

    /** 调用方已持锁。 */
    private static void write(String msg) {
        BUF[head] = FMT.format(new Date()) + "  " + msg;
        head = (head + 1) % CAPACITY;
        if (count < CAPACITY) count++;
    }

    /** 导出为纯文本：按时间先后，一行一条。 */
    static synchronized String dump() {
        StringBuilder sb = new StringBuilder();
        sb.append("# 车机日志采样  time  message\n");
        int total = count;
        if (total == 0) {
            sb.append("(空：尚未开启采样，或开启后未触发任何播放操作)\n");
            return sb.toString();
        }
        int start = (head - total + CAPACITY) % CAPACITY;
        for (int i = 0; i < total; i++) {
            int idx = (start + i) % CAPACITY;
            String line = BUF[idx];
            if (line != null) sb.append(line).append('\n');
        }
        return sb.toString();
    }

    static synchronized void clear() {
        head = 0;
        count = 0;
    }
}
