package com.navi.tool.ui.extra;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.navi.tool.ui.extra.CarControlAdapter;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import java.util.Comparator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w implements Comparator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5526a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5527b;

    public /* synthetic */ w(int i7, Object obj) {
        this.f5526a = i7;
        this.f5527b = obj;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int lambda$fetchAppList$77;
        int lambda$updateCarControlCards$69;
        int lambda$scanInstalledApps$62;
        int i7 = this.f5526a;
        Object obj3 = this.f5527b;
        switch (i7) {
            case 0:
                lambda$fetchAppList$77 = DesktopAssistantActivity.lambda$fetchAppList$77((List) obj3, (DesktopAssistantActivity.AppItem) obj, (DesktopAssistantActivity.AppItem) obj2);
                return lambda$fetchAppList$77;
            case 1:
                lambda$updateCarControlCards$69 = DesktopAssistantCarControlHelper.lambda$updateCarControlCards$69((List) obj3, (CarControlAdapter.CardItem) obj, (CarControlAdapter.CardItem) obj2);
                return lambda$updateCarControlCards$69;
            default:
                lambda$scanInstalledApps$62 = FileManagerActivity.lambda$scanInstalledApps$62((PackageManager) obj3, (PackageInfo) obj, (PackageInfo) obj2);
                return lambda$scanInstalledApps$62;
        }
    }
}
