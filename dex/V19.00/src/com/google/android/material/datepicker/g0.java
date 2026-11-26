package com.google.android.material.datepicker;

import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class g0 extends h {

    /* renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3616i;

    /* renamed from: j, reason: collision with root package name */
    public final /* synthetic */ TextInputLayout f3617j;

    /* renamed from: k, reason: collision with root package name */
    public final /* synthetic */ TextInputLayout f3618k;

    /* renamed from: l, reason: collision with root package name */
    public final /* synthetic */ e0 f3619l;

    /* renamed from: m, reason: collision with root package name */
    public final /* synthetic */ RangeDateSelector f3620m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g0(RangeDateSelector rangeDateSelector, String str, SimpleDateFormat simpleDateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, w wVar, int i7) {
        super(str, simpleDateFormat, textInputLayout, calendarConstraints);
        this.f3616i = i7;
        this.f3620m = rangeDateSelector;
        this.f3617j = textInputLayout2;
        this.f3618k = textInputLayout3;
        this.f3619l = wVar;
    }

    @Override // com.google.android.material.datepicker.h
    public final void a() {
        int i7 = this.f3616i;
        TextInputLayout textInputLayout = this.f3617j;
        e0 e0Var = this.f3619l;
        TextInputLayout textInputLayout2 = this.f3618k;
        RangeDateSelector rangeDateSelector = this.f3620m;
        switch (i7) {
            case 0:
                rangeDateSelector.f3589d = null;
                RangeDateSelector.l(rangeDateSelector, textInputLayout, textInputLayout2, e0Var);
                break;
            default:
                rangeDateSelector.f3590e = null;
                RangeDateSelector.l(rangeDateSelector, textInputLayout, textInputLayout2, e0Var);
                break;
        }
    }

    @Override // com.google.android.material.datepicker.h
    public final void b(Long l7) {
        int i7 = this.f3616i;
        TextInputLayout textInputLayout = this.f3617j;
        e0 e0Var = this.f3619l;
        TextInputLayout textInputLayout2 = this.f3618k;
        RangeDateSelector rangeDateSelector = this.f3620m;
        switch (i7) {
            case 0:
                rangeDateSelector.f3589d = l7;
                RangeDateSelector.l(rangeDateSelector, textInputLayout, textInputLayout2, e0Var);
                break;
            default:
                rangeDateSelector.f3590e = l7;
                RangeDateSelector.l(rangeDateSelector, textInputLayout, textInputLayout2, e0Var);
                break;
        }
    }
}
