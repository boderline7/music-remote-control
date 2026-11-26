package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class r4 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5439a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.r f5440b;

    public /* synthetic */ r4(androidx.appcompat.app.r rVar, int i7) {
        this.f5439a = i7;
        this.f5440b = rVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5439a;
        androidx.appcompat.app.r rVar = this.f5440b;
        switch (i7) {
            case 0:
                ((ImageViewerActivity) rVar).lambda$onCreate$0(view);
                break;
            default:
                ((PermissionManagerActivity) rVar).lambda$initViews$8(view);
                break;
        }
    }
}
