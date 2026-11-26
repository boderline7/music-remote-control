package com.navi.tool.ui.extra;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5118a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AdditionalFeaturesFragment f5119b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5120c;

    public /* synthetic */ e(AdditionalFeaturesFragment additionalFeaturesFragment, RecyclerView recyclerView, int i7) {
        this.f5118a = i7;
        this.f5119b = additionalFeaturesFragment;
        this.f5120c = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5118a;
        RecyclerView recyclerView = this.f5120c;
        AdditionalFeaturesFragment additionalFeaturesFragment = this.f5119b;
        switch (i7) {
            case 0:
                additionalFeaturesFragment.lambda$showScreenOnAutoStartAppsDialog$42(recyclerView);
                break;
            default:
                additionalFeaturesFragment.lambda$loadAutoStartApps$39(recyclerView);
                break;
        }
    }
}
