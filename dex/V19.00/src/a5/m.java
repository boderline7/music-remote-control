package a5;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class m implements Runnable {

    /* renamed from: b, reason: collision with root package name */
    public IOException f351b;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ n f353d;

    /* renamed from: c, reason: collision with root package name */
    public boolean f352c = false;

    /* renamed from: a, reason: collision with root package name */
    public final int f350a = n.SOCKET_READ_TIMEOUT;

    public m(n nVar) {
        this.f353d = nVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InetSocketAddress inetSocketAddress;
        try {
            ServerSocket serverSocket = this.f353d.f360c;
            if (this.f353d.f358a != null) {
                n nVar = this.f353d;
                inetSocketAddress = new InetSocketAddress(nVar.f358a, nVar.f359b);
            } else {
                inetSocketAddress = new InetSocketAddress(this.f353d.f359b);
            }
            serverSocket.bind(inetSocketAddress);
            this.f352c = true;
            do {
                try {
                    Socket accept = this.f353d.f360c.accept();
                    int i7 = this.f350a;
                    if (i7 > 0) {
                        accept.setSoTimeout(i7);
                    }
                    InputStream inputStream = accept.getInputStream();
                    n nVar2 = this.f353d;
                    androidx.recyclerview.widget.i iVar = nVar2.f363f;
                    nVar2.getClass();
                    iVar.d(new a(nVar2, inputStream, accept));
                } catch (IOException e7) {
                    n.f357k.log(Level.FINE, "Communication with the client broken", (Throwable) e7);
                }
            } while (!this.f353d.f360c.isClosed());
        } catch (IOException e8) {
            this.f351b = e8;
        }
    }
}
