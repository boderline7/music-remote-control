package com.navitool.bridge;

import android.content.Context;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.Callable;

/**
 * 动作表。
 *
 * 和上一版的区别：这里的每一项都对应车机助手内部一个**真实存在**的方法，
 * 运行时先反射查方法在不在，不在就整条不出现。所以前端不会再有「点了没反应」的按钮。
 *
 * 三类方法形状：
 *   实例 0 参   handleHvacAutoClick()           —— 开关类，用 helper 实例调
 *   静态 1 参   actionSetDriveModeSport(Context) —— 模式类
 *   静态 2 参   actionSetChildLock(Context,boolean) —— 带开关值的，值从请求里带
 *
 * 车况信息（车速、车门、油耗…）不在这里：那些只读，已经在卡片区显示。
 */
final class Acts {

    /**
     * 组, 方法名, 按钮文字, 参数值（-1 表示不需要）
     *
     * 已按需求剔除：吹风模式、雨刮灵敏度、方向盘加热、大灯高度；
     * 整组不显示：驾驶模式（7 种）、辅助（雷达 / 手刹）、底盘制动联动。
     */
    private static final String[][] TABLE = {
            {"空调", "handleHvacAutoClick", "AUTO", "-1"},
            {"空调", "handleHvacCirculationClick", "内外循环", "-1"},

            {"车身", "handleTrunkClick", "后备箱", "-1"},
            {"车身", "handleWirelessChargingClick", "无线充电", "-1"},
            {"车身", "handlePedestrianAlertClick", "行人提示音", "-1"},
            {"车身", "actionSetChildLock", "儿童锁 开", "1"},
            {"车身", "actionSetChildLock", "儿童锁 关", "0"},

            {"底盘", "actionSetSuspensionModeComfort", "悬架 舒适", "-1"},
            {"底盘", "actionSetSuspensionModeStandard", "悬架 标准", "-1"},
            {"底盘", "actionSetSuspensionModeSport", "悬架 运动", "-1"},
            {"底盘", "actionSetSuspensionLinkEnabled", "悬架联动 开", "1"},
            {"底盘", "actionSetSuspensionLinkEnabled", "悬架联动 关", "0"},
            {"底盘", "actionSetBrakeFeelModeComfort", "刹车 舒适", "-1"},
            {"底盘", "actionSetBrakeFeelModeSport", "刹车 运动", "-1"},
    };

    private Acts() {
    }

    /** 只列出方法确实存在的能力 */
    static String json() {
        StringBuilder sb = new StringBuilder(2048);
        sb.append("{\"ok\":1,\"groups\":[");
        String lastGroup = null;
        boolean firstInGroup = true;
        for (String[] e : TABLE) {
            Method m = find(e[1]);
            if (m == null) {
                continue;
            }
            if (!e[0].equals(lastGroup)) {
                if (lastGroup != null) {
                    sb.append("]},");
                }
                sb.append("{\"g\":").append(Bridge.q(e[0])).append(",\"items\":[");
                lastGroup = e[0];
                firstInGroup = true;
            }
            if (!firstInGroup) {
                sb.append(',');
            }
            firstInGroup = false;
            sb.append("{\"id\":").append(Bridge.q(e[1]))
                    .append(",\"t\":").append(Bridge.q(e[2]))
                    .append(",\"v\":").append(e[3]).append('}');
        }
        if (lastGroup != null) {
            sb.append("]}");
        }
        sb.append("]}");
        return sb.toString();
    }

    /** 当前真正能执行的项数（诊断里用） */
    static int count() {
        int n = 0;
        for (String[] e : TABLE) {
            if (find(e[1]) != null) {
                n++;
            }
        }
        return n;
    }

    /** 按方法名执行。所有会碰界面的动作都回主线程跑。 */
    static String run(Context ctx, final String name, final int v) {
        final Method m = find(name);
        if (m == null) {
            return "{\"ok\":0,\"err\":\"no such method\"}";
        }
        try {
            m.setAccessible(true);
            Object r = Bridge.onMainSync(new Callable<Object>() {
                public Object call() throws Exception {
                    Class<?>[] ps = m.getParameterTypes();
                    boolean st = Modifier.isStatic(m.getModifiers());
                    if (ps.length == 0) {
                        if (st) {
                            return m.invoke(null);
                        }
                        Object target = Bridge.helper;
                        if (target == null) {
                            throw new IllegalStateException("helper 未绑定");
                        }
                        return m.invoke(target);
                    }
                    if (ps.length == 1) {
                        return m.invoke(null, ctx);
                    }
                    Object arg = ps[1] == boolean.class
                            ? (Object) Boolean.valueOf(v != 0)
                            : (Object) Integer.valueOf(v);
                    return m.invoke(null, ctx, arg);
                }
            });
            boolean done = !(r instanceof Boolean) || ((Boolean) r).booleanValue();
            if (!done) {
                return "{\"ok\":0,\"err\":\"方法返回 false（车机没接受）\"}";
            }
            return "{\"ok\":1,\"via\":" + Bridge.q(name) + "}";
        } catch (Throwable t) {
            Bridge.note("act " + name + ": " + Bridge.root(t));
            return "{\"ok\":0,\"err\":" + Bridge.q(Bridge.root(t)) + "}";
        }
    }

    private static Method find(String name) {
        Class<?> c = null;
        Object helper = Bridge.helper;
        if (helper != null) {
            c = helper.getClass();
        } else {
            try {
                c = Class.forName("com.navi.tool.ui.extra.DesktopAssistantCarControlHelper");
            } catch (Throwable ignored) {
                return null;
            }
        }
        while (c != null) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.getName().equals(name) && !m.isSynthetic()) {
                    return m;
                }
            }
            c = c.getSuperclass();
        }
        return null;
    }
}
