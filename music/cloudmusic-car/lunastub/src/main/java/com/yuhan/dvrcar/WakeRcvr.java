package com.yuhan.dvrcar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 后台唤醒入口。
 *
 * <p>车机助手（18766）发现 18767 没在监听时，往这个组件发一条<b>显式广播</b>，收到就把进程
 * 和 {@link DvrSvc} 拉起来 —— 全程在后台，不启动任何界面，不抢焦点、不闪屏。
 *
 * <p>为什么用广播不用 Activity：startActivity 即使配透明主题，也会把对端切到前台并抢焦点，
 * 车机上看起来就是「莫名其妙跳了一下」。
 *
 * <p>为什么不用 bindService：绑定能拉起进程，但对方是 exported=false，而且绑定不会触发
 * onStartCommand，端口起不起要看 onCreate；广播直接调 start()，语义最直白。
 *
 * <p>限制：用户在系统设置里「强行停止」过本应用之后，广播也拉不起来（force-stopped 包被系统
 * 冻结），必须手动点一次图标。这是系统行为，绕不过去。
 */
public class WakeRcvr extends BroadcastReceiver {

    /** 车机助手发这条 action，同时会把 component 指名到本类。 */
    public static final String ACTION_WAKE = "com.yuhan.dvrcar.WAKE";

    @Override
    public void onReceive(Context c, Intent i) {
        try {
            Log.i(Cfg.TAG, "wake 广播：启动记录仪服务");
            LogCapture.logAlways("WAKE by broadcast");
            DvrSvc.start(c);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "wake 广播处理失败", t);
        }
    }
}
