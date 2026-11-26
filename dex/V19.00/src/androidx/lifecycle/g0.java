package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class g0 extends f0 {
    @Override // androidx.lifecycle.f0
    public final void h(Object obj) {
        f0.a("setValue");
        this.f2179g++;
        this.f2177e = obj;
        c(null);
    }

    public final void i(Object obj) {
        boolean z6;
        synchronized (this.f2173a) {
            z6 = this.f2178f == f0.f2172k;
            this.f2178f = obj;
        }
        if (z6) {
            l.b.G0().H0(this.f2182j);
        }
    }
}
