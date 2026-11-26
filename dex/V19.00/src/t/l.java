package t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends f {

    /* renamed from: d, reason: collision with root package name */
    public double f8981d;

    /* renamed from: e, reason: collision with root package name */
    public double f8982e;

    @Override // t.f
    public final double a(double d7) {
        double d8 = this.f8982e;
        double d9 = this.f8981d;
        if (d7 < d8) {
            return (d8 * d7) / (((d8 - d7) * d9) + d7);
        }
        return ((d7 - 1.0d) * (1.0d - d8)) / ((1.0d - d7) - ((d8 - d7) * d9));
    }

    @Override // t.f
    public final double b(double d7) {
        double d8 = this.f8982e;
        double d9 = this.f8981d;
        if (d7 < d8) {
            double d10 = d9 * d8 * d8;
            double d11 = ((d8 - d7) * d9) + d7;
            return d10 / (d11 * d11);
        }
        double d12 = d8 - 1.0d;
        double d13 = (((d8 - d7) * (-d9)) - d7) + 1.0d;
        return ((d12 * d9) * d12) / (d13 * d13);
    }
}
