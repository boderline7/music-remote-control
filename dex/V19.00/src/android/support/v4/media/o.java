package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class o {
    public static Uri a(MediaDescription mediaDescription) {
        return mediaDescription.getMediaUri();
    }

    public static void b(MediaDescription.Builder builder, Uri uri) {
        builder.setMediaUri(uri);
    }
}
