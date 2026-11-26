package a1;

import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import y0.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k implements TransformationMethod {

    /* renamed from: a, reason: collision with root package name */
    public final TransformationMethod f220a;

    public k(TransformationMethod transformationMethod) {
        this.f220a = transformationMethod;
    }

    @Override // android.text.method.TransformationMethod
    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        if (view.isInEditMode()) {
            return charSequence;
        }
        TransformationMethod transformationMethod = this.f220a;
        if (transformationMethod != null) {
            charSequence = transformationMethod.getTransformation(charSequence, view);
        }
        if (charSequence == null || l.a().b() != 1) {
            return charSequence;
        }
        l a7 = l.a();
        a7.getClass();
        return a7.f(0, charSequence.length(), charSequence);
    }

    @Override // android.text.method.TransformationMethod
    public final void onFocusChanged(View view, CharSequence charSequence, boolean z6, int i7, Rect rect) {
        TransformationMethod transformationMethod = this.f220a;
        if (transformationMethod != null) {
            transformationMethod.onFocusChanged(view, charSequence, z6, i7, rect);
        }
    }
}
