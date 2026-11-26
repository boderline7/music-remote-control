package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import n1.z;
import p0.d1;
import p0.m0;
import p0.o0;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class o {
    public ColorStateList A;
    public Typeface B;

    /* renamed from: a, reason: collision with root package name */
    public final int f4389a;

    /* renamed from: b, reason: collision with root package name */
    public final int f4390b;

    /* renamed from: c, reason: collision with root package name */
    public final int f4391c;

    /* renamed from: d, reason: collision with root package name */
    public final TimeInterpolator f4392d;

    /* renamed from: e, reason: collision with root package name */
    public final TimeInterpolator f4393e;

    /* renamed from: f, reason: collision with root package name */
    public final TimeInterpolator f4394f;

    /* renamed from: g, reason: collision with root package name */
    public final Context f4395g;

    /* renamed from: h, reason: collision with root package name */
    public final TextInputLayout f4396h;

    /* renamed from: i, reason: collision with root package name */
    public LinearLayout f4397i;

    /* renamed from: j, reason: collision with root package name */
    public int f4398j;

    /* renamed from: k, reason: collision with root package name */
    public FrameLayout f4399k;

    /* renamed from: l, reason: collision with root package name */
    public Animator f4400l;

    /* renamed from: m, reason: collision with root package name */
    public final float f4401m;

    /* renamed from: n, reason: collision with root package name */
    public int f4402n;

    /* renamed from: o, reason: collision with root package name */
    public int f4403o;

    /* renamed from: p, reason: collision with root package name */
    public CharSequence f4404p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f4405q;

    /* renamed from: r, reason: collision with root package name */
    public AppCompatTextView f4406r;

    /* renamed from: s, reason: collision with root package name */
    public CharSequence f4407s;

    /* renamed from: t, reason: collision with root package name */
    public int f4408t;

    /* renamed from: u, reason: collision with root package name */
    public int f4409u;

    /* renamed from: v, reason: collision with root package name */
    public ColorStateList f4410v;

    /* renamed from: w, reason: collision with root package name */
    public CharSequence f4411w;

    /* renamed from: x, reason: collision with root package name */
    public boolean f4412x;

    /* renamed from: y, reason: collision with root package name */
    public AppCompatTextView f4413y;

    /* renamed from: z, reason: collision with root package name */
    public int f4414z;

    public o(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.f4395g = context;
        this.f4396h = textInputLayout;
        this.f4401m = context.getResources().getDimensionPixelSize(q2.e.design_textinput_caption_translate_y);
        this.f4389a = l6.f.u0(context, q2.c.motionDurationShort4, z1.c.RSUB_INT_LIT8);
        this.f4390b = l6.f.u0(context, q2.c.motionDurationMedium4, z1.c.SUB_FLOAT);
        this.f4391c = l6.f.u0(context, q2.c.motionDurationShort4, z1.c.SUB_FLOAT);
        this.f4392d = l6.f.v0(context, q2.c.motionEasingEmphasizedDecelerateInterpolator, r2.a.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        int i7 = q2.c.motionEasingEmphasizedDecelerateInterpolator;
        TimeInterpolator timeInterpolator = r2.a.LINEAR_INTERPOLATOR;
        this.f4393e = l6.f.v0(context, i7, timeInterpolator);
        this.f4394f = l6.f.v0(context, q2.c.motionEasingLinearInterpolator, timeInterpolator);
    }

    public final void a(TextView textView, int i7) {
        if (this.f4397i == null && this.f4399k == null) {
            Context context = this.f4395g;
            LinearLayout linearLayout = new LinearLayout(context);
            this.f4397i = linearLayout;
            linearLayout.setOrientation(0);
            LinearLayout linearLayout2 = this.f4397i;
            TextInputLayout textInputLayout = this.f4396h;
            textInputLayout.addView(linearLayout2, -1, -2);
            this.f4399k = new FrameLayout(context);
            this.f4397i.addView(this.f4399k, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (textInputLayout.getEditText() != null) {
                b();
            }
        }
        if (i7 == 0 || i7 == 1) {
            this.f4399k.setVisibility(0);
            this.f4399k.addView(textView);
        } else {
            this.f4397i.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f4397i.setVisibility(0);
        this.f4398j++;
    }

    public final void b() {
        if (this.f4397i != null) {
            TextInputLayout textInputLayout = this.f4396h;
            if (textInputLayout.getEditText() != null) {
                EditText editText = textInputLayout.getEditText();
                Context context = this.f4395g;
                boolean N = l6.f.N(context);
                LinearLayout linearLayout = this.f4397i;
                int i7 = q2.e.material_helper_text_font_1_3_padding_horizontal;
                int i8 = d1.OVER_SCROLL_ALWAYS;
                int f7 = m0.f(editText);
                if (N) {
                    f7 = context.getResources().getDimensionPixelSize(i7);
                }
                int i9 = q2.e.material_helper_text_font_1_3_padding_top;
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(q2.e.material_helper_text_default_padding_top);
                if (N) {
                    dimensionPixelSize = context.getResources().getDimensionPixelSize(i9);
                }
                int i10 = q2.e.material_helper_text_font_1_3_padding_horizontal;
                int e7 = m0.e(editText);
                if (N) {
                    e7 = context.getResources().getDimensionPixelSize(i10);
                }
                m0.k(linearLayout, f7, dimensionPixelSize, e7, 0);
            }
        }
    }

    public final void c() {
        Animator animator = this.f4400l;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void d(ArrayList arrayList, boolean z6, TextView textView, int i7, int i8, int i9) {
        if (textView == null || !z6) {
            return;
        }
        if (i7 == i9 || i7 == i8) {
            boolean z7 = i9 == i7;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z7 ? 1.0f : 0.0f);
            int i10 = this.f4391c;
            ofFloat.setDuration(z7 ? this.f4390b : i10);
            ofFloat.setInterpolator(z7 ? this.f4393e : this.f4394f);
            if (i7 == i9 && i8 != 0) {
                ofFloat.setStartDelay(i10);
            }
            arrayList.add(ofFloat);
            if (i9 != i7 || i8 == 0) {
                return;
            }
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.f4401m, CropImageView.DEFAULT_ASPECT_RATIO);
            ofFloat2.setDuration(this.f4389a);
            ofFloat2.setInterpolator(this.f4392d);
            ofFloat2.setStartDelay(i10);
            arrayList.add(ofFloat2);
        }
    }

    public final TextView e(int i7) {
        if (i7 == 1) {
            return this.f4406r;
        }
        if (i7 != 2) {
            return null;
        }
        return this.f4413y;
    }

    public final void f() {
        this.f4404p = null;
        c();
        if (this.f4402n == 1) {
            if (!this.f4412x || TextUtils.isEmpty(this.f4411w)) {
                this.f4403o = 0;
            } else {
                this.f4403o = 2;
            }
        }
        i(this.f4402n, this.f4403o, h(this.f4406r, h0.FRAGMENT_ENCODE_SET));
    }

    public final void g(TextView textView, int i7) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.f4397i;
        if (linearLayout == null) {
            return;
        }
        if ((i7 == 0 || i7 == 1) && (frameLayout = this.f4399k) != null) {
            frameLayout.removeView(textView);
        } else {
            linearLayout.removeView(textView);
        }
        int i8 = this.f4398j - 1;
        this.f4398j = i8;
        LinearLayout linearLayout2 = this.f4397i;
        if (i8 == 0) {
            linearLayout2.setVisibility(8);
        }
    }

    public final boolean h(TextView textView, CharSequence charSequence) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        TextInputLayout textInputLayout = this.f4396h;
        return o0.c(textInputLayout) && textInputLayout.isEnabled() && !(this.f4403o == this.f4402n && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    public final void i(int i7, int i8, boolean z6) {
        TextView e7;
        TextView e8;
        if (i7 == i8) {
            return;
        }
        if (z6) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f4400l = animatorSet;
            ArrayList arrayList = new ArrayList();
            d(arrayList, this.f4412x, this.f4413y, 2, i7, i8);
            d(arrayList, this.f4405q, this.f4406r, 1, i7, i8);
            z.S0(animatorSet, arrayList);
            animatorSet.addListener(new n(this, i8, e(i7), i7, e(i8)));
            animatorSet.start();
        } else if (i7 != i8) {
            if (i8 != 0 && (e8 = e(i8)) != null) {
                e8.setVisibility(0);
                e8.setAlpha(1.0f);
            }
            if (i7 != 0 && (e7 = e(i7)) != null) {
                e7.setVisibility(4);
                if (i7 == 1) {
                    e7.setText((CharSequence) null);
                }
            }
            this.f4402n = i8;
        }
        TextInputLayout textInputLayout = this.f4396h;
        textInputLayout.r();
        textInputLayout.u(z6, false);
        textInputLayout.x();
    }
}
