package com.navitool.bridge.dvr;

import android.content.Context;

/**
 * 记录仪代理的对外入口 —— 整个 dvr 包里唯一 public 的东西。
 *
 * <p>18766 与记录仪代理同进程常驻，直接调 {@link #start} 即可，端口在进程在。
 *
 * <p>落盘位置也随之改到 {@code com.navi.tool} 自己的沙箱：已下载的记录仪文件缓存会丢
 * （重新下就行），{@code dvr_last.txt}（上次连的记录仪 IP）也会丢 → 首次需要重扫一遍。
 */
public final class Dvr {

    private Dvr() {
    }

    /** 幂等：已经起来了直接返回 true。由 BridgeService.onCreate 调用。 */
    public static boolean start(Context ctx) {
        if (ctx == null) {
            return false;
        }
        try {
            return DvrServer.I.start(ctx);
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean running() {
        try {
            return DvrServer.I.isRunning();
        } catch (Throwable t) {
            return false;
        }
    }

    public static int port() {
        return DvrServer.PORT;
    }
}
