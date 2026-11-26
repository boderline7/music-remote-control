package androidx.fragment.app;

import android.util.Log;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class y1 {

    /* renamed from: a, reason: collision with root package name */
    public final ViewGroup f2129a;

    /* renamed from: b, reason: collision with root package name */
    public final ArrayList f2130b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f2131c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    public boolean f2132d = false;

    /* renamed from: e, reason: collision with root package name */
    public boolean f2133e = false;

    public y1(ViewGroup viewGroup) {
        this.f2129a = viewGroup;
    }

    public static y1 f(ViewGroup viewGroup, p0 p0Var) {
        Object tag = viewGroup.getTag(b1.b.special_effects_controller_view_tag);
        if (tag instanceof y1) {
            return (y1) tag;
        }
        p0Var.getClass();
        l lVar = new l(viewGroup);
        viewGroup.setTag(b1.b.special_effects_controller_view_tag, lVar);
        return lVar;
    }

    public static y1 g(ViewGroup viewGroup, x0 x0Var) {
        return f(viewGroup, x0Var.z());
    }

    public final void a(x1 x1Var, w1 w1Var, d1 d1Var) {
        synchronized (this.f2130b) {
            try {
                l0.d dVar = new l0.d();
                v1 d7 = d(d1Var.f1933c);
                if (d7 != null) {
                    d7.c(x1Var, w1Var);
                    return;
                }
                v1 v1Var = new v1(x1Var, w1Var, d1Var, dVar);
                this.f2130b.add(v1Var);
                v1Var.f2073d.add(new t1(this, v1Var, 0));
                v1Var.f2073d.add(new t1(this, v1Var, 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract void b(ArrayList arrayList, boolean z6);

    public final void c() {
        if (this.f2133e) {
            return;
        }
        ViewGroup viewGroup = this.f2129a;
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        if (!p0.o0.b(viewGroup)) {
            e();
            this.f2132d = false;
            return;
        }
        synchronized (this.f2130b) {
            try {
                if (!this.f2130b.isEmpty()) {
                    ArrayList arrayList = new ArrayList(this.f2131c);
                    this.f2131c.clear();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        v1 v1Var = (v1) it.next();
                        if (Log.isLoggable("FragmentManager", 2)) {
                            Objects.toString(v1Var);
                        }
                        v1Var.a();
                        if (!v1Var.f2076g) {
                            this.f2131c.add(v1Var);
                        }
                    }
                    i();
                    ArrayList arrayList2 = new ArrayList(this.f2130b);
                    this.f2130b.clear();
                    this.f2131c.addAll(arrayList2);
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        ((v1) it2.next()).d();
                    }
                    b(arrayList2, this.f2132d);
                    this.f2132d = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final v1 d(z zVar) {
        Iterator it = this.f2130b.iterator();
        while (it.hasNext()) {
            v1 v1Var = (v1) it.next();
            if (v1Var.f2072c.equals(zVar) && !v1Var.f2075f) {
                return v1Var;
            }
        }
        return null;
    }

    public final void e() {
        ViewGroup viewGroup = this.f2129a;
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        boolean b7 = p0.o0.b(viewGroup);
        synchronized (this.f2130b) {
            try {
                i();
                Iterator it = this.f2130b.iterator();
                while (it.hasNext()) {
                    ((v1) it.next()).d();
                }
                Iterator it2 = new ArrayList(this.f2131c).iterator();
                while (it2.hasNext()) {
                    v1 v1Var = (v1) it2.next();
                    if (Log.isLoggable("FragmentManager", 2)) {
                        if (!b7) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Container ");
                            sb.append(this.f2129a);
                            sb.append(" is not attached to window. ");
                        }
                        Objects.toString(v1Var);
                    }
                    v1Var.a();
                }
                Iterator it3 = new ArrayList(this.f2130b).iterator();
                while (it3.hasNext()) {
                    v1 v1Var2 = (v1) it3.next();
                    if (Log.isLoggable("FragmentManager", 2)) {
                        if (!b7) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Container ");
                            sb2.append(this.f2129a);
                            sb2.append(" is not attached to window. ");
                        }
                        Objects.toString(v1Var2);
                    }
                    v1Var2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void h() {
        synchronized (this.f2130b) {
            try {
                i();
                this.f2133e = false;
                int size = this.f2130b.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    v1 v1Var = (v1) this.f2130b.get(size);
                    x1 c7 = x1.c(v1Var.f2072c.mView);
                    x1 x1Var = v1Var.f2070a;
                    x1 x1Var2 = x1.VISIBLE;
                    if (x1Var == x1Var2 && c7 != x1Var2) {
                        this.f2133e = v1Var.f2072c.isPostponed();
                        break;
                    }
                    size--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void i() {
        Iterator it = this.f2130b.iterator();
        while (it.hasNext()) {
            v1 v1Var = (v1) it.next();
            if (v1Var.f2071b == w1.ADDING) {
                v1Var.c(x1.b(v1Var.f2072c.requireView().getVisibility()), w1.NONE);
            }
        }
    }
}
