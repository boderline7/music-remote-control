package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import p0.d1;
import p0.l0;
import p0.m0;
import p3.l;
import p3.n;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class f extends ConstraintLayout {

    /* renamed from: s, reason: collision with root package name */
    public final e f4462s;

    /* renamed from: t, reason: collision with root package name */
    public int f4463t;

    /* renamed from: u, reason: collision with root package name */
    public final p3.j f4464u;

    /* JADX WARN: Type inference failed for: r5v3, types: [com.google.android.material.timepicker.e] */
    public f(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(context).inflate(q2.i.material_radial_view_group, this);
        p3.j jVar = new p3.j();
        this.f4464u = jVar;
        l lVar = new l(0.5f);
        n g7 = jVar.f8263a.f8241a.g();
        g7.f8293e = lVar;
        g7.f8294f = lVar;
        g7.f8295g = lVar;
        g7.f8296h = lVar;
        jVar.setShapeAppearanceModel(g7.a());
        this.f4464u.o(ColorStateList.valueOf(-1));
        p3.j jVar2 = this.f4464u;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        l0.q(this, jVar2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.RadialViewGroup, i7, 0);
        this.f4463t = obtainStyledAttributes.getDimensionPixelSize(m.RadialViewGroup_materialCircleRadius, 0);
        this.f4462s = new Runnable() { // from class: com.google.android.material.timepicker.e
            @Override // java.lang.Runnable
            public final void run() {
                f.this.p();
            }
        };
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i7, layoutParams);
        if (view.getId() == -1) {
            int i8 = d1.OVER_SCROLL_ALWAYS;
            view.setId(m0.a());
        }
        Handler handler = getHandler();
        if (handler != null) {
            e eVar = this.f4462s;
            handler.removeCallbacks(eVar);
            handler.post(eVar);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        p();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            e eVar = this.f4462s;
            handler.removeCallbacks(eVar);
            handler.post(eVar);
        }
    }

    public abstract void p();

    @Override // android.view.View
    public final void setBackgroundColor(int i7) {
        this.f4464u.o(ColorStateList.valueOf(i7));
    }
}
