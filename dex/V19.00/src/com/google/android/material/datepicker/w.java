package com.google.android.material.datepicker;

import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class w extends e0 {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3675a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.z f3676b;

    public w(androidx.fragment.app.z zVar, int i7) {
        this.f3675a = i7;
        this.f3676b = zVar;
    }

    @Override // com.google.android.material.datepicker.e0
    public final void a() {
        int i7 = this.f3675a;
        androidx.fragment.app.z zVar = this.f3676b;
        switch (i7) {
            case 0:
                ((x) zVar).A.setEnabled(false);
                break;
            default:
                Iterator it = ((y) zVar).f3612a.iterator();
                while (it.hasNext()) {
                    ((e0) it.next()).a();
                }
                break;
        }
    }

    @Override // com.google.android.material.datepicker.e0
    public final void b(Object obj) {
        int i7 = this.f3675a;
        androidx.fragment.app.z zVar = this.f3676b;
        switch (i7) {
            case 0:
                x xVar = (x) zVar;
                String c7 = xVar.e().c(xVar.getContext());
                xVar.f3700x.setContentDescription(xVar.e().b(xVar.requireContext()));
                xVar.f3700x.setText(c7);
                xVar.A.setEnabled(xVar.e().h());
                break;
            default:
                Iterator it = ((y) zVar).f3612a.iterator();
                while (it.hasNext()) {
                    ((e0) it.next()).b(obj);
                }
                break;
        }
    }
}
