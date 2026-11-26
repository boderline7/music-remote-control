package c6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class i implements t {
    public static final h Companion = new h();

    /* renamed from: f, reason: collision with root package name */
    public static final g f3085f = new g();

    /* renamed from: a, reason: collision with root package name */
    public final Class f3086a;

    /* renamed from: b, reason: collision with root package name */
    public final Method f3087b;

    /* renamed from: c, reason: collision with root package name */
    public final Method f3088c;

    /* renamed from: d, reason: collision with root package name */
    public final Method f3089d;

    /* renamed from: e, reason: collision with root package name */
    public final Method f3090e;

    public i(Class cls) {
        this.f3086a = cls;
        Method declaredMethod = cls.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        i5.a.f("sslSocketClass.getDeclar…:class.javaPrimitiveType)", declaredMethod);
        this.f3087b = declaredMethod;
        this.f3088c = cls.getMethod("setHostname", String.class);
        this.f3089d = cls.getMethod("getAlpnSelectedProtocol", new Class[0]);
        this.f3090e = cls.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // c6.t
    public final boolean a(SSLSocket sSLSocket) {
        return this.f3086a.isInstance(sSLSocket);
    }

    @Override // c6.t
    public final String b(SSLSocket sSLSocket) {
        if (!this.f3086a.isInstance(sSLSocket)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.f3089d.invoke(sSLSocket, new Object[0]);
            if (bArr != null) {
                return new String(bArr, o5.a.UTF_8);
            }
            return null;
        } catch (IllegalAccessException e7) {
            throw new AssertionError(e7);
        } catch (InvocationTargetException e8) {
            Throwable cause = e8.getCause();
            if ((cause instanceof NullPointerException) && i5.a.c(((NullPointerException) cause).getMessage(), "ssl == null")) {
                return null;
            }
            throw new AssertionError(e8);
        }
    }

    @Override // c6.t
    public final boolean c() {
        b6.e.Companion.getClass();
        return b6.e.f2984e;
    }

    @Override // c6.t
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        if (this.f3086a.isInstance(sSLSocket)) {
            try {
                this.f3087b.invoke(sSLSocket, Boolean.TRUE);
                if (str != null) {
                    this.f3088c.invoke(sSLSocket, str);
                }
                Method method = this.f3090e;
                b6.s.Companion.getClass();
                method.invoke(sSLSocket, b6.r.b(list));
            } catch (IllegalAccessException e7) {
                throw new AssertionError(e7);
            } catch (InvocationTargetException e8) {
                throw new AssertionError(e8);
            }
        }
    }
}
