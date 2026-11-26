package com.google.android.material.datepicker;

import android.view.View;
import java.util.Iterator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class v implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3673a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ x f3674b;

    public /* synthetic */ v(x xVar, int i7) {
        this.f3673a = i7;
        this.f3674b = xVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f3673a;
        x xVar = this.f3674b;
        switch (i7) {
            case 0:
                Iterator it = xVar.f3677a.iterator();
                if (!it.hasNext()) {
                    xVar.dismiss();
                    return;
                } else {
                    a.a.u(it.next());
                    xVar.e().a();
                    throw null;
                }
            default:
                Iterator it2 = xVar.f3678b.iterator();
                while (it2.hasNext()) {
                    ((View.OnClickListener) it2.next()).onClick(view);
                }
                xVar.dismiss();
                return;
        }
    }
}
