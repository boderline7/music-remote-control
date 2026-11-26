package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.a4;
import androidx.appcompat.widget.i1;
import androidx.appcompat.widget.w3;
import androidx.appcompat.widget.y3;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import p0.d1;
import p0.l1;
import p0.l2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j0 extends w implements k.m, LayoutInflater.Factory2 {

    /* renamed from: h0, reason: collision with root package name */
    public static final q.k f766h0 = new q.k();

    /* renamed from: i0, reason: collision with root package name */
    public static final int[] f767i0 = {R.attr.windowBackground};

    /* renamed from: j0, reason: collision with root package name */
    public static final boolean f768j0 = !"robolectric".equals(Build.FINGERPRINT);

    /* renamed from: k0, reason: collision with root package name */
    public static final boolean f769k0 = true;
    public ViewGroup A;
    public TextView B;
    public View C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public i0[] L;
    public i0 M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public Configuration R;
    public final int S;
    public int T;
    public int U;
    public boolean V;
    public e0 W;
    public e0 X;
    public boolean Y;
    public int Z;

    /* renamed from: b0, reason: collision with root package name */
    public boolean f771b0;

    /* renamed from: c0, reason: collision with root package name */
    public Rect f772c0;

    /* renamed from: d0, reason: collision with root package name */
    public Rect f773d0;

    /* renamed from: e0, reason: collision with root package name */
    public n0 f774e0;

    /* renamed from: f0, reason: collision with root package name */
    public OnBackInvokedDispatcher f775f0;

    /* renamed from: g0, reason: collision with root package name */
    public OnBackInvokedCallback f776g0;

    /* renamed from: j, reason: collision with root package name */
    public final Object f777j;

    /* renamed from: k, reason: collision with root package name */
    public final Context f778k;

    /* renamed from: l, reason: collision with root package name */
    public Window f779l;

    /* renamed from: m, reason: collision with root package name */
    public d0 f780m;

    /* renamed from: n, reason: collision with root package name */
    public final s f781n;

    /* renamed from: o, reason: collision with root package name */
    public b f782o;

    /* renamed from: p, reason: collision with root package name */
    public j.l f783p;

    /* renamed from: q, reason: collision with root package name */
    public CharSequence f784q;

    /* renamed from: r, reason: collision with root package name */
    public i1 f785r;

    /* renamed from: s, reason: collision with root package name */
    public y f786s;

    /* renamed from: t, reason: collision with root package name */
    public y f787t;

    /* renamed from: u, reason: collision with root package name */
    public j.c f788u;

    /* renamed from: v, reason: collision with root package name */
    public ActionBarContextView f789v;

    /* renamed from: w, reason: collision with root package name */
    public PopupWindow f790w;

    /* renamed from: x, reason: collision with root package name */
    public x f791x;

    /* renamed from: z, reason: collision with root package name */
    public boolean f793z;

    /* renamed from: y, reason: collision with root package name */
    public l1 f792y = null;

    /* renamed from: a0, reason: collision with root package name */
    public final x f770a0 = new x(this, 0);

    public j0(Context context, Window window, s sVar, Object obj) {
        r rVar;
        this.S = -100;
        this.f778k = context;
        this.f781n = sVar;
        this.f777j = obj;
        if (obj instanceof Dialog) {
            while (context != null) {
                if (!(context instanceof r)) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                } else {
                    rVar = (r) context;
                    break;
                }
            }
            rVar = null;
            if (rVar != null) {
                this.S = ((j0) rVar.getDelegate()).S;
            }
        }
        if (this.S == -100) {
            q.k kVar = f766h0;
            Integer num = (Integer) kVar.getOrDefault(this.f777j.getClass().getName(), null);
            if (num != null) {
                this.S = num.intValue();
                kVar.remove(this.f777j.getClass().getName());
            }
        }
        if (window != null) {
            o(window);
        }
        androidx.appcompat.widget.z.d();
    }

    public static l0.g p(Context context) {
        l0.g gVar;
        l0.g gVar2;
        if (Build.VERSION.SDK_INT >= 33 || (gVar = w.f868c) == null) {
            return null;
        }
        l0.g b7 = b0.b(context.getApplicationContext().getResources().getConfiguration());
        l0.h hVar = gVar.f7274a;
        if (((l0.i) hVar).f7275a.isEmpty()) {
            gVar2 = l0.g.f7273b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i7 = 0;
            while (i7 < ((l0.i) b7.f7274a).f7275a.size() + ((l0.i) hVar).f7275a.size()) {
                Locale locale = i7 < ((l0.i) hVar).f7275a.size() ? ((l0.i) hVar).f7275a.get(i7) : ((l0.i) b7.f7274a).f7275a.get(i7 - ((l0.i) hVar).f7275a.size());
                if (locale != null) {
                    linkedHashSet.add(locale);
                }
                i7++;
            }
            gVar2 = new l0.g(new l0.i(l0.f.a((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]))));
        }
        return ((l0.i) gVar2.f7274a).f7275a.isEmpty() ? b7 : gVar2;
    }

    public static Configuration u(Context context, int i7, l0.g gVar, Configuration configuration, boolean z6) {
        int i8 = i7 != 1 ? i7 != 2 ? z6 ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = CropImageView.DEFAULT_ASPECT_RATIO;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i8 | (configuration2.uiMode & (-49));
        if (gVar != null) {
            b0.d(configuration2, gVar);
        }
        return configuration2;
    }

    public final f0 A(Context context) {
        if (this.W == null) {
            if (g.f712e == null) {
                Context applicationContext = context.getApplicationContext();
                g.f712e = new g(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.W = new e0(this, g.f712e);
        }
        return this.W;
    }

    public final i0 B(int i7) {
        i0[] i0VarArr = this.L;
        if (i0VarArr == null || i0VarArr.length <= i7) {
            i0[] i0VarArr2 = new i0[i7 + 1];
            if (i0VarArr != null) {
                System.arraycopy(i0VarArr, 0, i0VarArr2, 0, i0VarArr.length);
            }
            this.L = i0VarArr2;
            i0VarArr = i0VarArr2;
        }
        i0 i0Var = i0VarArr[i7];
        if (i0Var != null) {
            return i0Var;
        }
        i0 i0Var2 = new i0();
        i0Var2.f729a = i7;
        i0Var2.f742n = false;
        i0VarArr[i7] = i0Var2;
        return i0Var2;
    }

    public final void C() {
        x();
        if (this.F && this.f782o == null) {
            Object obj = this.f777j;
            if (obj instanceof Activity) {
                this.f782o = new a1((Activity) obj, this.G);
            } else if (obj instanceof Dialog) {
                this.f782o = new a1((Dialog) obj);
            }
            b bVar = this.f782o;
            if (bVar != null) {
                bVar.m(this.f771b0);
            }
        }
    }

    public final void D(int i7) {
        this.Z = (1 << i7) | this.Z;
        if (this.Y) {
            return;
        }
        View decorView = this.f779l.getDecorView();
        int i8 = d1.OVER_SCROLL_ALWAYS;
        p0.l0.m(decorView, this.f770a0);
        this.Y = true;
    }

    public final int E(Context context, int i7) {
        if (i7 == -100) {
            return -1;
        }
        if (i7 != -1) {
            if (i7 == 0) {
                if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                return A(context).b();
            }
            if (i7 != 1 && i7 != 2) {
                if (i7 != 3) {
                    throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                }
                if (this.X == null) {
                    this.X = new e0(this, context);
                }
                return this.X.b();
            }
        }
        return i7;
    }

    public final boolean F() {
        boolean z6 = this.N;
        this.N = false;
        i0 B = B(0);
        if (B.f741m) {
            if (!z6) {
                t(B, true);
            }
            return true;
        }
        j.c cVar = this.f788u;
        if (cVar != null) {
            cVar.a();
            return true;
        }
        C();
        b bVar = this.f782o;
        return bVar != null && bVar.b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0162, code lost:
    
        if (r15.f7131g.getCount() > 0) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0142, code lost:
    
        if (r15 != null) goto L70;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void G(androidx.appcompat.app.i0 r14, android.view.KeyEvent r15) {
        /*
            Method dump skipped, instructions count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.j0.G(androidx.appcompat.app.i0, android.view.KeyEvent):void");
    }

    public final boolean H(i0 i0Var, int i7, KeyEvent keyEvent) {
        k.o oVar;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((i0Var.f739k || I(i0Var, keyEvent)) && (oVar = i0Var.f736h) != null) {
            return oVar.performShortcut(i7, keyEvent, 1);
        }
        return false;
    }

    public final boolean I(i0 i0Var, KeyEvent keyEvent) {
        i1 i1Var;
        i1 i1Var2;
        Resources.Theme theme;
        i1 i1Var3;
        i1 i1Var4;
        if (this.Q) {
            return false;
        }
        if (i0Var.f739k) {
            return true;
        }
        i0 i0Var2 = this.M;
        if (i0Var2 != null && i0Var2 != i0Var) {
            t(i0Var2, false);
        }
        Window.Callback callback = this.f779l.getCallback();
        int i7 = i0Var.f729a;
        if (callback != null) {
            i0Var.f735g = callback.onCreatePanelView(i7);
        }
        boolean z6 = i7 == 0 || i7 == 108;
        if (z6 && (i1Var4 = this.f785r) != null) {
            i1Var4.setMenuPrepared();
        }
        if (i0Var.f735g == null && (!z6 || !(this.f782o instanceof v0))) {
            k.o oVar = i0Var.f736h;
            if (oVar == null || i0Var.f743o) {
                if (oVar == null) {
                    Context context = this.f778k;
                    if ((i7 == 0 || i7 == 108) && this.f785r != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme2 = context.getTheme();
                        theme2.resolveAttribute(f.a.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            theme = context.getResources().newTheme();
                            theme.setTo(theme2);
                            theme.applyStyle(typedValue.resourceId, true);
                            theme.resolveAttribute(f.a.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme2.resolveAttribute(f.a.actionBarWidgetTheme, typedValue, true);
                            theme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (theme == null) {
                                theme = context.getResources().newTheme();
                                theme.setTo(theme2);
                            }
                            theme.applyStyle(typedValue.resourceId, true);
                        }
                        if (theme != null) {
                            j.f fVar = new j.f(context, 0);
                            fVar.getTheme().setTo(theme);
                            context = fVar;
                        }
                    }
                    k.o oVar2 = new k.o(context);
                    oVar2.f7143e = this;
                    k.o oVar3 = i0Var.f736h;
                    if (oVar2 != oVar3) {
                        if (oVar3 != null) {
                            oVar3.r(i0Var.f737i);
                        }
                        i0Var.f736h = oVar2;
                        k.k kVar = i0Var.f737i;
                        if (kVar != null) {
                            oVar2.b(kVar, oVar2.f7139a);
                        }
                    }
                    if (i0Var.f736h == null) {
                        return false;
                    }
                }
                if (z6 && (i1Var2 = this.f785r) != null) {
                    if (this.f786s == null) {
                        this.f786s = new y(this, 4);
                    }
                    i1Var2.setMenu(i0Var.f736h, this.f786s);
                }
                i0Var.f736h.y();
                if (!callback.onCreatePanelMenu(i7, i0Var.f736h)) {
                    k.o oVar4 = i0Var.f736h;
                    if (oVar4 != null) {
                        if (oVar4 != null) {
                            oVar4.r(i0Var.f737i);
                        }
                        i0Var.f736h = null;
                    }
                    if (z6 && (i1Var = this.f785r) != null) {
                        i1Var.setMenu(null, this.f786s);
                    }
                    return false;
                }
                i0Var.f743o = false;
            }
            i0Var.f736h.y();
            Bundle bundle = i0Var.f744p;
            if (bundle != null) {
                i0Var.f736h.s(bundle);
                i0Var.f744p = null;
            }
            if (!callback.onPreparePanel(0, i0Var.f735g, i0Var.f736h)) {
                if (z6 && (i1Var3 = this.f785r) != null) {
                    i1Var3.setMenu(null, this.f786s);
                }
                i0Var.f736h.x();
                return false;
            }
            i0Var.f736h.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            i0Var.f736h.x();
        }
        i0Var.f739k = true;
        i0Var.f740l = false;
        this.M = i0Var;
        return true;
    }

    public final void J() {
        if (this.f793z) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void K() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z6 = false;
            if (this.f775f0 != null && (B(0).f741m || this.f788u != null)) {
                z6 = true;
            }
            if (z6 && this.f776g0 == null) {
                this.f776g0 = c0.b(this.f775f0, this);
            } else {
                if (z6 || (onBackInvokedCallback = this.f776g0) == null) {
                    return;
                }
                c0.c(this.f775f0, onBackInvokedCallback);
            }
        }
    }

    public final int L(l2 l2Var, Rect rect) {
        boolean z6;
        boolean z7;
        int a7;
        int d7 = l2Var != null ? l2Var.d() : rect != null ? rect.top : 0;
        ActionBarContextView actionBarContextView = this.f789v;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z6 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f789v.getLayoutParams();
            if (this.f789v.isShown()) {
                if (this.f772c0 == null) {
                    this.f772c0 = new Rect();
                    this.f773d0 = new Rect();
                }
                Rect rect2 = this.f772c0;
                Rect rect3 = this.f773d0;
                if (l2Var == null) {
                    rect2.set(rect);
                } else {
                    rect2.set(l2Var.b(), l2Var.d(), l2Var.c(), l2Var.a());
                }
                ViewGroup viewGroup = this.A;
                Method method = a4.f1188a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect2, rect3);
                    } catch (Exception unused) {
                    }
                }
                int i7 = rect2.top;
                int i8 = rect2.left;
                int i9 = rect2.right;
                ViewGroup viewGroup2 = this.A;
                int i10 = d1.OVER_SCROLL_ALWAYS;
                l2 a8 = p0.s0.a(viewGroup2);
                int b7 = a8 == null ? 0 : a8.b();
                int c7 = a8 == null ? 0 : a8.c();
                if (marginLayoutParams.topMargin == i7 && marginLayoutParams.leftMargin == i8 && marginLayoutParams.rightMargin == i9) {
                    z7 = false;
                } else {
                    marginLayoutParams.topMargin = i7;
                    marginLayoutParams.leftMargin = i8;
                    marginLayoutParams.rightMargin = i9;
                    z7 = true;
                }
                Context context = this.f778k;
                if (i7 <= 0 || this.C != null) {
                    View view = this.C;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i11 = marginLayoutParams2.height;
                        int i12 = marginLayoutParams.topMargin;
                        if (i11 != i12 || marginLayoutParams2.leftMargin != b7 || marginLayoutParams2.rightMargin != c7) {
                            marginLayoutParams2.height = i12;
                            marginLayoutParams2.leftMargin = b7;
                            marginLayoutParams2.rightMargin = c7;
                            this.C.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(context);
                    this.C = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = b7;
                    layoutParams.rightMargin = c7;
                    this.A.addView(this.C, -1, layoutParams);
                }
                View view3 = this.C;
                r5 = view3 != null;
                if (r5 && view3.getVisibility() != 0) {
                    View view4 = this.C;
                    if ((p0.l0.g(view4) & 8192) != 0) {
                        int i13 = f.c.abc_decor_view_status_guard_light;
                        Object obj = g0.g.f6423a;
                        a7 = g0.d.a(context, i13);
                    } else {
                        int i14 = f.c.abc_decor_view_status_guard;
                        Object obj2 = g0.g.f6423a;
                        a7 = g0.d.a(context, i14);
                    }
                    view4.setBackgroundColor(a7);
                }
                if (!this.H && r5) {
                    d7 = 0;
                }
                z6 = r5;
                r5 = z7;
            } else if (marginLayoutParams.topMargin != 0) {
                marginLayoutParams.topMargin = 0;
                z6 = false;
            } else {
                z6 = false;
                r5 = false;
            }
            if (r5) {
                this.f789v.setLayoutParams(marginLayoutParams);
            }
        }
        View view5 = this.C;
        if (view5 != null) {
            view5.setVisibility(z6 ? 0 : 8);
        }
        return d7;
    }

    @Override // androidx.appcompat.app.w
    public final void a() {
        if (this.f782o != null) {
            C();
            if (this.f782o.g()) {
                return;
            }
            D(0);
        }
    }

    @Override // androidx.appcompat.app.w
    public final void c() {
        String str;
        this.O = true;
        n(false, true);
        y();
        Object obj = this.f777j;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    str = e0.s.c(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e7) {
                    throw new IllegalArgumentException(e7);
                }
            } catch (IllegalArgumentException unused) {
                str = null;
            }
            if (str != null) {
                b bVar = this.f782o;
                if (bVar == null) {
                    this.f771b0 = true;
                } else {
                    bVar.m(true);
                }
            }
            synchronized (w.f873h) {
                w.e(this);
                w.f872g.add(new WeakReference(this));
            }
        }
        this.R = new Configuration(this.f778k.getResources().getConfiguration());
        this.P = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @Override // androidx.appcompat.app.w
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.f777j
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = androidx.appcompat.app.w.f873h
            monitor-enter(r0)
            androidx.appcompat.app.w.e(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r1
        L11:
            boolean r0 = r3.Y
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.f779l
            android.view.View r0 = r0.getDecorView()
            androidx.appcompat.app.x r1 = r3.f770a0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.Q = r0
            int r0 = r3.S
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.f777j
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            q.k r0 = androidx.appcompat.app.j0.f766h0
            java.lang.Object r1 = r3.f777j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.S
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            q.k r0 = androidx.appcompat.app.j0.f766h0
            java.lang.Object r1 = r3.f777j
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            androidx.appcompat.app.b r0 = r3.f782o
            if (r0 == 0) goto L63
            r0.i()
        L63:
            androidx.appcompat.app.e0 r0 = r3.W
            if (r0 == 0) goto L6a
            r0.a()
        L6a:
            androidx.appcompat.app.e0 r0 = r3.X
            if (r0 == 0) goto L71
            r0.a()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.j0.d():void");
    }

    @Override // androidx.appcompat.app.w
    public final boolean f(int i7) {
        if (i7 == 8) {
            i7 = 108;
        } else if (i7 == 9) {
            i7 = 109;
        }
        if (this.J && i7 == 108) {
            return false;
        }
        if (this.F && i7 == 1) {
            this.F = false;
        }
        if (i7 == 1) {
            J();
            this.J = true;
            return true;
        }
        if (i7 == 2) {
            J();
            this.D = true;
            return true;
        }
        if (i7 == 5) {
            J();
            this.E = true;
            return true;
        }
        if (i7 == 10) {
            J();
            this.H = true;
            return true;
        }
        if (i7 == 108) {
            J();
            this.F = true;
            return true;
        }
        if (i7 != 109) {
            return this.f779l.requestFeature(i7);
        }
        J();
        this.G = true;
        return true;
    }

    @Override // androidx.appcompat.app.w
    public final void g(int i7) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f778k).inflate(i7, viewGroup);
        this.f780m.a(this.f779l.getCallback());
    }

    @Override // androidx.appcompat.app.w
    public final void h(View view) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.f780m.a(this.f779l.getCallback());
    }

    @Override // androidx.appcompat.app.w
    public final void i(View view, ViewGroup.LayoutParams layoutParams) {
        x();
        ViewGroup viewGroup = (ViewGroup) this.A.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.f780m.a(this.f779l.getCallback());
    }

    @Override // androidx.appcompat.app.w
    public final void k(CharSequence charSequence) {
        this.f784q = charSequence;
        i1 i1Var = this.f785r;
        if (i1Var != null) {
            i1Var.setWindowTitle(charSequence);
            return;
        }
        b bVar = this.f782o;
        if (bVar != null) {
            bVar.u(charSequence);
            return;
        }
        TextView textView = this.B;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // k.m
    public final boolean l(k.o oVar, MenuItem menuItem) {
        i0 i0Var;
        Window.Callback callback = this.f779l.getCallback();
        if (callback != null && !this.Q) {
            k.o k7 = oVar.k();
            i0[] i0VarArr = this.L;
            int length = i0VarArr != null ? i0VarArr.length : 0;
            int i7 = 0;
            while (true) {
                if (i7 < length) {
                    i0Var = i0VarArr[i7];
                    if (i0Var != null && i0Var.f736h == k7) {
                        break;
                    }
                    i7++;
                } else {
                    i0Var = null;
                    break;
                }
            }
            if (i0Var != null) {
                return callback.onMenuItemSelected(i0Var.f729a, menuItem);
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0163  */
    @Override // androidx.appcompat.app.w
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final j.c m(j.b r9) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.j0.m(j.b):j.c");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0165 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0180  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean n(boolean r17, boolean r18) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.j0.n(boolean, boolean):boolean");
    }

    public final void o(Window window) {
        Drawable drawable;
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        OnBackInvokedCallback onBackInvokedCallback;
        int resourceId;
        if (this.f779l != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof d0) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        d0 d0Var = new d0(this, callback);
        this.f780m = d0Var;
        window.setCallback(d0Var);
        int[] iArr = f767i0;
        Context context = this.f778k;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, iArr);
        if (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) {
            drawable = null;
        } else {
            androidx.appcompat.widget.z a7 = androidx.appcompat.widget.z.a();
            synchronized (a7) {
                drawable = a7.f1494a.f(context, resourceId, true);
            }
        }
        if (drawable != null) {
            window.setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.f779l = window;
        if (Build.VERSION.SDK_INT < 33 || (onBackInvokedDispatcher = this.f775f0) != null) {
            return;
        }
        if (onBackInvokedDispatcher != null && (onBackInvokedCallback = this.f776g0) != null) {
            c0.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f776g0 = null;
        }
        Object obj = this.f777j;
        if (obj instanceof Activity) {
            Activity activity = (Activity) obj;
            if (activity.getWindow() != null) {
                this.f775f0 = c0.a(activity);
                K();
            }
        }
        this.f775f0 = null;
        K();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        char c7;
        View view2;
        if (this.f774e0 == null) {
            int[] iArr = f.j.AppCompatTheme;
            Context context2 = this.f778k;
            String string = context2.obtainStyledAttributes(iArr).getString(f.j.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.f774e0 = new n0();
            } else {
                try {
                    this.f774e0 = (n0) context2.getClassLoader().loadClass(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable unused) {
                    this.f774e0 = new n0();
                }
            }
        }
        n0 n0Var = this.f774e0;
        int i7 = y3.MAX_SDK_WHERE_REQUIRED;
        n0Var.getClass();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.View, 0, 0);
        int resourceId = obtainStyledAttributes.getResourceId(f.j.View_theme, 0);
        obtainStyledAttributes.recycle();
        Context fVar = (resourceId == 0 || ((context instanceof j.f) && ((j.f) context).f6878a == resourceId)) ? context : new j.f(context, resourceId);
        str.getClass();
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c7 = 0;
                    break;
                }
                c7 = 65535;
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c7 = 1;
                    break;
                }
                c7 = 65535;
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c7 = 2;
                    break;
                }
                c7 = 65535;
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c7 = 3;
                    break;
                }
                c7 = 65535;
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c7 = 4;
                    break;
                }
                c7 = 65535;
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c7 = 5;
                    break;
                }
                c7 = 65535;
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c7 = 6;
                    break;
                }
                c7 = 65535;
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c7 = 7;
                    break;
                }
                c7 = 65535;
                break;
            case 799298502:
                if (str.equals("ToggleButton")) {
                    c7 = '\b';
                    break;
                }
                c7 = 65535;
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c7 = '\t';
                    break;
                }
                c7 = 65535;
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c7 = '\n';
                    break;
                }
                c7 = 65535;
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c7 = 11;
                    break;
                }
                c7 = 65535;
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c7 = '\f';
                    break;
                }
                c7 = 65535;
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c7 = '\r';
                    break;
                }
                c7 = 65535;
                break;
            default:
                c7 = 65535;
                break;
        }
        View view3 = null;
        switch (c7) {
            case 0:
                view2 = new AppCompatRatingBar(fVar, attributeSet);
                break;
            case 1:
                view2 = new AppCompatCheckedTextView(fVar, attributeSet);
                break;
            case 2:
                view2 = new AppCompatMultiAutoCompleteTextView(fVar, attributeSet);
                break;
            case 3:
                AppCompatTextView e7 = n0Var.e(fVar, attributeSet);
                n0Var.g(e7, str);
                view2 = e7;
                break;
            case 4:
                view2 = new AppCompatImageButton(fVar, attributeSet);
                break;
            case 5:
                view2 = new AppCompatSeekBar(fVar, attributeSet);
                break;
            case 6:
                view2 = new AppCompatSpinner(fVar, attributeSet);
                break;
            case 7:
                AppCompatRadioButton d7 = n0Var.d(fVar, attributeSet);
                n0Var.g(d7, str);
                view2 = d7;
                break;
            case '\b':
                view2 = new AppCompatToggleButton(fVar, attributeSet);
                break;
            case '\t':
                view2 = new AppCompatImageView(fVar, attributeSet);
                break;
            case '\n':
                AppCompatAutoCompleteTextView a7 = n0Var.a(fVar, attributeSet);
                n0Var.g(a7, str);
                view2 = a7;
                break;
            case 11:
                AppCompatCheckBox c8 = n0Var.c(fVar, attributeSet);
                n0Var.g(c8, str);
                view2 = c8;
                break;
            case '\f':
                view2 = new AppCompatEditText(fVar, attributeSet);
                break;
            case '\r':
                AppCompatButton b7 = n0Var.b(fVar, attributeSet);
                n0Var.g(b7, str);
                view2 = b7;
                break;
            default:
                view2 = null;
                break;
        }
        if (view2 == null && context != fVar) {
            Object[] objArr = n0Var.f839a;
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = fVar;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i8 = 0;
                    while (true) {
                        String[] strArr = n0.f837g;
                        if (i8 < 3) {
                            View f7 = n0Var.f(fVar, str, strArr[i8]);
                            if (f7 != null) {
                                objArr[0] = null;
                                objArr[1] = null;
                                view3 = f7;
                            } else {
                                i8++;
                            }
                        }
                    }
                } else {
                    View f8 = n0Var.f(fVar, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view3 = f8;
                }
            } catch (Exception unused2) {
            } finally {
                objArr[0] = null;
                objArr[1] = null;
            }
            view2 = view3;
        }
        if (view2 != null) {
            Context context3 = view2.getContext();
            if (context3 instanceof ContextWrapper) {
                int i9 = d1.OVER_SCROLL_ALWAYS;
                if (p0.k0.a(view2)) {
                    TypedArray obtainStyledAttributes2 = context3.obtainStyledAttributes(attributeSet, n0.f833c);
                    String string2 = obtainStyledAttributes2.getString(0);
                    if (string2 != null) {
                        view2.setOnClickListener(new m0(view2, string2));
                    }
                    obtainStyledAttributes2.recycle();
                }
            }
            if (Build.VERSION.SDK_INT <= 28) {
                TypedArray obtainStyledAttributes3 = fVar.obtainStyledAttributes(attributeSet, n0.f834d);
                if (obtainStyledAttributes3.hasValue(0)) {
                    boolean z6 = obtainStyledAttributes3.getBoolean(0, false);
                    int i10 = d1.OVER_SCROLL_ALWAYS;
                    new p0.h0(d0.c.tag_accessibility_heading, 3).b(view2, Boolean.valueOf(z6));
                }
                obtainStyledAttributes3.recycle();
                TypedArray obtainStyledAttributes4 = fVar.obtainStyledAttributes(attributeSet, n0.f835e);
                if (obtainStyledAttributes4.hasValue(0)) {
                    d1.p(view2, obtainStyledAttributes4.getString(0));
                }
                obtainStyledAttributes4.recycle();
                TypedArray obtainStyledAttributes5 = fVar.obtainStyledAttributes(attributeSet, n0.f836f);
                if (obtainStyledAttributes5.hasValue(0)) {
                    boolean z7 = obtainStyledAttributes5.getBoolean(0, false);
                    int i11 = d1.OVER_SCROLL_ALWAYS;
                    new p0.h0(d0.c.tag_screen_reader_focusable, 0).b(view2, Boolean.valueOf(z7));
                }
                obtainStyledAttributes5.recycle();
            }
        }
        return view2;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public final void q(int i7, i0 i0Var, k.o oVar) {
        if (oVar == null) {
            if (i0Var == null && i7 >= 0) {
                i0[] i0VarArr = this.L;
                if (i7 < i0VarArr.length) {
                    i0Var = i0VarArr[i7];
                }
            }
            if (i0Var != null) {
                oVar = i0Var.f736h;
            }
        }
        if ((i0Var == null || i0Var.f741m) && !this.Q) {
            d0 d0Var = this.f780m;
            Window.Callback callback = this.f779l.getCallback();
            d0Var.getClass();
            try {
                d0Var.f705e = true;
                callback.onPanelClosed(i7, oVar);
            } finally {
                d0Var.f705e = false;
            }
        }
    }

    public final void r(k.o oVar) {
        androidx.appcompat.widget.m mVar;
        if (this.K) {
            return;
        }
        this.K = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f785r;
        actionBarOverlayLayout.k();
        ActionMenuView actionMenuView = ((w3) actionBarOverlayLayout.f958e).f1442a.f1152a;
        if (actionMenuView != null && (mVar = actionMenuView.f985t) != null) {
            mVar.g();
            androidx.appcompat.widget.h hVar = mVar.f1343u;
            if (hVar != null && hVar.b()) {
                hVar.f7055j.dismiss();
            }
        }
        Window.Callback callback = this.f779l.getCallback();
        if (callback != null && !this.Q) {
            callback.onPanelClosed(108, oVar);
        }
        this.K = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0048, code lost:
    
        if (r6.l() != false) goto L20;
     */
    @Override // k.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void s(k.o r6) {
        /*
            r5 = this;
            androidx.appcompat.widget.i1 r6 = r5.f785r
            r0 = 1
            r1 = 0
            if (r6 == 0) goto Ld3
            androidx.appcompat.widget.ActionBarOverlayLayout r6 = (androidx.appcompat.widget.ActionBarOverlayLayout) r6
            r6.k()
            androidx.appcompat.widget.j1 r6 = r6.f958e
            androidx.appcompat.widget.w3 r6 = (androidx.appcompat.widget.w3) r6
            androidx.appcompat.widget.Toolbar r6 = r6.f1442a
            int r2 = r6.getVisibility()
            if (r2 != 0) goto Ld3
            androidx.appcompat.widget.ActionMenuView r6 = r6.f1152a
            if (r6 == 0) goto Ld3
            boolean r6 = r6.f984s
            if (r6 == 0) goto Ld3
            android.content.Context r6 = r5.f778k
            android.view.ViewConfiguration r6 = android.view.ViewConfiguration.get(r6)
            boolean r6 = r6.hasPermanentMenuKey()
            if (r6 == 0) goto L4a
            androidx.appcompat.widget.i1 r6 = r5.f785r
            androidx.appcompat.widget.ActionBarOverlayLayout r6 = (androidx.appcompat.widget.ActionBarOverlayLayout) r6
            r6.k()
            androidx.appcompat.widget.j1 r6 = r6.f958e
            androidx.appcompat.widget.w3 r6 = (androidx.appcompat.widget.w3) r6
            androidx.appcompat.widget.Toolbar r6 = r6.f1442a
            androidx.appcompat.widget.ActionMenuView r6 = r6.f1152a
            if (r6 == 0) goto Ld3
            androidx.appcompat.widget.m r6 = r6.f985t
            if (r6 == 0) goto Ld3
            android.support.v4.media.i r2 = r6.f1344v
            if (r2 != 0) goto L4a
            boolean r6 = r6.l()
            if (r6 == 0) goto Ld3
        L4a:
            android.view.Window r6 = r5.f779l
            android.view.Window$Callback r6 = r6.getCallback()
            androidx.appcompat.widget.i1 r2 = r5.f785r
            androidx.appcompat.widget.ActionBarOverlayLayout r2 = (androidx.appcompat.widget.ActionBarOverlayLayout) r2
            r2.k()
            androidx.appcompat.widget.j1 r2 = r2.f958e
            androidx.appcompat.widget.w3 r2 = (androidx.appcompat.widget.w3) r2
            androidx.appcompat.widget.Toolbar r2 = r2.f1442a
            boolean r2 = r2.q()
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 == 0) goto L8c
            androidx.appcompat.widget.i1 r0 = r5.f785r
            androidx.appcompat.widget.ActionBarOverlayLayout r0 = (androidx.appcompat.widget.ActionBarOverlayLayout) r0
            r0.k()
            androidx.appcompat.widget.j1 r0 = r0.f958e
            androidx.appcompat.widget.w3 r0 = (androidx.appcompat.widget.w3) r0
            androidx.appcompat.widget.Toolbar r0 = r0.f1442a
            androidx.appcompat.widget.ActionMenuView r0 = r0.f1152a
            if (r0 == 0) goto L7e
            androidx.appcompat.widget.m r0 = r0.f985t
            if (r0 == 0) goto L7e
            boolean r0 = r0.g()
        L7e:
            boolean r0 = r5.Q
            if (r0 != 0) goto Le0
            androidx.appcompat.app.i0 r0 = r5.B(r1)
            k.o r0 = r0.f736h
            r6.onPanelClosed(r3, r0)
            goto Le0
        L8c:
            if (r6 == 0) goto Le0
            boolean r2 = r5.Q
            if (r2 != 0) goto Le0
            boolean r2 = r5.Y
            if (r2 == 0) goto La9
            int r2 = r5.Z
            r0 = r0 & r2
            if (r0 == 0) goto La9
            android.view.Window r0 = r5.f779l
            android.view.View r0 = r0.getDecorView()
            androidx.appcompat.app.x r2 = r5.f770a0
            r0.removeCallbacks(r2)
            r2.run()
        La9:
            androidx.appcompat.app.i0 r0 = r5.B(r1)
            k.o r2 = r0.f736h
            if (r2 == 0) goto Le0
            boolean r4 = r0.f743o
            if (r4 != 0) goto Le0
            android.view.View r4 = r0.f735g
            boolean r1 = r6.onPreparePanel(r1, r4, r2)
            if (r1 == 0) goto Le0
            k.o r0 = r0.f736h
            r6.onMenuOpened(r3, r0)
            androidx.appcompat.widget.i1 r6 = r5.f785r
            androidx.appcompat.widget.ActionBarOverlayLayout r6 = (androidx.appcompat.widget.ActionBarOverlayLayout) r6
            r6.k()
            androidx.appcompat.widget.j1 r6 = r6.f958e
            androidx.appcompat.widget.w3 r6 = (androidx.appcompat.widget.w3) r6
            androidx.appcompat.widget.Toolbar r6 = r6.f1442a
            r6.w()
            goto Le0
        Ld3:
            androidx.appcompat.app.i0 r6 = r5.B(r1)
            r6.f742n = r0
            r5.t(r6, r1)
            r0 = 0
            r5.G(r6, r0)
        Le0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.j0.s(k.o):void");
    }

    public final void t(i0 i0Var, boolean z6) {
        g0 g0Var;
        i1 i1Var;
        if (z6 && i0Var.f729a == 0 && (i1Var = this.f785r) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) i1Var;
            actionBarOverlayLayout.k();
            if (((w3) actionBarOverlayLayout.f958e).f1442a.q()) {
                r(i0Var.f736h);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.f778k.getSystemService("window");
        if (windowManager != null && i0Var.f741m && (g0Var = i0Var.f733e) != null) {
            windowManager.removeView(g0Var);
            if (z6) {
                q(i0Var.f729a, i0Var, null);
            }
        }
        i0Var.f739k = false;
        i0Var.f740l = false;
        i0Var.f741m = false;
        i0Var.f734f = null;
        i0Var.f742n = true;
        if (this.M == i0Var) {
            this.M = null;
        }
        if (i0Var.f729a == 0) {
            K();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean v(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.j0.v(android.view.KeyEvent):boolean");
    }

    public final void w(int i7) {
        i0 B = B(i7);
        if (B.f736h != null) {
            Bundle bundle = new Bundle();
            B.f736h.u(bundle);
            if (bundle.size() > 0) {
                B.f744p = bundle;
            }
            B.f736h.y();
            B.f736h.clear();
        }
        B.f743o = true;
        B.f742n = true;
        if ((i7 == 108 || i7 == 0) && this.f785r != null) {
            i0 B2 = B(0);
            B2.f739k = false;
            I(B2, null);
        }
    }

    public final void x() {
        ViewGroup viewGroup;
        if (this.f793z) {
            return;
        }
        int[] iArr = f.j.AppCompatTheme;
        Context context = this.f778k;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!obtainStyledAttributes.hasValue(f.j.AppCompatTheme_windowActionBar)) {
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        int i7 = 0;
        if (obtainStyledAttributes.getBoolean(f.j.AppCompatTheme_windowNoTitle, false)) {
            f(1);
        } else if (obtainStyledAttributes.getBoolean(f.j.AppCompatTheme_windowActionBar, false)) {
            f(108);
        }
        if (obtainStyledAttributes.getBoolean(f.j.AppCompatTheme_windowActionBarOverlay, false)) {
            f(109);
        }
        if (obtainStyledAttributes.getBoolean(f.j.AppCompatTheme_windowActionModeOverlay, false)) {
            f(10);
        }
        this.I = obtainStyledAttributes.getBoolean(f.j.AppCompatTheme_android_windowIsFloating, false);
        obtainStyledAttributes.recycle();
        y();
        this.f779l.getDecorView();
        LayoutInflater from = LayoutInflater.from(context);
        int i8 = 2;
        if (this.J) {
            viewGroup = this.H ? (ViewGroup) from.inflate(f.g.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) from.inflate(f.g.abc_screen_simple, (ViewGroup) null);
        } else if (this.I) {
            viewGroup = (ViewGroup) from.inflate(f.g.abc_dialog_title_material, (ViewGroup) null);
            this.G = false;
            this.F = false;
        } else if (this.F) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(f.a.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new j.f(context, typedValue.resourceId) : context).inflate(f.g.abc_screen_toolbar, (ViewGroup) null);
            i1 i1Var = (i1) viewGroup.findViewById(f.f.decor_content_parent);
            this.f785r = i1Var;
            i1Var.setWindowCallback(this.f779l.getCallback());
            if (this.G) {
                ((ActionBarOverlayLayout) this.f785r).h(109);
            }
            if (this.D) {
                ((ActionBarOverlayLayout) this.f785r).h(2);
            }
            if (this.E) {
                ((ActionBarOverlayLayout) this.f785r).h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.F + ", windowActionBarOverlay: " + this.G + ", android:windowIsFloating: " + this.I + ", windowActionModeOverlay: " + this.H + ", windowNoTitle: " + this.J + " }");
        }
        y yVar = new y(this, i7);
        int i9 = d1.OVER_SCROLL_ALWAYS;
        p0.r0.u(viewGroup, yVar);
        if (this.f785r == null) {
            this.B = (TextView) viewGroup.findViewById(f.f.title);
        }
        Method method = a4.f1188a;
        try {
            Method method2 = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method2.isAccessible()) {
                method2.setAccessible(true);
            }
            method2.invoke(viewGroup, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(f.f.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f779l.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f779l.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new y(this, i8));
        this.A = viewGroup;
        Object obj = this.f777j;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.f784q;
        if (!TextUtils.isEmpty(title)) {
            i1 i1Var2 = this.f785r;
            if (i1Var2 != null) {
                i1Var2.setWindowTitle(title);
            } else {
                b bVar = this.f782o;
                if (bVar != null) {
                    bVar.u(title);
                } else {
                    TextView textView = this.B;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.A.findViewById(R.id.content);
        View decorView = this.f779l.getDecorView();
        contentFrameLayout2.setDecorPadding(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(f.j.AppCompatTheme);
        obtainStyledAttributes2.getValue(f.j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout2.getMinWidthMajor());
        obtainStyledAttributes2.getValue(f.j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout2.getMinWidthMinor());
        if (obtainStyledAttributes2.hasValue(f.j.AppCompatTheme_windowFixedWidthMajor)) {
            obtainStyledAttributes2.getValue(f.j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout2.getFixedWidthMajor());
        }
        if (obtainStyledAttributes2.hasValue(f.j.AppCompatTheme_windowFixedWidthMinor)) {
            obtainStyledAttributes2.getValue(f.j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout2.getFixedWidthMinor());
        }
        if (obtainStyledAttributes2.hasValue(f.j.AppCompatTheme_windowFixedHeightMajor)) {
            obtainStyledAttributes2.getValue(f.j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout2.getFixedHeightMajor());
        }
        if (obtainStyledAttributes2.hasValue(f.j.AppCompatTheme_windowFixedHeightMinor)) {
            obtainStyledAttributes2.getValue(f.j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout2.getFixedHeightMinor());
        }
        obtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.f793z = true;
        i0 B = B(0);
        if (this.Q || B.f736h != null) {
            return;
        }
        D(108);
    }

    public final void y() {
        if (this.f779l == null) {
            Object obj = this.f777j;
            if (obj instanceof Activity) {
                o(((Activity) obj).getWindow());
            }
        }
        if (this.f779l == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final Context z() {
        C();
        b bVar = this.f782o;
        Context e7 = bVar != null ? bVar.e() : null;
        return e7 == null ? this.f778k : e7;
    }
}
