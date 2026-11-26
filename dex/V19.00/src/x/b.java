package x;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends y.o {

    /* renamed from: a, reason: collision with root package name */
    public final t.p f9521a;

    /* renamed from: b, reason: collision with root package name */
    public t.m f9522b;

    /* renamed from: c, reason: collision with root package name */
    public t.o f9523c;

    public b() {
        t.p pVar = new t.p();
        pVar.f9003k = false;
        this.f9521a = pVar;
        this.f9523c = pVar;
    }

    @Override // y.o
    public final float a() {
        return this.f9523c.b();
    }

    public final void b(float f7, float f8, float f9, float f10, float f11, float f12) {
        t.p pVar = this.f9521a;
        this.f9523c = pVar;
        pVar.f9004l = f7;
        boolean z6 = f7 > f8;
        pVar.f9003k = z6;
        if (z6) {
            pVar.d(-f9, f7 - f8, f11, f12, f10);
        } else {
            pVar.d(f9, f8 - f7, f11, f12, f10);
        }
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f7) {
        return this.f9523c.getInterpolation(f7);
    }
}
