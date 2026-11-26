package com.navi.tool.service;

import com.navi.tool.service.MobileHudBluetoothServer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class e0 implements androidx.lifecycle.h0, MobileHudBluetoothServer.MessageListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MobileHudService f4860a;

    public /* synthetic */ e0(MobileHudService mobileHudService) {
        this.f4860a = mobileHudService;
    }

    @Override // androidx.lifecycle.h0
    public final void onChanged(Object obj) {
        this.f4860a.lambda$new$0((String) obj);
    }

    @Override // com.navi.tool.service.MobileHudBluetoothServer.MessageListener
    public final void onMessageReceived(String str) {
        this.f4860a.lambda$onCreate$1(str);
    }
}
