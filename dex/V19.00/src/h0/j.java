package h0;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class j {
    public static Drawable a(Resources resources, int i7, Resources.Theme theme) {
        return resources.getDrawable(i7, theme);
    }

    public static Drawable b(Resources resources, int i7, int i8, Resources.Theme theme) {
        return resources.getDrawableForDensity(i7, i8, theme);
    }
}
