package com.navi.tool.baidu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.navi.tool.service.PhoneNotificationRelayService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
class BaiduTrafficLightReceiver$1 extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("traffic_light");
            if (stringExtra != null && !stringExtra.isEmpty()) {
                JSONArray optJSONArray = new JSONObject(stringExtra).optJSONArray("crossTlis");
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    ArrayList arrayList = new ArrayList();
                    int i7 = -1;
                    for (int i8 = 0; i8 < optJSONArray.length(); i8++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i8);
                        if (optJSONObject != null) {
                            if (i7 < 0) {
                                i7 = optJSONObject.optInt("dist", -1);
                            }
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("phases");
                            if (optJSONArray2 != null) {
                                for (int i9 = 0; i9 < optJSONArray2.length(); i9++) {
                                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i9);
                                    if (optJSONObject2 != null) {
                                        String optString = optJSONObject2.optString("direction", h0.FRAGMENT_ENCODE_SET);
                                        arrayList.add(new g("straight".equals(optString) ? 2 : PhoneNotificationRelayService.OVERLAY_POSITION_LEFT.equals(optString) ? 1 : PhoneNotificationRelayService.OVERLAY_POSITION_RIGHT.equals(optString) ? 3 : "uturn".equals(optString) ? 0 : -1, "green".equals(optJSONObject2.optString("curColor", h0.FRAGMENT_ENCODE_SET)) ? 1 : 0, optJSONObject2.optInt("curCountDown", 0)));
                                    }
                                }
                            }
                        }
                    }
                    h hVar = h.B;
                    hVar.f4669r = arrayList;
                    hVar.f4670s = i7;
                    hVar.f4671t = System.currentTimeMillis();
                    i6.e.b().f(new f());
                    return;
                }
                h hVar2 = h.B;
                hVar2.f4669r = new ArrayList();
                hVar2.f4671t = System.currentTimeMillis();
                return;
            }
            h hVar3 = h.B;
            hVar3.f4669r = new ArrayList();
            hVar3.f4671t = System.currentTimeMillis();
        } catch (Exception unused) {
        }
    }
}
