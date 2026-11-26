package k;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    public int f7123a = -1;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ k f7124b;

    public j(k kVar) {
        this.f7124b = kVar;
        a();
    }

    public final void a() {
        o oVar = this.f7124b.f7127c;
        q qVar = oVar.f7160v;
        if (qVar != null) {
            oVar.i();
            ArrayList arrayList = oVar.f7148j;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (((q) arrayList.get(i7)) == qVar) {
                    this.f7123a = i7;
                    return;
                }
            }
        }
        this.f7123a = -1;
    }

    @Override // android.widget.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final q getItem(int i7) {
        k kVar = this.f7124b;
        o oVar = kVar.f7127c;
        oVar.i();
        ArrayList arrayList = oVar.f7148j;
        kVar.getClass();
        int i8 = this.f7123a;
        if (i8 >= 0 && i7 >= i8) {
            i7++;
        }
        return (q) arrayList.get(i7);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        k kVar = this.f7124b;
        o oVar = kVar.f7127c;
        oVar.i();
        int size = oVar.f7148j.size();
        kVar.getClass();
        return this.f7123a < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        return i7;
    }

    @Override // android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        if (view == null) {
            k kVar = this.f7124b;
            view = kVar.f7126b.inflate(kVar.f7129e, viewGroup, false);
        }
        ((d0) view).c(getItem(i7));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
