package com.navi.tool.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.navi.tool.model.AppInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AppDetectionUtils {
    public static final String AUTONAVI_EXCLUDE = "com.autonavi.amapauto.test";
    public static final String AUTONAVI_EXTRA_PACKAGE = "com.byd.launchermap";
    public static final String AUTONAVI_PREFIX = "com.autonavi.amap";
    public static final String BAIDU_PREFIX = "com.baidu.navi";
    private static final int FLOATING_MAP_DISPLAY_MAP = 0;
    private static final String PREF_FLOATING_MAP_ACTIVE_INDEX = "desktop_map_active_index";
    private static final String PREF_FLOATING_MAP_DISPLAY_MODE = "desktop_map_display_mode";
    private static final String PREF_FLOATING_MAP_PKG_1 = "desktop_map_package";
    private static final String PREF_FLOATING_MAP_PKG_2 = "desktop_map_package_2";
    private static final Object SNAPSHOT_LOCK = new Object();
    public static final String TENCENT_PREFIX = "com.tencent.wecarnavi";
    private static volatile boolean sScanning;
    private static volatile List<PackageInfo> sSnapshot;

    public static String getActiveFloatingMapPackage(SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            return null;
        }
        String string = sharedPreferences.getString(PREF_FLOATING_MAP_PKG_1, null);
        String string2 = sharedPreferences.getString(PREF_FLOATING_MAP_PKG_2, null);
        return (string2 == null || string2.isEmpty()) ? string : sharedPreferences.getInt(PREF_FLOATING_MAP_ACTIVE_INDEX, 0) == 1 ? string2 : (string == null || string.isEmpty()) ? string2 : string;
    }

    public static Drawable getAppIcon(Context context, String str) {
        Drawable customAppIcon = getCustomAppIcon(context, str);
        if (customAppIcon != null) {
            return customAppIcon;
        }
        if ("com.navi.tool.fileassistant".equals(str)) {
            int i7 = g4.o.ic_folder_yellow;
            Object obj = g0.g.f6423a;
            return g0.c.b(context, i7);
        }
        if ("com.navi.tool.permissionassistant".equals(str)) {
            int i8 = g4.o.ic_security;
            Object obj2 = g0.g.f6423a;
            return g0.c.b(context, i8);
        }
        if ("com.navi.tool.desktopassistant".equals(str)) {
            int i9 = g4.o.ic_dashboard;
            Object obj3 = g0.g.f6423a;
            return g0.c.b(context, i9);
        }
        if ("com.navi.tool.originalhome".equals(str)) {
            int i10 = g4.o.ic_original_home;
            Object obj4 = g0.g.f6423a;
            return g0.c.b(context, i10);
        }
        if ("com.navi.tool.action.restart".equals(str)) {
            int i11 = g4.o.ic_restart_desktop;
            Object obj5 = g0.g.f6423a;
            return g0.c.b(context, i11);
        }
        if ("com.navi.tool.action.adbswitch".equals(str)) {
            int i12 = g4.o.ic_adb_switch;
            Object obj6 = g0.g.f6423a;
            return g0.c.b(context, i12);
        }
        if ("com.navi.tool.thememode".equals(str)) {
            int i13 = g4.o.ic_theme;
            Object obj7 = g0.g.f6423a;
            return g0.c.b(context, i13);
        }
        if ("com.navi.tool.floatingmapmode".equals(str)) {
            Drawable floatingMapModeIcon = getFloatingMapModeIcon(context, SharedPreferencesUtils.getAppPrefs(context));
            if (floatingMapModeIcon != null) {
                return floatingMapModeIcon;
            }
            int i14 = g4.o.ic_floating_map_mode;
            Object obj8 = g0.g.f6423a;
            return g0.c.b(context, i14);
        }
        if ("com.navi.tool.wallpapermode".equals(str)) {
            int i15 = g4.o.ic_wallpaper_dynamic_mode;
            Object obj9 = g0.g.f6423a;
            return g0.c.b(context, i15);
        }
        try {
            return context.getPackageManager().getApplicationIcon(str);
        } catch (PackageManager.NameNotFoundException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    private static String getAppLabel(Context context, String str) {
        if (context != null && str != null && !str.isEmpty()) {
            try {
                PackageManager packageManager = context.getPackageManager();
                return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static int getAppType(String str) {
        if (isAmapPackage(str)) {
            return 1;
        }
        if (str.startsWith(BAIDU_PREFIX)) {
            return 2;
        }
        return str.startsWith(TENCENT_PREFIX) ? 3 : 4;
    }

    public static List<AppInfo> getAutoNaviApps(Context context) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo packageInfo : getSnapshot(context)) {
            String str = packageInfo.packageName;
            if (isAmapPackage(str)) {
                try {
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    if (isUserInstalled(applicationInfo)) {
                        arrayList.add(new AppInfo(str, packageManager.getApplicationLabel(applicationInfo).toString(), false));
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static List<String> getAutoNaviPackages(Context context) {
        ArrayList arrayList = new ArrayList();
        context.getPackageManager();
        for (PackageInfo packageInfo : getSnapshot(context)) {
            String str = packageInfo.packageName;
            if (isAmapPackage(str) && isUserInstalled(packageInfo.applicationInfo)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static Drawable getCustomAppIcon(Context context, String str) {
        try {
            String string = SharedPreferencesUtils.getAppPrefs(context).getString("gesture_panel_app_icon_" + str, null);
            if (string != null && !string.isEmpty()) {
                Drawable createFromPath = Drawable.createFromPath(string);
                if (createFromPath != null) {
                    return createFromPath;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static Drawable getFloatingMapModeIcon(Context context, SharedPreferences sharedPreferences) {
        String string;
        if (context == null || sharedPreferences == null || (string = sharedPreferences.getString(PREF_FLOATING_MAP_PKG_2, null)) == null || string.isEmpty()) {
            return null;
        }
        Drawable rawAppIcon = getRawAppIcon(context, getActiveFloatingMapPackage(sharedPreferences));
        if (rawAppIcon != null) {
            return rawAppIcon;
        }
        int i7 = g4.o.ic_floating_map_mode;
        Object obj = g0.g.f6423a;
        return g0.c.b(context, i7);
    }

    public static String getFloatingMapModeLabel(Context context, SharedPreferences sharedPreferences) {
        if (sharedPreferences == null) {
            return "悬浮地图";
        }
        String string = sharedPreferences.getString(PREF_FLOATING_MAP_PKG_2, null);
        if (string == null || string.isEmpty()) {
            return sharedPreferences.getInt(PREF_FLOATING_MAP_DISPLAY_MODE, 0) == 0 ? "悬浮地图" : "悬浮日历";
        }
        String appLabel = getAppLabel(context, getActiveFloatingMapPackage(sharedPreferences));
        return appLabel != null ? appLabel : "悬浮地图";
    }

    public static List<AppInfo> getInstalledNavigationApps(Context context) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        for (PackageInfo packageInfo : getSnapshot(context)) {
            String str = packageInfo.packageName;
            if (isTargetApp(str)) {
                try {
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    if (!isAmapPackage(str) || isUserInstalled(applicationInfo)) {
                        arrayList.add(new AppInfo(str, packageManager.getApplicationLabel(applicationInfo).toString(), false));
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
        }
        arrayList.sort(new m0.a(9));
        return arrayList;
    }

    public static List<String> getInstalledNavigationPackages(Context context) {
        ArrayList arrayList = new ArrayList();
        context.getPackageManager();
        Iterator<PackageInfo> it = getSnapshot(context).iterator();
        while (it.hasNext()) {
            String str = it.next().packageName;
            if (isTargetApp(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static List<PackageInfo> getInstalledPackagesSafe(PackageManager packageManager) {
        try {
            return packageManager.getInstalledPackages(0);
        } catch (Exception e7) {
            e7.printStackTrace();
            return new ArrayList();
        }
    }

    private static Drawable getRawAppIcon(Context context, String str) {
        if (context != null && str != null && !str.isEmpty()) {
            try {
                return context.getPackageManager().getApplicationIcon(str);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private static List<PackageInfo> getSnapshot(Context context) {
        List<PackageInfo> list = sSnapshot;
        if (list != null) {
            return list;
        }
        synchronized (SNAPSHOT_LOCK) {
            try {
                if (sSnapshot != null) {
                    return sSnapshot;
                }
                ArrayList arrayList = new ArrayList();
                for (PackageInfo packageInfo : getInstalledPackagesSafe(context.getPackageManager())) {
                    String str = packageInfo.packageName;
                    if (str != null && isTargetApp(str)) {
                        arrayList.add(packageInfo);
                    }
                }
                sSnapshot = arrayList;
                return arrayList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static boolean hasCustomAppIcon(Context context, String str) {
        try {
            String string = SharedPreferencesUtils.getAppPrefs(context).getString("gesture_panel_app_icon_" + str, null);
            if (string != null) {
                return !string.isEmpty();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void invalidate() {
        synchronized (SNAPSHOT_LOCK) {
            sSnapshot = null;
        }
    }

    public static boolean isAmapPackage(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals(AUTONAVI_EXTRA_PACKAGE)) {
            return true;
        }
        return str.startsWith(AUTONAVI_PREFIX) && !str.equals(AUTONAVI_EXCLUDE);
    }

    public static boolean isTargetApp(String str) {
        return isAmapPackage(str) || str.startsWith(BAIDU_PREFIX) || str.startsWith(TENCENT_PREFIX);
    }

    private static boolean isUserInstalled(ApplicationInfo applicationInfo) {
        if (applicationInfo == null) {
            return false;
        }
        int i7 = applicationInfo.flags;
        return (((i7 & 1) != 0) || ((i7 & 128) != 0)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getInstalledNavigationApps$1(AppInfo appInfo, AppInfo appInfo2) {
        return Integer.compare(getAppType(appInfo.getPackageName()), getAppType(appInfo2.getPackageName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$prewarm$0(Context context) {
        try {
            getSnapshot(context);
            synchronized (SNAPSHOT_LOCK) {
                sScanning = false;
            }
        } finally {
            Object obj = SNAPSHOT_LOCK;
        }
    }

    public static void launchAdbSwitch(Context context) {
        if (context == null) {
            return;
        }
        try {
            try {
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.desaysv.engmode.custom", "com.desaysv.engmode.custom.ui.AdbSwitchActivity"));
                    intent.addFlags(268435456);
                    context.startActivity(intent);
                } catch (Exception unused) {
                    Intent intent2 = new Intent();
                    intent2.setComponent(new ComponentName("com.desaysv.engmode", "com.desaysv.engmode.stub.PluginStubActivitySingleInstance0"));
                    intent2.setAction("com.desaysv.engmode.custom.ui.AdbSwitchActivity@");
                    intent2.addFlags(268435456);
                    context.startActivity(intent2);
                }
            } catch (Exception unused2) {
                Intent intent3 = new Intent();
                intent3.setComponent(new ComponentName("com.desaysv.engmode", "com.desaysv.engmode.MainActivity"));
                intent3.addFlags(268435456);
                context.startActivity(intent3);
                ToastHelper.show(context, "请从工程模式进入 ADB 开关");
            }
        } catch (Exception unused3) {
            ToastHelper.show(context, "未找到 ADB 开关界面");
        }
    }

    public static void prewarm(Context context) {
        Context applicationContext = context.getApplicationContext();
        synchronized (SNAPSHOT_LOCK) {
            if (sSnapshot == null && !sScanning) {
                sScanning = true;
                Thread thread = new Thread(new o(applicationContext, 3), "navi-pkg-prewarm");
                thread.setDaemon(true);
                thread.start();
            }
        }
    }
}
