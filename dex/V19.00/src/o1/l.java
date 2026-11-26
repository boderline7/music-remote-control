package o1;

import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class l extends k {

    /* renamed from: a, reason: collision with root package name */
    public i0.f[] f7834a;

    /* renamed from: b, reason: collision with root package name */
    public String f7835b;

    /* renamed from: c, reason: collision with root package name */
    public int f7836c;

    /* renamed from: d, reason: collision with root package name */
    public final int f7837d;

    public l() {
        this.f7834a = null;
        this.f7836c = 0;
    }

    public l(l lVar) {
        this.f7834a = null;
        this.f7836c = 0;
        this.f7835b = lVar.f7835b;
        this.f7837d = lVar.f7837d;
        this.f7834a = z.Q(lVar.f7834a);
    }

    public i0.f[] getPathData() {
        return this.f7834a;
    }

    public String getPathName() {
        return this.f7835b;
    }

    public void setPathData(i0.f[] fVarArr) {
        if (!z.s(this.f7834a, fVarArr)) {
            this.f7834a = z.Q(fVarArr);
            return;
        }
        i0.f[] fVarArr2 = this.f7834a;
        for (int i7 = 0; i7 < fVarArr.length; i7++) {
            fVarArr2[i7].f6778a = fVarArr[i7].f6778a;
            int i8 = 0;
            while (true) {
                float[] fArr = fVarArr[i7].f6779b;
                if (i8 < fArr.length) {
                    fVarArr2[i7].f6779b[i8] = fArr[i8];
                    i8++;
                }
            }
        }
    }
}
