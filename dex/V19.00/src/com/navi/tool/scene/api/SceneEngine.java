package com.navi.tool.scene.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public interface SceneEngine {
    void clearAllState();

    void clearExecuted(String str);

    List<SceneDecision> evaluate(List<SceneRuleData> list, Map<String, String> map, Set<String> set);

    boolean isExecuted(String str);

    void markExecuted(String str);

    boolean matches(SceneRuleData sceneRuleData, Map<String, String> map);

    void pruneState(Set<String> set);
}
