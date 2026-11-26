package p0;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u1 extends x1 {
    public static void e(View view, y1 y1Var) {
        q1 j7 = j(view);
        if (j7 != null) {
            ((w2.d) j7).f9463c.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            if (j7.f8173b == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                e(viewGroup.getChildAt(i7), y1Var);
            }
        }
    }

    public static void f(View view, y1 y1Var, WindowInsets windowInsets, boolean z6) {
        q1 j7 = j(view);
        if (j7 != null) {
            j7.f8172a = windowInsets;
            if (!z6) {
                w2.d dVar = (w2.d) j7;
                View view2 = dVar.f9463c;
                int[] iArr = dVar.f9466f;
                view2.getLocationOnScreen(iArr);
                dVar.f9464d = iArr[1];
                z6 = j7.f8173b == 0;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                f(viewGroup.getChildAt(i7), y1Var, windowInsets, z6);
            }
        }
    }

    public static void g(View view, l2 l2Var, List list) {
        q1 j7 = j(view);
        if (j7 != null) {
            j7.a(l2Var, list);
            if (j7.f8173b == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                g(viewGroup.getChildAt(i7), l2Var, list);
            }
        }
    }

    public static void h(View view, y1 y1Var, i6.i iVar) {
        q1 j7 = j(view);
        if (j7 != null) {
            w2.d dVar = (w2.d) j7;
            View view2 = dVar.f9463c;
            int[] iArr = dVar.f9466f;
            view2.getLocationOnScreen(iArr);
            int i7 = dVar.f9464d - iArr[1];
            dVar.f9465e = i7;
            view2.setTranslationY(i7);
            if (j7.f8173b == 0) {
                return;
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i8 = 0; i8 < viewGroup.getChildCount(); i8++) {
                h(viewGroup.getChildAt(i8), y1Var, iVar);
            }
        }
    }

    public static WindowInsets i(View view, WindowInsets windowInsets) {
        return view.getTag(d0.c.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
    }

    public static q1 j(View view) {
        Object tag = view.getTag(d0.c.tag_window_insets_animation_callback);
        if (tag instanceof t1) {
            return ((t1) tag).f8191a;
        }
        return null;
    }
}
