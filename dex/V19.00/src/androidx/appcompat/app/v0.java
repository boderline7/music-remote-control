package androidx.appcompat.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.s3;
import androidx.appcompat.widget.w3;
import java.util.ArrayList;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v0 extends b {

    /* renamed from: a, reason: collision with root package name */
    public final w3 f858a;

    /* renamed from: b, reason: collision with root package name */
    public final Window.Callback f859b;

    /* renamed from: c, reason: collision with root package name */
    public final t0 f860c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f861d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f862e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f863f;

    /* renamed from: g, reason: collision with root package name */
    public final ArrayList f864g = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    public final s0 f865h = new s0(0, this);

    public v0(Toolbar toolbar, CharSequence charSequence, d0 d0Var) {
        t0 t0Var = new t0(this);
        toolbar.getClass();
        w3 w3Var = new w3(toolbar, false);
        this.f858a = w3Var;
        d0Var.getClass();
        this.f859b = d0Var;
        w3Var.f1453l = d0Var;
        toolbar.setOnMenuItemClickListener(t0Var);
        if (!w3Var.f1449h) {
            w3Var.f1450i = charSequence;
            if ((w3Var.f1443b & 8) != 0) {
                Toolbar toolbar2 = w3Var.f1442a;
                toolbar2.setTitle(charSequence);
                if (w3Var.f1449h) {
                    d1.p(toolbar2.getRootView(), charSequence);
                }
            }
        }
        this.f860c = new t0(this);
    }

    @Override // androidx.appcompat.app.b
    public final boolean a() {
        androidx.appcompat.widget.m mVar;
        ActionMenuView actionMenuView = this.f858a.f1442a.f1152a;
        return (actionMenuView == null || (mVar = actionMenuView.f985t) == null || !mVar.g()) ? false : true;
    }

    @Override // androidx.appcompat.app.b
    public final boolean b() {
        k.q qVar;
        s3 s3Var = this.f858a.f1442a.M;
        if (s3Var == null || (qVar = s3Var.f1412b) == null) {
            return false;
        }
        if (s3Var == null) {
            qVar = null;
        }
        if (qVar == null) {
            return true;
        }
        qVar.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.b
    public final void c(boolean z6) {
        if (z6 == this.f863f) {
            return;
        }
        this.f863f = z6;
        ArrayList arrayList = this.f864g;
        if (arrayList.size() <= 0) {
            return;
        }
        a.a.u(arrayList.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.b
    public final int d() {
        return this.f858a.f1443b;
    }

    @Override // androidx.appcompat.app.b
    public final Context e() {
        return this.f858a.f1442a.getContext();
    }

    @Override // androidx.appcompat.app.b
    public final void f() {
        this.f858a.f1442a.setVisibility(8);
    }

    @Override // androidx.appcompat.app.b
    public final boolean g() {
        w3 w3Var = this.f858a;
        Toolbar toolbar = w3Var.f1442a;
        s0 s0Var = this.f865h;
        toolbar.removeCallbacks(s0Var);
        Toolbar toolbar2 = w3Var.f1442a;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        p0.l0.m(toolbar2, s0Var);
        return true;
    }

    @Override // androidx.appcompat.app.b
    public final void h() {
    }

    @Override // androidx.appcompat.app.b
    public final void i() {
        this.f858a.f1442a.removeCallbacks(this.f865h);
    }

    @Override // androidx.appcompat.app.b
    public final boolean j(int i7, KeyEvent keyEvent) {
        Menu w6 = w();
        if (w6 == null) {
            return false;
        }
        w6.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return w6.performShortcut(i7, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.b
    public final boolean k(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            l();
        }
        return true;
    }

    @Override // androidx.appcompat.app.b
    public final boolean l() {
        return this.f858a.f1442a.w();
    }

    @Override // androidx.appcompat.app.b
    public final void m(boolean z6) {
    }

    @Override // androidx.appcompat.app.b
    public final void n(boolean z6) {
        x(4, 4);
    }

    @Override // androidx.appcompat.app.b
    public final void o() {
        x(2, 2);
    }

    @Override // androidx.appcompat.app.b
    public final void p() {
        x(0, 8);
    }

    @Override // androidx.appcompat.app.b
    public final void q(int i7) {
        this.f858a.b(i7);
    }

    @Override // androidx.appcompat.app.b
    public final void r(Drawable drawable) {
        w3 w3Var = this.f858a;
        w3Var.f1448g = drawable;
        int i7 = w3Var.f1443b & 4;
        Toolbar toolbar = w3Var.f1442a;
        if (i7 == 0) {
            toolbar.setNavigationIcon((Drawable) null);
            return;
        }
        if (drawable == null) {
            drawable = w3Var.f1457p;
        }
        toolbar.setNavigationIcon(drawable);
    }

    @Override // androidx.appcompat.app.b
    public final void s(boolean z6) {
    }

    @Override // androidx.appcompat.app.b
    public final void t(String str) {
        w3 w3Var = this.f858a;
        w3Var.f1449h = true;
        w3Var.f1450i = str;
        if ((w3Var.f1443b & 8) != 0) {
            Toolbar toolbar = w3Var.f1442a;
            toolbar.setTitle(str);
            if (w3Var.f1449h) {
                d1.p(toolbar.getRootView(), str);
            }
        }
    }

    @Override // androidx.appcompat.app.b
    public final void u(CharSequence charSequence) {
        w3 w3Var = this.f858a;
        if (w3Var.f1449h) {
            return;
        }
        w3Var.f1450i = charSequence;
        if ((w3Var.f1443b & 8) != 0) {
            Toolbar toolbar = w3Var.f1442a;
            toolbar.setTitle(charSequence);
            if (w3Var.f1449h) {
                d1.p(toolbar.getRootView(), charSequence);
            }
        }
    }

    public final Menu w() {
        boolean z6 = this.f862e;
        w3 w3Var = this.f858a;
        if (!z6) {
            w3Var.f1442a.setMenuCallbacks(new u0(this), new t0(this));
            this.f862e = true;
        }
        return w3Var.f1442a.getMenu();
    }

    public final void x(int i7, int i8) {
        w3 w3Var = this.f858a;
        w3Var.a((i7 & i8) | ((~i8) & w3Var.f1443b));
    }
}
