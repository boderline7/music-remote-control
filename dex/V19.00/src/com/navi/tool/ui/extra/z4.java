package com.navi.tool.ui.extra;

import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class z4 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5606a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5607b;

    public /* synthetic */ z4(int i7, Object obj) {
        this.f5606a = i7;
        this.f5607b = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5606a;
        Object obj = this.f5607b;
        switch (i8) {
            case 0:
                PermissionManagerActivity.lambda$showInputMethodPicker$12((int[]) obj, dialogInterface, i7);
                break;
            default:
                ((StorageAnalysisActivity) obj).lambda$deleteSelectedFiles$5(dialogInterface, i7);
                break;
        }
    }
}
