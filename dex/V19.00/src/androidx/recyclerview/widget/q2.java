package androidx.recyclerview.widget;

import android.view.View;
import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q2 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2616a;

    /* renamed from: b, reason: collision with root package name */
    public Object f2617b;

    /* renamed from: c, reason: collision with root package name */
    public Object f2618c;

    public q2(int i7) {
        this.f2616a = i7;
        if (i7 != 1) {
            return;
        }
        this.f2617b = new q.k();
        this.f2618c = new q.e();
    }

    public q2(p1 p1Var) {
        this.f2616a = 2;
        this.f2617b = p1Var;
        t2 t2Var = new t2();
        t2Var.f2667a = 0;
        this.f2618c = t2Var;
    }

    public final void a(j2 j2Var) {
        v2 v2Var = (v2) ((q.k) this.f2617b).getOrDefault(j2Var, null);
        if (v2Var == null) {
            v2Var = v2.a();
            ((q.k) this.f2617b).put(j2Var, v2Var);
        }
        v2Var.f2696a |= 1;
    }

    public final void b(j2 j2Var, androidx.media.a aVar) {
        v2 v2Var = (v2) ((q.k) this.f2617b).getOrDefault(j2Var, null);
        if (v2Var == null) {
            v2Var = v2.a();
            ((q.k) this.f2617b).put(j2Var, v2Var);
        }
        v2Var.f2698c = aVar;
        v2Var.f2696a |= 8;
    }

    public final void c(j2 j2Var, androidx.media.a aVar) {
        v2 v2Var = (v2) ((q.k) this.f2617b).getOrDefault(j2Var, null);
        if (v2Var == null) {
            v2Var = v2.a();
            ((q.k) this.f2617b).put(j2Var, v2Var);
        }
        v2Var.f2697b = aVar;
        v2Var.f2696a |= 4;
    }

    public final void d() {
        switch (this.f2616a) {
            case 0:
                int[] iArr = (int[]) this.f2617b;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                this.f2618c = null;
                break;
            default:
                ((q.k) this.f2617b).clear();
                ((q.e) this.f2618c).a();
                break;
        }
    }

    public final void e(int i7) {
        Object obj = this.f2617b;
        if (((int[]) obj) == null) {
            int[] iArr = new int[Math.max(i7, 10) + 1];
            this.f2617b = iArr;
            Arrays.fill(iArr, -1);
        } else if (i7 >= ((int[]) obj).length) {
            int[] iArr2 = (int[]) obj;
            int length = ((int[]) obj).length;
            while (length <= i7) {
                length *= 2;
            }
            int[] iArr3 = new int[length];
            this.f2617b = iArr3;
            System.arraycopy(iArr2, 0, iArr3, 0, iArr2.length);
            Object obj2 = this.f2617b;
            Arrays.fill((int[]) obj2, iArr2.length, ((int[]) obj2).length, -1);
        }
    }

    public final View f(int i7, int i8, int i9, int i10) {
        int H;
        int i11;
        int I;
        View v6;
        p1 p1Var = (p1) ((u2) this.f2617b);
        int i12 = p1Var.f2597a;
        r1 r1Var = p1Var.f2598b;
        switch (i12) {
            case 0:
                H = r1Var.H();
                break;
            default:
                H = r1Var.J();
                break;
        }
        p1 p1Var2 = (p1) ((u2) this.f2617b);
        int i13 = p1Var2.f2597a;
        r1 r1Var2 = p1Var2.f2598b;
        switch (i13) {
            case 0:
                i11 = r1Var2.f2634n;
                I = r1Var2.I();
                break;
            default:
                i11 = r1Var2.f2635o;
                I = r1Var2.G();
                break;
        }
        int i14 = i11 - I;
        int i15 = i8 > i7 ? 1 : -1;
        View view = null;
        while (i7 != i8) {
            p1 p1Var3 = (p1) ((u2) this.f2617b);
            int i16 = p1Var3.f2597a;
            r1 r1Var3 = p1Var3.f2598b;
            switch (i16) {
                case 0:
                    v6 = r1Var3.v(i7);
                    break;
                default:
                    v6 = r1Var3.v(i7);
                    break;
            }
            int b7 = ((p1) ((u2) this.f2617b)).b(v6);
            int a7 = ((p1) ((u2) this.f2617b)).a(v6);
            Object obj = this.f2618c;
            t2 t2Var = (t2) obj;
            t2Var.f2668b = H;
            t2Var.f2669c = i14;
            t2Var.f2670d = b7;
            t2Var.f2671e = a7;
            if (i9 != 0) {
                ((t2) obj).f2667a = i9;
                if (((t2) obj).a()) {
                    return v6;
                }
            }
            if (i10 != 0) {
                Object obj2 = this.f2618c;
                ((t2) obj2).f2667a = i10;
                if (((t2) obj2).a()) {
                    view = v6;
                }
            }
            i7 += i15;
        }
        return view;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int g(int r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.f2617b
            int[] r0 = (int[]) r0
            r1 = -1
            if (r0 != 0) goto L8
            return r1
        L8:
            int r0 = r0.length
            if (r6 < r0) goto Lc
            return r1
        Lc:
            java.lang.Object r0 = r5.f2618c
            r2 = r0
            java.util.List r2 = (java.util.List) r2
            if (r2 != 0) goto L15
        L13:
            r0 = r1
            goto L72
        L15:
            java.util.List r0 = (java.util.List) r0
            r2 = 0
            if (r0 != 0) goto L1b
            goto L36
        L1b:
            int r0 = r0.size()
            int r0 = r0 + (-1)
        L21:
            if (r0 < 0) goto L36
            java.lang.Object r3 = r5.f2618c
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r3.get(r0)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r3
            int r4 = r3.f2357a
            if (r4 != r6) goto L33
            r2 = r3
            goto L36
        L33:
            int r0 = r0 + (-1)
            goto L21
        L36:
            if (r2 == 0) goto L3f
            java.lang.Object r0 = r5.f2618c
            java.util.List r0 = (java.util.List) r0
            r0.remove(r2)
        L3f:
            java.lang.Object r0 = r5.f2618c
            java.util.List r0 = (java.util.List) r0
            int r0 = r0.size()
            r2 = 0
        L48:
            if (r2 >= r0) goto L5c
            java.lang.Object r3 = r5.f2618c
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r3.get(r2)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r3
            int r3 = r3.f2357a
            if (r3 < r6) goto L59
            goto L5d
        L59:
            int r2 = r2 + 1
            goto L48
        L5c:
            r2 = r1
        L5d:
            if (r2 == r1) goto L13
            java.lang.Object r0 = r5.f2618c
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r2)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r0
            java.lang.Object r3 = r5.f2618c
            java.util.List r3 = (java.util.List) r3
            r3.remove(r2)
            int r0 = r0.f2357a
        L72:
            if (r0 != r1) goto L82
            java.lang.Object r0 = r5.f2617b
            int[] r0 = (int[]) r0
            int r2 = r0.length
            java.util.Arrays.fill(r0, r6, r2, r1)
            java.lang.Object r6 = r5.f2617b
            int[] r6 = (int[]) r6
            int r6 = r6.length
            return r6
        L82:
            java.lang.Object r2 = r5.f2617b
            int[] r2 = (int[]) r2
            int r0 = r0 + 1
            java.util.Arrays.fill(r2, r6, r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.q2.g(int):int");
    }

    public final boolean h(View view) {
        int H;
        int i7;
        int I;
        t2 t2Var = (t2) this.f2618c;
        p1 p1Var = (p1) ((u2) this.f2617b);
        int i8 = p1Var.f2597a;
        r1 r1Var = p1Var.f2598b;
        switch (i8) {
            case 0:
                H = r1Var.H();
                break;
            default:
                H = r1Var.J();
                break;
        }
        p1 p1Var2 = (p1) ((u2) this.f2617b);
        int i9 = p1Var2.f2597a;
        r1 r1Var2 = p1Var2.f2598b;
        switch (i9) {
            case 0:
                i7 = r1Var2.f2634n;
                I = r1Var2.I();
                break;
            default:
                i7 = r1Var2.f2635o;
                I = r1Var2.G();
                break;
        }
        int i10 = i7 - I;
        int b7 = ((p1) ((u2) this.f2617b)).b(view);
        int a7 = ((p1) ((u2) this.f2617b)).a(view);
        t2Var.f2668b = H;
        t2Var.f2669c = i10;
        t2Var.f2670d = b7;
        t2Var.f2671e = a7;
        Object obj = this.f2618c;
        ((t2) obj).f2667a = 24579;
        return ((t2) obj).a();
    }

    public final void i(int i7, int i8) {
        int[] iArr = (int[]) this.f2617b;
        if (iArr == null || i7 >= iArr.length) {
            return;
        }
        int i9 = i7 + i8;
        e(i9);
        int[] iArr2 = (int[]) this.f2617b;
        System.arraycopy(iArr2, i7, iArr2, i9, (iArr2.length - i7) - i8);
        Arrays.fill((int[]) this.f2617b, i7, i9, -1);
        List list = (List) this.f2618c;
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) ((List) this.f2618c).get(size);
            int i10 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f2357a;
            if (i10 >= i7) {
                staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f2357a = i10 + i8;
            }
        }
    }

    public final void j(int i7, int i8) {
        int[] iArr = (int[]) this.f2617b;
        if (iArr == null || i7 >= iArr.length) {
            return;
        }
        int i9 = i7 + i8;
        e(i9);
        int[] iArr2 = (int[]) this.f2617b;
        System.arraycopy(iArr2, i9, iArr2, i7, (iArr2.length - i7) - i8);
        int[] iArr3 = (int[]) this.f2617b;
        Arrays.fill(iArr3, iArr3.length - i8, iArr3.length, -1);
        List list = (List) this.f2618c;
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) ((List) this.f2618c).get(size);
            int i10 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f2357a;
            if (i10 >= i7) {
                if (i10 < i9) {
                    ((List) this.f2618c).remove(size);
                } else {
                    staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.f2357a = i10 - i8;
                }
            }
        }
    }

    public final androidx.media.a k(j2 j2Var, int i7) {
        v2 v2Var;
        androidx.media.a aVar;
        int e7 = ((q.k) this.f2617b).e(j2Var);
        if (e7 >= 0 && (v2Var = (v2) ((q.k) this.f2617b).j(e7)) != null) {
            int i8 = v2Var.f2696a;
            if ((i8 & i7) != 0) {
                int i9 = i8 & (~i7);
                v2Var.f2696a = i9;
                if (i7 == 4) {
                    aVar = v2Var.f2697b;
                } else {
                    if (i7 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    aVar = v2Var.f2698c;
                }
                if ((i9 & 12) == 0) {
                    ((q.k) this.f2617b).i(e7);
                    v2Var.f2696a = 0;
                    v2Var.f2697b = null;
                    v2Var.f2698c = null;
                    v2.f2695d.b(v2Var);
                }
                return aVar;
            }
        }
        return null;
    }

    public final void l(j2 j2Var) {
        v2 v2Var = (v2) ((q.k) this.f2617b).getOrDefault(j2Var, null);
        if (v2Var == null) {
            return;
        }
        v2Var.f2696a &= -2;
    }

    public final void m(j2 j2Var) {
        int h7 = ((q.e) this.f2618c).h() - 1;
        while (true) {
            if (h7 < 0) {
                break;
            }
            if (j2Var == ((q.e) this.f2618c).i(h7)) {
                q.e eVar = (q.e) this.f2618c;
                Object[] objArr = eVar.f8387c;
                Object obj = objArr[h7];
                Object obj2 = q.e.f8384e;
                if (obj != obj2) {
                    objArr[h7] = obj2;
                    eVar.f8385a = true;
                }
            } else {
                h7--;
            }
        }
        v2 v2Var = (v2) ((q.k) this.f2617b).remove(j2Var);
        if (v2Var != null) {
            v2Var.f2696a = 0;
            v2Var.f2697b = null;
            v2Var.f2698c = null;
            v2.f2695d.b(v2Var);
        }
    }
}
