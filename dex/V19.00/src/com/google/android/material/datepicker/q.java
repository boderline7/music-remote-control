package com.google.android.material.datepicker;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.w1;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class q extends w1 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d0 f3653a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MaterialButton f3654b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ t f3655c;

    public q(t tVar, d0 d0Var, MaterialButton materialButton) {
        this.f3655c = tVar;
        this.f3653a = d0Var;
        this.f3654b = materialButton;
    }

    @Override // androidx.recyclerview.widget.w1
    public final void a(int i7, RecyclerView recyclerView) {
        if (i7 == 0) {
            recyclerView.announceForAccessibility(this.f3654b.getText());
        }
    }

    @Override // androidx.recyclerview.widget.w1
    public final void b(RecyclerView recyclerView, int i7, int i8) {
        t tVar = this.f3655c;
        int P0 = i7 < 0 ? ((LinearLayoutManager) tVar.f3666j.getLayoutManager()).P0() : ((LinearLayoutManager) tVar.f3666j.getLayoutManager()).Q0();
        d0 d0Var = this.f3653a;
        Calendar c7 = j0.c(d0Var.f3607a.f3564a.f3579a);
        c7.add(2, P0);
        tVar.f3662f = new Month(c7);
        Calendar c8 = j0.c(d0Var.f3607a.f3564a.f3579a);
        c8.add(2, P0);
        this.f3654b.setText(new Month(c8).n());
    }
}
