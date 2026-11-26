package g3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public final Context f6433a;

    /* renamed from: b, reason: collision with root package name */
    public final ExtendedFloatingActionButton f6434b;

    /* renamed from: c, reason: collision with root package name */
    public final ArrayList f6435c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    public final v2.a f6436d;

    /* renamed from: e, reason: collision with root package name */
    public r2.e f6437e;

    /* renamed from: f, reason: collision with root package name */
    public r2.e f6438f;

    public a(ExtendedFloatingActionButton extendedFloatingActionButton, v2.a aVar) {
        this.f6434b = extendedFloatingActionButton;
        this.f6433a = extendedFloatingActionButton.getContext();
        this.f6436d = aVar;
    }

    public AnimatorSet a() {
        r2.e eVar = this.f6438f;
        if (eVar == null) {
            if (this.f6437e == null) {
                this.f6437e = r2.e.b(this.f6433a, c());
            }
            eVar = this.f6437e;
            eVar.getClass();
        }
        return b(eVar);
    }

    public final AnimatorSet b(r2.e eVar) {
        ArrayList arrayList = new ArrayList();
        boolean g7 = eVar.g("opacity");
        ExtendedFloatingActionButton extendedFloatingActionButton = this.f6434b;
        if (g7) {
            arrayList.add(eVar.d("opacity", extendedFloatingActionButton, View.ALPHA));
        }
        if (eVar.g("scale")) {
            arrayList.add(eVar.d("scale", extendedFloatingActionButton, View.SCALE_Y));
            arrayList.add(eVar.d("scale", extendedFloatingActionButton, View.SCALE_X));
        }
        if (eVar.g("width")) {
            arrayList.add(eVar.d("width", extendedFloatingActionButton, ExtendedFloatingActionButton.K));
        }
        if (eVar.g("height")) {
            arrayList.add(eVar.d("height", extendedFloatingActionButton, ExtendedFloatingActionButton.L));
        }
        if (eVar.g("paddingStart")) {
            arrayList.add(eVar.d("paddingStart", extendedFloatingActionButton, ExtendedFloatingActionButton.M));
        }
        if (eVar.g("paddingEnd")) {
            arrayList.add(eVar.d("paddingEnd", extendedFloatingActionButton, ExtendedFloatingActionButton.N));
        }
        if (eVar.g("labelOpacity")) {
            arrayList.add(eVar.d("labelOpacity", extendedFloatingActionButton, new n1.b(this)));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        z.S0(animatorSet, arrayList);
        return animatorSet;
    }

    public abstract int c();

    public void d() {
        this.f6436d.f9388b = null;
    }

    public abstract void e();

    public abstract void f(Animator animator);

    public abstract void g();

    public abstract void h();

    public abstract boolean i();
}
