package e2;

import com.android.dx.util.MutabilityException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h {

    /* renamed from: a, reason: collision with root package name */
    public boolean f6364a;

    public h(boolean z6) {
        this.f6364a = z6;
    }

    public final void e() {
        if (!this.f6364a) {
            throw new MutabilityException("immutable instance", null);
        }
    }
}
