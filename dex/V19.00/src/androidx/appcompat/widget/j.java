package androidx.appcompat.widget;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class j extends v1 {

    /* renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f1290j;

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f1291k;

    /* renamed from: l, reason: collision with root package name */
    public final /* synthetic */ View f1292l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(View view, View view2, Object obj, int i7) {
        super(view2);
        this.f1290j = i7;
        this.f1292l = view;
        this.f1291k = obj;
    }

    @Override // androidx.appcompat.widget.v1
    public final k.g0 b() {
        switch (this.f1290j) {
            case 0:
                h hVar = ((k) this.f1292l).f1293d.f1342t;
                if (hVar == null) {
                    return null;
                }
                return hVar.a();
            default:
                return (n0) this.f1291k;
        }
    }

    @Override // androidx.appcompat.widget.v1
    public final boolean c() {
        int i7 = this.f1290j;
        View view = this.f1292l;
        switch (i7) {
            case 0:
                ((k) view).f1293d.n();
                break;
            default:
                AppCompatSpinner appCompatSpinner = (AppCompatSpinner) view;
                if (!appCompatSpinner.getInternalPopup().b()) {
                    appCompatSpinner.f1047f.f(i0.b(appCompatSpinner), i0.a(appCompatSpinner));
                    break;
                }
                break;
        }
        return true;
    }

    @Override // androidx.appcompat.widget.v1
    public final boolean d() {
        switch (this.f1290j) {
            case 0:
                m mVar = ((k) this.f1292l).f1293d;
                if (mVar.f1344v == null) {
                    mVar.g();
                    break;
                }
                break;
            default:
                k.g0 b7 = b();
                if (b7 != null && b7.b()) {
                    b7.dismiss();
                    break;
                }
                break;
        }
        return true;
    }
}
