package s5;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g0 {
    public static String a(g0 g0Var, String str, int i7, int i8, String str2, boolean z6, boolean z7, boolean z8, boolean z9, int i9) {
        int i10 = (i9 & 1) != 0 ? 0 : i7;
        int length = (i9 & 2) != 0 ? str.length() : i8;
        boolean z10 = (i9 & 8) != 0 ? false : z6;
        boolean z11 = (i9 & 16) != 0 ? false : z7;
        boolean z12 = (i9 & 32) != 0 ? false : z8;
        boolean z13 = (i9 & 64) == 0 ? z9 : false;
        g0Var.getClass();
        i5.a.g("<this>", str);
        int i11 = i10;
        while (i11 < length) {
            int codePointAt = str.codePointAt(i11);
            int i12 = 32;
            int i13 = 128;
            if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && !z13) || o5.k.z(str2, (char) codePointAt) || ((codePointAt == 37 && (!z10 || (z11 && !c(i11, length, str)))) || (codePointAt == 43 && z12)))) {
                g6.h hVar = new g6.h();
                hVar.R(i10, i11, str);
                g6.h hVar2 = null;
                while (i11 < length) {
                    int codePointAt2 = str.codePointAt(i11);
                    if (!z10 || (codePointAt2 != 9 && codePointAt2 != 10 && codePointAt2 != 12 && codePointAt2 != 13)) {
                        if (codePointAt2 == 43 && z12) {
                            hVar.S(z10 ? "+" : "%2B");
                        } else if (codePointAt2 < i12 || codePointAt2 == 127 || ((codePointAt2 >= i13 && !z13) || o5.k.z(str2, (char) codePointAt2) || (codePointAt2 == 37 && (!z10 || (z11 && !c(i11, length, str)))))) {
                            if (hVar2 == null) {
                                hVar2 = new g6.h();
                            }
                            hVar2.T(codePointAt2);
                            while (!hVar2.t()) {
                                byte y6 = hVar2.y();
                                hVar.N(37);
                                char[] cArr = h0.f8791j;
                                hVar.N(cArr[((y6 & 255) >> 4) & 15]);
                                hVar.N(cArr[y6 & 15]);
                            }
                        } else {
                            hVar.T(codePointAt2);
                        }
                    }
                    i11 += Character.charCount(codePointAt2);
                    i12 = 32;
                    i13 = 128;
                }
                return hVar.H(hVar.f6573b, o5.a.UTF_8);
            }
            i11 += Character.charCount(codePointAt);
        }
        String substring = str.substring(i10, length);
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }

    public static int b(String str) {
        i5.a.g("scheme", str);
        if (i5.a.c(str, "http")) {
            return 80;
        }
        return i5.a.c(str, "https") ? 443 : -1;
    }

    public static boolean c(int i7, int i8, String str) {
        int i9 = i7 + 2;
        return i9 < i8 && str.charAt(i7) == '%' && t5.b.q(str.charAt(i7 + 1)) != -1 && t5.b.q(str.charAt(i9)) != -1;
    }

    public static String d(g0 g0Var, String str, int i7, int i8, boolean z6, int i9) {
        int i10;
        if ((i9 & 1) != 0) {
            i7 = 0;
        }
        if ((i9 & 2) != 0) {
            i8 = str.length();
        }
        if ((i9 & 4) != 0) {
            z6 = false;
        }
        g0Var.getClass();
        i5.a.g("<this>", str);
        int i11 = i7;
        while (i11 < i8) {
            char charAt = str.charAt(i11);
            if (charAt == '%' || (charAt == '+' && z6)) {
                g6.h hVar = new g6.h();
                hVar.R(i7, i11, str);
                while (i11 < i8) {
                    int codePointAt = str.codePointAt(i11);
                    if (codePointAt != 37 || (i10 = i11 + 2) >= i8) {
                        if (codePointAt == 43 && z6) {
                            hVar.N(32);
                            i11++;
                        }
                        hVar.T(codePointAt);
                        i11 += Character.charCount(codePointAt);
                    } else {
                        int q6 = t5.b.q(str.charAt(i11 + 1));
                        int q7 = t5.b.q(str.charAt(i10));
                        if (q6 != -1 && q7 != -1) {
                            hVar.N((q6 << 4) + q7);
                            i11 = Character.charCount(codePointAt) + i10;
                        }
                        hVar.T(codePointAt);
                        i11 += Character.charCount(codePointAt);
                    }
                }
                return hVar.H(hVar.f6573b, o5.a.UTF_8);
            }
            i11++;
        }
        String substring = str.substring(i7, i8);
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }

    public static ArrayList e(String str) {
        ArrayList arrayList = new ArrayList();
        int i7 = 0;
        while (i7 <= str.length()) {
            int F = o5.k.F(str, '&', i7, false, 4);
            if (F == -1) {
                F = str.length();
            }
            int F2 = o5.k.F(str, '=', i7, false, 4);
            if (F2 == -1 || F2 > F) {
                String substring = str.substring(i7, F);
                i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
                arrayList.add(substring);
                arrayList.add(null);
            } else {
                String substring2 = str.substring(i7, F2);
                i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring2);
                arrayList.add(substring2);
                String substring3 = str.substring(F2 + 1, F);
                i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring3);
                arrayList.add(substring3);
            }
            i7 = F + 1;
        }
        return arrayList;
    }
}
