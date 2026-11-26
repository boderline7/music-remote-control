package androidx.lifecycle;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a0 extends s {
    public static final y Companion = new y();

    /* renamed from: a, reason: collision with root package name */
    public final boolean f2134a;

    /* renamed from: b, reason: collision with root package name */
    public m.a f2135b;

    /* renamed from: c, reason: collision with root package name */
    public r f2136c;

    /* renamed from: d, reason: collision with root package name */
    public final WeakReference f2137d;

    /* renamed from: e, reason: collision with root package name */
    public int f2138e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f2139f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f2140g;

    /* renamed from: h, reason: collision with root package name */
    public final ArrayList f2141h;

    /* renamed from: i, reason: collision with root package name */
    public final p5.b f2142i;

    public a0(x xVar) {
        i5.a.g("provider", xVar);
        new AtomicReference();
        this.f2134a = true;
        this.f2135b = new m.a();
        r rVar = r.INITIALIZED;
        this.f2136c = rVar;
        this.f2141h = new ArrayList();
        this.f2137d = new WeakReference(xVar);
        this.f2142i = new p5.b(rVar == null ? q5.a.NULL : rVar);
    }

    @Override // androidx.lifecycle.s
    public final void a(w wVar) {
        v hVar;
        x xVar;
        i5.a.g("observer", wVar);
        d("addObserver");
        r rVar = this.f2136c;
        r rVar2 = r.DESTROYED;
        if (rVar != rVar2) {
            rVar2 = r.INITIALIZED;
        }
        i5.a.g("initialState", rVar2);
        z zVar = new z();
        b0 b0Var = b0.INSTANCE;
        boolean z6 = wVar instanceof v;
        boolean z7 = wVar instanceof f;
        if (z6 && z7) {
            hVar = new h((f) wVar, (v) wVar);
        } else if (z7) {
            hVar = new h((f) wVar, (v) null);
        } else if (z6) {
            hVar = (v) wVar;
        } else {
            Class<?> cls = wVar.getClass();
            b0 b0Var2 = b0.INSTANCE;
            b0Var2.getClass();
            if (b0.b(cls) == 2) {
                Object obj = b0.f2150b.get(cls);
                i5.a.d(obj);
                List list = (List) obj;
                if (list.size() == 1) {
                    b0.a((Constructor) list.get(0), wVar);
                    throw null;
                }
                int size = list.size();
                j[] jVarArr = new j[size];
                if (size > 0) {
                    Constructor constructor = (Constructor) list.get(0);
                    b0Var2.getClass();
                    b0.a(constructor, wVar);
                    throw null;
                }
                hVar = new e(jVarArr);
            } else {
                hVar = new h(wVar);
            }
        }
        zVar.f2216b = hVar;
        zVar.f2215a = rVar2;
        if (((z) this.f2135b.c(wVar, zVar)) == null && (xVar = (x) this.f2137d.get()) != null) {
            boolean z8 = this.f2138e != 0 || this.f2139f;
            r c7 = c(wVar);
            this.f2138e++;
            while (zVar.f2215a.compareTo(c7) < 0 && this.f2135b.f7471e.containsKey(wVar)) {
                this.f2141h.add(zVar.f2215a);
                o oVar = q.Companion;
                r rVar3 = zVar.f2215a;
                oVar.getClass();
                q b7 = o.b(rVar3);
                if (b7 == null) {
                    throw new IllegalStateException("no event up from " + zVar.f2215a);
                }
                zVar.a(xVar, b7);
                ArrayList arrayList = this.f2141h;
                arrayList.remove(arrayList.size() - 1);
                c7 = c(wVar);
            }
            if (!z8) {
                h();
            }
            this.f2138e--;
        }
    }

    @Override // androidx.lifecycle.s
    public final void b(w wVar) {
        i5.a.g("observer", wVar);
        d("removeObserver");
        this.f2135b.b(wVar);
    }

    public final r c(w wVar) {
        z zVar;
        HashMap hashMap = this.f2135b.f7471e;
        m.c cVar = hashMap.containsKey(wVar) ? ((m.c) hashMap.get(wVar)).f7476d : null;
        r rVar = (cVar == null || (zVar = (z) cVar.f7474b) == null) ? null : zVar.f2215a;
        ArrayList arrayList = this.f2141h;
        r rVar2 = arrayList.isEmpty() ^ true ? (r) arrayList.get(arrayList.size() - 1) : null;
        y yVar = Companion;
        r rVar3 = this.f2136c;
        yVar.getClass();
        i5.a.g("state1", rVar3);
        if (rVar == null || rVar.compareTo(rVar3) >= 0) {
            rVar = rVar3;
        }
        return (rVar2 == null || rVar2.compareTo(rVar) >= 0) ? rVar : rVar2;
    }

    public final void d(String str) {
        if (this.f2134a && !l.b.G0().f7265d.H0()) {
            throw new IllegalStateException(a.a.h("Method ", str, " must be called on the main thread").toString());
        }
    }

    public final void e(q qVar) {
        i5.a.g("event", qVar);
        d("handleLifecycleEvent");
        f(qVar.a());
    }

    public final void f(r rVar) {
        r rVar2 = this.f2136c;
        if (rVar2 == rVar) {
            return;
        }
        if (rVar2 == r.INITIALIZED && rVar == r.DESTROYED) {
            throw new IllegalStateException(("no event down from " + this.f2136c + " in component " + this.f2137d.get()).toString());
        }
        this.f2136c = rVar;
        if (this.f2139f || this.f2138e != 0) {
            this.f2140g = true;
            return;
        }
        this.f2139f = true;
        h();
        this.f2139f = false;
        if (this.f2136c == r.DESTROYED) {
            this.f2135b = new m.a();
        }
    }

    public final void g(r rVar) {
        i5.a.g("state", rVar);
        d("setCurrentState");
        f(rVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0030, code lost:
    
        r8.f2140g = false;
        r8.f2142i.e(r8.f2136c);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h() {
        /*
            Method dump skipped, instructions count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.a0.h():void");
    }
}
