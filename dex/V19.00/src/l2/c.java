package l2;

import h1.m;
import java.util.Arrays;
import q0.i;
import u.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c extends b {

    /* renamed from: f0, reason: collision with root package name */
    public m2.b[] f7312f0;

    /* renamed from: g0, reason: collision with root package name */
    public byte[] f7313g0;

    /* renamed from: h0, reason: collision with root package name */
    public int f7314h0;

    /* renamed from: i0, reason: collision with root package name */
    public int f7315i0;

    /* renamed from: j0, reason: collision with root package name */
    public int f7316j0;

    /* renamed from: k0, reason: collision with root package name */
    public int f7317k0;

    /* renamed from: l0, reason: collision with root package name */
    public m2.a[] f7318l0;

    /* renamed from: m0, reason: collision with root package name */
    public m2.b f7319m0;

    /* renamed from: n0, reason: collision with root package name */
    public m2.b f7320n0;

    /* renamed from: o0, reason: collision with root package name */
    public m2.b f7321o0;

    /* renamed from: p0, reason: collision with root package name */
    public m2.b f7322p0;

    /* renamed from: q0, reason: collision with root package name */
    public m2.b f7323q0;
    public static final int[] LDecode = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14, 16, 20, 24, 28, 32, 40, 48, 56, 64, 80, 96, z1.c.INVOKE_DIRECT, 128, z1.c.AND_LONG, z1.c.AND_LONG_2ADDR, z1.c.SHL_INT_LIT8};
    public static final byte[] LBits = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
    public static final int[] DDecode = {0, 1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, z1.c.AND_LONG_2ADDR, 256, 384, 512, 768, 1024, 1536, 2048, 3072, 4096, 6144, 8192, 12288, 16384, 24576, 32768, 49152, 65536, 98304, 131072, m.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 262144, 327680, 393216, 458752, i.ACTION_COLLAPSE, 589824, 655360, 720896, 786432, 851968, 917504, 983040};
    public static final int[] DBits = {0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16};
    public static final int[] SDDecode = {0, 4, 8, 16, 32, 64, 128, z1.c.AND_LONG_2ADDR};
    public static final int[] SDBits = {2, 2, 3, 4, 5, 6, 6, 6};

    public static void n(byte[] bArr, int i7, m2.b bVar, int i8) {
        int i9;
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        Arrays.fill(iArr, 0);
        Arrays.fill(bVar.f7527d, 0);
        int i10 = 0;
        while (true) {
            if (i10 >= i8) {
                break;
            }
            int i11 = bArr[i7 + i10] & 15;
            iArr[i11] = iArr[i11] + 1;
            i10++;
        }
        iArr[0] = 0;
        iArr2[0] = 0;
        int[] iArr3 = bVar.f7526c;
        iArr3[0] = 0;
        int[] iArr4 = bVar.f7525b;
        iArr4[0] = 0;
        long j7 = 0;
        for (i9 = 1; i9 < 16; i9++) {
            j7 = (j7 + iArr[i9]) * 2;
            long j8 = j7 << (15 - i9);
            if (j8 > 65535) {
                j8 = 65535;
            }
            iArr4[i9] = (int) j8;
            int i12 = i9 - 1;
            int i13 = iArr3[i12] + iArr[i12];
            iArr3[i9] = i13;
            iArr2[i9] = i13;
        }
        for (int i14 = 0; i14 < i8; i14++) {
            byte b7 = bArr[i7 + i14];
            if (b7 != 0) {
                int[] iArr5 = bVar.f7527d;
                int i15 = b7 & 15;
                int i16 = iArr2[i15];
                iArr2[i15] = i16 + 1;
                iArr5[i16] = i14;
            }
        }
        bVar.f7524a = i8;
    }

    public final void k(int i7, int i8) {
        int i9 = this.f7297l;
        this.f7297l = i9 + 1;
        this.f7294i[i9 & 3] = i8;
        this.L = i8;
        this.M = i7;
        this.f7292g -= i7;
        int i10 = this.f7295j;
        int i11 = i10 - i8;
        if (i11 < 4194004 && i10 < 4194004) {
            if (i11 + i7 <= i10) {
                byte[] bArr = this.f7293h;
                System.arraycopy(bArr, i11, bArr, i10, i7);
                this.f7295j += i7;
                return;
            }
            byte[] bArr2 = this.f7293h;
            int i12 = i11 + 1;
            bArr2[i10] = bArr2[i11];
            this.f7295j = i10 + 2;
            int i13 = i11 + 2;
            bArr2[i10 + 1] = bArr2[i12];
            while (i7 > 2) {
                i7--;
                byte[] bArr3 = this.f7293h;
                int i14 = this.f7295j;
                this.f7295j = i14 + 1;
                bArr3[i14] = bArr3[i13];
                i13++;
            }
            return;
        }
        while (true) {
            int i15 = i7 - 1;
            if (i7 == 0) {
                return;
            }
            byte[] bArr4 = this.f7293h;
            int i16 = this.f7295j;
            bArr4[i16] = bArr4[i11 & 4194303];
            this.f7295j = (i16 + 1) & 4194303;
            i7 = i15;
            i11++;
        }
    }

    public final boolean l() {
        int i7;
        int b7;
        byte[] bArr = new byte[19];
        byte[] bArr2 = new byte[1028];
        if (this.f7876a > this.f7291f - 25 && !j()) {
            return false;
        }
        int b8 = b();
        this.f7314h0 = 32768 & b8;
        int i8 = b8 & 16384;
        byte[] bArr3 = this.f7313g0;
        if (i8 == 0) {
            Arrays.fill(bArr3, (byte) 0);
        }
        a(2);
        if (this.f7314h0 != 0) {
            int i9 = ((b8 >>> 12) & 3) + 1;
            this.f7315i0 = i9;
            if (this.f7316j0 >= i9) {
                this.f7316j0 = 0;
            }
            a(2);
            i7 = this.f7315i0 * q.OPTIMIZATION_STANDARD;
        } else {
            i7 = 374;
        }
        for (int i10 = 0; i10 < 19; i10++) {
            bArr[i10] = (byte) (b() >>> 12);
            a(4);
        }
        m2.b bVar = this.f7323q0;
        n(bArr, 0, bVar, 19);
        int i11 = 0;
        while (i11 < i7) {
            if (this.f7876a > this.f7291f - 5 && !j()) {
                return false;
            }
            int m6 = m(bVar);
            if (m6 < 16) {
                bArr2[i11] = (byte) ((m6 + bArr3[i11]) & 15);
                i11++;
            } else if (m6 == 16) {
                int b9 = (b() >>> 14) + 3;
                a(2);
                while (true) {
                    int i12 = b9 - 1;
                    if (b9 > 0 && i11 < i7) {
                        bArr2[i11] = bArr2[i11 - 1];
                        i11++;
                        b9 = i12;
                    }
                }
            } else {
                if (m6 == 17) {
                    b7 = (b() >>> 13) + 3;
                    a(3);
                } else {
                    b7 = (b() >>> 9) + 11;
                    a(7);
                }
                while (true) {
                    int i13 = b7 - 1;
                    if (b7 > 0 && i11 < i7) {
                        bArr2[i11] = 0;
                        i11++;
                        b7 = i13;
                    }
                }
            }
        }
        if (this.f7876a > this.f7291f) {
            return true;
        }
        if (this.f7314h0 != 0) {
            for (int i14 = 0; i14 < this.f7315i0; i14++) {
                n(bArr2, i14 * q.OPTIMIZATION_STANDARD, this.f7312f0[i14], q.OPTIMIZATION_STANDARD);
            }
        } else {
            n(bArr2, 0, this.f7319m0, 298);
            n(bArr2, 298, this.f7320n0, 48);
            n(bArr2, 346, this.f7322p0, 28);
        }
        System.arraycopy(bArr2, 0, bArr3, 0, bArr3.length);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
    
        if (r0 < r2[3]) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
    
        if (r0 < r2[5]) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
    
        if (r0 < r2[7]) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006a, code lost:
    
        if (r0 < r2[9]) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
    
        if (r0 < r2[11]) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0087, code lost:
    
        if (r0 < r2[13]) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002a, code lost:
    
        if (r0 < r2[1]) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002c, code lost:
    
        r3 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int m(m2.b r8) {
        /*
            r7 = this;
            int r0 = r7.b()
            r1 = 65534(0xfffe, float:9.1833E-41)
            r0 = r0 & r1
            long r0 = (long) r0
            int[] r2 = r8.f7525b
            r3 = 8
            r4 = r2[r3]
            long r4 = (long) r4
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 >= 0) goto L51
            r4 = 4
            r5 = r2[r4]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L37
            r3 = 2
            r5 = r2[r3]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L2e
            r4 = 1
            r5 = r2[r4]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L8c
        L2c:
            r3 = r4
            goto L8c
        L2e:
            r3 = 3
            r5 = r2[r3]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L2c
            goto L8c
        L37:
            r4 = 6
            r5 = r2[r4]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L48
            r3 = 5
            r5 = r2[r3]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L2c
            goto L8c
        L48:
            r4 = 7
            r5 = r2[r4]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L8c
            goto L2c
        L51:
            r3 = 12
            r4 = r2[r3]
            long r4 = (long) r4
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 >= 0) goto L77
            r4 = 10
            r5 = r2[r4]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L6d
            r3 = 9
            r5 = r2[r3]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L2c
            goto L8c
        L6d:
            r4 = 11
            r5 = r2[r4]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L8c
            goto L2c
        L77:
            r3 = 14
            r4 = r2[r3]
            long r4 = (long) r4
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 >= 0) goto L8a
            r4 = 13
            r5 = r2[r4]
            long r5 = (long) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L8c
            goto L2c
        L8a:
            r3 = 15
        L8c:
            r7.a(r3)
            int[] r4 = r8.f7526c
            r4 = r4[r3]
            int r0 = (int) r0
            int r1 = r3 + (-1)
            r1 = r2[r1]
            int r0 = r0 - r1
            int r1 = 16 - r3
            int r0 = r0 >>> r1
            int r4 = r4 + r0
            int r0 = r8.f7524a
            if (r4 < r0) goto La2
            r4 = 0
        La2:
            int[] r8 = r8.f7527d
            r8 = r8[r4]
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.c.m(m2.b):int");
    }
}
