package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class NavigationMenuView extends RecyclerView implements k.e0 {
    public NavigationMenuView(Context context) {
        this(context, null);
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        setLayoutManager(new LinearLayoutManager(1));
    }

    @Override // k.e0
    public final void d(k.o oVar) {
    }

    public int getWindowAnimations() {
        return 0;
    }
}
