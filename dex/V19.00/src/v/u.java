package v;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u {
    public static final u CENTER;
    public static final u END;
    public static final u NONE;
    public static final u START;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ u[] f9375a;

    static {
        u uVar = new u("NONE", 0);
        NONE = uVar;
        u uVar2 = new u("START", 1);
        START = uVar2;
        u uVar3 = new u("END", 2);
        END = uVar3;
        u uVar4 = new u("CENTER", 3);
        CENTER = uVar4;
        f9375a = new u[]{uVar, uVar2, uVar3, uVar4};
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) f9375a.clone();
    }
}
