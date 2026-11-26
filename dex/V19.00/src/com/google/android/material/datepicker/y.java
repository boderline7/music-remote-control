package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class y<S> extends f0 {

    /* renamed from: b, reason: collision with root package name */
    public int f3703b;

    /* renamed from: c, reason: collision with root package name */
    public DateSelector f3704c;

    /* renamed from: d, reason: collision with root package name */
    public CalendarConstraints f3705d;

    @Override // androidx.fragment.app.z
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f3703b = bundle.getInt("THEME_RES_ID_KEY");
        this.f3704c = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.f3705d = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // androidx.fragment.app.z
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f3704c.g(layoutInflater.cloneInContext(new ContextThemeWrapper(getContext(), this.f3703b)), viewGroup, this.f3705d, new w(this, 1));
    }

    @Override // androidx.fragment.app.z
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f3703b);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.f3704c);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f3705d);
    }
}
