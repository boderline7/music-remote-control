package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.d0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class a extends d0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ChipTextInputComboView f4458a;

    public a(ChipTextInputComboView chipTextInputComboView) {
        this.f4458a = chipTextInputComboView;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        boolean isEmpty = TextUtils.isEmpty(editable);
        ChipTextInputComboView chipTextInputComboView = this.f4458a;
        if (isEmpty) {
            chipTextInputComboView.f4432a.setText(ChipTextInputComboView.a(chipTextInputComboView, "00"));
            return;
        }
        String a7 = ChipTextInputComboView.a(chipTextInputComboView, editable);
        Chip chip = chipTextInputComboView.f4432a;
        if (TextUtils.isEmpty(a7)) {
            a7 = ChipTextInputComboView.a(chipTextInputComboView, "00");
        }
        chip.setText(a7);
    }
}
