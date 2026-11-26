package p0;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class r0 {
    public static void a(WindowInsets windowInsets, View view) {
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(d0.c.tag_window_insets_animation_callback);
        if (onApplyWindowInsetsListener != null) {
            onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
        }
    }

    public static l2 b(View view, l2 l2Var, Rect rect) {
        WindowInsets g7 = l2Var.g();
        if (g7 != null) {
            return l2.h(view, view.computeSystemWindowInsets(g7, rect));
        }
        rect.setEmpty();
        return l2Var;
    }

    public static boolean c(View view, float f7, float f8, boolean z6) {
        return view.dispatchNestedFling(f7, f8, z6);
    }

    public static boolean d(View view, float f7, float f8) {
        return view.dispatchNestedPreFling(f7, f8);
    }

    public static boolean e(View view, int i7, int i8, int[] iArr, int[] iArr2) {
        return view.dispatchNestedPreScroll(i7, i8, iArr, iArr2);
    }

    public static boolean f(View view, int i7, int i8, int i9, int i10, int[] iArr) {
        return view.dispatchNestedScroll(i7, i8, i9, i10, iArr);
    }

    public static ColorStateList g(View view) {
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode h(View view) {
        return view.getBackgroundTintMode();
    }

    public static float i(View view) {
        return view.getElevation();
    }

    public static l2 j(View view) {
        if (!z1.f8211d || !view.isAttachedToWindow()) {
            return null;
        }
        try {
            Object obj = z1.f8208a.get(view.getRootView());
            if (obj == null) {
                return null;
            }
            Rect rect = (Rect) z1.f8209b.get(obj);
            Rect rect2 = (Rect) z1.f8210c.get(obj);
            if (rect == null || rect2 == null) {
                return null;
            }
            int i7 = Build.VERSION.SDK_INT;
            d2 c2Var = i7 >= 30 ? new c2() : i7 >= 29 ? new b2() : new a2();
            c2Var.e(i0.c.b(rect.left, rect.top, rect.right, rect.bottom));
            c2Var.g(i0.c.b(rect2.left, rect2.top, rect2.right, rect2.bottom));
            l2 b7 = c2Var.b();
            b7.f8161a.q(b7);
            b7.f8161a.d(view.getRootView());
            return b7;
        } catch (IllegalAccessException e7) {
            e7.getMessage();
            return null;
        }
    }

    public static String k(View view) {
        return view.getTransitionName();
    }

    public static float l(View view) {
        return view.getTranslationZ();
    }

    public static float m(View view) {
        return view.getZ();
    }

    public static boolean n(View view) {
        return view.hasNestedScrollingParent();
    }

    public static boolean o(View view) {
        return view.isImportantForAccessibility();
    }

    public static boolean p(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static void q(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    public static void r(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static void s(View view, float f7) {
        view.setElevation(f7);
    }

    public static void t(View view, boolean z6) {
        view.setNestedScrollingEnabled(z6);
    }

    public static void u(View view, b0 b0Var) {
        if (Build.VERSION.SDK_INT < 30) {
            view.setTag(d0.c.tag_on_apply_window_listener, b0Var);
        }
        if (b0Var == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(d0.c.tag_window_insets_animation_callback));
        } else {
            view.setOnApplyWindowInsetsListener(new q0(view, b0Var));
        }
    }

    public static void v(View view, String str) {
        view.setTransitionName(str);
    }

    public static void w(View view, float f7) {
        view.setTranslationZ(f7);
    }

    public static void x(View view, float f7) {
        view.setZ(f7);
    }

    public static boolean y(View view, int i7) {
        return view.startNestedScroll(i7);
    }

    public static void z(View view) {
        view.stopNestedScroll();
    }
}
