package com.google.android.material.navigation;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.u2;
import androidx.appcompat.widget.x3;
import androidx.fragment.app.o;
import com.yalantis.ucrop.view.CropImageView;
import k.d0;
import k.q;
import p0.d1;
import p0.f0;
import p0.l0;
import p0.t0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class NavigationBarItemView extends FrameLayout implements d0 {
    public static final int[] G = {R.attr.state_checked};
    public static final c H = new c();
    public static final d I = new d();
    public boolean A;
    public int B;
    public int C;
    public boolean D;
    public int E;
    public t2.a F;

    /* renamed from: a, reason: collision with root package name */
    public boolean f3931a;

    /* renamed from: b, reason: collision with root package name */
    public ColorStateList f3932b;

    /* renamed from: c, reason: collision with root package name */
    public Drawable f3933c;

    /* renamed from: d, reason: collision with root package name */
    public int f3934d;

    /* renamed from: e, reason: collision with root package name */
    public int f3935e;

    /* renamed from: f, reason: collision with root package name */
    public int f3936f;

    /* renamed from: g, reason: collision with root package name */
    public float f3937g;

    /* renamed from: h, reason: collision with root package name */
    public float f3938h;

    /* renamed from: i, reason: collision with root package name */
    public float f3939i;

    /* renamed from: j, reason: collision with root package name */
    public int f3940j;

    /* renamed from: k, reason: collision with root package name */
    public boolean f3941k;

    /* renamed from: l, reason: collision with root package name */
    public final FrameLayout f3942l;

    /* renamed from: m, reason: collision with root package name */
    public final View f3943m;

    /* renamed from: n, reason: collision with root package name */
    public final ImageView f3944n;

    /* renamed from: o, reason: collision with root package name */
    public final ViewGroup f3945o;

    /* renamed from: p, reason: collision with root package name */
    public final TextView f3946p;

    /* renamed from: q, reason: collision with root package name */
    public final TextView f3947q;

    /* renamed from: r, reason: collision with root package name */
    public int f3948r;

    /* renamed from: s, reason: collision with root package name */
    public int f3949s;

    /* renamed from: t, reason: collision with root package name */
    public q f3950t;

    /* renamed from: u, reason: collision with root package name */
    public ColorStateList f3951u;

    /* renamed from: v, reason: collision with root package name */
    public Drawable f3952v;

    /* renamed from: w, reason: collision with root package name */
    public Drawable f3953w;

    /* renamed from: x, reason: collision with root package name */
    public ValueAnimator f3954x;

    /* renamed from: y, reason: collision with root package name */
    public c f3955y;

    /* renamed from: z, reason: collision with root package name */
    public float f3956z;

    public NavigationBarItemView(Context context) {
        super(context);
        this.f3931a = false;
        this.f3948r = -1;
        this.f3949s = 0;
        this.f3955y = H;
        this.f3956z = CropImageView.DEFAULT_ASPECT_RATIO;
        this.A = false;
        this.B = 0;
        this.C = 0;
        this.D = false;
        this.E = 0;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.f3942l = (FrameLayout) findViewById(q2.g.navigation_bar_item_icon_container);
        this.f3943m = findViewById(q2.g.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(q2.g.navigation_bar_item_icon_view);
        this.f3944n = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(q2.g.navigation_bar_item_labels_group);
        this.f3945o = viewGroup;
        TextView textView = (TextView) findViewById(q2.g.navigation_bar_item_small_label_view);
        this.f3946p = textView;
        TextView textView2 = (TextView) findViewById(q2.g.navigation_bar_item_large_label_view);
        this.f3947q = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.f3934d = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.f3935e = viewGroup.getPaddingBottom();
        this.f3936f = getResources().getDimensionPixelSize(q2.e.m3_navigation_item_active_indicator_label_padding);
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.s(textView, 2);
        l0.s(textView2, 2);
        setFocusable(true);
        a(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new u2(1, this));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void f(android.widget.TextView r4, int r5) {
        /*
            r4.setTextAppearance(r5)
            android.content.Context r0 = r4.getContext()
            r1 = 0
            if (r5 != 0) goto Lc
        La:
            r5 = r1
            goto L4e
        Lc:
            int[] r2 = q2.m.TextAppearance
            android.content.res.TypedArray r5 = r0.obtainStyledAttributes(r5, r2)
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            int r3 = q2.m.TextAppearance_android_textSize
            boolean r3 = r5.getValue(r3, r2)
            r5.recycle()
            if (r3 != 0) goto L23
            goto La
        L23:
            int r5 = r2.getComplexUnit()
            r3 = 2
            if (r5 != r3) goto L40
            int r5 = r2.data
            float r5 = android.util.TypedValue.complexToFloat(r5)
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.density
            float r5 = r5 * r0
            int r5 = java.lang.Math.round(r5)
            goto L4e
        L40:
            int r5 = r2.data
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r5 = android.util.TypedValue.complexToDimensionPixelSize(r5, r0)
        L4e:
            if (r5 == 0) goto L54
            float r5 = (float) r5
            r4.setTextSize(r1, r5)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.f(android.widget.TextView, int):void");
    }

    public static void g(float f7, float f8, int i7, TextView textView) {
        textView.setScaleX(f7);
        textView.setScaleY(f8);
        textView.setVisibility(i7);
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.f3942l;
        return frameLayout != null ? frameLayout : this.f3944n;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int indexOfChild = viewGroup.indexOfChild(this);
        int i7 = 0;
        for (int i8 = 0; i8 < indexOfChild; i8++) {
            View childAt = viewGroup.getChildAt(i8);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i7++;
            }
        }
        return i7;
    }

    private int getSuggestedIconHeight() {
        return getIconOrContainer().getMeasuredHeight() + ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin;
    }

    private int getSuggestedIconWidth() {
        t2.a aVar = this.F;
        int minimumWidth = aVar == null ? 0 : aVar.getMinimumWidth() - this.F.f9062e.f9072b.f3303w.intValue();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(minimumWidth, layoutParams.rightMargin) + this.f3944n.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.leftMargin);
    }

    public static void h(View view, int i7, int i8) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i7;
        layoutParams.bottomMargin = i7;
        layoutParams.gravity = i8;
        view.setLayoutParams(layoutParams);
    }

    public static void j(int i7, ViewGroup viewGroup) {
        viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), i7);
    }

    public final void a(float f7, float f8) {
        this.f3937g = f7 - f8;
        this.f3938h = (f8 * 1.0f) / f7;
        this.f3939i = (f7 * 1.0f) / f8;
    }

    public final void b() {
        q qVar = this.f3950t;
        if (qVar != null) {
            setChecked(qVar.isChecked());
        }
    }

    @Override // k.d0
    public final void c(q qVar) {
        this.f3950t = qVar;
        setCheckable(qVar.isCheckable());
        setChecked(qVar.isChecked());
        setEnabled(qVar.isEnabled());
        setIcon(qVar.getIcon());
        setTitle(qVar.f7170e);
        setId(qVar.f7166a);
        if (!TextUtils.isEmpty(qVar.f7182q)) {
            setContentDescription(qVar.f7182q);
        }
        x3.a(this, !TextUtils.isEmpty(qVar.f7183r) ? qVar.f7183r : qVar.f7170e);
        setVisibility(qVar.isVisible() ? 0 : 8);
        this.f3931a = true;
    }

    public final void d() {
        Drawable drawable = this.f3933c;
        ColorStateList colorStateList = this.f3932b;
        FrameLayout frameLayout = this.f3942l;
        RippleDrawable rippleDrawable = null;
        boolean z6 = true;
        if (colorStateList != null) {
            Drawable activeIndicatorDrawable = getActiveIndicatorDrawable();
            if (this.A && getActiveIndicatorDrawable() != null && frameLayout != null && activeIndicatorDrawable != null) {
                rippleDrawable = new RippleDrawable(n3.a.c(this.f3932b), null, activeIndicatorDrawable);
                z6 = false;
            } else if (drawable == null) {
                drawable = new RippleDrawable(n3.a.a(this.f3932b), null, null);
            }
        }
        if (frameLayout != null) {
            frameLayout.setPadding(0, 0, 0, 0);
            frameLayout.setForeground(rippleDrawable);
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.q(this, drawable);
        setDefaultFocusHighlightEnabled(z6);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FrameLayout frameLayout = this.f3942l;
        if (frameLayout != null && this.A) {
            frameLayout.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void e(float f7, float f8) {
        View view = this.f3943m;
        if (view != null) {
            c cVar = this.f3955y;
            cVar.getClass();
            view.setScaleX(r2.a.a(0.4f, 1.0f, f7));
            view.setScaleY(cVar.a(f7, f8));
            view.setAlpha(r2.a.b(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, f8 == CropImageView.DEFAULT_ASPECT_RATIO ? 0.8f : 0.0f, f8 == CropImageView.DEFAULT_ASPECT_RATIO ? 1.0f : 0.2f, f7));
        }
        this.f3956z = f7;
    }

    public Drawable getActiveIndicatorDrawable() {
        View view = this.f3943m;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    public t2.a getBadge() {
        return this.F;
    }

    public int getItemBackgroundResId() {
        return q2.f.mtrl_navigation_bar_item_background;
    }

    @Override // k.d0
    public q getItemData() {
        return this.f3950t;
    }

    public int getItemDefaultMarginResId() {
        return q2.e.mtrl_navigation_bar_item_default_margin;
    }

    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.f3948r;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        ViewGroup viewGroup = this.f3945o;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return viewGroup.getMeasuredHeight() + getSuggestedIconHeight() + (viewGroup.getVisibility() == 0 ? this.f3936f : 0) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        ViewGroup viewGroup = this.f3945o;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), viewGroup.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
    }

    public final void i(int i7) {
        View view = this.f3943m;
        if (view == null || i7 <= 0) {
            return;
        }
        int min = Math.min(this.B, i7 - (this.E * 2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height = (this.D && this.f3940j == 2) ? min : this.C;
        layoutParams.width = min;
        view.setLayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 1);
        q qVar = this.f3950t;
        if (qVar != null && qVar.isCheckable() && this.f3950t.isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, G);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        t2.a aVar = this.F;
        if (aVar != null && aVar.isVisible()) {
            q qVar = this.f3950t;
            CharSequence charSequence = qVar.f7170e;
            if (!TextUtils.isEmpty(qVar.f7182q)) {
                charSequence = this.f3950t.f7182q;
            }
            accessibilityNodeInfo.setContentDescription(((Object) charSequence) + ", " + ((Object) this.F.c()));
        }
        accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) o.b(0, 1, getItemVisiblePosition(), 1, false, isSelected()).f2027a);
        if (isSelected()) {
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) q0.g.ACTION_CLICK.f8421a);
        }
        accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(q2.k.item_view_role_description));
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        post(new androidx.viewpager2.widget.q(this, i7));
    }

    public void setActiveIndicatorDrawable(Drawable drawable) {
        View view = this.f3943m;
        if (view == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
        d();
    }

    public void setActiveIndicatorEnabled(boolean z6) {
        this.A = z6;
        d();
        View view = this.f3943m;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i7) {
        this.C = i7;
        i(getWidth());
    }

    public void setActiveIndicatorLabelPadding(int i7) {
        if (this.f3936f != i7) {
            this.f3936f = i7;
            b();
        }
    }

    public void setActiveIndicatorMarginHorizontal(int i7) {
        this.E = i7;
        i(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z6) {
        this.D = z6;
    }

    public void setActiveIndicatorWidth(int i7) {
        this.B = i7;
        i(getWidth());
    }

    public void setBadge(t2.a aVar) {
        t2.a aVar2 = this.F;
        if (aVar2 == aVar) {
            return;
        }
        boolean z6 = aVar2 != null;
        ImageView imageView = this.f3944n;
        if (z6 && imageView != null && aVar2 != null) {
            setClipChildren(true);
            setClipToPadding(true);
            t2.a aVar3 = this.F;
            if (aVar3 != null) {
                if (t2.c.USE_COMPAT_PARENT || aVar3.d() != null) {
                    aVar3.d().setForeground(null);
                } else {
                    imageView.getOverlay().remove(aVar3);
                }
            }
            this.F = null;
        }
        this.F = aVar;
        if (imageView == null || aVar == null) {
            return;
        }
        setClipChildren(false);
        setClipToPadding(false);
        t2.c.a(this.F, imageView, t2.c.USE_COMPAT_PARENT ? (FrameLayout) imageView.getParent() : null);
    }

    public void setCheckable(boolean z6) {
        refreshDrawableState();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setChecked(boolean r13) {
        /*
            Method dump skipped, instructions count: 374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarItemView.setChecked(boolean):void");
    }

    @Override // android.view.View
    public void setEnabled(boolean z6) {
        super.setEnabled(z6);
        this.f3946p.setEnabled(z6);
        this.f3947q.setEnabled(z6);
        this.f3944n.setEnabled(z6);
        if (!z6) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            t0.d(this, null);
        } else {
            PointerIcon b7 = f0.b(getContext(), 1002);
            int i8 = d1.OVER_SCROLL_ALWAYS;
            t0.d(this, b7);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.f3952v) {
            return;
        }
        this.f3952v = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = drawable.mutate();
            this.f3953w = drawable;
            ColorStateList colorStateList = this.f3951u;
            if (colorStateList != null) {
                j0.b.h(drawable, colorStateList);
            }
        }
        this.f3944n.setImageDrawable(drawable);
    }

    public void setIconSize(int i7) {
        ImageView imageView = this.f3944n;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = i7;
        layoutParams.height = i7;
        imageView.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.f3951u = colorStateList;
        if (this.f3950t == null || (drawable = this.f3953w) == null) {
            return;
        }
        j0.b.h(drawable, colorStateList);
        this.f3953w.invalidateSelf();
    }

    public void setItemBackground(int i7) {
        Drawable b7;
        if (i7 == 0) {
            b7 = null;
        } else {
            Context context = getContext();
            Object obj = g0.g.f6423a;
            b7 = g0.c.b(context, i7);
        }
        setItemBackground(b7);
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        this.f3933c = drawable;
        d();
    }

    public void setItemPaddingBottom(int i7) {
        if (this.f3935e != i7) {
            this.f3935e = i7;
            b();
        }
    }

    public void setItemPaddingTop(int i7) {
        if (this.f3934d != i7) {
            this.f3934d = i7;
            b();
        }
    }

    public void setItemPosition(int i7) {
        this.f3948r = i7;
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.f3932b = colorStateList;
        d();
    }

    public void setLabelVisibilityMode(int i7) {
        if (this.f3940j != i7) {
            this.f3940j = i7;
            if (this.D && i7 == 2) {
                this.f3955y = I;
            } else {
                this.f3955y = H;
            }
            i(getWidth());
            b();
        }
    }

    public void setShifting(boolean z6) {
        if (this.f3941k != z6) {
            this.f3941k = z6;
            b();
        }
    }

    public void setShortcut(boolean z6, char c7) {
    }

    public void setTextAppearanceActive(int i7) {
        this.f3949s = i7;
        TextView textView = this.f3947q;
        f(textView, i7);
        a(this.f3946p.getTextSize(), textView.getTextSize());
    }

    public void setTextAppearanceActiveBoldEnabled(boolean z6) {
        setTextAppearanceActive(this.f3949s);
        TextView textView = this.f3947q;
        textView.setTypeface(textView.getTypeface(), z6 ? 1 : 0);
    }

    public void setTextAppearanceInactive(int i7) {
        TextView textView = this.f3946p;
        f(textView, i7);
        a(textView.getTextSize(), this.f3947q.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.f3946p.setTextColor(colorStateList);
            this.f3947q.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.f3946p.setText(charSequence);
        this.f3947q.setText(charSequence);
        q qVar = this.f3950t;
        if (qVar == null || TextUtils.isEmpty(qVar.f7182q)) {
            setContentDescription(charSequence);
        }
        q qVar2 = this.f3950t;
        if (qVar2 != null && !TextUtils.isEmpty(qVar2.f7183r)) {
            charSequence = this.f3950t.f7183r;
        }
        x3.a(this, charSequence);
    }
}
