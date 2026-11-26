package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.k0;
import p0.d1;
import p0.m0;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    public final TextInputLayout f4421a;

    /* renamed from: b, reason: collision with root package name */
    public final AppCompatTextView f4422b;

    /* renamed from: c, reason: collision with root package name */
    public CharSequence f4423c;

    /* renamed from: d, reason: collision with root package name */
    public final CheckableImageButton f4424d;

    /* renamed from: e, reason: collision with root package name */
    public ColorStateList f4425e;

    /* renamed from: f, reason: collision with root package name */
    public PorterDuff.Mode f4426f;

    /* renamed from: g, reason: collision with root package name */
    public int f4427g;

    /* renamed from: h, reason: collision with root package name */
    public ImageView.ScaleType f4428h;

    /* renamed from: i, reason: collision with root package name */
    public View.OnLongClickListener f4429i;

    /* renamed from: j, reason: collision with root package name */
    public boolean f4430j;

    public r(TextInputLayout textInputLayout, androidx.appcompat.app.g gVar) {
        super(textInputLayout.getContext());
        CharSequence Q;
        this.f4421a = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(q2.i.design_text_input_start_icon, (ViewGroup) this, false);
        this.f4424d = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.f4422b = appCompatTextView;
        if (l6.f.N(getContext())) {
            p0.o.g((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        View.OnLongClickListener onLongClickListener = this.f4429i;
        checkableImageButton.setOnClickListener(null);
        l6.f.y0(checkableImageButton, onLongClickListener);
        this.f4429i = null;
        checkableImageButton.setOnLongClickListener(null);
        l6.f.y0(checkableImageButton, null);
        if (gVar.R(q2.m.TextInputLayout_startIconTint)) {
            this.f4425e = l6.f.x(getContext(), gVar, q2.m.TextInputLayout_startIconTint);
        }
        if (gVar.R(q2.m.TextInputLayout_startIconTintMode)) {
            this.f4426f = k0.g(gVar.L(q2.m.TextInputLayout_startIconTintMode, -1), null);
        }
        if (gVar.R(q2.m.TextInputLayout_startIconDrawable)) {
            b(gVar.I(q2.m.TextInputLayout_startIconDrawable));
            if (gVar.R(q2.m.TextInputLayout_startIconContentDescription) && checkableImageButton.getContentDescription() != (Q = gVar.Q(q2.m.TextInputLayout_startIconContentDescription))) {
                checkableImageButton.setContentDescription(Q);
            }
            checkableImageButton.setCheckable(gVar.E(q2.m.TextInputLayout_startIconCheckable, true));
        }
        int H = gVar.H(q2.m.TextInputLayout_startIconMinSize, getResources().getDimensionPixelSize(q2.e.mtrl_min_touch_target_size));
        if (H < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (H != this.f4427g) {
            this.f4427g = H;
            checkableImageButton.setMinimumWidth(H);
            checkableImageButton.setMinimumHeight(H);
        }
        if (gVar.R(q2.m.TextInputLayout_startIconScaleType)) {
            ImageView.ScaleType q6 = l6.f.q(gVar.L(q2.m.TextInputLayout_startIconScaleType, -1));
            this.f4428h = q6;
            checkableImageButton.setScaleType(q6);
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(q2.g.textinput_prefix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        int i7 = d1.OVER_SCROLL_ALWAYS;
        o0.f(appCompatTextView, 1);
        appCompatTextView.setTextAppearance(gVar.N(q2.m.TextInputLayout_prefixTextAppearance, 0));
        if (gVar.R(q2.m.TextInputLayout_prefixTextColor)) {
            appCompatTextView.setTextColor(gVar.F(q2.m.TextInputLayout_prefixTextColor));
        }
        CharSequence Q2 = gVar.Q(q2.m.TextInputLayout_prefixText);
        this.f4423c = TextUtils.isEmpty(Q2) ? null : Q2;
        appCompatTextView.setText(Q2);
        e();
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    public final int a() {
        int i7;
        CheckableImageButton checkableImageButton = this.f4424d;
        if (checkableImageButton.getVisibility() == 0) {
            i7 = p0.o.b((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()) + checkableImageButton.getMeasuredWidth();
        } else {
            i7 = 0;
        }
        int i8 = d1.OVER_SCROLL_ALWAYS;
        return m0.f(this.f4422b) + m0.f(this) + i7;
    }

    public final void b(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.f4424d;
        checkableImageButton.setImageDrawable(drawable);
        if (drawable != null) {
            ColorStateList colorStateList = this.f4425e;
            PorterDuff.Mode mode = this.f4426f;
            TextInputLayout textInputLayout = this.f4421a;
            l6.f.e(textInputLayout, checkableImageButton, colorStateList, mode);
            c(true);
            l6.f.p0(textInputLayout, checkableImageButton, this.f4425e);
            return;
        }
        c(false);
        View.OnLongClickListener onLongClickListener = this.f4429i;
        checkableImageButton.setOnClickListener(null);
        l6.f.y0(checkableImageButton, onLongClickListener);
        this.f4429i = null;
        checkableImageButton.setOnLongClickListener(null);
        l6.f.y0(checkableImageButton, null);
        if (checkableImageButton.getContentDescription() != null) {
            checkableImageButton.setContentDescription(null);
        }
    }

    public final void c(boolean z6) {
        CheckableImageButton checkableImageButton = this.f4424d;
        if ((checkableImageButton.getVisibility() == 0) != z6) {
            checkableImageButton.setVisibility(z6 ? 0 : 8);
            d();
            e();
        }
    }

    public final void d() {
        int f7;
        EditText editText = this.f4421a.f4275d;
        if (editText == null) {
            return;
        }
        if (this.f4424d.getVisibility() == 0) {
            f7 = 0;
        } else {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            f7 = m0.f(editText);
        }
        int compoundPaddingTop = editText.getCompoundPaddingTop();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(q2.e.material_input_text_to_prefix_suffix_padding);
        int compoundPaddingBottom = editText.getCompoundPaddingBottom();
        int i8 = d1.OVER_SCROLL_ALWAYS;
        m0.k(this.f4422b, f7, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
    }

    public final void e() {
        int i7 = (this.f4423c == null || this.f4430j) ? 8 : 0;
        setVisibility((this.f4424d.getVisibility() == 0 || i7 == 0) ? 0 : 8);
        this.f4422b.setVisibility(i7);
        this.f4421a.q();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        d();
    }
}
