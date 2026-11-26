package androidx.fragment.app;

import android.transition.Transition;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k extends j {

    /* renamed from: c, reason: collision with root package name */
    public final Object f2001c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f2002d;

    /* renamed from: e, reason: collision with root package name */
    public final Object f2003e;

    public k(v1 v1Var, l0.d dVar, boolean z6, boolean z7) {
        super(v1Var, dVar);
        x1 x1Var = v1Var.f2070a;
        x1 x1Var2 = x1.VISIBLE;
        z zVar = v1Var.f2072c;
        if (x1Var == x1Var2) {
            this.f2001c = z6 ? zVar.getReenterTransition() : zVar.getEnterTransition();
            this.f2002d = z6 ? zVar.getAllowReturnTransitionOverlap() : zVar.getAllowEnterTransitionOverlap();
        } else {
            this.f2001c = z6 ? zVar.getReturnTransition() : zVar.getExitTransition();
            this.f2002d = true;
        }
        if (!z7) {
            this.f2003e = null;
        } else if (z6) {
            this.f2003e = zVar.getSharedElementReturnTransition();
        } else {
            this.f2003e = zVar.getSharedElementEnterTransition();
        }
    }

    public final q1 c(Object obj) {
        if (obj == null) {
            return null;
        }
        o1 o1Var = j1.f1999a;
        if (obj instanceof Transition) {
            return o1Var;
        }
        q1 q1Var = j1.f2000b;
        if (q1Var != null && q1Var.e(obj)) {
            return q1Var;
        }
        throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.f1997a.f2072c + " is not a valid framework Transition or AndroidX Transition");
    }
}
