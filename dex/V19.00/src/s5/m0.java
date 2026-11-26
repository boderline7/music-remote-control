package s5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m0 {

    /* renamed from: a, reason: collision with root package name */
    public final y.s f8817a = new y.s();

    /* renamed from: b, reason: collision with root package name */
    public final v2.a f8818b = new v2.a(19);

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f8819c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    public final ArrayList f8820d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    public final e0.f f8821e;

    /* renamed from: f, reason: collision with root package name */
    public final boolean f8822f;

    /* renamed from: g, reason: collision with root package name */
    public final c f8823g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f8824h;

    /* renamed from: i, reason: collision with root package name */
    public final boolean f8825i;

    /* renamed from: j, reason: collision with root package name */
    public final t f8826j;

    /* renamed from: k, reason: collision with root package name */
    public final v f8827k;

    /* renamed from: l, reason: collision with root package name */
    public final c f8828l;

    /* renamed from: m, reason: collision with root package name */
    public final SocketFactory f8829m;

    /* renamed from: n, reason: collision with root package name */
    public final List f8830n;

    /* renamed from: o, reason: collision with root package name */
    public final List f8831o;

    /* renamed from: p, reason: collision with root package name */
    public final f6.f f8832p;

    /* renamed from: q, reason: collision with root package name */
    public final j f8833q;

    /* renamed from: r, reason: collision with root package name */
    public int f8834r;

    /* renamed from: s, reason: collision with root package name */
    public int f8835s;

    /* renamed from: t, reason: collision with root package name */
    public int f8836t;

    public m0() {
        y yVar = y.NONE;
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        i5.a.g("<this>", yVar);
        this.f8821e = new e0.f(12, yVar);
        this.f8822f = true;
        c cVar = c.NONE;
        this.f8823g = cVar;
        this.f8824h = true;
        this.f8825i = true;
        this.f8826j = t.NO_COOKIES;
        this.f8827k = v.SYSTEM;
        this.f8828l = cVar;
        SocketFactory socketFactory = SocketFactory.getDefault();
        i5.a.f("getDefault()", socketFactory);
        this.f8829m = socketFactory;
        o0.Companion.getClass();
        this.f8830n = o0.A;
        this.f8831o = o0.f8841z;
        this.f8832p = f6.f.INSTANCE;
        this.f8833q = j.DEFAULT;
        this.f8834r = 10000;
        this.f8835s = 10000;
        this.f8836t = 10000;
    }

    public final void a(long j7, TimeUnit timeUnit) {
        i5.a.g("unit", timeUnit);
        this.f8834r = t5.b.b(j7, timeUnit);
    }

    public final void b(long j7, TimeUnit timeUnit) {
        i5.a.g("unit", timeUnit);
        this.f8835s = t5.b.b(j7, timeUnit);
    }
}
