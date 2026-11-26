package com.google.android.material.internal;

import android.view.SubMenu;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class k extends k.o {
    @Override // k.o, android.view.Menu
    public final SubMenu addSubMenu(int i7, int i8, int i9, CharSequence charSequence) {
        k.q a7 = a(i7, i8, i9, charSequence);
        w wVar = new w(this.f7139a, this, a7);
        a7.f7180o = wVar;
        wVar.setHeaderTitle(a7.f7170e);
        return wVar;
    }
}
