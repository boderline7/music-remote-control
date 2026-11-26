package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.customview.view.AbsSavedState;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class Toolbar extends ViewGroup {
    public ColorStateList A;
    public boolean B;
    public boolean C;
    public final ArrayList D;
    public final ArrayList E;
    public final int[] F;
    public final p0.s G;
    public ArrayList H;
    public u3 I;
    public final q3 J;
    public w3 K;
    public m L;
    public s3 M;
    public k.b0 N;
    public k.m O;
    public boolean P;
    public OnBackInvokedCallback Q;
    public OnBackInvokedDispatcher R;
    public boolean S;
    public final androidx.appcompat.app.s0 T;

    /* renamed from: a, reason: collision with root package name */
    public ActionMenuView f1152a;

    /* renamed from: b, reason: collision with root package name */
    public AppCompatTextView f1153b;

    /* renamed from: c, reason: collision with root package name */
    public AppCompatTextView f1154c;

    /* renamed from: d, reason: collision with root package name */
    public AppCompatImageButton f1155d;

    /* renamed from: e, reason: collision with root package name */
    public AppCompatImageView f1156e;

    /* renamed from: f, reason: collision with root package name */
    public final Drawable f1157f;

    /* renamed from: g, reason: collision with root package name */
    public final CharSequence f1158g;

    /* renamed from: h, reason: collision with root package name */
    public AppCompatImageButton f1159h;

    /* renamed from: i, reason: collision with root package name */
    public View f1160i;

    /* renamed from: j, reason: collision with root package name */
    public Context f1161j;

    /* renamed from: k, reason: collision with root package name */
    public int f1162k;

    /* renamed from: l, reason: collision with root package name */
    public int f1163l;

    /* renamed from: m, reason: collision with root package name */
    public int f1164m;

    /* renamed from: n, reason: collision with root package name */
    public final int f1165n;

    /* renamed from: o, reason: collision with root package name */
    public final int f1166o;

    /* renamed from: p, reason: collision with root package name */
    public int f1167p;

    /* renamed from: q, reason: collision with root package name */
    public int f1168q;

    /* renamed from: r, reason: collision with root package name */
    public int f1169r;

    /* renamed from: s, reason: collision with root package name */
    public int f1170s;

    /* renamed from: t, reason: collision with root package name */
    public n2 f1171t;

    /* renamed from: u, reason: collision with root package name */
    public int f1172u;

    /* renamed from: v, reason: collision with root package name */
    public int f1173v;

    /* renamed from: w, reason: collision with root package name */
    public final int f1174w;

    /* renamed from: x, reason: collision with root package name */
    public CharSequence f1175x;

    /* renamed from: y, reason: collision with root package name */
    public CharSequence f1176y;

    /* renamed from: z, reason: collision with root package name */
    public ColorStateList f1177z;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new v3();

        /* renamed from: b, reason: collision with root package name */
        public int f1178b;

        /* renamed from: c, reason: collision with root package name */
        public boolean f1179c;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f1178b = parcel.readInt();
            this.f1179c = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeInt(this.f1178b);
            parcel.writeInt(this.f1179c ? 1 : 0);
        }
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1174w = 8388627;
        this.D = new ArrayList();
        this.E = new ArrayList();
        this.F = new int[2];
        this.G = new p0.s(new p3(this, 0));
        this.H = new ArrayList();
        this.J = new q3(this);
        this.T = new androidx.appcompat.app.s0(3, this);
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(getContext(), attributeSet, f.j.Toolbar, i7);
        p0.d1.n(this, context, f.j.Toolbar, attributeSet, (TypedArray) S.f715c, i7);
        this.f1163l = S.N(f.j.Toolbar_titleTextAppearance, 0);
        this.f1164m = S.N(f.j.Toolbar_subtitleTextAppearance, 0);
        this.f1174w = ((TypedArray) S.f715c).getInteger(f.j.Toolbar_android_gravity, 8388627);
        this.f1165n = ((TypedArray) S.f715c).getInteger(f.j.Toolbar_buttonGravity, 48);
        int G = S.G(f.j.Toolbar_titleMargin, 0);
        G = S.R(f.j.Toolbar_titleMargins) ? S.G(f.j.Toolbar_titleMargins, G) : G;
        this.f1170s = G;
        this.f1169r = G;
        this.f1168q = G;
        this.f1167p = G;
        int G2 = S.G(f.j.Toolbar_titleMarginStart, -1);
        if (G2 >= 0) {
            this.f1167p = G2;
        }
        int G3 = S.G(f.j.Toolbar_titleMarginEnd, -1);
        if (G3 >= 0) {
            this.f1168q = G3;
        }
        int G4 = S.G(f.j.Toolbar_titleMarginTop, -1);
        if (G4 >= 0) {
            this.f1169r = G4;
        }
        int G5 = S.G(f.j.Toolbar_titleMarginBottom, -1);
        if (G5 >= 0) {
            this.f1170s = G5;
        }
        this.f1166o = S.H(f.j.Toolbar_maxButtonHeight, -1);
        int G6 = S.G(f.j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int G7 = S.G(f.j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int H = S.H(f.j.Toolbar_contentInsetLeft, 0);
        int H2 = S.H(f.j.Toolbar_contentInsetRight, 0);
        d();
        n2 n2Var = this.f1171t;
        n2Var.f1360h = false;
        if (H != Integer.MIN_VALUE) {
            n2Var.f1357e = H;
            n2Var.f1353a = H;
        }
        if (H2 != Integer.MIN_VALUE) {
            n2Var.f1358f = H2;
            n2Var.f1354b = H2;
        }
        if (G6 != Integer.MIN_VALUE || G7 != Integer.MIN_VALUE) {
            n2Var.a(G6, G7);
        }
        this.f1172u = S.G(f.j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.f1173v = S.G(f.j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.f1157f = S.I(f.j.Toolbar_collapseIcon);
        this.f1158g = S.Q(f.j.Toolbar_collapseContentDescription);
        CharSequence Q = S.Q(f.j.Toolbar_title);
        if (!TextUtils.isEmpty(Q)) {
            setTitle(Q);
        }
        CharSequence Q2 = S.Q(f.j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(Q2)) {
            setSubtitle(Q2);
        }
        this.f1161j = getContext();
        setPopupTheme(S.N(f.j.Toolbar_popupTheme, 0));
        Drawable I = S.I(f.j.Toolbar_navigationIcon);
        if (I != null) {
            setNavigationIcon(I);
        }
        CharSequence Q3 = S.Q(f.j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(Q3)) {
            setNavigationContentDescription(Q3);
        }
        Drawable I2 = S.I(f.j.Toolbar_logo);
        if (I2 != null) {
            setLogo(I2);
        }
        CharSequence Q4 = S.Q(f.j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(Q4)) {
            setLogoDescription(Q4);
        }
        if (S.R(f.j.Toolbar_titleTextColor)) {
            setTitleTextColor(S.F(f.j.Toolbar_titleTextColor));
        }
        if (S.R(f.j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(S.F(f.j.Toolbar_subtitleTextColor));
        }
        if (S.R(f.j.Toolbar_menu)) {
            n(S.N(f.j.Toolbar_menu, 0));
        }
        S.V();
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i7 = 0; i7 < menu.size(); i7++) {
            arrayList.add(menu.getItem(i7));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new j.l(getContext());
    }

    public static t3 h() {
        t3 t3Var = new t3(-2, -2);
        t3Var.f1417b = 0;
        t3Var.f670a = 8388627;
        return t3Var;
    }

    public static t3 i(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof t3) {
            t3 t3Var = (t3) layoutParams;
            t3 t3Var2 = new t3(t3Var);
            t3Var2.f1417b = 0;
            t3Var2.f1417b = t3Var.f1417b;
            return t3Var2;
        }
        if (layoutParams instanceof androidx.appcompat.app.a) {
            t3 t3Var3 = new t3((androidx.appcompat.app.a) layoutParams);
            t3Var3.f1417b = 0;
            return t3Var3;
        }
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            t3 t3Var4 = new t3(layoutParams);
            t3Var4.f1417b = 0;
            return t3Var4;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        t3 t3Var5 = new t3(marginLayoutParams);
        t3Var5.f1417b = 0;
        ((ViewGroup.MarginLayoutParams) t3Var5).leftMargin = marginLayoutParams.leftMargin;
        ((ViewGroup.MarginLayoutParams) t3Var5).topMargin = marginLayoutParams.topMargin;
        ((ViewGroup.MarginLayoutParams) t3Var5).rightMargin = marginLayoutParams.rightMargin;
        ((ViewGroup.MarginLayoutParams) t3Var5).bottomMargin = marginLayoutParams.bottomMargin;
        return t3Var5;
    }

    public static int l(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return p0.o.b(marginLayoutParams) + p0.o.c(marginLayoutParams);
    }

    public static int m(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final void a(int i7, ArrayList arrayList) {
        int i8 = p0.d1.OVER_SCROLL_ALWAYS;
        boolean z6 = p0.m0.d(this) == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, p0.m0.d(this));
        arrayList.clear();
        if (!z6) {
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                t3 t3Var = (t3) childAt.getLayoutParams();
                if (t3Var.f1417b == 0 && v(childAt) && j(t3Var.f670a) == absoluteGravity) {
                    arrayList.add(childAt);
                }
            }
            return;
        }
        for (int i10 = childCount - 1; i10 >= 0; i10--) {
            View childAt2 = getChildAt(i10);
            t3 t3Var2 = (t3) childAt2.getLayoutParams();
            if (t3Var2.f1417b == 0 && v(childAt2) && j(t3Var2.f670a) == absoluteGravity) {
                arrayList.add(childAt2);
            }
        }
    }

    public final void b(View view, boolean z6) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        t3 h7 = layoutParams == null ? h() : !checkLayoutParams(layoutParams) ? i(layoutParams) : (t3) layoutParams;
        h7.f1417b = 1;
        if (!z6 || this.f1160i == null) {
            addView(view, h7);
        } else {
            view.setLayoutParams(h7);
            this.E.add(view);
        }
    }

    public final void c() {
        if (this.f1159h == null) {
            AppCompatImageButton appCompatImageButton = new AppCompatImageButton(getContext(), null, f.a.toolbarNavigationButtonStyle);
            this.f1159h = appCompatImageButton;
            appCompatImageButton.setImageDrawable(this.f1157f);
            this.f1159h.setContentDescription(this.f1158g);
            t3 h7 = h();
            h7.f670a = (this.f1165n & z1.c.INVOKE_DIRECT) | 8388611;
            h7.f1417b = 2;
            this.f1159h.setLayoutParams(h7);
            this.f1159h.setOnClickListener(new androidx.appcompat.app.c(3, this));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof t3);
    }

    public final void d() {
        if (this.f1171t == null) {
            n2 n2Var = new n2();
            n2Var.f1353a = 0;
            n2Var.f1354b = 0;
            n2Var.f1355c = Integer.MIN_VALUE;
            n2Var.f1356d = Integer.MIN_VALUE;
            n2Var.f1357e = 0;
            n2Var.f1358f = 0;
            n2Var.f1359g = false;
            n2Var.f1360h = false;
            this.f1171t = n2Var;
        }
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.f1152a;
        if (actionMenuView.f981p == null) {
            k.o oVar = (k.o) actionMenuView.getMenu();
            if (this.M == null) {
                this.M = new s3(this);
            }
            this.f1152a.setExpandedActionViewsExclusive(true);
            oVar.b(this.M, this.f1161j);
            x();
        }
    }

    public final void f() {
        if (this.f1152a == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f1152a = actionMenuView;
            actionMenuView.setPopupTheme(this.f1162k);
            this.f1152a.setOnMenuItemClickListener(this.J);
            this.f1152a.setMenuCallbacks(this.N, new q3(this));
            t3 h7 = h();
            h7.f670a = (this.f1165n & z1.c.INVOKE_DIRECT) | 8388613;
            this.f1152a.setLayoutParams(h7);
            b(this.f1152a, false);
        }
    }

    public final void g() {
        if (this.f1155d == null) {
            this.f1155d = new AppCompatImageButton(getContext(), null, f.a.toolbarNavigationButtonStyle);
            t3 h7 = h();
            h7.f670a = (this.f1165n & z1.c.INVOKE_DIRECT) | 8388611;
            this.f1155d.setLayoutParams(h7);
        }
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return h();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        t3 t3Var = new t3(context, attributeSet);
        t3Var.f670a = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.ActionBarLayout);
        t3Var.f670a = obtainStyledAttributes.getInt(f.j.ActionBarLayout_android_layout_gravity, 0);
        obtainStyledAttributes.recycle();
        t3Var.f1417b = 0;
        return t3Var;
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return i(layoutParams);
    }

    public CharSequence getCollapseContentDescription() {
        AppCompatImageButton appCompatImageButton = this.f1159h;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        AppCompatImageButton appCompatImageButton = this.f1159h;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        n2 n2Var = this.f1171t;
        if (n2Var != null) {
            return n2Var.f1359g ? n2Var.f1353a : n2Var.f1354b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i7 = this.f1173v;
        return i7 != Integer.MIN_VALUE ? i7 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        n2 n2Var = this.f1171t;
        if (n2Var != null) {
            return n2Var.f1353a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        n2 n2Var = this.f1171t;
        if (n2Var != null) {
            return n2Var.f1354b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        n2 n2Var = this.f1171t;
        if (n2Var != null) {
            return n2Var.f1359g ? n2Var.f1354b : n2Var.f1353a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i7 = this.f1172u;
        return i7 != Integer.MIN_VALUE ? i7 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        k.o oVar;
        ActionMenuView actionMenuView = this.f1152a;
        return (actionMenuView == null || (oVar = actionMenuView.f981p) == null || !oVar.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.f1173v, 0));
    }

    public int getCurrentContentInsetLeft() {
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        return p0.m0.d(this) == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        return p0.m0.d(this) == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f1172u, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        AppCompatImageView appCompatImageView = this.f1156e;
        if (appCompatImageView != null) {
            return appCompatImageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        AppCompatImageView appCompatImageView = this.f1156e;
        if (appCompatImageView != null) {
            return appCompatImageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.f1152a.getMenu();
    }

    public View getNavButtonView() {
        return this.f1155d;
    }

    public CharSequence getNavigationContentDescription() {
        AppCompatImageButton appCompatImageButton = this.f1155d;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        AppCompatImageButton appCompatImageButton = this.f1155d;
        if (appCompatImageButton != null) {
            return appCompatImageButton.getDrawable();
        }
        return null;
    }

    public m getOuterActionMenuPresenter() {
        return this.L;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.f1152a.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.f1161j;
    }

    public int getPopupTheme() {
        return this.f1162k;
    }

    public CharSequence getSubtitle() {
        return this.f1176y;
    }

    public final TextView getSubtitleTextView() {
        return this.f1154c;
    }

    public CharSequence getTitle() {
        return this.f1175x;
    }

    public int getTitleMarginBottom() {
        return this.f1170s;
    }

    public int getTitleMarginEnd() {
        return this.f1168q;
    }

    public int getTitleMarginStart() {
        return this.f1167p;
    }

    public int getTitleMarginTop() {
        return this.f1169r;
    }

    public final TextView getTitleTextView() {
        return this.f1153b;
    }

    public j1 getWrapper() {
        if (this.K == null) {
            this.K = new w3(this, true);
        }
        return this.K;
    }

    public final int j(int i7) {
        int i8 = p0.d1.OVER_SCROLL_ALWAYS;
        int d7 = p0.m0.d(this);
        int absoluteGravity = Gravity.getAbsoluteGravity(i7, d7) & 7;
        return (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) ? absoluteGravity : d7 == 1 ? 5 : 3;
    }

    public final int k(int i7, View view) {
        t3 t3Var = (t3) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i8 = i7 > 0 ? (measuredHeight - i7) / 2 : 0;
        int i9 = t3Var.f670a & z1.c.INVOKE_DIRECT;
        if (i9 != 16 && i9 != 48 && i9 != 80) {
            i9 = this.f1174w & z1.c.INVOKE_DIRECT;
        }
        if (i9 == 48) {
            return getPaddingTop() - i8;
        }
        if (i9 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) t3Var).bottomMargin) - i8;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i10 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i11 = ((ViewGroup.MarginLayoutParams) t3Var).topMargin;
        if (i10 < i11) {
            i10 = i11;
        } else {
            int i12 = (((height - paddingBottom) - measuredHeight) - i10) - paddingTop;
            int i13 = ((ViewGroup.MarginLayoutParams) t3Var).bottomMargin;
            if (i12 < i13) {
                i10 = Math.max(0, i10 - (i13 - i12));
            }
        }
        return paddingTop + i10;
    }

    public void n(int i7) {
        getMenuInflater().inflate(i7, getMenu());
    }

    public final void o() {
        Iterator it = this.H.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(((MenuItem) it.next()).getItemId());
        }
        getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        getMenuInflater();
        Iterator it2 = this.G.f8184b.iterator();
        if (it2.hasNext()) {
            a.a.u(it2.next());
            throw null;
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.H = currentMenuItems2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        x();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.T);
        x();
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.C = false;
        }
        if (!this.C) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0293 A[LOOP:0: B:40:0x0291->B:41:0x0293, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02af A[LOOP:1: B:44:0x02ad->B:45:0x02af, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x02ce A[LOOP:2: B:48:0x02cc->B:49:0x02ce, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x031c A[LOOP:3: B:57:0x031a->B:58:0x031c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x021d  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instructions count: 813
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i7, int i8) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean a7 = a4.a(this);
        int i16 = !a7 ? 1 : 0;
        int i17 = 0;
        if (v(this.f1155d)) {
            u(this.f1155d, i7, 0, i8, this.f1166o);
            i9 = l(this.f1155d) + this.f1155d.getMeasuredWidth();
            i10 = Math.max(0, m(this.f1155d) + this.f1155d.getMeasuredHeight());
            i11 = View.combineMeasuredStates(0, this.f1155d.getMeasuredState());
        } else {
            i9 = 0;
            i10 = 0;
            i11 = 0;
        }
        if (v(this.f1159h)) {
            u(this.f1159h, i7, 0, i8, this.f1166o);
            i9 = l(this.f1159h) + this.f1159h.getMeasuredWidth();
            i10 = Math.max(i10, m(this.f1159h) + this.f1159h.getMeasuredHeight());
            i11 = View.combineMeasuredStates(i11, this.f1159h.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int max = Math.max(currentContentInsetStart, i9);
        int max2 = Math.max(0, currentContentInsetStart - i9);
        int[] iArr = this.F;
        iArr[a7 ? 1 : 0] = max2;
        if (v(this.f1152a)) {
            u(this.f1152a, i7, max, i8, this.f1166o);
            i12 = l(this.f1152a) + this.f1152a.getMeasuredWidth();
            i10 = Math.max(i10, m(this.f1152a) + this.f1152a.getMeasuredHeight());
            i11 = View.combineMeasuredStates(i11, this.f1152a.getMeasuredState());
        } else {
            i12 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int max3 = Math.max(currentContentInsetEnd, i12) + max;
        iArr[i16] = Math.max(0, currentContentInsetEnd - i12);
        if (v(this.f1160i)) {
            max3 += t(this.f1160i, i7, max3, i8, 0, iArr);
            i10 = Math.max(i10, m(this.f1160i) + this.f1160i.getMeasuredHeight());
            i11 = View.combineMeasuredStates(i11, this.f1160i.getMeasuredState());
        }
        if (v(this.f1156e)) {
            max3 += t(this.f1156e, i7, max3, i8, 0, iArr);
            i10 = Math.max(i10, m(this.f1156e) + this.f1156e.getMeasuredHeight());
            i11 = View.combineMeasuredStates(i11, this.f1156e.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt = getChildAt(i18);
            if (((t3) childAt.getLayoutParams()).f1417b == 0 && v(childAt)) {
                max3 += t(childAt, i7, max3, i8, 0, iArr);
                i10 = Math.max(i10, m(childAt) + childAt.getMeasuredHeight());
                i11 = View.combineMeasuredStates(i11, childAt.getMeasuredState());
            }
        }
        int i19 = this.f1169r + this.f1170s;
        int i20 = this.f1167p + this.f1168q;
        if (v(this.f1153b)) {
            t(this.f1153b, i7, max3 + i20, i8, i19, iArr);
            int l7 = l(this.f1153b) + this.f1153b.getMeasuredWidth();
            i13 = m(this.f1153b) + this.f1153b.getMeasuredHeight();
            i14 = View.combineMeasuredStates(i11, this.f1153b.getMeasuredState());
            i15 = l7;
        } else {
            i13 = 0;
            i14 = i11;
            i15 = 0;
        }
        if (v(this.f1154c)) {
            i15 = Math.max(i15, t(this.f1154c, i7, max3 + i20, i8, i13 + i19, iArr));
            i13 += m(this.f1154c) + this.f1154c.getMeasuredHeight();
            i14 = View.combineMeasuredStates(i14, this.f1154c.getMeasuredState());
        }
        int max4 = Math.max(i10, i13);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + max4;
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + max3 + i15, getSuggestedMinimumWidth()), i7, (-16777216) & i14);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i8, i14 << 16);
        if (this.P) {
            int childCount2 = getChildCount();
            for (int i21 = 0; i21 < childCount2; i21++) {
                View childAt2 = getChildAt(i21);
                if (!v(childAt2) || childAt2.getMeasuredWidth() <= 0 || childAt2.getMeasuredHeight() <= 0) {
                }
            }
            setMeasuredDimension(resolveSizeAndState, i17);
        }
        i17 = resolveSizeAndState2;
        setMeasuredDimension(resolveSizeAndState, i17);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        ActionMenuView actionMenuView = this.f1152a;
        k.o oVar = actionMenuView != null ? actionMenuView.f981p : null;
        int i7 = savedState.f1178b;
        if (i7 != 0 && this.M != null && oVar != null && (findItem = oVar.findItem(i7)) != null) {
            findItem.expandActionView();
        }
        if (savedState.f1179c) {
            androidx.appcompat.app.s0 s0Var = this.T;
            removeCallbacks(s0Var);
            post(s0Var);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        super.onRtlPropertiesChanged(i7);
        d();
        n2 n2Var = this.f1171t;
        boolean z6 = i7 == 1;
        if (z6 == n2Var.f1359g) {
            return;
        }
        n2Var.f1359g = z6;
        if (!n2Var.f1360h) {
            n2Var.f1353a = n2Var.f1357e;
            n2Var.f1354b = n2Var.f1358f;
            return;
        }
        if (z6) {
            int i8 = n2Var.f1356d;
            if (i8 == Integer.MIN_VALUE) {
                i8 = n2Var.f1357e;
            }
            n2Var.f1353a = i8;
            int i9 = n2Var.f1355c;
            if (i9 == Integer.MIN_VALUE) {
                i9 = n2Var.f1358f;
            }
            n2Var.f1354b = i9;
            return;
        }
        int i10 = n2Var.f1355c;
        if (i10 == Integer.MIN_VALUE) {
            i10 = n2Var.f1357e;
        }
        n2Var.f1353a = i10;
        int i11 = n2Var.f1356d;
        if (i11 == Integer.MIN_VALUE) {
            i11 = n2Var.f1358f;
        }
        n2Var.f1354b = i11;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        k.q qVar;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        s3 s3Var = this.M;
        if (s3Var != null && (qVar = s3Var.f1412b) != null) {
            savedState.f1178b = qVar.f7166a;
        }
        savedState.f1179c = q();
        return savedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.B = false;
        }
        if (!this.B) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.B = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.B = false;
        }
        return true;
    }

    public final boolean p(View view) {
        return view.getParent() == this || this.E.contains(view);
    }

    public final boolean q() {
        m mVar;
        ActionMenuView actionMenuView = this.f1152a;
        return (actionMenuView == null || (mVar = actionMenuView.f985t) == null || !mVar.l()) ? false : true;
    }

    public final int r(View view, int i7, int i8, int[] iArr) {
        t3 t3Var = (t3) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) t3Var).leftMargin - iArr[0];
        int max = Math.max(0, i9) + i7;
        iArr[0] = Math.max(0, -i9);
        int k7 = k(i8, view);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, k7, max + measuredWidth, view.getMeasuredHeight() + k7);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) t3Var).rightMargin + max;
    }

    public final int s(View view, int i7, int i8, int[] iArr) {
        t3 t3Var = (t3) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) t3Var).rightMargin - iArr[1];
        int max = i7 - Math.max(0, i9);
        iArr[1] = Math.max(0, -i9);
        int k7 = k(i8, view);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, k7, max, view.getMeasuredHeight() + k7);
        return max - (measuredWidth + ((ViewGroup.MarginLayoutParams) t3Var).leftMargin);
    }

    public void setBackInvokedCallbackEnabled(boolean z6) {
        if (this.S != z6) {
            this.S = z6;
            x();
        }
    }

    public void setCollapseContentDescription(int i7) {
        setCollapseContentDescription(i7 != 0 ? getContext().getText(i7) : null);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        AppCompatImageButton appCompatImageButton = this.f1159h;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(int i7) {
        setCollapseIcon(l6.f.A(getContext(), i7));
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.f1159h.setImageDrawable(drawable);
        } else {
            AppCompatImageButton appCompatImageButton = this.f1159h;
            if (appCompatImageButton != null) {
                appCompatImageButton.setImageDrawable(this.f1157f);
            }
        }
    }

    public void setCollapsible(boolean z6) {
        this.P = z6;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i7) {
        if (i7 < 0) {
            i7 = Integer.MIN_VALUE;
        }
        if (i7 != this.f1173v) {
            this.f1173v = i7;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i7) {
        if (i7 < 0) {
            i7 = Integer.MIN_VALUE;
        }
        if (i7 != this.f1172u) {
            this.f1172u = i7;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetsAbsolute(int i7, int i8) {
        d();
        n2 n2Var = this.f1171t;
        n2Var.f1360h = false;
        if (i7 != Integer.MIN_VALUE) {
            n2Var.f1357e = i7;
            n2Var.f1353a = i7;
        }
        if (i8 != Integer.MIN_VALUE) {
            n2Var.f1358f = i8;
            n2Var.f1354b = i8;
        }
    }

    public void setContentInsetsRelative(int i7, int i8) {
        d();
        this.f1171t.a(i7, i8);
    }

    public void setLogo(int i7) {
        setLogo(l6.f.A(getContext(), i7));
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f1156e == null) {
                this.f1156e = new AppCompatImageView(getContext());
            }
            if (!p(this.f1156e)) {
                b(this.f1156e, true);
            }
        } else {
            AppCompatImageView appCompatImageView = this.f1156e;
            if (appCompatImageView != null && p(appCompatImageView)) {
                removeView(this.f1156e);
                this.E.remove(this.f1156e);
            }
        }
        AppCompatImageView appCompatImageView2 = this.f1156e;
        if (appCompatImageView2 != null) {
            appCompatImageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(int i7) {
        setLogoDescription(getContext().getText(i7));
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f1156e == null) {
            this.f1156e = new AppCompatImageView(getContext());
        }
        AppCompatImageView appCompatImageView = this.f1156e;
        if (appCompatImageView != null) {
            appCompatImageView.setContentDescription(charSequence);
        }
    }

    public void setMenu(k.o oVar, m mVar) {
        if (oVar == null && this.f1152a == null) {
            return;
        }
        f();
        k.o oVar2 = this.f1152a.f981p;
        if (oVar2 == oVar) {
            return;
        }
        if (oVar2 != null) {
            oVar2.r(this.L);
            oVar2.r(this.M);
        }
        if (this.M == null) {
            this.M = new s3(this);
        }
        mVar.f1340r = true;
        if (oVar != null) {
            oVar.b(mVar, this.f1161j);
            oVar.b(this.M, this.f1161j);
        } else {
            mVar.f(this.f1161j, null);
            this.M.f(this.f1161j, null);
            mVar.m(true);
            this.M.m(true);
        }
        this.f1152a.setPopupTheme(this.f1162k);
        this.f1152a.setPresenter(mVar);
        this.L = mVar;
        x();
    }

    public void setMenuCallbacks(k.b0 b0Var, k.m mVar) {
        this.N = b0Var;
        this.O = mVar;
        ActionMenuView actionMenuView = this.f1152a;
        if (actionMenuView != null) {
            actionMenuView.setMenuCallbacks(b0Var, mVar);
        }
    }

    public void setNavigationContentDescription(int i7) {
        setNavigationContentDescription(i7 != 0 ? getContext().getText(i7) : null);
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        AppCompatImageButton appCompatImageButton = this.f1155d;
        if (appCompatImageButton != null) {
            appCompatImageButton.setContentDescription(charSequence);
            x3.a(this.f1155d, charSequence);
        }
    }

    public void setNavigationIcon(int i7) {
        setNavigationIcon(l6.f.A(getContext(), i7));
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!p(this.f1155d)) {
                b(this.f1155d, true);
            }
        } else {
            AppCompatImageButton appCompatImageButton = this.f1155d;
            if (appCompatImageButton != null && p(appCompatImageButton)) {
                removeView(this.f1155d);
                this.E.remove(this.f1155d);
            }
        }
        AppCompatImageButton appCompatImageButton2 = this.f1155d;
        if (appCompatImageButton2 != null) {
            appCompatImageButton2.setImageDrawable(drawable);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.f1155d.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(u3 u3Var) {
        this.I = u3Var;
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.f1152a.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i7) {
        if (this.f1162k != i7) {
            this.f1162k = i7;
            if (i7 == 0) {
                this.f1161j = getContext();
            } else {
                this.f1161j = new ContextThemeWrapper(getContext(), i7);
            }
        }
    }

    public void setSubtitle(int i7) {
        setSubtitle(getContext().getText(i7));
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.f1154c;
            if (appCompatTextView != null && p(appCompatTextView)) {
                removeView(this.f1154c);
                this.E.remove(this.f1154c);
            }
        } else {
            if (this.f1154c == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.f1154c = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.f1154c.setEllipsize(TextUtils.TruncateAt.END);
                int i7 = this.f1164m;
                if (i7 != 0) {
                    this.f1154c.setTextAppearance(context, i7);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f1154c.setTextColor(colorStateList);
                }
            }
            if (!p(this.f1154c)) {
                b(this.f1154c, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.f1154c;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.f1176y = charSequence;
    }

    public void setSubtitleTextAppearance(Context context, int i7) {
        this.f1164m = i7;
        AppCompatTextView appCompatTextView = this.f1154c;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(context, i7);
        }
    }

    public void setSubtitleTextColor(int i7) {
        setSubtitleTextColor(ColorStateList.valueOf(i7));
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        AppCompatTextView appCompatTextView = this.f1154c;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public void setTitle(int i7) {
        setTitle(getContext().getText(i7));
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            AppCompatTextView appCompatTextView = this.f1153b;
            if (appCompatTextView != null && p(appCompatTextView)) {
                removeView(this.f1153b);
                this.E.remove(this.f1153b);
            }
        } else {
            if (this.f1153b == null) {
                Context context = getContext();
                AppCompatTextView appCompatTextView2 = new AppCompatTextView(context);
                this.f1153b = appCompatTextView2;
                appCompatTextView2.setSingleLine();
                this.f1153b.setEllipsize(TextUtils.TruncateAt.END);
                int i7 = this.f1163l;
                if (i7 != 0) {
                    this.f1153b.setTextAppearance(context, i7);
                }
                ColorStateList colorStateList = this.f1177z;
                if (colorStateList != null) {
                    this.f1153b.setTextColor(colorStateList);
                }
            }
            if (!p(this.f1153b)) {
                b(this.f1153b, true);
            }
        }
        AppCompatTextView appCompatTextView3 = this.f1153b;
        if (appCompatTextView3 != null) {
            appCompatTextView3.setText(charSequence);
        }
        this.f1175x = charSequence;
    }

    public void setTitleMargin(int i7, int i8, int i9, int i10) {
        this.f1167p = i7;
        this.f1169r = i8;
        this.f1168q = i9;
        this.f1170s = i10;
        requestLayout();
    }

    public void setTitleMarginBottom(int i7) {
        this.f1170s = i7;
        requestLayout();
    }

    public void setTitleMarginEnd(int i7) {
        this.f1168q = i7;
        requestLayout();
    }

    public void setTitleMarginStart(int i7) {
        this.f1167p = i7;
        requestLayout();
    }

    public void setTitleMarginTop(int i7) {
        this.f1169r = i7;
        requestLayout();
    }

    public void setTitleTextAppearance(Context context, int i7) {
        this.f1163l = i7;
        AppCompatTextView appCompatTextView = this.f1153b;
        if (appCompatTextView != null) {
            appCompatTextView.setTextAppearance(context, i7);
        }
    }

    public void setTitleTextColor(int i7) {
        setTitleTextColor(ColorStateList.valueOf(i7));
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.f1177z = colorStateList;
        AppCompatTextView appCompatTextView = this.f1153b;
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(colorStateList);
        }
    }

    public final int t(View view, int i7, int i8, int i9, int i10, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i11 = marginLayoutParams.leftMargin - iArr[0];
        int i12 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i12) + Math.max(0, i11);
        iArr[0] = Math.max(0, -i11);
        iArr[1] = Math.max(0, -i12);
        view.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + max + i8, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i9, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    public final void u(View view, int i7, int i8, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i8, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i9, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i10 >= 0) {
            if (mode != 0) {
                i10 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i10);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i10, v.c.EXACTLY);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final boolean v(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public final boolean w() {
        m mVar;
        ActionMenuView actionMenuView = this.f1152a;
        return (actionMenuView == null || (mVar = actionMenuView.f985t) == null || !mVar.n()) ? false : true;
    }

    public final void x() {
        OnBackInvokedDispatcher onBackInvokedDispatcher;
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher a7 = r3.a(this);
            s3 s3Var = this.M;
            boolean z6 = false;
            int i7 = 1;
            if (((s3Var == null || s3Var.f1412b == null) ? false : true) && a7 != null) {
                int i8 = p0.d1.OVER_SCROLL_ALWAYS;
                if (p0.o0.b(this) && this.S) {
                    z6 = true;
                }
            }
            if (z6 && this.R == null) {
                if (this.Q == null) {
                    this.Q = r3.b(new p3(this, i7));
                }
                r3.c(a7, this.Q);
                this.R = a7;
                return;
            }
            if (z6 || (onBackInvokedDispatcher = this.R) == null) {
                return;
            }
            r3.d(onBackInvokedDispatcher, this.Q);
            this.R = null;
        }
    }
}
