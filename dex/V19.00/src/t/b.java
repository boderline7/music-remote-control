package t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends d {
    public static final int ARC_START_FLIP = 3;
    public static final int ARC_START_HORIZONTAL = 2;
    public static final int ARC_START_LINEAR = 0;
    public static final int ARC_START_VERTICAL = 1;

    /* renamed from: a, reason: collision with root package name */
    public final double[] f8949a;

    /* renamed from: b, reason: collision with root package name */
    public final a[] f8950b;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        if (r5 == r3) goto L15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public b(int[] r29, double[] r30, double[][] r31) {
        /*
            Method dump skipped, instructions count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: t.b.<init>(int[], double[], double[][]):void");
    }

    @Override // t.d
    public final double b(double d7) {
        a[] aVarArr = this.f8950b;
        a aVar = aVarArr[0];
        double d8 = aVar.f8933c;
        if (d7 < d8) {
            double d9 = d7 - d8;
            if (aVar.f8948r) {
                return (d9 * aVarArr[0].f8942l) + aVar.c(d8);
            }
            aVar.g(d8);
            return (aVarArr[0].a() * d9) + aVarArr[0].e();
        }
        if (d7 > aVarArr[aVarArr.length - 1].f8934d) {
            double d10 = aVarArr[aVarArr.length - 1].f8934d;
            double d11 = d7 - d10;
            int length = aVarArr.length - 1;
            return (d11 * aVarArr[length].f8942l) + aVarArr[length].c(d10);
        }
        for (int i7 = 0; i7 < aVarArr.length; i7++) {
            a aVar2 = aVarArr[i7];
            if (d7 <= aVar2.f8934d) {
                if (aVar2.f8948r) {
                    return aVar2.c(d7);
                }
                aVar2.g(d7);
                return aVarArr[i7].e();
            }
        }
        return Double.NaN;
    }

    @Override // t.d
    public final void c(double d7, double[] dArr) {
        a[] aVarArr = this.f8950b;
        a aVar = aVarArr[0];
        double d8 = aVar.f8933c;
        if (d7 < d8) {
            double d9 = d7 - d8;
            if (aVar.f8948r) {
                double c7 = aVar.c(d8);
                a aVar2 = aVarArr[0];
                dArr[0] = (aVar2.f8942l * d9) + c7;
                dArr[1] = (d9 * aVarArr[0].f8943m) + aVar2.d(d8);
                return;
            }
            aVar.g(d8);
            dArr[0] = (aVarArr[0].a() * d9) + aVarArr[0].e();
            dArr[1] = (aVarArr[0].b() * d9) + aVarArr[0].f();
            return;
        }
        if (d7 <= aVarArr[aVarArr.length - 1].f8934d) {
            for (int i7 = 0; i7 < aVarArr.length; i7++) {
                a aVar3 = aVarArr[i7];
                if (d7 <= aVar3.f8934d) {
                    if (aVar3.f8948r) {
                        dArr[0] = aVar3.c(d7);
                        dArr[1] = aVarArr[i7].d(d7);
                        return;
                    } else {
                        aVar3.g(d7);
                        dArr[0] = aVarArr[i7].e();
                        dArr[1] = aVarArr[i7].f();
                        return;
                    }
                }
            }
            return;
        }
        double d10 = aVarArr[aVarArr.length - 1].f8934d;
        double d11 = d7 - d10;
        int length = aVarArr.length - 1;
        a aVar4 = aVarArr[length];
        if (aVar4.f8948r) {
            double c8 = aVar4.c(d10);
            a aVar5 = aVarArr[length];
            dArr[0] = (aVar5.f8942l * d11) + c8;
            dArr[1] = (d11 * aVarArr[length].f8943m) + aVar5.d(d10);
            return;
        }
        aVar4.g(d7);
        dArr[0] = (aVarArr[length].a() * d11) + aVarArr[length].e();
        dArr[1] = (aVarArr[length].b() * d11) + aVarArr[length].f();
    }

    @Override // t.d
    public final void d(double d7, float[] fArr) {
        a[] aVarArr = this.f8950b;
        a aVar = aVarArr[0];
        double d8 = aVar.f8933c;
        if (d7 < d8) {
            double d9 = d7 - d8;
            if (aVar.f8948r) {
                double c7 = aVar.c(d8);
                a aVar2 = aVarArr[0];
                fArr[0] = (float) ((aVar2.f8942l * d9) + c7);
                fArr[1] = (float) ((d9 * aVarArr[0].f8943m) + aVar2.d(d8));
                return;
            }
            aVar.g(d8);
            fArr[0] = (float) ((aVarArr[0].a() * d9) + aVarArr[0].e());
            fArr[1] = (float) ((aVarArr[0].b() * d9) + aVarArr[0].f());
            return;
        }
        if (d7 <= aVarArr[aVarArr.length - 1].f8934d) {
            for (int i7 = 0; i7 < aVarArr.length; i7++) {
                a aVar3 = aVarArr[i7];
                if (d7 <= aVar3.f8934d) {
                    if (aVar3.f8948r) {
                        fArr[0] = (float) aVar3.c(d7);
                        fArr[1] = (float) aVarArr[i7].d(d7);
                        return;
                    } else {
                        aVar3.g(d7);
                        fArr[0] = (float) aVarArr[i7].e();
                        fArr[1] = (float) aVarArr[i7].f();
                        return;
                    }
                }
            }
            return;
        }
        double d10 = aVarArr[aVarArr.length - 1].f8934d;
        double d11 = d7 - d10;
        int length = aVarArr.length - 1;
        a aVar4 = aVarArr[length];
        if (!aVar4.f8948r) {
            aVar4.g(d7);
            fArr[0] = (float) aVarArr[length].e();
            fArr[1] = (float) aVarArr[length].f();
        } else {
            double c8 = aVar4.c(d10);
            a aVar5 = aVarArr[length];
            fArr[0] = (float) ((aVar5.f8942l * d11) + c8);
            fArr[1] = (float) ((d11 * aVarArr[length].f8943m) + aVar5.d(d10));
        }
    }

    @Override // t.d
    public final double e(double d7) {
        a[] aVarArr = this.f8950b;
        double d8 = aVarArr[0].f8933c;
        if (d7 < d8) {
            d7 = d8;
        }
        if (d7 > aVarArr[aVarArr.length - 1].f8934d) {
            d7 = aVarArr[aVarArr.length - 1].f8934d;
        }
        for (int i7 = 0; i7 < aVarArr.length; i7++) {
            a aVar = aVarArr[i7];
            if (d7 <= aVar.f8934d) {
                if (aVar.f8948r) {
                    return aVar.f8942l;
                }
                aVar.g(d7);
                return aVarArr[i7].a();
            }
        }
        return Double.NaN;
    }

    @Override // t.d
    public final void f(double d7, double[] dArr) {
        a[] aVarArr = this.f8950b;
        double d8 = aVarArr[0].f8933c;
        if (d7 < d8) {
            d7 = d8;
        } else if (d7 > aVarArr[aVarArr.length - 1].f8934d) {
            d7 = aVarArr[aVarArr.length - 1].f8934d;
        }
        for (int i7 = 0; i7 < aVarArr.length; i7++) {
            a aVar = aVarArr[i7];
            if (d7 <= aVar.f8934d) {
                if (aVar.f8948r) {
                    dArr[0] = aVar.f8942l;
                    dArr[1] = aVar.f8943m;
                    return;
                } else {
                    aVar.g(d7);
                    dArr[0] = aVarArr[i7].a();
                    dArr[1] = aVarArr[i7].b();
                    return;
                }
            }
        }
    }

    @Override // t.d
    public final double[] g() {
        return this.f8949a;
    }
}
