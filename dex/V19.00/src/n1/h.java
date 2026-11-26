package n1;

import android.animation.ObjectAnimator;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends l0 {
    public static final int IN = 1;
    public static final int OUT = 2;

    public h(int i7) {
        if ((i7 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.f7638x = i7;
    }

    public final ObjectAnimator J(View view, float f7, float f8) {
        if (f7 == f8) {
            return null;
        }
        b0.f7590a.O(view, f7);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, b0.f7591b, f8);
        ofFloat.addListener(new androidx.recyclerview.widget.b0(view));
        a(new g(0, this, view));
        return ofFloat;
    }

    @Override // n1.r
    public final void g(y yVar) {
        l0.H(yVar);
        yVar.f7685a.put("android:fade:transitionAlpha", Float.valueOf(b0.f7590a.N(yVar.f7686b)));
    }
}
