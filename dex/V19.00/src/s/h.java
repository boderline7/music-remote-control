package s;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {
    public static final h CONSTANT;
    public static final h ERROR;
    public static final h SLACK;
    public static final h UNKNOWN;
    public static final h UNRESTRICTED;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ h[] f8636a;

    static {
        h hVar = new h("UNRESTRICTED", 0);
        UNRESTRICTED = hVar;
        h hVar2 = new h("CONSTANT", 1);
        CONSTANT = hVar2;
        h hVar3 = new h("SLACK", 2);
        SLACK = hVar3;
        h hVar4 = new h("ERROR", 3);
        ERROR = hVar4;
        h hVar5 = new h("UNKNOWN", 4);
        UNKNOWN = hVar5;
        f8636a = new h[]{hVar, hVar2, hVar3, hVar4, hVar5};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f8636a.clone();
    }
}
