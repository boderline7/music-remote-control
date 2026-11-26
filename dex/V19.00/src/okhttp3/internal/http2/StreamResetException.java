package okhttp3.internal.http2;

import a6.b;
import java.io.IOException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class StreamResetException extends IOException {

    /* renamed from: a, reason: collision with root package name */
    public final b f8068a;

    public StreamResetException(b bVar) {
        super("stream was reset: " + bVar);
        this.f8068a = bVar;
    }
}
