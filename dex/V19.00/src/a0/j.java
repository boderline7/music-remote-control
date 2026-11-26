package a0;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    public int[] f69a;

    /* renamed from: b, reason: collision with root package name */
    public int[] f70b;

    /* renamed from: c, reason: collision with root package name */
    public int f71c;

    /* renamed from: d, reason: collision with root package name */
    public int[] f72d;

    /* renamed from: e, reason: collision with root package name */
    public float[] f73e;

    /* renamed from: f, reason: collision with root package name */
    public int f74f;

    /* renamed from: g, reason: collision with root package name */
    public int[] f75g;

    /* renamed from: h, reason: collision with root package name */
    public String[] f76h;

    /* renamed from: i, reason: collision with root package name */
    public int f77i;

    /* renamed from: j, reason: collision with root package name */
    public int[] f78j;

    /* renamed from: k, reason: collision with root package name */
    public boolean[] f79k;

    /* renamed from: l, reason: collision with root package name */
    public int f80l;

    public final void a(float f7, int i7) {
        int i8 = this.f74f;
        int[] iArr = this.f72d;
        if (i8 >= iArr.length) {
            this.f72d = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f73e;
            this.f73e = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.f72d;
        int i9 = this.f74f;
        iArr2[i9] = i7;
        float[] fArr2 = this.f73e;
        this.f74f = i9 + 1;
        fArr2[i9] = f7;
    }

    public final void b(int i7, int i8) {
        int i9 = this.f71c;
        int[] iArr = this.f69a;
        if (i9 >= iArr.length) {
            this.f69a = Arrays.copyOf(iArr, iArr.length * 2);
            int[] iArr2 = this.f70b;
            this.f70b = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f69a;
        int i10 = this.f71c;
        iArr3[i10] = i7;
        int[] iArr4 = this.f70b;
        this.f71c = i10 + 1;
        iArr4[i10] = i8;
    }

    public final void c(int i7, String str) {
        int i8 = this.f77i;
        int[] iArr = this.f75g;
        if (i8 >= iArr.length) {
            this.f75g = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f76h;
            this.f76h = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
        }
        int[] iArr2 = this.f75g;
        int i9 = this.f77i;
        iArr2[i9] = i7;
        String[] strArr2 = this.f76h;
        this.f77i = i9 + 1;
        strArr2[i9] = str;
    }

    public final void d(int i7, boolean z6) {
        int i8 = this.f80l;
        int[] iArr = this.f78j;
        if (i8 >= iArr.length) {
            this.f78j = Arrays.copyOf(iArr, iArr.length * 2);
            boolean[] zArr = this.f79k;
            this.f79k = Arrays.copyOf(zArr, zArr.length * 2);
        }
        int[] iArr2 = this.f78j;
        int i9 = this.f80l;
        iArr2[i9] = i7;
        boolean[] zArr2 = this.f79k;
        this.f80l = i9 + 1;
        zArr2[i9] = z6;
    }

    public final void e(k kVar) {
        for (int i7 = 0; i7 < this.f71c; i7++) {
            int i8 = this.f69a[i7];
            int i9 = this.f70b[i7];
            if (i8 == 6) {
                kVar.f85e.D = i9;
            } else if (i8 == 7) {
                kVar.f85e.E = i9;
            } else if (i8 == 8) {
                kVar.f85e.K = i9;
            } else if (i8 == 27) {
                kVar.f85e.F = i9;
            } else if (i8 == 28) {
                kVar.f85e.H = i9;
            } else if (i8 == 41) {
                kVar.f85e.W = i9;
            } else if (i8 == 42) {
                kVar.f85e.X = i9;
            } else if (i8 == 61) {
                kVar.f85e.A = i9;
            } else if (i8 == 62) {
                kVar.f85e.B = i9;
            } else if (i8 == 72) {
                kVar.f85e.f103g0 = i9;
            } else if (i8 == 73) {
                kVar.f85e.f105h0 = i9;
            } else if (i8 == 88) {
                kVar.f84d.f144l = i9;
            } else if (i8 == 89) {
                kVar.f84d.f145m = i9;
            } else if (i8 == 2) {
                kVar.f85e.J = i9;
            } else if (i8 == 31) {
                kVar.f85e.L = i9;
            } else if (i8 == 34) {
                kVar.f85e.I = i9;
            } else if (i8 == 38) {
                kVar.f81a = i9;
            } else if (i8 == 64) {
                kVar.f84d.f134b = i9;
            } else if (i8 == 66) {
                kVar.f84d.f138f = i9;
            } else if (i8 == 76) {
                kVar.f84d.f137e = i9;
            } else if (i8 == 78) {
                kVar.f83c.f148c = i9;
            } else if (i8 == 97) {
                kVar.f85e.f121p0 = i9;
            } else if (i8 == 93) {
                kVar.f85e.M = i9;
            } else if (i8 != 94) {
                switch (i8) {
                    case 11:
                        kVar.f85e.Q = i9;
                        break;
                    case 12:
                        kVar.f85e.R = i9;
                        break;
                    case 13:
                        kVar.f85e.N = i9;
                        break;
                    case 14:
                        kVar.f85e.P = i9;
                        break;
                    case 15:
                        kVar.f85e.S = i9;
                        break;
                    case 16:
                        kVar.f85e.O = i9;
                        break;
                    case 17:
                        kVar.f85e.f98e = i9;
                        break;
                    case 18:
                        kVar.f85e.f100f = i9;
                        break;
                    default:
                        switch (i8) {
                            case 21:
                                kVar.f85e.f96d = i9;
                                break;
                            case 22:
                                kVar.f83c.f147b = i9;
                                break;
                            case 23:
                                kVar.f85e.f94c = i9;
                                break;
                            case 24:
                                kVar.f85e.G = i9;
                                break;
                            default:
                                switch (i8) {
                                    case 54:
                                        kVar.f85e.Y = i9;
                                        break;
                                    case 55:
                                        kVar.f85e.Z = i9;
                                        break;
                                    case z1.c.IF_EQZ /* 56 */:
                                        kVar.f85e.f91a0 = i9;
                                        break;
                                    case z1.c.IF_NEZ /* 57 */:
                                        kVar.f85e.f93b0 = i9;
                                        break;
                                    case z1.c.IF_LTZ /* 58 */:
                                        kVar.f85e.f95c0 = i9;
                                        break;
                                    case z1.c.IF_GEZ /* 59 */:
                                        kVar.f85e.f97d0 = i9;
                                        break;
                                    default:
                                        switch (i8) {
                                            case z1.c.IGET /* 82 */:
                                                kVar.f84d.f135c = i9;
                                                break;
                                            case z1.c.IGET_WIDE /* 83 */:
                                                kVar.f86f.f160i = i9;
                                                break;
                                            case z1.c.IGET_OBJECT /* 84 */:
                                                kVar.f84d.f142j = i9;
                                                break;
                                        }
                                }
                        }
                }
            } else {
                kVar.f85e.T = i9;
            }
        }
        for (int i10 = 0; i10 < this.f74f; i10++) {
            int i11 = this.f72d[i10];
            float f7 = this.f73e[i10];
            if (i11 == 19) {
                kVar.f85e.f102g = f7;
            } else if (i11 == 20) {
                kVar.f85e.f129x = f7;
            } else if (i11 == 37) {
                kVar.f85e.f130y = f7;
            } else if (i11 == 60) {
                kVar.f86f.f153b = f7;
            } else if (i11 == 63) {
                kVar.f85e.C = f7;
            } else if (i11 == 79) {
                kVar.f84d.f139g = f7;
            } else if (i11 == 85) {
                kVar.f84d.f141i = f7;
            } else if (i11 == 39) {
                kVar.f85e.V = f7;
            } else if (i11 != 40) {
                switch (i11) {
                    case 43:
                        kVar.f83c.f149d = f7;
                        break;
                    case 44:
                        o oVar = kVar.f86f;
                        oVar.f165n = f7;
                        oVar.f164m = true;
                        break;
                    case 45:
                        kVar.f86f.f154c = f7;
                        break;
                    case 46:
                        kVar.f86f.f155d = f7;
                        break;
                    case 47:
                        kVar.f86f.f156e = f7;
                        break;
                    case 48:
                        kVar.f86f.f157f = f7;
                        break;
                    case 49:
                        kVar.f86f.f158g = f7;
                        break;
                    case 50:
                        kVar.f86f.f159h = f7;
                        break;
                    case 51:
                        kVar.f86f.f161j = f7;
                        break;
                    case 52:
                        kVar.f86f.f162k = f7;
                        break;
                    case 53:
                        kVar.f86f.f163l = f7;
                        break;
                    default:
                        switch (i11) {
                            case e.GUIDELINE_USE_RTL /* 67 */:
                                kVar.f84d.f140h = f7;
                                break;
                            case z1.c.AGET /* 68 */:
                                kVar.f83c.f150e = f7;
                                break;
                            case z1.c.AGET_WIDE /* 69 */:
                                kVar.f85e.f99e0 = f7;
                                break;
                            case z1.c.AGET_OBJECT /* 70 */:
                                kVar.f85e.f101f0 = f7;
                                break;
                        }
                }
            } else {
                kVar.f85e.U = f7;
            }
        }
        for (int i12 = 0; i12 < this.f77i; i12++) {
            int i13 = this.f75g[i12];
            String str = this.f76h[i12];
            if (i13 == 5) {
                kVar.f85e.f131z = str;
            } else if (i13 == 65) {
                kVar.f84d.f136d = str;
            } else if (i13 == 74) {
                l lVar = kVar.f85e;
                lVar.f111k0 = str;
                lVar.f109j0 = null;
            } else if (i13 == 77) {
                kVar.f85e.f113l0 = str;
            } else if (i13 == 90) {
                kVar.f84d.f143k = str;
            }
        }
        for (int i14 = 0; i14 < this.f80l; i14++) {
            int i15 = this.f78j[i14];
            boolean z6 = this.f79k[i14];
            if (i15 == 44) {
                kVar.f86f.f164m = z6;
            } else if (i15 == 75) {
                kVar.f85e.f119o0 = z6;
            } else if (i15 == 80) {
                kVar.f85e.f115m0 = z6;
            } else if (i15 == 81) {
                kVar.f85e.f117n0 = z6;
            }
        }
    }
}
