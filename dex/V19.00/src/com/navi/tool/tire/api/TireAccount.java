package com.navi.tool.tire.api;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TireAccount {
    public TireBrand brand = TireBrand.EXEED;
    public String phone = h0.FRAGMENT_ENCODE_SET;
    public String accessToken = h0.FRAGMENT_ENCODE_SET;
    public String sdkToken = h0.FRAGMENT_ENCODE_SET;
    public String tspUid = h0.FRAGMENT_ENCODE_SET;
    public String vin = h0.FRAGMENT_ENCODE_SET;
    public long expireAt = 0;

    public boolean isUsable() {
        String str = this.accessToken;
        return (str == null || str.isEmpty() || (this.expireAt > 0 && System.currentTimeMillis() >= this.expireAt)) ? false : true;
    }
}
