package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g2 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5174a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5175b;

    public /* synthetic */ g2(DesktopAssistantFragment desktopAssistantFragment, int i7) {
        this.f5174a = i7;
        this.f5175b = desktopAssistantFragment;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5174a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5175b;
        switch (i7) {
            case 0:
                desktopAssistantFragment.processNextVideoWallpaper();
                break;
            default:
                desktopAssistantFragment.processNextWallpaper();
                break;
        }
    }
}
