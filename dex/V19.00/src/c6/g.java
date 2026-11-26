package c6;

import javax.net.ssl.SSLSocket;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g implements r {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f3084a = "com.google.android.gms.org.conscrypt";

    @Override // c6.r
    public final boolean a(SSLSocket sSLSocket) {
        return o5.k.R(sSLSocket.getClass().getName(), this.f3084a + '.', false);
    }

    @Override // c6.r
    public final t b(SSLSocket sSLSocket) {
        h hVar = i.Companion;
        Class<?> cls = sSLSocket.getClass();
        hVar.getClass();
        Class<?> cls2 = cls;
        while (!i5.a.c(cls2.getSimpleName(), "OpenSSLSocketImpl")) {
            cls2 = cls2.getSuperclass();
            if (cls2 == null) {
                throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
            }
        }
        return new i(cls2);
    }
}
