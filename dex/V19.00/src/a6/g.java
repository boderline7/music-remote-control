package a6;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final g INSTANCE = new g();

    /* renamed from: a, reason: collision with root package name */
    public static final d[] f409a;

    /* renamed from: b, reason: collision with root package name */
    public static final Map f410b;

    static {
        d dVar = new d(d.TARGET_AUTHORITY, s5.h0.FRAGMENT_ENCODE_SET);
        g6.l lVar = d.TARGET_METHOD;
        d dVar2 = new d(lVar, "GET");
        d dVar3 = new d(lVar, "POST");
        g6.l lVar2 = d.TARGET_PATH;
        d dVar4 = new d(lVar2, "/");
        d dVar5 = new d(lVar2, "/index.html");
        g6.l lVar3 = d.TARGET_SCHEME;
        d dVar6 = new d(lVar3, "http");
        d dVar7 = new d(lVar3, "https");
        g6.l lVar4 = d.RESPONSE_STATUS;
        d[] dVarArr = {dVar, dVar2, dVar3, dVar4, dVar5, dVar6, dVar7, new d(lVar4, "200"), new d(lVar4, "204"), new d(lVar4, "206"), new d(lVar4, "304"), new d(lVar4, "400"), new d(lVar4, "404"), new d(lVar4, "500"), new d("accept-charset", s5.h0.FRAGMENT_ENCODE_SET), new d("accept-encoding", "gzip, deflate"), new d("accept-language", s5.h0.FRAGMENT_ENCODE_SET), new d("accept-ranges", s5.h0.FRAGMENT_ENCODE_SET), new d("accept", s5.h0.FRAGMENT_ENCODE_SET), new d("access-control-allow-origin", s5.h0.FRAGMENT_ENCODE_SET), new d("age", s5.h0.FRAGMENT_ENCODE_SET), new d("allow", s5.h0.FRAGMENT_ENCODE_SET), new d("authorization", s5.h0.FRAGMENT_ENCODE_SET), new d("cache-control", s5.h0.FRAGMENT_ENCODE_SET), new d("content-disposition", s5.h0.FRAGMENT_ENCODE_SET), new d("content-encoding", s5.h0.FRAGMENT_ENCODE_SET), new d("content-language", s5.h0.FRAGMENT_ENCODE_SET), new d("content-length", s5.h0.FRAGMENT_ENCODE_SET), new d("content-location", s5.h0.FRAGMENT_ENCODE_SET), new d("content-range", s5.h0.FRAGMENT_ENCODE_SET), new d("content-type", s5.h0.FRAGMENT_ENCODE_SET), new d("cookie", s5.h0.FRAGMENT_ENCODE_SET), new d("date", s5.h0.FRAGMENT_ENCODE_SET), new d("etag", s5.h0.FRAGMENT_ENCODE_SET), new d("expect", s5.h0.FRAGMENT_ENCODE_SET), new d("expires", s5.h0.FRAGMENT_ENCODE_SET), new d("from", s5.h0.FRAGMENT_ENCODE_SET), new d("host", s5.h0.FRAGMENT_ENCODE_SET), new d("if-match", s5.h0.FRAGMENT_ENCODE_SET), new d("if-modified-since", s5.h0.FRAGMENT_ENCODE_SET), new d("if-none-match", s5.h0.FRAGMENT_ENCODE_SET), new d("if-range", s5.h0.FRAGMENT_ENCODE_SET), new d("if-unmodified-since", s5.h0.FRAGMENT_ENCODE_SET), new d("last-modified", s5.h0.FRAGMENT_ENCODE_SET), new d("link", s5.h0.FRAGMENT_ENCODE_SET), new d("location", s5.h0.FRAGMENT_ENCODE_SET), new d("max-forwards", s5.h0.FRAGMENT_ENCODE_SET), new d("proxy-authenticate", s5.h0.FRAGMENT_ENCODE_SET), new d("proxy-authorization", s5.h0.FRAGMENT_ENCODE_SET), new d("range", s5.h0.FRAGMENT_ENCODE_SET), new d("referer", s5.h0.FRAGMENT_ENCODE_SET), new d("refresh", s5.h0.FRAGMENT_ENCODE_SET), new d("retry-after", s5.h0.FRAGMENT_ENCODE_SET), new d("server", s5.h0.FRAGMENT_ENCODE_SET), new d("set-cookie", s5.h0.FRAGMENT_ENCODE_SET), new d("strict-transport-security", s5.h0.FRAGMENT_ENCODE_SET), new d("transfer-encoding", s5.h0.FRAGMENT_ENCODE_SET), new d("user-agent", s5.h0.FRAGMENT_ENCODE_SET), new d("vary", s5.h0.FRAGMENT_ENCODE_SET), new d("via", s5.h0.FRAGMENT_ENCODE_SET), new d("www-authenticate", s5.h0.FRAGMENT_ENCODE_SET)};
        f409a = dVarArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(dVarArr.length);
        int length = dVarArr.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (!linkedHashMap.containsKey(dVarArr[i7].f378a)) {
                linkedHashMap.put(dVarArr[i7].f378a, Integer.valueOf(i7));
            }
        }
        Map unmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        i5.a.f("unmodifiableMap(result)", unmodifiableMap);
        f410b = unmodifiableMap;
    }

    public static void a(g6.l lVar) {
        i5.a.g("name", lVar);
        int c7 = lVar.c();
        for (int i7 = 0; i7 < c7; i7++) {
            byte f7 = lVar.f(i7);
            if (65 <= f7 && f7 < 91) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: ".concat(lVar.j()));
            }
        }
    }
}
