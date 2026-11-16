package com.navitool.bridge;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 读卡片数据。数据源是车机助手自己的 CarControlAdapter，
 * 我们只读不写，绝不构造第二个 Adapter。
 *
 * 输出结构与车机卡片一一对应：卡片类型 / 标题 / 列数 / 每条明细（项名、值、占列）。
 */
final class Cards {

    private Cards() {
    }

    static String json(Context ctx) {
        Object helper = Bridge.helper;
        if (helper == null) {
            return "{\"ok\":0,\"err\":\"helper 未绑定（桌面助手还没出现过）\","
                    + "\"log\":" + Bridge.logJson() + "}";
        }

        final Object h = helper;
        Object adapter = Bridge.onMainSync(new Callable<Object>() {
            public Object call() {
                return Bridge.getField(h, "carControlAdapter");
            }
        });
        if (adapter == null) {
            return "{\"ok\":0,\"err\":\"carControlAdapter 字段读不到\"}";
        }

        List<?> list = readData(adapter);
        if (list == null || list.isEmpty()) {
            Bridge.onMainSync(new Callable<Object>() {
                public Object call() {
                    return Bridge.call(h, "updateCarControlCards");
                }
            });
            list = readData(adapter);
        }

        StringBuilder sb = new StringBuilder(4096);
        sb.append("{\"ok\":1,\"at\":").append(System.currentTimeMillis());
        sb.append(",\"style\":").append(styleJson(ctx, adapter));

        sb.append(",\"cards\":[");
        if (list != null) {
            boolean firstCard = true;
            for (Object card : list) {
                if (card == null) {
                    continue;
                }
                if (!firstCard) {
                    sb.append(',');
                }
                firstCard = false;
                appendCard(sb, card);
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private static List<?> readData(Object adapter) {
        Object data = Bridge.call(adapter, "getData");
        return data instanceof List ? (List<?>) data : null;
    }

    private static void appendCard(StringBuilder sb, Object card) {
        Object type = Bridge.getField(card, "type");
        String typeName = type instanceof Enum ? ((Enum<?>) type).name() : "";
        String title = str(Bridge.getField(type, "title"));
        int cols = num(Bridge.getField(card, "columnCount"), 1);

        sb.append("{\"t\":").append(Bridge.q(typeName));
        sb.append(",\"title\":").append(Bridge.q(title));
        sb.append(",\"cols\":").append(cols);
        sb.append(",\"items\":[");

        Object details = Bridge.getField(card, "details");
        if (details instanceof List) {
            boolean first = true;
            for (Object d : (List<?>) details) {
                if (d == null) {
                    continue;
                }
                if (!first) {
                    sb.append(',');
                }
                first = false;
                sb.append("{\"k\":").append(Bridge.q(str(Bridge.getField(d, "key"))));
                sb.append(",\"v\":").append(Bridge.q(str(Bridge.getField(d, "value"))));
                sb.append(",\"s\":").append(num(Bridge.getField(d, "colSpan"), 1));
                sb.append('}');
            }
        }
        sb.append("]}");
    }

    /**
     * 样式全部取自 Adapter 的实际生效值（不是默认值），
     * 这样网页和车机在同一时刻用的是同一份数据。
     */
    private static String styleJson(Context ctx, Object adapter) {
        int propColor = num(Bridge.getField(adapter, "propertyTextColor"), -1);
        int valueColor = num(Bridge.getField(adapter, "valueTextColor"), -1);
        int alpha = num(Bridge.getField(adapter, "cardAlphaPercent"), 90);
        int fontSize = num(Bridge.getField(adapter, "contentFontSize"), 24);

        boolean round = false;
        int radius = 12;
        int margin = 8;
        try {
            SharedPreferences p = ctx.getSharedPreferences(Bridge.PREF_NAME, Context.MODE_PRIVATE);
            round = p.getBoolean("desktop_round_corner_enabled", false);
            radius = p.getInt("car_control_card_corner_radius", 12);
            margin = p.getInt("desktop_card_margin", 8);
        } catch (Throwable t) {
            Bridge.note("prefs: " + Bridge.root(t));
        }
        if (!round) {
            radius = 12;
        }

        return "{\"propColor\":" + Bridge.q(argb(propColor))
                + ",\"valueColor\":" + Bridge.q(argb(valueColor))
                + ",\"alpha\":" + alpha
                + ",\"fontSize\":" + fontSize
                + ",\"round\":" + (round ? 1 : 0)
                + ",\"radius\":" + radius
                + ",\"margin\":" + margin
                + "}";
    }

    static String argb(int color) {
        if (color == 0) {
            return "#000000";
        }
        return String.format("#%08X", color);
    }

    static int num(Object o, int def) {
        return o instanceof Number ? ((Number) o).intValue() : def;
    }

    static String str(Object o) {
        return o == null ? "" : String.valueOf(o);
    }
}
