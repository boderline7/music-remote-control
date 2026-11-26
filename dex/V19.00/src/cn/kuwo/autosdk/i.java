package cn.kuwo.autosdk;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i {
    public static final i LRC;
    public static final i LRCX;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ i[] f3126a;

    static {
        i iVar = new i("LRC", 0);
        LRC = iVar;
        i iVar2 = new i("LRCX", 1);
        LRCX = iVar2;
        f3126a = new i[]{iVar, iVar2};
    }

    public static i valueOf(String str) {
        return (i) Enum.valueOf(i.class, str);
    }

    public static i[] values() {
        return (i[]) f3126a.clone();
    }
}
