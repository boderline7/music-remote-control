package a0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {
    public static final b BOOLEAN_TYPE;
    public static final b COLOR_DRAWABLE_TYPE;
    public static final b COLOR_TYPE;
    public static final b DIMENSION_TYPE;
    public static final b FLOAT_TYPE;
    public static final b INT_TYPE;
    public static final b REFERENCE_TYPE;
    public static final b STRING_TYPE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ b[] f1a;

    static {
        b bVar = new b("INT_TYPE", 0);
        INT_TYPE = bVar;
        b bVar2 = new b("FLOAT_TYPE", 1);
        FLOAT_TYPE = bVar2;
        b bVar3 = new b("COLOR_TYPE", 2);
        COLOR_TYPE = bVar3;
        b bVar4 = new b("COLOR_DRAWABLE_TYPE", 3);
        COLOR_DRAWABLE_TYPE = bVar4;
        b bVar5 = new b("STRING_TYPE", 4);
        STRING_TYPE = bVar5;
        b bVar6 = new b("BOOLEAN_TYPE", 5);
        BOOLEAN_TYPE = bVar6;
        b bVar7 = new b("DIMENSION_TYPE", 6);
        DIMENSION_TYPE = bVar7;
        b bVar8 = new b("REFERENCE_TYPE", 7);
        REFERENCE_TYPE = bVar8;
        f1a = new b[]{bVar, bVar2, bVar3, bVar4, bVar5, bVar6, bVar7, bVar8};
    }

    public static b valueOf(String str) {
        return (b) Enum.valueOf(b.class, str);
    }

    public static b[] values() {
        return (b[]) f1a.clone();
    }
}
