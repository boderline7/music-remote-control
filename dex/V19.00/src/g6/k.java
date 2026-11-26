package g6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {
    public static l a(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 * 2;
            bArr[i7] = (byte) (h6.b.a(str.charAt(i8 + 1)) + (h6.b.a(str.charAt(i8)) << 4));
        }
        return new l(bArr);
    }

    public static l b(String str) {
        i5.a.g("<this>", str);
        byte[] bytes = str.getBytes(o5.a.UTF_8);
        i5.a.f("this as java.lang.String).getBytes(charset)", bytes);
        l lVar = new l(bytes);
        lVar.f6576c = str;
        return lVar;
    }

    public static l c(k kVar, byte[] bArr) {
        kVar.getClass();
        int length = bArr.length;
        n1.z.w(bArr.length, 0, length);
        return new l(c5.l.A1(bArr, 0, length));
    }
}
