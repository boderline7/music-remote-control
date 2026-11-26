package p0;

import android.os.Build;
import android.view.Window;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class p1 {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    public static void a(Window window, boolean z6) {
        if (Build.VERSION.SDK_INT >= 30) {
            o1.a(window, z6);
        } else {
            n1.a(window, z6);
        }
    }
}
