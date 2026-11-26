package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.j2;
import com.google.android.material.timepicker.TimeModel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class m0 extends h1 {

    /* renamed from: a, reason: collision with root package name */
    public final t f3648a;

    public m0(t tVar) {
        this.f3648a = tVar;
    }

    @Override // androidx.recyclerview.widget.h1
    public final int getItemCount() {
        return this.f3648a.f3660d.f3569f;
    }

    @Override // androidx.recyclerview.widget.h1
    public final void onBindViewHolder(j2 j2Var, int i7) {
        l0 l0Var = (l0) j2Var;
        t tVar = this.f3648a;
        int i8 = tVar.f3660d.f3564a.f3581c + i7;
        l0Var.f3645a.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(i8)));
        TextView textView = l0Var.f3645a;
        Context context = textView.getContext();
        textView.setContentDescription(j0.f().get(1) == i8 ? String.format(context.getString(q2.k.mtrl_picker_navigate_to_current_year_description), Integer.valueOf(i8)) : String.format(context.getString(q2.k.mtrl_picker_navigate_to_year_description), Integer.valueOf(i8)));
        android.support.v4.media.p pVar = tVar.f3664h;
        Calendar f7 = j0.f();
        androidx.appcompat.widget.w wVar = (androidx.appcompat.widget.w) (f7.get(1) == i8 ? pVar.f557f : pVar.f555d);
        Iterator it = tVar.f3659c.k().iterator();
        while (it.hasNext()) {
            f7.setTimeInMillis(((Long) it.next()).longValue());
            if (f7.get(1) == i8) {
                wVar = (androidx.appcompat.widget.w) pVar.f556e;
            }
        }
        wVar.n(textView);
        textView.setOnClickListener(new k0(this, i8));
    }

    @Override // androidx.recyclerview.widget.h1
    public final j2 onCreateViewHolder(ViewGroup viewGroup, int i7) {
        return new l0((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(q2.i.mtrl_calendar_year, viewGroup, false));
    }
}
