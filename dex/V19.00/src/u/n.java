package u;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n extends i {
    public static final int HORIZONTAL = 0;
    public static final int RELATIVE_BEGIN = 1;
    public static final int RELATIVE_END = 2;
    public static final int RELATIVE_PERCENT = 0;
    public static final int RELATIVE_UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public boolean A0;

    /* renamed from: v0, reason: collision with root package name */
    public float f9205v0 = -1.0f;

    /* renamed from: w0, reason: collision with root package name */
    public int f9206w0 = -1;

    /* renamed from: x0, reason: collision with root package name */
    public int f9207x0 = -1;

    /* renamed from: y0, reason: collision with root package name */
    public f f9208y0 = this.K;

    /* renamed from: z0, reason: collision with root package name */
    public int f9209z0 = 0;

    public n() {
        this.S.clear();
        this.S.add(this.f9208y0);
        int length = this.R.length;
        for (int i7 = 0; i7 < length; i7++) {
            this.R[i7] = this.f9208y0;
        }
    }

    @Override // u.i
    public final boolean C() {
        return this.A0;
    }

    @Override // u.i
    public final boolean D() {
        return this.A0;
    }

    @Override // u.i
    public final void S(s.d dVar, boolean z6) {
        if (this.V == null) {
            return;
        }
        f fVar = this.f9208y0;
        dVar.getClass();
        int n6 = s.d.n(fVar);
        if (this.f9209z0 == 1) {
            this.f9129a0 = n6;
            this.f9131b0 = 0;
            N(this.V.m());
            Q(0);
            return;
        }
        this.f9129a0 = 0;
        this.f9131b0 = n6;
        Q(this.V.s());
        N(0);
    }

    public final void T(int i7) {
        this.f9208y0.l(i7);
        this.A0 = true;
    }

    public final void U(int i7) {
        if (this.f9209z0 == i7) {
            return;
        }
        this.f9209z0 = i7;
        ArrayList arrayList = this.S;
        arrayList.clear();
        if (this.f9209z0 == 1) {
            this.f9208y0 = this.J;
        } else {
            this.f9208y0 = this.K;
        }
        arrayList.add(this.f9208y0);
        f[] fVarArr = this.R;
        int length = fVarArr.length;
        for (int i8 = 0; i8 < length; i8++) {
            fVarArr[i8] = this.f9208y0;
        }
    }

    @Override // u.i
    public final void c(s.d dVar, boolean z6) {
        j jVar = (j) this.V;
        if (jVar == null) {
            return;
        }
        Object k7 = jVar.k(e.LEFT);
        Object k8 = jVar.k(e.RIGHT);
        i iVar = this.V;
        boolean z7 = iVar != null && iVar.U[0] == h.WRAP_CONTENT;
        if (this.f9209z0 == 0) {
            k7 = jVar.k(e.TOP);
            k8 = jVar.k(e.BOTTOM);
            i iVar2 = this.V;
            z7 = iVar2 != null && iVar2.U[1] == h.WRAP_CONTENT;
        }
        if (this.A0) {
            f fVar = this.f9208y0;
            if (fVar.f9118c) {
                s.i k9 = dVar.k(fVar);
                dVar.d(k9, this.f9208y0.d());
                if (this.f9206w0 != -1) {
                    if (z7) {
                        dVar.f(dVar.k(k8), k9, 0, 5);
                    }
                } else if (this.f9207x0 != -1 && z7) {
                    s.i k10 = dVar.k(k8);
                    dVar.f(k9, dVar.k(k7), 0, 5);
                    dVar.f(k10, k9, 0, 5);
                }
                this.A0 = false;
                return;
            }
        }
        if (this.f9206w0 != -1) {
            s.i k11 = dVar.k(this.f9208y0);
            dVar.e(k11, dVar.k(k7), this.f9206w0, 8);
            if (z7) {
                dVar.f(dVar.k(k8), k11, 0, 5);
                return;
            }
            return;
        }
        if (this.f9207x0 != -1) {
            s.i k12 = dVar.k(this.f9208y0);
            s.i k13 = dVar.k(k8);
            dVar.e(k12, k13, -this.f9207x0, 8);
            if (z7) {
                dVar.f(k12, dVar.k(k7), 0, 5);
                dVar.f(k13, k12, 0, 5);
                return;
            }
            return;
        }
        if (this.f9205v0 != -1.0f) {
            s.i k14 = dVar.k(this.f9208y0);
            s.i k15 = dVar.k(k8);
            float f7 = this.f9205v0;
            s.c l7 = dVar.l();
            l7.f8608d.i(k14, -1.0f);
            l7.f8608d.i(k15, f7);
            dVar.c(l7);
        }
    }

    @Override // u.i
    public final boolean d() {
        return true;
    }

    @Override // u.i
    public final void h(i iVar, HashMap hashMap) {
        super.h(iVar, hashMap);
        n nVar = (n) iVar;
        this.f9205v0 = nVar.f9205v0;
        this.f9206w0 = nVar.f9206w0;
        this.f9207x0 = nVar.f9207x0;
        U(nVar.f9209z0);
    }

    @Override // u.i
    public final f k(e eVar) {
        int i7 = m.f9204a[eVar.ordinal()];
        if (i7 == 1 || i7 == 2) {
            if (this.f9209z0 == 1) {
                return this.f9208y0;
            }
            return null;
        }
        if ((i7 == 3 || i7 == 4) && this.f9209z0 == 0) {
            return this.f9208y0;
        }
        return null;
    }
}
