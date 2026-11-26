package l2;

import com.github.junrar.exception.CrcErrorException;
import com.github.junrar.exception.InitDeciphererFailedException;
import java.io.EOFException;
import java.io.OutputStream;
import javax.crypto.Cipher;
import k2.g;
import k2.n;
import n1.z;
import y0.s;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public final h2.d f7277a;

    /* renamed from: b, reason: collision with root package name */
    public long f7278b;

    /* renamed from: c, reason: collision with root package name */
    public OutputStream f7279c;

    /* renamed from: d, reason: collision with root package name */
    public g f7280d;

    /* renamed from: e, reason: collision with root package name */
    public long f7281e;

    /* renamed from: f, reason: collision with root package name */
    public long f7282f;

    /* renamed from: g, reason: collision with root package name */
    public s f7283g;

    public a(h2.d dVar) {
        this.f7277a = dVar;
    }

    public final void a(g gVar) {
        long j7 = gVar.f7210a;
        h2.d dVar = this.f7277a;
        this.f7278b = gVar.f7235u;
        dVar.f6707a.a(j7 + gVar.a(dVar.C()));
        this.f7283g = new s(dVar.f6707a);
        this.f7280d = gVar;
        this.f7282f = -1L;
        if ((gVar.f7213d & 4) != 0) {
            try {
                Cipher q6 = z.q(dVar.f6717k, gVar.f7234t);
                s sVar = this.f7283g;
                sVar.f10008b = q6;
                sVar.f10007a = true;
            } catch (Exception e7) {
                throw new InitDeciphererFailedException(e7);
            }
        }
    }

    public final int b(byte[] bArr, int i7, int i8) {
        int i9;
        int i10 = 0;
        int i11 = 0;
        while (i8 > 0) {
            long j7 = i8;
            long j8 = this.f7278b;
            int i12 = j7 > j8 ? (int) j8 : i8;
            s sVar = this.f7283g;
            sVar.getClass();
            byte[] bArr2 = new byte[i12];
            int b7 = sVar.b(bArr2, i12);
            System.arraycopy(bArr2, 0, bArr, i7, i12);
            if (b7 < 0) {
                throw new EOFException();
            }
            if ((this.f7280d.f7213d & 2) != 0) {
                this.f7282f = i2.a.a((int) this.f7282f, bArr, i7, b7);
            }
            i10 += b7;
            i8 -= b7;
            i7 += b7;
            this.f7278b -= b7;
            h2.d dVar = this.f7277a;
            if (b7 > 0) {
                dVar.getClass();
            } else {
                dVar.getClass();
            }
            if (this.f7278b != 0 || (this.f7280d.f7213d & 2) == 0) {
                i11 = b7;
                break;
            }
            p2.a B = dVar.f6714h.B(dVar, dVar.f6715i);
            g gVar = this.f7280d;
            if (gVar.f7225k >= 20 && (i9 = gVar.f7224j) != -1 && this.f7282f != (~i9)) {
                throw new CrcErrorException();
            }
            dVar.G(B);
            g D = dVar.D();
            if (D == null) {
                return -1;
            }
            a(D);
            i11 = b7;
        }
        return i11 != -1 ? i10 : i11;
    }

    public final void c(byte[] bArr, int i7, int i8) {
        this.f7279c.write(bArr, i7, i8);
        if (this.f7277a.f6710d.f7242g != n.OLD) {
            this.f7281e = i2.a.a((int) this.f7281e, bArr, i7, i8);
            return;
        }
        short s6 = (short) this.f7281e;
        int[] iArr = i2.a.f6795a;
        int min = Math.min(bArr.length, i8);
        for (int i9 = 0; i9 < min; i9++) {
            short s7 = (short) (s6 + ((short) (bArr[i9] & 255)));
            s6 = (short) ((s7 >>> 15) | (s7 << 1));
        }
        this.f7281e = s6;
    }
}
