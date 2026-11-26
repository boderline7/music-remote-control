package e6;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import t5.b;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final String a(a aVar, byte[] bArr, byte[][] bArr2, int i7) {
        int i8;
        boolean z6;
        int i9;
        int i10;
        aVar.getClass();
        int length = bArr.length;
        int i11 = 0;
        while (i11 < length) {
            int i12 = (i11 + length) / 2;
            while (i12 > -1 && bArr[i12] != 10) {
                i12--;
            }
            int i13 = i12 + 1;
            int i14 = 1;
            while (true) {
                i8 = i13 + i14;
                if (bArr[i8] == 10) {
                    break;
                }
                i14++;
            }
            int i15 = i8 - i13;
            int i16 = i7;
            boolean z7 = false;
            int i17 = 0;
            int i18 = 0;
            while (true) {
                if (z7) {
                    i9 = 46;
                    z6 = false;
                } else {
                    byte b7 = bArr2[i16][i17];
                    byte[] bArr3 = b.EMPTY_BYTE_ARRAY;
                    int i19 = b7 & 255;
                    z6 = z7;
                    i9 = i19;
                }
                byte b8 = bArr[i13 + i18];
                byte[] bArr4 = b.EMPTY_BYTE_ARRAY;
                i10 = i9 - (b8 & 255);
                if (i10 != 0) {
                    break;
                }
                i18++;
                i17++;
                if (i18 == i15) {
                    break;
                }
                if (bArr2[i16].length != i17) {
                    z7 = z6;
                } else {
                    if (i16 == bArr2.length - 1) {
                        break;
                    }
                    i16++;
                    i17 = -1;
                    z7 = true;
                }
            }
            if (i10 >= 0) {
                if (i10 <= 0) {
                    int i20 = i15 - i18;
                    int length2 = bArr2[i16].length - i17;
                    int length3 = bArr2.length;
                    for (int i21 = i16 + 1; i21 < length3; i21++) {
                        length2 += bArr2[i21].length;
                    }
                    if (length2 >= i20) {
                        if (length2 <= i20) {
                            Charset charset = StandardCharsets.UTF_8;
                            i5.a.f("UTF_8", charset);
                            return new String(bArr, i13, i15, charset);
                        }
                    }
                }
                i11 = i8 + 1;
            }
            length = i12;
        }
        return null;
    }
}
