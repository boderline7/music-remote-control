package com.navi.tool.ui.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CompoundButton;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AutoStartAppAdapter;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4961a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AppInfo f4962b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4963c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f4964d;

    public /* synthetic */ e(Object obj, AppInfo appInfo, Object obj2, int i7) {
        this.f4961a = i7;
        this.f4963c = obj;
        this.f4962b = appInfo;
        this.f4964d = obj2;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f4961a;
        AppInfo appInfo = this.f4962b;
        Object obj = this.f4964d;
        Object obj2 = this.f4963c;
        switch (i7) {
            case 0:
                ((AutoStartAppAdapter) obj2).lambda$onBindViewHolder$0(appInfo, (AutoStartAppAdapter.ViewHolder) obj, compoundButton, z6);
                break;
            default:
                PanelAppSettingsAdapter.ViewHolder.lambda$bind$3((SharedPreferences) obj2, appInfo, (Context) obj, compoundButton, z6);
                break;
        }
    }
}
