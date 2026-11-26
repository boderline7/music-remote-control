package b4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends n {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f2960b;

    public /* synthetic */ i(int i7) {
        this.f2960b = i7;
    }

    public static void h(boolean[] zArr, int i7, int i8) {
        for (int i9 = 0; i9 < 9; i9++) {
            boolean z6 = true;
            int i10 = i7 + i9;
            if (((1 << (8 - i9)) & i8) == 0) {
                z6 = false;
            }
            zArr[i10] = z6;
        }
    }

    public static int i(int i7, String str) {
        int i8 = 0;
        int i9 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            i8 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * i9;
            i9++;
            if (i9 > i7) {
                i9 = 1;
            }
        }
        return i8 % 47;
    }

    public static e j(int i7, CharSequence charSequence) {
        int length = charSequence.length();
        if (i7 >= length) {
            return e.UNCODABLE;
        }
        char charAt = charSequence.charAt(i7);
        if (charAt == 241) {
            return e.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return e.UNCODABLE;
        }
        int i8 = i7 + 1;
        if (i8 >= length) {
            return e.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i8);
        return (charAt2 < '0' || charAt2 > '9') ? e.ONE_DIGIT : e.TWO_DIGITS;
    }

    public static boolean[] k(int i7, ArrayList arrayList) {
        int i8 = i7 % z1.c.SPUT;
        int[][] iArr = c.f2951d;
        arrayList.add(iArr[i8]);
        arrayList.add(iArr[106]);
        Iterator it = arrayList.iterator();
        int i9 = 0;
        int i10 = 0;
        while (it.hasNext()) {
            for (int i11 : (int[]) it.next()) {
                i10 += i11;
            }
        }
        boolean[] zArr = new boolean[i10];
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            i9 += n.b(zArr, i9, (int[]) it2.next(), true);
        }
        return zArr;
    }

    public static void l(int i7, int[] iArr) {
        for (int i8 = 0; i8 < 9; i8++) {
            int i9 = 1;
            if (((1 << (8 - i8)) & i7) != 0) {
                i9 = 2;
            }
            iArr[i8] = i9;
        }
    }

    @Override // b4.n
    public final boolean[] d(String str) {
        String str2 = str;
        char c7 = '$';
        char c8 = 26;
        switch (this.f2960b) {
            case 0:
                return e(str2, null);
            case 1:
                int length = str.length();
                if (length > 80) {
                    throw new IllegalArgumentException(a.a.d("Requested contents should be less than 80 digits long, but got ", length));
                }
                int i7 = 0;
                while (true) {
                    if (i7 < length) {
                        if ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str2.charAt(i7)) < 0) {
                            int length2 = str.length();
                            StringBuilder sb = new StringBuilder();
                            int i8 = 0;
                            while (i8 < length2) {
                                char charAt = str2.charAt(i8);
                                if (charAt != 0) {
                                    if (charAt != ' ') {
                                        if (charAt == '@') {
                                            sb.append("%V");
                                        } else if (charAt == '`') {
                                            sb.append("%W");
                                        } else if (charAt != '-' && charAt != '.') {
                                            if (charAt <= 26) {
                                                sb.append(c7);
                                                sb.append((char) (charAt + '@'));
                                            } else if (charAt < ' ') {
                                                sb.append('%');
                                                sb.append((char) (charAt + '&'));
                                            } else if (charAt <= ',' || charAt == '/' || charAt == ':') {
                                                sb.append('/');
                                                sb.append((char) (charAt + ' '));
                                            } else if (charAt <= '9') {
                                                sb.append(charAt);
                                            } else if (charAt <= '?') {
                                                sb.append('%');
                                                sb.append((char) (charAt + 11));
                                            } else if (charAt <= 'Z') {
                                                sb.append(charAt);
                                            } else if (charAt <= '_') {
                                                sb.append('%');
                                                sb.append((char) (charAt - 16));
                                            } else if (charAt <= 'z') {
                                                sb.append('+');
                                                sb.append((char) (charAt - ' '));
                                            } else {
                                                if (charAt > 127) {
                                                    throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + str2.charAt(i8) + "'");
                                                }
                                                sb.append('%');
                                                sb.append((char) (charAt - '+'));
                                            }
                                        }
                                    }
                                    sb.append(charAt);
                                } else {
                                    sb.append("%U");
                                }
                                i8++;
                                c7 = '$';
                            }
                            str2 = sb.toString();
                            length = str2.length();
                            if (length > 80) {
                                throw new IllegalArgumentException(a.a.e("Requested contents should be less than 80 digits long, but got ", length, " (extended full ASCII mode)"));
                            }
                        } else {
                            i7++;
                            c7 = '$';
                        }
                    }
                }
                int[] iArr = new int[9];
                boolean[] zArr = new boolean[(length * 13) + 25];
                l(z1.c.REM_INT, iArr);
                int b7 = n.b(zArr, 0, iArr, true);
                int[] iArr2 = {1};
                int b8 = n.b(zArr, b7, iArr2, false) + b7;
                for (int i9 = 0; i9 < length; i9++) {
                    l(c.f2952e["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(str2.charAt(i9))], iArr);
                    int b9 = n.b(zArr, b8, iArr, true) + b8;
                    b8 = n.b(zArr, b9, iArr2, false) + b9;
                }
                l(z1.c.REM_INT, iArr);
                n.b(zArr, b8, iArr, true);
                return zArr;
            default:
                int length3 = str.length();
                StringBuilder sb2 = new StringBuilder(length3 * 2);
                int i10 = 0;
                while (i10 < length3) {
                    char charAt2 = str2.charAt(i10);
                    if (charAt2 == 0) {
                        sb2.append("bU");
                    } else if (charAt2 <= c8) {
                        sb2.append('a');
                        sb2.append((char) (charAt2 + '@'));
                    } else if (charAt2 <= 31) {
                        sb2.append('b');
                        sb2.append((char) (charAt2 + '&'));
                    } else if (charAt2 == ' ' || charAt2 == '$' || charAt2 == '%' || charAt2 == '+') {
                        sb2.append(charAt2);
                    } else if (charAt2 <= ',') {
                        sb2.append('c');
                        sb2.append((char) (charAt2 + ' '));
                    } else if (charAt2 <= '9') {
                        sb2.append(charAt2);
                    } else if (charAt2 == ':') {
                        sb2.append("cZ");
                    } else if (charAt2 <= '?') {
                        sb2.append('b');
                        sb2.append((char) (charAt2 + 11));
                    } else if (charAt2 == '@') {
                        sb2.append("bV");
                    } else if (charAt2 <= 'Z') {
                        sb2.append(charAt2);
                    } else if (charAt2 <= '_') {
                        sb2.append('b');
                        sb2.append((char) (charAt2 - 16));
                    } else if (charAt2 == '`') {
                        sb2.append("bW");
                    } else if (charAt2 <= 'z') {
                        sb2.append('d');
                        sb2.append((char) (charAt2 - ' '));
                    } else {
                        if (charAt2 > 127) {
                            throw new IllegalArgumentException("Requested content contains a non-encodable character: '" + charAt2 + "'");
                        }
                        sb2.append('b');
                        sb2.append((char) (charAt2 - '+'));
                    }
                    i10++;
                    c8 = 26;
                }
                String sb3 = sb2.toString();
                int length4 = sb3.length();
                if (length4 > 80) {
                    throw new IllegalArgumentException(a.a.d("Requested contents should be less than 80 digits long after converting to extended encoding, but got ", length4));
                }
                boolean[] zArr2 = new boolean[((sb3.length() + 4) * 9) + 1];
                h(zArr2, 0, j.f2962e);
                int i11 = 9;
                for (int i12 = 0; i12 < length4; i12++) {
                    h(zArr2, i11, j.f2961d["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(sb3.charAt(i12))]);
                    i11 += 9;
                }
                int i13 = i(20, sb3);
                int[] iArr3 = j.f2961d;
                h(zArr2, i11, iArr3[i13]);
                StringBuilder j7 = a.a.j(sb3);
                j7.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(i13));
                h(zArr2, i11 + 9, iArr3[i(15, j7.toString())]);
                h(zArr2, i11 + 18, j.f2962e);
                zArr2[i11 + 27] = true;
                return zArr2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:152:0x0221, code lost:
    
        if (r8 == 101) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0245, code lost:
    
        if (r10 > 244) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x027a, code lost:
    
        if (j(r2 + 3, r25) == b4.e.TWO_DIGITS) goto L181;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x027c, code lost:
    
        r5 = 99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x028e, code lost:
    
        if (r10 == b4.e.ONE_DIGIT) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x029d, code lost:
    
        if (r10 == b4.e.TWO_DIGITS) goto L181;
     */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ec  */
    @Override // b4.n
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean[] e(java.lang.String r25, java.util.HashMap r26) {
        /*
            Method dump skipped, instructions count: 922
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: b4.i.e(java.lang.String, java.util.HashMap):boolean[]");
    }

    @Override // b4.n
    public final Set g() {
        switch (this.f2960b) {
            case 0:
                return Collections.singleton(w3.a.CODE_128);
            case 1:
                return Collections.singleton(w3.a.CODE_39);
            default:
                return Collections.singleton(w3.a.CODE_93);
        }
    }
}
