package u0;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.widget.u;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import p0.d1;
import p0.l0;
import p3.f;
import q0.i;
import q0.m;
import q0.o;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b extends p0.c {
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;

    /* renamed from: n, reason: collision with root package name */
    public static final Rect f9218n = new Rect(u.MAX_ACTIVITY_COUNT_UNLIMITED, u.MAX_ACTIVITY_COUNT_UNLIMITED, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /* renamed from: o, reason: collision with root package name */
    public static final f f9219o;

    /* renamed from: p, reason: collision with root package name */
    public static final f f9220p;

    /* renamed from: h, reason: collision with root package name */
    public final AccessibilityManager f9225h;

    /* renamed from: i, reason: collision with root package name */
    public final View f9226i;

    /* renamed from: j, reason: collision with root package name */
    public a f9227j;

    /* renamed from: d, reason: collision with root package name */
    public final Rect f9221d = new Rect();

    /* renamed from: e, reason: collision with root package name */
    public final Rect f9222e = new Rect();

    /* renamed from: f, reason: collision with root package name */
    public final Rect f9223f = new Rect();

    /* renamed from: g, reason: collision with root package name */
    public final int[] f9224g = new int[2];

    /* renamed from: k, reason: collision with root package name */
    public int f9228k = Integer.MIN_VALUE;

    /* renamed from: l, reason: collision with root package name */
    public int f9229l = Integer.MIN_VALUE;

    /* renamed from: m, reason: collision with root package name */
    public int f9230m = Integer.MIN_VALUE;

    static {
        int i7 = 0;
        f9219o = new f(3, i7);
        f9220p = new f(4, i7);
    }

    public b(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f9226i = view;
        this.f9225h = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        int i7 = d1.OVER_SCROLL_ALWAYS;
        if (l0.c(view) == 0) {
            l0.s(view, 1);
        }
    }

    @Override // p0.c
    public final m b(View view) {
        if (this.f9227j == null) {
            this.f9227j = new a(this);
        }
        return this.f9227j;
    }

    @Override // p0.c
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
    }

    @Override // p0.c
    public final void d(View view, i iVar) {
        this.f8103a.onInitializeAccessibilityNodeInfo(view, iVar.f8426a);
        t(iVar);
    }

    public final boolean j(int i7) {
        if (this.f9229l != i7) {
            return false;
        }
        this.f9229l = Integer.MIN_VALUE;
        v(i7, false);
        x(i7, 8);
        return true;
    }

    public final AccessibilityEvent k(int i7, int i8) {
        View view = this.f9226i;
        if (i7 == -1) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(i8);
            view.onInitializeAccessibilityEvent(obtain);
            return obtain;
        }
        AccessibilityEvent obtain2 = AccessibilityEvent.obtain(i8);
        i r6 = r(i7);
        obtain2.getText().add(r6.e());
        AccessibilityNodeInfo accessibilityNodeInfo = r6.f8426a;
        obtain2.setContentDescription(accessibilityNodeInfo.getContentDescription());
        obtain2.setScrollable(accessibilityNodeInfo.isScrollable());
        obtain2.setPassword(accessibilityNodeInfo.isPassword());
        obtain2.setEnabled(accessibilityNodeInfo.isEnabled());
        obtain2.setChecked(accessibilityNodeInfo.isChecked());
        if (obtain2.getText().isEmpty() && obtain2.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain2.setClassName(accessibilityNodeInfo.getClassName());
        o.a(obtain2, view, i7);
        obtain2.setPackageName(view.getContext().getPackageName());
        return obtain2;
    }

    public final i l(int i7) {
        AccessibilityNodeInfo accessibilityNodeInfo;
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
        i iVar = new i(obtain);
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        iVar.f("android.view.View");
        Rect rect = f9218n;
        obtain.setBoundsInParent(rect);
        obtain.setBoundsInScreen(rect);
        iVar.f8427b = -1;
        View view = this.f9226i;
        obtain.setParent(view);
        u(i7, iVar);
        if (iVar.e() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        Rect rect2 = this.f9222e;
        iVar.d(rect2);
        if (rect2.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = obtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        obtain.setPackageName(view.getContext().getPackageName());
        iVar.f8428c = i7;
        obtain.setSource(view, i7);
        if (this.f9228k == i7) {
            obtain.setAccessibilityFocused(true);
            iVar.a(128);
        } else {
            obtain.setAccessibilityFocused(false);
            iVar.a(64);
        }
        boolean z6 = this.f9229l == i7;
        if (z6) {
            iVar.a(2);
        } else if (obtain.isFocusable()) {
            iVar.a(1);
        }
        obtain.setFocused(z6);
        int[] iArr = this.f9224g;
        view.getLocationOnScreen(iArr);
        Rect rect3 = this.f9221d;
        obtain.getBoundsInScreen(rect3);
        if (rect3.equals(rect)) {
            iVar.d(rect3);
            if (iVar.f8427b != -1) {
                i iVar2 = new i(AccessibilityNodeInfo.obtain());
                int i8 = iVar.f8427b;
                while (true) {
                    accessibilityNodeInfo = iVar2.f8426a;
                    if (i8 == -1) {
                        break;
                    }
                    iVar2.f8427b = -1;
                    accessibilityNodeInfo.setParent(view, -1);
                    accessibilityNodeInfo.setBoundsInParent(rect);
                    u(i8, iVar2);
                    iVar2.d(rect2);
                    rect3.offset(rect2.left, rect2.top);
                    i8 = iVar2.f8427b;
                }
                accessibilityNodeInfo.recycle();
            }
            rect3.offset(iArr[0] - view.getScrollX(), iArr[1] - view.getScrollY());
        }
        Rect rect4 = this.f9223f;
        if (view.getLocalVisibleRect(rect4)) {
            rect4.offset(iArr[0] - view.getScrollX(), iArr[1] - view.getScrollY());
            if (rect3.intersect(rect4)) {
                AccessibilityNodeInfo accessibilityNodeInfo2 = iVar.f8426a;
                accessibilityNodeInfo2.setBoundsInScreen(rect3);
                if (!rect3.isEmpty() && view.getWindowVisibility() == 0) {
                    Object parent = view.getParent();
                    while (true) {
                        if (parent instanceof View) {
                            View view2 = (View) parent;
                            if (view2.getAlpha() <= CropImageView.DEFAULT_ASPECT_RATIO || view2.getVisibility() != 0) {
                                break;
                            }
                            parent = view2.getParent();
                        } else if (parent != null) {
                            accessibilityNodeInfo2.setVisibleToUser(true);
                        }
                    }
                }
            }
        }
        return iVar;
    }

    public final boolean m(MotionEvent motionEvent) {
        int i7;
        AccessibilityManager accessibilityManager = this.f9225h;
        if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int n6 = n(motionEvent.getX(), motionEvent.getY());
            int i8 = this.f9230m;
            if (i8 != n6) {
                this.f9230m = n6;
                x(n6, 128);
                x(i8, 256);
            }
            return n6 != Integer.MIN_VALUE;
        }
        if (action != 10 || (i7 = this.f9230m) == Integer.MIN_VALUE) {
            return false;
        }
        if (i7 != Integer.MIN_VALUE) {
            this.f9230m = Integer.MIN_VALUE;
            x(Integer.MIN_VALUE, 128);
            x(i7, 256);
        }
        return true;
    }

    public abstract int n(float f7, float f8);

    public abstract void o(ArrayList arrayList);

    public final void p(int i7) {
        View view;
        ViewParent parent;
        if (i7 == Integer.MIN_VALUE || !this.f9225h.isEnabled() || (parent = (view = this.f9226i).getParent()) == null) {
            return;
        }
        AccessibilityEvent k7 = k(i7, 2048);
        q0.b.b(k7, 0);
        parent.requestSendAccessibilityEvent(view, k7);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q(int r20, android.graphics.Rect r21) {
        /*
            Method dump skipped, instructions count: 489
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: u0.b.q(int, android.graphics.Rect):boolean");
    }

    public final i r(int i7) {
        if (i7 != -1) {
            return l(i7);
        }
        View view = this.f9226i;
        AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(view);
        i iVar = new i(obtain);
        int i8 = d1.OVER_SCROLL_ALWAYS;
        view.onInitializeAccessibilityNodeInfo(obtain);
        ArrayList arrayList = new ArrayList();
        o(arrayList);
        if (obtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i9 = 0; i9 < size; i9++) {
            iVar.f8426a.addChild(view, ((Integer) arrayList.get(i9)).intValue());
        }
        return iVar;
    }

    public abstract boolean s(int i7, int i8, Bundle bundle);

    public void t(i iVar) {
    }

    public abstract void u(int i7, i iVar);

    public void v(int i7, boolean z6) {
    }

    public final boolean w(int i7) {
        int i8;
        View view = this.f9226i;
        if ((!view.isFocused() && !view.requestFocus()) || (i8 = this.f9229l) == i7) {
            return false;
        }
        if (i8 != Integer.MIN_VALUE) {
            j(i8);
        }
        if (i7 == Integer.MIN_VALUE) {
            return false;
        }
        this.f9229l = i7;
        v(i7, true);
        x(i7, 8);
        return true;
    }

    public final void x(int i7, int i8) {
        View view;
        ViewParent parent;
        if (i7 == Integer.MIN_VALUE || !this.f9225h.isEnabled() || (parent = (view = this.f9226i).getParent()) == null) {
            return;
        }
        parent.requestSendAccessibilityEvent(view, k(i7, i8));
    }
}
