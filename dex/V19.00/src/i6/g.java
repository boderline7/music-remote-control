package i6;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import org.greenrobot.eventbus.EventBusException;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends Handler {

    /* renamed from: a, reason: collision with root package name */
    public final i f6843a;

    /* renamed from: b, reason: collision with root package name */
    public final int f6844b;

    /* renamed from: c, reason: collision with root package name */
    public final e f6845c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f6846d;

    public g(e eVar, Looper looper) {
        super(looper);
        this.f6845c = eVar;
        this.f6844b = 10;
        this.f6843a = new i(22);
    }

    public final void a(Object obj, p pVar) {
        j a7 = j.a(obj, pVar);
        synchronized (this) {
            try {
                this.f6843a.f(a7);
                if (!this.f6846d) {
                    this.f6846d = true;
                    if (!sendMessage(obtainMessage())) {
                        throw new EventBusException("Could not send handler message");
                    }
                }
            } finally {
            }
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                j t6 = this.f6843a.t();
                if (t6 == null) {
                    synchronized (this) {
                        t6 = this.f6843a.t();
                        if (t6 == null) {
                            this.f6846d = false;
                            return;
                        }
                    }
                }
                this.f6845c.c(t6);
            } while (SystemClock.uptimeMillis() - uptimeMillis < this.f6844b);
            if (!sendMessage(obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            this.f6846d = true;
        } catch (Throwable th) {
            this.f6846d = false;
            throw th;
        }
    }
}
