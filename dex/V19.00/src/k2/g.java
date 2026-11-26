package k2;

import androidx.appcompat.widget.u;
import com.github.junrar.exception.CorruptHeaderException;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import n1.z;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends c {

    /* renamed from: w, reason: collision with root package name */
    public static final j6.a f7223w = j6.b.d(g.class);

    /* renamed from: j, reason: collision with root package name */
    public final int f7224j;

    /* renamed from: k, reason: collision with root package name */
    public final byte f7225k;

    /* renamed from: l, reason: collision with root package name */
    public final byte f7226l;

    /* renamed from: m, reason: collision with root package name */
    public final short f7227m;

    /* renamed from: n, reason: collision with root package name */
    public final int f7228n;

    /* renamed from: o, reason: collision with root package name */
    public final int f7229o;

    /* renamed from: p, reason: collision with root package name */
    public final byte[] f7230p;

    /* renamed from: q, reason: collision with root package name */
    public final String f7231q;

    /* renamed from: r, reason: collision with root package name */
    public final String f7232r;

    /* renamed from: s, reason: collision with root package name */
    public final byte[] f7233s;

    /* renamed from: t, reason: collision with root package name */
    public final byte[] f7234t;

    /* renamed from: u, reason: collision with root package name */
    public final long f7235u;

    /* renamed from: v, reason: collision with root package name */
    public final long f7236v;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    public g(c cVar, byte[] bArr) {
        super(cVar);
        short s6;
        byte[] bArr2;
        int i7;
        this.f7234t = new byte[8];
        long X0 = z.X0(bArr);
        byte b7 = bArr[4];
        if (!h.msdos.a(b7) && !h.os2.a(b7) && !h.win32.a(b7) && !h.unix.a(b7) && !h.macos.a(b7)) {
            h.beos.getClass();
        }
        this.f7224j = z.W0(bArr, 5);
        int W0 = z.W0(bArr, 9);
        this.f7225k = (byte) (this.f7225k | (bArr[13] & 255));
        this.f7226l = (byte) (this.f7226l | (bArr[14] & 255));
        short Y0 = z.Y0(bArr, 15);
        this.f7227m = Y0;
        z.W0(bArr, 17);
        int i8 = 21;
        if ((this.f7213d & 256) != 0) {
            this.f7228n = z.W0(bArr, 21);
            this.f7229o = z.W0(bArr, 25);
            i8 = 29;
        } else {
            this.f7228n = 0;
            this.f7229o = 0;
            if (X0 == -1) {
                this.f7229o = u.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
        }
        this.f7235u = ((this.f7235u | this.f7228n) << 32) | this.f7217h;
        this.f7236v = ((this.f7236v | this.f7229o) << 32) + X0;
        int i9 = Y0 > 4096 ? 4096 : Y0;
        this.f7227m = i9;
        if (i9 <= 0) {
            throw new CorruptHeaderException("Invalid file name with negative size");
        }
        byte[] bArr3 = new byte[i9];
        this.f7230p = bArr3;
        System.arraycopy(bArr, i8, bArr3, 0, i9);
        int i10 = i8 + i9;
        if (s.FileHeader.a(this.f7212c)) {
            int i11 = 1;
            if ((this.f7213d & 512) != 0) {
                int i12 = 0;
                while (true) {
                    bArr2 = this.f7230p;
                    if (i12 >= bArr2.length || bArr2[i12] == 0) {
                        break;
                    } else {
                        i12++;
                    }
                }
                this.f7231q = new String(bArr2, 0, i12);
                if (i12 != this.f7227m) {
                    int i13 = i12 + 1;
                    byte[] bArr4 = this.f7230p;
                    int i14 = i12 + 2;
                    int i15 = bArr4[i13] & 255;
                    StringBuilder sb = new StringBuilder();
                    int i16 = 0;
                    int i17 = 0;
                    int i18 = 0;
                    while (i14 < bArr4.length) {
                        if (i16 == 0) {
                            i16 = 8;
                            i17 = bArr4[i14] & 255;
                            i14++;
                        }
                        int i19 = i17 >>> 6;
                        if (i19 == 0) {
                            i7 = i14 + 1;
                            sb.append((char) (bArr4[i14] & 255));
                        } else if (i19 != i11) {
                            if (i19 == 2) {
                                sb.append((char) (((bArr4[i14 + 1] & 255) << 8) + (bArr4[i14] & 255)));
                                i18++;
                                i14 += 2;
                            } else if (i19 == 3) {
                                int i20 = i14 + 1;
                                byte b8 = bArr4[i14];
                                int i21 = b8 & 255;
                                if ((b8 & 128) != 0) {
                                    i14 += 2;
                                    int i22 = bArr4[i20] & 255;
                                    int i23 = (b8 & Byte.MAX_VALUE) + 2;
                                    int i24 = i18;
                                    while (i23 > 0 && i24 < bArr4.length) {
                                        sb.append((char) ((i15 << 8) + (((bArr4[i24] & 255) + i22) & 255)));
                                        i23--;
                                        i24++;
                                    }
                                    i18 = i24;
                                } else {
                                    int i25 = i21 + 2;
                                    int i26 = i18;
                                    while (i25 > 0 && i26 < bArr4.length) {
                                        sb.append((char) (bArr4[i26] & 255));
                                        i25--;
                                        i26++;
                                    }
                                    i18 = i26;
                                    i14 = i20;
                                }
                            }
                            i16 -= 2;
                            i17 = (i17 << 2) & 255;
                            i11 = 1;
                        } else {
                            i7 = i14 + 1;
                            sb.append((char) ((bArr4[i14] & 255) + (i15 << 8)));
                        }
                        i18++;
                        i14 = i7;
                        i16 -= 2;
                        i17 = (i17 << 2) & 255;
                        i11 = 1;
                    }
                    this.f7232r = sb.toString();
                } else {
                    this.f7232r = h0.FRAGMENT_ENCODE_SET;
                }
            } else {
                this.f7231q = new String(bArr3);
                this.f7232r = h0.FRAGMENT_ENCODE_SET;
            }
            try {
                new File(d()).getCanonicalPath();
            } catch (IOException unused) {
                throw new CorruptHeaderException("Invalid filename: " + d());
            }
        }
        if (s.NewSubHeader.a(this.f7212c)) {
            int i27 = (this.f7214e - 32) - this.f7227m;
            i27 = (this.f7213d & b.LHD_SALT) != 0 ? i27 - 8 : i27;
            if (i27 > 0) {
                this.f7233s = new byte[i27];
                for (int i28 = 0; i28 < i27; i28++) {
                    this.f7233s[i28] = bArr[i10];
                    i10++;
                }
            }
            if (Arrays.equals(l.SUBHEAD_TYPE_RR.f7243a, this.f7230p)) {
                byte[] bArr5 = this.f7233s;
                byte b9 = bArr5[8];
                byte b10 = bArr5[9];
                byte b11 = bArr5[10];
                byte b12 = bArr5[11];
            }
        }
        if ((this.f7213d & b.LHD_SALT) != 0) {
            for (int i29 = 0; i29 < 8; i29++) {
                this.f7234t[i29] = bArr[i10];
                i10++;
            }
        }
        FileTime fromMillis = FileTime.fromMillis(c(W0));
        if ((this.f7213d & b.LHD_EXTTIME) != 0) {
            if (i10 + 1 < bArr.length) {
                s6 = z.Y0(bArr, i10);
                i10 += 2;
            } else {
                f7223w.e(d());
                s6 = 0;
            }
            e(0, s6, bArr, e(4, s6, bArr, e(8, s6, bArr, e(12, s6, bArr, i10, fromMillis).f830b, null).f830b, null).f830b, null);
        }
    }

    public static long c(int i7) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, (i7 >>> 25) + 1980);
        calendar.set(2, ((i7 >>> 21) & 15) - 1);
        calendar.set(5, (i7 >>> 16) & 31);
        calendar.set(11, (i7 >>> 11) & 31);
        calendar.set(12, (i7 >>> 5) & 63);
        calendar.set(13, (i7 & 31) * 2);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static androidx.appcompat.app.n e(int i7, short s6, byte[] bArr, int i8, FileTime fileTime) {
        long seconds;
        int i9 = s6 >>> i7;
        if ((i9 & 8) == 0) {
            return new androidx.appcompat.app.n(i8, fileTime);
        }
        if (fileTime != null) {
            seconds = fileTime.to(TimeUnit.SECONDS);
        } else {
            seconds = TimeUnit.MILLISECONDS.toSeconds(c(z.W0(bArr, i8)));
            i8 += 4;
        }
        long j7 = 0;
        for (int i10 = 0; i10 < (i9 & 3); i10++) {
            j7 = (j7 >>> 8) | ((bArr[i8] & 255) << 16);
            i8++;
        }
        long j8 = j7 * 100;
        if ((i9 & 4) != 0) {
            j8 += TimeUnit.SECONDS.toNanos(1L);
        }
        return new androidx.appcompat.app.n(i8, FileTime.from(Instant.ofEpochSecond(seconds, j8)));
    }

    public final String d() {
        String str;
        return ((this.f7213d & 512) == 0 || (str = this.f7232r) == null || str.isEmpty()) ? this.f7231q : str;
    }

    public final String toString() {
        return super.toString();
    }
}
