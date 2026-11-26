package b6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m extends s {
    public static final l Companion = new l();

    /* renamed from: c, reason: collision with root package name */
    public final Method f2994c;

    /* renamed from: d, reason: collision with root package name */
    public final Method f2995d;

    /* renamed from: e, reason: collision with root package name */
    public final Method f2996e;

    /* renamed from: f, reason: collision with root package name */
    public final Class f2997f;

    /* renamed from: g, reason: collision with root package name */
    public final Class f2998g;

    public m(Method method, Method method2, Method method3, Class cls, Class cls2) {
        this.f2994c = method;
        this.f2995d = method2;
        this.f2996e = method3;
        this.f2997f = cls;
        this.f2998g = cls2;
    }

    @Override // b6.s
    public final void a(SSLSocket sSLSocket) {
        try {
            this.f2996e.invoke(null, sSLSocket);
        } catch (IllegalAccessException e7) {
            throw new AssertionError("failed to remove ALPN", e7);
        } catch (InvocationTargetException e8) {
            throw new AssertionError("failed to remove ALPN", e8);
        }
    }

    @Override // b6.s
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        s.Companion.getClass();
        try {
            this.f2994c.invoke(null, sSLSocket, Proxy.newProxyInstance(s.class.getClassLoader(), new Class[]{this.f2997f, this.f2998g}, new k(r.a(list))));
        } catch (IllegalAccessException e7) {
            throw new AssertionError("failed to set ALPN", e7);
        } catch (InvocationTargetException e8) {
            throw new AssertionError("failed to set ALPN", e8);
        }
    }

    @Override // b6.s
    public final String f(SSLSocket sSLSocket) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.f2995d.invoke(null, sSLSocket));
            i5.a.e("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider", invocationHandler);
            k kVar = (k) invocationHandler;
            boolean z6 = kVar.f2992b;
            if (!z6 && kVar.f2993c == null) {
                s.i("ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 4, null);
                return null;
            }
            if (z6) {
                return null;
            }
            return kVar.f2993c;
        } catch (IllegalAccessException e7) {
            throw new AssertionError("failed to get ALPN selected protocol", e7);
        } catch (InvocationTargetException e8) {
            throw new AssertionError("failed to get ALPN selected protocol", e8);
        }
    }
}
