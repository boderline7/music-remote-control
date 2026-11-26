package s5;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c1 {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static d1 a(String str) {
        i5.a.g("javaName", str);
        int hashCode = str.hashCode();
        if (hashCode != 79201641) {
            if (hashCode != 79923350) {
                switch (hashCode) {
                    case -503070503:
                        if (str.equals("TLSv1.1")) {
                            return d1.TLS_1_1;
                        }
                        break;
                    case -503070502:
                        if (str.equals("TLSv1.2")) {
                            return d1.TLS_1_2;
                        }
                        break;
                    case -503070501:
                        if (str.equals("TLSv1.3")) {
                            return d1.TLS_1_3;
                        }
                        break;
                }
            } else if (str.equals("TLSv1")) {
                return d1.TLS_1_0;
            }
        } else if (str.equals("SSLv3")) {
            return d1.SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: ".concat(str));
    }
}
