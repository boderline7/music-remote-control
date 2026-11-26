package com.navi.tool.baidu;

import android.os.SystemClock;
import com.baidu.naviauto.imaplbs.IEventListener;
import n1.z;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class a extends IEventListener.Stub {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f4624a;

    public a(e eVar) {
        this.f4624a = eVar;
    }

    @Override // com.baidu.naviauto.imaplbs.IEventListener
    public final void onEvent(String str) {
        String str2 = h0.FRAGMENT_ENCODE_SET;
        try {
            String str3 = this.f4624a.f4640g;
            if (str3 != null && !str3.isEmpty()) {
                str = z.P(this.f4624a.f4640g, str);
            }
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("function", h0.FRAGMENT_ENCODE_SET);
            String optString2 = jSONObject.optString("result", h0.FRAGMENT_ENCODE_SET);
            this.f4624a.f4642i = SystemClock.elapsedRealtime();
            "add_on_enlarge_info_change_listener".equals(optString);
            if ("add_on_navi_status_change_listener".equals(optString)) {
                this.f4624a.getClass();
                e.p(optString2);
                return;
            }
            if ("add_on_lane_info_change_listener".equals(optString)) {
                e.d(this.f4624a, optString2);
                return;
            }
            if ("add_on_remain_info_change_listener".equals(optString)) {
                this.f4624a.getClass();
                if (optString2 != null && !optString2.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject(optString2);
                    int optInt = jSONObject2.optInt("remainDistance", 0);
                    int optInt2 = jSONObject2.optInt("remainTime", 0);
                    h hVar = h.B;
                    hVar.f4662k = String.valueOf(optInt);
                    String.valueOf(optInt);
                    hVar.f4663l = optInt2;
                    e.r();
                    return;
                }
                return;
            }
            if ("add_on_speed_change_listener".equals(optString)) {
                this.f4624a.getClass();
                if (optString2 != null && !optString2.isEmpty()) {
                    float parseFloat = Float.parseFloat(optString2.trim());
                    h hVar2 = h.B;
                    String.valueOf(Math.round(parseFloat));
                    hVar2.getClass();
                    return;
                }
                return;
            }
            if ("add_on_speed_limit_change_listener".equals(optString)) {
                this.f4624a.getClass();
                if (optString2 != null && !optString2.isEmpty()) {
                    int optInt3 = new JSONObject(optString2).optInt("limitSpeed", 0);
                    h hVar3 = h.B;
                    if (optInt3 > 0) {
                        str2 = String.valueOf(optInt3);
                    }
                    hVar3.f4653b = str2;
                    return;
                }
                return;
            }
            if ("add_on_turn_info_change_listener".equals(optString)) {
                e.e(this.f4624a, optString2);
                return;
            }
            if ("add_on_road_condition_change_listener".equals(optString)) {
                e.f(this.f4624a, optString2);
                return;
            }
            if ("add_on_enlarge_info_change_listener".equals(optString)) {
                e.g(this.f4624a, optString2);
            } else if ("add_on_location_change_listener".equals(optString)) {
                e.a(this.f4624a, optString2);
            } else if ("add_on_highway_subscribe_info_change_listener".equals(optString)) {
                e.b(this.f4624a, optString2);
            }
        } catch (Exception unused) {
        }
    }
}
