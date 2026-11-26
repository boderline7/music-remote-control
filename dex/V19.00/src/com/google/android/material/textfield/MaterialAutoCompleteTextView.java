package com.google.android.material.textfield;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.e2;
import androidx.appcompat.widget.y2;
import com.google.android.material.internal.e0;
import java.util.Locale;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class MaterialAutoCompleteTextView extends AppCompatAutoCompleteTextView {

    /* renamed from: e, reason: collision with root package name */
    public final e2 f4259e;

    /* renamed from: f, reason: collision with root package name */
    public final AccessibilityManager f4260f;

    /* renamed from: g, reason: collision with root package name */
    public final Rect f4261g;

    /* renamed from: h, reason: collision with root package name */
    public final int f4262h;

    /* renamed from: i, reason: collision with root package name */
    public final float f4263i;

    /* renamed from: j, reason: collision with root package name */
    public ColorStateList f4264j;

    /* renamed from: k, reason: collision with root package name */
    public int f4265k;

    /* renamed from: l, reason: collision with root package name */
    public ColorStateList f4266l;

    public MaterialAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, q2.c.autoCompleteTextViewStyle);
    }

    public MaterialAutoCompleteTextView(Context context, AttributeSet attributeSet, int i7) {
        super(t3.a.a(context, attributeSet, i7, 0), attributeSet, i7);
        this.f4261g = new Rect();
        Context context2 = getContext();
        TypedArray d7 = e0.d(context2, attributeSet, q2.m.MaterialAutoCompleteTextView, i7, q2.l.Widget_AppCompat_AutoCompleteTextView, new int[0]);
        if (d7.hasValue(q2.m.MaterialAutoCompleteTextView_android_inputType) && d7.getInt(q2.m.MaterialAutoCompleteTextView_android_inputType, 0) == 0) {
            setKeyListener(null);
        }
        this.f4262h = d7.getResourceId(q2.m.MaterialAutoCompleteTextView_simpleItemLayout, q2.i.mtrl_auto_complete_simple_item);
        this.f4263i = d7.getDimensionPixelOffset(q2.m.MaterialAutoCompleteTextView_android_popupElevation, q2.e.mtrl_exposed_dropdown_menu_popup_elevation);
        if (d7.hasValue(q2.m.MaterialAutoCompleteTextView_dropDownBackgroundTint)) {
            this.f4264j = ColorStateList.valueOf(d7.getColor(q2.m.MaterialAutoCompleteTextView_dropDownBackgroundTint, 0));
        }
        this.f4265k = d7.getColor(q2.m.MaterialAutoCompleteTextView_simpleItemSelectedColor, 0);
        this.f4266l = l6.f.w(context2, d7, q2.m.MaterialAutoCompleteTextView_simpleItemSelectedRippleColor);
        this.f4260f = (AccessibilityManager) context2.getSystemService("accessibility");
        e2 e2Var = new e2(context2);
        this.f4259e = e2Var;
        e2Var.f1236y = true;
        e2Var.f1237z.setFocusable(true);
        e2Var.f1226o = this;
        e2Var.f1237z.setInputMethodMode(2);
        e2Var.p(getAdapter());
        e2Var.f1227p = new y2(1, this);
        if (d7.hasValue(q2.m.MaterialAutoCompleteTextView_simpleItems)) {
            setSimpleItems(d7.getResourceId(q2.m.MaterialAutoCompleteTextView_simpleItems, 0));
        }
        d7.recycle();
    }

    public static void a(MaterialAutoCompleteTextView materialAutoCompleteTextView, Object obj) {
        materialAutoCompleteTextView.setText(materialAutoCompleteTextView.convertSelectionToString(obj), false);
    }

    public final TextInputLayout b() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    @Override // android.widget.AutoCompleteTextView
    public final void dismissDropDown() {
        AccessibilityManager accessibilityManager = this.f4260f;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.dismissDropDown();
        } else {
            this.f4259e.dismiss();
        }
    }

    public ColorStateList getDropDownBackgroundTintList() {
        return this.f4264j;
    }

    @Override // android.widget.TextView
    public CharSequence getHint() {
        TextInputLayout b7 = b();
        return (b7 == null || !b7.E) ? super.getHint() : b7.getHint();
    }

    public float getPopupElevation() {
        return this.f4263i;
    }

    public int getSimpleItemSelectedColor() {
        return this.f4265k;
    }

    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.f4266l;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout b7 = b();
        if (b7 != null && b7.E && super.getHint() == null) {
            String str = Build.MANUFACTURER;
            if ((str != null ? str.toLowerCase(Locale.ENGLISH) : h0.FRAGMENT_ENCODE_SET).equals("meizu")) {
                setHint(h0.FRAGMENT_ENCODE_SET);
            }
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f4259e.dismiss();
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (View.MeasureSpec.getMode(i7) == Integer.MIN_VALUE) {
            int measuredWidth = getMeasuredWidth();
            ListAdapter adapter = getAdapter();
            TextInputLayout b7 = b();
            int i9 = 0;
            if (adapter != null && b7 != null) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
                e2 e2Var = this.f4259e;
                int min = Math.min(adapter.getCount(), Math.max(0, !e2Var.f1237z.isShowing() ? -1 : e2Var.f1214c.getSelectedItemPosition()) + 15);
                View view = null;
                int i10 = 0;
                for (int max = Math.max(0, min - 15); max < min; max++) {
                    int itemViewType = adapter.getItemViewType(max);
                    if (itemViewType != i9) {
                        view = null;
                        i9 = itemViewType;
                    }
                    view = adapter.getView(max, view, b7);
                    if (view.getLayoutParams() == null) {
                        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                    }
                    view.measure(makeMeasureSpec, makeMeasureSpec2);
                    i10 = Math.max(i10, view.getMeasuredWidth());
                }
                Drawable background = e2Var.f1237z.getBackground();
                if (background != null) {
                    Rect rect = this.f4261g;
                    background.getPadding(rect);
                    i10 += rect.left + rect.right;
                }
                i9 = b7.getEndIconView().getMeasuredWidth() + i10;
            }
            setMeasuredDimension(Math.min(Math.max(measuredWidth, i9), View.MeasureSpec.getSize(i7)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z6) {
        AccessibilityManager accessibilityManager = this.f4260f;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.onWindowFocusChanged(z6);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(T t6) {
        super.setAdapter(t6);
        this.f4259e.p(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundDrawable(Drawable drawable) {
        super.setDropDownBackgroundDrawable(drawable);
        e2 e2Var = this.f4259e;
        if (e2Var != null) {
            e2Var.n(drawable);
        }
    }

    public void setDropDownBackgroundTint(int i7) {
        setDropDownBackgroundTintList(ColorStateList.valueOf(i7));
    }

    public void setDropDownBackgroundTintList(ColorStateList colorStateList) {
        this.f4264j = colorStateList;
        Drawable dropDownBackground = getDropDownBackground();
        if (dropDownBackground instanceof p3.j) {
            ((p3.j) dropDownBackground).o(this.f4264j);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.f4259e.f1228q = getOnItemSelectedListener();
    }

    @Override // android.widget.TextView
    public void setRawInputType(int i7) {
        super.setRawInputType(i7);
        TextInputLayout b7 = b();
        if (b7 != null) {
            b7.s();
        }
    }

    public void setSimpleItemSelectedColor(int i7) {
        this.f4265k = i7;
        if (getAdapter() instanceof p) {
            ((p) getAdapter()).a();
        }
    }

    public void setSimpleItemSelectedRippleColor(ColorStateList colorStateList) {
        this.f4266l = colorStateList;
        if (getAdapter() instanceof p) {
            ((p) getAdapter()).a();
        }
    }

    public void setSimpleItems(int i7) {
        setSimpleItems(getResources().getStringArray(i7));
    }

    public void setSimpleItems(String[] strArr) {
        setAdapter(new p(this, getContext(), this.f4262h, strArr));
    }

    @Override // android.widget.AutoCompleteTextView
    public final void showDropDown() {
        AccessibilityManager accessibilityManager = this.f4260f;
        if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
            super.showDropDown();
        } else {
            this.f4259e.g();
        }
    }
}
