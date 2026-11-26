package n1;

import android.os.Build;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class g0 extends f0 {

    /* renamed from: i, reason: collision with root package name */
    public static boolean f7611i = true;

    @Override // p3.f
    public void I(int i7, View view) {
        if (Build.VERSION.SDK_INT == 28) {
            super.I(i7, view);
        } else if (f7611i) {
            try {
                view.setTransitionVisibility(i7);
            } catch (NoSuchMethodError unused) {
                f7611i = false;
            }
        }
    }
}
