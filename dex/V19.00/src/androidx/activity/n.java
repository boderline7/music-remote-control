package androidx.activity;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n implements m, ViewTreeObserver.OnDrawListener, Runnable {

    /* renamed from: b, reason: collision with root package name */
    public Runnable f641b;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ o f643d;

    /* renamed from: a, reason: collision with root package name */
    public final long f640a = SystemClock.uptimeMillis() + 10000;

    /* renamed from: c, reason: collision with root package name */
    public boolean f642c = false;

    public n(androidx.fragment.app.d0 d0Var) {
        this.f643d = d0Var;
    }

    @Override // androidx.activity.m
    public final void a(View view) {
        if (this.f642c) {
            return;
        }
        this.f642c = true;
        view.getViewTreeObserver().addOnDrawListener(this);
    }

    public final void b() {
        o oVar = this.f643d;
        oVar.getWindow().getDecorView().removeCallbacks(this);
        oVar.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f641b = runnable;
        View decorView = this.f643d.getWindow().getDecorView();
        if (!this.f642c) {
            decorView.postOnAnimation(new e(1, this));
        } else if (Looper.myLooper() == Looper.getMainLooper()) {
            decorView.invalidate();
        } else {
            decorView.postInvalidate();
        }
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        boolean z6;
        Runnable runnable = this.f641b;
        if (runnable == null) {
            if (SystemClock.uptimeMillis() > this.f640a) {
                this.f642c = false;
                this.f643d.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        runnable.run();
        this.f641b = null;
        w wVar = this.f643d.mFullyDrawnReporter;
        synchronized (wVar.f654a) {
            z6 = wVar.f655b;
        }
        if (z6) {
            this.f642c = false;
            this.f643d.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f643d.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}
