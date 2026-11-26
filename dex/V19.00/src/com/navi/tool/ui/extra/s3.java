package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.widget.EditText;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class s3 implements DialogInterface.OnShowListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5450a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.r f5451b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ KeyEvent.Callback f5452c;

    public /* synthetic */ s3(androidx.appcompat.app.r rVar, KeyEvent.Callback callback, int i7) {
        this.f5450a = i7;
        this.f5451b = rVar;
        this.f5452c = callback;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        int i7 = this.f5450a;
        KeyEvent.Callback callback = this.f5452c;
        androidx.appcompat.app.r rVar = this.f5451b;
        switch (i7) {
            case 0:
                ((FileManagerActivity) rVar).lambda$showCreateDialog$115((EditText) callback, dialogInterface);
                break;
            default:
                ((PermissionManagerActivity) rVar).lambda$showInputMethodPicker$14((androidx.appcompat.app.o) callback, dialogInterface);
                break;
        }
    }
}
