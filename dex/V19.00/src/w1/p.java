package w1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p implements Comparable {
    public p(int i7, o oVar, a2.m mVar) {
        if (i7 < 0) {
            throw new IllegalArgumentException("address < 0");
        }
        if (oVar == null) {
            throw new NullPointerException("disposition == null");
        }
        try {
            mVar.getClass();
            throw new NullPointerException("spec.getLocalItem() == null");
        } catch (NullPointerException unused) {
            throw new NullPointerException("spec == null");
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        ((p) obj).getClass();
        o oVar = o.START;
        boolean z6 = oVar == null;
        if (z6 != (oVar == null)) {
            return z6 ? 1 : -1;
        }
        throw null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        ((p) obj).getClass();
        o oVar = o.START;
        if ((oVar == null) != (oVar == null)) {
            return false;
        }
        throw null;
    }

    public final String toString() {
        return Integer.toHexString(0) + " null null";
    }
}
