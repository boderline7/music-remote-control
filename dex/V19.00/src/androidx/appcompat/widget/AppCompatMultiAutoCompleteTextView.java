package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView {

    /* renamed from: d, reason: collision with root package name */
    public static final int[] f1031d = {R.attr.popupBackground};

    /* renamed from: a, reason: collision with root package name */
    public final w f1032a;

    /* renamed from: b, reason: collision with root package name */
    public final x0 f1033b;

    /* renamed from: c, reason: collision with root package name */
    public final i6.i f1034c;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.autoCompleteTextViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m3.a(context);
        l3.a(this, getContext());
        androidx.appcompat.app.g S = androidx.appcompat.app.g.S(getContext(), attributeSet, f1031d, i7);
        if (S.R(0)) {
            setDropDownBackgroundDrawable(S.I(0));
        }
        S.V();
        w wVar = new w(this);
        this.f1032a = wVar;
        wVar.g(attributeSet, i7);
        x0 x0Var = new x0(this);
        this.f1033b = x0Var;
        x0Var.f(attributeSet, i7);
        x0Var.b();
        i6.i iVar = new i6.i((EditText) this);
        this.f1034c = iVar;
        iVar.j(attributeSet, i7);
        KeyListener keyListener = getKeyListener();
        if (!(keyListener instanceof NumberKeyListener)) {
            boolean isFocusable = super.isFocusable();
            boolean isClickable = super.isClickable();
            boolean isLongClickable = super.isLongClickable();
            int inputType = super.getInputType();
            KeyListener i8 = iVar.i(keyListener);
            if (i8 == keyListener) {
                return;
            }
            super.setKeyListener(i8);
            super.setRawInputType(inputType);
            super.setFocusable(isFocusable);
            super.setClickable(isClickable);
            super.setLongClickable(isLongClickable);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w wVar = this.f1032a;
        if (wVar != null) {
            wVar.b();
        }
        x0 x0Var = this.f1033b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1032a;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1032a;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1033b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1033b.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        l6.f.V(this, editorInfo, onCreateInputConnection);
        return this.f1034c.q(onCreateInputConnection, editorInfo);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1032a;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1032a;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1033b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1033b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i7) {
        setDropDownBackgroundDrawable(l6.f.A(getContext(), i7));
    }

    public void setEmojiCompatEnabled(boolean z6) {
        ((p3.f) ((a1.b) this.f1034c.f6849c).f200d).G(z6);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f1034c.i(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1032a;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1032a;
        if (wVar != null) {
            wVar.m(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        x0 x0Var = this.f1033b;
        x0Var.l(colorStateList);
        x0Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        x0 x0Var = this.f1033b;
        x0Var.m(mode);
        x0Var.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        x0 x0Var = this.f1033b;
        if (x0Var != null) {
            x0Var.g(context, i7);
        }
    }
}
