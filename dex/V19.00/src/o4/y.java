package o4;

import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.tire.api.TireEngine;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class y implements t {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TirePollingService f8049a;

    public y(TirePollingService tirePollingService) {
        this.f8049a = tirePollingService;
    }

    @Override // o4.t
    public final void onError(String str) {
        this.f8049a.f4734e = false;
        this.f8049a.f4735f = System.currentTimeMillis() + 300000;
    }

    @Override // o4.t
    public final void onReady(TireEngine tireEngine) {
        this.f8049a.f4734e = false;
        this.f8049a.f4733d = tireEngine;
        TirePollingService.a(this.f8049a);
    }
}
