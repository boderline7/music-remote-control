package com.google.android.material.datepicker;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class i implements View.OnFocusChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3632a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3633b;

    public /* synthetic */ i(int i7, Object obj) {
        this.f3632a = i7;
        this.f3633b = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z6) {
        int i7 = this.f3632a;
        Object obj = this.f3633b;
        switch (i7) {
            case 0:
                for (EditText editText : (EditText[]) obj) {
                    if (editText.hasFocus()) {
                        break;
                    }
                }
                Context context = view.getContext();
                Object obj2 = g0.g.f6423a;
                InputMethodManager inputMethodManager = (InputMethodManager) g0.d.b(context, InputMethodManager.class);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    break;
                }
                break;
            case 1:
                com.google.android.material.textfield.c cVar = (com.google.android.material.textfield.c) obj;
                cVar.t(cVar.u());
                break;
            default:
                com.google.android.material.textfield.i iVar = (com.google.android.material.textfield.i) obj;
                iVar.f4348l = z6;
                iVar.q();
                if (!z6) {
                    iVar.t(false);
                    iVar.f4349m = false;
                    break;
                }
                break;
        }
    }
}
