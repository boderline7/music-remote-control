package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.WeakHashMap;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k2 extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final l2 f2521d;

    /* renamed from: e, reason: collision with root package name */
    public final WeakHashMap f2522e = new WeakHashMap();

    public k2(l2 l2Var) {
        this.f2521d = l2Var;
    }

    @Override // p0.c
    public final boolean a(View view, AccessibilityEvent accessibilityEvent) {
        p0.c cVar = (p0.c) this.f2522e.get(view);
        return cVar != null ? cVar.a(view, accessibilityEvent) : this.f8103a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // p0.c
    public final q0.m b(View view) {
        p0.c cVar = (p0.c) this.f2522e.get(view);
        return cVar != null ? cVar.b(view) : super.b(view);
    }

    @Override // p0.c
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        p0.c cVar = (p0.c) this.f2522e.get(view);
        if (cVar != null) {
            cVar.c(view, accessibilityEvent);
        } else {
            super.c(view, accessibilityEvent);
        }
    }

    @Override // p0.c
    public final void d(View view, q0.i iVar) {
        l2 l2Var = this.f2521d;
        boolean N = l2Var.f2528d.N();
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        View.AccessibilityDelegate accessibilityDelegate = this.f8103a;
        if (!N) {
            RecyclerView recyclerView = l2Var.f2528d;
            if (recyclerView.getLayoutManager() != null) {
                recyclerView.getLayoutManager().Y(view, iVar);
                p0.c cVar = (p0.c) this.f2522e.get(view);
                if (cVar != null) {
                    cVar.d(view, iVar);
                    return;
                } else {
                    accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    return;
                }
            }
        }
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
    }

    @Override // p0.c
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        p0.c cVar = (p0.c) this.f2522e.get(view);
        if (cVar != null) {
            cVar.e(view, accessibilityEvent);
        } else {
            super.e(view, accessibilityEvent);
        }
    }

    @Override // p0.c
    public final boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        p0.c cVar = (p0.c) this.f2522e.get(viewGroup);
        return cVar != null ? cVar.f(viewGroup, view, accessibilityEvent) : this.f8103a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // p0.c
    public final boolean g(View view, int i7, Bundle bundle) {
        l2 l2Var = this.f2521d;
        if (!l2Var.f2528d.N()) {
            RecyclerView recyclerView = l2Var.f2528d;
            if (recyclerView.getLayoutManager() != null) {
                p0.c cVar = (p0.c) this.f2522e.get(view);
                if (cVar != null) {
                    if (cVar.g(view, i7, bundle)) {
                        return true;
                    }
                } else if (super.g(view, i7, bundle)) {
                    return true;
                }
                z1 z1Var = recyclerView.getLayoutManager().f2622b.f2299b;
                return false;
            }
        }
        return super.g(view, i7, bundle);
    }

    @Override // p0.c
    public final void h(View view, int i7) {
        p0.c cVar = (p0.c) this.f2522e.get(view);
        if (cVar != null) {
            cVar.h(view, i7);
        } else {
            super.h(view, i7);
        }
    }

    @Override // p0.c
    public final void i(View view, AccessibilityEvent accessibilityEvent) {
        p0.c cVar = (p0.c) this.f2522e.get(view);
        if (cVar != null) {
            cVar.i(view, accessibilityEvent);
        } else {
            super.i(view, accessibilityEvent);
        }
    }
}
