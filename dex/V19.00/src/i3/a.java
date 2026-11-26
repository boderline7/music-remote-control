package i3;

import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final float DEFAULT_EPSILON = 1.0E-4f;

    public static float a(float f7, float f8, float f9, float f10) {
        return (float) Math.hypot(f9 - f7, f10 - f8);
    }

    public static float b(float f7, float f8, float f9, float f10) {
        float a7 = a(f7, f8, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        float a8 = a(f7, f8, f9, CropImageView.DEFAULT_ASPECT_RATIO);
        float a9 = a(f7, f8, f9, f10);
        float a10 = a(f7, f8, CropImageView.DEFAULT_ASPECT_RATIO, f10);
        return (a7 <= a8 || a7 <= a9 || a7 <= a10) ? (a8 <= a9 || a8 <= a10) ? a9 > a10 ? a9 : a10 : a8 : a7;
    }
}
