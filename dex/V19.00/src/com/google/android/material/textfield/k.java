package com.google.android.material.textfield;

import android.widget.EditText;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f4356a;

    public k(l lVar) {
        this.f4356a = lVar;
    }

    public final void a(TextInputLayout textInputLayout) {
        l lVar = this.f4356a;
        if (lVar.f4376s == textInputLayout.getEditText()) {
            return;
        }
        EditText editText = lVar.f4376s;
        j jVar = lVar.f4379v;
        if (editText != null) {
            editText.removeTextChangedListener(jVar);
            if (lVar.f4376s.getOnFocusChangeListener() == lVar.b().e()) {
                lVar.f4376s.setOnFocusChangeListener(null);
            }
        }
        EditText editText2 = textInputLayout.getEditText();
        lVar.f4376s = editText2;
        if (editText2 != null) {
            editText2.addTextChangedListener(jVar);
        }
        lVar.b().m(lVar.f4376s);
        lVar.j(lVar.b());
    }
}
