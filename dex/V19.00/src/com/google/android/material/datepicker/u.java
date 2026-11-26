package com.google.android.material.datepicker;

import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.navi.tool.ui.theme.PositionSettingsDialog;
import com.navi.tool.view.DesktopMapCalendarCardView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class u implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3671a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3672b;

    public /* synthetic */ u(int i7, Object obj) {
        this.f3671a = i7;
        this.f3672b = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f3671a;
        Object obj = this.f3672b;
        switch (i7) {
            case 0:
                x xVar = (x) obj;
                xVar.A.setEnabled(xVar.e().h());
                xVar.f3701y.toggle();
                xVar.f3690n = xVar.f3690n == 1 ? 0 : 1;
                xVar.i(xVar.f3701y);
                xVar.h();
                break;
            case 1:
                com.google.android.material.textfield.c cVar = (com.google.android.material.textfield.c) obj;
                EditText editText = cVar.f4331i;
                if (editText != null) {
                    Editable text = editText.getText();
                    if (text != null) {
                        text.clear();
                    }
                    cVar.q();
                    break;
                }
                break;
            case 2:
                ((com.google.android.material.textfield.i) obj).u();
                break;
            case 3:
                com.google.android.material.textfield.q qVar = (com.google.android.material.textfield.q) obj;
                EditText editText2 = qVar.f4419f;
                if (editText2 != null) {
                    int selectionEnd = editText2.getSelectionEnd();
                    EditText editText3 = qVar.f4419f;
                    if (editText3 == null || !(editText3.getTransformationMethod() instanceof PasswordTransformationMethod)) {
                        qVar.f4419f.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        qVar.f4419f.setTransformationMethod(null);
                    }
                    if (selectionEnd >= 0) {
                        qVar.f4419f.setSelection(selectionEnd);
                    }
                    qVar.q();
                    break;
                }
                break;
            case 4:
                ((PositionSettingsDialog) obj).lambda$onCreateDialog$0(view);
                break;
            default:
                String[] strArr = DesktopMapCalendarCardView.f5918e;
                ((androidx.appcompat.app.o) obj).dismiss();
                break;
        }
    }
}
