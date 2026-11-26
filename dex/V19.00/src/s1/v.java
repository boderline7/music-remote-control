package s1;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class v {
    public static final v BUFFERING;
    public static final v INIT;
    public static final v PAUSE;
    public static final v PLAYING;
    public static final v STOP;

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ v[] f8661a;

    static {
        v vVar = new v("INIT", 0);
        INIT = vVar;
        v vVar2 = new v("PLAYING", 1);
        PLAYING = vVar2;
        v vVar3 = new v("BUFFERING", 2);
        BUFFERING = vVar3;
        v vVar4 = new v("PAUSE", 3);
        PAUSE = vVar4;
        v vVar5 = new v("STOP", 4);
        STOP = vVar5;
        f8661a = new v[]{vVar, vVar2, vVar3, vVar4, vVar5};
    }

    public static v valueOf(String str) {
        return (v) Enum.valueOf(v.class, str);
    }

    public static v[] values() {
        return (v[]) f8661a.clone();
    }
}
