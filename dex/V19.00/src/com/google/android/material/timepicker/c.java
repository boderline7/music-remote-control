package com.google.android.material.timepicker;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.fragment.app.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ClockFaceView f4460d;

    public c(ClockFaceView clockFaceView) {
        this.f4460d = clockFaceView;
    }

    @Override // p0.c
    public final void d(View view, q0.i iVar) {
        View.AccessibilityDelegate accessibilityDelegate = this.f8103a;
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        int intValue = ((Integer) view.getTag(q2.g.material_value_index)).intValue();
        if (intValue > 0) {
            accessibilityNodeInfo.setTraversalAfter((View) this.f4460d.f4438z.get(intValue - 1));
        }
        iVar.g(o.b(0, 1, intValue, 1, false, view.isSelected()));
        accessibilityNodeInfo.setClickable(true);
        iVar.b(q0.g.ACTION_CLICK);
    }

    @Override // p0.c
    public final boolean g(View view, int i7, Bundle bundle) {
        if (i7 != 16) {
            return super.g(view, i7, bundle);
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        ClockFaceView clockFaceView = this.f4460d;
        view.getHitRect(clockFaceView.f4435w);
        float centerX = clockFaceView.f4435w.centerX();
        float centerY = clockFaceView.f4435w.centerY();
        clockFaceView.f4434v.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, centerX, centerY, 0));
        clockFaceView.f4434v.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 1, centerX, centerY, 0));
        return true;
    }
}
