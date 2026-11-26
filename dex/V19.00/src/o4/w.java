package o4;

import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.model.LogMediaProfile;
import com.navi.tool.receiver.PackageInstallReceiver;
import com.navi.tool.service.LogLinkageManager;
import com.navi.tool.service.MobileHudBluetoothServer;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.tire.api.TireEngine;
import com.navi.tool.tire.api.TireSnapshot;
import com.navi.tool.ui.extra.t1;
import java.io.DataInputStream;
import java.io.DataOutputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final /* synthetic */ class w implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8041a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8042b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f8043c;

    /* renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f8044d;

    /* renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f8045e;

    public /* synthetic */ w(Context context, String str, String str2, BroadcastReceiver.PendingResult pendingResult) {
        this.f8041a = 1;
        this.f8042b = context;
        this.f8043c = str;
        this.f8044d = str2;
        this.f8045e = pendingResult;
    }

    public /* synthetic */ w(Object obj, Object obj2, Object obj3, Object obj4, int i7) {
        this.f8041a = i7;
        this.f8043c = obj;
        this.f8044d = obj2;
        this.f8042b = obj3;
        this.f8045e = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f8041a) {
            case 0:
                TireEngine tireEngine = (TireEngine) this.f8043c;
                TireAccount tireAccount = (TireAccount) this.f8044d;
                Context context = (Context) this.f8042b;
                t1 t1Var = (t1) this.f8045e;
                try {
                    TirePollingService.f4728j = System.currentTimeMillis();
                    TireSnapshot fetch = tireEngine.fetch(tireAccount);
                    TirePollingService.c(context, fetch);
                    z.j(context, System.currentTimeMillis());
                    z.h(context);
                    TirePollingService tirePollingService = TirePollingService.f4729k;
                    if (tirePollingService != null) {
                        tirePollingService.f4735f = 0L;
                    }
                    TirePollingService.b(t1Var, true, fetch.online, "胎压已更新");
                } catch (Throwable th) {
                    try {
                        String th2 = th.getMessage() == null ? th.toString() : th.getMessage();
                        if (!th2.contains("登录已过期") && !th2.contains("401") && !th2.contains("Unauthorized")) {
                            TirePollingService.b(t1Var, false, false, "拉取失败：".concat(th2));
                        }
                        z.g(context);
                        z.b(context);
                        TirePollingService.e(context);
                        TirePollingService.b(t1Var, false, false, "登录已过期，请重新登录");
                    } catch (Throwable th3) {
                        TirePollingService.f4726h.set(false);
                        throw th3;
                    }
                }
                TirePollingService.f4726h.set(false);
                return;
            case 1:
                PackageInstallReceiver.lambda$onReceive$1((Context) this.f8042b, (String) this.f8043c, (String) this.f8044d, (BroadcastReceiver.PendingResult) this.f8045e);
                return;
            case 2:
                ((LogLinkageManager) this.f8043c).lambda$dispatchMediaDecision$3((LogMediaProfile) this.f8044d, (String) this.f8042b, (String) this.f8045e);
                return;
            case 3:
                ((MobileHudBluetoothServer) this.f8043c).lambda$connectLoop$2((BluetoothSocket) this.f8044d, (DataInputStream) this.f8042b, (String) this.f8045e);
                return;
            default:
                q4.h hVar = (q4.h) this.f8043c;
                String str = (String) this.f8044d;
                String str2 = (String) this.f8042b;
                String str3 = (String) this.f8045e;
                hVar.getClass();
                synchronized (("hud".equals(str) ? hVar.f8477m : hVar.f8476l)) {
                    try {
                        DataOutputStream e7 = hVar.e(str2, str);
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("cmd", "send_text");
                        if ("hud".equals(str)) {
                            jsonObject.addProperty("service", "hud");
                        }
                        jsonObject.addProperty("text", str3);
                        e7.writeUTF(hVar.f8468d.toJson((JsonElement) jsonObject));
                        e7.flush();
                    } catch (Exception e8) {
                        hVar.c(str);
                        hVar.i(str2, str);
                        if ("general".equals(str)) {
                            hVar.j("Send text error: " + e8.getMessage());
                        } else {
                            hVar.f8466b.post(new q4.d(hVar, "hud", str2, 2));
                        }
                    } finally {
                    }
                }
                return;
        }
    }
}
