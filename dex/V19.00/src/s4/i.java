package s4;

import com.navi.tool.NaviApplication;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.io.IOException;
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
public abstract class i {

    /* renamed from: a, reason: collision with root package name */
    public static final o0 f8746a;

    static {
        m0 m0Var = new m0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        m0Var.a(10L, timeUnit);
        m0Var.b(10L, timeUnit);
        f8746a = new o0(m0Var);
    }

    public static h a(double d7, double d8) {
        String str;
        String str2 = h0.FRAGMENT_ENCODE_SET;
        String str3 = "{'lon':" + d8 + ",'lat':" + d7 + ",'ver':1}";
        StringBuilder sb = new StringBuilder();
        sb.append(z.h());
        sb.append("/geoproxy.php?uid=");
        try {
            str = SharedPreferencesUtils.getLicenseUid(NaviApplication.getInstance());
        } catch (Throwable unused) {
            str = h0.FRAGMENT_ENCODE_SET;
        }
        sb.append(z.Z(str));
        sb.append("&tk=");
        try {
            str2 = SharedPreferencesUtils.getLicenseToken(NaviApplication.getInstance());
        } catch (Throwable unused2) {
        }
        sb.append(z.Z(str2));
        sb.append("&type=geocode&postStr=");
        sb.append(z.Z(str3));
        String sb2 = sb.toString();
        r0 r0Var = new r0();
        r0Var.f(sb2);
        r0Var.b();
        r0Var.c("Accept", "application/json");
        r0Var.c("User-Agent", "NaviTool/1.0");
        x0 f7 = f8746a.a(r0Var.a()).f();
        try {
            if (!f7.B()) {
                throw new IOException("天地图Geo请求失败: " + f7.f8916d);
            }
            a1 a1Var = f7.f8919g;
            if (a1Var == null) {
                throw new IOException("天地图Geo响应体为空");
            }
            h b7 = b(a1Var.A());
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

    public static h b(String str) {
        if (str == null) {
            throw new IOException("天地图Geo响应为空");
        }
        String trim = str.trim();
        if (trim.startsWith("\ufeff")) {
            trim = trim.substring(1).trim();
        }
        int indexOf = trim.indexOf(z1.c.NEG_INT);
        int lastIndexOf = trim.lastIndexOf(z1.c.NEG_LONG);
        if (indexOf >= 0 && lastIndexOf > indexOf) {
            trim = trim.substring(indexOf, lastIndexOf + 1);
        }
        if (trim.isEmpty()) {
            throw new IOException("天地图Geo响应为空白");
        }
        JSONObject jSONObject = new JSONObject(trim);
        String optString = jSONObject.optString("status");
        if (!"0".equals(optString)) {
            throw new IOException("天地图Geo返回错误: " + optString + " " + jSONObject.optString("msg"));
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("result");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
        if (optJSONObject2 == null) {
            optJSONObject2 = new JSONObject();
        }
        h hVar = new h();
        hVar.f8739a = c(optJSONObject.optString("formatted_address"));
        hVar.f8740b = c(optJSONObject2.optString("county_code"));
        hVar.f8741c = c(optJSONObject2.optString("county"));
        c(optJSONObject2.optString("town_code"));
        hVar.f8742d = c(optJSONObject2.optString("town"));
        hVar.f8743e = c(optJSONObject2.optString("address"));
        hVar.f8744f = c(optJSONObject2.optString("city"));
        c(optJSONObject2.optString("city_code"));
        hVar.f8745g = c(optJSONObject2.optString("province"));
        c(optJSONObject2.optString("province_code"));
        return hVar;
    }

    public static String c(String str) {
        if (str == null) {
            return h0.FRAGMENT_ENCODE_SET;
        }
        String trim = str.trim();
        return (trim.isEmpty() || "null".equalsIgnoreCase(trim)) ? h0.FRAGMENT_ENCODE_SET : trim;
    }
}
