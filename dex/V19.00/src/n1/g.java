package n1;

import android.view.View;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends s {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7608a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7609b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f7610c;

    public /* synthetic */ g(int i7, Object obj, Object obj2) {
        this.f7608a = i7;
        this.f7610c = obj;
        this.f7609b = obj2;
    }

    @Override // n1.q
    public final void d(r rVar) {
        int i7 = this.f7608a;
        Object obj = this.f7609b;
        switch (i7) {
            case 0:
                g0 g0Var = b0.f7590a;
                g0Var.O((View) obj, 1.0f);
                g0Var.getClass();
                rVar.v(this);
                break;
            case 1:
                ((ArrayList) ((q.b) obj).getOrDefault(((t) this.f7610c).f7674b, null)).remove(rVar);
                rVar.v(this);
                break;
            default:
                ((r) obj).y();
                rVar.v(this);
                break;
        }
    }
}
