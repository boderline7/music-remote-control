package a1;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.widget.k3;
import y0.l;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class j implements TextWatcher {

    /* renamed from: a, reason: collision with root package name */
    public final EditText f216a;

    /* renamed from: c, reason: collision with root package name */
    public k3 f218c;

    /* renamed from: b, reason: collision with root package name */
    public final boolean f217b = false;

    /* renamed from: d, reason: collision with root package name */
    public boolean f219d = true;

    public j(EditText editText) {
        this.f216a = editText;
    }

    public static void a(EditText editText, int i7) {
        int length;
        if (i7 == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            l a7 = l.a();
            if (editableText == null) {
                length = 0;
            } else {
                a7.getClass();
                length = editableText.length();
            }
            a7.f(0, length, editableText);
            if (selectionStart >= 0 && selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionStart, selectionEnd);
            } else if (selectionStart >= 0) {
                Selection.setSelection(editableText, selectionStart);
            } else if (selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionEnd);
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        EditText editText = this.f216a;
        if (editText.isInEditMode() || !this.f219d) {
            return;
        }
        if ((this.f217b || l.f9977j != null) && i8 <= i9 && (charSequence instanceof Spannable)) {
            int b7 = l.a().b();
            if (b7 != 0) {
                if (b7 == 1) {
                    l.a().f(i7, i9 + i7, (Spannable) charSequence);
                    return;
                } else if (b7 != 3) {
                    return;
                }
            }
            l a7 = l.a();
            if (this.f218c == null) {
                this.f218c = new k3(editText);
            }
            a7.g(this.f218c);
        }
    }
}
