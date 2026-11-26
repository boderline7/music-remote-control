package m0;

import android.os.Process;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k extends Thread {

    /* renamed from: a, reason: collision with root package name */
    public final int f7508a;

    public k(Runnable runnable, String str, int i7) {
        super(runnable, str);
        this.f7508a = i7;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.f7508a);
        super.run();
    }
}
