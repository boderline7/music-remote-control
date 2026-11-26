package com.google.android.material.internal;

import android.view.View;
import p0.d1;
import p0.l2;
import p0.m0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class h0 implements j0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f3865a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f3866b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f3867c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ j0 f3868d;

    public h0(boolean z6, boolean z7, boolean z8, com.google.android.material.bottomappbar.b bVar) {
        this.f3865a = z6;
        this.f3866b = z7;
        this.f3867c = z8;
        this.f3868d = bVar;
    }

    @Override // com.google.android.material.internal.j0
    public final l2 d(View view, l2 l2Var, androidx.media.a aVar) {
        if (this.f3865a) {
            aVar.f2238d = l2Var.a() + aVar.f2238d;
        }
        boolean f7 = k0.f(view);
        if (this.f3866b) {
            if (f7) {
                aVar.f2237c = l2Var.b() + aVar.f2237c;
            } else {
                aVar.f2235a = l2Var.b() + aVar.f2235a;
            }
        }
        if (this.f3867c) {
            if (f7) {
                aVar.f2235a = l2Var.c() + aVar.f2235a;
            } else {
                aVar.f2237c = l2Var.c() + aVar.f2237c;
            }
        }
        int i7 = aVar.f2235a;
        int i8 = aVar.f2236b;
        int i9 = aVar.f2237c;
        int i10 = aVar.f2238d;
        int i11 = d1.OVER_SCROLL_ALWAYS;
        m0.k(view, i7, i8, i9, i10);
        j0 j0Var = this.f3868d;
        return j0Var != null ? j0Var.d(view, l2Var, aVar) : l2Var;
    }
}
