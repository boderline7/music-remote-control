package p0;

import android.graphics.Rect;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n0 {
    public static Rect a(View view) {
        return view.getClipBounds();
    }

    public static boolean b(View view) {
        return view.isInLayout();
    }

    public static void c(View view, Rect rect) {
        view.setClipBounds(rect);
    }
}
