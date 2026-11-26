package k;

import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class s extends r implements ActionProvider.VisibilityListener {

    /* renamed from: d, reason: collision with root package name */
    public e.a f7194d;

    @Override // p0.d
    public final boolean a() {
        return this.f7192b.isVisible();
    }

    @Override // p0.d
    public final View b(MenuItem menuItem) {
        return this.f7192b.onCreateActionView(menuItem);
    }

    @Override // p0.d
    public final boolean c() {
        return this.f7192b.overridesItemVisibility();
    }

    @Override // p0.d
    public final void d(e.a aVar) {
        this.f7194d = aVar;
        this.f7192b.setVisibilityListener(this);
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z6) {
        e.a aVar = this.f7194d;
        if (aVar != null) {
            o oVar = ((q) aVar.f6290b).f7179n;
            oVar.f7146h = true;
            oVar.p(true);
        }
    }
}
