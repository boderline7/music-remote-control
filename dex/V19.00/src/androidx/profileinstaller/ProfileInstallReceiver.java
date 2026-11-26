package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import h1.f;
import java.io.File;
import java.io.Serializable;
import l.a;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {
    public static final String ACTION_BENCHMARK_OPERATION = "androidx.profileinstaller.action.BENCHMARK_OPERATION";
    public static final String ACTION_INSTALL_PROFILE = "androidx.profileinstaller.action.INSTALL_PROFILE";
    public static final String ACTION_SAVE_PROFILE = "androidx.profileinstaller.action.SAVE_PROFILE";
    public static final String ACTION_SKIP_FILE = "androidx.profileinstaller.action.SKIP_FILE";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (ACTION_INSTALL_PROFILE.equals(action)) {
            f.b(context, new a(2), new e.a(24, this), true);
            return;
        }
        if (!ACTION_SKIP_FILE.equals(action)) {
            if (ACTION_SAVE_PROFILE.equals(action)) {
                Process.sendSignal(Process.myPid(), 10);
                setResultCode(12);
                return;
            }
            if (!ACTION_BENCHMARK_OPERATION.equals(action) || (extras = intent.getExtras()) == null) {
                return;
            }
            String string = extras.getString("EXTRA_BENCHMARK_OPERATION");
            e.a aVar = new e.a(24, this);
            if (!"DROP_SHADER_CACHE".equals(string)) {
                aVar.n(16, null);
                return;
            } else if (z.S(context.createDeviceProtectedStorageContext().getCodeCacheDir())) {
                aVar.n(14, null);
                return;
            } else {
                aVar.n(15, null);
                return;
            }
        }
        Bundle extras2 = intent.getExtras();
        if (extras2 != null) {
            String string2 = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
            if (!"WRITE_SKIP_FILE".equals(string2)) {
                if ("DELETE_SKIP_FILE".equals(string2)) {
                    e.a aVar2 = new e.a(24, this);
                    new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
                    new h1.a(aVar2, 11, (Serializable) null, 1).run();
                    return;
                }
                return;
            }
            e.a aVar3 = new e.a(24, this);
            try {
                f.a(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
                new h1.a(aVar3, 10, (Serializable) null, 1).run();
            } catch (PackageManager.NameNotFoundException e7) {
                new h1.a(aVar3, 7, e7, 1).run();
            }
        }
    }
}
