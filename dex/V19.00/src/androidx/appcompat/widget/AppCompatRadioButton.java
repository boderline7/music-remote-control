package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.RadioButton;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatRadioButton extends RadioButton implements androidx.core.widget.y {

    /* renamed from: a, reason: collision with root package name */
    public final x f1035a;

    /* renamed from: b, reason: collision with root package name */
    public final w f1036b;

    /* renamed from: c, reason: collision with root package name */
    public final x0 f1037c;

    /* renamed from: d, reason: collision with root package name */
    public b0 f1038d;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.radioButtonStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m3.a(context);
        l3.a(this, getContext());
        x xVar = new x(this, 1);
        this.f1035a = xVar;
        xVar.c(attributeSet, i7);
        w wVar = new w(this);
        this.f1036b = wVar;
        wVar.g(attributeSet, i7);
        x0 x0Var = new x0(this);
        this.f1037c = x0Var;
        x0Var.f(attributeSet, i7);
        getEmojiTextViewHelper().b(attributeSet, i7);
    }

    private b0 getEmojiTextViewHelper() {
        if (this.f1038d == null) {
            this.f1038d = new b0(this);
        }
        return this.f1038d;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w wVar = this.f1036b;
        if (wVar != null) {
            wVar.b();
        }
        x0 x0Var = this.f1037c;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        x xVar = this.f1035a;
        if (xVar != null) {
            xVar.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1036b;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1036b;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    @Override // androidx.core.widget.y
    public ColorStateList getSupportButtonTintList() {
        x xVar = this.f1035a;
        if (xVar != null) {
            return xVar.f1459b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        x xVar = this.f1035a;
        if (xVar != null) {
            return xVar.f1460c;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1037c.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1037c.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z6) {
        super.setAllCaps(z6);
        getEmojiTextViewHelper().c(z6);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1036b;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1036b;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i7) {
        setButtonDrawable(l6.f.A(getContext(), i7));
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        x xVar = this.f1035a;
        if (xVar != null) {
            if (xVar.f1463f) {
                xVar.f1463f = false;
            } else {
                xVar.f1463f = true;
                xVar.a();
            }
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1037c;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1037c;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z6) {
        getEmojiTextViewHelper().d(z6);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1036b;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1036b;
        if (wVar != null) {
            wVar.m(mode);
        }
    }

    @Override // androidx.core.widget.y
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        x xVar = this.f1035a;
        if (xVar != null) {
            xVar.f1459b = colorStateList;
            xVar.f1461d = true;
            xVar.a();
        }
    }

    @Override // androidx.core.widget.y
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        x xVar = this.f1035a;
        if (xVar != null) {
            xVar.f1460c = mode;
            xVar.f1462e = true;
            xVar.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        x0 x0Var = this.f1037c;
        x0Var.l(colorStateList);
        x0Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        x0 x0Var = this.f1037c;
        x0Var.m(mode);
        x0Var.b();
    }
}
