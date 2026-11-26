package e4;

import androidx.appcompat.app.n;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import p0.a0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    public static final int[] f6374a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* renamed from: b, reason: collision with root package name */
    public static final Charset f6375b = StandardCharsets.ISO_8859_1;

    /* JADX WARN: Removed duplicated region for block: B:18:0x004f A[LOOP:0: B:11:0x0024->B:18:0x004f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r8, d4.b r9, y3.a r10, java.nio.charset.Charset r11) {
        /*
            Method dump skipped, instructions count: 279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: e4.c.a(java.lang.String, d4.b, y3.a, java.nio.charset.Charset):void");
    }

    public static boolean b(String str) {
        byte[] bytes = str.getBytes(y3.g.SHIFT_JIS_CHARSET);
        int length = bytes.length;
        if (length % 2 != 0) {
            return false;
        }
        for (int i7 = 0; i7 < length; i7 += 2) {
            int i8 = bytes[i7] & 255;
            if ((i8 < 129 || i8 > 159) && (i8 < 224 || i8 > 235)) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(int i7, d4.c cVar, d4.a aVar) {
        int i8 = cVar.f6288c;
        n nVar = cVar.f6287b[aVar.ordinal()];
        int i9 = nVar.f830b;
        int i10 = 0;
        for (a0 a0Var : (a0[]) nVar.f831c) {
            i10 += a0Var.f8092a;
        }
        return i8 - (i10 * i9) >= (i7 + 7) / 8;
    }
}
