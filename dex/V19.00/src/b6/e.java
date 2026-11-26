package b6;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import c6.t;
import c6.v;
import com.navi.tool.service.ConfigBackupService;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends s {
    public static final c Companion = new c();

    /* renamed from: e, reason: collision with root package name */
    public static final boolean f2984e;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f2985c;

    /* renamed from: d, reason: collision with root package name */
    public final c6.n f2986d;

    static {
        s.Companion.getClass();
        boolean z6 = false;
        if (i5.a.c("Dalvik", System.getProperty("java.vm.name")) && Build.VERSION.SDK_INT < 30) {
            z6 = true;
        }
        f2984e = z6;
    }

    public e() {
        v vVar;
        Method method;
        Method method2;
        t[] tVarArr = new t[4];
        v.Companion.getClass();
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketImpl"));
            Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketFactoryImpl"));
            Class.forName("com.android.org.conscrypt".concat(".SSLParametersImpl"));
            vVar = new v(cls);
        } catch (Exception e7) {
            s.Companion.getClass();
            s.f3002a.getClass();
            s.i("unable to load android socket classes", 5, e7);
            vVar = null;
        }
        tVarArr[0] = vVar;
        c6.i.Companion.getClass();
        tVarArr[1] = new c6.s(c6.i.f3085f);
        c6.q.Companion.getClass();
        tVarArr[2] = new c6.s(c6.q.f3095a);
        c6.l.Companion.getClass();
        tVarArr[3] = new c6.s(c6.l.f3091a);
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < 4; i7++) {
            t tVar = tVarArr[i7];
            if (tVar != null) {
                arrayList.add(tVar);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (((t) next).c()) {
                arrayList2.add(next);
            }
        }
        this.f2985c = arrayList2;
        c6.n.Companion.getClass();
        try {
            Class<?> cls2 = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls2.getMethod("get", new Class[0]);
            method2 = cls2.getMethod("open", String.class);
            method = cls2.getMethod("warnIfOpen", new Class[0]);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        this.f2986d = new c6.n(method3, method2, method);
    }

    @Override // b6.s
    public final f6.e b(X509TrustManager x509TrustManager) {
        X509TrustManagerExtensions x509TrustManagerExtensions;
        c6.d.Companion.getClass();
        try {
            x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        } catch (IllegalArgumentException unused) {
            x509TrustManagerExtensions = null;
        }
        c6.d dVar = x509TrustManagerExtensions != null ? new c6.d(x509TrustManager, x509TrustManagerExtensions) : null;
        return dVar != null ? dVar : super.b(x509TrustManager);
    }

    @Override // b6.s
    public final f6.g c(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new d(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // b6.s
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object obj;
        i5.a.g("protocols", list);
        Iterator it = this.f2985c.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((t) obj).a(sSLSocket)) {
                    break;
                }
            }
        }
        t tVar = (t) obj;
        if (tVar != null) {
            tVar.d(sSLSocket, str, list);
        }
    }

    @Override // b6.s
    public final void e(Socket socket, InetSocketAddress inetSocketAddress, int i7) {
        i5.a.g("address", inetSocketAddress);
        try {
            socket.connect(inetSocketAddress, i7);
        } catch (ClassCastException e7) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e7;
            }
            throw new IOException("Exception in connect", e7);
        }
    }

    @Override // b6.s
    public final String f(SSLSocket sSLSocket) {
        Object obj;
        Iterator it = this.f2985c.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((t) obj).a(sSLSocket)) {
                break;
            }
        }
        t tVar = (t) obj;
        if (tVar != null) {
            return tVar.b(sSLSocket);
        }
        return null;
    }

    @Override // b6.s
    public final Object g() {
        c6.n nVar = this.f2986d;
        nVar.getClass();
        Method method = nVar.f3092a;
        if (method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(null, new Object[0]);
            Method method2 = nVar.f3093b;
            i5.a.d(method2);
            method2.invoke(invoke, "response.body().close()");
            return invoke;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // b6.s
    public final boolean h(String str) {
        i5.a.g("hostname", str);
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    @Override // b6.s
    public final void j(String str, Object obj) {
        i5.a.g(ConfigBackupService.EXTRA_MESSAGE, str);
        c6.n nVar = this.f2986d;
        nVar.getClass();
        if (obj != null) {
            try {
                Method method = nVar.f3094c;
                i5.a.d(method);
                method.invoke(obj, new Object[0]);
                return;
            } catch (Exception unused) {
            }
        }
        s.i(str, 5, null);
    }
}
