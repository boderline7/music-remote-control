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
     * 整组不显示：驾驶模式（7 种）、辅助（雷达 / 手刹）、底盘制动联动、车身（后备箱 / 无线充电 /
     * 行人提示音 / 儿童锁）。
     */
    private static final String[][] TABLE = {
            {"空调", "handleHvacAutoClick", "AUTO", "-1"},
            {"空调", "handleHvacCirculationClick", "内外循环", "-1"},

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

    /** 按钮文字（操作记录里写人话，记的是「内外循环」不是 handleHvacCirculationClick） */
    static String labelOf(String name) {
        if (name == null) {
            return "";
        }
        for (String[] e : TABLE) {
            if (e[1].equals(name)) {
                return e[2];
            }
        }
        return name;
    }

    /** 动作表清单文本：页面「动作表」分段和 /dump.txt 都从这里取 */
    static String text() {
        StringBuilder sb = new StringBuilder(2048);
        int live = count();
        sb.append("==== 动作表 ====\n");
        sb.append("可执行  ").append(live).append(" 项 / 表内 ").append(TABLE.length).append(" 条\n");
        sb.append("说明    只列真实存在的方法，不存在的整条不出现\n");

        String last = null;
        for (String[] e : TABLE) {
            String miss = find(e[1]) == null ? "  ← 方法不存在，已隐藏" : "";
            if (!e[0].equals(last)) {
                sb.append("\n[").append(e[0]).append("]\n");
                last = e[0];
            }
            sb.append("  ").append(Txt.pad(e[2], 18)).append(' ').append(e[1]);
            if (!"-1".equals(e[3])) {
                sb.append("  v=").append(e[3]);
            }
            sb.append(miss).append('\n');
        }
        return sb.toString();
    }

    // ---------- 风量 ----------

    /**
     * sendHvacCommand 的命令号，抄自车控卡片里两个 SeekBar 的松手回调：
     * 前排 sb_hvac_fan → (0x0C, progress+1)，后排 sb_hvac_rear_fan → (0x44, progress)，
     * progress 为 0 时先补一发 (0x43, 1) 表示关闭。
     */
    private static final int CMD_FAN_FRONT = 0x0C;
    private static final int CMD_FAN_REAR = 0x44;
    private static final int CMD_FAN_REAR_OFF = 0x43;

    /**
     * 前后排风量。front / rear 传 -1 表示这一路不动。
     *
     * 车机助手里风量是 SeekBar 不是按钮，回调调的是私有的 sendHvacCommand，
     * 所以动作表按方法名筛的时候筛不出来 —— 这不是不支持，是形状不同。
     * 这里走同一个方法、同一组命令号，档位范围也跟原界面一致：前排 1-7，后排 0-4（0 = 关）。
     */
    static String fan(Context ctx, final int front, final int rear) {
        final Object helper = Bridge.helper;
        if (helper == null) {
            return "{\"ok\":0,\"err\":\"helper 未绑定\"}";
        }
        final Method send = find2(helper.getClass(), "sendHvacCommand");
        if (send == null) {
            return "{\"ok\":0,\"err\":\"no such method\"}";
        }
        try {
            Bridge.onMainSync(new Callable<Object>() {
                public Object call() throws Exception {
                    send.setAccessible(true);
                    if (front >= 0) {
                        send.invoke(helper, Integer.valueOf(CMD_FAN_FRONT),
                                Integer.valueOf(clamp(front, 1, 7)));
                    }
                    if (rear >= 0) {
                        int v = clamp(rear, 0, 4);
                        if (v == 0) {
                            send.invoke(helper, Integer.valueOf(CMD_FAN_REAR_OFF), Integer.valueOf(1));
                            send.invoke(helper, Integer.valueOf(CMD_FAN_REAR), Integer.valueOf(0));
                        } else {
                            // 后排被 0x43 关过之后，直接甩目标档位车机不接；
                            // 先补一发 1 档把它开起来，再写目标档位。
                            if (level(helper, "hvac_rear_fan") == 0) {
                                send.invoke(helper, Integer.valueOf(CMD_FAN_REAR), Integer.valueOf(1));
                            }
                            send.invoke(helper, Integer.valueOf(CMD_FAN_REAR), Integer.valueOf(v));
                        }
                    }
                    return null;
                }
            });
        } catch (Throwable t) {
            Bridge.note("fan: " + Bridge.root(t));
            return "{\"ok\":0,\"err\":" + Bridge.q(Bridge.root(t)) + "}";
        }
        return "{\"ok\":1,\"front\":" + level(helper, "hvac_fan")
                + ",\"rear\":" + level(helper, "hvac_rear_fan") + "}";
    }

    /** 读当前档位：借 getCarInfoValue(...)。读不到返回 -1，「关闭 / 关」算 0。 */
    static int level(Object helper, String id) {
        try {
            Method m = helper.getClass().getMethod("getCarInfoValue", String.class);
            Object r = m.invoke(helper, id);
            if (r instanceof String) {
                String s = ((String) r).trim();
                if (s.length() == 0) {
                    return -1;
                }
                // 车机把「关」写成「关闭」「关」，档位写成「2 档」「2档」
                if (s.indexOf('关') >= 0 || "off".equalsIgnoreCase(s)) {
                    return 0;
                }
                String d = s.replaceAll("[^0-9]", "");
                if (d.length() > 0) {
                    return Integer.parseInt(d);
                }
            }
        } catch (Throwable ignored) {
        }
        return -1;
    }

    // ---------- 温度 ----------

    /**
     * 温度：SceneModeManager 里 hvac_temp 走 actionSendCommand(context, 0x5000A, 0x0A, 摄氏度-15)。
     * 注意第一个参数是空调通道 0x5000A，不是座椅那组 0x50001 —— 发错通道车机一声不响。
     * 值域 15-30 整度，parseTemp 也是这么夹的。
     */
    private static final int CMD_TEMP = 0x0A;
    private static final int VD_HVAC = 0x5000A;
    private static final int TEMP_MIN = 15;
    private static final int TEMP_MAX = 30;

    static String temp(Context ctx, final int v) {
        final Object helper = Bridge.helper;
        if (helper == null) {
            return "{\"ok\":0,\"err\":\"helper 未绑定\"}";
        }
        if (v >= 0) {
            final Method send = findN(helper.getClass(), "actionSendCommand", 4);
            if (send == null) {
                return "{\"ok\":0,\"err\":\"no such method\"}";
            }
            try {
                final int deg = clamp(v, TEMP_MIN, TEMP_MAX);
                Bridge.onMainSync(new Callable<Object>() {
                    public Object call() throws Exception {
                        send.setAccessible(true);
                        send.invoke(null, ctx, Integer.valueOf(VD_HVAC),
                                Integer.valueOf(CMD_TEMP), Integer.valueOf(deg - TEMP_MIN));
                        return null;
                    }
                });
            } catch (Throwable t) {
                Bridge.note("temp: " + Bridge.root(t));
                return "{\"ok\":0,\"err\":" + Bridge.q(Bridge.root(t)) + "}";
            }
        }
        return "{\"ok\":1,\"v\":" + tempOf(helper) + "}";
    }

    /**
     * 温度回读。车机可能给「22.0」「22°C」「22 ℃」，也可能给 0-15 的原始档，
     * 所以先把第一段数字（带小数点）抠出来，再看是不是原始档。
     * 不能像风量那样把所有非数字都删掉 —— 「22.0」会变成 220。
     */
    private static int tempOf(Object helper) {
        String s = raw(helper, "hvac_temp");
        if (s == null || s.trim().length() == 0) {
            // 有的车机把主驾温度挂在另一个 key 上
            s = raw(helper, "hvac_temp_driver");
        }
        if (s == null || s.trim().length() == 0) {
            s = raw(helper, "hvac_temp_fl");
        }
        if (s == null) {
            return -1;
        }
        s = s.trim();
        if (s.length() == 0) {
            return -1;
        }
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("(-?\\d+(?:\\.\\d+)?)").matcher(s);
        if (!m.find()) {
            return -1;
        }
        int v;
        try {
            v = (int) Math.round(Double.parseDouble(m.group(1)));
        } catch (Throwable t) {
            return -1;
        }
        boolean celsius = s.indexOf('.') >= 0 || s.indexOf('°') >= 0
                || s.indexOf('℃') >= 0 || s.indexOf('C') >= 0;
        if (!celsius && v > 0 && v < TEMP_MIN) {
            return v + TEMP_MIN;          // 原始档 0-15 → 15-30℃
        }
        return v;
    }

    // ---------- 座椅 ----------

    private static final String[] SEAT_POS = {"FL", "FR", "RL", "RR"};

    /**
     * 座椅命令号（车机总线 CMD_ID），照抄助手 handleSeatClick 里那张表：
     *   加热  FL 0x5F / FR 0x60 / RL 0x8B / RR 0x8D
     *   通风  FL 0x5D / FR 0x5E —— 只有前排
     * 后排通风在这台车的助手里既没有命令号也没有状态位（getCarInfoValue 只认
     * seat_vent_fl / seat_vent_fr），所以这里返回 -1，前端干脆不画那一行。
     */
    private static int seatCmd(String pos, boolean vent) {
        if (vent) {
            return "FL".equals(pos) ? 0x5D : ("FR".equals(pos) ? 0x5E : -1);
        }
        if ("FL".equals(pos)) {
            return 0x5F;
        }
        if ("FR".equals(pos)) {
            return 0x60;
        }
        if ("RL".equals(pos)) {
            return 0x8B;
        }
        return "RR".equals(pos) ? 0x8D : -1;
    }

    /** 这个座位支不支持这一路（后排通风不支持）。 */
    static boolean seatSupported(String pos, boolean vent) {
        return seatCmd(pos == null ? "" : pos.trim().toUpperCase(), vent) > 0;
    }

    /**
     * 直接下发「设成几档」：level 0 = 关，1-3 = 档位。
     *
     * 不再调 handleSeatClick 的循环：它要先从内部 Map 里读出当前档位再 +1，
     * 读不到（或者读到「3 档」这种带后缀的字符串）就会算出别的值往下发，
     * 后排之前就出现过「点左后加热把前排带起来」。这里直接给定档位，一步到位。
     *
     * level >= 0 表示要设成这个档；level < 0 只读状态。
     */
    static String seat(Context ctx, final String pos, final String kind, final int level) {
        final Object helper = Bridge.helper;
        if (helper == null) {
            return "{\"ok\":0,\"err\":\"helper 未绑定\"}";
        }
        String p = pos == null ? "" : pos.trim().toUpperCase();
        final boolean vent = "vent".equalsIgnoreCase(kind == null ? "" : kind.trim());
        final int cmd = seatCmd(p, vent);
        if (cmd < 0) {
            return "{\"ok\":0,\"err\":\"这台车不支持\"}";
        }
        if (level >= 0) {
            final int lv = clamp(level, 0, 3);
            final boolean left = "FL".equals(p) || "RL".equals(p);
            final boolean heat = !vent;
            final Method m = findN(helper.getClass(), "sendSeatCommand", 4);
            if (m == null) {
                return "{\"ok\":0,\"err\":\"no sendSeatCommand\"}";
            }
            try {
                Bridge.onMainSync(new Callable<Object>() {
                    public Object call() throws Exception {
                        m.setAccessible(true);
                        m.invoke(helper, Integer.valueOf(cmd), Integer.valueOf(lv),
                                Boolean.valueOf(left), Boolean.valueOf(heat));
                        return null;
                    }
                });
            } catch (Throwable t) {
                Bridge.note("seat: " + Bridge.root(t));
                return "{\"ok\":0,\"err\":" + Bridge.q(Bridge.root(t)) + "}";
            }
        }
        return seatJson(helper);
    }

    /** 四座加热 / 通风的当前状态，原样带出去（「关」「2 档」「--」都有可能）。 */
    static String seatJson(Object helper) {
        StringBuilder sb = new StringBuilder(320);
        sb.append("{\"ok\":1,\"seats\":{");
        boolean first = true;
        for (String pos : SEAT_POS) {
            for (String kind : new String[]{"heat", "vent"}) {
                if (!first) {
                    sb.append(',');
                }
                first = false;
                sb.append('"').append(kind).append('_').append(pos.toLowerCase()).append("\":")
                        .append(Bridge.q(raw(helper, "seat_" + kind + "_" + pos.toLowerCase())));
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    private static String raw(Object helper, String id) {
        try {
            Method m = helper.getClass().getMethod("getCarInfoValue", String.class);
            Object r = m.invoke(helper, id);
            return r == null ? "" : String.valueOf(r);
        } catch (Throwable ignored) {
            return "";
        }
    }

    private static Method findN(Class<?> type, String name, int args) {
        Class<?> c = type;
        while (c != null) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.getName().equals(name) && m.getParameterTypes().length == args) {
                    return m;
                }
            }
            c = c.getSuperclass();
        }
        return null;
    }

    private static Method find2b(Class<?> type, String name, Class<?> a, Class<?> b) {
        Class<?> c = type;
        while (c != null) {
            for (Method m : c.getDeclaredMethods()) {
                Class<?>[] ps = m.getParameterTypes();
                if (m.getName().equals(name) && ps.length == 2
                        && ps[0] == a && ps[1] == b) {
                    return m;
                }
            }
            c = c.getSuperclass();
        }
        return null;
    }

    private static Method find2(Class<?> type, String name) {
        Class<?> c = type;
        while (c != null) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.getName().equals(name) && m.getParameterTypes().length == 2) {
                    return m;
                }
            }
            c = c.getSuperclass();
        }
        return null;
    }

    private static int clamp(int v, int lo, int hi) {
        return v < lo ? lo : (v > hi ? hi : v);
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
