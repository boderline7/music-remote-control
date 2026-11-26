package com.navi.tool.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.accessibility.AccessibilityEvent;
import com.navi.tool.utils.AccessibilityGuard;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class GestureAccessibilityService extends AccessibilityService {
    private static GestureAccessibilityService instance;

    public static boolean isConnected() {
        return instance != null;
    }

    public static boolean isEnabled(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        if (string == null || string.isEmpty()) {
            return false;
        }
        String packageName = context.getPackageName();
        String name = GestureAccessibilityService.class.getName();
        String str = packageName + "/" + name;
        String[] split = packageName.split("\\.");
        String[] split2 = name.split("\\.");
        int i7 = 0;
        while (i7 < split.length && i7 < split2.length && split[i7].equals(split2[i7])) {
            i7++;
        }
        StringBuilder sb = new StringBuilder();
        while (i7 < split2.length) {
            sb.append('.');
            sb.append(split2[i7]);
            i7++;
        }
        String str2 = packageName + "/" + ((Object) sb);
        String lowerCase = string.toLowerCase();
        return lowerCase.equalsIgnoreCase(str) || lowerCase.contains(str.toLowerCase()) || lowerCase.contains(str2.toLowerCase());
    }

    public static void performBack() {
        GestureAccessibilityService gestureAccessibilityService = instance;
        if (gestureAccessibilityService != null) {
            gestureAccessibilityService.performGlobalAction(1);
        } else {
            AccessibilityGuard.rebindIfNeeded();
        }
    }

    public static void performHome() {
        GestureAccessibilityService gestureAccessibilityService = instance;
        if (gestureAccessibilityService != null) {
            gestureAccessibilityService.performGlobalAction(2);
        } else {
            AccessibilityGuard.rebindIfNeeded();
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
        GestureActionService.ensureRunning(this);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        instance = null;
        try {
            Intent intent2 = new Intent("com.navi.tool.ACTION_REPAIR_ACCESSIBILITY");
            intent2.setPackage(getPackageName());
            sendBroadcast(intent2);
        } catch (Throwable unused) {
        }
        return super.onUnbind(intent);
    }
}
