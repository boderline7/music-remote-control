package a6;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class d {
    public static final c Companion = new c();
    public static final g6.l PSEUDO_PREFIX;
    public static final g6.l RESPONSE_STATUS;
    public static final String RESPONSE_STATUS_UTF8 = ":status";
    public static final g6.l TARGET_AUTHORITY;
    public static final String TARGET_AUTHORITY_UTF8 = ":authority";
    public static final g6.l TARGET_METHOD;
    public static final String TARGET_METHOD_UTF8 = ":method";
    public static final g6.l TARGET_PATH;
    public static final String TARGET_PATH_UTF8 = ":path";
    public static final g6.l TARGET_SCHEME;
    public static final String TARGET_SCHEME_UTF8 = ":scheme";

    /* renamed from: a, reason: collision with root package name */
    public final g6.l f378a;

    /* renamed from: b, reason: collision with root package name */
    public final g6.l f379b;

    /* renamed from: c, reason: collision with root package name */
    public final int f380c;

    static {
        g6.l.Companion.getClass();
        PSEUDO_PREFIX = g6.k.b(":");
        RESPONSE_STATUS = g6.k.b(RESPONSE_STATUS_UTF8);
        TARGET_METHOD = g6.k.b(TARGET_METHOD_UTF8);
        TARGET_PATH = g6.k.b(TARGET_PATH_UTF8);
        TARGET_SCHEME = g6.k.b(TARGET_SCHEME_UTF8);
        TARGET_AUTHORITY = g6.k.b(TARGET_AUTHORITY_UTF8);
    }

    public d(g6.l lVar, g6.l lVar2) {
        i5.a.g("name", lVar);
        i5.a.g("value", lVar2);
        this.f378a = lVar;
        this.f379b = lVar2;
        this.f380c = lVar2.c() + lVar.c() + 32;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public d(g6.l lVar, String str) {
        this(lVar, g6.k.b(str));
        i5.a.g("name", lVar);
        i5.a.g("value", str);
        g6.l.Companion.getClass();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public d(String str, String str2) {
        this(g6.k.b(str), g6.k.b(str2));
        i5.a.g("name", str);
        i5.a.g("value", str2);
        g6.l.Companion.getClass();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return i5.a.c(this.f378a, dVar.f378a) && i5.a.c(this.f379b, dVar.f379b);
    }

    public final int hashCode() {
        return this.f379b.hashCode() + (this.f378a.hashCode() * 31);
    }

    public final String toString() {
        return this.f378a.j() + ": " + this.f379b.j();
    }
}
