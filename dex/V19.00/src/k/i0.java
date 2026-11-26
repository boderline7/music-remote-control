package k;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class i0 extends o implements SubMenu {
    public final q A;

    /* renamed from: z, reason: collision with root package name */
    public final o f7122z;

    public i0(Context context, o oVar, q qVar) {
        super(context);
        this.f7122z = oVar;
        this.A = qVar;
    }

    @Override // k.o
    public final boolean d(q qVar) {
        return this.f7122z.d(qVar);
    }

    @Override // k.o
    public final boolean e(o oVar, MenuItem menuItem) {
        return super.e(oVar, menuItem) || this.f7122z.e(oVar, menuItem);
    }

    @Override // k.o
    public final boolean f(q qVar) {
        return this.f7122z.f(qVar);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.A;
    }

    @Override // k.o
    public final String j() {
        q qVar = this.A;
        int i7 = qVar != null ? qVar.f7166a : 0;
        if (i7 == 0) {
            return null;
        }
        return a.a.d("android:menu:actionviewstates:", i7);
    }

    @Override // k.o
    public final o k() {
        return this.f7122z.k();
    }

    @Override // k.o
    public final boolean m() {
        return this.f7122z.m();
    }

    @Override // k.o
    public final boolean n() {
        return this.f7122z.n();
    }

    @Override // k.o
    public final boolean o() {
        return this.f7122z.o();
    }

    @Override // k.o, android.view.Menu
    public final void setGroupDividerEnabled(boolean z6) {
        this.f7122z.setGroupDividerEnabled(z6);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i7) {
        w(0, null, i7, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        w(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i7) {
        w(i7, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        w(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        w(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i7) {
        this.A.setIcon(i7);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.A.setIcon(drawable);
        return this;
    }

    @Override // k.o, android.view.Menu
    public final void setQwertyMode(boolean z6) {
        this.f7122z.setQwertyMode(z6);
    }
}
