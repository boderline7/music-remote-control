package com.google.android.material.carousel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.widget.u;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.e2;
import androidx.recyclerview.widget.g2;
import androidx.recyclerview.widget.r1;
import androidx.recyclerview.widget.s1;
import androidx.recyclerview.widget.z1;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import l6.f;
import n1.z;
import p0.d1;
import p0.m0;
import r2.a;
import y2.b;
import y2.c;
import y2.d;
import y2.e;
import y2.h;
import y2.i;
import y2.j;
import y2.k;
import y2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class CarouselLayoutManager extends r1 implements e2 {
    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_START = 0;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public int A;
    public int B;
    public final int C;

    /* renamed from: p, reason: collision with root package name */
    public int f3489p;

    /* renamed from: q, reason: collision with root package name */
    public int f3490q;

    /* renamed from: r, reason: collision with root package name */
    public int f3491r;

    /* renamed from: s, reason: collision with root package name */
    public final e f3492s;

    /* renamed from: t, reason: collision with root package name */
    public final f f3493t;

    /* renamed from: u, reason: collision with root package name */
    public j f3494u;

    /* renamed from: v, reason: collision with root package name */
    public i f3495v;

    /* renamed from: w, reason: collision with root package name */
    public int f3496w;

    /* renamed from: x, reason: collision with root package name */
    public HashMap f3497x;

    /* renamed from: y, reason: collision with root package name */
    public y2.f f3498y;

    /* renamed from: z, reason: collision with root package name */
    public final b f3499z;

    /* JADX WARN: Type inference failed for: r2v0, types: [y2.b] */
    public CarouselLayoutManager() {
        m mVar = new m();
        this.f3492s = new e();
        final int i7 = 0;
        this.f3496w = 0;
        this.f3499z = new View.OnLayoutChangeListener(this) { // from class: y2.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarouselLayoutManager f10130b;

            {
                this.f10130b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
                int i16 = i7;
                CarouselLayoutManager carouselLayoutManager = this.f10130b;
                switch (i16) {
                    case 0:
                        carouselLayoutManager.getClass();
                        if (i8 != i12 || i9 != i13 || i10 != i14 || i11 != i15) {
                            view.post(new androidx.activity.e(7, carouselLayoutManager));
                            break;
                        }
                    default:
                        carouselLayoutManager.getClass();
                        if (i8 != i12 || i9 != i13 || i10 != i14 || i11 != i15) {
                            view.post(new androidx.activity.e(7, carouselLayoutManager));
                            break;
                        }
                }
            }
        };
        this.B = -1;
        this.C = 0;
        this.f3493t = mVar;
        b1();
        d1(0);
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [y2.b] */
    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i7, int i8) {
        this.f3492s = new e();
        this.f3496w = 0;
        final int i9 = 1;
        this.f3499z = new View.OnLayoutChangeListener(this) { // from class: y2.b

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarouselLayoutManager f10130b;

            {
                this.f10130b = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i82, int i92, int i10, int i11, int i12, int i13, int i14, int i15) {
                int i16 = i9;
                CarouselLayoutManager carouselLayoutManager = this.f10130b;
                switch (i16) {
                    case 0:
                        carouselLayoutManager.getClass();
                        if (i82 != i12 || i92 != i13 || i10 != i14 || i11 != i15) {
                            view.post(new androidx.activity.e(7, carouselLayoutManager));
                            break;
                        }
                    default:
                        carouselLayoutManager.getClass();
                        if (i82 != i12 || i92 != i13 || i10 != i14 || i11 != i15) {
                            view.post(new androidx.activity.e(7, carouselLayoutManager));
                            break;
                        }
                }
            }
        };
        this.B = -1;
        this.C = 0;
        this.f3493t = new m();
        b1();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, q2.m.Carousel);
            this.C = obtainStyledAttributes.getInt(q2.m.Carousel_carousel_alignment, 0);
            b1();
            d1(obtainStyledAttributes.getInt(q2.m.RecyclerView_android_orientation, 0));
            obtainStyledAttributes.recycle();
        }
    }

    public static float P0(float f7, i6.i iVar) {
        h hVar = (h) iVar.f6848b;
        float f8 = hVar.f10153d;
        h hVar2 = (h) iVar.f6849c;
        return a.b(f8, hVar2.f10153d, hVar.f10151b, hVar2.f10151b, f7);
    }

    public static i6.i T0(float f7, List list, boolean z6) {
        float f8 = Float.MAX_VALUE;
        int i7 = -1;
        int i8 = -1;
        int i9 = -1;
        int i10 = -1;
        float f9 = -3.4028235E38f;
        float f10 = Float.MAX_VALUE;
        float f11 = Float.MAX_VALUE;
        for (int i11 = 0; i11 < list.size(); i11++) {
            h hVar = (h) list.get(i11);
            float f12 = z6 ? hVar.f10151b : hVar.f10150a;
            float abs = Math.abs(f12 - f7);
            if (f12 <= f7 && abs <= f8) {
                i7 = i11;
                f8 = abs;
            }
            if (f12 > f7 && abs <= f10) {
                i9 = i11;
                f10 = abs;
            }
            if (f12 <= f11) {
                i8 = i11;
                f11 = f12;
            }
            if (f12 > f9) {
                i10 = i11;
                f9 = f12;
            }
        }
        if (i7 == -1) {
            i7 = i8;
        }
        if (i9 == -1) {
            i9 = i10;
        }
        return new i6.i((h) list.get(i7), (h) list.get(i9));
    }

    @Override // androidx.recyclerview.widget.r1
    public final void A(View view, Rect rect) {
        RecyclerView.L(view, rect);
        float centerY = rect.centerY();
        if (U0()) {
            centerY = rect.centerX();
        }
        float P0 = P0(centerY, T0(centerY, this.f3495v.f10157b, true));
        boolean U0 = U0();
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        float width = U0 ? (rect.width() - P0) / 2.0f : 0.0f;
        if (!U0()) {
            f7 = (rect.height() - P0) / 2.0f;
        }
        rect.set((int) (rect.left + width), (int) (rect.top + f7), (int) (rect.right - width), (int) (rect.bottom - f7));
    }

    @Override // androidx.recyclerview.widget.r1
    public final void C0(RecyclerView recyclerView, int i7) {
        c cVar = new c(this, recyclerView.getContext(), 0);
        cVar.setTargetPosition(i7);
        D0(cVar);
    }

    public final void F0(View view, int i7, d dVar) {
        float f7 = this.f3495v.f10156a / 2.0f;
        int i8 = 0;
        b(view, i7, false);
        float f8 = dVar.f10134b;
        int i9 = (int) (f8 - f7);
        int i10 = (int) (f8 + f7);
        y2.f fVar = this.f3498y;
        int i11 = fVar.f10139b;
        CarouselLayoutManager carouselLayoutManager = fVar.f10140c;
        switch (i11) {
            case 0:
                int b7 = fVar.b();
                int c7 = fVar.c();
                carouselLayoutManager.getClass();
                r1.Q(view, b7, i9, c7, i10);
                break;
            default:
                switch (i11) {
                    case 0:
                        break;
                    default:
                        i8 = carouselLayoutManager.J();
                        break;
                }
                int a7 = fVar.a();
                carouselLayoutManager.getClass();
                r1.Q(view, i9, i8, i10, a7);
                break;
        }
        e1(view, dVar.f10133a, (i6.i) dVar.f10136d);
    }

    public final float G0(float f7, float f8) {
        return V0() ? f7 - f8 : f7 + f8;
    }

    public final void H0(int i7, z1 z1Var, g2 g2Var) {
        float K0 = K0(i7);
        while (i7 < g2Var.b()) {
            d Y0 = Y0(z1Var, K0, i7);
            Object obj = Y0.f10136d;
            float f7 = Y0.f10134b;
            if (W0(f7, (i6.i) obj)) {
                return;
            }
            K0 = G0(K0, this.f3495v.f10156a);
            if (!X0(f7, (i6.i) obj)) {
                F0((View) Y0.f10135c, -1, Y0);
            }
            i7++;
        }
    }

    public final void I0(int i7, z1 z1Var) {
        float K0 = K0(i7);
        while (i7 >= 0) {
            d Y0 = Y0(z1Var, K0, i7);
            i6.i iVar = (i6.i) Y0.f10136d;
            float f7 = Y0.f10134b;
            if (X0(f7, iVar)) {
                return;
            }
            float f8 = this.f3495v.f10156a;
            K0 = V0() ? K0 + f8 : K0 - f8;
            if (!W0(f7, iVar)) {
                F0((View) Y0.f10135c, 0, Y0);
            }
            i7--;
        }
    }

    public final float J0(View view, float f7, i6.i iVar) {
        int i7;
        int i8;
        h hVar = (h) iVar.f6848b;
        float f8 = hVar.f10151b;
        h hVar2 = (h) iVar.f6849c;
        float b7 = a.b(f8, hVar2.f10151b, hVar.f10150a, hVar2.f10150a, f7);
        if (((h) iVar.f6849c) != this.f3495v.b() && ((h) iVar.f6848b) != this.f3495v.d()) {
            return b7;
        }
        s1 s1Var = (s1) view.getLayoutParams();
        switch (this.f3498y.f10139b) {
            case 0:
                i7 = ((ViewGroup.MarginLayoutParams) s1Var).topMargin;
                i8 = ((ViewGroup.MarginLayoutParams) s1Var).bottomMargin;
                break;
            default:
                i7 = ((ViewGroup.MarginLayoutParams) s1Var).rightMargin;
                i8 = ((ViewGroup.MarginLayoutParams) s1Var).leftMargin;
                break;
        }
        float f9 = (i7 + i8) / this.f3495v.f10156a;
        h hVar3 = (h) iVar.f6849c;
        return b7 + (((1.0f - hVar3.f10152c) + f9) * (f7 - hVar3.f10150a));
    }

    public final float K0(int i7) {
        return G0(Q0() - this.f3489p, this.f3495v.f10156a * i7);
    }

    public final void L0(z1 z1Var, g2 g2Var) {
        while (w() > 0) {
            View v6 = v(0);
            float N0 = N0(v6);
            if (!X0(N0, T0(N0, this.f3495v.f10157b, true))) {
                break;
            } else {
                n0(v6, z1Var);
            }
        }
        while (w() - 1 >= 0) {
            View v7 = v(w() - 1);
            float N02 = N0(v7);
            if (!W0(N02, T0(N02, this.f3495v.f10157b, true))) {
                break;
            } else {
                n0(v7, z1Var);
            }
        }
        if (w() == 0) {
            I0(this.f3496w - 1, z1Var);
            H0(this.f3496w, z1Var, g2Var);
        } else {
            int K = r1.K(v(0));
            int K2 = r1.K(v(w() - 1));
            I0(K - 1, z1Var);
            H0(K2 + 1, z1Var, g2Var);
        }
    }

    public final int M0() {
        return U0() ? this.f2634n : this.f2635o;
    }

    public final float N0(View view) {
        RecyclerView.L(view, new Rect());
        return U0() ? r0.centerX() : r0.centerY();
    }

    public final i O0(int i7) {
        i iVar;
        HashMap hashMap = this.f3497x;
        return (hashMap == null || (iVar = (i) hashMap.get(Integer.valueOf(z.y(i7, 0, Math.max(0, E() + (-1)))))) == null) ? this.f3494u.f10160a : iVar;
    }

    public final int Q0() {
        y2.f fVar = this.f3498y;
        int i7 = fVar.f10139b;
        CarouselLayoutManager carouselLayoutManager = fVar.f10140c;
        switch (i7) {
            case 0:
                switch (i7) {
                    case 0:
                        return 0;
                    default:
                        return carouselLayoutManager.J();
                }
            default:
                return carouselLayoutManager.V0() ? fVar.c() : fVar.b();
        }
    }

    public final int R0(int i7, i iVar) {
        if (!V0()) {
            return (int) ((iVar.f10156a / 2.0f) + ((i7 * iVar.f10156a) - iVar.a().f10150a));
        }
        float M0 = M0() - iVar.c().f10150a;
        float f7 = iVar.f10156a;
        return (int) ((M0 - (i7 * f7)) - (f7 / 2.0f));
    }

    public final int S0(int i7, i iVar) {
        int i8 = u.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (h hVar : iVar.f10157b.subList(iVar.f10158c, iVar.f10159d + 1)) {
            float f7 = iVar.f10156a;
            float f8 = (f7 / 2.0f) + (i7 * f7);
            int M0 = (V0() ? (int) ((M0() - hVar.f10150a) - f8) : (int) (f8 - hVar.f10150a)) - this.f3489p;
            if (Math.abs(i8) > Math.abs(M0)) {
                i8 = M0;
            }
        }
        return i8;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void T(RecyclerView recyclerView) {
        b1();
        recyclerView.addOnLayoutChangeListener(this.f3499z);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void U(RecyclerView recyclerView) {
        recyclerView.removeOnLayoutChangeListener(this.f3499z);
    }

    public final boolean U0() {
        return this.f3498y.f2100a == 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0027, code lost:
    
        if (r9 != 1) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0030, code lost:
    
        if (V0() != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0033, code lost:
    
        if (r9 == 1) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x003c, code lost:
    
        if (V0() != false) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    @Override // androidx.recyclerview.widget.r1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View V(android.view.View r6, int r7, androidx.recyclerview.widget.z1 r8, androidx.recyclerview.widget.g2 r9) {
        /*
            r5 = this;
            int r9 = r5.w()
            r0 = 0
            if (r9 != 0) goto L8
            return r0
        L8:
            y2.f r9 = r5.f3498y
            int r9 = r9.f2100a
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = -1
            r3 = 1
            if (r7 == r3) goto L40
            r4 = 2
            if (r7 == r4) goto L3e
            r4 = 17
            if (r7 == r4) goto L36
            r4 = 33
            if (r7 == r4) goto L33
            r4 = 66
            if (r7 == r4) goto L2a
            r4 = 130(0x82, float:1.82E-43)
            if (r7 == r4) goto L27
        L25:
            r7 = r1
            goto L41
        L27:
            if (r9 != r3) goto L25
            goto L3e
        L2a:
            if (r9 != 0) goto L25
            boolean r7 = r5.V0()
            if (r7 == 0) goto L3e
            goto L40
        L33:
            if (r9 != r3) goto L25
            goto L40
        L36:
            if (r9 != 0) goto L25
            boolean r7 = r5.V0()
            if (r7 == 0) goto L40
        L3e:
            r7 = r3
            goto L41
        L40:
            r7 = r2
        L41:
            if (r7 != r1) goto L44
            return r0
        L44:
            r9 = 0
            if (r7 != r2) goto L80
            int r6 = androidx.recyclerview.widget.r1.K(r6)
            if (r6 != 0) goto L4e
            return r0
        L4e:
            android.view.View r6 = r5.v(r9)
            int r6 = androidx.recyclerview.widget.r1.K(r6)
            int r6 = r6 - r3
            if (r6 < 0) goto L6f
            int r7 = r5.E()
            if (r6 < r7) goto L60
            goto L6f
        L60:
            float r7 = r5.K0(r6)
            y2.d r6 = r5.Y0(r8, r7, r6)
            java.lang.Object r7 = r6.f10135c
            android.view.View r7 = (android.view.View) r7
            r5.F0(r7, r9, r6)
        L6f:
            boolean r6 = r5.V0()
            if (r6 == 0) goto L7b
            int r6 = r5.w()
            int r9 = r6 + (-1)
        L7b:
            android.view.View r6 = r5.v(r9)
            goto Lc3
        L80:
            int r6 = androidx.recyclerview.widget.r1.K(r6)
            int r7 = r5.E()
            int r7 = r7 - r3
            if (r6 != r7) goto L8c
            return r0
        L8c:
            int r6 = r5.w()
            int r6 = r6 - r3
            android.view.View r6 = r5.v(r6)
            int r6 = androidx.recyclerview.widget.r1.K(r6)
            int r6 = r6 + r3
            if (r6 < 0) goto Lb2
            int r7 = r5.E()
            if (r6 < r7) goto La3
            goto Lb2
        La3:
            float r7 = r5.K0(r6)
            y2.d r6 = r5.Y0(r8, r7, r6)
            java.lang.Object r7 = r6.f10135c
            android.view.View r7 = (android.view.View) r7
            r5.F0(r7, r2, r6)
        Lb2:
            boolean r6 = r5.V0()
            if (r6 == 0) goto Lb9
            goto Lbf
        Lb9:
            int r6 = r5.w()
            int r9 = r6 + (-1)
        Lbf:
            android.view.View r6 = r5.v(r9)
        Lc3:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.V(android.view.View, int, androidx.recyclerview.widget.z1, androidx.recyclerview.widget.g2):android.view.View");
    }

    public final boolean V0() {
        return U0() && F() == 1;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void W(AccessibilityEvent accessibilityEvent) {
        super.W(accessibilityEvent);
        if (w() > 0) {
            accessibilityEvent.setFromIndex(r1.K(v(0)));
            accessibilityEvent.setToIndex(r1.K(v(w() - 1)));
        }
    }

    public final boolean W0(float f7, i6.i iVar) {
        float P0 = P0(f7, iVar) / 2.0f;
        float f8 = V0() ? f7 + P0 : f7 - P0;
        if (V0()) {
            if (f8 >= CropImageView.DEFAULT_ASPECT_RATIO) {
                return false;
            }
        } else if (f8 <= M0()) {
            return false;
        }
        return true;
    }

    public final boolean X0(float f7, i6.i iVar) {
        float G0 = G0(f7, P0(f7, iVar) / 2.0f);
        if (V0()) {
            if (G0 <= M0()) {
                return false;
            }
        } else if (G0 >= CropImageView.DEFAULT_ASPECT_RATIO) {
            return false;
        }
        return true;
    }

    public final d Y0(z1 z1Var, float f7, int i7) {
        View view = z1Var.i(i7, Long.MAX_VALUE).itemView;
        Z0(view);
        float G0 = G0(f7, this.f3495v.f10156a / 2.0f);
        i6.i T0 = T0(G0, this.f3495v.f10157b, false);
        float J0 = J0(view, G0, T0);
        d dVar = new d();
        dVar.f10135c = view;
        dVar.f10133a = G0;
        dVar.f10134b = J0;
        dVar.f10136d = T0;
        return dVar;
    }

    public final void Z0(View view) {
        if (!(view instanceof k)) {
            throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
        }
        s1 s1Var = (s1) view.getLayoutParams();
        Rect rect = new Rect();
        d(view, rect);
        int i7 = rect.left + rect.right;
        int i8 = rect.top + rect.bottom;
        j jVar = this.f3494u;
        view.measure(r1.x(this.f2634n, this.f2632l, I() + H() + ((ViewGroup.MarginLayoutParams) s1Var).leftMargin + ((ViewGroup.MarginLayoutParams) s1Var).rightMargin + i7, (int) ((jVar == null || this.f3498y.f2100a != 0) ? ((ViewGroup.MarginLayoutParams) s1Var).width : jVar.f10160a.f10156a), U0()), r1.x(this.f2635o, this.f2633m, G() + J() + ((ViewGroup.MarginLayoutParams) s1Var).topMargin + ((ViewGroup.MarginLayoutParams) s1Var).bottomMargin + i8, (int) ((jVar == null || this.f3498y.f2100a != 1) ? ((ViewGroup.MarginLayoutParams) s1Var).height : jVar.f10160a.f10156a), f()));
    }

    @Override // androidx.recyclerview.widget.e2
    public final PointF a(int i7) {
        if (this.f3494u == null) {
            return null;
        }
        int R0 = R0(i7, O0(i7)) - this.f3489p;
        return U0() ? new PointF(R0, CropImageView.DEFAULT_ASPECT_RATIO) : new PointF(CropImageView.DEFAULT_ASPECT_RATIO, R0);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void a0(int i7, int i8) {
        int E = E();
        int i9 = this.A;
        if (E == i9 || this.f3494u == null) {
            return;
        }
        if (this.f3493t.C0(this, i9)) {
            b1();
        }
        this.A = E;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00cb, code lost:
    
        if (r7 == r9) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01f3, code lost:
    
        if (r6 == r8) goto L109;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a1(androidx.recyclerview.widget.z1 r29) {
        /*
            Method dump skipped, instructions count: 678
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.a1(androidx.recyclerview.widget.z1):void");
    }

    public final void b1() {
        this.f3494u = null;
        q0();
    }

    public final int c1(int i7, z1 z1Var, g2 g2Var) {
        if (w() == 0 || i7 == 0) {
            return 0;
        }
        if (this.f3494u == null) {
            a1(z1Var);
        }
        int i8 = this.f3489p;
        int i9 = this.f3490q;
        int i10 = this.f3491r;
        int i11 = i8 + i7;
        if (i11 < i9) {
            i7 = i9 - i8;
        } else if (i11 > i10) {
            i7 = i10 - i8;
        }
        this.f3489p = i8 + i7;
        f1(this.f3494u);
        float f7 = this.f3495v.f10156a / 2.0f;
        float K0 = K0(r1.K(v(0)));
        Rect rect = new Rect();
        float f8 = V0() ? this.f3495v.c().f10151b : this.f3495v.a().f10151b;
        float f9 = Float.MAX_VALUE;
        for (int i12 = 0; i12 < w(); i12++) {
            View v6 = v(i12);
            float G0 = G0(K0, f7);
            i6.i T0 = T0(G0, this.f3495v.f10157b, false);
            float J0 = J0(v6, G0, T0);
            RecyclerView.L(v6, rect);
            e1(v6, G0, T0);
            switch (this.f3498y.f10139b) {
                case 0:
                    v6.offsetTopAndBottom((int) (J0 - (rect.top + f7)));
                    break;
                default:
                    v6.offsetLeftAndRight((int) (J0 - (rect.left + f7)));
                    break;
            }
            float abs = Math.abs(f8 - J0);
            if (abs < f9) {
                this.B = r1.K(v6);
                f9 = abs;
            }
            K0 = G0(K0, this.f3495v.f10156a);
        }
        L0(z1Var, g2Var);
        return i7;
    }

    @Override // androidx.recyclerview.widget.r1
    public final void d0(int i7, int i8) {
        int E = E();
        int i9 = this.A;
        if (E == i9 || this.f3494u == null) {
            return;
        }
        if (this.f3493t.C0(this, i9)) {
            b1();
        }
        this.A = E;
    }

    public final void d1(int i7) {
        y2.f fVar;
        if (i7 != 0 && i7 != 1) {
            throw new IllegalArgumentException(a.a.d("invalid orientation:", i7));
        }
        c(null);
        y2.f fVar2 = this.f3498y;
        if (fVar2 == null || i7 != fVar2.f2100a) {
            if (i7 == 0) {
                fVar = new y2.f(0, this, 1);
            } else {
                if (i7 != 1) {
                    throw new IllegalArgumentException("invalid orientation");
                }
                fVar = new y2.f(1, this, 0);
            }
            this.f3498y = fVar;
            b1();
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean e() {
        return U0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e1(View view, float f7, i6.i iVar) {
        RectF rectF;
        int i7;
        if (view instanceof k) {
            h hVar = (h) iVar.f6848b;
            float f8 = hVar.f10152c;
            h hVar2 = (h) iVar.f6849c;
            float b7 = a.b(f8, hVar2.f10152c, hVar.f10150a, hVar2.f10150a, f7);
            float height = view.getHeight();
            float width = view.getWidth();
            float b8 = a.b(CropImageView.DEFAULT_ASPECT_RATIO, width / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, b7);
            float b9 = a.b(CropImageView.DEFAULT_ASPECT_RATIO, height / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, b7);
            switch (this.f3498y.f10139b) {
                case 0:
                    rectF = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, b9, width, height - b9);
                    break;
                default:
                    rectF = new RectF(b8, CropImageView.DEFAULT_ASPECT_RATIO, width - b8, height);
                    break;
            }
            float J0 = J0(view, f7, iVar);
            RectF rectF2 = new RectF(J0 - (rectF.width() / 2.0f), J0 - (rectF.height() / 2.0f), (rectF.width() / 2.0f) + J0, (rectF.height() / 2.0f) + J0);
            float b10 = this.f3498y.b();
            y2.f fVar = this.f3498y;
            switch (fVar.f10139b) {
                case 0:
                    i7 = 0;
                    break;
                default:
                    i7 = fVar.f10140c.J();
                    break;
            }
            RectF rectF3 = new RectF(b10, i7, this.f3498y.c(), this.f3498y.a());
            this.f3493t.getClass();
            switch (this.f3498y.f10139b) {
                case 0:
                    float f9 = rectF2.top;
                    float f10 = rectF3.top;
                    if (f9 < f10 && rectF2.bottom > f10) {
                        float f11 = f10 - f9;
                        rectF.top += f11;
                        rectF3.top += f11;
                    }
                    float f12 = rectF2.bottom;
                    float f13 = rectF3.bottom;
                    if (f12 > f13 && rectF2.top < f13) {
                        float f14 = f12 - f13;
                        rectF.bottom = Math.max(rectF.bottom - f14, rectF.top);
                        rectF2.bottom = Math.max(rectF2.bottom - f14, rectF2.top);
                        break;
                    }
                    break;
                default:
                    float f15 = rectF2.left;
                    float f16 = rectF3.left;
                    if (f15 < f16 && rectF2.right > f16) {
                        float f17 = f16 - f15;
                        rectF.left += f17;
                        rectF2.left += f17;
                    }
                    float f18 = rectF2.right;
                    float f19 = rectF3.right;
                    if (f18 > f19 && rectF2.left < f19) {
                        float f20 = f18 - f19;
                        rectF.right = Math.max(rectF.right - f20, rectF.left);
                        rectF2.right = Math.max(rectF2.right - f20, rectF2.left);
                        break;
                    }
                    break;
            }
            switch (this.f3498y.f10139b) {
                case 0:
                    if (rectF2.bottom <= rectF3.top) {
                        float floor = ((float) Math.floor(rectF.bottom)) - 1.0f;
                        rectF.bottom = floor;
                        rectF.top = Math.min(rectF.top, floor);
                    }
                    if (rectF2.top >= rectF3.bottom) {
                        float ceil = ((float) Math.ceil(rectF.top)) + 1.0f;
                        rectF.top = ceil;
                        rectF.bottom = Math.max(ceil, rectF.bottom);
                        break;
                    }
                    break;
                default:
                    if (rectF2.right <= rectF3.left) {
                        float floor2 = ((float) Math.floor(rectF.right)) - 1.0f;
                        rectF.right = floor2;
                        rectF.left = Math.min(rectF.left, floor2);
                    }
                    if (rectF2.left >= rectF3.right) {
                        float ceil2 = ((float) Math.ceil(rectF.left)) + 1.0f;
                        rectF.left = ceil2;
                        rectF.right = Math.max(ceil2, rectF.right);
                        break;
                    }
                    break;
            }
            ((k) view).setMaskRectF(rectF);
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean f() {
        return !U0();
    }

    @Override // androidx.recyclerview.widget.r1
    public final void f0(z1 z1Var, g2 g2Var) {
        i iVar;
        int i7;
        i iVar2;
        int i8;
        int a7;
        if (g2Var.b() <= 0 || M0() <= CropImageView.DEFAULT_ASPECT_RATIO) {
            l0(z1Var);
            this.f3496w = 0;
            return;
        }
        boolean V0 = V0();
        boolean z6 = this.f3494u == null;
        if (z6) {
            a1(z1Var);
        }
        j jVar = this.f3494u;
        boolean V02 = V0();
        if (V02) {
            List list = jVar.f10162c;
            iVar = (i) list.get(list.size() - 1);
        } else {
            List list2 = jVar.f10161b;
            iVar = (i) list2.get(list2.size() - 1);
        }
        h c7 = V02 ? iVar.c() : iVar.a();
        RecyclerView recyclerView = this.f2622b;
        if (recyclerView != null) {
            int i9 = d1.OVER_SCROLL_ALWAYS;
            i7 = m0.f(recyclerView);
        } else {
            i7 = 0;
        }
        float f7 = i7 * (V02 ? 1 : -1);
        float f8 = c7.f10150a;
        float f9 = iVar.f10156a / 2.0f;
        int Q0 = (int) ((f7 + Q0()) - (V0() ? f8 + f9 : f8 - f9));
        j jVar2 = this.f3494u;
        boolean V03 = V0();
        if (V03) {
            List list3 = jVar2.f10161b;
            iVar2 = (i) list3.get(list3.size() - 1);
        } else {
            List list4 = jVar2.f10162c;
            iVar2 = (i) list4.get(list4.size() - 1);
        }
        h a8 = V03 ? iVar2.a() : iVar2.c();
        float b7 = (g2Var.b() - 1) * iVar2.f10156a;
        RecyclerView recyclerView2 = this.f2622b;
        if (recyclerView2 != null) {
            int i10 = d1.OVER_SCROLL_ALWAYS;
            i8 = m0.e(recyclerView2);
        } else {
            i8 = 0;
        }
        float f10 = (b7 + i8) * (V03 ? -1.0f : 1.0f);
        float Q02 = a8.f10150a - Q0();
        y2.f fVar = this.f3498y;
        switch (fVar.f10139b) {
            case 0:
                a7 = fVar.a();
                break;
            default:
                if (fVar.f10140c.V0()) {
                    a7 = fVar.b();
                    break;
                } else {
                    a7 = fVar.c();
                    break;
                }
        }
        int i11 = (int) ((f10 - Q02) + (a7 - a8.f10150a));
        int min = V03 ? Math.min(0, i11) : Math.max(0, i11);
        this.f3490q = V0 ? min : Q0;
        if (V0) {
            min = Q0;
        }
        this.f3491r = min;
        if (z6) {
            this.f3489p = Q0;
            j jVar3 = this.f3494u;
            int E = E();
            int i12 = this.f3490q;
            int i13 = this.f3491r;
            boolean V04 = V0();
            float f11 = jVar3.f10160a.f10156a;
            HashMap hashMap = new HashMap();
            int i14 = 0;
            for (int i15 = 0; i15 < E; i15++) {
                int i16 = V04 ? (E - i15) - 1 : i15;
                float f12 = i16 * f11 * (V04 ? -1 : 1);
                float f13 = i13 - jVar3.f10166g;
                List list5 = jVar3.f10162c;
                if (f12 > f13 || i15 >= E - list5.size()) {
                    hashMap.put(Integer.valueOf(i16), (i) list5.get(z.y(i14, 0, list5.size() - 1)));
                    i14++;
                }
            }
            int i17 = 0;
            for (int i18 = E - 1; i18 >= 0; i18--) {
                int i19 = V04 ? (E - i18) - 1 : i18;
                float f14 = i19 * f11 * (V04 ? -1 : 1);
                float f15 = i12 + jVar3.f10165f;
                List list6 = jVar3.f10161b;
                if (f14 < f15 || i18 < list6.size()) {
                    hashMap.put(Integer.valueOf(i19), (i) list6.get(z.y(i17, 0, list6.size() - 1)));
                    i17++;
                }
            }
            this.f3497x = hashMap;
            int i20 = this.B;
            if (i20 != -1) {
                this.f3489p = R0(i20, O0(i20));
            }
        }
        int i21 = this.f3489p;
        int i22 = this.f3490q;
        int i23 = this.f3491r;
        this.f3489p = (i21 < i22 ? i22 - i21 : i21 > i23 ? i23 - i21 : 0) + i21;
        this.f3496w = z.y(this.f3496w, 0, g2Var.b());
        f1(this.f3494u);
        q(z1Var);
        L0(z1Var, g2Var);
        this.A = E();
    }

    public final void f1(j jVar) {
        i iVar;
        int i7 = this.f3491r;
        int i8 = this.f3490q;
        if (i7 <= i8) {
            if (V0()) {
                iVar = (i) jVar.f10162c.get(r4.size() - 1);
            } else {
                iVar = (i) jVar.f10161b.get(r4.size() - 1);
            }
            this.f3495v = iVar;
        } else {
            this.f3495v = jVar.a(this.f3489p, i8, i7);
        }
        List list = this.f3495v.f10157b;
        e eVar = this.f3492s;
        eVar.getClass();
        eVar.f10138b = Collections.unmodifiableList(list);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void g0(g2 g2Var) {
        if (w() == 0) {
            this.f3496w = 0;
        } else {
            this.f3496w = r1.K(v(0));
        }
    }

    @Override // androidx.recyclerview.widget.r1
    public final int k(g2 g2Var) {
        if (w() == 0 || this.f3494u == null || E() <= 1) {
            return 0;
        }
        return (int) (this.f2634n * (this.f3494u.f10160a.f10156a / m(g2Var)));
    }

    @Override // androidx.recyclerview.widget.r1
    public final int l(g2 g2Var) {
        return this.f3489p;
    }

    @Override // androidx.recyclerview.widget.r1
    public final int m(g2 g2Var) {
        return this.f3491r - this.f3490q;
    }

    @Override // androidx.recyclerview.widget.r1
    public final int n(g2 g2Var) {
        if (w() == 0 || this.f3494u == null || E() <= 1) {
            return 0;
        }
        return (int) (this.f2635o * (this.f3494u.f10160a.f10156a / p(g2Var)));
    }

    @Override // androidx.recyclerview.widget.r1
    public final int o(g2 g2Var) {
        return this.f3489p;
    }

    @Override // androidx.recyclerview.widget.r1
    public final int p(g2 g2Var) {
        return this.f3491r - this.f3490q;
    }

    @Override // androidx.recyclerview.widget.r1
    public final boolean p0(RecyclerView recyclerView, View view, Rect rect, boolean z6, boolean z7) {
        int S0;
        if (this.f3494u == null || (S0 = S0(r1.K(view), O0(r1.K(view)))) == 0) {
            return false;
        }
        int i7 = this.f3489p;
        int i8 = this.f3490q;
        int i9 = this.f3491r;
        int i10 = i7 + S0;
        if (i10 < i8) {
            S0 = i8 - i7;
        } else if (i10 > i9) {
            S0 = i9 - i7;
        }
        int S02 = S0(r1.K(view), this.f3494u.a(i7 + S0, i8, i9));
        if (U0()) {
            recyclerView.scrollBy(S02, 0);
            return true;
        }
        recyclerView.scrollBy(0, S02);
        return true;
    }

    @Override // androidx.recyclerview.widget.r1
    public final int r0(int i7, z1 z1Var, g2 g2Var) {
        if (U0()) {
            return c1(i7, z1Var, g2Var);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.r1
    public final s1 s() {
        return new s1(-2, -2);
    }

    @Override // androidx.recyclerview.widget.r1
    public final void s0(int i7) {
        this.B = i7;
        if (this.f3494u == null) {
            return;
        }
        this.f3489p = R0(i7, O0(i7));
        this.f3496w = z.y(i7, 0, Math.max(0, E() - 1));
        f1(this.f3494u);
        q0();
    }

    @Override // androidx.recyclerview.widget.r1
    public final int t0(int i7, z1 z1Var, g2 g2Var) {
        if (f()) {
            return c1(i7, z1Var, g2Var);
        }
        return 0;
    }
}
