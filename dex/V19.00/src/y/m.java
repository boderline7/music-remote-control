package y;

import android.view.animation.Interpolator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m implements Interpolator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9864a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ t.f f9865b;

    public m(t.f fVar) {
        this.f9864a = 0;
        this.f9865b = fVar;
    }

    public /* synthetic */ m(t.f fVar, int i7) {
        this.f9864a = i7;
        this.f9865b = fVar;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f7) {
        int i7 = this.f9864a;
        t.f fVar = this.f9865b;
        switch (i7) {
        }
        return (float) fVar.a(f7);
    }
}
