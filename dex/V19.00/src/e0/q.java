package e0;

import android.app.job.JobParameters;
import android.app.job.JobServiceEngine;
import android.os.AsyncTask;
import androidx.core.app.JobIntentService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class q extends JobServiceEngine {

    /* renamed from: a, reason: collision with root package name */
    public final JobIntentService f6312a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f6313b;

    /* renamed from: c, reason: collision with root package name */
    public JobParameters f6314c;

    public q(JobIntentService jobIntentService) {
        super(jobIntentService);
        this.f6313b = new Object();
        this.f6312a = jobIntentService;
    }

    @Override // android.app.job.JobServiceEngine
    public final boolean onStartJob(JobParameters jobParameters) {
        this.f6314c = jobParameters;
        JobIntentService jobIntentService = this.f6312a;
        if (jobIntentService.f1740b != null) {
            return true;
        }
        o oVar = new o(jobIntentService);
        jobIntentService.f1740b = oVar;
        oVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return true;
    }

    @Override // android.app.job.JobServiceEngine
    public final boolean onStopJob(JobParameters jobParameters) {
        o oVar = this.f6312a.f1740b;
        if (oVar != null) {
            oVar.cancel(false);
        }
        synchronized (this.f6313b) {
            this.f6314c = null;
        }
        return true;
    }
}
