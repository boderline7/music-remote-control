package g3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e extends a {

    /* renamed from: g, reason: collision with root package name */
    public final h f6459g;

    /* renamed from: h, reason: collision with root package name */
    public final boolean f6460h;

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ExtendedFloatingActionButton f6461i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(ExtendedFloatingActionButton extendedFloatingActionButton, v2.a aVar, h hVar, boolean z6) {
        super(extendedFloatingActionButton, aVar);
        this.f6461i = extendedFloatingActionButton;
        this.f6459g = hVar;
        this.f6460h = z6;
    }

    @Override // g3.a
    public final AnimatorSet a() {
        r2.e eVar = this.f6438f;
        if (eVar == null) {
            if (this.f6437e == null) {
                this.f6437e = r2.e.b(this.f6433a, c());
            }
            eVar = this.f6437e;
            eVar.getClass();
        }
        boolean g7 = eVar.g("width");
        h hVar = this.f6459g;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6461i;
        if (g7) {
            PropertyValuesHolder[] e7 = eVar.e("width");
            e7[0].setFloatValues(extendedFloatingActionButton.getWidth(), hVar.k());
            eVar.h("width", e7);
        }
        if (eVar.g("height")) {
            PropertyValuesHolder[] e8 = eVar.e("height");
            e8[0].setFloatValues(extendedFloatingActionButton.getHeight(), hVar.l());
            eVar.h("height", e8);
        }
        if (eVar.g("paddingStart")) {
            PropertyValuesHolder[] e9 = eVar.e("paddingStart");
            PropertyValuesHolder propertyValuesHolder = e9[0];
            int i7 = d1.OVER_SCROLL_ALWAYS;
            propertyValuesHolder.setFloatValues(m0.f(extendedFloatingActionButton), hVar.p());
            eVar.h("paddingStart", e9);
        }
        if (eVar.g("paddingEnd")) {
            PropertyValuesHolder[] e10 = eVar.e("paddingEnd");
            PropertyValuesHolder propertyValuesHolder2 = e10[0];
            int i8 = d1.OVER_SCROLL_ALWAYS;
            propertyValuesHolder2.setFloatValues(m0.e(extendedFloatingActionButton), hVar.n());
            eVar.h("paddingEnd", e10);
        }
        if (eVar.g("labelOpacity")) {
            PropertyValuesHolder[] e11 = eVar.e("labelOpacity");
            boolean z6 = this.f6460h;
            e11[0].setFloatValues(z6 ? 0.0f : 1.0f, z6 ? 1.0f : 0.0f);
            eVar.h("labelOpacity", e11);
        }
        return b(eVar);
    }

    @Override // g3.a
    public final int c() {
        return this.f6460h ? q2.b.mtrl_extended_fab_change_size_expand_motion_spec : q2.b.mtrl_extended_fab_change_size_collapse_motion_spec;
    }

    @Override // g3.a
    public final void e() {
        this.f6436d.f9388b = null;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6461i;
        extendedFloatingActionButton.E = false;
        extendedFloatingActionButton.setHorizontallyScrolling(false);
        ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        h hVar = this.f6459g;
        layoutParams.width = hVar.s().width;
        layoutParams.height = hVar.s().height;
    }

    @Override // g3.a
    public final void f(Animator animator) {
        v2.a aVar = this.f6436d;
        Animator animator2 = (Animator) aVar.f9388b;
        if (animator2 != null) {
            animator2.cancel();
        }
        aVar.f9388b = animator;
        boolean z6 = this.f6460h;
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6461i;
        extendedFloatingActionButton.D = z6;
        extendedFloatingActionButton.E = true;
        extendedFloatingActionButton.setHorizontallyScrolling(true);
    }

    @Override // g3.a
    public final void g() {
    }

    @Override // g3.a
    public final void h() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6461i;
        boolean z6 = this.f6460h;
        extendedFloatingActionButton.D = z6;
        ViewGroup.LayoutParams layoutParams = extendedFloatingActionButton.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (!z6) {
            extendedFloatingActionButton.H = layoutParams.width;
            extendedFloatingActionButton.I = layoutParams.height;
        }
        h hVar = this.f6459g;
        layoutParams.width = hVar.s().width;
        layoutParams.height = hVar.s().height;
        int p6 = hVar.p();
        int paddingTop = extendedFloatingActionButton.getPaddingTop();
        int n6 = hVar.n();
        int paddingBottom = extendedFloatingActionButton.getPaddingBottom();
        int i7 = d1.OVER_SCROLL_ALWAYS;
        m0.k(extendedFloatingActionButton, p6, paddingTop, n6, paddingBottom);
        extendedFloatingActionButton.requestLayout();
    }

    @Override // g3.a
    public final boolean i() {
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6461i;
        return this.f6460h == extendedFloatingActionButton.D || extendedFloatingActionButton.getIcon() == null || TextUtils.isEmpty(extendedFloatingActionButton.getText());
    }
}
