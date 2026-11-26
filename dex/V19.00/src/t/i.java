package t;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends d {

    /* renamed from: a, reason: collision with root package name */
    public double[] f8970a;

    /* renamed from: b, reason: collision with root package name */
    public double[][] f8971b;

    /* renamed from: c, reason: collision with root package name */
    public double[] f8972c;

    @Override // t.d
    public final double b(double d7) {
        double d8;
        double d9;
        double e7;
        double[] dArr = this.f8970a;
        int length = dArr.length;
        double d10 = dArr[0];
        double[][] dArr2 = this.f8971b;
        if (d7 <= d10) {
            d8 = dArr2[0][0];
            d9 = d7 - d10;
            e7 = e(d10);
        } else {
            int i7 = length - 1;
            double d11 = dArr[i7];
            if (d7 < d11) {
                int i8 = 0;
                while (i8 < i7) {
                    double d12 = dArr[i8];
                    if (d7 == d12) {
                        return dArr2[i8][0];
                    }
                    int i9 = i8 + 1;
                    double d13 = dArr[i9];
                    if (d7 < d13) {
                        double d14 = (d7 - d12) / (d13 - d12);
                        return (dArr2[i9][0] * d14) + ((1.0d - d14) * dArr2[i8][0]);
                    }
                    i8 = i9;
                }
                return 0.0d;
            }
            d8 = dArr2[i7][0];
            d9 = d7 - d11;
            e7 = e(d11);
        }
        return (e7 * d9) + d8;
    }

    @Override // t.d
    public final void c(double d7, double[] dArr) {
        double[] dArr2 = this.f8970a;
        int length = dArr2.length;
        double[][] dArr3 = this.f8971b;
        int i7 = 0;
        int length2 = dArr3[0].length;
        double d8 = dArr2[0];
        double[] dArr4 = this.f8972c;
        if (d7 <= d8) {
            f(d8, dArr4);
            for (int i8 = 0; i8 < length2; i8++) {
                dArr[i8] = ((d7 - dArr2[0]) * dArr4[i8]) + dArr3[0][i8];
            }
            return;
        }
        int i9 = length - 1;
        double d9 = dArr2[i9];
        if (d7 >= d9) {
            f(d9, dArr4);
            while (i7 < length2) {
                dArr[i7] = ((d7 - dArr2[i9]) * dArr4[i7]) + dArr3[i9][i7];
                i7++;
            }
            return;
        }
        int i10 = 0;
        while (i10 < length - 1) {
            if (d7 == dArr2[i10]) {
                for (int i11 = 0; i11 < length2; i11++) {
                    dArr[i11] = dArr3[i10][i11];
                }
            }
            int i12 = i10 + 1;
            double d10 = dArr2[i12];
            if (d7 < d10) {
                double d11 = dArr2[i10];
                double d12 = (d7 - d11) / (d10 - d11);
                while (i7 < length2) {
                    dArr[i7] = (dArr3[i12][i7] * d12) + ((1.0d - d12) * dArr3[i10][i7]);
                    i7++;
                }
                return;
            }
            i10 = i12;
        }
    }

    @Override // t.d
    public final void d(double d7, float[] fArr) {
        double[] dArr = this.f8970a;
        int length = dArr.length;
        double[][] dArr2 = this.f8971b;
        int i7 = 0;
        int length2 = dArr2[0].length;
        double d8 = dArr[0];
        double[] dArr3 = this.f8972c;
        if (d7 <= d8) {
            f(d8, dArr3);
            for (int i8 = 0; i8 < length2; i8++) {
                fArr[i8] = (float) (((d7 - dArr[0]) * dArr3[i8]) + dArr2[0][i8]);
            }
            return;
        }
        int i9 = length - 1;
        double d9 = dArr[i9];
        if (d7 >= d9) {
            f(d9, dArr3);
            while (i7 < length2) {
                fArr[i7] = (float) (((d7 - dArr[i9]) * dArr3[i7]) + dArr2[i9][i7]);
                i7++;
            }
            return;
        }
        int i10 = 0;
        while (i10 < i9) {
            if (d7 == dArr[i10]) {
                for (int i11 = 0; i11 < length2; i11++) {
                    fArr[i11] = (float) dArr2[i10][i11];
                }
            }
            int i12 = i10 + 1;
            double d10 = dArr[i12];
            if (d7 < d10) {
                double d11 = dArr[i10];
                double d12 = (d7 - d11) / (d10 - d11);
                while (i7 < length2) {
                    fArr[i7] = (float) ((dArr2[i12][i7] * d12) + ((1.0d - d12) * dArr2[i10][i7]));
                    i7++;
                }
                return;
            }
            i10 = i12;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0012, code lost:
    
        if (r9 >= r3) goto L4;
     */
    @Override // t.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final double e(double r9) {
        /*
            r8 = this;
            double[] r0 = r8.f8970a
            int r1 = r0.length
            r2 = 0
            r3 = r0[r2]
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 >= 0) goto Lc
        La:
            r9 = r3
            goto L15
        Lc:
            int r3 = r1 + (-1)
            r3 = r0[r3]
            int r5 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r5 < 0) goto L15
            goto La
        L15:
            r3 = r2
        L16:
            int r4 = r1 + (-1)
            if (r3 >= r4) goto L34
            int r4 = r3 + 1
            r5 = r0[r4]
            int r7 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r7 > 0) goto L32
            r9 = r0[r3]
            double r5 = r5 - r9
            double[][] r9 = r8.f8971b
            r10 = r9[r3]
            r0 = r10[r2]
            r9 = r9[r4]
            r2 = r9[r2]
            double r2 = r2 - r0
            double r2 = r2 / r5
            return r2
        L32:
            r3 = r4
            goto L16
        L34:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: t.i.e(double):double");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0017, code lost:
    
        if (r12 >= r5) goto L4;
     */
    @Override // t.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(double r12, double[] r14) {
        /*
            r11 = this;
            double[] r0 = r11.f8970a
            int r1 = r0.length
            double[][] r2 = r11.f8971b
            r3 = 0
            r4 = r2[r3]
            int r4 = r4.length
            r5 = r0[r3]
            int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r7 > 0) goto L11
        Lf:
            r12 = r5
            goto L1a
        L11:
            int r5 = r1 + (-1)
            r5 = r0[r5]
            int r7 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r7 < 0) goto L1a
            goto Lf
        L1a:
            r5 = r3
        L1b:
            int r6 = r1 + (-1)
            if (r5 >= r6) goto L3d
            int r6 = r5 + 1
            r7 = r0[r6]
            int r9 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r9 > 0) goto L3b
            r12 = r0[r5]
            double r7 = r7 - r12
        L2a:
            if (r3 >= r4) goto L3d
            r12 = r2[r5]
            r0 = r12[r3]
            r12 = r2[r6]
            r9 = r12[r3]
            double r9 = r9 - r0
            double r9 = r9 / r7
            r14[r3] = r9
            int r3 = r3 + 1
            goto L2a
        L3b:
            r5 = r6
            goto L1b
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: t.i.f(double, double[]):void");
    }

    @Override // t.d
    public final double[] g() {
        return this.f8970a;
    }
}
