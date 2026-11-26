package androidx.appcompat.widget;

import android.R;
import android.graphics.Insets;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.reflect.InvocationTargetException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class m1 {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f1350a = {R.attr.state_checked};

    /* renamed from: b, reason: collision with root package name */
    public static final int[] f1351b = new int[0];
    public static final Rect INSETS_NONE = new Rect();

    public static void a(Drawable drawable) {
        String name = drawable.getClass().getName();
        int i7 = Build.VERSION.SDK_INT;
        if (i7 < 29 || i7 >= 31 || !"android.graphics.drawable.ColorStateListDrawable".equals(name)) {
            return;
        }
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(f1350a);
        } else {
            drawable.setState(f1351b);
        }
        drawable.setState(state);
    }

    public static Rect b(Drawable drawable) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11 = Build.VERSION.SDK_INT;
        if (i11 >= 29) {
            Insets a7 = l1.a(drawable);
            i7 = a7.left;
            i8 = a7.top;
            i9 = a7.right;
            i10 = a7.bottom;
            return new Rect(i7, i8, i9, i10);
        }
        Drawable p12 = n1.z.p1(drawable);
        if (i11 >= 29) {
            boolean z6 = k1.f1298a;
        } else if (k1.f1298a) {
            try {
                Object invoke = k1.f1299b.invoke(p12, new Object[0]);
                if (invoke != null) {
                    return new Rect(k1.f1300c.getInt(invoke), k1.f1301d.getInt(invoke), k1.f1302e.getInt(invoke), k1.f1303f.getInt(invoke));
                }
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
        return INSETS_NONE;
    }

    public static PorterDuff.Mode c(int i7, PorterDuff.Mode mode) {
        if (i7 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i7 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i7 == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i7) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
