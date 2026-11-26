package p0;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e2 extends j2 {

    /* renamed from: h, reason: collision with root package name */
    public static boolean f8122h;

    /* renamed from: i, reason: collision with root package name */
    public static Method f8123i;

    /* renamed from: j, reason: collision with root package name */
    public static Class f8124j;

    /* renamed from: k, reason: collision with root package name */
    public static Field f8125k;

    /* renamed from: l, reason: collision with root package name */
    public static Field f8126l;

    /* renamed from: c, reason: collision with root package name */
    public final WindowInsets f8127c;

    /* renamed from: d, reason: collision with root package name */
    public i0.c[] f8128d;

    /* renamed from: e, reason: collision with root package name */
    public i0.c f8129e;

    /* renamed from: f, reason: collision with root package name */
    public l2 f8130f;

    /* renamed from: g, reason: collision with root package name */
    public i0.c f8131g;

    public e2(l2 l2Var, WindowInsets windowInsets) {
        super(l2Var);
        this.f8129e = null;
        this.f8127c = windowInsets;
    }

    @SuppressLint({"WrongConstant"})
    private i0.c s(int i7, boolean z6) {
        i0.c cVar = i0.c.NONE;
        for (int i8 = 1; i8 <= 256; i8 <<= 1) {
            if ((i7 & i8) != 0) {
                cVar = i0.c.a(cVar, t(i8, z6));
            }
        }
        return cVar;
    }

    private i0.c u() {
        l2 l2Var = this.f8130f;
        return l2Var != null ? l2Var.f8161a.h() : i0.c.NONE;
    }

    private i0.c v(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!f8122h) {
            x();
        }
        Method method = f8123i;
        if (method != null && f8124j != null && f8125k != null) {
            try {
                Object invoke = method.invoke(view, new Object[0]);
                if (invoke == null) {
                    return null;
                }
                Rect rect = (Rect) f8125k.get(f8126l.get(invoke));
                if (rect != null) {
                    return i0.c.b(rect.left, rect.top, rect.right, rect.bottom);
                }
                return null;
            } catch (ReflectiveOperationException e7) {
                e7.getMessage();
            }
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static void x() {
        try {
            f8123i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            f8124j = cls;
            f8125k = cls.getDeclaredField("mVisibleInsets");
            f8126l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            f8125k.setAccessible(true);
            f8126l.setAccessible(true);
        } catch (ReflectiveOperationException e7) {
            e7.getMessage();
        }
        f8122h = true;
    }

    @Override // p0.j2
    public void d(View view) {
        i0.c v6 = v(view);
        if (v6 == null) {
            v6 = i0.c.NONE;
        }
        y(v6);
    }

    @Override // p0.j2
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.f8131g, ((e2) obj).f8131g);
        }
        return false;
    }

    @Override // p0.j2
    public i0.c f(int i7) {
        return s(i7, false);
    }

    @Override // p0.j2
    public final i0.c j() {
        if (this.f8129e == null) {
            WindowInsets windowInsets = this.f8127c;
            this.f8129e = i0.c.b(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.f8129e;
    }

    @Override // p0.j2
    public l2 l(int i7, int i8, int i9, int i10) {
        l2 h7 = l2.h(null, this.f8127c);
        int i11 = Build.VERSION.SDK_INT;
        d2 c2Var = i11 >= 30 ? new c2(h7) : i11 >= 29 ? new b2(h7) : new a2(h7);
        c2Var.g(l2.e(j(), i7, i8, i9, i10));
        c2Var.e(l2.e(h(), i7, i8, i9, i10));
        return c2Var.b();
    }

    @Override // p0.j2
    public boolean n() {
        return this.f8127c.isRound();
    }

    @Override // p0.j2
    @SuppressLint({"WrongConstant"})
    public boolean o(int i7) {
        for (int i8 = 1; i8 <= 256; i8 <<= 1) {
            if ((i7 & i8) != 0 && !w(i8)) {
                return false;
            }
        }
        return true;
    }

    @Override // p0.j2
    public void p(i0.c[] cVarArr) {
        this.f8128d = cVarArr;
    }

    @Override // p0.j2
    public void q(l2 l2Var) {
        this.f8130f = l2Var;
    }

    public i0.c t(int i7, boolean z6) {
        i0.c h7;
        int i8;
        if (i7 == 1) {
            return z6 ? i0.c.b(0, Math.max(u().f6774b, j().f6774b), 0, 0) : i0.c.b(0, j().f6774b, 0, 0);
        }
        if (i7 == 2) {
            if (z6) {
                i0.c u6 = u();
                i0.c h8 = h();
                return i0.c.b(Math.max(u6.f6773a, h8.f6773a), 0, Math.max(u6.f6775c, h8.f6775c), Math.max(u6.f6776d, h8.f6776d));
            }
            i0.c j7 = j();
            l2 l2Var = this.f8130f;
            h7 = l2Var != null ? l2Var.f8161a.h() : null;
            int i9 = j7.f6776d;
            if (h7 != null) {
                i9 = Math.min(i9, h7.f6776d);
            }
            return i0.c.b(j7.f6773a, 0, j7.f6775c, i9);
        }
        if (i7 == 8) {
            i0.c[] cVarArr = this.f8128d;
            h7 = cVarArr != null ? cVarArr[n1.z.A0(8)] : null;
            if (h7 != null) {
                return h7;
            }
            i0.c j8 = j();
            i0.c u7 = u();
            int i10 = j8.f6776d;
            if (i10 > u7.f6776d) {
                return i0.c.b(0, 0, 0, i10);
            }
            i0.c cVar = this.f8131g;
            return (cVar == null || cVar.equals(i0.c.NONE) || (i8 = this.f8131g.f6776d) <= u7.f6776d) ? i0.c.NONE : i0.c.b(0, 0, 0, i8);
        }
        if (i7 == 16) {
            return i();
        }
        if (i7 == 32) {
            return g();
        }
        if (i7 == 64) {
            return k();
        }
        if (i7 != 128) {
            return i0.c.NONE;
        }
        l2 l2Var2 = this.f8130f;
        k e7 = l2Var2 != null ? l2Var2.f8161a.e() : e();
        if (e7 == null) {
            return i0.c.NONE;
        }
        int i11 = Build.VERSION.SDK_INT;
        DisplayCutout displayCutout = e7.f8159a;
        return i0.c.b(i11 >= 28 ? j.d(displayCutout) : 0, i11 >= 28 ? j.f(displayCutout) : 0, i11 >= 28 ? j.e(displayCutout) : 0, i11 >= 28 ? j.c(displayCutout) : 0);
    }

    public boolean w(int i7) {
        if (i7 != 1 && i7 != 2) {
            if (i7 == 4) {
                return false;
            }
            if (i7 != 8 && i7 != 128) {
                return true;
            }
        }
        return !t(i7, false).equals(i0.c.NONE);
    }

    public void y(i0.c cVar) {
        this.f8131g = cVar;
    }
}
