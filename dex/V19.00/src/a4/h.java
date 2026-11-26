package a4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {
    public static final h ASCII;
    public static final h B256;
    public static final h C40;
    public static final h EDF;
    public static final h TEXT;
    public static final h X12;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ h[] f293a;

    static {
        h hVar = new h("ASCII", 0);
        ASCII = hVar;
        h hVar2 = new h("C40", 1);
        C40 = hVar2;
        h hVar3 = new h("TEXT", 2);
        TEXT = hVar3;
        h hVar4 = new h("X12", 3);
        X12 = hVar4;
        h hVar5 = new h("EDF", 4);
        EDF = hVar5;
        h hVar6 = new h("B256", 5);
        B256 = hVar6;
        f293a = new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f293a.clone();
    }
}
