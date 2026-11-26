package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c1 extends d1 {

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2393d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c1(r1 r1Var, int i7) {
        super(r1Var);
        this.f2393d = i7;
    }

    @Override // androidx.recyclerview.widget.d1
    public final int b(View view) {
        int i7 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                s1 s1Var = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.C(view) + ((ViewGroup.MarginLayoutParams) s1Var).rightMargin;
            default:
                s1 s1Var2 = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.z(view) + ((ViewGroup.MarginLayoutParams) s1Var2).bottomMargin;
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final int c(View view) {
        int i7 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                s1 s1Var = (s1) view.getLayoutParams();
                r1Var.getClass();
                Rect rect = ((s1) view.getLayoutParams()).f2653b;
                return view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) s1Var).leftMargin + ((ViewGroup.MarginLayoutParams) s1Var).rightMargin;
            default:
                s1 s1Var2 = (s1) view.getLayoutParams();
                r1Var.getClass();
                Rect rect2 = ((s1) view.getLayoutParams()).f2653b;
                return view.getMeasuredHeight() + rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) s1Var2).topMargin + ((ViewGroup.MarginLayoutParams) s1Var2).bottomMargin;
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final int d(View view) {
        int i7 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                s1 s1Var = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.B(view) - ((ViewGroup.MarginLayoutParams) s1Var).leftMargin;
            default:
                s1 s1Var2 = (s1) view.getLayoutParams();
                r1Var.getClass();
                return r1.D(view) - ((ViewGroup.MarginLayoutParams) s1Var2).topMargin;
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final int e() {
        int i7 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                return r1Var.f2634n - r1Var.I();
            default:
                return r1Var.f2635o - r1Var.G();
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final int f() {
        int i7 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                return r1Var.H();
            default:
                return r1Var.J();
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final int g() {
        int i7 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                return (r1Var.f2634n - r1Var.H()) - r1Var.I();
            default:
                return (r1Var.f2635o - r1Var.J()) - r1Var.G();
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final int i(View view) {
        int i7 = this.f2393d;
        Rect rect = this.f2427c;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                r1Var.N(view, rect);
                return rect.right;
            default:
                r1Var.N(view, rect);
                return rect.bottom;
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final int j(View view) {
        int i7 = this.f2393d;
        Rect rect = this.f2427c;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                r1Var.N(view, rect);
                return rect.left;
            default:
                r1Var.N(view, rect);
                return rect.top;
        }
    }

    @Override // androidx.recyclerview.widget.d1
    public final void k(int i7) {
        int i8 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i8) {
            case 0:
                r1Var.R(i7);
                break;
            default:
                r1Var.S(i7);
                break;
        }
    }

    public final int l(View view) {
        int i7 = this.f2393d;
        r1 r1Var = this.f2425a;
        switch (i7) {
            case 0:
                s1 s1Var = (s1) view.getLayoutParams();
                r1Var.getClass();
                Rect rect = ((s1) view.getLayoutParams()).f2653b;
                return view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) s1Var).topMargin + ((ViewGroup.MarginLayoutParams) s1Var).bottomMargin;
            default:
                s1 s1Var2 = (s1) view.getLayoutParams();
                r1Var.getClass();
                Rect rect2 = ((s1) view.getLayoutParams()).f2653b;
                return view.getMeasuredWidth() + rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) s1Var2).leftMargin + ((ViewGroup.MarginLayoutParams) s1Var2).rightMargin;
        }
    }
}
