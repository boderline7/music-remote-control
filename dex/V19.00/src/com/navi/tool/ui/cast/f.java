package com.navi.tool.ui.cast;

import com.navi.tool.ui.cast.ScreenCastingFragment;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4983a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ScreenCastingFragment.SelectAppsAdapter f4984b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4985c;

    public /* synthetic */ f(ScreenCastingFragment.SelectAppsAdapter selectAppsAdapter, List list) {
        this.f4984b = selectAppsAdapter;
        this.f4985c = list;
    }

    public /* synthetic */ f(ScreenCastingFragment screenCastingFragment, ScreenCastingFragment.SelectAppsAdapter selectAppsAdapter) {
        this.f4985c = screenCastingFragment;
        this.f4984b = selectAppsAdapter;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4983a;
        ScreenCastingFragment.SelectAppsAdapter selectAppsAdapter = this.f4984b;
        Object obj = this.f4985c;
        switch (i7) {
            case 0:
                ScreenCastingFragment.lambda$showCustomCastingDialog$2(selectAppsAdapter, (List) obj);
                break;
            default:
                ((ScreenCastingFragment) obj).lambda$showCustomCastingDialog$3(selectAppsAdapter);
                break;
        }
    }
}
