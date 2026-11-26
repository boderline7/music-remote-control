package g6;

import android.support.v4.media.session.PlaybackStateCompat;
import com.kugou.auto.proxy.KgMusic;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h implements j, i, Cloneable, ByteChannel {

    /* renamed from: a, reason: collision with root package name */
    public v f6572a;

    /* renamed from: b, reason: collision with root package name */
    public long f6573b;

    public final long A() {
        long j7 = this.f6573b;
        if (j7 == 0) {
            return 0L;
        }
        v vVar = this.f6572a;
        i5.a.d(vVar);
        v vVar2 = vVar.f6602g;
        i5.a.d(vVar2);
        if (vVar2.f6598c < 8192 && vVar2.f6600e) {
            j7 -= r3 - vVar2.f6597b;
        }
        return j7;
    }

    public final void B(h hVar, long j7, long j8) {
        i5.a.g("out", hVar);
        n1.z.w(this.f6573b, j7, j8);
        if (j8 == 0) {
            return;
        }
        hVar.f6573b += j8;
        v vVar = this.f6572a;
        while (true) {
            i5.a.d(vVar);
            long j9 = vVar.f6598c - vVar.f6597b;
            if (j7 < j9) {
                break;
            }
            j7 -= j9;
            vVar = vVar.f6601f;
        }
        while (j8 > 0) {
            i5.a.d(vVar);
            v c7 = vVar.c();
            int i7 = c7.f6597b + ((int) j7);
            c7.f6597b = i7;
            c7.f6598c = Math.min(i7 + ((int) j8), c7.f6598c);
            v vVar2 = hVar.f6572a;
            if (vVar2 == null) {
                c7.f6602g = c7;
                c7.f6601f = c7;
                hVar.f6572a = c7;
            } else {
                v vVar3 = vVar2.f6602g;
                i5.a.d(vVar3);
                vVar3.b(c7);
            }
            j8 -= c7.f6598c - c7.f6597b;
            vVar = vVar.f6601f;
            j7 = 0;
        }
    }

    public final byte C(long j7) {
        n1.z.w(this.f6573b, j7, 1L);
        v vVar = this.f6572a;
        if (vVar == null) {
            i5.a.d(null);
            throw null;
        }
        long j8 = this.f6573b;
        if (j8 - j7 < j7) {
            while (j8 > j7) {
                vVar = vVar.f6602g;
                i5.a.d(vVar);
                j8 -= vVar.f6598c - vVar.f6597b;
            }
            return vVar.f6596a[(int) ((vVar.f6597b + j7) - j8)];
        }
        long j9 = 0;
        while (true) {
            int i7 = vVar.f6598c;
            int i8 = vVar.f6597b;
            long j10 = (i7 - i8) + j9;
            if (j10 > j7) {
                return vVar.f6596a[(int) ((i8 + j7) - j9)];
            }
            vVar = vVar.f6601f;
            i5.a.d(vVar);
            j9 = j10;
        }
    }

    public final long D(byte b7, long j7, long j8) {
        v vVar;
        long j9 = 0;
        if (0 > j7 || j7 > j8) {
            throw new IllegalArgumentException(("size=" + this.f6573b + " fromIndex=" + j7 + " toIndex=" + j8).toString());
        }
        long j10 = this.f6573b;
        if (j8 > j10) {
            j8 = j10;
        }
        if (j7 == j8 || (vVar = this.f6572a) == null) {
            return -1L;
        }
        if (j10 - j7 < j7) {
            while (j10 > j7) {
                vVar = vVar.f6602g;
                i5.a.d(vVar);
                j10 -= vVar.f6598c - vVar.f6597b;
            }
            while (j10 < j8) {
                int min = (int) Math.min(vVar.f6598c, (vVar.f6597b + j8) - j10);
                for (int i7 = (int) ((vVar.f6597b + j7) - j10); i7 < min; i7++) {
                    if (vVar.f6596a[i7] == b7) {
                        return (i7 - vVar.f6597b) + j10;
                    }
                }
                j10 += vVar.f6598c - vVar.f6597b;
                vVar = vVar.f6601f;
                i5.a.d(vVar);
                j7 = j10;
            }
            return -1L;
        }
        while (true) {
            long j11 = (vVar.f6598c - vVar.f6597b) + j9;
            if (j11 > j7) {
                break;
            }
            vVar = vVar.f6601f;
            i5.a.d(vVar);
            j9 = j11;
        }
        while (j9 < j8) {
            int min2 = (int) Math.min(vVar.f6598c, (vVar.f6597b + j8) - j9);
            for (int i8 = (int) ((vVar.f6597b + j7) - j9); i8 < min2; i8++) {
                if (vVar.f6596a[i8] == b7) {
                    return (i8 - vVar.f6597b) + j9;
                }
            }
            j9 += vVar.f6598c - vVar.f6597b;
            vVar = vVar.f6601f;
            i5.a.d(vVar);
            j7 = j9;
        }
        return -1L;
    }

    public final long E(l lVar) {
        int i7;
        int i8;
        i5.a.g("targetBytes", lVar);
        v vVar = this.f6572a;
        if (vVar == null) {
            return -1L;
        }
        long j7 = this.f6573b;
        long j8 = 0;
        byte[] bArr = lVar.f6574a;
        if (j7 < 0) {
            while (j7 > 0) {
                vVar = vVar.f6602g;
                i5.a.d(vVar);
                j7 -= vVar.f6598c - vVar.f6597b;
            }
            if (bArr.length == 2) {
                byte b7 = bArr[0];
                byte b8 = bArr[1];
                while (j7 < this.f6573b) {
                    i7 = (int) ((vVar.f6597b + j8) - j7);
                    int i9 = vVar.f6598c;
                    while (i7 < i9) {
                        byte b9 = vVar.f6596a[i7];
                        if (b9 != b7 && b9 != b8) {
                            i7++;
                        }
                        i8 = vVar.f6597b;
                    }
                    j8 = (vVar.f6598c - vVar.f6597b) + j7;
                    vVar = vVar.f6601f;
                    i5.a.d(vVar);
                    j7 = j8;
                }
                return -1L;
            }
            while (j7 < this.f6573b) {
                i7 = (int) ((vVar.f6597b + j8) - j7);
                int i10 = vVar.f6598c;
                while (i7 < i10) {
                    byte b10 = vVar.f6596a[i7];
                    for (byte b11 : bArr) {
                        if (b10 == b11) {
                            i8 = vVar.f6597b;
                        }
                    }
                    i7++;
                }
                j8 = (vVar.f6598c - vVar.f6597b) + j7;
                vVar = vVar.f6601f;
                i5.a.d(vVar);
                j7 = j8;
            }
            return -1L;
        }
        j7 = 0;
        while (true) {
            long j9 = (vVar.f6598c - vVar.f6597b) + j7;
            if (j9 > 0) {
                break;
            }
            vVar = vVar.f6601f;
            i5.a.d(vVar);
            j7 = j9;
        }
        if (bArr.length == 2) {
            byte b12 = bArr[0];
            byte b13 = bArr[1];
            while (j7 < this.f6573b) {
                i7 = (int) ((vVar.f6597b + j8) - j7);
                int i11 = vVar.f6598c;
                while (i7 < i11) {
                    byte b14 = vVar.f6596a[i7];
                    if (b14 != b12 && b14 != b13) {
                        i7++;
                    }
                    i8 = vVar.f6597b;
                }
                j8 = (vVar.f6598c - vVar.f6597b) + j7;
                vVar = vVar.f6601f;
                i5.a.d(vVar);
                j7 = j8;
            }
            return -1L;
        }
        while (j7 < this.f6573b) {
            i7 = (int) ((vVar.f6597b + j8) - j7);
            int i12 = vVar.f6598c;
            while (i7 < i12) {
                byte b15 = vVar.f6596a[i7];
                for (byte b16 : bArr) {
                    if (b15 == b16) {
                        i8 = vVar.f6597b;
                    }
                }
                i7++;
            }
            j8 = (vVar.f6598c - vVar.f6597b) + j7;
            vVar = vVar.f6601f;
            i5.a.d(vVar);
            j7 = j8;
        }
        return -1L;
        return (i7 - i8) + j7;
    }

    public final int F(byte[] bArr, int i7, int i8) {
        i5.a.g("sink", bArr);
        n1.z.w(bArr.length, i7, i8);
        v vVar = this.f6572a;
        if (vVar == null) {
            return -1;
        }
        int min = Math.min(i8, vVar.f6598c - vVar.f6597b);
        int i9 = vVar.f6597b;
        c5.l.y1(i7, i9, i9 + min, vVar.f6596a, bArr);
        int i10 = vVar.f6597b + min;
        vVar.f6597b = i10;
        this.f6573b -= min;
        if (i10 == vVar.f6598c) {
            this.f6572a = vVar.a();
            w.a(vVar);
        }
        return min;
    }

    public final byte[] G(long j7) {
        if (j7 < 0 || j7 > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j7).toString());
        }
        if (this.f6573b < j7) {
            throw new EOFException();
        }
        int i7 = (int) j7;
        byte[] bArr = new byte[i7];
        int i8 = 0;
        while (i8 < i7) {
            int F = F(bArr, i8, i7 - i8);
            if (F == -1) {
                throw new EOFException();
            }
            i8 += F;
        }
        return bArr;
    }

    public final String H(long j7, Charset charset) {
        i5.a.g("charset", charset);
        if (j7 < 0 || j7 > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j7).toString());
        }
        if (this.f6573b < j7) {
            throw new EOFException();
        }
        if (j7 == 0) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        v vVar = this.f6572a;
        i5.a.d(vVar);
        int i7 = vVar.f6597b;
        if (i7 + j7 > vVar.f6598c) {
            return new String(G(j7), charset);
        }
        int i8 = (int) j7;
        String str = new String(vVar.f6596a, i7, i8, charset);
        int i9 = vVar.f6597b + i8;
        vVar.f6597b = i9;
        this.f6573b -= j7;
        if (i9 == vVar.f6598c) {
            this.f6572a = vVar.a();
            w.a(vVar);
        }
        return str;
    }

    public final l I(int i7) {
        if (i7 == 0) {
            return l.EMPTY;
        }
        n1.z.w(this.f6573b, 0L, i7);
        v vVar = this.f6572a;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            i5.a.d(vVar);
            int i11 = vVar.f6598c;
            int i12 = vVar.f6597b;
            if (i11 == i12) {
                throw new AssertionError("s.limit == s.pos");
            }
            i9 += i11 - i12;
            i10++;
            vVar = vVar.f6601f;
        }
        byte[][] bArr = new byte[i10][];
        int[] iArr = new int[i10 * 2];
        v vVar2 = this.f6572a;
        int i13 = 0;
        while (i8 < i7) {
            i5.a.d(vVar2);
            bArr[i13] = vVar2.f6596a;
            i8 += vVar2.f6598c - vVar2.f6597b;
            iArr[i13] = Math.min(i8, i7);
            iArr[i13 + i10] = vVar2.f6597b;
            vVar2.f6599d = true;
            i13++;
            vVar2 = vVar2.f6601f;
        }
        return new x(bArr, iArr);
    }

    public final v J(int i7) {
        if (i7 < 1 || i7 > 8192) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        v vVar = this.f6572a;
        if (vVar == null) {
            v b7 = w.b();
            this.f6572a = b7;
            b7.f6602g = b7;
            b7.f6601f = b7;
            return b7;
        }
        v vVar2 = vVar.f6602g;
        i5.a.d(vVar2);
        if (vVar2.f6598c + i7 <= 8192 && vVar2.f6600e) {
            return vVar2;
        }
        v b8 = w.b();
        vVar2.b(b8);
        return b8;
    }

    public final void K(l lVar) {
        i5.a.g("byteString", lVar);
        lVar.k(this, lVar.c());
    }

    public final void L(byte[] bArr, int i7, int i8) {
        i5.a.g("source", bArr);
        long j7 = i8;
        n1.z.w(bArr.length, i7, j7);
        int i9 = i8 + i7;
        while (i7 < i9) {
            v J = J(1);
            int min = Math.min(i9 - i7, 8192 - J.f6598c);
            int i10 = i7 + min;
            c5.l.y1(J.f6598c, i7, i10, bArr, J.f6596a);
            J.f6598c += min;
            i7 = i10;
        }
        this.f6573b += j7;
    }

    public final long M(a0 a0Var) {
        i5.a.g("source", a0Var);
        long j7 = 0;
        while (true) {
            long k7 = a0Var.k(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (k7 == -1) {
                return j7;
            }
            j7 += k7;
        }
    }

    public final void N(int i7) {
        v J = J(1);
        int i8 = J.f6598c;
        J.f6598c = i8 + 1;
        J.f6596a[i8] = (byte) i7;
        this.f6573b++;
    }

    public final void O(long j7) {
        if (j7 == 0) {
            N(48);
            return;
        }
        long j8 = (j7 >>> 1) | j7;
        long j9 = j8 | (j8 >>> 2);
        long j10 = j9 | (j9 >>> 4);
        long j11 = j10 | (j10 >>> 8);
        long j12 = j11 | (j11 >>> 16);
        long j13 = j12 | (j12 >>> 32);
        long j14 = j13 - ((j13 >>> 1) & 6148914691236517205L);
        long j15 = ((j14 >>> 2) & 3689348814741910323L) + (j14 & 3689348814741910323L);
        long j16 = ((j15 >>> 4) + j15) & 1085102592571150095L;
        long j17 = j16 + (j16 >>> 8);
        long j18 = j17 + (j17 >>> 16);
        int i7 = (int) ((((j18 & 63) + ((j18 >>> 32) & 63)) + 3) / 4);
        v J = J(i7);
        int i8 = J.f6598c;
        for (int i9 = (i8 + i7) - 1; i9 >= i8; i9--) {
            J.f6596a[i9] = h6.a.f6769a[(int) (15 & j7)];
            j7 >>>= 4;
        }
        J.f6598c += i7;
        this.f6573b += i7;
    }

    public final void P(int i7) {
        v J = J(4);
        int i8 = J.f6598c;
        byte[] bArr = J.f6596a;
        bArr[i8] = (byte) ((i7 >>> 24) & 255);
        bArr[i8 + 1] = (byte) ((i7 >>> 16) & 255);
        bArr[i8 + 2] = (byte) ((i7 >>> 8) & 255);
        bArr[i8 + 3] = (byte) (i7 & 255);
        J.f6598c = i8 + 4;
        this.f6573b += 4;
    }

    public final void Q(int i7) {
        v J = J(2);
        int i8 = J.f6598c;
        byte[] bArr = J.f6596a;
        bArr[i8] = (byte) ((i7 >>> 8) & 255);
        bArr[i8 + 1] = (byte) (i7 & 255);
        J.f6598c = i8 + 2;
        this.f6573b += 2;
    }

    public final void R(int i7, int i8, String str) {
        char charAt;
        i5.a.g("string", str);
        if (i7 < 0) {
            throw new IllegalArgumentException(a.a.d("beginIndex < 0: ", i7).toString());
        }
        if (i8 < i7) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i8 + " < " + i7).toString());
        }
        if (i8 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i8 + " > " + str.length()).toString());
        }
        while (i7 < i8) {
            char charAt2 = str.charAt(i7);
            if (charAt2 < 128) {
                v J = J(1);
                int i9 = J.f6598c - i7;
                int min = Math.min(i8, 8192 - i9);
                int i10 = i7 + 1;
                byte[] bArr = J.f6596a;
                bArr[i7 + i9] = (byte) charAt2;
                while (true) {
                    i7 = i10;
                    if (i7 >= min || (charAt = str.charAt(i7)) >= 128) {
                        break;
                    }
                    i10 = i7 + 1;
                    bArr[i7 + i9] = (byte) charAt;
                }
                int i11 = J.f6598c;
                int i12 = (i9 + i7) - i11;
                J.f6598c = i11 + i12;
                this.f6573b += i12;
            } else {
                if (charAt2 < 2048) {
                    v J2 = J(2);
                    int i13 = J2.f6598c;
                    byte b7 = (byte) ((charAt2 >> 6) | z1.c.AND_LONG_2ADDR);
                    byte[] bArr2 = J2.f6596a;
                    bArr2[i13] = b7;
                    bArr2[i13 + 1] = (byte) ((charAt2 & '?') | 128);
                    J2.f6598c = i13 + 2;
                    this.f6573b += 2;
                } else if (charAt2 < 55296 || charAt2 > 57343) {
                    v J3 = J(3);
                    int i14 = J3.f6598c;
                    byte b8 = (byte) ((charAt2 >> '\f') | z1.c.SHL_INT_LIT8);
                    byte[] bArr3 = J3.f6596a;
                    bArr3[i14] = b8;
                    bArr3[i14 + 1] = (byte) ((63 & (charAt2 >> 6)) | 128);
                    bArr3[i14 + 2] = (byte) ((charAt2 & '?') | 128);
                    J3.f6598c = i14 + 3;
                    this.f6573b += 3;
                } else {
                    int i15 = i7 + 1;
                    char charAt3 = i15 < i8 ? str.charAt(i15) : (char) 0;
                    if (charAt2 > 56319 || 56320 > charAt3 || charAt3 >= 57344) {
                        N(63);
                        i7 = i15;
                    } else {
                        int i16 = (((charAt2 & 1023) << 10) | (charAt3 & 1023)) + 65536;
                        v J4 = J(4);
                        int i17 = J4.f6598c;
                        byte b9 = (byte) ((i16 >> 18) | KgMusic.imgUrlSize240);
                        byte[] bArr4 = J4.f6596a;
                        bArr4[i17] = b9;
                        bArr4[i17 + 1] = (byte) (((i16 >> 12) & 63) | 128);
                        bArr4[i17 + 2] = (byte) (((i16 >> 6) & 63) | 128);
                        bArr4[i17 + 3] = (byte) ((i16 & 63) | 128);
                        J4.f6598c = i17 + 4;
                        this.f6573b += 4;
                        i7 += 2;
                    }
                }
                i7++;
            }
        }
    }

    public final void S(String str) {
        i5.a.g("string", str);
        R(0, str.length(), str);
    }

    public final void T(int i7) {
        String str;
        if (i7 < 128) {
            N(i7);
            return;
        }
        if (i7 < 2048) {
            v J = J(2);
            int i8 = J.f6598c;
            byte b7 = (byte) ((i7 >> 6) | z1.c.AND_LONG_2ADDR);
            byte[] bArr = J.f6596a;
            bArr[i8] = b7;
            bArr[i8 + 1] = (byte) ((i7 & 63) | 128);
            J.f6598c = i8 + 2;
            this.f6573b += 2;
            return;
        }
        if (55296 <= i7 && i7 < 57344) {
            N(63);
            return;
        }
        if (i7 < 65536) {
            v J2 = J(3);
            int i9 = J2.f6598c;
            byte b8 = (byte) ((i7 >> 12) | z1.c.SHL_INT_LIT8);
            byte[] bArr2 = J2.f6596a;
            bArr2[i9] = b8;
            bArr2[i9 + 1] = (byte) (((i7 >> 6) & 63) | 128);
            bArr2[i9 + 2] = (byte) ((i7 & 63) | 128);
            J2.f6598c = i9 + 3;
            this.f6573b += 3;
            return;
        }
        if (i7 <= 1114111) {
            v J3 = J(4);
            int i10 = J3.f6598c;
            byte b9 = (byte) ((i7 >> 18) | KgMusic.imgUrlSize240);
            byte[] bArr3 = J3.f6596a;
            bArr3[i10] = b9;
            bArr3[i10 + 1] = (byte) (((i7 >> 12) & 63) | 128);
            bArr3[i10 + 2] = (byte) (((i7 >> 6) & 63) | 128);
            bArr3[i10 + 3] = (byte) ((i7 & 63) | 128);
            J3.f6598c = i10 + 4;
            this.f6573b += 4;
            return;
        }
        StringBuilder sb = new StringBuilder("Unexpected code point: 0x");
        if (i7 != 0) {
            char[] cArr = h6.b.f6770a;
            int i11 = 0;
            char[] cArr2 = {cArr[(i7 >> 28) & 15], cArr[(i7 >> 24) & 15], cArr[(i7 >> 20) & 15], cArr[(i7 >> 16) & 15], cArr[(i7 >> 12) & 15], cArr[(i7 >> 8) & 15], cArr[(i7 >> 4) & 15], cArr[i7 & 15]};
            while (i11 < 8 && cArr2[i11] == '0') {
                i11++;
            }
            c5.g.Companion.getClass();
            if (i11 < 0) {
                throw new IndexOutOfBoundsException(a.a.e("startIndex: ", i11, ", endIndex: 8, size: 8"));
            }
            if (i11 > 8) {
                throw new IllegalArgumentException(a.a.e("startIndex: ", i11, " > endIndex: 8"));
            }
            str = new String(cArr2, i11, 8 - i11);
        } else {
            str = "0";
        }
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    @Override // g6.a0
    public final d0 a() {
        return d0.NONE;
    }

    @Override // g6.i
    public final i b(byte[] bArr) {
        i5.a.g("source", bArr);
        L(bArr, 0, bArr.length);
        return this;
    }

    @Override // g6.i
    public final /* bridge */ /* synthetic */ i c(byte[] bArr, int i7, int i8) {
        L(bArr, i7, i8);
        return this;
    }

    public final Object clone() {
        h hVar = new h();
        if (this.f6573b != 0) {
            v vVar = this.f6572a;
            i5.a.d(vVar);
            v c7 = vVar.c();
            hVar.f6572a = c7;
            c7.f6602g = c7;
            c7.f6601f = c7;
            for (v vVar2 = vVar.f6601f; vVar2 != vVar; vVar2 = vVar2.f6601f) {
                v vVar3 = c7.f6602g;
                i5.a.d(vVar3);
                i5.a.d(vVar2);
                vVar3.b(vVar2.c());
            }
            hVar.f6573b = this.f6573b;
        }
        return hVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, g6.y
    public final void close() {
    }

    @Override // g6.j
    public final int d(r rVar) {
        i5.a.g("options", rVar);
        int b7 = h6.a.b(this, rVar, false);
        if (b7 == -1) {
            return -1;
        }
        h(rVar.f6588a[b7].c());
        return b7;
    }

    @Override // g6.i
    public final /* bridge */ /* synthetic */ i e(long j7) {
        O(j7);
        return this;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof h) {
                long j7 = this.f6573b;
                h hVar = (h) obj;
                if (j7 == hVar.f6573b) {
                    if (j7 != 0) {
                        v vVar = this.f6572a;
                        i5.a.d(vVar);
                        v vVar2 = hVar.f6572a;
                        i5.a.d(vVar2);
                        int i7 = vVar.f6597b;
                        int i8 = vVar2.f6597b;
                        long j8 = 0;
                        while (j8 < this.f6573b) {
                            long min = Math.min(vVar.f6598c - i7, vVar2.f6598c - i8);
                            long j9 = 0;
                            while (j9 < min) {
                                int i9 = i7 + 1;
                                byte b7 = vVar.f6596a[i7];
                                int i10 = i8 + 1;
                                if (b7 == vVar2.f6596a[i8]) {
                                    j9++;
                                    i8 = i10;
                                    i7 = i9;
                                }
                            }
                            if (i7 == vVar.f6598c) {
                                v vVar3 = vVar.f6601f;
                                i5.a.d(vVar3);
                                i7 = vVar3.f6597b;
                                vVar = vVar3;
                            }
                            if (i8 == vVar2.f6598c) {
                                vVar2 = vVar2.f6601f;
                                i5.a.d(vVar2);
                                i8 = vVar2.f6597b;
                            }
                            j8 += min;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // g6.j
    public final l f(long j7) {
        if (j7 < 0 || j7 > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j7).toString());
        }
        if (this.f6573b < j7) {
            throw new EOFException();
        }
        if (j7 < PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            return new l(G(j7));
        }
        l I = I((int) j7);
        h(j7);
        return I;
    }

    @Override // g6.i, g6.y, java.io.Flushable
    public final void flush() {
    }

    @Override // g6.j
    public final String g(long j7) {
        if (j7 < 0) {
            throw new IllegalArgumentException(("limit < 0: " + j7).toString());
        }
        long j8 = j7 != Long.MAX_VALUE ? j7 + 1 : Long.MAX_VALUE;
        long D = D((byte) 10, 0L, j8);
        if (D != -1) {
            return h6.a.a(this, D);
        }
        if (j8 < this.f6573b && C(j8 - 1) == 13 && C(j8) == 10) {
            return h6.a.a(this, j8);
        }
        h hVar = new h();
        B(hVar, 0L, Math.min(32, this.f6573b));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f6573b, j7) + " content=" + hVar.f(hVar.f6573b).d() + (char) 8230);
    }

    @Override // g6.j
    public final void h(long j7) {
        while (j7 > 0) {
            v vVar = this.f6572a;
            if (vVar == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j7, vVar.f6598c - vVar.f6597b);
            long j8 = min;
            this.f6573b -= j8;
            j7 -= j8;
            int i7 = vVar.f6597b + min;
            vVar.f6597b = i7;
            if (i7 == vVar.f6598c) {
                this.f6572a = vVar.a();
                w.a(vVar);
            }
        }
    }

    public final int hashCode() {
        v vVar = this.f6572a;
        if (vVar == null) {
            return 0;
        }
        int i7 = 1;
        do {
            int i8 = vVar.f6598c;
            for (int i9 = vVar.f6597b; i9 < i8; i9++) {
                i7 = (i7 * 31) + vVar.f6596a[i9];
            }
            vVar = vVar.f6601f;
            i5.a.d(vVar);
        } while (vVar != this.f6572a);
        return i7;
    }

    @Override // g6.j
    public final short i() {
        if (this.f6573b < 2) {
            throw new EOFException();
        }
        v vVar = this.f6572a;
        i5.a.d(vVar);
        int i7 = vVar.f6597b;
        int i8 = vVar.f6598c;
        if (i8 - i7 < 2) {
            return (short) (((y() & 255) << 8) | (y() & 255));
        }
        int i9 = i7 + 1;
        byte[] bArr = vVar.f6596a;
        int i10 = (bArr[i7] & 255) << 8;
        int i11 = i7 + 2;
        int i12 = (bArr[i9] & 255) | i10;
        this.f6573b -= 2;
        if (i11 == i8) {
            this.f6572a = vVar.a();
            w.a(vVar);
        } else {
            vVar.f6597b = i11;
        }
        return (short) i12;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    @Override // g6.i
    public final /* bridge */ /* synthetic */ i j(int i7) {
        Q(i7);
        return this;
    }

    @Override // g6.a0
    public final long k(h hVar, long j7) {
        i5.a.g("sink", hVar);
        if (j7 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        long j8 = this.f6573b;
        if (j8 == 0) {
            return -1L;
        }
        if (j7 > j8) {
            j7 = j8;
        }
        hVar.z(this, j7);
        return j7;
    }

    @Override // g6.i
    public final /* bridge */ /* synthetic */ i l(l lVar) {
        K(lVar);
        return this;
    }

    @Override // g6.j
    public final long m(d dVar) {
        long j7 = this.f6573b;
        if (j7 > 0) {
            dVar.z(this, j7);
        }
        return j7;
    }

    @Override // g6.j
    public final int n() {
        if (this.f6573b < 4) {
            throw new EOFException();
        }
        v vVar = this.f6572a;
        i5.a.d(vVar);
        int i7 = vVar.f6597b;
        int i8 = vVar.f6598c;
        if (i8 - i7 < 4) {
            return ((y() & 255) << 24) | ((y() & 255) << 16) | ((y() & 255) << 8) | (y() & 255);
        }
        byte[] bArr = vVar.f6596a;
        int i9 = i7 + 3;
        int i10 = ((bArr[i7 + 1] & 255) << 16) | ((bArr[i7] & 255) << 24) | ((bArr[i7 + 2] & 255) << 8);
        int i11 = i7 + 4;
        int i12 = i10 | (bArr[i9] & 255);
        this.f6573b -= 4;
        if (i11 == i8) {
            this.f6572a = vVar.a();
            w.a(vVar);
        } else {
            vVar.f6597b = i11;
        }
        return i12;
    }

    @Override // g6.i
    public final /* bridge */ /* synthetic */ i o(int i7) {
        P(i7);
        return this;
    }

    @Override // g6.j
    public final String p() {
        return g(Long.MAX_VALUE);
    }

    @Override // g6.j
    public final void q(long j7) {
        if (this.f6573b < j7) {
            throw new EOFException();
        }
    }

    @Override // g6.i
    public final /* bridge */ /* synthetic */ i r(String str) {
        S(str);
        return this;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        i5.a.g("sink", byteBuffer);
        v vVar = this.f6572a;
        if (vVar == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), vVar.f6598c - vVar.f6597b);
        byteBuffer.put(vVar.f6596a, vVar.f6597b, min);
        int i7 = vVar.f6597b + min;
        vVar.f6597b = i7;
        this.f6573b -= min;
        if (i7 == vVar.f6598c) {
            this.f6572a = vVar.a();
            w.a(vVar);
        }
        return min;
    }

    @Override // g6.j
    public final h s() {
        return this;
    }

    @Override // g6.j
    public final boolean t() {
        return this.f6573b == 0;
    }

    public final String toString() {
        long j7 = this.f6573b;
        if (j7 <= 2147483647L) {
            return I((int) j7).toString();
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + this.f6573b).toString());
    }

    @Override // g6.i
    public final /* bridge */ /* synthetic */ i u(int i7) {
        N(i7);
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a6 A[EDGE_INSN: B:40:0x00a6->B:37:0x00a6 BREAK  A[LOOP:0: B:4:0x000c->B:39:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009e  */
    @Override // g6.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long v() {
        /*
            r14 = this;
            long r0 = r14.f6573b
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lad
            r0 = 0
            r1 = r0
            r6 = r1
            r4 = r2
        Lc:
            g6.v r7 = r14.f6572a
            i5.a.d(r7)
            int r8 = r7.f6597b
            int r9 = r7.f6598c
        L15:
            if (r8 >= r9) goto L92
            byte[] r10 = r7.f6596a
            r10 = r10[r8]
            r11 = 48
            if (r10 < r11) goto L26
            r11 = 57
            if (r10 > r11) goto L26
            int r11 = r10 + (-48)
            goto L3b
        L26:
            r11 = 97
            if (r10 < r11) goto L31
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L31
            int r11 = r10 + (-87)
            goto L3b
        L31:
            r11 = 65
            if (r10 < r11) goto L6a
            r11 = 70
            if (r10 > r11) goto L6a
            int r11 = r10 + (-55)
        L3b:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L4b
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r1 = r1 + 1
            goto L15
        L4b:
            g6.h r0 = new g6.h
            r0.<init>()
            r0.O(r4)
            r0.N(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            long r2 = r0.f6573b
            java.nio.charset.Charset r4 = o5.a.UTF_8
            java.lang.String r0 = r0.H(r2, r4)
            java.lang.String r2 = "Number too large: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        L6a:
            r6 = 1
            if (r1 == 0) goto L6e
            goto L92
        L6e:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            r2 = 2
            char[] r2 = new char[r2]
            char[] r3 = h6.b.f6770a
            int r4 = r10 >> 4
            r4 = r4 & 15
            char r4 = r3[r4]
            r2[r0] = r4
            r0 = r10 & 15
            char r0 = r3[r0]
            r2[r6] = r0
            java.lang.String r0 = new java.lang.String
            r0.<init>(r2)
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        L92:
            if (r8 != r9) goto L9e
            g6.v r8 = r7.a()
            r14.f6572a = r8
            g6.w.a(r7)
            goto La0
        L9e:
            r7.f6597b = r8
        La0:
            if (r6 != 0) goto La6
            g6.v r7 = r14.f6572a
            if (r7 != 0) goto Lc
        La6:
            long r2 = r14.f6573b
            long r0 = (long) r1
            long r2 = r2 - r0
            r14.f6573b = r2
            return r4
        Lad:
            java.io.EOFException r0 = new java.io.EOFException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: g6.h.v():long");
    }

    @Override // g6.j
    public final String w(Charset charset) {
        return H(this.f6573b, charset);
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        i5.a.g("source", byteBuffer);
        int remaining = byteBuffer.remaining();
        int i7 = remaining;
        while (i7 > 0) {
            v J = J(1);
            int min = Math.min(i7, 8192 - J.f6598c);
            byteBuffer.get(J.f6596a, J.f6598c, min);
            i7 -= min;
            J.f6598c += min;
        }
        this.f6573b += remaining;
        return remaining;
    }

    @Override // g6.j
    public final g x() {
        return new g(this, 0);
    }

    @Override // g6.j
    public final byte y() {
        if (this.f6573b == 0) {
            throw new EOFException();
        }
        v vVar = this.f6572a;
        i5.a.d(vVar);
        int i7 = vVar.f6597b;
        int i8 = vVar.f6598c;
        int i9 = i7 + 1;
        byte b7 = vVar.f6596a[i7];
        this.f6573b--;
        if (i9 == i8) {
            this.f6572a = vVar.a();
            w.a(vVar);
        } else {
            vVar.f6597b = i9;
        }
        return b7;
    }

    @Override // g6.y
    public final void z(h hVar, long j7) {
        v b7;
        i5.a.g("source", hVar);
        if (hVar == this) {
            throw new IllegalArgumentException("source == this".toString());
        }
        n1.z.w(hVar.f6573b, 0L, j7);
        while (j7 > 0) {
            v vVar = hVar.f6572a;
            i5.a.d(vVar);
            int i7 = vVar.f6598c;
            v vVar2 = hVar.f6572a;
            i5.a.d(vVar2);
            long j8 = i7 - vVar2.f6597b;
            int i8 = 0;
            if (j7 < j8) {
                v vVar3 = this.f6572a;
                v vVar4 = vVar3 != null ? vVar3.f6602g : null;
                if (vVar4 != null && vVar4.f6600e) {
                    if ((vVar4.f6598c + j7) - (vVar4.f6599d ? 0 : vVar4.f6597b) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        v vVar5 = hVar.f6572a;
                        i5.a.d(vVar5);
                        vVar5.d(vVar4, (int) j7);
                        hVar.f6573b -= j7;
                        this.f6573b += j7;
                        return;
                    }
                }
                v vVar6 = hVar.f6572a;
                i5.a.d(vVar6);
                int i9 = (int) j7;
                if (i9 <= 0 || i9 > vVar6.f6598c - vVar6.f6597b) {
                    throw new IllegalArgumentException("byteCount out of range".toString());
                }
                if (i9 >= 1024) {
                    b7 = vVar6.c();
                } else {
                    b7 = w.b();
                    int i10 = vVar6.f6597b;
                    c5.l.y1(0, i10, i10 + i9, vVar6.f6596a, b7.f6596a);
                }
                b7.f6598c = b7.f6597b + i9;
                vVar6.f6597b += i9;
                v vVar7 = vVar6.f6602g;
                i5.a.d(vVar7);
                vVar7.b(b7);
                hVar.f6572a = b7;
            }
            v vVar8 = hVar.f6572a;
            i5.a.d(vVar8);
            long j9 = vVar8.f6598c - vVar8.f6597b;
            hVar.f6572a = vVar8.a();
            v vVar9 = this.f6572a;
            if (vVar9 == null) {
                this.f6572a = vVar8;
                vVar8.f6602g = vVar8;
                vVar8.f6601f = vVar8;
            } else {
                v vVar10 = vVar9.f6602g;
                i5.a.d(vVar10);
                vVar10.b(vVar8);
                v vVar11 = vVar8.f6602g;
                if (vVar11 == vVar8) {
                    throw new IllegalStateException("cannot compact".toString());
                }
                i5.a.d(vVar11);
                if (vVar11.f6600e) {
                    int i11 = vVar8.f6598c - vVar8.f6597b;
                    v vVar12 = vVar8.f6602g;
                    i5.a.d(vVar12);
                    int i12 = 8192 - vVar12.f6598c;
                    v vVar13 = vVar8.f6602g;
                    i5.a.d(vVar13);
                    if (!vVar13.f6599d) {
                        v vVar14 = vVar8.f6602g;
                        i5.a.d(vVar14);
                        i8 = vVar14.f6597b;
                    }
                    if (i11 <= i12 + i8) {
                        v vVar15 = vVar8.f6602g;
                        i5.a.d(vVar15);
                        vVar8.d(vVar15, i11);
                        vVar8.a();
                        w.a(vVar8);
                    }
                }
            }
            hVar.f6573b -= j9;
            this.f6573b += j9;
            j7 -= j9;
        }
    }
}
