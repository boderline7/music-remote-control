package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import p0.d1;
import p0.m0;
import p3.a0;
import p3.j;
import p3.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public final MaterialButton f3456a;

    /* renamed from: b, reason: collision with root package name */
    public p f3457b;

    /* renamed from: c, reason: collision with root package name */
    public int f3458c;

    /* renamed from: d, reason: collision with root package name */
    public int f3459d;

    /* renamed from: e, reason: collision with root package name */
    public int f3460e;

    /* renamed from: f, reason: collision with root package name */
    public int f3461f;

    /* renamed from: g, reason: collision with root package name */
    public int f3462g;

    /* renamed from: h, reason: collision with root package name */
    public int f3463h;

    /* renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f3464i;

    /* renamed from: j, reason: collision with root package name */
    public ColorStateList f3465j;

    /* renamed from: k, reason: collision with root package name */
    public ColorStateList f3466k;

    /* renamed from: l, reason: collision with root package name */
    public ColorStateList f3467l;

    /* renamed from: m, reason: collision with root package name */
    public j f3468m;

    /* renamed from: q, reason: collision with root package name */
    public boolean f3472q;

    /* renamed from: s, reason: collision with root package name */
    public RippleDrawable f3474s;

    /* renamed from: t, reason: collision with root package name */
    public int f3475t;

    /* renamed from: n, reason: collision with root package name */
    public boolean f3469n = false;

    /* renamed from: o, reason: collision with root package name */
    public boolean f3470o = false;

    /* renamed from: p, reason: collision with root package name */
    public boolean f3471p = false;

    /* renamed from: r, reason: collision with root package name */
    public boolean f3473r = true;

    public c(MaterialButton materialButton, p pVar) {
        this.f3456a = materialButton;
        this.f3457b = pVar;
    }

    public final a0 a() {
        RippleDrawable rippleDrawable = this.f3474s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.f3474s.getNumberOfLayers() > 2 ? (a0) this.f3474s.getDrawable(2) : (a0) this.f3474s.getDrawable(1);
    }

    public final j b(boolean z6) {
        RippleDrawable rippleDrawable = this.f3474s;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (j) ((LayerDrawable) ((InsetDrawable) this.f3474s.getDrawable(0)).getDrawable()).getDrawable(!z6 ? 1 : 0);
    }

    public final void c(p pVar) {
        this.f3457b = pVar;
        if (b(false) != null) {
            b(false).setShapeAppearanceModel(pVar);
        }
        if (b(true) != null) {
            b(true).setShapeAppearanceModel(pVar);
        }
        if (a() != null) {
            a().setShapeAppearanceModel(pVar);
        }
    }

    public final void d(int i7, int i8) {
        int i9 = d1.OVER_SCROLL_ALWAYS;
        MaterialButton materialButton = this.f3456a;
        int f7 = m0.f(materialButton);
        int paddingTop = materialButton.getPaddingTop();
        int e7 = m0.e(materialButton);
        int paddingBottom = materialButton.getPaddingBottom();
        int i10 = this.f3460e;
        int i11 = this.f3461f;
        this.f3461f = i8;
        this.f3460e = i7;
        if (!this.f3470o) {
            e();
        }
        m0.k(materialButton, f7, (paddingTop + i7) - i10, e7, (paddingBottom + i8) - i11);
    }

    public final void e() {
        j jVar = new j(this.f3457b);
        MaterialButton materialButton = this.f3456a;
        jVar.l(materialButton.getContext());
        j0.b.h(jVar, this.f3465j);
        PorterDuff.Mode mode = this.f3464i;
        if (mode != null) {
            j0.b.i(jVar, mode);
        }
        float f7 = this.f3463h;
        ColorStateList colorStateList = this.f3466k;
        jVar.u(f7);
        jVar.t(colorStateList);
        j jVar2 = new j(this.f3457b);
        jVar2.setTint(0);
        float f8 = this.f3463h;
        int b7 = this.f3469n ? b3.a.b(q2.c.colorSurface, materialButton) : 0;
        jVar2.u(f8);
        jVar2.t(ColorStateList.valueOf(b7));
        j jVar3 = new j(this.f3457b);
        this.f3468m = jVar3;
        j0.b.g(jVar3, -1);
        RippleDrawable rippleDrawable = new RippleDrawable(n3.a.c(this.f3467l), new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{jVar2, jVar}), this.f3458c, this.f3460e, this.f3459d, this.f3461f), this.f3468m);
        this.f3474s = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        j b8 = b(false);
        if (b8 != null) {
            b8.n(this.f3475t);
            b8.setState(materialButton.getDrawableState());
        }
    }

    public final void f() {
        int i7 = 0;
        j b7 = b(false);
        j b8 = b(true);
        if (b7 != null) {
            float f7 = this.f3463h;
            ColorStateList colorStateList = this.f3466k;
            b7.u(f7);
            b7.t(colorStateList);
            if (b8 != null) {
                float f8 = this.f3463h;
                if (this.f3469n) {
                    i7 = b3.a.b(q2.c.colorSurface, this.f3456a);
                }
                b8.u(f8);
                b8.t(ColorStateList.valueOf(i7));
            }
        }
    }
}
