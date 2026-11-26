package com.google.android.material.textfield;

import android.text.Editable;
import com.google.android.material.internal.d0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j extends d0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f4355a;

    public j(l lVar) {
        this.f4355a = lVar;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.f4355a.b().a();
    }

    @Override // com.google.android.material.internal.d0, android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        this.f4355a.b().b();
    }
}
