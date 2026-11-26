package k2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n {
    public static final n OLD;
    public static final n V4;
    public static final n V5;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ n[] f7244a;

    static {
        n nVar = new n("OLD", 0);
        OLD = nVar;
        n nVar2 = new n("V4", 1);
        V4 = nVar2;
        n nVar3 = new n("V5", 2);
        V5 = nVar3;
        f7244a = new n[]{nVar, nVar2, nVar3};
    }

    public static n valueOf(String str) {
        return (n) Enum.valueOf(n.class, str);
    }

    public static n[] values() {
        return (n[]) f7244a.clone();
    }
}
