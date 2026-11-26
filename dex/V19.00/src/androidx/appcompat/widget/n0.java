package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n0 extends e2 implements p0 {
    public CharSequence C;
    public ListAdapter D;
    public final Rect E;
    public int F;
    public final /* synthetic */ AppCompatSpinner G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n0(AppCompatSpinner appCompatSpinner, Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7, 0);
        this.G = appCompatSpinner;
        this.E = new Rect();
        this.f1226o = appCompatSpinner;
        this.f1236y = true;
        this.f1237z.setFocusable(true);
        this.f1227p = new androidx.appcompat.app.i(1, this, appCompatSpinner);
    }

    @Override // androidx.appcompat.widget.p0
    public final void f(int i7, int i8) {
        ViewTreeObserver viewTreeObserver;
        d0 d0Var = this.f1237z;
        boolean isShowing = d0Var.isShowing();
        s();
        this.f1237z.setInputMethodMode(2);
        g();
        s1 s1Var = this.f1214c;
        s1Var.setChoiceMode(1);
        i0.d(s1Var, i7);
        i0.c(s1Var, i8);
        AppCompatSpinner appCompatSpinner = this.G;
        int selectedItemPosition = appCompatSpinner.getSelectedItemPosition();
        s1 s1Var2 = this.f1214c;
        if (d0Var.isShowing() && s1Var2 != null) {
            s1Var2.setListSelectionHidden(false);
            s1Var2.setSelection(selectedItemPosition);
            if (s1Var2.getChoiceMode() != 0) {
                s1Var2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (isShowing || (viewTreeObserver = appCompatSpinner.getViewTreeObserver()) == null) {
            return;
        }
        k.e eVar = new k.e(4, this);
        viewTreeObserver.addOnGlobalLayoutListener(eVar);
        this.f1237z.setOnDismissListener(new m0(this, eVar));
    }

    @Override // androidx.appcompat.widget.p0
    public final CharSequence j() {
        return this.C;
    }

    @Override // androidx.appcompat.widget.p0
    public final void m(CharSequence charSequence) {
        this.C = charSequence;
    }

    @Override // androidx.appcompat.widget.e2, androidx.appcompat.widget.p0
    public final void p(ListAdapter listAdapter) {
        super.p(listAdapter);
        this.D = listAdapter;
    }

    @Override // androidx.appcompat.widget.p0
    public final void q(int i7) {
        this.F = i7;
    }

    public final void s() {
        int i7;
        d0 d0Var = this.f1237z;
        Drawable background = d0Var.getBackground();
        AppCompatSpinner appCompatSpinner = this.G;
        if (background != null) {
            background.getPadding(appCompatSpinner.f1049h);
            boolean a7 = a4.a(appCompatSpinner);
            Rect rect = appCompatSpinner.f1049h;
            i7 = a7 ? rect.right : -rect.left;
        } else {
            Rect rect2 = appCompatSpinner.f1049h;
            rect2.right = 0;
            rect2.left = 0;
            i7 = 0;
        }
        int paddingLeft = appCompatSpinner.getPaddingLeft();
        int paddingRight = appCompatSpinner.getPaddingRight();
        int width = appCompatSpinner.getWidth();
        int i8 = appCompatSpinner.f1048g;
        if (i8 == -2) {
            int a8 = appCompatSpinner.a((SpinnerAdapter) this.D, d0Var.getBackground());
            int i9 = appCompatSpinner.getContext().getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = appCompatSpinner.f1049h;
            int i10 = (i9 - rect3.left) - rect3.right;
            if (a8 > i10) {
                a8 = i10;
            }
            r(Math.max(a8, (width - paddingLeft) - paddingRight));
        } else if (i8 == -1) {
            r((width - paddingLeft) - paddingRight);
        } else {
            r(i8);
        }
        this.f1217f = a4.a(appCompatSpinner) ? (((width - paddingRight) - this.f1216e) - this.F) + i7 : paddingLeft + this.F + i7;
    }
}
