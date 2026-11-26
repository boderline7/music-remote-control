package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class s1 implements View.OnLongClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5446a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantCarControlHelper f5447b;

    public /* synthetic */ s1(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, int i7) {
        this.f5446a = i7;
        this.f5447b = desktopAssistantCarControlHelper;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        boolean lambda$bindSeatPanelConfigLongPress$57;
        boolean lambda$initCarControlViews$0;
        boolean lambda$initCarControlViews$1;
        boolean lambda$initCarControlViews$2;
        int i7 = this.f5446a;
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5447b;
        switch (i7) {
            case 0:
                lambda$bindSeatPanelConfigLongPress$57 = desktopAssistantCarControlHelper.lambda$bindSeatPanelConfigLongPress$57(view);
                return lambda$bindSeatPanelConfigLongPress$57;
            case 1:
                lambda$initCarControlViews$0 = desktopAssistantCarControlHelper.lambda$initCarControlViews$0(view);
                return lambda$initCarControlViews$0;
            case 2:
                lambda$initCarControlViews$1 = desktopAssistantCarControlHelper.lambda$initCarControlViews$1(view);
                return lambda$initCarControlViews$1;
            default:
                lambda$initCarControlViews$2 = desktopAssistantCarControlHelper.lambda$initCarControlViews$2(view);
                return lambda$initCarControlViews$2;
        }
    }
}
