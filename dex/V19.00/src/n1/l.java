package n1;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.q1;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class l extends q1 {
    @Override // androidx.fragment.app.q1
    public final void a(View view, Object obj) {
        if (obj != null) {
            ((r) obj).b(view);
        }
    }

    @Override // androidx.fragment.app.q1
    public final void b(Object obj, ArrayList arrayList) {
        r rVar = (r) obj;
        if (rVar == null) {
            return;
        }
        int i7 = 0;
        if (rVar instanceof w) {
            w wVar = (w) rVar;
            int size = wVar.f7679x.size();
            while (i7 < size) {
                b((i7 < 0 || i7 >= wVar.f7679x.size()) ? null : (r) wVar.f7679x.get(i7), arrayList);
                i7++;
            }
            return;
        }
        if (q1.h(rVar.f7657e) && q1.h(null) && q1.h(null) && q1.h(rVar.f7658f)) {
            int size2 = arrayList.size();
            while (i7 < size2) {
                rVar.b((View) arrayList.get(i7));
                i7++;
            }
        }
    }

    @Override // androidx.fragment.app.q1
    public final void c(ViewGroup viewGroup, Object obj) {
        u.a(viewGroup, (r) obj);
    }

    @Override // androidx.fragment.app.q1
    public final boolean e(Object obj) {
        return obj instanceof r;
    }

    @Override // androidx.fragment.app.q1
    public final Object f(Object obj) {
        if (obj != null) {
            return ((r) obj).clone();
        }
        return null;
    }

    @Override // androidx.fragment.app.q1
    public final Object i(Object obj, Object obj2, Object obj3) {
        r rVar = (r) obj;
        r rVar2 = (r) obj2;
        r rVar3 = (r) obj3;
        if (rVar != null && rVar2 != null) {
            w wVar = new w();
            wVar.H(rVar);
            wVar.H(rVar2);
            wVar.K(1);
            rVar = wVar;
        } else if (rVar == null) {
            rVar = rVar2 != null ? rVar2 : null;
        }
        if (rVar3 == null) {
            return rVar;
        }
        w wVar2 = new w();
        if (rVar != null) {
            wVar2.H(rVar);
        }
        wVar2.H(rVar3);
        return wVar2;
    }

    @Override // androidx.fragment.app.q1
    public final Object j(Object obj, Object obj2, Object obj3) {
        w wVar = new w();
        if (obj != null) {
            wVar.H((r) obj);
        }
        if (obj2 != null) {
            wVar.H((r) obj2);
        }
        if (obj3 != null) {
            wVar.H((r) obj3);
        }
        return wVar;
    }

    @Override // androidx.fragment.app.q1
    public final void l(Object obj, View view, ArrayList arrayList) {
        ((r) obj).a(new j(view, arrayList));
    }

    @Override // androidx.fragment.app.q1
    public final void m(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2, Object obj4, ArrayList arrayList3) {
        ((r) obj).a(new k(this, obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // androidx.fragment.app.q1
    public final void n(View view, Object obj) {
        if (view != null) {
            q1.g(view, new Rect());
            ((r) obj).A(new i());
        }
    }

    @Override // androidx.fragment.app.q1
    public final void o(Object obj, Rect rect) {
        if (obj != null) {
            ((r) obj).A(new i());
        }
    }

    @Override // androidx.fragment.app.q1
    public final void r(Object obj, View view, ArrayList arrayList) {
        w wVar = (w) obj;
        ArrayList arrayList2 = wVar.f7658f;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            q1.d(arrayList2, (View) arrayList.get(i7));
        }
        arrayList2.add(view);
        arrayList.add(view);
        b(wVar, arrayList);
    }

    @Override // androidx.fragment.app.q1
    public final void s(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        w wVar = (w) obj;
        if (wVar != null) {
            ArrayList arrayList3 = wVar.f7658f;
            arrayList3.clear();
            arrayList3.addAll(arrayList2);
            u(wVar, arrayList, arrayList2);
        }
    }

    @Override // androidx.fragment.app.q1
    public final Object t(Object obj) {
        if (obj == null) {
            return null;
        }
        w wVar = new w();
        wVar.H((r) obj);
        return wVar;
    }

    public final void u(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        r rVar = (r) obj;
        int i7 = 0;
        if (rVar instanceof w) {
            w wVar = (w) rVar;
            int size = wVar.f7679x.size();
            while (i7 < size) {
                u((i7 < 0 || i7 >= wVar.f7679x.size()) ? null : (r) wVar.f7679x.get(i7), arrayList, arrayList2);
                i7++;
            }
            return;
        }
        if (q1.h(rVar.f7657e) && q1.h(null) && q1.h(null)) {
            ArrayList arrayList3 = rVar.f7658f;
            if (arrayList3.size() == arrayList.size() && arrayList3.containsAll(arrayList)) {
                int size2 = arrayList2 == null ? 0 : arrayList2.size();
                while (i7 < size2) {
                    rVar.b((View) arrayList2.get(i7));
                    i7++;
                }
                for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    rVar.w((View) arrayList.get(size3));
                }
            }
        }
    }
}
