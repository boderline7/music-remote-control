package com.navi.tool.ui.theme;

import androidx.lifecycle.h0;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements AppAdapter.OnAppToggleListener, h0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5827a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AutoBrightnessFragment f5828b;

    public /* synthetic */ f(AutoBrightnessFragment autoBrightnessFragment, int i7) {
        this.f5827a = i7;
        this.f5828b = autoBrightnessFragment;
    }

    @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
    public final void onAppToggle(AppInfo appInfo) {
        this.f5828b.lambda$onViewCreated$0(appInfo);
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        int i7 = this.f5827a;
        AutoBrightnessFragment autoBrightnessFragment = this.f5828b;
        switch (i7) {
            case 1:
                autoBrightnessFragment.lambda$onViewCreated$2((List) obj);
                break;
            default:
                autoBrightnessFragment.lambda$onViewCreated$6((Integer) obj);
                break;
        }
    }
}
