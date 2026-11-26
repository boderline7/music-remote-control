package com.google.android.material.textfield;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;
import com.yalantis.ucrop.view.CropImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c extends m {

    /* renamed from: e, reason: collision with root package name */
    public final int f4327e;

    /* renamed from: f, reason: collision with root package name */
    public final int f4328f;

    /* renamed from: g, reason: collision with root package name */
    public final TimeInterpolator f4329g;

    /* renamed from: h, reason: collision with root package name */
    public final TimeInterpolator f4330h;

    /* renamed from: i, reason: collision with root package name */
    public EditText f4331i;

    /* renamed from: j, reason: collision with root package name */
    public final com.google.android.material.datepicker.u f4332j;

    /* renamed from: k, reason: collision with root package name */
    public final com.google.android.material.datepicker.i f4333k;

    /* renamed from: l, reason: collision with root package name */
    public AnimatorSet f4334l;

    /* renamed from: m, reason: collision with root package name */
    public ValueAnimator f4335m;

    public c(l lVar) {
        super(lVar);
        this.f4332j = new com.google.android.material.datepicker.u(1, this);
        this.f4333k = new com.google.android.material.datepicker.i(1, this);
        this.f4327e = l6.f.u0(lVar.getContext(), q2.c.motionDurationShort3, 100);
        this.f4328f = l6.f.u0(lVar.getContext(), q2.c.motionDurationShort3, z1.c.OR_INT);
        this.f4329g = l6.f.v0(lVar.getContext(), q2.c.motionEasingLinearInterpolator, r2.a.LINEAR_INTERPOLATOR);
        this.f4330h = l6.f.v0(lVar.getContext(), q2.c.motionEasingEmphasizedInterpolator, r2.a.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    }

    @Override // com.google.android.material.textfield.m
    public final void a() {
        if (this.f4381b.f4373p != null) {
            return;
        }
        t(u());
    }

    @Override // com.google.android.material.textfield.m
    public final int c() {
        return q2.k.clear_text_end_icon_content_description;
    }

    @Override // com.google.android.material.textfield.m
    public final int d() {
        return q2.f.mtrl_ic_cancel;
    }

    @Override // com.google.android.material.textfield.m
    public final View.OnFocusChangeListener e() {
        return this.f4333k;
    }

    @Override // com.google.android.material.textfield.m
    public final View.OnClickListener f() {
        return this.f4332j;
    }

    @Override // com.google.android.material.textfield.m
    public final View.OnFocusChangeListener g() {
        return this.f4333k;
    }

    @Override // com.google.android.material.textfield.m
    public final void m(EditText editText) {
        this.f4331i = editText;
        this.f4380a.setEndIconVisible(u());
    }

    @Override // com.google.android.material.textfield.m
    public final void p(boolean z6) {
        if (this.f4381b.f4373p == null) {
            return;
        }
        t(z6);
    }

    @Override // com.google.android.material.textfield.m
    public final void r() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        ofFloat.setInterpolator(this.f4330h);
        ofFloat.setDuration(this.f4328f);
        final int i7 = 0;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.textfield.a

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ c f4324b;

            {
                this.f4324b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i8 = i7;
                c cVar = this.f4324b;
                switch (i8) {
                    case 0:
                        cVar.getClass();
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = cVar.f4383d;
                        checkableImageButton.setScaleX(floatValue);
                        checkableImageButton.setScaleY(floatValue);
                        break;
                    default:
                        cVar.getClass();
                        cVar.f4383d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                }
            }
        });
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        TimeInterpolator timeInterpolator = this.f4329g;
        ofFloat2.setInterpolator(timeInterpolator);
        int i8 = this.f4327e;
        ofFloat2.setDuration(i8);
        final int i9 = 1;
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.textfield.a

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ c f4324b;

            {
                this.f4324b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i82 = i9;
                c cVar = this.f4324b;
                switch (i82) {
                    case 0:
                        cVar.getClass();
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = cVar.f4383d;
                        checkableImageButton.setScaleX(floatValue);
                        checkableImageButton.setScaleY(floatValue);
                        break;
                    default:
                        cVar.getClass();
                        cVar.f4383d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.f4334l = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2);
        this.f4334l.addListener(new b(this, i7));
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        ofFloat3.setInterpolator(timeInterpolator);
        ofFloat3.setDuration(i8);
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.textfield.a

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ c f4324b;

            {
                this.f4324b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int i82 = i9;
                c cVar = this.f4324b;
                switch (i82) {
                    case 0:
                        cVar.getClass();
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = cVar.f4383d;
                        checkableImageButton.setScaleX(floatValue);
                        checkableImageButton.setScaleY(floatValue);
                        break;
                    default:
                        cVar.getClass();
                        cVar.f4383d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                }
            }
        });
        this.f4335m = ofFloat3;
        ofFloat3.addListener(new b(this, i9));
    }

    @Override // com.google.android.material.textfield.m
    public final void s() {
        EditText editText = this.f4331i;
        if (editText != null) {
            editText.post(new androidx.activity.e(11, this));
        }
    }

    public final void t(boolean z6) {
        boolean z7 = this.f4381b.d() == z6;
        if (z6 && !this.f4334l.isRunning()) {
            this.f4335m.cancel();
            this.f4334l.start();
            if (z7) {
                this.f4334l.end();
                return;
            }
            return;
        }
        if (z6) {
            return;
        }
        this.f4334l.cancel();
        this.f4335m.start();
        if (z7) {
            this.f4335m.end();
        }
    }

    public final boolean u() {
        EditText editText = this.f4331i;
        return editText != null && (editText.hasFocus() || this.f4383d.hasFocus()) && this.f4331i.getText().length() > 0;
    }
}
