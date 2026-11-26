package androidx.appcompat.widget;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import android.widget.TextView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AppCompatEditText extends EditText implements p0.d0 {

    /* renamed from: a, reason: collision with root package name */
    public final w f1019a;

    /* renamed from: b, reason: collision with root package name */
    public final x0 f1020b;

    /* renamed from: c, reason: collision with root package name */
    public final i6.i f1021c;

    /* renamed from: d, reason: collision with root package name */
    public final androidx.core.widget.x f1022d;

    /* renamed from: e, reason: collision with root package name */
    public final i6.i f1023e;

    /* renamed from: f, reason: collision with root package name */
    public a0 f1024f;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, f.a.editTextStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatEditText(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        m3.a(context);
        l3.a(this, getContext());
        w wVar = new w(this);
        this.f1019a = wVar;
        wVar.g(attributeSet, i7);
        x0 x0Var = new x0(this);
        this.f1020b = x0Var;
        x0Var.f(attributeSet, i7);
        x0Var.b();
        this.f1021c = new i6.i((TextView) this);
        this.f1022d = new androidx.core.widget.x();
        i6.i iVar = new i6.i((EditText) this);
        this.f1023e = iVar;
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

    private a0 getSuperCaller() {
        if (this.f1024f == null) {
            this.f1024f = new a0(this);
        }
        return this.f1024f;
    }

    @Override // p0.d0
    public final p0.i a(p0.i iVar) {
        return this.f1022d.a(this, iVar);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        w wVar = this.f1019a;
        if (wVar != null) {
            wVar.b();
        }
        x0 x0Var = this.f1020b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return androidx.core.widget.w.d(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        w wVar = this.f1019a;
        if (wVar != null) {
            return wVar.e();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        w wVar = this.f1019a;
        if (wVar != null) {
            return wVar.f();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f1020b.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f1020b.e();
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        i6.i iVar;
        if (Build.VERSION.SDK_INT >= 28 || (iVar = this.f1021c) == null) {
            return super.getTextClassifier();
        }
        TextClassifier textClassifier = (TextClassifier) iVar.f6849c;
        return textClassifier == null ? q0.a((TextView) iVar.f6848b) : textClassifier;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        String[] g7;
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1020b.getClass();
        x0.h(this, onCreateInputConnection, editorInfo);
        l6.f.V(this, editorInfo, onCreateInputConnection);
        if (onCreateInputConnection != null && Build.VERSION.SDK_INT <= 30 && (g7 = p0.d1.g(this)) != null) {
            editorInfo.contentMimeTypes = g7;
            onCreateInputConnection = new s0.c(onCreateInputConnection, new e0.f(1, this));
        }
        return this.f1023e.q(onCreateInputConnection, editorInfo);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 31 && dragEvent.getLocalState() == null && p0.d1.g(this) != null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    activity = null;
                    break;
                }
                if (context instanceof Activity) {
                    activity = (Activity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (activity == null) {
                toString();
            } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3 && f0.a(dragEvent, this, activity)) {
                return true;
            }
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i7) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 31 || p0.d1.g(this) == null || !(i7 == 16908322 || i7 == 16908337)) {
            return super.onTextContextMenuItem(i7);
        }
        ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
        ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            p0.f eVar = i8 >= 31 ? new p0.e(primaryClip, 1) : new p0.g(primaryClip, 1);
            eVar.e(i7 == 16908322 ? 0 : 1);
            p0.d1.k(this, eVar.a());
        }
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        w wVar = this.f1019a;
        if (wVar != null) {
            wVar.h();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        w wVar = this.f1019a;
        if (wVar != null) {
            wVar.i(i7);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1020b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        x0 x0Var = this.f1020b;
        if (x0Var != null) {
            x0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.w.e(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z6) {
        ((p3.f) ((a1.b) this.f1023e.f6849c).f200d).G(z6);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f1023e.i(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        w wVar = this.f1019a;
        if (wVar != null) {
            wVar.l(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1019a;
        if (wVar != null) {
            wVar.m(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        x0 x0Var = this.f1020b;
        x0Var.l(colorStateList);
        x0Var.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        x0 x0Var = this.f1020b;
        x0Var.m(mode);
        x0Var.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        x0 x0Var = this.f1020b;
        if (x0Var != null) {
            x0Var.g(context, i7);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        i6.i iVar;
        if (Build.VERSION.SDK_INT >= 28 || (iVar = this.f1021c) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            iVar.f6849c = textClassifier;
        }
    }
}
