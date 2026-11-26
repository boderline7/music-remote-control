package com.navitool.bridge;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.SystemClock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

/**
 * 诊断文本。
 *
 * 为什么放在服务端而不是车机 App 里：车机没有好用的输入法，屏幕上也没法复制；
 * 这段文本从网页取，手机上直接复制或存成文件，比在车机上戳按钮省事得多。
 */
final class Diag {

    private Diag() {
    }

    static String text(Context ctx) {
        StringBuilder sb = new StringBuilder(6144);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        sb.append("==== 车机桥接诊断 ====\n");
        sb.append("时间   ").append(fmt.format(new Date())).append('\n');
        sb.append("设备   ").append(Build.MANUFACTURER).append(' ').append(Build.MODEL)
                .append(" · Android ").append(Build.VERSION.RELEASE)
                .append(" (SDK ").append(Build.VERSION.SDK_INT).append(")\n");
        sb.append("访问   http://").append(lanIp()).append(':').append(Bridge.PORT).append("/\n");

        // ------------------------------------------------------------ 服务
        sb.append("\n-- 服务 --\n");
        sb.append("端口   ").append(Bridge.PORT)
                .append(Bridge.bound ? "  已绑定\n" : "  未绑定 ← 没有监听，车控一定离线\n");
        sb.append("自测   ")
                .append(probe("127.0.0.1") ? "127.0.0.1 通\n"
                        : "127.0.0.1 不通 ← 本机回环可用，跨应用连接被拦，改用局域网地址\n");
        sb.append("自测   ")
                .append(probe(lanIp()) ? lanIp() + " 通\n" : lanIp() + " 不通 ← 网络层就没通\n");

        long up = -1;
        try {
            up = SystemClock.elapsedRealtime() - android.os.Process.getStartElapsedRealtime();
        } catch (Throwable ignored) {
        }
        sb.append("进程   pid ").append(android.os.Process.myPid());
        if (up >= 0) {
            sb.append(" · 已运行 ").append(dur(up));
        }
        sb.append('\n');

        // ------------------------------------------------------------ 引擎
        sb.append("\n-- 助手引擎 --\n");
        sb.append("界面   ").append(Bridge.activity != null ? "已抓到 DesktopAssistantActivity\n"
                : "未抓到 ← 车机上打开一次「桌面助手」\n");
        sb.append("引擎   ");
        if (Bridge.helper != null) {
            sb.append("已绑定 ").append(Bridge.helper.getClass().getSimpleName());
            if (Bridge.helperAt > 0) {
                sb.append("（").append(dur(System.currentTimeMillis() - Bridge.helperAt)).append("前）");
            }
            sb.append('\n');
        } else {
            sb.append("未绑定 ← 卡片和动作都用不了，先让桌面助手出现\n");
        }
        sb.append("动作   ").append(Acts.count()).append(" 项可执行\n");
        sb.append("操作   ").append(OpLog.total()).append(" 次调用 · 失败 ")
                .append(OpLog.failed()).append('\n');
        sb.append("最近错误 ").append(Bridge.lastError.length() == 0 ? "无\n"
                : Bridge.lastError + "\n");

        // ------------------------------------------------------------ 包
        sb.append("\n-- 安装包 --\n");
        pkg(sb, ctx, ctx.getPackageName(), fmt);
        pkg(sb, ctx, "com.cloudmusic.car", fmt);   // 云音乐车机版（音乐）

        // ------------------------------------------------------------ 日志
        sb.append("\n-- 桥接日志（进程内存，按发生顺序）--\n");
        String mem = Bridge.logText(60);
        sb.append(mem.length() == 0 ? "(空)\n" : mem);
        sb.append('\n');

        sb.append("-- 系统日志（本应用相关，最近 60 行）--\n");
        String sys = exec("logcat -d -t 400 -v time 2>/dev/null"
                + " | grep -iE \"NavitoolBridge|AndroidRuntime|navi\\.tool|bridge-http\""
                + " | tail -60", 60, 2500L);
        sb.append(sys.length() == 0 ? "(没有相关日志)\n" : sys);

        return sb.toString();
    }

    /**
     * 一份全量打包：诊断 + 车况 + 动作表 + 操作记录。
     * 排障时只要这一个文件，不用来回切换分段。
     */
    static String dump(Context ctx) {
        StringBuilder sb = new StringBuilder(16384);
        sb.append(text(ctx)).append('\n');
        sb.append(Cards.text(ctx)).append('\n');
        sb.append(Acts.text()).append('\n');
        sb.append(OpLog.text());
        sb.append("\n==== 结束 ====\n");
        return sb.toString();
    }

    // ------------------------------------------------------------- 内部

    private static void pkg(StringBuilder sb, Context ctx, String name, SimpleDateFormat fmt) {
        try {
            PackageInfo pi = ctx.getPackageManager().getPackageInfo(name, 0);
            sb.append("  ").append(name).append("  ")
                    .append(pi.versionName).append(" (vC ").append(pi.versionCode).append(')');
            if (pi.lastUpdateTime > 0) {
                sb.append("  ").append(fmt.format(new Date(pi.lastUpdateTime)));
            }
            sb.append('\n');
        } catch (Throwable t) {
            sb.append("  ").append(name).append("  未安装\n");
        }
    }

    /** 本机局域网 IPv4（车控页就是从手机用这个地址打开的） */
    static String lanIp() {
        try {
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (ni == null || !ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                for (InetAddress a : Collections.list(ni.getInetAddresses())) {
                    if (a instanceof Inet4Address && !a.isLoopbackAddress()) {
                        String h = a.getHostAddress();
                        if (h != null && !h.startsWith("169.254.")) {
                            return h;
                        }
                    }
                }
            }
        } catch (Throwable ignored) {
        }
        return "127.0.0.1";
    }

    private static boolean probe(String host) {
        Socket s = null;
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(host, Bridge.PORT), 400);
            return true;
        } catch (Throwable t) {
            return false;
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (Throwable ignored) {
            }
        }
    }

    private static String dur(long ms) {
        long s = ms / 1000;
        if (s < 60) {
            return s + " 秒";
        }
        long m = s / 60;
        if (m < 60) {
            return m + " 分 " + (s % 60) + " 秒";
        }
        long h = m / 60;
        if (h < 24) {
            return h + " 小时 " + (m % 60) + " 分";
        }
        return (h / 24) + " 天 " + (h % 24) + " 小时";
    }

    private static String exec(String cmd, int maxLines, final long timeoutMs) {
        StringBuilder out = new StringBuilder(2048);
        Process p = null;
        BufferedReader r = null;
        try {
            p = Runtime.getRuntime().exec(new String[]{"/system/bin/sh", "-c", cmd});
            final Process fp = p;
            Thread killer = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(timeoutMs);
                    } catch (InterruptedException ignored) {
                    }
                    try {
                        fp.destroy();
                    } catch (Throwable ignored) {
                    }
                }
            });
            killer.setDaemon(true);
            killer.start();
            r = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
            String line;
            int n = 0;
            while ((line = r.readLine()) != null && n++ < maxLines) {
                out.append(line).append('\n');
            }
        } catch (Throwable t) {
            out.append("读取失败：").append(t).append('\n');
        } finally {
            try {
                if (r != null) {
                    r.close();
                }
            } catch (Throwable ignored) {
            }
            if (p != null) {
                try {
                    p.destroy();
                } catch (Throwable ignored) {
                }
            }
        }
        return out.toString();
    }
}
