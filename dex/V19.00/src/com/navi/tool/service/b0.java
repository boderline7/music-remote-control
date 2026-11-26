package com.navi.tool.service;

import android.widget.ScrollView;
import com.navi.tool.service.LogMonitorService;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class b0 implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4846a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4847b;

    public /* synthetic */ b0(int i7, Object obj) {
        this.f4846a = i7;
        this.f4847b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i7 = this.f4846a;
        Object obj = this.f4847b;
        switch (i7) {
            case 0:
                ((LogMonitorService.AnonymousClass2) obj).lambda$onReceive$0();
                break;
            case 1:
                ((AutoStartService) obj).lambda$scheduleLaunches$1();
                break;
            case 2:
                ((LogLinkageService) obj).lambda$onCreate$0();
                break;
            case 3:
                ((Throwable) obj).getMessage();
                break;
            case 4:
                ((ScrollView) obj).scrollTo(0, 0);
                break;
            default:
                ((ReturnAppService) obj).lambda$scheduleLaunch$0();
                break;
        }
    }
}
