package y1;

import com.android.dex.util.ExceptionWithContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class z extends c0 {

    /* renamed from: j, reason: collision with root package name */
    public static final c0.g f10116j = new c0.g(6);

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f10117f;

    /* renamed from: g, reason: collision with root package name */
    public final HashMap f10118g;

    /* renamed from: h, reason: collision with root package name */
    public final y f10119h;

    /* renamed from: i, reason: collision with root package name */
    public int f10120i;

    public z(String str, f fVar, int i7, y yVar) {
        super(str, fVar, i7);
        this.f10117f = new ArrayList(100);
        this.f10118g = new HashMap(100);
        this.f10119h = yVar;
        this.f10120i = -1;
    }

    @Override // y1.c0
    public final int a(p pVar) {
        return ((a0) pVar).f();
    }

    @Override // y1.c0
    public final Collection c() {
        return this.f10117f;
    }

    @Override // y1.c0
    public final void e() {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f10117f;
            int size = arrayList.size();
            if (i7 >= size) {
                return;
            }
            while (i7 < size) {
                ((a0) arrayList.get(i7)).a(this.f10052b);
                i7++;
            }
        }
    }

    @Override // y1.c0
    public final int h() {
        f();
        return this.f10120i;
    }

    @Override // y1.c0
    public final void j(e2.c cVar) {
        boolean d7 = cVar.d();
        Iterator it = this.f10117f.iterator();
        int i7 = 0;
        boolean z6 = true;
        while (it.hasNext()) {
            a0 a0Var = (a0) it.next();
            if (d7) {
                if (z6) {
                    z6 = false;
                } else {
                    cVar.b(0, "\n");
                }
            }
            int i8 = a0Var.f10033a - 1;
            int i9 = (~i8) & (i7 + i8);
            if (i7 != i9) {
                cVar.n(i9 - i7);
                i7 = i9;
            }
            a0Var.d(this.f10052b, cVar);
            i7 += a0Var.c();
        }
        if (i7 != this.f10120i) {
            throw new RuntimeException("output size mismatch");
        }
    }

    public final void k(a0 a0Var) {
        g();
        try {
            if (a0Var.f10033a > this.f10053c) {
                throw new IllegalArgumentException("incompatible item alignment");
            }
            this.f10117f.add(a0Var);
        } catch (NullPointerException unused) {
            throw new NullPointerException("item == null");
        }
    }

    public final synchronized a0 l(a0 a0Var) {
        g();
        a0 a0Var2 = (a0) this.f10118g.get(a0Var);
        if (a0Var2 != null) {
            return a0Var2;
        }
        k(a0Var);
        this.f10118g.put(a0Var, a0Var);
        return a0Var;
    }

    public final void m() {
        f();
        int i7 = x.f10114a[this.f10119h.ordinal()];
        ArrayList arrayList = this.f10117f;
        if (i7 == 1) {
            Collections.sort(arrayList);
        } else if (i7 == 2) {
            Collections.sort(arrayList, f10116j);
        }
        int size = arrayList.size();
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            a0 a0Var = (a0) arrayList.get(i9);
            try {
                int h7 = a0Var.h(this, i8);
                if (h7 < i8) {
                    throw new RuntimeException("bogus place() result for " + a0Var);
                }
                i8 = a0Var.c() + h7;
            } catch (RuntimeException e7) {
                throw ExceptionWithContext.a("...while placing " + a0Var, e7);
            }
        }
        this.f10120i = i8;
    }
}
