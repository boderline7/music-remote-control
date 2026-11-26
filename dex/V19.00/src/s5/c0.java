package s5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c0 {
    public static void a(String str) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name is empty".toString());
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char charAt = str.charAt(i7);
            if ('!' > charAt || charAt >= 127) {
                throw new IllegalArgumentException(t5.b.h("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i7), str).toString());
            }
        }
    }

    public static void b(String str, String str2) {
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char charAt = str.charAt(i7);
            if (charAt != '\t' && (' ' > charAt || charAt >= 127)) {
                StringBuilder sb = new StringBuilder();
                sb.append(t5.b.h("Unexpected char %#04x at %d in %s value", Integer.valueOf(charAt), Integer.valueOf(i7), str2));
                sb.append(t5.b.p(str2) ? h0.FRAGMENT_ENCODE_SET : ": ".concat(str));
                throw new IllegalArgumentException(sb.toString().toString());
            }
        }
    }

    public static d0 c(String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values".toString());
        }
        String[] strArr2 = (String[]) strArr.clone();
        int length = strArr2.length;
        int i7 = 0;
        for (int i8 = 0; i8 < length; i8++) {
            String str = strArr2[i8];
            if (str == null) {
                throw new IllegalArgumentException("Headers cannot be null".toString());
            }
            strArr2[i8] = o5.k.U(str).toString();
        }
        int q02 = n1.z.q0(0, strArr2.length - 1, 2);
        if (q02 >= 0) {
            while (true) {
                String str2 = strArr2[i7];
                String str3 = strArr2[i7 + 1];
                a(str2);
                b(str3, str2);
                if (i7 == q02) {
                    break;
                }
                i7 += 2;
            }
        }
        return new d0(strArr2);
    }
}
