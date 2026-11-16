package com.navitool.bridge;

import android.content.Context;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 动作执行器。核心思路：不自己发命令，只去调车机助手自己的方法。
 *
 * 匹配顺序：
 *   1. 显式别名表（少数几个不按命名规律的）
 *   2. 命名规律  steering_heat -> handleSteeringHeatClick
 *   3. 静态动作  actionXxx(Context)  例如 actionOpenRadar
 *
 * 全部失败时返回尝试过的候选清单，方便定位，不抛异常。
 */
final class Actions {

    /** 不按命名规律的少数几个，人工登记 */
    private static final String[][] ALIAS = {
            {"hvac_circ", "handleHvacCirculationClick"},
            {"hvac_mode", "showHvacModeGrid"},
            {"trunk", "handleTrunkClick"},
            {"trunk_switch", "handleTrunkClick"},
            {"memory_apply", "handleSeatMemoryApply"},
            {"memory_save", "handleSeatMemorySave"},
    };

    private Actions() {
    }

    static String json(Context ctx, String body) {
        String act = Json.pick(body, "a");
        if (act == null || act.length() == 0) {
            return "{\"ok\":0,\"err\":\"missing 'a'\"}";
        }
        Object helper = Bridge.helper;
        if (helper == null) {
            return "{\"ok\":0,\"err\":\"helper 未绑定\"}";
        }

        List<String> tried = new ArrayList<String>();

        // 1. 别名表
        for (String[] pair : ALIAS) {
            if (pair[0].equalsIgnoreCase(act)) {
                tried.add(pair[1]);
                if (invokeNoArg(helper, pair[1], tried)) {
                    return ok(act, pair[1]);
                }
                break;
            }
        }

        // 2. 命名规律
        String byRule = "handle" + camel(act) + "Click";
        tried.add(byRule);
        if (invokeNoArg(helper, byRule, tried)) {
            return ok(act, byRule);
        }

        // 3. 静态动作 actionXxx(Context)
        String byAction = "action" + camel(act);
        tried.add(byAction);
        Class<?> hc = helper.getClass();
        Class<?> c = hc;
        while (c != null) {
            try {
                Method m = c.getDeclaredMethod(byAction, Context.class);
                m.setAccessible(true);
                Object r = m.invoke(null, ctx);
                boolean done = !(r instanceof Boolean) || (Boolean) r;
                if (done) {
                    return ok(act, byAction);
                }
            } catch (NoSuchMethodException e) {
                c = c.getSuperclass();
            } catch (Throwable t) {
                tried.add("!" + Bridge.root(t));
                break;
            }
        }

        StringBuilder sb = new StringBuilder(256);
        sb.append("{\"ok\":0,\"err\":\"no method matched\",\"act\":").append(Bridge.q(act));
        sb.append(",\"tried\":[");
        for (int i = 0; i < tried.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(Bridge.q(tried.get(i)));
        }
        sb.append("]}");
        return sb.toString();
    }

    private static boolean invokeNoArg(Object target, String name, List<String> tried) {
        try {
            Method m = findNoArg(target.getClass(), name);
            if (m == null) {
                return false;
            }
            m.setAccessible(true);
            m.invoke(target);
            return true;
        } catch (Throwable t) {
            tried.add("!" + Bridge.root(t));
            return false;
        }
    }

    static Method findNoArg(Class<?> type, String name) {
        Class<?> c = type;
        while (c != null) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.getName().equals(name) && m.getParameterTypes().length == 0) {
                    return m;
                }
            }
            c = c.getSuperclass();
        }
        return null;
    }

    /** steering_heat -> SteeringHeat */
    static String camel(String id) {
        StringBuilder sb = new StringBuilder(id.length());
        boolean up = true;
        for (int i = 0; i < id.length(); i++) {
            char ch = id.charAt(i);
            if (ch == '_' || ch == '-' || ch == ' ') {
                up = true;
                continue;
            }
            sb.append(up ? Character.toUpperCase(ch) : ch);
            up = false;
        }
        return sb.toString();
    }

    private static String ok(String act, String via) {
        return "{\"ok\":1,\"act\":" + Bridge.q(act) + ",\"via\":" + Bridge.q(via)
                + ",\"at\":" + System.currentTimeMillis() + "}";
    }
}
