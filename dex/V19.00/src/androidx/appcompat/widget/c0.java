package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c0 {

    /* renamed from: a, reason: collision with root package name */
    public int f1198a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f1199b;

    /* renamed from: c, reason: collision with root package name */
    public w1.d f1200c;

    /* renamed from: d, reason: collision with root package name */
    public Object f1201d;

    /* renamed from: e, reason: collision with root package name */
    public Object f1202e;

    public c0(ImageView imageView) {
        this.f1198a = 0;
        this.f1199b = imageView;
    }

    public c0(w1.g gVar) {
        this.f1199b = gVar;
        this.f1200c = null;
        this.f1201d = null;
        this.f1198a = 0;
        this.f1202e = null;
    }

    public final void a() {
        ImageView imageView = (ImageView) this.f1199b;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            m1.a(drawable);
        }
        if (drawable != null) {
            if (((n3) this.f1200c) != null) {
                if (((n3) this.f1202e) == null) {
                    this.f1202e = new n3(0);
                }
                n3 n3Var = (n3) this.f1202e;
                n3Var.f1363c = null;
                n3Var.f1362b = false;
                n3Var.f1364d = null;
                n3Var.f1361a = false;
                ColorStateList a7 = androidx.core.widget.h.a(imageView);
                if (a7 != null) {
                    n3Var.f1362b = true;
                    n3Var.f1363c = a7;
                }
                PorterDuff.Mode b7 = androidx.core.widget.h.b(imageView);
                if (b7 != null) {
                    n3Var.f1361a = true;
                    n3Var.f1364d = b7;
                }
                if (n3Var.f1362b || n3Var.f1361a) {
                    z.e(drawable, n3Var, imageView.getDrawableState());
                    return;
                }
            }
            n3 n3Var2 = (n3) this.f1201d;
            if (n3Var2 != null) {
                z.e(drawable, n3Var2, imageView.getDrawableState());
                return;
            }
            n3 n3Var3 = (n3) this.f1200c;
            if (n3Var3 != null) {
                z.e(drawable, n3Var3, imageView.getDrawableState());
            }
        }
    }

    public final void b() {
        if (this.f1200c == null) {
            w1.g gVar = (w1.g) this.f1199b;
            gVar.a();
            this.f1200c = gVar.f9424d;
        }
    }

    public final void c(AttributeSet attributeSet, int i7) {
        int N;
        Object obj = this.f1199b;
        ImageView imageView = (ImageView) obj;
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(imageView.getContext(), attributeSet, f.j.AppCompatImageView, i7);
        p0.d1.n(imageView, imageView.getContext(), f.j.AppCompatImageView, attributeSet, (TypedArray) S.f715c, i7);
        try {
            Drawable drawable = ((ImageView) obj).getDrawable();
            if (drawable == null && (N = S.N(f.j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = l6.f.A(((ImageView) obj).getContext(), N)) != null) {
                ((ImageView) obj).setImageDrawable(drawable);
            }
            if (drawable != null) {
                m1.a(drawable);
            }
            if (S.R(f.j.AppCompatImageView_tint)) {
                androidx.core.widget.h.c((ImageView) obj, S.F(f.j.AppCompatImageView_tint));
            }
            if (S.R(f.j.AppCompatImageView_tintMode)) {
                androidx.core.widget.h.d((ImageView) obj, m1.c(S.L(f.j.AppCompatImageView_tintMode, -1), null));
            }
            S.V();
        } catch (Throwable th) {
            S.V();
            throw th;
        }
    }

    public final void d(int i7) {
        Object obj = this.f1199b;
        if (i7 != 0) {
            ImageView imageView = (ImageView) obj;
            Drawable A = l6.f.A(imageView.getContext(), i7);
            if (A != null) {
                m1.a(A);
            }
            imageView.setImageDrawable(A);
        } else {
            ((ImageView) obj).setImageDrawable(null);
        }
        a();
    }

    public final void e(ColorStateList colorStateList) {
        if (((n3) this.f1201d) == null) {
            this.f1201d = new n3(0);
        }
        n3 n3Var = (n3) this.f1201d;
        n3Var.f1363c = colorStateList;
        n3Var.f1362b = true;
        a();
    }

    public final void f(PorterDuff.Mode mode) {
        if (((n3) this.f1201d) == null) {
            this.f1201d = new n3(0);
        }
        n3 n3Var = (n3) this.f1201d;
        n3Var.f1364d = mode;
        n3Var.f1361a = true;
        a();
    }
}
