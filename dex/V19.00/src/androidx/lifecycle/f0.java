package androidx.lifecycle;

import java.io.Serializable;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class f0 {

    /* renamed from: k, reason: collision with root package name */
    public static final Object f2172k = new Object();

    /* renamed from: a, reason: collision with root package name */
    public final Object f2173a;

    /* renamed from: b, reason: collision with root package name */
    public final m.g f2174b;

    /* renamed from: c, reason: collision with root package name */
    public int f2175c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f2176d;

    /* renamed from: e, reason: collision with root package name */
    public volatile Object f2177e;

    /* renamed from: f, reason: collision with root package name */
    public volatile Object f2178f;

    /* renamed from: g, reason: collision with root package name */
    public int f2179g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f2180h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f2181i;

    /* renamed from: j, reason: collision with root package name */
    public final androidx.appcompat.app.s0 f2182j;

    public f0() {
        this.f2173a = new Object();
        this.f2174b = new m.g();
        this.f2175c = 0;
        Object obj = f2172k;
        this.f2178f = obj;
        this.f2182j = new androidx.appcompat.app.s0(11, this);
        this.f2177e = obj;
        this.f2179g = -1;
    }

    public f0(Serializable serializable) {
        this.f2173a = new Object();
        this.f2174b = new m.g();
        this.f2175c = 0;
        this.f2178f = f2172k;
        this.f2182j = new androidx.appcompat.app.s0(11, this);
        this.f2177e = serializable;
        this.f2179g = 0;
    }

    public static void a(String str) {
        if (!l.b.G0().f7265d.H0()) {
            throw new IllegalStateException(a.a.h("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void b(e0 e0Var) {
        if (e0Var.f2169b) {
            if (!e0Var.e()) {
                e0Var.b(false);
                return;
            }
            int i7 = e0Var.f2170c;
            int i8 = this.f2179g;
            if (i7 >= i8) {
                return;
            }
            e0Var.f2170c = i8;
            e0Var.f2168a.onChanged(this.f2177e);
        }
    }

    public final void c(e0 e0Var) {
        if (this.f2180h) {
            this.f2181i = true;
            return;
        }
        this.f2180h = true;
        do {
            this.f2181i = false;
            if (e0Var != null) {
                b(e0Var);
                e0Var = null;
            } else {
                m.g gVar = this.f2174b;
                gVar.getClass();
                m.d dVar = new m.d(gVar);
                gVar.f7484c.put(dVar, Boolean.FALSE);
                while (dVar.hasNext()) {
                    b((e0) ((Map.Entry) dVar.next()).getValue());
                    if (this.f2181i) {
                        break;
                    }
                }
            }
        } while (this.f2181i);
        this.f2180h = false;
    }

    public final Object d() {
        Object obj = this.f2177e;
        if (obj != f2172k) {
            return obj;
        }
        return null;
    }

    public final void e(x xVar, h0 h0Var) {
        Object obj;
        a("observe");
        if (((a0) xVar.getLifecycle()).f2136c == r.DESTROYED) {
            return;
        }
        d0 d0Var = new d0(this, xVar, h0Var);
        m.g gVar = this.f2174b;
        m.c a7 = gVar.a(h0Var);
        if (a7 != null) {
            obj = a7.f7474b;
        } else {
            m.c cVar = new m.c(h0Var, d0Var);
            gVar.f7485d++;
            m.c cVar2 = gVar.f7483b;
            if (cVar2 == null) {
                gVar.f7482a = cVar;
                gVar.f7483b = cVar;
            } else {
                cVar2.f7475c = cVar;
                cVar.f7476d = cVar2;
                gVar.f7483b = cVar;
            }
            obj = null;
        }
        e0 e0Var = (e0) obj;
        if (e0Var != null && !e0Var.d(xVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (e0Var != null) {
            return;
        }
        xVar.getLifecycle().a(d0Var);
    }

    public final void f(h0 h0Var) {
        Object obj;
        a("observeForever");
        c0 c0Var = new c0(this, h0Var);
        m.g gVar = this.f2174b;
        m.c a7 = gVar.a(h0Var);
        if (a7 != null) {
            obj = a7.f7474b;
        } else {
            m.c cVar = new m.c(h0Var, c0Var);
            gVar.f7485d++;
            m.c cVar2 = gVar.f7483b;
            if (cVar2 == null) {
                gVar.f7482a = cVar;
                gVar.f7483b = cVar;
            } else {
                cVar2.f7475c = cVar;
                cVar.f7476d = cVar2;
                gVar.f7483b = cVar;
            }
            obj = null;
        }
        e0 e0Var = (e0) obj;
        if (e0Var instanceof d0) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (e0Var != null) {
            return;
        }
        c0Var.b(true);
    }

    public final void g(h0 h0Var) {
        a("removeObserver");
        e0 e0Var = (e0) this.f2174b.b(h0Var);
        if (e0Var == null) {
            return;
        }
        e0Var.c();
        e0Var.b(false);
    }

    public abstract void h(Object obj);
}
