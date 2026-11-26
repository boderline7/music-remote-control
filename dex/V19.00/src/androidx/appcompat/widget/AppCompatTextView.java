package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatTextView extends TextView {

    /* renamed from: a, reason: collision with root package name */
    public final w f1051a;

    /* renamed from: b, reason: collision with root package name */
    public final x0 f1052b;

    /* renamed from: c, reason: collision with root package name */
    public final i6.i f1053c;

    /* renamed from: d, reason: collision with root package name */
    public b0 f1054d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f1055e;

    /* renamed from: f, reason: collision with root package name */
    public e.a f1056f;

    /* renamed from: g, reason: collision with root package name */
    public Future f1057g;

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatTextView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m3.a(context);
        this.f1055e = false;
        this.f1056f = null;
        l3.a(this, getContext());
        w wVar = new w(this);
        this.f1051a = wVar;
        wVar.g(attributeSet, i7);
        x0 x0Var = new x0(this);
        this.f1052b = x0Var;
        x0Var.f(attributeSet, i7);
        x0Var.b();
        this.f1053c = new i6.i(this);
        getEmojiTextViewHelper().b(attributeSet, i7);
    }

    private b0 getEmojiTextViewHelper() {
        if (this.f1054d == null) {
            this.f1054d = new b0(this);
        }
        return this.f1054d;
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w wVar = this.f1051a;
        if (wVar != null) {
            wVar.b();
        }
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (a4.f1189b) {
            return super.getAutoSizeMaxTextSize();
        }
        x0 x0Var = this.f1052b;
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
        x0 x0Var = this.f1052b;
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
        x0 x0Var = this.f1052b;
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
        x0 x0Var = this.f1052b;
        return x0Var != null ? x0Var.f1473i.f1258f : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (a4.f1189b) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            return x0Var.f1473i.f1253a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.w.d(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public y0 getSuperCaller() {
        if (this.f1056f == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                this.f1056f = new z0(this);
            } else {
                this.f1056f = new e.a(7, this);
            }
        }
        return this.f1056f;
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1051a;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1051a;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1052b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1052b.e();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        l();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        i6.i iVar;
        if (Build.VERSION.SDK_INT >= 28 || (iVar = this.f1053c) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = (TextClassifier) iVar.f6849c;
        return textClassifier == null ? q0.a((TextView) iVar.f6848b) : textClassifier;
    }

    public n0.e getTextMetricsParamsCompat() {
        return androidx.core.widget.w.a(this);
    }

    public final void l() {
        Future future = this.f1057g;
        if (future == null) {
            return;
        }
        try {
            this.f1057g = null;
            a.a.u(future.get());
            if (Build.VERSION.SDK_INT >= 29) {
                throw null;
            }
            androidx.core.widget.w.a(this);
            throw null;
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1052b.getClass();
        x0.h(this, onCreateInputConnection, editorInfo);
        l6.f.V(this, editorInfo, onCreateInputConnection);
        return onCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        x0 x0Var = this.f1052b;
        if (x0Var == null || a4.f1189b) {
            return;
        }
        x0Var.f1473i.a();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i7, int i8) {
        l();
        super.onMeasure(i7, i8);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        super.onTextChanged(charSequence, i7, i8, i9);
        x0 x0Var = this.f1052b;
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
        x0 x0Var = this.f1052b;
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
        x0 x0Var = this.f1052b;
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
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.k(i7);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1051a;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1051a;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i7 != 0 ? l6.f.A(context, i7) : null, i8 != 0 ? l6.f.A(context, i8) : null, i9 != 0 ? l6.f.A(context, i9) : null, i10 != 0 ? l6.f.A(context, i10) : null);
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i7, int i8, int i9, int i10) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i7 != 0 ? l6.f.A(context, i7) : null, i8 != 0 ? l6.f.A(context, i8) : null, i9 != 0 ? l6.f.A(context, i9) : null, i10 != 0 ? l6.f.A(context, i10) : null);
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1052b;
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

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i7) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().p(i7);
        } else {
            androidx.core.widget.w.b(this, i7);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i7) {
        if (Build.VERSION.SDK_INT >= 28) {
            getSuperCaller().o(i7);
        } else {
            androidx.core.widget.w.c(this, i7);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i7) {
        n1.z.u(i7);
        if (i7 != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i7 - r0, 1.0f);
        }
    }

    public void setPrecomputedText(n0.f fVar) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        androidx.core.widget.w.a(this);
        throw null;
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1051a;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1051a;
        if (wVar != null) {
            wVar.m(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        x0 x0Var = this.f1052b;
        x0Var.l(colorStateList);
        x0Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        x0 x0Var = this.f1052b;
        x0Var.m(mode);
        x0Var.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        x0 x0Var = this.f1052b;
        if (x0Var != null) {
            x0Var.g(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        i6.i iVar;
        if (Build.VERSION.SDK_INT >= 28 || (iVar = this.f1053c) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            iVar.f6849c = textClassifier;
        }
    }

    public void setTextFuture(Future<n0.f> future) {
        this.f1057g = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(n0.e eVar) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = eVar.f7577b;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        int i7 = 1;
        if (textDirectionHeuristic2 != textDirectionHeuristic3 && textDirectionHeuristic2 != (textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (textDirectionHeuristic2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i7 = 2;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LTR) {
                i7 = 3;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.RTL) {
                i7 = 4;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LOCALE) {
                i7 = 5;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic) {
                i7 = 6;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic3) {
                i7 = 7;
            }
        }
        androidx.core.widget.r.h(this, i7);
        getPaint().set(eVar.f7576a);
        androidx.core.widget.s.e(this, eVar.f7578c);
        androidx.core.widget.s.h(this, eVar.f7579d);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i7, float f7) {
        boolean z6 = a4.f1189b;
        if (z6) {
            super.setTextSize(i7, f7);
            return;
        }
        x0 x0Var = this.f1052b;
        if (x0Var == null || z6) {
            return;
        }
        g1 g1Var = x0Var.f1473i;
        if (g1Var.f()) {
            return;
        }
        g1Var.g(f7, i7);
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i7) {
        Typeface typeface2;
        if (this.f1055e) {
            return;
        }
        if (typeface == null || i7 <= 0) {
            typeface2 = null;
        } else {
            Context context = getContext();
            e.a aVar = i0.h.f6781a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typeface2 = Typeface.create(typeface, i7);
        }
        this.f1055e = true;
        if (typeface2 != null) {
            typeface = typeface2;
        }
        try {
            super.setTypeface(typeface, i7);
        } finally {
            this.f1055e = false;
        }
    }
}
