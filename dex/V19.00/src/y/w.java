package y;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class w {
    public static final w FINISHED;
    public static final w MOVING;
    public static final w SETUP;
    public static final w UNDEFINED;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ w[] f9925a;

    static {
        w wVar = new w("UNDEFINED", 0);
        UNDEFINED = wVar;
        w wVar2 = new w("SETUP", 1);
        SETUP = wVar2;
        w wVar3 = new w("MOVING", 2);
        MOVING = wVar3;
        w wVar4 = new w("FINISHED", 3);
        FINISHED = wVar4;
        f9925a = new w[]{wVar, wVar2, wVar3, wVar4};
    }

    public static w valueOf(String str) {
        return (w) Enum.valueOf(w.class, str);
    }

    public static w[] values() {
        return (w[]) f9925a.clone();
    }
}
