package l2;

import com.kugou.auto.proxy.KgMusic;
import java.util.Arrays;
import p0.v;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b extends o2.a {
    public static final int[] N = {32768, 40960, 49152, 53248, 57344, 59904, 60928, 61440, 61952, 61952, 65535};
    public static final int[] O = {0, 0, 0, 2, 3, 5, 7, 11, 16, 20, 24, 32, 32};
    public static final int[] P = {40960, 49152, 53248, 57344, 59904, 60928, 61440, 61952, 62016, 65535};
    public static final int[] Q = {0, 0, 0, 0, 5, 7, 9, 13, 18, 22, 26, 34, 36};
    public static final int[] R = {32768, 49152, 57344, 61952, 61952, 61952, 61952, 61952, 65535};
    public static final int[] S = {0, 0, 0, 0, 0, 8, 16, 24, 33, 33, 33, 33, 33};
    public static final int[] T = {8192, 49152, 57344, 61440, 61952, 61952, 63456, 65535};
    public static final int[] U = {0, 0, 0, 0, 0, 0, 4, 44, 60, 76, 80, 80, z1.c.NEG_FLOAT};
    public static final int[] V = {4096, 9216, 32768, 49152, 64000, 65535, 65535, 65535};
    public static final int[] W = {0, 0, 0, 0, 0, 0, 2, 7, 53, z1.c.INVOKE_SUPER_RANGE, 233, 0, 0};
    public static final int[] X = {2048, 9216, 60928, 65152, 65535, 65535, 65535};
    public static final int[] Y = {0, 0, 0, 0, 0, 0, 0, 2, 16, z1.c.MUL_INT_LIT8, z1.c.INVOKE_POLYMORPHIC_RANGE, 0, 0};
    public static final int[] Z = {v.ACTION_POINTER_INDEX_MASK, 65535, 65535, 65535, 65535, 65535};

    /* renamed from: a0, reason: collision with root package name */
    public static final int[] f7284a0 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 255, 0, 0, 0};

    /* renamed from: b0, reason: collision with root package name */
    public static final int[] f7285b0 = {1, 3, 4, 4, 5, 6, 7, 8, 8, 4, 4, 5, 6, 6, 4, 0};

    /* renamed from: c0, reason: collision with root package name */
    public static final int[] f7286c0 = {0, z1.c.AND_LONG, z1.c.ADD_INT_LIT16, z1.c.SHL_INT_LIT8, KgMusic.imgUrlSize240, 248, z1.c.INVOKE_CUSTOM, z1.c.CONST_METHOD_HANDLE, 255, z1.c.AND_LONG_2ADDR, 128, z1.c.ADD_INT, z1.c.SHL_INT, z1.c.SUB_LONG, z1.c.ADD_INT_2ADDR};

    /* renamed from: d0, reason: collision with root package name */
    public static final int[] f7287d0 = {2, 3, 3, 3, 4, 4, 5, 6, 6, 4, 4, 5, 6, 6, 4, 0};

    /* renamed from: e0, reason: collision with root package name */
    public static final int[] f7288e0 = {0, 64, 96, z1.c.AND_LONG, z1.c.ADD_INT_LIT16, z1.c.SHL_INT_LIT8, KgMusic.imgUrlSize240, 248, z1.c.INVOKE_CUSTOM, z1.c.AND_LONG_2ADDR, 128, z1.c.ADD_INT, z1.c.SHL_INT, z1.c.SUB_LONG, z1.c.ADD_INT_2ADDR};
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;

    /* renamed from: d, reason: collision with root package name */
    public int f7289d;

    /* renamed from: e, reason: collision with root package name */
    public a f7290e;

    /* renamed from: f, reason: collision with root package name */
    public int f7291f;

    /* renamed from: g, reason: collision with root package name */
    public long f7292g;

    /* renamed from: h, reason: collision with root package name */
    public byte[] f7293h;

    /* renamed from: i, reason: collision with root package name */
    public int[] f7294i;

    /* renamed from: j, reason: collision with root package name */
    public int f7295j;

    /* renamed from: k, reason: collision with root package name */
    public int f7296k;

    /* renamed from: l, reason: collision with root package name */
    public int f7297l;

    /* renamed from: m, reason: collision with root package name */
    public int[] f7298m;

    /* renamed from: n, reason: collision with root package name */
    public int[] f7299n;

    /* renamed from: o, reason: collision with root package name */
    public int[] f7300o;

    /* renamed from: p, reason: collision with root package name */
    public int[] f7301p;

    /* renamed from: q, reason: collision with root package name */
    public int[] f7302q;

    /* renamed from: r, reason: collision with root package name */
    public int[] f7303r;

    /* renamed from: s, reason: collision with root package name */
    public int[] f7304s;

    /* renamed from: t, reason: collision with root package name */
    public int[] f7305t;

    /* renamed from: u, reason: collision with root package name */
    public int[] f7306u;

    /* renamed from: v, reason: collision with root package name */
    public int[] f7307v;

    /* renamed from: w, reason: collision with root package name */
    public int[] f7308w;

    /* renamed from: x, reason: collision with root package name */
    public int f7309x;

    /* renamed from: y, reason: collision with root package name */
    public int f7310y;

    /* renamed from: z, reason: collision with root package name */
    public int f7311z;

    public static void c(int[] iArr, int[] iArr2) {
        int i7 = 0;
        for (int i8 = 7; i8 >= 0; i8--) {
            int i9 = 0;
            while (i9 < 32) {
                iArr[i7] = (iArr[i7] & (-256)) | i8;
                i9++;
                i7++;
            }
        }
        Arrays.fill(iArr2, 0);
        for (int i10 = 6; i10 >= 0; i10--) {
            iArr2[i10] = (7 - i10) * 32;
        }
    }

    public final int d(int i7, int i8, int[] iArr, int[] iArr2) {
        int i9 = i7 & 65520;
        int i10 = 0;
        while (iArr[i10] <= i9) {
            i8++;
            i10++;
        }
        a(i8);
        return ((i9 - (i10 != 0 ? iArr[i10 - 1] : 0)) >>> (16 - i8)) + iArr2[i8];
    }

    public final void e() {
        int d7 = d(b(), 5, V, W);
        while (true) {
            int[] iArr = this.f7301p;
            int i7 = iArr[d7];
            this.f7309x = i7 >>> 8;
            int i8 = i7 + 1;
            int i9 = i7 & 255;
            int[] iArr2 = this.f7308w;
            int i10 = iArr2[i9];
            iArr2[i9] = i10 + 1;
            if ((i8 & 255) != 0) {
                iArr[d7] = iArr[i10];
                iArr[i10] = i8;
                return;
            }
            c(iArr, iArr2);
        }
    }

    public final void f() {
        int b7 = b();
        int i7 = this.f7310y;
        int[] iArr = W;
        int[] iArr2 = V;
        int d7 = (i7 > 30207 ? d(b7, 8, Z, f7284a0) : i7 > 24063 ? d(b7, 6, X, Y) : i7 > 13823 ? d(b7, 5, iArr2, iArr) : i7 > 3583 ? d(b7, 5, T, U) : d(b7, 4, R, S)) & 255;
        if (this.F != 0) {
            if (d7 == 0 && b7 > 4095) {
                d7 = 256;
            }
            d7--;
            if (d7 == -1) {
                int b8 = b();
                a(1);
                if ((32768 & b8) != 0) {
                    this.F = 0;
                    this.E = 0;
                    return;
                }
                int i8 = (b8 & 16384) == 0 ? 3 : 4;
                a(1);
                int d8 = (d(b(), 5, iArr2, iArr) << 5) | (b() >>> 11);
                a(5);
                h(d8, i8);
                return;
            }
        } else {
            int i9 = this.E;
            this.E = i9 + 1;
            if (i9 >= 16 && this.H == 0) {
                this.F = 1;
            }
        }
        int i10 = this.f7310y + d7;
        this.f7310y = i10 - (i10 >>> 8);
        int i11 = this.I + 16;
        this.I = i11;
        if (i11 > 255) {
            this.I = z1.c.ADD_INT;
            this.J >>>= 1;
        }
        byte[] bArr = this.f7293h;
        int i12 = this.f7295j;
        this.f7295j = i12 + 1;
        int[] iArr3 = this.f7298m;
        bArr[i12] = (byte) (iArr3[d7] >>> 8);
        this.f7292g--;
        while (true) {
            int i13 = iArr3[d7];
            int i14 = i13 + 1;
            int i15 = i13 & 255;
            int[] iArr4 = this.f7306u;
            int i16 = iArr4[i15];
            iArr4[i15] = i16 + 1;
            if ((i14 & 255) <= 161) {
                iArr3[d7] = iArr3[i16];
                iArr3[i16] = i14;
                return;
            }
            c(iArr3, iArr4);
        }
    }

    public final void g() {
        int[] iArr;
        int i7;
        int i8;
        int i9 = 0;
        this.E = 0;
        int i10 = this.J + 16;
        this.J = i10;
        if (i10 > 255) {
            this.J = z1.c.ADD_INT;
            this.I >>>= 1;
        }
        int i11 = this.B;
        int b7 = b();
        int i12 = this.B;
        if (i12 >= 122) {
            b7 = d(b7, 3, P, Q);
        } else if (i12 >= 64) {
            b7 = d(b7, 2, N, O);
        } else if (b7 < 256) {
            a(16);
        } else {
            while (((b7 << i9) & 32768) == 0) {
                i9++;
            }
            a(i9 + 1);
            b7 = i9;
        }
        int i13 = this.B + b7;
        this.B = i13 - (i13 >>> 5);
        int b8 = b();
        int i14 = this.f7311z;
        int d7 = i14 > 10495 ? d(b8, 5, V, W) : i14 > 1791 ? d(b8, 5, T, U) : d(b8, 4, R, S);
        int i15 = this.f7311z + d7;
        this.f7311z = i15 - (i15 >>> 8);
        while (true) {
            iArr = this.f7300o;
            int i16 = iArr[d7 & 255];
            i7 = i16 + 1;
            int i17 = i16 & 255;
            int[] iArr2 = this.f7307v;
            i8 = iArr2[i17];
            iArr2[i17] = i8 + 1;
            if ((i7 & 255) != 0) {
                break;
            } else {
                c(iArr, iArr2);
            }
        }
        iArr[d7] = iArr[i8];
        iArr[i8] = i7;
        int b9 = ((65280 & i7) | (b() >>> 8)) >>> 1;
        a(7);
        int i18 = this.C;
        if (b7 != 1 && b7 != 4) {
            if (b7 == 0 && b9 <= this.K) {
                int i19 = i18 + 1;
                this.C = i19 - (i19 >>> 8);
            } else if (i18 > 0) {
                this.C = i18 - 1;
            }
        }
        int i20 = b7 + 3;
        if (b9 >= this.K) {
            i20 = b7 + 4;
        }
        if (b9 <= 256) {
            i20 += 8;
        }
        if (i18 > 176 || (this.f7310y >= 10752 && i11 < 64)) {
            this.K = 32512;
        } else {
            this.K = 8193;
        }
        int i21 = this.f7297l;
        this.f7294i[i21] = b9;
        this.f7297l = (i21 + 1) & 3;
        this.M = i20;
        this.L = b9;
        h(b9, i20);
    }

    public final void h(int i7, int i8) {
        this.f7292g -= i8;
        int i9 = this.f7295j;
        int i10 = (i9 - i7) & 4194303;
        if (i7 == 1) {
            byte[] bArr = this.f7293h;
            Arrays.fill(bArr, i9, i9 + i8, bArr[i10]);
            this.f7295j += i8;
        } else {
            if (i10 + i8 <= i9) {
                byte[] bArr2 = this.f7293h;
                System.arraycopy(bArr2, i10, bArr2, i9, i8);
                this.f7295j += i8;
                return;
            }
            while (true) {
                int i11 = i8 - 1;
                if (i8 == 0) {
                    return;
                }
                byte[] bArr3 = this.f7293h;
                int i12 = this.f7295j;
                bArr3[i12] = bArr3[i10];
                this.f7295j = (i12 + 1) & 4194303;
                i10 = (i10 + 1) & 4194303;
                i8 = i11;
            }
        }
    }

    public final void i() {
        int i7 = this.f7295j;
        int i8 = this.f7296k;
        if (i7 < i8) {
            this.f7290e.c(this.f7293h, i8, (-i8) & 4194303);
            this.f7290e.c(this.f7293h, 0, this.f7295j);
        } else {
            this.f7290e.c(this.f7293h, i8, i7 - i8);
        }
        this.f7296k = this.f7295j;
    }

    public final boolean j() {
        int i7 = this.f7291f;
        int i8 = this.f7876a;
        int i9 = i7 - i8;
        if (i9 < 0) {
            return false;
        }
        byte[] bArr = this.f7878c;
        if (i8 > 16384) {
            if (i9 > 0) {
                System.arraycopy(bArr, i8, bArr, 0, i9);
            }
            this.f7876a = 0;
            this.f7291f = i9;
            i7 = i9;
        }
        int b7 = this.f7290e.b(bArr, i7, (32768 - i7) & (-16));
        if (b7 > 0) {
            this.f7291f += b7;
        }
        this.f7289d = this.f7291f - 30;
        return b7 != -1;
    }
}
