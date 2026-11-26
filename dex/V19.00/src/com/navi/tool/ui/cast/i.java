package com.navi.tool.ui.cast;

import android.widget.CompoundButton;
import androidx.recyclerview.widget.h1;
import com.navi.tool.ui.cast.ScreenCastingFragment;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class i implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4990a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ScreenCastingFragment.AppItem f4991b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ h1 f4992c;

    public /* synthetic */ i(h1 h1Var, ScreenCastingFragment.AppItem appItem, int i7) {
        this.f4990a = i7;
        this.f4992c = h1Var;
        this.f4991b = appItem;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
        int i7 = this.f4990a;
        ScreenCastingFragment.AppItem appItem = this.f4991b;
        h1 h1Var = this.f4992c;
        switch (i7) {
            case 0:
                ((ScreenCastingFragment.AppsAdapter) h1Var).lambda$onBindViewHolder$2(appItem, compoundButton, z6);
                break;
            default:
                ((ScreenCastingFragment.SelectAppsAdapter) h1Var).lambda$onBindViewHolder$0(appItem, compoundButton, z6);
                break;
        }
    }
}
