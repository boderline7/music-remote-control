package p0;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s {

    /* renamed from: a, reason: collision with root package name */
    public final Runnable f8183a;

    /* renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f8184b = new CopyOnWriteArrayList();

    /* renamed from: c, reason: collision with root package name */
    public final HashMap f8185c = new HashMap();

    public s(Runnable runnable) {
        this.f8183a = runnable;
    }

    public final void a() {
        this.f8184b.remove((Object) null);
        r rVar = (r) this.f8185c.remove(null);
        if (rVar != null) {
            rVar.f8174a.b(rVar.f8175b);
            rVar.f8175b = null;
        }
        this.f8183a.run();
    }
}
