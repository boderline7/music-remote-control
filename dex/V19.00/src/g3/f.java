package g3;

import android.animation.Animator;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class f extends a {

    /* renamed from: g, reason: collision with root package name */
    public boolean f6462g;

    /* renamed from: h, reason: collision with root package name */
    public final /* synthetic */ ExtendedFloatingActionButton f6463h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(ExtendedFloatingActionButton extendedFloatingActionButton, v2.a aVar) {
        super(extendedFloatingActionButton, aVar);
        this.f6463h = extendedFloatingActionButton;
    }

    @Override // g3.a
    public final int c() {
        return q2.b.mtrl_extended_fab_hide_motion_spec;
    }

    @Override // g3.a
    public final void d() {
        super.d();
        this.f6462g = true;
    }

    @Override // g3.a
    public final void e() {
        this.f6436d.f9388b = null;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6463h;
        extendedFloatingActionButton.f3713u = 0;
        if (this.f6462g) {
            return;
        }
        extendedFloatingActionButton.setVisibility(8);
    }

    @Override // g3.a
    public final void f(Animator animator) {
        v2.a aVar = this.f6436d;
        Animator animator2 = (Animator) aVar.f9388b;
        if (animator2 != null) {
            animator2.cancel();
        }
        aVar.f9388b = animator;
        this.f6462g = false;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6463h;
        extendedFloatingActionButton.setVisibility(0);
        extendedFloatingActionButton.f3713u = 1;
    }

    @Override // g3.a
    public final void g() {
    }

    @Override // g3.a
    public final void h() {
        this.f6463h.setVisibility(8);
    }

    @Override // g3.a
    public final boolean i() {
        int i7 = ExtendedFloatingActionButton.J;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6463h;
        if (extendedFloatingActionButton.getVisibility() == 0) {
            if (extendedFloatingActionButton.f3713u != 1) {
                return false;
            }
        } else if (extendedFloatingActionButton.f3713u == 2) {
            return false;
        }
        return true;
    }
}
