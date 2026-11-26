package b4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final g A;
    public static final g B;
    public static final g C;
    public static final g NONE;
    public static final g SHIFT;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ g[] f2957a;

    static {
        g gVar = new g("A", 0);
        A = gVar;
        g gVar2 = new g("B", 1);
        B = gVar2;
        g gVar3 = new g("C", 2);
        C = gVar3;
        g gVar4 = new g("SHIFT", 3);
        SHIFT = gVar4;
        g gVar5 = new g("NONE", 4);
        NONE = gVar5;
        f2957a = new g[]{gVar, gVar2, gVar3, gVar4, gVar5};
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f2957a.clone();
    }
}
