package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.internal.e0;
import l3.d;
import l3.e;
import l3.g;
import l3.h;
import l3.j;
import l3.n;
import l6.f;
import q2.c;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CircularProgressIndicator extends BaseProgressIndicator<h> {
    public static final int DEF_STYLE_RES = l.Widget_MaterialComponents_CircularProgressIndicator;
    public static final int INDICATOR_DIRECTION_CLOCKWISE = 0;
    public static final int INDICATOR_DIRECTION_COUNTERCLOCKWISE = 1;

    public CircularProgressIndicator(Context context) {
        this(context, null);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7, DEF_STYLE_RES);
        Context context2 = getContext();
        h hVar = (h) this.f4023a;
        setIndeterminateDrawable(new n(context2, hVar, new e(hVar), new g(hVar)));
        setProgressDrawable(new j(getContext(), hVar, new e(hVar)));
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final d a(Context context, AttributeSet attributeSet) {
        int i7 = c.circularProgressIndicatorStyle;
        int i8 = DEF_STYLE_RES;
        h hVar = new h(context, attributeSet, i7, i8);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(q2.e.mtrl_progress_circular_size_medium);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(q2.e.mtrl_progress_circular_inset_medium);
        int[] iArr = m.CircularProgressIndicator;
        e0.a(context, attributeSet, i7, i8);
        e0.b(context, attributeSet, iArr, i7, i8, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i7, i8);
        hVar.f7368g = Math.max(f.z(context, obtainStyledAttributes, m.CircularProgressIndicator_indicatorSize, dimensionPixelSize), hVar.f7343a * 2);
        hVar.f7369h = f.z(context, obtainStyledAttributes, m.CircularProgressIndicator_indicatorInset, dimensionPixelSize2);
        hVar.f7370i = obtainStyledAttributes.getInt(m.CircularProgressIndicator_indicatorDirectionCircular, 0);
        obtainStyledAttributes.recycle();
        return hVar;
    }

    public int getIndicatorDirection() {
        return ((h) this.f4023a).f7370i;
    }

    public int getIndicatorInset() {
        return ((h) this.f4023a).f7369h;
    }

    public int getIndicatorSize() {
        return ((h) this.f4023a).f7368g;
    }

    public void setIndicatorDirection(int i7) {
        ((h) this.f4023a).f7370i = i7;
        invalidate();
    }

    public void setIndicatorInset(int i7) {
        d dVar = this.f4023a;
        if (((h) dVar).f7369h != i7) {
            ((h) dVar).f7369h = i7;
            invalidate();
        }
    }

    public void setIndicatorSize(int i7) {
        int max = Math.max(i7, getTrackThickness() * 2);
        d dVar = this.f4023a;
        if (((h) dVar).f7368g != max) {
            ((h) dVar).f7368g = max;
            ((h) dVar).getClass();
            invalidate();
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i7) {
        super.setTrackThickness(i7);
        ((h) this.f4023a).getClass();
    }
}
