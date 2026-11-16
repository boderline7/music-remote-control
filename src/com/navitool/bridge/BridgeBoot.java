package com.navitool.bridge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 开机自启：车机一启动就把 18766 顶起来，不依赖桌面助手有没有被打开。
 */
public final class BridgeBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String a = intent == null ? "" : intent.getAction();
        Bridge.note("boot broadcast: " + a);
        try {
            Bridge.install(context.getApplicationContext());
        } catch (Throwable t) {
            Bridge.note("boot install: " + Bridge.root(t));
        }
        BridgeService.kick(context);
    }
}
