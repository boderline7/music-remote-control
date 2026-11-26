package b4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f {
    public static final f A;
    public static final f B;
    public static final f C;
    public static final f NONE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ f[] f2956a;

    static {
        f fVar = new f("A", 0);
        A = fVar;
        f fVar2 = new f("B", 1);
        B = fVar2;
        f fVar3 = new f("C", 2);
        C = fVar3;
        f fVar4 = new f("NONE", 3);
        NONE = fVar4;
        f2956a = new f[]{fVar, fVar2, fVar3, fVar4};
    }

    public static f valueOf(String str) {
        return (f) Enum.valueOf(f.class, str);
    }

    public static f[] values() {
        return (f[]) f2956a.clone();
    }
}
