package g3;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements TypeEvaluator {

    /* renamed from: a, reason: collision with root package name */
    public final float[] f6474a = new float[9];

    /* renamed from: b, reason: collision with root package name */
    public final float[] f6475b = new float[9];

    /* renamed from: c, reason: collision with root package name */
    public final Matrix f6476c = new Matrix();

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ q f6477d;

    public l(q qVar) {
        this.f6477d = qVar;
    }

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f7, Object obj, Object obj2) {
        this.f6477d.f6509p = f7;
        float[] fArr = this.f6474a;
        ((Matrix) obj).getValues(fArr);
        float[] fArr2 = this.f6475b;
        ((Matrix) obj2).getValues(fArr2);
        for (int i7 = 0; i7 < 9; i7++) {
            float f8 = fArr2[i7];
            float f9 = fArr[i7];
            fArr2[i7] = a.a.a(f8, f9, f7, f9);
        }
        Matrix matrix = this.f6476c;
        matrix.setValues(fArr2);
        return matrix;
    }
}
