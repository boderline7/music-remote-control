package p0;

import android.content.ClipData;
import android.view.PointerIcon;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class t0 {
    public static void a(View view) {
        view.cancelDragAndDrop();
    }

    public static void b(View view) {
        view.dispatchFinishTemporaryDetach();
    }

    public static void c(View view) {
        view.dispatchStartTemporaryDetach();
    }

    public static void d(View view, PointerIcon pointerIcon) {
        view.setPointerIcon(pointerIcon);
    }

    public static boolean e(View view, ClipData clipData, View.DragShadowBuilder dragShadowBuilder, Object obj, int i7) {
        return view.startDragAndDrop(clipData, dragShadowBuilder, obj, i7);
    }

    public static void f(View view, View.DragShadowBuilder dragShadowBuilder) {
        view.updateDragShadow(dragShadowBuilder);
    }
}
