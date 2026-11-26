package p0;

import android.graphics.Rect;
import android.view.Gravity;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class m {
    public static void a(int i7, int i8, int i9, Rect rect, int i10, int i11, Rect rect2, int i12) {
        Gravity.apply(i7, i8, i9, rect, i10, i11, rect2, i12);
    }

    public static void b(int i7, int i8, int i9, Rect rect, Rect rect2, int i10) {
        Gravity.apply(i7, i8, i9, rect, rect2, i10);
    }

    public static void c(int i7, Rect rect, Rect rect2, int i8) {
        Gravity.applyDisplay(i7, rect, rect2, i8);
    }
}
