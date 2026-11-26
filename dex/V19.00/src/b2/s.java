package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s extends a {
    public static final s PRIMITIVE_TYPE_NAT = new s(new u("TYPE"), new u("Ljava/lang/Class;"));

    /* renamed from: a, reason: collision with root package name */
    public final u f2938a;

    /* renamed from: b, reason: collision with root package name */
    public final u f2939b;

    public s(u uVar, u uVar2) {
        this.f2938a = uVar;
        this.f2939b = uVar2;
    }

    @Override // e2.i
    public final String d() {
        return this.f2938a.d() + ':' + this.f2939b.d();
    }

    @Override // b2.a
    public final int e(a aVar) {
        s sVar = (s) aVar;
        int compareTo = this.f2938a.compareTo(sVar.f2938a);
        return compareTo != 0 ? compareTo : this.f2939b.compareTo(sVar.f2939b);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f2938a.equals(sVar.f2938a) && this.f2939b.equals(sVar.f2939b);
    }

    @Override // b2.a
    public final String f() {
        return "nat";
    }

    public final int hashCode() {
        return (this.f2938a.f2940a.hashCode() * 31) ^ this.f2939b.f2940a.hashCode();
    }

    public final String toString() {
        return "nat{" + d() + '}';
    }
}
