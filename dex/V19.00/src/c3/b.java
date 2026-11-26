package c3;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.n;
import androidx.appcompat.app.o;
import com.navi.tool.ui.extra.n5;
import p0.d1;
import p0.r0;
import p3.j;
import q2.c;
import q2.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b extends n {

    /* renamed from: f, reason: collision with root package name */
    public static final int f3049f = c.alertDialogStyle;

    /* renamed from: g, reason: collision with root package name */
    public static final int f3050g = l.MaterialAlertDialog_MaterialComponents;

    /* renamed from: h, reason: collision with root package name */
    public static final int f3051h = c.materialAlertDialogTheme;

    /* renamed from: d, reason: collision with root package name */
    public final j f3052d;

    /* renamed from: e, reason: collision with root package name */
    public final Rect f3053e;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public b(android.content.Context r15) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: c3.b.<init>(android.content.Context):void");
    }

    public final void A(String str, n5 n5Var) {
        super.p(str, n5Var);
    }

    public final void B(String str, DialogInterface.OnClickListener onClickListener) {
        super.r(str, onClickListener);
    }

    public final void C(String str) {
        super.t(str);
    }

    public final void D(View view) {
        super.u(view);
    }

    @Override // androidx.appcompat.app.n
    public final o e() {
        o e7 = super.e();
        Window window = e7.getWindow();
        View decorView = window.getDecorView();
        j jVar = this.f3052d;
        if (jVar instanceof j) {
            int i7 = d1.OVER_SCROLL_ALWAYS;
            jVar.n(r0.i(decorView));
        }
        Rect rect = this.f3053e;
        window.setBackgroundDrawable(new InsetDrawable((Drawable) jVar, rect.left, rect.top, rect.right, rect.bottom));
        decorView.setOnTouchListener(new a(e7, rect));
        return e7;
    }

    @Override // androidx.appcompat.app.n
    public final n k() {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n l(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n m(CharSequence charSequence) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n n(int i7) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n o(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n p(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n q(int i7, DialogInterface.OnClickListener onClickListener) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n r(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n s(int i7) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n t(CharSequence charSequence) {
        throw null;
    }

    @Override // androidx.appcompat.app.n
    public final n u(View view) {
        throw null;
    }

    public final void w() {
        super.k();
    }

    public final void x(Drawable drawable) {
        ((androidx.appcompat.app.j) this.f831c).f747c = drawable;
    }

    public final void y(CharSequence charSequence) {
        super.m(charSequence);
    }

    public final void z(String str, n5 n5Var) {
        super.o(str, n5Var);
    }
}
