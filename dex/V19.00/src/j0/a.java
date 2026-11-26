package j0;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.InsetDrawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static int a(Drawable drawable) {
        return drawable.getAlpha();
    }

    public static Drawable b(DrawableContainer.DrawableContainerState drawableContainerState, int i7) {
        return drawableContainerState.getChild(i7);
    }

    public static Drawable c(InsetDrawable insetDrawable) {
        return insetDrawable.getDrawable();
    }

    public static boolean d(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    public static void e(Drawable drawable, boolean z6) {
        drawable.setAutoMirrored(z6);
    }
}
