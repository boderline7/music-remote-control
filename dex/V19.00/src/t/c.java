package t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends d {

    /* renamed from: a, reason: collision with root package name */
    public double f8951a;

    /* renamed from: b, reason: collision with root package name */
    public double[] f8952b;

    @Override // t.d
    public final double b(double d7) {
        return this.f8952b[0];
    }

    @Override // t.d
    public final void c(double d7, double[] dArr) {
        double[] dArr2 = this.f8952b;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
    }

    @Override // t.d
    public final void d(double d7, float[] fArr) {
        int i7 = 0;
        while (true) {
            double[] dArr = this.f8952b;
            if (i7 >= dArr.length) {
                return;
            }
            fArr[i7] = (float) dArr[i7];
            i7++;
        }
    }

    @Override // t.d
    public final double e(double d7) {
        return 0.0d;
    }

    @Override // t.d
    public final void f(double d7, double[] dArr) {
        for (int i7 = 0; i7 < this.f8952b.length; i7++) {
            dArr[i7] = 0.0d;
        }
    }

    @Override // t.d
    public final double[] g() {
        return new double[]{this.f8951a};
    }
}
