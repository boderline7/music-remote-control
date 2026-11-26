package com.navi.tool.scene.api;

import java.util.List;

/**
 * 编译期桩：与 app dex 里的 com.navi.tool.scene.api.SceneRuleData 保持同签名。
 * 只用于让 SceneEngineImpl 编得过，**不会打进 classes3.dex**（make.py 编译后删掉本包产物）。
 * 运行时用的是 app 里的真类。
 */
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

        public Condition(String f, String o, String v) {
            this.field = f;
            this.operator = o;
            this.value = v;
        }
    }

    public SceneRuleData(String id, String logic, boolean resetEnabled,
                         String resetType, List<Condition> conditions) {
        this.id = id;
        this.logic = logic;
        this.resetEnabled = resetEnabled;
        this.resetType = resetType;
        this.conditions = conditions;
    }
}
