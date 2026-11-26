package b4;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    public int[][] f2958a;

    /* renamed from: b, reason: collision with root package name */
    public g[][] f2959b;

    public static void a(ArrayList arrayList, int i7, int[] iArr, int[] iArr2, int i8) {
        arrayList.add(c.f2951d[i7]);
        if (i8 != 0) {
            iArr2[0] = iArr2[0] + 1;
        }
        iArr[0] = (i7 * iArr2[0]) + iArr[0];
    }

    public static boolean b(CharSequence charSequence, f fVar, int i7) {
        int i8;
        char charAt;
        char charAt2 = charSequence.charAt(i7);
        int i9 = d.f2954b[fVar.ordinal()];
        if (i9 == 1) {
            return charAt2 == 241 || charAt2 == 242 || charAt2 == 243 || charAt2 == 244 || " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fÿ".indexOf(charAt2) >= 0;
        }
        if (i9 == 2) {
            return charAt2 == 241 || charAt2 == 242 || charAt2 == 243 || charAt2 == 244 || " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u007fÿ".indexOf(charAt2) >= 0;
        }
        if (i9 != 3) {
            return false;
        }
        return charAt2 == 241 || ((i8 = i7 + 1) < charSequence.length() && charAt2 >= '0' && charAt2 <= '9' && (charAt = charSequence.charAt(i8)) >= '0' && charAt <= '9');
    }

    public final int c(CharSequence charSequence, f fVar, int i7) {
        int i8;
        int i9;
        int i10 = this.f2958a[fVar.ordinal()][i7];
        if (i10 > 0) {
            return i10;
        }
        g gVar = g.NONE;
        int i11 = i7 + 1;
        int i12 = 0;
        boolean z6 = i11 >= charSequence.length();
        f[] fVarArr = {f.A, f.B};
        int i13 = Integer.MAX_VALUE;
        while (true) {
            if (i12 > 1) {
                break;
            }
            if (b(charSequence, fVarArr[i12], i7)) {
                g gVar2 = g.NONE;
                f fVar2 = fVarArr[i12];
                if (fVar != fVar2) {
                    gVar2 = g.valueOf(fVar2.toString());
                    i9 = 2;
                } else {
                    i9 = 1;
                }
                if (!z6) {
                    i9 += c(charSequence, fVarArr[i12], i11);
                }
                if (i9 < i13) {
                    gVar = gVar2;
                    i13 = i9;
                }
                if (fVar == fVarArr[(i12 + 1) % 2]) {
                    g gVar3 = g.SHIFT;
                    int c7 = z6 ? 2 : c(charSequence, fVar, i11) + 2;
                    if (c7 < i13) {
                        i13 = c7;
                        gVar = gVar3;
                    }
                }
            }
            i12++;
        }
        f fVar3 = f.C;
        if (b(charSequence, fVar3, i7)) {
            g gVar4 = g.NONE;
            if (fVar != fVar3) {
                gVar4 = g.C;
                i8 = 2;
            } else {
                i8 = 1;
            }
            int i14 = (charSequence.charAt(i7) != 241 ? 2 : 1) + i7;
            if (i14 < charSequence.length()) {
                i8 += c(charSequence, fVar3, i14);
            }
            if (i8 < i13) {
                gVar = gVar4;
                i13 = i8;
            }
        }
        if (i13 != Integer.MAX_VALUE) {
            this.f2958a[fVar.ordinal()][i7] = i13;
            this.f2959b[fVar.ordinal()][i7] = gVar;
            return i13;
        }
        throw new IllegalArgumentException("Bad character in input: ASCII value=" + ((int) charSequence.charAt(i7)));
    }
}
