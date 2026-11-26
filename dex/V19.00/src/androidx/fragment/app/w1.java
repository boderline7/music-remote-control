package androidx.fragment.app;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class w1 {
    public static final w1 ADDING;
    public static final w1 NONE;
    public static final w1 REMOVING;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ w1[] f2101a;

    static {
        w1 w1Var = new w1("NONE", 0);
        NONE = w1Var;
        w1 w1Var2 = new w1("ADDING", 1);
        ADDING = w1Var2;
        w1 w1Var3 = new w1("REMOVING", 2);
        REMOVING = w1Var3;
        f2101a = new w1[]{w1Var, w1Var2, w1Var3};
    }

    public static w1 valueOf(String str) {
        return (w1) Enum.valueOf(w1.class, str);
    }

    public static w1[] values() {
        return (w1[]) f2101a.clone();
    }
}
