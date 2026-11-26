package n1;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import p0.d1;
import p0.r0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class r implements Cloneable {
    public static final int MATCH_ID = 3;
    public static final int MATCH_INSTANCE = 1;
    public static final int MATCH_ITEM_ID = 4;
    public static final int MATCH_NAME = 2;

    /* renamed from: u, reason: collision with root package name */
    public static final int[] f7650u = {2, 1, 3, 4};

    /* renamed from: v, reason: collision with root package name */
    public static final p3.f f7651v = new p3.f(18);

    /* renamed from: w, reason: collision with root package name */
    public static final ThreadLocal f7652w = new ThreadLocal();

    /* renamed from: k, reason: collision with root package name */
    public ArrayList f7663k;

    /* renamed from: l, reason: collision with root package name */
    public ArrayList f7664l;

    /* renamed from: s, reason: collision with root package name */
    public l6.f f7671s;

    /* renamed from: a, reason: collision with root package name */
    public final String f7653a = getClass().getName();

    /* renamed from: b, reason: collision with root package name */
    public long f7654b = -1;

    /* renamed from: c, reason: collision with root package name */
    public long f7655c = -1;

    /* renamed from: d, reason: collision with root package name */
    public TimeInterpolator f7656d = null;

    /* renamed from: e, reason: collision with root package name */
    public final ArrayList f7657e = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    public final ArrayList f7658f = new ArrayList();

    /* renamed from: g, reason: collision with root package name */
    public j.h f7659g = new j.h(5);

    /* renamed from: h, reason: collision with root package name */
    public j.h f7660h = new j.h(5);

    /* renamed from: i, reason: collision with root package name */
    public w f7661i = null;

    /* renamed from: j, reason: collision with root package name */
    public final int[] f7662j = f7650u;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f7665m = new ArrayList();

    /* renamed from: n, reason: collision with root package name */
    public int f7666n = 0;

    /* renamed from: o, reason: collision with root package name */
    public boolean f7667o = false;

    /* renamed from: p, reason: collision with root package name */
    public boolean f7668p = false;

    /* renamed from: q, reason: collision with root package name */
    public ArrayList f7669q = null;

    /* renamed from: r, reason: collision with root package name */
    public ArrayList f7670r = new ArrayList();

    /* renamed from: t, reason: collision with root package name */
    public p3.f f7672t = f7651v;

    public static void c(j.h hVar, View view, y yVar) {
        ((q.b) hVar.f6890a).put(view, yVar);
        int id = view.getId();
        if (id >= 0) {
            if (((SparseArray) hVar.f6891b).indexOfKey(id) >= 0) {
                ((SparseArray) hVar.f6891b).put(id, null);
            } else {
                ((SparseArray) hVar.f6891b).put(id, view);
            }
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        String k7 = r0.k(view);
        if (k7 != null) {
            if (((q.b) hVar.f6893d).containsKey(k7)) {
                ((q.b) hVar.f6893d).put(k7, null);
            } else {
                ((q.b) hVar.f6893d).put(k7, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                q.e eVar = (q.e) hVar.f6892c;
                if (eVar.f8385a) {
                    eVar.c();
                }
                if (q.d.b(eVar.f8386b, eVar.f8388d, itemIdAtPosition) < 0) {
                    p0.l0.r(view, true);
                    ((q.e) hVar.f6892c).f(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) ((q.e) hVar.f6892c).d(itemIdAtPosition, null);
                if (view2 != null) {
                    p0.l0.r(view2, false);
                    ((q.e) hVar.f6892c).f(itemIdAtPosition, null);
                }
            }
        }
    }

    public static q.b o() {
        ThreadLocal threadLocal = f7652w;
        q.b bVar = (q.b) threadLocal.get();
        if (bVar != null) {
            return bVar;
        }
        q.b bVar2 = new q.b();
        threadLocal.set(bVar2);
        return bVar2;
    }

    public static boolean t(y yVar, y yVar2, String str) {
        Object obj = yVar.f7685a.get(str);
        Object obj2 = yVar2.f7685a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    public void A(l6.f fVar) {
        this.f7671s = fVar;
    }

    public void B(TimeInterpolator timeInterpolator) {
        this.f7656d = timeInterpolator;
    }

    public void C(p3.f fVar) {
        if (fVar == null) {
            this.f7672t = f7651v;
        } else {
            this.f7672t = fVar;
        }
    }

    public void D() {
    }

    public void E(long j7) {
        this.f7654b = j7;
    }

    public final void F() {
        if (this.f7666n == 0) {
            ArrayList arrayList = this.f7669q;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.f7669q.clone();
                int size = arrayList2.size();
                for (int i7 = 0; i7 < size; i7++) {
                    ((q) arrayList2.get(i7)).b();
                }
            }
            this.f7668p = false;
        }
        this.f7666n++;
    }

    public String G(String str) {
        StringBuilder j7 = a.a.j(str);
        j7.append(getClass().getSimpleName());
        j7.append("@");
        j7.append(Integer.toHexString(hashCode()));
        j7.append(": ");
        String sb = j7.toString();
        if (this.f7655c != -1) {
            sb = sb + "dur(" + this.f7655c + ") ";
        }
        if (this.f7654b != -1) {
            sb = sb + "dly(" + this.f7654b + ") ";
        }
        if (this.f7656d != null) {
            sb = sb + "interp(" + this.f7656d + ") ";
        }
        ArrayList arrayList = this.f7657e;
        int size = arrayList.size();
        ArrayList arrayList2 = this.f7658f;
        if (size <= 0 && arrayList2.size() <= 0) {
            return sb;
        }
        String g7 = a.a.g(sb, "tgts(");
        if (arrayList.size() > 0) {
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                if (i7 > 0) {
                    g7 = a.a.g(g7, ", ");
                }
                StringBuilder j8 = a.a.j(g7);
                j8.append(arrayList.get(i7));
                g7 = j8.toString();
            }
        }
        if (arrayList2.size() > 0) {
            for (int i8 = 0; i8 < arrayList2.size(); i8++) {
                if (i8 > 0) {
                    g7 = a.a.g(g7, ", ");
                }
                StringBuilder j9 = a.a.j(g7);
                j9.append(arrayList2.get(i8));
                g7 = j9.toString();
            }
        }
        return a.a.g(g7, ")");
    }

    public void a(q qVar) {
        if (this.f7669q == null) {
            this.f7669q = new ArrayList();
        }
        this.f7669q.add(qVar);
    }

    public void b(View view) {
        this.f7658f.add(view);
    }

    public abstract void d(y yVar);

    public final void e(View view, boolean z6) {
        if (view == null) {
            return;
        }
        view.getId();
        if (view.getParent() instanceof ViewGroup) {
            y yVar = new y(view);
            if (z6) {
                g(yVar);
            } else {
                d(yVar);
            }
            yVar.f7687c.add(this);
            f(yVar);
            if (z6) {
                c(this.f7659g, view, yVar);
            } else {
                c(this.f7660h, view, yVar);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                e(viewGroup.getChildAt(i7), z6);
            }
        }
    }

    public void f(y yVar) {
    }

    public abstract void g(y yVar);

    public final void h(ViewGroup viewGroup, boolean z6) {
        i(z6);
        ArrayList arrayList = this.f7657e;
        int size = arrayList.size();
        ArrayList arrayList2 = this.f7658f;
        if (size <= 0 && arrayList2.size() <= 0) {
            e(viewGroup, z6);
            return;
        }
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            View findViewById = viewGroup.findViewById(((Integer) arrayList.get(i7)).intValue());
            if (findViewById != null) {
                y yVar = new y(findViewById);
                if (z6) {
                    g(yVar);
                } else {
                    d(yVar);
                }
                yVar.f7687c.add(this);
                f(yVar);
                if (z6) {
                    c(this.f7659g, findViewById, yVar);
                } else {
                    c(this.f7660h, findViewById, yVar);
                }
            }
        }
        for (int i8 = 0; i8 < arrayList2.size(); i8++) {
            View view = (View) arrayList2.get(i8);
            y yVar2 = new y(view);
            if (z6) {
                g(yVar2);
            } else {
                d(yVar2);
            }
            yVar2.f7687c.add(this);
            f(yVar2);
            if (z6) {
                c(this.f7659g, view, yVar2);
            } else {
                c(this.f7660h, view, yVar2);
            }
        }
    }

    public final void i(boolean z6) {
        if (z6) {
            ((q.b) this.f7659g.f6890a).clear();
            ((SparseArray) this.f7659g.f6891b).clear();
            ((q.e) this.f7659g.f6892c).a();
        } else {
            ((q.b) this.f7660h.f6890a).clear();
            ((SparseArray) this.f7660h.f6891b).clear();
            ((q.e) this.f7660h.f6892c).a();
        }
    }

    @Override // 
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public r clone() {
        try {
            r rVar = (r) super.clone();
            rVar.f7670r = new ArrayList();
            rVar.f7659g = new j.h(5);
            rVar.f7660h = new j.h(5);
            rVar.f7663k = null;
            rVar.f7664l = null;
            return rVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator k(ViewGroup viewGroup, y yVar, y yVar2) {
        return null;
    }

    public void l(ViewGroup viewGroup, j.h hVar, j.h hVar2, ArrayList arrayList, ArrayList arrayList2) {
        Animator k7;
        int i7;
        View view;
        y yVar;
        Animator animator;
        q.b o6 = o();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        int i8 = 0;
        while (i8 < size) {
            y yVar2 = (y) arrayList.get(i8);
            y yVar3 = (y) arrayList2.get(i8);
            y yVar4 = null;
            if (yVar2 != null && !yVar2.f7687c.contains(this)) {
                yVar2 = null;
            }
            if (yVar3 != null && !yVar3.f7687c.contains(this)) {
                yVar3 = null;
            }
            if (!(yVar2 == null && yVar3 == null) && ((yVar2 == null || yVar3 == null || r(yVar2, yVar3)) && (k7 = k(viewGroup, yVar2, yVar3)) != null)) {
                String str = this.f7653a;
                if (yVar3 != null) {
                    String[] p6 = p();
                    view = yVar3.f7686b;
                    if (p6 != null && p6.length > 0) {
                        yVar = new y(view);
                        y yVar5 = (y) ((q.b) hVar2.f6890a).getOrDefault(view, null);
                        i7 = size;
                        if (yVar5 != null) {
                            int i9 = 0;
                            while (i9 < p6.length) {
                                HashMap hashMap = yVar.f7685a;
                                String str2 = p6[i9];
                                hashMap.put(str2, yVar5.f7685a.get(str2));
                                i9++;
                                p6 = p6;
                            }
                        }
                        int i10 = o6.f8412c;
                        for (int i11 = 0; i11 < i10; i11++) {
                            animator = null;
                            p pVar = (p) o6.getOrDefault((Animator) o6.h(i11), null);
                            if (pVar.f7647c != null && pVar.f7645a == view && pVar.f7646b.equals(str) && pVar.f7647c.equals(yVar)) {
                                break;
                            }
                        }
                    } else {
                        i7 = size;
                        yVar = null;
                    }
                    animator = k7;
                    k7 = animator;
                    yVar4 = yVar;
                } else {
                    i7 = size;
                    view = yVar2.f7686b;
                }
                if (k7 != null) {
                    g0 g0Var = b0.f7590a;
                    m0 m0Var = new m0(viewGroup);
                    p pVar2 = new p();
                    pVar2.f7645a = view;
                    pVar2.f7646b = str;
                    pVar2.f7647c = yVar4;
                    pVar2.f7648d = m0Var;
                    pVar2.f7649e = this;
                    o6.put(k7, pVar2);
                    this.f7670r.add(k7);
                }
            } else {
                i7 = size;
            }
            i8++;
            size = i7;
        }
        if (sparseIntArray.size() != 0) {
            for (int i12 = 0; i12 < sparseIntArray.size(); i12++) {
                Animator animator2 = (Animator) this.f7670r.get(sparseIntArray.keyAt(i12));
                animator2.setStartDelay(animator2.getStartDelay() + (sparseIntArray.valueAt(i12) - Long.MAX_VALUE));
            }
        }
    }

    public final void m() {
        int i7 = this.f7666n - 1;
        this.f7666n = i7;
        if (i7 == 0) {
            ArrayList arrayList = this.f7669q;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.f7669q.clone();
                int size = arrayList2.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((q) arrayList2.get(i8)).d(this);
                }
            }
            for (int i9 = 0; i9 < ((q.e) this.f7659g.f6892c).h(); i9++) {
                View view = (View) ((q.e) this.f7659g.f6892c).i(i9);
                if (view != null) {
                    int i10 = d1.OVER_SCROLL_ALWAYS;
                    p0.l0.r(view, false);
                }
            }
            for (int i11 = 0; i11 < ((q.e) this.f7660h.f6892c).h(); i11++) {
                View view2 = (View) ((q.e) this.f7660h.f6892c).i(i11);
                if (view2 != null) {
                    int i12 = d1.OVER_SCROLL_ALWAYS;
                    p0.l0.r(view2, false);
                }
            }
            this.f7668p = true;
        }
    }

    public final y n(View view, boolean z6) {
        w wVar = this.f7661i;
        if (wVar != null) {
            return wVar.n(view, z6);
        }
        ArrayList arrayList = z6 ? this.f7663k : this.f7664l;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                i7 = -1;
                break;
            }
            y yVar = (y) arrayList.get(i7);
            if (yVar == null) {
                return null;
            }
            if (yVar.f7686b == view) {
                break;
            }
            i7++;
        }
        if (i7 >= 0) {
            return (y) (z6 ? this.f7664l : this.f7663k).get(i7);
        }
        return null;
    }

    public String[] p() {
        return null;
    }

    public final y q(View view, boolean z6) {
        w wVar = this.f7661i;
        if (wVar != null) {
            return wVar.q(view, z6);
        }
        return (y) ((q.b) (z6 ? this.f7659g : this.f7660h).f6890a).getOrDefault(view, null);
    }

    public boolean r(y yVar, y yVar2) {
        if (yVar == null || yVar2 == null) {
            return false;
        }
        String[] p6 = p();
        if (p6 == null) {
            Iterator it = yVar.f7685a.keySet().iterator();
            while (it.hasNext()) {
                if (t(yVar, yVar2, (String) it.next())) {
                }
            }
            return false;
        }
        for (String str : p6) {
            if (!t(yVar, yVar2, str)) {
            }
        }
        return false;
        return true;
    }

    public final boolean s(View view) {
        int id = view.getId();
        ArrayList arrayList = this.f7657e;
        int size = arrayList.size();
        ArrayList arrayList2 = this.f7658f;
        return (size == 0 && arrayList2.size() == 0) || arrayList.contains(Integer.valueOf(id)) || arrayList2.contains(view);
    }

    public final String toString() {
        return G(s5.h0.FRAGMENT_ENCODE_SET);
    }

    public void u(View view) {
        if (this.f7668p) {
            return;
        }
        q.b o6 = o();
        int i7 = o6.f8412c;
        g0 g0Var = b0.f7590a;
        WindowId windowId = view.getWindowId();
        for (int i8 = i7 - 1; i8 >= 0; i8--) {
            p pVar = (p) o6.j(i8);
            if (pVar.f7645a != null) {
                n0 n0Var = pVar.f7648d;
                if ((n0Var instanceof m0) && ((m0) n0Var).f7639a.equals(windowId)) {
                    ((Animator) o6.h(i8)).pause();
                }
            }
        }
        ArrayList arrayList = this.f7669q;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.f7669q.clone();
            int size = arrayList2.size();
            for (int i9 = 0; i9 < size; i9++) {
                ((q) arrayList2.get(i9)).c();
            }
        }
        this.f7667o = true;
    }

    public void v(q qVar) {
        ArrayList arrayList = this.f7669q;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(qVar);
        if (this.f7669q.size() == 0) {
            this.f7669q = null;
        }
    }

    public void w(View view) {
        this.f7658f.remove(view);
    }

    public void x(ViewGroup viewGroup) {
        if (this.f7667o) {
            if (!this.f7668p) {
                q.b o6 = o();
                int i7 = o6.f8412c;
                g0 g0Var = b0.f7590a;
                WindowId windowId = viewGroup.getWindowId();
                for (int i8 = i7 - 1; i8 >= 0; i8--) {
                    p pVar = (p) o6.j(i8);
                    if (pVar.f7645a != null) {
                        n0 n0Var = pVar.f7648d;
                        if ((n0Var instanceof m0) && ((m0) n0Var).f7639a.equals(windowId)) {
                            ((Animator) o6.h(i8)).resume();
                        }
                    }
                }
                ArrayList arrayList = this.f7669q;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.f7669q.clone();
                    int size = arrayList2.size();
                    for (int i9 = 0; i9 < size; i9++) {
                        ((q) arrayList2.get(i9)).e();
                    }
                }
            }
            this.f7667o = false;
        }
    }

    public void y() {
        F();
        q.b o6 = o();
        Iterator it = this.f7670r.iterator();
        while (it.hasNext()) {
            Animator animator = (Animator) it.next();
            if (o6.containsKey(animator)) {
                F();
                if (animator != null) {
                    animator.addListener(new o(0, this, o6));
                    long j7 = this.f7655c;
                    if (j7 >= 0) {
                        animator.setDuration(j7);
                    }
                    long j8 = this.f7654b;
                    if (j8 >= 0) {
                        animator.setStartDelay(animator.getStartDelay() + j8);
                    }
                    TimeInterpolator timeInterpolator = this.f7656d;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new androidx.appcompat.widget.d(1, this));
                    animator.start();
                }
            }
        }
        this.f7670r.clear();
        m();
    }

    public void z(long j7) {
        this.f7655c = j7;
    }
}
