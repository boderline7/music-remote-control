package a4;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {

    /* renamed from: g, reason: collision with root package name */
    public static final int[] f282g = {3, 5, 8, 10, 12, 16, 18, 22, 30, 32, 36, 44, 49, 62, 86, z1.c.INVOKE_INTERFACE, z1.c.ADD_INT, z1.c.DIV_DOUBLE, z1.c.SUB_DOUBLE_2ADDR, 280, 368, 456, 576, 696, 816, 1050, 1304, 1558};

    /* renamed from: h, reason: collision with root package name */
    public static final int[] f283h = {3, 5, 8, 12, 18, 22, 30, 36, 44, 62, 86, z1.c.INVOKE_INTERFACE, z1.c.ADD_INT, z1.c.DIV_DOUBLE, z1.c.SUB_DOUBLE_2ADDR, 280, 368, 456, 576, 696, 816, 1050, 1304, 1558};

    /* renamed from: i, reason: collision with root package name */
    public static final int[] f284i = {5, 10, 16, 33, 32, 49};

    /* renamed from: a, reason: collision with root package name */
    public final g f285a;

    /* renamed from: b, reason: collision with root package name */
    public final h f286b;

    /* renamed from: c, reason: collision with root package name */
    public final int f287c;

    /* renamed from: d, reason: collision with root package name */
    public final int f288d;

    /* renamed from: e, reason: collision with root package name */
    public final f f289e;

    /* renamed from: f, reason: collision with root package name */
    public final int f290f;

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0072, code lost:
    
        if (r0 == r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a9, code lost:
    
        if (r0 != a4.h.X12) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00cf, code lost:
    
        if (r0 != a4.h.X12) goto L81;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public f(a4.g r6, a4.h r7, int r8, int r9, a4.f r10) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a4.f.<init>(a4.g, a4.h, int, int, a4.f):void");
    }

    public static byte[] a(int i7) {
        return new byte[]{(byte) i7};
    }

    public static byte[] b(int i7, int i8) {
        return new byte[]{(byte) i7, (byte) i8};
    }

    public static int c(boolean z6, int i7, char c7, int i8) {
        if (c7 == i8) {
            return 27;
        }
        if (z6) {
            if (c7 <= 31) {
                return c7;
            }
            if (c7 == ' ') {
                return 3;
            }
            return c7 <= '/' ? c7 - '!' : c7 <= '9' ? c7 - ',' : c7 <= '@' ? c7 - '+' : c7 <= 'Z' ? c7 - '3' : c7 <= '_' ? c7 - 'E' : c7 <= 127 ? c7 - '`' : c7;
        }
        if (c7 != 0) {
            if (i7 == 0 && c7 <= 3) {
                return c7 - 1;
            }
            if (i7 == 1 && c7 <= 31) {
                return c7;
            }
            if (c7 == ' ') {
                return 3;
            }
            if (c7 >= '!' && c7 <= '/') {
                return c7 - '!';
            }
            if (c7 >= '0' && c7 <= '9') {
                return c7 - ',';
            }
            if (c7 >= ':' && c7 <= '@') {
                return c7 - '+';
            }
            if (c7 >= 'A' && c7 <= 'Z') {
                return c7 - '@';
            }
            if (c7 >= '[' && c7 <= '_') {
                return c7 - 'E';
            }
            if (c7 != '`') {
                return (c7 < 'a' || c7 > 'z') ? (c7 < '{' || c7 > 127) ? c7 : c7 - '`' : c7 - 'S';
            }
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x001e, code lost:
    
        if (r5 == r7) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int i(char r5, boolean r6, int r7) {
        /*
            r0 = 31
            r1 = 0
            if (r6 == 0) goto L8
            if (r5 > r0) goto L8
            goto L32
        L8:
            if (r6 != 0) goto Ld
            if (r5 > r0) goto Ld
            goto L32
        Ld:
            r0 = 27
            char[] r2 = a4.i.f294a
            if (r6 == 0) goto L21
            r3 = r1
        L14:
            if (r3 >= r0) goto L1e
            char r4 = r2[r3]
            if (r4 != r5) goto L1b
            goto L2f
        L1b:
            int r3 = r3 + 1
            goto L14
        L1e:
            if (r5 != r7) goto L21
            goto L2f
        L21:
            if (r6 != 0) goto L31
        L23:
            if (r1 >= r0) goto L2d
            char r6 = r2[r1]
            if (r6 != r5) goto L2a
            goto L2f
        L2a:
            int r1 = r1 + 1
            goto L23
        L2d:
            if (r5 != r7) goto L31
        L2f:
            r1 = 1
            goto L32
        L31:
            r1 = 2
        L32:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a4.f.i(char, boolean, int):int");
    }

    public static int j(char c7) {
        if (c7 == '\r') {
            return 0;
        }
        if (c7 == '*') {
            return 1;
        }
        if (c7 == '>') {
            return 2;
        }
        if (c7 == ' ') {
            return 3;
        }
        return (c7 < '0' || c7 > '9') ? (c7 < 'A' || c7 > 'Z') ? c7 : c7 - '3' : c7 - ',';
    }

    public static void k(byte[] bArr, int i7, int i8, int i9, int i10) {
        int i11 = ((i9 & 255) * 40) + ((i8 & 255) * 1600) + (i10 & 255) + 1;
        bArr[i7] = (byte) (i11 / 256);
        bArr[i7 + 1] = (byte) (i11 % 256);
    }

    public final byte[] d(int i7, boolean z6) {
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < this.f288d; i8++) {
            char charAt = this.f285a.charAt(this.f287c + i8);
            if ((z6 && l6.f.P(charAt)) || (!z6 && l6.f.R(charAt))) {
                arrayList.add(Byte.valueOf((byte) c(z6, 0, charAt, i7)));
            } else if (i.d(charAt, i7)) {
                char c7 = (char) ((charAt & 255) - 128);
                if (!(z6 && l6.f.P(c7)) && (z6 || !l6.f.R(c7))) {
                    arrayList.add((byte) 1);
                    arrayList.add((byte) 30);
                    int i9 = i(c7, z6, i7);
                    arrayList.add(Byte.valueOf((byte) i9));
                    arrayList.add(Byte.valueOf((byte) c(z6, i9, c7, i7)));
                } else {
                    arrayList.add((byte) 1);
                    arrayList.add((byte) 30);
                    arrayList.add(Byte.valueOf((byte) c(z6, 0, c7, i7)));
                }
            } else {
                int i10 = i(charAt, z6, i7);
                arrayList.add(Byte.valueOf((byte) i10));
                arrayList.add(Byte.valueOf((byte) c(z6, i10, charAt, i7)));
            }
        }
        if (arrayList.size() % 3 != 0) {
            arrayList.add((byte) 0);
        }
        byte[] bArr = new byte[(arrayList.size() / 3) * 2];
        int i11 = 0;
        for (int i12 = 0; i12 < arrayList.size(); i12 += 3) {
            k(bArr, i11, ((Byte) arrayList.get(i12)).byteValue() & 255, ((Byte) arrayList.get(i12 + 1)).byteValue() & 255, ((Byte) arrayList.get(i12 + 2)).byteValue() & 255);
            i11 += 2;
        }
        return bArr;
    }

    public final h e() {
        h hVar = h.EDF;
        int i7 = this.f288d;
        int i8 = this.f290f;
        h hVar2 = this.f286b;
        if (hVar2 == hVar) {
            if (i7 < 4) {
                return h.ASCII;
            }
            int f7 = f();
            if (f7 > 0) {
                int i9 = i8 + f7;
                if (g(i9) - i9 <= 2 - f7) {
                    return h.ASCII;
                }
            }
        }
        if (hVar2 == h.C40 || hVar2 == h.TEXT || hVar2 == h.X12) {
            if (this.f287c + i7 >= ((int[]) this.f285a.f831c).length && g(i8) - i8 == 0) {
                return h.ASCII;
            }
            if (f() == 1) {
                int i10 = i8 + 1;
                if (g(i10) - i10 == 0) {
                    return h.ASCII;
                }
            }
        }
        return hVar2;
    }

    public final int f() {
        g gVar = this.f285a;
        int length = ((int[]) gVar.f831c).length;
        int i7 = this.f287c + this.f288d;
        int i8 = length - i7;
        if (i8 <= 4 && i7 < length) {
            if (i8 == 1) {
                return i.d(gVar.charAt(i7), gVar.f830b) ? 0 : 1;
            }
            if (i8 == 2) {
                if (!i.d(gVar.charAt(i7), gVar.f830b)) {
                    int i9 = i7 + 1;
                    if (!i.d(gVar.charAt(i9), gVar.f830b)) {
                        return (l6.f.L(gVar.charAt(i7)) && l6.f.L(gVar.charAt(i9))) ? 1 : 2;
                    }
                }
                return 0;
            }
            if (i8 == 3) {
                if (l6.f.L(gVar.charAt(i7)) && l6.f.L(gVar.charAt(i7 + 1)) && !i.d(gVar.charAt(i7 + 2), gVar.f830b)) {
                    return 2;
                }
                return (l6.f.L(gVar.charAt(i7 + 1)) && l6.f.L(gVar.charAt(i7 + 2)) && !i.d(gVar.charAt(i7), gVar.f830b)) ? 2 : 0;
            }
            if (l6.f.L(gVar.charAt(i7)) && l6.f.L(gVar.charAt(i7 + 1)) && l6.f.L(gVar.charAt(i7 + 2)) && l6.f.L(gVar.charAt(i7 + 3))) {
                return 2;
            }
        }
        return 0;
    }

    public final int g(int i7) {
        int i8 = e.f281b[this.f285a.f291d.ordinal()];
        if (i8 == 1) {
            int[] iArr = f283h;
            for (int i9 = 0; i9 < 24; i9++) {
                int i10 = iArr[i9];
                if (i10 >= i7) {
                    return i10;
                }
            }
        } else if (i8 == 2) {
            int[] iArr2 = f284i;
            for (int i11 = 0; i11 < 6; i11++) {
                int i12 = iArr2[i11];
                if (i12 >= i7) {
                    return i12;
                }
            }
        }
        int[] iArr3 = f282g;
        for (int i13 = 0; i13 < 28; i13++) {
            int i14 = iArr3[i13];
            if (i14 >= i7) {
                return i14;
            }
        }
        return iArr3[27];
    }

    public final h h() {
        f fVar = this.f289e;
        return fVar == null ? h.ASCII : fVar.e();
    }
}
