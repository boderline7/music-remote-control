package k;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class a0 {

    /* renamed from: a, reason: collision with root package name */
    public final Context f7046a;

    /* renamed from: b, reason: collision with root package name */
    public final o f7047b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f7048c;

    /* renamed from: d, reason: collision with root package name */
    public final int f7049d;

    /* renamed from: e, reason: collision with root package name */
    public final int f7050e;

    /* renamed from: f, reason: collision with root package name */
    public View f7051f;

    /* renamed from: h, reason: collision with root package name */
    public boolean f7053h;

    /* renamed from: i, reason: collision with root package name */
    public b0 f7054i;

    /* renamed from: j, reason: collision with root package name */
    public x f7055j;

    /* renamed from: k, reason: collision with root package name */
    public PopupWindow.OnDismissListener f7056k;

    /* renamed from: g, reason: collision with root package name */
    public int f7052g = 8388611;

    /* renamed from: l, reason: collision with root package name */
    public final y f7057l = new y(this);

    public a0(int i7, int i8, Context context, View view, o oVar, boolean z6) {
        this.f7046a = context;
        this.f7047b = oVar;
        this.f7051f = view;
        this.f7048c = z6;
        this.f7049d = i7;
        this.f7050e = i8;
    }

    public final x a() {
        x h0Var;
        if (this.f7055j == null) {
            Context context = this.f7046a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            z.a(defaultDisplay, point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(f.d.abc_cascading_menus_min_smallest_width)) {
                h0Var = new i(this.f7046a, this.f7051f, this.f7049d, this.f7050e, this.f7048c);
            } else {
                Context context2 = this.f7046a;
                o oVar = this.f7047b;
                h0Var = new h0(this.f7049d, this.f7050e, context2, this.f7051f, oVar, this.f7048c);
            }
            h0Var.n(this.f7047b);
            h0Var.t(this.f7057l);
            h0Var.p(this.f7051f);
            h0Var.k(this.f7054i);
            h0Var.q(this.f7053h);
            h0Var.r(this.f7052g);
            this.f7055j = h0Var;
        }
        return this.f7055j;
    }

    public final boolean b() {
        x xVar = this.f7055j;
        return xVar != null && xVar.b();
    }

    public void c() {
        this.f7055j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f7056k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(int i7, int i8, boolean z6, boolean z7) {
        x a7 = a();
        a7.u(z7);
        if (z6) {
            int i9 = this.f7052g;
            View view = this.f7051f;
            int i10 = d1.OVER_SCROLL_ALWAYS;
            if ((Gravity.getAbsoluteGravity(i9, m0.d(view)) & 7) == 5) {
                i7 -= this.f7051f.getWidth();
            }
            a7.s(i7);
            a7.v(i8);
            int i11 = (int) ((this.f7046a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            a7.f7202a = new Rect(i7 - i11, i8 - i11, i7 + i11, i8 + i11);
        }
        a7.g();
    }
}
