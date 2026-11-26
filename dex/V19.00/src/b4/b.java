package b4;

import java.util.Collections;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends n {

    /* renamed from: b, reason: collision with root package name */
    public static final char[] f2947b;

    /* renamed from: c, reason: collision with root package name */
    public static final char[] f2948c = {'T', 'N', '*', 'E'};

    /* renamed from: d, reason: collision with root package name */
    public static final char[] f2949d = {'/', ':', '+', '.'};

    /* renamed from: e, reason: collision with root package name */
    public static final char f2950e;

    static {
        char[] cArr = {'A', 'B', 'C', 'D'};
        f2947b = cArr;
        f2950e = cArr[0];
    }

    @Override // b4.n
    public final boolean[] d(String str) {
        int i7;
        int length = str.length();
        char c7 = f2950e;
        if (length < 2) {
            str = c7 + str + c7;
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            char[] cArr = f2947b;
            boolean G0 = a.G0(cArr, upperCase);
            boolean G02 = a.G0(cArr, upperCase2);
            char[] cArr2 = f2948c;
            boolean G03 = a.G0(cArr2, upperCase);
            boolean G04 = a.G0(cArr2, upperCase2);
            if (G0) {
                if (!G02) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(str));
                }
            } else if (!G03) {
                if (G02 || G04) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(str));
                }
                str = c7 + str + c7;
            } else if (!G04) {
                throw new IllegalArgumentException("Invalid start/end guards: ".concat(str));
            }
        }
        int i8 = 20;
        for (int i9 = 1; i9 < str.length() - 1; i9++) {
            if (Character.isDigit(str.charAt(i9)) || str.charAt(i9) == '-' || str.charAt(i9) == '$') {
                i8 += 9;
            } else {
                if (!a.G0(f2949d, str.charAt(i9))) {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i9) + '\'');
                }
                i8 += 10;
            }
        }
        boolean[] zArr = new boolean[(str.length() - 1) + i8];
        int i10 = 0;
        for (int i11 = 0; i11 < str.length(); i11++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i11));
            if (i11 == 0 || i11 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i12 = 0;
            while (true) {
                char[] cArr3 = a.f2945d;
                if (i12 >= cArr3.length) {
                    i7 = 0;
                    break;
                }
                if (upperCase3 == cArr3[i12]) {
                    i7 = a.f2946e[i12];
                    break;
                }
                i12++;
            }
            int i13 = 0;
            int i14 = 0;
            boolean z6 = true;
            while (i13 < 7) {
                zArr[i10] = z6;
                i10++;
                if (((i7 >> (6 - i13)) & 1) == 0 || i14 == 1) {
                    z6 = !z6;
                    i13++;
                    i14 = 0;
                } else {
                    i14++;
                }
            }
            if (i11 < str.length() - 1) {
                zArr[i10] = false;
                i10++;
            }
        }
        return zArr;
    }

    @Override // b4.n
    public final Set g() {
        return Collections.singleton(w3.a.CODABAR);
    }
}
