package com.google.android.material.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatImageView;
import c0.b;
import c0.e;
import e0.f;
import java.util.ArrayList;
import p0.d1;
import p0.l0;
import q0.g;
import q1.h;
import q2.k;
import q2.l;
import w2.c;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {

    /* renamed from: m, reason: collision with root package name */
    public static final int f3417m = l.Widget_Material3_BottomSheet_DragHandle;

    /* renamed from: d, reason: collision with root package name */
    public final AccessibilityManager f3418d;

    /* renamed from: e, reason: collision with root package name */
    public BottomSheetBehavior f3419e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f3420f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f3421g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f3422h;

    /* renamed from: i, reason: collision with root package name */
    public final String f3423i;

    /* renamed from: j, reason: collision with root package name */
    public final String f3424j;

    /* renamed from: k, reason: collision with root package name */
    public final String f3425k;

    /* renamed from: l, reason: collision with root package name */
    public final c f3426l;

    public BottomSheetDragHandleView(Context context) {
        this(context, null);
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.bottomSheetDragHandleStyle);
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attributeSet, int i7) {
        super(t3.a.a(context, attributeSet, i7, f3417m), attributeSet, i7);
        this.f3423i = getResources().getString(k.bottomsheet_action_expand);
        this.f3424j = getResources().getString(k.bottomsheet_action_collapse);
        this.f3425k = getResources().getString(k.bottomsheet_drag_handle_clicked);
        this.f3426l = new c(this);
        this.f3418d = (AccessibilityManager) getContext().getSystemService("accessibility");
        e();
        d1.o(this, new h(1, this));
    }

    private void setBottomSheetBehavior(BottomSheetBehavior<?> bottomSheetBehavior) {
        BottomSheetBehavior bottomSheetBehavior2 = this.f3419e;
        c cVar = this.f3426l;
        if (bottomSheetBehavior2 != null) {
            bottomSheetBehavior2.X.remove(cVar);
            this.f3419e.G(null);
        }
        this.f3419e = bottomSheetBehavior;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.G(this);
            d(this.f3419e.L);
            ArrayList arrayList = this.f3419e.X;
            if (!arrayList.contains(cVar)) {
                arrayList.add(cVar);
            }
        }
        e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0031, code lost:
    
        if (r1 != false) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean c() {
        /*
            r7 = this;
            boolean r0 = r7.f3421g
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            android.view.accessibility.AccessibilityManager r0 = r7.f3418d
            if (r0 != 0) goto Lb
            goto L1d
        Lb:
            r2 = 16384(0x4000, float:2.2959E-41)
            android.view.accessibility.AccessibilityEvent r2 = android.view.accessibility.AccessibilityEvent.obtain(r2)
            java.util.List r3 = r2.getText()
            java.lang.String r4 = r7.f3425k
            r3.add(r4)
            r0.sendAccessibilityEvent(r2)
        L1d:
            com.google.android.material.bottomsheet.BottomSheetBehavior r0 = r7.f3419e
            boolean r2 = r0.f3382b
            r3 = 1
            if (r2 != 0) goto L28
            r0.getClass()
            r1 = r3
        L28:
            com.google.android.material.bottomsheet.BottomSheetBehavior r0 = r7.f3419e
            int r2 = r0.L
            r4 = 6
            r5 = 3
            r6 = 4
            if (r2 != r6) goto L34
            if (r1 == 0) goto L41
            goto L42
        L34:
            if (r2 != r5) goto L3b
            if (r1 == 0) goto L39
            goto L42
        L39:
            r4 = r6
            goto L42
        L3b:
            boolean r1 = r7.f3422h
            if (r1 == 0) goto L40
            goto L41
        L40:
            r5 = r6
        L41:
            r4 = r5
        L42:
            r0.I(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetDragHandleView.c():boolean");
    }

    public final void d(int i7) {
        if (i7 == 4) {
            this.f3422h = true;
        } else if (i7 == 3) {
            this.f3422h = false;
        }
        d1.m(this, g.ACTION_CLICK, this.f3422h ? this.f3423i : this.f3424j, new f(2, this));
    }

    public final void e() {
        this.f3421g = this.f3420f && this.f3419e != null;
        int i7 = this.f3419e == null ? 2 : 1;
        int i8 = d1.OVER_SCROLL_ALWAYS;
        l0.s(this, i7);
        setClickable(this.f3421g);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public final void onAccessibilityStateChanged(boolean z6) {
        this.f3420f = z6;
        e();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        BottomSheetBehavior<?> bottomSheetBehavior;
        super.onAttachedToWindow();
        View view = this;
        while (true) {
            Object parent = view.getParent();
            bottomSheetBehavior = null;
            view = parent instanceof View ? (View) parent : null;
            if (view == null) {
                break;
            }
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof e) {
                b bVar = ((e) layoutParams).f3007a;
                if (bVar instanceof BottomSheetBehavior) {
                    bottomSheetBehavior = (BottomSheetBehavior) bVar;
                    break;
                }
            }
        }
        setBottomSheetBehavior(bottomSheetBehavior);
        AccessibilityManager accessibilityManager = this.f3418d;
        if (accessibilityManager != null) {
            accessibilityManager.addAccessibilityStateChangeListener(this);
            onAccessibilityStateChanged(accessibilityManager.isEnabled());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        AccessibilityManager accessibilityManager = this.f3418d;
        if (accessibilityManager != null) {
            accessibilityManager.removeAccessibilityStateChangeListener(this);
        }
        setBottomSheetBehavior(null);
        super.onDetachedFromWindow();
    }
}
