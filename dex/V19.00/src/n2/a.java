package n2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a {
    public static final a BLOCK_LZ;
    public static final a BLOCK_PPM;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ a[] f7696a;

    static {
        a aVar = new a("BLOCK_LZ", 0);
        BLOCK_LZ = aVar;
        a aVar2 = new a("BLOCK_PPM", 1);
        BLOCK_PPM = aVar2;
        f7696a = new a[]{aVar, aVar2};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) f7696a.clone();
    }
}
