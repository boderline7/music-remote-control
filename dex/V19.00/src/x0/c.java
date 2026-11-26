package x0;

import android.view.Choreographer;
import n0.k;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends k {

    /* renamed from: b, reason: collision with root package name */
    public final Choreographer f9548b;

    /* renamed from: c, reason: collision with root package name */
    public final b f9549c;

    public c(e.a aVar) {
        super(aVar);
        this.f9548b = Choreographer.getInstance();
        this.f9549c = new b(0, this);
    }

    public final void e() {
        this.f9548b.postFrameCallback(this.f9549c);
    }
}
