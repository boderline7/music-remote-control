package a2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public int f233a;

    /* renamed from: b, reason: collision with root package name */
    public c2.d f234b;

    public final boolean equals(Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        int i7 = this.f233a;
        c2.d dVar = this.f234b;
        String str = m.PREFIX;
        return ((m) obj).a(i7, dVar);
    }

    public final int hashCode() {
        int i7 = this.f233a;
        c2.d dVar = this.f234b;
        String str = m.PREFIX;
        return (dVar.hashCode() * 31) + i7;
    }
}
