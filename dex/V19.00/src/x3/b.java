package x3;

import i6.i;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f9613a = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    public static void a(y3.b bVar, int i7, int i8) {
        for (int i9 = 0; i9 < i8; i9 += 2) {
            int i10 = i7 - i9;
            int i11 = i10;
            while (true) {
                int i12 = i7 + i9;
                if (i11 <= i12) {
                    bVar.b(i11, i10);
                    bVar.b(i11, i12);
                    bVar.b(i10, i11);
                    bVar.b(i12, i11);
                    i11++;
                }
            }
        }
        int i13 = i7 - i8;
        bVar.b(i13, i13);
        int i14 = i13 + 1;
        bVar.b(i14, i13);
        bVar.b(i13, i14);
        int i15 = i7 + i8;
        bVar.b(i15, i13);
        bVar.b(i15, i14);
        bVar.b(i15, i15 - 1);
    }

    public static y3.a b(y3.a aVar, int i7, int i8) {
        z3.a aVar2;
        int i9 = aVar.f10173b / i8;
        if (i8 == 4) {
            aVar2 = z3.a.AZTEC_PARAM;
        } else if (i8 == 6) {
            aVar2 = z3.a.AZTEC_DATA_6;
        } else if (i8 == 8) {
            aVar2 = z3.a.AZTEC_DATA_8;
        } else if (i8 == 10) {
            aVar2 = z3.a.AZTEC_DATA_10;
        } else {
            if (i8 != 12) {
                throw new IllegalArgumentException(a.a.d("Unsupported word size ", i8));
            }
            aVar2 = z3.a.AZTEC_DATA_12;
        }
        i iVar = new i(aVar2);
        int i10 = i7 / i8;
        int[] iArr = new int[i10];
        int i11 = aVar.f10173b / i8;
        for (int i12 = 0; i12 < i11; i12++) {
            int i13 = 0;
            for (int i14 = 0; i14 < i8; i14++) {
                i13 |= aVar.d((i12 * i8) + i14) ? 1 << ((i8 - i14) - 1) : 0;
            }
            iArr[i12] = i13;
        }
        iVar.d(i10 - i9, iArr);
        y3.a aVar3 = new y3.a();
        aVar3.b(0, i7 % i8);
        for (int i15 = 0; i15 < i10; i15++) {
            aVar3.b(iArr[i15], i8);
        }
        return aVar3;
    }

    public static y3.a c(int i7, y3.a aVar) {
        y3.a aVar2 = new y3.a();
        int i8 = aVar.f10173b;
        int i9 = (1 << i7) - 2;
        int i10 = 0;
        while (i10 < i8) {
            int i11 = 0;
            for (int i12 = 0; i12 < i7; i12++) {
                int i13 = i10 + i12;
                if (i13 >= i8 || aVar.d(i13)) {
                    i11 |= 1 << ((i7 - 1) - i12);
                }
            }
            int i14 = i11 & i9;
            if (i14 == i9) {
                aVar2.b(i14, i7);
            } else if (i14 == 0) {
                aVar2.b(i11 | 1, i7);
            } else {
                aVar2.b(i11, i7);
                i10 += i7;
            }
            i10--;
            i10 += i7;
        }
        return aVar2;
    }
}
