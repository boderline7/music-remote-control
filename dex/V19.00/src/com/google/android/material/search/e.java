package com.google.android.material.search;

import android.view.View;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class e implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4084a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SearchView f4085b;

    public /* synthetic */ e(SearchView searchView, int i7) {
        this.f4084a = i7;
        this.f4085b = searchView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f4084a;
        SearchView searchView = this.f4085b;
        switch (i7) {
            case 0:
                int i8 = SearchView.D;
                searchView.g();
                break;
            case 1:
                int i9 = SearchView.D;
                searchView.l();
                break;
            default:
                searchView.f4063j.setText(h0.FRAGMENT_ENCODE_SET);
                searchView.j();
                break;
        }
    }
}
