package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class w {
    public static Bundle a(PlaybackState playbackState) {
        return playbackState.getExtras();
    }

    public static void b(PlaybackState.Builder builder, Bundle bundle) {
        builder.setExtras(bundle);
    }
}
