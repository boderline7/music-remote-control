package p0;

import android.view.View;
import android.view.WindowInsetsController;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class y0 {
    public static CharSequence a(View view) {
        return view.getStateDescription();
    }

    public static s2 b(View view) {
        WindowInsetsController windowInsetsController = view.getWindowInsetsController();
        if (windowInsetsController != null) {
            return new s2(windowInsetsController);
        }
        return null;
    }

    public static void c(View view, CharSequence charSequence) {
        view.setStateDescription(charSequence);
    }
}
