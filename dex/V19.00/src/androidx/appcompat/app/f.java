package androidx.appcompat.app;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class f {
    public static void a(ActionBar actionBar, int i7) {
        actionBar.setHomeActionContentDescription(i7);
    }

    public static void b(ActionBar actionBar, Drawable drawable) {
        actionBar.setHomeAsUpIndicator(drawable);
    }
}
