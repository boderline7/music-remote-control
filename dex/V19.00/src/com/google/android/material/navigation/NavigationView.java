package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.v;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import k.q;
import n1.o;
import p0.d1;
import p0.l2;
import p0.m0;
import p3.b0;
import p3.n;
import p3.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class NavigationView extends ScrimInsetsFrameLayout implements j3.b {

    /* renamed from: v, reason: collision with root package name */
    public static final int[] f3990v = {R.attr.state_checked};

    /* renamed from: w, reason: collision with root package name */
    public static final int[] f3991w = {-16842910};

    /* renamed from: x, reason: collision with root package name */
    public static final int f3992x = q2.l.Widget_Design_NavigationView;

    /* renamed from: h, reason: collision with root package name */
    public final com.google.android.material.internal.k f3993h;

    /* renamed from: i, reason: collision with root package name */
    public final v f3994i;

    /* renamed from: j, reason: collision with root package name */
    public l f3995j;

    /* renamed from: k, reason: collision with root package name */
    public final int f3996k;

    /* renamed from: l, reason: collision with root package name */
    public final int[] f3997l;

    /* renamed from: m, reason: collision with root package name */
    public j.l f3998m;

    /* renamed from: n, reason: collision with root package name */
    public final k.e f3999n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f4000o;

    /* renamed from: p, reason: collision with root package name */
    public boolean f4001p;

    /* renamed from: q, reason: collision with root package name */
    public final int f4002q;

    /* renamed from: r, reason: collision with root package name */
    public final b0 f4003r;

    /* renamed from: s, reason: collision with root package name */
    public final j3.i f4004s;

    /* renamed from: t, reason: collision with root package name */
    public final j3.f f4005t;

    /* renamed from: u, reason: collision with root package name */
    public final k f4006u;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new m();

        /* renamed from: b, reason: collision with root package name */
        public Bundle f4007b;

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeBundle(this.f4007b);
        }
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0211  */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NavigationView(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instructions count: 766
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private MenuInflater getMenuInflater() {
        if (this.f3998m == null) {
            this.f3998m = new j.l(getContext());
        }
        return this.f3998m;
    }

    @Override // j3.b
    public final void a() {
        Pair i7 = i();
        DrawerLayout drawerLayout = (DrawerLayout) i7.first;
        j3.i iVar = this.f4004s;
        androidx.activity.c cVar = iVar.f6958f;
        iVar.f6958f = null;
        if (cVar == null || Build.VERSION.SDK_INT < 34) {
            drawerLayout.c(this, true);
            return;
        }
        int i8 = ((w0.d) i7.second).f9405a;
        int i9 = a.f4008a;
        iVar.b(cVar, i8, new o(drawerLayout, this), new x2.b(2, drawerLayout));
    }

    @Override // j3.b
    public final void b(androidx.activity.c cVar) {
        i();
        this.f4004s.f6958f = cVar;
    }

    @Override // j3.b
    public final void c(androidx.activity.c cVar) {
        int i7 = ((w0.d) i().second).f9405a;
        j3.i iVar = this.f4004s;
        androidx.activity.c cVar2 = iVar.f6958f;
        iVar.f6958f = cVar;
        if (cVar2 == null) {
            return;
        }
        iVar.c(i7, cVar.f610c, cVar.f611d == 0);
    }

    @Override // j3.b
    public final void d() {
        i();
        this.f4004s.a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        b0 b0Var = this.f4003r;
        if (b0Var.b()) {
            Path path = b0Var.f8231e;
            if (!path.isEmpty()) {
                canvas.save();
                canvas.clipPath(path);
                super.dispatchDraw(canvas);
                canvas.restore();
                return;
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public final void e(l2 l2Var) {
        v vVar = this.f3994i;
        vVar.getClass();
        int d7 = l2Var.d();
        if (vVar.f3906z != d7) {
            vVar.f3906z = d7;
            int i7 = (vVar.f3882b.getChildCount() <= 0 && vVar.f3904x) ? vVar.f3906z : 0;
            NavigationMenuView navigationMenuView = vVar.f3881a;
            navigationMenuView.setPadding(0, i7, 0, navigationMenuView.getPaddingBottom());
        }
        NavigationMenuView navigationMenuView2 = vVar.f3881a;
        navigationMenuView2.setPadding(0, navigationMenuView2.getPaddingTop(), 0, l2Var.a());
        d1.b(vVar.f3882b, l2Var);
    }

    public final ColorStateList g(int i7) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i7, typedValue, true)) {
            return null;
        }
        ColorStateList b7 = g0.g.b(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(f.a.colorPrimary, typedValue, true)) {
            return null;
        }
        int i8 = typedValue.data;
        int defaultColor = b7.getDefaultColor();
        int[] iArr = f3991w;
        return new ColorStateList(new int[][]{iArr, f3990v, FrameLayout.EMPTY_STATE_SET}, new int[]{b7.getColorForState(iArr, defaultColor), i8, defaultColor});
    }

    public j3.i getBackHelper() {
        return this.f4004s;
    }

    public MenuItem getCheckedItem() {
        return this.f3994i.f3885e.f3873b;
    }

    public int getDividerInsetEnd() {
        return this.f3994i.f3900t;
    }

    public int getDividerInsetStart() {
        return this.f3994i.f3899s;
    }

    public int getHeaderCount() {
        return this.f3994i.f3882b.getChildCount();
    }

    public Drawable getItemBackground() {
        return this.f3994i.f3893m;
    }

    public int getItemHorizontalPadding() {
        return this.f3994i.f3895o;
    }

    public int getItemIconPadding() {
        return this.f3994i.f3897q;
    }

    public ColorStateList getItemIconTintList() {
        return this.f3994i.f3892l;
    }

    public int getItemMaxLines() {
        return this.f3994i.f3905y;
    }

    public ColorStateList getItemTextColor() {
        return this.f3994i.f3891k;
    }

    public int getItemVerticalPadding() {
        return this.f3994i.f3896p;
    }

    public Menu getMenu() {
        return this.f3993h;
    }

    public int getSubheaderInsetEnd() {
        return this.f3994i.f3902v;
    }

    public int getSubheaderInsetStart() {
        return this.f3994i.f3901u;
    }

    public final InsetDrawable h(androidx.appcompat.app.g gVar, ColorStateList colorStateList) {
        p3.j jVar = new p3.j(p.a(getContext(), gVar.N(q2.m.NavigationView_itemShapeAppearance, 0), gVar.N(q2.m.NavigationView_itemShapeAppearanceOverlay, 0)).a());
        jVar.o(colorStateList);
        return new InsetDrawable((Drawable) jVar, gVar.H(q2.m.NavigationView_itemShapeInsetStart, 0), gVar.H(q2.m.NavigationView_itemShapeInsetTop, 0), gVar.H(q2.m.NavigationView_itemShapeInsetEnd, 0), gVar.H(q2.m.NavigationView_itemShapeInsetBottom, 0));
    }

    public final Pair i() {
        ViewParent parent = getParent();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if ((parent instanceof DrawerLayout) && (layoutParams instanceof w0.d)) {
            return new Pair((DrawerLayout) parent, (w0.d) layoutParams);
        }
        throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        j3.c cVar;
        super.onAttachedToWindow();
        l6.f.A0(this);
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            j3.f fVar = this.f4005t;
            if (fVar.f6962a != null) {
                DrawerLayout drawerLayout = (DrawerLayout) parent;
                k kVar = this.f4006u;
                if (kVar == null) {
                    drawerLayout.getClass();
                } else {
                    ArrayList arrayList = drawerLayout.f1850t;
                    if (arrayList != null) {
                        arrayList.remove(kVar);
                    }
                }
                drawerLayout.a(kVar);
                if (!DrawerLayout.o(this) || (cVar = fVar.f6962a) == null) {
                    return;
                }
                cVar.b(fVar.f6963b, fVar.f6964c, true);
            }
        }
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.f3999n);
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            DrawerLayout drawerLayout = (DrawerLayout) parent;
            k kVar = this.f4006u;
            if (kVar == null) {
                drawerLayout.getClass();
                return;
            }
            ArrayList arrayList = drawerLayout.f1850t;
            if (arrayList == null) {
                return;
            }
            arrayList.remove(kVar);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int i9 = this.f3996k;
        if (mode == Integer.MIN_VALUE) {
            i7 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i7), i9), v.c.EXACTLY);
        } else if (mode == 0) {
            i7 = View.MeasureSpec.makeMeasureSpec(i9, v.c.EXACTLY);
        }
        super.onMeasure(i7, i8);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        this.f3993h.t(savedState.f4007b);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f4007b = bundle;
        this.f3993h.v(bundle);
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        int i11;
        super.onSizeChanged(i7, i8, i9, i10);
        if ((getParent() instanceof DrawerLayout) && (getLayoutParams() instanceof w0.d) && (i11 = this.f4002q) > 0 && (getBackground() instanceof p3.j)) {
            int i12 = ((w0.d) getLayoutParams()).f9405a;
            int i13 = d1.OVER_SCROLL_ALWAYS;
            boolean z6 = Gravity.getAbsoluteGravity(i12, m0.d(this)) == 3;
            p3.j jVar = (p3.j) getBackground();
            n g7 = jVar.f8263a.f8241a.g();
            g7.c(i11);
            if (z6) {
                g7.f8293e = new p3.a(CropImageView.DEFAULT_ASPECT_RATIO);
                g7.f8296h = new p3.a(CropImageView.DEFAULT_ASPECT_RATIO);
            } else {
                g7.f8294f = new p3.a(CropImageView.DEFAULT_ASPECT_RATIO);
                g7.f8295g = new p3.a(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            p a7 = g7.a();
            jVar.setShapeAppearanceModel(a7);
            b0 b0Var = this.f4003r;
            b0Var.f8229c = a7;
            b0Var.c();
            b0Var.a(this);
            b0Var.f8230d = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, i7, i8);
            b0Var.c();
            b0Var.a(this);
            b0Var.f8228b = true;
            b0Var.a(this);
        }
    }

    public void setBottomInsetScrimEnabled(boolean z6) {
        this.f4001p = z6;
    }

    public void setCheckedItem(int i7) {
        MenuItem findItem = this.f3993h.findItem(i7);
        if (findItem != null) {
            this.f3994i.f3885e.b((q) findItem);
        }
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem findItem = this.f3993h.findItem(menuItem.getItemId());
        if (findItem == null) {
            throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
        }
        this.f3994i.f3885e.b((q) findItem);
    }

    public void setDividerInsetEnd(int i7) {
        v vVar = this.f3994i;
        vVar.f3900t = i7;
        vVar.m(false);
    }

    public void setDividerInsetStart(int i7) {
        v vVar = this.f3994i;
        vVar.f3899s = i7;
        vVar.m(false);
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        l6.f.x0(this, f7);
    }

    public void setForceCompatClippingEnabled(boolean z6) {
        b0 b0Var = this.f4003r;
        if (z6 != b0Var.f8227a) {
            b0Var.f8227a = z6;
            b0Var.a(this);
        }
    }

    public void setItemBackground(Drawable drawable) {
        v vVar = this.f3994i;
        vVar.f3893m = drawable;
        vVar.m(false);
    }

    public void setItemBackgroundResource(int i7) {
        Context context = getContext();
        Object obj = g0.g.f6423a;
        setItemBackground(g0.c.b(context, i7));
    }

    public void setItemHorizontalPadding(int i7) {
        v vVar = this.f3994i;
        vVar.f3895o = i7;
        vVar.m(false);
    }

    public void setItemHorizontalPaddingResource(int i7) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i7);
        v vVar = this.f3994i;
        vVar.f3895o = dimensionPixelSize;
        vVar.m(false);
    }

    public void setItemIconPadding(int i7) {
        v vVar = this.f3994i;
        vVar.f3897q = i7;
        vVar.m(false);
    }

    public void setItemIconPaddingResource(int i7) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i7);
        v vVar = this.f3994i;
        vVar.f3897q = dimensionPixelSize;
        vVar.m(false);
    }

    public void setItemIconSize(int i7) {
        v vVar = this.f3994i;
        if (vVar.f3898r != i7) {
            vVar.f3898r = i7;
            vVar.f3903w = true;
            vVar.m(false);
        }
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        v vVar = this.f3994i;
        vVar.f3892l = colorStateList;
        vVar.m(false);
    }

    public void setItemMaxLines(int i7) {
        v vVar = this.f3994i;
        vVar.f3905y = i7;
        vVar.m(false);
    }

    public void setItemTextAppearance(int i7) {
        v vVar = this.f3994i;
        vVar.f3889i = i7;
        vVar.m(false);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z6) {
        v vVar = this.f3994i;
        vVar.f3890j = z6;
        vVar.m(false);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        v vVar = this.f3994i;
        vVar.f3891k = colorStateList;
        vVar.m(false);
    }

    public void setItemVerticalPadding(int i7) {
        v vVar = this.f3994i;
        vVar.f3896p = i7;
        vVar.m(false);
    }

    public void setItemVerticalPaddingResource(int i7) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i7);
        v vVar = this.f3994i;
        vVar.f3896p = dimensionPixelSize;
        vVar.m(false);
    }

    public void setNavigationItemSelectedListener(l lVar) {
        this.f3995j = lVar;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i7) {
        super.setOverScrollMode(i7);
        v vVar = this.f3994i;
        if (vVar != null) {
            vVar.B = i7;
            NavigationMenuView navigationMenuView = vVar.f3881a;
            if (navigationMenuView != null) {
                navigationMenuView.setOverScrollMode(i7);
            }
        }
    }

    public void setSubheaderInsetEnd(int i7) {
        v vVar = this.f3994i;
        vVar.f3902v = i7;
        vVar.m(false);
    }

    public void setSubheaderInsetStart(int i7) {
        v vVar = this.f3994i;
        vVar.f3901u = i7;
        vVar.m(false);
    }

    public void setTopInsetScrimEnabled(boolean z6) {
        this.f4000o = z6;
    }
}
