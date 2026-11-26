package com.navi.tool.ui.extra;

import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class t1 implements androidx.lifecycle.h0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5462a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantCarControlHelper f5463b;

    public /* synthetic */ t1(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, int i7) {
        this.f5462a = i7;
        this.f5463b = desktopAssistantCarControlHelper;
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        int i7 = this.f5462a;
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5463b;
        switch (i7) {
            case 1:
                desktopAssistantCarControlHelper.lambda$initCarControlViews$19((Map) obj);
                break;
            case 2:
                desktopAssistantCarControlHelper.lambda$initCarControlViews$20((Integer) obj);
                break;
            case 3:
                desktopAssistantCarControlHelper.onPhoneTireChanged((o4.s) obj);
                break;
            case 4:
                desktopAssistantCarControlHelper.lambda$initCarControlViews$39((String) obj);
                break;
            case 5:
                desktopAssistantCarControlHelper.lambda$initCarControlViews$40((String) obj);
                break;
            case 6:
                desktopAssistantCarControlHelper.lambda$initCarControlViews$41((Map) obj);
                break;
            case 7:
                desktopAssistantCarControlHelper.lambda$initCarControlViews$43((Map) obj);
                break;
            case 8:
                desktopAssistantCarControlHelper.updateWiperSensitivityDisplay((String) obj);
                break;
            case 9:
                desktopAssistantCarControlHelper.updateLightHeightDisplay((String) obj);
                break;
            default:
                desktopAssistantCarControlHelper.lambda$initCarControlViews$44((Map) obj);
                break;
        }
    }
}
