package com.navi.tool.ui.extra;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5292a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AdditionalFeaturesFragment f5293b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f5294c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5295d;

    public /* synthetic */ l(AdditionalFeaturesFragment additionalFeaturesFragment, ArrayList arrayList, RecyclerView recyclerView, int i7) {
        this.f5292a = i7;
        this.f5293b = additionalFeaturesFragment;
        this.f5294c = arrayList;
        this.f5295d = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5292a;
        AdditionalFeaturesFragment additionalFeaturesFragment = this.f5293b;
        RecyclerView recyclerView = this.f5295d;
        List list = this.f5294c;
        switch (i7) {
            case 0:
                additionalFeaturesFragment.lambda$showScreenOnAutoStartAppsDialog$41(list, recyclerView);
                break;
            default:
                additionalFeaturesFragment.lambda$loadAutoStartApps$38(list, recyclerView);
                break;
        }
    }
}
