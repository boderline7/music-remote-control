package com.yuhan.dvrcar;

import android.app.Application;
import android.util.Log;

/**
 * 进程一起来就把记录仪服务拉上，不用等人点图标。
 *
 * <p>音乐桥接整块删掉了：这里不再起任何播放器相关的东西，也不向第三方同步音源模式。
 * 本包只负责文件传输（小绿传）和行车记录仪（18767）。
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            LogCapture.init(this);
            watchCrashes();
            Log.i(Cfg.TAG, "进程启动（文件传输 + 记录仪）");
            LogCapture.logAlways("APP 启动（文件传输 + 记录仪）");
            // 行车记录仪独立服务：无条件拉起
            DvrSvc.start(this);
        } catch (Throwable t) {
            // Application.onCreate 抛异常 = 整个进程闪退，绝不能放出去
            Log.w(Cfg.TAG, "app start failed（已兜底，不闪退）", t);
            LogCapture.logAlways("APP_START_FAILED " + t);
            try { DvrSvc.start(this); } catch (Throwable ignored) {}
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
