package s5;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class r0 {

    /* renamed from: a, reason: collision with root package name */
    public h0 f8886a;

    /* renamed from: d, reason: collision with root package name */
    public v0 f8889d;

    /* renamed from: e, reason: collision with root package name */
    public LinkedHashMap f8890e = new LinkedHashMap();

    /* renamed from: b, reason: collision with root package name */
    public String f8887b = "GET";

    /* renamed from: c, reason: collision with root package name */
    public b0 f8888c = new b0();

    public final androidx.appcompat.widget.y a() {
        Map unmodifiableMap;
        h0 h0Var = this.f8886a;
        if (h0Var == null) {
            throw new IllegalStateException("url == null".toString());
        }
        String str = this.f8887b;
        d0 b7 = this.f8888c.b();
        v0 v0Var = this.f8889d;
        LinkedHashMap linkedHashMap = this.f8890e;
        byte[] bArr = t5.b.EMPTY_BYTE_ARRAY;
        i5.a.g("<this>", linkedHashMap);
        if (linkedHashMap.isEmpty()) {
            unmodifiableMap = c5.l.B1();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(linkedHashMap));
            i5.a.f("{\n    Collections.unmodi…(LinkedHashMap(this))\n  }", unmodifiableMap);
        }
        return new androidx.appcompat.widget.y(h0Var, str, b7, v0Var, unmodifiableMap);
    }

    public final void b() {
        d("GET", null);
    }

    public final void c(String str, String str2) {
        i5.a.g("value", str2);
        b0 b0Var = this.f8888c;
        b0Var.getClass();
        d0.Companion.getClass();
        c0.a(str);
        c0.b(str2, str);
        b0Var.c(str);
        b0Var.a(str, str2);
    }

    public final void d(String str, v0 v0Var) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("method.isEmpty() == true".toString());
        }
        if (v0Var == null) {
            if (!(!(i5.a.c(str, "POST") || i5.a.c(str, "PUT") || i5.a.c(str, "PATCH") || i5.a.c(str, "PROPPATCH") || i5.a.c(str, "REPORT")))) {
                throw new IllegalArgumentException(a.a.h("method ", str, " must have a request body.").toString());
            }
        } else if (!y5.g.a(str)) {
            throw new IllegalArgumentException(a.a.h("method ", str, " must not have a request body.").toString());
        }
        this.f8887b = str;
        this.f8889d = v0Var;
    }

    public final void e(String str) {
        this.f8888c.c(str);
    }

    public final void f(String str) {
        i5.a.g("url", str);
        if (o5.k.R(str, "ws:", true)) {
            String substring = str.substring(3);
            i5.a.f("this as java.lang.String).substring(startIndex)", substring);
            str = "http:".concat(substring);
        } else if (o5.k.R(str, "wss:", true)) {
            String substring2 = str.substring(4);
            i5.a.f("this as java.lang.String).substring(startIndex)", substring2);
            str = "https:".concat(substring2);
        }
        h0.Companion.getClass();
        i5.a.g("<this>", str);
        f0 f0Var = new f0();
        f0Var.b(null, str);
        this.f8886a = f0Var.a();
    }
}
