package androidx.recyclerview.widget;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public int f2371a;

    /* renamed from: b, reason: collision with root package name */
    public int f2372b;

    /* renamed from: c, reason: collision with root package name */
    public Object f2373c;

    /* renamed from: d, reason: collision with root package name */
    public int f2374d;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        int i7 = this.f2371a;
        if (i7 != aVar.f2371a) {
            return false;
        }
        if (i7 == 8 && Math.abs(this.f2374d - this.f2372b) == 1 && this.f2374d == aVar.f2372b && this.f2372b == aVar.f2374d) {
            return true;
        }
        if (this.f2374d != aVar.f2374d || this.f2372b != aVar.f2372b) {
            return false;
        }
        Object obj2 = this.f2373c;
        if (obj2 != null) {
            if (!obj2.equals(aVar.f2373c)) {
                return false;
            }
        } else if (aVar.f2373c != null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((this.f2371a * 31) + this.f2372b) * 31) + this.f2374d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i7 = this.f2371a;
        sb.append(i7 != 1 ? i7 != 2 ? i7 != 4 ? i7 != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb.append(",s:");
        sb.append(this.f2372b);
        sb.append("c:");
        sb.append(this.f2374d);
        sb.append(",p:");
        sb.append(this.f2373c);
        sb.append("]");
        return sb.toString();
    }
}
