package h6;

import g6.h;
import g6.r;
import g6.v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {
    public static final long OVERFLOW_DIGIT_START = -7;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final int SEGMENTING_THRESHOLD = 4096;

    /* renamed from: a, reason: collision with root package name */
    public static final byte[] f6769a;

    static {
        byte[] bytes = "0123456789abcdef".getBytes(o5.a.UTF_8);
        i5.a.f("this as java.lang.String).getBytes(charset)", bytes);
        f6769a = bytes;
    }

    public static final String a(h hVar, long j7) {
        i5.a.g("<this>", hVar);
        if (j7 > 0) {
            long j8 = j7 - 1;
            if (hVar.C(j8) == 13) {
                String H = hVar.H(j8, o5.a.UTF_8);
                hVar.h(2L);
                return H;
            }
        }
        String H2 = hVar.H(j7, o5.a.UTF_8);
        hVar.h(1L);
        return H2;
    }

    public static final int b(h hVar, r rVar, boolean z6) {
        int i7;
        int i8;
        v vVar;
        byte[] bArr;
        int i9;
        i5.a.g("<this>", hVar);
        i5.a.g("options", rVar);
        v vVar2 = hVar.f6572a;
        int i10 = -2;
        if (vVar2 == null) {
            return z6 ? -2 : -1;
        }
        int i11 = vVar2.f6597b;
        int i12 = vVar2.f6598c;
        byte[] bArr2 = vVar2.f6596a;
        v vVar3 = vVar2;
        int i13 = -1;
        int i14 = 0;
        loop0: while (true) {
            int i15 = i14 + 1;
            int[] iArr = rVar.f6589b;
            int i16 = iArr[i14];
            int i17 = i14 + 2;
            int i18 = iArr[i15];
            if (i18 != -1) {
                i13 = i18;
            }
            if (vVar3 == null) {
                break;
            }
            if (i16 >= 0) {
                int i19 = i11 + 1;
                int i20 = bArr2[i11] & 255;
                int i21 = i17 + i16;
                while (i17 != i21) {
                    if (i20 == iArr[i17]) {
                        i7 = iArr[i17 + i16];
                        if (i19 == i12) {
                            vVar3 = vVar3.f6601f;
                            i5.a.d(vVar3);
                            i8 = vVar3.f6597b;
                            i12 = vVar3.f6598c;
                            bArr2 = vVar3.f6596a;
                            if (vVar3 == vVar2) {
                                vVar3 = null;
                            }
                        } else {
                            i8 = i19;
                        }
                    } else {
                        i17++;
                    }
                }
                return i13;
            }
            int i22 = (i16 * (-1)) + i17;
            while (true) {
                int i23 = i11 + 1;
                int i24 = i17 + 1;
                if ((bArr2[i11] & 255) != iArr[i17]) {
                    return i13;
                }
                boolean z7 = i24 == i22;
                if (i23 == i12) {
                    i5.a.d(vVar3);
                    v vVar4 = vVar3.f6601f;
                    i5.a.d(vVar4);
                    i9 = vVar4.f6597b;
                    int i25 = vVar4.f6598c;
                    bArr = vVar4.f6596a;
                    if (vVar4 != vVar2) {
                        vVar = vVar4;
                        i12 = i25;
                    } else {
                        if (!z7) {
                            break loop0;
                        }
                        i12 = i25;
                        vVar = null;
                    }
                } else {
                    vVar = vVar3;
                    bArr = bArr2;
                    i9 = i23;
                }
                if (z7) {
                    i7 = iArr[i24];
                    i8 = i9;
                    bArr2 = bArr;
                    vVar3 = vVar;
                    break;
                }
                i11 = i9;
                bArr2 = bArr;
                vVar3 = vVar;
                i17 = i24;
            }
            if (i7 >= 0) {
                return i7;
            }
            i14 = -i7;
            i11 = i8;
            i10 = -2;
        }
        return z6 ? i10 : i13;
    }
}
