package com.google.android.material.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.s0;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.service.LogMonitorService;
import com.navi.tool.service.MusicIslandService;
import com.navi.tool.service.ThemeMonitorService;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.NavigationStatusSimulatorService;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import p0.d1;
import p0.s2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class g0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3862a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f3863b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f3864c;

    public /* synthetic */ g0(Object obj, boolean z6, int i7) {
        this.f3862a = i7;
        this.f3864c = obj;
        this.f3863b = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s2 h7;
        int i7 = this.f3862a;
        boolean z6 = this.f3863b;
        Object obj = this.f3864c;
        switch (i7) {
            case 0:
                View view = (View) obj;
                if (z6 && (h7 = d1.h(view)) != null) {
                    h7.f8190a.J();
                    break;
                } else {
                    Context context = view.getContext();
                    Object obj2 = g0.g.f6423a;
                    ((InputMethodManager) g0.d.b(context, InputMethodManager.class)).showSoftInput(view, 1);
                    break;
                }
            case 1:
                AmapBroadcastService amapBroadcastService = (AmapBroadcastService) obj;
                String str = AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION;
                amapBroadcastService.getClass();
                if (SharedPreferencesUtils.isCustomAmapBroadcastEnabled(amapBroadcastService) != z6) {
                    SharedPreferencesUtils.setCustomAmapBroadcastEnabled(amapBroadcastService, z6);
                    if (z6) {
                        AmapBroadcastConstants.setAmapBroadcast(SharedPreferencesUtils.getCustomAmapBroadcastValue(amapBroadcastService));
                        SharedPreferencesUtils.setClusterCrossMaskEnabled(amapBroadcastService, true);
                        NavigationStatusSimulatorService.startService(amapBroadcastService);
                        ToastHelper.show(amapBroadcastService, "检测到自定义高德广播，已开启自定义广播与仪表路口图");
                    } else {
                        AmapBroadcastConstants.resetAmapBroadcast();
                        SharedPreferencesUtils.setClusterCrossMaskEnabled(amapBroadcastService, false);
                        NavigationStatusSimulatorService.stopService(amapBroadcastService);
                        ToastHelper.show(amapBroadcastService, "检测到标准高德广播，已关闭自定义广播");
                    }
                    Intent intent = new Intent(amapBroadcastService, (Class<?>) AmapBroadcastService.class);
                    Intent intent2 = new Intent(amapBroadcastService, (Class<?>) ThemeMonitorService.class);
                    amapBroadcastService.stopService(intent);
                    amapBroadcastService.stopService(intent2);
                    new Handler(Looper.getMainLooper()).postDelayed(new y0.n(amapBroadcastService, intent, intent2, 2), 2000L);
                    break;
                }
                break;
            case 2:
                TirePollingService tirePollingService = (TirePollingService) obj;
                if (!z6) {
                    AtomicBoolean atomicBoolean = TirePollingService.f4725g;
                    tirePollingService.stopSelf();
                    break;
                } else {
                    Handler handler = tirePollingService.f4730a;
                    s0 s0Var = tirePollingService.f4732c;
                    handler.removeCallbacks(s0Var);
                    handler.post(s0Var);
                    break;
                }
            case 3:
                ((LogMonitorService) obj).lambda$onCreate$1(z6);
                break;
            default:
                ((MusicIslandService) obj).lambda$loadCachedMusicSnapshot$8(z6);
                break;
        }
    }
}
