package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.g0;
import com.google.android.material.textfield.TextInputLayout;
import p0.d1;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
class ChipTextInputComboView extends FrameLayout implements Checkable {

    /* renamed from: a, reason: collision with root package name */
    public final Chip f4432a;

    /* renamed from: b, reason: collision with root package name */
    public final EditText f4433b;

    public ChipTextInputComboView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        LayoutInflater from = LayoutInflater.from(context);
        Chip chip = (Chip) from.inflate(q2.i.material_time_chip, (ViewGroup) this, false);
        this.f4432a = chip;
        chip.setAccessibilityClassName("android.view.View");
        TextInputLayout textInputLayout = (TextInputLayout) from.inflate(q2.i.material_time_input, (ViewGroup) this, false);
        EditText editText = textInputLayout.getEditText();
        this.f4433b = editText;
        editText.setVisibility(4);
        editText.addTextChangedListener(new a(this));
        editText.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
        addView(chip);
        addView(textInputLayout);
        TextView textView = (TextView) findViewById(q2.g.material_label);
        int i7 = d1.OVER_SCROLL_ALWAYS;
        editText.setId(m0.a());
        m0.h(textView, editText.getId());
        editText.setSaveEnabled(false);
        editText.setLongClickable(false);
    }

    public static String a(ChipTextInputComboView chipTextInputComboView, CharSequence charSequence) {
        try {
            return String.format(chipTextInputComboView.getResources().getConfiguration().locale, TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.f4432a.isChecked();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f4433b.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
    }

    @Override // android.widget.Checkable
    public final void setChecked(boolean z6) {
        Chip chip = this.f4432a;
        chip.setChecked(z6);
        int i7 = z6 ? 0 : 4;
        EditText editText = this.f4433b;
        editText.setVisibility(i7);
        chip.setVisibility(z6 ? 8 : 0);
        if (isChecked()) {
            editText.requestFocus();
            editText.post(new g0(editText, false, 0));
        }
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.f4432a.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public final void setTag(int i7, Object obj) {
        this.f4432a.setTag(i7, obj);
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        this.f4432a.toggle();
    }
}
