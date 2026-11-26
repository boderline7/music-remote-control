package t1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class k {

    /* renamed from: a, reason: collision with root package name */
    public static volatile int f9047a = 0;

    /* renamed from: b, reason: collision with root package name */
    public static final j[] f9048b = new j[5];

    /* renamed from: c, reason: collision with root package name */
    public static String f9049c = null;

    /* renamed from: d, reason: collision with root package name */
    public static String f9050d = null;

    /* renamed from: e, reason: collision with root package name */
    public static final String f9051e = "kwplayerhd_ar_1.8.7";

    /* renamed from: f, reason: collision with root package name */
    public static String f9052f = "";

    public static void a(cn.kuwo.autosdk.e eVar) {
        j jVar;
        if (f9047a == 0) {
            jVar = new j();
        } else {
            j[] jVarArr = f9048b;
            synchronized (jVarArr) {
                try {
                    if (f9047a == 0) {
                        jVar = new j();
                    } else {
                        f9047a--;
                        jVar = jVarArr[f9047a];
                        jVarArr[f9047a] = null;
                    }
                } finally {
                }
            }
        }
        jVar.f9045a = eVar;
        if (jVar.f9046b) {
            synchronized (jVar) {
                jVar.notify();
            }
        } else {
            jVar.start();
            jVar.f9046b = true;
        }
    }
}
