package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class v {
    public static void a(PlaybackState.Builder builder, PlaybackState.CustomAction customAction) {
        builder.addCustomAction(customAction);
    }

    public static PlaybackState.CustomAction b(PlaybackState.CustomAction.Builder builder) {
        return builder.build();
    }

    public static PlaybackState c(PlaybackState.Builder builder) {
        return builder.build();
    }

    public static PlaybackState.Builder d() {
        return new PlaybackState.Builder();
    }

    public static PlaybackState.CustomAction.Builder e(String str, CharSequence charSequence, int i7) {
        return new PlaybackState.CustomAction.Builder(str, charSequence, i7);
    }

    public static String f(PlaybackState.CustomAction customAction) {
        return customAction.getAction();
    }

    public static long g(PlaybackState playbackState) {
        return playbackState.getActions();
    }

    public static long h(PlaybackState playbackState) {
        return playbackState.getActiveQueueItemId();
    }

    public static long i(PlaybackState playbackState) {
        return playbackState.getBufferedPosition();
    }

    public static List<PlaybackState.CustomAction> j(PlaybackState playbackState) {
        return playbackState.getCustomActions();
    }

    public static CharSequence k(PlaybackState playbackState) {
        return playbackState.getErrorMessage();
    }

    public static Bundle l(PlaybackState.CustomAction customAction) {
        return customAction.getExtras();
    }

    public static int m(PlaybackState.CustomAction customAction) {
        return customAction.getIcon();
    }

    public static long n(PlaybackState playbackState) {
        return playbackState.getLastPositionUpdateTime();
    }

    public static CharSequence o(PlaybackState.CustomAction customAction) {
        return customAction.getName();
    }

    public static float p(PlaybackState playbackState) {
        return playbackState.getPlaybackSpeed();
    }

    public static long q(PlaybackState playbackState) {
        return playbackState.getPosition();
    }

    public static int r(PlaybackState playbackState) {
        return playbackState.getState();
    }

    public static void s(PlaybackState.Builder builder, long j7) {
        builder.setActions(j7);
    }

    public static void t(PlaybackState.Builder builder, long j7) {
        builder.setActiveQueueItemId(j7);
    }

    public static void u(PlaybackState.Builder builder, long j7) {
        builder.setBufferedPosition(j7);
    }

    public static void v(PlaybackState.Builder builder, CharSequence charSequence) {
        builder.setErrorMessage(charSequence);
    }

    public static void w(PlaybackState.CustomAction.Builder builder, Bundle bundle) {
        builder.setExtras(bundle);
    }

    public static void x(PlaybackState.Builder builder, int i7, long j7, float f7, long j8) {
        builder.setState(i7, j7, f7, j8);
    }
}
