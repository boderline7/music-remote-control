package com.navi.tool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.navi.tool.utils.SharedPreferencesUtils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class PackageRemovalReceiver extends BroadcastReceiver {
    private static final String TAG = "PackageRemovalReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_REMOVED".equals(action) || "android.intent.action.PACKAGE_FULLY_REMOVED".equals(action)) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
                return;
            }
            SharedPreferencesUtils.onAppUninstalled(context, schemeSpecificPart);
        }
    }
}
