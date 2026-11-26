package com.navi.tool.ui.extra;

import androidx.activity.result.ActivityResult;
import com.navi.tool.ui.adapter.CarSettingAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements d.a, CarSettingAdapter.OnSettingChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AdditionalFeaturesFragment f5148a;

    public /* synthetic */ f(AdditionalFeaturesFragment additionalFeaturesFragment) {
        this.f5148a = additionalFeaturesFragment;
    }

    @Override // d.a
    public final void a(Object obj) {
        this.f5148a.lambda$initIconChooser$44((ActivityResult) obj);
    }

    @Override // com.navi.tool.ui.adapter.CarSettingAdapter.OnSettingChangeListener
    public final void onSettingChanged() {
        this.f5148a.updateCarSettingsCount();
    }
}
