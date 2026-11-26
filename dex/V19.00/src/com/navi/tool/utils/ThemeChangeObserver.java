package com.navi.tool.utils;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.lifecycle.x;
import com.navi.tool.receiver.ThemeChangeReceiver;
import com.navi.tool.service.ThemeMonitorService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ThemeChangeObserver implements androidx.lifecycle.f {
    private static final String TAG = "ThemeChangeObserver";
    private final Context context;
    private int currentNightMode;
    private boolean enableBackgroundMonitoring;
    private boolean isRegistered;
    private final OnThemeChangeListener listener;
    private final BroadcastReceiver themeChangeReceiver;

    public interface OnThemeChangeListener {
        void onThemeChanged(boolean z6);
    }

    public ThemeChangeObserver(Context context, OnThemeChangeListener onThemeChangeListener) {
        this.isRegistered = false;
        this.enableBackgroundMonitoring = true;
        this.themeChangeReceiver = new BroadcastReceiver() { // from class: com.navi.tool.utils.ThemeChangeObserver.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction())) {
                    ThemeChangeObserver.this.checkThemeChange();
                }
            }
        };
        this.context = context.getApplicationContext();
        this.listener = onThemeChangeListener;
        this.currentNightMode = getCurrentNightMode();
    }

    public ThemeChangeObserver(Context context, OnThemeChangeListener onThemeChangeListener, boolean z6) {
        this(context, onThemeChangeListener);
        this.enableBackgroundMonitoring = z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkThemeChange() {
        int currentNightMode = getCurrentNightMode();
        if (currentNightMode != this.currentNightMode) {
            this.currentNightMode = currentNightMode;
            this.listener.onThemeChanged(currentNightMode == 32);
        }
    }

    private int getCurrentNightMode() {
        UiModeManager uiModeManager = (UiModeManager) this.context.getSystemService("uimode");
        if (uiModeManager == null) {
            return this.context.getResources().getConfiguration().uiMode & 48;
        }
        int nightMode = uiModeManager.getNightMode();
        if (nightMode == 2) {
            return 32;
        }
        return nightMode == 1 ? 16 : 0;
    }

    private void registerReceiver() {
        if (this.isRegistered) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.CONFIGURATION_CHANGED");
        if (Build.VERSION.SDK_INT >= 33) {
            this.context.registerReceiver(this.themeChangeReceiver, intentFilter, 4);
        } else {
            this.context.registerReceiver(this.themeChangeReceiver, intentFilter);
        }
        this.isRegistered = true;
    }

    private void startBackgroundMonitoring() {
        ThemeMonitorService.setThemeChangeListener(new ThemeChangeReceiver.OnThemeChangeListener() { // from class: com.navi.tool.utils.ThemeChangeObserver.2
            @Override // com.navi.tool.receiver.ThemeChangeReceiver.OnThemeChangeListener
            public void onThemeChanged(boolean z6) {
                ThemeChangeObserver.this.listener.onThemeChanged(z6);
            }
        });
        this.context.startForegroundService(new Intent(this.context, (Class<?>) ThemeMonitorService.class));
    }

    private void unregisterReceiver() {
        if (this.isRegistered) {
            this.context.unregisterReceiver(this.themeChangeReceiver);
            this.isRegistered = false;
        }
    }

    public boolean isBackgroundMonitoringEnabled() {
        return this.enableBackgroundMonitoring;
    }

    @Override // androidx.lifecycle.f
    public /* bridge */ /* synthetic */ void onCreate(x xVar) {
        super.onCreate(xVar);
    }

    @Override // androidx.lifecycle.f
    public /* bridge */ /* synthetic */ void onDestroy(x xVar) {
        super.onDestroy(xVar);
    }

    @Override // androidx.lifecycle.f
    public /* bridge */ /* synthetic */ void onPause(x xVar) {
        super.onPause(xVar);
    }

    @Override // androidx.lifecycle.f
    public void onResume(x xVar) {
        i5.a.g("owner", xVar);
        checkThemeChange();
    }

    @Override // androidx.lifecycle.f
    public void onStart(x xVar) {
        super.onStart(xVar);
        registerReceiver();
        if (this.enableBackgroundMonitoring) {
            startBackgroundMonitoring();
        }
    }

    @Override // androidx.lifecycle.f
    public void onStop(x xVar) {
        super.onStop(xVar);
        unregisterReceiver();
    }

    public void setBackgroundMonitoringEnabled(boolean z6) {
        this.enableBackgroundMonitoring = z6;
    }

    public void stopBackgroundMonitoring() {
        this.context.stopService(new Intent(this.context, (Class<?>) ThemeMonitorService.class));
    }
}
