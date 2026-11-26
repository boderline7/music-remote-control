package com.navi.tool.ui.extra;

import android.view.View;
import java.util.HashSet;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class q1 implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5413a = 0;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantCarControlHelper f5414b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Set f5415c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.appcompat.app.o f5416d;

    public /* synthetic */ q1(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, androidx.appcompat.app.o oVar, HashSet hashSet) {
        this.f5414b = desktopAssistantCarControlHelper;
        this.f5416d = oVar;
        this.f5415c = hashSet;
    }

    public /* synthetic */ q1(DesktopAssistantCarControlHelper desktopAssistantCarControlHelper, HashSet hashSet, androidx.appcompat.app.o oVar) {
        this.f5414b = desktopAssistantCarControlHelper;
        this.f5415c = hashSet;
        this.f5416d = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i7 = this.f5413a;
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5414b;
        androidx.appcompat.app.o oVar = this.f5416d;
        Set set = this.f5415c;
        switch (i7) {
            case 0:
                desktopAssistantCarControlHelper.lambda$showSeatPanelActionDialog$52(oVar, set, view);
                break;
            default:
                desktopAssistantCarControlHelper.lambda$showSeatPanelActionDialog$53(set, oVar, view);
                break;
        }
    }
}
