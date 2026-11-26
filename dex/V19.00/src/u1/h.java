package u1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class h {
    public static final h EQ;
    public static final h GE;
    public static final h GT;
    public static final h LE;
    public static final h LT;
    public static final h NE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ h[] f9260a;

    static {
        h hVar = new h() { // from class: u1.b
            @Override // u1.h
            public final a2.p a(c2.b bVar) {
                return a2.r.a(bVar, a2.r.IF_LTZ_INT, null, a2.r.IF_LT_INT, null);
            }
        };
        LT = hVar;
        h hVar2 = new h() { // from class: u1.c
            @Override // u1.h
            public final a2.p a(c2.b bVar) {
                return a2.r.a(bVar, a2.r.IF_LEZ_INT, null, a2.r.IF_LE_INT, null);
            }
        };
        LE = hVar2;
        h hVar3 = new h() { // from class: u1.d
            @Override // u1.h
            public final a2.p a(c2.b bVar) {
                return a2.r.a(bVar, a2.r.IF_EQZ_INT, a2.r.IF_EQZ_OBJECT, a2.r.IF_EQ_INT, a2.r.IF_EQ_OBJECT);
            }
        };
        EQ = hVar3;
        h hVar4 = new h() { // from class: u1.e
            @Override // u1.h
            public final a2.p a(c2.b bVar) {
                return a2.r.a(bVar, a2.r.IF_GEZ_INT, null, a2.r.IF_GE_INT, null);
            }
        };
        GE = hVar4;
        h hVar5 = new h() { // from class: u1.f
            @Override // u1.h
            public final a2.p a(c2.b bVar) {
                return a2.r.a(bVar, a2.r.IF_GTZ_INT, null, a2.r.IF_GT_INT, null);
            }
        };
        GT = hVar5;
        h hVar6 = new h() { // from class: u1.g
            @Override // u1.h
            public final a2.p a(c2.b bVar) {
                return a2.r.a(bVar, a2.r.IF_NEZ_INT, a2.r.IF_NEZ_OBJECT, a2.r.IF_NE_INT, a2.r.IF_NE_OBJECT);
            }
        };
        NE = hVar6;
        f9260a = new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f9260a.clone();
    }

    public abstract a2.p a(c2.b bVar);
}
