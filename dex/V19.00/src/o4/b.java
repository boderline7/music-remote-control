package o4;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.navi.tool.service.ConfigBackupService;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class b {

    /* renamed from: l, reason: collision with root package name */
    public static b f7921l;

    /* renamed from: a, reason: collision with root package name */
    public final Context f7922a;

    /* renamed from: c, reason: collision with root package name */
    public f2.c f7924c;

    /* renamed from: d, reason: collision with root package name */
    public boolean f7925d = false;

    /* renamed from: e, reason: collision with root package name */
    public boolean f7926e = false;

    /* renamed from: f, reason: collision with root package name */
    public boolean f7927f = false;

    /* renamed from: g, reason: collision with root package name */
    public long f7928g = 0;

    /* renamed from: h, reason: collision with root package name */
    public final android.support.v4.media.j f7929h = new android.support.v4.media.j(1, this);

    /* renamed from: i, reason: collision with root package name */
    public final a f7930i = new a(this, 0);

    /* renamed from: j, reason: collision with root package name */
    public final a f7931j = new a(this, 1);

    /* renamed from: k, reason: collision with root package name */
    public final e0.f f7932k = new e0.f(9, this);

    /* renamed from: b, reason: collision with root package name */
    public final Handler f7923b = new Handler(Looper.getMainLooper());

    public b(Context context) {
        this.f7922a = context;
    }

    public static synchronized b b(Context context) {
        b bVar;
        synchronized (b.class) {
            try {
                if (f7921l == null) {
                    f7921l = new b(context.getApplicationContext());
                }
                bVar = f7921l;
            } catch (Throwable th) {
                throw th;
            }
        }
        return bVar;
    }

    public final void a() {
        a aVar = this.f7930i;
        Handler handler = this.f7923b;
        try {
            Intent intent = new Intent();
            intent.setAction("com.autonavi.amapauto.aidl.json_protocol_service");
            intent.setClassName("com.autonavi.amapcar", "com.autonavi.amapauto.protocol.service.JsonProtocolService");
            if (this.f7922a.bindService(intent, this.f7929h, 1)) {
                handler.postDelayed(this.f7931j, 10000L);
            } else if (this.f7926e) {
                handler.postDelayed(aVar, 5000L);
            }
        } catch (Exception e7) {
            e7.getMessage();
            if (this.f7926e) {
                handler.postDelayed(aVar, 5000L);
            }
        }
    }

    public final void c(String str) {
        if (str == null || str.isEmpty() || "--".equals(str)) {
            return;
        }
        try {
            float parseFloat = Float.parseFloat(str);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.f7928g < 200) {
                return;
            }
            this.f7928g = elapsedRealtime;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("requestCode", h0.FRAGMENT_ENCODE_SET);
                jSONObject.put("responseCode", h0.FRAGMENT_ENCODE_SET);
                jSONObject.put("needResponse", false);
                jSONObject.put("protocolId", 80190);
                jSONObject.put("versionName", "4.0.0.20200410");
                jSONObject.put("requestAuthor", h0.FRAGMENT_ENCODE_SET);
                jSONObject.put(ConfigBackupService.EXTRA_MESSAGE, h0.FRAGMENT_ENCODE_SET);
                jSONObject.put("messageType", "dispatch");
                jSONObject.put("statusCode", 200);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("speedoMeter", parseFloat);
                jSONObject.put("data", jSONObject2);
                ((f2.a) this.f7924c).h(this.f7922a.getPackageName(), jSONObject.toString());
            } catch (Exception e7) {
                e7.getMessage();
            }
        } catch (NumberFormatException unused) {
        }
    }

    public final void d() {
        if (this.f7926e) {
            return;
        }
        this.f7926e = true;
        if (!this.f7927f) {
            c.c().f7938f.f(this.f7932k);
            this.f7927f = true;
        }
        if (!this.f7925d || this.f7924c == null) {
            a();
        }
    }

    public final void e() {
        this.f7926e = false;
        Handler handler = this.f7923b;
        handler.removeCallbacks(this.f7930i);
        handler.removeCallbacks(this.f7931j);
        if (this.f7927f) {
            c.c().f7938f.g(this.f7932k);
            this.f7927f = false;
        }
        if (this.f7925d) {
            try {
                this.f7922a.unbindService(this.f7929h);
            } catch (Exception unused) {
            }
            this.f7925d = false;
        }
        this.f7924c = null;
        c.c().f7936d.i(Boolean.FALSE);
    }
}
