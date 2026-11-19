package com.navitool.bridge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * 操作记录：每一次 /act 调用都留一条，页面能看、能复制、能下载。
 *
 * 为什么放在服务端：车机屏幕上选不中文字，也没有能用的输入法。
 * 点完按钮到底成没成、报了什么错，只能靠网页把这段文本捞出去。
 */
final class OpLog {

    /** 环形缓冲上限。150 条够覆盖一次上车调用的全过程，也不占内存。 */
    private static final int MAX = 150;

    private static final LinkedList<Entry> RING = new LinkedList<Entry>();
    private static final SimpleDateFormat TS = new SimpleDateFormat("HH:mm:ss", Locale.US);
    private static final SimpleDateFormat FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    private static final long STARTED = System.currentTimeMillis();
    private static int total;
    private static int failed;

    private OpLog() {
    }

    static final class Entry {
        long at;
        String id;
        String label;
        int v;
        boolean ok;
        long ms;
        String err;
    }

    static synchronized void add(String id, String label, int v, boolean ok, long ms, String err) {
        Entry e = new Entry();
        e.at = System.currentTimeMillis();
        e.id = id == null ? "" : id;
        e.label = (label == null || label.length() == 0) ? e.id : label;
        e.v = v;
        e.ok = ok;
        e.ms = ms;
        e.err = err == null ? "" : err;
        RING.addFirst(e);                    // 最新的在最上面
        while (RING.size() > MAX) {
            RING.removeLast();
        }
        total++;
        if (!ok) {
            failed++;
        }
    }

    static synchronized void clear() {
        RING.clear();
        total = 0;
        failed = 0;
    }

    /** 累计调用次数（进程生命周期，清空记录后会归零） */
    static synchronized int total() {
        return total;
    }

    static synchronized int failed() {
        return failed;
    }

    static synchronized String text() {
        StringBuilder sb = new StringBuilder(4096);
        sb.append("==== 操作记录 ====\n");
        sb.append("进程启动  ").append(FULL.format(new Date(STARTED))).append('\n');
        sb.append("调用统计  ").append(total).append(" 次 · 成功 ").append(total - failed)
                .append(" · 失败 ").append(failed).append('\n');
        sb.append("保留      ").append(RING.size()).append('/').append(MAX)
                .append(" 条，最新的在上面\n");

        if (RING.isEmpty()) {
            sb.append("\n(还没有操作记录)\n");
            return sb.toString();
        }

        sb.append("\n时间      结果  耗时    动作\n");
        sb.append(Txt.rule(42)).append('\n');
        for (Entry e : RING) {
            sb.append(TS.format(new Date(e.at))).append("  ");
            sb.append(e.ok ? "成功" : "失败");
            sb.append("  ");
            sb.append(pad(e.ms + "ms", 6));
            sb.append(' ');
            sb.append(e.label);
            sb.append("  (").append(e.id);
            if (e.v >= 0) {
                sb.append(" v=").append(e.v);
            }
            sb.append(')');
            if (e.err.length() > 0) {
                sb.append("  ← ").append(e.err);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static String pad(String s, int n) {
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < n) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
