package p0;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class x0 {
    public static View.AccessibilityDelegate a(View view) {
        return view.getAccessibilityDelegate();
    }

    public static List<Rect> b(View view) {
        return view.getSystemGestureExclusionRects();
    }

    public static void c(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i7, int i8) {
        view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i7, i8);
    }

    public static void d(View view, List<Rect> list) {
        view.setSystemGestureExclusionRects(list);
    }
}
