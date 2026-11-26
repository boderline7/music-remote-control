package com.navi.tool.ui.extra;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5067a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5068b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f5069c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5070d;

    public /* synthetic */ c2(DesktopAssistantFragment desktopAssistantFragment, RecyclerView recyclerView, ArrayList arrayList, int i7) {
        this.f5067a = i7;
        this.f5068b = desktopAssistantFragment;
        this.f5070d = recyclerView;
        this.f5069c = arrayList;
    }

    public /* synthetic */ c2(DesktopAssistantFragment desktopAssistantFragment, List list, RecyclerView recyclerView, int i7) {
        this.f5067a = i7;
        this.f5068b = desktopAssistantFragment;
        this.f5069c = list;
        this.f5070d = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5067a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5068b;
        List list = this.f5069c;
        RecyclerView recyclerView = this.f5070d;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$showSelectTopInfoDialog$24(recyclerView, list);
                break;
            case 1:
                desktopAssistantFragment.lambda$showAdjustTopInfoDialog$27(list, recyclerView);
                break;
            case 2:
                desktopAssistantFragment.lambda$showSetDockAppsDialog$16(list, recyclerView);
                break;
            case 3:
                desktopAssistantFragment.lambda$showSetDesktopAppsDialog$20(list, recyclerView);
                break;
            default:
                desktopAssistantFragment.lambda$showSelectSystemAppsDialog$13(recyclerView, list);
                break;
        }
    }
}
