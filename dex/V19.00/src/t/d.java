package t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class d {
    public static final int CONSTANT = 2;
    public static final int LINEAR = 1;
    public static final int SPLINE = 0;

    public static d a(int i7, double[] dArr, double[][] dArr2) {
        if (dArr.length == 1) {
            i7 = 2;
        }
        if (i7 == 0) {
            return new j(dArr, dArr2);
        }
        if (i7 == 2) {
            double d7 = dArr[0];
            double[] dArr3 = dArr2[0];
            c cVar = new c();
            cVar.f8951a = d7;
            cVar.f8952b = dArr3;
            return cVar;
        }
        i iVar = new i();
        int length = dArr2[0].length;
        iVar.f8972c = new double[length];
        iVar.f8970a = dArr;
        iVar.f8971b = dArr2;
        if (length > 2) {
            double d8 = 0.0d;
            int i8 = 0;
            while (true) {
                double d9 = d8;
                if (i8 >= dArr.length) {
                    break;
                }
                double d10 = dArr2[i8][0];
                if (i8 > 0) {
                    Math.hypot(d10 - d8, d10 - d9);
                }
                i8++;
                d8 = d10;
            }
        }
        return iVar;
    }

    public abstract double b(double d7);

    public abstract void c(double d7, double[] dArr);

    public abstract void d(double d7, float[] fArr);

    public abstract double e(double d7);

    public abstract void f(double d7, double[] dArr);

    public abstract double[] g();
}
