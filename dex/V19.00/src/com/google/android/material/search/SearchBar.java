package com.google.android.material.search;

import a6.k0;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.f0;
import com.yalantis.ucrop.view.CropImageView;
import k.o;
import p0.d1;
import p0.m0;
import p0.r0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SearchBar extends Toolbar {

    /* renamed from: m0, reason: collision with root package name */
    public static final int f4039m0 = q2.l.Widget_Material3_SearchBar;
    public final TextView U;
    public final boolean V;
    public final boolean W;

    /* renamed from: a0, reason: collision with root package name */
    public final k0 f4040a0;

    /* renamed from: b0, reason: collision with root package name */
    public final Drawable f4041b0;

    /* renamed from: c0, reason: collision with root package name */
    public final boolean f4042c0;

    /* renamed from: d0, reason: collision with root package name */
    public final boolean f4043d0;

    /* renamed from: e0, reason: collision with root package name */
    public View f4044e0;

    /* renamed from: f0, reason: collision with root package name */
    public final Integer f4045f0;

    /* renamed from: g0, reason: collision with root package name */
    public Drawable f4046g0;

    /* renamed from: h0, reason: collision with root package name */
    public int f4047h0;

    /* renamed from: i0, reason: collision with root package name */
    public boolean f4048i0;

    /* renamed from: j0, reason: collision with root package name */
    public final p3.j f4049j0;

    /* renamed from: k0, reason: collision with root package name */
    public final AccessibilityManager f4050k0;

    /* renamed from: l0, reason: collision with root package name */
    public final e0.f f4051l0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        public String f4052b;

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i7) {
            parcel.writeParcelable(this.f1829a, i7);
            parcel.writeString(this.f4052b);
        }
    }

    public static class ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {

        /* renamed from: g, reason: collision with root package name */
        public boolean f4053g;

        public ScrollingViewBehavior() {
            this.f4053g = false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f4053g = false;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, c0.b
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            super.h(coordinatorLayout, view, view2);
            if (!this.f4053g && (view2 instanceof AppBarLayout)) {
                this.f4053g = true;
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                appBarLayout.setBackgroundColor(0);
                appBarLayout.setTargetElevation(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            return false;
        }
    }

    public SearchBar(Context context) {
        this(context, null);
    }

    public SearchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.materialSearchBarStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SearchBar(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.search.SearchBar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private void setNavigationIconDecorative(boolean z6) {
        ImageButton b7 = f0.b(this);
        if (b7 == null) {
            return;
        }
        b7.setClickable(!z6);
        b7.setFocusable(!z6);
        Drawable background = b7.getBackground();
        if (background != null) {
            this.f4046g0 = background;
        }
        b7.setBackgroundDrawable(z6 ? null : this.f4046g0);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (this.V && this.f4044e0 == null && !(view instanceof ActionMenuView)) {
            this.f4044e0 = view;
            view.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        }
        super.addView(view, i7, layoutParams);
    }

    public View getCenterView() {
        return this.f4044e0;
    }

    public float getCompatElevation() {
        p3.j jVar = this.f4049j0;
        if (jVar != null) {
            return jVar.f8263a.f8254n;
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        return r0.i(this);
    }

    public float getCornerSize() {
        return this.f4049j0.j();
    }

    public int getDefaultMarginVerticalResource() {
        return q2.e.m3_searchbar_margin_vertical;
    }

    public int getDefaultNavigationIconResource() {
        return q2.f.ic_search_black_24;
    }

    public CharSequence getHint() {
        return this.U.getHint();
    }

    public int getMenuResId() {
        return this.f4047h0;
    }

    public int getStrokeColor() {
        return this.f4049j0.f8263a.f8244d.getDefaultColor();
    }

    public float getStrokeWidth() {
        return this.f4049j0.f8263a.f8251k;
    }

    public CharSequence getText() {
        return this.U.getText();
    }

    public TextView getTextView() {
        return this.U;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public final void n(int i7) {
        Menu menu = getMenu();
        boolean z6 = menu instanceof o;
        if (z6) {
            ((o) menu).y();
        }
        super.n(i7);
        this.f4047h0 = i7;
        if (z6) {
            ((o) menu).x();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        l6.f.B0(this, this.f4049j0);
        if (this.W && (getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(q2.e.m3_searchbar_margin_horizontal);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(getDefaultMarginVerticalResource());
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            int i7 = marginLayoutParams.leftMargin;
            if (i7 == 0) {
                i7 = dimensionPixelSize;
            }
            marginLayoutParams.leftMargin = i7;
            int i8 = marginLayoutParams.topMargin;
            if (i8 == 0) {
                i8 = dimensionPixelSize2;
            }
            marginLayoutParams.topMargin = i8;
            int i9 = marginLayoutParams.rightMargin;
            if (i9 != 0) {
                dimensionPixelSize = i9;
            }
            marginLayoutParams.rightMargin = dimensionPixelSize;
            int i10 = marginLayoutParams.bottomMargin;
            if (i10 != 0) {
                dimensionPixelSize2 = i10;
            }
            marginLayoutParams.bottomMargin = dimensionPixelSize2;
        }
        y();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(EditText.class.getCanonicalName());
        accessibilityNodeInfo.setEditable(isEnabled());
        CharSequence text = getText();
        boolean isEmpty = TextUtils.isEmpty(text);
        accessibilityNodeInfo.setHintText(getHint());
        accessibilityNodeInfo.setShowingHintText(isEmpty);
        if (isEmpty) {
            text = getHint();
        }
        accessibilityNodeInfo.setText(text);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        View view = this.f4044e0;
        if (view == null) {
            return;
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredWidth2 = (getMeasuredWidth() / 2) - (measuredWidth / 2);
        int i11 = measuredWidth + measuredWidth2;
        int measuredHeight = this.f4044e0.getMeasuredHeight();
        int measuredHeight2 = (getMeasuredHeight() / 2) - (measuredHeight / 2);
        int i12 = measuredHeight + measuredHeight2;
        View view2 = this.f4044e0;
        int i13 = d1.OVER_SCROLL_ALWAYS;
        if (m0.d(this) == 1) {
            view2.layout(getMeasuredWidth() - i11, measuredHeight2, getMeasuredWidth() - measuredWidth2, i12);
        } else {
            view2.layout(measuredWidth2, measuredHeight2, i11, i12);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        View view = this.f4044e0;
        if (view != null) {
            view.measure(i7, i8);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f1829a);
        setText(savedState.f4052b);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        CharSequence text = getText();
        savedState.f4052b = text == null ? null : text.toString();
        return savedState;
    }

    public void setCenterView(View view) {
        View view2 = this.f4044e0;
        if (view2 != null) {
            removeView(view2);
            this.f4044e0 = null;
        }
        if (view != null) {
            addView(view);
        }
    }

    public void setDefaultScrollFlagsEnabled(boolean z6) {
        this.f4048i0 = z6;
        y();
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        p3.j jVar = this.f4049j0;
        if (jVar != null) {
            jVar.n(f7);
        }
    }

    public void setHint(int i7) {
        this.U.setHint(i7);
    }

    public void setHint(CharSequence charSequence) {
        this.U.setHint(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        int b7;
        if (this.f4042c0 && drawable != null) {
            Integer num = this.f4045f0;
            if (num != null) {
                b7 = num.intValue();
            } else {
                b7 = b3.a.b(drawable == this.f4041b0 ? q2.c.colorOnSurfaceVariant : q2.c.colorOnSurface, this);
            }
            drawable = drawable.mutate();
            j0.b.g(drawable, b7);
        }
        super.setNavigationIcon(drawable);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        if (this.f4043d0) {
            return;
        }
        super.setNavigationOnClickListener(onClickListener);
        setNavigationIconDecorative(onClickListener == null);
    }

    public void setOnLoadAnimationFadeInEnabled(boolean z6) {
        this.f4040a0.getClass();
    }

    public void setStrokeColor(int i7) {
        if (getStrokeColor() != i7) {
            this.f4049j0.t(ColorStateList.valueOf(i7));
        }
    }

    public void setStrokeWidth(float f7) {
        if (getStrokeWidth() != f7) {
            this.f4049j0.u(f7);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    public void setText(int i7) {
        this.U.setText(i7);
    }

    public void setText(CharSequence charSequence) {
        this.U.setText(charSequence);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public final void y() {
        if (getLayoutParams() instanceof s2.c) {
            s2.c cVar = (s2.c) getLayoutParams();
            if (this.f4048i0) {
                if (cVar.f8667a == 0) {
                    cVar.f8667a = 53;
                }
            } else if (cVar.f8667a == 53) {
                cVar.f8667a = 0;
            }
        }
    }
}
