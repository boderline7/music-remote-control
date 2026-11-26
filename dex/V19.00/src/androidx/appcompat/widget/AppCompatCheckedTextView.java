package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatCheckedTextView extends CheckedTextView {

    /* renamed from: a, reason: collision with root package name */
    public final x f1015a;

    /* renamed from: b, reason: collision with root package name */
    public final w f1016b;

    /* renamed from: c, reason: collision with root package name */
    public final x0 f1017c;

    /* renamed from: d, reason: collision with root package name */
    public b0 f1018d;

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.checkedTextViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m3.a(context);
        l3.a(this, getContext());
        x0 x0Var = new x0(this);
        this.f1017c = x0Var;
        x0Var.f(attributeSet, i7);
        x0Var.b();
        w wVar = new w(this);
        this.f1016b = wVar;
        wVar.g(attributeSet, i7);
        x xVar = new x(this, 0);
        this.f1015a = xVar;
        xVar.c(attributeSet, i7);
        getEmojiTextViewHelper().b(attributeSet, i7);
    }

    private b0 getEmojiTextViewHelper() {
        if (this.f1018d == null) {
            this.f1018d = new b0(this);
        }
        return this.f1018d;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        x0 x0Var = this.f1017c;
        if (x0Var != null) {
            x0Var.b();
        }
        w wVar = this.f1016b;
        if (wVar != null) {
            wVar.b();
        }
        x xVar = this.f1015a;
        if (xVar != null) {
            xVar.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.w.d(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1016b;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1016b;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    public ColorStateList getSupportCheckMarkTintList() {
        x xVar = this.f1015a;
        if (xVar != null) {
            return xVar.f1459b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        x xVar = this.f1015a;
        if (xVar != null) {
            return xVar.f1460c;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1017c.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1017c.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        l6.f.V(this, editorInfo, onCreateInputConnection);
        return onCreateInputConnection;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z6) {
        super.setAllCaps(z6);
        getEmojiTextViewHelper().c(z6);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1016b;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1016b;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i7) {
        setCheckMarkDrawable(l6.f.A(getContext(), i7));
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        x xVar = this.f1015a;
        if (xVar != null) {
            if (xVar.f1463f) {
                xVar.f1463f = false;
            } else {
                xVar.f1463f = true;
                xVar.b();
            }
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1017c;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1017c;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.w.e(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z6) {
        getEmojiTextViewHelper().d(z6);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1016b;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1016b;
        if (wVar != null) {
            wVar.m(mode);
        }
    }

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        x xVar = this.f1015a;
        if (xVar != null) {
            xVar.f1459b = colorStateList;
            xVar.f1461d = true;
            xVar.b();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        x xVar = this.f1015a;
        if (xVar != null) {
            xVar.f1460c = mode;
            xVar.f1462e = true;
            xVar.b();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        x0 x0Var = this.f1017c;
        x0Var.l(colorStateList);
        x0Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        x0 x0Var = this.f1017c;
        x0Var.m(mode);
        x0Var.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        x0 x0Var = this.f1017c;
        if (x0Var != null) {
            x0Var.g(context, i7);
        }
    }
}
