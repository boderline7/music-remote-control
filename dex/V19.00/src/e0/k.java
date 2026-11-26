package e0;

import android.app.AppOpsManager;
import android.content.Context;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class k {
    public static int a(AppOpsManager appOpsManager, String str, int i7, String str2) {
        if (appOpsManager == null) {
            return 1;
        }
        return appOpsManager.checkOpNoThrow(str, i7, str2);
    }

    public static String b(Context context) {
        return context.getOpPackageName();
    }

    public static AppOpsManager c(Context context) {
        return (AppOpsManager) context.getSystemService(AppOpsManager.class);
    }
}
