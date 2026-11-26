package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z1 {

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f2718a;

    /* renamed from: b, reason: collision with root package name */
    public ArrayList f2719b;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f2720c;

    /* renamed from: d, reason: collision with root package name */
    public final List f2721d;

    /* renamed from: e, reason: collision with root package name */
    public int f2722e;

    /* renamed from: f, reason: collision with root package name */
    public int f2723f;

    /* renamed from: g, reason: collision with root package name */
    public y1 f2724g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f2725h;

    public z1(RecyclerView recyclerView) {
        this.f2725h = recyclerView;
        ArrayList arrayList = new ArrayList();
        this.f2718a = arrayList;
        this.f2719b = null;
        this.f2720c = new ArrayList();
        this.f2721d = Collections.unmodifiableList(arrayList);
        this.f2722e = 2;
        this.f2723f = 2;
    }

    public final void a(j2 j2Var, boolean z6) {
        RecyclerView.j(j2Var);
        View view = j2Var.itemView;
        RecyclerView recyclerView = this.f2725h;
        l2 l2Var = recyclerView.f2322m0;
        if (l2Var != null) {
            k2 k2Var = l2Var.f2529e;
            p0.d1.o(view, k2Var instanceof k2 ? (p0.c) k2Var.f2522e.remove(view) : null);
        }
        if (z6) {
            h1 h1Var = recyclerView.f2319l;
            if (h1Var != null) {
                h1Var.onViewRecycled(j2Var);
            }
            if (recyclerView.f2308f0 != null) {
                recyclerView.f2307f.m(j2Var);
            }
        }
        j2Var.mOwnerRecyclerView = null;
        y1 c7 = c();
        c7.getClass();
        int itemViewType = j2Var.getItemViewType();
        ArrayList arrayList = c7.a(itemViewType).f2705a;
        if (((x1) c7.f2714a.get(itemViewType)).f2706b <= arrayList.size()) {
            return;
        }
        j2Var.resetInternal();
        arrayList.add(j2Var);
    }

    public final int b(int i7) {
        RecyclerView recyclerView = this.f2725h;
        if (i7 >= 0 && i7 < recyclerView.f2308f0.b()) {
            return !recyclerView.f2308f0.f2471g ? i7 : recyclerView.f2303d.f(i7, 0);
        }
        throw new IndexOutOfBoundsException("invalid position " + i7 + ". State item count is " + recyclerView.f2308f0.b() + recyclerView.z());
    }

    public final y1 c() {
        if (this.f2724g == null) {
            y1 y1Var = new y1();
            y1Var.f2714a = new SparseArray();
            y1Var.f2715b = 0;
            this.f2724g = y1Var;
        }
        return this.f2724g;
    }

    public final void d() {
        ArrayList arrayList = this.f2720c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            e(size);
        }
        arrayList.clear();
        int[] iArr = RecyclerView.f2294w0;
        e0 e0Var = this.f2725h.f2306e0;
        int[] iArr2 = (int[]) e0Var.f2437d;
        if (iArr2 != null) {
            Arrays.fill(iArr2, -1);
        }
        e0Var.f2436c = 0;
    }

    public final void e(int i7) {
        ArrayList arrayList = this.f2720c;
        a((j2) arrayList.get(i7), true);
        arrayList.remove(i7);
    }

    public final void f(View view) {
        j2 K = RecyclerView.K(view);
        boolean isTmpDetached = K.isTmpDetached();
        RecyclerView recyclerView = this.f2725h;
        if (isTmpDetached) {
            recyclerView.removeDetachedView(view, false);
        }
        if (K.isScrap()) {
            K.unScrap();
        } else if (K.wasReturnedFromScrap()) {
            K.clearReturnedFromScrapFlag();
        }
        g(K);
        if (recyclerView.K == null || K.isRecyclable()) {
            return;
        }
        recyclerView.K.d(K);
    }

    public final void g(j2 j2Var) {
        boolean z6;
        boolean isScrap = j2Var.isScrap();
        boolean z7 = true;
        RecyclerView recyclerView = this.f2725h;
        if (isScrap || j2Var.itemView.getParent() != null) {
            StringBuilder sb = new StringBuilder("Scrapped or attached views may not be recycled. isScrap:");
            sb.append(j2Var.isScrap());
            sb.append(" isAttached:");
            sb.append(j2Var.itemView.getParent() != null);
            sb.append(recyclerView.z());
            throw new IllegalArgumentException(sb.toString());
        }
        if (j2Var.isTmpDetached()) {
            throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + j2Var + recyclerView.z());
        }
        if (j2Var.shouldIgnore()) {
            throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + recyclerView.z());
        }
        boolean doesTransientStatePreventRecycling = j2Var.doesTransientStatePreventRecycling();
        h1 h1Var = recyclerView.f2319l;
        if ((h1Var != null && doesTransientStatePreventRecycling && h1Var.onFailedToRecycleView(j2Var)) || j2Var.isRecyclable()) {
            if (this.f2723f <= 0 || j2Var.hasAnyOfTheFlags(526)) {
                z6 = false;
            } else {
                ArrayList arrayList = this.f2720c;
                int size = arrayList.size();
                if (size >= this.f2723f && size > 0) {
                    e(0);
                    size--;
                }
                int[] iArr = RecyclerView.f2294w0;
                if (size > 0 && !recyclerView.f2306e0.e(j2Var.mPosition)) {
                    int i7 = size - 1;
                    while (i7 >= 0) {
                        if (!recyclerView.f2306e0.e(((j2) arrayList.get(i7)).mPosition)) {
                            break;
                        } else {
                            i7--;
                        }
                    }
                    size = i7 + 1;
                }
                arrayList.add(size, j2Var);
                z6 = true;
            }
            if (z6) {
                z7 = false;
            } else {
                a(j2Var, true);
            }
            r1 = z6;
        } else {
            z7 = false;
        }
        recyclerView.f2307f.m(j2Var);
        if (r1 || z7 || !doesTransientStatePreventRecycling) {
            return;
        }
        j2Var.mOwnerRecyclerView = null;
    }

    public final void h(View view) {
        m1 m1Var;
        j2 K = RecyclerView.K(view);
        boolean hasAnyOfTheFlags = K.hasAnyOfTheFlags(12);
        RecyclerView recyclerView = this.f2725h;
        if (!hasAnyOfTheFlags && K.isUpdated() && (m1Var = recyclerView.K) != null) {
            q qVar = (q) m1Var;
            if (K.getUnmodifiedPayloads().isEmpty() && qVar.f2600g && !K.isInvalid()) {
                if (this.f2719b == null) {
                    this.f2719b = new ArrayList();
                }
                K.setScrapContainer(this, true);
                this.f2719b.add(K);
                return;
            }
        }
        if (!K.isInvalid() || K.isRemoved() || recyclerView.f2319l.hasStableIds()) {
            K.setScrapContainer(this, false);
            this.f2718a.add(K);
        } else {
            throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + recyclerView.z());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:234:0x0411, code lost:
    
        if ((r8 + r12) >= r27) goto L216;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x01aa, code lost:
    
        if (r3.f2471g == false) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x01e5, code lost:
    
        r10.addFlags(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x01ed, code lost:
    
        if (r10.isScrap() == false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01ef, code lost:
    
        r2.removeDetachedView(r10.itemView, false);
        r10.unScrap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0201, code lost:
    
        g(r10);
        r10 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01fc, code lost:
    
        if (r10.wasReturnedFromScrap() == false) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01fe, code lost:
    
        r10.clearReturnedFromScrapFlag();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x01c9, code lost:
    
        if (r2.f2319l.getItemViewType(r10.mPosition) != r10.getItemViewType()) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01e2, code lost:
    
        if (r10.getItemId() != r2.f2319l.getItemId(r10.mPosition)) goto L112;
     */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x04b4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:219:0x049b  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.recyclerview.widget.j2 i(int r26, long r27) {
        /*
            Method dump skipped, instructions count: 1259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.z1.i(int, long):androidx.recyclerview.widget.j2");
    }

    public final void j(j2 j2Var) {
        if (j2Var.mInChangeScrap) {
            this.f2719b.remove(j2Var);
        } else {
            this.f2718a.remove(j2Var);
        }
        j2Var.mScrapContainer = null;
        j2Var.mInChangeScrap = false;
        j2Var.clearReturnedFromScrapFlag();
    }

    public final void k() {
        r1 r1Var = this.f2725h.f2321m;
        this.f2723f = this.f2722e + (r1Var != null ? r1Var.f2630j : 0);
        ArrayList arrayList = this.f2720c;
        for (int size = arrayList.size() - 1; size >= 0 && arrayList.size() > this.f2723f; size--) {
            e(size);
        }
    }
}
