package com.luna.music.car;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 跨包模式同步。
 *
 * 两个 App（本桥接层 + 第三方自研播放器）各自存一份模式，谁都不能保证对方进程活着，
 * 所以切换时用显式广播把新模式推给对方：对方如果是 manifest 里注册的 receiver，
 * 系统会唤醒它的进程投递，receiver 里按新模式自己启停服务。
 *
 * - {@link #notify}: 本包切模式后推给第三方 App（com.cloudmusic.car）。
 * - {@link Receiver}: 第三方 App 切模式后推回来，本包收到就存模式 + 按模式启停本包服务。
 *
 * manifest 需要：
 * <pre>
 *   &lt;receiver android:name="com.luna.music.car.ModeSync$Receiver"
 *             android:exported="true"
 *             android:permission="android.permission.INTERNET"&gt;
 *     &lt;intent-filter&gt;
 *       &lt;action android:name="com.cloudmusic.car.remote.MODE_SYNC"/&gt;
 *     &lt;/intent-filter&gt;
 *   &lt;/receiver&gt;
 * </pre>
 */
final class ModeSync {

    private ModeSync() {
    }

    /** 把当前模式推给第三方 App（com.cloudmusic.car 的 ModeReceiver）。 */
    static void notify(Context ctx, int mode) {
        try {
            Intent i = new Intent(Mode.SYNC_ACTION);
            i.setPackage(Mode.THIRD_PARTY_PKG);
            i.putExtra(Mode.SYNC_EXTRA, mode == Mode.OFFICIAL ? "official" : "third_party");
            ctx.sendBroadcast(i);
            Log.i(Cfg.TAG, "mode sync -> " + Mode.THIRD_PARTY_PKG + " : " + (mode == Mode.OFFICIAL ? "official" : "third_party"));
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "mode sync failed", t);
        }
    }

    /** 第三方 App 切模式后推回给本包。 */
    public static class Receiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context c, Intent intent) {
            // receiver 里抛异常整个进程会一起闪退，必须兜底
            try {
                handle(c, intent);
                } catch (Throwable t) {
                    Log.w(Cfg.TAG, "mode sync receiver failed", t);
                    LogCapture.error("MODESYNC 收到广播后处理失败：", t);
                }
        }

        private void handle(Context c, Intent intent) {
            String want = intent == null ? null : intent.getStringExtra(Mode.SYNC_EXTRA);
            if (want == null) return;
            int target;
            if ("official".equals(want)) target = Mode.OFFICIAL;
            else if ("third_party".equals(want)) target = Mode.THIRD_PARTY;
            else return;
            Log.i(Cfg.TAG, "mode sync <- " + (target == Mode.OFFICIAL ? "official" : "third_party"));
            Mode.save(c, target);
            // 按新模式启停本包服务：official 拉起来（这是对方主动要求，覆盖停服标记），
            // third_party 则真停服让出 18765
            if (target == Mode.OFFICIAL) {
                Svc.startByUser(c);
            } else {
                Svc.stop(c);
            }
        }
    }
}
