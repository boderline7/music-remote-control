package n1;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c0 extends p3.f {

    /* renamed from: e, reason: collision with root package name */
    public static boolean f7592e = true;

    public c0() {
        super(19, 0);
    }

    public float N(View view) {
        float transitionAlpha;
        if (f7592e) {
            try {
                transitionAlpha = view.getTransitionAlpha();
                return transitionAlpha;
            } catch (NoSuchMethodError unused) {
                f7592e = false;
            }
        }
        return view.getAlpha();
    }

    public void O(View view, float f7) {
        if (f7592e) {
            try {
                view.setTransitionAlpha(f7);
                return;
            } catch (NoSuchMethodError unused) {
                f7592e = false;
            }
        }
        view.setAlpha(f7);
    }
}
