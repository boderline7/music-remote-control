package com.sagittarius.v6.a;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a {
    public static byte[] a(String str) {
        int i7;
        int i8;
        int i9;
        int i10;
        int length = str.length();
        while (length > 0) {
            char charAt = str.charAt(length - 1);
            if (charAt != '=' && charAt != '\n' && charAt != '\r' && charAt != ' ' && charAt != '\t') {
                break;
            }
            length--;
        }
        byte[] bArr = new byte[(int) ((length * 6) / 8)];
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i11 < length) {
            char charAt2 = str.charAt(i11);
            if (charAt2 >= 'A' && charAt2 <= 'Z') {
                i7 = charAt2 - 'A';
            } else if (charAt2 >= 'a' && charAt2 <= 'z') {
                i7 = charAt2 - 'G';
            } else if (charAt2 >= '0' && charAt2 <= '9') {
                i7 = charAt2 + 4;
            } else if (charAt2 == '+' || charAt2 == '-') {
                i7 = 62;
            } else if (charAt2 == '/' || charAt2 == '_') {
                i7 = 63;
            } else {
                if (charAt2 != '\n' && charAt2 != '\r' && charAt2 != ' ' && charAt2 != '\t') {
                    return null;
                }
                i8 = i12;
                i9 = i13;
                i10 = i14;
                i11++;
                i14 = i10;
                i13 = i9;
                i12 = i8;
            }
            i8 = ((byte) i7) | (i12 << 6);
            i9 = i13 + 1;
            if (i9 % 4 == 0) {
                int i15 = i14 + 1;
                bArr[i14] = (byte) (i8 >> 16);
                int i16 = i15 + 1;
                bArr[i15] = (byte) (i8 >> 8);
                i10 = i16 + 1;
                bArr[i16] = (byte) i8;
            } else {
                i10 = i14;
            }
            i11++;
            i14 = i10;
            i13 = i9;
            i12 = i8;
        }
        int i17 = i13 % 4;
        if (i17 == 1) {
            return null;
        }
        if (i17 == 2) {
            bArr[i14] = (byte) ((i12 << 12) >> 16);
            i14++;
        } else if (i17 == 3) {
            int i18 = i12 << 6;
            int i19 = i14 + 1;
            bArr[i14] = (byte) (i18 >> 16);
            i14 = i19 + 1;
            bArr[i19] = (byte) (i18 >> 8);
        }
        if (i14 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i14];
        System.arraycopy(bArr, 0, bArr2, 0, i14);
        return bArr2;
    }
}
