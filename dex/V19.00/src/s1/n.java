package s1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n {
    public static final n MODE_ALL_CIRCLE;
    public static final n MODE_ALL_ORDER;
    public static final n MODE_ALL_RANDOM;
    public static final n MODE_SINGLE_CIRCLE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ n[] f8659a;

    static {
        n nVar = new n() { // from class: s1.j
            @Override // s1.n
            public final String a() {
                return "MEDIA_ONE";
            }
        };
        MODE_SINGLE_CIRCLE = nVar;
        n nVar2 = new n() { // from class: s1.k
            @Override // s1.n
            public final String a() {
                return "MEDIA_ORDER";
            }
        };
        MODE_ALL_ORDER = nVar2;
        n nVar3 = new n() { // from class: s1.l
            @Override // s1.n
            public final String a() {
                return "MEDIA_CIRCLE";
            }
        };
        MODE_ALL_CIRCLE = nVar3;
        n nVar4 = new n() { // from class: s1.m
            @Override // s1.n
            public final String a() {
                return "MEDIA_RANDOM";
            }
        };
        MODE_ALL_RANDOM = nVar4;
        f8659a = new n[]{nVar, nVar2, nVar3, nVar4};
    }

    public static n valueOf(String str) {
        return (n) Enum.valueOf(n.class, str);
    }

    public static n[] values() {
        return (n[]) f8659a.clone();
    }

    public abstract String a();
}
