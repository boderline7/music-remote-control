package androidx.lifecycle;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class o {
    public static q a(r rVar) {
        i5.a.g("state", rVar);
        int i7 = n.$EnumSwitchMapping$0[rVar.ordinal()];
        if (i7 == 1) {
            return q.ON_DESTROY;
        }
        if (i7 == 2) {
            return q.ON_STOP;
        }
        if (i7 != 3) {
            return null;
        }
        return q.ON_PAUSE;
    }

    public static q b(r rVar) {
        i5.a.g("state", rVar);
        int i7 = n.$EnumSwitchMapping$0[rVar.ordinal()];
        if (i7 == 1) {
            return q.ON_START;
        }
        if (i7 == 2) {
            return q.ON_RESUME;
        }
        if (i7 != 5) {
            return null;
        }
        return q.ON_CREATE;
    }
}
