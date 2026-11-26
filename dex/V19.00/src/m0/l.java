package m0;

import java.util.concurrent.ThreadFactory;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    public String f7509a;

    /* renamed from: b, reason: collision with root package name */
    public int f7510b;

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new k(runnable, this.f7509a, this.f7510b);
    }
}
