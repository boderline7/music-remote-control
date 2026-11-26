package com.navi.tool.utils;

import android.app.AppOpsManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MapPermissionGrantHelper {

    public static class GrantResult {
        public final int failed;
        public final int granted;
        public final int total;

        public GrantResult(int i7, int i8, int i9) {
            this.total = i7;
            this.granted = i8;
            this.failed = i9;
        }
    }

    public static boolean applyPermissionState(Context context, ComponentName componentName, String str, String str2, boolean z6) {
        boolean z7;
        try {
            ((DevicePolicyManager) context.getSystemService("device_policy")).setPermissionGrantState(componentName, str, str2, z6 ? 1 : 2);
            z7 = true;
        } catch (Exception unused) {
            z7 = false;
        }
        if (hasAppOpSupport(context, str2)) {
            trySetAppOp(context, str, str2, z6);
            z7 = true;
        }
        return z7 && isPermissionEffectivelyGranted(context, componentName, str, str2) == z6;
    }

    public static boolean checkAppOpState(Context context, String str, String str2) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            int packageUid = context.getPackageManager().getPackageUid(str, 0);
            int opCode = getOpCode(context, str2);
            if (opCode != -1) {
                try {
                    Class<?> cls = appOpsManager.getClass();
                    Class<?> cls2 = Integer.TYPE;
                    if (((Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf(opCode), Integer.valueOf(packageUid), str)).intValue() == 0) {
                        return true;
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
            String permissionToOp = AppOpsManager.permissionToOp(str2);
            if (permissionToOp != null) {
                if (appOpsManager.checkOpNoThrow(permissionToOp, packageUid, str) == 0) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getOpCode(Context context, String str) {
        char c7;
        str.getClass();
        switch (str.hashCode()) {
            case -2078357533:
                if (str.equals("android.permission.WRITE_SETTINGS")) {
                    c7 = 0;
                    break;
                }
                c7 = 65535;
                break;
            case -2062386608:
                if (str.equals("android.permission.READ_SMS")) {
                    c7 = 1;
                    break;
                }
                c7 = 65535;
                break;
            case -1928411001:
                if (str.equals("android.permission.READ_CALENDAR")) {
                    c7 = 2;
                    break;
                }
                c7 = 65535;
                break;
            case -1925850455:
                if (str.equals("android.permission.POST_NOTIFICATIONS")) {
                    c7 = 3;
                    break;
                }
                c7 = 65535;
                break;
            case -1921431796:
                if (str.equals("android.permission.READ_CALL_LOG")) {
                    c7 = 4;
                    break;
                }
                c7 = 65535;
                break;
            case -1888586689:
                if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                    c7 = 5;
                    break;
                }
                c7 = 65535;
                break;
            case -1813079487:
                if (str.equals("android.permission.MANAGE_EXTERNAL_STORAGE")) {
                    c7 = 6;
                    break;
                }
                c7 = 65535;
                break;
            case -1561629405:
                if (str.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                    c7 = 7;
                    break;
                }
                c7 = 65535;
                break;
            case -1147355431:
                if (str.equals("android.permission.FOREGROUND_SERVICE")) {
                    c7 = '\b';
                    break;
                }
                c7 = 65535;
                break;
            case -895673731:
                if (str.equals("android.permission.RECEIVE_SMS")) {
                    c7 = '\t';
                    break;
                }
                c7 = 65535;
                break;
            case -406040016:
                if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                    c7 = '\n';
                    break;
                }
                c7 = 65535;
                break;
            case -162862488:
                if (str.equals("android.permission.PACKAGE_USAGE_STATS")) {
                    c7 = 11;
                    break;
                }
                c7 = 65535;
                break;
            case -63024214:
                if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                    c7 = '\f';
                    break;
                }
                c7 = 65535;
                break;
            case 169397625:
                if (str.equals("android.permission.WRITE_SMS")) {
                    c7 = '\r';
                    break;
                }
                c7 = 65535;
                break;
            case 214526995:
                if (str.equals("android.permission.WRITE_CONTACTS")) {
                    c7 = 14;
                    break;
                }
                c7 = 65535;
                break;
            case 463403621:
                if (str.equals("android.permission.CAMERA")) {
                    c7 = 15;
                    break;
                }
                c7 = 65535;
                break;
            case 603653886:
                if (str.equals("android.permission.WRITE_CALENDAR")) {
                    c7 = 16;
                    break;
                }
                c7 = 65535;
                break;
            case 610633091:
                if (str.equals("android.permission.WRITE_CALL_LOG")) {
                    c7 = 17;
                    break;
                }
                c7 = 65535;
                break;
            case 1353874541:
                if (str.equals("android.permission.ACCESS_NOTIFICATIONS")) {
                    c7 = 18;
                    break;
                }
                c7 = 65535;
                break;
            case 1365911975:
                if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                    c7 = 19;
                    break;
                }
                c7 = 65535;
                break;
            case 1777263169:
                if (str.equals("android.permission.REQUEST_INSTALL_PACKAGES")) {
                    c7 = 20;
                    break;
                }
                c7 = 65535;
                break;
            case 1831139720:
                if (str.equals("android.permission.RECORD_AUDIO")) {
                    c7 = 21;
                    break;
                }
                c7 = 65535;
                break;
            case 1977429404:
                if (str.equals("android.permission.READ_CONTACTS")) {
                    c7 = 22;
                    break;
                }
                c7 = 65535;
                break;
            default:
                c7 = 65535;
                break;
        }
        switch (c7) {
            case 0:
                return 23;
            case 1:
                return 14;
            case 2:
                return 8;
            case 3:
                return 11;
            case 4:
                return 6;
            case 5:
                return 1;
            case 6:
                return 92;
            case 7:
                return 24;
            case '\b':
                return 76;
            case '\t':
                return 16;
            case '\n':
                return 59;
            case 11:
                return 43;
            case '\f':
                return 0;
            case '\r':
                return 15;
            case 14:
                return 5;
            case 15:
                return 26;
            case 16:
                return 9;
            case 17:
                return 7;
            case 18:
                return 11;
            case 19:
                return 60;
            case 20:
                return 66;
            case 21:
                return 27;
            case 22:
                return 4;
            default:
                try {
                    Object invoke = AppOpsManager.class.getMethod("permissionToOpCode", String.class).invoke(null, str);
                    if (invoke != null) {
                        return ((Integer) invoke).intValue();
                    }
                } catch (Exception unused) {
                }
                try {
                    String permissionToOp = AppOpsManager.permissionToOp(str);
                    if (permissionToOp != null) {
                        Object invoke2 = AppOpsManager.class.getMethod("strOpToOp", String.class).invoke(null, permissionToOp);
                        if (invoke2 instanceof Integer) {
                            return ((Integer) invoke2).intValue();
                        }
                    }
                } catch (Exception unused2) {
                }
                return -1;
        }
    }

    public static GrantResult grantAllPermissions(Context context, ComponentName componentName, String str) {
        int i7;
        int i8;
        int i9 = 0;
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
            if (strArr != null) {
                int length = strArr.length;
                int i10 = 0;
                i7 = 0;
                i8 = 0;
                while (i9 < length) {
                    try {
                        String str2 = strArr[i9];
                        if (isPermissionSupported(str2)) {
                            if (!isSpecialAppOpPermission(str2) && !isRuntimeManagedPermission(context, str2) && getOpCode(context, str2) == -1) {
                            }
                            i10++;
                            boolean applyPermissionState = applyPermissionState(context, componentName, str, str2, true);
                            boolean isPermissionEffectivelyGranted = isPermissionEffectivelyGranted(context, componentName, str, str2);
                            if (applyPermissionState && isPermissionEffectivelyGranted) {
                                i7++;
                            } else {
                                i8++;
                            }
                        }
                        i9++;
                    } catch (Exception e7) {
                        e = e7;
                        i9 = i10;
                        e.printStackTrace();
                        return new GrantResult(i9, i7, i8);
                    }
                }
                i9 = i10;
            } else {
                i7 = 0;
                i8 = 0;
            }
        } catch (Exception e8) {
            e = e8;
            i7 = 0;
            i8 = 0;
        }
        return new GrantResult(i9, i7, i8);
    }

    public static boolean hasAppOpSupport(Context context, String str) {
        return (getOpCode(context, str) == -1 && AppOpsManager.permissionToOp(str) == null) ? false : true;
    }

    public static boolean isDeviceOwner(Context context) {
        try {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
            if (devicePolicyManager != null) {
                return devicePolicyManager.isDeviceOwnerApp(context.getPackageName());
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isPermissionEffectivelyGranted(Context context, ComponentName componentName, String str, String str2) {
        int i7;
        if (isSpecialAppOpPermission(str2)) {
            return checkAppOpState(context, str, str2);
        }
        try {
            i7 = ((DevicePolicyManager) context.getSystemService("device_policy")).getPermissionGrantState(componentName, str, str2);
        } catch (Exception unused) {
            i7 = 0;
        }
        if (i7 == 2 && isRuntimeManagedPermission(context, str2)) {
            return false;
        }
        if (i7 == 1 || context.getPackageManager().checkPermission(str2, str) == 0) {
            return true;
        }
        if (isRuntimeManagedPermission(context, str2) || !hasAppOpSupport(context, str2)) {
            return false;
        }
        return checkAppOpState(context, str, str2);
    }

    public static boolean isPermissionSupported(String str) {
        int i7 = Build.VERSION.SDK_INT;
        return "android.permission.MANAGE_EXTERNAL_STORAGE".equals(str) ? i7 >= 30 : ("android.permission.ACCESS_BACKGROUND_LOCATION".equals(str) || "android.permission.ACTIVITY_RECOGNITION".equals(str) || "android.permission.ACCESS_MEDIA_LOCATION".equals(str)) ? i7 >= 29 : ("android.permission.POST_NOTIFICATIONS".equals(str) || "android.permission.READ_MEDIA_IMAGES".equals(str) || "android.permission.READ_MEDIA_VIDEO".equals(str) || "android.permission.READ_MEDIA_AUDIO".equals(str) || "android.permission.NEARBY_WIFI_DEVICES".equals(str) || "android.permission.BODY_SENSORS_BACKGROUND".equals(str)) ? i7 >= 33 : !("android.permission.BLUETOOTH_SCAN".equals(str) || "android.permission.BLUETOOTH_CONNECT".equals(str) || "android.permission.BLUETOOTH_ADVERTISE".equals(str)) || i7 >= 31;
    }

    public static boolean isRuntimeManagedPermission(Context context, String str) {
        try {
            int i7 = context.getPackageManager().getPermissionInfo(str, 0).protectionLevel;
            return (i7 & 15) == 1 || (i7 & 64) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isSpecialAppOpPermission(String str) {
        str.getClass();
        switch (str) {
            case "android.permission.WRITE_SETTINGS":
            case "android.permission.MANAGE_EXTERNAL_STORAGE":
            case "android.permission.SYSTEM_ALERT_WINDOW":
            case "android.permission.PACKAGE_USAGE_STATS":
            case "android.permission.ACCESS_NOTIFICATIONS":
            case "android.permission.REQUEST_INSTALL_PACKAGES":
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0053 A[Catch: Exception -> 0x0083, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x0083, blocks: (B:3:0x0001, B:8:0x0053), top: B:2:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean trySetAppOp(android.content.Context r12, java.lang.String r13, java.lang.String r14, boolean r15) {
        /*
            r0 = 0
            java.lang.String r1 = "appops"
            java.lang.Object r1 = r12.getSystemService(r1)     // Catch: java.lang.Exception -> L83
            android.app.AppOpsManager r1 = (android.app.AppOpsManager) r1     // Catch: java.lang.Exception -> L83
            android.content.pm.PackageManager r2 = r12.getPackageManager()     // Catch: java.lang.Exception -> L83
            int r2 = r2.getPackageUid(r13, r0)     // Catch: java.lang.Exception -> L83
            r3 = 1
            r15 = r15 ^ r3
            int r12 = getOpCode(r12, r14)     // Catch: java.lang.Exception -> L83
            r4 = -1
            java.lang.String r5 = "setMode"
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r7 = 3
            r8 = 2
            r9 = 4
            if (r12 == r4) goto L50
            java.lang.Class r4 = r1.getClass()     // Catch: java.lang.Exception -> L50
            java.lang.Class[] r10 = new java.lang.Class[r9]     // Catch: java.lang.Exception -> L50
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L50
            r10[r0] = r11     // Catch: java.lang.Exception -> L50
            r10[r3] = r11     // Catch: java.lang.Exception -> L50
            r10[r8] = r6     // Catch: java.lang.Exception -> L50
            r10[r7] = r11     // Catch: java.lang.Exception -> L50
            java.lang.reflect.Method r4 = r4.getMethod(r5, r10)     // Catch: java.lang.Exception -> L50
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch: java.lang.Exception -> L50
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch: java.lang.Exception -> L50
            r10[r0] = r12     // Catch: java.lang.Exception -> L50
            java.lang.Integer r12 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Exception -> L50
            r10[r3] = r12     // Catch: java.lang.Exception -> L50
            r10[r8] = r13     // Catch: java.lang.Exception -> L50
            java.lang.Integer r12 = java.lang.Integer.valueOf(r15)     // Catch: java.lang.Exception -> L50
            r10[r7] = r12     // Catch: java.lang.Exception -> L50
            r4.invoke(r1, r10)     // Catch: java.lang.Exception -> L50
            r12 = r3
            goto L51
        L50:
            r12 = r0
        L51:
            if (r12 != 0) goto L85
            java.lang.String r14 = android.app.AppOpsManager.permissionToOp(r14)     // Catch: java.lang.Exception -> L83
            if (r14 == 0) goto L85
            java.lang.Class r4 = r1.getClass()     // Catch: java.lang.Exception -> L85
            java.lang.Class[] r10 = new java.lang.Class[r9]     // Catch: java.lang.Exception -> L85
            r10[r0] = r6     // Catch: java.lang.Exception -> L85
            java.lang.Class r11 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L85
            r10[r3] = r11     // Catch: java.lang.Exception -> L85
            r10[r8] = r6     // Catch: java.lang.Exception -> L85
            r10[r7] = r11     // Catch: java.lang.Exception -> L85
            java.lang.reflect.Method r4 = r4.getMethod(r5, r10)     // Catch: java.lang.Exception -> L85
            java.lang.Object[] r5 = new java.lang.Object[r9]     // Catch: java.lang.Exception -> L85
            r5[r0] = r14     // Catch: java.lang.Exception -> L85
            java.lang.Integer r14 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Exception -> L85
            r5[r3] = r14     // Catch: java.lang.Exception -> L85
            r5[r8] = r13     // Catch: java.lang.Exception -> L85
            java.lang.Integer r13 = java.lang.Integer.valueOf(r15)     // Catch: java.lang.Exception -> L85
            r5[r7] = r13     // Catch: java.lang.Exception -> L85
            r4.invoke(r1, r5)     // Catch: java.lang.Exception -> L85
            goto L86
        L83:
            r12 = move-exception
            goto L87
        L85:
            r3 = r12
        L86:
            return r3
        L87:
            r12.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.utils.MapPermissionGrantHelper.trySetAppOp(android.content.Context, java.lang.String, java.lang.String, boolean):boolean");
    }
}
