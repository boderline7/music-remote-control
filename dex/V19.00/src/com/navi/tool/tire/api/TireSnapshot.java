package com.navi.tool.tire.api;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class TireSnapshot {
    public static final int INVALID_PRESSURE = -1;
    public static final int INVALID_TEMP = Integer.MIN_VALUE;
    public boolean online;
    public int flKpa = -1;
    public int frKpa = -1;
    public int rlKpa = -1;
    public int rrKpa = -1;
    public int flTemp = Integer.MIN_VALUE;
    public int frTemp = Integer.MIN_VALUE;
    public int rlTemp = Integer.MIN_VALUE;
    public int rrTemp = Integer.MIN_VALUE;
    public String updateTime = h0.FRAGMENT_ENCODE_SET;
    public long updateTimestamp = 0;
    public String serverTime = h0.FRAGMENT_ENCODE_SET;
    public long fetchedAt = 0;

    public boolean hasAnyPressure() {
        return this.flKpa > 0 || this.frKpa > 0 || this.rlKpa > 0 || this.rrKpa > 0;
    }
}
