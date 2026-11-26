package androidx.viewpager2.widget;

import android.R;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.h1;
import p0.d1;
import p0.l0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l extends n0.k {

    /* renamed from: b, reason: collision with root package name */
    public final k f2914b;

    /* renamed from: c, reason: collision with root package name */
    public final k f2915c;

    /* renamed from: d, reason: collision with root package name */
    public e f2916d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ViewPager2 f2917e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(ViewPager2 viewPager2) {
        super(viewPager2);
        this.f2917e = viewPager2;
        this.f2914b = new k(this, 0);
        this.f2915c = new k(this, 1);
    }

    public final void e(h1 h1Var) {
        k();
        if (h1Var != null) {
            h1Var.registerAdapterDataObserver(this.f2916d);
        }
    }

    public final void f(h1 h1Var) {
        if (h1Var != null) {
            h1Var.unregisterAdapterDataObserver(this.f2916d);
        }
    }

    public final void g(RecyclerView recyclerView) {
        int i7 = d1.OVER_SCROLL_ALWAYS;
        l0.s(recyclerView, 2);
        this.f2916d = new e(1, this);
        ViewPager2 viewPager2 = this.f2917e;
        if (l0.c(viewPager2) == 0) {
            l0.s(viewPager2, 1);
        }
    }

    public final void h(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i7;
        int i8;
        int itemCount;
        ViewPager2 viewPager2 = this.f2917e;
        if (viewPager2.getAdapter() == null) {
            i7 = 0;
            i8 = 0;
        } else if (viewPager2.getOrientation() == 1) {
            i7 = viewPager2.getAdapter().getItemCount();
            i8 = 0;
        } else {
            i8 = viewPager2.getAdapter().getItemCount();
            i7 = 0;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) q0.h.a(i7, i8, 0).f8425a);
        h1 adapter = viewPager2.getAdapter();
        if (adapter == null || (itemCount = adapter.getItemCount()) == 0 || !viewPager2.f2885r) {
            return;
        }
        if (viewPager2.f2871d > 0) {
            accessibilityNodeInfo.addAction(8192);
        }
        if (viewPager2.f2871d < itemCount - 1) {
            accessibilityNodeInfo.addAction(4096);
        }
        accessibilityNodeInfo.setScrollable(true);
    }

    public final void i(int i7, Bundle bundle) {
        if (i7 != 8192 && i7 != 4096) {
            throw new IllegalStateException();
        }
        ViewPager2 viewPager2 = this.f2917e;
        int currentItem = i7 == 8192 ? viewPager2.getCurrentItem() - 1 : viewPager2.getCurrentItem() + 1;
        if (viewPager2.f2885r) {
            viewPager2.c(currentItem, true);
        }
    }

    public final void j(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setSource(this.f2917e);
        accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
    }

    public final void k() {
        int itemCount;
        int i7 = R.id.accessibilityActionPageLeft;
        ViewPager2 viewPager2 = this.f2917e;
        d1.l(R.id.accessibilityActionPageLeft, viewPager2);
        d1.i(0, viewPager2);
        d1.l(R.id.accessibilityActionPageRight, viewPager2);
        d1.i(0, viewPager2);
        d1.l(R.id.accessibilityActionPageUp, viewPager2);
        d1.i(0, viewPager2);
        d1.l(R.id.accessibilityActionPageDown, viewPager2);
        d1.i(0, viewPager2);
        if (viewPager2.getAdapter() == null || (itemCount = viewPager2.getAdapter().getItemCount()) == 0 || !viewPager2.f2885r) {
            return;
        }
        int orientation = viewPager2.getOrientation();
        k kVar = this.f2915c;
        k kVar2 = this.f2914b;
        if (orientation != 0) {
            if (viewPager2.f2871d < itemCount - 1) {
                d1.m(viewPager2, new q0.g(R.id.accessibilityActionPageDown), null, kVar2);
            }
            if (viewPager2.f2871d > 0) {
                d1.m(viewPager2, new q0.g(R.id.accessibilityActionPageUp), null, kVar);
                return;
            }
            return;
        }
        boolean z6 = viewPager2.f2874g.F() == 1;
        int i8 = z6 ? 16908360 : 16908361;
        if (z6) {
            i7 = 16908361;
        }
        if (viewPager2.f2871d < itemCount - 1) {
            d1.m(viewPager2, new q0.g(i8), null, kVar2);
        }
        if (viewPager2.f2871d > 0) {
            d1.m(viewPager2, new q0.g(i7), null, kVar);
        }
    }
}
