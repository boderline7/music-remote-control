package v4;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public int f9398a;

    /* renamed from: b, reason: collision with root package name */
    public int f9399b;

    /* renamed from: c, reason: collision with root package name */
    public int f9400c;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.f9398a == aVar.f9398a && this.f9399b == aVar.f9399b && this.f9400c == aVar.f9400c;
    }

    public final int hashCode() {
        return (((this.f9398a * 31) + this.f9399b) * 31) + this.f9400c;
    }
}
