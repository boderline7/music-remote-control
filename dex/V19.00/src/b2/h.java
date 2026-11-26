package b2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h extends n {
    public static final h VALUE_0 = new h(Double.doubleToLongBits(0.0d));
    public static final h VALUE_1 = new h(Double.doubleToLongBits(1.0d));

    @Override // c2.d
    public final c2.c b() {
        return c2.c.DOUBLE;
    }

    @Override // e2.i
    public final String d() {
        return Double.toString(Double.longBitsToDouble(this.f2935a));
    }

    @Override // b2.a
    public final String f() {
        return "double";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("double{0x");
        long j7 = this.f2935a;
        sb.append(z.n1(j7));
        sb.append(" / ");
        sb.append(Double.longBitsToDouble(j7));
        sb.append('}');
        return sb.toString();
    }
}
