package com.navi.tool.ui.extra;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class n2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5349a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5350b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f5351c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5352d;

    public /* synthetic */ n2(DesktopAssistantFragment desktopAssistantFragment, Context context, RecyclerView recyclerView, int i7) {
        this.f5349a = i7;
        this.f5350b = desktopAssistantFragment;
        this.f5351c = context;
        this.f5352d = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5349a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5350b;
        RecyclerView recyclerView = this.f5352d;
        Context context = this.f5351c;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$showSetDockAppsDialog$17(context, recyclerView);
                break;
            case 1:
                desktopAssistantFragment.lambda$showSelectSystemAppsDialog$14(context, recyclerView);
                break;
            default:
                desktopAssistantFragment.lambda$showSetDesktopAppsDialog$21(context, recyclerView);
                break;
        }
    }
}
