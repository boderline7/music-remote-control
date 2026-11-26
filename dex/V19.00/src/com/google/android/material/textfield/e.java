package com.google.android.material.textfield;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e extends p3.i {

    /* renamed from: v, reason: collision with root package name */
    public final RectF f4337v;

    public e(e eVar) {
        super(eVar);
        this.f4337v = eVar.f4337v;
    }

    public e(p3.p pVar, RectF rectF) {
        super(pVar);
        this.f4337v = rectF;
    }

    @Override // p3.i, android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        f fVar = new f(this);
        fVar.invalidateSelf();
        return fVar;
    }
}
