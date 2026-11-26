package o1;

import android.graphics.Paint;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends l {

    /* renamed from: e, reason: collision with root package name */
    public k4.a f7811e;

    /* renamed from: f, reason: collision with root package name */
    public float f7812f;

    /* renamed from: g, reason: collision with root package name */
    public k4.a f7813g;

    /* renamed from: h, reason: collision with root package name */
    public float f7814h;

    /* renamed from: i, reason: collision with root package name */
    public float f7815i;

    /* renamed from: j, reason: collision with root package name */
    public float f7816j;

    /* renamed from: k, reason: collision with root package name */
    public float f7817k;

    /* renamed from: l, reason: collision with root package name */
    public float f7818l;

    /* renamed from: m, reason: collision with root package name */
    public Paint.Cap f7819m;

    /* renamed from: n, reason: collision with root package name */
    public Paint.Join f7820n;

    /* renamed from: o, reason: collision with root package name */
    public float f7821o;

    @Override // o1.k
    public final boolean a() {
        return this.f7813g.d() || this.f7811e.d();
    }

    @Override // o1.k
    public final boolean b(int[] iArr) {
        return this.f7811e.e(iArr) | this.f7813g.e(iArr);
    }

    public float getFillAlpha() {
        return this.f7815i;
    }

    public int getFillColor() {
        return this.f7813g.f7258a;
    }

    public float getStrokeAlpha() {
        return this.f7814h;
    }

    public int getStrokeColor() {
        return this.f7811e.f7258a;
    }

    public float getStrokeWidth() {
        return this.f7812f;
    }

    public float getTrimPathEnd() {
        return this.f7817k;
    }

    public float getTrimPathOffset() {
        return this.f7818l;
    }

    public float getTrimPathStart() {
        return this.f7816j;
    }

    public void setFillAlpha(float f7) {
        this.f7815i = f7;
    }

    public void setFillColor(int i7) {
        this.f7813g.f7258a = i7;
    }

    public void setStrokeAlpha(float f7) {
        this.f7814h = f7;
    }

    public void setStrokeColor(int i7) {
        this.f7811e.f7258a = i7;
    }

    public void setStrokeWidth(float f7) {
        this.f7812f = f7;
    }

    public void setTrimPathEnd(float f7) {
        this.f7817k = f7;
    }

    public void setTrimPathOffset(float f7) {
        this.f7818l = f7;
    }

    public void setTrimPathStart(float f7) {
        this.f7816j = f7;
    }
}
