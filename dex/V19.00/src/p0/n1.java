package p0;

import android.view.View;
import android.view.Window;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n1 {
    public static void a(Window window, boolean z6) {
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(z6 ? systemUiVisibility & (-1793) : systemUiVisibility | 1792);
    }
}
