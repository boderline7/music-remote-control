package k;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class l extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    public final o f7132a;

    /* renamed from: b, reason: collision with root package name */
    public int f7133b = -1;

    /* renamed from: c, reason: collision with root package name */
    public boolean f7134c;

    /* renamed from: d, reason: collision with root package name */
    public final boolean f7135d;

    /* renamed from: e, reason: collision with root package name */
    public final LayoutInflater f7136e;

    /* renamed from: f, reason: collision with root package name */
    public final int f7137f;

    public l(o oVar, LayoutInflater layoutInflater, boolean z6, int i7) {
        this.f7135d = z6;
        this.f7136e = layoutInflater;
        this.f7132a = oVar;
        this.f7137f = i7;
        a();
    }

    public final void a() {
        o oVar = this.f7132a;
        q qVar = oVar.f7160v;
        if (qVar != null) {
            oVar.i();
            ArrayList arrayList = oVar.f7148j;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                if (((q) arrayList.get(i7)) == qVar) {
                    this.f7133b = i7;
                    return;
                }
            }
        }
        this.f7133b = -1;
    }

    @Override // android.widget.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final q getItem(int i7) {
        ArrayList l7;
        boolean z6 = this.f7135d;
        o oVar = this.f7132a;
        if (z6) {
            oVar.i();
            l7 = oVar.f7148j;
        } else {
            l7 = oVar.l();
        }
        int i8 = this.f7133b;
        if (i8 >= 0 && i7 >= i8) {
            i7++;
        }
        return (q) l7.get(i7);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList l7;
        boolean z6 = this.f7135d;
        o oVar = this.f7132a;
        if (z6) {
            oVar.i();
            l7 = oVar.f7148j;
        } else {
            l7 = oVar.l();
        }
        return this.f7133b < 0 ? l7.size() : l7.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i7) {
        return i7;
    }

    @Override // android.widget.Adapter
    public final View getView(int i7, View view, ViewGroup viewGroup) {
        boolean z6 = false;
        if (view == null) {
            view = this.f7136e.inflate(this.f7137f, viewGroup, false);
        }
        int i8 = getItem(i7).f7167b;
        int i9 = i7 - 1;
        int i10 = i9 >= 0 ? getItem(i9).f7167b : i8;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.f7132a.m() && i8 != i10) {
            z6 = true;
        }
        listMenuItemView.setGroupDividerEnabled(z6);
        d0 d0Var = (d0) view;
        if (this.f7134c) {
            listMenuItemView.setForceShowIcon(true);
        }
        d0Var.c(getItem(i7));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
