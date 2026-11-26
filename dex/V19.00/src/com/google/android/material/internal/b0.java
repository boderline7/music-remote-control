package com.google.android.material.internal;

import android.content.Context;
import android.text.TextPaint;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b0 {

    /* renamed from: c, reason: collision with root package name */
    public float f3798c;

    /* renamed from: d, reason: collision with root package name */
    public float f3799d;

    /* renamed from: f, reason: collision with root package name */
    public final WeakReference f3801f;

    /* renamed from: g, reason: collision with root package name */
    public m3.e f3802g;

    /* renamed from: a, reason: collision with root package name */
    public final TextPaint f3796a = new TextPaint(1);

    /* renamed from: b, reason: collision with root package name */
    public final z2.b f3797b = new z2.b(1, this);

    /* renamed from: e, reason: collision with root package name */
    public boolean f3800e = true;

    public b0(a0 a0Var) {
        this.f3801f = new WeakReference(null);
        this.f3801f = new WeakReference(a0Var);
    }

    public final float a(String str) {
        if (!this.f3800e) {
            return this.f3798c;
        }
        b(str);
        return this.f3798c;
    }

    public final void b(String str) {
        TextPaint textPaint = this.f3796a;
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        this.f3798c = str == null ? 0.0f : textPaint.measureText((CharSequence) str, 0, str.length());
        if (str != null) {
            f7 = Math.abs(textPaint.getFontMetrics().ascent);
        }
        this.f3799d = f7;
        this.f3800e = false;
    }

    public final void c(m3.e eVar, Context context) {
        if (this.f3802g != eVar) {
            this.f3802g = eVar;
            if (eVar != null) {
                TextPaint textPaint = this.f3796a;
                z2.b bVar = this.f3797b;
                eVar.f(context, textPaint, bVar);
                a0 a0Var = (a0) this.f3801f.get();
                if (a0Var != null) {
                    textPaint.drawableState = a0Var.getState();
                }
                eVar.e(context, textPaint, bVar);
                this.f3800e = true;
            }
            a0 a0Var2 = (a0) this.f3801f.get();
            if (a0Var2 != null) {
                a0Var2.a();
                a0Var2.onStateChange(a0Var2.getState());
            }
        }
    }
}
