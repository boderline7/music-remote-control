package com.luna.music.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 开机把服务拉起来。车机重启后没人点图标，服务不自己起来的话遥控和助手那条通道都是死的。
 *
 * 只有「官方网易云车机版」这条音源需要本包干活；当前音源是第三方自研播放器时它自己会起，
 * 本包不插一脚，只把模式同步过去让它按模式启停。
 */
public class Boot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 广播入口抛异常进程一起闪退，整个包 try-catch 兜底
        try {
            handle(context, intent);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "boot receiver failed", t);
        }
    }

    private void handle(Context context, Intent intent) {
        if (intent == null) return;
        String action = intent.getAction();
        if (Alive.HEARTBEAT.equals(action)) {
            Log.i(Cfg.TAG, "心跳到点，确认服务在场");
            // Svc.start 内部判断：用户手动停过 / 当前音源是第三方都不拉起
            Svc.start(context);
            return;
        }
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)
                || Intent.ACTION_MY_PACKAGE_REPLACED.equals(action)
                || Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(action)
                || "android.intent.action.QUICKBOOT_POWERON".equals(action)) {
            Log.i(Cfg.TAG, "开机自启，音源=" + Mode.name(context));
            Svc.start(context);
            // 开机同步模式给第三方 App（官方则让它停，第三方则让它把服务拉起来）
            ModeSync.notify(context, Mode.current(context));
            if (Svc.autoAllowed(context)) Alive.schedule(context);
        }
    }
}
