package com.navitool.bridge;

import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 能力自检。不修改任何东西，只把「能自动对上的」和「要人工兜底的」列清楚。
 * 这是装车后第一眼要看的东西。
 */
final class Probe {

    private Probe() {
    }

    static String json(Context ctx) {
        Object helper = Bridge.helper;
        StringBuilder sb = new StringBuilder(8192);
        sb.append("{\"ok\":1,\"at\":").append(System.currentTimeMillis());

        if (helper == null) {
            sb.append(",\"helper\":0,\"err\":\"未绑定，桌面助手还没出现过\"");
            sb.append(",\"log\":").append(Bridge.logJson()).append('}');
            return sb.toString();
        }

        sb.append(",\"helper\":1");
        sb.append(",\"cls\":").append(Bridge.q(helper.getClass().getName()));
        sb.append(",\"boundAge\":").append(Bridge.helperAt == 0 ? -1
                : (System.currentTimeMillis() - Bridge.helperAt));

        // ---- 能力清单
        String[] ids = asStrings(Bridge.call(helper, "getTopInfoIds"));
        String[] names = asStrings(staticField(helper, "TOP_INFO_NAMES"));

        sb.append(",\"topIds\":").append(strArray(ids));
        sb.append(",\"names\":").append(strArray(names));

        // ---- 逐项匹配
        int auto = 0;
        int manual = 0;
        StringBuilder match = new StringBuilder(2048);
        match.append('[');
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                String id = ids[i];
                if (id == null) {
                    continue;
                }
                String name = (names != null && i < names.length) ? names[i] : "";
                String byRule = "handle" + Actions.camel(id) + "Click";
                Method m = Actions.findNoArg(helper.getClass(), byRule);
                String alias = aliasOf(id);
                Method m2 = alias == null ? null : Actions.findNoArg(helper.getClass(), alias);

                String via;
                boolean ok;
                if (m2 != null) {
                    via = alias;
                    ok = true;
                } else if (m != null) {
                    via = byRule;
                    ok = true;
                } else {
                    via = byRule;
                    ok = false;
                }
                if (ok) {
                    auto++;
                } else {
                    manual++;
                }
                if (match.length() > 1) {
                    match.append(',');
                }
                match.append("{\"id\":").append(Bridge.q(id))
                        .append(",\"name\":").append(Bridge.q(name))
                        .append(",\"via\":").append(Bridge.q(via))
                        .append(",\"ok\":").append(ok ? 1 : 0);
                if (m != null) {
                    match.append(",\"mod\":").append(Bridge.q(Modifier.toString(m.getModifiers())));
                }
                match.append('}');
            }
        }
        match.append(']');
        sb.append(",\"match\":").append(match);
        sb.append(",\"summary\":{\"total\":").append(auto + manual)
                .append(",\"auto\":").append(auto)
                .append(",\"manual\":").append(manual).append('}');

        // ---- 辅助字段是否就位
        sb.append(",\"fields\":{");
        sb.append("\"carControlAdapter\":").append(Bridge.getField(helper, "carControlAdapter") != null ? 1 : 0);
        sb.append(",\"prefs\":").append(Bridge.getField(helper, "prefs") != null ? 1 : 0);
        sb.append(",\"carControlContainer\":").append(Bridge.getField(helper, "carControlContainer") != null ? 1 : 0);
        sb.append('}');

        // ---- 对外可用的实例方法清单（去掉合成方法）
        StringBuilder methods = new StringBuilder(4096);
        methods.append('[');
        boolean first = true;
        for (Method m : helper.getClass().getDeclaredMethods()) {
            if (m.isSynthetic()) {
                continue;
            }
            int mod = m.getModifiers();
            if (!Modifier.isPublic(mod)) {
                continue;
            }
            if (!first) {
                methods.append(',');
            }
            first = false;
            methods.append(Bridge.q(m.getName() + "/" + m.getParameterTypes().length));
        }
        methods.append(']');
        sb.append(",\"methods\":").append(methods);

        sb.append(",\"log\":").append(Bridge.logJson());
        sb.append('}');
        return sb.toString();
    }

    private static String aliasOf(String id) {
        if ("hvac_circ".equals(id)) {
            return "handleHvacCirculationClick";
        }
        if ("hvac_mode".equals(id)) {
            return "showHvacModeGrid";
        }
        if ("trunk_switch".equals(id) || "trunk".equals(id)) {
            return "handleTrunkClick";
        }
        return null;
    }

    private static Object staticField(Object target, String name) {
        Class<?> c = target.getClass();
        while (c != null) {
            try {
                Field f = c.getDeclaredField(name);
                f.setAccessible(true);
                return f.get(null);
            } catch (NoSuchFieldException e) {
                c = c.getSuperclass();
            } catch (Throwable t) {
                Bridge.note("probe field " + name + ": " + Bridge.root(t));
                return null;
            }
        }
        return null;
    }

    private static String[] asStrings(Object o) {
        return o instanceof String[] ? (String[]) o : null;
    }

    private static String strArray(String[] a) {
        if (a == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(512);
        sb.append('[');
        for (int i = 0; i < a.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(Bridge.q(a[i]));
        }
        return sb.append(']').toString();
    }
}
