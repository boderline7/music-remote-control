package androidx.recyclerview.widget;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n1 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f2551a;

    public /* synthetic */ n1(RecyclerView recyclerView) {
        this.f2551a = recyclerView;
    }

    public final void a(a aVar) {
        int i7 = aVar.f2371a;
        RecyclerView recyclerView = this.f2551a;
        if (i7 == 1) {
            recyclerView.f2321m.a0(aVar.f2372b, aVar.f2374d);
            return;
        }
        if (i7 == 2) {
            recyclerView.f2321m.d0(aVar.f2372b, aVar.f2374d);
        } else if (i7 == 4) {
            recyclerView.f2321m.e0(aVar.f2372b, aVar.f2374d);
        } else {
            if (i7 != 8) {
                return;
            }
            recyclerView.f2321m.c0(aVar.f2372b, aVar.f2374d);
        }
    }

    public final j2 b(int i7) {
        RecyclerView recyclerView = this.f2551a;
        int h7 = recyclerView.f2305e.h();
        int i8 = 0;
        j2 j2Var = null;
        while (true) {
            if (i8 >= h7) {
                break;
            }
            j2 K = RecyclerView.K(recyclerView.f2305e.g(i8));
            if (K != null && !K.isRemoved() && K.mPosition == i7) {
                if (!recyclerView.f2305e.k(K.itemView)) {
                    j2Var = K;
                    break;
                }
                j2Var = K;
            }
            i8++;
        }
        if (j2Var == null || recyclerView.f2305e.k(j2Var.itemView)) {
            return null;
        }
        return j2Var;
    }

    public final void c(int i7, int i8, Object obj) {
        int i9;
        int i10;
        RecyclerView recyclerView = this.f2551a;
        int h7 = recyclerView.f2305e.h();
        int i11 = i8 + i7;
        for (int i12 = 0; i12 < h7; i12++) {
            View g7 = recyclerView.f2305e.g(i12);
            j2 K = RecyclerView.K(g7);
            if (K != null && !K.shouldIgnore() && (i10 = K.mPosition) >= i7 && i10 < i11) {
                K.addFlags(2);
                K.addChangePayload(obj);
                ((s1) g7.getLayoutParams()).f2654c = true;
            }
        }
        z1 z1Var = recyclerView.f2299b;
        ArrayList arrayList = z1Var.f2720c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            j2 j2Var = (j2) arrayList.get(size);
            if (j2Var != null && (i9 = j2Var.mPosition) >= i7 && i9 < i11) {
                j2Var.addFlags(2);
                z1Var.e(size);
            }
        }
        recyclerView.f2316j0 = true;
    }

    public final void d(int i7, int i8) {
        RecyclerView recyclerView = this.f2551a;
        int h7 = recyclerView.f2305e.h();
        for (int i9 = 0; i9 < h7; i9++) {
            j2 K = RecyclerView.K(recyclerView.f2305e.g(i9));
            if (K != null && !K.shouldIgnore() && K.mPosition >= i7) {
                K.offsetPosition(i8, false);
                recyclerView.f2308f0.f2470f = true;
            }
        }
        ArrayList arrayList = recyclerView.f2299b.f2720c;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            j2 j2Var = (j2) arrayList.get(i10);
            if (j2Var != null && j2Var.mPosition >= i7) {
                j2Var.offsetPosition(i8, true);
            }
        }
        recyclerView.requestLayout();
        recyclerView.f2314i0 = true;
    }

    public final void e(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        RecyclerView recyclerView = this.f2551a;
        int h7 = recyclerView.f2305e.h();
        int i16 = -1;
        if (i7 < i8) {
            i10 = i7;
            i9 = i8;
            i11 = -1;
        } else {
            i9 = i7;
            i10 = i8;
            i11 = 1;
        }
        for (int i17 = 0; i17 < h7; i17++) {
            j2 K = RecyclerView.K(recyclerView.f2305e.g(i17));
            if (K != null && (i15 = K.mPosition) >= i10 && i15 <= i9) {
                if (i15 == i7) {
                    K.offsetPosition(i8 - i7, false);
                } else {
                    K.offsetPosition(i11, false);
                }
                recyclerView.f2308f0.f2470f = true;
            }
        }
        z1 z1Var = recyclerView.f2299b;
        z1Var.getClass();
        if (i7 < i8) {
            i13 = i7;
            i12 = i8;
        } else {
            i12 = i7;
            i13 = i8;
            i16 = 1;
        }
        ArrayList arrayList = z1Var.f2720c;
        int size = arrayList.size();
        for (int i18 = 0; i18 < size; i18++) {
            j2 j2Var = (j2) arrayList.get(i18);
            if (j2Var != null && (i14 = j2Var.mPosition) >= i13 && i14 <= i12) {
                if (i14 == i7) {
                    j2Var.offsetPosition(i8 - i7, false);
                } else {
                    j2Var.offsetPosition(i16, false);
                }
            }
        }
        recyclerView.requestLayout();
        recyclerView.f2314i0 = true;
    }

    public final void f(j2 j2Var, androidx.media.a aVar, androidx.media.a aVar2) {
        RecyclerView recyclerView = this.f2551a;
        recyclerView.getClass();
        j2Var.setIsRecyclable(false);
        q qVar = (q) recyclerView.K;
        if (aVar != null) {
            qVar.getClass();
            int i7 = aVar.f2235a;
            int i8 = aVar2.f2235a;
            if (i7 != i8 || aVar.f2236b != aVar2.f2236b) {
                if (!qVar.g(j2Var, i7, aVar.f2236b, i8, aVar2.f2236b)) {
                    return;
                }
                recyclerView.V();
            }
        }
        qVar.l(j2Var);
        j2Var.itemView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        qVar.f2602i.add(j2Var);
        recyclerView.V();
    }

    public final void g(j2 j2Var, androidx.media.a aVar, androidx.media.a aVar2) {
        RecyclerView recyclerView = this.f2551a;
        recyclerView.f2299b.j(j2Var);
        recyclerView.f(j2Var);
        j2Var.setIsRecyclable(false);
        q qVar = (q) recyclerView.K;
        qVar.getClass();
        int i7 = aVar.f2235a;
        int i8 = aVar.f2236b;
        View view = j2Var.itemView;
        int left = aVar2 == null ? view.getLeft() : aVar2.f2235a;
        int top = aVar2 == null ? view.getTop() : aVar2.f2236b;
        if (j2Var.isRemoved() || (i7 == left && i8 == top)) {
            qVar.l(j2Var);
            qVar.f2601h.add(j2Var);
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            if (!qVar.g(j2Var, i7, i8, left, top)) {
                return;
            }
        }
        recyclerView.V();
    }

    public final void h(int i7) {
        RecyclerView recyclerView = this.f2551a;
        View childAt = recyclerView.getChildAt(i7);
        if (childAt != null) {
            recyclerView.o(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i7);
    }
}
