package com.navi.tool.ui.extra;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class u0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5481a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantActivity f5482b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f5483c;

    public /* synthetic */ u0(DesktopAssistantActivity desktopAssistantActivity, int i7, int i8) {
        this.f5481a = i8;
        this.f5482b = desktopAssistantActivity;
        this.f5483c = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5481a;
        int i8 = this.f5483c;
        DesktopAssistantActivity desktopAssistantActivity = this.f5482b;
        switch (i7) {
            case 0:
                desktopAssistantActivity.lambda$scheduleVideoWatchdog$92(i8);
                break;
            case 1:
                desktopAssistantActivity.lambda$new$1(i8);
                break;
            case 2:
                desktopAssistantActivity.lambda$refreshAppListSilently$76(i8);
                break;
            default:
                desktopAssistantActivity.lambda$loadWallpaper$84(i8);
                break;
        }
    }
}
