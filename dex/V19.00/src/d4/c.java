package d4;

import androidx.appcompat.app.n;
import p0.a0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {

    /* renamed from: d, reason: collision with root package name */
    public static final c[] f6285d = a();

    /* renamed from: a, reason: collision with root package name */
    public final int f6286a;

    /* renamed from: b, reason: collision with root package name */
    public final n[] f6287b;

    /* renamed from: c, reason: collision with root package name */
    public final int f6288c;

    public c(int i7, n... nVarArr) {
        this.f6286a = i7;
        this.f6287b = nVarArr;
        n nVar = nVarArr[0];
        int i8 = nVar.f830b;
        int i9 = 0;
        for (a0 a0Var : (a0[]) nVar.f831c) {
            i9 += (a0Var.f8093b + i8) * a0Var.f8092a;
        }
        this.f6288c = i9;
    }

    public static c[] a() {
        int i7 = 6;
        int i8 = 10;
        c cVar = new c(1, new n(7, i7, new a0[]{new a0(1, 19)}), new n(i8, i7, new a0[]{new a0(1, 16)}), new n(13, i7, new a0[]{new a0(1, 13)}), new n(17, i7, new a0[]{new a0(1, 9)}));
        int i9 = 22;
        c cVar2 = new c(2, new n(i8, i7, new a0[]{new a0(1, 34)}), new n(16, i7, new a0[]{new a0(1, 28)}), new n(i9, i7, new a0[]{new a0(1, 22)}), new n(28, i7, new a0[]{new a0(1, 16)}));
        int i10 = 26;
        int i11 = 18;
        c cVar3 = new c(3, new n(15, i7, new a0[]{new a0(1, 55)}), new n(i10, i7, new a0[]{new a0(1, 44)}), new n(i11, i7, new a0[]{new a0(2, 17)}), new n(i9, i7, new a0[]{new a0(2, 13)}));
        int i12 = 24;
        c cVar4 = new c(4, new n(20, i7, new a0[]{new a0(1, 80)}), new n(i11, i7, new a0[]{new a0(2, 32)}), new n(i10, i7, new a0[]{new a0(2, 24)}), new n(16, i7, new a0[]{new a0(4, 9)}));
        c cVar5 = new c(5, new n(i10, i7, new a0[]{new a0(1, 108)}), new n(i12, i7, new a0[]{new a0(2, 43)}), new n(18, i7, new a0[]{new a0(2, 15), new a0(2, 16)}), new n(22, i7, new a0[]{new a0(2, 11), new a0(2, 12)}));
        c cVar6 = new c(6, new n(18, i7, new a0[]{new a0(2, 68)}), new n(16, i7, new a0[]{new a0(4, 27)}), new n(i12, i7, new a0[]{new a0(4, 19)}), new n(28, i7, new a0[]{new a0(4, 15)}));
        int i13 = 18;
        c cVar7 = new c(7, new n(20, i7, new a0[]{new a0(2, 78)}), new n(i13, i7, new a0[]{new a0(4, 31)}), new n(i13, i7, new a0[]{new a0(2, 14), new a0(4, 15)}), new n(26, i7, new a0[]{new a0(4, 13), new a0(1, 14)}));
        int i14 = 22;
        int i15 = 6;
        c cVar8 = new c(8, new n(24, i7, new a0[]{new a0(2, 97)}), new n(i14, i7, new a0[]{new a0(2, 38), new a0(2, 39)}), new n(i14, i15, new a0[]{new a0(4, 18), new a0(2, 19)}), new n(26, i15, new a0[]{new a0(4, 14), new a0(2, 15)}));
        c cVar9 = new c(9, new n(30, 6, new a0[]{new a0(2, z1.c.INVOKE_VIRTUAL_RANGE)}), new n(22, 6, new a0[]{new a0(3, 36), new a0(2, 37)}), new n(20, 6, new a0[]{new a0(4, 16), new a0(4, 17)}), new n(24, 6, new a0[]{new a0(4, 12), new a0(4, 13)}));
        c cVar10 = new c(10, new n(18, 6, new a0[]{new a0(2, 68), new a0(2, 69)}), new n(26, 6, new a0[]{new a0(4, 43), new a0(1, 44)}), new n(24, 6, new a0[]{new a0(6, 19), new a0(2, 20)}), new n(28, 6, new a0[]{new a0(6, 15), new a0(2, 16)}));
        c cVar11 = new c(11, new n(20, 6, new a0[]{new a0(4, 81)}), new n(30, 6, new a0[]{new a0(1, 50), new a0(4, 51)}), new n(28, 6, new a0[]{new a0(4, 22), new a0(4, 23)}), new n(24, 6, new a0[]{new a0(3, 12), new a0(8, 13)}));
        int i16 = 6;
        int i17 = 6;
        c cVar12 = new c(12, new n(24, 6, new a0[]{new a0(2, 92), new a0(2, 93)}), new n(22, i16, new a0[]{new a0(6, 36), new a0(2, 37)}), new n(26, i16, new a0[]{new a0(4, 20), new a0(6, 21)}), new n(28, i17, new a0[]{new a0(7, 14), new a0(4, 15)}));
        c cVar13 = new c(13, new n(26, i17, new a0[]{new a0(4, z1.c.SPUT_BYTE)}), new n(22, 6, new a0[]{new a0(8, 37), new a0(1, 38)}), new n(24, 6, new a0[]{new a0(8, 20), new a0(4, 21)}), new n(22, 6, new a0[]{new a0(12, 11), new a0(4, 12)}));
        c cVar14 = new c(14, new n(30, 6, new a0[]{new a0(3, 115), new a0(1, z1.c.INVOKE_VIRTUAL_RANGE)}), new n(24, 6, new a0[]{new a0(4, 40), new a0(5, 41)}), new n(20, 6, new a0[]{new a0(11, 16), new a0(5, 17)}), new n(24, 6, new a0[]{new a0(11, 12), new a0(5, 13)}));
        c cVar15 = new c(15, new n(22, 6, new a0[]{new a0(5, 87), new a0(1, 88)}), new n(24, 6, new a0[]{new a0(5, 41), new a0(5, 42)}), new n(30, 6, new a0[]{new a0(5, 24), new a0(7, 25)}), new n(24, 6, new a0[]{new a0(11, 12), new a0(7, 13)}));
        c cVar16 = new c(16, new n(24, 6, new a0[]{new a0(5, 98), new a0(1, 99)}), new n(28, 6, new a0[]{new a0(7, 45), new a0(3, 46)}), new n(24, 6, new a0[]{new a0(15, 19), new a0(2, 20)}), new n(30, 6, new a0[]{new a0(3, 15), new a0(13, 16)}));
        c cVar17 = new c(17, new n(28, 6, new a0[]{new a0(1, z1.c.SPUT_BYTE), new a0(5, 108)}), new n(28, 6, new a0[]{new a0(10, 46), new a0(1, 47)}), new n(28, 6, new a0[]{new a0(1, 22), new a0(15, 23)}), new n(28, 6, new a0[]{new a0(2, 14), new a0(17, 15)}));
        c cVar18 = new c(18, new n(30, 6, new a0[]{new a0(5, z1.c.INVOKE_INTERFACE_RANGE), new a0(1, 121)}), new n(26, 6, new a0[]{new a0(9, 43), new a0(4, 44)}), new n(28, 6, new a0[]{new a0(17, 22), new a0(1, 23)}), new n(28, 6, new a0[]{new a0(2, 14), new a0(19, 15)}));
        c cVar19 = new c(19, new n(28, 6, new a0[]{new a0(3, z1.c.INVOKE_STATIC), new a0(4, z1.c.INVOKE_INTERFACE)}), new n(26, 6, new a0[]{new a0(3, 44), new a0(11, 45)}), new n(26, 6, new a0[]{new a0(17, 21), new a0(4, 22)}), new n(26, 6, new a0[]{new a0(9, 13), new a0(16, 14)}));
        c cVar20 = new c(20, new n(28, 6, new a0[]{new a0(3, z1.c.SPUT_BYTE), new a0(5, 108)}), new n(26, 6, new a0[]{new a0(3, 41), new a0(13, 42)}), new n(30, 6, new a0[]{new a0(15, 24), new a0(5, 25)}), new n(28, 6, new a0[]{new a0(15, 15), new a0(10, 16)}));
        int i18 = 6;
        c cVar21 = new c(21, new n(28, i18, new a0[]{new a0(4, z1.c.INVOKE_VIRTUAL_RANGE), new a0(4, z1.c.INVOKE_SUPER_RANGE)}), new n(26, i18, new a0[]{new a0(17, 42)}), new n(28, i18, new a0[]{new a0(17, 22), new a0(6, 23)}), new n(30, i18, new a0[]{new a0(19, 16), new a0(6, 17)}));
        int i19 = 28;
        int i20 = 6;
        c cVar22 = new c(22, new n(i19, 6, new a0[]{new a0(2, z1.c.INVOKE_SUPER), new a0(7, z1.c.INVOKE_DIRECT)}), new n(i19, 6, new a0[]{new a0(17, 46)}), new n(30, i20, new a0[]{new a0(7, 24), new a0(16, 25)}), new n(24, i20, new a0[]{new a0(34, 13)}));
        c cVar23 = new c(23, new n(30, 6, new a0[]{new a0(4, 121), new a0(5, 122)}), new n(28, 6, new a0[]{new a0(4, 47), new a0(14, 48)}), new n(30, 6, new a0[]{new a0(11, 24), new a0(14, 25)}), new n(30, 6, new a0[]{new a0(16, 15), new a0(14, 16)}));
        c cVar24 = new c(24, new n(30, 6, new a0[]{new a0(6, z1.c.INVOKE_SUPER_RANGE), new a0(4, z1.c.INVOKE_DIRECT_RANGE)}), new n(28, 6, new a0[]{new a0(6, 45), new a0(14, 46)}), new n(30, 6, new a0[]{new a0(11, 24), new a0(16, 25)}), new n(30, 6, new a0[]{new a0(30, 16), new a0(2, 17)}));
        c cVar25 = new c(25, new n(26, 6, new a0[]{new a0(8, z1.c.SPUT_BOOLEAN), new a0(4, z1.c.SPUT_BYTE)}), new n(28, 6, new a0[]{new a0(8, 47), new a0(13, 48)}), new n(30, 6, new a0[]{new a0(7, 24), new a0(22, 25)}), new n(30, 6, new a0[]{new a0(22, 15), new a0(13, 16)}));
        int i21 = 6;
        c cVar26 = new c(26, new n(28, 6, new a0[]{new a0(10, z1.c.INVOKE_INTERFACE), new a0(2, 115)}), new n(28, i21, new a0[]{new a0(19, 46), new a0(4, 47)}), new n(28, i21, new a0[]{new a0(28, 22), new a0(6, 23)}), new n(30, 6, new a0[]{new a0(33, 16), new a0(4, 17)}));
        c cVar27 = new c(27, new n(30, 6, new a0[]{new a0(8, 122), new a0(4, z1.c.NEG_INT)}), new n(28, 6, new a0[]{new a0(22, 45), new a0(3, 46)}), new n(30, 6, new a0[]{new a0(8, 23), new a0(26, 24)}), new n(30, 6, new a0[]{new a0(12, 15), new a0(28, 16)}));
        c cVar28 = new c(28, new n(30, 6, new a0[]{new a0(3, z1.c.INVOKE_SUPER_RANGE), new a0(10, z1.c.INVOKE_DIRECT_RANGE)}), new n(28, 6, new a0[]{new a0(3, 45), new a0(23, 46)}), new n(30, 6, new a0[]{new a0(4, 24), new a0(31, 25)}), new n(30, 6, new a0[]{new a0(11, 15), new a0(31, 16)}));
        c cVar29 = new c(29, new n(30, 6, new a0[]{new a0(7, z1.c.INVOKE_VIRTUAL_RANGE), new a0(7, z1.c.INVOKE_SUPER_RANGE)}), new n(28, 6, new a0[]{new a0(21, 45), new a0(7, 46)}), new n(30, 6, new a0[]{new a0(1, 23), new a0(37, 24)}), new n(30, 6, new a0[]{new a0(19, 15), new a0(26, 16)}));
        int i22 = 6;
        c cVar30 = new c(30, new n(30, 6, new a0[]{new a0(5, 115), new a0(10, z1.c.INVOKE_VIRTUAL_RANGE)}), new n(28, i22, new a0[]{new a0(19, 47), new a0(10, 48)}), new n(30, i22, new a0[]{new a0(15, 24), new a0(25, 25)}), new n(30, 6, new a0[]{new a0(23, 15), new a0(25, 16)}));
        int i23 = 30;
        int i24 = 6;
        return new c[]{cVar, cVar2, cVar3, cVar4, cVar5, cVar6, cVar7, cVar8, cVar9, cVar10, cVar11, cVar12, cVar13, cVar14, cVar15, cVar16, cVar17, cVar18, cVar19, cVar20, cVar21, cVar22, cVar23, cVar24, cVar25, cVar26, cVar27, cVar28, cVar29, cVar30, new c(31, new n(30, 6, new a0[]{new a0(13, 115), new a0(3, z1.c.INVOKE_VIRTUAL_RANGE)}), new n(28, 6, new a0[]{new a0(2, 46), new a0(29, 47)}), new n(30, 6, new a0[]{new a0(42, 24), new a0(1, 25)}), new n(i23, 6, new a0[]{new a0(23, 15), new a0(28, 16)})), new c(32, new n(i23, 6, new a0[]{new a0(17, 115)}), new n(28, 6, new a0[]{new a0(10, 46), new a0(23, 47)}), new n(30, 6, new a0[]{new a0(10, 24), new a0(35, 25)}), new n(30, 6, new a0[]{new a0(19, 15), new a0(35, 16)})), new c(33, new n(30, 6, new a0[]{new a0(17, 115), new a0(1, z1.c.INVOKE_VIRTUAL_RANGE)}), new n(28, 6, new a0[]{new a0(14, 46), new a0(21, 47)}), new n(30, 6, new a0[]{new a0(29, 24), new a0(19, 25)}), new n(30, i24, new a0[]{new a0(11, 15), new a0(46, 16)})), new c(34, new n(30, i24, new a0[]{new a0(13, 115), new a0(6, z1.c.INVOKE_VIRTUAL_RANGE)}), new n(28, 6, new a0[]{new a0(14, 46), new a0(23, 47)}), new n(30, 6, new a0[]{new a0(44, 24), new a0(7, 25)}), new n(30, 6, new a0[]{new a0(59, 16), new a0(1, 17)})), new c(35, new n(30, 6, new a0[]{new a0(12, 121), new a0(7, 122)}), new n(28, 6, new a0[]{new a0(12, 47), new a0(26, 48)}), new n(30, 6, new a0[]{new a0(39, 24), new a0(14, 25)}), new n(30, 6, new a0[]{new a0(22, 15), new a0(41, 16)})), new c(36, new n(30, 6, new a0[]{new a0(6, 121), new a0(14, 122)}), new n(28, 6, new a0[]{new a0(6, 47), new a0(34, 48)}), new n(30, 6, new a0[]{new a0(46, 24), new a0(10, 25)}), new n(30, 6, new a0[]{new a0(2, 15), new a0(64, 16)})), new c(37, new n(30, 6, new a0[]{new a0(17, 122), new a0(4, z1.c.NEG_INT)}), new n(28, 6, new a0[]{new a0(29, 46), new a0(14, 47)}), new n(30, 6, new a0[]{new a0(49, 24), new a0(10, 25)}), new n(30, 6, new a0[]{new a0(24, 15), new a0(46, 16)})), new c(38, new n(30, 6, new a0[]{new a0(4, 122), new a0(18, z1.c.NEG_INT)}), new n(28, 6, new a0[]{new a0(13, 46), new a0(32, 47)}), new n(30, 6, new a0[]{new a0(48, 24), new a0(14, 25)}), new n(30, 6, new a0[]{new a0(42, 15), new a0(32, 16)})), new c(39, new n(30, 6, new a0[]{new a0(20, z1.c.INVOKE_SUPER_RANGE), new a0(4, z1.c.INVOKE_DIRECT_RANGE)}), new n(28, 6, new a0[]{new a0(40, 47), new a0(7, 48)}), new n(30, 6, new a0[]{new a0(43, 24), new a0(22, 25)}), new n(30, 6, new a0[]{new a0(10, 15), new a0(67, 16)})), new c(40, new n(30, 6, new a0[]{new a0(19, z1.c.INVOKE_DIRECT_RANGE), new a0(6, z1.c.INVOKE_STATIC_RANGE)}), new n(28, 6, new a0[]{new a0(18, 47), new a0(31, 48)}), new n(30, 6, new a0[]{new a0(34, 24), new a0(34, 25)}), new n(30, 6, new a0[]{new a0(20, 15), new a0(61, 16)}))};
    }

    public static c b(int i7) {
        if (i7 < 1 || i7 > 40) {
            throw new IllegalArgumentException();
        }
        return f6285d[i7 - 1];
    }

    public final String toString() {
        return String.valueOf(this.f6286a);
    }
}
