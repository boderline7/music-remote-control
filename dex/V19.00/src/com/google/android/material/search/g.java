package com.google.android.material.search;

import android.view.View;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.j0;
import com.google.android.material.internal.k0;
import p0.b0;
import p0.l2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final /* synthetic */ class g implements b0, j0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SearchView f4089a;

    public /* synthetic */ g(SearchView searchView) {
        this.f4089a = searchView;
    }

    @Override // com.google.android.material.internal.j0
    public final l2 d(View view, l2 l2Var, androidx.media.a aVar) {
        MaterialToolbar materialToolbar = this.f4089a.f4060g;
        boolean f7 = k0.f(materialToolbar);
        materialToolbar.setPadding(l2Var.b() + (f7 ? aVar.f2237c : aVar.f2235a), aVar.f2236b, l2Var.c() + (f7 ? aVar.f2235a : aVar.f2237c), aVar.f2238d);
        return l2Var;
    }

    @Override // p0.b0
    public final l2 e(View view, l2 l2Var) {
        SearchView.e(this.f4089a, l2Var);
        return l2Var;
    }
}
