package l6;

import androidx.appcompat.app.g;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c implements j6.a {

    /* renamed from: a, reason: collision with root package name */
    public final String f7458a;

    /* renamed from: b, reason: collision with root package name */
    public volatile j6.a f7459b;

    /* renamed from: c, reason: collision with root package name */
    public Boolean f7460c;

    /* renamed from: d, reason: collision with root package name */
    public Method f7461d;

    /* renamed from: e, reason: collision with root package name */
    public g f7462e;

    /* renamed from: f, reason: collision with root package name */
    public final Queue f7463f;

    /* renamed from: g, reason: collision with root package name */
    public final boolean f7464g;

    public c(String str, LinkedBlockingQueue linkedBlockingQueue, boolean z6) {
        this.f7458a = str;
        this.f7463f = linkedBlockingQueue;
        this.f7464g = z6;
    }

    @Override // j6.a
    public final String A() {
        return this.f7458a;
    }

    @Override // j6.a
    public final void a(Long l7, Long l8) {
        b().a(l7, l8);
    }

    public final j6.a b() {
        if (this.f7459b != null) {
            return this.f7459b;
        }
        if (this.f7464g) {
            return b.NOP_LOGGER;
        }
        if (this.f7462e == null) {
            this.f7462e = new g(this, this.f7463f);
        }
        return this.f7462e;
    }

    @Override // j6.a
    public final boolean c() {
        return b().c();
    }

    @Override // j6.a
    public final void d(String str, Object obj) {
        b().d(str, obj);
    }

    @Override // j6.a
    public final void e(String str) {
        b().e(str);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && c.class == obj.getClass() && this.f7458a.equals(((c) obj).f7458a);
    }

    public final boolean f() {
        Boolean bool = this.f7460c;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            this.f7461d = this.f7459b.getClass().getMethod("log", k6.a.class);
            this.f7460c = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.f7460c = Boolean.FALSE;
        }
        return this.f7460c.booleanValue();
    }

    public final int hashCode() {
        return this.f7458a.hashCode();
    }

    @Override // j6.a
    public final void i(String str, Exception exc) {
        b().i(str, exc);
    }

    @Override // j6.a
    public final void j(Object... objArr) {
        b().j(objArr);
    }

    @Override // j6.a
    public final void q(String str, IOException iOException) {
        b().q(str, iOException);
    }

    @Override // j6.a
    public final void t(String str) {
        b().t(str);
    }

    @Override // j6.a
    public final void u(Exception exc) {
        b().u(exc);
    }

    @Override // j6.a
    public final void v() {
        b().v();
    }

    @Override // j6.a
    public final void w(String str) {
        b().w(str);
    }
}
