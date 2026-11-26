package o2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g {
    public static final g VM_OPINT;
    public static final g VM_OPNONE;
    public static final g VM_OPREG;
    public static final g VM_OPREGMEM;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ g[] f7892a;

    static {
        g gVar = new g("VM_OPREG", 0);
        VM_OPREG = gVar;
        g gVar2 = new g("VM_OPINT", 1);
        VM_OPINT = gVar2;
        g gVar3 = new g("VM_OPREGMEM", 2);
        VM_OPREGMEM = gVar3;
        g gVar4 = new g("VM_OPNONE", 3);
        VM_OPNONE = gVar4;
        f7892a = new g[]{gVar, gVar2, gVar3, gVar4};
    }

    public static g valueOf(String str) {
        return (g) Enum.valueOf(g.class, str);
    }

    public static g[] values() {
        return (g[]) f7892a.clone();
    }
}
