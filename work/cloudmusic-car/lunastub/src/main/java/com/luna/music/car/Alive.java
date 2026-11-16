package com.luna.music.car;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

/**
 * 保活心跳。
 *
 * 车机的省电策略、内存回收、用户从最近任务划掉，都会把服务弄死。前台服务 + START_STICKY
 * 能挡住大部分，挡住的那部分靠 15 分钟一次的闹钟兜底：闹钟到点给 {@link Boot} 发一个
 * 显式广播，服务没活着就重新起来，活着就什么也不做。
 */
final class Alive {

    static final String HEARTBEAT = "com.luna.music.car.HEARTBEAT";

    private Alive() {
    }

    static void schedule(Context c) {
        try {
            AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
            if (am == null) return;
            Intent i = new Intent(c, Boot.class);
            i.setAction(HEARTBEAT);
            int flags = PendingIntent.FLAG_UPDATE_CURRENT;
            if (Build.VERSION.SDK_INT >= 23) flags |= PendingIntent.FLAG_IMMUTABLE;
            PendingIntent pi = PendingIntent.getBroadcast(c, 0, i, flags);
            long period = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
            am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + period, period, pi);
            Log.i(Cfg.TAG, "心跳已排，15 分钟一次");
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "心跳排不上", t);
        }
    }

    /** 撤掉心跳。用户主动停服时必须调：setInexactRepeating 排下的闹钟不会因为
     *  stopService 消失，不撤的话 15 分钟后它又把服务拉起来重新占 18765。 */
    static void cancel(Context c) {
        try {
            AlarmManager am = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
            if (am == null) return;
            Intent i = new Intent(c, Boot.class);
            i.setAction(HEARTBEAT);
            int flags = PendingIntent.FLAG_UPDATE_CURRENT;
            if (Build.VERSION.SDK_INT >= 23) flags |= PendingIntent.FLAG_IMMUTABLE;
            PendingIntent pi = PendingIntent.getBroadcast(c, 0, i, flags);
            am.cancel(pi);
            pi.cancel();
            Log.i(Cfg.TAG, "心跳已撤销");
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "心跳撤不掉", t);
        }
    }
}
