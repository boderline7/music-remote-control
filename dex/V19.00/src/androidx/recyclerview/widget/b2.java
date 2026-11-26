package androidx.recyclerview.widget;

import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b2 extends j1 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2386a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2387b;

    public /* synthetic */ b2(int i7, Object obj) {
        this.f2386a = i7;
        this.f2387b = obj;
    }

    public b2(androidx.viewpager2.adapter.f fVar) {
        this.f2386a = 1;
        this.f2387b = fVar;
    }

    @Override // androidx.recyclerview.widget.j1
    public final void a() {
        int i7 = this.f2386a;
        Object obj = this.f2387b;
        switch (i7) {
            case 0:
                RecyclerView recyclerView = (RecyclerView) obj;
                recyclerView.i(null);
                recyclerView.f2308f0.f2470f = true;
                recyclerView.X(true);
                if (!recyclerView.f2303d.g()) {
                    recyclerView.requestLayout();
                    break;
                }
                break;
            case 1:
                ((androidx.viewpager2.adapter.f) obj).b(true);
                break;
            default:
                ((s3.k) obj).b();
                break;
        }
    }

    @Override // androidx.recyclerview.widget.j1
    public final void b(int i7, int i8, Object obj) {
        int i9 = this.f2386a;
        Object obj2 = this.f2387b;
        switch (i9) {
            case 0:
                RecyclerView recyclerView = (RecyclerView) obj2;
                recyclerView.i(null);
                b bVar = recyclerView.f2303d;
                if (i8 >= 1) {
                    ArrayList arrayList = bVar.f2377b;
                    arrayList.add(bVar.h(obj, 4, i7, i8));
                    bVar.f2381f |= 4;
                    if (arrayList.size() == 1) {
                        f();
                        break;
                    }
                } else {
                    bVar.getClass();
                    break;
                }
                break;
            case 1:
                a();
                break;
            default:
                ((s3.k) obj2).b();
                break;
        }
    }

    @Override // androidx.recyclerview.widget.j1
    public final void c(int i7, int i8) {
        int i9 = this.f2386a;
        Object obj = this.f2387b;
        switch (i9) {
            case 0:
                RecyclerView recyclerView = (RecyclerView) obj;
                recyclerView.i(null);
                b bVar = recyclerView.f2303d;
                if (i8 >= 1) {
                    ArrayList arrayList = bVar.f2377b;
                    arrayList.add(bVar.h(null, 1, i7, i8));
                    bVar.f2381f |= 1;
                    if (arrayList.size() == 1) {
                        f();
                        break;
                    }
                } else {
                    bVar.getClass();
                    break;
                }
                break;
            case 1:
                a();
                break;
            default:
                ((s3.k) obj).b();
                break;
        }
    }

    @Override // androidx.recyclerview.widget.j1
    public final void d(int i7, int i8) {
        int i9 = this.f2386a;
        Object obj = this.f2387b;
        switch (i9) {
            case 0:
                RecyclerView recyclerView = (RecyclerView) obj;
                recyclerView.i(null);
                b bVar = recyclerView.f2303d;
                bVar.getClass();
                if (i7 != i8) {
                    ArrayList arrayList = bVar.f2377b;
                    arrayList.add(bVar.h(null, 8, i7, i8));
                    bVar.f2381f |= 8;
                    if (arrayList.size() == 1) {
                        f();
                        break;
                    }
                }
                break;
            case 1:
                a();
                break;
            default:
                ((s3.k) obj).b();
                break;
        }
    }

    @Override // androidx.recyclerview.widget.j1
    public final void e(int i7, int i8) {
        int i9 = this.f2386a;
        Object obj = this.f2387b;
        switch (i9) {
            case 0:
                RecyclerView recyclerView = (RecyclerView) obj;
                recyclerView.i(null);
                b bVar = recyclerView.f2303d;
                if (i8 >= 1) {
                    ArrayList arrayList = bVar.f2377b;
                    arrayList.add(bVar.h(null, 2, i7, i8));
                    bVar.f2381f |= 2;
                    if (arrayList.size() == 1) {
                        f();
                        break;
                    }
                } else {
                    bVar.getClass();
                    break;
                }
                break;
            case 1:
                a();
                break;
            default:
                ((s3.k) obj).b();
                break;
        }
    }

    public final void f() {
        int[] iArr = RecyclerView.f2294w0;
        RecyclerView recyclerView = (RecyclerView) this.f2387b;
        if (!recyclerView.f2331r || !recyclerView.f2329q) {
            recyclerView.f2343y = true;
            recyclerView.requestLayout();
        } else {
            g1 g1Var = recyclerView.f2311h;
            int i7 = p0.d1.OVER_SCROLL_ALWAYS;
            p0.l0.m(recyclerView, g1Var);
        }
    }
}
