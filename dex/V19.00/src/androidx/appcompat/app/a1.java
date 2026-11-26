package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.j1;
import androidx.appcompat.widget.s3;
import androidx.appcompat.widget.w3;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import p0.d1;
import p0.i1;
import p0.k1;
import p0.l1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a1 extends b implements androidx.appcompat.widget.f {

    /* renamed from: a, reason: collision with root package name */
    public Context f674a;

    /* renamed from: b, reason: collision with root package name */
    public Context f675b;

    /* renamed from: c, reason: collision with root package name */
    public ActionBarOverlayLayout f676c;

    /* renamed from: d, reason: collision with root package name */
    public ActionBarContainer f677d;

    /* renamed from: e, reason: collision with root package name */
    public j1 f678e;

    /* renamed from: f, reason: collision with root package name */
    public ActionBarContextView f679f;

    /* renamed from: g, reason: collision with root package name */
    public final View f680g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f681h;

    /* renamed from: i, reason: collision with root package name */
    public z0 f682i;

    /* renamed from: j, reason: collision with root package name */
    public z0 f683j;

    /* renamed from: k, reason: collision with root package name */
    public j.b f684k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f685l;

    /* renamed from: m, reason: collision with root package name */
    public final ArrayList f686m;

    /* renamed from: n, reason: collision with root package name */
    public int f687n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f688o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f689p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f690q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f691r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f692s;

    /* renamed from: t, reason: collision with root package name */
    public j.n f693t;

    /* renamed from: u, reason: collision with root package name */
    public boolean f694u;

    /* renamed from: v, reason: collision with root package name */
    public boolean f695v;

    /* renamed from: w, reason: collision with root package name */
    public final y0 f696w;

    /* renamed from: x, reason: collision with root package name */
    public final y0 f697x;

    /* renamed from: y, reason: collision with root package name */
    public final e.a f698y;

    /* renamed from: z, reason: collision with root package name */
    public static final AccelerateInterpolator f673z = new AccelerateInterpolator();
    public static final DecelerateInterpolator A = new DecelerateInterpolator();

    public a1(Activity activity, boolean z6) {
        new ArrayList();
        this.f686m = new ArrayList();
        this.f687n = 0;
        this.f688o = true;
        this.f692s = true;
        this.f696w = new y0(this, 0);
        this.f697x = new y0(this, 1);
        this.f698y = new e.a(2, this);
        View decorView = activity.getWindow().getDecorView();
        x(decorView);
        if (z6) {
            return;
        }
        this.f680g = decorView.findViewById(R.id.content);
    }

    public a1(Dialog dialog) {
        new ArrayList();
        this.f686m = new ArrayList();
        this.f687n = 0;
        this.f688o = true;
        this.f692s = true;
        this.f696w = new y0(this, 0);
        this.f697x = new y0(this, 1);
        this.f698y = new e.a(2, this);
        x(dialog.getWindow().getDecorView());
    }

    public final void A(boolean z6) {
        int i7 = 0;
        boolean z7 = this.f691r || !(this.f689p || this.f690q);
        e.a aVar = this.f698y;
        View view = this.f680g;
        if (!z7) {
            if (this.f692s) {
                this.f692s = false;
                j.n nVar = this.f693t;
                if (nVar != null) {
                    nVar.a();
                }
                int i8 = this.f687n;
                y0 y0Var = this.f696w;
                if (i8 != 0 || (!this.f694u && !z6)) {
                    y0Var.a();
                    return;
                }
                this.f677d.setAlpha(1.0f);
                this.f677d.setTransitioning(true);
                j.n nVar2 = new j.n();
                float f7 = -this.f677d.getHeight();
                if (z6) {
                    this.f677d.getLocationInWindow(new int[]{0, 0});
                    f7 -= r12[1];
                }
                l1 a7 = d1.a(this.f677d);
                a7.e(f7);
                View view2 = (View) a7.f8160a.get();
                if (view2 != null) {
                    k1.a(view2.animate(), aVar != null ? new i1(i7, aVar, view2) : null);
                }
                boolean z8 = nVar2.f6939e;
                ArrayList arrayList = nVar2.f6935a;
                if (!z8) {
                    arrayList.add(a7);
                }
                if (this.f688o && view != null) {
                    l1 a8 = d1.a(view);
                    a8.e(f7);
                    if (!nVar2.f6939e) {
                        arrayList.add(a8);
                    }
                }
                AccelerateInterpolator accelerateInterpolator = f673z;
                boolean z9 = nVar2.f6939e;
                if (!z9) {
                    nVar2.f6937c = accelerateInterpolator;
                }
                if (!z9) {
                    nVar2.f6936b = 250L;
                }
                if (!z9) {
                    nVar2.f6938d = y0Var;
                }
                this.f693t = nVar2;
                nVar2.b();
                return;
            }
            return;
        }
        if (this.f692s) {
            return;
        }
        this.f692s = true;
        j.n nVar3 = this.f693t;
        if (nVar3 != null) {
            nVar3.a();
        }
        this.f677d.setVisibility(0);
        int i9 = this.f687n;
        y0 y0Var2 = this.f697x;
        if (i9 == 0 && (this.f694u || z6)) {
            this.f677d.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            float f8 = -this.f677d.getHeight();
            if (z6) {
                this.f677d.getLocationInWindow(new int[]{0, 0});
                f8 -= r12[1];
            }
            this.f677d.setTranslationY(f8);
            j.n nVar4 = new j.n();
            l1 a9 = d1.a(this.f677d);
            a9.e(CropImageView.DEFAULT_ASPECT_RATIO);
            View view3 = (View) a9.f8160a.get();
            if (view3 != null) {
                k1.a(view3.animate(), aVar != null ? new i1(i7, aVar, view3) : null);
            }
            boolean z10 = nVar4.f6939e;
            ArrayList arrayList2 = nVar4.f6935a;
            if (!z10) {
                arrayList2.add(a9);
            }
            if (this.f688o && view != null) {
                view.setTranslationY(f8);
                l1 a10 = d1.a(view);
                a10.e(CropImageView.DEFAULT_ASPECT_RATIO);
                if (!nVar4.f6939e) {
                    arrayList2.add(a10);
                }
            }
            DecelerateInterpolator decelerateInterpolator = A;
            boolean z11 = nVar4.f6939e;
            if (!z11) {
                nVar4.f6937c = decelerateInterpolator;
            }
            if (!z11) {
                nVar4.f6936b = 250L;
            }
            if (!z11) {
                nVar4.f6938d = y0Var2;
            }
            this.f693t = nVar4;
            nVar4.b();
        } else {
            this.f677d.setAlpha(1.0f);
            this.f677d.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            if (this.f688o && view != null) {
                view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            y0Var2.a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f676c;
        if (actionBarOverlayLayout != null) {
            int i10 = d1.OVER_SCROLL_ALWAYS;
            p0.p0.c(actionBarOverlayLayout);
        }
    }

    @Override // androidx.appcompat.app.b
    public final boolean b() {
        s3 s3Var;
        j1 j1Var = this.f678e;
        if (j1Var == null || (s3Var = ((w3) j1Var).f1442a.M) == null || s3Var.f1412b == null) {
            return false;
        }
        s3 s3Var2 = ((w3) j1Var).f1442a.M;
        k.q qVar = s3Var2 == null ? null : s3Var2.f1412b;
        if (qVar == null) {
            return true;
        }
        qVar.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.b
    public final void c(boolean z6) {
        if (z6 == this.f685l) {
            return;
        }
        this.f685l = z6;
        ArrayList arrayList = this.f686m;
        if (arrayList.size() <= 0) {
            return;
        }
        a.a.u(arrayList.get(0));
        throw null;
    }

    @Override // androidx.appcompat.app.b
    public final int d() {
        return ((w3) this.f678e).f1443b;
    }

    @Override // androidx.appcompat.app.b
    public final Context e() {
        if (this.f675b == null) {
            TypedValue typedValue = new TypedValue();
            this.f674a.getTheme().resolveAttribute(f.a.actionBarWidgetTheme, typedValue, true);
            int i7 = typedValue.resourceId;
            if (i7 != 0) {
                this.f675b = new ContextThemeWrapper(this.f674a, i7);
            } else {
                this.f675b = this.f674a;
            }
        }
        return this.f675b;
    }

    @Override // androidx.appcompat.app.b
    public final void f() {
        if (this.f689p) {
            return;
        }
        this.f689p = true;
        A(false);
    }

    @Override // androidx.appcompat.app.b
    public final void h() {
        z(j.a.b(this.f674a).f6874a.getResources().getBoolean(f.b.abc_action_bar_embed_tabs));
    }

    @Override // androidx.appcompat.app.b
    public final boolean j(int i7, KeyEvent keyEvent) {
        k.o oVar;
        z0 z0Var = this.f682i;
        if (z0Var == null || (oVar = z0Var.f890d) == null) {
            return false;
        }
        oVar.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return oVar.performShortcut(i7, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.b
    public final void m(boolean z6) {
        if (this.f681h) {
            return;
        }
        n(z6);
    }

    @Override // androidx.appcompat.app.b
    public final void n(boolean z6) {
        y(z6 ? 4 : 0, 4);
    }

    @Override // androidx.appcompat.app.b
    public final void o() {
        y(2, 2);
    }

    @Override // androidx.appcompat.app.b
    public final void p() {
        y(0, 8);
    }

    @Override // androidx.appcompat.app.b
    public final void q(int i7) {
        ((w3) this.f678e).b(i7);
    }

    @Override // androidx.appcompat.app.b
    public final void r(Drawable drawable) {
        w3 w3Var = (w3) this.f678e;
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
        j.n nVar;
        this.f694u = z6;
        if (z6 || (nVar = this.f693t) == null) {
            return;
        }
        nVar.a();
    }

    @Override // androidx.appcompat.app.b
    public final void t(String str) {
        w3 w3Var = (w3) this.f678e;
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
        w3 w3Var = (w3) this.f678e;
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

    @Override // androidx.appcompat.app.b
    public final j.c v(a0 a0Var) {
        z0 z0Var = this.f682i;
        if (z0Var != null) {
            z0Var.a();
        }
        this.f676c.setHideOnContentScrollEnabled(false);
        this.f679f.e();
        z0 z0Var2 = new z0(this, this.f679f.getContext(), a0Var);
        k.o oVar = z0Var2.f890d;
        oVar.y();
        try {
            if (!z0Var2.f891e.b(z0Var2, oVar)) {
                return null;
            }
            this.f682i = z0Var2;
            z0Var2.g();
            this.f679f.c(z0Var2);
            w(true);
            return z0Var2;
        } finally {
            oVar.x();
        }
    }

    public final void w(boolean z6) {
        l1 l7;
        l1 l1Var;
        if (z6) {
            if (!this.f691r) {
                this.f691r = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.f676c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                A(false);
            }
        } else if (this.f691r) {
            this.f691r = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f676c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            A(false);
        }
        ActionBarContainer actionBarContainer = this.f677d;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        if (!p0.o0.c(actionBarContainer)) {
            if (z6) {
                ((w3) this.f678e).f1442a.setVisibility(4);
                this.f679f.setVisibility(0);
                return;
            } else {
                ((w3) this.f678e).f1442a.setVisibility(0);
                this.f679f.setVisibility(8);
                return;
            }
        }
        if (z6) {
            w3 w3Var = (w3) this.f678e;
            l7 = d1.a(w3Var.f1442a);
            l7.a(CropImageView.DEFAULT_ASPECT_RATIO);
            l7.c(100L);
            l7.d(new j.m(w3Var, 4));
            l1Var = this.f679f.l(0, 200L);
        } else {
            w3 w3Var2 = (w3) this.f678e;
            l1 a7 = d1.a(w3Var2.f1442a);
            a7.a(1.0f);
            a7.c(200L);
            a7.d(new j.m(w3Var2, 0));
            l7 = this.f679f.l(8, 100L);
            l1Var = a7;
        }
        j.n nVar = new j.n();
        ArrayList arrayList = nVar.f6935a;
        arrayList.add(l7);
        View view = (View) l7.f8160a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = (View) l1Var.f8160a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(l1Var);
        nVar.b();
    }

    public final void x(View view) {
        j1 wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(f.f.decor_content_parent);
        this.f676c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback findViewById = view.findViewById(f.f.action_bar);
        if (findViewById instanceof j1) {
            wrapper = (j1) findViewById;
        } else {
            if (!(findViewById instanceof Toolbar)) {
                throw new IllegalStateException("Can't make a decor toolbar out of ".concat(findViewById != null ? findViewById.getClass().getSimpleName() : "null"));
            }
            wrapper = ((Toolbar) findViewById).getWrapper();
        }
        this.f678e = wrapper;
        this.f679f = (ActionBarContextView) view.findViewById(f.f.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(f.f.action_bar_container);
        this.f677d = actionBarContainer;
        j1 j1Var = this.f678e;
        if (j1Var == null || this.f679f == null || actionBarContainer == null) {
            throw new IllegalStateException(a1.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        Context context = ((w3) j1Var).f1442a.getContext();
        this.f674a = context;
        if ((((w3) this.f678e).f1443b & 4) != 0) {
            this.f681h = true;
        }
        j.a b7 = j.a.b(context);
        int i7 = b7.f6874a.getApplicationInfo().targetSdkVersion;
        this.f678e.getClass();
        z(b7.f6874a.getResources().getBoolean(f.b.abc_action_bar_embed_tabs));
        TypedArray obtainStyledAttributes = this.f674a.obtainStyledAttributes(null, f.j.ActionBar, f.a.actionBarStyle, 0);
        if (obtainStyledAttributes.getBoolean(f.j.ActionBar_hideOnContentScroll, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f676c;
            if (!actionBarOverlayLayout2.f961h) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.f695v = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(f.j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.f677d;
            int i8 = d1.OVER_SCROLL_ALWAYS;
            p0.r0.s(actionBarContainer2, dimensionPixelSize);
        }
        obtainStyledAttributes.recycle();
    }

    public final void y(int i7, int i8) {
        j1 j1Var = this.f678e;
        int i9 = ((w3) j1Var).f1443b;
        if ((i8 & 4) != 0) {
            this.f681h = true;
        }
        ((w3) j1Var).a((i7 & i8) | ((~i8) & i9));
    }

    public final void z(boolean z6) {
        if (z6) {
            this.f677d.setTabContainer(null);
            w3 w3Var = (w3) this.f678e;
            ScrollingTabContainerView scrollingTabContainerView = w3Var.f1444c;
            if (scrollingTabContainerView != null) {
                ViewParent parent = scrollingTabContainerView.getParent();
                Toolbar toolbar = w3Var.f1442a;
                if (parent == toolbar) {
                    toolbar.removeView(w3Var.f1444c);
                }
            }
            w3Var.f1444c = null;
        } else {
            w3 w3Var2 = (w3) this.f678e;
            ScrollingTabContainerView scrollingTabContainerView2 = w3Var2.f1444c;
            if (scrollingTabContainerView2 != null) {
                ViewParent parent2 = scrollingTabContainerView2.getParent();
                Toolbar toolbar2 = w3Var2.f1442a;
                if (parent2 == toolbar2) {
                    toolbar2.removeView(w3Var2.f1444c);
                }
            }
            w3Var2.f1444c = null;
            this.f677d.setTabContainer(null);
        }
        this.f678e.getClass();
        ((w3) this.f678e).f1442a.setCollapsible(false);
        this.f676c.setHasNonEmbeddedTabs(false);
    }
}
