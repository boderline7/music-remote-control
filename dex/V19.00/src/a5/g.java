package a5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final g CONNECT;
    public static final g COPY;
    public static final g DELETE;
    public static final g GET;
    public static final g HEAD;
    public static final g LOCK;
    public static final g MKCOL;
    public static final g MOVE;
    public static final g OPTIONS;
    public static final g PATCH;
    public static final g POST;
    public static final g PROPFIND;
    public static final g PROPPATCH;
    public static final g PUT;
    public static final g TRACE;
    public static final g UNLOCK;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ g[] f335a;

    static {
        g gVar = new g("GET", 0);
        GET = gVar;
        g gVar2 = new g("PUT", 1);
        PUT = gVar2;
        g gVar3 = new g("POST", 2);
        POST = gVar3;
        g gVar4 = new g("DELETE", 3);
        DELETE = gVar4;
        g gVar5 = new g("HEAD", 4);
        HEAD = gVar5;
        g gVar6 = new g("OPTIONS", 5);
        OPTIONS = gVar6;
        g gVar7 = new g("TRACE", 6);
        TRACE = gVar7;
        g gVar8 = new g("CONNECT", 7);
        CONNECT = gVar8;
        g gVar9 = new g("PATCH", 8);
        PATCH = gVar9;
        g gVar10 = new g("PROPFIND", 9);
        PROPFIND = gVar10;
        g gVar11 = new g("PROPPATCH", 10);
        PROPPATCH = gVar11;
        g gVar12 = new g("MKCOL", 11);
        MKCOL = gVar12;
        g gVar13 = new g("MOVE", 12);
        MOVE = gVar13;
        g gVar14 = new g("COPY", 13);
        COPY = gVar14;
        g gVar15 = new g("LOCK", 14);
        LOCK = gVar15;
        g gVar16 = new g("UNLOCK", 15);
        UNLOCK = gVar16;
        f335a = new g[]{gVar, gVar2, gVar3, gVar4, gVar5, gVar6, gVar7, gVar8, gVar9, gVar10, gVar11, gVar12, gVar13, gVar14, gVar15, gVar16};
    }

    public static g a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f335a.clone();
    }
}
