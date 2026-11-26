package u;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {
    public static final h FIXED;
    public static final h MATCH_CONSTRAINT;
    public static final h MATCH_PARENT;
    public static final h WRAP_CONTENT;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ h[] f9127a;

    static {
        h hVar = new h("FIXED", 0);
        FIXED = hVar;
        h hVar2 = new h("WRAP_CONTENT", 1);
        WRAP_CONTENT = hVar2;
        h hVar3 = new h("MATCH_CONSTRAINT", 2);
        MATCH_CONSTRAINT = hVar3;
        h hVar4 = new h("MATCH_PARENT", 3);
        MATCH_PARENT = hVar4;
        f9127a = new h[]{hVar, hVar2, hVar3, hVar4};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f9127a.clone();
    }
}
