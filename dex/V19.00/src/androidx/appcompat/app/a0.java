package androidx.appcompat.app;

import android.view.MenuItem;
import android.view.ViewGroup;
import com.yalantis.ucrop.view.CropImageView;
import p0.d1;
import p0.l1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a0 implements j.b {

    /* renamed from: a, reason: collision with root package name */
    public final j.b f671a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j0 f672b;

    public a0(j0 j0Var, j.b bVar) {
        this.f672b = j0Var;
        this.f671a = bVar;
    }

    @Override // j.b
    public final boolean a(j.c cVar, MenuItem menuItem) {
        return this.f671a.a(cVar, menuItem);
    }

    @Override // j.b
    public final boolean b(j.c cVar, k.o oVar) {
        return this.f671a.b(cVar, oVar);
    }

    @Override // j.b
    public final void d(j.c cVar) {
        this.f671a.d(cVar);
        j0 j0Var = this.f672b;
        if (j0Var.f790w != null) {
            j0Var.f779l.getDecorView().removeCallbacks(j0Var.f791x);
        }
        if (j0Var.f789v != null) {
            l1 l1Var = j0Var.f792y;
            if (l1Var != null) {
                l1Var.b();
            }
            l1 a7 = d1.a(j0Var.f789v);
            a7.a(CropImageView.DEFAULT_ASPECT_RATIO);
            j0Var.f792y = a7;
            a7.d(new z(2, this));
        }
        s sVar = j0Var.f781n;
        if (sVar != null) {
            sVar.onSupportActionModeFinished(j0Var.f788u);
        }
        j0Var.f788u = null;
        ViewGroup viewGroup = j0Var.A;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        p0.p0.c(viewGroup);
        j0Var.K();
    }

    @Override // j.b
    public final boolean i(j.c cVar, k.o oVar) {
        ViewGroup viewGroup = this.f672b.A;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        p0.p0.c(viewGroup);
        return this.f671a.i(cVar, oVar);
    }
}
