package s4;

import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import s5.h0;
import s5.m0;
import s5.o0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    public static final o0 f8728a;

    static {
        m0 m0Var = new m0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        m0Var.a(10L, timeUnit);
        m0Var.b(10L, timeUnit);
        f8728a = new o0(m0Var);
    }

    public static b a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        b bVar = new b();
        JSONObject optJSONObject = jSONObject.optJSONObject("metadata");
        if (optJSONObject != null) {
            optJSONObject.optString("tag", h0.FRAGMENT_ENCODE_SET);
            bVar.f8725a = optJSONObject.optBoolean("zeroResult", false);
            JSONArray optJSONArray = optJSONObject.optJSONArray("attributions");
            if (optJSONArray != null) {
                for (int i7 = 0; i7 < optJSONArray.length(); i7++) {
                    String optString = optJSONArray.optString(i7, h0.FRAGMENT_ENCODE_SET);
                    if (!optString.isEmpty()) {
                        bVar.f8726b.add(optString);
                    }
                }
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("alerts");
        if (optJSONArray2 != null) {
            for (int i8 = 0; i8 < optJSONArray2.length(); i8++) {
                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i8);
                if (optJSONObject2 != null) {
                    a aVar = new a();
                    aVar.f8712a = optJSONObject2.optString("id", h0.FRAGMENT_ENCODE_SET);
                    optJSONObject2.optString("senderName", h0.FRAGMENT_ENCODE_SET);
                    aVar.f8713b = optJSONObject2.optString("issuedTime", h0.FRAGMENT_ENCODE_SET);
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("messageType");
                    if (optJSONObject3 != null) {
                        aVar.f8714c = optJSONObject3.optString("code", h0.FRAGMENT_ENCODE_SET);
                        JSONArray optJSONArray3 = optJSONObject3.optJSONArray("supersedes");
                        if (optJSONArray3 != null) {
                            for (int i9 = 0; i9 < optJSONArray3.length(); i9++) {
                                String optString2 = optJSONArray3.optString(i9, h0.FRAGMENT_ENCODE_SET);
                                if (!optString2.isEmpty()) {
                                    aVar.f8715d.add(optString2);
                                }
                            }
                        }
                    }
                    JSONObject optJSONObject4 = optJSONObject2.optJSONObject("eventType");
                    if (optJSONObject4 != null) {
                        aVar.f8716e = optJSONObject4.optString("name", h0.FRAGMENT_ENCODE_SET);
                        optJSONObject4.optString("code", h0.FRAGMENT_ENCODE_SET);
                    }
                    optJSONObject2.optString("urgency", h0.FRAGMENT_ENCODE_SET);
                    aVar.f8717f = optJSONObject2.optString("severity", h0.FRAGMENT_ENCODE_SET);
                    optJSONObject2.optString("certainty", h0.FRAGMENT_ENCODE_SET);
                    aVar.f8718g = optJSONObject2.optString("icon", h0.FRAGMENT_ENCODE_SET);
                    JSONObject optJSONObject5 = optJSONObject2.optJSONObject("color");
                    if (optJSONObject5 != null) {
                        aVar.f8719h = optJSONObject5.optString("code", h0.FRAGMENT_ENCODE_SET);
                    }
                    optJSONObject2.optString("effectiveTime", h0.FRAGMENT_ENCODE_SET);
                    optJSONObject2.optString("onsetTime", h0.FRAGMENT_ENCODE_SET);
                    aVar.f8720i = optJSONObject2.optString("expireTime", optJSONObject2.optString("expiredTime", h0.FRAGMENT_ENCODE_SET));
                    aVar.f8721j = optJSONObject2.optString("headline", h0.FRAGMENT_ENCODE_SET);
                    aVar.f8722k = optJSONObject2.optString("description", h0.FRAGMENT_ENCODE_SET);
                    optJSONObject2.optString("criteria", h0.FRAGMENT_ENCODE_SET);
                    aVar.f8723l = optJSONObject2.optString("instruction", h0.FRAGMENT_ENCODE_SET);
                    JSONArray optJSONArray4 = optJSONObject2.optJSONArray("responseTypes");
                    if (optJSONArray4 != null) {
                        for (int i10 = 0; i10 < optJSONArray4.length(); i10++) {
                            String optString3 = optJSONArray4.optString(i10, h0.FRAGMENT_ENCODE_SET);
                            if (!optString3.isEmpty()) {
                                aVar.f8724m.add(optString3);
                            }
                        }
                    }
                    bVar.f8727c.add(aVar);
                }
            }
        }
        return bVar;
    }
}
