package a5;

import java.io.FilterOutputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends FilterOutputStream {
    public final void A() {
        ((FilterOutputStream) this).out.write("0\r\n\r\n".getBytes());
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(int i7) {
        write(new byte[]{(byte) i7}, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public final void write(byte[] bArr, int i7, int i8) {
        if (i8 == 0) {
            return;
        }
        ((FilterOutputStream) this).out.write(String.format("%x\r\n", Integer.valueOf(i8)).getBytes());
        ((FilterOutputStream) this).out.write(bArr, i7, i8);
        ((FilterOutputStream) this).out.write("\r\n".getBytes());
    }
}
