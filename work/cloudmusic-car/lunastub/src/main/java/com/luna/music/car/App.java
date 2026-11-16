package com.luna.music.car;

import android.app.Application;
import android.util.Log;

/**
 * 进程一起来就把服务拉上，不用等人点图标。
 *
 * 覆盖面：装完第一次打开、开机、被系统回收后任何原因重启进程。已经在跑时再 start 是幂等的，
 * 不会多起一条通知。当前音源是第三方自研播放器时 {@link Svc} 自己会退出，这里不用分支；
 * 同时把当前模式推给第三方 App，好让它的 RemoteServer 按模式启停（跨包唤醒）。
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            LogCapture.init(this);
            watchCrashes();
            Log.i(Cfg.TAG, "进程启动，音源=" + Mode.name(this));
            LogCapture.logAlways("APP 启动，音源=" + Mode.name(this)
                    + "，官方=" + Mode.hasOfficial(this) + "，第三方=" + Mode.hasThirdParty(this));
            Svc.start(this);
            // 开机 / 进程重建时把模式同步过去：官方则让第三方停，第三方则让它把服务拉起来
            ModeSync.notify(this, Mode.current(this));
            // 用户手动停服 / 音源是第三方时不再排心跳，否则 15 分钟后服务自己回来抢 18765
            if (Svc.autoAllowed(this)) Alive.schedule(this);
        } catch (Throwable t) {
            // Application.onCreate 抛异常 = 整个进程闪退，绝不能放出去
            Log.w(Cfg.TAG, "app start failed（已兜底，不闪退）", t);
            LogCapture.logAlways("APP_START_FAILED " + t);
            try { Svc.start(this); } catch (Throwable ignored) {}
        }
    }

    /** 任何未捕获异常尽量写进日志采样，用户下载日志就能看到崩溃现场，不用再靠猜。 */
    private void watchCrashes() {
        final Thread.UncaughtExceptionHandler prev = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                try {
                    String t = ex == null ? "null"
                            : (ex.getClass().getSimpleName() + (ex.getMessage() == null ? "" : " " + ex.getMessage()));
                    LogCapture.logAlways("CRASH on " + thread.getName() + " : " + t);
                    StackTraceElement[] st = ex == null ? null : ex.getStackTrace();
                    for (int i = 0; i < (st == null ? 0 : Math.min(st.length, 8)); i++) {
                        LogCapture.logAlways("  at " + st[i].toString());
                    }
                } catch (Throwable ignored) {
                }
                if (prev != null) {
                    prev.uncaughtException(thread, ex);
                } else if (ex instanceof ThreadDeath) {
                    // 正常线程结束，不做处理
                } else {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        });
    }
}
