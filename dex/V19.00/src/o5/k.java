package o5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class k extends i {
    public static boolean A(CharSequence charSequence, String str) {
        i5.a.g("<this>", charSequence);
        return G(charSequence, str, 0, false, 2) >= 0;
    }

    public static boolean B(String str, String str2) {
        i5.a.g("<this>", str);
        return str.endsWith(str2);
    }

    public static boolean C(String str, String str2) {
        return str == null ? str2 == null : str.equalsIgnoreCase(str2);
    }

    public static final int D(CharSequence charSequence) {
        i5.a.g("<this>", charSequence);
        return charSequence.length() - 1;
    }

    /* JADX WARN: Incorrect condition in loop: B:20:0x004c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int E(int r9, java.lang.CharSequence r10, java.lang.String r11, boolean r12) {
        /*
            java.lang.String r0 = "<this>"
            i5.a.g(r0, r10)
            java.lang.String r0 = "string"
            i5.a.g(r0, r11)
            if (r12 != 0) goto L19
            boolean r0 = r10 instanceof java.lang.String
            if (r0 != 0) goto L11
            goto L19
        L11:
            java.lang.String r10 = (java.lang.String) r10
            int r9 = r10.indexOf(r11, r9)
            goto L70
        L19:
            int r0 = r10.length()
            l5.e r1 = new l5.e
            if (r9 >= 0) goto L22
            r9 = 0
        L22:
            int r2 = r10.length()
            if (r0 <= r2) goto L29
            r0 = r2
        L29:
            r2 = 1
            r1.<init>(r9, r0, r2)
            boolean r0 = r10 instanceof java.lang.String
            int r2 = r1.f7452c
            int r1 = r1.f7451b
            if (r0 == 0) goto L53
            if (r2 <= 0) goto L39
            if (r9 <= r1) goto L3d
        L39:
            if (r2 >= 0) goto L6f
            if (r1 > r9) goto L6f
        L3d:
            r3 = 0
            r7 = r10
            java.lang.String r7 = (java.lang.String) r7
            int r5 = r11.length()
            r4 = r9
            r6 = r11
            r8 = r12
            boolean r0 = K(r3, r4, r5, r6, r7, r8)
            if (r0 == 0) goto L4f
            goto L70
        L4f:
            if (r9 == r1) goto L6f
            int r9 = r9 + r2
            goto L3d
        L53:
            if (r2 <= 0) goto L57
            if (r9 <= r1) goto L5b
        L57:
            if (r2 >= 0) goto L6f
            if (r1 > r9) goto L6f
        L5b:
            r4 = 0
            int r7 = r11.length()
            r3 = r11
            r5 = r10
            r6 = r9
            r8 = r12
            boolean r0 = L(r3, r4, r5, r6, r7, r8)
            if (r0 == 0) goto L6b
            goto L70
        L6b:
            if (r9 == r1) goto L6f
            int r9 = r9 + r2
            goto L5b
        L6f:
            r9 = -1
        L70:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: o5.k.E(int, java.lang.CharSequence, java.lang.String, boolean):int");
    }

    public static int F(CharSequence charSequence, char c7, int i7, boolean z6, int i8) {
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        if ((i8 & 4) != 0) {
            z6 = false;
        }
        i5.a.g("<this>", charSequence);
        return (z6 || !(charSequence instanceof String)) ? H(i7, charSequence, z6, new char[]{c7}) : ((String) charSequence).indexOf(c7, i7);
    }

    public static /* synthetic */ int G(CharSequence charSequence, String str, int i7, boolean z6, int i8) {
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        if ((i8 & 4) != 0) {
            z6 = false;
        }
        return E(i7, charSequence, str, z6);
    }

    public static final int H(int i7, CharSequence charSequence, boolean z6, char[] cArr) {
        int i8;
        i5.a.g("<this>", charSequence);
        i5.a.g("chars", cArr);
        boolean z7 = true;
        if (!z6 && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c5.l.D1(cArr), i7);
        }
        if (i7 < 0) {
            i7 = 0;
        }
        l5.e eVar = new l5.e(i7, D(charSequence), 1);
        int i9 = eVar.f7451b;
        int i10 = eVar.f7452c;
        if (i10 <= 0 ? i7 < i9 : i7 > i9) {
            z7 = false;
        }
        if (!z7) {
            i7 = i9;
        }
        while (z7) {
            if (i7 != i9) {
                i8 = i7 + i10;
            } else {
                if (!z7) {
                    throw new NoSuchElementException();
                }
                i8 = i7;
                z7 = false;
            }
            char charAt = charSequence.charAt(i7);
            for (char c7 : cArr) {
                if (i5.a.k(c7, charAt, z6)) {
                    return i7;
                }
            }
            i7 = i8;
        }
        return -1;
    }

    public static final boolean I(String str) {
        i5.a.g("<this>", str);
        if (str.length() == 0) {
            return true;
        }
        Iterable eVar = new l5.e(0, str.length() - 1, 1);
        if ((eVar instanceof Collection) && ((Collection) eVar).isEmpty()) {
            return true;
        }
        Iterator it = eVar.iterator();
        while (((l5.c) it).f7455c) {
            char charAt = str.charAt(((l5.c) it).b());
            if (!Character.isWhitespace(charAt) && !Character.isSpaceChar(charAt)) {
                return false;
            }
        }
        return true;
    }

    public static int J(CharSequence charSequence, char c7, int i7, int i8) {
        if ((i8 & 2) != 0) {
            i7 = D(charSequence);
        }
        i5.a.g("<this>", charSequence);
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(c7, i7);
        }
        char[] cArr = {c7};
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(c5.l.D1(cArr), i7);
        }
        int D = D(charSequence);
        if (i7 > D) {
            i7 = D;
        }
        while (-1 < i7) {
            if (i5.a.k(cArr[0], charSequence.charAt(i7), false)) {
                return i7;
            }
            i7--;
        }
        return -1;
    }

    public static final boolean K(int i7, int i8, int i9, String str, String str2, boolean z6) {
        i5.a.g("<this>", str);
        i5.a.g("other", str2);
        return !z6 ? str.regionMatches(i7, str2, i8, i9) : str.regionMatches(z6, i7, str2, i8, i9);
    }

    public static final boolean L(CharSequence charSequence, int i7, CharSequence charSequence2, int i8, int i9, boolean z6) {
        i5.a.g("<this>", charSequence);
        i5.a.g("other", charSequence2);
        if (i8 < 0 || i7 < 0 || i7 > charSequence.length() - i9 || i8 > charSequence2.length() - i9) {
            return false;
        }
        for (int i10 = 0; i10 < i9; i10++) {
            if (!i5.a.k(charSequence.charAt(i7 + i10), charSequence2.charAt(i8 + i10), z6)) {
                return false;
            }
        }
        return true;
    }

    public static String M(String str, String str2) {
        if (!R(str2, str, false)) {
            return str2;
        }
        String substring = str2.substring(str.length());
        i5.a.f("this as java.lang.String).substring(startIndex)", substring);
        return substring;
    }

    public static String N(String str, String str2, String str3) {
        i5.a.g("<this>", str);
        int E = E(0, str, str2, false);
        if (E < 0) {
            return str;
        }
        int length = str2.length();
        int i7 = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i8 = 0;
        do {
            sb.append((CharSequence) str, i8, E);
            sb.append(str3);
            i8 = E + length;
            if (E >= str.length()) {
                break;
            }
            E = E(E + i7, str, str2, false);
        } while (E > 0);
        sb.append((CharSequence) str, i8, str.length());
        String sb2 = sb.toString();
        i5.a.f("stringBuilder.append(this, i, length).toString()", sb2);
        return sb2;
    }

    public static final void O(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException(a.a.d("Limit must be non-negative, but was ", i7).toString());
        }
    }

    public static List P(CharSequence charSequence, char[] cArr) {
        i5.a.g("<this>", charSequence);
        if (cArr.length != 1) {
            O(0);
            c cVar = new c(charSequence, 0, 0, new j(cArr, false, 0));
            ArrayList arrayList = new ArrayList(c5.l.x1(new n5.c(cVar)));
            Iterator it = cVar.iterator();
            while (it.hasNext()) {
                arrayList.add(S(charSequence, (l5.e) it.next()));
            }
            return arrayList;
        }
        String valueOf = String.valueOf(cArr[0]);
        O(0);
        int E = E(0, charSequence, valueOf, false);
        if (E == -1) {
            return z.G0(charSequence.toString());
        }
        ArrayList arrayList2 = new ArrayList(10);
        int i7 = 0;
        do {
            arrayList2.add(charSequence.subSequence(i7, E).toString());
            i7 = valueOf.length() + E;
            E = E(i7, charSequence, valueOf, false);
        } while (E != -1);
        arrayList2.add(charSequence.subSequence(i7, charSequence.length()).toString());
        return arrayList2;
    }

    public static boolean Q(String str, String str2, int i7, boolean z6) {
        i5.a.g("<this>", str);
        return !z6 ? str.startsWith(str2, i7) : K(i7, 0, str2.length(), str, str2, z6);
    }

    public static boolean R(String str, String str2, boolean z6) {
        i5.a.g("<this>", str);
        i5.a.g("prefix", str2);
        return !z6 ? str.startsWith(str2) : K(0, 0, str2.length(), str, str2, z6);
    }

    public static final String S(CharSequence charSequence, l5.e eVar) {
        i5.a.g("<this>", charSequence);
        i5.a.g("range", eVar);
        return charSequence.subSequence(Integer.valueOf(eVar.f7450a).intValue(), Integer.valueOf(eVar.f7451b).intValue() + 1).toString();
    }

    public static String T(String str) {
        i5.a.g("<this>", str);
        i5.a.g("missingDelimiterValue", str);
        int J = J(str, '.', 0, 6);
        if (J == -1) {
            return str;
        }
        String substring = str.substring(J + 1, str.length());
        i5.a.f("this as java.lang.String…ing(startIndex, endIndex)", substring);
        return substring;
    }

    public static CharSequence U(CharSequence charSequence) {
        i5.a.g("<this>", charSequence);
        int length = charSequence.length() - 1;
        int i7 = 0;
        boolean z6 = false;
        while (i7 <= length) {
            char charAt = charSequence.charAt(!z6 ? i7 : length);
            boolean z7 = Character.isWhitespace(charAt) || Character.isSpaceChar(charAt);
            if (z6) {
                if (!z7) {
                    break;
                }
                length--;
            } else if (z7) {
                i7++;
            } else {
                z6 = true;
            }
        }
        return charSequence.subSequence(i7, length + 1);
    }

    public static boolean z(CharSequence charSequence, char c7) {
        return F(charSequence, c7, 0, false, 2) >= 0;
    }
}
