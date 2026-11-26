package com.navi.tool.service;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4850a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MobileHudBluetoothServer f4851b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f4852c;

    public /* synthetic */ c0(MobileHudBluetoothServer mobileHudBluetoothServer, String str, int i7) {
        this.f4850a = i7;
        this.f4851b = mobileHudBluetoothServer;
        this.f4852c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4850a;
        String str = this.f4852c;
        MobileHudBluetoothServer mobileHudBluetoothServer = this.f4851b;
        switch (i7) {
            case 0:
                mobileHudBluetoothServer.lambda$onSystemDeviceConnected$1(str);
                break;
            case 1:
                mobileHudBluetoothServer.lambda$bootstrapConnectedDevice$3(str);
                break;
            case 2:
                mobileHudBluetoothServer.lambda$readLoop$4(str);
                break;
            case 3:
                mobileHudBluetoothServer.lambda$readLoop$4(str);
                break;
            case 4:
                mobileHudBluetoothServer.lambda$readLoop$4(str);
                break;
            default:
                mobileHudBluetoothServer.lambda$sendText$0(str);
                break;
        }
    }
}
