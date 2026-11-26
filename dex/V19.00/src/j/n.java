package j;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import p0.l1;
import p0.m1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class n {

    /* renamed from: c, reason: collision with root package name */
    public Interpolator f6937c;

    /* renamed from: d, reason: collision with root package name */
    public m1 f6938d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f6939e;

    /* renamed from: b, reason: collision with root package name */
    public long f6936b = -1;

    /* renamed from: f, reason: collision with root package name */
    public final m f6940f = new m(this);

    /* renamed from: a, reason: collision with root package name */
    public final ArrayList f6935a = new ArrayList();

    public final void a() {
        if (this.f6939e) {
            Iterator it = this.f6935a.iterator();
            while (it.hasNext()) {
                ((l1) it.next()).b();
            }
            this.f6939e = false;
        }
    }

    public final void b() {
        View view;
        if (this.f6939e) {
            return;
        }
        Iterator it = this.f6935a.iterator();
        while (it.hasNext()) {
            l1 l1Var = (l1) it.next();
            long j7 = this.f6936b;
            if (j7 >= 0) {
                l1Var.c(j7);
            }
            Interpolator interpolator = this.f6937c;
            if (interpolator != null && (view = (View) l1Var.f8160a.get()) != null) {
                view.animate().setInterpolator(interpolator);
            }
            if (this.f6938d != null) {
                l1Var.d(this.f6940f);
            }
            View view2 = (View) l1Var.f8160a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.f6939e = true;
    }
}
