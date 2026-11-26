package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q extends m {

    /* renamed from: e, reason: collision with root package name */
    public final int f4418e;

    /* renamed from: f, reason: collision with root package name */
    public EditText f4419f;

    /* renamed from: g, reason: collision with root package name */
    public final com.google.android.material.datepicker.u f4420g;

    public q(l lVar, int i7) {
        super(lVar);
        this.f4418e = q2.f.design_password_eye;
        this.f4420g = new com.google.android.material.datepicker.u(3, this);
        if (i7 != 0) {
            this.f4418e = i7;
        }
    }

    @Override // com.google.android.material.textfield.m
    public final void b() {
        q();
    }

    @Override // com.google.android.material.textfield.m
    public final int c() {
        return q2.k.password_toggle_content_description;
    }

    @Override // com.google.android.material.textfield.m
    public final int d() {
        return this.f4418e;
    }

    @Override // com.google.android.material.textfield.m
    public final View.OnClickListener f() {
        return this.f4420g;
    }

    @Override // com.google.android.material.textfield.m
    public final boolean k() {
        return true;
    }

    @Override // com.google.android.material.textfield.m
    public final boolean l() {
        EditText editText = this.f4419f;
        return !(editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod));
    }

    @Override // com.google.android.material.textfield.m
    public final void m(EditText editText) {
        this.f4419f = editText;
        q();
    }

    @Override // com.google.android.material.textfield.m
    public final void r() {
        EditText editText = this.f4419f;
        if (editText != null) {
            if (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224) {
                this.f4419f.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    @Override // com.google.android.material.textfield.m
    public final void s() {
        EditText editText = this.f4419f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
