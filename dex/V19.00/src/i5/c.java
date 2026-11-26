package i5;

import androidx.activity.g0;
import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c implements Serializable {
    public static final Object NO_RECEIVER = b.f6800a;

    /* renamed from: a, reason: collision with root package name */
    public transient g f6801a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f6802b;

    /* renamed from: c, reason: collision with root package name */
    public final Class f6803c = g0.class;

    /* renamed from: d, reason: collision with root package name */
    public final String f6804d = "updateEnabledCallbacks";

    /* renamed from: e, reason: collision with root package name */
    public final String f6805e = "updateEnabledCallbacks()V";

    /* renamed from: f, reason: collision with root package name */
    public final boolean f6806f;

    public c(g0 g0Var, boolean z6) {
        this.f6802b = g0Var;
        this.f6806f = z6;
    }

    public final d b() {
        d fVar;
        Class cls = this.f6803c;
        if (cls == null) {
            return null;
        }
        if (this.f6806f) {
            k.f6813a.getClass();
            fVar = new i(cls);
        } else {
            k.f6813a.getClass();
            fVar = new f(cls);
        }
        return fVar;
    }
}
