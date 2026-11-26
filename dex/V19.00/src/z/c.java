package z;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public final float[] f10233a = new float[20];

    /* renamed from: b, reason: collision with root package name */
    public final ColorMatrix f10234b = new ColorMatrix();

    /* renamed from: c, reason: collision with root package name */
    public final ColorMatrix f10235c = new ColorMatrix();

    /* renamed from: d, reason: collision with root package name */
    public float f10236d = 1.0f;

    /* renamed from: e, reason: collision with root package name */
    public float f10237e = 1.0f;

    /* renamed from: f, reason: collision with root package name */
    public float f10238f = 1.0f;

    /* renamed from: g, reason: collision with root package name */
    public float f10239g = 1.0f;

    public final void a(ImageView imageView) {
        boolean z6;
        float log;
        float f7;
        float f8;
        float f9;
        ColorMatrix colorMatrix = this.f10234b;
        colorMatrix.reset();
        float f10 = this.f10237e;
        float f11 = 1.0f;
        float[] fArr = this.f10233a;
        if (f10 != 1.0f) {
            float f12 = 1.0f - f10;
            float f13 = 0.2999f * f12;
            float f14 = 0.587f * f12;
            float f15 = f12 * 0.114f;
            fArr[0] = f13 + f10;
            fArr[1] = f14;
            fArr[2] = f15;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f13;
            fArr[6] = f14 + f10;
            fArr[7] = f15;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f13;
            fArr[11] = f14;
            fArr[12] = f15 + f10;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
            colorMatrix.set(fArr);
            z6 = true;
        } else {
            z6 = false;
        }
        float f16 = this.f10238f;
        ColorMatrix colorMatrix2 = this.f10235c;
        if (f16 != 1.0f) {
            colorMatrix2.setScale(f16, f16, f16, 1.0f);
            colorMatrix.postConcat(colorMatrix2);
            z6 = true;
        }
        float f17 = this.f10239g;
        if (f17 != 1.0f) {
            if (f17 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                f17 = 0.01f;
            }
            float f18 = (5000.0f / f17) / 100.0f;
            if (f18 > 66.0f) {
                double d7 = f18 - 60.0f;
                f7 = ((float) Math.pow(d7, -0.13320475816726685d)) * 329.69873f;
                log = ((float) Math.pow(d7, 0.07551484555006027d)) * 288.12216f;
            } else {
                log = (((float) Math.log(f18)) * 99.4708f) - 161.11957f;
                f7 = 255.0f;
            }
            if (f18 >= 66.0f) {
                f8 = log;
                f9 = 255.0f;
            } else if (f18 > 19.0f) {
                f8 = log;
                f9 = (((float) Math.log(f18 - 10.0f)) * 138.51773f) - 305.0448f;
            } else {
                f8 = log;
                f9 = 0.0f;
            }
            float min = Math.min(255.0f, Math.max(f7, CropImageView.DEFAULT_ASPECT_RATIO));
            float min2 = Math.min(255.0f, Math.max(f8, CropImageView.DEFAULT_ASPECT_RATIO));
            float min3 = Math.min(255.0f, Math.max(f9, CropImageView.DEFAULT_ASPECT_RATIO));
            float log2 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
            float log3 = (((float) Math.log(40.0f)) * 138.51773f) - 305.0448f;
            float min4 = Math.min(255.0f, Math.max(255.0f, CropImageView.DEFAULT_ASPECT_RATIO));
            float min5 = Math.min(255.0f, Math.max(log2, CropImageView.DEFAULT_ASPECT_RATIO));
            float min6 = min3 / Math.min(255.0f, Math.max(log3, CropImageView.DEFAULT_ASPECT_RATIO));
            fArr[0] = min / min4;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = min2 / min5;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = min6;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            f11 = 1.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
            colorMatrix2.set(fArr);
            colorMatrix.postConcat(colorMatrix2);
            z6 = true;
        }
        float f19 = this.f10236d;
        if (f19 != f11) {
            fArr[0] = f19;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f19;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = f19;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
            colorMatrix2.set(fArr);
            colorMatrix.postConcat(colorMatrix2);
        } else if (!z6) {
            imageView.clearColorFilter();
            return;
        }
        imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }
}
