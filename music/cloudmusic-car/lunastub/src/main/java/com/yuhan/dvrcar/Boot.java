package com.luna.music.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 开机/升级后把记录仪服务拉起来。
 *
 * <p>音乐桥接已剥离，这里不再启动 {@link Svc}、不再排心跳、也不再同步音源模式。
 * 旧版本升级上来可能还残留着心跳闹钟，收到时顺手撤掉。
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
        if ("com.luna.music.car.HEARTBEAT".equals(action)) {
            // 老版本留下的心跳：什么都不做，服务已经不再启动
            return;
        }
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)
                || Intent.ACTION_MY_PACKAGE_REPLACED.equals(action)
                || Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(action)
                || "android.intent.action.QUICKBOOT_POWERON".equals(action)) {
            Log.i(Cfg.TAG, "开机自启：记录仪");
            // 行车记录仪独立服务：无条件拉起
            DvrSvc.start(context);
        }
    }
}
