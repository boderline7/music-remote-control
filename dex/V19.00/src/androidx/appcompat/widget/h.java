package androidx.appcompat.widget;

import android.content.Context;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h extends k.a0 {

    /* renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1269m = 0;

    /* renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f1270n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(m mVar, Context context, k.i0 i0Var, View view) {
        super(f.a.actionOverflowMenuStyle, 0, context, view, i0Var, false);
        this.f1270n = mVar;
        if (!i0Var.A.f()) {
            View view2 = mVar.f1332j;
            this.f7051f = view2 == null ? (View) mVar.f1330h : view2;
        }
        e.a aVar = mVar.f1346x;
        this.f7054i = aVar;
        k.x xVar = this.f7055j;
        if (xVar != null) {
            xVar.k(aVar);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(m mVar, Context context, k.o oVar, k kVar) {
        super(f.a.actionOverflowMenuStyle, 0, context, kVar, oVar, true);
        this.f1270n = mVar;
        this.f7052g = 8388613;
        e.a aVar = mVar.f1346x;
        this.f7054i = aVar;
        k.x xVar = this.f7055j;
        if (xVar != null) {
            xVar.k(aVar);
        }
    }

    @Override // k.a0
    public final void c() {
        int i7 = this.f1269m;
        m mVar = this.f1270n;
        switch (i7) {
            case 0:
                mVar.f1343u = null;
                mVar.f1347y = 0;
                super.c();
                break;
            default:
                k.o oVar = mVar.f1325c;
                if (oVar != null) {
                    oVar.c(true);
                }
                mVar.f1342t = null;
                super.c();
                break;
        }
    }
}
