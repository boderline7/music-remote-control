package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    public boolean f924a;

    /* renamed from: b, reason: collision with root package name */
    public ScrollingTabContainerView f925b;

    /* renamed from: c, reason: collision with root package name */
    public View f926c;

    /* renamed from: d, reason: collision with root package name */
    public View f927d;

    /* renamed from: e, reason: collision with root package name */
    public Drawable f928e;

    /* renamed from: f, reason: collision with root package name */
    public Drawable f929f;

    /* renamed from: g, reason: collision with root package name */
    public Drawable f930g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f931h;

    /* renamed from: i, reason: collision with root package name */
    public boolean f932i;

    /* renamed from: j, reason: collision with root package name */
    public final int f933j;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b bVar = new b(this);
        int i7 = p0.d1.OVER_SCROLL_ALWAYS;
        p0.l0.q(this, bVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.j.ActionBar);
        this.f928e = obtainStyledAttributes.getDrawable(f.j.ActionBar_background);
        this.f929f = obtainStyledAttributes.getDrawable(f.j.ActionBar_backgroundStacked);
        this.f933j = obtainStyledAttributes.getDimensionPixelSize(f.j.ActionBar_height, -1);
        boolean z6 = true;
        if (getId() == f.f.split_action_bar) {
            this.f931h = true;
            this.f930g = obtainStyledAttributes.getDrawable(f.j.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        if (!this.f931h ? this.f928e != null || this.f929f != null : this.f930g != null) {
            z6 = false;
        }
        setWillNotDraw(z6);
    }

    public static int a(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f928e;
        if (drawable != null && drawable.isStateful()) {
            this.f928e.setState(getDrawableState());
        }
        Drawable drawable2 = this.f929f;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f929f.setState(getDrawableState());
        }
        Drawable drawable3 = this.f930g;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f930g.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.f925b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f928e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f929f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f930g;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f926c = findViewById(f.f.action_bar);
        this.f927d = findViewById(f.f.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f924a || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        Drawable drawable;
        super.onLayout(z6, i7, i8, i9, i10);
        ScrollingTabContainerView scrollingTabContainerView = this.f925b;
        boolean z7 = true;
        boolean z8 = (scrollingTabContainerView == null || scrollingTabContainerView.getVisibility() == 8) ? false : true;
        if (scrollingTabContainerView != null && scrollingTabContainerView.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) scrollingTabContainerView.getLayoutParams();
            int measuredHeight2 = measuredHeight - scrollingTabContainerView.getMeasuredHeight();
            int i11 = layoutParams.bottomMargin;
            scrollingTabContainerView.layout(i7, measuredHeight2 - i11, i9, measuredHeight - i11);
        }
        if (this.f931h) {
            Drawable drawable2 = this.f930g;
            if (drawable2 == null) {
                return;
            } else {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        } else {
            if (this.f928e == null) {
                z7 = false;
            } else if (this.f926c.getVisibility() == 0) {
                this.f928e.setBounds(this.f926c.getLeft(), this.f926c.getTop(), this.f926c.getRight(), this.f926c.getBottom());
            } else {
                View view = this.f927d;
                if (view == null || view.getVisibility() != 0) {
                    this.f928e.setBounds(0, 0, 0, 0);
                } else {
                    this.f928e.setBounds(this.f927d.getLeft(), this.f927d.getTop(), this.f927d.getRight(), this.f927d.getBottom());
                }
            }
            this.f932i = z8;
            if (z8 && (drawable = this.f929f) != null) {
                drawable.setBounds(scrollingTabContainerView.getLeft(), scrollingTabContainerView.getTop(), scrollingTabContainerView.getRight(), scrollingTabContainerView.getBottom());
            } else if (!z7) {
                return;
            }
        }
        invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        int a7;
        int i9;
        if (this.f926c == null && View.MeasureSpec.getMode(i8) == Integer.MIN_VALUE && (i9 = this.f933j) >= 0) {
            i8 = View.MeasureSpec.makeMeasureSpec(Math.min(i9, View.MeasureSpec.getSize(i8)), Integer.MIN_VALUE);
        }
        super.onMeasure(i7, i8);
        if (this.f926c == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i8);
        ScrollingTabContainerView scrollingTabContainerView = this.f925b;
        if (scrollingTabContainerView == null || scrollingTabContainerView.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        View view = this.f926c;
        if (view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0) {
            View view2 = this.f927d;
            a7 = (view2 == null || view2.getVisibility() == 8 || view2.getMeasuredHeight() == 0) ? 0 : a(this.f927d);
        } else {
            a7 = a(this.f926c);
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min(a(this.f925b) + a7, mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i8) : u.MAX_ACTIVITY_COUNT_UNLIMITED));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f928e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f928e);
        }
        this.f928e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f926c;
            if (view != null) {
                this.f928e.setBounds(view.getLeft(), this.f926c.getTop(), this.f926c.getRight(), this.f926c.getBottom());
            }
        }
        boolean z6 = false;
        if (!this.f931h ? !(this.f928e != null || this.f929f != null) : this.f930g == null) {
            z6 = true;
        }
        setWillNotDraw(z6);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f930g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f930g);
        }
        this.f930g = drawable;
        boolean z6 = this.f931h;
        boolean z7 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z6 && (drawable2 = this.f930g) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!z6 ? !(this.f928e != null || this.f929f != null) : this.f930g == null) {
            z7 = true;
        }
        setWillNotDraw(z7);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f929f;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f929f);
        }
        this.f929f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f932i && (drawable2 = this.f929f) != null) {
                drawable2.setBounds(this.f925b.getLeft(), this.f925b.getTop(), this.f925b.getRight(), this.f925b.getBottom());
            }
        }
        boolean z6 = false;
        if (!this.f931h ? !(this.f928e != null || this.f929f != null) : this.f930g == null) {
            z6 = true;
        }
        setWillNotDraw(z6);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        ScrollingTabContainerView scrollingTabContainerView2 = this.f925b;
        if (scrollingTabContainerView2 != null) {
            removeView(scrollingTabContainerView2);
        }
        this.f925b = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z6) {
        this.f924a = z6;
        setDescendantFocusability(z6 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        super.setVisibility(i7);
        boolean z6 = i7 == 0;
        Drawable drawable = this.f928e;
        if (drawable != null) {
            drawable.setVisible(z6, false);
        }
        Drawable drawable2 = this.f929f;
        if (drawable2 != null) {
            drawable2.setVisible(z6, false);
        }
        Drawable drawable3 = this.f930g;
        if (drawable3 != null) {
            drawable3.setVisible(z6, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i7) {
        if (i7 != 0) {
            return super.startActionModeForChild(view, callback, i7);
        }
        return null;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.f928e;
        boolean z6 = this.f931h;
        return (drawable == drawable2 && !z6) || (drawable == this.f929f && this.f932i) || ((drawable == this.f930g && z6) || super.verifyDrawable(drawable));
    }
}
