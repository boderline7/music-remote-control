package y;

import android.content.Context;
import android.graphics.Rect;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    public int f9911a;

    /* renamed from: b, reason: collision with root package name */
    public int f9912b;

    /* renamed from: c, reason: collision with root package name */
    public u.j f9913c;

    /* renamed from: d, reason: collision with root package name */
    public Object f9914d;

    /* renamed from: e, reason: collision with root package name */
    public Object f9915e;

    /* renamed from: f, reason: collision with root package name */
    public Object f9916f;

    /* renamed from: g, reason: collision with root package name */
    public final Object f9917g;

    public s() {
        this.f9911a = 64;
        this.f9912b = 5;
        this.f9915e = new ArrayDeque();
        this.f9916f = new ArrayDeque();
        this.f9917g = new ArrayDeque();
    }

    public s(MotionLayout motionLayout) {
        this.f9917g = motionLayout;
        this.f9913c = new u.j();
        this.f9914d = new u.j();
        this.f9915e = null;
        this.f9916f = null;
    }

    public static void d(u.j jVar, u.j jVar2) {
        ArrayList arrayList = jVar.f9216v0;
        HashMap hashMap = new HashMap();
        hashMap.put(jVar, jVar2);
        jVar2.f9216v0.clear();
        jVar2.h(jVar, hashMap);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            u.i iVar = (u.i) it.next();
            u.i aVar = iVar instanceof u.a ? new u.a() : iVar instanceof u.n ? new u.n() : iVar instanceof u.l ? new u.l() : iVar instanceof u.r ? new u.r() : iVar instanceof u.o ? new u.p() : new u.i();
            jVar2.f9216v0.add(aVar);
            u.i iVar2 = aVar.V;
            if (iVar2 != null) {
                ((u.t) iVar2).f9216v0.remove(aVar);
                aVar.E();
            }
            aVar.V = jVar2;
            hashMap.put(iVar, aVar);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            u.i iVar3 = (u.i) it2.next();
            ((u.i) hashMap.get(iVar3)).h(iVar3, hashMap);
        }
    }

    public static u.i h(u.j jVar, View view) {
        if (jVar.f9143h0 == view) {
            return jVar;
        }
        ArrayList arrayList = jVar.f9216v0;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            u.i iVar = (u.i) arrayList.get(i7);
            if (iVar.f9143h0 == view) {
                return iVar;
            }
        }
        return null;
    }

    public final void a() {
        MotionLayout motionLayout = (MotionLayout) this.f9917g;
        int childCount = motionLayout.getChildCount();
        motionLayout.C.clear();
        SparseArray sparseArray = new SparseArray();
        int[] iArr = new int[childCount];
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = motionLayout.getChildAt(i7);
            n nVar = new n(childAt);
            int id = childAt.getId();
            iArr[i7] = id;
            sparseArray.put(id, nVar);
            motionLayout.C.put(childAt, nVar);
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt2 = motionLayout.getChildAt(i8);
            n nVar2 = (n) motionLayout.C.get(childAt2);
            if (nVar2 != null) {
                a0.p pVar = (a0.p) this.f9915e;
                x xVar = nVar2.f9871f;
                if (pVar != null) {
                    u.i h7 = h(this.f9913c, childAt2);
                    if (h7 != null) {
                        Rect p6 = MotionLayout.p(motionLayout, h7);
                        a0.p pVar2 = (a0.p) this.f9915e;
                        int width = motionLayout.getWidth();
                        int height = motionLayout.getHeight();
                        int i9 = pVar2.f171c;
                        if (i9 != 0) {
                            n.g(p6, nVar2.f9866a, i9, width, height);
                        }
                        xVar.f9929c = CropImageView.DEFAULT_ASPECT_RATIO;
                        xVar.f9930d = CropImageView.DEFAULT_ASPECT_RATIO;
                        nVar2.f(xVar);
                        xVar.f(p6.left, p6.top, p6.width(), p6.height());
                        a0.k h8 = pVar2.h(nVar2.f9868c);
                        xVar.a(h8);
                        a0.m mVar = h8.f84d;
                        nVar2.f9877l = mVar.f139g;
                        nVar2.f9873h.c(p6, pVar2, i9, nVar2.f9868c);
                        nVar2.B = h8.f86f.f160i;
                        nVar2.D = mVar.f142j;
                        nVar2.E = mVar.f141i;
                        Context context = nVar2.f9867b.getContext();
                        int i10 = mVar.f144l;
                        nVar2.F = i10 != -2 ? i10 != -1 ? i10 != 0 ? i10 != 1 ? i10 != 2 ? i10 != 4 ? i10 != 5 ? null : new OvershootInterpolator() : new BounceInterpolator() : new DecelerateInterpolator() : new AccelerateInterpolator() : new AccelerateDecelerateInterpolator() : new m(t.f.c(mVar.f143k)) : AnimationUtils.loadInterpolator(context, mVar.f145m);
                    } else if (motionLayout.M != 0) {
                        l6.f.E();
                        l6.f.G(childAt2);
                        childAt2.getClass();
                    }
                } else {
                    motionLayout.getClass();
                }
                if (((a0.p) this.f9916f) != null) {
                    u.i h9 = h((u.j) this.f9914d, childAt2);
                    if (h9 != null) {
                        Rect p7 = MotionLayout.p(motionLayout, h9);
                        a0.p pVar3 = (a0.p) this.f9916f;
                        int width2 = motionLayout.getWidth();
                        int height2 = motionLayout.getHeight();
                        int i11 = pVar3.f171c;
                        if (i11 != 0) {
                            n.g(p7, nVar2.f9866a, i11, width2, height2);
                            p7 = nVar2.f9866a;
                        }
                        x xVar2 = nVar2.f9872g;
                        xVar2.f9929c = 1.0f;
                        xVar2.f9930d = 1.0f;
                        nVar2.f(xVar2);
                        xVar2.f(p7.left, p7.top, p7.width(), p7.height());
                        xVar2.a(pVar3.h(nVar2.f9868c));
                        nVar2.f9874i.c(p7, pVar3, i11, nVar2.f9868c);
                    } else if (motionLayout.M != 0) {
                        l6.f.E();
                        l6.f.G(childAt2);
                        childAt2.getClass();
                    }
                }
            }
        }
        for (int i12 = 0; i12 < childCount; i12++) {
            n nVar3 = (n) sparseArray.get(iArr[i12]);
            int i13 = nVar3.f9871f.f9937k;
            if (i13 != -1) {
                n nVar4 = (n) sparseArray.get(i13);
                nVar3.f9871f.h(nVar4, nVar4.f9871f);
                nVar3.f9872g.h(nVar4, nVar4.f9872g);
            }
        }
    }

    public final synchronized void b() {
        try {
            Iterator it = ((ArrayDeque) this.f9915e).iterator();
            while (it.hasNext()) {
                ((x5.g) it.next()).f9683c.d();
            }
            Iterator it2 = ((ArrayDeque) this.f9916f).iterator();
            while (it2.hasNext()) {
                ((x5.g) it2.next()).f9683c.d();
            }
            Iterator it3 = ((ArrayDeque) this.f9917g).iterator();
            while (it3.hasNext()) {
                ((x5.j) it3.next()).d();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void c(int i7, int i8) {
        MotionLayout motionLayout = (MotionLayout) this.f9917g;
        int optimizationLevel = motionLayout.getOptimizationLevel();
        if (motionLayout.f1587x == motionLayout.getStartState()) {
            u.j jVar = (u.j) this.f9914d;
            a0.p pVar = (a0.p) this.f9916f;
            motionLayout.n(jVar, optimizationLevel, (pVar == null || pVar.f171c == 0) ? i7 : i8, (pVar == null || pVar.f171c == 0) ? i8 : i7);
            a0.p pVar2 = (a0.p) this.f9915e;
            if (pVar2 != null) {
                u.j jVar2 = this.f9913c;
                int i9 = pVar2.f171c;
                int i10 = i9 == 0 ? i7 : i8;
                if (i9 == 0) {
                    i7 = i8;
                }
                motionLayout.n(jVar2, optimizationLevel, i10, i7);
                return;
            }
            return;
        }
        a0.p pVar3 = (a0.p) this.f9915e;
        if (pVar3 != null) {
            u.j jVar3 = this.f9913c;
            int i11 = pVar3.f171c;
            motionLayout.n(jVar3, optimizationLevel, i11 == 0 ? i7 : i8, i11 == 0 ? i8 : i7);
        }
        u.j jVar4 = (u.j) this.f9914d;
        a0.p pVar4 = (a0.p) this.f9916f;
        int i12 = (pVar4 == null || pVar4.f171c == 0) ? i7 : i8;
        if (pVar4 == null || pVar4.f171c == 0) {
            i7 = i8;
        }
        motionLayout.n(jVar4, optimizationLevel, i12, i7);
    }

    public final x5.g e(String str) {
        Iterator it = ((ArrayDeque) this.f9916f).iterator();
        while (it.hasNext()) {
            x5.g gVar = (x5.g) it.next();
            if (i5.a.c(((h0) gVar.f9683c.f9687b.f1482b).f8795d, str)) {
                return gVar;
            }
        }
        Iterator it2 = ((ArrayDeque) this.f9915e).iterator();
        while (it2.hasNext()) {
            x5.g gVar2 = (x5.g) it2.next();
            if (i5.a.c(((h0) gVar2.f9683c.f9687b.f1482b).f8795d, str)) {
                return gVar2;
            }
        }
        return null;
    }

    public final void f(ArrayDeque arrayDeque, Object obj) {
        Runnable runnable;
        synchronized (this) {
            if (!arrayDeque.remove(obj)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = (Runnable) this.f9913c;
        }
        if (j() || runnable == null) {
            return;
        }
        runnable.run();
    }

    public final void g(x5.g gVar) {
        i5.a.g("call", gVar);
        gVar.f9682b.decrementAndGet();
        f((ArrayDeque) this.f9916f, gVar);
    }

    public final void i(a0.p pVar, a0.p pVar2) {
        this.f9915e = pVar;
        this.f9916f = pVar2;
        this.f9913c = new u.j();
        u.j jVar = new u.j();
        this.f9914d = jVar;
        u.j jVar2 = this.f9913c;
        MotionLayout motionLayout = (MotionLayout) this.f9917g;
        int i7 = MotionLayout.TOUCH_UP_COMPLETE;
        u.j jVar3 = motionLayout.f1688c;
        v.r rVar = jVar3.f9178z0;
        jVar2.f9178z0 = rVar;
        jVar2.f9176x0.f9335f = rVar;
        v.r rVar2 = jVar3.f9178z0;
        jVar.f9178z0 = rVar2;
        jVar.f9176x0.f9335f = rVar2;
        jVar2.f9216v0.clear();
        ((u.j) this.f9914d).f9216v0.clear();
        d(motionLayout.f1688c, this.f9913c);
        d(motionLayout.f1688c, (u.j) this.f9914d);
        if (motionLayout.G > 0.5d) {
            if (pVar != null) {
                l(this.f9913c, pVar);
            }
            l((u.j) this.f9914d, pVar2);
        } else {
            l((u.j) this.f9914d, pVar2);
            if (pVar != null) {
                l(this.f9913c, pVar);
            }
        }
        this.f9913c.A0 = motionLayout.k();
        u.j jVar4 = this.f9913c;
        jVar4.f9175w0.c(jVar4);
        ((u.j) this.f9914d).A0 = motionLayout.k();
        u.j jVar5 = (u.j) this.f9914d;
        jVar5.f9175w0.c(jVar5);
        ViewGroup.LayoutParams layoutParams = motionLayout.getLayoutParams();
        if (layoutParams != null) {
            if (layoutParams.width == -2) {
                u.j jVar6 = this.f9913c;
                u.h hVar = u.h.WRAP_CONTENT;
                jVar6.O(hVar);
                ((u.j) this.f9914d).O(hVar);
            }
            if (layoutParams.height == -2) {
                u.j jVar7 = this.f9913c;
                u.h hVar2 = u.h.WRAP_CONTENT;
                jVar7.P(hVar2);
                ((u.j) this.f9914d).P(hVar2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean j() {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: y.s.j():boolean");
    }

    public final void k() {
        HashMap hashMap;
        MotionLayout motionLayout = (MotionLayout) this.f9917g;
        int i7 = motionLayout.f1591z;
        int i8 = motionLayout.A;
        int mode = View.MeasureSpec.getMode(i7);
        int mode2 = View.MeasureSpec.getMode(i8);
        motionLayout.f1576r0 = mode;
        motionLayout.f1578s0 = mode2;
        motionLayout.getOptimizationLevel();
        c(i7, i8);
        int i9 = 0;
        if (!(motionLayout.getParent() instanceof MotionLayout) || mode != 1073741824 || mode2 != 1073741824) {
            c(i7, i8);
            motionLayout.f1572n0 = this.f9913c.s();
            motionLayout.f1573o0 = this.f9913c.m();
            motionLayout.f1574p0 = ((u.j) this.f9914d).s();
            int m6 = ((u.j) this.f9914d).m();
            motionLayout.f1575q0 = m6;
            motionLayout.f1571m0 = (motionLayout.f1572n0 == motionLayout.f1574p0 && motionLayout.f1573o0 == m6) ? false : true;
        }
        int i10 = motionLayout.f1572n0;
        int i11 = motionLayout.f1573o0;
        int i12 = motionLayout.f1576r0;
        if (i12 == Integer.MIN_VALUE || i12 == 0) {
            i10 = (int) ((motionLayout.f1580t0 * (motionLayout.f1574p0 - i10)) + i10);
        }
        int i13 = i10;
        int i14 = motionLayout.f1578s0;
        int i15 = (i14 == Integer.MIN_VALUE || i14 == 0) ? (int) ((motionLayout.f1580t0 * (motionLayout.f1575q0 - i11)) + i11) : i11;
        u.j jVar = this.f9913c;
        motionLayout.m(i7, i8, i13, i15, jVar.J0 || ((u.j) this.f9914d).J0, jVar.K0 || ((u.j) this.f9914d).K0);
        int childCount = motionLayout.getChildCount();
        motionLayout.B0.a();
        motionLayout.K = true;
        SparseArray sparseArray = new SparseArray();
        int i16 = 0;
        while (true) {
            hashMap = motionLayout.C;
            if (i16 >= childCount) {
                break;
            }
            View childAt = motionLayout.getChildAt(i16);
            sparseArray.put(childAt.getId(), (n) hashMap.get(childAt));
            i16++;
        }
        int width = motionLayout.getWidth();
        int height = motionLayout.getHeight();
        z zVar = motionLayout.f1577s.f9735c;
        int i17 = zVar != null ? zVar.f9962p : -1;
        if (i17 != -1) {
            for (int i18 = 0; i18 < childCount; i18++) {
                n nVar = (n) hashMap.get(motionLayout.getChildAt(i18));
                if (nVar != null) {
                    nVar.A = i17;
                }
            }
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        int[] iArr = new int[hashMap.size()];
        int i19 = 0;
        for (int i20 = 0; i20 < childCount; i20++) {
            n nVar2 = (n) hashMap.get(motionLayout.getChildAt(i20));
            int i21 = nVar2.f9871f.f9937k;
            if (i21 != -1) {
                sparseBooleanArray.put(i21, true);
                iArr[i19] = nVar2.f9871f.f9937k;
                i19++;
            }
        }
        if (motionLayout.f1564f0 != null) {
            for (int i22 = 0; i22 < i19; i22++) {
                n nVar3 = (n) hashMap.get(motionLayout.findViewById(iArr[i22]));
                if (nVar3 != null) {
                    motionLayout.f1577s.f(nVar3);
                }
            }
            Iterator it = motionLayout.f1564f0.iterator();
            while (it.hasNext()) {
                ((MotionHelper) it.next()).s(motionLayout, hashMap);
            }
            for (int i23 = 0; i23 < i19; i23++) {
                n nVar4 = (n) hashMap.get(motionLayout.findViewById(iArr[i23]));
                if (nVar4 != null) {
                    nVar4.h(width, height, motionLayout.getNanoTime());
                }
            }
        } else {
            for (int i24 = 0; i24 < i19; i24++) {
                n nVar5 = (n) hashMap.get(motionLayout.findViewById(iArr[i24]));
                if (nVar5 != null) {
                    motionLayout.f1577s.f(nVar5);
                    nVar5.h(width, height, motionLayout.getNanoTime());
                }
            }
        }
        for (int i25 = 0; i25 < childCount; i25++) {
            View childAt2 = motionLayout.getChildAt(i25);
            n nVar6 = (n) hashMap.get(childAt2);
            if (!sparseBooleanArray.get(childAt2.getId()) && nVar6 != null) {
                motionLayout.f1577s.f(nVar6);
                nVar6.h(width, height, motionLayout.getNanoTime());
            }
        }
        z zVar2 = motionLayout.f1577s.f9735c;
        float f7 = zVar2 != null ? zVar2.f9955i : 0.0f;
        if (f7 != CropImageView.DEFAULT_ASPECT_RATIO) {
            boolean z6 = ((double) f7) < 0.0d;
            float abs = Math.abs(f7);
            float f8 = -3.4028235E38f;
            float f9 = Float.MAX_VALUE;
            float f10 = -3.4028235E38f;
            float f11 = Float.MAX_VALUE;
            for (int i26 = 0; i26 < childCount; i26++) {
                n nVar7 = (n) hashMap.get(motionLayout.getChildAt(i26));
                if (!Float.isNaN(nVar7.f9877l)) {
                    for (int i27 = 0; i27 < childCount; i27++) {
                        n nVar8 = (n) hashMap.get(motionLayout.getChildAt(i27));
                        if (!Float.isNaN(nVar8.f9877l)) {
                            f9 = Math.min(f9, nVar8.f9877l);
                            f8 = Math.max(f8, nVar8.f9877l);
                        }
                    }
                    while (i9 < childCount) {
                        n nVar9 = (n) hashMap.get(motionLayout.getChildAt(i9));
                        if (!Float.isNaN(nVar9.f9877l)) {
                            nVar9.f9879n = 1.0f / (1.0f - abs);
                            if (z6) {
                                nVar9.f9878m = abs - (((f8 - nVar9.f9877l) / (f8 - f9)) * abs);
                            } else {
                                nVar9.f9878m = abs - (((nVar9.f9877l - f9) * abs) / (f8 - f9));
                            }
                        }
                        i9++;
                    }
                    return;
                }
                x xVar = nVar7.f9872g;
                float f12 = xVar.f9931e;
                float f13 = xVar.f9932f;
                float f14 = z6 ? f13 - f12 : f13 + f12;
                f11 = Math.min(f11, f14);
                f10 = Math.max(f10, f14);
            }
            while (i9 < childCount) {
                n nVar10 = (n) hashMap.get(motionLayout.getChildAt(i9));
                x xVar2 = nVar10.f9872g;
                float f15 = xVar2.f9931e;
                float f16 = xVar2.f9932f;
                float f17 = z6 ? f16 - f15 : f16 + f15;
                nVar10.f9879n = 1.0f / (1.0f - abs);
                nVar10.f9878m = abs - (((f17 - f11) * abs) / (f10 - f11));
                i9++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void l(u.j jVar, a0.p pVar) {
        a0.k kVar;
        a0.k kVar2;
        SparseArray sparseArray = new SparseArray();
        a0.q qVar = new a0.q();
        sparseArray.clear();
        sparseArray.put(0, jVar);
        MotionLayout motionLayout = (MotionLayout) this.f9917g;
        sparseArray.put(motionLayout.getId(), jVar);
        if (pVar != null && pVar.f171c != 0) {
            u.j jVar2 = (u.j) this.f9914d;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(motionLayout.getHeight(), v.c.EXACTLY);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(motionLayout.getWidth(), v.c.EXACTLY);
            int i7 = MotionLayout.TOUCH_UP_COMPLETE;
            motionLayout.n(jVar2, optimizationLevel, makeMeasureSpec, makeMeasureSpec2);
        }
        Iterator it = jVar.f9216v0.iterator();
        while (it.hasNext()) {
            u.i iVar = (u.i) it.next();
            iVar.f9147j0 = true;
            sparseArray.put(((View) iVar.f9143h0).getId(), iVar);
        }
        Iterator it2 = jVar.f9216v0.iterator();
        while (it2.hasNext()) {
            u.i iVar2 = (u.i) it2.next();
            View view = (View) iVar2.f9143h0;
            int id = view.getId();
            HashMap hashMap = pVar.f174f;
            if (hashMap.containsKey(Integer.valueOf(id)) && (kVar2 = (a0.k) hashMap.get(Integer.valueOf(id))) != null) {
                kVar2.a(qVar);
            }
            iVar2.Q(pVar.h(view.getId()).f85e.f94c);
            iVar2.N(pVar.h(view.getId()).f85e.f96d);
            if (view instanceof ConstraintHelper) {
                ConstraintHelper constraintHelper = (ConstraintHelper) view;
                int id2 = constraintHelper.getId();
                HashMap hashMap2 = pVar.f174f;
                if (hashMap2.containsKey(Integer.valueOf(id2)) && (kVar = (a0.k) hashMap2.get(Integer.valueOf(id2))) != null && (iVar2 instanceof u.p)) {
                    constraintHelper.m(kVar, (u.p) iVar2, qVar, sparseArray);
                }
                if (view instanceof Barrier) {
                    ((Barrier) view).r();
                }
            }
            qVar.resolveLayoutDirection(motionLayout.getLayoutDirection());
            int i8 = MotionLayout.TOUCH_UP_COMPLETE;
            motionLayout.a(false, view, iVar2, qVar, sparseArray);
            if (pVar.h(view.getId()).f83c.f148c == 1) {
                iVar2.f9145i0 = view.getVisibility();
            } else {
                iVar2.f9145i0 = pVar.h(view.getId()).f83c.f147b;
            }
        }
        Iterator it3 = jVar.f9216v0.iterator();
        while (it3.hasNext()) {
            u.i iVar3 = (u.i) it3.next();
            if (iVar3 instanceof u.s) {
                ConstraintHelper constraintHelper2 = (ConstraintHelper) iVar3.f9143h0;
                u.o oVar = (u.o) iVar3;
                constraintHelper2.q(oVar, sparseArray);
                u.s sVar = (u.s) oVar;
                for (int i9 = 0; i9 < sVar.f9211w0; i9++) {
                    u.i iVar4 = sVar.f9210v0[i9];
                    if (iVar4 != null) {
                        iVar4.G = true;
                    }
                }
            }
        }
    }
}
