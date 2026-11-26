package com.navi.tool.ui.extra;

import android.content.DialogInterface;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i implements DialogInterface.OnDismissListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5218a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AdditionalFeaturesFragment f5219b;

    public /* synthetic */ i(AdditionalFeaturesFragment additionalFeaturesFragment, int i7) {
        this.f5218a = i7;
        this.f5219b = additionalFeaturesFragment;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i7 = this.f5218a;
        AdditionalFeaturesFragment additionalFeaturesFragment = this.f5219b;
        switch (i7) {
            case 0:
                additionalFeaturesFragment.lambda$showAutoStartAppsDialog$36(dialogInterface);
                break;
            case 1:
                additionalFeaturesFragment.lambda$showCarSettingsDialog$43(dialogInterface);
                break;
            default:
                additionalFeaturesFragment.lambda$showSelectPanelAppsDialog$29(dialogInterface);
                break;
        }
    }
}
