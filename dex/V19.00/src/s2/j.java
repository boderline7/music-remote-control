package s2;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class j extends c0.b {

    /* renamed from: a, reason: collision with root package name */
    public k f8686a;

    /* renamed from: b, reason: collision with root package name */
    public int f8687b = 0;

    public j() {
    }

    public j(int i7) {
    }

    @Override // c0.b
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i7) {
        x(coordinatorLayout, view, i7);
        if (this.f8686a == null) {
            this.f8686a = new k(view);
        }
        k kVar = this.f8686a;
        View view2 = kVar.f8688a;
        kVar.f8689b = view2.getTop();
        kVar.f8690c = view2.getLeft();
        this.f8686a.a();
        int i8 = this.f8687b;
        if (i8 == 0) {
            return true;
        }
        this.f8686a.b(i8);
        this.f8687b = 0;
        return true;
    }

    public final int w() {
        k kVar = this.f8686a;
        if (kVar != null) {
            return kVar.f8691d;
        }
        return 0;
    }

    public void x(CoordinatorLayout coordinatorLayout, View view, int i7) {
        coordinatorLayout.u(i7, view);
    }
}
