package c1;

import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    public final float[] f3032a;

    /* renamed from: b, reason: collision with root package name */
    public final float f3033b;

    public d(float[] fArr) {
        this.f3032a = fArr;
        this.f3033b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f7) {
        if (f7 >= 1.0f) {
            return 1.0f;
        }
        if (f7 <= CropImageView.DEFAULT_ASPECT_RATIO) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        float[] fArr = this.f3032a;
        int min = Math.min((int) ((fArr.length - 1) * f7), fArr.length - 2);
        float f8 = this.f3033b;
        float f9 = (f7 - (min * f8)) / f8;
        float f10 = fArr[min];
        return a.a.a(fArr[min + 1], f10, f9, f10);
    }
}
