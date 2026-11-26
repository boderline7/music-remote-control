package y1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y {
    public static final y INSTANCE;
    public static final y NONE;
    public static final y TYPE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ y[] f10115a;

    static {
        y yVar = new y("NONE", 0);
        NONE = yVar;
        y yVar2 = new y("TYPE", 1);
        TYPE = yVar2;
        y yVar3 = new y("INSTANCE", 2);
        INSTANCE = yVar3;
        f10115a = new y[]{yVar, yVar2, yVar3};
    }

    public static y valueOf(String str) {
        return (y) Enum.valueOf(y.class, str);
    }

    public static y[] values() {
        return (y[]) f10115a.clone();
    }
}
