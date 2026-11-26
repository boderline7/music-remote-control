package j;

import a2.s;
import a2.t;
import a2.u;
import a6.k0;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashSet;
import k.f0;
import n1.z;
import w1.v;
import w1.x;
import w1.y;
import y0.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class h implements b, l0.c, a2.f {

    /* renamed from: e, reason: collision with root package name */
    public static h f6889e;

    /* renamed from: a, reason: collision with root package name */
    public Object f6890a;

    /* renamed from: b, reason: collision with root package name */
    public Object f6891b;

    /* renamed from: c, reason: collision with root package name */
    public Object f6892c;

    /* renamed from: d, reason: collision with root package name */
    public Object f6893d;

    public h(int i7) {
        if (i7 == 2) {
            this.f6890a = new s.e(10, 1);
            this.f6893d = new q.k();
            this.f6892c = new ArrayList();
            this.f6891b = new HashSet();
            return;
        }
        if (i7 == 5) {
            this.f6890a = new q.b();
            this.f6891b = new SparseArray();
            this.f6892c = new q.e();
            this.f6893d = new q.b();
            return;
        }
        if (i7 == 8) {
            this.f6892c = new ArrayList();
            this.f6890a = null;
            this.f6891b = null;
            this.f6893d = new androidx.appcompat.widget.d(6, this);
            return;
        }
        if (i7 == 9) {
            this.f6890a = new Object();
            this.f6891b = new Handler(Looper.getMainLooper(), new r3.c(this));
        } else {
            this.f6890a = new s.e(256, 0);
            this.f6891b = new s.e(256, 0);
            this.f6892c = new s.e(256, 0);
            this.f6893d = new s.i[32];
        }
    }

    public h(Typeface typeface, z0.b bVar) {
        int i7;
        int i8;
        this.f6893d = typeface;
        this.f6890a = bVar;
        this.f6892c = new w(1024);
        z0.b bVar2 = (z0.b) this.f6890a;
        int a7 = bVar2.a(6);
        if (a7 != 0) {
            int i9 = a7 + bVar2.f10244a;
            i7 = bVar2.f10245b.getInt(bVar2.f10245b.getInt(i9) + i9);
        } else {
            i7 = 0;
        }
        this.f6891b = new char[i7 * 2];
        z0.b bVar3 = (z0.b) this.f6890a;
        int a8 = bVar3.a(6);
        if (a8 != 0) {
            int i10 = a8 + bVar3.f10244a;
            i8 = bVar3.f10245b.getInt(bVar3.f10245b.getInt(i10) + i10);
        } else {
            i8 = 0;
        }
        for (int i11 = 0; i11 < i8; i11++) {
            y0.q qVar = new y0.q(this, i11);
            z0.a c7 = qVar.c();
            int a9 = c7.a(4);
            Character.toChars(a9 != 0 ? c7.f10245b.getInt(a9 + c7.f10244a) : 0, (char[]) this.f6891b, i11 * 2);
            z.t("invalid metadata codepoint length", qVar.b() > 0);
            ((w) this.f6892c).a(qVar, 0, qVar.b() - 1);
        }
    }

    public h(w1.w wVar, i6.i iVar) {
        this.f6893d = wVar;
        this.f6890a = iVar;
    }

    @Override // j.b
    public final boolean a(c cVar, MenuItem menuItem) {
        return ((ActionMode.Callback) this.f6890a).onActionItemClicked(m(cVar), new k.w((Context) this.f6891b, (k0.b) menuItem));
    }

    @Override // j.b
    public final boolean b(c cVar, k.o oVar) {
        return ((ActionMode.Callback) this.f6890a).onCreateActionMode(m(cVar), n(oVar));
    }

    @Override // a2.f
    public void c(a2.j jVar) {
        w1.h xVar;
        a2.p pVar = jVar.f228a;
        int i7 = pVar.f241a;
        if (i7 == 54 || i7 == 56) {
            return;
        }
        w1.j a7 = v.a(jVar);
        a2.m mVar = jVar.f230c;
        s sVar = jVar.f229b;
        int i8 = pVar.f245e;
        if (i8 != 1 && i8 != 2) {
            if (i8 == 3) {
                return;
            }
            if (i8 == 4) {
                xVar = new y(a7, sVar, w1.w.a(jVar, mVar), ((w1.e[]) ((w1.w) this.f6893d).f9448c.f714b)[((a2.b) this.f6891b).f223c.g(1)]);
                j(xVar);
            } else if (i8 != 6) {
                throw new RuntimeException("shouldn't happen");
            }
        }
        xVar = new x(a7, sVar, w1.w.a(jVar, mVar));
        j(xVar);
    }

    @Override // j.b
    public final void d(c cVar) {
        ((ActionMode.Callback) this.f6890a).onDestroyActionMode(m(cVar));
    }

    @Override // a2.f
    public void e(a2.i iVar) {
        s sVar = iVar.f229b;
        w1.j a7 = v.a(iVar);
        a2.p pVar = iVar.f228a;
        int i7 = pVar.f241a;
        if (pVar.f245e != 1) {
            throw new RuntimeException("shouldn't happen");
        }
        b2.a aVar = iVar.f227e;
        a2.m mVar = iVar.f230c;
        if (i7 != 3) {
            j(new w1.f(a7, sVar, w1.w.a(iVar, mVar), aVar));
            return;
        }
        w1.w wVar = (w1.w) this.f6893d;
        if (wVar.f9454i) {
            return;
        }
        j(new x(a7, sVar, a2.n.j(mVar, a2.m.f((wVar.f9451f - wVar.f9453h) + ((b2.k) aVar).f2934a, mVar.f238b.b()))));
    }

    @Override // a2.f
    public void f(t tVar) {
        s sVar = tVar.f229b;
        w1.j a7 = v.a(tVar);
        a2.p pVar = tVar.f228a;
        if (pVar.f245e != 6) {
            throw new RuntimeException("Expected BRANCH_THROW got " + pVar.f245e);
        }
        j((w1.e) this.f6892c);
        boolean z6 = pVar.f246f;
        b2.a aVar = tVar.f227e;
        if (z6) {
            j(new w1.f(a7, sVar, tVar.f231d, aVar));
            return;
        }
        a2.m o6 = o();
        a2.n a8 = w1.w.a(tVar, o6);
        boolean z7 = a7.f9435e;
        int i7 = pVar.f241a;
        if ((z7 || i7 == 43) == (o6 != null)) {
            j((i7 != 41 || a7.f9431a == 35) ? new w1.f(a7, sVar, a8, aVar) : new x(a7, sVar, a8));
        } else {
            throw new RuntimeException("Insn with result/move-result-pseudo mismatch " + tVar);
        }
    }

    @Override // a2.f
    public void g(u uVar) {
        s sVar = uVar.f229b;
        w1.j a7 = v.a(uVar);
        if (uVar.f228a.f245e != 6) {
            throw new RuntimeException("shouldn't happen");
        }
        a2.m o6 = o();
        if (a7.f9435e == (o6 != null)) {
            j((w1.e) this.f6892c);
            j(new x(a7, sVar, w1.w.a(uVar, o6)));
        } else {
            throw new RuntimeException("Insn with result/move-result-pseudo mismatch" + uVar);
        }
    }

    @Override // l0.c
    public final void h() {
        ((View) this.f6890a).clearAnimation();
        ((ViewGroup) this.f6891b).endViewTransition((View) this.f6890a);
        ((androidx.fragment.app.i) this.f6892c).a();
    }

    @Override // j.b
    public final boolean i(c cVar, k.o oVar) {
        return ((ActionMode.Callback) this.f6890a).onPrepareActionMode(m(cVar), n(oVar));
    }

    public final void j(w1.h hVar) {
        ((w1.t) ((i6.i) this.f6890a).f6848b).a(hVar);
    }

    public final void k(int[] iArr, ValueAnimator valueAnimator) {
        k0 k0Var = new k0(iArr, valueAnimator);
        valueAnimator.addListener((Animator.AnimatorListener) this.f6893d);
        ((ArrayList) this.f6892c).add(k0Var);
    }

    public final void l(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) ((q.k) this.f6893d).getOrDefault(obj, null);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i7 = 0; i7 < size; i7++) {
                l(arrayList2.get(i7), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    public final i m(c cVar) {
        int size = ((ArrayList) this.f6892c).size();
        for (int i7 = 0; i7 < size; i7++) {
            i iVar = (i) ((ArrayList) this.f6892c).get(i7);
            if (iVar != null && iVar.f6895b == cVar) {
                return iVar;
            }
        }
        i iVar2 = new i((Context) this.f6891b, cVar);
        ((ArrayList) this.f6892c).add(iVar2);
        return iVar2;
    }

    public final Menu n(k.o oVar) {
        Menu menu = (Menu) ((q.k) this.f6893d).getOrDefault(oVar, null);
        if (menu != null) {
            return menu;
        }
        f0 f0Var = new f0((Context) this.f6891b, oVar);
        ((q.k) this.f6893d).put(oVar, f0Var);
        return f0Var;
    }

    public final a2.m o() {
        int i7 = ((a2.b) this.f6891b).f224d;
        if (i7 < 0) {
            return null;
        }
        a2.g gVar = (a2.g) ((a2.c) ((w1.w) this.f6893d).f9446a.f249b).j(i7).f222b.f(0);
        if (gVar.f228a.f241a != 56) {
            return null;
        }
        return gVar.f230c;
    }
}
