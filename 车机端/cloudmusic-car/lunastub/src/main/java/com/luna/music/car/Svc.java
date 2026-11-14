package com.luna.music.car;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/** 常驻前台服务：绑定官方 App + 18765 遥控服务 + NSD 发现。 */
public class Svc extends Service {
    private static final String CHANNEL = "ncmremote";
    private static final int NOTIF_ID = 18765;
    /** 本包当前是否真的在提供遥控服务（占着 18765）。让位给第三方时为 false。 */
    private boolean active;

    /** 主动停服（切音源）标记：否则 onDestroy 会以为是「被杀」又用心跳把自己拉回来。 */
    private static volatile boolean wantStop;

    /** 用户手动停服的持久标记：停过就不再被心跳 / 开机 / 进程重建 / 入口页自动拉起。
     *  只靠内存里的 wantStop 不够——进程被杀之后闹钟广播照样会来，服务又会被拉起来抢端口。 */
    private static final String SP = "svc-state";
    private static final String K_STOPPED = "stopped";

    static boolean isStopped(Context c) {
        try {
            return c.getSharedPreferences(SP, Context.MODE_PRIVATE).getBoolean(K_STOPPED, false);
        } catch (Throwable t) {
            return false;
        }
    }

    private static void setStopped(Context c, boolean v) {
        try {
            c.getSharedPreferences(SP, Context.MODE_PRIVATE).edit().putBoolean(K_STOPPED, v).apply();
        } catch (Throwable ignored) {}
    }

    /** 自动拉起是否允许：用户手动停过、或当前音源是第三方，都不允许。 */
    static boolean autoAllowed(Context c) {
        return !isStopped(c) && !Mode.isThirdParty(c);
    }

    /** 自动路径（心跳 / 开机 / 进程重建 / 入口页）：停过或音源是第三方就不拉起。 */
    static void start(Context c) {
        if (!autoAllowed(c)) {
            String why = isStopped(c) ? "用户已手动停服" : "当前音源是第三方";
            Log.i(Cfg.TAG, "start 跳过：" + why);
            LogCapture.logAlways("SVC 跳过启动：" + why);
            return;
        }
        startNow(c);
    }

    /** 用户点「启动 / 重启服务」、切回官方：清掉停服标记后强制拉起。 */
    static void startByUser(Context c) {
        setStopped(c, false);
        if (Mode.isThirdParty(c)) {
            Log.i(Cfg.TAG, "startByUser 跳过：当前音源是第三方");
            LogCapture.logAlways("SVC 跳过启动：当前音源是第三方，端口继续交给它");
            return;
        }
        startNow(c);
    }

    private static void startNow(Context c) {
        wantStop = false;
        Intent i = new Intent(c, Svc.class);
        try {
            if (Build.VERSION.SDK_INT >= 26) c.startForegroundService(i);
            else c.startService(i);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "start service failed", t);
            LogCapture.logAlways("SVC start 失败：" + t);
        }
    }

    /** 真停服要三件事一起做：撤心跳（否则 15 分钟后自己回来）、立刻关监听 socket
     *  （不等 onDestroy，端口马上还给系统、第三方立刻能绑）、再 stopService。
     *  同时落持久标记，进程被杀后心跳广播来了也不会再拉起来。 */
    static void stop(Context c) {
        wantStop = true;
        setStopped(c, true);
        Alive.cancel(c);
        Server.I.stop();
        Nsd.I.unregister();
        Bridge.I.stopAll();
        Log.i(Cfg.TAG, "主动停服：心跳已撤、端口已关");
        LogCapture.logAlways("SVC 主动停服：撤心跳 + 关 18765 + stopService");
        try { c.stopService(new Intent(c, Svc.class)); } catch (Throwable ignored) {}
    }

    /** 启动条件只看两件事：用户没停服、音源是官方。开关开着就一直提供服务，不按网络起停。 */
    @Override public void onCreate() {
        super.onCreate();
        try {
            if (!autoAllowed(this)) {
                standDown(isStopped(this) ? "用户已手动停服" : "当前音源是第三方", Mode.isThirdParty(this));
                return;
            }
            if (Server.I.isRunning()) {
                active = true;
                return;
            }
            if (Server.portTaken()) {
                yieldToThirdParty("onCreate");
                return;
            }
            active = initAll();
            if (active) Alive.schedule(this);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "service init failed（已兜底，不闪退）", t);
            LogCapture.logAlways("SVC onCreate 异常：" + t);
            try { foreground(false, "启动失败，稍后重试"); } catch (Throwable ignored) {}
            active = false;
        }
    }

    /** 本次不提供服务（用户手动停服 / 音源是第三方）：不绑端口，直接退场。
     *  仍要先 foreground 满足 startForegroundService 的 5 秒契约，否则系统按 ANR 杀进程。 */
    private void standDown(String why, boolean thirdParty) {
        Log.i(Cfg.TAG, why + "，本包不提供服务");
        LogCapture.logAlways("SVC 不提供服务：" + why);
        active = false;
        Server.I.yielded = thirdParty;
        try { foreground(false); } catch (Throwable ignored) {}
        stopSelf();
    }

    /** 让位给第三方：本包不提供服务。注意必须先调用 startForeground 满足
     *  startForegroundService 的 5 秒契约，否则系统会按 ANR 直接杀掉进程（表现为闪退）。 */
    private void yieldToThirdParty(String where) {
        Log.i(Cfg.TAG, where + ": 18765 已被占用，判定第三方音源在服务，本包让位");
        LogCapture.logAlways("SVC " + where + " 让位：18765 被第三方占用");
        active = false;
        Server.I.yielded = true;
        try { foreground(false); } catch (Throwable ignored) {}
        stopSelf();
    }

    /** 官方模式下的全套初始化；各组件内部自带 try-catch，这里兜最后一层。
     *  先抢端口再连官方：端口都拿不到就没必要连 MediaBrowser 了。 */
    private boolean initAll() {
        boolean ok = Server.I.start(this);
        if (!ok) {
            active = false;
            Server.I.yielded = true;
            return false;
        }
        Server.I.yielded = false;
        Ncm.I.init(this);
        Nsd.I.register(this);
        Bridge.I.start(this);
        foreground(true);
        Log.i(Cfg.TAG, "service created, server=" + ok);
        // 三阶段分开记：端口探测（portTaken）、HTTP 监听（Server 已启动）、服务进入前台。
        // 「端口交接成功」不等于「服务交接成功」，分开记才能看出卡在哪一段。
        LogCapture.logAlways("SVC 进入前台，本包开始提供服务（端口 " + Cfg.PORT + "）");
        return true;
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            if (!autoAllowed(this)) {
                standDown(isStopped(this) ? "用户已手动停服" : "当前音源是第三方", Mode.isThirdParty(this));
                return START_NOT_STICKY;
            }
            if (Server.I.isRunning()) {
                active = true;
                return START_STICKY;
            }
            if (Server.portTaken()) {
                yieldToThirdParty("onStartCommand");
                return START_NOT_STICKY;
            }
            active = initAll();
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "onStartCommand failed（已兜底，不闪退）", t);
            LogCapture.logAlways("SVC onStartCommand 异常：" + t);
            // startForegroundService 要求 5 秒内必须 startForeground，
            // 初始化失败也要先满足契约，否则系统按 ANR 直接杀进程。
            try { foreground(false, "启动失败，稍后重试"); } catch (Throwable ignored) {}
        }
        return START_STICKY;
    }

    @Override public void onDestroy() {
        Server.I.stop();
        Nsd.I.unregister();
        Bridge.I.stopAll();
        LogCapture.logAlways("SVC 已销毁，active=" + active + "，stopped=" + isStopped(this));
        // 用户手动停过：连心跳一起撤掉，别让它在 15 分钟后把自己拉回来抢端口。
        // 不是主动停服的话，留个心跳让它过一会儿自己回来；已让位给第三方时本来就不干活，不必再排。
        if (isStopped(this) || Mode.isThirdParty(this)) Alive.cancel(this);
        else if (active && !wantStop) Alive.schedule(this);
        else Alive.cancel(this);
        active = false;
        wantStop = false;
        super.onDestroy();
    }

    /** 从最近任务划掉不等于要停服务，重新拉一次；但用户手动停服 / 音源是第三方时不许拉。 */
    @Override public void onTaskRemoved(Intent rootIntent) {
        if (!autoAllowed(this)) {
            Log.i(Cfg.TAG, "onTaskRemoved 不重启服务：用户停服或音源是第三方");
            super.onTaskRemoved(rootIntent);
            return;
        }
        Svc.start(this);
        super.onTaskRemoved(rootIntent);
    }

    @Override public IBinder onBind(Intent intent) { return null; }

    private void foreground(boolean ok) {
        foreground(ok, ok ? null : "18765 端口被占用");
    }

    /** text 不为空时用它做副标题：热点待机 / 启动失败 / 端口被占。 */
    private void foreground(boolean ok, String text) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm != null) {
                NotificationChannel ch = new NotificationChannel(CHANNEL, "网易云遥控", NotificationManager.IMPORTANCE_LOW);
                nm.createNotificationChannel(ch);
            }
        }
        Notification.Builder b = Build.VERSION.SDK_INT >= 26
                ? new Notification.Builder(this, CHANNEL) : new Notification.Builder(this);
        // targetSdk 31+ 在 Android 12+ 上必须带 IMMUTABLE/MUTABLE，否则直接抛异常闪退
        int piFlags = PendingIntent.FLAG_UPDATE_CURRENT
                | (Build.VERSION.SDK_INT >= 23 ? PendingIntent.FLAG_IMMUTABLE : 0);
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Main.class), piFlags);
        Notification n = b.setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle("网易云遥控")
                .setContentText(text != null ? text : "http://" + Ip.local() + ":" + Cfg.PORT)
                .setContentIntent(pi)
                .setOngoing(true)
                .build();
        startForeground(NOTIF_ID, n);
    }
}

/** mDNS 注册：手机端靠 _cloudmusic-car._tcp 自动发现车机，端口不变。 */
final class Nsd {
    static final Nsd I = new Nsd();
    private NsdManager mgr;
    private NsdManager.RegistrationListener reg;
    private WifiManager.MulticastLock lock;
    private Nsd() {}

    void register(Context c) {
        if (mgr != null) return;
        try {
            WifiManager wifi = (WifiManager) c.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                lock = wifi.createMulticastLock("ncmremote");
                lock.setReferenceCounted(true);
                lock.acquire();
            }
            mgr = (NsdManager) c.getSystemService(Context.NSD_SERVICE);
            NsdServiceInfo info = new NsdServiceInfo();
            info.setServiceName(Cfg.NSD_NAME);
            info.setServiceType(Cfg.NSD_TYPE);
            info.setPort(Cfg.PORT);
            reg = new RegListener();
            mgr.registerService(info, NsdManager.PROTOCOL_DNS_SD, reg);
            Log.i(Cfg.TAG, "NSD registered " + Cfg.NSD_TYPE + ":" + Cfg.PORT);
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "NSD register failed", t);
        }
    }

    void unregister() {
        try { if (mgr != null && reg != null) mgr.unregisterService(reg); } catch (Throwable ignored) {}
        try { if (lock != null && lock.isHeld()) lock.release(); } catch (Throwable ignored) {}
        mgr = null;
        reg = null;
        lock = null;
    }
}

class RegListener implements NsdManager.RegistrationListener {
    @Override public void onRegistrationFailed(NsdServiceInfo info, int code) {
        Log.w(Cfg.TAG, "NSD registration failed " + code);
    }
    @Override public void onUnregistrationFailed(NsdServiceInfo info, int code) {}
    @Override public void onServiceRegistered(NsdServiceInfo info) {
        Log.i(Cfg.TAG, "NSD registered as " + info.getServiceName());
    }
    @Override public void onServiceUnregistered(NsdServiceInfo info) {}
}

/** 局域网 IPv4，优先 wlan / ap 网卡。 */
final class Ip {
    static String local() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            String best = "";
            while (nis != null && nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) continue;
                Enumeration<java.net.InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    java.net.InetAddress a = addrs.nextElement();
                    if (!(a instanceof Inet4Address) || !a.isSiteLocalAddress()) continue;
                    String ip = a.getHostAddress();
                    if (ip == null) continue;
                    String n = ni.getName().toLowerCase();
                    if (n.startsWith("wlan") || n.startsWith("ap")) return ip;
                    if (best.length() == 0) best = ip;
                }
            }
            return best;
        } catch (Throwable t) { return ""; }
    }

    /** 所有网卡的全部局域网 IPv4：车机可能同时有热点 / 无线 / 有线，全列出来让手机逐个试。 */
    static String[] all() {
        try {
            List<String> ips = new ArrayList<String>();
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis != null && nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (!ni.isUp() || ni.isLoopback() || ni.isVirtual()) continue;
                Enumeration<java.net.InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    java.net.InetAddress a = addrs.nextElement();
                    if (!(a instanceof Inet4Address) || !a.isSiteLocalAddress()) continue;
                    String ip = a.getHostAddress();
                    if (ip != null && !ips.contains(ip)) ips.add(ip);
                }
            }
            return ips.toArray(new String[0]);
        } catch (Throwable t) { return new String[0]; }
    }
    private Ip() {}
}
