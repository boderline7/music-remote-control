package n0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class k implements g {

    /* renamed from: a, reason: collision with root package name */
    public final Object f7582a;

    public abstract boolean a();

    public abstract float b(Object obj);

    public final boolean c(CharSequence charSequence, int i7) {
        if (charSequence == null || i7 < 0 || charSequence.length() - i7 < 0) {
            throw new IllegalArgumentException();
        }
        j jVar = (j) this.f7582a;
        if (jVar == null) {
            return a();
        }
        int a7 = jVar.a(charSequence, i7);
        if (a7 == 0) {
            return true;
        }
        if (a7 != 1) {
            return a();
        }
        return false;
    }

    public abstract void d(Object obj, float f7);
}
