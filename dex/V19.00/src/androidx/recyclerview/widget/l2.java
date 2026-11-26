package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class l2 extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final RecyclerView f2528d;

    /* renamed from: e, reason: collision with root package name */
    public final k2 f2529e;

    public l2(RecyclerView recyclerView) {
        this.f2528d = recyclerView;
        k2 k2Var = this.f2529e;
        if (k2Var != null) {
            this.f2529e = k2Var;
        } else {
            this.f2529e = new k2(this);
        }
    }

    @Override // p0.c
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || this.f2528d.N()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().W(accessibilityEvent);
        }
    }

    @Override // p0.c
    public void d(View view, q0.i iVar) {
        this.f8103a.onInitializeAccessibilityNodeInfo(view, iVar.f8426a);
        RecyclerView recyclerView = this.f2528d;
        if (recyclerView.N() || recyclerView.getLayoutManager() == null) {
            return;
        }
        r1 layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.f2622b;
        layoutManager.X(recyclerView2.f2299b, recyclerView2.f2308f0, iVar);
    }

    @Override // p0.c
    public final boolean g(View view, int i7, Bundle bundle) {
        if (super.g(view, i7, bundle)) {
            return true;
        }
        RecyclerView recyclerView = this.f2528d;
        if (recyclerView.N() || recyclerView.getLayoutManager() == null) {
            return false;
        }
        r1 layoutManager = recyclerView.getLayoutManager();
        RecyclerView recyclerView2 = layoutManager.f2622b;
        return layoutManager.k0(recyclerView2.f2299b, recyclerView2.f2308f0, i7, bundle);
    }
}
