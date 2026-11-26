package com.google.android.material.slider;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import n1.z;
import q0.g;
import q0.i;
import q2.k;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a extends u0.b {

    /* renamed from: q, reason: collision with root package name */
    public final c f4151q;

    /* renamed from: r, reason: collision with root package name */
    public final Rect f4152r;

    public a(c cVar) {
        super(cVar);
        this.f4152r = new Rect();
        this.f4151q = cVar;
    }

    @Override // u0.b
    public final int n(float f7, float f8) {
        int i7 = 0;
        while (true) {
            c cVar = this.f4151q;
            if (i7 >= cVar.getValues().size()) {
                return -1;
            }
            Rect rect = this.f4152r;
            cVar.t(i7, rect);
            if (rect.contains((int) f7, (int) f8)) {
                return i7;
            }
            i7++;
        }
    }

    @Override // u0.b
    public final void o(ArrayList arrayList) {
        for (int i7 = 0; i7 < this.f4151q.getValues().size(); i7++) {
            arrayList.add(Integer.valueOf(i7));
        }
    }

    @Override // u0.b
    public final boolean s(int i7, int i8, Bundle bundle) {
        c cVar = this.f4151q;
        if (!cVar.isEnabled()) {
            return false;
        }
        if (i8 != 4096 && i8 != 8192) {
            if (i8 != 16908349 || bundle == null || !bundle.containsKey(i.ACTION_ARGUMENT_PROGRESS_VALUE) || !cVar.r(bundle.getFloat(i.ACTION_ARGUMENT_PROGRESS_VALUE), i7)) {
                return false;
            }
            cVar.u();
            cVar.postInvalidate();
            p(i7);
            return true;
        }
        float f7 = cVar.N;
        if (f7 == CropImageView.DEFAULT_ASPECT_RATIO) {
            f7 = 1.0f;
        }
        if ((cVar.J - cVar.I) / f7 > 20) {
            f7 *= Math.round(r1 / r5);
        }
        if (i8 == 8192) {
            f7 = -f7;
        }
        if (cVar.i()) {
            f7 = -f7;
        }
        if (!cVar.r(z.x(cVar.getValues().get(i7).floatValue() + f7, cVar.getValueFrom(), cVar.getValueTo()), i7)) {
            return false;
        }
        cVar.u();
        cVar.postInvalidate();
        p(i7);
        return true;
    }

    @Override // u0.b
    public final void u(int i7, i iVar) {
        iVar.b(g.ACTION_SET_PROGRESS);
        c cVar = this.f4151q;
        List<Float> values = cVar.getValues();
        float floatValue = values.get(i7).floatValue();
        float valueFrom = cVar.getValueFrom();
        float valueTo = cVar.getValueTo();
        if (cVar.isEnabled()) {
            if (floatValue > valueFrom) {
                iVar.a(8192);
            }
            if (floatValue < valueTo) {
                iVar.a(4096);
            }
        }
        AccessibilityNodeInfo.RangeInfo obtain = AccessibilityNodeInfo.RangeInfo.obtain(1, valueFrom, valueTo, floatValue);
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        accessibilityNodeInfo.setRangeInfo(obtain);
        iVar.f(SeekBar.class.getName());
        StringBuilder sb = new StringBuilder();
        if (cVar.getContentDescription() != null) {
            sb.append(cVar.getContentDescription());
            sb.append(",");
        }
        String format = String.format(((float) ((int) floatValue)) == floatValue ? "%.0f" : "%.2f", Float.valueOf(floatValue));
        String string = cVar.getContext().getString(k.material_slider_value);
        if (values.size() > 1) {
            string = i7 == cVar.getValues().size() - 1 ? cVar.getContext().getString(k.material_slider_range_end) : i7 == 0 ? cVar.getContext().getString(k.material_slider_range_start) : h0.FRAGMENT_ENCODE_SET;
        }
        sb.append(String.format(Locale.US, "%s, %s", string, format));
        accessibilityNodeInfo.setContentDescription(sb.toString());
        Rect rect = this.f4152r;
        cVar.t(i7, rect);
        accessibilityNodeInfo.setBoundsInParent(rect);
    }
}
