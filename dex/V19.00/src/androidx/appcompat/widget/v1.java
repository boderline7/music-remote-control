package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewConfiguration;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class v1 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final float f1424a;

    /* renamed from: b, reason: collision with root package name */
    public final int f1425b;

    /* renamed from: c, reason: collision with root package name */
    public final int f1426c;

    /* renamed from: d, reason: collision with root package name */
    public final View f1427d;

    /* renamed from: e, reason: collision with root package name */
    public u1 f1428e;

    /* renamed from: f, reason: collision with root package name */
    public u1 f1429f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f1430g;

    /* renamed from: h, reason: collision with root package name */
    public int f1431h;

    /* renamed from: i, reason: collision with root package name */
    public final int[] f1432i = new int[2];

    public v1(View view) {
        this.f1427d = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1424a = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f1425b = tapTimeout;
        this.f1426c = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        u1 u1Var = this.f1429f;
        View view = this.f1427d;
        if (u1Var != null) {
            view.removeCallbacks(u1Var);
        }
        u1 u1Var2 = this.f1428e;
        if (u1Var2 != null) {
            view.removeCallbacks(u1Var2);
        }
    }

    public abstract k.g0 b();

    public abstract boolean c();

    public abstract boolean d();

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0059, code lost:
    
        if (r14 != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007b, code lost:
    
        if (r4 != 3) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00fe  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r13, android.view.MotionEvent r14) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.v1.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f1430g = false;
        this.f1431h = -1;
        u1 u1Var = this.f1428e;
        if (u1Var != null) {
            this.f1427d.removeCallbacks(u1Var);
        }
    }
}
