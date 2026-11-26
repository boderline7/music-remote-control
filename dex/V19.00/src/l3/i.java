package l3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends n0.k {
    @Override // n0.k
    public final float b(Object obj) {
        return ((j) obj).f7375o * 10000.0f;
    }

    @Override // n0.k
    public final void d(Object obj, float f7) {
        j jVar = (j) obj;
        jVar.f7375o = f7 / 10000.0f;
        jVar.invalidateSelf();
    }
}
