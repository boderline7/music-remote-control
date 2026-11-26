package o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public final Object f7788a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f7789b;

    public c(Object obj, Object obj2) {
        this.f7788a = obj;
        this.f7789b = obj2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return b.a(cVar.f7788a, this.f7788a) && b.a(cVar.f7789b, this.f7789b);
    }

    public final int hashCode() {
        Object obj = this.f7788a;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f7789b;
        return (obj2 != null ? obj2.hashCode() : 0) ^ hashCode;
    }

    public final String toString() {
        return "Pair{" + this.f7788a + " " + this.f7789b + "}";
    }
}
