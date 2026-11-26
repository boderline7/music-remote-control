package m0;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    public static final q.f f7497a = new q.f(16);

    /* renamed from: b, reason: collision with root package name */
    public static final ThreadPoolExecutor f7498b;

    /* renamed from: c, reason: collision with root package name */
    public static final Object f7499c;

    /* renamed from: d, reason: collision with root package name */
    public static final q.k f7500d;

    static {
        l lVar = new l();
        lVar.f7509a = "fonts-androidx";
        lVar.f7510b = 10;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), lVar);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f7498b = threadPoolExecutor;
        f7499c = new Object();
        f7500d = new q.k();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static m0.f a(java.lang.String r6, android.content.Context r7, androidx.appcompat.widget.w r8, int r9) {
        /*
            q.f r0 = m0.g.f7497a
            java.lang.Object r1 = r0.a(r6)
            android.graphics.Typeface r1 = (android.graphics.Typeface) r1
            if (r1 == 0) goto L10
            m0.f r6 = new m0.f
            r6.<init>(r1)
            return r6
        L10:
            m0.h r8 = m0.c.a(r7, r8)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L5a
            r1 = 1
            r2 = -3
            m0.i[] r3 = r8.f7502b
            int r8 = r8.f7501a
            if (r8 == 0) goto L22
            if (r8 == r1) goto L20
        L1e:
            r1 = r2
            goto L3b
        L20:
            r1 = -2
            goto L3b
        L22:
            if (r3 == 0) goto L3b
            int r8 = r3.length
            if (r8 != 0) goto L28
            goto L3b
        L28:
            int r8 = r3.length
            r1 = 0
            r4 = r1
        L2b:
            if (r4 >= r8) goto L3b
            r5 = r3[r4]
            int r5 = r5.f7507e
            if (r5 == 0) goto L38
            if (r5 >= 0) goto L36
            goto L1e
        L36:
            r1 = r5
            goto L3b
        L38:
            int r4 = r4 + 1
            goto L2b
        L3b:
            if (r1 == 0) goto L43
            m0.f r6 = new m0.f
            r6.<init>(r1)
            return r6
        L43:
            e.a r8 = i0.h.f6781a
            android.graphics.Typeface r7 = r8.u(r7, r3, r9)
            if (r7 == 0) goto L54
            r0.b(r6, r7)
            m0.f r6 = new m0.f
            r6.<init>(r7)
            return r6
        L54:
            m0.f r6 = new m0.f
            r6.<init>(r2)
            return r6
        L5a:
            m0.f r6 = new m0.f
            r7 = -1
            r6.<init>(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: m0.g.a(java.lang.String, android.content.Context, androidx.appcompat.widget.w, int):m0.f");
    }
}
