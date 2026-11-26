package y;

import com.yalantis.ucrop.view.CropImageView;
import java.util.LinkedHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x implements Comparable {
    public static final boolean DEBUG = false;
    public static final boolean OLD_WAY = false;
    public static final String TAG = "MotionPaths";

    /* renamed from: r, reason: collision with root package name */
    public static final String[] f9926r = {"position", "x", "y", "width", "height", "pathRotate"};

    /* renamed from: a, reason: collision with root package name */
    public t.f f9927a;

    /* renamed from: c, reason: collision with root package name */
    public float f9929c;

    /* renamed from: d, reason: collision with root package name */
    public float f9930d;

    /* renamed from: e, reason: collision with root package name */
    public float f9931e;

    /* renamed from: f, reason: collision with root package name */
    public float f9932f;

    /* renamed from: g, reason: collision with root package name */
    public float f9933g;

    /* renamed from: h, reason: collision with root package name */
    public float f9934h;

    /* renamed from: b, reason: collision with root package name */
    public int f9928b = 0;

    /* renamed from: i, reason: collision with root package name */
    public float f9935i = Float.NaN;

    /* renamed from: j, reason: collision with root package name */
    public int f9936j = -1;

    /* renamed from: k, reason: collision with root package name */
    public int f9937k = -1;

    /* renamed from: l, reason: collision with root package name */
    public float f9938l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    public n f9939m = null;

    /* renamed from: n, reason: collision with root package name */
    public LinkedHashMap f9940n = new LinkedHashMap();

    /* renamed from: o, reason: collision with root package name */
    public int f9941o = 0;

    /* renamed from: p, reason: collision with root package name */
    public double[] f9942p = new double[18];

    /* renamed from: q, reason: collision with root package name */
    public double[] f9943q = new double[18];

    public static boolean b(float f7, float f8) {
        return (Float.isNaN(f7) || Float.isNaN(f8)) ? Float.isNaN(f7) != Float.isNaN(f8) : Math.abs(f7 - f8) > 1.0E-6f;
    }

    public static void g(float f7, float f8, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        for (int i7 = 0; i7 < iArr.length; i7++) {
            float f13 = (float) dArr[i7];
            double d7 = dArr2[i7];
            int i8 = iArr[i7];
            if (i8 == 1) {
                f9 = f13;
            } else if (i8 == 2) {
                f11 = f13;
            } else if (i8 == 3) {
                f10 = f13;
            } else if (i8 == 4) {
                f12 = f13;
            }
        }
        float f14 = f9 - ((CropImageView.DEFAULT_ASPECT_RATIO * f10) / 2.0f);
        float f15 = f11 - ((CropImageView.DEFAULT_ASPECT_RATIO * f12) / 2.0f);
        fArr[0] = (((f10 * 1.0f) + f14) * f7) + ((1.0f - f7) * f14) + CropImageView.DEFAULT_ASPECT_RATIO;
        fArr[1] = (((f12 * 1.0f) + f15) * f8) + ((1.0f - f8) * f15) + CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void a(a0.k kVar) {
        int i7;
        this.f9927a = t.f.c(kVar.f84d.f136d);
        a0.m mVar = kVar.f84d;
        this.f9936j = mVar.f137e;
        this.f9937k = mVar.f134b;
        this.f9935i = mVar.f140h;
        this.f9928b = mVar.f138f;
        float f7 = kVar.f83c.f150e;
        this.f9938l = kVar.f85e.C;
        for (String str : kVar.f87g.keySet()) {
            a0.c cVar = (a0.c) kVar.f87g.get(str);
            if (cVar != null && (i7 = a0.a.f0a[cVar.f4c.ordinal()]) != 1 && i7 != 2 && i7 != 3) {
                this.f9940n.put(str, cVar);
            }
        }
    }

    public final void c(double[] dArr, int[] iArr) {
        float[] fArr = {this.f9930d, this.f9931e, this.f9932f, this.f9933g, this.f9934h, this.f9935i};
        int i7 = 0;
        for (int i8 : iArr) {
            if (i8 < 6) {
                dArr[i7] = fArr[r4];
                i7++;
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(this.f9930d, ((x) obj).f9930d);
    }

    public final void d(double d7, int[] iArr, double[] dArr, float[] fArr, int i7) {
        float f7 = this.f9931e;
        float f8 = this.f9932f;
        float f9 = this.f9933g;
        float f10 = this.f9934h;
        for (int i8 = 0; i8 < iArr.length; i8++) {
            float f11 = (float) dArr[i8];
            int i9 = iArr[i8];
            if (i9 == 1) {
                f7 = f11;
            } else if (i9 == 2) {
                f8 = f11;
            } else if (i9 == 3) {
                f9 = f11;
            } else if (i9 == 4) {
                f10 = f11;
            }
        }
        n nVar = this.f9939m;
        if (nVar != null) {
            float[] fArr2 = new float[2];
            nVar.c(d7, fArr2, new float[2]);
            float f12 = fArr2[0];
            float f13 = fArr2[1];
            double d8 = f12;
            double d9 = f7;
            double d10 = f8;
            f7 = (float) (((Math.sin(d10) * d9) + d8) - (f9 / 2.0f));
            f8 = (float) ((f13 - (Math.cos(d10) * d9)) - (f10 / 2.0f));
        }
        fArr[i7] = (f9 / 2.0f) + f7 + CropImageView.DEFAULT_ASPECT_RATIO;
        fArr[i7 + 1] = (f10 / 2.0f) + f8 + CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public final void e(String str, double[] dArr) {
        a0.c cVar = (a0.c) this.f9940n.get(str);
        if (cVar == null) {
            return;
        }
        int i7 = 0;
        if (cVar.c() == 1) {
            dArr[0] = cVar.a();
            return;
        }
        int c7 = cVar.c();
        cVar.b(new float[c7]);
        int i8 = 0;
        while (i7 < c7) {
            dArr[i8] = r1[i7];
            i7++;
            i8++;
        }
    }

    public final void f(float f7, float f8, float f9, float f10) {
        this.f9931e = f7;
        this.f9932f = f8;
        this.f9933g = f9;
        this.f9934h = f10;
    }

    public final void h(n nVar, x xVar) {
        double d7 = (((this.f9933g / 2.0f) + this.f9931e) - xVar.f9931e) - (xVar.f9933g / 2.0f);
        double d8 = (((this.f9934h / 2.0f) + this.f9932f) - xVar.f9932f) - (xVar.f9934h / 2.0f);
        this.f9939m = nVar;
        this.f9931e = (float) Math.hypot(d8, d7);
        if (Float.isNaN(this.f9938l)) {
            this.f9932f = (float) (Math.atan2(d8, d7) + 1.5707963267948966d);
        } else {
            this.f9932f = (float) Math.toRadians(this.f9938l);
        }
    }
}
