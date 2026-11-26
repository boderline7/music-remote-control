package androidx.appcompat.app;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.AppCompatSpinner;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class i implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f726a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f727b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f728c;

    public /* synthetic */ i(int i7, Object obj, Object obj2) {
        this.f726a = i7;
        this.f728c = obj;
        this.f727b = obj2;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j7) {
        int i8 = this.f726a;
        Object obj = this.f728c;
        switch (i8) {
            case 0:
                j jVar = (j) obj;
                DialogInterface.OnClickListener onClickListener = jVar.f762r;
                m mVar = (m) this.f727b;
                onClickListener.onClick(mVar.f800b, i7);
                if (!jVar.f764t) {
                    mVar.f800b.dismiss();
                    break;
                }
                break;
            default:
                androidx.appcompat.widget.n0 n0Var = (androidx.appcompat.widget.n0) obj;
                n0Var.G.setSelection(i7);
                AppCompatSpinner appCompatSpinner = n0Var.G;
                if (appCompatSpinner.getOnItemClickListener() != null) {
                    appCompatSpinner.performItemClick(view, i7, n0Var.D.getItemId(i7));
                }
                n0Var.dismiss();
                break;
        }
    }
}
