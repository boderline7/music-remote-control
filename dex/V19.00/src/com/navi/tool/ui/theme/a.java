package com.navi.tool.ui.theme;

import androidx.lifecycle.h0;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements AppAdapter.OnAppToggleListener, h0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Auto3DFragment f5817a;

    public /* synthetic */ a(Auto3DFragment auto3DFragment) {
        this.f5817a = auto3DFragment;
    }

    @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
    public final void onAppToggle(AppInfo appInfo) {
        this.f5817a.lambda$onViewCreated$0(appInfo);
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        this.f5817a.lambda$onViewCreated$8((List) obj);
    }
}
