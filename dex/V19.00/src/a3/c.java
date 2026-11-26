package a3;

import android.animation.TypeEvaluator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements TypeEvaluator {
    public static final TypeEvaluator<f> CIRCULAR_REVEAL = new c();

    /* renamed from: a, reason: collision with root package name */
    public final f f263a = new f();

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f7, Object obj, Object obj2) {
        f fVar = (f) obj;
        f fVar2 = (f) obj2;
        float f8 = fVar.f264a;
        float f9 = 1.0f - f7;
        float f10 = (fVar2.f264a * f7) + (f8 * f9);
        float f11 = fVar.f265b;
        float f12 = (fVar2.f265b * f7) + (f11 * f9);
        float f13 = fVar.f266c;
        float f14 = (f7 * fVar2.f266c) + (f9 * f13);
        f fVar3 = this.f263a;
        fVar3.f264a = f10;
        fVar3.f265b = f12;
        fVar3.f266c = f14;
        return fVar3;
    }
}
