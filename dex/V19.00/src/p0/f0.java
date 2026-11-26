package p0;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.PointerIcon;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class f0 {
    public static PointerIcon a(Bitmap bitmap, float f7, float f8) {
        return PointerIcon.create(bitmap, f7, f8);
    }

    public static PointerIcon b(Context context, int i7) {
        return PointerIcon.getSystemIcon(context, i7);
    }

    public static PointerIcon c(Resources resources, int i7) {
        return PointerIcon.load(resources, i7);
    }
}
