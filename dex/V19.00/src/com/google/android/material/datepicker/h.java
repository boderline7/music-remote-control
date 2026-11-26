package com.google.android.material.datepicker;

import android.text.Editable;
import android.text.TextUtils;
import androidx.appcompat.app.p0;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public abstract class h extends com.google.android.material.internal.d0 {

    /* renamed from: a, reason: collision with root package name */
    public final TextInputLayout f3621a;

    /* renamed from: b, reason: collision with root package name */
    public final String f3622b;

    /* renamed from: c, reason: collision with root package name */
    public final DateFormat f3623c;

    /* renamed from: d, reason: collision with root package name */
    public final CalendarConstraints f3624d;

    /* renamed from: e, reason: collision with root package name */
    public final String f3625e;

    /* renamed from: f, reason: collision with root package name */
    public final p0 f3626f;

    /* renamed from: g, reason: collision with root package name */
    public g f3627g;

    /* renamed from: h, reason: collision with root package name */
    public int f3628h = 0;

    public h(String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.f3622b = str;
        this.f3623c = simpleDateFormat;
        this.f3621a = textInputLayout;
        this.f3624d = calendarConstraints;
        this.f3625e = textInputLayout.getContext().getString(q2.k.mtrl_picker_out_of_range);
        this.f3626f = new p0(4, this, str);
    }

    public abstract void a();

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) || editable.length() == 0) {
            return;
        }
        int length = editable.length();
        String str = this.f3622b;
        if (length >= str.length() || editable.length() < this.f3628h) {
            return;
        }
        char charAt = str.charAt(editable.length());
        if (Character.isDigit(charAt)) {
            return;
        }
        editable.append(charAt);
    }

    public abstract void b(Long l7);

    @Override // com.google.android.material.internal.d0, android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        this.f3628h = charSequence.length();
    }

    @Override // com.google.android.material.internal.d0, android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        CalendarConstraints calendarConstraints = this.f3624d;
        TextInputLayout textInputLayout = this.f3621a;
        p0 p0Var = this.f3626f;
        textInputLayout.removeCallbacks(p0Var);
        textInputLayout.removeCallbacks(this.f3627g);
        textInputLayout.setError(null);
        b(null);
        if (TextUtils.isEmpty(charSequence) || charSequence.length() < this.f3622b.length()) {
            return;
        }
        try {
            Date parse = this.f3623c.parse(charSequence.toString());
            textInputLayout.setError(null);
            long time = parse.getTime();
            if (calendarConstraints.f3566c.f(time)) {
                Calendar c7 = j0.c(calendarConstraints.f3564a.f3579a);
                c7.set(5, 1);
                if (c7.getTimeInMillis() <= time) {
                    Month month = calendarConstraints.f3565b;
                    int i10 = month.f3583e;
                    Calendar c8 = j0.c(month.f3579a);
                    c8.set(5, i10);
                    if (time <= c8.getTimeInMillis()) {
                        b(Long.valueOf(parse.getTime()));
                        return;
                    }
                }
            }
            g gVar = new g(this, time, 0);
            this.f3627g = gVar;
            textInputLayout.post(gVar);
        } catch (ParseException unused) {
            textInputLayout.post(p0Var);
        }
    }
}
