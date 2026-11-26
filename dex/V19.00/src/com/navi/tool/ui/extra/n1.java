package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n1 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5345a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantCarControlHelper f5346b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5347c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5348d;

    public /* synthetic */ n1(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, int i7, androidx.appcompat.app.o oVar, int i8) {
        this.f5345a = i8;
        this.f5346b = desktopAssistantCarControlHelper;
        this.f5347c = i7;
        this.f5348d = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5345a;
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5346b;
        androidx.appcompat.app.o oVar = this.f5348d;
        int i8 = this.f5347c;
        switch (i7) {
            case 0:
                desktopAssistantCarControlHelper.lambda$showHvacModeGrid$68(i8, oVar, view);
                break;
            case 1:
                desktopAssistantCarControlHelper.lambda$showSeatMemorySaveConfirm$61(i8, oVar, view);
                break;
            default:
                desktopAssistantCarControlHelper.lambda$showSeatMemoryApplyConfirm$59(i8, oVar, view);
                break;
        }
    }
}
