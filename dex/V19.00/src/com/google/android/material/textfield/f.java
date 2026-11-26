package com.google.android.material.textfield;

import android.graphics.Canvas;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f extends g {
    @Override // p3.j
    public final void g(Canvas canvas) {
        if (this.f4339y.f4337v.isEmpty()) {
            super.g(canvas);
            return;
        }
        canvas.save();
        canvas.clipOutRect(this.f4339y.f4337v);
        super.g(canvas);
        canvas.restore();
    }
}
