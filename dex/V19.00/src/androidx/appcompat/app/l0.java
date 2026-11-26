package androidx.appcompat.app;

import android.R;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class l0 extends androidx.activity.q implements s {

    /* renamed from: d, reason: collision with root package name */
    public j0 f797d;

    /* renamed from: e, reason: collision with root package name */
    public final k0 f798e;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.appcompat.app.k0] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public l0(android.content.Context r5, int r6) {
        /*
            r4 = this;
            r0 = 1
            if (r6 != 0) goto L14
            android.util.TypedValue r1 = new android.util.TypedValue
            r1.<init>()
            android.content.res.Resources$Theme r2 = r5.getTheme()
            int r3 = f.a.dialogTheme
            r2.resolveAttribute(r3, r1, r0)
            int r1 = r1.resourceId
            goto L15
        L14:
            r1 = r6
        L15:
            r4.<init>(r5, r1)
            androidx.appcompat.app.k0 r1 = new androidx.appcompat.app.k0
            r2 = r4
            androidx.appcompat.app.o r2 = (androidx.appcompat.app.o) r2
            r1.<init>()
            r4.f798e = r1
            androidx.appcompat.app.w r1 = r4.c()
            if (r6 != 0) goto L38
            android.util.TypedValue r6 = new android.util.TypedValue
            r6.<init>()
            android.content.res.Resources$Theme r5 = r5.getTheme()
            int r2 = f.a.dialogTheme
            r5.resolveAttribute(r2, r6, r0)
            int r6 = r6.resourceId
        L38:
            r5 = r1
            androidx.appcompat.app.j0 r5 = (androidx.appcompat.app.j0) r5
            r5.T = r6
            r1.c()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.l0.<init>(android.content.Context, int):void");
    }

    @Override // android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        j0 j0Var = (j0) c();
        j0Var.x();
        ((ViewGroup) j0Var.A.findViewById(R.id.content)).addView(view, layoutParams);
        j0Var.f780m.a(j0Var.f779l.getCallback());
    }

    public final w c() {
        if (this.f797d == null) {
            q0 q0Var = w.f866a;
            this.f797d = new j0(getContext(), getWindow(), this, this);
        }
        return this.f797d;
    }

    public final boolean d(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
        c().d();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return n1.z.U(this.f798e, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public final View findViewById(int i7) {
        j0 j0Var = (j0) c();
        j0Var.x();
        return j0Var.f779l.findViewById(i7);
    }

    @Override // android.app.Dialog
    public final void invalidateOptionsMenu() {
        c().a();
    }

    @Override // androidx.activity.q, android.app.Dialog
    public void onCreate(Bundle bundle) {
        j0 j0Var = (j0) c();
        LayoutInflater from = LayoutInflater.from(j0Var.f778k);
        if (from.getFactory() == null) {
            from.setFactory2(j0Var);
        } else {
            boolean z6 = from.getFactory2() instanceof j0;
        }
        super.onCreate(bundle);
        c().c();
    }

    @Override // androidx.activity.q, android.app.Dialog
    public final void onStop() {
        super.onStop();
        j0 j0Var = (j0) c();
        j0Var.C();
        b bVar = j0Var.f782o;
        if (bVar != null) {
            bVar.s(false);
        }
    }

    @Override // androidx.appcompat.app.s
    public final void onSupportActionModeFinished(j.c cVar) {
    }

    @Override // androidx.appcompat.app.s
    public final void onSupportActionModeStarted(j.c cVar) {
    }

    @Override // androidx.appcompat.app.s
    public final j.c onWindowStartingSupportActionMode(j.b bVar) {
        return null;
    }

    @Override // android.app.Dialog
    public final void setContentView(int i7) {
        c().g(i7);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        c().h(view);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c().i(view, layoutParams);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i7) {
        super.setTitle(i7);
        c().k(getContext().getString(i7));
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        c().k(charSequence);
    }
}
