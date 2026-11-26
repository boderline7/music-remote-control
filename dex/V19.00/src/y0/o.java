package y0;

import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class o extends l6.f {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l6.f f9992d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ThreadPoolExecutor f9993e;

    public o(l6.f fVar, ThreadPoolExecutor threadPoolExecutor) {
        this.f9992d = fVar;
        this.f9993e = threadPoolExecutor;
    }

    @Override // l6.f
    public final void Y(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.f9993e;
        try {
            this.f9992d.Y(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // l6.f
    public final void e0(j.h hVar) {
        ThreadPoolExecutor threadPoolExecutor = this.f9993e;
        try {
            this.f9992d.e0(hVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
