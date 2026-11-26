package v;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final g BASELINE;
    public static final g BOTTOM;
    public static final g HORIZONTAL_DIMENSION;
    public static final g LEFT;
    public static final g RIGHT;
    public static final g TOP;
    public static final g UNKNOWN;
    public static final g VERTICAL_DIMENSION;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ g[] f9338a;

    static {
        g gVar = new g("UNKNOWN", 0);
        UNKNOWN = gVar;
        g gVar2 = new g("HORIZONTAL_DIMENSION", 1);
        HORIZONTAL_DIMENSION = gVar2;
        g gVar3 = new g("VERTICAL_DIMENSION", 2);
        VERTICAL_DIMENSION = gVar3;
        g gVar4 = new g("LEFT", 3);
        LEFT = gVar4;
        g gVar5 = new g("RIGHT", 4);
        RIGHT = gVar5;
        g gVar6 = new g("TOP", 5);
        TOP = gVar6;
        g gVar7 = new g("BOTTOM", 6);
        BOTTOM = gVar7;
        g gVar8 = new g("BASELINE", 7);
        BASELINE = gVar8;
        f9338a = new g[]{gVar, gVar2, gVar3, gVar4, gVar5, gVar6, gVar7, gVar8};
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f9338a.clone();
    }
}
