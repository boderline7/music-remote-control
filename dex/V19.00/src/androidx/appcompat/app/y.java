package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.h1;
import androidx.appcompat.widget.t1;
import p0.d1;
import p0.l2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y implements p0.b0, t1, h1, d, k.b0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f883a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j0 f884b;

    public /* synthetic */ y(j0 j0Var, int i7) {
        this.f883a = i7;
        this.f884b = j0Var;
    }

    @Override // androidx.appcompat.app.d
    public final Drawable B() {
        int resourceId;
        Context z6 = this.f884b.z();
        TypedArray obtainStyledAttributes = z6.obtainStyledAttributes((AttributeSet) null, new int[]{f.a.homeAsUpIndicator});
        Drawable drawable = (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) ? obtainStyledAttributes.getDrawable(0) : l6.f.A(z6, resourceId);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    @Override // k.b0
    public final void a(k.o oVar, boolean z6) {
        i0 i0Var;
        int i7 = this.f883a;
        j0 j0Var = this.f884b;
        switch (i7) {
            case 4:
                j0Var.r(oVar);
                break;
            default:
                k.o k7 = oVar.k();
                int i8 = 0;
                boolean z7 = k7 != oVar;
                if (z7) {
                    oVar = k7;
                }
                i0[] i0VarArr = j0Var.L;
                int length = i0VarArr != null ? i0VarArr.length : 0;
                while (true) {
                    if (i8 >= length) {
                        i0Var = null;
                    } else {
                        i0Var = i0VarArr[i8];
                        if (i0Var == null || i0Var.f736h != oVar) {
                            i8++;
                        }
                    }
                }
                if (i0Var != null) {
                    if (!z7) {
                        j0Var.t(i0Var, z6);
                        break;
                    } else {
                        j0Var.q(i0Var.f729a, i0Var, k7);
                        j0Var.t(i0Var, true);
                        break;
                    }
                }
                break;
        }
    }

    @Override // androidx.appcompat.app.d
    public final void b(int i7) {
        j0 j0Var = this.f884b;
        j0Var.C();
        b bVar = j0Var.f782o;
        if (bVar != null) {
            bVar.q(i7);
        }
    }

    @Override // k.b0
    public final boolean c(k.o oVar) {
        Window.Callback callback;
        int i7 = this.f883a;
        j0 j0Var = this.f884b;
        switch (i7) {
            case 4:
                Window.Callback callback2 = j0Var.f779l.getCallback();
                if (callback2 != null) {
                    callback2.onMenuOpened(108, oVar);
                    break;
                }
                break;
            default:
                if (oVar == oVar.k() && j0Var.F && (callback = j0Var.f779l.getCallback()) != null && !j0Var.Q) {
                    callback.onMenuOpened(108, oVar);
                    break;
                }
                break;
        }
        return true;
    }

    @Override // p0.b0
    public final l2 e(View view, l2 l2Var) {
        int d7 = l2Var.d();
        int L = this.f884b.L(l2Var, null);
        if (d7 != L) {
            l2Var = l2Var.f(l2Var.b(), L, l2Var.c(), l2Var.a());
        }
        return d1.j(view, l2Var);
    }

    @Override // androidx.appcompat.app.d
    public final void g(Drawable drawable, int i7) {
        j0 j0Var = this.f884b;
        j0Var.C();
        b bVar = j0Var.f782o;
        if (bVar != null) {
            bVar.r(drawable);
            bVar.q(i7);
        }
    }

    @Override // androidx.appcompat.app.d
    public final Context m() {
        return this.f884b.z();
    }

    @Override // androidx.appcompat.app.d
    public final boolean o() {
        j0 j0Var = this.f884b;
        j0Var.C();
        b bVar = j0Var.f782o;
        return (bVar == null || (bVar.d() & 4) == 0) ? false : true;
    }
}
