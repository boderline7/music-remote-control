package com.navi.tool.service;

import com.navi.tool.service.MobileHudService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4862a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MobileHudService.AnonymousClass4 f4863b;

    public /* synthetic */ f0(MobileHudService.AnonymousClass4 anonymousClass4, int i7) {
        this.f4862a = i7;
        this.f4863b = anonymousClass4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4862a;
        MobileHudService.AnonymousClass4 anonymousClass4 = this.f4863b;
        switch (i7) {
            case 0:
                anonymousClass4.lambda$onClientConnected$0();
                break;
            default:
                anonymousClass4.lambda$onClientDisconnected$1();
                break;
        }
    }
}
