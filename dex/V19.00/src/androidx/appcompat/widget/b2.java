package androidx.appcompat.widget;

import android.database.DataSetObserver;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b2 extends DataSetObserver {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1193a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1194b;

    public /* synthetic */ b2(int i7, Object obj) {
        this.f1193a = i7;
        this.f1194b = obj;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        int i7 = this.f1193a;
        Object obj = this.f1194b;
        switch (i7) {
            case 0:
                e2 e2Var = (e2) obj;
                if (e2Var.f1237z.isShowing()) {
                    e2Var.g();
                    break;
                }
                break;
            default:
                t0.b bVar = (t0.b) obj;
                bVar.f9014a = true;
                bVar.notifyDataSetChanged();
                break;
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        int i7 = this.f1193a;
        Object obj = this.f1194b;
        switch (i7) {
            case 0:
                ((e2) obj).dismiss();
                break;
            default:
                t0.b bVar = (t0.b) obj;
                bVar.f9014a = false;
                bVar.notifyDataSetInvalidated();
                break;
        }
    }
}
