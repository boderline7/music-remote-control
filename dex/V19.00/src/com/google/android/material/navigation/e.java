package com.google.android.material.navigation;

import android.content.Context;
import android.view.SubMenu;
import k.o;
import k.q;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class e extends o {
    public final int A;

    /* renamed from: z, reason: collision with root package name */
    public final Class f4011z;

    public e(Context context, Class cls, int i7) {
        super(context);
        this.f4011z = cls;
        this.A = i7;
    }

    @Override // k.o
    public final q a(int i7, int i8, int i9, CharSequence charSequence) {
        int size = this.f7144f.size() + 1;
        int i10 = this.A;
        if (size <= i10) {
            y();
            q a7 = super.a(i7, i8, i9, charSequence);
            a7.g(true);
            x();
            return a7;
        }
        String simpleName = this.f4011z.getSimpleName();
        StringBuilder sb = new StringBuilder("Maximum number of items supported by ");
        sb.append(simpleName);
        sb.append(" is ");
        sb.append(i10);
        sb.append(". Limit can be checked with ");
        throw new IllegalArgumentException(a.a.i(sb, simpleName, "#getMaxItemCount()"));
    }

    @Override // k.o, android.view.Menu
    public final SubMenu addSubMenu(int i7, int i8, int i9, CharSequence charSequence) {
        throw new UnsupportedOperationException(this.f4011z.getSimpleName().concat(" does not support submenus"));
    }
}
