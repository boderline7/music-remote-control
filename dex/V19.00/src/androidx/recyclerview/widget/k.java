package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2517a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f2518b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2519c;

    public /* synthetic */ k(int i7, Object obj, Object obj2) {
        this.f2517a = i7;
        this.f2519c = obj;
        this.f2518b = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long j7;
        char c7;
        String str;
        int[] iArr;
        String str2;
        w wVar;
        int i7;
        int i8 = this.f2517a;
        Object obj = this.f2518b;
        Object obj2 = this.f2519c;
        switch (i8) {
            case 0:
                ArrayList arrayList = (ArrayList) obj;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    p pVar = (p) it.next();
                    q qVar = (q) obj2;
                    j2 j2Var = pVar.f2566a;
                    qVar.getClass();
                    View view = j2Var.itemView;
                    int i9 = pVar.f2569d - pVar.f2567b;
                    int i10 = pVar.f2570e - pVar.f2568c;
                    if (i9 != 0) {
                        view.animate().translationX(CropImageView.DEFAULT_ASPECT_RATIO);
                    }
                    if (i10 != 0) {
                        view.animate().translationY(CropImageView.DEFAULT_ASPECT_RATIO);
                    }
                    ViewPropertyAnimator animate = view.animate();
                    qVar.f2609p.add(j2Var);
                    animate.setDuration(qVar.f2542e).setListener(new m(qVar, j2Var, i9, view, i10, animate)).start();
                }
                arrayList.clear();
                ((q) obj2).f2606m.remove(arrayList);
                return;
            case 1:
                ArrayList arrayList2 = (ArrayList) obj;
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    o oVar = (o) it2.next();
                    q qVar2 = (q) obj2;
                    qVar2.getClass();
                    j2 j2Var2 = oVar.f2559a;
                    View view2 = j2Var2 == null ? null : j2Var2.itemView;
                    j2 j2Var3 = oVar.f2560b;
                    View view3 = j2Var3 != null ? j2Var3.itemView : null;
                    ArrayList arrayList3 = qVar2.f2611r;
                    long j8 = qVar2.f2543f;
                    if (view2 != null) {
                        ViewPropertyAnimator duration = view2.animate().setDuration(j8);
                        arrayList3.add(oVar.f2559a);
                        duration.translationX(oVar.f2563e - oVar.f2561c);
                        duration.translationY(oVar.f2564f - oVar.f2562d);
                        j7 = j8;
                        duration.alpha(CropImageView.DEFAULT_ASPECT_RATIO).setListener(new n(qVar2, oVar, duration, view2, 0)).start();
                    } else {
                        j7 = j8;
                    }
                    if (view3 != null) {
                        ViewPropertyAnimator animate2 = view3.animate();
                        arrayList3.add(oVar.f2560b);
                        c7 = 0;
                        animate2.translationX(CropImageView.DEFAULT_ASPECT_RATIO).translationY(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(j7).alpha(1.0f).setListener(new n(qVar2, oVar, animate2, view3, 1)).start();
                    } else {
                        c7 = 0;
                    }
                }
                arrayList2.clear();
                ((q) obj2).f2607n.remove(arrayList2);
                return;
            case 2:
                ArrayList arrayList4 = (ArrayList) obj;
                Iterator it3 = arrayList4.iterator();
                while (it3.hasNext()) {
                    j2 j2Var4 = (j2) it3.next();
                    q qVar3 = (q) obj2;
                    qVar3.getClass();
                    View view4 = j2Var4.itemView;
                    ViewPropertyAnimator animate3 = view4.animate();
                    qVar3.f2608o.add(j2Var4);
                    animate3.alpha(1.0f).setDuration(qVar3.f2540c).setListener(new l(qVar3, j2Var4, view4, animate3)).start();
                }
                arrayList4.clear();
                ((q) obj2).f2605l.remove(arrayList4);
                return;
            default:
                d dVar = (d) obj2;
                g gVar = dVar.f2398e;
                if (gVar.f2456g == dVar.f2396c) {
                    s sVar = (s) obj;
                    List list = gVar.f2455f;
                    List list2 = dVar.f2395b;
                    gVar.f2454e = list2;
                    gVar.f2455f = Collections.unmodifiableList(list2);
                    sVar.getClass();
                    a1 a1Var = gVar.f2450a;
                    h hVar = a1Var instanceof h ? (h) a1Var : new h(a1Var);
                    ArrayList arrayList5 = new ArrayList();
                    List list3 = sVar.f2636a;
                    int size = list3.size() - 1;
                    int i11 = sVar.f2640e;
                    int i12 = sVar.f2641f;
                    while (size >= 0) {
                        w wVar2 = (w) list3.get(size);
                        int i13 = wVar2.f2701c;
                        int i14 = wVar2.f2699a + i13;
                        int i15 = wVar2.f2700b + i13;
                        String str3 = " ";
                        List list4 = list3;
                        int[] iArr2 = sVar.f2637b;
                        g gVar2 = gVar;
                        boolean z6 = sVar.f2642g;
                        List list5 = list;
                        b1 b1Var = sVar.f2639d;
                        if (i14 < i11) {
                            int i16 = i11 - i14;
                            if (z6) {
                                int i17 = i16 - 1;
                                while (i17 >= 0) {
                                    d dVar2 = dVar;
                                    int i18 = i14 + i17;
                                    int i19 = iArr2[i18];
                                    int i20 = size;
                                    int i21 = i19 & 31;
                                    if (i21 != 0) {
                                        iArr = iArr2;
                                        if (i21 == 4 || i21 == 8) {
                                            int i22 = i19 >> 5;
                                            wVar = wVar2;
                                            i7 = i13;
                                            u b7 = s.b(i22, arrayList5, false);
                                            str2 = str3;
                                            hVar.c(i18, b7.f2673b - 1);
                                            if (i21 == 4) {
                                                hVar.d(b7.f2673b - 1, 1, b1Var.g(i18, i22));
                                            }
                                        } else {
                                            if (i21 != 16) {
                                                throw new IllegalStateException("unknown flag for pos " + i18 + str3 + Long.toBinaryString(i21));
                                            }
                                            arrayList5.add(new u(i18, i18, true));
                                            str2 = str3;
                                            wVar = wVar2;
                                            i7 = i13;
                                        }
                                    } else {
                                        iArr = iArr2;
                                        str2 = str3;
                                        wVar = wVar2;
                                        i7 = i13;
                                        int i23 = 1;
                                        hVar.a(i18, 1);
                                        Iterator it4 = arrayList5.iterator();
                                        while (it4.hasNext()) {
                                            ((u) it4.next()).f2673b -= i23;
                                            i23 = 1;
                                        }
                                    }
                                    i17--;
                                    dVar = dVar2;
                                    wVar2 = wVar;
                                    size = i20;
                                    iArr2 = iArr;
                                    i13 = i7;
                                    str3 = str2;
                                }
                            } else {
                                hVar.a(i14, i16);
                            }
                        }
                        d dVar3 = dVar;
                        int[] iArr3 = iArr2;
                        int i24 = size;
                        String str4 = str3;
                        w wVar3 = wVar2;
                        int i25 = i13;
                        if (i15 < i12) {
                            int i26 = i12 - i15;
                            if (z6) {
                                int i27 = i26 - 1;
                                while (i27 >= 0) {
                                    int i28 = i15 + i27;
                                    int i29 = sVar.f2638c[i28];
                                    int i30 = i29 & 31;
                                    if (i30 != 0) {
                                        if (i30 == 4) {
                                            str = str4;
                                        } else if (i30 == 8) {
                                            str = str4;
                                        } else {
                                            if (i30 != 16) {
                                                throw new IllegalStateException("unknown flag for pos " + i28 + str4 + Long.toBinaryString(i30));
                                            }
                                            arrayList5.add(new u(i28, i14, false));
                                            str = str4;
                                        }
                                        int i31 = i29 >> 5;
                                        hVar.c(s.b(i31, arrayList5, true).f2673b, i14);
                                        if (i30 == 4) {
                                            hVar.d(i14, 1, b1Var.g(i31, i28));
                                        }
                                    } else {
                                        str = str4;
                                        int i32 = 1;
                                        hVar.b(i14, 1);
                                        Iterator it5 = arrayList5.iterator();
                                        while (it5.hasNext()) {
                                            ((u) it5.next()).f2673b += i32;
                                            i32 = 1;
                                        }
                                    }
                                    i27--;
                                    str4 = str;
                                }
                            } else {
                                hVar.b(i14, i26);
                            }
                        }
                        int i33 = i25 - 1;
                        while (i33 >= 0) {
                            w wVar4 = wVar3;
                            int i34 = wVar4.f2699a + i33;
                            if ((iArr3[i34] & 31) == 2) {
                                hVar.d(i34, 1, b1Var.g(i34, wVar4.f2700b + i33));
                            }
                            i33--;
                            wVar3 = wVar4;
                        }
                        w wVar5 = wVar3;
                        i11 = wVar5.f2699a;
                        i12 = wVar5.f2700b;
                        size = i24 - 1;
                        list3 = list4;
                        gVar = gVar2;
                        list = list5;
                        dVar = dVar3;
                    }
                    hVar.e();
                    gVar.a(list, dVar.f2397d);
                    return;
                }
                return;
        }
    }
}
