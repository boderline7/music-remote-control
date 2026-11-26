package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends e2.d implements Comparable {
    public static final d EMPTY = new d(0);

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        d dVar = (d) obj;
        if (this == dVar) {
            return 0;
        }
        int length = this.f6354b.length;
        int length2 = dVar.f6354b.length;
        int min = Math.min(length, length2);
        for (int i7 = 0; i7 < min; i7++) {
            int compareTo = ((c) f(i7)).compareTo((c) dVar.f(i7));
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
