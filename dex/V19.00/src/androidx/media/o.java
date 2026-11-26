package androidx.media;

import android.media.session.MediaSessionManager;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextPaint;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract /* synthetic */ class o {
    public static /* synthetic */ PrecomputedText.Params.Builder j(TextPaint textPaint) {
        return new PrecomputedText.Params.Builder(textPaint);
    }

    public static /* synthetic */ void u(String str, int i7, int i8) {
        new MediaSessionManager.RemoteUserInfo(str, i7, i8);
    }

    public static /* bridge */ /* synthetic */ boolean v(Spannable spannable) {
        return spannable instanceof PrecomputedText;
    }
}
