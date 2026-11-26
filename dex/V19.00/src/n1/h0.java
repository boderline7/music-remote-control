package n1;

import android.graphics.Matrix;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h0 extends g0 {
    @Override // n1.g0, p3.f
    public final void I(int i7, View view) {
        view.setTransitionVisibility(i7);
    }

    @Override // n1.c0
    public final float N(View view) {
        float transitionAlpha;
        transitionAlpha = view.getTransitionAlpha();
        return transitionAlpha;
    }

    @Override // n1.c0
    public final void O(View view, float f7) {
        view.setTransitionAlpha(f7);
    }

    @Override // n1.e0
    public final void P(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }

    @Override // n1.e0
    public final void Q(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }

    @Override // n1.f0
    public final void R(View view, int i7, int i8, int i9, int i10) {
        view.setLeftTopRightBottom(i7, i8, i9, i10);
    }
}
