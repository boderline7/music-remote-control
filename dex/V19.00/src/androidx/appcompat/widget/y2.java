package androidx.appcompat.widget;

import android.view.View;
import android.widget.AdapterView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y2 implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1490a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f1491b;

    public /* synthetic */ y2(int i7, View view) {
        this.f1490a = i7;
        this.f1491b = view;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j7) {
        Object item;
        int i8 = this.f1490a;
        View view2 = this.f1491b;
        switch (i8) {
            case 0:
                ((SearchView) view2).p(i7);
                break;
            default:
                MaterialAutoCompleteTextView materialAutoCompleteTextView = (MaterialAutoCompleteTextView) view2;
                if (i7 < 0) {
                    e2 e2Var = materialAutoCompleteTextView.f4259e;
                    item = !e2Var.f1237z.isShowing() ? null : e2Var.f1214c.getSelectedItem();
                } else {
                    item = materialAutoCompleteTextView.getAdapter().getItem(i7);
                }
                MaterialAutoCompleteTextView materialAutoCompleteTextView2 = (MaterialAutoCompleteTextView) view2;
                MaterialAutoCompleteTextView.a(materialAutoCompleteTextView2, item);
                AdapterView.OnItemClickListener onItemClickListener = materialAutoCompleteTextView2.getOnItemClickListener();
                e2 e2Var2 = materialAutoCompleteTextView2.f4259e;
                if (onItemClickListener != null) {
                    if (view == null || i7 < 0) {
                        view = e2Var2.f1237z.isShowing() ? e2Var2.f1214c.getSelectedView() : null;
                        i7 = !e2Var2.f1237z.isShowing() ? -1 : e2Var2.f1214c.getSelectedItemPosition();
                        j7 = !e2Var2.f1237z.isShowing() ? Long.MIN_VALUE : e2Var2.f1214c.getSelectedItemId();
                    }
                    onItemClickListener.onItemClick(e2Var2.f1214c, view, i7, j7);
                }
                e2Var2.dismiss();
                break;
        }
    }
}
