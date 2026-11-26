package b2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends m {
    public static final g VALUE_0 = new g(0);

    @Override // c2.d
    public final c2.c b() {
        return c2.c.CHAR;
    }

    @Override // e2.i
    public final String d() {
        return Integer.toString(this.f2934a);
    }

    @Override // b2.a
    public final String f() {
        return "char";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("char{0x");
        int i7 = this.f2934a;
        sb.append(z.l1(i7));
        sb.append(" / ");
        sb.append(i7);
        sb.append('}');
        return sb.toString();
    }
}
