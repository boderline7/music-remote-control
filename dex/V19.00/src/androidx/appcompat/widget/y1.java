package androidx.appcompat.widget;

import android.view.View;
import android.widget.AdapterView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y1 implements AdapterView.OnItemSelectedListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1488a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1489b;

    public /* synthetic */ y1(int i7, Object obj) {
        this.f1488a = i7;
        this.f1489b = obj;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i7, long j7) {
        s1 s1Var;
        int i8 = this.f1488a;
        Object obj = this.f1489b;
        switch (i8) {
            case 0:
                if (i7 != -1 && (s1Var = ((e2) obj).f1214c) != null) {
                    s1Var.setListSelectionHidden(false);
                    break;
                }
                break;
            default:
                ((SearchView) obj).q(i7);
                break;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}
