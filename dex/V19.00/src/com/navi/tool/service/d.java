package com.navi.tool.service;

import com.desaysv.ivi.vdb.event.VDEvent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4853a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g2.c f4854b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ VDEvent f4855c;

    public /* synthetic */ d(g2.c cVar, VDEvent vDEvent, int i7) {
        this.f4853a = i7;
        this.f4854b = cVar;
        this.f4855c = vDEvent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4853a;
        VDEvent vDEvent = this.f4855c;
        g2.c cVar = this.f4854b;
        switch (i7) {
            case 0:
                CarLanDashboardService.lambda$sendPlayStatus$3(cVar, vDEvent);
                break;
            default:
                CarLanDashboardService.lambda$sendId3Data$2(cVar, vDEvent);
                break;
        }
    }
}
