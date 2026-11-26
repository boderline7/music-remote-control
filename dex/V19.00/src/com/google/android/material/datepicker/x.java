package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.x0;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;
import p0.d1;
import p0.o0;
import p0.p1;
import p0.p2;
import p0.r0;
import p0.r2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class x<S> extends androidx.fragment.app.q {
    public static final int INPUT_MODE_CALENDAR = 0;
    public static final int INPUT_MODE_TEXT = 1;
    public Button A;
    public boolean B;
    public CharSequence C;
    public CharSequence D;

    /* renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f3677a = new LinkedHashSet();

    /* renamed from: b, reason: collision with root package name */
    public final LinkedHashSet f3678b = new LinkedHashSet();

    /* renamed from: c, reason: collision with root package name */
    public final LinkedHashSet f3679c = new LinkedHashSet();

    /* renamed from: d, reason: collision with root package name */
    public final LinkedHashSet f3680d = new LinkedHashSet();

    /* renamed from: e, reason: collision with root package name */
    public int f3681e;

    /* renamed from: f, reason: collision with root package name */
    public DateSelector f3682f;

    /* renamed from: g, reason: collision with root package name */
    public f0 f3683g;

    /* renamed from: h, reason: collision with root package name */
    public CalendarConstraints f3684h;

    /* renamed from: i, reason: collision with root package name */
    public DayViewDecorator f3685i;

    /* renamed from: j, reason: collision with root package name */
    public t f3686j;

    /* renamed from: k, reason: collision with root package name */
    public int f3687k;

    /* renamed from: l, reason: collision with root package name */
    public CharSequence f3688l;

    /* renamed from: m, reason: collision with root package name */
    public boolean f3689m;

    /* renamed from: n, reason: collision with root package name */
    public int f3690n;

    /* renamed from: o, reason: collision with root package name */
    public int f3691o;

    /* renamed from: p, reason: collision with root package name */
    public CharSequence f3692p;

    /* renamed from: q, reason: collision with root package name */
    public int f3693q;

    /* renamed from: r, reason: collision with root package name */
    public CharSequence f3694r;

    /* renamed from: s, reason: collision with root package name */
    public int f3695s;

    /* renamed from: t, reason: collision with root package name */
    public CharSequence f3696t;

    /* renamed from: u, reason: collision with root package name */
    public int f3697u;

    /* renamed from: v, reason: collision with root package name */
    public CharSequence f3698v;

    /* renamed from: w, reason: collision with root package name */
    public TextView f3699w;

    /* renamed from: x, reason: collision with root package name */
    public TextView f3700x;

    /* renamed from: y, reason: collision with root package name */
    public CheckableImageButton f3701y;

    /* renamed from: z, reason: collision with root package name */
    public p3.j f3702z;

    public static int f(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(q2.e.mtrl_calendar_content_padding);
        Month month = new Month(j0.f());
        int dimensionPixelSize = resources.getDimensionPixelSize(q2.e.mtrl_calendar_day_width);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(q2.e.mtrl_calendar_month_horizontal_padding);
        int i7 = month.f3582d;
        return ((i7 - 1) * dimensionPixelOffset2) + (dimensionPixelSize * i7) + (dimensionPixelOffset * 2);
    }

    public static boolean g(Context context, int i7) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(l6.f.w0(context, q2.c.materialCalendarStyle, t.class.getCanonicalName()).data, new int[]{i7});
        boolean z6 = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z6;
    }

    public final DateSelector e() {
        if (this.f3682f == null) {
            this.f3682f = (DateSelector) getArguments().getParcelable("DATE_SELECTOR_KEY");
        }
        return this.f3682f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.fragment.app.z, com.google.android.material.datepicker.y] */
    public final void h() {
        Context requireContext = requireContext();
        int i7 = this.f3681e;
        if (i7 == 0) {
            i7 = e().d(requireContext);
        }
        DateSelector e7 = e();
        CalendarConstraints calendarConstraints = this.f3684h;
        DayViewDecorator dayViewDecorator = this.f3685i;
        t tVar = new t();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i7);
        bundle.putParcelable("GRID_SELECTOR_KEY", e7);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", dayViewDecorator);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.f3567d);
        tVar.setArguments(bundle);
        this.f3686j = tVar;
        if (this.f3690n == 1) {
            DateSelector e8 = e();
            CalendarConstraints calendarConstraints2 = this.f3684h;
            ?? yVar = new y();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("THEME_RES_ID_KEY", i7);
            bundle2.putParcelable("DATE_SELECTOR_KEY", e8);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
            yVar.setArguments(bundle2);
            tVar = yVar;
        }
        this.f3683g = tVar;
        this.f3699w.setText((this.f3690n == 1 && getResources().getConfiguration().orientation == 2) ? this.D : this.C);
        String c7 = e().c(getContext());
        this.f3700x.setContentDescription(e().b(requireContext()));
        this.f3700x.setText(c7);
        x0 childFragmentManager = getChildFragmentManager();
        childFragmentManager.getClass();
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(childFragmentManager);
        int i8 = q2.g.mtrl_calendar_frame;
        f0 f0Var = this.f3683g;
        if (i8 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        aVar.c(i8, f0Var, null, 2);
        if (aVar.f1980g) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        aVar.f1981h = false;
        aVar.f1910q.t(aVar, false);
        this.f3683g.d(new w(this, 0));
    }

    public final void i(CheckableImageButton checkableImageButton) {
        this.f3701y.setContentDescription(this.f3690n == 1 ? checkableImageButton.getContext().getString(q2.k.mtrl_picker_toggle_to_calendar_input_mode) : checkableImageButton.getContext().getString(q2.k.mtrl_picker_toggle_to_text_input_mode));
    }

    @Override // androidx.fragment.app.q, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.f3679c.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.q, androidx.fragment.app.z
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f3681e = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.f3682f = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.f3684h = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f3685i = (DayViewDecorator) bundle.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.f3687k = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.f3688l = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.f3690n = bundle.getInt("INPUT_MODE_KEY");
        this.f3691o = bundle.getInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY");
        this.f3692p = bundle.getCharSequence("POSITIVE_BUTTON_TEXT_KEY");
        this.f3693q = bundle.getInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.f3694r = bundle.getCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        this.f3695s = bundle.getInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY");
        this.f3696t = bundle.getCharSequence("NEGATIVE_BUTTON_TEXT_KEY");
        this.f3697u = bundle.getInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY");
        this.f3698v = bundle.getCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY");
        CharSequence charSequence = this.f3688l;
        if (charSequence == null) {
            charSequence = requireContext().getResources().getText(this.f3687k);
        }
        this.C = charSequence;
        if (charSequence != null) {
            CharSequence[] split = TextUtils.split(String.valueOf(charSequence), "\n");
            if (split.length > 1) {
                charSequence = split[0];
            }
        } else {
            charSequence = null;
        }
        this.D = charSequence;
    }

    @Override // androidx.fragment.app.q
    public final Dialog onCreateDialog(Bundle bundle) {
        Context requireContext = requireContext();
        Context requireContext2 = requireContext();
        int i7 = this.f3681e;
        if (i7 == 0) {
            i7 = e().d(requireContext2);
        }
        Dialog dialog = new Dialog(requireContext, i7);
        Context context = dialog.getContext();
        this.f3689m = g(context, R.attr.windowFullscreen);
        this.f3702z = new p3.j(context, null, q2.c.materialCalendarStyle, q2.l.Widget_MaterialComponents_MaterialCalendar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, q2.m.MaterialCalendar, q2.c.materialCalendarStyle, q2.l.Widget_MaterialComponents_MaterialCalendar);
        int color = obtainStyledAttributes.getColor(q2.m.MaterialCalendar_backgroundTint, 0);
        obtainStyledAttributes.recycle();
        this.f3702z.l(context);
        this.f3702z.o(ColorStateList.valueOf(color));
        p3.j jVar = this.f3702z;
        View decorView = dialog.getWindow().getDecorView();
        int i8 = d1.OVER_SCROLL_ALWAYS;
        jVar.n(r0.i(decorView));
        return dialog;
    }

    @Override // androidx.fragment.app.z
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(this.f3689m ? q2.i.mtrl_picker_fullscreen : q2.i.mtrl_picker_dialog, viewGroup);
        Context context = inflate.getContext();
        DayViewDecorator dayViewDecorator = this.f3685i;
        if (dayViewDecorator != null) {
            dayViewDecorator.getClass();
        }
        if (this.f3689m) {
            inflate.findViewById(q2.g.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(f(context), -2));
        } else {
            inflate.findViewById(q2.g.mtrl_calendar_main_pane).setLayoutParams(new LinearLayout.LayoutParams(f(context), -1));
        }
        TextView textView = (TextView) inflate.findViewById(q2.g.mtrl_picker_header_selection_text);
        this.f3700x = textView;
        int i7 = d1.OVER_SCROLL_ALWAYS;
        int i8 = 1;
        o0.f(textView, 1);
        this.f3701y = (CheckableImageButton) inflate.findViewById(q2.g.mtrl_picker_header_toggle);
        this.f3699w = (TextView) inflate.findViewById(q2.g.mtrl_picker_title_text);
        this.f3701y.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.f3701y;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, l6.f.A(context, q2.f.material_ic_calendar_black_24dp));
        int i9 = 0;
        stateListDrawable.addState(new int[0], l6.f.A(context, q2.f.material_ic_edit_black_24dp));
        checkableImageButton.setImageDrawable(stateListDrawable);
        this.f3701y.setChecked(this.f3690n != 0);
        d1.o(this.f3701y, null);
        i(this.f3701y);
        this.f3701y.setOnClickListener(new u(i9, this));
        this.A = (Button) inflate.findViewById(q2.g.confirm_button);
        if (e().h()) {
            this.A.setEnabled(true);
        } else {
            this.A.setEnabled(false);
        }
        this.A.setTag("CONFIRM_BUTTON_TAG");
        CharSequence charSequence = this.f3692p;
        if (charSequence != null) {
            this.A.setText(charSequence);
        } else {
            int i10 = this.f3691o;
            if (i10 != 0) {
                this.A.setText(i10);
            }
        }
        CharSequence charSequence2 = this.f3694r;
        if (charSequence2 != null) {
            this.A.setContentDescription(charSequence2);
        } else if (this.f3693q != 0) {
            this.A.setContentDescription(getContext().getResources().getText(this.f3693q));
        }
        this.A.setOnClickListener(new v(this, i9));
        Button button = (Button) inflate.findViewById(q2.g.cancel_button);
        button.setTag("CANCEL_BUTTON_TAG");
        CharSequence charSequence3 = this.f3696t;
        if (charSequence3 != null) {
            button.setText(charSequence3);
        } else {
            int i11 = this.f3695s;
            if (i11 != 0) {
                button.setText(i11);
            }
        }
        CharSequence charSequence4 = this.f3698v;
        if (charSequence4 != null) {
            button.setContentDescription(charSequence4);
        } else if (this.f3697u != 0) {
            button.setContentDescription(getContext().getResources().getText(this.f3697u));
        }
        button.setOnClickListener(new v(this, i8));
        return inflate;
    }

    @Override // androidx.fragment.app.q, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.f3680d.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.q, androidx.fragment.app.z
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.f3681e);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.f3682f);
        CalendarConstraints calendarConstraints = this.f3684h;
        b bVar = new b();
        int i7 = b.f3600c;
        int i8 = b.f3600c;
        long j7 = calendarConstraints.f3564a.f3584f;
        long j8 = calendarConstraints.f3565b.f3584f;
        bVar.f3601a = Long.valueOf(calendarConstraints.f3567d.f3584f);
        int i9 = calendarConstraints.f3568e;
        CalendarConstraints.DateValidator dateValidator = calendarConstraints.f3566c;
        bVar.f3602b = dateValidator;
        t tVar = this.f3686j;
        Month month = tVar == null ? null : tVar.f3662f;
        if (month != null) {
            bVar.f3601a = Long.valueOf(month.f3584f);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", dateValidator);
        Month m6 = Month.m(j7);
        Month m7 = Month.m(j8);
        CalendarConstraints.DateValidator dateValidator2 = (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY");
        Long l7 = bVar.f3601a;
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(m6, m7, dateValidator2, l7 == null ? null : Month.m(l7.longValue()), i9));
        bundle.putParcelable("DAY_VIEW_DECORATOR_KEY", this.f3685i);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.f3687k);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.f3688l);
        bundle.putInt("INPUT_MODE_KEY", this.f3690n);
        bundle.putInt("POSITIVE_BUTTON_TEXT_RES_ID_KEY", this.f3691o);
        bundle.putCharSequence("POSITIVE_BUTTON_TEXT_KEY", this.f3692p);
        bundle.putInt("POSITIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.f3693q);
        bundle.putCharSequence("POSITIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.f3694r);
        bundle.putInt("NEGATIVE_BUTTON_TEXT_RES_ID_KEY", this.f3695s);
        bundle.putCharSequence("NEGATIVE_BUTTON_TEXT_KEY", this.f3696t);
        bundle.putInt("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_RES_ID_KEY", this.f3697u);
        bundle.putCharSequence("NEGATIVE_BUTTON_CONTENT_DESCRIPTION_KEY", this.f3698v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.q, androidx.fragment.app.z
    public final void onStart() {
        p2 p2Var;
        p2 p2Var2;
        WindowInsetsController insetsController;
        WindowInsetsController insetsController2;
        super.onStart();
        Window window = requireDialog().getWindow();
        if (this.f3689m) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.f3702z);
            if (!this.B) {
                View findViewById = requireView().findViewById(q2.g.fullscreen_header);
                ColorStateList d7 = d3.a.d(findViewById.getBackground());
                Integer valueOf = d7 != null ? Integer.valueOf(d7.getDefaultColor()) : null;
                int i7 = Build.VERSION.SDK_INT;
                boolean z6 = false;
                boolean z7 = valueOf == null || valueOf.intValue() == 0;
                int c7 = b3.a.c(window.getContext(), R.attr.colorBackground, d1.MEASURED_STATE_MASK);
                if (z7) {
                    valueOf = Integer.valueOf(c7);
                }
                Integer valueOf2 = Integer.valueOf(c7);
                p1.a(window, false);
                window.getContext();
                int e7 = i7 < 27 ? i0.a.e(b3.a.c(window.getContext(), R.attr.navigationBarColor, d1.MEASURED_STATE_MASK), 128) : 0;
                window.setStatusBarColor(0);
                window.setNavigationBarColor(e7);
                boolean z8 = b3.a.e(0) || b3.a.e(valueOf.intValue());
                View decorView = window.getDecorView();
                if (Build.VERSION.SDK_INT >= 30) {
                    insetsController2 = window.getInsetsController();
                    r2 r2Var = new r2(insetsController2);
                    r2Var.f8182f = window;
                    p2Var = r2Var;
                } else {
                    p2Var = new p2(window, decorView);
                }
                p2Var.F(z8);
                boolean e8 = b3.a.e(valueOf2.intValue());
                if (b3.a.e(e7) || (e7 == 0 && e8)) {
                    z6 = true;
                }
                View decorView2 = window.getDecorView();
                if (Build.VERSION.SDK_INT >= 30) {
                    insetsController = window.getInsetsController();
                    r2 r2Var2 = new r2(insetsController);
                    r2Var2.f8182f = window;
                    p2Var2 = r2Var2;
                } else {
                    p2Var2 = new p2(window, decorView2);
                }
                p2Var2.E(z6);
                d.i iVar = new d.i(this, findViewById.getLayoutParams().height, findViewById, findViewById.getPaddingTop());
                int i8 = d1.OVER_SCROLL_ALWAYS;
                r0.u(findViewById, iVar);
                this.B = true;
            }
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(q2.e.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.f3702z, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new c3.a(requireDialog(), rect));
        }
        h();
    }

    @Override // androidx.fragment.app.q, androidx.fragment.app.z
    public final void onStop() {
        this.f3683g.f3612a.clear();
        super.onStop();
    }
}
