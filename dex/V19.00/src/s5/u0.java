package s5;

import java.nio.charset.Charset;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u0 {
    public static t0 a(String str, l0 l0Var) {
        i5.a.g("<this>", str);
        Charset charset = o5.a.UTF_8;
        if (l0Var != null) {
            k0 k0Var = l0.Companion;
            Charset a7 = l0Var.a(null);
            if (a7 == null) {
                l0.Companion.getClass();
                l0Var = k0.b(l0Var + "; charset=utf-8");
            } else {
                charset = a7;
            }
        }
        byte[] bytes = str.getBytes(charset);
        i5.a.f("this as java.lang.String).getBytes(charset)", bytes);
        return b(bytes, l0Var, 0, bytes.length);
    }

    public static t0 b(byte[] bArr, l0 l0Var, int i7, int i8) {
        i5.a.g("<this>", bArr);
        long length = bArr.length;
        long j7 = i7;
        long j8 = i8;
        byte[] bArr2 = t5.b.EMPTY_BYTE_ARRAY;
        if ((j7 | j8) < 0 || j7 > length || length - j7 < j8) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new t0(l0Var, bArr, i8, i7);
    }

    public static /* synthetic */ t0 c(u0 u0Var, byte[] bArr, l0 l0Var, int i7, int i8) {
        if ((i8 & 1) != 0) {
            l0Var = null;
        }
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        int length = (i8 & 4) != 0 ? bArr.length : 0;
        u0Var.getClass();
        return b(bArr, l0Var, i7, length);
    }
}
