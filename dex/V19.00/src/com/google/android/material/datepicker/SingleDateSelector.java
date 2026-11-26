package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class SingleDateSelector implements DateSelector<Long> {
    public static final Parcelable.Creator<SingleDateSelector> CREATOR = new z(4);

    /* renamed from: a, reason: collision with root package name */
    public Long f3591a;

    @Override // com.google.android.material.datepicker.DateSelector
    public final Object a() {
        return this.f3591a;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String b(Context context) {
        Resources resources = context.getResources();
        Long l7 = this.f3591a;
        return resources.getString(q2.k.mtrl_picker_announce_current_selection, l7 == null ? resources.getString(q2.k.mtrl_picker_announce_current_selection_none) : n1.z.t0(l7.longValue(), Locale.getDefault()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String c(Context context) {
        Resources resources = context.getResources();
        Long l7 = this.f3591a;
        if (l7 == null) {
            return resources.getString(q2.k.mtrl_picker_date_header_unselected);
        }
        return resources.getString(q2.k.mtrl_picker_date_header_selected, n1.z.t0(l7.longValue(), Locale.getDefault()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final int d(Context context) {
        return l6.f.w0(context, q2.c.materialCalendarTheme, x.class.getCanonicalName()).data;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList e() {
        return new ArrayList();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
    
        if (r2.equals("samsung") != false) goto L12;
     */
    @Override // com.google.android.material.datepicker.DateSelector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View g(android.view.LayoutInflater r11, android.view.ViewGroup r12, com.google.android.material.datepicker.CalendarConstraints r13, com.google.android.material.datepicker.w r14) {
        /*
            r10 = this;
            int r0 = q2.i.mtrl_picker_text_input_date
            r1 = 0
            android.view.View r11 = r11.inflate(r0, r12, r1)
            int r12 = q2.g.mtrl_picker_text_input_date
            android.view.View r12 = r11.findViewById(r12)
            r9 = r12
            com.google.android.material.textfield.TextInputLayout r9 = (com.google.android.material.textfield.TextInputLayout) r9
            android.widget.EditText r12 = r9.getEditText()
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r2 = ""
            if (r0 == 0) goto L21
            java.util.Locale r3 = java.util.Locale.ENGLISH
            java.lang.String r3 = r0.toLowerCase(r3)
            goto L22
        L21:
            r3 = r2
        L22:
            java.lang.String r4 = "lge"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L3a
            if (r0 == 0) goto L32
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r2 = r0.toLowerCase(r2)
        L32:
            java.lang.String r0 = "samsung"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L3f
        L3a:
            r0 = 17
            r12.setInputType(r0)
        L3f:
            java.text.SimpleDateFormat r5 = com.google.android.material.datepicker.j0.d()
            android.content.res.Resources r0 = r11.getResources()
            java.lang.String r4 = com.google.android.material.datepicker.j0.e(r0, r5)
            r9.setPlaceholderText(r4)
            java.lang.Long r0 = r10.f3591a
            if (r0 == 0) goto L59
            java.lang.String r0 = r5.format(r0)
            r12.setText(r0)
        L59:
            com.google.android.material.datepicker.h0 r0 = new com.google.android.material.datepicker.h0
            r2 = r0
            r3 = r10
            r6 = r9
            r7 = r13
            r8 = r14
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            r12.addTextChangedListener(r0)
            r13 = 1
            android.widget.EditText[] r13 = new android.widget.EditText[r13]
            r13[r1] = r12
            com.google.android.material.datepicker.DateSelector.j(r13)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.SingleDateSelector.g(android.view.LayoutInflater, android.view.ViewGroup, com.google.android.material.datepicker.CalendarConstraints, com.google.android.material.datepicker.w):android.view.View");
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final boolean h() {
        return this.f3591a != null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final void i(long j7) {
        this.f3591a = Long.valueOf(j7);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList k() {
        ArrayList arrayList = new ArrayList();
        Long l7 = this.f3591a;
        if (l7 != null) {
            arrayList.add(l7);
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeValue(this.f3591a);
    }
}
