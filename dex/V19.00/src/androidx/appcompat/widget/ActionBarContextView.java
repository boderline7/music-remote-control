package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ActionBarContextView extends ViewGroup {

    /* renamed from: a, reason: collision with root package name */
    public final a f934a;

    /* renamed from: b, reason: collision with root package name */
    public final Context f935b;

    /* renamed from: c, reason: collision with root package name */
    public ActionMenuView f936c;

    /* renamed from: d, reason: collision with root package name */
    public m f937d;

    /* renamed from: e, reason: collision with root package name */
    public int f938e;

    /* renamed from: f, reason: collision with root package name */
    public p0.l1 f939f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f940g;

    /* renamed from: h, reason: collision with root package name */
    public boolean f941h;

    /* renamed from: i, reason: collision with root package name */
    public CharSequence f942i;

    /* renamed from: j, reason: collision with root package name */
    public CharSequence f943j;

    /* renamed from: k, reason: collision with root package name */
    public View f944k;

    /* renamed from: l, reason: collision with root package name */
    public View f945l;

    /* renamed from: m, reason: collision with root package name */
    public View f946m;

    /* renamed from: n, reason: collision with root package name */
    public LinearLayout f947n;

    /* renamed from: o, reason: collision with root package name */
    public TextView f948o;

    /* renamed from: p, reason: collision with root package name */
    public TextView f949p;

    /* renamed from: q, reason: collision with root package name */
    public final int f950q;

    /* renamed from: r, reason: collision with root package name */
    public final int f951r;

    /* renamed from: s, reason: collision with root package name */
    public boolean f952s;

    /* renamed from: t, reason: collision with root package name */
    public final int f953t;

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        int resourceId;
        this.f934a = new a(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(f.a.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f935b = context;
        } else {
            this.f935b = new ContextThemeWrapper(context, typedValue.resourceId);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.ActionMode, i7, 0);
        int i8 = f.j.ActionMode_background;
        Drawable drawable = (!obtainStyledAttributes.hasValue(i8) || (resourceId = obtainStyledAttributes.getResourceId(i8, 0)) == 0) ? obtainStyledAttributes.getDrawable(i8) : l6.f.A(context, resourceId);
        int i9 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.l0.q(this, drawable);
        this.f950q = obtainStyledAttributes.getResourceId(f.j.ActionMode_titleTextStyle, 0);
        this.f951r = obtainStyledAttributes.getResourceId(f.j.ActionMode_subtitleTextStyle, 0);
        this.f938e = obtainStyledAttributes.getLayoutDimension(f.j.ActionMode_height, 0);
        this.f953t = obtainStyledAttributes.getResourceId(f.j.ActionMode_closeItemLayout, f.g.abc_action_mode_close_item_material);
        obtainStyledAttributes.recycle();
    }

    public static int f(View view, int i7, int i8) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i7, Integer.MIN_VALUE), i8);
        return Math.max(0, i7 - view.getMeasuredWidth());
    }

    public static int j(int i7, int i8, int i9, View view, boolean z6) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i10 = ((i9 - measuredHeight) / 2) + i8;
        if (z6) {
            view.layout(i7 - measuredWidth, i10, i7, measuredHeight + i10);
        } else {
            view.layout(i7, i10, i7 + measuredWidth, measuredHeight + i10);
        }
        return z6 ? -measuredWidth : measuredWidth;
    }

    public final void c(j.c cVar) {
        View view = this.f944k;
        if (view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.f953t, (ViewGroup) this, false);
            this.f944k = inflate;
            addView(inflate);
        } else if (view.getParent() == null) {
            addView(this.f944k);
        }
        View findViewById = this.f944k.findViewById(f.f.action_mode_close_button);
        this.f945l = findViewById;
        findViewById.setOnClickListener(new c(this, cVar));
        k.o c7 = cVar.c();
        m mVar = this.f937d;
        if (mVar != null) {
            mVar.g();
            h hVar = mVar.f1343u;
            if (hVar != null && hVar.b()) {
                hVar.f7055j.dismiss();
            }
        }
        m mVar2 = new m(getContext());
        this.f937d = mVar2;
        mVar2.f1335m = true;
        mVar2.f1336n = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        c7.b(this.f937d, this.f935b);
        m mVar3 = this.f937d;
        k.e0 e0Var = mVar3.f1330h;
        if (e0Var == null) {
            k.e0 e0Var2 = (k.e0) mVar3.f1326d.inflate(mVar3.f1328f, (ViewGroup) this, false);
            mVar3.f1330h = e0Var2;
            e0Var2.d(mVar3.f1325c);
            mVar3.m(true);
        }
        k.e0 e0Var3 = mVar3.f1330h;
        if (e0Var != e0Var3) {
            ((ActionMenuView) e0Var3).setPresenter(mVar3);
        }
        ActionMenuView actionMenuView = (ActionMenuView) e0Var3;
        this.f936c = actionMenuView;
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.l0.q(actionMenuView, null);
        addView(this.f936c, layoutParams);
    }

    public final void d() {
        if (this.f947n == null) {
            LayoutInflater.from(getContext()).inflate(f.g.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f947n = linearLayout;
            this.f948o = (TextView) linearLayout.findViewById(f.f.action_bar_title);
            this.f949p = (TextView) this.f947n.findViewById(f.f.action_bar_subtitle);
            int i7 = this.f950q;
            if (i7 != 0) {
                this.f948o.setTextAppearance(getContext(), i7);
            }
            int i8 = this.f951r;
            if (i8 != 0) {
                this.f949p.setTextAppearance(getContext(), i8);
            }
        }
        this.f948o.setText(this.f942i);
        this.f949p.setText(this.f943j);
        boolean z6 = !TextUtils.isEmpty(this.f942i);
        boolean z7 = !TextUtils.isEmpty(this.f943j);
        this.f949p.setVisibility(z7 ? 0 : 8);
        this.f947n.setVisibility((z6 || z7) ? 0 : 8);
        if (this.f947n.getParent() == null) {
            addView(this.f947n);
        }
    }

    public final void e() {
        removeAllViews();
        this.f946m = null;
        this.f936c = null;
        this.f937d = null;
        View view = this.f945l;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.View
    /* renamed from: g, reason: merged with bridge method [inline-methods] */
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, f.j.ActionBar, f.a.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(f.j.ActionBar_height, 0));
        obtainStyledAttributes.recycle();
        m mVar = this.f937d;
        if (mVar != null) {
            mVar.f1339q = j.a.b(mVar.f1324b).c();
            k.o oVar = mVar.f1325c;
            if (oVar != null) {
                oVar.p(true);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getAnimatedVisibility() {
        return this.f939f != null ? this.f934a.f1185b : getVisibility();
    }

    public int getContentHeight() {
        return this.f938e;
    }

    public CharSequence getSubtitle() {
        return this.f943j;
    }

    public CharSequence getTitle() {
        return this.f942i;
    }

    @Override // android.view.View
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f941h = false;
        }
        if (!this.f941h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.f941h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f941h = false;
        }
        return true;
    }

    @Override // android.view.View
    /* renamed from: i, reason: merged with bridge method [inline-methods] */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f940g = false;
        }
        if (!this.f940g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f940g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f940g = false;
        }
        return true;
    }

    @Override // android.view.View
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i7) {
        if (i7 != getVisibility()) {
            p0.l1 l1Var = this.f939f;
            if (l1Var != null) {
                l1Var.b();
            }
            super.setVisibility(i7);
        }
    }

    public final p0.l1 l(int i7, long j7) {
        p0.l1 l1Var = this.f939f;
        if (l1Var != null) {
            l1Var.b();
        }
        a aVar = this.f934a;
        if (i7 != 0) {
            p0.l1 a7 = p0.d1.a(this);
            a7.a(CropImageView.DEFAULT_ASPECT_RATIO);
            a7.c(j7);
            aVar.f1186c.f939f = a7;
            aVar.f1185b = i7;
            a7.d(aVar);
            return a7;
        }
        if (getVisibility() != 0) {
            setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        }
        p0.l1 a8 = p0.d1.a(this);
        a8.a(1.0f);
        a8.c(j7);
        aVar.f1186c.f939f = a8;
        aVar.f1185b = i7;
        a8.d(aVar);
        return a8;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m mVar = this.f937d;
        if (mVar != null) {
            mVar.g();
            h hVar = this.f937d.f1343u;
            if (hVar == null || !hVar.b()) {
                return;
            }
            hVar.f7055j.dismiss();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        boolean a7 = a4.a(this);
        int paddingRight = a7 ? (i9 - i7) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i10 - i8) - getPaddingTop()) - getPaddingBottom();
        View view = this.f944k;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f944k.getLayoutParams();
            int i11 = a7 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i12 = a7 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i13 = a7 ? paddingRight - i11 : paddingRight + i11;
            int j7 = j(i13, paddingTop, paddingTop2, this.f944k, a7) + i13;
            paddingRight = a7 ? j7 - i12 : j7 + i12;
        }
        LinearLayout linearLayout = this.f947n;
        if (linearLayout != null && this.f946m == null && linearLayout.getVisibility() != 8) {
            paddingRight += j(paddingRight, paddingTop, paddingTop2, this.f947n, a7);
        }
        View view2 = this.f946m;
        if (view2 != null) {
            j(paddingRight, paddingTop, paddingTop2, view2, a7);
        }
        int paddingLeft = a7 ? getPaddingLeft() : (i9 - i7) - getPaddingRight();
        ActionMenuView actionMenuView = this.f936c;
        if (actionMenuView != null) {
            j(paddingLeft, paddingTop, paddingTop2, actionMenuView, !a7);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int i9 = v.c.EXACTLY;
        if (mode != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i8) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i7);
        int i10 = this.f938e;
        if (i10 <= 0) {
            i10 = View.MeasureSpec.getSize(i8);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int i11 = i10 - paddingBottom;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i11, Integer.MIN_VALUE);
        View view = this.f944k;
        if (view != null) {
            int f7 = f(view, paddingLeft, makeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f944k.getLayoutParams();
            paddingLeft = f7 - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f936c;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = f(this.f936c, paddingLeft, makeMeasureSpec);
        }
        LinearLayout linearLayout = this.f947n;
        if (linearLayout != null && this.f946m == null) {
            if (this.f952s) {
                this.f947n.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                int measuredWidth = this.f947n.getMeasuredWidth();
                boolean z6 = measuredWidth <= paddingLeft;
                if (z6) {
                    paddingLeft -= measuredWidth;
                }
                this.f947n.setVisibility(z6 ? 0 : 8);
            } else {
                paddingLeft = f(linearLayout, paddingLeft, makeMeasureSpec);
            }
        }
        View view2 = this.f946m;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i12 = layoutParams.width;
            int i13 = i12 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i12 >= 0) {
                paddingLeft = Math.min(i12, paddingLeft);
            }
            int i14 = layoutParams.height;
            if (i14 == -2) {
                i9 = Integer.MIN_VALUE;
            }
            if (i14 >= 0) {
                i11 = Math.min(i14, i11);
            }
            this.f946m.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i13), View.MeasureSpec.makeMeasureSpec(i11, i9));
        }
        if (this.f938e > 0) {
            setMeasuredDimension(size, i10);
            return;
        }
        int childCount = getChildCount();
        int i15 = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            int measuredHeight = getChildAt(i16).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i15) {
                i15 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i15);
    }

    public void setContentHeight(int i7) {
        this.f938e = i7;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f946m;
        if (view2 != null) {
            removeView(view2);
        }
        this.f946m = view;
        if (view != null && (linearLayout = this.f947n) != null) {
            removeView(linearLayout);
            this.f947n = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f943j = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.f942i = charSequence;
        d();
        p0.d1.p(this, charSequence);
    }

    public void setTitleOptional(boolean z6) {
        if (z6 != this.f952s) {
            requestLayout();
        }
        this.f952s = z6;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
