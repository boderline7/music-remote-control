package com.navi.tool.scene.api;

/** 编译期桩，同 SceneRuleData。 */
public final class SceneDecision {

    public final boolean matched;
    public final String ruleId;
    public final boolean trigger;

    public SceneDecision(String ruleId, boolean matched, boolean trigger) {
        this.ruleId = ruleId;
        this.matched = matched;
        this.trigger = trigger;
    }
}
