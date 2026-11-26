package com.google.android.material.datepicker;

import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h0 extends h {

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e0 f3629i;

    /* renamed from: j, reason: collision with root package name */
    public final /* synthetic */ TextInputLayout f3630j;

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ SingleDateSelector f3631k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(SingleDateSelector singleDateSelector, String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, w wVar, TextInputLayout textInputLayout2) {
        super(str, simpleDateFormat, textInputLayout, calendarConstraints);
        this.f3631k = singleDateSelector;
        this.f3629i = wVar;
        this.f3630j = textInputLayout2;
    }

    @Override // com.google.android.material.datepicker.h
    public final void a() {
        this.f3630j.getError();
        this.f3631k.getClass();
        this.f3629i.a();
    }

    @Override // com.google.android.material.datepicker.h
    public final void b(Long l7) {
        SingleDateSelector singleDateSelector = this.f3631k;
        if (l7 == null) {
            singleDateSelector.f3591a = null;
        } else {
            singleDateSelector.i(l7.longValue());
        }
        singleDateSelector.getClass();
        this.f3629i.b(singleDateSelector.f3591a);
    }
}
