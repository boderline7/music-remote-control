package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.internal.e0;
import l3.d;
import l3.j;
import l3.n;
import l3.o;
import l3.p;
import l3.r;
import l3.s;
import p0.d1;
import p0.m0;
import q2.c;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class LinearProgressIndicator extends BaseProgressIndicator<s> {
    public static final int DEF_STYLE_RES = l.Widget_MaterialComponents_LinearProgressIndicator;
    public static final int INDETERMINATE_ANIMATION_TYPE_CONTIGUOUS = 0;
    public static final int INDETERMINATE_ANIMATION_TYPE_DISJOINT = 1;
    public static final int INDICATOR_DIRECTION_END_TO_START = 3;
    public static final int INDICATOR_DIRECTION_LEFT_TO_RIGHT = 0;
    public static final int INDICATOR_DIRECTION_RIGHT_TO_LEFT = 1;
    public static final int INDICATOR_DIRECTION_START_TO_END = 2;

    public LinearProgressIndicator(Context context) {
        this(context, null);
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7, DEF_STYLE_RES);
        Context context2 = getContext();
        s sVar = (s) this.f4023a;
        setIndeterminateDrawable(new n(context2, sVar, new o(sVar), sVar.f7418g == 0 ? new p(sVar) : new r(context2, sVar)));
        setProgressDrawable(new j(getContext(), sVar, new o(sVar)));
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final d a(Context context, AttributeSet attributeSet) {
        int i7 = c.linearProgressIndicatorStyle;
        int i8 = DEF_STYLE_RES;
        s sVar = new s(context, attributeSet, i7, i8);
        int[] iArr = m.LinearProgressIndicator;
        int i9 = c.linearProgressIndicatorStyle;
        e0.a(context, attributeSet, i9, i8);
        e0.b(context, attributeSet, iArr, i9, i8, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i9, i8);
        sVar.f7418g = obtainStyledAttributes.getInt(m.LinearProgressIndicator_indeterminateAnimationType, 1);
        sVar.f7419h = obtainStyledAttributes.getInt(m.LinearProgressIndicator_indicatorDirectionLinear, 0);
        obtainStyledAttributes.recycle();
        sVar.a();
        sVar.f7420i = sVar.f7419h == 1;
        return sVar;
    }

    public int getIndeterminateAnimationType() {
        return ((s) this.f4023a).f7418g;
    }

    public int getIndicatorDirection() {
        return ((s) this.f4023a).f7419h;
    }

    @Override // android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        d dVar = this.f4023a;
        s sVar = (s) dVar;
        boolean z7 = true;
        if (((s) dVar).f7419h != 1) {
            int i11 = d1.OVER_SCROLL_ALWAYS;
            if ((m0.d(this) != 1 || ((s) dVar).f7419h != 2) && (m0.d(this) != 0 || ((s) dVar).f7419h != 3)) {
                z7 = false;
            }
        }
        sVar.f7420i = z7;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        int paddingRight = i7 - (getPaddingRight() + getPaddingLeft());
        int paddingBottom = i8 - (getPaddingBottom() + getPaddingTop());
        n indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
        j progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
    }

    public void setIndeterminateAnimationType(int i7) {
        d dVar = this.f4023a;
        if (((s) dVar).f7418g == i7) {
            return;
        }
        if (b() && isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        ((s) dVar).f7418g = i7;
        ((s) dVar).a();
        if (i7 == 0) {
            n indeterminateDrawable = getIndeterminateDrawable();
            p pVar = new p((s) dVar);
            indeterminateDrawable.f7393m = pVar;
            pVar.f7060a = indeterminateDrawable;
        } else {
            n indeterminateDrawable2 = getIndeterminateDrawable();
            r rVar = new r(getContext(), (s) dVar);
            indeterminateDrawable2.f7393m = rVar;
            rVar.f7060a = indeterminateDrawable2;
        }
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(int... iArr) {
        super.setIndicatorColor(iArr);
        ((s) this.f4023a).a();
    }

    public void setIndicatorDirection(int i7) {
        d dVar = this.f4023a;
        ((s) dVar).f7419h = i7;
        s sVar = (s) dVar;
        boolean z6 = true;
        if (i7 != 1) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            if ((m0.d(this) != 1 || ((s) dVar).f7419h != 2) && (m0.d(this) != 0 || i7 != 3)) {
                z6 = false;
            }
        }
        sVar.f7420i = z6;
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setProgressCompat(int i7, boolean z6) {
        d dVar = this.f4023a;
        if (dVar != null && ((s) dVar).f7418g == 0 && isIndeterminate()) {
            return;
        }
        super.setProgressCompat(i7, z6);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int i7) {
        super.setTrackCornerRadius(i7);
        ((s) this.f4023a).a();
        invalidate();
    }
}
