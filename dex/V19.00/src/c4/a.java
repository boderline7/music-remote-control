package c4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final a AUTO;
    public static final a BYTE;
    public static final a NUMERIC;
    public static final a TEXT;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a[] f3054a;

    static {
        a aVar = new a("AUTO", 0);
        AUTO = aVar;
        a aVar2 = new a("TEXT", 1);
        TEXT = aVar2;
        a aVar3 = new a("BYTE", 2);
        BYTE = aVar3;
        a aVar4 = new a("NUMERIC", 3);
        NUMERIC = aVar4;
        f3054a = new a[]{aVar, aVar2, aVar3, aVar4};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f3054a.clone();
    }
}
