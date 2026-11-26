package androidx.viewpager2.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.w1;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class d extends w1 {

    /* renamed from: a, reason: collision with root package name */
    public j f2895a;

    /* renamed from: b, reason: collision with root package name */
    public final ViewPager2 f2896b;

    /* renamed from: c, reason: collision with root package name */
    public final RecyclerView f2897c;

    /* renamed from: d, reason: collision with root package name */
    public final LinearLayoutManager f2898d;

    /* renamed from: e, reason: collision with root package name */
    public int f2899e;

    /* renamed from: f, reason: collision with root package name */
    public int f2900f;

    /* renamed from: g, reason: collision with root package name */
    public final c f2901g;

    /* renamed from: h, reason: collision with root package name */
    public int f2902h;

    /* renamed from: i, reason: collision with root package name */
    public int f2903i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2904j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f2905k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f2906l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f2907m;

    public d(ViewPager2 viewPager2) {
        this.f2896b = viewPager2;
        o oVar = viewPager2.f2877j;
        this.f2897c = oVar;
        this.f2898d = (LinearLayoutManager) oVar.getLayoutManager();
        this.f2901g = new c();
        d();
    }

    @Override // androidx.recyclerview.widget.w1
    public final void a(int i7, RecyclerView recyclerView) {
        j jVar;
        j jVar2;
        int i8 = this.f2899e;
        boolean z6 = true;
        if (!(i8 == 1 && this.f2900f == 1) && i7 == 1) {
            this.f2907m = false;
            this.f2899e = 1;
            int i9 = this.f2903i;
            if (i9 != -1) {
                this.f2902h = i9;
                this.f2903i = -1;
            } else if (this.f2902h == -1) {
                this.f2902h = this.f2898d.P0();
            }
            c(1);
            return;
        }
        if ((i8 == 1 || i8 == 4) && i7 == 2) {
            if (this.f2905k) {
                c(2);
                this.f2904j = true;
                return;
            }
            return;
        }
        if (i8 != 1 && i8 != 4) {
            z6 = false;
        }
        c cVar = this.f2901g;
        if (z6 && i7 == 0) {
            e();
            if (!this.f2905k) {
                int i10 = cVar.f2892a;
                if (i10 != -1 && (jVar2 = this.f2895a) != null) {
                    jVar2.onPageScrolled(i10, CropImageView.DEFAULT_ASPECT_RATIO, 0);
                }
            } else if (cVar.f2894c == 0) {
                int i11 = this.f2902h;
                int i12 = cVar.f2892a;
                if (i11 != i12 && (jVar = this.f2895a) != null) {
                    jVar.onPageSelected(i12);
                }
            }
            c(0);
            d();
        }
        if (this.f2899e == 2 && i7 == 0 && this.f2906l) {
            e();
            if (cVar.f2894c == 0) {
                int i13 = this.f2903i;
                int i14 = cVar.f2892a;
                if (i13 != i14) {
                    if (i14 == -1) {
                        i14 = 0;
                    }
                    j jVar3 = this.f2895a;
                    if (jVar3 != null) {
                        jVar3.onPageSelected(i14);
                    }
                }
                c(0);
                d();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
    
        if ((r7 < 0) == (r5.f2896b.f2874g.F() == 1)) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    @Override // androidx.recyclerview.widget.w1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(androidx.recyclerview.widget.RecyclerView r6, int r7, int r8) {
        /*
            r5 = this;
            r6 = 1
            r5.f2905k = r6
            r5.e()
            boolean r0 = r5.f2904j
            androidx.viewpager2.widget.c r1 = r5.f2901g
            r2 = -1
            r3 = 0
            if (r0 == 0) goto L40
            r5.f2904j = r3
            if (r8 > 0) goto L28
            if (r8 != 0) goto L30
            if (r7 >= 0) goto L18
            r7 = r6
            goto L19
        L18:
            r7 = r3
        L19:
            androidx.viewpager2.widget.ViewPager2 r8 = r5.f2896b
            androidx.viewpager2.widget.i r8 = r8.f2874g
            int r8 = r8.F()
            if (r8 != r6) goto L25
            r8 = r6
            goto L26
        L25:
            r8 = r3
        L26:
            if (r7 != r8) goto L30
        L28:
            int r7 = r1.f2894c
            if (r7 == 0) goto L30
            int r7 = r1.f2892a
            int r7 = r7 + r6
            goto L32
        L30:
            int r7 = r1.f2892a
        L32:
            r5.f2903i = r7
            int r8 = r5.f2902h
            if (r8 == r7) goto L50
            androidx.viewpager2.widget.j r8 = r5.f2895a
            if (r8 == 0) goto L50
            r8.onPageSelected(r7)
            goto L50
        L40:
            int r7 = r5.f2899e
            if (r7 != 0) goto L50
            int r7 = r1.f2892a
            if (r7 != r2) goto L49
            r7 = r3
        L49:
            androidx.viewpager2.widget.j r8 = r5.f2895a
            if (r8 == 0) goto L50
            r8.onPageSelected(r7)
        L50:
            int r7 = r1.f2892a
            if (r7 != r2) goto L55
            r7 = r3
        L55:
            float r8 = r1.f2893b
            int r0 = r1.f2894c
            androidx.viewpager2.widget.j r4 = r5.f2895a
            if (r4 == 0) goto L60
            r4.onPageScrolled(r7, r8, r0)
        L60:
            int r7 = r1.f2892a
            int r8 = r5.f2903i
            if (r7 == r8) goto L68
            if (r8 != r2) goto L76
        L68:
            int r7 = r1.f2894c
            if (r7 != 0) goto L76
            int r7 = r5.f2900f
            if (r7 == r6) goto L76
            r5.c(r3)
            r5.d()
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.d.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i7) {
        if ((this.f2899e == 3 && this.f2900f == 0) || this.f2900f == i7) {
            return;
        }
        this.f2900f = i7;
        j jVar = this.f2895a;
        if (jVar != null) {
            jVar.onPageScrollStateChanged(i7);
        }
    }

    public final void d() {
        this.f2899e = 0;
        this.f2900f = 0;
        c cVar = this.f2901g;
        cVar.f2892a = -1;
        cVar.f2893b = CropImageView.DEFAULT_ASPECT_RATIO;
        cVar.f2894c = 0;
        this.f2902h = -1;
        this.f2903i = -1;
        this.f2904j = false;
        this.f2905k = false;
        this.f2907m = false;
        this.f2906l = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0138, code lost:
    
        r1 = r0.w();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x013c, code lost:
    
        if (r3 >= r1) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0146, code lost:
    
        if (androidx.viewpager2.widget.a.a(r0.v(r3)) != false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0148, code lost:
    
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0152, code lost:
    
        throw new java.lang.IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x016a, code lost:
    
        throw new java.lang.IllegalStateException(java.lang.String.format(java.util.Locale.US, "Page can only be offset by a positive amount, not by %d", java.lang.Integer.valueOf(r2.f2894c)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x012f, code lost:
    
        if (r7[r4 - 1][1] >= r5) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0136, code lost:
    
        if (r0.w() <= 1) goto L63;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e() {
        /*
            Method dump skipped, instructions count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.d.e():void");
    }
}
