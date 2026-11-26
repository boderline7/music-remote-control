package g6;

import java.io.Closeable;
import java.io.Flushable;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface y extends Closeable, Flushable {
    d0 a();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    void z(h hVar, long j7);
}
