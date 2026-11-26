package a6;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class p extends w5.a {

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ q f456e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f457f = false;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ o0 f458g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(String str, q qVar, o0 o0Var) {
        super(str, true);
        this.f456e = qVar;
        this.f458g = o0Var;
    }

    @Override // w5.a
    public final long a() {
        long a7;
        int i7;
        g0[] g0VarArr;
        q qVar = this.f456e;
        boolean z6 = this.f457f;
        o0 o0Var = this.f458g;
        qVar.getClass();
        i5.a.g("settings", o0Var);
        i5.j jVar = new i5.j();
        w wVar = qVar.f460b;
        synchronized (wVar.f501y) {
            synchronized (wVar) {
                try {
                    o0 o0Var2 = wVar.f495s;
                    if (!z6) {
                        o0 o0Var3 = new o0();
                        o0Var3.b(o0Var2);
                        o0Var3.b(o0Var);
                        o0Var = o0Var3;
                    }
                    jVar.f6812a = o0Var;
                    a7 = o0Var.a() - o0Var2.a();
                    if (a7 != 0 && !wVar.f479c.isEmpty()) {
                        g0VarArr = (g0[]) wVar.f479c.values().toArray(new g0[0]);
                        o0 o0Var4 = jVar.f6812a;
                        i5.a.g("<set-?>", o0Var4);
                        wVar.f495s = o0Var4;
                        wVar.f487k.c(new n(wVar.f480d + " onSettings", wVar, jVar, 0), 0L);
                    }
                    g0VarArr = null;
                    o0 o0Var42 = jVar.f6812a;
                    i5.a.g("<set-?>", o0Var42);
                    wVar.f495s = o0Var42;
                    wVar.f487k.c(new n(wVar.f480d + " onSettings", wVar, jVar, 0), 0L);
                } catch (Throwable th) {
                    throw th;
                }
            }
            try {
                wVar.f501y.A(jVar.f6812a);
            } catch (IOException e7) {
                wVar.B(e7);
            }
        }
        if (g0VarArr == null) {
            return -1L;
        }
        for (g0 g0Var : g0VarArr) {
            synchronized (g0Var) {
                g0Var.f416f += a7;
                if (a7 > 0) {
                    g0Var.notifyAll();
                }
            }
        }
        return -1L;
    }
}
