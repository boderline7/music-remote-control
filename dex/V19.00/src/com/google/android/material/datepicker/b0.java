package com.google.android.material.datepicker;

import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b0 implements AdapterView.OnItemClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ MaterialCalendarGridView f3603a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d0 f3604b;

    public b0(d0 d0Var, MaterialCalendarGridView materialCalendarGridView) {
        this.f3604b = d0Var;
        this.f3603a = materialCalendarGridView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i7, long j7) {
        MaterialCalendarGridView materialCalendarGridView = this.f3603a;
        a0 a7 = materialCalendarGridView.a();
        if (i7 < a7.a() || i7 > a7.c()) {
            return;
        }
        s sVar = this.f3604b.f3610d;
        long longValue = materialCalendarGridView.a().getItem(i7).longValue();
        t tVar = ((o) sVar).f3649a;
        if (tVar.f3660d.f3566c.f(longValue)) {
            tVar.f3659c.i(longValue);
            Iterator it = tVar.f3612a.iterator();
            while (it.hasNext()) {
                ((e0) it.next()).b(tVar.f3659c.a());
            }
            tVar.f3666j.getAdapter().notifyDataSetChanged();
            RecyclerView recyclerView = tVar.f3665i;
            if (recyclerView != null) {
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        }
    }
}
