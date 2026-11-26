package b2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p extends n {
    public static final p VALUE_0 = new p(0);
    public static final p VALUE_1 = new p(1);

    @Override // c2.d
    public final c2.c b() {
        return c2.c.LONG;
    }

    @Override // e2.i
    public final String d() {
        return Long.toString(this.f2935a);
    }

    @Override // b2.a
    public final String f() {
        return "long";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("long{0x");
        long j7 = this.f2935a;
        sb.append(z.n1(j7));
        sb.append(" / ");
        sb.append(j7);
        sb.append('}');
        return sb.toString();
    }
}
