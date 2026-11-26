package c4;

import com.google.zxing.WriterException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class e {

    /* renamed from: c, reason: collision with root package name */
    public static final byte[] f3061c;

    /* renamed from: a, reason: collision with root package name */
    public static final byte[] f3059a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, o2.d.VMCF_USEFLAGS, 0, 0, 0};

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f3060b = {59, 60, 62, o2.d.VMCF_CHFLAGS, 91, 92, 93, 95, 96, 126, 33, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};

    /* renamed from: d, reason: collision with root package name */
    public static final byte[] f3062d = new byte[128];

    /* renamed from: e, reason: collision with root package name */
    public static final Charset f3063e = StandardCharsets.ISO_8859_1;

    static {
        byte[] bArr = new byte[128];
        f3061c = bArr;
        Arrays.fill(bArr, (byte) -1);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            byte[] bArr2 = f3059a;
            if (i8 >= bArr2.length) {
                break;
            }
            byte b7 = bArr2[i8];
            if (b7 > 0) {
                f3061c[b7] = (byte) i8;
            }
            i8++;
        }
        Arrays.fill(f3062d, (byte) -1);
        while (true) {
            byte[] bArr3 = f3060b;
            if (i7 >= bArr3.length) {
                return;
            }
            byte b8 = bArr3[i7];
            if (b8 > 0) {
                f3062d[b8] = (byte) i7;
            }
            i7++;
        }
    }

    public static void a(byte[] bArr, int i7, int i8, StringBuilder sb) {
        if (i7 == 1 && i8 == 0) {
            sb.append((char) 913);
        } else if (i7 % 6 == 0) {
            sb.append((char) 924);
        } else {
            sb.append((char) 901);
        }
        int i9 = 0;
        if (i7 >= 6) {
            char[] cArr = new char[5];
            int i10 = 0;
            while (i7 - i10 >= 6) {
                long j7 = 0;
                for (int i11 = 0; i11 < 6; i11++) {
                    j7 = (j7 << 8) + (bArr[i10 + i11] & 255);
                }
                for (int i12 = 0; i12 < 5; i12++) {
                    cArr[i12] = (char) (j7 % 900);
                    j7 /= 900;
                }
                for (int i13 = 4; i13 >= 0; i13--) {
                    sb.append(cArr[i13]);
                }
                i10 += 6;
            }
            i9 = i10;
        }
        while (i9 < i7) {
            sb.append((char) (bArr[i9] & 255));
            i9++;
        }
    }

    public static void b(int i7, int i8, int i9, y3.e eVar, StringBuilder sb) {
        int min = Math.min(i8 + i7, eVar.length());
        int i10 = i7;
        while (true) {
            if (i10 >= min || !eVar.a(i10)) {
                int i11 = i10;
                while (i11 < min && !eVar.a(i11)) {
                    i11++;
                }
                int i12 = i11 - i10;
                if (i12 <= 0) {
                    return;
                }
                byte[] bArr = new byte[i12];
                for (int i13 = i10; i13 < i11; i13++) {
                    bArr[i13 - i10] = (byte) (eVar.charAt(i13) & 255);
                }
                a(bArr, i12, i10 == i7 ? i9 : 1, sb);
                i10 = i11;
            } else {
                e(eVar.b(i10), sb);
                i10++;
            }
        }
    }

    public static void c(y3.e eVar, int i7, int i8, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i8 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900L);
        BigInteger valueOf2 = BigInteger.valueOf(0L);
        int i9 = 0;
        while (i9 < i8) {
            sb2.setLength(0);
            int min = Math.min(44, i8 - i9);
            StringBuilder sb3 = new StringBuilder("1");
            int i10 = i7 + i9;
            sb3.append((Object) eVar.subSequence(i10, i10 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i9 += min;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00fb A[EDGE_INSN: B:24:0x00fb->B:25:0x00fb BREAK  A[LOOP:0: B:2:0x000e->B:19:0x000e], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x000e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int d(int r17, int r18, int r19, y3.e r20, java.lang.StringBuilder r21) {
        /*
            Method dump skipped, instructions count: 299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: c4.e.d(int, int, int, y3.e, java.lang.StringBuilder):int");
    }

    public static void e(int i7, StringBuilder sb) {
        if (i7 >= 0 && i7 < 900) {
            sb.append((char) 927);
            sb.append((char) i7);
        } else if (i7 < 810900) {
            sb.append((char) 926);
            sb.append((char) ((i7 / 900) - 1));
            sb.append((char) (i7 % 900));
        } else {
            if (i7 >= 811800) {
                throw new WriterException(a.a.d("ECI number not in valid range from 0..811799, but was ", i7));
            }
            sb.append((char) 925);
            sb.append((char) (810900 - i7));
        }
    }

    public static boolean f(char c7) {
        return c7 == ' ' || (c7 >= 'a' && c7 <= 'z');
    }

    public static boolean g(char c7) {
        return c7 == ' ' || (c7 >= 'A' && c7 <= 'Z');
    }
}
