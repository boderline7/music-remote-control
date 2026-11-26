package j2;

import java.io.File;
import java.io.RandomAccessFile;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b implements a {

    /* renamed from: a, reason: collision with root package name */
    public final RandomAccessFile f6952a;

    public b(File file) {
        this.f6952a = new RandomAccessFile(file, "r");
    }

    @Override // j2.a
    public final void a(long j7) {
        this.f6952a.seek(j7);
    }

    @Override // j2.a
    public final int b(byte[] bArr, int i7) {
        this.f6952a.readFully(bArr, 0, i7);
        return i7;
    }

    @Override // j2.a
    public final long c() {
        return this.f6952a.getFilePointer();
    }

    @Override // j2.a
    public final void close() {
        this.f6952a.close();
    }
}
