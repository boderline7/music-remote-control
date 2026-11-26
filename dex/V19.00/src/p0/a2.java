package p0;

import android.graphics.Rect;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a2 extends d2 {

    /* renamed from: e, reason: collision with root package name */
    public static Field f8095e;

    /* renamed from: f, reason: collision with root package name */
    public static boolean f8096f;

    /* renamed from: g, reason: collision with root package name */
    public static Constructor f8097g;

    /* renamed from: h, reason: collision with root package name */
    public static boolean f8098h;

    /* renamed from: c, reason: collision with root package name */
    public WindowInsets f8099c;

    /* renamed from: d, reason: collision with root package name */
    public i0.c f8100d;

    public a2() {
        this.f8099c = i();
    }

    public a2(l2 l2Var) {
        super(l2Var);
        this.f8099c = l2Var.g();
    }

    private static WindowInsets i() {
        if (!f8096f) {
            try {
                f8095e = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException unused) {
            }
            f8096f = true;
        }
        Field field = f8095e;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException unused2) {
            }
        }
        if (!f8098h) {
            try {
                f8097g = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException unused3) {
            }
            f8098h = true;
        }
        Constructor constructor = f8097g;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException unused4) {
            }
        }
        return null;
    }

    @Override // p0.d2
    public l2 b() {
        a();
        l2 h7 = l2.h(null, this.f8099c);
        i0.c[] cVarArr = this.f8117b;
        j2 j2Var = h7.f8161a;
        j2Var.p(cVarArr);
        j2Var.r(this.f8100d);
        return h7;
    }

    @Override // p0.d2
    public void e(i0.c cVar) {
        this.f8100d = cVar;
    }

    @Override // p0.d2
    public void g(i0.c cVar) {
        WindowInsets windowInsets = this.f8099c;
        if (windowInsets != null) {
            this.f8099c = windowInsets.replaceSystemWindowInsets(cVar.f6773a, cVar.f6774b, cVar.f6775c, cVar.f6776d);
        }
    }
}
