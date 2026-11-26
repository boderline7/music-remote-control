package com.navi.tool.amapflying;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.receiver.Auto3DBroadcastReceiver;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.ToastHelper;
import h0.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4501a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AmapBroadcastService f4502b;

    public /* synthetic */ a(AmapBroadcastService amapBroadcastService, int i7) {
        this.f4501a = i7;
        this.f4502b = amapBroadcastService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4501a;
        int i8 = 1;
        AmapBroadcastService amapBroadcastService = this.f4502b;
        switch (i7) {
            case 0:
                String str = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                Handler handler = new Handler(Looper.getMainLooper());
                for (int i9 = 0; i9 < 10; i9++) {
                    handler.postDelayed(new n(i8, 2, amapBroadcastService), i9 * 1000);
                }
                break;
            case 1:
                String str2 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.d(3);
                break;
            case 2:
                String str3 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                ToastHelper.show(amapBroadcastService, "车机不支持该仪表广播");
                break;
            case 3:
                String str4 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                ToastHelper.show(amapBroadcastService, "发送仪表广播失败");
                break;
            case 4:
                String str5 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                ToastHelper.show(amapBroadcastService, "车机不支持该仪表广播");
                break;
            case 5:
                String str6 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                ToastHelper.show(amapBroadcastService, "发送仪表广播失败");
                break;
            case 6:
                String str7 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.e();
                break;
            case 7:
                String str8 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.d(3);
                break;
            case 8:
                String str9 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.e();
                break;
            case 9:
                String str10 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.b();
                break;
            default:
                String str11 = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                try {
                    Intent intent = new Intent(AmapBroadcastConstants.DEFAULT_AMAP_BROADCAST);
                    intent.putExtra(AmapBroadcastConstants.EXTRA_SELF_BROADCAST, true);
                    intent.putExtra(Auto3DBroadcastReceiver.KEY_TYPE, Auto3DBroadcastReceiver.KEY_TYPE_VALUE);
                    intent.putExtra(Auto3DBroadcastReceiver.EXTRA_STATE, 0);
                    amapBroadcastService.sendBroadcast(intent);
                    break;
                } catch (Throwable unused) {
                    return;
                }
        }
    }
}
