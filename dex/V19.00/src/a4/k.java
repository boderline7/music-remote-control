package a4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k {
    public static final k FORCE_NONE;
    public static final k FORCE_RECTANGLE;
    public static final k FORCE_SQUARE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ k[] f304a;

    static {
        k kVar = new k("FORCE_NONE", 0);
        FORCE_NONE = kVar;
        k kVar2 = new k("FORCE_SQUARE", 1);
        FORCE_SQUARE = kVar2;
        k kVar3 = new k("FORCE_RECTANGLE", 2);
        FORCE_RECTANGLE = kVar3;
        f304a = new k[]{kVar, kVar2, kVar3};
    }

    public static k valueOf(String str) {
        return (k) Enum.valueOf(k.class, str);
    }

    public static k[] values() {
        return (k[]) f304a.clone();
    }
}
