package com.navi.tool.service;

import android.app.Service;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class j implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4880a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Service f4881b;

    public /* synthetic */ j(Service service, int i7) {
        this.f4880a = i7;
        this.f4881b = service;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f4880a;
        Service service = this.f4881b;
        switch (i7) {
            case 0:
                ((GestureActionService) service).lambda$updatePanelLayoutFromPrefs$2(view);
                break;
            case 1:
                ((GestureActionService) service).lambda$ensurePanelView$1(view);
                break;
            default:
                ((WeatherAlertOverlayService) service).lambda$showOverlay$0(view);
                break;
        }
    }
}
