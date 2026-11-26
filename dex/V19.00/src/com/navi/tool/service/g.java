package com.navi.tool.service;

import android.content.ComponentCallbacks;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.voice.VoiceNavigationFragment;
import com.navi.tool.utils.Pan123Api;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g implements Pan123Api.UploadCallback, AppAdapter.OnAppToggleListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ComponentCallbacks f4864a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4865b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4866c;

    public /* synthetic */ g(ComponentCallbacks componentCallbacks, Object obj, Object obj2) {
        this.f4864a = componentCallbacks;
        this.f4865b = obj;
        this.f4866c = obj2;
    }

    @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
    public final void onAppToggle(AppInfo appInfo) {
        ((VoiceNavigationFragment) this.f4864a).lambda$showNativeAmapSelectionDialog$11((List) this.f4865b, (RecyclerView) this.f4866c, appInfo);
    }

    @Override // com.navi.tool.utils.Pan123Api.UploadCallback
    public final void onProgress(int i7) {
        ((ConfigBackupService) this.f4864a).lambda$doBackup$3((int[]) this.f4865b, (String) this.f4866c, i7);
    }
}
