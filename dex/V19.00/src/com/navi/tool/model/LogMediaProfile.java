package com.navi.tool.model;

import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogMediaProfile {
    private String action;
    private int comboCount;
    private int comboSeconds;
    private String conditionOperator;
    private String conditionType;
    private String conditionValue;
    private String conditionVariable;
    private int cooldownSeconds;
    private int delaySeconds;
    private boolean enabled;
    private String mediaPath;
    private String name;
    private String pattern;
    private String playMode;
    private String releasePattern;
    private String tag;
    private String variableName;
    private String variableOp;
    private String variableValue;

    public LogMediaProfile() {
    }

    public LogMediaProfile(String str, String str2, String str3, String str4, String str5, boolean z6) {
        this(str, str2, str3, str4, str5, z6, null, null, null, null, null, null, null, null, null, 0, 0, 0, 0);
    }

    public LogMediaProfile(String str, String str2, String str3, String str4, String str5, boolean z6, String str6, String str7) {
        this(str, str2, str3, str4, str5, z6, str6, str7, null, null, null, null, null, null, null, 0, 0, 0, 0);
    }

    public LogMediaProfile(String str, String str2, String str3, String str4, String str5, boolean z6, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i7) {
        this(str, str2, str3, str4, str5, z6, str6, str7, str8, str9, str10, str11, str12, str13, str14, i7, 0, 0, 0);
    }

    public LogMediaProfile(String str, String str2, String str3, String str4, String str5, boolean z6, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i7, int i8) {
        this(str, str2, str3, str4, str5, z6, str6, str7, str8, str9, str10, str11, str12, str13, str14, i7, i8, 0, 0);
    }

    public LogMediaProfile(String str, String str2, String str3, String str4, String str5, boolean z6, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i7, int i8, int i9, int i10) {
        this.name = str;
        this.tag = str2;
        this.pattern = str3;
        this.mediaPath = str4;
        this.action = (str5 == null || str5.trim().isEmpty()) ? "play_file" : str5.trim();
        this.enabled = z6;
        this.playMode = (str6 == null || str6.trim().isEmpty()) ? "direct" : str6.trim();
        this.releasePattern = str7 == null ? h0.FRAGMENT_ENCODE_SET : str7;
        this.conditionType = str8;
        this.conditionVariable = str9;
        this.conditionOperator = str10;
        this.conditionValue = str11;
        this.variableName = str12;
        this.variableOp = str13;
        this.variableValue = str14;
        this.delaySeconds = i7;
        this.cooldownSeconds = i8;
        this.comboSeconds = i9;
        this.comboCount = i10;
    }

    public LogMediaProfile(String str, String str2, String str3, String str4, boolean z6) {
        this.name = str;
        this.tag = str2;
        this.pattern = str3;
        this.mediaPath = str4;
        this.action = "play_file";
        this.enabled = z6;
        this.playMode = "direct";
        this.releasePattern = h0.FRAGMENT_ENCODE_SET;
        this.delaySeconds = 0;
        this.cooldownSeconds = 0;
        this.comboSeconds = 0;
        this.comboCount = 0;
    }

    public String getAction() {
        String str = this.action;
        return (str == null || str.isEmpty()) ? "play_file" : this.action;
    }

    public int getComboCount() {
        return this.comboCount;
    }

    public int getComboSeconds() {
        return this.comboSeconds;
    }

    public String getConditionOperator() {
        String str = this.conditionOperator;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public String getConditionType() {
        String str = this.conditionType;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public String getConditionValue() {
        String str = this.conditionValue;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public String getConditionVariable() {
        String str = this.conditionVariable;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public int getCooldownSeconds() {
        return this.cooldownSeconds;
    }

    public int getDelaySeconds() {
        return this.delaySeconds;
    }

    public String getMediaPath() {
        return this.mediaPath;
    }

    public String getName() {
        return this.name;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getPlayMode() {
        String str = this.playMode;
        return (str == null || str.isEmpty()) ? "direct" : this.playMode;
    }

    public String getReleasePattern() {
        String str = this.releasePattern;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public String getTag() {
        return this.tag;
    }

    public String getVariableName() {
        String str = this.variableName;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public String getVariableOp() {
        String str = this.variableOp;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public String getVariableValue() {
        String str = this.variableValue;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setComboCount(int i7) {
        this.comboCount = i7;
    }

    public void setComboSeconds(int i7) {
        this.comboSeconds = i7;
    }

    public void setConditionOperator(String str) {
        this.conditionOperator = str;
    }

    public void setConditionType(String str) {
        this.conditionType = str;
    }

    public void setConditionValue(String str) {
        this.conditionValue = str;
    }

    public void setConditionVariable(String str) {
        this.conditionVariable = str;
    }

    public void setCooldownSeconds(int i7) {
        this.cooldownSeconds = i7;
    }

    public void setDelaySeconds(int i7) {
        this.delaySeconds = i7;
    }

    public void setEnabled(boolean z6) {
        this.enabled = z6;
    }

    public void setMediaPath(String str) {
        this.mediaPath = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPattern(String str) {
        this.pattern = str;
    }

    public void setPlayMode(String str) {
        this.playMode = str;
    }

    public void setReleasePattern(String str) {
        this.releasePattern = str;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setVariableName(String str) {
        this.variableName = str;
    }

    public void setVariableOp(String str) {
        this.variableOp = str;
    }

    public void setVariableValue(String str) {
        this.variableValue = str;
    }
}
