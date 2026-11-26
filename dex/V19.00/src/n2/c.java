package n2;

import java.lang.reflect.Array;
import java.util.Arrays;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c {
    public static final int BIN_SCALE = 16384;
    public static final int[] G = {15581, 7999, 22975, 18675, 25761, 23228, 26162, 24657};
    public static final int INTERVAL = 128;
    public static final int INT_BITS = 7;
    public static final int MAX_FREQ = 124;
    public static final int MAX_O = 64;
    public static final int PERIOD_BITS = 7;
    public static final int TOT_BITS = 14;

    /* renamed from: b, reason: collision with root package name */
    public j f7698b;

    /* renamed from: e, reason: collision with root package name */
    public k f7701e;

    /* renamed from: f, reason: collision with root package name */
    public int f7702f;

    /* renamed from: g, reason: collision with root package name */
    public int f7703g;

    /* renamed from: h, reason: collision with root package name */
    public int f7704h;

    /* renamed from: i, reason: collision with root package name */
    public int f7705i;

    /* renamed from: j, reason: collision with root package name */
    public int f7706j;

    /* renamed from: k, reason: collision with root package name */
    public int f7707k;

    /* renamed from: p, reason: collision with root package name */
    public int f7712p;

    /* renamed from: q, reason: collision with root package name */
    public int f7713q;

    /* renamed from: r, reason: collision with root package name */
    public int f7714r;

    /* renamed from: a, reason: collision with root package name */
    public final j[][] f7697a = (j[][]) Array.newInstance((Class<?>) j.class, 25, 16);

    /* renamed from: l, reason: collision with root package name */
    public final int[] f7708l = new int[256];

    /* renamed from: m, reason: collision with root package name */
    public final int[] f7709m = new int[256];

    /* renamed from: n, reason: collision with root package name */
    public final int[] f7710n = new int[256];

    /* renamed from: o, reason: collision with root package name */
    public final int[] f7711o = new int[256];

    /* renamed from: s, reason: collision with root package name */
    public final int[][] f7715s = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 128, 64);

    /* renamed from: t, reason: collision with root package name */
    public final g f7716t = new g();

    /* renamed from: u, reason: collision with root package name */
    public final m f7717u = new m();

    /* renamed from: v, reason: collision with root package name */
    public final k f7718v = new k(null);

    /* renamed from: w, reason: collision with root package name */
    public final k f7719w = new k(null);

    /* renamed from: x, reason: collision with root package name */
    public final k f7720x = new k(null);

    /* renamed from: y, reason: collision with root package name */
    public final k f7721y = new k(null);

    /* renamed from: z, reason: collision with root package name */
    public final l f7722z = new l();
    public final l A = new l();
    public final d B = new d(null);
    public final d C = new d(null);
    public final d D = new d(null);
    public final d E = new d(null);
    public final int[] F = new int[64];

    /* renamed from: c, reason: collision with root package name */
    public d f7699c = null;

    /* renamed from: d, reason: collision with root package name */
    public d f7700d = null;

    /* JADX WARN: Code restructure failed: missing block: B:31:0x005e, code lost:
    
        r12 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(boolean r12, n2.k r13) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.c.a(boolean, n2.k):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x06ba  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x06d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int b() {
        /*
            Method dump skipped, instructions count: 1860
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.c.b():int");
    }

    public final void c() {
        int[] iArr;
        Arrays.fill(this.f7708l, 0);
        m mVar = this.f7717u;
        byte[] bArr = mVar.f7767m;
        int i7 = mVar.f7768n;
        Arrays.fill(bArr, i7, (mVar.f7762h.length * 4) + i7, (byte) 0);
        int i8 = mVar.f7759e;
        mVar.f7763i = i8;
        int i9 = mVar.f7755a;
        int i10 = ((i9 / 8) / 12) * 84;
        int i11 = m.UNIT_SIZE;
        int i12 = (i10 / 12) * i11;
        int i13 = i9 - i10;
        int i14 = (i13 % 12) + ((i13 / 12) * i11) + i8;
        mVar.f7764j = i14;
        mVar.f7760f = i14;
        mVar.f7766l = i8 + i13;
        mVar.f7761g = i14 + i12;
        int i15 = 0;
        int i16 = 1;
        while (true) {
            iArr = mVar.f7756b;
            if (i15 >= 4) {
                break;
            }
            iArr[i15] = i16 & 255;
            i15++;
            i16++;
        }
        int i17 = i16 + 1;
        while (i15 < 8) {
            iArr[i15] = i17 & 255;
            i15++;
            i17 += 2;
        }
        int i18 = i17 + 1;
        while (i15 < 12) {
            iArr[i15] = i18 & 255;
            i15++;
            i18 += 3;
        }
        int i19 = i18 + 1;
        while (i15 < 38) {
            iArr[i15] = i19 & 255;
            i15++;
            i19 += 4;
        }
        mVar.f7758d = 0;
        int i20 = 0;
        int i21 = 0;
        while (i20 < 128) {
            int i22 = i20 + 1;
            i21 += iArr[i21] < i22 ? 1 : 0;
            mVar.f7757c[i20] = i21 & 255;
            i20 = i22;
        }
        this.f7707k = (-Math.min(this.f7705i, 12)) - 1;
        int a7 = mVar.a();
        this.f7699c.f(a7);
        this.f7700d.f(a7);
        d dVar = this.f7699c;
        dVar.f7726f = 0;
        byte[] bArr2 = dVar.f7734a;
        if (bArr2 != null) {
            z.r1(bArr2, dVar.f7735b + 8, 0);
        }
        this.f7704h = this.f7705i;
        this.f7699c.g(256);
        d dVar2 = this.f7699c;
        b bVar = dVar2.f7724d;
        z.t1(bVar.f7735b, (short) (dVar2.a() + 1), bVar.f7734a);
        int b7 = mVar.b(128);
        this.f7701e.f7735b = b7;
        b bVar2 = this.f7699c.f7724d;
        z.r1(bVar2.f7734a, bVar2.f7735b + 2, b7);
        byte[] bArr3 = mVar.f7767m;
        int a8 = this.f7699c.f7724d.a();
        this.f7706j = this.f7707k;
        this.f7713q = 0;
        for (int i23 = 0; i23 < 256; i23++) {
            int i24 = (i23 * 6) + a8;
            bArr3[i24] = (byte) i23;
            bArr3[i24 + 1] = (byte) 1;
            z.r1(bArr3, i24 + 2, 0);
        }
        for (int i25 = 0; i25 < 128; i25++) {
            for (int i26 = 0; i26 < 8; i26++) {
                for (int i27 = 0; i27 < 64; i27 += 8) {
                    this.f7715s[i25][i26 + i27] = 16384 - (G[i26] / (i25 + 2));
                }
            }
        }
        for (int i28 = 0; i28 < 25; i28++) {
            for (int i29 = 0; i29 < 16; i29++) {
                j jVar = this.f7697a[i28][i29];
                jVar.f7750b = 3;
                jVar.f7749a = (((i28 * 5) + 10) << 3) & 65535;
                jVar.f7751c = 4;
            }
        }
    }

    public final String toString() {
        return "ModelPPM[\n  numMasked=" + this.f7702f + "\n  initEsc=" + this.f7703g + "\n  orderFall=" + this.f7704h + "\n  maxOrder=" + this.f7705i + "\n  runLength=" + this.f7706j + "\n  initRL=" + this.f7707k + "\n  escCount=" + this.f7712p + "\n  prevSuccess=" + this.f7713q + "\n  foundState=" + this.f7701e + "\n  coder=" + this.f7716t + "\n  subAlloc=" + this.f7717u + "\n]";
    }
}
