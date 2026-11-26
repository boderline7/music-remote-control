package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.SpinnerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {

    /* renamed from: a, reason: collision with root package name */
    public android.support.v4.media.i f1093a;

    /* renamed from: b, reason: collision with root package name */
    public final LinearLayoutCompat f1094b;

    /* renamed from: c, reason: collision with root package name */
    public AppCompatSpinner f1095c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f1096d;

    /* renamed from: e, reason: collision with root package name */
    public int f1097e;

    /* renamed from: f, reason: collision with root package name */
    public int f1098f;

    /* renamed from: g, reason: collision with root package name */
    public int f1099g;

    /* renamed from: h, reason: collision with root package name */
    public int f1100h;

    static {
        new DecelerateInterpolator();
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        new q2(this);
        setHorizontalScrollBarEnabled(false);
        j.a b7 = j.a.b(context);
        setContentHeight(b7.d());
        this.f1098f = b7.f6874a.getResources().getDimensionPixelSize(f.d.abc_action_bar_stacked_tab_max_width);
        LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext(), null, f.a.actionBarTabBarStyle);
        linearLayoutCompat.setMeasureWithLargestChildEnabled(true);
        linearLayoutCompat.setGravity(17);
        linearLayoutCompat.setLayoutParams(new w1(-2, -1));
        this.f1094b = linearLayoutCompat;
        addView(linearLayoutCompat, new ViewGroup.LayoutParams(-2, -1));
    }

    public final void a() {
        AppCompatSpinner appCompatSpinner = this.f1095c;
        if (appCompatSpinner != null && appCompatSpinner.getParent() == this) {
            removeView(this.f1095c);
            addView(this.f1094b, new ViewGroup.LayoutParams(-2, -1));
            setTabSelected(this.f1095c.getSelectedItemPosition());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        android.support.v4.media.i iVar = this.f1093a;
        if (iVar != null) {
            post(iVar);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        j.a b7 = j.a.b(getContext());
        setContentHeight(b7.d());
        this.f1098f = b7.f6874a.getResources().getDimensionPixelSize(f.d.abc_action_bar_stacked_tab_max_width);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        android.support.v4.media.i iVar = this.f1093a;
        if (iVar != null) {
            removeCallbacks(iVar);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i7, long j7) {
        ((p2) view).getClass();
        throw null;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        boolean z6 = mode == 1073741824;
        setFillViewport(z6);
        LinearLayoutCompat linearLayoutCompat = this.f1094b;
        int childCount = linearLayoutCompat.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f1097e = -1;
        } else {
            if (childCount > 2) {
                this.f1097e = (int) (View.MeasureSpec.getSize(i7) * 0.4f);
            } else {
                this.f1097e = View.MeasureSpec.getSize(i7) / 2;
            }
            this.f1097e = Math.min(this.f1097e, this.f1098f);
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f1099g, v.c.EXACTLY);
        if (z6 || !this.f1096d) {
            a();
        } else {
            linearLayoutCompat.measure(0, makeMeasureSpec);
            if (linearLayoutCompat.getMeasuredWidth() > View.MeasureSpec.getSize(i7)) {
                AppCompatSpinner appCompatSpinner = this.f1095c;
                if (appCompatSpinner == null || appCompatSpinner.getParent() != this) {
                    if (this.f1095c == null) {
                        AppCompatSpinner appCompatSpinner2 = new AppCompatSpinner(getContext(), null, f.a.actionDropDownStyle);
                        appCompatSpinner2.setLayoutParams(new w1(-2, -1));
                        appCompatSpinner2.setOnItemSelectedListener(this);
                        this.f1095c = appCompatSpinner2;
                    }
                    removeView(linearLayoutCompat);
                    addView(this.f1095c, new ViewGroup.LayoutParams(-2, -1));
                    if (this.f1095c.getAdapter() == null) {
                        this.f1095c.setAdapter((SpinnerAdapter) new o2(this));
                    }
                    Runnable runnable = this.f1093a;
                    if (runnable != null) {
                        removeCallbacks(runnable);
                        this.f1093a = null;
                    }
                    this.f1095c.setSelection(this.f1100h);
                }
            } else {
                a();
            }
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i7, makeMeasureSpec);
        int measuredWidth2 = getMeasuredWidth();
        if (!z6 || measuredWidth == measuredWidth2) {
            return;
        }
        setTabSelected(this.f1100h);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }

    public void setAllowCollapse(boolean z6) {
        this.f1096d = z6;
    }

    public void setContentHeight(int i7) {
        this.f1099g = i7;
        requestLayout();
    }

    public void setTabSelected(int i7) {
        this.f1100h = i7;
        LinearLayoutCompat linearLayoutCompat = this.f1094b;
        int childCount = linearLayoutCompat.getChildCount();
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = linearLayoutCompat.getChildAt(i8);
            boolean z6 = i8 == i7;
            childAt.setSelected(z6);
            if (z6) {
                View childAt2 = linearLayoutCompat.getChildAt(i7);
                Runnable runnable = this.f1093a;
                if (runnable != null) {
                    removeCallbacks(runnable);
                }
                android.support.v4.media.i iVar = new android.support.v4.media.i(2, this, childAt2);
                this.f1093a = iVar;
                post(iVar);
            }
            i8++;
        }
        AppCompatSpinner appCompatSpinner = this.f1095c;
        if (appCompatSpinner == null || i7 < 0) {
            return;
        }
        appCompatSpinner.setSelection(i7);
    }
}
