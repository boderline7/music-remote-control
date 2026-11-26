package p3;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends f {

    /* renamed from: e, reason: collision with root package name */
    public final float f8239e;

    public g(float f7) {
        super(0, 0);
        this.f8239e = f7 - 0.001f;
    }

    @Override // p3.f
    public final void p(float f7, float f8, float f9, z zVar) {
        double d7 = this.f8239e;
        float sqrt = (float) ((Math.sqrt(2.0d) * d7) / 2.0d);
        float sqrt2 = (float) Math.sqrt(Math.pow(d7, 2.0d) - Math.pow(sqrt, 2.0d));
        zVar.e(f8 - sqrt, ((float) (-((Math.sqrt(2.0d) * d7) - d7))) + sqrt2, 270.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        zVar.d(f8, (float) (-((Math.sqrt(2.0d) * d7) - d7)));
        zVar.d(f8 + sqrt, ((float) (-((Math.sqrt(2.0d) * d7) - d7))) + sqrt2);
    }
}
