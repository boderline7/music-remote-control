package com.navi.tool.ui.extra;

import android.view.View;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class g0 implements View.OnLongClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5171a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5172b;

    public /* synthetic */ g0(int i7, Object obj) {
        this.f5171a = i7;
        this.f5172b = obj;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        boolean lambda$setupTwoViewInteraction$102;
        boolean lambda$initViews$55;
        boolean lambda$initViews$56;
        boolean lambda$initViews$5;
        int i7 = this.f5171a;
        Object obj = this.f5172b;
        switch (i7) {
            case 0:
                lambda$setupTwoViewInteraction$102 = ((DesktopAssistantActivity) obj).lambda$setupTwoViewInteraction$102(view);
                return lambda$setupTwoViewInteraction$102;
            case 1:
                lambda$initViews$55 = ((DesktopAssistantActivity) obj).lambda$initViews$55(view);
                return lambda$initViews$55;
            case 2:
                lambda$initViews$56 = ((DesktopAssistantActivity) obj).lambda$initViews$56(view);
                return lambda$initViews$56;
            default:
                lambda$initViews$5 = ((DesktopAssistantFragment) obj).lambda$initViews$5(view);
                return lambda$initViews$5;
        }
    }
}
