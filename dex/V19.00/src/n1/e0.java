package n1;

import android.graphics.Matrix;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e0 extends c0 {

    /* renamed from: f, reason: collision with root package name */
    public static boolean f7602f = true;

    /* renamed from: g, reason: collision with root package name */
    public static boolean f7603g = true;

    public void P(View view, Matrix matrix) {
        if (f7602f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f7602f = false;
            }
        }
    }

    public void Q(View view, Matrix matrix) {
        if (f7603g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                f7603g = false;
            }
        }
    }
}
