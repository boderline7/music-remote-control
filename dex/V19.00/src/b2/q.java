package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class q extends w {

    /* renamed from: a, reason: collision with root package name */
    public final v f2936a;

    /* renamed from: b, reason: collision with root package name */
    public final s f2937b;

    public q(v vVar, s sVar) {
        if (vVar == null) {
            throw new NullPointerException("definingClass == null");
        }
        this.f2936a = vVar;
        this.f2937b = sVar;
    }

    @Override // e2.i
    public final String d() {
        return this.f2936a.f2943a.d() + '.' + this.f2937b.d();
    }

    public final boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        q qVar = (q) obj;
        return this.f2936a.equals(qVar.f2936a) && this.f2937b.equals(qVar.f2937b);
    }

    public final int hashCode() {
        return (this.f2936a.hashCode() * 31) ^ this.f2937b.hashCode();
    }

    public final String toString() {
        return f() + '{' + d() + '}';
    }
}
