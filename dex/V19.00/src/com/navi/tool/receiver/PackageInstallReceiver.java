package com.navi.tool.receiver;

import a.a;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.appcompat.app.p0;
import com.navi.tool.NaviApplication;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;
import com.navi.tool.utils.AmapResidualCleaner;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.MapPermissionGrantHelper;
import com.navi.tool.utils.SharedPreferencesUtils;
import e0.u;
import e0.v;
import g0.g;
import g4.s;
import java.io.File;
import o4.w;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class PackageInstallReceiver extends BroadcastReceiver {
    private static final String TAG = "PackageInstallReceiver";
    private static final String[] MAP_PACKAGE_PREFIXES = {AppDetectionUtils.AUTONAVI_PREFIX, "com.baidu.naviauto", AppDetectionUtils.TENCENT_PREFIX, "com.mojoxing.light", AppDetectionUtils.AUTONAVI_EXTRA_PACKAGE};
    private static PackageInstallReceiver sDynamicInstance = null;
    private static String sLastProcessedPkg = null;
    private static long sLastProcessedTime = 0;
    private static volatile boolean sCleaningAmapResidual = false;

    private void cleanAmapResidual(Context context, String str) {
        if (AmapResidualCleaner.isAmapPackage(str)) {
            File dataDir = AmapResidualCleaner.dataDir();
            if (dataDir.exists() && AmapResidualCleaner.canWritePublicStorage() && !sCleaningAmapResidual) {
                sCleaningAmapResidual = true;
                new Thread(new p0(17, dataDir, goAsync()), "amap-residual-clean").start();
            }
        }
    }

    private String getDefaultMapName(String str) {
        return str == null ? "地图" : str.startsWith(AppDetectionUtils.AUTONAVI_PREFIX) ? "高德地图" : str.startsWith("com.baidu.naviauto") ? "百度地图" : str.startsWith(AppDetectionUtils.TENCENT_PREFIX) ? "腾讯地图" : str.startsWith("com.mojoxing.light") ? "红绿灯领航" : "地图";
    }

    private boolean isDuplicate(String str) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (str.equals(sLastProcessedPkg) && elapsedRealtime - sLastProcessedTime < PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS) {
            return true;
        }
        sLastProcessedPkg = str;
        sLastProcessedTime = elapsedRealtime;
        return false;
    }

    private boolean isMapPackage(String str) {
        if (str == null) {
            return false;
        }
        for (String str2 : MAP_PACKAGE_PREFIXES) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001e, code lost:
    
        if (r1 != false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
    
        r5.finish();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0021, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0014, code lost:
    
        r4.exists();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0012, code lost:
    
        if (r1 == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$cleanAmapResidual$2(java.io.File r4, android.content.BroadcastReceiver.PendingResult r5) {
        /*
            r0 = 0
            boolean r1 = com.navi.tool.utils.AmapResidualCleaner.deleteTree(r4)     // Catch: java.lang.Throwable -> L1b
            if (r1 != 0) goto L10
            r2 = 2000(0x7d0, double:9.88E-321)
            java.lang.Thread.sleep(r2)     // Catch: java.lang.Throwable -> L1c
            boolean r1 = com.navi.tool.utils.AmapResidualCleaner.deleteTree(r4)     // Catch: java.lang.Throwable -> L1c
        L10:
            com.navi.tool.receiver.PackageInstallReceiver.sCleaningAmapResidual = r0
            if (r1 != 0) goto L17
        L14:
            r4.exists()
        L17:
            r5.finish()
            goto L21
        L1b:
            r1 = r0
        L1c:
            com.navi.tool.receiver.PackageInstallReceiver.sCleaningAmapResidual = r0
            if (r1 != 0) goto L17
            goto L14
        L21:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.receiver.PackageInstallReceiver.lambda$cleanAmapResidual$2(java.io.File, android.content.BroadcastReceiver$PendingResult):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onReceive$1(Context context, String str, String str2, BroadcastReceiver.PendingResult pendingResult) {
        String str3;
        try {
            try {
                Thread.sleep(500L);
                MapPermissionGrantHelper.GrantResult grantAllPermissions = MapPermissionGrantHelper.grantAllPermissions(context, new ComponentName(context, (Class<?>) NaviDeviceAdminReceiver.class), str);
                if (grantAllPermissions.total > 0) {
                    str3 = "已为「" + str2 + "」自动授予" + grantAllPermissions.granted + "项权限";
                } else {
                    str3 = "「" + str2 + "」无需额外授权";
                }
                new Handler(Looper.getMainLooper()).post(new p0(18, context, str3));
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            pendingResult.finish();
        } catch (Throwable th) {
            pendingResult.finish();
            throw th;
        }
    }

    public static void registerDynamic(Context context) {
        if (sDynamicInstance != null) {
            return;
        }
        sDynamicInstance = new PackageInstallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addDataScheme("package");
        try {
            try {
                g.c(context, sDynamicInstance, intentFilter);
            } catch (Exception unused) {
                context.registerReceiver(sDynamicInstance, intentFilter);
            }
        } catch (Exception unused2) {
            sDynamicInstance = null;
        }
    }

    private void showNotification(Context context, String str, String str2) {
        try {
            PendingIntent activity = PendingIntent.getActivity(context, 0, context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()), 201326592);
            v vVar = new v(context, NaviApplication.CHANNEL_ID);
            vVar.f6341s.icon = s.ic_launcher;
            vVar.f6327e = v.c(str);
            vVar.f6328f = v.c(str2);
            u uVar = new u();
            uVar.f6322b = v.c(str2);
            vVar.e(uVar);
            vVar.f6329g = activity;
            vVar.d(16, true);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify((int) System.currentTimeMillis(), vVar.a());
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        boolean z6 = true;
        boolean z7 = "android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action);
        if (!"android.intent.action.PACKAGE_REMOVED".equals(action) && !"android.intent.action.PACKAGE_CHANGED".equals(action)) {
            z6 = false;
        }
        if ((z7 || z6) && intent.getData() != null) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (AppDetectionUtils.isTargetApp(schemeSpecificPart)) {
                AppDetectionUtils.invalidate();
            }
            if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                if (intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
                    return;
                }
                cleanAmapResidual(context, schemeSpecificPart);
                return;
            }
            if (z7 && !isDuplicate(schemeSpecificPart)) {
                if (SharedPreferencesUtils.restoreUninstalledFloatingConfig(context, schemeSpecificPart)) {
                    try {
                        context.sendBroadcast(new Intent("com.navi.tool.DESKTOP_APPS_UPDATED"));
                        context.sendBroadcast(new Intent("com.navi.tool.ACTION_MAP_APP_UPDATED"));
                        context.sendBroadcast(new Intent(DesktopAssistantMusicUiHelper.ACTION_MUSIC_APP_UPDATED));
                    } catch (Exception e7) {
                        e7.getMessage();
                    }
                }
                if (isMapPackage(schemeSpecificPart)) {
                    String defaultMapName = getDefaultMapName(schemeSpecificPart);
                    try {
                        CharSequence applicationLabel = context.getPackageManager().getApplicationLabel(context.getPackageManager().getApplicationInfo(schemeSpecificPart, 0));
                        if (applicationLabel != null && applicationLabel.length() > 0) {
                            defaultMapName = applicationLabel.toString();
                        }
                    } catch (Exception unused) {
                    }
                    if (MapPermissionGrantHelper.isDeviceOwner(context)) {
                        new Thread(new w(context, schemeSpecificPart, defaultMapName, goAsync())).start();
                    } else {
                        showNotification(context, a.B("已安装", defaultMapName), a.h("已安装「", defaultMapName, "」，但权限助手未激活，无法自动授权"));
                    }
                }
            }
        }
    }
}
