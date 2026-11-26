package b5;

import androidx.lifecycle.z0;
import java.io.Serializable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    public h5.a f2977a;

    /* renamed from: b, reason: collision with root package name */
    public volatile Object f2978b = c.INSTANCE;

    /* renamed from: c, reason: collision with root package name */
    public final Object f2979c = this;

    public b(z0 z0Var) {
        this.f2977a = z0Var;
    }

    public final Object a() {
        Object obj;
        Object obj2 = this.f2978b;
        c cVar = c.INSTANCE;
        if (obj2 != cVar) {
            return obj2;
        }
        synchronized (this.f2979c) {
            obj = this.f2978b;
            if (obj == cVar) {
                h5.a aVar = this.f2977a;
                i5.a.d(aVar);
                obj = aVar.a();
                this.f2978b = obj;
                this.f2977a = null;
            }
        }
        return obj;
    }

    public final String toString() {
        return this.f2978b != c.INSTANCE ? String.valueOf(a()) : "Lazy value not initialized yet.";
    }
}
