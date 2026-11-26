package com.navi.tool.navi.api;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class PoiPoint {
    public final String latitude;
    public final String longitude;
    public final String name;

    public PoiPoint(String str, String str2, String str3) {
        this.name = str == null ? h0.FRAGMENT_ENCODE_SET : str;
        this.latitude = str2 == null ? h0.FRAGMENT_ENCODE_SET : str2;
        this.longitude = str3 == null ? h0.FRAGMENT_ENCODE_SET : str3;
    }
}
