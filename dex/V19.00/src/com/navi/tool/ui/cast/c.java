package com.navi.tool.ui.cast;

import com.navi.tool.ui.cast.ScreenCastingFragment;
import java.text.Collator;
import java.util.Comparator;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class c implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4978a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Collator f4979b;

    public /* synthetic */ c(Collator collator, int i7) {
        this.f4978a = i7;
        this.f4979b = collator;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int lambda$loadAllLauncherApps$4;
        int lambda$loadLauncherApps$8;
        int i7 = this.f4978a;
        Collator collator = this.f4979b;
        ScreenCastingFragment.AppItem appItem = (ScreenCastingFragment.AppItem) obj;
        ScreenCastingFragment.AppItem appItem2 = (ScreenCastingFragment.AppItem) obj2;
        switch (i7) {
            case 0:
                lambda$loadAllLauncherApps$4 = ScreenCastingFragment.lambda$loadAllLauncherApps$4(collator, appItem, appItem2);
                return lambda$loadAllLauncherApps$4;
            default:
                lambda$loadLauncherApps$8 = ScreenCastingFragment.lambda$loadLauncherApps$8(collator, appItem, appItem2);
                return lambda$loadLauncherApps$8;
        }
    }
}
