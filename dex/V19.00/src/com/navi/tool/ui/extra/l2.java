package com.navi.tool.ui.extra;

import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l2 implements AppAdapter.OnAppToggleListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5302a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5303b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5304c;

    public /* synthetic */ l2(DesktopAssistantFragment desktopAssistantFragment, RecyclerView recyclerView, int i7) {
        this.f5302a = i7;
        this.f5303b = desktopAssistantFragment;
        this.f5304c = recyclerView;
    }

    @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
    public final void onAppToggle(AppInfo appInfo) {
        int i7 = this.f5302a;
        RecyclerView recyclerView = this.f5304c;
        DesktopAssistantFragment desktopAssistantFragment = this.f5303b;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$showSelectTopInfoDialog$23(recyclerView, appInfo);
                break;
            default:
                desktopAssistantFragment.lambda$showSelectSystemAppsDialog$12(recyclerView, appInfo);
                break;
        }
    }
}
