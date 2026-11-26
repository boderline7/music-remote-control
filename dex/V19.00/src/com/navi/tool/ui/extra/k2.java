package com.navi.tool.ui.extra;

import android.content.DialogInterface;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class k2 implements DialogInterface.OnDismissListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5275a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5276b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean[] f5277c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ List f5278d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f5279e;

    public /* synthetic */ k2(DesktopAssistantFragment desktopAssistantFragment, boolean[] zArr, ArrayList arrayList, String str, int i7) {
        this.f5275a = i7;
        this.f5276b = desktopAssistantFragment;
        this.f5277c = zArr;
        this.f5278d = arrayList;
        this.f5279e = str;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        int i7 = this.f5275a;
        boolean[] zArr = this.f5277c;
        DesktopAssistantFragment desktopAssistantFragment = this.f5276b;
        String str = this.f5279e;
        List list = this.f5278d;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$showManageVideoWallpapersDialog$73(zArr, list, str, dialogInterface);
                break;
            default:
                desktopAssistantFragment.lambda$showManageWallpapersDialog$135(zArr, list, str, dialogInterface);
                break;
        }
    }
}
