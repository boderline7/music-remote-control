package n1;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.appcompat.widget.i3;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class b0 {

    /* renamed from: a, reason: collision with root package name */
    public static final g0 f7590a;

    /* renamed from: b, reason: collision with root package name */
    public static final i3 f7591b;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            f7590a = new h0();
        } else {
            f7590a = new g0();
        }
        f7591b = new i3(Float.class, "translationAlpha", 6);
        new i3(Rect.class, "clipBounds", 7);
    }

    public static void a(View view, int i7, int i8, int i9, int i10) {
        f7590a.R(view, i7, i8, i9, i10);
    }
}
