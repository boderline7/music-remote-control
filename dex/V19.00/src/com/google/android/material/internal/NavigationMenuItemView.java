package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.widget.w1;
import androidx.appcompat.widget.x3;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements k.d0 {
    public static final int[] G = {R.attr.state_checked};
    public FrameLayout A;
    public k.q B;
    public ColorStateList C;
    public boolean D;
    public Drawable E;
    public final q1.h F;

    /* renamed from: v, reason: collision with root package name */
    public int f3777v;

    /* renamed from: w, reason: collision with root package name */
    public boolean f3778w;

    /* renamed from: x, reason: collision with root package name */
    public boolean f3779x;

    /* renamed from: y, reason: collision with root package name */
    public boolean f3780y;

    /* renamed from: z, reason: collision with root package name */
    public final CheckedTextView f3781z;

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f3780y = true;
        q1.h hVar = new q1.h(4, this);
        this.F = hVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(q2.i.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(q2.e.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(q2.g.design_menu_item_text);
        this.f3781z = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        d1.o(checkedTextView, hVar);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.A == null) {
                this.A = (FrameLayout) ((ViewStub) findViewById(q2.g.design_menu_item_action_area_stub)).inflate();
            }
            this.A.removeAllViews();
            this.A.addView(view);
        }
    }

    @Override // k.d0
    public final void c(k.q qVar) {
        StateListDrawable stateListDrawable;
        this.B = qVar;
        int i7 = qVar.f7166a;
        if (i7 > 0) {
            setId(i7);
        }
        setVisibility(qVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(f.a.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(G, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            int i8 = d1.OVER_SCROLL_ALWAYS;
            p0.l0.q(this, stateListDrawable);
        }
        setCheckable(qVar.isCheckable());
        setChecked(qVar.isChecked());
        setEnabled(qVar.isEnabled());
        setTitle(qVar.f7170e);
        setIcon(qVar.getIcon());
        setActionView(qVar.getActionView());
        setContentDescription(qVar.f7182q);
        x3.a(this, qVar.f7183r);
        k.q qVar2 = this.B;
        CharSequence charSequence = qVar2.f7170e;
        CheckedTextView checkedTextView = this.f3781z;
        if (charSequence == null && qVar2.getIcon() == null && this.B.getActionView() != null) {
            checkedTextView.setVisibility(8);
            FrameLayout frameLayout = this.A;
            if (frameLayout != null) {
                w1 w1Var = (w1) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) w1Var).width = -1;
                this.A.setLayoutParams(w1Var);
                return;
            }
            return;
        }
        checkedTextView.setVisibility(0);
        FrameLayout frameLayout2 = this.A;
        if (frameLayout2 != null) {
            w1 w1Var2 = (w1) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) w1Var2).width = -2;
            this.A.setLayoutParams(w1Var2);
        }
    }

    @Override // k.d0
    public k.q getItemData() {
        return this.B;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i7) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i7 + 1);
        k.q qVar = this.B;
        if (qVar != null && qVar.isCheckable() && this.B.isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, G);
        }
        return onCreateDrawableState;
    }

    public void setCheckable(boolean z6) {
        refreshDrawableState();
        if (this.f3779x != z6) {
            this.f3779x = z6;
            this.F.h(this.f3781z, 2048);
        }
    }

    public void setChecked(boolean z6) {
        refreshDrawableState();
        CheckedTextView checkedTextView = this.f3781z;
        checkedTextView.setChecked(z6);
        checkedTextView.setTypeface(checkedTextView.getTypeface(), (z6 && this.f3780y) ? 1 : 0);
    }

    public void setHorizontalPadding(int i7) {
        setPadding(i7, getPaddingTop(), i7, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.D) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                j0.b.h(drawable, this.C);
            }
            int i7 = this.f3777v;
            drawable.setBounds(0, 0, i7, i7);
        } else if (this.f3778w) {
            if (this.E == null) {
                Resources resources = getResources();
                int i8 = q2.f.navigation_empty_icon;
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal threadLocal = h0.q.f6642a;
                Drawable a7 = h0.j.a(resources, i8, theme);
                this.E = a7;
                if (a7 != null) {
                    int i9 = this.f3777v;
                    a7.setBounds(0, 0, i9, i9);
                }
            }
            drawable = this.E;
        }
        androidx.core.widget.r.e(this.f3781z, drawable, null, null, null);
    }

    public void setIconPadding(int i7) {
        this.f3781z.setCompoundDrawablePadding(i7);
    }

    public void setIconSize(int i7) {
        this.f3777v = i7;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.C = colorStateList;
        this.D = colorStateList != null;
        k.q qVar = this.B;
        if (qVar != null) {
            setIcon(qVar.getIcon());
        }
    }

    public void setMaxLines(int i7) {
        this.f3781z.setMaxLines(i7);
    }

    public void setNeedsEmptyIcon(boolean z6) {
        this.f3778w = z6;
    }

    public void setShortcut(boolean z6, char c7) {
    }

    public void setTextAppearance(int i7) {
        this.f3781z.setTextAppearance(i7);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.f3781z.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.f3781z.setText(charSequence);
    }
}
