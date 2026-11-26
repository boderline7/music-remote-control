package a5;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.logging.Level;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final InputStream f306a;

    /* renamed from: b, reason: collision with root package name */
    public final Socket f307b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ n f308c;

    public a(n nVar, InputStream inputStream, Socket socket) {
        this.f308c = nVar;
        this.f306a = inputStream;
        this.f307b = socket;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InputStream inputStream = this.f306a;
        n nVar = this.f308c;
        Socket socket = this.f307b;
        OutputStream outputStream = null;
        try {
            try {
                outputStream = socket.getOutputStream();
                nVar.f364g.getClass();
                f fVar = new f(this.f308c, new e(), this.f306a, outputStream, socket.getInetAddress());
                while (!socket.isClosed()) {
                    fVar.d();
                }
                n.d(outputStream);
                n.d(inputStream);
                n.d(socket);
            } catch (Exception e7) {
                if ((!(e7 instanceof SocketException) || !"NanoHttpd Shutdown".equals(e7.getMessage())) && !(e7 instanceof SocketTimeoutException)) {
                    n.f357k.log(Level.SEVERE, "Communication with the client broken, or an bug in the handler code", (Throwable) e7);
                }
                n.d(outputStream);
                n.d(inputStream);
                n.d(socket);
            }
            ((List) nVar.f363f.f2488c).remove(this);
        } catch (Throwable th) {
            n.d(outputStream);
            n.d(inputStream);
            n.d(socket);
            ((List) nVar.f363f.f2488c).remove(this);
            throw th;
        }
    }
}
