package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2497a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f2498b;

    /* renamed from: c, reason: collision with root package name */
    public final Object f2499c;

    /* renamed from: d, reason: collision with root package name */
    public final Object f2500d;

    public j(n1 n1Var) {
        this.f2497a = 0;
        this.f2498b = n1Var;
        this.f2499c = new i(0);
        this.f2500d = new ArrayList();
    }

    public j(Executor executor, t tVar) {
        this.f2497a = 1;
        this.f2498b = null;
        this.f2499c = executor;
        this.f2500d = tVar;
    }

    public final void a(View view, int i7, boolean z6) {
        Object obj = this.f2498b;
        int childCount = i7 < 0 ? ((n1) obj).f2551a.getChildCount() : f(i7);
        ((i) this.f2499c).f(childCount, z6);
        if (z6) {
            i(view);
        }
        RecyclerView recyclerView = ((n1) obj).f2551a;
        recyclerView.addView(view, childCount);
        j2 K = RecyclerView.K(view);
        h1 h1Var = recyclerView.f2319l;
        if (h1Var != null && K != null) {
            h1Var.onViewAttachedToWindow(K);
        }
        ArrayList arrayList = recyclerView.A;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((t1) recyclerView.A.get(size)).b(view);
            }
        }
    }

    public final void b(View view, int i7, ViewGroup.LayoutParams layoutParams, boolean z6) {
        Object obj = this.f2498b;
        int childCount = i7 < 0 ? ((n1) obj).f2551a.getChildCount() : f(i7);
        ((i) this.f2499c).f(childCount, z6);
        if (z6) {
            i(view);
        }
        n1 n1Var = (n1) obj;
        n1Var.getClass();
        j2 K = RecyclerView.K(view);
        RecyclerView recyclerView = n1Var.f2551a;
        if (K != null) {
            if (!K.isTmpDetached() && !K.shouldIgnore()) {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + K + recyclerView.z());
            }
            K.clearTmpDetachFlag();
        }
        recyclerView.attachViewToParent(view, childCount, layoutParams);
    }

    public final void c(int i7) {
        j2 K;
        int f7 = f(i7);
        ((i) this.f2499c).g(f7);
        RecyclerView recyclerView = ((n1) this.f2498b).f2551a;
        View childAt = recyclerView.getChildAt(f7);
        if (childAt != null && (K = RecyclerView.K(childAt)) != null) {
            if (K.isTmpDetached() && !K.shouldIgnore()) {
                throw new IllegalArgumentException("called detach on an already detached child " + K + recyclerView.z());
            }
            K.addFlags(256);
        }
        recyclerView.detachViewFromParent(f7);
    }

    public final View d(int i7) {
        return ((n1) this.f2498b).f2551a.getChildAt(f(i7));
    }

    public final int e() {
        return ((n1) this.f2498b).f2551a.getChildCount() - ((List) this.f2500d).size();
    }

    public final int f(int i7) {
        if (i7 < 0) {
            return -1;
        }
        int childCount = ((n1) this.f2498b).f2551a.getChildCount();
        int i8 = i7;
        while (i8 < childCount) {
            Object obj = this.f2499c;
            int b7 = i7 - (i8 - ((i) obj).b(i8));
            if (b7 == 0) {
                while (((i) obj).e(i8)) {
                    i8++;
                }
                return i8;
            }
            i8 += b7;
        }
        return -1;
    }

    public final View g(int i7) {
        return ((n1) this.f2498b).f2551a.getChildAt(i7);
    }

    public final int h() {
        return ((n1) this.f2498b).f2551a.getChildCount();
    }

    public final void i(View view) {
        ((List) this.f2500d).add(view);
        n1 n1Var = (n1) this.f2498b;
        n1Var.getClass();
        j2 K = RecyclerView.K(view);
        if (K != null) {
            K.onEnteredHiddenState(n1Var.f2551a);
        }
    }

    public final int j(View view) {
        int indexOfChild = ((n1) this.f2498b).f2551a.indexOfChild(view);
        if (indexOfChild == -1) {
            return -1;
        }
        Object obj = this.f2499c;
        if (((i) obj).e(indexOfChild)) {
            return -1;
        }
        return indexOfChild - ((i) obj).b(indexOfChild);
    }

    public final boolean k(View view) {
        return ((List) this.f2500d).contains(view);
    }

    public final void l(View view) {
        if (((List) this.f2500d).remove(view)) {
            n1 n1Var = (n1) this.f2498b;
            n1Var.getClass();
            j2 K = RecyclerView.K(view);
            if (K != null) {
                K.onLeftHiddenState(n1Var.f2551a);
            }
        }
    }

    public final String toString() {
        switch (this.f2497a) {
            case 0:
                return ((i) this.f2499c).toString() + ", hidden list:" + ((List) this.f2500d).size();
            default:
                return super.toString();
        }
    }
}
