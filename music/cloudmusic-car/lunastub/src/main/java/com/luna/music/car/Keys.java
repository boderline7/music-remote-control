package com.luna.music.car;

import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

/**
 * 方控按键的「收口器」。
 *
 * <p>背景：官方 App（com.netease.cloudmusic.iot）被桥接包 {@link Bridge} 连着但没在播时，
 * 系统会把它那个暂停态的 MediaSession 当成当前媒体会话，方向盘按键全投给它；而它此时不处理，
 * 用户看到的就是「重启后方控没反应，手机遥控却好好的」。桥接包原来那套
 * {@code AudioManager.dispatchMediaKeyEvent()} 兜底需要 MEDIA_CONTENT_CONTROL（系统签名权限），
 * 普通 App 根本发不出去，等于没兜。
 *
 * <p>做法：自己开一个只负责收键的 MediaSession。官方在播时 {@code setActive(false)} 让位，
 * 官方暂停 / 没起来 / 会话掉了时接管，把按键翻译成 {@link Ncm#execute}，用 IPC 直接驱动官方，
 * 不依赖系统把 KeyEvent 投给谁。
 *
 * <p>不播音频、不设 metadata、不申请音频焦点，所以不会多出媒体通知，也不改变当前播放器。
 */
public final class Keys {
    public static final Keys I = new Keys();
    private static final String TAG = "LunaKeys";

    private MediaSession s;
    private Context ctx;

    private Keys() {}

    public synchronized void start(Context c) {
        if (s != null) return;
        ctx = c.getApplicationContext();
        try {
            s = new MediaSession(ctx, "luna-bridge-keys");
            s.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS
                    | MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);
            s.setCallback(new MediaSession.Callback() {
                @Override public void onPlay() { tap("play"); }
                @Override public void onPause() { tap("pause"); }
                @Override public void onSkipToNext() { tap("next"); }
                @Override public void onSkipToPrevious() { tap("prev"); }
                @Override public void onPlayFromMediaId(String id, Bundle extras) { tap("play"); }

                @Override public boolean onMediaButtonEvent(Intent i) {
                    // 车机方控大多是 KEYCODE_MEDIA_PLAY_PAUSE，默认分发不一定会落到 onPlay/onPause，
                    // 这里自己判一下播放态再决定，其余键交回默认实现。
                    try {
                        android.view.KeyEvent k =
                                i == null ? null : i.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                        int code = k == null ? -1 : k.getKeyCode();
                        if (code == android.view.KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                                || code == android.view.KeyEvent.KEYCODE_HEADSETHOOK) {
                            if (k.getAction() == android.view.KeyEvent.ACTION_UP) {
                                tap(Ncm.I.playing() ? "pause" : "play");
                            }
                            return true;
                        }
                    } catch (Throwable t) {
                        Log.w(TAG, "解析方控键失败", t);
                    }
                    return super.onMediaButtonEvent(i);
                }
            });
            s.setActive(true);
            Log.i(TAG, "方控收口会话已建立（官方在播时让位）");
        } catch (Throwable t) {
            Log.w(TAG, "建立方控会话失败", t);
            s = null;
        }
    }

    /** 官方会话状态变化时同步：在播就让位给官方，否则自己接管。 */
    public synchronized void follow(int iotState) {
        if (s == null) return;
        boolean playing = iotState == PlaybackState.STATE_PLAYING
                || iotState == PlaybackState.STATE_BUFFERING;
        try {
            if (s.isActive() == playing) {
                s.setActive(!playing);
                Log.i(TAG, playing ? "官方在播，方控交回官方" : "官方未播，方控由桥接接管");
            }
        } catch (Throwable t) {
            Log.w(TAG, "切换方控归属失败", t);
        }
    }

    /** 官方会话没了（断开/被回收）：立刻接管，别让方控空窗。 */
    public void takeOver() {
        follow(PlaybackState.STATE_NONE);
    }

    public synchronized void stop() {
        if (s == null) return;
        try {
            s.setActive(false);
            s.release();
        } catch (Throwable ignored) {}
        s = null;
        Log.i(TAG, "方控收口会话已释放");
    }

    private void tap(String action) {
        try {
            int code = Ncm.I.execute(action, new JSONObject());
            LogCapture.log("方控 → " + action + " 返回 " + code);
        } catch (Throwable t) {
            Log.w(TAG, "方控执行失败 " + action, t);
        }
    }
}
