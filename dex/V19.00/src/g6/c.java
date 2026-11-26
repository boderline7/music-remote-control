package g6;

import java.util.concurrent.locks.ReentrantLock;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class c extends Thread {
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        ReentrantLock reentrantLock;
        f a7;
        while (true) {
            try {
                f.Companion.getClass();
                reentrantLock = f.f6562g;
                reentrantLock.lock();
                try {
                    a7 = b.a();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            } catch (InterruptedException unused) {
            }
            if (a7 == f.f6566k) {
                f.f6566k = null;
                reentrantLock.unlock();
                return;
            } else {
                reentrantLock.unlock();
                if (a7 != null) {
                    a7.k();
                }
            }
        }
    }
}
