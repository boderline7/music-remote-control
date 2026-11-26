package com.google.android.material.navigation;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import com.google.android.material.internal.c0;
import com.yalantis.ucrop.view.CropImageView;
import java.util.HashSet;
import k.e0;
import k.o;
import k.q;
import p0.d1;
import p0.l0;
import p3.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements e0 {
    public static final int[] F = {R.attr.state_checked};
    public static final int[] G = {-16842910};
    public p A;
    public boolean B;
    public ColorStateList C;
    public g D;
    public o E;

    /* renamed from: a, reason: collision with root package name */
    public final n1.a f3957a;

    /* renamed from: b, reason: collision with root package name */
    public final androidx.appcompat.app.c f3958b;

    /* renamed from: c, reason: collision with root package name */
    public final o0.d f3959c;

    /* renamed from: d, reason: collision with root package name */
    public final SparseArray f3960d;

    /* renamed from: e, reason: collision with root package name */
    public int f3961e;

    /* renamed from: f, reason: collision with root package name */
    public NavigationBarItemView[] f3962f;

    /* renamed from: g, reason: collision with root package name */
    public int f3963g;

    /* renamed from: h, reason: collision with root package name */
    public int f3964h;

    /* renamed from: i, reason: collision with root package name */
    public ColorStateList f3965i;

    /* renamed from: j, reason: collision with root package name */
    public int f3966j;

    /* renamed from: k, reason: collision with root package name */
    public ColorStateList f3967k;

    /* renamed from: l, reason: collision with root package name */
    public final ColorStateList f3968l;

    /* renamed from: m, reason: collision with root package name */
    public int f3969m;

    /* renamed from: n, reason: collision with root package name */
    public int f3970n;

    /* renamed from: o, reason: collision with root package name */
    public boolean f3971o;

    /* renamed from: p, reason: collision with root package name */
    public Drawable f3972p;

    /* renamed from: q, reason: collision with root package name */
    public ColorStateList f3973q;

    /* renamed from: r, reason: collision with root package name */
    public int f3974r;

    /* renamed from: s, reason: collision with root package name */
    public final SparseArray f3975s;

    /* renamed from: t, reason: collision with root package name */
    public int f3976t;

    /* renamed from: u, reason: collision with root package name */
    public int f3977u;

    /* renamed from: v, reason: collision with root package name */
    public int f3978v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f3979w;

    /* renamed from: x, reason: collision with root package name */
    public int f3980x;

    /* renamed from: y, reason: collision with root package name */
    public int f3981y;

    /* renamed from: z, reason: collision with root package name */
    public int f3982z;

    public NavigationBarMenuView(Context context) {
        super(context);
        this.f3959c = new o0.d(5);
        this.f3960d = new SparseArray(5);
        this.f3963g = 0;
        this.f3964h = 0;
        this.f3975s = new SparseArray(5);
        this.f3976t = -1;
        this.f3977u = -1;
        this.f3978v = -1;
        this.B = false;
        this.f3968l = b();
        if (isInEditMode()) {
            this.f3957a = null;
        } else {
            n1.a aVar = new n1.a();
            this.f3957a = aVar;
            aVar.K(0);
            aVar.z(l6.f.u0(getContext(), q2.c.motionDurationMedium4, getResources().getInteger(q2.h.material_motion_duration_long_1)));
            aVar.B(l6.f.v0(getContext(), q2.c.motionEasingStandard, r2.a.FAST_OUT_SLOW_IN_INTERPOLATOR));
            aVar.H(new c0());
        }
        this.f3958b = new androidx.appcompat.app.c(6, this);
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.s(this, 1);
    }

    public static boolean f(int i7, int i8) {
        if (i7 == -1) {
            if (i8 <= 3) {
                return false;
            }
        } else if (i7 != 0) {
            return false;
        }
        return true;
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.f3959c.a();
        return navigationBarItemView == null ? e(getContext()) : navigationBarItemView;
    }

    private void setBadgeIfNeeded(NavigationBarItemView navigationBarItemView) {
        t2.a aVar;
        int id = navigationBarItemView.getId();
        if (id == -1 || (aVar = (t2.a) this.f3975s.get(id)) == null) {
            return;
        }
        navigationBarItemView.setBadge(aVar);
    }

    public final void a() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.f3959c.b(navigationBarItemView);
                    if (navigationBarItemView.F != null) {
                        ImageView imageView = navigationBarItemView.f3944n;
                        if (imageView != null) {
                            navigationBarItemView.setClipChildren(true);
                            navigationBarItemView.setClipToPadding(true);
                            t2.a aVar = navigationBarItemView.F;
                            if (aVar != null) {
                                if (t2.c.USE_COMPAT_PARENT || aVar.d() != null) {
                                    aVar.d().setForeground(null);
                                } else {
                                    imageView.getOverlay().remove(aVar);
                                }
                            }
                        }
                        navigationBarItemView.F = null;
                    }
                    navigationBarItemView.f3950t = null;
                    navigationBarItemView.f3956z = CropImageView.DEFAULT_ASPECT_RATIO;
                    navigationBarItemView.f3931a = false;
                }
            }
        }
        if (this.E.f7144f.size() == 0) {
            this.f3963g = 0;
            this.f3964h = 0;
            this.f3962f = null;
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i7 = 0; i7 < this.E.f7144f.size(); i7++) {
            hashSet.add(Integer.valueOf(this.E.getItem(i7).getItemId()));
        }
        int i8 = 0;
        while (true) {
            SparseArray sparseArray = this.f3975s;
            if (i8 >= sparseArray.size()) {
                break;
            }
            int keyAt = sparseArray.keyAt(i8);
            if (!hashSet.contains(Integer.valueOf(keyAt))) {
                sparseArray.delete(keyAt);
            }
            i8++;
        }
        this.f3962f = new NavigationBarItemView[this.E.f7144f.size()];
        boolean f7 = f(this.f3961e, this.E.l().size());
        for (int i9 = 0; i9 < this.E.f7144f.size(); i9++) {
            this.D.f4013b = true;
            this.E.getItem(i9).setCheckable(true);
            this.D.f4013b = false;
            NavigationBarItemView newItem = getNewItem();
            this.f3962f[i9] = newItem;
            newItem.setIconTintList(this.f3965i);
            newItem.setIconSize(this.f3966j);
            newItem.setTextColor(this.f3968l);
            newItem.setTextAppearanceInactive(this.f3969m);
            newItem.setTextAppearanceActive(this.f3970n);
            newItem.setTextAppearanceActiveBoldEnabled(this.f3971o);
            newItem.setTextColor(this.f3967k);
            int i10 = this.f3976t;
            if (i10 != -1) {
                newItem.setItemPaddingTop(i10);
            }
            int i11 = this.f3977u;
            if (i11 != -1) {
                newItem.setItemPaddingBottom(i11);
            }
            int i12 = this.f3978v;
            if (i12 != -1) {
                newItem.setActiveIndicatorLabelPadding(i12);
            }
            newItem.setActiveIndicatorWidth(this.f3980x);
            newItem.setActiveIndicatorHeight(this.f3981y);
            newItem.setActiveIndicatorMarginHorizontal(this.f3982z);
            newItem.setActiveIndicatorDrawable(c());
            newItem.setActiveIndicatorResizeable(this.B);
            newItem.setActiveIndicatorEnabled(this.f3979w);
            Drawable drawable = this.f3972p;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.f3974r);
            }
            newItem.setItemRippleColor(this.f3973q);
            newItem.setShifting(f7);
            newItem.setLabelVisibilityMode(this.f3961e);
            q qVar = (q) this.E.getItem(i9);
            newItem.c(qVar);
            newItem.setItemPosition(i9);
            SparseArray sparseArray2 = this.f3960d;
            int i13 = qVar.f7166a;
            newItem.setOnTouchListener((View.OnTouchListener) sparseArray2.get(i13));
            newItem.setOnClickListener(this.f3958b);
            int i14 = this.f3963g;
            if (i14 != 0 && i13 == i14) {
                this.f3964h = i9;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int min = Math.min(this.E.f7144f.size() - 1, this.f3964h);
        this.f3964h = min;
        this.E.getItem(min).setChecked(true);
    }

    public final ColorStateList b() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R.attr.textColorSecondary, typedValue, true)) {
            return null;
        }
        ColorStateList b7 = g0.g.b(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(f.a.colorPrimary, typedValue, true)) {
            return null;
        }
        int i7 = typedValue.data;
        int defaultColor = b7.getDefaultColor();
        int[] iArr = G;
        return new ColorStateList(new int[][]{iArr, F, ViewGroup.EMPTY_STATE_SET}, new int[]{b7.getColorForState(iArr, defaultColor), i7, defaultColor});
    }

    public final p3.j c() {
        if (this.A == null || this.C == null) {
            return null;
        }
        p3.j jVar = new p3.j(this.A);
        jVar.o(this.C);
        return jVar;
    }

    @Override // k.e0
    public final void d(o oVar) {
        this.E = oVar;
    }

    public abstract NavigationBarItemView e(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.f3978v;
    }

    public SparseArray<t2.a> getBadgeDrawables() {
        return this.f3975s;
    }

    public ColorStateList getIconTintList() {
        return this.f3965i;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.C;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.f3979w;
    }

    public int getItemActiveIndicatorHeight() {
        return this.f3981y;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.f3982z;
    }

    public p getItemActiveIndicatorShapeAppearance() {
        return this.A;
    }

    public int getItemActiveIndicatorWidth() {
        return this.f3980x;
    }

    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        return (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) ? this.f3972p : navigationBarItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.f3974r;
    }

    public int getItemIconSize() {
        return this.f3966j;
    }

    public int getItemPaddingBottom() {
        return this.f3977u;
    }

    public int getItemPaddingTop() {
        return this.f3976t;
    }

    public ColorStateList getItemRippleColor() {
        return this.f3973q;
    }

    public int getItemTextAppearanceActive() {
        return this.f3970n;
    }

    public int getItemTextAppearanceInactive() {
        return this.f3969m;
    }

    public ColorStateList getItemTextColor() {
        return this.f3967k;
    }

    public int getLabelVisibilityMode() {
        return this.f3961e;
    }

    public o getMenu() {
        return this.E;
    }

    public int getSelectedItemId() {
        return this.f3963g;
    }

    public int getSelectedItemPosition() {
        return this.f3964h;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) q0.h.a(1, this.E.l().size(), 1).f8425a);
    }

    public void setActiveIndicatorLabelPadding(int i7) {
        this.f3978v = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorLabelPadding(i7);
            }
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.f3965i = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.C = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(c());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z6) {
        this.f3979w = z6;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorEnabled(z6);
            }
        }
    }

    public void setItemActiveIndicatorHeight(int i7) {
        this.f3981y = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorHeight(i7);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(int i7) {
        this.f3982z = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorMarginHorizontal(i7);
            }
        }
    }

    public void setItemActiveIndicatorResizeable(boolean z6) {
        this.B = z6;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorResizeable(z6);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(p pVar) {
        this.A = pVar;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(c());
            }
        }
    }

    public void setItemActiveIndicatorWidth(int i7) {
        this.f3980x = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorWidth(i7);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.f3972p = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i7) {
        this.f3974r = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(i7);
            }
        }
    }

    public void setItemIconSize(int i7) {
        this.f3966j = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(i7);
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setItemOnTouchListener(int i7, View.OnTouchListener onTouchListener) {
        SparseArray sparseArray = this.f3960d;
        if (onTouchListener == null) {
            sparseArray.remove(i7);
        } else {
            sparseArray.put(i7, onTouchListener);
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView.getItemData().f7166a == i7) {
                    navigationBarItemView.setOnTouchListener(onTouchListener);
                }
            }
        }
    }

    public void setItemPaddingBottom(int i7) {
        this.f3977u = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingBottom(i7);
            }
        }
    }

    public void setItemPaddingTop(int i7) {
        this.f3976t = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingTop(i7);
            }
        }
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.f3973q = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemRippleColor(colorStateList);
            }
        }
    }

    public void setItemTextAppearanceActive(int i7) {
        this.f3970n = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActive(i7);
                ColorStateList colorStateList = this.f3967k;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z6) {
        this.f3971o = z6;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActiveBoldEnabled(z6);
            }
        }
    }

    public void setItemTextAppearanceInactive(int i7) {
        this.f3969m = i7;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceInactive(i7);
                ColorStateList colorStateList = this.f3967k;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f3967k = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.f3962f;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i7) {
        this.f3961e = i7;
    }

    public void setPresenter(g gVar) {
        this.D = gVar;
    }
}
