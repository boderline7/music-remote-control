package i6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p {

    /* renamed from: a, reason: collision with root package name */
    public final Object f6871a;

    /* renamed from: b, reason: collision with root package name */
    public final m f6872b;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f6873c = true;

    public p(Object obj, m mVar) {
        this.f6871a = obj;
        this.f6872b = mVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f6871a == pVar.f6871a && this.f6872b.equals(pVar.f6872b);
    }

    public final int hashCode() {
        return this.f6872b.f6862f.hashCode() + this.f6871a.hashCode();
    }
}
