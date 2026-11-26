package com.navi.tool.ui.adapter;

import android.view.View;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.adapter.DockAppAdapter;
import com.navi.tool.ui.adapter.SelectAppAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4958a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AppInfo f4959b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4960c;

    public /* synthetic */ d(Object obj, AppInfo appInfo, int i7) {
        this.f4958a = i7;
        this.f4960c = obj;
        this.f4959b = appInfo;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f4958a;
        AppInfo appInfo = this.f4959b;
        Object obj = this.f4960c;
        switch (i7) {
            case 0:
                AppAdapter.AppViewHolder.lambda$bind$3((AppAdapter.OnAppToggleListener) obj, appInfo, view);
                break;
            case 1:
                DockAppAdapter.ViewHolder.lambda$bind$0((DockAppAdapter.OnAppClickListener) obj, appInfo, view);
                break;
            default:
                SelectAppAdapter.ViewHolder.lambda$bind$0((SelectAppAdapter.OnAppClickListener) obj, appInfo, view);
                break;
        }
    }
}
