package k;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import androidx.appcompat.view.menu.ExpandedMenuView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class k implements c0, AdapterView.OnItemClickListener {
    public static final String VIEWS_TAG = "android:menu:list";

    /* renamed from: a, reason: collision with root package name */
    public Context f7125a;

    /* renamed from: b, reason: collision with root package name */
    public LayoutInflater f7126b;

    /* renamed from: c, reason: collision with root package name */
    public o f7127c;

    /* renamed from: d, reason: collision with root package name */
    public ExpandedMenuView f7128d;

    /* renamed from: e, reason: collision with root package name */
    public final int f7129e;

    /* renamed from: f, reason: collision with root package name */
    public b0 f7130f;

    /* renamed from: g, reason: collision with root package name */
    public j f7131g;

    public k(Context context, int i7) {
        this.f7129e = i7;
        this.f7125a = context;
        this.f7126b = LayoutInflater.from(context);
    }

    @Override // k.c0
    public final void a(o oVar, boolean z6) {
        b0 b0Var = this.f7130f;
        if (b0Var != null) {
            b0Var.a(oVar, z6);
        }
    }

    @Override // k.c0
    public final boolean c(q qVar) {
        return false;
    }

    @Override // k.c0
    public final boolean d(i0 i0Var) {
        if (!i0Var.hasVisibleItems()) {
            return false;
        }
        p pVar = new p();
        pVar.f7163a = i0Var;
        Context context = i0Var.f7139a;
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(context);
        Object obj = nVar.f831c;
        k kVar = new k(((androidx.appcompat.app.j) obj).f745a, f.g.abc_list_menu_item_layout);
        pVar.f7165c = kVar;
        kVar.f7130f = pVar;
        i0Var.b(kVar, context);
        k kVar2 = pVar.f7165c;
        if (kVar2.f7131g == null) {
            kVar2.f7131g = new j(kVar2);
        }
        androidx.appcompat.app.j jVar = (androidx.appcompat.app.j) obj;
        jVar.f761q = kVar2.f7131g;
        jVar.f762r = pVar;
        View view = i0Var.f7153o;
        if (view != null) {
            ((androidx.appcompat.app.j) obj).f749e = view;
        } else {
            ((androidx.appcompat.app.j) obj).f747c = i0Var.f7152n;
            nVar.t(i0Var.f7151m);
        }
        ((androidx.appcompat.app.j) obj).f759o = pVar;
        androidx.appcompat.app.o e7 = nVar.e();
        pVar.f7164b = e7;
        e7.setOnDismissListener(pVar);
        WindowManager.LayoutParams attributes = pVar.f7164b.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        pVar.f7164b.show();
        b0 b0Var = this.f7130f;
        if (b0Var == null) {
            return true;
        }
        b0Var.c(i0Var);
        return true;
    }

    @Override // k.c0
    public final boolean e(q qVar) {
        return false;
    }

    @Override // k.c0
    public final void f(Context context, o oVar) {
        if (this.f7125a != null) {
            this.f7125a = context;
            if (this.f7126b == null) {
                this.f7126b = LayoutInflater.from(context);
            }
        }
        this.f7127c = oVar;
        j jVar = this.f7131g;
        if (jVar != null) {
            jVar.notifyDataSetChanged();
        }
    }

    @Override // k.c0
    public final int getId() {
        return 0;
    }

    @Override // k.c0
    public final boolean h() {
        return false;
    }

    @Override // k.c0
    public final Parcelable i() {
        if (this.f7128d == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.f7128d;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray(VIEWS_TAG, sparseArray);
        return bundle;
    }

    @Override // k.c0
    public final void j(Parcelable parcelable) {
        SparseArray<Parcelable> sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray(VIEWS_TAG);
        if (sparseParcelableArray != null) {
            this.f7128d.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // k.c0
    public final void k(b0 b0Var) {
        this.f7130f = b0Var;
    }

    @Override // k.c0
    public final void m(boolean z6) {
        j jVar = this.f7131g;
        if (jVar != null) {
            jVar.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j7) {
        this.f7127c.q(this.f7131g.getItem(i7), this, 0);
    }
}
