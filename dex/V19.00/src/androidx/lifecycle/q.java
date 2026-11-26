package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q {
    private static final /* synthetic */ q[] $VALUES;
    public static final o Companion;
    public static final q ON_ANY;
    public static final q ON_CREATE;
    public static final q ON_DESTROY;
    public static final q ON_PAUSE;
    public static final q ON_RESUME;
    public static final q ON_START;
    public static final q ON_STOP;

    static {
        q qVar = new q("ON_CREATE", 0);
        ON_CREATE = qVar;
        q qVar2 = new q("ON_START", 1);
        ON_START = qVar2;
        q qVar3 = new q("ON_RESUME", 2);
        ON_RESUME = qVar3;
        q qVar4 = new q("ON_PAUSE", 3);
        ON_PAUSE = qVar4;
        q qVar5 = new q("ON_STOP", 4);
        ON_STOP = qVar5;
        q qVar6 = new q("ON_DESTROY", 5);
        ON_DESTROY = qVar6;
        q qVar7 = new q("ON_ANY", 6);
        ON_ANY = qVar7;
        $VALUES = new q[]{qVar, qVar2, qVar3, qVar4, qVar5, qVar6, qVar7};
        Companion = new o();
    }

    public static q valueOf(String str) {
        return (q) Enum.valueOf(q.class, str);
    }

    public static q[] values() {
        return (q[]) $VALUES.clone();
    }

    public final r a() {
        switch (p.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
            case 2:
                return r.CREATED;
            case 3:
            case 4:
                return r.STARTED;
            case 5:
                return r.RESUMED;
            case 6:
                return r.DESTROYED;
            default:
                throw new IllegalArgumentException(this + " has no target state");
        }
    }
}
