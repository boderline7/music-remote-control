package com.google.android.material.bottomappbar;

import com.yalantis.ucrop.view.CropImageView;
import p3.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i extends p3.f implements Cloneable {

    /* renamed from: e, reason: collision with root package name */
    public float f3373e;

    /* renamed from: f, reason: collision with root package name */
    public float f3374f;

    /* renamed from: g, reason: collision with root package name */
    public float f3375g;

    /* renamed from: h, reason: collision with root package name */
    public float f3376h;

    /* renamed from: i, reason: collision with root package name */
    public float f3377i;

    /* renamed from: j, reason: collision with root package name */
    public float f3378j;

    public final void N(float f7) {
        if (f7 < CropImageView.DEFAULT_ASPECT_RATIO) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f3376h = f7;
    }

    @Override // p3.f
    public final void p(float f7, float f8, float f9, z zVar) {
        float f10;
        float f11;
        float f12 = this.f3375g;
        if (f12 == CropImageView.DEFAULT_ASPECT_RATIO) {
            zVar.d(f7, CropImageView.DEFAULT_ASPECT_RATIO);
            return;
        }
        float f13 = ((this.f3374f * 2.0f) + f12) / 2.0f;
        float f14 = f9 * this.f3373e;
        float f15 = f8 + this.f3377i;
        float a7 = a.a.a(1.0f, f9, f13, this.f3376h * f9);
        if (a7 / f13 >= 1.0f) {
            zVar.d(f7, CropImageView.DEFAULT_ASPECT_RATIO);
            return;
        }
        float f16 = this.f3378j;
        float f17 = f16 * f9;
        boolean z6 = f16 == -1.0f || Math.abs((f16 * 2.0f) - f12) < 0.1f;
        if (z6) {
            f10 = a7;
            f11 = 0.0f;
        } else {
            f11 = 1.75f;
            f10 = 0.0f;
        }
        float f18 = f13 + f14;
        float f19 = f10 + f14;
        float sqrt = (float) Math.sqrt((f18 * f18) - (f19 * f19));
        float f20 = f15 - sqrt;
        float f21 = f15 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan(sqrt / f19));
        float f22 = (90.0f - degrees) + f11;
        zVar.d(f20, CropImageView.DEFAULT_ASPECT_RATIO);
        float f23 = f14 * 2.0f;
        zVar.a(f20 - f14, CropImageView.DEFAULT_ASPECT_RATIO, f20 + f14, f23, 270.0f, degrees);
        if (z6) {
            zVar.a(f15 - f13, (-f13) - f10, f15 + f13, f13 - f10, 180.0f - f22, (f22 * 2.0f) - 180.0f);
        } else {
            float f24 = this.f3374f;
            float f25 = f17 * 2.0f;
            float f26 = f15 - f13;
            float f27 = f17 + f24;
            zVar.a(f26, -f27, f26 + f24 + f25, f27, 180.0f - f22, ((f22 * 2.0f) - 180.0f) / 2.0f);
            float f28 = f15 + f13;
            float f29 = this.f3374f;
            zVar.d(f28 - ((f29 / 2.0f) + f17), f29 + f17);
            float f30 = this.f3374f;
            float f31 = f17 + f30;
            zVar.a(f28 - (f25 + f30), -f31, f28, f31, 90.0f, f22 - 90.0f);
        }
        zVar.a(f21 - f14, CropImageView.DEFAULT_ASPECT_RATIO, f21 + f14, f23, 270.0f - degrees, degrees);
        zVar.d(f7, CropImageView.DEFAULT_ASPECT_RATIO);
    }
}
