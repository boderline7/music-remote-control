package com.navi.tool.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import com.navi.tool.service.MobileHudService;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarSettingsUtils {
    private static final String TAG = "CarSettingsUtils";

    public static void applyCarSettings(Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        long j7 = 0;
        if (SharedPreferencesUtils.isCarSettingAutoStartEnabled(context, MobileHudService.CONNECTION_MODE_WIFI)) {
            handler.postDelayed(new o(context, 4), 0L);
            j7 = 2000;
        }
        if (SharedPreferencesUtils.isCarSettingAutoStartEnabled(context, "hotspot")) {
            handler.postDelayed(new o(context, 5), j7);
            j7 += 2000;
        }
        if (SharedPreferencesUtils.isCarSettingAutoStartEnabled(context, MobileHudService.CONNECTION_MODE_BLUETOOTH)) {
            handler.postDelayed(new o(context, 6), j7);
        }
    }

    @SuppressLint({"MissingPermission"})
    public static void setBluetoothEnabled(Context context, boolean z6) {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                if (z6 && !defaultAdapter.isEnabled()) {
                    defaultAdapter.enable();
                } else if (!z6 && defaultAdapter.isEnabled()) {
                    defaultAdapter.disable();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void setHotspotEnabled(Context context, boolean z6) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (!z6) {
                try {
                    connectivityManager.getClass().getDeclaredMethod("stopTethering", Integer.TYPE).invoke(connectivityManager, 0);
                    return;
                } catch (Exception unused) {
                    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(MobileHudService.CONNECTION_MODE_WIFI);
                    wifiManager.getClass().getDeclaredMethod("stopSoftAp", new Class[0]).invoke(wifiManager, new Object[0]);
                    return;
                }
            }
            String property = System.getProperty("dexmaker.dexcache");
            try {
                File cacheDir = context.getCacheDir();
                System.setProperty("dexmaker.dexcache", cacheDir.getAbsolutePath());
                cacheDir.getAbsolutePath();
                Class<?> cls = Class.forName("android.net.ConnectivityManager$OnStartTetheringCallback");
                d2.c cVar = new d2.c(cls);
                File file = new File(cacheDir, a2.m.PREFIX + Integer.toString(1));
                cVar.f6276d = file;
                file.mkdir();
                cVar.f6275c = new InvocationHandler() { // from class: com.navi.tool.utils.CarSettingsUtils.1
                    @Override // java.lang.reflect.InvocationHandler
                    public Object invoke(Object obj, Method method, Object[] objArr) {
                        method.getName();
                        return null;
                    }
                };
                Object a7 = cVar.a();
                try {
                    connectivityManager.getClass().getDeclaredMethod("startTethering", Integer.TYPE, Boolean.TYPE, cls).invoke(connectivityManager, 0, Boolean.FALSE, a7);
                } catch (NoSuchMethodException unused2) {
                    connectivityManager.getClass().getDeclaredMethod("startTethering", Integer.TYPE, Boolean.TYPE, cls, Handler.class).invoke(connectivityManager, 0, Boolean.FALSE, a7, new Handler(Looper.getMainLooper()));
                }
                if (property != null) {
                    System.setProperty("dexmaker.dexcache", property);
                } else {
                    System.clearProperty("dexmaker.dexcache");
                }
            } catch (Throwable th) {
                if (property != null) {
                    System.setProperty("dexmaker.dexcache", property);
                } else {
                    System.clearProperty("dexmaker.dexcache");
                }
                throw th;
            }
        } catch (Exception unused3) {
        }
    }

    public static void setWifiEnabled(Context context, boolean z6) {
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(MobileHudService.CONNECTION_MODE_WIFI);
            if (wifiManager != null) {
                wifiManager.setWifiEnabled(z6);
            }
        } catch (Exception unused) {
        }
    }
}
