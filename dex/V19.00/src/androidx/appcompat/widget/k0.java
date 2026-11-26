package androidx.appcompat.widget;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k0 implements p0, DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public androidx.appcompat.app.o f1294a;

    /* renamed from: b, reason: collision with root package name */
    public ListAdapter f1295b;

    /* renamed from: c, reason: collision with root package name */
    public CharSequence f1296c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ AppCompatSpinner f1297d;

    public k0(AppCompatSpinner appCompatSpinner) {
        this.f1297d = appCompatSpinner;
    }

    @Override // androidx.appcompat.widget.p0
    public final boolean b() {
        androidx.appcompat.app.o oVar = this.f1294a;
        if (oVar != null) {
            return oVar.isShowing();
        }
        return false;
    }

    @Override // androidx.appcompat.widget.p0
    public final void c(int i7) {
    }

    @Override // androidx.appcompat.widget.p0
    public final void dismiss() {
        androidx.appcompat.app.o oVar = this.f1294a;
        if (oVar != null) {
            oVar.dismiss();
            this.f1294a = null;
        }
    }

    @Override // androidx.appcompat.widget.p0
    public final int e() {
        return 0;
    }

    @Override // androidx.appcompat.widget.p0
    public final void f(int i7, int i8) {
        if (this.f1295b == null) {
            return;
        }
        AppCompatSpinner appCompatSpinner = this.f1297d;
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(appCompatSpinner.getPopupContext());
        CharSequence charSequence = this.f1296c;
        if (charSequence != null) {
            nVar.t(charSequence);
        }
        ListAdapter listAdapter = this.f1295b;
        int selectedItemPosition = appCompatSpinner.getSelectedItemPosition();
        androidx.appcompat.app.j jVar = (androidx.appcompat.app.j) nVar.f831c;
        jVar.f761q = listAdapter;
        jVar.f762r = this;
        jVar.f765u = selectedItemPosition;
        jVar.f764t = true;
        androidx.appcompat.app.o e7 = nVar.e();
        this.f1294a = e7;
        AlertController$RecycleListView alertController$RecycleListView = e7.f840f.f805g;
        i0.d(alertController$RecycleListView, i7);
        i0.c(alertController$RecycleListView, i8);
        this.f1294a.show();
    }

    @Override // androidx.appcompat.widget.p0
    public final int h() {
        return 0;
    }

    @Override // androidx.appcompat.widget.p0
    public final Drawable i() {
        return null;
    }

    @Override // androidx.appcompat.widget.p0
    public final CharSequence j() {
        return this.f1296c;
    }

    @Override // androidx.appcompat.widget.p0
    public final void m(CharSequence charSequence) {
        this.f1296c = charSequence;
    }

    @Override // androidx.appcompat.widget.p0
    public final void n(Drawable drawable) {
    }

    @Override // androidx.appcompat.widget.p0
    public final void o(int i7) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i7) {
        AppCompatSpinner appCompatSpinner = this.f1297d;
        appCompatSpinner.setSelection(i7);
        if (appCompatSpinner.getOnItemClickListener() != null) {
            appCompatSpinner.performItemClick(null, i7, this.f1295b.getItemId(i7));
        }
        dismiss();
    }

    @Override // androidx.appcompat.widget.p0
    public final void p(ListAdapter listAdapter) {
        this.f1295b = listAdapter;
    }

    @Override // androidx.appcompat.widget.p0
    public final void q(int i7) {
    }
}
