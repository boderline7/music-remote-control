package p3;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends l6.f {
    @Override // l6.f
    public final void y(float f7, float f8, z zVar) {
        zVar.e(CropImageView.DEFAULT_ASPECT_RATIO, f8 * f7, 180.0f, 90.0f);
        double d7 = f8;
        double d8 = f7;
        zVar.d((float) (Math.sin(Math.toRadians(90.0f)) * d7 * d8), (float) (Math.sin(Math.toRadians(CropImageView.DEFAULT_ASPECT_RATIO)) * d7 * d8));
    }
}
