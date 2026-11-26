package g6;

import java.nio.channels.WritableByteChannel;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface i extends y, WritableByteChannel {
    i b(byte[] bArr);

    i c(byte[] bArr, int i7, int i8);

    i e(long j7);

    @Override // g6.y, java.io.Flushable
    void flush();

    i j(int i7);

    i l(l lVar);

    i o(int i7);

    i r(String str);

    i u(int i7);
}
