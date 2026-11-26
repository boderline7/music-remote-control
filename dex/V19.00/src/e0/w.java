package e0;

import android.app.ForegroundServiceStartNotAllowedException;
import android.content.ClipData;
import android.view.ContentInfo;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract /* synthetic */ class w {
    public static /* bridge */ /* synthetic */ ForegroundServiceStartNotAllowedException d(Object obj) {
        return (ForegroundServiceStartNotAllowedException) obj;
    }

    public static /* synthetic */ ContentInfo.Builder f(ClipData clipData, int i7) {
        return new ContentInfo.Builder(clipData, i7);
    }

    public static /* bridge */ /* synthetic */ ContentInfo h(Object obj) {
        return (ContentInfo) obj;
    }

    public static /* bridge */ /* synthetic */ boolean p(Object obj) {
        return obj instanceof ForegroundServiceStartNotAllowedException;
    }
}
