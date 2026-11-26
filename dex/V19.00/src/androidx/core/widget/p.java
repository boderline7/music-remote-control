package androidx.core.widget;

import android.widget.PopupWindow;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class p {
    public static boolean a(PopupWindow popupWindow) {
        return popupWindow.getOverlapAnchor();
    }

    public static int b(PopupWindow popupWindow) {
        return popupWindow.getWindowLayoutType();
    }

    public static void c(PopupWindow popupWindow, boolean z6) {
        popupWindow.setOverlapAnchor(z6);
    }

    public static void d(PopupWindow popupWindow, int i7) {
        popupWindow.setWindowLayoutType(i7);
    }
}
