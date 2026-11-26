package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.internal.e0;
import p0.d1;
import p0.l0;
import p3.p;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final int LABEL_VISIBILITY_AUTO = -1;
    public static final int LABEL_VISIBILITY_LABELED = 1;
    public static final int LABEL_VISIBILITY_SELECTED = 0;
    public static final int LABEL_VISIBILITY_UNLABELED = 2;

    /* renamed from: a, reason: collision with root package name */
    public final e f3985a;

    /* renamed from: b, reason: collision with root package name */
    public final NavigationBarMenuView f3986b;

    /* renamed from: c, reason: collision with root package name */
    public final g f3987c;

    /* renamed from: d, reason: collision with root package name */
    public j.l f3988d;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new j();

        /* renamed from: b, reason: collision with root package name */
        public Bundle f3989b;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3989b = parcel.readBundle(classLoader == null ? getClass().getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeBundle(this.f3989b);
        }
    }

    public NavigationBarView(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(t3.a.a(context, attributeSet, i7, i8), attributeSet, i7);
        g gVar = new g();
        gVar.f4013b = false;
        this.f3987c = gVar;
        Context context2 = getContext();
        androidx.appcompat.app.g e7 = e0.e(context2, attributeSet, q2.m.NavigationBarView, i7, i8, q2.m.NavigationBarView_itemTextAppearanceInactive, q2.m.NavigationBarView_itemTextAppearanceActive);
        e eVar = new e(context2, getClass(), getMaxItemCount());
        this.f3985a = eVar;
        NavigationBarMenuView a7 = a(context2);
        this.f3986b = a7;
        gVar.f4012a = a7;
        gVar.f4014c = 1;
        a7.setPresenter(gVar);
        eVar.b(gVar, eVar.f7139a);
        getContext();
        gVar.f4012a.E = eVar;
        if (e7.R(q2.m.NavigationBarView_itemIconTint)) {
            a7.setIconTintList(e7.F(q2.m.NavigationBarView_itemIconTint));
        } else {
            a7.setIconTintList(a7.b());
        }
        setItemIconSize(e7.H(q2.m.NavigationBarView_itemIconSize, getResources().getDimensionPixelSize(q2.e.mtrl_navigation_bar_item_default_icon_size)));
        if (e7.R(q2.m.NavigationBarView_itemTextAppearanceInactive)) {
            setItemTextAppearanceInactive(e7.N(q2.m.NavigationBarView_itemTextAppearanceInactive, 0));
        }
        if (e7.R(q2.m.NavigationBarView_itemTextAppearanceActive)) {
            setItemTextAppearanceActive(e7.N(q2.m.NavigationBarView_itemTextAppearanceActive, 0));
        }
        setItemTextAppearanceActiveBoldEnabled(e7.E(q2.m.NavigationBarView_itemTextAppearanceActiveBoldEnabled, true));
        if (e7.R(q2.m.NavigationBarView_itemTextColor)) {
            setItemTextColor(e7.F(q2.m.NavigationBarView_itemTextColor));
        }
        Drawable background = getBackground();
        ColorStateList d7 = d3.a.d(background);
        if (background == null || d7 != null) {
            p3.j jVar = new p3.j(p.c(context2, attributeSet, i7, i8).a());
            if (d7 != null) {
                jVar.o(d7);
            }
            jVar.l(context2);
            int i9 = d1.OVER_SCROLL_ALWAYS;
            l0.q(this, jVar);
        }
        if (e7.R(q2.m.NavigationBarView_itemPaddingTop)) {
            setItemPaddingTop(e7.H(q2.m.NavigationBarView_itemPaddingTop, 0));
        }
        if (e7.R(q2.m.NavigationBarView_itemPaddingBottom)) {
            setItemPaddingBottom(e7.H(q2.m.NavigationBarView_itemPaddingBottom, 0));
        }
        if (e7.R(q2.m.NavigationBarView_activeIndicatorLabelPadding)) {
            setActiveIndicatorLabelPadding(e7.H(q2.m.NavigationBarView_activeIndicatorLabelPadding, 0));
        }
        if (e7.R(q2.m.NavigationBarView_elevation)) {
            setElevation(e7.H(q2.m.NavigationBarView_elevation, 0));
        }
        j0.b.h(getBackground().mutate(), l6.f.x(context2, e7, q2.m.NavigationBarView_backgroundTint));
        setLabelVisibilityMode(((TypedArray) e7.f715c).getInteger(q2.m.NavigationBarView_labelVisibilityMode, -1));
        int N = e7.N(q2.m.NavigationBarView_itemBackground, 0);
        if (N != 0) {
            a7.setItemBackgroundRes(N);
        } else {
            setItemRippleColor(l6.f.x(context2, e7, q2.m.NavigationBarView_itemRippleColor));
        }
        int N2 = e7.N(q2.m.NavigationBarView_itemActiveIndicatorStyle, 0);
        if (N2 != 0) {
            setItemActiveIndicatorEnabled(true);
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(N2, q2.m.NavigationBarActiveIndicator);
            setItemActiveIndicatorWidth(obtainStyledAttributes.getDimensionPixelSize(q2.m.NavigationBarActiveIndicator_android_width, 0));
            setItemActiveIndicatorHeight(obtainStyledAttributes.getDimensionPixelSize(q2.m.NavigationBarActiveIndicator_android_height, 0));
            setItemActiveIndicatorMarginHorizontal(obtainStyledAttributes.getDimensionPixelOffset(q2.m.NavigationBarActiveIndicator_marginHorizontal, 0));
            setItemActiveIndicatorColor(l6.f.w(context2, obtainStyledAttributes, q2.m.NavigationBarActiveIndicator_android_color));
            setItemActiveIndicatorShapeAppearance(p.a(context2, obtainStyledAttributes.getResourceId(q2.m.NavigationBarActiveIndicator_shapeAppearance, 0), 0).a());
            obtainStyledAttributes.recycle();
        }
        if (e7.R(q2.m.NavigationBarView_menu)) {
            int N3 = e7.N(q2.m.NavigationBarView_menu, 0);
            gVar.f4013b = true;
            getMenuInflater().inflate(N3, eVar);
            gVar.f4013b = false;
            gVar.m(true);
        }
        e7.V();
        addView(a7);
        eVar.f7143e = new v2.a(8, this);
    }

    private MenuInflater getMenuInflater() {
        if (this.f3988d == null) {
            this.f3988d = new j.l(getContext());
        }
        return this.f3988d;
    }

    public abstract NavigationBarMenuView a(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.f3986b.getActiveIndicatorLabelPadding();
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.f3986b.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorHeight() {
        return this.f3986b.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.f3986b.getItemActiveIndicatorMarginHorizontal();
    }

    public p getItemActiveIndicatorShapeAppearance() {
        return this.f3986b.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.f3986b.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.f3986b.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f3986b.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f3986b.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f3986b.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.f3986b.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.f3986b.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.f3986b.getItemRippleColor();
    }

    public int getItemTextAppearanceActive() {
        return this.f3986b.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f3986b.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f3986b.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f3986b.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.f3985a;
    }

    public k.e0 getMenuView() {
        return this.f3986b;
    }

    public g getPresenter() {
        return this.f3987c;
    }

    public int getSelectedItemId() {
        return this.f3986b.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        l6.f.A0(this);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        this.f3985a.t(savedState.f3989b);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f3989b = bundle;
        this.f3985a.v(bundle);
        return savedState;
    }

    public void setActiveIndicatorLabelPadding(int i7) {
        this.f3986b.setActiveIndicatorLabelPadding(i7);
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        l6.f.x0(this, f7);
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.f3986b.setItemActiveIndicatorColor(colorStateList);
    }

    public void setItemActiveIndicatorEnabled(boolean z6) {
        this.f3986b.setItemActiveIndicatorEnabled(z6);
    }

    public void setItemActiveIndicatorHeight(int i7) {
        this.f3986b.setItemActiveIndicatorHeight(i7);
    }

    public void setItemActiveIndicatorMarginHorizontal(int i7) {
        this.f3986b.setItemActiveIndicatorMarginHorizontal(i7);
    }

    public void setItemActiveIndicatorShapeAppearance(p pVar) {
        this.f3986b.setItemActiveIndicatorShapeAppearance(pVar);
    }

    public void setItemActiveIndicatorWidth(int i7) {
        this.f3986b.setItemActiveIndicatorWidth(i7);
    }

    public void setItemBackground(Drawable drawable) {
        this.f3986b.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i7) {
        this.f3986b.setItemBackgroundRes(i7);
    }

    public void setItemIconSize(int i7) {
        this.f3986b.setItemIconSize(i7);
    }

    public void setItemIconSizeRes(int i7) {
        setItemIconSize(getResources().getDimensionPixelSize(i7));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f3986b.setIconTintList(colorStateList);
    }

    public void setItemOnTouchListener(int i7, View.OnTouchListener onTouchListener) {
        this.f3986b.setItemOnTouchListener(i7, onTouchListener);
    }

    public void setItemPaddingBottom(int i7) {
        this.f3986b.setItemPaddingBottom(i7);
    }

    public void setItemPaddingTop(int i7) {
        this.f3986b.setItemPaddingTop(i7);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.f3986b.setItemRippleColor(colorStateList);
    }

    public void setItemTextAppearanceActive(int i7) {
        this.f3986b.setItemTextAppearanceActive(i7);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z6) {
        this.f3986b.setItemTextAppearanceActiveBoldEnabled(z6);
    }

    public void setItemTextAppearanceInactive(int i7) {
        this.f3986b.setItemTextAppearanceInactive(i7);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f3986b.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i7) {
        NavigationBarMenuView navigationBarMenuView = this.f3986b;
        if (navigationBarMenuView.getLabelVisibilityMode() != i7) {
            navigationBarMenuView.setLabelVisibilityMode(i7);
            this.f3987c.m(false);
        }
    }

    public void setOnItemReselectedListener(h hVar) {
    }

    public void setOnItemSelectedListener(i iVar) {
    }

    public void setSelectedItemId(int i7) {
        e eVar = this.f3985a;
        MenuItem findItem = eVar.findItem(i7);
        if (findItem == null || eVar.q(findItem, this.f3987c, 0)) {
            return;
        }
        findItem.setChecked(true);
    }
}
