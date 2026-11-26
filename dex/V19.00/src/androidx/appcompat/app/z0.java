package androidx.appcompat.app;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class z0 extends j.c implements k.m {

    /* renamed from: c, reason: collision with root package name */
    public final Context f889c;

    /* renamed from: d, reason: collision with root package name */
    public final k.o f890d;

    /* renamed from: e, reason: collision with root package name */
    public j.b f891e;

    /* renamed from: f, reason: collision with root package name */
    public WeakReference f892f;

    /* renamed from: g, reason: collision with root package name */
    public final /* synthetic */ a1 f893g;

    public z0(a1 a1Var, Context context, a0 a0Var) {
        this.f893g = a1Var;
        this.f889c = context;
        this.f891e = a0Var;
        k.o oVar = new k.o(context);
        oVar.f7150l = 1;
        this.f890d = oVar;
        oVar.f7143e = this;
    }

    @Override // j.c
    public final void a() {
        a1 a1Var = this.f893g;
        if (a1Var.f682i != this) {
            return;
        }
        boolean z6 = a1Var.f689p;
        boolean z7 = a1Var.f690q;
        if (z6 || z7) {
            a1Var.f683j = this;
            a1Var.f684k = this.f891e;
        } else {
            this.f891e.d(this);
        }
        this.f891e = null;
        a1Var.w(false);
        ActionBarContextView actionBarContextView = a1Var.f679f;
        if (actionBarContextView.f944k == null) {
            actionBarContextView.e();
        }
        a1Var.f676c.setHideOnContentScrollEnabled(a1Var.f695v);
        a1Var.f682i = null;
    }

    @Override // j.c
    public final View b() {
        WeakReference weakReference = this.f892f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // j.c
    public final k.o c() {
        return this.f890d;
    }

    @Override // j.c
    public final MenuInflater d() {
        return new j.l(this.f889c);
    }

    @Override // j.c
    public final CharSequence e() {
        return this.f893g.f679f.getSubtitle();
    }

    @Override // j.c
    public final CharSequence f() {
        return this.f893g.f679f.getTitle();
    }

    @Override // j.c
    public final void g() {
        if (this.f893g.f682i != this) {
            return;
        }
        k.o oVar = this.f890d;
        oVar.y();
        try {
            this.f891e.i(this, oVar);
        } finally {
            oVar.x();
        }
    }

    @Override // j.c
    public final boolean h() {
        return this.f893g.f679f.f952s;
    }

    @Override // j.c
    public final void i(View view) {
        this.f893g.f679f.setCustomView(view);
        this.f892f = new WeakReference(view);
    }

    @Override // j.c
    public final void j(int i7) {
        k(this.f893g.f674a.getResources().getString(i7));
    }

    @Override // j.c
    public final void k(CharSequence charSequence) {
        this.f893g.f679f.setSubtitle(charSequence);
    }

    @Override // k.m
    public final boolean l(k.o oVar, MenuItem menuItem) {
        j.b bVar = this.f891e;
        if (bVar != null) {
            return bVar.a(this, menuItem);
        }
        return false;
    }

    @Override // j.c
    public final void m(int i7) {
        n(this.f893g.f674a.getResources().getString(i7));
    }

    @Override // j.c
    public final void n(CharSequence charSequence) {
        this.f893g.f679f.setTitle(charSequence);
    }

    @Override // j.c
    public final void o(boolean z6) {
        this.f6876b = z6;
        this.f893g.f679f.setTitleOptional(z6);
    }

    @Override // k.m
    public final void s(k.o oVar) {
        if (this.f891e == null) {
            return;
        }
        g();
        androidx.appcompat.widget.m mVar = this.f893g.f679f.f937d;
        if (mVar != null) {
            mVar.n();
        }
    }
}
