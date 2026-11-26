package com.navi.tool.navi.api;

import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface NaviEngine {
    int detect(String str);

    boolean isNaviSelectLine(String str);

    PoiPoint parseNaviSelect(String str);

    List<NaviAction> plan(int i7, NaviConfig naviConfig);
}
