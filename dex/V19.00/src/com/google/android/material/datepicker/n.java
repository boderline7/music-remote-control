package com.google.android.material.datepicker;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.g2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class n extends LinearLayoutManager {
    public final /* synthetic */ int E;
    public final /* synthetic */ t F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(t tVar, int i7, int i8) {
        super(i7);
        this.F = tVar;
        this.E = i8;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.r1
    public final void C0(RecyclerView recyclerView, int i7) {
        y2.c cVar = new y2.c(this, recyclerView.getContext(), 1);
        cVar.setTargetPosition(i7);
        D0(cVar);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void F0(g2 g2Var, int[] iArr) {
        int i7 = this.E;
        t tVar = this.F;
        if (i7 == 0) {
            iArr[0] = tVar.f3666j.getWidth();
            iArr[1] = tVar.f3666j.getWidth();
        } else {
            iArr[0] = tVar.f3666j.getHeight();
            iArr[1] = tVar.f3666j.getHeight();
        }
    }
}
