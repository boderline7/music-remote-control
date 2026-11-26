package g3;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n implements TypeEvaluator {

    /* renamed from: a, reason: collision with root package name */
    public FloatEvaluator f6487a;

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f7, Object obj, Object obj2) {
        float floatValue = this.f6487a.evaluate(f7, (Number) obj, (Number) obj2).floatValue();
        if (floatValue < 0.1f) {
            floatValue = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        return Float.valueOf(floatValue);
    }
}
