package com.google.android.material.textfield;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class g extends p3.j {

    /* renamed from: z, reason: collision with root package name */
    public static final /* synthetic */ int f4338z = 0;

    /* renamed from: y, reason: collision with root package name */
    public e f4339y;

    public g(e eVar) {
        super(eVar);
        this.f4339y = eVar;
    }

    @Override // p3.j, android.graphics.drawable.Drawable
    public final Drawable mutate() {
        this.f4339y = new e(this.f4339y);
        return this;
    }

    public final void y(float f7, float f8, float f9, float f10) {
        RectF rectF = this.f4339y.f4337v;
        if (f7 == rectF.left && f8 == rectF.top && f9 == rectF.right && f10 == rectF.bottom) {
            return;
        }
        rectF.set(f7, f8, f9, f10);
        invalidateSelf();
    }
}
