package com.navi.tool.service;

import com.navi.tool.model.SceneRule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SceneCatalog {
    public static final List<FieldDef> FIELDS = Arrays.asList(new FieldDef("speed", "车速", "km/h", null), new FieldDef("outside_temp", "车外温度", "°C", null), new FieldDef("gear", "档位", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"P", "R", "N", "D", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8"}), new FieldDef("engine_status", "引擎状态", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"运行", "熄火"}), new FieldDef("power_mode", "电源状态", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"OFF", "ACC", "ON", "运行"}), new FieldDef("door_fl", "主驾门", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("door_fr", "副驾门", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("door_rl", "左后门", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("door_rr", "右后门", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("driving_brake_link", "制动关联", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"已关联", "未关联"}), new FieldDef("drive_mode", "驾驶模式", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"经济", "标准", "运动", "雪地", "泥地", "越野", "沙地"}), new FieldDef("brake_feel_mode", "制动感觉", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"舒适", "运动"}), new FieldDef("suspension_link", "悬架关联", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"已关联", "未关联"}), new FieldDef("suspension_mode", "悬架模式", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"舒适", "标准", "运动"}), new FieldDef("child_lock", "儿童锁", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"锁定", "未锁"}), new FieldDef("seat_heat_fl", "主驾加热", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"关", "1 档", "2 档", "3 档"}), new FieldDef("seat_heat_fr", "副驾加热", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"关", "1 档", "2 档", "3 档"}), new FieldDef("seat_vent_fl", "主驾通风", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"关", "1 档", "2 档", "3 档"}), new FieldDef("seat_vent_fr", "副驾通风", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"关", "1 档", "2 档", "3 档"}), new FieldDef("hvac_auto", "空调AUTO", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("hvac_ac", "空调AC", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("hvac_circ", "内外循环", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"内循环", "外循环"}), new FieldDef("fragrance_switch", "香氛", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("steering_heat", "方向盘加热", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("wiper_sensitivity", "雨感灵敏度", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"1档(低)", "2档", "3档", "4档"}), new FieldDef("light_parking", "示廓灯", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("light_high", "远光灯", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"开", "关"}), new FieldDef("wireless_charging_state", "无线充电状态", s5.h0.FRAGMENT_ENCODE_SET, new String[]{"充电中", "已充满", "充电异常"}));
    public static final List<ActionDef> ACTIONS = Arrays.asList(new ActionDef("brake_link", "制动关联", new String[]{"开启", "关闭"}), new ActionDef("brake_feel_mode", "制动感觉", new String[]{"舒适", "运动"}), new ActionDef("drive_mode", "驾驶模式", new String[]{"经济", "标准", "运动", "雪地", "泥地", "越野", "沙地"}), new ActionDef("suspension_link", "悬架关联", new String[]{"开启", "关闭"}), new ActionDef("suspension_mode", "悬架模式", new String[]{"舒适", "标准", "运动"}), new ActionDef("seat_heat_fl", "主驾加热", new String[]{"关", "1档", "2档", "3档"}), new ActionDef("seat_heat_fr", "副驾加热", new String[]{"关", "1档", "2档", "3档"}), new ActionDef("seat_heat_rl", "左后加热", new String[]{"关", "1档", "2档", "3档"}), new ActionDef("seat_heat_rr", "右后加热", new String[]{"关", "1档", "2档", "3档"}), new ActionDef("seat_vent_fl", "主驾通风", new String[]{"关", "1档", "2档", "3档"}), new ActionDef("seat_vent_fr", "副驾通风", new String[]{"关", "1档", "2档", "3档"}), new ActionDef("child_lock", "儿童锁", new String[]{"锁定", "未锁"}), new ActionDef("seat_memory_apply", "座椅记忆调用", new String[]{"配置1", "配置2", "配置3"}), new ActionDef("drive_enjoy_mode", "驾享模式", new String[]{"执行"}), new ActionDef("fragrance_mode", "香氛模式", new String[]{"AUTO", "1档", "2档", "3档", "4档", "5档", "关闭"}), new ActionDef("fragrance_type", "香氛类型", new String[]{"清新自然", "治愈舒缓", "运动激情"}), new ActionDef("steering_heat", "方向盘加热", new String[]{"开", "关"}), new ActionDef("pedestrian_alert", "行人提示", new String[]{"开", "关"}), new ActionDef("wiper_sensitivity", "雨感灵敏度", new String[]{"1档", "2档", "3档", "4档"}), new ActionDef("light_height", "灯光高度", new String[]{"0档", "1档", "2档", "3档"}), new ActionDef("all_windows", "所有车窗", new String[]{"关闭"}), new ActionDef("sunshade", "遮阳帘", new String[]{"打开", "关闭"}), new ActionDef("camera_360", "全景影像", new String[]{"打开", "关闭"}), new ActionDef("hvac_ac", "空调AC", new String[]{"开", "关"}), new ActionDef("hvac_auto", "空调AUTO", new String[]{"开", "关"}), new ActionDef("hvac_temp", "空调温度", null), new ActionDef("hvac_fan", "风量", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8"}), new ActionDef("hvac_rear_fan", "后排风量", new String[]{"1", "2", "3", "4"}), new ActionDef("hvac_circ", "内外循环", new String[]{"内循环", "外循环"}), new ActionDef("hvac_mode", "吹风模式", new String[]{"吹脸", "吹脸+吹脚", "吹脚", "除霜+吹脚", "除霜", "除霜+吹脸", "混合"}), new ActionDef("hvac_front_defrost", "前除霜", new String[]{"开", "关"}), new ActionDef("hvac_rear_defrost", "后除霜", new String[]{"开", "关"}), new ActionDef("rear_belt_reminder", "后排安全带提示", new String[]{"开", "关"}), new ActionDef("seat_main_forward", "主驾前移", null), new ActionDef("seat_main_backward", "主驾后移", null), new ActionDef("close_ac_ui", "关闭空调界面", null), new ActionDef("open_ac_ui", "开启空调界面", null), new ActionDef("close_systemui_ac_ui", "关闭UI级空调界面", null), new ActionDef("wireless_charging_switch", "无线充电开关", new String[]{"开启", "关闭"}));

    public static class ActionDef {
        public final String key;
        public final String label;
        public final String[] options;

        public ActionDef(String str, String str2, String[] strArr) {
            this.key = str;
            this.label = str2;
            this.options = strArr;
        }

        public boolean hasValueOptions() {
            String[] strArr = this.options;
            return strArr != null && strArr.length > 0;
        }
    }

    public static class FieldDef {
        public final String key;
        public final String label;
        public final String[] options;
        public final String unit;

        public FieldDef(String str, String str2, String str3, String[] strArr) {
            this.key = str;
            this.label = str2;
            this.unit = str3;
            this.options = strArr;
        }

        public boolean isEnum() {
            String[] strArr = this.options;
            return strArr != null && strArr.length > 0;
        }
    }

    public static String actionKeyByLabel(String str) {
        for (ActionDef actionDef : ACTIONS) {
            if (actionDef.label.equals(str)) {
                return actionDef.key;
            }
        }
        return s5.h0.FRAGMENT_ENCODE_SET;
    }

    public static String[] actionLabels() {
        ArrayList arrayList = new ArrayList();
        Iterator<ActionDef> it = ACTIONS.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().label);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static String actionSummary(SceneRule.Action action) {
        ActionDef findAction = findAction(action.getType());
        return (findAction == null ? action.getType() : findAction.label) + " -> " + action.getValue();
    }

    public static String conditionSummary(SceneRule.Condition condition) {
        String str;
        String str2;
        FieldDef findField = findField(condition.getField());
        String field = findField == null ? condition.getField() : findField.label;
        if (findField == null || (str2 = findField.unit) == null || str2.isEmpty()) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        } else {
            str = " " + findField.unit;
        }
        return field + " " + condition.getOperator() + " " + condition.getValue() + str;
    }

    public static String fieldKeyByLabel(String str) {
        for (FieldDef fieldDef : FIELDS) {
            if (fieldDef.label.equals(str)) {
                return fieldDef.key;
            }
        }
        return s5.h0.FRAGMENT_ENCODE_SET;
    }

    public static String[] fieldLabels() {
        ArrayList arrayList = new ArrayList();
        Iterator<FieldDef> it = FIELDS.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().label);
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static ActionDef findAction(String str) {
        for (ActionDef actionDef : ACTIONS) {
            if (actionDef.key.equals(str)) {
                return actionDef;
            }
        }
        return null;
    }

    public static FieldDef findField(String str) {
        for (FieldDef fieldDef : FIELDS) {
            if (fieldDef.key.equals(str)) {
                return fieldDef;
            }
        }
        return null;
    }
}
