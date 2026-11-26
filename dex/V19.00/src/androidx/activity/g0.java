package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.fragment.app.q0;
import androidx.fragment.app.x0;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g0 {

    /* renamed from: a, reason: collision with root package name */
    public final Runnable f623a;

    /* renamed from: b, reason: collision with root package name */
    public final c5.k f624b = new c5.k();

    /* renamed from: c, reason: collision with root package name */
    public q0 f625c;

    /* renamed from: d, reason: collision with root package name */
    public final OnBackInvokedCallback f626d;

    /* renamed from: e, reason: collision with root package name */
    public OnBackInvokedDispatcher f627e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f628f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f629g;

    public g0(Runnable runnable) {
        this.f623a = runnable;
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 33) {
            this.f626d = i7 >= 34 ? c0.INSTANCE.a(new x(this, 0), new x(this, 1), new y(this, 0), new y(this, 1)) : a0.INSTANCE.a(new y(this, 2));
        }
    }

    public final void a() {
        Object obj;
        c5.k kVar = this.f624b;
        ListIterator<E> listIterator = kVar.listIterator(kVar.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            } else {
                obj = listIterator.previous();
                if (((q0) obj).f2039a) {
                    break;
                }
            }
        }
        q0 q0Var = (q0) obj;
        this.f625c = null;
        if (q0Var == null) {
            Runnable runnable = this.f623a;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        x0 x0Var = q0Var.f2042d;
        x0Var.s(true);
        if (x0Var.f2109h.f2039a) {
            x0Var.H();
        } else {
            x0Var.f2108g.a();
        }
    }

    public final void b(boolean z6) {
        OnBackInvokedCallback onBackInvokedCallback;
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.f627e;
        if (onBackInvokedDispatcher == null || (onBackInvokedCallback = this.f626d) == null) {
            return;
        }
        if (z6 && !this.f628f) {
            a0.INSTANCE.b(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f628f = true;
        } else {
            if (z6 || !this.f628f) {
                return;
            }
            a0.INSTANCE.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f628f = false;
        }
    }

    public final void c() {
        boolean z6 = this.f629g;
        c5.k kVar = this.f624b;
        boolean z7 = false;
        if (!(kVar instanceof Collection) || !kVar.isEmpty()) {
            Iterator it = kVar.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((q0) it.next()).f2039a) {
                    z7 = true;
                    break;
                }
            }
        }
        this.f629g = z7;
        if (z7 == z6 || Build.VERSION.SDK_INT < 33) {
            return;
        }
        b(z7);
    }
}
