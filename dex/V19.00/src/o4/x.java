package o4;

import android.content.Context;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.tire.api.TireEngine;
import com.navi.tool.ui.extra.t1;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class x implements t {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TireAccount f8046a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Context f8047b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ t1 f8048c;

    public x(TireAccount tireAccount, Context context, t1 t1Var) {
        this.f8046a = tireAccount;
        this.f8047b = context;
        this.f8048c = t1Var;
    }

    @Override // o4.t
    public final void onError(String str) {
        TirePollingService.f4726h.set(false);
        TirePollingService.b(this.f8048c, false, false, "模块加载失败：".concat(str));
    }

    @Override // o4.t
    public final void onReady(TireEngine tireEngine) {
        TirePollingService.f4727i.execute(new w(tireEngine, this.f8046a, this.f8047b, this.f8048c, 0));
    }
}
