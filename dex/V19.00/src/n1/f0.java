package n1;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class f0 extends e0 {

    /* renamed from: h, reason: collision with root package name */
    public static boolean f7607h = true;

    public void R(View view, int i7, int i8, int i9, int i10) {
        if (f7607h) {
            try {
                view.setLeftTopRightBottom(i7, i8, i9, i10);
            } catch (NoSuchMethodError unused) {
                f7607h = false;
            }
        }
    }
}
