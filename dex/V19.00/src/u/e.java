package u;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {
    public static final e BASELINE;
    public static final e BOTTOM;
    public static final e CENTER;
    public static final e CENTER_X;
    public static final e CENTER_Y;
    public static final e LEFT;
    public static final e NONE;
    public static final e RIGHT;
    public static final e TOP;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ e[] f9115a;

    static {
        e eVar = new e("NONE", 0);
        NONE = eVar;
        e eVar2 = new e("LEFT", 1);
        LEFT = eVar2;
        e eVar3 = new e("TOP", 2);
        TOP = eVar3;
        e eVar4 = new e("RIGHT", 3);
        RIGHT = eVar4;
        e eVar5 = new e("BOTTOM", 4);
        BOTTOM = eVar5;
        e eVar6 = new e("BASELINE", 5);
        BASELINE = eVar6;
        e eVar7 = new e("CENTER", 6);
        CENTER = eVar7;
        e eVar8 = new e("CENTER_X", 7);
        CENTER_X = eVar8;
        e eVar9 = new e("CENTER_Y", 8);
        CENTER_Y = eVar9;
        f9115a = new e[]{eVar, eVar2, eVar3, eVar4, eVar5, eVar6, eVar7, eVar8, eVar9};
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f9115a.clone();
    }
}
