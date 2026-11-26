package androidx.viewpager2.adapter;

import android.view.ViewParent;
import androidx.fragment.app.x0;
import androidx.fragment.app.z;
import androidx.lifecycle.r;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.b2;
import androidx.viewpager2.widget.ViewPager2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    public d f2861a;

    /* renamed from: b, reason: collision with root package name */
    public b2 f2862b;

    /* renamed from: c, reason: collision with root package name */
    public e f2863c;

    /* renamed from: d, reason: collision with root package name */
    public ViewPager2 f2864d;

    /* renamed from: e, reason: collision with root package name */
    public long f2865e = -1;

    /* renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g f2866f;

    public f(g gVar) {
        this.f2866f = gVar;
    }

    public static ViewPager2 a(RecyclerView recyclerView) {
        ViewParent parent = recyclerView.getParent();
        if (parent instanceof ViewPager2) {
            return (ViewPager2) parent;
        }
        throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
    }

    public final void b(boolean z6) {
        int currentItem;
        g gVar = this.f2866f;
        if (gVar.shouldDelayFragmentTransactions() || this.f2864d.getScrollState() != 0 || gVar.mFragments.h() == 0 || gVar.getItemCount() == 0 || (currentItem = this.f2864d.getCurrentItem()) >= gVar.getItemCount()) {
            return;
        }
        long itemId = gVar.getItemId(currentItem);
        if (itemId != this.f2865e || z6) {
            z zVar = null;
            z zVar2 = (z) gVar.mFragments.d(itemId, null);
            if (zVar2 == null || !zVar2.isAdded()) {
                return;
            }
            this.f2865e = itemId;
            x0 x0Var = gVar.mFragmentManager;
            x0Var.getClass();
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(x0Var);
            for (int i7 = 0; i7 < gVar.mFragments.h(); i7++) {
                long e7 = gVar.mFragments.e(i7);
                z zVar3 = (z) gVar.mFragments.i(i7);
                if (zVar3.isAdded()) {
                    if (e7 != this.f2865e) {
                        aVar.j(zVar3, r.STARTED);
                    } else {
                        zVar = zVar3;
                    }
                    zVar3.setMenuVisibility(e7 == this.f2865e);
                }
            }
            if (zVar != null) {
                aVar.j(zVar, r.RESUMED);
            }
            if (aVar.f1974a.isEmpty()) {
                return;
            }
            if (aVar.f1980g) {
                throw new IllegalStateException("This transaction is already being added to the back stack");
            }
            aVar.f1981h = false;
            aVar.f1910q.t(aVar, false);
        }
    }
}
