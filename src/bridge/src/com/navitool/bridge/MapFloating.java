package com.navitool.bridge;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.lang.reflect.Field;

/**
 * 悬浮地图 <-> 悬浮日历切换时，真正结束/唤醒当前悬浮的地图应用。
 *
 * 原厂 cycleFloatingMapDisplayMode() 切到日历只发 closemap 收起悬浮窗，
 * 地图进程仍在后台跑；切回只发 showmap 重新贴窗。这里补上「进程级」的
 * 结束与唤醒。
 *
 * 结束方式优先用 Device Owner 的 setApplicationHidden(pkg,true) 冻结：
 * 进程立即停止且不能自启，比 force-stop 更彻底；切回先 unfreeze(false)
 * 再发 showmap 唤醒。助手不是 Device Owner 时退回 killBackgroundProcesses
 * + am force-stop 老办法。
 *
 * 只处理当前激活的那一个地图包，第二个不动。
 * 注入点：DesktopAssistantActivity.cycleFloatingMapDisplayMode() 里
 * isFloatingMapMode() 之后，把切换前的状态 wasMapMode 传进来。
 */
public final class MapFloating {
    private static final String PREFS = "navi_tool_prefs";
    private static final String PKG_1 = "desktop_map_package";
    private static final String PKG_2 = "desktop_map_package_2";
    private static final String ACTIVE_IDX = "desktop_map_active_index";
    private static final String DEVICE_ADMIN =
            "com.navi.tool.receiver.NaviDeviceAdminReceiver";

    private MapFloating() {
    }

    /** wasMapMode=true 表示本次点击将从地图切到日历（结束）；false 表示切回地图（唤醒）。 */
    public static void onCycle(Context ctx, boolean wasMapMode) {
        String pkg = activePkg(ctx);
        if (pkg == null || pkg.length() == 0) {
            return;
        }
        // 不碰自己和系统关键进程
        if (pkg.equals(ctx.getPackageName()) || pkg.startsWith("com.android.")) {
            return;
        }
        if (wasMapMode) {
            boolean hidden = hide(ctx, pkg, true);
            if (!hidden) {
                forceStop(ctx, pkg);   // 非 Device Owner 兜底
            }
        } else {
            hide(ctx, pkg, false);     // 先解冻，否则 showmap 唤醒不了
            final String target = pkg;
            // 等 updateMapCalendarVisibility 把 mapContainer 显示出来再取坐标
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendShow(ctx, target);
                }
            }, 900);
        }
    }

    /** 与原厂 AppDetectionUtils.getActiveFloatingMapPackage 同一套 key。 */
    private static String activePkg(Context ctx) {
        try {
            SharedPreferences p = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            String a = p.getString(PKG_1, null);
            String b = p.getString(PKG_2, null);
            int idx = p.getInt(ACTIVE_IDX, 0);
            if (b == null || b.length() == 0) {
                return a;
            }
            if (idx == 1) {
                return b;
            }
            return (a == null || a.length() == 0) ? b : a;
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Device Owner 冻结/解冻：setApplicationHidden 让目标包整个进入 hidden 状态，
     * 进程立即停止且不能自启；解冻后恢复。返回是否生效。
     */
    private static boolean hide(Context ctx, String pkg, boolean freeze) {
        try {
            DevicePolicyManager dpm =
                    (DevicePolicyManager) ctx.getSystemService(Context.DEVICE_POLICY_SERVICE);
            if (dpm == null || !dpm.isDeviceOwnerApp(ctx.getPackageName())) {
                return false;
            }
            ComponentName admin = new ComponentName(ctx.getPackageName(), DEVICE_ADMIN);
            boolean ok = dpm.setApplicationHidden(admin, pkg, freeze);
            Bridge.note("map hidden(" + freeze + "): " + pkg + " -> " + ok);
            return ok;
        } catch (Throwable t) {
            Bridge.note("map hidden fail: " + Bridge.root(t));
            return false;
        }
    }

    /** 非 Device Owner 兜底：复用原厂 LogLinkageManager.closePackage 的两招。 */
    private static void forceStop(Context ctx, String pkg) {
        try {
            android.app.ActivityManager am =
                    (android.app.ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
            if (am != null) {
                am.killBackgroundProcesses(pkg);
            }
        } catch (Throwable t) {
        }
        try {
            Runtime.getRuntime().exec("am force-stop " + pkg);
        } catch (Throwable t) {
        }
        Bridge.note("map force-stop(fallback): " + pkg);
    }

    /** 反射拿 mapContainer 坐标，发 showmap 唤醒悬浮窗。 */
    private static void sendShow(Context ctx, String pkg) {
        try {
            Field f = ctx.getClass().getDeclaredField("mapContainer");
            f.setAccessible(true);
            View v = (View) f.get(ctx);
            if (v == null || v.getWidth() <= 0 || v.getHeight() <= 0) {
                return;
            }
            int[] loc = new int[2];
            v.getLocationOnScreen(loc);
            Intent i = new Intent("com.autonavi.plus.showmap");
            i.setPackage(pkg);
            i.putExtra("x", loc[0]);
            i.putExtra("y", loc[1]);
            i.putExtra("w", loc[0] + v.getWidth());
            i.putExtra("h", loc[1] + v.getHeight());
            i.addFlags(0x20); // Intent.FLAG_INCLUDE_STOPPED_PACKAGES
            ctx.sendBroadcast(i);
            Bridge.note("map show: " + pkg);
        } catch (Throwable t) {
            Bridge.note("map show fail: " + Bridge.root(t));
        }
    }
}
