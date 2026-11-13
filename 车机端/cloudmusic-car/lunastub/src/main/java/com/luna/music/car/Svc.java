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
import android.os.PowerManager;
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
    private PowerManager.WakeLock wake;
    /** 本包当前是否真的在提供遥控服务（占着 18765）。让位给第三方时为 false。 */
    private boolean active;

    /** 主动停服（切音源）标记：否则 onDestroy 会以为是「被杀」又用心跳把自己拉回来。 */
    private static volatile boolean wantStop;

    static void start(Context c) {
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

    static void stop(Context c) {
        wantStop = true;
        c.stopService(new Intent(c, Svc.class));
    }

    /** 角色只看端口：18765 已被别人监听，说明第三方播放器在提供服务，本包让位；
     *  端口空闲才由本包连官方 App 并开始服务。不再按包名/持久化模式猜，避免两边互顶。
     *  初始化不管炸在哪一步都不能闪退（官方 App 没装/刚卸载时 MediaBrowser、Asset、
     *  广播注册任意一环都会抛），整段 try-catch，失败只记日志，由 Alive 心跳兜底重试。 */
    @Override public void onCreate() {
        super.onCreate();
        try {
            if (Server.I.isRunning()) {
                active = true;
                Alive.schedule(this);
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
            try { foreground(false); } catch (Throwable ignored) {}
            active = false;
            Alive.schedule(this);
        }
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
        // 部分车机/手机在息屏后会把后台进程挂起，持有唤醒锁能明显降低被冻结的概率。
        // 只在还没有锁的时候建：端口被占时 isRunning() 为 false，onStartCommand 会反复
        // 调 initAll，每次都 new 一个 WakeLock 会把旧的引用顶掉、永远释放不掉。
        if (wake == null) {
            try {
                PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                if (pm != null) {
                    wake = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ncmremote:svc");
                    wake.setReferenceCounted(false);
                    wake.acquire();
                }
            } catch (Throwable t) {
                Log.w(Cfg.TAG, "wakelock failed", t);
            }
        }
        Log.i(Cfg.TAG, "service created, server=" + ok);
        // 三阶段分开记：端口探测（portTaken）、HTTP 监听（Server 已启动）、服务进入前台。
        // 「端口交接成功」不等于「服务交接成功」，分开记才能看出卡在哪一段。
        LogCapture.logAlways("SVC 进入前台，本包开始提供服务（端口 " + Cfg.PORT + "）");
        return true;
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        try {
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
            try { foreground(false); } catch (Throwable ignored) {}
        }
        return START_STICKY;
    }

    @Override public void onDestroy() {
        Server.I.stop();
        Nsd.I.unregister();
        Bridge.I.stopAll();
        try { if (wake != null && wake.isHeld()) wake.release(); } catch (Throwable ignored) {}
        wake = null;
        LogCapture.logAlways("SVC 已销毁，active=" + active);
        // 不是用户主动停服的话，留个心跳让它过一会儿自己回来。
        // 已经让位给第三方时本包本来就不干活，别每 15 分钟把它白拉起来一次。
        if (active && !wantStop) Alive.schedule(this);
        active = false;
        wantStop = false;
        super.onDestroy();
    }

    /** 从最近任务划掉不等于要停服务，重新拉一次。 */
    @Override public void onTaskRemoved(Intent rootIntent) {
        Svc.start(this);
        super.onTaskRemoved(rootIntent);
    }

    @Override public IBinder onBind(Intent intent) { return null; }

    private void foreground(boolean ok) {
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
                .setContentText(ok ? "http://" + Ip.local() + ":" + Cfg.PORT : "18765 端口被占用")
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
