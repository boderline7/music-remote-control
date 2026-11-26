package s5;

import java.util.LinkedHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l {
    public static final m a(l lVar, String str) {
        m mVar = new m(str);
        m.f8815c.put(str, mVar);
        return mVar;
    }

    public final synchronized m b(String str) {
        m mVar;
        String str2;
        try {
            i5.a.g("javaName", str);
            LinkedHashMap linkedHashMap = m.f8815c;
            mVar = (m) linkedHashMap.get(str);
            if (mVar == null) {
                if (o5.k.R(str, "TLS_", false)) {
                    String substring = str.substring(4);
                    i5.a.f("this as java.lang.String).substring(startIndex)", substring);
                    str2 = "SSL_".concat(substring);
                } else if (o5.k.R(str, "SSL_", false)) {
                    String substring2 = str.substring(4);
                    i5.a.f("this as java.lang.String).substring(startIndex)", substring2);
                    str2 = "TLS_".concat(substring2);
                } else {
                    str2 = str;
                }
                mVar = (m) linkedHashMap.get(str2);
                if (mVar == null) {
                    mVar = new m(str);
                }
                linkedHashMap.put(str, mVar);
            }
        } catch (Throwable th) {
            throw th;
        }
        return mVar;
    }
}
