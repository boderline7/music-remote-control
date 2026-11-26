package p0;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeProvider;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l0 {
    public static AccessibilityNodeProvider a(View view) {
        return view.getAccessibilityNodeProvider();
    }

    public static boolean b(View view) {
        return view.getFitsSystemWindows();
    }

    public static int c(View view) {
        return view.getImportantForAccessibility();
    }

    public static int d(View view) {
        return view.getMinimumHeight();
    }

    public static int e(View view) {
        return view.getMinimumWidth();
    }

    public static ViewParent f(View view) {
        return view.getParentForAccessibility();
    }

    public static int g(View view) {
        return view.getWindowSystemUiVisibility();
    }

    public static boolean h(View view) {
        return view.hasOverlappingRendering();
    }

    public static boolean i(View view) {
        return view.hasTransientState();
    }

    public static boolean j(View view, int i7, Bundle bundle) {
        return view.performAccessibilityAction(i7, bundle);
    }

    public static void k(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void l(View view, int i7, int i8, int i9, int i10) {
        view.postInvalidateOnAnimation(i7, i8, i9, i10);
    }

    public static void m(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static void n(View view, Runnable runnable, long j7) {
        view.postOnAnimationDelayed(runnable, j7);
    }

    public static void o(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public static void p(View view) {
        view.requestFitSystemWindows();
    }

    public static void q(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void r(View view, boolean z6) {
        view.setHasTransientState(z6);
    }

    public static void s(View view, int i7) {
        view.setImportantForAccessibility(i7);
    }
}
