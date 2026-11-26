package l;

import l6.f;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends f {

    /* renamed from: e, reason: collision with root package name */
    public static volatile b f7264e;

    /* renamed from: d, reason: collision with root package name */
    public final e f7265d = new e();

    public static b G0() {
        if (f7264e != null) {
            return f7264e;
        }
        synchronized (b.class) {
            try {
                if (f7264e == null) {
                    f7264e = new b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f7264e;
    }

    public final void H0(Runnable runnable) {
        this.f7265d.I0(runnable);
    }
}
