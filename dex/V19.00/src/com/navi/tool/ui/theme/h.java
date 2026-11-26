package com.navi.tool.ui.theme;

import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.lifecycle.h0;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.voice.VoiceNavigationFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class h implements h0, AppAdapter.OnAppToggleListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f5832a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f5833b;

    public /* synthetic */ h(View view, Object obj) {
        this.f5832a = obj;
        this.f5833b = view;
    }

    @Override // com.navi.tool.ui.adapter.AppAdapter.OnAppToggleListener
    public final void onAppToggle(AppInfo appInfo) {
        ((VoiceNavigationFragment) this.f5832a).lambda$showReturnAppSelectionDialog$15((RecyclerView) this.f5833b, appInfo);
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        AutoBrightnessFragment.lambda$onViewCreated$5((SeekBar) this.f5832a, (TextView) this.f5833b, (Integer) obj);
    }
}
