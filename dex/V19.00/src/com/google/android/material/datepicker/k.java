package com.google.android.material.datepicker;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.Calendar;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3638a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d0 f3639b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ t f3640c;

    public /* synthetic */ k(t tVar, d0 d0Var, int i7) {
        this.f3638a = i7;
        this.f3640c = tVar;
        this.f3639b = d0Var;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f3638a;
        d0 d0Var = this.f3639b;
        t tVar = this.f3640c;
        switch (i7) {
            case 0:
                int Q0 = ((LinearLayoutManager) tVar.f3666j.getLayoutManager()).Q0() - 1;
                if (Q0 >= 0) {
                    Calendar c7 = j0.c(d0Var.f3607a.f3564a.f3579a);
                    c7.add(2, Q0);
                    tVar.e(new Month(c7));
                    break;
                }
                break;
            default:
                int P0 = ((LinearLayoutManager) tVar.f3666j.getLayoutManager()).P0() + 1;
                if (P0 < tVar.f3666j.getAdapter().getItemCount()) {
                    Calendar c8 = j0.c(d0Var.f3607a.f3564a.f3579a);
                    c8.add(2, P0);
                    tVar.e(new Month(c8));
                    break;
                }
                break;
        }
    }
}
