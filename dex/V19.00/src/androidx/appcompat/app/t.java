package androidx.appcompat.app;

import android.content.Context;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class t implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f853a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f854b;

    public /* synthetic */ t(Context context, int i7) {
        this.f853a = i7;
        this.f854b = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a8, code lost:
    
        if (r5 != null) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b9  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r11 = this;
            int r0 = r11.f853a
            android.content.Context r1 = r11.f854b
            switch(r0) {
                case 0: goto L4f;
                case 1: goto L34;
                case 2: goto L27;
                case 3: goto L13;
                case 4: goto Lf;
                case 5: goto Lb;
                default: goto L7;
            }
        L7:
            com.navi.tool.receiver.PersonalizedNavigationReceiver.a(r1)
            return
        Lb:
            com.navi.tool.receiver.BootReceiver.a(r1)
            return
        Lf:
            com.navi.tool.receiver.BootReceiver.d(r1)
            return
        L13:
            java.lang.String r0 = com.navi.tool.amapflying.receiver.AmapStandardBroadcastReceiver.f4503a
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Exception -> L26
            java.lang.String r2 = "AUTONAVI_STANDARD_BROADCAST_RECV"
            r0.<init>(r2)     // Catch: java.lang.Exception -> L26
            java.lang.String r2 = "KEY_TYPE"
            r3 = 10031(0x272f, float:1.4056E-41)
            r0.putExtra(r2, r3)     // Catch: java.lang.Exception -> L26
            r1.sendBroadcast(r0)     // Catch: java.lang.Exception -> L26
        L26:
            return
        L27:
            l.a r0 = new l.a
            r2 = 5
            r0.<init>(r2)
            p3.f r2 = h1.f.f6681a
            r3 = 0
            h1.f.b(r1, r0, r2, r3)
            return
        L34:
            java.util.concurrent.ThreadPoolExecutor r0 = new java.util.concurrent.ThreadPoolExecutor
            r5 = 0
            r6 = 1
            r7 = 0
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.LinkedBlockingQueue r10 = new java.util.concurrent.LinkedBlockingQueue
            r10.<init>()
            r4 = r0
            r4.<init>(r5, r6, r7, r9, r10)
            androidx.appcompat.app.t r2 = new androidx.appcompat.app.t
            r3 = 2
            r2.<init>(r1, r3)
            r0.execute(r2)
            return
        L4f:
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            r3 = 1
            if (r0 < r2) goto Ld1
            android.content.ComponentName r0 = new android.content.ComponentName
            java.lang.String r2 = "androidx.appcompat.app.AppLocalesMetadataHolderService"
            r0.<init>(r1, r2)
            android.content.pm.PackageManager r2 = r1.getPackageManager()
            int r2 = r2.getComponentEnabledSetting(r0)
            if (r2 == r3) goto Ld1
            boolean r2 = n1.z.B0()
            java.lang.String r4 = "locale"
            if (r2 == 0) goto La6
            q.c r2 = androidx.appcompat.app.w.f872g
            java.util.Iterator r2 = r2.iterator()
        L75:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L94
            java.lang.Object r5 = r2.next()
            java.lang.ref.WeakReference r5 = (java.lang.ref.WeakReference) r5
            java.lang.Object r5 = r5.get()
            androidx.appcompat.app.w r5 = (androidx.appcompat.app.w) r5
            if (r5 == 0) goto L75
            androidx.appcompat.app.j0 r5 = (androidx.appcompat.app.j0) r5
            android.content.Context r5 = r5.f778k
            if (r5 == 0) goto L75
            java.lang.Object r2 = r5.getSystemService(r4)
            goto L95
        L94:
            r2 = 0
        L95:
            if (r2 == 0) goto Lab
            android.os.LocaleList r2 = androidx.appcompat.app.v.a(r2)
            l0.g r5 = new l0.g
            l0.i r6 = new l0.i
            r6.<init>(r2)
            r5.<init>(r6)
            goto Lad
        La6:
            l0.g r5 = androidx.appcompat.app.w.f868c
            if (r5 == 0) goto Lab
            goto Lad
        Lab:
            l0.g r5 = l0.g.f7273b
        Lad:
            l0.h r2 = r5.f7274a
            l0.i r2 = (l0.i) r2
            android.os.LocaleList r2 = r2.f7275a
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto Lca
            java.lang.String r2 = l6.f.o0(r1)
            java.lang.Object r4 = r1.getSystemService(r4)
            if (r4 == 0) goto Lca
            android.os.LocaleList r2 = androidx.appcompat.app.u.a(r2)
            androidx.appcompat.app.v.b(r4, r2)
        Lca:
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            r1.setComponentEnabledSetting(r0, r3, r3)
        Ld1:
            androidx.appcompat.app.w.f871f = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.t.run():void");
    }
}
