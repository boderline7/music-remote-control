package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b2 implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5039a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5040b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ AppInfo f5041c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ PanelAppSettingsAdapter f5042d;

    public /* synthetic */ b2(DesktopAssistantFragment desktopAssistantFragment, AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, int i7) {
        this.f5039a = i7;
        this.f5040b = desktopAssistantFragment;
        this.f5041c = appInfo;
        this.f5042d = panelAppSettingsAdapter;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        int i8 = this.f5039a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5040b;
        PanelAppSettingsAdapter panelAppSettingsAdapter = this.f5042d;
        AppInfo appInfo = this.f5041c;
        switch (i8) {
            case 0:
                desktopAssistantFragment.lambda$showChangeTopInfoNameDialog$30(appInfo, panelAppSettingsAdapter, dialogInterface, i7);
                break;
            default:
                desktopAssistantFragment.lambda$showChangeNameDialog$9(appInfo, panelAppSettingsAdapter, dialogInterface, i7);
                break;
        }
    }
}
