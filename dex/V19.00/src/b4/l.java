package b4;

import com.google.zxing.FormatException;
import java.util.Collections;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends p {

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f2965b;

    public l(int i7) {
        this.f2965b = i7;
    }

    @Override // b4.n
    public final boolean[] d(String str) {
        switch (this.f2965b) {
            case 0:
                int length = str.length();
                if (length == 12) {
                    try {
                        str = str + o.H0(str);
                    } catch (FormatException e7) {
                        throw new IllegalArgumentException(e7);
                    }
                } else {
                    if (length != 13) {
                        throw new IllegalArgumentException(a.a.d("Requested contents should be 12 or 13 digits long, but got ", length));
                    }
                    try {
                        if (!o.G0(str)) {
                            throw new IllegalArgumentException("Contents do not pass checksum");
                        }
                    } catch (FormatException unused) {
                        throw new IllegalArgumentException("Illegal contents");
                    }
                }
                n.c(str);
                int i7 = k.f2963i[Character.digit(str.charAt(0), 10)];
                boolean[] zArr = new boolean[95];
                int b7 = n.b(zArr, 0, o.f2970d, true);
                for (int i8 = 1; i8 <= 6; i8++) {
                    int digit = Character.digit(str.charAt(i8), 10);
                    if (((i7 >> (6 - i8)) & 1) == 1) {
                        digit += 10;
                    }
                    b7 += n.b(zArr, b7, o.f2974h[digit], false);
                }
                int b8 = n.b(zArr, b7, o.f2971e, false) + b7;
                for (int i9 = 7; i9 <= 12; i9++) {
                    b8 += n.b(zArr, b8, o.f2973g[Character.digit(str.charAt(i9), 10)], true);
                }
                n.b(zArr, b8, o.f2970d, true);
                return zArr;
            case 1:
                int length2 = str.length();
                if (length2 == 7) {
                    try {
                        str = str + o.H0(str);
                    } catch (FormatException e8) {
                        throw new IllegalArgumentException(e8);
                    }
                } else {
                    if (length2 != 8) {
                        throw new IllegalArgumentException(a.a.d("Requested contents should be 7 or 8 digits long, but got ", length2));
                    }
                    try {
                        if (!o.G0(str)) {
                            throw new IllegalArgumentException("Contents do not pass checksum");
                        }
                    } catch (FormatException unused2) {
                        throw new IllegalArgumentException("Illegal contents");
                    }
                }
                n.c(str);
                boolean[] zArr2 = new boolean[67];
                int b9 = n.b(zArr2, 0, o.f2970d, true);
                for (int i10 = 0; i10 <= 3; i10++) {
                    b9 += n.b(zArr2, b9, o.f2973g[Character.digit(str.charAt(i10), 10)], false);
                }
                int b10 = n.b(zArr2, b9, o.f2971e, false) + b9;
                for (int i11 = 4; i11 <= 7; i11++) {
                    b10 += n.b(zArr2, b10, o.f2973g[Character.digit(str.charAt(i11), 10)], true);
                }
                n.b(zArr2, b10, o.f2970d, true);
                return zArr2;
            default:
                int length3 = str.length();
                if (length3 == 7) {
                    try {
                        str = str + o.H0(k.I0(str));
                    } catch (FormatException e9) {
                        throw new IllegalArgumentException(e9);
                    }
                } else {
                    if (length3 != 8) {
                        throw new IllegalArgumentException(a.a.d("Requested contents should be 7 or 8 digits long, but got ", length3));
                    }
                    try {
                        if (!o.G0(k.I0(str))) {
                            throw new IllegalArgumentException("Contents do not pass checksum");
                        }
                    } catch (FormatException unused3) {
                        throw new IllegalArgumentException("Illegal contents");
                    }
                }
                n.c(str);
                int digit2 = Character.digit(str.charAt(0), 10);
                if (digit2 != 0 && digit2 != 1) {
                    throw new IllegalArgumentException("Number system must be 0 or 1");
                }
                int i12 = k.f2964j[digit2][Character.digit(str.charAt(7), 10)];
                boolean[] zArr3 = new boolean[51];
                int b11 = n.b(zArr3, 0, o.f2970d, true);
                for (int i13 = 1; i13 <= 6; i13++) {
                    int digit3 = Character.digit(str.charAt(i13), 10);
                    if (((i12 >> (6 - i13)) & 1) == 1) {
                        digit3 += 10;
                    }
                    b11 += n.b(zArr3, b11, o.f2974h[digit3], false);
                }
                n.b(zArr3, b11, o.f2972f, false);
                return zArr3;
        }
    }

    @Override // b4.n
    public final Set g() {
        switch (this.f2965b) {
            case 0:
                return Collections.singleton(w3.a.EAN_13);
            case 1:
                return Collections.singleton(w3.a.EAN_8);
            default:
                return Collections.singleton(w3.a.UPC_E);
        }
    }
}
