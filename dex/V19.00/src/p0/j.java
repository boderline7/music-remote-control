package p0;

import android.graphics.Rect;
import android.view.DisplayCutout;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class j {
    public static DisplayCutout a(Rect rect, List<Rect> list) {
        return new DisplayCutout(rect, list);
    }

    public static List<Rect> b(DisplayCutout displayCutout) {
        return displayCutout.getBoundingRects();
    }

    public static int c(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetBottom();
    }

    public static int d(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetLeft();
    }

    public static int e(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetRight();
    }

    public static int f(DisplayCutout displayCutout) {
        return displayCutout.getSafeInsetTop();
    }
}
