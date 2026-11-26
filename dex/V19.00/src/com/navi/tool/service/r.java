package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class r implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4914a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ LogLinkageManager f4915b;

    public /* synthetic */ r(LogLinkageManager logLinkageManager, int i7) {
        this.f4914a = i7;
        this.f4915b = logLinkageManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4914a;
        LogLinkageManager logLinkageManager = this.f4915b;
        switch (i7) {
            case 0:
                logLinkageManager.lambda$reload$1();
                break;
            case 1:
                logLinkageManager.lambda$triggerAutoFavorite$7();
                break;
            default:
                logLinkageManager.lambda$new$0();
                break;
        }
    }
}
