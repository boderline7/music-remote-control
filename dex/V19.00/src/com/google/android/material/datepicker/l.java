package com.google.android.material.datepicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.r1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3643a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ t f3644b;

    public l(t tVar, int i7) {
        this.f3644b = tVar;
        this.f3643a = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        r1 r1Var;
        RecyclerView recyclerView = this.f3644b.f3666j;
        if (recyclerView.f2339v || (r1Var = recyclerView.f2321m) == null) {
            return;
        }
        r1Var.C0(recyclerView, this.f3643a);
    }
}
