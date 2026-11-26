package p0;

import android.graphics.Insets;
import android.view.WindowInsets;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class h2 extends g2 {

    /* renamed from: n, reason: collision with root package name */
    public i0.c f8140n;

    /* renamed from: o, reason: collision with root package name */
    public i0.c f8141o;

    /* renamed from: p, reason: collision with root package name */
    public i0.c f8142p;

    public h2(l2 l2Var, WindowInsets windowInsets) {
        super(l2Var, windowInsets);
        this.f8140n = null;
        this.f8141o = null;
        this.f8142p = null;
    }

    @Override // p0.j2
    public i0.c g() {
        Insets mandatorySystemGestureInsets;
        if (this.f8141o == null) {
            mandatorySystemGestureInsets = this.f8127c.getMandatorySystemGestureInsets();
            this.f8141o = i0.c.c(mandatorySystemGestureInsets);
        }
        return this.f8141o;
    }

    @Override // p0.j2
    public i0.c i() {
        Insets systemGestureInsets;
        if (this.f8140n == null) {
            systemGestureInsets = this.f8127c.getSystemGestureInsets();
            this.f8140n = i0.c.c(systemGestureInsets);
        }
        return this.f8140n;
    }

    @Override // p0.j2
    public i0.c k() {
        Insets tappableElementInsets;
        if (this.f8142p == null) {
            tappableElementInsets = this.f8127c.getTappableElementInsets();
            this.f8142p = i0.c.c(tappableElementInsets);
        }
        return this.f8142p;
    }

    @Override // p0.e2, p0.j2
    public l2 l(int i7, int i8, int i9, int i10) {
        WindowInsets inset;
        inset = this.f8127c.inset(i7, i8, i9, i10);
        return l2.h(null, inset);
    }

    @Override // p0.f2, p0.j2
    public void r(i0.c cVar) {
    }
}
