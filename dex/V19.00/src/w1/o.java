package w1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o {
    public static final o END_CLOBBERED_BY_NEXT;
    public static final o END_CLOBBERED_BY_PREV;
    public static final o END_MOVED;
    public static final o END_REPLACED;
    public static final o END_SIMPLY;
    public static final o START;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ o[] f9438a;

    static {
        o oVar = new o("START", 0);
        START = oVar;
        o oVar2 = new o("END_SIMPLY", 1);
        END_SIMPLY = oVar2;
        o oVar3 = new o("END_REPLACED", 2);
        END_REPLACED = oVar3;
        o oVar4 = new o("END_MOVED", 3);
        END_MOVED = oVar4;
        o oVar5 = new o("END_CLOBBERED_BY_PREV", 4);
        END_CLOBBERED_BY_PREV = oVar5;
        o oVar6 = new o("END_CLOBBERED_BY_NEXT", 5);
        END_CLOBBERED_BY_NEXT = oVar6;
        f9438a = new o[]{oVar, oVar2, oVar3, oVar4, oVar5, oVar6};
    }

    public static o valueOf(String str) {
        return (o) Enum.valueOf(o.class, str);
    }

    public static o[] values() {
        return (o[]) f9438a.clone();
    }
}
