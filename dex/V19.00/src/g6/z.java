package g6;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class z extends f {

    /* renamed from: l, reason: collision with root package name */
    public final Socket f6608l;

    public z(Socket socket) {
        this.f6608l = socket;
    }

    @Override // g6.f
    public final IOException j(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // g6.f
    public final void k() {
        Socket socket = this.f6608l;
        try {
            socket.close();
        } catch (AssertionError e7) {
            if (!i5.a.m(e7)) {
                throw e7;
            }
            p.f6587a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e7);
        } catch (Exception e8) {
            p.f6587a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e8);
        }
    }
}
