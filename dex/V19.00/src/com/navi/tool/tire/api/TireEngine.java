package com.navi.tool.tire.api;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface TireEngine {
    TireSnapshot fetch(TireAccount tireAccount);

    String fetchCaptcha(TireBrand tireBrand, String str);

    TireAccount login(TireBrand tireBrand, String str, String str2);

    void sendSmsCode(TireBrand tireBrand, String str, String str2);
}
