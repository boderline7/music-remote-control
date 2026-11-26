package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s2 {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f2656a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    public int f2657b = Integer.MIN_VALUE;

    /* renamed from: c, reason: collision with root package name */
    public int f2658c = Integer.MIN_VALUE;

    /* renamed from: d, reason: collision with root package name */
    public int f2659d = 0;

    /* renamed from: e, reason: collision with root package name */
    public final int f2660e;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ StaggeredGridLayoutManager f2661f;

    public s2(StaggeredGridLayoutManager staggeredGridLayoutManager, int i7) {
        this.f2661f = staggeredGridLayoutManager;
        this.f2660e = i7;
    }

    public final void a() {
        View view = (View) this.f2656a.get(r0.size() - 1);
        o2 o2Var = (o2) view.getLayoutParams();
        this.f2658c = this.f2661f.f2348r.b(view);
        o2Var.getClass();
    }

    public final void b() {
        this.f2656a.clear();
        this.f2657b = Integer.MIN_VALUE;
        this.f2658c = Integer.MIN_VALUE;
        this.f2659d = 0;
    }

    public final int c() {
        return this.f2661f.f2353w ? e(r1.size() - 1, -1) : e(0, this.f2656a.size());
    }

    public final int d() {
        return this.f2661f.f2353w ? e(0, this.f2656a.size()) : e(r1.size() - 1, -1);
    }

    public final int e(int i7, int i8) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f2661f;
        int f7 = staggeredGridLayoutManager.f2348r.f();
        int e7 = staggeredGridLayoutManager.f2348r.e();
        int i9 = i8 > i7 ? 1 : -1;
        while (i7 != i8) {
            View view = (View) this.f2656a.get(i7);
            int d7 = staggeredGridLayoutManager.f2348r.d(view);
            int b7 = staggeredGridLayoutManager.f2348r.b(view);
            boolean z6 = d7 <= e7;
            boolean z7 = b7 >= f7;
            if (z6 && z7 && (d7 < f7 || b7 > e7)) {
                return r1.K(view);
            }
            i7 += i9;
        }
        return -1;
    }

    public final int f(int i7) {
        int i8 = this.f2658c;
        if (i8 != Integer.MIN_VALUE) {
            return i8;
        }
        if (this.f2656a.size() == 0) {
            return i7;
        }
        a();
        return this.f2658c;
    }

    public final View g(int i7, int i8) {
        ArrayList arrayList = this.f2656a;
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f2661f;
        View view = null;
        if (i8 != -1) {
            int size = arrayList.size() - 1;
            while (size >= 0) {
                View view2 = (View) arrayList.get(size);
                if ((staggeredGridLayoutManager.f2353w && r1.K(view2) >= i7) || ((!staggeredGridLayoutManager.f2353w && r1.K(view2) <= i7) || !view2.hasFocusable())) {
                    break;
                }
                size--;
                view = view2;
            }
        } else {
            int size2 = arrayList.size();
            int i9 = 0;
            while (i9 < size2) {
                View view3 = (View) arrayList.get(i9);
                if ((staggeredGridLayoutManager.f2353w && r1.K(view3) <= i7) || ((!staggeredGridLayoutManager.f2353w && r1.K(view3) >= i7) || !view3.hasFocusable())) {
                    break;
                }
                i9++;
                view = view3;
            }
        }
        return view;
    }

    public final int h(int i7) {
        int i8 = this.f2657b;
        if (i8 != Integer.MIN_VALUE) {
            return i8;
        }
        if (this.f2656a.size() == 0) {
            return i7;
        }
        View view = (View) this.f2656a.get(0);
        o2 o2Var = (o2) view.getLayoutParams();
        this.f2657b = this.f2661f.f2348r.d(view);
        o2Var.getClass();
        return this.f2657b;
    }
}
