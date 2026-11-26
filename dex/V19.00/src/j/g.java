package j;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class g extends c implements k.m {

    /* renamed from: c, reason: collision with root package name */
    public Context f6883c;

    /* renamed from: d, reason: collision with root package name */
    public ActionBarContextView f6884d;

    /* renamed from: e, reason: collision with root package name */
    public b f6885e;

    /* renamed from: f, reason: collision with root package name */
    public WeakReference f6886f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f6887g;

    /* renamed from: h, reason: collision with root package name */
    public k.o f6888h;

    @Override // j.c
    public final void a() {
        if (this.f6887g) {
            return;
        }
        this.f6887g = true;
        this.f6885e.d(this);
    }

    @Override // j.c
    public final View b() {
        WeakReference weakReference = this.f6886f;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // j.c
    public final k.o c() {
        return this.f6888h;
    }

    @Override // j.c
    public final MenuInflater d() {
        return new l(this.f6884d.getContext());
    }

    @Override // j.c
    public final CharSequence e() {
        return this.f6884d.getSubtitle();
    }

    @Override // j.c
    public final CharSequence f() {
        return this.f6884d.getTitle();
    }

    @Override // j.c
    public final void g() {
        this.f6885e.i(this, this.f6888h);
    }

    @Override // j.c
    public final boolean h() {
        return this.f6884d.f952s;
    }

    @Override // j.c
    public final void i(View view) {
        this.f6884d.setCustomView(view);
        this.f6886f = view != null ? new WeakReference(view) : null;
    }

    @Override // j.c
    public final void j(int i7) {
        k(this.f6883c.getString(i7));
    }

    @Override // j.c
    public final void k(CharSequence charSequence) {
        this.f6884d.setSubtitle(charSequence);
    }

    @Override // k.m
    public final boolean l(k.o oVar, MenuItem menuItem) {
        return this.f6885e.a(this, menuItem);
    }

    @Override // j.c
    public final void m(int i7) {
        n(this.f6883c.getString(i7));
    }

    @Override // j.c
    public final void n(CharSequence charSequence) {
        this.f6884d.setTitle(charSequence);
    }

    @Override // j.c
    public final void o(boolean z6) {
        this.f6876b = z6;
        this.f6884d.setTitleOptional(z6);
    }

    @Override // k.m
    public final void s(k.o oVar) {
        g();
        androidx.appcompat.widget.m mVar = this.f6884d.f937d;
        if (mVar != null) {
            mVar.n();
        }
    }
}
