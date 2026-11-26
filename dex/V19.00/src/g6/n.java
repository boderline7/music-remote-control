package g6;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n implements a0 {

    /* renamed from: a, reason: collision with root package name */
    public byte f6578a;

    /* renamed from: b, reason: collision with root package name */
    public final t f6579b;

    /* renamed from: c, reason: collision with root package name */
    public final Inflater f6580c;

    /* renamed from: d, reason: collision with root package name */
    public final o f6581d;

    /* renamed from: e, reason: collision with root package name */
    public final CRC32 f6582e;

    public n(a0 a0Var) {
        i5.a.g("source", a0Var);
        t tVar = new t(a0Var);
        this.f6579b = tVar;
        Inflater inflater = new Inflater(true);
        this.f6580c = inflater;
        this.f6581d = new o(tVar, inflater);
        this.f6582e = new CRC32();
    }

    public static void A(int i7, int i8, String str) {
        if (i8 == i7) {
            return;
        }
        String format = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i8), Integer.valueOf(i7)}, 3));
        i5.a.f("format(this, *args)", format);
        throw new IOException(format);
    }

    public final void B(h hVar, long j7, long j8) {
        v vVar = hVar.f6572a;
        i5.a.d(vVar);
        while (true) {
            int i7 = vVar.f6598c;
            int i8 = vVar.f6597b;
            if (j7 < i7 - i8) {
                break;
            }
            j7 -= i7 - i8;
            vVar = vVar.f6601f;
            i5.a.d(vVar);
        }
        while (j8 > 0) {
            int min = (int) Math.min(vVar.f6598c - r6, j8);
            this.f6582e.update(vVar.f6596a, (int) (vVar.f6597b + j7), min);
            j8 -= min;
            vVar = vVar.f6601f;
            i5.a.d(vVar);
            j7 = 0;
        }
    }

    @Override // g6.a0
    public final d0 a() {
        return this.f6579b.f6593a.a();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f6581d.close();
    }

    @Override // g6.a0
    public final long k(h hVar, long j7) {
        t tVar;
        h hVar2;
        long j8;
        i5.a.g("sink", hVar);
        if (j7 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (j7 == 0) {
            return 0L;
        }
        byte b7 = this.f6578a;
        CRC32 crc32 = this.f6582e;
        t tVar2 = this.f6579b;
        if (b7 == 0) {
            tVar2.q(10L);
            h hVar3 = tVar2.f6594b;
            byte C = hVar3.C(3L);
            boolean z6 = ((C >> 1) & 1) == 1;
            if (z6) {
                B(tVar2.f6594b, 0L, 10L);
            }
            A(8075, tVar2.i(), "ID1ID2");
            tVar2.h(8L);
            if (((C >> 2) & 1) == 1) {
                tVar2.q(2L);
                if (z6) {
                    B(tVar2.f6594b, 0L, 2L);
                }
                short i7 = hVar3.i();
                long j9 = ((short) (((i7 & 255) << 8) | ((i7 & 65280) >>> 8))) & 65535;
                tVar2.q(j9);
                if (z6) {
                    B(tVar2.f6594b, 0L, j9);
                    j8 = j9;
                } else {
                    j8 = j9;
                }
                tVar2.h(j8);
            }
            if (((C >> 3) & 1) == 1) {
                hVar2 = hVar3;
                long A = tVar2.A((byte) 0, 0L, Long.MAX_VALUE);
                if (A == -1) {
                    throw new EOFException();
                }
                if (z6) {
                    tVar = tVar2;
                    B(tVar2.f6594b, 0L, A + 1);
                } else {
                    tVar = tVar2;
                }
                tVar.h(A + 1);
            } else {
                hVar2 = hVar3;
                tVar = tVar2;
            }
            if (((C >> 4) & 1) == 1) {
                long A2 = tVar.A((byte) 0, 0L, Long.MAX_VALUE);
                if (A2 == -1) {
                    throw new EOFException();
                }
                if (z6) {
                    B(tVar.f6594b, 0L, A2 + 1);
                }
                tVar.h(A2 + 1);
            }
            if (z6) {
                tVar.q(2L);
                short i8 = hVar2.i();
                A((short) (((i8 & 255) << 8) | ((i8 & 65280) >>> 8)), (short) crc32.getValue(), "FHCRC");
                crc32.reset();
            }
            this.f6578a = (byte) 1;
        } else {
            tVar = tVar2;
        }
        if (this.f6578a == 1) {
            long j10 = hVar.f6573b;
            long k7 = this.f6581d.k(hVar, j7);
            if (k7 != -1) {
                B(hVar, j10, k7);
                return k7;
            }
            this.f6578a = (byte) 2;
        }
        if (this.f6578a != 2) {
            return -1L;
        }
        A(tVar.B(), (int) crc32.getValue(), "CRC");
        A(tVar.B(), (int) this.f6580c.getBytesWritten(), "ISIZE");
        this.f6578a = (byte) 3;
        if (tVar.t()) {
            return -1L;
        }
        throw new IOException("gzip finished without exhausting source");
    }
}
