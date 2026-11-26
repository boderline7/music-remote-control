package y0;

import android.content.pm.PackageManager;
import android.content.pm.Signature;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d extends c {
    @Override // p3.f
    public final Signature[] u(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }
}
