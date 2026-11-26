package com.navi.tool.ui.extra;

import android.content.Context;
import com.navi.tool.utils.ToastHelper;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class f1 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5151a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f5152b;

    public /* synthetic */ f1(Context context, int i7) {
        this.f5151a = i7;
        this.f5152b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f5151a;
        Context context = this.f5152b;
        switch (i7) {
            case 0:
                ToastHelper.show(context, "当前状态不允许应用座椅配置");
                break;
            case 1:
                DesktopAssistantMusicUiHelper.lambda$refreshMusicStateBySource$0(context);
                break;
            default:
                DesktopAssistantMusicUiHelper.lambda$refreshMusicStateBySource$1(context);
                break;
        }
    }
}
