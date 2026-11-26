package com.navi.tool.ui.extra;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class p2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5393a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5394b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5395c;

    public /* synthetic */ p2(DesktopAssistantFragment desktopAssistantFragment, RecyclerView recyclerView, int i7) {
        this.f5393a = i7;
        this.f5394b = desktopAssistantFragment;
        this.f5395c = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5393a;
        RecyclerView recyclerView = this.f5395c;
        DesktopAssistantFragment desktopAssistantFragment = this.f5394b;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$showAdjustTopInfoDialog$28(recyclerView);
                break;
            default:
                desktopAssistantFragment.lambda$showSelectTopInfoDialog$25(recyclerView);
                break;
        }
    }
}
