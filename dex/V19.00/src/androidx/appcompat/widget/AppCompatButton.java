package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatButton extends Button {

    /* renamed from: a, reason: collision with root package name */
    public final w f1008a;

    /* renamed from: b, reason: collision with root package name */
    public final x0 f1009b;

    /* renamed from: c, reason: collision with root package name */
    public b0 f1010c;

    public AppCompatButton(Context context) {
        this(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.buttonStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatButton(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m3.a(context);
        l3.a(this, getContext());
        w wVar = new w(this);
        this.f1008a = wVar;
        wVar.g(attributeSet, i7);
        x0 x0Var = new x0(this);
        this.f1009b = x0Var;
        x0Var.f(attributeSet, i7);
        x0Var.b();
        getEmojiTextViewHelper().b(attributeSet, i7);
    }

    private b0 getEmojiTextViewHelper() {
        if (this.f1010c == null) {
            this.f1010c = new b0(this);
        }
        return this.f1010c;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w wVar = this.f1008a;
        if (wVar != null) {
            wVar.b();
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (a4.f1189b) {
            return super.getAutoSizeMaxTextSize();
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            return Math.round(x0Var.f1473i.f1257e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (a4.f1189b) {
            return super.getAutoSizeMinTextSize();
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            return Math.round(x0Var.f1473i.f1256d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (a4.f1189b) {
            return super.getAutoSizeStepGranularity();
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            return Math.round(x0Var.f1473i.f1255c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (a4.f1189b) {
            return super.getAutoSizeTextAvailableSizes();
        }
        x0 x0Var = this.f1009b;
        return x0Var != null ? x0Var.f1473i.f1258f : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (a4.f1189b) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            return x0Var.f1473i.f1253a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.w.d(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1008a;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1008a;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1009b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1009b.e();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        x0 x0Var = this.f1009b;
        if (x0Var == null || a4.f1189b) {
            return;
        }
        x0Var.f1473i.a();
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        super.onTextChanged(charSequence, i7, i8, i9);
        x0 x0Var = this.f1009b;
        if (x0Var == null || a4.f1189b) {
            return;
        }
        g1 g1Var = x0Var.f1473i;
        if (g1Var.f()) {
            g1Var.a();
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z6) {
        super.setAllCaps(z6);
        getEmojiTextViewHelper().c(z6);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i7, int i8, int i9, int i10) {
        if (a4.f1189b) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i7, i8, i9, i10);
            return;
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            x0Var.i(i7, i8, i9, i10);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i7) {
        if (a4.f1189b) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i7);
            return;
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            x0Var.j(iArr, i7);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i7) {
        if (a4.f1189b) {
            super.setAutoSizeTextTypeWithDefaults(i7);
            return;
        }
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            x0Var.k(i7);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1008a;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1008a;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.w.e(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z6) {
        getEmojiTextViewHelper().d(z6);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportAllCaps(boolean z6) {
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            x0Var.f1465a.setAllCaps(z6);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1008a;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1008a;
        if (wVar != null) {
            wVar.m(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        x0 x0Var = this.f1009b;
        x0Var.l(colorStateList);
        x0Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        x0 x0Var = this.f1009b;
        x0Var.m(mode);
        x0Var.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        x0 x0Var = this.f1009b;
        if (x0Var != null) {
            x0Var.g(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i7, float f7) {
        boolean z6 = a4.f1189b;
        if (z6) {
            super.setTextSize(i7, f7);
            return;
        }
        x0 x0Var = this.f1009b;
        if (x0Var == null || z6) {
            return;
        }
        g1 g1Var = x0Var.f1473i;
        if (g1Var.f()) {
            return;
        }
        g1Var.g(f7, i7);
    }
}
