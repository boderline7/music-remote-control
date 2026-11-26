package e0;

import android.os.AsyncTask;
import androidx.core.app.JobIntentService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends AsyncTask {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ JobIntentService f6309a;

    public o(JobIntentService jobIntentService) {
        this.f6309a = jobIntentService;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054 A[SYNTHETIC] */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object doInBackground(java.lang.Object[] r5) {
        /*
            r4 = this;
            java.lang.Void[] r5 = (java.lang.Void[]) r5
        L2:
            androidx.core.app.JobIntentService r5 = r4.f6309a
            e0.q r0 = r5.f1739a
            r0.getClass()
            e0.q r5 = r5.f1739a
            java.lang.Object r0 = r5.f6313b
            monitor-enter(r0)
            android.app.job.JobParameters r1 = r5.f6314c     // Catch: java.lang.Throwable -> L16
            r2 = 0
            if (r1 != 0) goto L18
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L16
        L14:
            r0 = r2
            goto L31
        L16:
            r5 = move-exception
            goto L55
        L18:
            android.app.job.JobWorkItem r1 = r1.dequeueWork()     // Catch: java.lang.Throwable -> L16
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L16
            if (r1 == 0) goto L14
            android.content.Intent r0 = r1.getIntent()
            androidx.core.app.JobIntentService r3 = r5.f6312a
            java.lang.ClassLoader r3 = r3.getClassLoader()
            r0.setExtrasClassLoader(r3)
            e0.p r0 = new e0.p
            r0.<init>(r5, r1)
        L31:
            if (r0 == 0) goto L54
            androidx.core.app.JobIntentService r5 = r4.f6309a
            android.app.job.JobWorkItem r1 = r0.f6310a
            r1.getIntent()
            r5.a()
            e0.q r5 = r0.f6311b
            java.lang.Object r5 = r5.f6313b
            monitor-enter(r5)
            e0.q r1 = r0.f6311b     // Catch: java.lang.Throwable -> L4e
            android.app.job.JobParameters r1 = r1.f6314c     // Catch: java.lang.Throwable -> L4e
            if (r1 == 0) goto L50
            android.app.job.JobWorkItem r0 = r0.f6310a     // Catch: java.lang.Throwable -> L4e
            r1.completeWork(r0)     // Catch: java.lang.Throwable -> L4e
            goto L50
        L4e:
            r0 = move-exception
            goto L52
        L50:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L4e
            goto L2
        L52:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L4e
            throw r0
        L54:
            return r2
        L55:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L16
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: e0.o.doInBackground(java.lang.Object[]):java.lang.Object");
    }

    @Override // android.os.AsyncTask
    public final void onCancelled(Object obj) {
        this.f6309a.getClass();
    }

    @Override // android.os.AsyncTask
    public final void onPostExecute(Object obj) {
        this.f6309a.getClass();
    }
}
