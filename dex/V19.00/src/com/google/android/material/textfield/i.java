package com.google.android.material.textfield;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i extends m {

    /* renamed from: e, reason: collision with root package name */
    public final int f4341e;

    /* renamed from: f, reason: collision with root package name */
    public final int f4342f;

    /* renamed from: g, reason: collision with root package name */
    public final TimeInterpolator f4343g;

    /* renamed from: h, reason: collision with root package name */
    public AutoCompleteTextView f4344h;

    /* renamed from: i, reason: collision with root package name */
    public final com.google.android.material.datepicker.u f4345i;

    /* renamed from: j, reason: collision with root package name */
    public final com.google.android.material.datepicker.i f4346j;

    /* renamed from: k, reason: collision with root package name */
    public final e0.f f4347k;

    /* renamed from: l, reason: collision with root package name */
    public boolean f4348l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f4349m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f4350n;

    /* renamed from: o, reason: collision with root package name */
    public long f4351o;

    /* renamed from: p, reason: collision with root package name */
    public AccessibilityManager f4352p;

    /* renamed from: q, reason: collision with root package name */
    public ValueAnimator f4353q;

    /* renamed from: r, reason: collision with root package name */
    public ValueAnimator f4354r;

    public i(l lVar) {
        super(lVar);
        int i7 = 2;
        this.f4345i = new com.google.android.material.datepicker.u(i7, this);
        this.f4346j = new com.google.android.material.datepicker.i(i7, this);
        this.f4347k = new e0.f(6, this);
        this.f4351o = Long.MAX_VALUE;
        this.f4342f = l6.f.u0(lVar.getContext(), q2.c.motionDurationShort3, 67);
        this.f4341e = l6.f.u0(lVar.getContext(), q2.c.motionDurationShort3, 50);
        this.f4343g = l6.f.v0(lVar.getContext(), q2.c.motionEasingLinearInterpolator, r2.a.LINEAR_INTERPOLATOR);
    }

    @Override // com.google.android.material.textfield.m
    public final void a() {
        if (this.f4352p.isTouchExplorationEnabled() && this.f4344h.getInputType() != 0 && !this.f4383d.hasFocus()) {
            this.f4344h.dismissDropDown();
        }
        this.f4344h.post(new androidx.activity.e(12, this));
    }

    @Override // com.google.android.material.textfield.m
    public final int c() {
        return q2.k.exposed_dropdown_menu_content_description;
    }

    @Override // com.google.android.material.textfield.m
    public final int d() {
        return q2.f.mtrl_dropdown_arrow;
    }

    @Override // com.google.android.material.textfield.m
    public final View.OnFocusChangeListener e() {
        return this.f4346j;
    }

    @Override // com.google.android.material.textfield.m
    public final View.OnClickListener f() {
        return this.f4345i;
    }

    @Override // com.google.android.material.textfield.m
    public final q0.d h() {
        return this.f4347k;
    }

    @Override // com.google.android.material.textfield.m
    public final boolean i(int i7) {
        return i7 != 0;
    }

    @Override // com.google.android.material.textfield.m
    public final boolean j() {
        return this.f4348l;
    }

    @Override // com.google.android.material.textfield.m
    public final boolean l() {
        return this.f4350n;
    }

    @Override // com.google.android.material.textfield.m
    public final void m(EditText editText) {
        if (!(editText instanceof AutoCompleteTextView)) {
            throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
        }
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
        this.f4344h = autoCompleteTextView;
        autoCompleteTextView.setOnTouchListener(new com.google.android.material.search.c(1, this));
        this.f4344h.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: com.google.android.material.textfield.h
            @Override // android.widget.AutoCompleteTextView.OnDismissListener
            public final void onDismiss() {
                i iVar = i.this;
                iVar.f4349m = true;
                iVar.f4351o = System.currentTimeMillis();
                iVar.t(false);
            }
        });
        this.f4344h.setThreshold(0);
        TextInputLayout textInputLayout = this.f4380a;
        textInputLayout.setErrorIconDrawable((Drawable) null);
        if (editText.getInputType() == 0 && this.f4352p.isTouchExplorationEnabled()) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            l0.s(this.f4383d, 2);
        }
        textInputLayout.setEndIconVisible(true);
    }

    @Override // com.google.android.material.textfield.m
    public final void n(q0.i iVar) {
        if (this.f4344h.getInputType() == 0) {
            iVar.f(Spinner.class.getName());
        }
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        if (accessibilityNodeInfo.isShowingHintText()) {
            accessibilityNodeInfo.setHintText(null);
        }
    }

    @Override // com.google.android.material.textfield.m
    public final void o(AccessibilityEvent accessibilityEvent) {
        if (this.f4352p.isEnabled() && this.f4344h.getInputType() == 0) {
            boolean z6 = accessibilityEvent.getEventType() == 32768 && this.f4350n && !this.f4344h.isPopupShowing();
            if (accessibilityEvent.getEventType() == 1 || z6) {
                u();
                this.f4349m = true;
                this.f4351o = System.currentTimeMillis();
            }
        }
    }

    @Override // com.google.android.material.textfield.m
    public final void r() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        TimeInterpolator timeInterpolator = this.f4343g;
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setDuration(this.f4342f);
        ofFloat.addUpdateListener(new x2.b(5, this));
        this.f4354r = ofFloat;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration(this.f4341e);
        ofFloat2.addUpdateListener(new x2.b(5, this));
        this.f4353q = ofFloat2;
        ofFloat2.addListener(new androidx.appcompat.widget.d(11, this));
        this.f4352p = (AccessibilityManager) this.f4382c.getSystemService("accessibility");
    }

    @Override // com.google.android.material.textfield.m
    public final void s() {
        AutoCompleteTextView autoCompleteTextView = this.f4344h;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener(null);
            this.f4344h.setOnDismissListener(null);
        }
    }

    public final void t(boolean z6) {
        if (this.f4350n != z6) {
            this.f4350n = z6;
            this.f4354r.cancel();
            this.f4353q.start();
        }
    }

    public final void u() {
        if (this.f4344h == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f4351o;
        if (currentTimeMillis < 0 || currentTimeMillis > 300) {
            this.f4349m = false;
        }
        if (this.f4349m) {
            this.f4349m = false;
            return;
        }
        t(!this.f4350n);
        if (!this.f4350n) {
            this.f4344h.dismissDropDown();
        } else {
            this.f4344h.requestFocus();
            this.f4344h.showDropDown();
        }
    }
}
