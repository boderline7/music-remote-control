package com.navi.tool.utils;

import android.content.Context;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class o implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5879a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f5880b;

    public /* synthetic */ o(Context context, int i7) {
        this.f5879a = i7;
        this.f5880b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5879a;
        Context context = this.f5880b;
        switch (i7) {
            case 0:
                ToastHelper.showLong(context, "应用成功！");
                break;
            case 1:
                AnrWatchdog.retryPending(context);
                break;
            case 2:
                AnrWatchdog.loop(context);
                break;
            case 3:
                AppDetectionUtils.lambda$prewarm$0(context);
                break;
            case 4:
                CarSettingsUtils.setWifiEnabled(context, true);
                break;
            case 5:
                CarSettingsUtils.setHotspotEnabled(context, true);
                break;
            default:
                CarSettingsUtils.setBluetoothEnabled(context, true);
                break;
        }
    }
}
