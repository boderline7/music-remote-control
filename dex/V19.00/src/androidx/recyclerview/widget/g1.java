package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2463a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f2464b;

    public /* synthetic */ g1(int i7, RecyclerView recyclerView) {
        this.f2463a = i7;
        this.f2464b = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        int i7 = this.f2463a;
        RecyclerView recyclerView3 = this.f2464b;
        switch (i7) {
            case 0:
                if (recyclerView3.f2333s && !recyclerView3.isLayoutRequested()) {
                    if (!recyclerView3.f2329q) {
                        recyclerView3.requestLayout();
                        break;
                    } else if (!recyclerView3.f2339v) {
                        recyclerView3.m();
                        break;
                    } else {
                        recyclerView3.f2337u = true;
                        break;
                    }
                }
                break;
            default:
                m1 m1Var = recyclerView3.K;
                if (m1Var != null) {
                    q qVar = (q) m1Var;
                    ArrayList arrayList = qVar.f2601h;
                    boolean z6 = !arrayList.isEmpty();
                    ArrayList arrayList2 = qVar.f2603j;
                    boolean z7 = !arrayList2.isEmpty();
                    ArrayList arrayList3 = qVar.f2604k;
                    boolean z8 = !arrayList3.isEmpty();
                    ArrayList arrayList4 = qVar.f2602i;
                    boolean z9 = !arrayList4.isEmpty();
                    if (z6 || z7 || z9 || z8) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            boolean hasNext = it.hasNext();
                            recyclerView2 = recyclerView3;
                            long j7 = qVar.f2541d;
                            if (hasNext) {
                                j2 j2Var = (j2) it.next();
                                View view = j2Var.itemView;
                                ViewPropertyAnimator animate = view.animate();
                                qVar.f2610q.add(j2Var);
                                animate.setDuration(j7).alpha(CropImageView.DEFAULT_ASPECT_RATIO).setListener(new l(qVar, j2Var, animate, view)).start();
                                recyclerView3 = recyclerView2;
                            } else {
                                arrayList.clear();
                                if (z7) {
                                    ArrayList arrayList5 = new ArrayList();
                                    arrayList5.addAll(arrayList2);
                                    qVar.f2606m.add(arrayList5);
                                    arrayList2.clear();
                                    k kVar = new k(0, qVar, arrayList5);
                                    if (z6) {
                                        View view2 = ((p) arrayList5.get(0)).f2566a.itemView;
                                        int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                                        p0.l0.n(view2, kVar, j7);
                                    } else {
                                        kVar.run();
                                    }
                                }
                                if (z8) {
                                    ArrayList arrayList6 = new ArrayList();
                                    arrayList6.addAll(arrayList3);
                                    qVar.f2607n.add(arrayList6);
                                    arrayList3.clear();
                                    k kVar2 = new k(1, qVar, arrayList6);
                                    if (z6) {
                                        View view3 = ((o) arrayList6.get(0)).f2559a.itemView;
                                        int i9 = p0.d1.OVER_SCROLL_ALWAYS;
                                        p0.l0.n(view3, kVar2, j7);
                                    } else {
                                        kVar2.run();
                                    }
                                }
                                if (z9) {
                                    ArrayList arrayList7 = new ArrayList();
                                    arrayList7.addAll(arrayList4);
                                    qVar.f2605l.add(arrayList7);
                                    arrayList4.clear();
                                    k kVar3 = new k(2, qVar, arrayList7);
                                    if (z6 || z7 || z8) {
                                        if (!z6) {
                                            j7 = 0;
                                        }
                                        long max = Math.max(z7 ? qVar.f2542e : 0L, z8 ? qVar.f2543f : 0L) + j7;
                                        View view4 = ((j2) arrayList7.get(0)).itemView;
                                        int i10 = p0.d1.OVER_SCROLL_ALWAYS;
                                        p0.l0.n(view4, kVar3, max);
                                    } else {
                                        kVar3.run();
                                    }
                                }
                            }
                        }
                    } else {
                        recyclerView2 = recyclerView3;
                    }
                    recyclerView = recyclerView2;
                } else {
                    recyclerView = recyclerView3;
                }
                recyclerView.f2320l0 = false;
                break;
        }
    }
}
