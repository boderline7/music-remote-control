package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.i3;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c0.a;
import c0.b;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import g3.e;
import g3.f;
import g3.g;
import java.util.ArrayList;
import p0.d1;
import p0.m0;
import q2.c;
import q2.l;
import q2.m;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements a {
    public static final int J = l.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    public static final i3 K;
    public static final i3 L;
    public static final i3 M;
    public static final i3 N;
    public int A;
    public int B;
    public final ExtendedFloatingActionButtonBehavior C;
    public boolean D;
    public boolean E;
    public boolean F;
    public ColorStateList G;
    public int H;
    public int I;

    /* renamed from: u, reason: collision with root package name */
    public int f3713u;

    /* renamed from: v, reason: collision with root package name */
    public final e f3714v;

    /* renamed from: w, reason: collision with root package name */
    public final e f3715w;

    /* renamed from: x, reason: collision with root package name */
    public final g f3716x;

    /* renamed from: y, reason: collision with root package name */
    public final f f3717y;

    /* renamed from: z, reason: collision with root package name */
    public final int f3718z;

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends b {

        /* renamed from: a, reason: collision with root package name */
        public Rect f3719a;

        /* renamed from: b, reason: collision with root package name */
        public final boolean f3720b;

        /* renamed from: c, reason: collision with root package name */
        public final boolean f3721c;

        public ExtendedFloatingActionButtonBehavior() {
            this.f3720b = false;
            this.f3721c = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, m.ExtendedFloatingActionButton_Behavior_Layout);
            this.f3720b = obtainStyledAttributes.getBoolean(m.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.f3721c = obtainStyledAttributes.getBoolean(m.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }

        @Override // c0.b
        public final /* bridge */ /* synthetic */ boolean e(View view, Rect rect) {
            return false;
        }

        @Override // c0.b
        public final void g(c0.e eVar) {
            if (eVar.f3014h == 0) {
                eVar.f3014h = 80;
            }
        }

        @Override // c0.b
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                w(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton);
                return false;
            }
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            if (!(layoutParams instanceof c0.e) || !(((c0.e) layoutParams).f3007a instanceof BottomSheetBehavior)) {
                return false;
            }
            x(view2, extendedFloatingActionButton);
            return false;
        }

        @Override // c0.b
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
            ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) view;
            ArrayList o6 = coordinatorLayout.o(extendedFloatingActionButton);
            int size = o6.size();
            for (int i8 = 0; i8 < size; i8++) {
                View view2 = (View) o6.get(i8);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof c0.e) && (((c0.e) layoutParams).f3007a instanceof BottomSheetBehavior) && x(view2, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (w(coordinatorLayout, (AppBarLayout) view2, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.u(i7, extendedFloatingActionButton);
            return true;
        }

        public final boolean w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            c0.e eVar = (c0.e) extendedFloatingActionButton.getLayoutParams();
            if ((!this.f3720b && !this.f3721c) || eVar.f3012f != appBarLayout.getId()) {
                return false;
            }
            if (this.f3719a == null) {
                this.f3719a = new Rect();
            }
            Rect rect = this.f3719a;
            com.google.android.material.internal.e.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, this.f3721c ? 2 : 1);
            } else {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, this.f3721c ? 3 : 0);
            }
            return true;
        }

        public final boolean x(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            c0.e eVar = (c0.e) extendedFloatingActionButton.getLayoutParams();
            if ((!this.f3720b && !this.f3721c) || eVar.f3012f != view.getId()) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((c0.e) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, this.f3721c ? 2 : 1);
            } else {
                ExtendedFloatingActionButton.f(extendedFloatingActionButton, this.f3721c ? 3 : 0);
            }
            return true;
        }
    }

    static {
        Class<Float> cls = Float.class;
        K = new i3(cls, "width", 8);
        L = new i3(cls, "height", 9);
        M = new i3(cls, "paddingStart", 10);
        N = new i3(cls, "paddingEnd", 11);
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.extendedFloatingActionButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ExtendedFloatingActionButton(android.content.Context r18, android.util.AttributeSet r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r7 = r19
            r8 = r20
            int r9 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.J
            r1 = r18
            android.content.Context r1 = t3.a.a(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            r10 = 0
            r0.f3713u = r10
            v2.a r1 = new v2.a
            r11 = 3
            r1.<init>(r11)
            g3.g r12 = new g3.g
            r12.<init>(r0, r1)
            r0.f3716x = r12
            g3.f r13 = new g3.f
            r13.<init>(r0, r1)
            r0.f3717y = r13
            r14 = 1
            r0.D = r14
            r0.E = r10
            r0.F = r10
            android.content.Context r15 = r17.getContext()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior r1 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior
            r1.<init>(r15, r7)
            r0.C = r1
            int[] r3 = q2.m.ExtendedFloatingActionButton
            int[] r6 = new int[r10]
            r1 = r15
            r2 = r19
            r4 = r20
            r5 = r9
            android.content.res.TypedArray r1 = com.google.android.material.internal.e0.d(r1, r2, r3, r4, r5, r6)
            int r2 = q2.m.ExtendedFloatingActionButton_showMotionSpec
            r2.e r2 = r2.e.a(r15, r1, r2)
            int r3 = q2.m.ExtendedFloatingActionButton_hideMotionSpec
            r2.e r3 = r2.e.a(r15, r1, r3)
            int r4 = q2.m.ExtendedFloatingActionButton_extendMotionSpec
            r2.e r4 = r2.e.a(r15, r1, r4)
            int r5 = q2.m.ExtendedFloatingActionButton_shrinkMotionSpec
            r2.e r5 = r2.e.a(r15, r1, r5)
            int r6 = q2.m.ExtendedFloatingActionButton_collapsedSize
            r10 = -1
            int r6 = r1.getDimensionPixelSize(r6, r10)
            r0.f3718z = r6
            int r6 = q2.m.ExtendedFloatingActionButton_extendStrategy
            int r6 = r1.getInt(r6, r14)
            int r10 = p0.d1.OVER_SCROLL_ALWAYS
            int r10 = p0.m0.f(r17)
            r0.A = r10
            int r10 = p0.m0.e(r17)
            r0.B = r10
            v2.a r10 = new v2.a
            r10.<init>(r11)
            g3.e r11 = new g3.e
            g3.c r7 = new g3.c
            r7.<init>(r0, r14)
            i6.i r14 = new i6.i
            r8 = 17
            r14.<init>(r0, r7, r8)
            androidx.appcompat.app.g r8 = new androidx.appcompat.app.g
            r8.<init>(r0, r14, r7)
            r16 = r7
            r7 = 1
            if (r6 == r7) goto La2
            r7 = 2
            if (r6 == r7) goto La0
            r7 = r8
        L9e:
            r6 = 1
            goto La5
        La0:
            r7 = r14
            goto L9e
        La2:
            r6 = r7
            r7 = r16
        La5:
            r11.<init>(r0, r10, r7, r6)
            r0.f3715w = r11
            g3.e r6 = new g3.e
            g3.c r7 = new g3.c
            r8 = 0
            r7.<init>(r0, r8)
            r6.<init>(r0, r10, r7, r8)
            r0.f3714v = r6
            r12.f6438f = r2
            r13.f6438f = r3
            r11.f6438f = r4
            r6.f6438f = r5
            r1.recycle()
            p3.d r1 = p3.p.PILL
            r2 = r19
            r3 = r20
            p3.n r1 = p3.p.d(r15, r2, r3, r9, r1)
            p3.p r1 = r1.a()
            r0.setShapeAppearanceModel(r1)
            android.content.res.ColorStateList r1 = r17.getTextColors()
            r0.G = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
    
        if (r4.F == false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void f(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r4, int r5) {
        /*
            r0 = 1
            r1 = 2
            if (r5 == 0) goto L20
            if (r5 == r0) goto L1d
            if (r5 == r1) goto L1a
            r2 = 3
            if (r5 != r2) goto Le
            g3.e r2 = r4.f3715w
            goto L22
        Le:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unknown strategy type: "
            java.lang.String r5 = a.a.d(r0, r5)
            r4.<init>(r5)
            throw r4
        L1a:
            g3.e r2 = r4.f3714v
            goto L22
        L1d:
            g3.f r2 = r4.f3717y
            goto L22
        L20:
            g3.g r2 = r4.f3716x
        L22:
            boolean r3 = r2.i()
            if (r3 == 0) goto L2a
            goto L99
        L2a:
            int r3 = p0.d1.OVER_SCROLL_ALWAYS
            boolean r3 = p0.o0.c(r4)
            if (r3 != 0) goto L46
            int r3 = r4.getVisibility()
            if (r3 == 0) goto L3d
            int r0 = r4.f3713u
            if (r0 != r1) goto L42
            goto L93
        L3d:
            int r3 = r4.f3713u
            if (r3 == r0) goto L42
            goto L93
        L42:
            boolean r0 = r4.F
            if (r0 == 0) goto L93
        L46:
            boolean r0 = r4.isInEditMode()
            if (r0 != 0) goto L93
            if (r5 != r1) goto L69
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            if (r5 == 0) goto L5d
            int r0 = r5.width
            r4.H = r0
            int r5 = r5.height
            r4.I = r5
            goto L69
        L5d:
            int r5 = r4.getWidth()
            r4.H = r5
            int r5 = r4.getHeight()
            r4.I = r5
        L69:
            r5 = 0
            r4.measure(r5, r5)
            android.animation.AnimatorSet r4 = r2.a()
            g3.d r5 = new g3.d
            r5.<init>(r2)
            r4.addListener(r5)
            java.util.ArrayList r5 = r2.f6435c
            java.util.Iterator r5 = r5.iterator()
        L7f:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L8f
            java.lang.Object r0 = r5.next()
            android.animation.Animator$AnimatorListener r0 = (android.animation.Animator.AnimatorListener) r0
            r4.addListener(r0)
            goto L7f
        L8f:
            r4.start()
            goto L99
        L93:
            r2.h()
            r2.g()
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.f(com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton, int):void");
    }

    public final void g(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
    }

    @Override // c0.a
    public b getBehavior() {
        return this.C;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    public int getCollapsedSize() {
        int i7 = this.f3718z;
        if (i7 >= 0) {
            return i7;
        }
        int i8 = d1.OVER_SCROLL_ALWAYS;
        return (Math.min(m0.f(this), m0.e(this)) * 2) + getIconSize();
    }

    public r2.e getExtendMotionSpec() {
        return this.f3715w.f6438f;
    }

    public r2.e getHideMotionSpec() {
        return this.f3717y.f6438f;
    }

    public r2.e getShowMotionSpec() {
        return this.f3716x.f6438f;
    }

    public r2.e getShrinkMotionSpec() {
        return this.f3714v.f6438f;
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.D && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.D = false;
            this.f3714v.h();
        }
    }

    public void setAnimateShowBeforeLayout(boolean z6) {
        this.F = z6;
    }

    public void setExtendMotionSpec(r2.e eVar) {
        this.f3715w.f6438f = eVar;
    }

    public void setExtendMotionSpecResource(int i7) {
        setExtendMotionSpec(r2.e.b(getContext(), i7));
    }

    public void setExtended(boolean z6) {
        if (this.D == z6) {
            return;
        }
        e eVar = z6 ? this.f3715w : this.f3714v;
        if (eVar.i()) {
            return;
        }
        eVar.h();
    }

    public void setHideMotionSpec(r2.e eVar) {
        this.f3717y.f6438f = eVar;
    }

    public void setHideMotionSpecResource(int i7) {
        setHideMotionSpec(r2.e.b(getContext(), i7));
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i7, int i8, int i9, int i10) {
        super.setPadding(i7, i8, i9, i10);
        if (!this.D || this.E) {
            return;
        }
        int i11 = d1.OVER_SCROLL_ALWAYS;
        this.A = m0.f(this);
        this.B = m0.e(this);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i7, int i8, int i9, int i10) {
        super.setPaddingRelative(i7, i8, i9, i10);
        if (!this.D || this.E) {
            return;
        }
        this.A = i7;
        this.B = i9;
    }

    public void setShowMotionSpec(r2.e eVar) {
        this.f3716x.f6438f = eVar;
    }

    public void setShowMotionSpecResource(int i7) {
        setShowMotionSpec(r2.e.b(getContext(), i7));
    }

    public void setShrinkMotionSpec(r2.e eVar) {
        this.f3714v.f6438f = eVar;
    }

    public void setShrinkMotionSpecResource(int i7) {
        setShrinkMotionSpec(r2.e.b(getContext(), i7));
    }

    @Override // android.widget.TextView
    public void setTextColor(int i7) {
        super.setTextColor(i7);
        this.G = getTextColors();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        this.G = getTextColors();
    }
}
