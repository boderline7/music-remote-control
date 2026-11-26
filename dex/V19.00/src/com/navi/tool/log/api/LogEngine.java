package com.navi.tool.log.api;

import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface LogEngine {
    List<LogDecision> evaluate(List<LogRuleData> list, String str, long j7);

    void resetRuleState();
}
