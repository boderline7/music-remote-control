package t;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m implements o {

    /* renamed from: a, reason: collision with root package name */
    public double f8983a;

    /* renamed from: b, reason: collision with root package name */
    public double f8984b;

    /* renamed from: c, reason: collision with root package name */
    public double f8985c;

    /* renamed from: d, reason: collision with root package name */
    public float f8986d;

    /* renamed from: e, reason: collision with root package name */
    public float f8987e;

    /* renamed from: f, reason: collision with root package name */
    public float f8988f;

    /* renamed from: g, reason: collision with root package name */
    public float f8989g;

    /* renamed from: h, reason: collision with root package name */
    public float f8990h;

    /* renamed from: i, reason: collision with root package name */
    public int f8991i;

    @Override // t.o
    public final boolean a() {
        double d7 = this.f8987e - this.f8985c;
        double d8 = this.f8984b;
        double d9 = this.f8988f;
        return Math.sqrt((((d8 * d7) * d7) + ((d9 * d9) * ((double) this.f8989g))) / d8) <= ((double) this.f8990h);
    }

    @Override // t.o
    public final float b() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @Override // t.o
    public final float getInterpolation(float f7) {
        m mVar = this;
        float f8 = f7;
        double d7 = f8 - mVar.f8986d;
        double d8 = mVar.f8984b;
        double d9 = mVar.f8983a;
        int sqrt = (int) ((9.0d / ((Math.sqrt(d8 / mVar.f8989g) * d7) * 4.0d)) + 1.0d);
        double d10 = d7 / sqrt;
        int i7 = 0;
        while (i7 < sqrt) {
            double d11 = mVar.f8987e;
            double d12 = mVar.f8985c;
            int i8 = sqrt;
            int i9 = i7;
            double d13 = mVar.f8988f;
            double d14 = mVar.f8989g;
            double d15 = ((((((-d8) * (d11 - d12)) - (d9 * d13)) / d14) * d10) / 2.0d) + d13;
            double d16 = ((((-((((d10 * d15) / 2.0d) + d11) - d12)) * d8) - (d15 * d9)) / d14) * d10;
            float f9 = (float) (d13 + d16);
            this.f8988f = f9;
            float f10 = (float) ((((d16 / 2.0d) + d13) * d10) + d11);
            this.f8987e = f10;
            int i10 = this.f8991i;
            if (i10 > 0) {
                if (f10 < CropImageView.DEFAULT_ASPECT_RATIO && (i10 & 1) == 1) {
                    this.f8987e = -f10;
                    this.f8988f = -f9;
                }
                float f11 = this.f8987e;
                if (f11 > 1.0f && (i10 & 2) == 2) {
                    this.f8987e = 2.0f - f11;
                    this.f8988f = -this.f8988f;
                }
            }
            f8 = f7;
            sqrt = i8;
            i7 = i9 + 1;
            mVar = this;
        }
        m mVar2 = mVar;
        mVar2.f8986d = f8;
        return mVar2.f8987e;
    }
}
