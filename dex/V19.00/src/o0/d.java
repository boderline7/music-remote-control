package o0;

import s.e;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends e {

    /* renamed from: d, reason: collision with root package name */
    public final Object f7790d;

    public d(int i7) {
        super(i7, 1);
        this.f7790d = new Object();
    }

    @Override // s.e
    public final Object a() {
        Object a7;
        synchronized (this.f7790d) {
            a7 = super.a();
        }
        return a7;
    }

    @Override // s.e
    public final boolean b(Object obj) {
        boolean b7;
        synchronized (this.f7790d) {
            b7 = super.b(obj);
        }
        return b7;
    }
}
