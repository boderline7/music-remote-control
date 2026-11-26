package com.navi.tool.utils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5861a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ConfigSyncManager f5862b;

    public /* synthetic */ g(ConfigSyncManager configSyncManager, int i7) {
        this.f5861a = i7;
        this.f5862b = configSyncManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5861a;
        ConfigSyncManager configSyncManager = this.f5862b;
        switch (i7) {
            case 0:
                configSyncManager.restartApp();
                break;
            case 1:
                configSyncManager.checkPostDownloadPermissions();
                break;
            case 2:
                configSyncManager.lambda$upload$4();
                break;
            case 3:
                configSyncManager.lambda$scheduleUpload$2();
                break;
            default:
                configSyncManager.lambda$scheduleUpload$3();
                break;
        }
    }
}
