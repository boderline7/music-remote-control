package b6;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import c6.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends s {
    public static final a Companion = new a();

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f2980d;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f2981c;

    static {
        s.Companion.getClass();
        f2980d = i5.a.c("Dalvik", System.getProperty("java.vm.name")) && Build.VERSION.SDK_INT >= 29;
    }

    public b() {
        t[] tVarArr = new t[4];
        c6.b.Companion.getClass();
        s.Companion.getClass();
        tVarArr[0] = (!i5.a.c("Dalvik", System.getProperty("java.vm.name")) || Build.VERSION.SDK_INT < 29) ? null : new c6.b();
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
        this.f2981c = arrayList2;
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
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object obj;
        i5.a.g("protocols", list);
        Iterator it = this.f2981c.iterator();
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
    public final String f(SSLSocket sSLSocket) {
        Object obj;
        Iterator it = this.f2981c.iterator();
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
    public final boolean h(String str) {
        i5.a.g("hostname", str);
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }
}
