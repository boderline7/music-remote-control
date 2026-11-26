package s4;

import android.os.Handler;
import android.os.Looper;
import com.navi.tool.service.ConfigBackupService;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import n1.z;
import org.json.JSONObject;
import s5.a1;
import s5.h0;
import s5.m0;
import s5.o0;
import s5.r0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class g {

    /* renamed from: a, reason: collision with root package name */
    public static final Handler f8736a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    public static final ExecutorService f8737b = Executors.newSingleThreadExecutor();

    /* renamed from: c, reason: collision with root package name */
    public static final o0 f8738c;

    static {
        m0 m0Var = new m0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        m0Var.a(10L, timeUnit);
        m0Var.b(10L, timeUnit);
        f8738c = new o0(m0Var);
    }

    public static f a(double d7, double d8) {
        String N0 = z.N0(d7, d8);
        r0 r0Var = new r0();
        r0Var.f(N0);
        r0Var.b();
        x0 f7 = f8738c.a(r0Var.a()).f();
        try {
            if (!f7.B()) {
                throw new IOException("天气API请求失败: " + f7.f8916d);
            }
            a1 a1Var = f7.f8919g;
            if (a1Var == null) {
                throw new IOException("天气API响应体为空");
            }
            f b7 = b(a1Var.A());
            f7.close();
            return b7;
        } catch (Throwable th) {
            try {
                f7.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static f b(String str) {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("code");
        if (!"200".equals(optString)) {
            throw new IOException("天气API返回错误: " + optString + " " + jSONObject.optString(ConfigBackupService.EXTRA_MESSAGE));
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("now");
        f fVar = new f();
        fVar.f8732a = jSONObject2.optString("icon", "999");
        fVar.f8733b = jSONObject2.optString("text", "--");
        fVar.f8734c = jSONObject2.optString("temp", "--");
        jSONObject2.optString("feelsLike", "--");
        jSONObject2.optString("obsTime", h0.FRAGMENT_ENCODE_SET);
        jSONObject.optString("fxLink", h0.FRAGMENT_ENCODE_SET);
        fVar.f8735d = System.currentTimeMillis();
        return fVar;
    }
}
