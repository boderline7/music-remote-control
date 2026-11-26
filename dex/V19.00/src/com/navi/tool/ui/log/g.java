package com.navi.tool.ui.log;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5684a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f5685b;

    public /* synthetic */ g(int i7, RecyclerView recyclerView) {
        this.f5684a = i7;
        this.f5685b = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5684a;
        RecyclerView recyclerView = this.f5685b;
        switch (i7) {
            case 0:
                recyclerView.requestLayout();
                break;
            case 1:
                recyclerView.requestLayout();
                break;
            case 2:
                recyclerView.requestLayout();
                break;
            default:
                recyclerView.requestLayout();
                break;
        }
    }
}
