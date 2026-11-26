package com.navi.tool.scene.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class SceneRuleData {
    public final List<Condition> conditions;
    public final String id;
    public final String logic;
    public final boolean resetEnabled;
    public final String resetType;

    public static final class Condition {
        public final String field;
        public final String operator;
        public final String value;

        public Condition(String str, String str2, String str3) {
            this.field = str == null ? h0.FRAGMENT_ENCODE_SET : str;
            this.operator = (str2 == null || str2.isEmpty()) ? "=" : str2;
            this.value = str3 == null ? h0.FRAGMENT_ENCODE_SET : str3;
        }
    }

    public SceneRuleData(String str, String str2, boolean z6, String str3, List<Condition> list) {
        this.id = str == null ? h0.FRAGMENT_ENCODE_SET : str;
        this.logic = str2 == null ? "AND" : str2;
        this.resetEnabled = z6;
        this.resetType = str3 == null ? "ENGINE_RUN_TO_OFF" : str3;
        this.conditions = Collections.unmodifiableList(new ArrayList(list == null ? Collections.emptyList() : list));
    }
}
