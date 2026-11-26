package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.ImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class h {
    public static ColorStateList a(ImageView imageView) {
        return imageView.getImageTintList();
    }

    public static PorterDuff.Mode b(ImageView imageView) {
        return imageView.getImageTintMode();
    }

    public static void c(ImageView imageView, ColorStateList colorStateList) {
        imageView.setImageTintList(colorStateList);
    }

    public static void d(ImageView imageView, PorterDuff.Mode mode) {
        imageView.setImageTintMode(mode);
    }
}
