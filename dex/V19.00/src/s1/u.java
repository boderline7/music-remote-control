package s1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class u {
    public static final String CHANGE_SONGLIST = "CHANGE_SONGLIST";
    public static final u STATE_BACK;
    public static final u STATE_FORWARD;
    public static final u STATE_NEXT;
    public static final u STATE_PAUSE;
    public static final u STATE_PLAY;
    public static final u STATE_PRE;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ u[] f8660a;

    static {
        u uVar = new u() { // from class: s1.o
            @Override // s1.u
            public final String a() {
                return "MEDIA_PRE";
            }
        };
        STATE_PRE = uVar;
        u uVar2 = new u() { // from class: s1.p
            @Override // s1.u
            public final String a() {
                return "MEDIA_NEXT";
            }
        };
        STATE_NEXT = uVar2;
        u uVar3 = new u() { // from class: s1.q
            @Override // s1.u
            public final String a() {
                return "MEDIA_PAUSE";
            }
        };
        STATE_PAUSE = uVar3;
        u uVar4 = new u() { // from class: s1.r
            @Override // s1.u
            public final String a() {
                return "MEDIA_PLAY";
            }
        };
        STATE_PLAY = uVar4;
        u uVar5 = new u() { // from class: s1.s
            @Override // s1.u
            public final String a() {
                return "MEDIA_FORWARD";
            }
        };
        STATE_FORWARD = uVar5;
        u uVar6 = new u() { // from class: s1.t
            @Override // s1.u
            public final String a() {
                return "MEDIA_BACK";
            }
        };
        STATE_BACK = uVar6;
        f8660a = new u[]{uVar, uVar2, uVar3, uVar4, uVar5, uVar6};
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) f8660a.clone();
    }

    public abstract String a();
}
