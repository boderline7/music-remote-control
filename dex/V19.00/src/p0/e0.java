package p0;

import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e0 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final View f8119a;

    /* renamed from: b, reason: collision with root package name */
    public ViewTreeObserver f8120b;

    /* renamed from: c, reason: collision with root package name */
    public final Runnable f8121c;

    public e0(View view, Runnable runnable) {
        this.f8119a = view;
        this.f8120b = view.getViewTreeObserver();
        this.f8121c = runnable;
    }

    public static void a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        if (runnable == null) {
            throw new NullPointerException("runnable == null");
        }
        e0 e0Var = new e0(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(e0Var);
        view.addOnAttachStateChangeListener(e0Var);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean isAlive = this.f8120b.isAlive();
        View view = this.f8119a;
        if (isAlive) {
            this.f8120b.removeOnPreDrawListener(this);
        } else {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view.removeOnAttachStateChangeListener(this);
        this.f8121c.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.f8120b = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean isAlive = this.f8120b.isAlive();
        View view2 = this.f8119a;
        if (isAlive) {
            this.f8120b.removeOnPreDrawListener(this);
        } else {
            view2.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view2.removeOnAttachStateChangeListener(this);
    }
}
