package androidx.fragment.app;

import java.io.Writer;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s1 extends Writer {

    /* renamed from: a, reason: collision with root package name */
    public final StringBuilder f2054a = new StringBuilder(128);

    public final void A() {
        StringBuilder sb = this.f2054a;
        if (sb.length() > 0) {
            sb.toString();
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        A();
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
        A();
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i7, int i8) {
        for (int i9 = 0; i9 < i8; i9++) {
            char c7 = cArr[i7 + i9];
            if (c7 == '\n') {
                A();
            } else {
                this.f2054a.append(c7);
            }
        }
    }
}
