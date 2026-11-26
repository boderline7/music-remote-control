package g6;

import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends d0 {

    /* renamed from: d, reason: collision with root package name */
    public d0 f6577d;

    public m(d0 d0Var) {
        i5.a.g("delegate", d0Var);
        this.f6577d = d0Var;
    }

    @Override // g6.d0
    public final d0 a() {
        return this.f6577d.a();
    }

    @Override // g6.d0
    public final d0 b() {
        return this.f6577d.b();
    }

    @Override // g6.d0
    public final long c() {
        return this.f6577d.c();
    }

    @Override // g6.d0
    public final d0 d(long j7) {
        return this.f6577d.d(j7);
    }

    @Override // g6.d0
    public final boolean e() {
        return this.f6577d.e();
    }

    @Override // g6.d0
    public final void f() {
        this.f6577d.f();
    }

    @Override // g6.d0
    public final d0 g(long j7, TimeUnit timeUnit) {
        i5.a.g("unit", timeUnit);
        return this.f6577d.g(j7, timeUnit);
    }
}
