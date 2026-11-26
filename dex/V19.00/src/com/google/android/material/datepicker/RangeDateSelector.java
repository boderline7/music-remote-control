package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class RangeDateSelector implements DateSelector<o0.c> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new z(3);

    /* renamed from: a, reason: collision with root package name */
    public String f3586a;

    /* renamed from: b, reason: collision with root package name */
    public Long f3587b;

    /* renamed from: c, reason: collision with root package name */
    public Long f3588c;

    /* renamed from: d, reason: collision with root package name */
    public Long f3589d;

    /* renamed from: e, reason: collision with root package name */
    public Long f3590e;

    public static void l(RangeDateSelector rangeDateSelector, TextInputLayout textInputLayout, TextInputLayout textInputLayout2, e0 e0Var) {
        Long l7 = rangeDateSelector.f3589d;
        if (l7 == null || rangeDateSelector.f3590e == null) {
            if (textInputLayout.getError() != null && rangeDateSelector.f3586a.contentEquals(textInputLayout.getError())) {
                textInputLayout.setError(null);
            }
            if (textInputLayout2.getError() != null && " ".contentEquals(textInputLayout2.getError())) {
                textInputLayout2.setError(null);
            }
            e0Var.a();
        } else if (l7.longValue() <= rangeDateSelector.f3590e.longValue()) {
            Long l8 = rangeDateSelector.f3589d;
            rangeDateSelector.f3587b = l8;
            Long l9 = rangeDateSelector.f3590e;
            rangeDateSelector.f3588c = l9;
            e0Var.b(new o0.c(l8, l9));
        } else {
            textInputLayout.setError(rangeDateSelector.f3586a);
            textInputLayout2.setError(" ");
            e0Var.a();
        }
        if (!TextUtils.isEmpty(textInputLayout.getError())) {
            textInputLayout.getError();
        } else {
            if (TextUtils.isEmpty(textInputLayout2.getError())) {
                return;
            }
            textInputLayout2.getError();
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final Object a() {
        return new o0.c(this.f3587b, this.f3588c);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String b(Context context) {
        Resources resources = context.getResources();
        o0.c h02 = n1.z.h0(this.f3587b, this.f3588c);
        Object obj = h02.f7788a;
        String string = obj == null ? resources.getString(q2.k.mtrl_picker_announce_current_selection_none) : (String) obj;
        Object obj2 = h02.f7789b;
        return resources.getString(q2.k.mtrl_picker_announce_current_range_selection, string, obj2 == null ? resources.getString(q2.k.mtrl_picker_announce_current_selection_none) : (String) obj2);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final String c(Context context) {
        Resources resources = context.getResources();
        Long l7 = this.f3587b;
        if (l7 == null && this.f3588c == null) {
            return resources.getString(q2.k.mtrl_picker_range_header_unselected);
        }
        Long l8 = this.f3588c;
        if (l8 == null) {
            return resources.getString(q2.k.mtrl_picker_range_header_only_start_selected, n1.z.i0(l7.longValue()));
        }
        if (l7 == null) {
            return resources.getString(q2.k.mtrl_picker_range_header_only_end_selected, n1.z.i0(l8.longValue()));
        }
        o0.c h02 = n1.z.h0(l7, l8);
        return resources.getString(q2.k.mtrl_picker_range_header_selected, h02.f7788a, h02.f7789b);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final int d(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return l6.f.w0(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(q2.e.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? q2.c.materialCalendarTheme : q2.c.materialCalendarFullscreenTheme, x.class.getCanonicalName()).data;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new o0.c(this.f3587b, this.f3588c));
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x004b, code lost:
    
        if (r1.equals("samsung") != false) goto L12;
     */
    @Override // com.google.android.material.datepicker.DateSelector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View g(android.view.LayoutInflater r20, android.view.ViewGroup r21, com.google.android.material.datepicker.CalendarConstraints r22, com.google.android.material.datepicker.w r23) {
        /*
            r19 = this;
            r10 = r19
            int r0 = q2.i.mtrl_picker_text_input_date_range
            r11 = 0
            r1 = r20
            r2 = r21
            android.view.View r12 = r1.inflate(r0, r2, r11)
            int r0 = q2.g.mtrl_picker_text_input_range_start
            android.view.View r0 = r12.findViewById(r0)
            r13 = r0
            com.google.android.material.textfield.TextInputLayout r13 = (com.google.android.material.textfield.TextInputLayout) r13
            int r0 = q2.g.mtrl_picker_text_input_range_end
            android.view.View r0 = r12.findViewById(r0)
            r14 = r0
            com.google.android.material.textfield.TextInputLayout r14 = (com.google.android.material.textfield.TextInputLayout) r14
            android.widget.EditText r15 = r13.getEditText()
            android.widget.EditText r9 = r14.getEditText()
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = ""
            if (r0 == 0) goto L34
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r2 = r0.toLowerCase(r2)
            goto L35
        L34:
            r2 = r1
        L35:
            java.lang.String r3 = "lge"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L4d
            if (r0 == 0) goto L45
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r1 = r0.toLowerCase(r1)
        L45:
            java.lang.String r0 = "samsung"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L55
        L4d:
            r0 = 17
            r15.setInputType(r0)
            r9.setInputType(r0)
        L55:
            android.content.res.Resources r0 = r12.getResources()
            int r1 = q2.k.mtrl_picker_invalid_range
            java.lang.String r0 = r0.getString(r1)
            r10.f3586a = r0
            java.text.SimpleDateFormat r8 = com.google.android.material.datepicker.j0.d()
            java.lang.Long r0 = r10.f3587b
            if (r0 == 0) goto L74
            java.lang.String r0 = r8.format(r0)
            r15.setText(r0)
            java.lang.Long r0 = r10.f3587b
            r10.f3589d = r0
        L74:
            java.lang.Long r0 = r10.f3588c
            if (r0 == 0) goto L83
            java.lang.String r0 = r8.format(r0)
            r9.setText(r0)
            java.lang.Long r0 = r10.f3588c
            r10.f3590e = r0
        L83:
            android.content.res.Resources r0 = r12.getResources()
            java.lang.String r7 = com.google.android.material.datepicker.j0.e(r0, r8)
            r13.setPlaceholderText(r7)
            r14.setPlaceholderText(r7)
            com.google.android.material.datepicker.g0 r6 = new com.google.android.material.datepicker.g0
            r16 = 0
            r0 = r6
            r1 = r19
            r2 = r7
            r3 = r8
            r4 = r13
            r5 = r22
            r11 = r6
            r6 = r13
            r17 = r7
            r7 = r14
            r18 = r8
            r8 = r23
            r10 = r9
            r9 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r15.addTextChangedListener(r11)
            com.google.android.material.datepicker.g0 r11 = new com.google.android.material.datepicker.g0
            r9 = 1
            r0 = r11
            r2 = r17
            r3 = r18
            r4 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r10.addTextChangedListener(r11)
            r0 = 2
            android.widget.EditText[] r0 = new android.widget.EditText[r0]
            r1 = 0
            r0[r1] = r15
            r1 = 1
            r0[r1] = r10
            com.google.android.material.datepicker.DateSelector.j(r0)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.RangeDateSelector.g(android.view.LayoutInflater, android.view.ViewGroup, com.google.android.material.datepicker.CalendarConstraints, com.google.android.material.datepicker.w):android.view.View");
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final boolean h() {
        Long l7 = this.f3587b;
        return (l7 == null || this.f3588c == null || l7.longValue() > this.f3588c.longValue()) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final void i(long j7) {
        Long l7 = this.f3587b;
        if (l7 == null) {
            this.f3587b = Long.valueOf(j7);
        } else if (this.f3588c == null && l7.longValue() <= j7) {
            this.f3588c = Long.valueOf(j7);
        } else {
            this.f3588c = null;
            this.f3587b = Long.valueOf(j7);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public final ArrayList k() {
        ArrayList arrayList = new ArrayList();
        Long l7 = this.f3587b;
        if (l7 != null) {
            arrayList.add(l7);
        }
        Long l8 = this.f3588c;
        if (l8 != null) {
            arrayList.add(l8);
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeValue(this.f3587b);
        parcel.writeValue(this.f3588c);
    }
}
