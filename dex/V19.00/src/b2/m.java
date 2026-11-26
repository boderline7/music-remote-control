package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class m extends o {

    /* renamed from: a, reason: collision with root package name */
    public final int f2934a;

    public m(int i7) {
        this.f2934a = i7;
    }

    @Override // b2.a
    public final int e(a aVar) {
        int i7 = ((m) aVar).f2934a;
        int i8 = this.f2934a;
        if (i8 < i7) {
            return -1;
        }
        return i8 > i7 ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            if (this.f2934a == ((m) obj).f2934a) {
                return true;
            }
        }
        return false;
    }

    @Override // b2.o
    public final boolean g() {
        return true;
    }

    @Override // b2.o
    public final int h() {
        return this.f2934a;
    }

    public final int hashCode() {
        return this.f2934a;
    }

    @Override // b2.o
    public final long i() {
        return this.f2934a;
    }
}
