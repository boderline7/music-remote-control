package com.google.android.material.timepicker;

import android.view.ViewTreeObserver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ClockFaceView f4459a;

    public b(ClockFaceView clockFaceView) {
        this.f4459a = clockFaceView;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        ClockFaceView clockFaceView = this.f4459a;
        if (!clockFaceView.isShown()) {
            return true;
        }
        clockFaceView.getViewTreeObserver().removeOnPreDrawListener(this);
        int height = ((clockFaceView.getHeight() / 2) - clockFaceView.f4434v.f4442d) - clockFaceView.D;
        if (height != clockFaceView.f4463t) {
            clockFaceView.f4463t = height;
            clockFaceView.p();
            int i7 = clockFaceView.f4463t;
            ClockHandView clockHandView = clockFaceView.f4434v;
            clockHandView.f4450l = i7;
            clockHandView.invalidate();
        }
        return true;
    }
}
