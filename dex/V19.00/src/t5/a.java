package t5;

import java.util.concurrent.ThreadFactory;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class a implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f9090a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f9091b;

    public /* synthetic */ a(String str, boolean z6) {
        this.f9090a = str;
        this.f9091b = z6;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        String str = this.f9090a;
        i5.a.g("$name", str);
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(this.f9091b);
        return thread;
    }
}
