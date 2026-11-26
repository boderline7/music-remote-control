package h1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class n {

    /* renamed from: a, reason: collision with root package name */
    public static final r.g f6689a = new r.g();

    /* renamed from: b, reason: collision with root package name */
    public static final Object f6690b = new Object();

    /* renamed from: c, reason: collision with root package name */
    public static m f6691c = null;

    public static long a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? k.a(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    public static m b() {
        m mVar = new m();
        f6691c = mVar;
        r.g gVar = f6689a;
        gVar.getClass();
        if (r.f.f8503f.k(gVar, null, mVar)) {
            r.f.b(gVar);
        }
        return f6691c;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(21:14|(1:79)(1:18)|19|(1:78)(1:23)|24|25|26|(2:64|65)(1:28)|29|(8:36|(1:40)|(1:59)(1:47)|48|(2:55|56)|52|53|54)|(1:63)|(1:40)|(1:42)|59|48|(1:50)|55|56|52|53|54) */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009d, code lost:
    
        r4 = 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(android.content.Context r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: h1.n.c(android.content.Context, boolean):void");
    }
}
