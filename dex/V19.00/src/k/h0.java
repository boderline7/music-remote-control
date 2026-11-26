package k;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.widget.j2;
import androidx.appcompat.widget.s1;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h0 extends x implements PopupWindow.OnDismissListener, View.OnKeyListener {

    /* renamed from: v, reason: collision with root package name */
    public static final int f7076v = f.g.abc_popup_menu_item_layout;

    /* renamed from: b, reason: collision with root package name */
    public final Context f7077b;

    /* renamed from: c, reason: collision with root package name */
    public final o f7078c;

    /* renamed from: d, reason: collision with root package name */
    public final l f7079d;

    /* renamed from: e, reason: collision with root package name */
    public final boolean f7080e;

    /* renamed from: f, reason: collision with root package name */
    public final int f7081f;

    /* renamed from: g, reason: collision with root package name */
    public final int f7082g;

    /* renamed from: h, reason: collision with root package name */
    public final int f7083h;

    /* renamed from: i, reason: collision with root package name */
    public final j2 f7084i;

    /* renamed from: j, reason: collision with root package name */
    public final e f7085j;

    /* renamed from: k, reason: collision with root package name */
    public final f f7086k;

    /* renamed from: l, reason: collision with root package name */
    public PopupWindow.OnDismissListener f7087l;

    /* renamed from: m, reason: collision with root package name */
    public View f7088m;

    /* renamed from: n, reason: collision with root package name */
    public View f7089n;

    /* renamed from: o, reason: collision with root package name */
    public b0 f7090o;

    /* renamed from: p, reason: collision with root package name */
    public ViewTreeObserver f7091p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f7092q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f7093r;

    /* renamed from: s, reason: collision with root package name */
    public int f7094s;

    /* renamed from: t, reason: collision with root package name */
    public int f7095t = 0;

    /* renamed from: u, reason: collision with root package name */
    public boolean f7096u;

    public h0(int i7, int i8, Context context, View view, o oVar, boolean z6) {
        int i9 = 1;
        this.f7085j = new e(i9, this);
        this.f7086k = new f(i9, this);
        this.f7077b = context;
        this.f7078c = oVar;
        this.f7080e = z6;
        this.f7079d = new l(oVar, LayoutInflater.from(context), z6, f7076v);
        this.f7082g = i7;
        this.f7083h = i8;
        Resources resources = context.getResources();
        this.f7081f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(f.d.abc_config_prefDialogWidth));
        this.f7088m = view;
        this.f7084i = new j2(context, null, i7, i8);
        oVar.b(this, context);
    }

    @Override // k.c0
    public final void a(o oVar, boolean z6) {
        if (oVar != this.f7078c) {
            return;
        }
        dismiss();
        b0 b0Var = this.f7090o;
        if (b0Var != null) {
            b0Var.a(oVar, z6);
        }
    }

    @Override // k.g0
    public final boolean b() {
        return !this.f7092q && this.f7084i.f1237z.isShowing();
    }

    @Override // k.c0
    public final boolean d(i0 i0Var) {
        if (i0Var.hasVisibleItems()) {
            a0 a0Var = new a0(this.f7082g, this.f7083h, this.f7077b, this.f7089n, i0Var, this.f7080e);
            b0 b0Var = this.f7090o;
            a0Var.f7054i = b0Var;
            x xVar = a0Var.f7055j;
            if (xVar != null) {
                xVar.k(b0Var);
            }
            boolean w6 = x.w(i0Var);
            a0Var.f7053h = w6;
            x xVar2 = a0Var.f7055j;
            if (xVar2 != null) {
                xVar2.q(w6);
            }
            a0Var.f7056k = this.f7087l;
            this.f7087l = null;
            this.f7078c.c(false);
            j2 j2Var = this.f7084i;
            int i7 = j2Var.f1217f;
            int h7 = j2Var.h();
            int i8 = this.f7095t;
            View view = this.f7088m;
            int i9 = d1.OVER_SCROLL_ALWAYS;
            if ((Gravity.getAbsoluteGravity(i8, m0.d(view)) & 7) == 5) {
                i7 += this.f7088m.getWidth();
            }
            if (!a0Var.b()) {
                if (a0Var.f7051f != null) {
                    a0Var.d(i7, h7, true, true);
                }
            }
            b0 b0Var2 = this.f7090o;
            if (b0Var2 != null) {
                b0Var2.c(i0Var);
            }
            return true;
        }
        return false;
    }

    @Override // k.g0
    public final void dismiss() {
        if (b()) {
            this.f7084i.dismiss();
        }
    }

    @Override // k.g0
    public final void g() {
        View view;
        if (b()) {
            return;
        }
        if (this.f7092q || (view = this.f7088m) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.f7089n = view;
        j2 j2Var = this.f7084i;
        j2Var.f1237z.setOnDismissListener(this);
        j2Var.f1227p = this;
        j2Var.f1236y = true;
        j2Var.f1237z.setFocusable(true);
        View view2 = this.f7089n;
        boolean z6 = this.f7091p == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f7091p = viewTreeObserver;
        if (z6) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f7085j);
        }
        view2.addOnAttachStateChangeListener(this.f7086k);
        j2Var.f1226o = view2;
        j2Var.f1223l = this.f7095t;
        boolean z7 = this.f7093r;
        Context context = this.f7077b;
        l lVar = this.f7079d;
        if (!z7) {
            this.f7094s = x.o(lVar, context, this.f7081f);
            this.f7093r = true;
        }
        j2Var.r(this.f7094s);
        j2Var.f1237z.setInputMethodMode(2);
        Rect rect = this.f7202a;
        j2Var.f1235x = rect != null ? new Rect(rect) : null;
        j2Var.g();
        s1 s1Var = j2Var.f1214c;
        s1Var.setOnKeyListener(this);
        if (this.f7096u) {
            o oVar = this.f7078c;
            if (oVar.f7151m != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(f.g.abc_popup_menu_header_item_layout, (ViewGroup) s1Var, false);
                TextView textView = (TextView) frameLayout.findViewById(R.id.title);
                if (textView != null) {
                    textView.setText(oVar.f7151m);
                }
                frameLayout.setEnabled(false);
                s1Var.addHeaderView(frameLayout, null, false);
            }
        }
        j2Var.p(lVar);
        j2Var.g();
    }

    @Override // k.c0
    public final boolean h() {
        return false;
    }

    @Override // k.c0
    public final Parcelable i() {
        return null;
    }

    @Override // k.c0
    public final void j(Parcelable parcelable) {
    }

    @Override // k.c0
    public final void k(b0 b0Var) {
        this.f7090o = b0Var;
    }

    @Override // k.g0
    public final s1 l() {
        return this.f7084i.f1214c;
    }

    @Override // k.c0
    public final void m(boolean z6) {
        this.f7093r = false;
        l lVar = this.f7079d;
        if (lVar != null) {
            lVar.notifyDataSetChanged();
        }
    }

    @Override // k.x
    public final void n(o oVar) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.f7092q = true;
        this.f7078c.c(true);
        ViewTreeObserver viewTreeObserver = this.f7091p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f7091p = this.f7089n.getViewTreeObserver();
            }
            this.f7091p.removeGlobalOnLayoutListener(this.f7085j);
            this.f7091p = null;
        }
        this.f7089n.removeOnAttachStateChangeListener(this.f7086k);
        PopupWindow.OnDismissListener onDismissListener = this.f7087l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i7, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i7 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // k.x
    public final void p(View view) {
        this.f7088m = view;
    }

    @Override // k.x
    public final void q(boolean z6) {
        this.f7079d.f7134c = z6;
    }

    @Override // k.x
    public final void r(int i7) {
        this.f7095t = i7;
    }

    @Override // k.x
    public final void s(int i7) {
        this.f7084i.f1217f = i7;
    }

    @Override // k.x
    public final void t(PopupWindow.OnDismissListener onDismissListener) {
        this.f7087l = onDismissListener;
    }

    @Override // k.x
    public final void u(boolean z6) {
        this.f7096u = z6;
    }

    @Override // k.x
    public final void v(int i7) {
        this.f7084i.o(i7);
    }
}
