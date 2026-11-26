package k;

import android.view.MenuItem;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class u implements MenuItem.OnActionExpandListener {

    /* renamed from: a, reason: collision with root package name */
    public final MenuItem.OnActionExpandListener f7196a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ w f7197b;

    public u(w wVar, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f7197b = wVar;
        this.f7196a = onActionExpandListener;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.f7196a.onMenuItemActionCollapse(this.f7197b.n(menuItem));
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.f7196a.onMenuItemActionExpand(this.f7197b.n(menuItem));
    }
}
