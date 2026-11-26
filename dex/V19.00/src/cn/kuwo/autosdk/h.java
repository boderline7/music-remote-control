package cn.kuwo.autosdk;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {
    public static final h BACKGROUNDPIC;
    public static final h HEADPIC;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ h[] f3125a;

    static {
        h hVar = new h("HEADPIC", 0);
        HEADPIC = hVar;
        h hVar2 = new h("BACKGROUNDPIC", 1);
        BACKGROUNDPIC = hVar2;
        f3125a = new h[]{hVar, hVar2};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f3125a.clone();
    }
}
