package com.yuhan.dvrcar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 开机 / 升级后把记录仪服务拉起来。
 *
 * <p>这个包只有两件事：文件传输（小绿传）和行车记录仪（18767）。音乐桥接连同它排的
 * 心跳闹钟一起删掉了，所以这里不再需要过滤任何残留 action —— 换了包名以后，老版本
 * 的心跳广播也投不到这里来。
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
