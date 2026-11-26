package b4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {
    public static final e FNC_1;
    public static final e ONE_DIGIT;
    public static final e TWO_DIGITS;
    public static final e UNCODABLE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ e[] f2955a;

    static {
        e eVar = new e("UNCODABLE", 0);
        UNCODABLE = eVar;
        e eVar2 = new e("ONE_DIGIT", 1);
        ONE_DIGIT = eVar2;
        e eVar3 = new e("TWO_DIGITS", 2);
        TWO_DIGITS = eVar3;
        e eVar4 = new e("FNC_1", 3);
        FNC_1 = eVar4;
        f2955a = new e[]{eVar, eVar2, eVar3, eVar4};
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f2955a.clone();
    }
}
