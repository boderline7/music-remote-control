package t;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p implements o {

    /* renamed from: a, reason: collision with root package name */
    public float f8993a;

    /* renamed from: b, reason: collision with root package name */
    public float f8994b;

    /* renamed from: c, reason: collision with root package name */
    public float f8995c;

    /* renamed from: d, reason: collision with root package name */
    public float f8996d;

    /* renamed from: e, reason: collision with root package name */
    public float f8997e;

    /* renamed from: f, reason: collision with root package name */
    public float f8998f;

    /* renamed from: g, reason: collision with root package name */
    public float f8999g;

    /* renamed from: h, reason: collision with root package name */
    public float f9000h;

    /* renamed from: i, reason: collision with root package name */
    public float f9001i;

    /* renamed from: j, reason: collision with root package name */
    public int f9002j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f9003k;

    /* renamed from: l, reason: collision with root package name */
    public float f9004l;

    /* renamed from: m, reason: collision with root package name */
    public float f9005m;

    @Override // t.o
    public final boolean a() {
        return b() < 1.0E-5f && Math.abs(this.f9001i - this.f9005m) < 1.0E-5f;
    }

    @Override // t.o
    public final float b() {
        return this.f9003k ? -c(this.f9005m) : c(this.f9005m);
    }

    public final float c(float f7) {
        float f8;
        float f9;
        float f10 = this.f8996d;
        if (f7 <= f10) {
            f8 = this.f8993a;
            f9 = this.f8994b;
        } else {
            int i7 = this.f9002j;
            if (i7 == 1) {
                return CropImageView.DEFAULT_ASPECT_RATIO;
            }
            f7 -= f10;
            f10 = this.f8997e;
            if (f7 >= f10) {
                if (i7 == 2) {
                    return this.f9000h;
                }
                float f11 = f7 - f10;
                float f12 = this.f8998f;
                if (f11 >= f12) {
                    return this.f9001i;
                }
                float f13 = this.f8995c;
                return f13 - ((f11 * f13) / f12);
            }
            f8 = this.f8994b;
            f9 = this.f8995c;
        }
        return (((f9 - f8) * f7) / f10) + f8;
    }

    public final void d(float f7, float f8, float f9, float f10, float f11) {
        if (f7 == CropImageView.DEFAULT_ASPECT_RATIO) {
            f7 = 1.0E-4f;
        }
        this.f8993a = f7;
        float f12 = f7 / f9;
        float f13 = (f12 * f7) / 2.0f;
        if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
            float sqrt = (float) Math.sqrt((f8 - ((((-f7) / f9) * f7) / 2.0f)) * f9);
            if (sqrt < f10) {
                this.f9002j = 2;
                this.f8993a = f7;
                this.f8994b = sqrt;
                this.f8995c = CropImageView.DEFAULT_ASPECT_RATIO;
                float f14 = (sqrt - f7) / f9;
                this.f8996d = f14;
                this.f8997e = sqrt / f9;
                this.f8999g = ((f7 + sqrt) * f14) / 2.0f;
                this.f9000h = f8;
                this.f9001i = f8;
                return;
            }
            this.f9002j = 3;
            this.f8993a = f7;
            this.f8994b = f10;
            this.f8995c = f10;
            float f15 = (f10 - f7) / f9;
            this.f8996d = f15;
            float f16 = f10 / f9;
            this.f8998f = f16;
            float f17 = ((f7 + f10) * f15) / 2.0f;
            float f18 = (f16 * f10) / 2.0f;
            this.f8997e = ((f8 - f17) - f18) / f10;
            this.f8999g = f17;
            this.f9000h = f8 - f18;
            this.f9001i = f8;
            return;
        }
        if (f13 >= f8) {
            this.f9002j = 1;
            this.f8993a = f7;
            this.f8994b = CropImageView.DEFAULT_ASPECT_RATIO;
            this.f8999g = f8;
            this.f8996d = (2.0f * f8) / f7;
            return;
        }
        float f19 = f8 - f13;
        float f20 = f19 / f7;
        if (f20 + f12 < f11) {
            this.f9002j = 2;
            this.f8993a = f7;
            this.f8994b = f7;
            this.f8995c = CropImageView.DEFAULT_ASPECT_RATIO;
            this.f8999g = f19;
            this.f9000h = f8;
            this.f8996d = f20;
            this.f8997e = f12;
            return;
        }
        float sqrt2 = (float) Math.sqrt(((f7 * f7) / 2.0f) + (f9 * f8));
        float f21 = (sqrt2 - f7) / f9;
        this.f8996d = f21;
        float f22 = sqrt2 / f9;
        this.f8997e = f22;
        if (sqrt2 < f10) {
            this.f9002j = 2;
            this.f8993a = f7;
            this.f8994b = sqrt2;
            this.f8995c = CropImageView.DEFAULT_ASPECT_RATIO;
            this.f8996d = f21;
            this.f8997e = f22;
            this.f8999g = ((f7 + sqrt2) * f21) / 2.0f;
            this.f9000h = f8;
            return;
        }
        this.f9002j = 3;
        this.f8993a = f7;
        this.f8994b = f10;
        this.f8995c = f10;
        float f23 = (f10 - f7) / f9;
        this.f8996d = f23;
        float f24 = f10 / f9;
        this.f8998f = f24;
        float f25 = ((f7 + f10) * f23) / 2.0f;
        float f26 = (f24 * f10) / 2.0f;
        this.f8997e = ((f8 - f25) - f26) / f10;
        this.f8999g = f25;
        this.f9000h = f8 - f26;
        this.f9001i = f8;
    }

    @Override // t.o
    public final float getInterpolation(float f7) {
        float f8;
        float f9 = this.f8996d;
        if (f7 <= f9) {
            float f10 = this.f8993a;
            f8 = ((((this.f8994b - f10) * f7) * f7) / (f9 * 2.0f)) + (f10 * f7);
        } else {
            int i7 = this.f9002j;
            if (i7 == 1) {
                f8 = this.f8999g;
            } else {
                float f11 = f7 - f9;
                float f12 = this.f8997e;
                if (f11 < f12) {
                    float f13 = this.f8999g;
                    float f14 = this.f8994b;
                    f8 = ((((this.f8995c - f14) * f11) * f11) / (f12 * 2.0f)) + (f14 * f11) + f13;
                } else if (i7 == 2) {
                    f8 = this.f9000h;
                } else {
                    float f15 = f11 - f12;
                    float f16 = this.f8998f;
                    if (f15 <= f16) {
                        float f17 = this.f9000h;
                        float f18 = this.f8995c * f15;
                        f8 = (f17 + f18) - ((f18 * f15) / (f16 * 2.0f));
                    } else {
                        f8 = this.f9001i;
                    }
                }
            }
        }
        this.f9005m = f7;
        return this.f9003k ? this.f9004l - f8 : this.f9004l + f8;
    }
}
