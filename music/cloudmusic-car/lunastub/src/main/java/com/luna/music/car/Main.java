package com.luna.music.car;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * 入口页：横屏（1920×720）优化的方块按钮 + 小状态框。
 *
 * 音源只有两个候选：第三方自研播放器 {@code com.cloudmusic.car} 与官方网易云车机版。
 * 装了一个就直接用那一个，这里只显示结果；两个都装过才出现「官方网易云 / 第三方自研」两个
 * 切换钮（默认第三方自研，选择持久化保存，不会自动跳回去）。
 *
 * 布局：按钮全部靠上，一个一个小方块（不做全屏长条）；状态文字放在按钮下方的
 * 小方框里，超高可以滚动，不再占满整屏把按钮顶到屏幕外面。
 */
public class Main extends Activity {

    /** 1.x/2.4 那代独立探针，与合并包都占 18764，装着就必须卸掉。 */
    private static final String OLD_PROBE = "com.wb.ncmremote";

    /** 小方块高度（dp）。宽度由行内权重撑满，横屏不再挤在左边。 */
    private static final int SQUARE = 120;

    private TextView tvMini;
    private TextView tv;
    private TextView addrBar;
    private TextView carBar;
    private LinearLayout switchRow;
    private Button oldProbeBtn;
    private final Handler handler = new Handler(Looper.getMainLooper());

    /** 后台探测完成后回主线程刷一次界面 */
    private final Runnable afterProbe = new Runnable() {
        @Override public void run() {
            refresh();
        }
    };

    /** 周期刷新：切音源/服务重启后状态自动跟上，不用手动一遍遍点「刷新状态」。 */
    private final Runnable ticker = new Runnable() {
        @Override public void run() {
            refresh();
            handler.postDelayed(this, 2000);
        }
    };

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        try {
            buildUi();
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "main ui failed", t);
            toast("界面初始化失败：" + t);
        }
        try {
            Svc.start(this);
        } catch (Throwable t) {
            LogCapture.error("MAIN 启动服务失败：", t);
        }
        refresh();
        handler.post(ticker);
        // 900ms 后再刷一次拿服务起来的真实状态；用 handler 而不是 View.postDelayed，
        // 才能和 ticker 一起在 onDestroy 里统一移除，避免 Activity 销毁后还在碰视图。
        handler.postDelayed(new MainRefresh(this), 900);
    }

    @Override protected void onDestroy() {
        handler.removeCallbacks(ticker);
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /** 标题行 + 小方块按钮网格（靠上）+ 状态小方框（可滚动）。 */
    private void buildUi() {
        int pad = dp(14);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(pad, pad, pad, pad);
        root.setBackgroundColor(0xFFF7F8F5);

        // 标题行：左侧标题，右侧一行迷你状态
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);
        TextView title = new TextView(this);
        title.setText("设置");
        title.setTextSize(20);
        title.setTypeface(null, android.graphics.Typeface.BOLD);
        title.setTextColor(0xFF202620);
        header.addView(title);
        tvMini = new TextView(this);
        tvMini.setTextSize(14);
        tvMini.setTextColor(0xFF6E7569);
        tvMini.setSingleLine(true);
        LinearLayout.LayoutParams miniLp = new LinearLayout.LayoutParams(0, dp(30), 1f);
        miniLp.setMargins(dp(10), 0, 0, 0);
        tvMini.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        tvMini.setLayoutParams(miniLp);
        header.addView(tvMini);
        root.addView(header);

        // 按钮网格：全部靠上，一个小方块，不拉长条
        ScrollView scroll = new ScrollView(this);
        LinearLayout grid = new LinearLayout(this);
        grid.setOrientation(LinearLayout.VERTICAL);
        grid.addView(row(button(1, "启动桥接"), button(6, "启动音乐")));
        grid.addView(row(button(2, "重启"), button(5, "停止退出"), button(3, "刷新")));

        switchRow = new LinearLayout(this);
        switchRow.setOrientation(LinearLayout.HORIZONTAL);
        switchRow.setVisibility(View.GONE);
        switchRow.addView(square(7, "官方网易云"));
        switchRow.addView(square(8, "第三方自研"));
        grid.addView(switchRow);
        for (int n = 0; n < switchRow.getChildCount(); n++) {
            View v = switchRow.getChildAt(n);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, dp(SQUARE), 1f);
            lp.setMargins(dp(5), dp(5), dp(5), dp(5));
            v.setLayoutParams(lp);
        }

        oldProbeBtn = square(9, "卸载旧版\n单遥控 APK");
        oldProbeBtn.setVisibility(View.GONE);
        grid.addView(oldProbeBtn, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(SQUARE)));

        // 状态小方框：放在按钮下面，超高滚动，不占整屏
        tv = new TextView(this);
        tv.setTextSize(15);
        tv.setTextColor(0xFF3F4A42);
        tv.setBackground(card(0xFFFFFFFF, 0xFFE4E7E1));
        tv.setPadding(dp(12), dp(10), dp(12), dp(10));
        tv.setGravity(Gravity.TOP | Gravity.START);
        LinearLayout body = new LinearLayout(this);
        body.setOrientation(LinearLayout.VERTICAL);
        body.addView(grid);
        // 地址栏：只显示，不响应点击（车机屏幕本来就不好选中，复制没意义）
        addrBar = new TextView(this);
        addrBar.setTextSize(16);
        addrBar.setTextColor(0xFF40584A);
        addrBar.setSingleLine(true);
        addrBar.setBackground(card(0xFFFFFFFF, 0xFFE4E7E1));
        addrBar.setPadding(dp(12), dp(8), dp(12), dp(8));
        body.addView(addrBar, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(46)));
        // 车控地址：18766 由车机助手常驻提供，和音乐这条线互不影响
        carBar = new TextView(this);
        carBar.setTextSize(16);
        carBar.setTextColor(0xFF55705F);
        carBar.setSingleLine(true);
        carBar.setBackground(card(0xFFFFFFFF, 0xFFE4E7E1));
        carBar.setPadding(dp(12), dp(8), dp(12), dp(8));
        LinearLayout.LayoutParams carLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(46));
        carLp.setMargins(0, dp(6), 0, 0);
        body.addView(carBar, carLp);
        body.addView(tv, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(220)));
        scroll.addView(body);
        LinearLayout.LayoutParams scLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
        scLp.setMargins(0, dp(6), 0, 0);
        scroll.setLayoutParams(scLp);
        root.addView(scroll);

        setContentView(root);
    }

    /** 一行最多三个小方块；权重撑满整行宽度，不再挤在左边。 */
    private LinearLayout row(Button... bs) {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < bs.length; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, dp(SQUARE), 1f);
            lp.setMargins(dp(5), dp(5), dp(5), dp(5));
            bs[i].setLayoutParams(lp);
            r.addView(bs[i]);
        }
        return r;
    }

    /** 白色圆角卡片：跟小绿传一个规格（18dp 圆角 + 1dp 细描边） */
    private Drawable card(int fill, int stroke) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(fill);
        g.setCornerRadius(dp(18));
        if (stroke != 0) g.setStroke(Math.max(1, dp(1)), stroke);
        return g;
    }

    /** 纯色圆角底 */
    private Drawable tileBg(int color) {
        return card(color, 0);
    }

    /** 按下变亮、松开复原：不然点了像没反应 */
    private Drawable tileState() {
        StateListDrawable d = new StateListDrawable();
        d.addState(new int[]{android.R.attr.state_pressed}, card(0xFFE7EEE8, 0xFFE4E7E1));
        d.addState(new int[]{}, card(0xFFFFFFFF, 0xFFE4E7E1));
        return d;
    }

    /** 固定小方块按钮：短文本两行放得下，横屏也好点。 */
    private Button square(int code, String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextSize(20);
        b.setTextColor(0xFF2E4338);
        b.setTypeface(null, android.graphics.Typeface.BOLD);
        b.setBackground(tileState());
        b.setGravity(Gravity.CENTER);
        b.setAllCaps(false);
        b.setOnClickListener(new MainClick(this, code));
        return b;
    }

    private Button button(int code, String text) {
        return square(code, text);
    }

    /** 把车机助手拉起来：它的进程一起来，ContentProvider 就会把 18766 绑上 */
    void launchNavi() {
        try {
            Intent i = getPackageManager().getLaunchIntentForPackage("com.navi.tool");
            if (i == null) {
                toast("车机助手没装，或没有启动入口");
                return;
            }
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            toast("已拉起车机助手，稍等再看 18766");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    CarPort.invalidate();
                    refresh();
                    CarPort.refreshAsync(new Runnable() {
                        public void run() {
                            refresh();
                            toast(CarPort.alive()
                                    ? "18766 已通，车控可以用了"
                                    : "还是没通，车控页 → 设置 → 诊断");
                        }
                    });
                }
            }, 3000L);
        } catch (Throwable t) {
            toast("拉起失败：" + t);
        }
    }

    private int dp(int v) {
        Resources r = getResources();
        return Math.round(v * r.getDisplayMetrics().density);
    }

    void refresh() {
        if (tv == null || tvMini == null) return;
        // 角色：本包占着 18764 就是它在服务；音源是第三方、或用户手动停过，本包都不干活
        boolean running = Server.I.isRunning();
        boolean yielded = Server.I.yielded;
        boolean third = Mode.isThirdParty(this);
        boolean stopped = Svc.isStopped(this);
        String role = running ? "运行中"
                : (third || yielded) ? "第三方托管"
                : stopped ? "已停止" : "未运行";
        tvMini.setText(Mode.name(this) + (Mode.both(this) ? "（可切换）" : "（自动）")
                + "　音乐：" + role + "　车控：" + (CarPort.alive() ? "在线" : "离线"));
        StringBuilder sb = new StringBuilder();
        // 只显示一个主地址：以前把几块网卡的地址用 " / " 拼成一串，
        // 手机上根本认不出该输哪个
        String addr = Ip.local();
        if (addr == null || addr.length() == 0) {
            String[] ips = Ip.all();
            addr = ips.length > 0 ? ips[0] : "本机";
        }
        boolean carOk = CarPort.alive();
        if (carBar != null) {
            carBar.setText("车控 18766：" + (carOk ? "在线" : "未响应") + "　" + CarPort.url());
        }
        // 两条线各说一句：车控常驻，音乐随音源启停
        sb.append("车控 18766：").append(carOk ? "常驻在线（车机助手提供）" : "未响应（车机助手没起来？）").append("\n");
        sb.append("探测：").append(CarPort.detail()).append("\n");
        sb.append("音乐 18764：").append(running ? "运行中（本包提供）" : (third || yielded) ? "第三方提供" : "未运行").append("\n");
        sb.append("────────────\n");
        if (!Mode.hasOfficial(this) && !Mode.hasThirdParty(this)) {
            sb.append("车上没找到可用音源。请先安装官方网易云车机版，或第三方自研播放器。");
        } else if (running) {
            sb.append("服务：运行中")
                    .append("　连接数：").append(Server.I.clientCount()).append("\n")
                    .append(Ncm.I.describe()).append("\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT).append("\n")
                    .append("歌单：").append(Arrays.toString(Ncm.I.playlists()));
        } else if (third) {
            sb.append("服务：交给第三方自研播放器\n")
                    .append("当前音源是第三方，本包不占 18764；心跳和开机也不会再拉起它\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT)
                    .append("（由第三方提供）");
        } else if (stopped) {
            sb.append("服务：已停止（你手动停的）\n")
                    .append("心跳已撤销、18764 已关闭，不会自动拉起\n")
                    .append("点「启动桥接」恢复桥接\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT)
                    .append("（当前无人监听）");
        } else if (yielded) {
            sb.append("服务：交给第三方自研播放器\n")
                    .append("18764 已被占用，本包自动让位，不抢端口\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT)
                    .append("（由第三方提供）");
        } else {
            sb.append("服务：未运行\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT)
                    .append("\n点「启动桥接」接管端口");
        }
        if (Mode.installed(this, OLD_PROBE)) {
            sb.append("\n⚠ 车上还装着旧版单遥控 APK（com.wb.ncmremote），")
                    .append("它和本包争同一个 18764 端口，请按下面按钮卸掉。");
        }
        if (addrBar != null) {
            String base = "http://" + addr + ":" + Cfg.PORT;
            String tag = running ? "" : ((third || yielded) ? "（第三方提供）" : "");
            addrBar.setText("地址：" + base + tag);
        }
        tv.setText(sb.toString());
        if (oldProbeBtn != null) {
            oldProbeBtn.setVisibility(Mode.installed(this, OLD_PROBE) ? View.VISIBLE : View.GONE);
        }

        // 探测绝不在 UI 线程做：连不上时每个地址要等好几百毫秒，
        // 放在这里会让整个界面连按下态都画不出来。
        CarPort.refreshAsync(afterProbe);

        switchRow.setVisibility(Mode.both(this) ? View.VISIBLE : View.GONE);
        // 高亮跟着「当前音源」走，不再跟「谁占着端口」走：停着的时候也看得出选的是哪个
        for (int n = 0; n < switchRow.getChildCount(); n++) {
            View v = switchRow.getChildAt(n);
            if (!(v instanceof Button)) continue;
            boolean active = (n == 0) == !third;
            v.setBackground(active ? tileBg(0xFF40584A) : card(0xFFFFFFFF, 0xFFE4E7E1));
            ((Button) v).setTextColor(active ? 0xFFFFFFFF : 0xFF6E7569);
        }
    }

    void click(int code) {
        try {
            if (code == 1) {
                // 当前音源是第三方时本包不该占端口，点了也只解释一句，免得又抢回去
                if (Mode.isThirdParty(this)) {
                    toast("当前音源是第三方自研，音乐控制交给它（18765）；要桥接官方请先切「官方网易云」");
                } else {
                    Svc.startByUser(this);
                }
            } else if (code == 2) {
                // 重启：音乐这边是本地重复启停，车控那边发 /restart 让车机助手重抓引擎；
                // 18766 完全不通时再拉起车机助手（车机上没法自己清后台，只能这样兜）
                Svc.stop(this);
                toast("正在重启：音乐 + 车控");
                handler.postDelayed(new MainRestart(this), 900L);
                CarPort.restartRemoteAsync(new Runnable() {
                    public void run() {
                        toast("车控 18766 已重启");
                        CarPort.invalidate();
                        refresh();
                    }
                }, new Runnable() {
                    public void run() {
                        toast("车控没响应，正在拉起车机助手");
                        launchNavi();
                    }
                });
            } else if (code == 3) {
                // 「刷新状态」之前没有分支，点了没反应
                CarPort.invalidate();
                refresh();
            } else if (code == 5) {
                // 车机没有可靠的任务管理器，退出得由 App 自己收尾：停服、关端口、移出任务栈
                Svc.stop(this);
                toast("已停止音乐桥接，正在退出…");
                handler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            finishAndRemoveTask();
                        } catch (Throwable t) {
                            finish();
                        }
                    }
                }, 700L);
                return;      // 退出中不再刷新，免得又去探测、又把界面画一遍
            } else if (code == 6) {
                startMusic();
            } else if (code == 10) {
                openCarPage();
            } else if (code == 11) {
                CarPort.invalidate();
                refresh();
            } else if (code == 7) {
                switchTo(Mode.OFFICIAL);
                return;
            } else if (code == 8) {
                switchTo(Mode.THIRD_PARTY);
                return;
            } else if (code == 9) {
                uninstallOld();
            }
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "click failed code=" + code, t);
            toast("操作失败：" + t);
        }
        refresh();
    }

    /** 切音源：存下选择，停掉旧服务，再按新模式拉起来。选择持久化，不会自动跳回。 */
    private void switchTo(int target) {
        if (target == Mode.OFFICIAL && !Mode.hasOfficial(this)) {
            toast("没装官方网易云车机版");
            return;
        }
        if (target == Mode.THIRD_PARTY && !Mode.hasThirdParty(this)) {
            toast("没装第三方自研播放器");
            return;
        }
        if (Mode.current(this) == target) return;
        Mode.save(this, target);
        // 同步给第三方 App：它按新模式自己启停 RemoteServer
        ModeSync.notify(this, target);
        // 停服会撤心跳 + 立刻关 18764，端口马上还给系统
        Svc.stop(this);
        LogCapture.logAlways("MAIN 切换音源 → " + Mode.label(target));
        if (target == Mode.THIRD_PARTY) {
            // 切到第三方后本包彻底退出，不能 600ms 后又去把端口抢回来
            toast("已切到「" + Mode.label(target) + "」，音乐服务交给它，本包不再自动拉起");
            refresh();
            return;
        }
        toast("已切回官方，桥接服务正在重新接管 18764");
        refresh();
        handler.postDelayed(new MainRestart(this), 600L);
    }

    /** 卸掉旧的独立探针包，避免两个包抢 18764。 */
    private void uninstallOld() {
        try {
            startActivity(new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + OLD_PROBE)));
        } catch (Throwable t) {
            toast("请在系统设置里卸载 " + OLD_PROBE);
        }
    }

    void openMusicApp() {
        String pkg = Mode.isOfficial(this) ? Mode.OFFICIAL_PKG : Mode.THIRD_PARTY_PKG;
        Intent intent = getPackageManager().getLaunchIntentForPackage(pkg);
        if (intent == null) {
            toast("打不开：" + pkg);
            return;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        try {
            startActivity(intent);
        } catch (Throwable t) {
            toast("打不开：" + pkg);
        }
    }

    /**
     * 启动音乐：把播放器叫到前台，再补一个播放键。
     *
     * 只打开 App 不够——上次是暂停状态的话，打开还是暂停。
     * 媒体键两条线的播放器都认，比猜它的 Activity 靠谱。
     */
    void startMusic() {
        openMusicApp();
        handler.postDelayed(new Runnable() {
            public void run() {
                mediaPlay();
            }
        }, 900L);
    }

    void mediaPlay() {
        try {
            android.media.AudioManager am =
                    (android.media.AudioManager) getSystemService(AUDIO_SERVICE);
            if (am == null) {
                return;
            }
            am.dispatchMediaKeyEvent(new android.view.KeyEvent(
                    android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_MEDIA_PLAY));
            am.dispatchMediaKeyEvent(new android.view.KeyEvent(
                    android.view.KeyEvent.ACTION_UP, android.view.KeyEvent.KEYCODE_MEDIA_PLAY));
        } catch (Throwable t) {
            Log.w(Cfg.TAG, "mediaPlay failed", t);
        }
    }

    /** 打开车控页：18766 由车机助手常驻提供，跟音乐模式无关。 */
    void openCarPage() {
        try {
            String ip = Ip.local();
            if (ip == null || ip.length() == 0) ip = "127.0.0.1";
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + ip + ":18766/"));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } catch (Throwable t) {
            toast("没有可用浏览器，手动打开 http://" + (Ip.local() == null ? "127.0.0.1" : Ip.local()) + ":18766/");
        }
    }

    void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}


/**
 * 18766 活着没有。
 *
 * 不能只探 127.0.0.1：部分车机把应用间回环连接拦掉了（浏览器能开、Socket 探不到），
 * 会误报「离线」。所以两个都试 —— 先回环，再本机局域网 IP。
 */
final class CarPort {

    static final int PORT = 18766;

    private static volatile long at = 0L;
    private static volatile boolean alive = false;
    private static volatile boolean busy = false;
    private static volatile String detail = "";
    private static final android.os.Handler UI =
            new android.os.Handler(android.os.Looper.getMainLooper());

    private CarPort() {
    }

    static void invalidate() {
        at = 0L;
    }

    /** 探通过的地址记下来，下次先试它 */
    private static volatile String good = null;

    /** 上次探测结果（主线程只读这个，不做任何网络操作） */
    static boolean alive() {
        return alive;
    }

    /** 每个地址试出来的结果，显示在状态框里：「172.16.10.4 通 · 127.0.0.1 ConnectException」 */
    static String detail() {
        String d = detail;
        return d == null || d.length() == 0 ? "检测中…" : d;
    }

    /**
     * 探测放后台线程。
     *
     * 以前是在 refresh() 里同步 connect：连不上时每个地址要等 600ms，几个地址叠起来
     * 主线程就卡住，按钮连按下态都画不出来 —— 用户看到的就是「点了没反应」。
     * 而且一旦 connect 抛异常（部分车机禁应用间连本机端口），同步版本只能吞成离线。
     */
    static void refreshAsync(final Runnable onMain) {
        long now = System.currentTimeMillis();
        if (now - at < 3000L || busy) {
            return;
        }
        at = now;
        busy = true;
        new Thread(new Runnable() {
            public void run() {
                try {
                    doProbe();
                } catch (Throwable t) {
                    detail = "探测异常：" + t.getClass().getSimpleName();
                } finally {
                    busy = false;
                }
                if (onMain != null) {
                    UI.post(onMain);
                }
            }
        }, "car-probe").start();
    }

    private static void doProbe() {
        StringBuilder d = new StringBuilder();
        boolean ok = false;

        // 上次通过的先试，省一次网卡枚举
        String g = good;
        if (g != null && g.length() > 0) {
            String e = probe(g);
            ok = (e == null);
            d.append(g).append(ok ? " 通" : " " + e);
        }

        // 回环 → 全部网卡地址。两个进程看到的网卡顺序不一定一样，不能只试第一个。
        if (!ok) {
            String[] cand = candidates();
            for (int i = 0; i < cand.length; i++) {
                String h = cand[i];
                if (h.equals(g)) {
                    continue;
                }
                String e = probe(h);
                boolean r = (e == null);
                if (d.length() > 0) {
                    d.append(" · ");
                }
                d.append(h).append(r ? " 通" : " " + e);
                if (r) {
                    good = h;
                    ok = true;
                    break;
                }
            }
        }

        alive = ok;
        detail = d.length() == 0 ? "没有可用地址" : d.toString();
    }

    /** 127.0.0.1 打头，后面是本机所有局域网 IPv4 */
    static String[] candidates() {
        String[] ips = Ip.all();
        String[] out = new String[ips.length + 1];
        out[0] = "127.0.0.1";
        System.arraycopy(ips, 0, out, 1, ips.length);
        return out;
    }

    /** 页面上「车控」那行显示的实际地址 */
    static String url() {
        String g = good;
        if (g == null || g.length() == 0) {
            g = Ip.local();
        }
        if (g == null || g.length() == 0) g = "127.0.0.1";
        return "http://" + g + ":" + PORT + "/";
    }

    /**
     * 让 18766 自己软重启（重抓引擎 + 再要一次前台服务）。
     * 通了调 onOk；一个地址都不通才调 onFail——那时只能让上层去拉起车机助手。
     */
    static void restartRemoteAsync(final Runnable onOk, final Runnable onFail) {
        new Thread(new Runnable() {
            public void run() {
                boolean ok = false;
                String g = good;
                if (g != null && g.length() > 0 && httpGet(g, "/restart")) {
                    ok = true;
                } else {
                    String[] cand = candidates();
                    for (int i = 0; i < cand.length && !ok; i++) {
                        if (cand[i].equals(g)) {
                            continue;
                        }
                        if (httpGet(cand[i], "/restart")) {
                            good = cand[i];
                            ok = true;
                        }
                    }
                }
                if (ok) {
                    at = 0L;            // 让下一次探测真的跑一遍
                }
                UI.post(ok ? onOk : onFail);
            }
        }, "car-restart").start();
    }

    /** 极简 GET：只关心有没有 200，body 读掉就扔 */
    private static boolean httpGet(String host, String path) {
        java.net.HttpURLConnection c = null;
        try {
            c = (java.net.HttpURLConnection) new java.net.URL(
                    "http://" + host + ":" + PORT + path).openConnection();
            c.setConnectTimeout(900);
            c.setReadTimeout(2500);     // /restart 里要重抓一次引擎，给它慢一点
            c.setRequestMethod("GET");
            c.setUseCaches(false);
            int code = c.getResponseCode();
            java.io.InputStream in = code == 200 ? c.getInputStream() : c.getErrorStream();
            StringBuilder body = new StringBuilder(128);
            if (in != null) {
                byte[] buf = new byte[256];
                int n;
                while ((n = in.read(buf)) > 0 && body.length() < 1024) {
                    body.append(new String(buf, 0, n, "UTF-8"));
                }
                in.close();
            }
            // 只看 200 不够：/restart 里 helper 没绑上会回 ok:0，那种情况得去拉起车机助手
            return code == 200 && body.indexOf("\"ok\":1") >= 0;
        } catch (Throwable t) {
            return false;
        } finally {
            if (c != null) {
                c.disconnect();
            }
        }
    }

    /** 通返回 null，不通返回异常简称（ConnectException / SocketTimeoutException / EACCES…） */
    private static String probe(String host) {
        java.net.Socket sock = null;
        try {
            sock = new java.net.Socket();
            sock.connect(new java.net.InetSocketAddress(host, PORT), 600);
            return null;
        } catch (Throwable t) {
            String n = t.getClass().getSimpleName();
            if (n == null || n.length() == 0) {
                n = "失败";
            }
            // 带上原因，"Permission denied" 和 "Connection refused" 是两回事
            String m = t.getMessage();
            if (m != null && m.length() > 0) {
                if (m.length() > 28) {
                    m = m.substring(0, 28);
                }
                n = n + " " + m;
            }
            return n;
        } finally {
            try {
                if (sock != null) {
                    sock.close();
                }
            } catch (Throwable ignored) {
            }
        }
    }
}

class MainRefresh implements Runnable {
    private final Main a;
    MainRefresh(Main a) { this.a = a; }
    @Override public void run() { a.refresh(); }
}

class MainRestart implements Runnable {
    private final Main a;
    MainRestart(Main a) { this.a = a; }
    @Override public void run() {
        // 切回官方：清掉「用户停服」标记后重新接管（端口空闲才会真的绑上）
        Svc.startByUser(a);
        a.refresh();
    }
}

class MainClick implements View.OnClickListener {
    private final Main a;
    private final int code;
    MainClick(Main a, int code) { this.a = a; this.code = code; }
    @Override public void onClick(View v) { a.click(code); }
}

/** 地址栏点击：把「地址：http://x.x.x.x:18764」里的完整地址复制到剪贴板。 */
