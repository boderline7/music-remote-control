package com.navitool.bridge;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 桥接核心：只做两件事
 *   1. 抓住桌面助手 Activity，反射读出它内部的 carControlHelper 实例（只读引用，绝不新建）
 *   2. 提供反射工具给 Cards / Actions 使用
 *
 * 设计原则：任何一个反射点失败都不抛出，把错误记进 log，通过 /probe 暴露出来。
 * 原工程一个字节都不改。
 */
final class Bridge {

    static final String TAG = "NavitoolBridge";
    static final int PORT = 18766;

    /** 车机助手的配置文件名，卡片样式参数都在里面 */
    static final String PREF_NAME = "com.navi.tool_preferences";

    /** 桌面助手 Activity 全名 */
    static final String ACTIVITY_CLASS = "com.navi.tool.ui.extra.DesktopAssistantActivity";
    /** Activity 里持有车控引擎的字段名 */
    static final String HELPER_FIELD = "carControlHelper";

    static final Handler MAIN = new Handler(Looper.getMainLooper());

    static volatile Activity activity;
    static volatile Object helper;
    static volatile long helperAt;
    static volatile String lastError = "";
    /** install 只做一次：provider 和 service 都会调它 */
    static volatile boolean installed = false;
    /** 18766 有没有真的绑上 */
    static volatile boolean bound = false;

    private static final List<String> LOG = new ArrayList<String>();

    private Bridge() {
    }

    // ---------------------------------------------------------------- log

    static void note(String s) {
        synchronized (LOG) {
            LOG.add(s);
            if (LOG.size() > 80) {
                LOG.remove(0);
            }
        }
        try {
            android.util.Log.i(TAG, s);
        } catch (Throwable ignored) {
        }
    }

    static String logJson() {
        StringBuilder sb = new StringBuilder("[");
        synchronized (LOG) {
            for (int i = 0; i < LOG.size(); i++) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append('"').append(esc(LOG.get(i))).append('"');
            }
        }
        return sb.append(']').toString();
    }

    /** 纯文本版日志，给 /diag 用（手机上看着改代码方便） */
    static String logText(int max) {
        StringBuilder sb = new StringBuilder(1024);
        synchronized (LOG) {
            int from = Math.max(0, LOG.size() - max);
            for (int i = from; i < LOG.size(); i++) {
                sb.append(LOG.get(i)).append('\n');
            }
        }
        return sb.toString();
    }

    // ------------------------------------------------------------ install

    static synchronized void install(Context ctx) {
        if (installed) {
            return;
        }
        try {
            Context app = ctx.getApplicationContext();
            if (!(app instanceof Application)) {
                lastError = "install: applicationContext is not Application";
                note(lastError);
                return;
            }
            ((Application) app).registerActivityLifecycleCallbacks(
                    new Application.ActivityLifecycleCallbacks() {
                        public void onActivityCreated(Activity a, Bundle b) {
                        }

                        public void onActivityStarted(Activity a) {
                        }

                        public void onActivityResumed(Activity a) {
                            grab(a);
                        }

                        public void onActivityPaused(Activity a) {
                        }

                        public void onActivityStopped(Activity a) {
                        }

                        public void onActivitySaveInstanceState(Activity a, Bundle b) {
                        }

                        public void onActivityDestroyed(Activity a) {
                            if (a == activity) {
                                activity = null;
                                helper = null;
                                note("helper released (activity destroyed)");
                            }
                        }
                    });
            installed = true;
            note("install ok, watching " + ACTIVITY_CLASS);
        } catch (Throwable t) {
            lastError = "install: " + t;
            note(lastError);
        }
    }

    /** Activity 一露面就把里面的引擎引用抓下来 */
    static void grab(Activity a) {
        if (a == null) {
            return;
        }
        String cn = a.getClass().getName();
        if (!cn.equals(ACTIVITY_CLASS) && !cn.startsWith(ACTIVITY_CLASS + "$")) {
            return;
        }
        activity = a;
        Object h = getField(a, HELPER_FIELD);
        if (h != null) {
            helper = h;
            helperAt = System.currentTimeMillis();
            note("helper bound: " + h.getClass().getName());
        } else {
            note("helper field '" + HELPER_FIELD + "' is null on " + cn);
        }
    }

    // --------------------------------------------------------- reflection

    static Object getField(Object target, String name) {
        if (target == null) {
            return null;
        }
        Class<?> c = target.getClass();
        while (c != null) {
            try {
                Field f = c.getDeclaredField(name);
                f.setAccessible(true);
                return f.get(target);
            } catch (NoSuchFieldException e) {
                c = c.getSuperclass();
            } catch (Throwable t) {
                lastError = "field " + name + ": " + t;
                note(lastError);
                return null;
            }
        }
        return null;
    }

    /** 用字段名找方法（同名可能重载，取第一个参数个数匹配的） */
    static Method findMethod(Object target, String name, int argc) {
        if (target == null) {
            return null;
        }
        Class<?> c = target.getClass();
        while (c != null) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.getName().equals(name) && m.getParameterTypes().length == argc) {
                    m.setAccessible(true);
                    return m;
                }
            }
            c = c.getSuperclass();
        }
        return null;
    }

    static Object call(Object target, String name, Object... args) {
        if (target == null) {
            lastError = "call " + name + ": target is null";
            return null;
        }
        try {
            Method m = findMethod(target, name, args == null ? 0 : args.length);
            if (m == null) {
                lastError = "call " + name + ": method not found";
                return null;
            }
            return m.invoke(target, args);
        } catch (Throwable t) {
            lastError = "call " + name + ": " + root(t);
            note(lastError);
            return null;
        }
    }

    /** 主线程同步执行（读卡片数据必须在主线程） */
    static Object onMainSync(final Callable<Object> task) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                return task.call();
            } catch (Throwable t) {
                lastError = "onMain: " + root(t);
                note(lastError);
                return null;
            }
        }
        final Object[] box = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        MAIN.post(new Runnable() {
            public void run() {
                try {
                    box[0] = task.call();
                } catch (Throwable t) {
                    box[0] = t;
                } finally {
                    latch.countDown();
                }
            }
        });
        try {
            latch.await(1800, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ignored) {
        }
        Object r = box[0];
        if (r instanceof Throwable) {
            lastError = "onMain: " + root((Throwable) r);
            note(lastError);
            return null;
        }
        return r;
    }

    static String root(Throwable t) {
        while (t.getCause() != null) {
            t = t.getCause();
        }
        return t.getClass().getSimpleName() + ": " + t.getMessage();
    }

    // ---------------------------------------------------------------- json

    static String esc(String s) {
        if (s == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(s.length() + 8);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (ch < 0x20) {
                        sb.append("\\u");
                        String hex = Integer.toHexString(ch);
                        for (int k = hex.length(); k < 4; k++) {
                            sb.append('0');
                        }
                        sb.append(hex);
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }

    static String q(String s) {
        return s == null ? "null" : "\"" + esc(s) + "\"";
    }
}
