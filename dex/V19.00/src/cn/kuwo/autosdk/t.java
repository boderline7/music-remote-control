package cn.kuwo.autosdk;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class t {
    public static final String CATEGORY_ARTISTPIC = "ARTISTPIC_CACHE";
    public static final t CATEGORY_ARTISTPIC_TYPE;
    public static final String CATEGORY_CONFIG = "CONFIG_CACHE";
    public static final t CATEGORY_CONFIG_TYPE;
    public static final String CATEGORY_LYRICS = "LYRICS_CACHE";
    public static final t CATEGORY_LYRICS_TYPE;
    public static final String CATEGORY_MVPIC = "MVPIC_CACHE";
    public static final t CATEGORY_MVPIC_TYPE;
    public static final String CATEGORY_QUKU = "QUKU_CACHE";
    public static final t CATEGORY_QUKU_TYPE;
    public static final String CATEGORY_SMALLPIC = "SMALLPIC_CACHE";
    public static final t CATEGORY_SMALLPIC_TYPE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ t[] f3130a;

    static {
        t tVar = new t() { // from class: cn.kuwo.autosdk.n
            @Override // cn.kuwo.autosdk.t
            public final int a() {
                return t1.f.T_DAY;
            }

            @Override // cn.kuwo.autosdk.t
            public final int b() {
                return 1;
            }
        };
        CATEGORY_QUKU_TYPE = tVar;
        t tVar2 = new t() { // from class: cn.kuwo.autosdk.o
            @Override // cn.kuwo.autosdk.t
            public final int a() {
                return t1.f.T_MONTH;
            }

            @Override // cn.kuwo.autosdk.t
            public final int b() {
                return 2;
            }
        };
        CATEGORY_LYRICS_TYPE = tVar2;
        t tVar3 = new t() { // from class: cn.kuwo.autosdk.p
            @Override // cn.kuwo.autosdk.t
            public final int a() {
                return t1.f.T_MONTH;
            }

            @Override // cn.kuwo.autosdk.t
            public final int b() {
                return 2;
            }
        };
        CATEGORY_ARTISTPIC_TYPE = tVar3;
        t tVar4 = new t() { // from class: cn.kuwo.autosdk.q
            @Override // cn.kuwo.autosdk.t
            public final int a() {
                return t1.f.T_HOUR;
            }

            @Override // cn.kuwo.autosdk.t
            public final int b() {
                return 24;
            }
        };
        CATEGORY_CONFIG_TYPE = tVar4;
        t tVar5 = new t() { // from class: cn.kuwo.autosdk.r
            @Override // cn.kuwo.autosdk.t
            public final int a() {
                return t1.f.T_DAY;
            }

            @Override // cn.kuwo.autosdk.t
            public final int b() {
                return 7;
            }
        };
        CATEGORY_SMALLPIC_TYPE = tVar5;
        t tVar6 = new t() { // from class: cn.kuwo.autosdk.s
            @Override // cn.kuwo.autosdk.t
            public final int a() {
                return t1.f.T_MONTH;
            }

            @Override // cn.kuwo.autosdk.t
            public final int b() {
                return 6;
            }
        };
        CATEGORY_MVPIC_TYPE = tVar6;
        f3130a = new t[]{tVar, tVar2, tVar3, tVar4, tVar5, tVar6};
    }

    public static t valueOf(String str) {
        return (t) Enum.valueOf(t.class, str);
    }

    public static t[] values() {
        return (t[]) f3130a.clone();
    }

    public abstract int a();

    public abstract int b();
}
