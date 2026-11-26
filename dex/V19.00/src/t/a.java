package t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: s, reason: collision with root package name */
    public static final double[] f8930s = new double[91];

    /* renamed from: a, reason: collision with root package name */
    public double[] f8931a;

    /* renamed from: b, reason: collision with root package name */
    public double f8932b;

    /* renamed from: c, reason: collision with root package name */
    public double f8933c;

    /* renamed from: d, reason: collision with root package name */
    public double f8934d;

    /* renamed from: e, reason: collision with root package name */
    public double f8935e;

    /* renamed from: f, reason: collision with root package name */
    public double f8936f;

    /* renamed from: g, reason: collision with root package name */
    public double f8937g;

    /* renamed from: h, reason: collision with root package name */
    public double f8938h;

    /* renamed from: i, reason: collision with root package name */
    public double f8939i;

    /* renamed from: j, reason: collision with root package name */
    public double f8940j;

    /* renamed from: k, reason: collision with root package name */
    public double f8941k;

    /* renamed from: l, reason: collision with root package name */
    public double f8942l;

    /* renamed from: m, reason: collision with root package name */
    public double f8943m;

    /* renamed from: n, reason: collision with root package name */
    public double f8944n;

    /* renamed from: o, reason: collision with root package name */
    public double f8945o;

    /* renamed from: p, reason: collision with root package name */
    public double f8946p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f8947q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f8948r;

    public final double a() {
        double d7 = this.f8940j * this.f8946p;
        double hypot = this.f8944n / Math.hypot(d7, (-this.f8941k) * this.f8945o);
        if (this.f8947q) {
            d7 = -d7;
        }
        return d7 * hypot;
    }

    public final double b() {
        double d7 = this.f8940j * this.f8946p;
        double d8 = (-this.f8941k) * this.f8945o;
        double hypot = this.f8944n / Math.hypot(d7, d8);
        return this.f8947q ? (-d8) * hypot : d8 * hypot;
    }

    public final double c(double d7) {
        double d8 = (d7 - this.f8933c) * this.f8939i;
        double d9 = this.f8936f;
        double d10 = this.f8935e;
        return ((d9 - d10) * d8) + d10;
    }

    public final double d(double d7) {
        double d8 = (d7 - this.f8933c) * this.f8939i;
        double d9 = this.f8938h;
        double d10 = this.f8937g;
        return ((d9 - d10) * d8) + d10;
    }

    public final double e() {
        return (this.f8940j * this.f8945o) + this.f8942l;
    }

    public final double f() {
        return (this.f8941k * this.f8946p) + this.f8943m;
    }

    public final void g(double d7) {
        double d8 = (this.f8947q ? this.f8934d - d7 : d7 - this.f8933c) * this.f8939i;
        double d9 = 0.0d;
        if (d8 > 0.0d) {
            d9 = 1.0d;
            if (d8 < 1.0d) {
                double[] dArr = this.f8931a;
                double length = d8 * (dArr.length - 1);
                int i7 = (int) length;
                double d10 = dArr[i7];
                d9 = ((dArr[i7 + 1] - d10) * (length - i7)) + d10;
            }
        }
        double d11 = d9 * 1.5707963267948966d;
        this.f8945o = Math.sin(d11);
        this.f8946p = Math.cos(d11);
    }
}
