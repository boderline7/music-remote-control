package com.luna.music.car;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
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
 * 切换钮（默认官方车机，选择持久化保存，不会自动跳回去）。
 *
 * 布局：按钮全部靠上，一个一个小方块（不做全屏长条）；状态文字放在按钮下方的
 * 小方框里，超高可以滚动，不再占满整屏把按钮顶到屏幕外面。
 */
public class Main extends Activity {

    /** 1.x/2.4 那代独立探针，与合并包都占 18765，装着就必须卸掉。 */
    private static final String OLD_PROBE = "com.wb.ncmremote";

    /** 小方块高度（dp）。宽度由行内权重撑满，横屏不再挤在左边。 */
    private static final int SQUARE = 120;

    private TextView tvMini;
    private TextView tv;
    private TextView addrBar;
    private LinearLayout switchRow;
    private Button oldProbeBtn;
    private final Handler handler = new Handler(Looper.getMainLooper());

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
        int pad = dp(12);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(pad, pad, pad, pad);
        root.setBackgroundColor(0xFF000000);

        // 标题行：左侧标题，右侧一行迷你状态
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);
        TextView title = new TextView(this);
        title.setText("网易云遥控");
        title.setTextSize(24);
        title.setTextColor(0xFFFFFFFF);
        header.addView(title);
        tvMini = new TextView(this);
        tvMini.setTextSize(15);
        tvMini.setTextColor(0xFF8E8E93);
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
        grid.addView(row(button(1, "启动 / 重启服务"), button(3, "刷新状态"), button(4, "停止服务")));
        grid.addView(row(button(6, "打开音乐 App")));

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
        tv.setTextSize(16);
        tv.setTextColor(0xFFEDEDF2);
        tv.setBackgroundColor(0xFF141416);
        tv.setPadding(dp(8), dp(6), dp(8), dp(6));
        tv.setGravity(Gravity.TOP | Gravity.START);
        LinearLayout body = new LinearLayout(this);
        body.setOrientation(LinearLayout.VERTICAL);
        body.addView(grid);
        // 地址栏：一行一个，点一下复制完整地址，方便手机浏览器打开遥控页
        addrBar = new TextView(this);
        addrBar.setTextSize(16);
        addrBar.setTextColor(0xFF3EA6FF);
        addrBar.setSingleLine(true);
        addrBar.setBackgroundColor(0xFF1A1A1E);
        addrBar.setPadding(dp(8), dp(8), dp(8), dp(8));
        addrBar.setOnClickListener(new MainAddrClick(this, addrBar));
        body.addView(addrBar, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, dp(46)));
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

    /** 固定小方块按钮：短文本两行放得下，横屏也好点。 */
    private Button square(int code, String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextSize(20);
        b.setTextColor(0xFFEDEDF2);
        b.setBackgroundColor(0xFF1C1C1E);
        b.setGravity(Gravity.CENTER);
        b.setOnClickListener(new MainClick(this, code));
        return b;
    }

    private Button button(int code, String text) {
        return square(code, text);
    }

    private int dp(int v) {
        Resources r = getResources();
        return Math.round(v * r.getDisplayMetrics().density);
    }

    void refresh() {
        if (tv == null || tvMini == null) return;
        // 角色只看端口：本包占着 18765 就是它在服务，被占就是让位给第三方
        boolean running = Server.I.isRunning();
        boolean yielded = Server.I.yielded;
        tvMini.setText(Mode.name(this) + (Mode.both(this) ? "（可切换）" : "（自动）")
                + "　服务：" + (running ? "运行中" : (yielded ? "第三方托管" : "未运行")));
        StringBuilder sb = new StringBuilder();
        String addr = "";
        String[] ips = Ip.all();
        for (int i = 0; i < ips.length; i++) {
            if (addr.length() > 0) addr += " / ";
            addr += ips[i];
        }
        if (addr.length() == 0) addr = Ip.local();
        if (addr.length() == 0) addr = "本机";
        if (!Mode.hasOfficial(this) && !Mode.hasThirdParty(this)) {
            sb.append("车上没找到可用音源。请先安装官方网易云车机版，或第三方自研播放器。");
        } else if (running) {
            sb.append("服务：运行中")
                    .append("　连接数：").append(Server.I.clientCount()).append("\n")
                    .append(Ncm.I.describe()).append("\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT).append("\n")
                    .append("歌单：").append(Arrays.toString(Ncm.I.playlists()));
        } else if (yielded) {
            sb.append("服务：交给第三方自研播放器\n")
                    .append("18765 已被占用，本包自动让位，不抢端口\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT)
                    .append("（由第三方提供）");
        } else {
            sb.append("服务：未运行\n")
                    .append("地址：http://").append(addr).append(":").append(Cfg.PORT)
                    .append("\n点「启动 / 重启服务」接管端口");
        }
        if (Mode.installed(this, OLD_PROBE)) {
            sb.append("\n⚠ 车上还装着旧版单遥控 APK（com.wb.ncmremote），")
                    .append("它和本包争同一个 18765 端口，请按下面按钮卸掉。");
        }
        if (addrBar != null) {
            String base = "http://" + addr + ":" + Cfg.PORT;
            addrBar.setText("地址：" + base + (running ? "" : (yielded ? "（第三方提供）" : "")) + "　点一下复制");
        }
        tv.setText(sb.toString());
        if (oldProbeBtn != null) {
            oldProbeBtn.setVisibility(Mode.installed(this, OLD_PROBE) ? View.VISIBLE : View.GONE);
        }

        switchRow.setVisibility(Mode.both(this) ? View.VISIBLE : View.GONE);
        // 高亮跟着「谁在服务」走：本包在跑 = 官方音源，否则算第三方
        for (int n = 0; n < switchRow.getChildCount(); n++) {
            View v = switchRow.getChildAt(n);
            if (!(v instanceof Button)) continue;
            boolean active = (n == 0) == running;
            v.setBackgroundColor(active ? 0xFF3A3A3C : 0xFF1C1C1E);
            ((Button) v).setTextColor(active ? 0xFFFFFFFF : 0xFF8E8E93);
        }
    }

    void click(int code) {
        try {
            if (code == 1) {
                // 谁来提供服务只看 18765：端口空闲本包就接管，被第三方占用则自动让位
                Svc.start(this);
            } else if (code == 4) {
                Svc.stop(this);
            } else if (code == 6) {
                openMusicApp();
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
        Svc.stop(this);
        LogCapture.logAlways("MAIN 切换音源 → " + Mode.label(target));
        toast(target == Mode.OFFICIAL
                ? "已切回官方；若仍显示「第三方托管」，请先关掉第三方播放器再点启动"
                : "已切到「" + Mode.label(target) + "」，端口交给它");
        refresh();
        handler.postDelayed(new MainRestart(this), 600L);
    }

    /** 卸掉旧的独立探针包，避免两个包抢 18765。 */
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

    void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
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
        // 端口空闲就接管，被第三方占用 Svc 自己会让位
        Svc.start(a);
        a.refresh();
    }
}

class MainClick implements View.OnClickListener {
    private final Main a;
    private final int code;
    MainClick(Main a, int code) { this.a = a; this.code = code; }
    @Override public void onClick(View v) { a.click(code); }
}

/** 地址栏点击：把「地址：http://x.x.x.x:18765」里的完整地址复制到剪贴板。 */
class MainAddrClick implements View.OnClickListener {
    private final Main a;
    private final TextView bar;
    MainAddrClick(Main a, TextView bar) { this.a = a; this.bar = bar; }
    @Override public void onClick(View v) {
        try {
            String text = bar.getText().toString();
            int i = text.indexOf("http://");
            if (i >= 0) text = text.substring(i);
            int j = text.indexOf('　');
            if (j > 0) text = text.substring(0, j);
            ClipboardManager cm = (ClipboardManager) a.getSystemService(Activity.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("url", text));
            a.toast("已复制：" + text);
        } catch (Throwable t) {
            a.toast("复制失败，请长按手动复制");
        }
    }
}
