package androidx.appcompat.widget;

import android.view.MenuItem;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q3 implements q, k.m {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1384a;

    public /* synthetic */ q3(Toolbar toolbar) {
        this.f1384a = toolbar;
    }

    @Override // k.m
    public final boolean l(k.o oVar, MenuItem menuItem) {
        k.m mVar = this.f1384a.O;
        return mVar != null && mVar.l(oVar, menuItem);
    }

    @Override // k.m
    public final void s(k.o oVar) {
        Toolbar toolbar = this.f1384a;
        m mVar = toolbar.f1152a.f985t;
        if (mVar == null || !mVar.l()) {
            Iterator it = toolbar.G.f8184b.iterator();
            if (it.hasNext()) {
                a.a.u(it.next());
                throw null;
            }
        }
        k.m mVar2 = toolbar.O;
        if (mVar2 != null) {
            mVar2.s(oVar);
        }
    }
}
