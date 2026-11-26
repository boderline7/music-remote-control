package n1;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n implements TypeEvaluator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7640a = 1;

    /* renamed from: b, reason: collision with root package name */
    public Object f7641b;

    public n(Rect rect) {
        this.f7641b = rect;
    }

    public final Rect a(float f7, Rect rect, Rect rect2) {
        switch (this.f7640a) {
            case 0:
                int i7 = rect.left + ((int) ((rect2.left - r0) * f7));
                int i8 = rect.top + ((int) ((rect2.top - r1) * f7));
                int i9 = rect.right + ((int) ((rect2.right - r2) * f7));
                int i10 = rect.bottom + ((int) ((rect2.bottom - r6) * f7));
                Rect rect3 = (Rect) this.f7641b;
                if (rect3 != null) {
                    rect3.set(i7, i8, i9, i10);
                    break;
                } else {
                    break;
                }
            default:
                ((Rect) this.f7641b).set(rect.left + ((int) ((rect2.left - r0) * f7)), rect.top + ((int) ((rect2.top - r1) * f7)), rect.right + ((int) ((rect2.right - r2) * f7)), rect.bottom + ((int) ((rect2.bottom - r6) * f7)));
                break;
        }
        return (Rect) this.f7641b;
    }

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f7, Object obj, Object obj2) {
        switch (this.f7640a) {
            case 0:
                return a(f7, (Rect) obj, (Rect) obj2);
            case 1:
                return a(f7, (Rect) obj, (Rect) obj2);
            default:
                i0.f[] fVarArr = (i0.f[]) obj;
                i0.f[] fVarArr2 = (i0.f[]) obj2;
                if (!z.s(fVarArr, fVarArr2)) {
                    throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
                }
                if (!z.s((i0.f[]) this.f7641b, fVarArr)) {
                    this.f7641b = z.Q(fVarArr);
                }
                for (int i7 = 0; i7 < fVarArr.length; i7++) {
                    i0.f fVar = ((i0.f[]) this.f7641b)[i7];
                    i0.f fVar2 = fVarArr[i7];
                    i0.f fVar3 = fVarArr2[i7];
                    fVar.getClass();
                    fVar.f6778a = fVar2.f6778a;
                    int i8 = 0;
                    while (true) {
                        float[] fArr = fVar2.f6779b;
                        if (i8 < fArr.length) {
                            fVar.f6779b[i8] = (fVar3.f6779b[i8] * f7) + ((1.0f - f7) * fArr[i8]);
                            i8++;
                        }
                    }
                }
                return (i0.f[]) this.f7641b;
        }
    }
}
