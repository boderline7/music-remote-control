package h1;

import a2.r;
import a2.s;
import a2.t;
import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import n1.z;
import u1.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    public boolean f6660a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f6661b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f6662c;

    /* renamed from: d, reason: collision with root package name */
    public Object f6663d;

    /* renamed from: e, reason: collision with root package name */
    public final Object f6664e;

    /* renamed from: f, reason: collision with root package name */
    public Object f6665f;

    /* renamed from: g, reason: collision with root package name */
    public final Serializable f6666g;

    /* renamed from: h, reason: collision with root package name */
    public final Object f6667h;

    /* renamed from: i, reason: collision with root package name */
    public final Serializable f6668i;

    /* renamed from: j, reason: collision with root package name */
    public Object f6669j;

    public b(AssetManager assetManager, l.a aVar, e eVar, String str, File file) {
        this.f6660a = false;
        this.f6661b = assetManager;
        this.f6662c = aVar;
        this.f6663d = eVar;
        this.f6667h = str;
        this.f6668i = "dexopt/baseline.profm";
        this.f6666g = file;
        int i7 = Build.VERSION.SDK_INT;
        byte[] bArr = null;
        if (i7 <= 33) {
            switch (i7) {
                case 26:
                    bArr = o.f6695d;
                    break;
                case 27:
                    bArr = o.f6694c;
                    break;
                case 28:
                case 29:
                case 30:
                    bArr = o.f6693b;
                    break;
                case 31:
                case 32:
                case 33:
                    bArr = o.f6692a;
                    break;
            }
        }
        this.f6664e = bArr;
    }

    public b(u1.j jVar) {
        this.f6662c = new ArrayList();
        this.f6665f = new ArrayList();
        this.f6666g = new ArrayList();
        this.f6667h = s.NO_INFO;
        new ArrayList();
        this.f6668i = new ArrayList();
        this.f6669j = c2.b.EMPTY;
        u1.p pVar = jVar.f9264a;
        this.f6661b = pVar;
        if ((jVar.f9265b & 8) != 0) {
            this.f6664e = null;
        } else {
            u1.o oVar = new u1.o(this, pVar.f9296a);
            this.f6664e = oVar;
            ((List) this.f6665f).add(oVar);
        }
        for (q qVar : pVar.f9299d.f9305a) {
            ((List) this.f6665f).add(new u1.o(this, qVar));
        }
        u1.n nVar = new u1.n();
        this.f6663d = nVar;
        b(nVar);
        ((u1.n) this.f6663d).f9287c = true;
    }

    public static void d(u1.o oVar, q qVar) {
        if (oVar.f9293b.equals(qVar)) {
            return;
        }
        throw new IllegalArgumentException("requested " + qVar + " but was " + oVar.f9293b);
    }

    public final void a(a2.g gVar, u1.n nVar) {
        u1.n nVar2 = (u1.n) this.f6663d;
        if (nVar2 == null || !nVar2.f9287c) {
            throw new IllegalStateException("no current label");
        }
        nVar2.f9285a.add(gVar);
        int i7 = gVar.f228a.f245e;
        if (i7 == 1) {
            if (nVar == null) {
                return;
            }
            throw new IllegalArgumentException("unexpected branch: " + nVar);
        }
        if (i7 == 2) {
            if (nVar == null) {
                this.f6663d = null;
                return;
            } else {
                throw new IllegalArgumentException("unexpected branch: " + nVar);
            }
        }
        if (i7 == 3) {
            if (nVar == null) {
                throw new IllegalArgumentException("branch == null");
            }
            ((u1.n) this.f6663d).f9289e = nVar;
            this.f6663d = null;
            return;
        }
        if (i7 == 4) {
            if (nVar == null) {
                throw new IllegalArgumentException("branch == null");
            }
            List emptyList = Collections.emptyList();
            u1.n nVar3 = new u1.n();
            b(nVar3);
            u1.n nVar4 = (u1.n) this.f6663d;
            nVar4.f9289e = nVar3;
            nVar4.f9290f = nVar;
            nVar4.f9288d = emptyList;
            this.f6663d = nVar3;
            nVar3.f9287c = true;
            return;
        }
        if (i7 != 6) {
            throw new IllegalArgumentException();
        }
        if (nVar != null) {
            throw new IllegalArgumentException("unexpected branch: " + nVar);
        }
        ArrayList arrayList = new ArrayList((List) this.f6668i);
        u1.n nVar5 = new u1.n();
        b(nVar5);
        u1.n nVar6 = (u1.n) this.f6663d;
        nVar6.f9289e = nVar5;
        nVar6.f9290f = null;
        nVar6.f9288d = arrayList;
        this.f6663d = nVar5;
        nVar5.f9287c = true;
    }

    public final void b(u1.n nVar) {
        b bVar = nVar.f9286b;
        if (bVar == this) {
            return;
        }
        if (bVar != null) {
            throw new IllegalArgumentException("Cannot adopt label; it belongs to another Code");
        }
        nVar.f9286b = this;
        ((List) this.f6662c).add(nVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x006c, code lost:
    
        if (r7 == 7) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(u1.o r13, u1.o r14) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.b.c(u1.o, u1.o):void");
    }

    public final u1.o e(int i7, q qVar) {
        if (((u1.o) this.f6664e) != null) {
            i7++;
        }
        u1.o oVar = (u1.o) ((List) this.f6665f).get(i7);
        d(oVar, qVar);
        return oVar;
    }

    public final u1.o f(q qVar) {
        u1.o oVar = (u1.o) this.f6664e;
        if (oVar == null) {
            throw new IllegalStateException("static methods cannot access 'this'");
        }
        d(oVar, qVar);
        return oVar;
    }

    public final void g() {
        a2.p pVar;
        if (this.f6660a) {
            throw new AssertionError();
        }
        this.f6660a = true;
        int i7 = 0;
        for (u1.o oVar : (List) this.f6666g) {
            oVar.f9294c = i7;
            q qVar = oVar.f9293b;
            oVar.f9295d = a2.m.f(i7, qVar.f9303b);
            i7 += qVar.f9303b.e();
        }
        ArrayList arrayList = new ArrayList();
        int i8 = i7;
        for (u1.o oVar2 : (List) this.f6665f) {
            b2.k j7 = b2.k.j(i8 - i7);
            oVar2.f9294c = i8;
            q qVar2 = oVar2.f9293b;
            oVar2.f9295d = a2.m.f(i8, qVar2.f9303b);
            i8 += qVar2.f9303b.e();
            c2.c cVar = qVar2.f9303b;
            a2.p pVar2 = r.NOP;
            int a7 = cVar.a();
            if (a7 == 4) {
                pVar = r.MOVE_PARAM_DOUBLE;
            } else if (a7 == 5) {
                pVar = r.MOVE_PARAM_FLOAT;
            } else if (a7 == 6) {
                pVar = r.MOVE_PARAM_INT;
            } else if (a7 == 7) {
                pVar = r.MOVE_PARAM_LONG;
            } else {
                if (a7 != 9) {
                    r.b(cVar);
                    throw null;
                }
                pVar = r.MOVE_PARAM_OBJECT;
            }
            arrayList.add(new a2.i(pVar, (s) this.f6667h, oVar2.a(), a2.n.EMPTY, j7));
        }
        ((u1.n) ((List) this.f6662c).get(0)).f9285a.addAll(0, arrayList);
    }

    public final void h(a2.p pVar, u1.p pVar2, u1.o oVar, u1.o oVar2, u1.o... oVarArr) {
        s sVar = (s) this.f6667h;
        int i7 = oVar2 != null ? 1 : 0;
        a2.n nVar = new a2.n(oVarArr.length + i7);
        if (oVar2 != null) {
            nVar.g(0, oVar2.a());
        }
        for (int i8 = 0; i8 < oVarArr.length; i8++) {
            nVar.g(i8 + i7, oVarArr[i8].a());
        }
        a(new t(pVar, sVar, nVar, (c2.b) this.f6669j, pVar2.f9300e), null);
        if (oVar != null) {
            k(oVar, false);
        }
    }

    public final void i(u1.p pVar, u1.o oVar, u1.o oVar2, u1.o... oVarArr) {
        String a7 = pVar.a(true);
        if (a7 == null) {
            ConcurrentHashMap concurrentHashMap = c2.a.f3034e;
            throw new NullPointerException("descriptor == null");
        }
        ConcurrentHashMap concurrentHashMap2 = c2.a.f3034e;
        c2.a aVar = (c2.a) concurrentHashMap2.get(a7);
        if (aVar == null) {
            c2.a b7 = c2.a.b(a7);
            c2.a aVar2 = (c2.a) concurrentHashMap2.putIfAbsent(b7.f3035a, b7);
            aVar = aVar2 != null ? aVar2 : b7;
        }
        a2.p pVar2 = r.NOP;
        h(new a2.p(51, aVar.c(), c2.b.THROWABLE), pVar, oVar, oVar2, oVarArr);
    }

    public final void j(u1.o oVar, Object obj) {
        a2.p pVar;
        if (obj == null) {
            pVar = r.CONST_OBJECT_NOTHROW;
        } else {
            c2.c cVar = oVar.f9293b.f9303b;
            a2.p pVar2 = r.NOP;
            cVar.getClass();
            if (cVar == c2.c.KNOWN_NULL) {
                pVar = r.CONST_OBJECT_NOTHROW;
            } else {
                int a7 = cVar.a();
                if (a7 == 4) {
                    pVar = r.CONST_DOUBLE;
                } else if (a7 == 5) {
                    pVar = r.CONST_FLOAT;
                } else if (a7 == 6) {
                    pVar = r.CONST_INT;
                } else if (a7 == 7) {
                    pVar = r.CONST_LONG;
                } else {
                    if (a7 != 9) {
                        r.b(cVar);
                        throw null;
                    }
                    pVar = r.CONST_OBJECT;
                }
            }
        }
        a2.p pVar3 = pVar;
        int i7 = pVar3.f245e;
        Object obj2 = this.f6667h;
        if (i7 == 1) {
            a(new a2.i(pVar3, (s) obj2, oVar.a(), a2.n.EMPTY, z.f0(obj)), null);
        } else {
            a(new t(pVar3, (s) obj2, a2.n.EMPTY, (c2.b) this.f6669j, z.f0(obj)), null);
            k(oVar, true);
        }
    }

    public final void k(u1.o oVar, boolean z6) {
        a2.p pVar;
        if (z6) {
            c2.c cVar = oVar.f9293b.f9303b;
            a2.p pVar2 = r.NOP;
            cVar.getClass();
            pVar = new a2.p(56, cVar, c2.b.EMPTY, null);
        } else {
            c2.c cVar2 = oVar.f9293b.f9303b;
            a2.p pVar3 = r.NOP;
            cVar2.getClass();
            pVar = new a2.p(55, cVar2, c2.b.EMPTY, null);
        }
        a(new a2.j(pVar, (s) this.f6667h, oVar.a(), a2.n.EMPTY), null);
    }

    public final u1.o l(q qVar) {
        if (this.f6660a) {
            throw new IllegalStateException("Cannot allocate locals after adding instructions");
        }
        u1.o oVar = new u1.o(this, qVar);
        ((List) this.f6666g).add(oVar);
        return oVar;
    }

    public final FileInputStream m(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e7) {
            String message = e7.getMessage();
            if (message != null && message.contains("compressed")) {
                ((e) this.f6663d).j();
            }
            return null;
        }
    }

    public final void n(int i7, Serializable serializable) {
        ((Executor) this.f6662c).execute(new a(this, i7, serializable, 0));
    }

    public final void o(u1.o oVar) {
        a2.p pVar;
        q qVar = oVar.f9293b;
        u1.p pVar2 = (u1.p) this.f6661b;
        boolean equals = qVar.equals(pVar2.f9297b);
        q qVar2 = oVar.f9293b;
        if (!equals) {
            throw new IllegalArgumentException("declared " + pVar2.f9297b + " but returned " + qVar2);
        }
        c2.c cVar = qVar2.f9303b;
        a2.p pVar3 = r.NOP;
        int a7 = cVar.a();
        if (a7 == 0) {
            pVar = r.RETURN_VOID;
        } else if (a7 == 9) {
            pVar = r.RETURN_OBJECT;
        } else if (a7 == 4) {
            pVar = r.RETURN_DOUBLE;
        } else if (a7 == 5) {
            pVar = r.RETURN_FLOAT;
        } else if (a7 == 6) {
            pVar = r.RETURN_INT;
        } else {
            if (a7 != 7) {
                r.b(cVar);
                throw null;
            }
            pVar = r.RETURN_LONG;
        }
        a(new a2.j(pVar, (s) this.f6667h, null, a2.n.i(oVar.a())), null);
    }

    public final void p() {
        Object obj = this.f6661b;
        if (((u1.p) obj).f9297b.equals(q.VOID)) {
            a(new a2.j(r.RETURN_VOID, (s) this.f6667h, null, a2.n.EMPTY), null);
            return;
        }
        throw new IllegalArgumentException("declared " + ((u1.p) obj).f9297b + " but returned void");
    }
}
