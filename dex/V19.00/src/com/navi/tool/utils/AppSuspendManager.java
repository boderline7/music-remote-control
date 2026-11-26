package com.navi.tool.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.navi.tool.receiver.NaviDeviceAdminReceiver;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AppSuspendManager {
    private static final String KEY_SUSPENDED_PACKAGES = "suspended_packages_set";

    public static Set<String> getMismatchedSuspendedPackages(Context context) {
        HashSet hashSet = new HashSet();
        if (!isDeviceOwner(context)) {
            return hashSet;
        }
        Set<String> suspendedPackagesFromPrefs = getSuspendedPackagesFromPrefs(context);
        if (suspendedPackagesFromPrefs.isEmpty()) {
            return hashSet;
        }
        PackageManager packageManager = context.getPackageManager();
        for (String str : suspendedPackagesFromPrefs) {
            if (str != null && !str.trim().isEmpty() && !AppFreezeManager.isProtectedPackage(context, str) && !isHomePackage(context, str)) {
                try {
                    packageManager.getPackageInfo(str, 0);
                    if (!isSuspended(context, str)) {
                        hashSet.add(str);
                    }
                } catch (Exception unused) {
                }
            }
        }
        return hashSet;
    }

    public static Set<String> getSuspendedPackagesFromPrefs(Context context) {
        return normalizePackages(SharedPreferencesUtils.getAppPrefs(context).getStringSet(KEY_SUSPENDED_PACKAGES, new HashSet()));
    }

    public static boolean isDeviceOwner(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        return devicePolicyManager != null && devicePolicyManager.isDeviceOwnerApp(context.getPackageName());
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

    public static boolean isSuspended(Context context, String str) {
        DevicePolicyManager devicePolicyManager;
        if (!isDeviceOwner(context) || str == null || str.trim().isEmpty() || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return false;
        }
        try {
            return devicePolicyManager.isPackageSuspended(new ComponentName(context, (Class<?>) NaviDeviceAdminReceiver.class), str);
        } catch (Exception unused) {
            return getSuspendedPackagesFromPrefs(context).contains(str);
        }
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

    public static int reapplySpecificSuspendedPackages(Context context, Set<String> set) {
        if (!isDeviceOwner(context) || set == null || set.isEmpty()) {
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        int i7 = 0;
        for (String str : set) {
            if (str != null && !str.trim().isEmpty() && !AppFreezeManager.isProtectedPackage(context, str) && !isHomePackage(context, str)) {
                try {
                    packageManager.getPackageInfo(str, 0);
                    if (setSuspendedState(context, str, true)) {
                        i7++;
                    }
                } catch (Exception unused) {
                }
            }
        }
        return i7;
    }

    public static int reapplySuspendedPackages(Context context) {
        return reapplySpecificSuspendedPackages(context, getSuspendedPackagesFromPrefs(context));
    }

    public static void saveSuspendedPackagesToPrefs(Context context, Set<String> set) {
        SharedPreferencesUtils.getAppPrefs(context).edit().putStringSet(KEY_SUSPENDED_PACKAGES, new HashSet(normalizePackages(set))).apply();
    }

    public static boolean setSuspendedState(Context context, String str, boolean z6) {
        DevicePolicyManager devicePolicyManager;
        boolean z7;
        if (isDeviceOwner(context) && str != null && !str.trim().isEmpty()) {
            if ((z6 && (AppFreezeManager.isProtectedPackage(context, str) || isHomePackage(context, str))) || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
                return false;
            }
            try {
                String[] packagesSuspended = devicePolicyManager.setPackagesSuspended(new ComponentName(context, (Class<?>) NaviDeviceAdminReceiver.class), new String[]{str}, z6);
                if (packagesSuspended != null) {
                    for (String str2 : packagesSuspended) {
                        if (str.equals(str2)) {
                            z7 = false;
                            break;
                        }
                    }
                }
                z7 = true;
                if (z7) {
                    Set<String> suspendedPackagesFromPrefs = getSuspendedPackagesFromPrefs(context);
                    if (z6) {
                        suspendedPackagesFromPrefs.add(str);
                    } else {
                        suspendedPackagesFromPrefs.remove(str);
                    }
                    saveSuspendedPackagesToPrefs(context, suspendedPackagesFromPrefs);
                }
                return z7;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static int unsuspendAllFromPrefs(Context context) {
        if (!isDeviceOwner(context)) {
            return 0;
        }
        HashSet hashSet = new HashSet(getSuspendedPackagesFromPrefs(context));
        if (hashSet.isEmpty()) {
            return 0;
        }
        Iterator it = hashSet.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str != null && !str.trim().isEmpty() && setSuspendedState(context, str, false)) {
                i7++;
            }
        }
        return i7;
    }
}
