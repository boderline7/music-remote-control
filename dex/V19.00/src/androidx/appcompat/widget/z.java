package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z {

    /* renamed from: b, reason: collision with root package name */
    public static final PorterDuff.Mode f1492b = PorterDuff.Mode.SRC_IN;

    /* renamed from: c, reason: collision with root package name */
    public static z f1493c;

    /* renamed from: a, reason: collision with root package name */
    public l2 f1494a;

    public static synchronized z a() {
        z zVar;
        synchronized (z.class) {
            try {
                if (f1493c == null) {
                    d();
                }
                zVar = f1493c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zVar;
    }

    public static synchronized PorterDuffColorFilter c(int i7, PorterDuff.Mode mode) {
        PorterDuffColorFilter g7;
        synchronized (z.class) {
            g7 = l2.g(i7, mode);
        }
        return g7;
    }

    public static synchronized void d() {
        synchronized (z.class) {
            if (f1493c == null) {
                z zVar = new z();
                f1493c = zVar;
                zVar.f1494a = l2.c();
                l2 l2Var = f1493c.f1494a;
                y yVar = new y();
                synchronized (l2Var) {
                    l2Var.f1315e = yVar;
                }
            }
        }
    }

    public static void e(Drawable drawable, n3 n3Var, int[] iArr) {
        PorterDuff.Mode mode = l2.f1308f;
        int[] state = drawable.getState();
        int[] iArr2 = m1.f1350a;
        if (drawable.mutate() == drawable) {
            if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
                drawable.setState(new int[0]);
                drawable.setState(state);
            }
            boolean z6 = n3Var.f1362b;
            if (!z6 && !n3Var.f1361a) {
                drawable.clearColorFilter();
                return;
            }
            PorterDuffColorFilter porterDuffColorFilter = null;
            ColorStateList colorStateList = z6 ? (ColorStateList) n3Var.f1363c : null;
            PorterDuff.Mode mode2 = n3Var.f1361a ? (PorterDuff.Mode) n3Var.f1364d : l2.f1308f;
            if (colorStateList != null && mode2 != null) {
                porterDuffColorFilter = l2.g(colorStateList.getColorForState(iArr, 0), mode2);
            }
            drawable.setColorFilter(porterDuffColorFilter);
        }
    }

    public final synchronized Drawable b(Context context, int i7) {
        return this.f1494a.e(context, i7);
    }
}
