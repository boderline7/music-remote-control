package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class r {
    public static final r CREATED;
    public static final r DESTROYED;
    public static final r INITIALIZED;
    public static final r RESUMED;
    public static final r STARTED;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ r[] f2201a;

    static {
        r rVar = new r("DESTROYED", 0);
        DESTROYED = rVar;
        r rVar2 = new r("INITIALIZED", 1);
        INITIALIZED = rVar2;
        r rVar3 = new r("CREATED", 2);
        CREATED = rVar3;
        r rVar4 = new r("STARTED", 3);
        STARTED = rVar4;
        r rVar5 = new r("RESUMED", 4);
        RESUMED = rVar5;
        f2201a = new r[]{rVar, rVar2, rVar3, rVar4, rVar5};
    }

    public static r valueOf(String str) {
        return (r) Enum.valueOf(r.class, str);
    }

    public static r[] values() {
        return (r[]) f2201a.clone();
    }

    public final boolean a(r rVar) {
        i5.a.g("state", rVar);
        return compareTo(rVar) >= 0;
    }
}
