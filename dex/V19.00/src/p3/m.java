package p3;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends l6.f {
    @Override // l6.f
    public final void y(float f7, float f8, z zVar) {
        zVar.e(CropImageView.DEFAULT_ASPECT_RATIO, f8 * f7, 180.0f, 90.0f);
        float f9 = f8 * 2.0f * f7;
        zVar.a(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, f9, f9, 180.0f, 90.0f);
    }
}
