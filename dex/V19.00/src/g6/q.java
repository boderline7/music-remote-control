package g6;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q {
    public static void a(long j7, h hVar, int i7, ArrayList arrayList, int i8, int i9, ArrayList arrayList2) {
        int i10;
        int i11;
        int i12;
        int i13;
        h hVar2;
        int i14 = i7;
        if (i8 >= i9) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        for (int i15 = i8; i15 < i9; i15++) {
            if (((l) arrayList.get(i15)).c() < i14) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        l lVar = (l) arrayList.get(i8);
        l lVar2 = (l) arrayList.get(i9 - 1);
        int i16 = -1;
        if (i14 == lVar.c()) {
            int intValue = ((Number) arrayList2.get(i8)).intValue();
            int i17 = i8 + 1;
            l lVar3 = (l) arrayList.get(i17);
            i10 = i17;
            i11 = intValue;
            lVar = lVar3;
        } else {
            i10 = i8;
            i11 = -1;
        }
        if (lVar.f(i14) == lVar2.f(i14)) {
            int min = Math.min(lVar.c(), lVar2.c());
            int i18 = 0;
            for (int i19 = i14; i19 < min && lVar.f(i19) == lVar2.f(i19); i19++) {
                i18++;
            }
            long j8 = 4;
            long j9 = (hVar.f6573b / j8) + j7 + 2 + i18 + 1;
            hVar.P(-i18);
            hVar.P(i11);
            int i20 = i18 + i14;
            while (i14 < i20) {
                hVar.P(lVar.f(i14) & 255);
                i14++;
            }
            if (i10 + 1 == i9) {
                if (i20 != ((l) arrayList.get(i10)).c()) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                hVar.P(((Number) arrayList2.get(i10)).intValue());
                return;
            } else {
                h hVar3 = new h();
                hVar.P(((int) ((hVar3.f6573b / j8) + j9)) * (-1));
                a(j9, hVar3, i20, arrayList, i10, i9, arrayList2);
                hVar.M(hVar3);
                return;
            }
        }
        int i21 = 1;
        for (int i22 = i10 + 1; i22 < i9; i22++) {
            if (((l) arrayList.get(i22 - 1)).f(i14) != ((l) arrayList.get(i22)).f(i14)) {
                i21++;
            }
        }
        long j10 = 4;
        long j11 = (hVar.f6573b / j10) + j7 + 2 + (i21 * 2);
        hVar.P(i21);
        hVar.P(i11);
        for (int i23 = i10; i23 < i9; i23++) {
            int f7 = ((l) arrayList.get(i23)).f(i14);
            if (i23 == i10 || f7 != ((l) arrayList.get(i23 - 1)).f(i14)) {
                hVar.P(f7 & 255);
            }
        }
        h hVar4 = new h();
        int i24 = i10;
        while (i24 < i9) {
            byte f8 = ((l) arrayList.get(i24)).f(i14);
            int i25 = i24 + 1;
            int i26 = i25;
            while (true) {
                if (i26 >= i9) {
                    i12 = i9;
                    break;
                } else {
                    if (f8 != ((l) arrayList.get(i26)).f(i14)) {
                        i12 = i26;
                        break;
                    }
                    i26++;
                }
            }
            if (i25 == i12 && i14 + 1 == ((l) arrayList.get(i24)).c()) {
                hVar.P(((Number) arrayList2.get(i24)).intValue());
                i13 = i12;
                hVar2 = hVar4;
            } else {
                hVar.P(((int) ((hVar4.f6573b / j10) + j11)) * i16);
                i13 = i12;
                hVar2 = hVar4;
                a(j11, hVar4, i14 + 1, arrayList, i24, i12, arrayList2);
            }
            hVar4 = hVar2;
            i24 = i13;
            i16 = -1;
        }
        hVar.M(hVar4);
    }
}
