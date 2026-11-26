package com.google.android.material.datepicker;

import android.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.f1;
import com.google.android.material.button.MaterialButton;
import p0.d1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class t<S> extends f0 {

    /* renamed from: o, reason: collision with root package name */
    public static final /* synthetic */ int f3657o = 0;

    /* renamed from: b, reason: collision with root package name */
    public int f3658b;

    /* renamed from: c, reason: collision with root package name */
    public DateSelector f3659c;

    /* renamed from: d, reason: collision with root package name */
    public CalendarConstraints f3660d;

    /* renamed from: e, reason: collision with root package name */
    public DayViewDecorator f3661e;

    /* renamed from: f, reason: collision with root package name */
    public Month f3662f;

    /* renamed from: g, reason: collision with root package name */
    public r f3663g;

    /* renamed from: h, reason: collision with root package name */
    public android.support.v4.media.p f3664h;

    /* renamed from: i, reason: collision with root package name */
    public RecyclerView f3665i;

    /* renamed from: j, reason: collision with root package name */
    public RecyclerView f3666j;

    /* renamed from: k, reason: collision with root package name */
    public View f3667k;

    /* renamed from: l, reason: collision with root package name */
    public View f3668l;

    /* renamed from: m, reason: collision with root package name */
    public View f3669m;

    /* renamed from: n, reason: collision with root package name */
    public View f3670n;

    @Override // com.google.android.material.datepicker.f0
    public final void d(w wVar) {
        this.f3612a.add(wVar);
    }

    public final void e(Month month) {
        d0 d0Var = (d0) this.f3666j.getAdapter();
        int o6 = d0Var.f3607a.f3564a.o(month);
        int o7 = o6 - d0Var.f3607a.f3564a.o(this.f3662f);
        boolean z6 = Math.abs(o7) > 3;
        boolean z7 = o7 > 0;
        this.f3662f = month;
        if (z6 && z7) {
            this.f3666j.e0(o6 - 3);
            this.f3666j.post(new l(this, o6));
        } else if (!z6) {
            this.f3666j.post(new l(this, o6));
        } else {
            this.f3666j.e0(o6 + 3);
            this.f3666j.post(new l(this, o6));
        }
    }

    public final void f(r rVar) {
        this.f3663g = rVar;
        if (rVar == r.YEAR) {
            this.f3665i.getLayoutManager().s0(this.f3662f.f3581c - ((m0) this.f3665i.getAdapter()).f3648a.f3660d.f3564a.f3581c);
            this.f3669m.setVisibility(0);
            this.f3670n.setVisibility(8);
            this.f3667k.setVisibility(8);
            this.f3668l.setVisibility(8);
            return;
        }
        if (rVar == r.DAY) {
            this.f3669m.setVisibility(8);
            this.f3670n.setVisibility(0);
            this.f3667k.setVisibility(0);
            this.f3668l.setVisibility(0);
            e(this.f3662f);
        }
    }

    @Override // androidx.fragment.app.z
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f3658b = bundle.getInt("THEME_RES_ID_KEY");
        this.f3659c = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f3660d = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f3661e = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.f3662f = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.z
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i7;
        int i8;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f3658b);
        this.f3664h = new android.support.v4.media.p(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.f3660d.f3564a;
        int i9 = 0;
        int i10 = 1;
        if (x.g(contextThemeWrapper, R.attr.windowFullscreen)) {
            i7 = q2.i.mtrl_calendar_vertical;
            i8 = 1;
        } else {
            i7 = q2.i.mtrl_calendar_horizontal;
            i8 = 0;
        }
        View inflate = cloneInContext.inflate(i7, viewGroup, false);
        Resources resources = requireContext().getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(q2.e.mtrl_calendar_navigation_bottom_padding) + resources.getDimensionPixelOffset(q2.e.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelSize(q2.e.mtrl_calendar_navigation_height);
        int dimensionPixelSize = resources.getDimensionPixelSize(q2.e.mtrl_calendar_days_of_week_height);
        int i11 = a0.f3592g;
        inflate.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(q2.e.mtrl_calendar_month_vertical_padding) * (i11 - 1)) + (resources.getDimensionPixelSize(q2.e.mtrl_calendar_day_height) * i11) + resources.getDimensionPixelOffset(q2.e.mtrl_calendar_bottom_padding));
        GridView gridView = (GridView) inflate.findViewById(q2.g.mtrl_calendar_days_of_week);
        d1.o(gridView, new m(i9, this));
        int i12 = this.f3660d.f3568e;
        gridView.setAdapter((ListAdapter) (i12 > 0 ? new j(i12) : new j()));
        gridView.setNumColumns(month.f3582d);
        gridView.setEnabled(false);
        this.f3666j = (RecyclerView) inflate.findViewById(q2.g.mtrl_calendar_months);
        getContext();
        this.f3666j.setLayoutManager(new n(this, i8, i8));
        this.f3666j.setTag("MONTHS_VIEW_GROUP_TAG");
        d0 d0Var = new d0(contextThemeWrapper, this.f3659c, this.f3660d, this.f3661e, new o(this));
        this.f3666j.setAdapter(d0Var);
        int integer = contextThemeWrapper.getResources().getInteger(q2.h.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(q2.g.mtrl_calendar_year_selector_frame);
        this.f3665i = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.f3665i.setLayoutManager(new GridLayoutManager(integer, 0));
            this.f3665i.setAdapter(new m0(this));
            this.f3665i.g(new p(this));
        }
        if (inflate.findViewById(q2.g.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) inflate.findViewById(q2.g.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            d1.o(materialButton, new m(2, this));
            View findViewById = inflate.findViewById(q2.g.month_navigation_previous);
            this.f3667k = findViewById;
            findViewById.setTag("NAVIGATION_PREV_TAG");
            View findViewById2 = inflate.findViewById(q2.g.month_navigation_next);
            this.f3668l = findViewById2;
            findViewById2.setTag("NAVIGATION_NEXT_TAG");
            this.f3669m = inflate.findViewById(q2.g.mtrl_calendar_year_selector_frame);
            this.f3670n = inflate.findViewById(q2.g.mtrl_calendar_day_selector_frame);
            f(r.DAY);
            materialButton.setText(this.f3662f.n());
            this.f3666j.h(new q(this, d0Var, materialButton));
            materialButton.setOnClickListener(new androidx.appcompat.app.c(4, this));
            this.f3668l.setOnClickListener(new k(this, d0Var, i10));
            this.f3667k.setOnClickListener(new k(this, d0Var, i9));
        }
        if (!x.g(contextThemeWrapper, R.attr.windowFullscreen)) {
            new f1().a(this.f3666j);
        }
        this.f3666j.e0(d0Var.f3607a.f3564a.o(this.f3662f));
        d1.o(this.f3666j, new m(i10, this));
        return inflate;
    }

    @Override // androidx.fragment.app.z
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f3658b);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f3659c);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f3660d);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.f3661e);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f3662f);
    }
}
