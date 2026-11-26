package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Comparable {

    /* renamed from: a, reason: collision with root package name */
    public final b2.v f9413a;

    /* renamed from: b, reason: collision with root package name */
    public final int f9414b;

    public a(b2.v vVar, int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("handler < 0");
        }
        this.f9414b = i7;
        this.f9413a = vVar;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(a aVar) {
        int i7 = aVar.f9414b;
        int i8 = this.f9414b;
        if (i8 < i7) {
            return -1;
        }
        if (i8 > i7) {
            return 1;
        }
        return this.f9413a.compareTo(aVar.f9413a);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && compareTo((a) obj) == 0;
    }

    public final int hashCode() {
        return this.f9413a.hashCode() + (this.f9414b * 31);
    }
}
