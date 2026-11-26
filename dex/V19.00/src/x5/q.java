package x5;

import c5.r;
import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import n1.z;
import s5.h0;
import s5.y;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q {
    public static final p Companion = new p();

    /* renamed from: a, reason: collision with root package name */
    public final s5.a f9725a;

    /* renamed from: b, reason: collision with root package name */
    public final v2.a f9726b;

    /* renamed from: c, reason: collision with root package name */
    public final s5.f f9727c;

    /* renamed from: d, reason: collision with root package name */
    public final y f9728d;

    /* renamed from: e, reason: collision with root package name */
    public final List f9729e;

    /* renamed from: f, reason: collision with root package name */
    public int f9730f;

    /* renamed from: g, reason: collision with root package name */
    public List f9731g;

    /* renamed from: h, reason: collision with root package name */
    public final ArrayList f9732h;

    public q(s5.a aVar, v2.a aVar2, j jVar, y yVar) {
        List k7;
        i5.a.g("address", aVar);
        i5.a.g("routeDatabase", aVar2);
        i5.a.g("call", jVar);
        i5.a.g("eventListener", yVar);
        this.f9725a = aVar;
        this.f9726b = aVar2;
        this.f9727c = jVar;
        this.f9728d = yVar;
        r rVar = r.INSTANCE;
        this.f9729e = rVar;
        this.f9731g = rVar;
        this.f9732h = new ArrayList();
        h0 h0Var = aVar.f8755i;
        i5.a.g("url", h0Var);
        Proxy proxy = aVar.f8753g;
        if (proxy != null) {
            k7 = z.G0(proxy);
        } else {
            URI g7 = h0Var.g();
            if (g7.getHost() == null) {
                k7 = t5.b.k(Proxy.NO_PROXY);
            } else {
                List<Proxy> select = aVar.f8754h.select(g7);
                k7 = (select == null || select.isEmpty()) ? t5.b.k(Proxy.NO_PROXY) : t5.b.x(select);
            }
        }
        this.f9729e = k7;
        this.f9730f = 0;
    }

    public final boolean a() {
        return (this.f9730f < this.f9729e.size()) || (this.f9732h.isEmpty() ^ true);
    }
}
