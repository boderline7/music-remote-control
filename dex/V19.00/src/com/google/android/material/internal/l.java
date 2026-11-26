package com.google.android.material.internal;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class l extends u {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(int i7, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(q2.i.design_navigation_item_separator, viewGroup, false));
        if (i7 != 2) {
        } else {
            super(layoutInflater.inflate(q2.i.design_navigation_item_subheader, viewGroup, false));
        }
    }
}
