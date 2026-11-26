package com.navi.tool.ui.extra;

import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class m1 implements DialogInterface.OnShowListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5330a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5331b;

    public /* synthetic */ m1(androidx.appcompat.app.o oVar, int i7) {
        this.f5330a = i7;
        this.f5331b = oVar;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        int i7 = this.f5330a;
        androidx.appcompat.app.o oVar = this.f5331b;
        switch (i7) {
            case 0:
                DesktopAssistantCarControlHelper.lambda$showFragranceControlDialog$75(oVar, dialogInterface);
                break;
            case 1:
                DesktopAssistantCarControlHelper.lambda$showHvacModeGrid$67(oVar, dialogInterface);
                break;
            default:
                DesktopAssistantCarControlHelper.lambda$showSeatMassageDialog$72(oVar, dialogInterface);
                break;
        }
    }
}
