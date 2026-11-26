package com.navi.tool.ui.adapter;

import android.widget.CompoundButton;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4955a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AppInfo f4956b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4957c;

    public /* synthetic */ c(Object obj, AppInfo appInfo, int i7) {
        this.f4955a = i7;
        this.f4957c = obj;
        this.f4956b = appInfo;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f4955a;
        AppInfo appInfo = this.f4956b;
        Object obj = this.f4957c;
        switch (i7) {
            case 0:
                AppAdapter.AppViewHolder.lambda$bind$2((AppAdapter.OnAppToggleListener) obj, appInfo, compoundButton, z6);
                break;
            case 1:
                ((CarSettingAdapter) obj).lambda$onBindViewHolder$0(appInfo, compoundButton, z6);
                break;
            default:
                ((ScreenOnAutoStartAppAdapter) obj).lambda$onBindViewHolder$0(appInfo, compoundButton, z6);
                break;
        }
    }
}
