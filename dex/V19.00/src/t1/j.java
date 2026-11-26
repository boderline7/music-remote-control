package t1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends Thread {

    /* renamed from: a, reason: collision with root package name */
    public volatile Runnable f9045a;

    /* renamed from: b, reason: collision with root package name */
    public volatile boolean f9046b;

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
    
        wait();
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r4 = this;
        L0:
            r0 = 0
            android.os.Process.setThreadPriority(r0)
            java.lang.Runnable r1 = r4.f9045a
            r1.run()
            int r1 = t1.k.f9047a
            r2 = 5
            if (r1 >= r2) goto L33
            monitor-enter(r4)
            t1.j[] r1 = t1.k.f9048b     // Catch: java.lang.Throwable -> L19
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L19
            int r3 = t1.k.f9047a     // Catch: java.lang.Throwable -> L1b
            if (r3 < r2) goto L1d
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1b
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L19
            goto L33
        L19:
            r0 = move-exception
            goto L31
        L1b:
            r0 = move-exception
            goto L2f
        L1d:
            int r2 = t1.k.f9047a     // Catch: java.lang.Throwable -> L1b
            r1[r2] = r4     // Catch: java.lang.Throwable -> L1b
            int r2 = t1.k.f9047a     // Catch: java.lang.Throwable -> L1b
            int r2 = r2 + 1
            t1.k.f9047a = r2     // Catch: java.lang.Throwable -> L1b
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1b
            r4.wait()     // Catch: java.lang.Throwable -> L19 java.lang.InterruptedException -> L2d
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L19
            goto L0
        L2d:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L19
            goto L33
        L2f:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1b
            throw r0     // Catch: java.lang.Throwable -> L19
        L31:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L19
            throw r0
        L33:
            r4.f9046b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.j.run():void");
    }
}
