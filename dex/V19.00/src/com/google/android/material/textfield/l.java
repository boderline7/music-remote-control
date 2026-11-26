package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.k0;
import java.util.Iterator;
import java.util.LinkedHashSet;
import p0.d1;
import p0.l0;
import p0.m0;
import p0.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l extends LinearLayout {

    /* renamed from: w, reason: collision with root package name */
    public static final /* synthetic */ int f4357w = 0;

    /* renamed from: a, reason: collision with root package name */
    public final TextInputLayout f4358a;

    /* renamed from: b, reason: collision with root package name */
    public final FrameLayout f4359b;

    /* renamed from: c, reason: collision with root package name */
    public final CheckableImageButton f4360c;

    /* renamed from: d, reason: collision with root package name */
    public ColorStateList f4361d;

    /* renamed from: e, reason: collision with root package name */
    public PorterDuff.Mode f4362e;

    /* renamed from: f, reason: collision with root package name */
    public View.OnLongClickListener f4363f;

    /* renamed from: g, reason: collision with root package name */
    public final CheckableImageButton f4364g;

    /* renamed from: h, reason: collision with root package name */
    public final d.i f4365h;

    /* renamed from: i, reason: collision with root package name */
    public int f4366i;

    /* renamed from: j, reason: collision with root package name */
    public final LinkedHashSet f4367j;

    /* renamed from: k, reason: collision with root package name */
    public ColorStateList f4368k;

    /* renamed from: l, reason: collision with root package name */
    public PorterDuff.Mode f4369l;

    /* renamed from: m, reason: collision with root package name */
    public int f4370m;

    /* renamed from: n, reason: collision with root package name */
    public ImageView.ScaleType f4371n;

    /* renamed from: o, reason: collision with root package name */
    public View.OnLongClickListener f4372o;

    /* renamed from: p, reason: collision with root package name */
    public CharSequence f4373p;

    /* renamed from: q, reason: collision with root package name */
    public final AppCompatTextView f4374q;

    /* renamed from: r, reason: collision with root package name */
    public boolean f4375r;

    /* renamed from: s, reason: collision with root package name */
    public EditText f4376s;

    /* renamed from: t, reason: collision with root package name */
    public final AccessibilityManager f4377t;

    /* renamed from: u, reason: collision with root package name */
    public q0.d f4378u;

    /* renamed from: v, reason: collision with root package name */
    public final j f4379v;

    public l(TextInputLayout textInputLayout, androidx.appcompat.app.g gVar) {
        super(textInputLayout.getContext());
        CharSequence Q;
        this.f4366i = 0;
        this.f4367j = new LinkedHashSet();
        this.f4379v = new j(this);
        k kVar = new k(this);
        this.f4377t = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.f4358a = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388613));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.f4359b = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater from = LayoutInflater.from(getContext());
        CheckableImageButton a7 = a(q2.g.text_input_error_icon, from, this);
        this.f4360c = a7;
        CheckableImageButton a8 = a(q2.g.text_input_end_icon, from, frameLayout);
        this.f4364g = a8;
        this.f4365h = new d.i(this, gVar);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.f4374q = appCompatTextView;
        if (gVar.R(q2.m.TextInputLayout_errorIconTint)) {
            this.f4361d = l6.f.x(getContext(), gVar, q2.m.TextInputLayout_errorIconTint);
        }
        if (gVar.R(q2.m.TextInputLayout_errorIconTintMode)) {
            this.f4362e = k0.g(gVar.L(q2.m.TextInputLayout_errorIconTintMode, -1), null);
        }
        if (gVar.R(q2.m.TextInputLayout_errorIconDrawable)) {
            i(gVar.I(q2.m.TextInputLayout_errorIconDrawable));
        }
        a7.setContentDescription(getResources().getText(q2.k.error_icon_content_description));
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.s(a7, 2);
        a7.setClickable(false);
        a7.setPressable(false);
        a7.setFocusable(false);
        if (!gVar.R(q2.m.TextInputLayout_passwordToggleEnabled)) {
            if (gVar.R(q2.m.TextInputLayout_endIconTint)) {
                this.f4368k = l6.f.x(getContext(), gVar, q2.m.TextInputLayout_endIconTint);
            }
            if (gVar.R(q2.m.TextInputLayout_endIconTintMode)) {
                this.f4369l = k0.g(gVar.L(q2.m.TextInputLayout_endIconTintMode, -1), null);
            }
        }
        if (gVar.R(q2.m.TextInputLayout_endIconMode)) {
            g(gVar.L(q2.m.TextInputLayout_endIconMode, 0));
            if (gVar.R(q2.m.TextInputLayout_endIconContentDescription) && a8.getContentDescription() != (Q = gVar.Q(q2.m.TextInputLayout_endIconContentDescription))) {
                a8.setContentDescription(Q);
            }
            a8.setCheckable(gVar.E(q2.m.TextInputLayout_endIconCheckable, true));
        } else if (gVar.R(q2.m.TextInputLayout_passwordToggleEnabled)) {
            if (gVar.R(q2.m.TextInputLayout_passwordToggleTint)) {
                this.f4368k = l6.f.x(getContext(), gVar, q2.m.TextInputLayout_passwordToggleTint);
            }
            if (gVar.R(q2.m.TextInputLayout_passwordToggleTintMode)) {
                this.f4369l = k0.g(gVar.L(q2.m.TextInputLayout_passwordToggleTintMode, -1), null);
            }
            g(gVar.E(q2.m.TextInputLayout_passwordToggleEnabled, false) ? 1 : 0);
            CharSequence Q2 = gVar.Q(q2.m.TextInputLayout_passwordToggleContentDescription);
            if (a8.getContentDescription() != Q2) {
                a8.setContentDescription(Q2);
            }
        }
        int H = gVar.H(q2.m.TextInputLayout_endIconMinSize, getResources().getDimensionPixelSize(q2.e.mtrl_min_touch_target_size));
        if (H < 0) {
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        }
        if (H != this.f4370m) {
            this.f4370m = H;
            a8.setMinimumWidth(H);
            a8.setMinimumHeight(H);
            a7.setMinimumWidth(H);
            a7.setMinimumHeight(H);
        }
        if (gVar.R(q2.m.TextInputLayout_endIconScaleType)) {
            ImageView.ScaleType q6 = l6.f.q(gVar.L(q2.m.TextInputLayout_endIconScaleType, -1));
            this.f4371n = q6;
            a8.setScaleType(q6);
            a7.setScaleType(q6);
        }
        appCompatTextView.setVisibility(8);
        appCompatTextView.setId(q2.g.textinput_suffix_text);
        appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        o0.f(appCompatTextView, 1);
        appCompatTextView.setTextAppearance(gVar.N(q2.m.TextInputLayout_suffixTextAppearance, 0));
        if (gVar.R(q2.m.TextInputLayout_suffixTextColor)) {
            appCompatTextView.setTextColor(gVar.F(q2.m.TextInputLayout_suffixTextColor));
        }
        CharSequence Q3 = gVar.Q(q2.m.TextInputLayout_suffixText);
        this.f4373p = TextUtils.isEmpty(Q3) ? null : Q3;
        appCompatTextView.setText(Q3);
        n();
        frameLayout.addView(a8);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(a7);
        textInputLayout.f4278e0.add(kVar);
        if (textInputLayout.f4275d != null) {
            kVar.a(textInputLayout);
        }
        addOnAttachStateChangeListener(new k.f(3, this));
    }

    public final CheckableImageButton a(int i7, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(q2.i.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i7);
        if (l6.f.N(getContext())) {
            p0.o.h((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        return checkableImageButton;
    }

    public final m b() {
        int i7 = this.f4366i;
        d.i iVar = this.f4365h;
        SparseArray sparseArray = (SparseArray) iVar.f6259c;
        m mVar = (m) sparseArray.get(i7);
        if (mVar == null) {
            if (i7 != -1) {
                int i8 = 1;
                if (i7 == 0) {
                    mVar = new d((l) iVar.f6260d, i8);
                } else if (i7 == 1) {
                    mVar = new q((l) iVar.f6260d, iVar.f6258b);
                } else if (i7 == 2) {
                    mVar = new c((l) iVar.f6260d);
                } else {
                    if (i7 != 3) {
                        throw new IllegalArgumentException(a.a.d("Invalid end icon mode: ", i7));
                    }
                    mVar = new i((l) iVar.f6260d);
                }
            } else {
                mVar = new d((l) iVar.f6260d, 0);
            }
            sparseArray.append(i7, mVar);
        }
        return mVar;
    }

    public final int c() {
        int c7;
        if (d() || e()) {
            CheckableImageButton checkableImageButton = this.f4364g;
            c7 = p0.o.c((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()) + checkableImageButton.getMeasuredWidth();
        } else {
            c7 = 0;
        }
        int i7 = d1.OVER_SCROLL_ALWAYS;
        return m0.e(this.f4374q) + m0.e(this) + c7;
    }

    public final boolean d() {
        return this.f4359b.getVisibility() == 0 && this.f4364g.getVisibility() == 0;
    }

    public final boolean e() {
        return this.f4360c.getVisibility() == 0;
    }

    public final void f(boolean z6) {
        boolean z7;
        boolean isActivated;
        boolean isChecked;
        m b7 = b();
        boolean k7 = b7.k();
        CheckableImageButton checkableImageButton = this.f4364g;
        boolean z8 = true;
        if (!k7 || (isChecked = checkableImageButton.isChecked()) == b7.l()) {
            z7 = false;
        } else {
            checkableImageButton.setChecked(!isChecked);
            z7 = true;
        }
        if (!(b7 instanceof i) || (isActivated = checkableImageButton.isActivated()) == b7.j()) {
            z8 = z7;
        } else {
            checkableImageButton.setActivated(!isActivated);
        }
        if (z6 || z8) {
            l6.f.p0(this.f4358a, checkableImageButton, this.f4368k);
        }
    }

    public final void g(int i7) {
        if (this.f4366i == i7) {
            return;
        }
        m b7 = b();
        q0.d dVar = this.f4378u;
        AccessibilityManager accessibilityManager = this.f4377t;
        if (dVar != null && accessibilityManager != null) {
            q0.c.b(accessibilityManager, dVar);
        }
        this.f4378u = null;
        b7.s();
        this.f4366i = i7;
        Iterator it = this.f4367j.iterator();
        if (it.hasNext()) {
            a.a.u(it.next());
            throw null;
        }
        h(i7 != 0);
        m b8 = b();
        int i8 = this.f4365h.f6257a;
        if (i8 == 0) {
            i8 = b8.d();
        }
        Drawable A = i8 != 0 ? l6.f.A(getContext(), i8) : null;
        CheckableImageButton checkableImageButton = this.f4364g;
        checkableImageButton.setImageDrawable(A);
        TextInputLayout textInputLayout = this.f4358a;
        if (A != null) {
            l6.f.e(textInputLayout, checkableImageButton, this.f4368k, this.f4369l);
            l6.f.p0(textInputLayout, checkableImageButton, this.f4368k);
        }
        int c7 = b8.c();
        CharSequence text = c7 != 0 ? getResources().getText(c7) : null;
        if (checkableImageButton.getContentDescription() != text) {
            checkableImageButton.setContentDescription(text);
        }
        checkableImageButton.setCheckable(b8.k());
        if (!b8.i(textInputLayout.getBoxBackgroundMode())) {
            throw new IllegalStateException("The current box background mode " + textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + i7);
        }
        b8.r();
        q0.d h7 = b8.h();
        this.f4378u = h7;
        if (h7 != null && accessibilityManager != null) {
            int i9 = d1.OVER_SCROLL_ALWAYS;
            if (o0.b(this)) {
                q0.c.a(accessibilityManager, this.f4378u);
            }
        }
        View.OnClickListener f7 = b8.f();
        View.OnLongClickListener onLongClickListener = this.f4372o;
        checkableImageButton.setOnClickListener(f7);
        l6.f.y0(checkableImageButton, onLongClickListener);
        EditText editText = this.f4376s;
        if (editText != null) {
            b8.m(editText);
            j(b8);
        }
        l6.f.e(textInputLayout, checkableImageButton, this.f4368k, this.f4369l);
        f(true);
    }

    public final void h(boolean z6) {
        if (d() != z6) {
            this.f4364g.setVisibility(z6 ? 0 : 8);
            k();
            m();
            this.f4358a.q();
        }
    }

    public final void i(Drawable drawable) {
        CheckableImageButton checkableImageButton = this.f4360c;
        checkableImageButton.setImageDrawable(drawable);
        l();
        l6.f.e(this.f4358a, checkableImageButton, this.f4361d, this.f4362e);
    }

    public final void j(m mVar) {
        if (this.f4376s == null) {
            return;
        }
        if (mVar.e() != null) {
            this.f4376s.setOnFocusChangeListener(mVar.e());
        }
        if (mVar.g() != null) {
            this.f4364g.setOnFocusChangeListener(mVar.g());
        }
    }

    public final void k() {
        this.f4359b.setVisibility((this.f4364g.getVisibility() != 0 || e()) ? 8 : 0);
        setVisibility((d() || e() || !((this.f4373p == null || this.f4375r) ? 8 : false)) ? 0 : 8);
    }

    public final void l() {
        CheckableImageButton checkableImageButton = this.f4360c;
        Drawable drawable = checkableImageButton.getDrawable();
        TextInputLayout textInputLayout = this.f4358a;
        checkableImageButton.setVisibility((drawable != null && textInputLayout.f4287j.f4405q && textInputLayout.m()) ? 0 : 8);
        k();
        m();
        if (this.f4366i != 0) {
            return;
        }
        textInputLayout.q();
    }

    public final void m() {
        int i7;
        TextInputLayout textInputLayout = this.f4358a;
        if (textInputLayout.f4275d == null) {
            return;
        }
        if (d() || e()) {
            i7 = 0;
        } else {
            EditText editText = textInputLayout.f4275d;
            int i8 = d1.OVER_SCROLL_ALWAYS;
            i7 = m0.e(editText);
        }
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(q2.e.material_input_text_to_prefix_suffix_padding);
        int paddingTop = textInputLayout.f4275d.getPaddingTop();
        int paddingBottom = textInputLayout.f4275d.getPaddingBottom();
        int i9 = d1.OVER_SCROLL_ALWAYS;
        m0.k(this.f4374q, dimensionPixelSize, paddingTop, i7, paddingBottom);
    }

    public final void n() {
        AppCompatTextView appCompatTextView = this.f4374q;
        int visibility = appCompatTextView.getVisibility();
        int i7 = (this.f4373p == null || this.f4375r) ? 8 : 0;
        if (visibility != i7) {
            b().p(i7 == 0);
        }
        k();
        appCompatTextView.setVisibility(i7);
        this.f4358a.q();
    }
}
