package com.navi.tool.service;

import android.app.Service;
import android.os.IBinder;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b implements IBinder.DeathRecipient {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4844a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Service f4845b;

    public /* synthetic */ b(Service service, int i7) {
        this.f4844a = i7;
        this.f4845b = service;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        int i7 = this.f4844a;
        Service service = this.f4845b;
        switch (i7) {
            case 0:
                ((CarLanDashboardService) service).lambda$new$0();
                break;
            default:
                ((LionMediaBridgeService) service).lambda$new$0();
                break;
        }
    }
}
