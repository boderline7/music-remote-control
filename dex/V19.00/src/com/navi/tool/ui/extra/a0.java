package com.navi.tool.ui.extra;

import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4998a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f4999b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ List f5000c;

    public /* synthetic */ a0(DesktopAssistantActivity desktopAssistantActivity, List list, int i7) {
        this.f4998a = i7;
        this.f4999b = desktopAssistantActivity;
        this.f5000c = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4998a;
        List list = this.f5000c;
        DesktopAssistantActivity desktopAssistantActivity = this.f4999b;
        switch (i7) {
            case 0:
                desktopAssistantActivity.lambda$showAppSelectionDialog$66(list);
                break;
            case 1:
                desktopAssistantActivity.lambda$loadAppList$73(list);
                break;
            default:
                desktopAssistantActivity.lambda$refreshAppListSilently$75(list);
                break;
        }
    }
}
