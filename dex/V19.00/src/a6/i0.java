package a6;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i0 implements Closeable {
    public static final h0 Companion = new h0();

    /* renamed from: g, reason: collision with root package name */
    public static final Logger f437g = Logger.getLogger(h.class.getName());

    /* renamed from: a, reason: collision with root package name */
    public final g6.i f438a;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f439b;

    /* renamed from: c, reason: collision with root package name */
    public final g6.h f440c;

    /* renamed from: d, reason: collision with root package name */
    public int f441d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f442e;

    /* renamed from: f, reason: collision with root package name */
    public final f f443f;

    public i0(g6.i iVar, boolean z6) {
        this.f438a = iVar;
        this.f439b = z6;
        g6.h hVar = new g6.h();
        this.f440c = hVar;
        this.f441d = 16384;
        this.f443f = new f(hVar);
    }

    public final synchronized void A(o0 o0Var) {
        try {
            i5.a.g("peerSettings", o0Var);
            if (this.f442e) {
                throw new IOException("closed");
            }
            int i7 = this.f441d;
            int i8 = o0Var.f454a;
            if ((i8 & 32) != 0) {
                i7 = o0Var.f455b[5];
            }
            this.f441d = i7;
            if (((i8 & 2) != 0 ? o0Var.f455b[1] : -1) != -1) {
                f fVar = this.f443f;
                int i9 = (i8 & 2) != 0 ? o0Var.f455b[1] : -1;
                fVar.getClass();
                int min = Math.min(i9, 16384);
                int i10 = fVar.f403e;
                if (i10 != min) {
                    if (min < i10) {
                        fVar.f401c = Math.min(fVar.f401c, min);
                    }
                    fVar.f402d = true;
                    fVar.f403e = min;
                    int i11 = fVar.f407i;
                    if (min < i11) {
                        if (min == 0) {
                            d[] dVarArr = fVar.f404f;
                            c5.l.C1(dVarArr, null, 0, dVarArr.length);
                            fVar.f405g = fVar.f404f.length - 1;
                            fVar.f406h = 0;
                            fVar.f407i = 0;
                        } else {
                            fVar.a(i11 - min);
                        }
                    }
                }
            }
            C(0, 0, 4, 1);
            this.f438a.flush();
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void B(boolean z6, int i7, g6.h hVar, int i8) {
        if (this.f442e) {
            throw new IOException("closed");
        }
        C(i7, i8, 0, z6 ? 1 : 0);
        if (i8 > 0) {
            i5.a.d(hVar);
            this.f438a.z(hVar, i8);
        }
    }

    public final void C(int i7, int i8, int i9, int i10) {
        Level level = Level.FINE;
        Logger logger = f437g;
        if (logger.isLoggable(level)) {
            h.INSTANCE.getClass();
            logger.fine(h.a(i7, i8, i9, i10, false));
        }
        if (i8 > this.f441d) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.f441d + ": " + i8).toString());
        }
        if ((Integer.MIN_VALUE & i7) != 0) {
            throw new IllegalArgumentException(a.a.d("reserved bit set: ", i7).toString());
        }
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        g6.i iVar = this.f438a;
        i5.a.g("<this>", iVar);
        iVar.u((i8 >>> 16) & 255);
        iVar.u((i8 >>> 8) & 255);
        iVar.u(i8 & 255);
        iVar.u(i9 & 255);
        iVar.u(i10 & 255);
        iVar.o(i7 & androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public final synchronized void D(int i7, b bVar, byte[] bArr) {
        try {
            i5.a.g("errorCode", bVar);
            i5.a.g("debugData", bArr);
            if (this.f442e) {
                throw new IOException("closed");
            }
            if (bVar.f372a == -1) {
                throw new IllegalArgumentException("errorCode.httpCode == -1".toString());
            }
            C(0, bArr.length + 8, 7, 0);
            this.f438a.o(i7);
            this.f438a.o(bVar.f372a);
            if (!(bArr.length == 0)) {
                this.f438a.b(bArr);
            }
            this.f438a.flush();
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void E(int i7, int i8, boolean z6) {
        if (this.f442e) {
            throw new IOException("closed");
        }
        C(0, 8, 6, z6 ? 1 : 0);
        this.f438a.o(i7);
        this.f438a.o(i8);
        this.f438a.flush();
    }

    public final synchronized void F(int i7, b bVar) {
        i5.a.g("errorCode", bVar);
        if (this.f442e) {
            throw new IOException("closed");
        }
        if (bVar.f372a == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        C(i7, 4, 3, 0);
        this.f438a.o(bVar.f372a);
        this.f438a.flush();
    }

    public final synchronized void G(int i7, long j7) {
        if (this.f442e) {
            throw new IOException("closed");
        }
        if (j7 == 0 || j7 > 2147483647L) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j7).toString());
        }
        C(i7, 4, 8, 0);
        this.f438a.o((int) j7);
        this.f438a.flush();
    }

    public final void H(int i7, long j7) {
        while (j7 > 0) {
            long min = Math.min(this.f441d, j7);
            j7 -= min;
            C(i7, (int) min, 9, j7 == 0 ? 4 : 0);
            this.f438a.z(this.f440c, min);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.f442e = true;
        this.f438a.close();
    }

    public final synchronized void flush() {
        if (this.f442e) {
            throw new IOException("closed");
        }
        this.f438a.flush();
    }
}
