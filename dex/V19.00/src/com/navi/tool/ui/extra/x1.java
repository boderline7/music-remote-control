package com.navi.tool.ui.extra;

import androidx.activity.result.ActivityResult;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class x1 implements d.a {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5552a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DesktopAssistantFragment f5553b;

    public /* synthetic */ x1(DesktopAssistantFragment desktopAssistantFragment, int i7) {
        this.f5552a = i7;
        this.f5553b = desktopAssistantFragment;
    }

    @Override // d.a
    public final void a(Object obj) {
        int i7 = this.f5552a;
        DesktopAssistantFragment desktopAssistantFragment = this.f5553b;
        ActivityResult activityResult = (ActivityResult) obj;
        switch (i7) {
            case 0:
                desktopAssistantFragment.lambda$onViewCreated$0(activityResult);
                break;
            case 1:
                desktopAssistantFragment.lambda$onViewCreated$1(activityResult);
                break;
            case 2:
                desktopAssistantFragment.lambda$onViewCreated$2(activityResult);
                break;
            case 3:
                desktopAssistantFragment.lambda$onViewCreated$3(activityResult);
                break;
            default:
                desktopAssistantFragment.lambda$initIconChooser$6(activityResult);
                break;
        }
    }
}
