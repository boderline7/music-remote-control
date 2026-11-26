package w5;

import androidx.appcompat.widget.u;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public final ThreadPoolExecutor f9510a;

    public e(t5.a aVar) {
        this.f9510a = new ThreadPoolExecutor(0, u.MAX_ACTIVITY_COUNT_UNLIMITED, 60L, TimeUnit.SECONDS, new SynchronousQueue(), aVar);
    }
}
