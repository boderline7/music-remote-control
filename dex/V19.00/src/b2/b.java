package b2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends e2.d implements Comparable {
    @Override // java.lang.Comparable
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public final int compareTo(b bVar) {
        int length = this.f6354b.length;
        int length2 = bVar.f6354b.length;
        int i7 = length < length2 ? length : length2;
        for (int i8 = 0; i8 < i7; i8++) {
            int compareTo = ((a) f(i8)).compareTo((a) bVar.f(i8));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        if (length < length2) {
            return -1;
        }
        return length > length2 ? 1 : 0;
    }
}
