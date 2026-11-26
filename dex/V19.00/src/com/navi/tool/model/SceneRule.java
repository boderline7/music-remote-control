package com.navi.tool.model;

import a.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SceneRule {
    public static final int MAX_DELAY_SECONDS = 1800;
    private String name;
    private String id = UUID.randomUUID().toString();
    private boolean enabled = true;
    private String logic = "AND";
    private int delaySeconds = 0;
    private boolean resetEnabled = false;
    private String resetType = "ENGINE_RUN_TO_OFF";
    private List<Condition> conditions = new ArrayList();
    private List<Action> actions = new ArrayList();

    public static class Action {
        private String type;
        private String value;

        public Action() {
        }

        public Action(String str, String str2) {
            this.type = str;
            this.value = str2;
        }

        public static Action fromJson(JSONObject jSONObject) {
            Action action = new Action();
            if (jSONObject == null) {
                return action;
            }
            action.setType(jSONObject.optString("type", h0.FRAGMENT_ENCODE_SET));
            action.setValue(jSONObject.optString("value", h0.FRAGMENT_ENCODE_SET));
            return action;
        }

        public String getType() {
            String str = this.type;
            return str == null ? h0.FRAGMENT_ENCODE_SET : str;
        }

        public String getValue() {
            String str = this.value;
            return str == null ? h0.FRAGMENT_ENCODE_SET : str;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", getType());
                jSONObject.put("value", getValue());
            } catch (Exception unused) {
            }
            return jSONObject;
        }
    }

    public static class Condition {
        private String field;
        private String operator;
        private String value;

        public Condition() {
        }

        public Condition(String str, String str2, String str3) {
            this.field = str;
            this.operator = str2;
            this.value = str3;
        }

        public static Condition fromJson(JSONObject jSONObject) {
            Condition condition = new Condition();
            if (jSONObject == null) {
                return condition;
            }
            condition.setField(jSONObject.optString("field", h0.FRAGMENT_ENCODE_SET));
            condition.setOperator(jSONObject.optString("operator", "="));
            condition.setValue(jSONObject.optString("value", h0.FRAGMENT_ENCODE_SET));
            return condition;
        }

        public String getField() {
            String str = this.field;
            return str == null ? h0.FRAGMENT_ENCODE_SET : str;
        }

        public String getOperator() {
            String str = this.operator;
            return str == null ? "=" : str;
        }

        public String getValue() {
            String str = this.value;
            return str == null ? h0.FRAGMENT_ENCODE_SET : str;
        }

        public void setField(String str) {
            this.field = str;
        }

        public void setOperator(String str) {
            this.operator = str;
        }

        public void setValue(String str) {
            this.value = str;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("field", getField());
                jSONObject.put("operator", getOperator());
                jSONObject.put("value", getValue());
            } catch (Exception unused) {
            }
            return jSONObject;
        }
    }

    public static String formatDelay(int i7) {
        if (i7 <= 0) {
            return "立即执行";
        }
        if (i7 < 60) {
            return a.e("延时 ", i7, " 秒");
        }
        int i8 = i7 / 60;
        int i9 = i7 % 60;
        if (i9 == 0) {
            return a.e("延时 ", i8, " 分钟");
        }
        return "延时 " + i8 + " 分 " + i9 + " 秒";
    }

    public static SceneRule fromJson(JSONObject jSONObject) {
        SceneRule sceneRule = new SceneRule();
        if (jSONObject == null) {
            return sceneRule;
        }
        sceneRule.setId(jSONObject.optString("id", UUID.randomUUID().toString()));
        sceneRule.setName(jSONObject.optString("name", h0.FRAGMENT_ENCODE_SET));
        sceneRule.setEnabled(jSONObject.optBoolean("enabled", true));
        sceneRule.setLogic(jSONObject.optString("logic", "AND"));
        int optInt = jSONObject.optInt("delayMinutes", 0) * 60;
        if (jSONObject.has("delaySeconds")) {
            optInt = jSONObject.optInt("delaySeconds", optInt);
        }
        sceneRule.setDelaySeconds(optInt);
        sceneRule.setResetEnabled(jSONObject.optBoolean("resetEnabled", false));
        sceneRule.setResetType(jSONObject.optString("resetType", "ENGINE_RUN_TO_OFF"));
        JSONArray optJSONArray = jSONObject.optJSONArray("conditions");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i7 = 0; i7 < optJSONArray.length(); i7++) {
                arrayList.add(Condition.fromJson(optJSONArray.optJSONObject(i7)));
            }
        }
        sceneRule.setConditions(arrayList);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("actions");
        ArrayList arrayList2 = new ArrayList();
        if (optJSONArray2 != null) {
            for (int i8 = 0; i8 < optJSONArray2.length(); i8++) {
                arrayList2.add(Action.fromJson(optJSONArray2.optJSONObject(i8)));
            }
        }
        sceneRule.setActions(arrayList2);
        return sceneRule;
    }

    public List<Action> getActions() {
        if (this.actions == null) {
            this.actions = new ArrayList();
        }
        return this.actions;
    }

    public List<Condition> getConditions() {
        if (this.conditions == null) {
            this.conditions = new ArrayList();
        }
        return this.conditions;
    }

    public int getDelaySeconds() {
        return this.delaySeconds;
    }

    public String getId() {
        String str = this.id;
        if (str == null || str.isEmpty()) {
            this.id = UUID.randomUUID().toString();
        }
        return this.id;
    }

    public String getLogic() {
        String str = this.logic;
        return str == null ? "AND" : str;
    }

    public String getName() {
        String str = this.name;
        return str == null ? h0.FRAGMENT_ENCODE_SET : str;
    }

    public String getResetType() {
        String str = this.resetType;
        return str == null ? "ENGINE_RUN_TO_OFF" : str;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isResetEnabled() {
        return this.resetEnabled;
    }

    public void setActions(List<Action> list) {
        this.actions = list;
    }

    public void setConditions(List<Condition> list) {
        this.conditions = list;
    }

    public void setDelaySeconds(int i7) {
        if (i7 < 0) {
            i7 = 0;
        }
        if (i7 > 1800) {
            i7 = 1800;
        }
        this.delaySeconds = i7;
    }

    public void setEnabled(boolean z6) {
        this.enabled = z6;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLogic(String str) {
        this.logic = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setResetEnabled(boolean z6) {
        this.resetEnabled = z6;
    }

    public void setResetType(String str) {
        this.resetType = str;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", getId());
            jSONObject.put("name", getName());
            jSONObject.put("enabled", isEnabled());
            jSONObject.put("logic", getLogic());
            jSONObject.put("delaySeconds", getDelaySeconds());
            jSONObject.put("resetEnabled", isResetEnabled());
            jSONObject.put("resetType", getResetType());
            JSONArray jSONArray = new JSONArray();
            Iterator<Condition> it = getConditions().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJson());
            }
            jSONObject.put("conditions", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            Iterator<Action> it2 = getActions().iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().toJson());
            }
            jSONObject.put("actions", jSONArray2);
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
