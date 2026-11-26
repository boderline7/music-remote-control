package b2;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends m {

    /* renamed from: b, reason: collision with root package name */
    public static final k[] f2933b = new k[511];
    public static final k VALUE_M1 = j(-1);
    public static final k VALUE_0 = j(0);
    public static final k VALUE_1 = j(1);
    public static final k VALUE_2 = j(2);
    public static final k VALUE_3 = j(3);
    public static final k VALUE_4 = j(4);
    public static final k VALUE_5 = j(5);

    public static k j(int i7) {
        k[] kVarArr = f2933b;
        int length = (Integer.MAX_VALUE & i7) % kVarArr.length;
        k kVar = kVarArr[length];
        if (kVar != null && kVar.f2934a == i7) {
            return kVar;
        }
        k kVar2 = new k(i7);
        kVarArr[length] = kVar2;
        return kVar2;
    }

    @Override // c2.d
    public final c2.c b() {
        return c2.c.INT;
    }

    @Override // e2.i
    public final String d() {
        return Integer.toString(this.f2934a);
    }

    @Override // b2.a
    public final String f() {
        return "int";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("int{0x");
        int i7 = this.f2934a;
        sb.append(z.m1(i7));
        sb.append(" / ");
        sb.append(i7);
        sb.append('}');
        return sb.toString();
    }
}
