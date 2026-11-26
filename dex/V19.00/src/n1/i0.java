package n1;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i0 extends s {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f7612a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f7613b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f7614c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l0 f7615d;

    public i0(l0 l0Var, ViewGroup viewGroup, View view, View view2) {
        this.f7615d = l0Var;
        this.f7612a = viewGroup;
        this.f7613b = view;
        this.f7614c = view2;
    }

    @Override // n1.s, n1.q
    public final void c() {
        this.f7612a.getOverlay().remove(this.f7613b);
    }

    @Override // n1.q
    public final void d(r rVar) {
        this.f7614c.setTag(m.save_overlay_view, null);
        this.f7612a.getOverlay().remove(this.f7613b);
        rVar.v(this);
    }

    @Override // n1.s, n1.q
    public final void e() {
        View view = this.f7613b;
        if (view.getParent() == null) {
            this.f7612a.getOverlay().add(view);
            return;
        }
        l0 l0Var = this.f7615d;
        ArrayList arrayList = l0Var.f7665m;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((Animator) arrayList.get(size)).cancel();
        }
        ArrayList arrayList2 = l0Var.f7669q;
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return;
        }
        ArrayList arrayList3 = (ArrayList) l0Var.f7669q.clone();
        int size2 = arrayList3.size();
        for (int i7 = 0; i7 < size2; i7++) {
            ((q) arrayList3.get(i7)).a();
        }
    }
}
