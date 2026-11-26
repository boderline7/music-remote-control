package c6;

import android.net.ssl.SSLSockets;
import android.os.Build;
import java.io.IOException;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements t {
    public static final a Companion = new a();

    @Override // c6.t
    public final boolean a(SSLSocket sSLSocket) {
        boolean isSupportedSocket;
        isSupportedSocket = SSLSockets.isSupportedSocket(sSLSocket);
        return isSupportedSocket;
    }

    @Override // c6.t
    public final String b(SSLSocket sSLSocket) {
        String applicationProtocol;
        applicationProtocol = sSLSocket.getApplicationProtocol();
        if (applicationProtocol == null || i5.a.c(applicationProtocol, h0.FRAGMENT_ENCODE_SET)) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // c6.t
    public final boolean c() {
        Companion.getClass();
        b6.s.Companion.getClass();
        return i5.a.c("Dalvik", System.getProperty("java.vm.name")) && Build.VERSION.SDK_INT >= 29;
    }

    @Override // c6.t
    public final void d(SSLSocket sSLSocket, String str, List list) {
        i5.a.g("protocols", list);
        try {
            SSLSockets.setUseSessionTickets(sSLSocket, true);
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            b6.s.Companion.getClass();
            sSLParameters.setApplicationProtocols((String[]) b6.r.a(list).toArray(new String[0]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalArgumentException e7) {
            throw new IOException("Android internal error", e7);
        }
    }
}
