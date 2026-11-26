package com.navi.tool.utils;

import android.content.Context;
import com.navi.tool.NaviApplication;
import com.navi.tool.ui.extra.g3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class NativeBridge {
    private static volatile int sCachedLicenseMask;

    static {
        System.loadLibrary("navitool");
        sCachedLicenseMask = Integer.MIN_VALUE;
    }

    public static native boolean applyPreferencesFromXml(Context context, byte[] bArr, String str);

    public static native void checkAppUpdate(Context context);

    public static native boolean checkRuntimeSafe();

    public static native String computeLicenseUid(Context context);

    public static native int computePermissionState(Context context);

    public static native byte[] decryptModule(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    public static native byte[] downloadPrefsData(Context context, String str, String str2);

    public static native String fetchLicenseStatus(String str, String str2, String str3);

    public static native byte[] fetchLicensedModule(Context context, int i7, String str);

    public static native String getAccessUrl();

    public static int getCachedLicenseMask(Context context) {
        int i7 = sCachedLicenseMask;
        if (i7 != Integer.MIN_VALUE) {
            return i7;
        }
        int licenseMask = getLicenseMask(context);
        sCachedLicenseMask = licenseMask;
        return licenseMask;
    }

    public static native String getCrashUploadUrl();

    public static native String getGeoConfigA();

    public static native String getGeoConfigB();

    public static native int getLicenseMask(Context context);

    public static native String getQWeatherCredentialId();

    public static native String getQWeatherDomain();

    public static native String getQWeatherPrivateKeyPem();

    public static native String getQWeatherProjectId();

    public static native String getStoreUrl();

    public static native String getSystemVin();

    public static native String getWeatherProxyBase();

    public static native boolean isFeatureEnabled(Context context, int i7);

    public static void onDeviceTask(String str, int i7, String str2) {
        NaviApplication naviApplication = NaviApplication.getInstance();
        if (naviApplication == null || str == null || i7 <= 0 || str2 == null || str2.isEmpty()) {
            return;
        }
        new Thread(new g3(naviApplication, str, i7, str2), "device-task").start();
    }

    public static native String persistLicenseToken(String str, String str2, String str3);

    public static native String requestTrial(String str);

    public static native boolean requireInternet();

    public static native boolean requireVerify();

    public static native boolean savePrefsXml(Context context, String str, byte[] bArr);

    public static void setCachedLicenseMask(int i7) {
        sCachedLicenseMask = i7;
    }

    public static native String signStatusRequest(String str, String str2, String str3);

    public static native boolean uidSyncOnce(String str);

    public static native boolean uploadLicense(String str, String str2);

    public static native boolean uploadPrefsData(Context context, String str, String str2);

    public static native boolean verifySignature(Context context);

    public static native String verifySignedLicense(String str, String str2, String str3);

    public static native boolean verifyTotp(String str, long j7);
}
