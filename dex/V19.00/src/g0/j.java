package g0;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.os.Process;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class j {
    public static final int PERMISSION_DENIED = -1;
    public static final int PERMISSION_DENIED_APP_OP = -2;
    public static final int PERMISSION_GRANTED = 0;

    public static int a(Context context, String str) {
        int c7;
        int myPid = Process.myPid();
        int myUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, myPid, myUid) == -1) {
            return -1;
        }
        String d7 = e0.j.d(str);
        if (d7 != null) {
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(myUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            int myUid2 = Process.myUid();
            String packageName2 = context.getPackageName();
            if (myUid2 != myUid || !o0.b.a(packageName2, packageName)) {
                c7 = e0.j.c((AppOpsManager) e0.j.a(context, AppOpsManager.class), d7, packageName);
            } else if (Build.VERSION.SDK_INT >= 29) {
                AppOpsManager c8 = e0.k.c(context);
                c7 = e0.k.a(c8, d7, Binder.getCallingUid(), packageName);
                if (c7 == 0) {
                    c7 = e0.k.a(c8, d7, myUid, e0.k.b(context));
                }
            } else {
                c7 = e0.j.c((AppOpsManager) e0.j.a(context, AppOpsManager.class), d7, packageName);
            }
            if (c7 != 0) {
                return -2;
            }
        }
        return 0;
    }
}
