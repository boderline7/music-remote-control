package com.google.android.material.textfield;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatTextView;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class s extends p0.c {

    /* renamed from: d, reason: collision with root package name */
    public final TextInputLayout f4431d;

    public s(TextInputLayout textInputLayout) {
        this.f4431d = textInputLayout;
    }

    @Override // p0.c
    public final void d(View view, q0.i iVar) {
        View.AccessibilityDelegate accessibilityDelegate = this.f8103a;
        AccessibilityNodeInfo accessibilityNodeInfo = iVar.f8426a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        TextInputLayout textInputLayout = this.f4431d;
        EditText editText = textInputLayout.getEditText();
        Editable text = editText != null ? editText.getText() : null;
        CharSequence hint = textInputLayout.getHint();
        CharSequence error = textInputLayout.getError();
        CharSequence placeholderText = textInputLayout.getPlaceholderText();
        int counterMaxLength = textInputLayout.getCounterMaxLength();
        CharSequence counterOverflowDescription = textInputLayout.getCounterOverflowDescription();
        boolean isEmpty = TextUtils.isEmpty(text);
        boolean z6 = !isEmpty;
        boolean z7 = true;
        boolean z8 = !TextUtils.isEmpty(hint);
        boolean z9 = !textInputLayout.f4308t0;
        boolean z10 = !TextUtils.isEmpty(error);
        if (!z10 && TextUtils.isEmpty(counterOverflowDescription)) {
            z7 = false;
        }
        String charSequence = z8 ? hint.toString() : h0.FRAGMENT_ENCODE_SET;
        r rVar = textInputLayout.f4271b;
        AppCompatTextView appCompatTextView = rVar.f4422b;
        if (appCompatTextView.getVisibility() == 0) {
            accessibilityNodeInfo.setLabelFor(appCompatTextView);
            accessibilityNodeInfo.setTraversalAfter(appCompatTextView);
        } else {
            accessibilityNodeInfo.setTraversalAfter(rVar.f4424d);
        }
        if (z6) {
            accessibilityNodeInfo.setText(text);
        } else if (!TextUtils.isEmpty(charSequence)) {
            accessibilityNodeInfo.setText(charSequence);
            if (z9 && placeholderText != null) {
                accessibilityNodeInfo.setText(charSequence + ", " + ((Object) placeholderText));
            }
        } else if (placeholderText != null) {
            accessibilityNodeInfo.setText(placeholderText);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            accessibilityNodeInfo.setHintText(charSequence);
            accessibilityNodeInfo.setShowingHintText(isEmpty);
        }
        if (text == null || text.length() != counterMaxLength) {
            counterMaxLength = -1;
        }
        accessibilityNodeInfo.setMaxTextLength(counterMaxLength);
        if (z7) {
            if (!z10) {
                error = counterOverflowDescription;
            }
            accessibilityNodeInfo.setError(error);
        }
        AppCompatTextView appCompatTextView2 = textInputLayout.f4287j.f4413y;
        if (appCompatTextView2 != null) {
            accessibilityNodeInfo.setLabelFor(appCompatTextView2);
        }
        textInputLayout.f4273c.b().n(iVar);
    }

    @Override // p0.c
    public final void e(View view, AccessibilityEvent accessibilityEvent) {
        super.e(view, accessibilityEvent);
        this.f4431d.f4273c.b().o(accessibilityEvent);
    }
}
