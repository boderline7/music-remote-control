package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c0 extends n1.r {
    @Override // n1.r
    public final void d(n1.y yVar) {
        View view = yVar.f7686b;
        if (view instanceof TextView) {
            yVar.f7685a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // n1.r
    public final void g(n1.y yVar) {
        View view = yVar.f7686b;
        if (view instanceof TextView) {
            yVar.f7685a.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // n1.r
    public final Animator k(ViewGroup viewGroup, n1.y yVar, n1.y yVar2) {
        if (yVar != null && yVar2 != null && (yVar.f7686b instanceof TextView)) {
            View view = yVar2.f7686b;
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                HashMap hashMap = yVar.f7685a;
                HashMap hashMap2 = yVar2.f7685a;
                float floatValue = hashMap.get("android:textscale:scale") != null ? ((Float) hashMap.get("android:textscale:scale")).floatValue() : 1.0f;
                float floatValue2 = hashMap2.get("android:textscale:scale") != null ? ((Float) hashMap2.get("android:textscale:scale")).floatValue() : 1.0f;
                if (floatValue == floatValue2) {
                    return null;
                }
                ValueAnimator ofFloat = ValueAnimator.ofFloat(floatValue, floatValue2);
                ofFloat.addUpdateListener(new androidx.swiperefreshlayout.widget.b(2, this, textView));
                return ofFloat;
            }
        }
        return null;
    }
}
