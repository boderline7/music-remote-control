package com.navi.tool.ui.extra;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.navi.tool.model.AppInfo;
import com.navi.tool.ui.adapter.DockAppAdapter;
import com.navi.tool.ui.adapter.SelectAppAdapter;
import com.navi.tool.ui.extra.InstalledAppAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c0 implements InstalledAppAdapter.OnAppClickListener, SelectAppAdapter.OnAppClickListener, DockAppAdapter.OnAppClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5061a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5062b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f5063c;

    public /* synthetic */ c0(DesktopAssistantActivity desktopAssistantActivity, Object obj, int i7) {
        this.f5061a = i7;
        this.f5062b = desktopAssistantActivity;
        this.f5063c = obj;
    }

    @Override // com.navi.tool.ui.extra.InstalledAppAdapter.OnAppClickListener
    public final void onAppClick(ApplicationInfo applicationInfo) {
        this.f5062b.lambda$showAppSelectionDialogWith$69((androidx.appcompat.app.o) this.f5063c, applicationInfo);
    }

    @Override // com.navi.tool.ui.adapter.SelectAppAdapter.OnAppClickListener, com.navi.tool.ui.adapter.DockAppAdapter.OnAppClickListener
    public final void onAppClick(AppInfo appInfo) {
        int i7 = this.f5061a;
        DesktopAssistantActivity desktopAssistantActivity = this.f5062b;
        Object obj = this.f5063c;
        switch (i7) {
            case 1:
                desktopAssistantActivity.lambda$showCustomMusicAppSelectionDialog$38((androidx.appcompat.app.o[]) obj, appInfo);
                break;
            default:
                desktopAssistantActivity.lambda$loadDockApps$49((PackageManager) obj, appInfo);
                break;
        }
    }
}
