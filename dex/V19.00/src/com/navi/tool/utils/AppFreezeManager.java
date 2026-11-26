package com.navi.tool.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.navi.tool.receiver.NaviDeviceAdminReceiver;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AppFreezeManager {
    private static final Set<String> ALWAYS_SKIP_PACKAGES = new HashSet(Arrays.asList("android", "com.android.systemui", "com.android.settings", "com.android.phone", "com.google.android.packageinstaller", "com.android.packageinstaller", "com.android.permissioncontroller"));
    private static final String KEY_FROZEN_PACKAGES = "frozen_packages_set";

    public static int bulkSetFrozenByType(Context context, boolean z6, boolean z7) {
        String str;
        ApplicationInfo applicationInfo;
        if (!isDeviceOwner(context)) {
            return 0;
        }
        int i7 = 0;
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            if (packageInfo != null && (str = packageInfo.packageName) != null && (applicationInfo = packageInfo.applicationInfo) != null) {
                int i8 = applicationInfo.flags;
                if (((i8 & 1) != 0 && (i8 & 128) == 0) == z6 && (!isProtectedPackage(context, str) || !z7)) {
                    if (setFrozenState(context, packageInfo.packageName, z7)) {
                        i7++;
                    }
                }
            }
        }
        return i7;
    }

    public static Set<String> getFrozenPackages(Context context) {
        return getFrozenPackagesFromPrefs(context);
    }

    public static Set<String> getFrozenPackagesFromPrefs(Context context) {
        return readFrozenPackagesFromPrefs(context);
    }

    public static Set<String> getMismatchedFrozenPackages(Context context) {
        HashSet hashSet = new HashSet();
        if (!isDeviceOwner(context)) {
            return hashSet;
        }
        Set<String> frozenPackages = getFrozenPackages(context);
        if (frozenPackages.isEmpty()) {
            return hashSet;
        }
        PackageManager packageManager = context.getPackageManager();
        for (String str : frozenPackages) {
            if (str != null && !str.isEmpty() && !isProtectedPackage(context, str)) {
                try {
                    packageManager.getPackageInfo(str, 0);
                    if (!isFrozen(context, str)) {
                        hashSet.add(str);
                    }
                } catch (Exception unused) {
                }
            }
        }
        return hashSet;
    }

    public static boolean isDeviceOwner(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        return devicePolicyManager != null && devicePolicyManager.isDeviceOwnerApp(context.getPackageName());
    }

    public static boolean isFrozen(Context context, String str) {
        DevicePolicyManager devicePolicyManager;
        if (!isDeviceOwner(context) || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return false;
        }
        try {
            return devicePolicyManager.isApplicationHidden(new ComponentName(context, (Class<?>) NaviDeviceAdminReceiver.class), str);
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean isHomePackage(Context context, String str) {
        List<ResolveInfo> queryIntentActivities;
        ActivityInfo activityInfo;
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        } catch (Exception unused) {
        }
        if (queryIntentActivities == null) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (resolveInfo != null && (activityInfo = resolveInfo.activityInfo) != null && str.equals(activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isProtectedPackage(Context context, String str) {
        return str == null || str.isEmpty() || context.getPackageName().equals(str) || ALWAYS_SKIP_PACKAGES.contains(str);
    }

    private static Set<String> normalizePackages(Set<String> set) {
        HashSet hashSet = new HashSet();
        if (set == null) {
            return hashSet;
        }
        for (String str : set) {
            if (str != null) {
                String trim = str.trim();
                if (!trim.isEmpty()) {
                    hashSet.add(trim);
                }
            }
        }
        return hashSet;
    }

    private static Set<String> readFrozenPackagesFromPrefs(Context context) {
        return normalizePackages(SharedPreferencesUtils.getAppPrefs(context).getStringSet(KEY_FROZEN_PACKAGES, new HashSet()));
    }

    public static int reapplyFrozenPackages(Context context) {
        if (!isDeviceOwner(context)) {
            return 0;
        }
        Set<String> frozenPackagesFromPrefs = getFrozenPackagesFromPrefs(context);
        if (frozenPackagesFromPrefs.isEmpty()) {
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, (Class<?>) NaviDeviceAdminReceiver.class);
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null) {
            return 0;
        }
        int i7 = 0;
        for (String str : frozenPackagesFromPrefs) {
            try {
                packageManager.getPackageInfo(str, 0);
                if (!isProtectedPackage(context, str) && devicePolicyManager.setApplicationHidden(componentName, str, true)) {
                    i7++;
                }
            } catch (Exception unused) {
            }
        }
        return i7;
    }

    public static int reapplySpecificPackages(Context context, Set<String> set) {
        DevicePolicyManager devicePolicyManager;
        if (!isDeviceOwner(context) || set == null || set.isEmpty() || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return 0;
        }
        ComponentName componentName = new ComponentName(context, (Class<?>) NaviDeviceAdminReceiver.class);
        PackageManager packageManager = context.getPackageManager();
        int i7 = 0;
        for (String str : set) {
            if (str != null && !str.isEmpty() && !isProtectedPackage(context, str)) {
                try {
                    packageManager.getPackageInfo(str, 0);
                    if (devicePolicyManager.setApplicationHidden(componentName, str, true)) {
                        i7++;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return i7;
    }

    public static void saveFrozenPackagesToPrefs(Context context, Set<String> set) {
        writeFrozenPackagesToPrefs(context, normalizePackages(set));
    }

    public static boolean setFrozenState(Context context, String str, boolean z6) {
        DevicePolicyManager devicePolicyManager;
        if (!isDeviceOwner(context)) {
            return false;
        }
        if ((isProtectedPackage(context, str) && z6) || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return false;
        }
        try {
            boolean applicationHidden = devicePolicyManager.setApplicationHidden(new ComponentName(context, (Class<?>) NaviDeviceAdminReceiver.class), str, z6);
            if (applicationHidden) {
                Set<String> frozenPackages = getFrozenPackages(context);
                if (z6) {
                    frozenPackages.add(str);
                } else {
                    frozenPackages.remove(str);
                }
                saveFrozenPackagesToPrefs(context, frozenPackages);
            }
            return applicationHidden;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int unfreezeFrozenPackagesFromPrefs(Context context) {
        if (!isDeviceOwner(context)) {
            return 0;
        }
        HashSet hashSet = new HashSet(getFrozenPackagesFromPrefs(context));
        if (hashSet.isEmpty()) {
            return 0;
        }
        Iterator it = hashSet.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && !str.isEmpty() && setFrozenState(context, str, false)) {
                i7++;
            }
        }
        return i7;
    }

    private static void writeFrozenPackagesToPrefs(Context context, Set<String> set) {
        SharedPreferencesUtils.getAppPrefs(context).edit().putStringSet(KEY_FROZEN_PACKAGES, new HashSet(set)).apply();
    }
}
