package j;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import k.f0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class i extends ActionMode {

    /* renamed from: a, reason: collision with root package name */
    public final Context f6894a;

    /* renamed from: b, reason: collision with root package name */
    public final c f6895b;

    public i(Context context, c cVar) {
        this.f6894a = context;
        this.f6895b = cVar;
    }

    @Override // android.view.ActionMode
    public final void finish() {
        this.f6895b.a();
    }

    @Override // android.view.ActionMode
    public final View getCustomView() {
        return this.f6895b.b();
    }

    @Override // android.view.ActionMode
    public final Menu getMenu() {
        return new f0(this.f6894a, this.f6895b.c());
    }

    @Override // android.view.ActionMode
    public final MenuInflater getMenuInflater() {
        return this.f6895b.d();
    }

    @Override // android.view.ActionMode
    public final CharSequence getSubtitle() {
        return this.f6895b.e();
    }

    @Override // android.view.ActionMode
    public final Object getTag() {
        return this.f6895b.f6875a;
    }

    @Override // android.view.ActionMode
    public final CharSequence getTitle() {
        return this.f6895b.f();
    }

    @Override // android.view.ActionMode
    public final boolean getTitleOptionalHint() {
        return this.f6895b.f6876b;
    }

    @Override // android.view.ActionMode
    public final void invalidate() {
        this.f6895b.g();
    }

    @Override // android.view.ActionMode
    public final boolean isTitleOptional() {
        return this.f6895b.h();
    }

    @Override // android.view.ActionMode
    public final void setCustomView(View view) {
        this.f6895b.i(view);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i7) {
        this.f6895b.j(i7);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.f6895b.k(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTag(Object obj) {
        this.f6895b.f6875a = obj;
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i7) {
        this.f6895b.m(i7);
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.f6895b.n(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitleOptionalHint(boolean z6) {
        this.f6895b.o(z6);
    }
}
