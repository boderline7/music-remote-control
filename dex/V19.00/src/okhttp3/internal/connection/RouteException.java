package okhttp3.internal.connection;

import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class RouteException extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    public final IOException f8066a;

    /* renamed from: b, reason: collision with root package name */
    public IOException f8067b;

    public RouteException(IOException iOException) {
        super(iOException);
        this.f8066a = iOException;
        this.f8067b = iOException;
    }
}
