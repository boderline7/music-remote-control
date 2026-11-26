package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n extends o {

    /* renamed from: a, reason: collision with root package name */
    public final long f2935a;

    public n(long j7) {
        this.f2935a = j7;
    }

    @Override // b2.a
    public final int e(a aVar) {
        long j7 = ((n) aVar).f2935a;
        long j8 = this.f2935a;
        if (j8 < j7) {
            return -1;
        }
        return j8 > j7 ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            if (this.f2935a == ((n) obj).f2935a) {
                return true;
            }
        }
        return false;
    }

    @Override // b2.o
    public final boolean g() {
        long j7 = this.f2935a;
        return ((long) ((int) j7)) == j7;
    }

    @Override // b2.o
    public final int h() {
        return (int) this.f2935a;
    }

    public final int hashCode() {
        long j7 = this.f2935a;
        return ((int) (j7 >> 32)) ^ ((int) j7);
    }

    @Override // b2.o
    public final long i() {
        return this.f2935a;
    }
}
