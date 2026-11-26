package com.navi.tool.log.api;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class LogRuleData {
    public static final int TYPE_APP = 0;
    public static final int TYPE_ASSISTANT = 2;
    public static final int TYPE_MEDIA = 1;
    public final String action;
    public final int comboCount;
    public final int comboSeconds;
    public final String conditionOperator;
    public final String conditionType;
    public final String conditionValue;
    public final String conditionVariable;
    public final int cooldownSeconds;
    public final String id;
    public final String name;
    public final String pattern;
    public final String tag;
    public final int type;
    public final String variableName;
    public final String variableOp;
    public final String variableValue;

    public LogRuleData(String str, int i7, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, int i8, int i9, int i10) {
        this.id = text(str);
        this.type = i7;
        this.name = text(str2);
        this.tag = text(str3);
        this.pattern = text(str4);
        this.action = text(str5);
        this.conditionType = text(str6);
        this.conditionVariable = text(str7);
        this.conditionOperator = text(str8);
        this.conditionValue = text(str9);
        this.variableName = text(str10);
        this.variableOp = text(str11);
        this.variableValue = text(str12);
        this.cooldownSeconds = i8;
        this.comboSeconds = i9;
        this.comboCount = i10;
    }

    private static String text(String str) {
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }
}
