package p0;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l2 {
    public static final l2 CONSUMED;

    /* renamed from: a, reason: collision with root package name */
    public final j2 f8161a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            CONSUMED = i2.f8148q;
        } else {
            CONSUMED = j2.f8157b;
        }
    }

    public l2() {
        this.f8161a = new j2(this);
    }

    public l2(WindowInsets windowInsets) {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 30) {
            this.f8161a = new i2(this, windowInsets);
            return;
        }
        if (i7 >= 29) {
            this.f8161a = new h2(this, windowInsets);
        } else if (i7 >= 28) {
            this.f8161a = new g2(this, windowInsets);
        } else {
            this.f8161a = new f2(this, windowInsets);
        }
    }

    public static i0.c e(i0.c cVar, int i7, int i8, int i9, int i10) {
        int max = Math.max(0, cVar.f6773a - i7);
        int max2 = Math.max(0, cVar.f6774b - i8);
        int max3 = Math.max(0, cVar.f6775c - i9);
        int max4 = Math.max(0, cVar.f6776d - i10);
        return (max == i7 && max2 == i8 && max3 == i9 && max4 == i10) ? cVar : i0.c.b(max, max2, max3, max4);
    }

    public static l2 h(View view, WindowInsets windowInsets) {
        windowInsets.getClass();
        l2 l2Var = new l2(windowInsets);
        if (view != null) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            if (o0.b(view)) {
                l2 a7 = s0.a(view);
                j2 j2Var = l2Var.f8161a;
                j2Var.q(a7);
                j2Var.d(view.getRootView());
            }
        }
        return l2Var;
    }

    public final int a() {
        return this.f8161a.j().f6776d;
    }

    public final int b() {
        return this.f8161a.j().f6773a;
    }

    public final int c() {
        return this.f8161a.j().f6775c;
    }

    public final int d() {
        return this.f8161a.j().f6774b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l2)) {
            return false;
        }
        return o0.b.a(this.f8161a, ((l2) obj).f8161a);
    }

    public final l2 f(int i7, int i8, int i9, int i10) {
        int i11 = Build.VERSION.SDK_INT;
        d2 c2Var = i11 >= 30 ? new c2(this) : i11 >= 29 ? new b2(this) : new a2(this);
        c2Var.g(i0.c.b(i7, i8, i9, i10));
        return c2Var.b();
    }

    public final WindowInsets g() {
        j2 j2Var = this.f8161a;
        if (j2Var instanceof e2) {
            return ((e2) j2Var).f8127c;
        }
        return null;
    }

    public final int hashCode() {
        j2 j2Var = this.f8161a;
        if (j2Var == null) {
            return 0;
        }
        return j2Var.hashCode();
    }
}
