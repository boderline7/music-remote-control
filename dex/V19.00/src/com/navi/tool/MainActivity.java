package com.navi.tool;

import a.a;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsetsController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.h;
import androidx.appcompat.app.j;
import androidx.appcompat.app.n;
import androidx.appcompat.app.p0;
import androidx.appcompat.app.r;
import androidx.appcompat.app.w;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.x0;
import androidx.fragment.app.z;
import androidx.recyclerview.widget.h1;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.l;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.navi.tool.MainActivity;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.amapflying.util.kugou.KugouMusicManager;
import com.navi.tool.amapflying.util.kuwo.KuwoMusicManager;
import com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager;
import com.navi.tool.amapflying.util.netease.NeteaseMusicManager;
import com.navi.tool.amapflying.util.qq.QQMusicManager;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;
import com.navi.tool.databinding.ActivityMainBinding;
import com.navi.tool.protocol.AutoProtocolManager;
import com.navi.tool.service.CarLanDashboardService;
import com.navi.tool.service.GestureAccessibilityService;
import com.navi.tool.service.GestureActionService;
import com.navi.tool.service.LionMediaBridgeService;
import com.navi.tool.service.LogLinkageService;
import com.navi.tool.service.LogMonitorService;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.service.MusicIslandService;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.ui.cast.ScreenCastingFloatingService;
import com.navi.tool.ui.extra.DesktopAssistantActivity;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.ui.extra.PermissionManagerActivity;
import com.navi.tool.ui.extra.TireLoginActivity;
import com.navi.tool.ui.main.SectionsPagerAdapter;
import com.navi.tool.ui.main.SectionsPagerAdapterFiltered;
import com.navi.tool.ui.settings.CustomAmapBroadcastDialog;
import com.navi.tool.ui.theme.ThemeViewModel;
import com.navi.tool.utils.AmapBroadcastConstants;
import com.navi.tool.utils.ConfigSyncManager;
import com.navi.tool.utils.LogAppProfileStorage;
import com.navi.tool.utils.LogAssistantProfileStorage;
import com.navi.tool.utils.LogMediaProfileStorage;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.NavigationStatusSimulatorService;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import com.navi.tool.utils.UpdateManager;
import com.yalantis.ucrop.view.CropImageView;
import g4.b;
import g4.c;
import g4.d;
import g4.e;
import g4.f;
import g4.g;
import g4.i;
import g4.o;
import g4.p;
import g4.q;
import g4.u;
import java.util.List;
import p0.p1;
import p0.p2;
import p0.r2;
import s3.k;
import s5.h0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MainActivity extends r implements l {
    private static final String KEY_THEME_MODE = "theme_mode";
    private static final String KEY_USAGE_INSTRUCTION_ACCEPTED = "usage_instruction_accepted";
    private static final String PREF_NAME = "navi_tool_prefs";
    private static final String TAG = "MainActivity";
    private static final int THEME_DARK = 1;
    private static final int THEME_LIGHT = 0;
    private static final int THEME_SYSTEM = 2;
    private static MainActivity instance;
    private volatile boolean appliedFull;
    private AutoProtocolManager autoProtocolManager;
    private ActivityMainBinding binding;
    private ConfigSyncManager configSyncManager;
    private int currentUiMode;
    private DrawerLayout drawer;
    private SharedPreferences.OnSharedPreferenceChangeListener mainShortcutPrefListener;
    private SharedPreferences mainShortcutPrefs;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private SharedPreferences sharedPreferences;
    private ThemeViewModel themeViewModel;
    private h toggle;
    private ViewPager2 viewPager;
    private boolean licenseUiReloadScheduled = false;
    private int tireTapCount = 0;
    private long tireLastTapAt = 0;
    private boolean networkToastShown = false;

    private void applyTheme(int i7) {
        if (i7 == 0) {
            w.j(1);
        } else if (i7 != 1) {
            w.j(-1);
        } else {
            w.j(2);
        }
    }

    private void disablePaidExtrasForFree() {
        SharedPreferencesUtils.getAppPrefs(this).edit().putBoolean("dashboard_enabled", false).putBoolean("lane_lines_enabled", false).putBoolean("navigation_eta_enabled", false).putBoolean("gesture_actions_enabled", false).apply();
        GestureActionService.stop(this);
        Intent intent = new Intent(this, (Class<?>) FloatingWindowService.class);
        intent.setAction("stop_all_views");
        startService(intent);
        stopService(intent);
        SharedPreferencesUtils.getAppPrefs(this).edit().putBoolean("voice_navigation_switch_state", false).putBoolean("log_link_switch_state", false).putBoolean("log_link_apps_switch_state", false).putBoolean("log_link_media_switch_state", false).putBoolean("log_link_assistant_switch_state", false).putBoolean("destination_weather_enabled", false).apply();
        Intent intent2 = new Intent(this, (Class<?>) LogMonitorService.class);
        intent2.setAction(LogMonitorService.ACTION_STOP_LOG_MONITORING);
        startService(intent2);
        stopService(intent2);
        Intent intent3 = new Intent(this, (Class<?>) LogLinkageService.class);
        intent3.setAction(LogLinkageService.ACTION_STOP_LOG_LINKAGE);
        startService(intent3);
        stopService(intent3);
        SharedPreferencesUtils.setScreenCastingEnabled(this, false);
        SharedPreferencesUtils.setScreenCastingSelectedPkg(this, null);
        stopService(new Intent(this, (Class<?>) ScreenCastingFloatingService.class));
        SharedPreferencesUtils.getAppPrefs(this).edit().putBoolean("desktop_assistant_enabled", false).apply();
        SharedPreferencesUtils.getAppPrefs(this).edit().putBoolean("car_link_service_enabled", false).putBoolean("lion_sync_service_enabled", false).putBoolean("lion_media_bridge_enabled", false).putBoolean(MobileHudService.PREF_ENABLED, false).putBoolean(MobileHudService.PREF_PHONE_NOTIFICATION_ENABLED, false).putBoolean("carlan_dashboard_enabled", false).putBoolean("desktop_music_enabled", false).putBoolean("desktop_music_island_enabled", false).putBoolean("scene_mode_master_enabled", false).apply();
        stopService(new Intent(this, (Class<?>) CarInfoService.class));
        stopService(new Intent(this, (Class<?>) LionCarInfoService.class));
        stopService(new Intent(this, (Class<?>) LionMediaBridgeService.class));
        stopService(new Intent(this, (Class<?>) CarLanDashboardService.class));
        stopService(new Intent(this, (Class<?>) MobileHudService.class));
        stopService(new Intent(this, (Class<?>) PhoneNotificationRelayService.class));
        stopService(new Intent(this, (Class<?>) MusicIslandService.class));
    }

    private String getAppVersionName() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    private int getDesktopAssistantTabIndex() {
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            return -1;
        }
        h1 adapter = viewPager2.getAdapter();
        if (!(adapter instanceof SectionsPagerAdapterFiltered)) {
            return 9;
        }
        SectionsPagerAdapterFiltered sectionsPagerAdapterFiltered = (SectionsPagerAdapterFiltered) adapter;
        for (int i7 = 0; i7 < sectionsPagerAdapterFiltered.getItemCount(); i7++) {
            if (sectionsPagerAdapterFiltered.getMappedIndex(i7) == 9) {
                return i7;
            }
        }
        return -1;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    private int getSavedThemeMode() {
        return this.sharedPreferences.getInt(KEY_THEME_MODE, 2);
    }

    private int getScreenCastingTabIndex() {
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            return -1;
        }
        h1 adapter = viewPager2.getAdapter();
        if (!(adapter instanceof SectionsPagerAdapterFiltered)) {
            return 5;
        }
        SectionsPagerAdapterFiltered sectionsPagerAdapterFiltered = (SectionsPagerAdapterFiltered) adapter;
        for (int i7 = 0; i7 < sectionsPagerAdapterFiltered.getItemCount(); i7++) {
            if (sectionsPagerAdapterFiltered.getMappedIndex(i7) == 6) {
                return i7;
            }
        }
        return -1;
    }

    private int getTtsTabIndex() {
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            return -1;
        }
        h1 adapter = viewPager2.getAdapter();
        if (!(adapter instanceof SectionsPagerAdapterFiltered)) {
            return 2;
        }
        SectionsPagerAdapterFiltered sectionsPagerAdapterFiltered = (SectionsPagerAdapterFiltered) adapter;
        for (int i7 = 0; i7 < sectionsPagerAdapterFiltered.getItemCount(); i7++) {
            if (sectionsPagerAdapterFiltered.getMappedIndex(i7) == 3) {
                return i7;
            }
        }
        return -1;
    }

    private void handleStartTrial(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在请求试用...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(new i(this, str, progressDialog)).start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void hideSystemUI() {
        p2 p2Var;
        WindowInsetsController insetsController;
        p1.a(getWindow(), true);
        View decorView = getWindow().getDecorView();
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 30) {
            insetsController = window.getInsetsController();
            r2 r2Var = new r2(insetsController);
            r2Var.f8182f = window;
            p2Var = r2Var;
        } else {
            p2Var = new p2(window, decorView);
        }
        p2Var.y(7);
        p2Var.H();
    }

    private void initAccessGate() {
        new Thread(new b(this, 3)).start();
    }

    private void initAmapBroadcast() {
        if (SharedPreferencesUtils.isCustomAmapBroadcastEnabled(this)) {
            AmapBroadcastConstants.setAmapBroadcast(SharedPreferencesUtils.getCustomAmapBroadcastValue(this));
        } else {
            AmapBroadcastConstants.resetAmapBroadcast();
        }
    }

    private void initializeDashboardIfNeeded() {
        DisplayManager displayManager;
        int i7;
        try {
            SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
            if (!appPrefs.getBoolean("dashboard_enabled", false) || (displayManager = (DisplayManager) getSystemService("display")) == null) {
                return;
            }
            Display[] displays = displayManager.getDisplays();
            int length = displays.length;
            int i8 = 0;
            while (true) {
                if (i8 >= length) {
                    i7 = -1;
                    break;
                }
                Display display = displays[i8];
                if (display.getDisplayId() != 0) {
                    i7 = display.getDisplayId();
                    break;
                }
                i8++;
            }
            if (i7 != -1) {
                boolean z6 = appPrefs.getBoolean("lane_lines_enabled", false);
                boolean z7 = appPrefs.getBoolean("navigation_eta_enabled", false);
                if (Settings.canDrawOverlays(this)) {
                    int i9 = appPrefs.getInt("lane_lines_x", 0);
                    int i10 = appPrefs.getInt("lane_lines_y", 0);
                    int i11 = appPrefs.getInt("navigation_eta_x", 0);
                    int i12 = appPrefs.getInt("navigation_eta_y", 0);
                    boolean z8 = appPrefs.getBoolean("navigation_eta_top_info_enabled", false);
                    if (z6) {
                        try {
                            Intent intent = new Intent(this, (Class<?>) FloatingWindowService.class);
                            intent.putExtra("action", "create_lane_lines");
                            intent.putExtra("display_id", i7);
                            intent.putExtra("x", i9);
                            intent.putExtra("y", i10);
                            startService(intent);
                        } catch (Exception unused) {
                        }
                    }
                    if (z7) {
                        Intent intent2 = new Intent(this, (Class<?>) FloatingWindowService.class);
                        intent2.putExtra("action", "create_navigation_eta");
                        intent2.putExtra("display_id", i7);
                        intent2.putExtra("x", i11);
                        intent2.putExtra("y", i12);
                        intent2.putExtra("show_top_info", z8);
                        startService(intent2);
                    }
                }
            }
        } catch (Exception unused2) {
        }
    }

    private void installTireEntry() {
        this.binding.f4746k.setOnClickListener(new c(this, 1));
    }

    private boolean isNetworkAvailable() {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        if (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleStartTrial$26(DialogInterface dialogInterface, int i7) {
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleStartTrial$27(ProgressDialog progressDialog, String str) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (str != null && str.contains("试用成功")) {
            n nVar = new n(this);
            nVar.t("试用开通成功");
            nVar.m("已开通7天vip，使用手机助手或者电脑助手激活相关权限后尽情体验，需要手动重启两次APP才可完成版本转换，点击重启按钮体验全功能版本");
            nVar.k();
            nVar.r("重启APP", new g(0));
            nVar.v();
            return;
        }
        if (str == null || !str.contains("已试用")) {
            ToastHelper.showLong(this, "请求试用失败: " + str);
        } else {
            n nVar2 = new n(this);
            nVar2.t("提示");
            nVar2.m("此车机已试用过，想继续使用软件，点击切换版本加软件作者微信购买，年费49.8元，永久168元");
            nVar2.r("关闭", null);
            nVar2.v();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleStartTrial$28(ProgressDialog progressDialog) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        ToastHelper.showLong(this, "网络异常，请求失败");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleStartTrial$29(String str, ProgressDialog progressDialog) {
        try {
            runOnUiThread(new i(this, progressDialog, NativeBridge.requestTrial(str)));
        } catch (Exception unused) {
            runOnUiThread(new p0(7, this, progressDialog));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAccessGate$36(boolean z6) {
        if (z6) {
            if (SharedPreferencesUtils.isOtpVerified(this)) {
                return;
            }
            showOtpDialog();
        } else if (SharedPreferencesUtils.isOtpVerified(this)) {
            SharedPreferencesUtils.clearOtpVerified(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAccessGate$37() {
        if (isNetworkAvailable()) {
            runOnUiThread(new g4.h(this, NativeBridge.requireVerify(), 1));
        } else {
            showNetworkToastOnce();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$installTireEntry$25(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.tireLastTapAt > 2000) {
            this.tireTapCount = 0;
        }
        this.tireLastTapAt = currentTimeMillis;
        int i7 = this.tireTapCount + 1;
        this.tireTapCount = i7;
        if (i7 < 5) {
            return;
        }
        this.tireTapCount = 0;
        if ((NativeBridge.getCachedLicenseMask(this) & 4096) == 0) {
            return;
        }
        startActivity(new Intent(this, (Class<?>) TireLoginActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        this.drawer.t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1() {
        finish();
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$10(View view) {
        showThemeSelectorDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$11(View view) {
        Intent intent = new Intent(this, (Class<?>) FileManagerActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$12(View view) {
        Intent intent = new Intent(this, (Class<?>) PermissionManagerActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$13(View view) {
        Intent intent = new Intent(this, (Class<?>) DesktopAssistantActivity.class);
        intent.addFlags(268435456);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$14() {
        this.autoProtocolManager.playSoundText("播报测试");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$15(View view) {
        this.autoProtocolManager.initialize(this);
        new Handler(Looper.getMainLooper()).postDelayed(new b(this, 7), 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2() {
        try {
            NativeBridge.checkAppUpdate(this);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3() {
        ToastHelper.showLong(this, "账号迁移成功，下次启动生效");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(String str, String str2) {
        try {
            if (NativeBridge.uploadLicense(str, str2)) {
                SharedPreferencesUtils.setLicenseUploaded(this, true);
                if (str.equals(str2)) {
                    return;
                }
                SharedPreferencesUtils.setLicenseUid(this, str2);
                runOnUiThread(new b(this, 2));
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$5() {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.addFlags(268468224);
        startActivity(intent);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$6() {
        try {
            NativeBridge.checkAppUpdate(this);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$7(boolean z6, String str, String str2, int i7, boolean z7) {
        this.appliedFull = z6;
        updateToolbarTitle(str, z6, str2);
        NativeBridge.setCachedLicenseMask(i7);
        updateDrawerMenu(this, i7);
        SectionsPagerAdapter sectionsPagerAdapter = this.sectionsPagerAdapter;
        if (sectionsPagerAdapter != null) {
            sectionsPagerAdapter.setLicenseMask(i7);
            if (z7) {
                this.sectionsPagerAdapter.notifyDataSetChanged();
            }
        }
        if (z7) {
            if (z6) {
                ToastHelper.showLong(this, "授权已更新，正在刷新界面");
                new Thread(new b(this, 10)).start();
                scheduleLicenseUiReload();
            } else {
                disablePaidExtrasForFree();
                ToastHelper.showLong(this, "当前已转为免费版本，5秒之后重启！");
                new Handler(Looper.getMainLooper()).postDelayed(new b(this, 9), 5000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$onCreate$8(final java.lang.String r14) {
        /*
            r13 = this;
            java.lang.String r0 = ""
            boolean r1 = r13.isNetworkAvailable()
            if (r1 != 0) goto Lc
            r13.showNetworkToastOnce()
            return
        Lc:
            java.lang.String r1 = com.navi.tool.utils.LicenseVerifier.newNonce()
            r2 = 0
            android.content.pm.PackageManager r3 = r13.getPackageManager()     // Catch: java.lang.Exception -> L20
            java.lang.String r4 = r13.getPackageName()     // Catch: java.lang.Exception -> L20
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r2)     // Catch: java.lang.Exception -> L20
            java.lang.String r3 = r3.versionName     // Catch: java.lang.Exception -> L20
            goto L21
        L20:
            r3 = r0
        L21:
            if (r3 != 0) goto L24
            r3 = r0
        L24:
            r4 = 1
            java.lang.String r3 = com.navi.tool.utils.NativeBridge.fetchLicenseStatus(r14, r1, r3)     // Catch: java.lang.Exception -> L33
            if (r3 == 0) goto L34
            boolean r5 = r3.isEmpty()     // Catch: java.lang.Exception -> L34
            if (r5 != 0) goto L34
            r5 = r4
            goto L35
        L33:
            r3 = r0
        L34:
            r5 = r2
        L35:
            if (r5 == 0) goto L41
            com.navi.tool.utils.LicenseVerifier$Result r6 = com.navi.tool.utils.LicenseVerifier.verify(r3, r14, r1)
            java.lang.String r7 = r6.error
            if (r7 == 0) goto L42
            r5 = r2
            goto L42
        L41:
            r6 = 0
        L42:
            if (r5 != 0) goto L48
            r13.showNetworkToastOnce()
            return
        L48:
            boolean r5 = r6.block
            if (r5 == 0) goto L57
            int r14 = android.os.Process.myPid()
            android.os.Process.killProcess(r14)
            java.lang.System.exit(r2)
            return
        L57:
            java.lang.String r5 = r6.expd
            if (r5 != 0) goto L5d
            r10 = r0
            goto L5e
        L5d:
            r10 = r5
        L5e:
            int r0 = com.navi.tool.utils.NativeBridge.getLicenseMask(r13)
            r0 = r0 & 3950(0xf6e, float:5.535E-42)
            if (r0 == 0) goto L68
            r0 = r4
            goto L69
        L68:
            r0 = r2
        L69:
            com.navi.tool.utils.SharedPreferencesUtils.setLicenseState(r13, r3, r1)
            int r11 = com.navi.tool.utils.NativeBridge.getLicenseMask(r13)
            r1 = r11 & 3950(0xf6e, float:5.535E-42)
            if (r1 == 0) goto L76
            r8 = r4
            goto L77
        L76:
            r8 = r2
        L77:
            if (r0 == r8) goto L7b
            r12 = r4
            goto L7c
        L7b:
            r12 = r2
        L7c:
            com.navi.tool.utils.ConfigSyncManager r0 = r13.configSyncManager
            r0.maybeDownloadAndApply(r14, r8)
            g4.j r0 = new g4.j
            r6 = r0
            r7 = r13
            r9 = r14
            r6.<init>()
            r13.runOnUiThread(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.MainActivity.lambda$onCreate$8(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$9(View view) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(268435456);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onNavigationItemSelected$41(DialogInterface dialogInterface, int i7) {
        try {
            finishAffinity();
        } catch (Exception unused) {
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$recheckLicense$33() {
        Intent intent = new Intent(this, (Class<?>) MainActivity.class);
        intent.addFlags(268468224);
        startActivity(intent);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$recheckLicense$34(boolean z6) {
        if (isFinishing() || isDestroyed() || z6 == this.appliedFull) {
            return;
        }
        this.appliedFull = z6;
        if (!z6) {
            disablePaidExtrasForFree();
        }
        ToastHelper.showLong(this, z6 ? "已转为全功能版本，正在重启…" : "当前已转为免费版本，5秒之后重启！");
        new Handler(Looper.getMainLooper()).postDelayed(new b(this, 4), 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0011  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0010 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$recheckLicense$35() {
        /*
            r3 = this;
            r0 = 0
            int r1 = com.navi.tool.utils.NativeBridge.getLicenseMask(r3)     // Catch: java.lang.Throwable -> Lb
            r1 = r1 & 3950(0xf6e, float:5.535E-42)
            if (r1 == 0) goto Lb
            r1 = 1
            goto Lc
        Lb:
            r1 = r0
        Lc:
            boolean r2 = r3.appliedFull
            if (r1 != r2) goto L11
            return
        L11:
            g4.h r2 = new g4.h
            r2.<init>(r3, r1, r0)
            r3.runOnUiThread(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.MainActivity.lambda$recheckLicense$35():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$registerMainShortcutPrefListener$19(SharedPreferences sharedPreferences, String str) {
        if (str == null) {
            return;
        }
        if ("file_assistant_enabled".equals(str) || "permission_assistant_enabled".equals(str) || "desktop_assistant_enabled".equals(str)) {
            runOnUiThread(new b(this, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleLicenseUiReload$32() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        recreate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupTabsAndPager$21(com.google.android.material.tabs.b bVar, int i7) {
        switch (i7) {
            case 0:
                bVar.a(o.ic_theme);
                bVar.c(u.auto_theme);
                break;
            case 1:
                bVar.a(o.ic_3d);
                bVar.c(u.auto_3d);
                break;
            case 2:
                bVar.a(o.ic_volume_up);
                bVar.c(u.tts);
                break;
            case 3:
                bVar.a(o.ic_dashboard_ext);
                bVar.c(u.dashboard_extension);
                break;
            case 4:
                bVar.a(o.ic_mic);
                bVar.c(u.voice_navigation);
                break;
            case 5:
                bVar.a(o.ic_cast);
                bVar.c(u.screen_casting);
                break;
            case 6:
                bVar.a(o.ic_info);
                bVar.c(u.log_linkage);
                break;
            case 7:
                bVar.a(o.ic_hand);
                bVar.c(u.additional_features);
                break;
            case 8:
                bVar.a(o.ic_settings);
                bVar.c(u.misc_settings);
                break;
            case 9:
                bVar.a(o.ic_dashboard);
                bVar.c(u.desktop_assistant);
                break;
            case 10:
                bVar.a(o.ic_car_service);
                bVar.c(u.car_link_service);
                break;
            case 11:
                bVar.a(o.ic_scene_mode);
                bVar.c(u.scene_mode);
                break;
            case 12:
                bVar.a(o.ic_store_service);
                bVar.c(u.store_service);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupTabsAndPager$22(SectionsPagerAdapterFiltered sectionsPagerAdapterFiltered, com.google.android.material.tabs.b bVar, int i7) {
        int mappedIndex = sectionsPagerAdapterFiltered.getMappedIndex(i7);
        if (mappedIndex == 0) {
            bVar.a(o.ic_theme);
            bVar.c(u.auto_theme);
        } else if (mappedIndex == 2) {
            bVar.a(o.ic_3d);
            bVar.c(u.auto_3d);
        } else {
            if (mappedIndex != 3) {
                return;
            }
            bVar.a(o.ic_volume_up);
            bVar.c(u.tts);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showNetworkToastOnce$31() {
        if (this.networkToastShown || isFinishing() || isDestroyed()) {
            return;
        }
        this.networkToastShown = true;
        ToastHelper.showLong(this, "当前网络异常，部分联网功能暂不可用，请检查网络");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOtpDialog$38(TextInputEditText textInputEditText, DialogInterface dialogInterface, int i7) {
        if (NativeBridge.verifyTotp(textInputEditText.getText() != null ? textInputEditText.getText().toString().trim() : h0.FRAGMENT_ENCODE_SET, System.currentTimeMillis() / 1000)) {
            SharedPreferencesUtils.setOtpVerified(this, true);
        } else {
            ToastHelper.show(this, "验证码错误");
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showOtpDialog$39(DialogInterface dialogInterface, int i7) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSwitchVersionDialog$30(DialogInterface dialogInterface, int i7) {
        showWeChatQrDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showThemeSelectorDialog$40(RadioGroup radioGroup, DialogInterface dialogInterface, int i7) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        int i8 = checkedRadioButtonId == p.radio_light ? 0 : checkedRadioButtonId == p.radio_dark ? 1 : 2;
        saveThemeMode(i8);
        applyTheme(i8);
        recreate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUsageInstructionDialog$16(DialogInterface dialogInterface, int i7) {
        a.m(this.sharedPreferences, KEY_USAGE_INSTRUCTION_ACCEPTED, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUsageInstructionDialog$17(DialogInterface dialogInterface, int i7) {
        finishAffinity();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showUsageInstructionDialog$18(Button button, Button button2) {
        if (button != null) {
            button.setEnabled(true);
        }
        if (button2 != null) {
            button2.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbarTitle$23(View view) {
        showSwitchVersionDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateToolbarTitle$24(String str, View view) {
        handleStartTrial(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recheckLicense() {
        new Thread(new b(this, 11)).start();
    }

    private void registerMainShortcutPrefListener() {
        if (this.mainShortcutPrefs == null) {
            this.mainShortcutPrefs = SharedPreferencesUtils.getAppPrefs(this);
        }
        SharedPreferences sharedPreferences = this.mainShortcutPrefs;
        if (sharedPreferences == null || this.mainShortcutPrefListener != null) {
            return;
        }
        f fVar = new f(0, this);
        this.mainShortcutPrefListener = fVar;
        sharedPreferences.registerOnSharedPreferenceChangeListener(fVar);
    }

    private void saveThemeMode(int i7) {
        a.l(this.sharedPreferences, KEY_THEME_MODE, i7);
    }

    private void scheduleLicenseUiReload() {
        if (this.licenseUiReloadScheduled || isFinishing() || isDestroyed()) {
            return;
        }
        this.licenseUiReloadScheduled = true;
        new Handler(Looper.getMainLooper()).postDelayed(new b(this, 8), 800L);
    }

    private void setupTabsAndPager(boolean z6, int i7) {
        TabLayout tabLayout = this.binding.f4745j;
        if (!z6) {
            SectionsPagerAdapterFiltered sectionsPagerAdapterFiltered = new SectionsPagerAdapterFiltered(this, new int[]{0, 2, 3});
            ViewPager2 viewPager2 = this.binding.f4747l;
            this.viewPager = viewPager2;
            viewPager2.setAdapter(sectionsPagerAdapterFiltered);
            this.viewPager.setOffscreenPageLimit(sectionsPagerAdapterFiltered.getItemCount());
            this.viewPager.setUserInputEnabled(false);
            new k(tabLayout, this.viewPager, new e0.f(7, sectionsPagerAdapterFiltered)).a();
            return;
        }
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, i7);
        this.sectionsPagerAdapter = sectionsPagerAdapter;
        ViewPager2 viewPager22 = this.binding.f4747l;
        this.viewPager = viewPager22;
        viewPager22.setAdapter(sectionsPagerAdapter);
        this.viewPager.setOffscreenPageLimit(1);
        this.viewPager.setUserInputEnabled(false);
        new k(tabLayout, this.viewPager, new y2.l(6)).a();
        View childAt = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(8);
        if (childAt != null) {
            childAt.getLayoutParams().width = 0;
            childAt.setVisibility(8);
        }
    }

    private void showFragmentContainer(z zVar) {
        this.binding.f4747l.setVisibility(8);
        this.binding.f4745j.setVisibility(8);
        this.binding.f4741f.setVisibility(8);
        x0 supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.getClass();
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(supportFragmentManager);
        int i7 = p.container;
        if (i7 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        aVar.c(i7, zVar, null, 2);
        if (!aVar.f1981h) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        aVar.f1980g = true;
        aVar.f1982i = null;
        aVar.e(false);
    }

    private void showNetworkToastOnce() {
        runOnUiThread(new b(this, 0));
    }

    private void showOtpDialog() {
        View inflate = LayoutInflater.from(this).inflate(q.dialog_otp_verification, (ViewGroup) null);
        TextInputEditText textInputEditText = (TextInputEditText) inflate.findViewById(p.edit_code);
        n nVar = new n(this);
        nVar.u(inflate);
        nVar.k();
        nVar.r("确认", new d(this, textInputEditText, 1));
        nVar.o("取消", new e(this, 3));
        nVar.v();
    }

    private void showSwitchVersionDialog() {
        n nVar = new n(this);
        nVar.t("切换版本说明");
        nVar.m("1，全功能版本主打仪表扩展，语音导航，日志联动，桌面助手，车控服务、情景模式等等自动化任务。更多功能等你发现\n2，其中语音导航功能更加适合奇瑞系8155车机。自带语音助手的其他车机，可联系我抓日志想办法适配。\n3，全功能版是收费功能，目前实行订阅制，全年49.8元，永久版168元。\n4，其实免费版完全满足95%车友的需求\n5，如无这些需求，点击关闭窗口。如有需要，点击加我微信。");
        nVar.o("关闭窗口", null);
        nVar.r("加我微信", new e(this, 0));
        nVar.v();
    }

    private void showThemeSelectorDialog() {
        View inflate = LayoutInflater.from(this).inflate(q.dialog_theme_selector, (ViewGroup) null);
        RadioGroup radioGroup = (RadioGroup) inflate.findViewById(p.radio_group_theme);
        int savedThemeMode = getSavedThemeMode();
        if (savedThemeMode == 0) {
            ((RadioButton) radioGroup.findViewById(p.radio_light)).setChecked(true);
        } else if (savedThemeMode != 1) {
            ((RadioButton) radioGroup.findViewById(p.radio_system)).setChecked(true);
        } else {
            ((RadioButton) radioGroup.findViewById(p.radio_dark)).setChecked(true);
        }
        n nVar = new n(this);
        nVar.s(u.select_theme);
        nVar.u(inflate);
        nVar.q(u.ok, new d(this, radioGroup, 0));
        nVar.n(u.cancel);
        nVar.v();
    }

    public static void showUpdateDialogFromNative(final String str, final String str2, final byte[] bArr, final boolean z6) {
        final MainActivity mainActivity = getInstance();
        if (mainActivity == null) {
            return;
        }
        mainActivity.runOnUiThread(new Runnable() { // from class: g4.a
            @Override // java.lang.Runnable
            public final void run() {
                UpdateManager.showUpdateDialog(MainActivity.this, str, str2, bArr, z6);
            }
        });
    }

    private void showUsageInstructionDialog() {
        int i7 = (int) ((getResources().getDisplayMetrics().density * 16.0f) + 0.5f);
        TextView textView = new TextView(this);
        textView.setText("1，在开启每个开关前，请认真阅读开关的介绍说明文字\n\n2，对于不看文字就问我如何用，这是干啥的，直接忽略甚至拉黑\n\n3，全功能版本的费用，仅限于车机助手的使用权利，不包含其他任何定制或者分享、帮助安装任何APK的义务\n\n4，我有阅读障碍，软件有问题请带截图或者视频联系我反馈，直接发文字可能会忽略");
        textView.setTextSize(16.0f);
        textView.setTypeface(textView.getTypeface(), 1);
        textView.setPadding(i7, i7, i7, i7);
        n nVar = new n(this);
        nVar.t("使用说明");
        nVar.u(textView);
        nVar.k();
        nVar.r("同意", new e(this, 1));
        nVar.o("不同意", new e(this, 2));
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        Button e8 = e7.e(-1);
        Button e9 = e7.e(-2);
        if (e8 != null) {
            e8.setEnabled(false);
        }
        if (e9 != null) {
            e9.setEnabled(false);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new p0(6, e8, e9), 10000L);
    }

    private void showViewPager() {
        this.binding.f4747l.setVisibility(0);
        this.binding.f4745j.setVisibility(0);
        this.binding.f4741f.setVisibility(0);
        if (getSupportFragmentManager().w(p.container) != null) {
            x0 supportFragmentManager = getSupportFragmentManager();
            supportFragmentManager.getClass();
            androidx.fragment.app.a aVar = new androidx.fragment.app.a(supportFragmentManager);
            aVar.i(getSupportFragmentManager().w(p.container));
            aVar.e(false);
        }
    }

    private void showWeChatQrDialog() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(o.grwx);
        imageView.setAdjustViewBounds(true);
        n nVar = new n(this);
        nVar.t("微信二维码");
        nVar.u(imageView);
        nVar.r("关闭", null);
        nVar.v();
    }

    private void startMusicManagerForBridge(SharedPreferences sharedPreferences) {
        switch (sharedPreferences.getInt("desktop_music_source", 0)) {
            case 0:
                QQMusicManager.getInstance(this).start();
                break;
            case 1:
                KuwoMusicManager.getInstance(this).start();
                break;
            case 2:
                KugouMusicManager.getInstance(this).start();
                break;
            case 3:
                NeteaseMusicManager.getInstance(this).start();
                break;
            case 4:
                NewKuwoMusicManager.getInstance(this).start();
                break;
            case 5:
                k4.a.c(this).g();
                break;
            case 6:
                l4.e.b(this).d();
                break;
        }
    }

    private void syncMusicIslandService() {
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        boolean z6 = appPrefs.getBoolean("desktop_music_enabled", false);
        boolean z7 = appPrefs.getBoolean("desktop_music_island_enabled", false);
        if (z6 && z7) {
            MusicIslandService.startOrRefresh(this);
        } else {
            MusicIslandService.stop(this);
        }
    }

    private void updateDrawerMenu(Context context) {
        updateDrawerMenu(context, NativeBridge.getLicenseMask(context));
    }

    private void updateDrawerMenu(Context context, int i7) {
        MenuItem menuItem;
        boolean z6;
        Menu menu = this.binding.f4744i.getMenu();
        if (menu == null) {
            return;
        }
        MenuItem findItem = menu.findItem(p.nav_auto_theme);
        MenuItem findItem2 = menu.findItem(p.nav_auto_brightness);
        MenuItem findItem3 = menu.findItem(p.nav_auto_3d);
        MenuItem findItem4 = menu.findItem(p.nav_tts);
        MenuItem findItem5 = menu.findItem(p.nav_dashboard);
        MenuItem findItem6 = menu.findItem(p.nav_voice_navigation);
        MenuItem findItem7 = menu.findItem(p.nav_screen_casting);
        MenuItem findItem8 = menu.findItem(p.nav_custom_amap_broadcast);
        MenuItem findItem9 = menu.findItem(p.nav_log_linkage);
        MenuItem findItem10 = menu.findItem(p.nav_additional_features);
        MenuItem findItem11 = menu.findItem(p.nav_misc_settings);
        MenuItem findItem12 = menu.findItem(p.nav_desktop_assistant);
        MenuItem findItem13 = menu.findItem(p.nav_car_link_service);
        MenuItem findItem14 = menu.findItem(p.nav_scene_mode);
        MenuItem findItem15 = menu.findItem(p.nav_store_service);
        MenuItem findItem16 = menu.findItem(p.nav_exit);
        if (findItem != null) {
            findItem.setVisible(true);
        }
        if (findItem2 != null) {
            findItem2.setVisible(false);
        }
        if (findItem3 != null) {
            findItem3.setVisible(true);
        }
        if (findItem4 != null) {
            findItem4.setVisible(true);
        }
        boolean z7 = (i7 & SharedPreferencesUtils.CAP_FEATURES) != 0;
        if (findItem5 != null) {
            findItem5.setVisible((i7 & 2) != 0);
        }
        if (findItem6 != null) {
            findItem6.setVisible((i7 & 4) != 0);
        }
        if (findItem7 != null) {
            findItem7.setVisible((i7 & 8) != 0);
        }
        if (findItem8 != null) {
            findItem8.setVisible(z7);
        }
        if (findItem9 != null) {
            findItem9.setVisible((i7 & 32) != 0);
        }
        if (findItem10 != null) {
            findItem10.setVisible((i7 & 64) != 0);
        }
        if (findItem11 != null) {
            findItem11.setVisible(z7);
        }
        if (findItem12 != null) {
            findItem12.setVisible((i7 & 256) != 0);
        }
        if (findItem13 != null) {
            findItem13.setVisible((i7 & 512) != 0);
        }
        if (findItem14 != null) {
            if ((i7 & 1024) != 0) {
                menuItem = findItem14;
                z6 = true;
            } else {
                menuItem = findItem14;
                z6 = false;
            }
            menuItem.setVisible(z6);
        }
        if (findItem15 != null) {
            findItem15.setVisible((i7 & 2048) != 0);
        }
        if (findItem16 != null) {
            findItem16.setVisible(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMainShortcutButtonsVisibility() {
        if (this.binding == null) {
            return;
        }
        SharedPreferences sharedPreferences = this.mainShortcutPrefs;
        if (sharedPreferences == null) {
            sharedPreferences = SharedPreferencesUtils.getAppPrefs(this);
        }
        boolean z6 = sharedPreferences.getBoolean("file_assistant_enabled", false);
        boolean z7 = sharedPreferences.getBoolean("permission_assistant_enabled", false);
        boolean z8 = sharedPreferences.getBoolean("desktop_assistant_enabled", false);
        this.binding.f4739d.setVisibility(z6 ? 0 : 8);
        this.binding.f4740e.setVisibility(z7 ? 0 : 8);
        this.binding.f4738c.setVisibility(z8 ? 0 : 8);
    }

    private void updateToolbarTitle(String str, boolean z6, String str2) {
        int i7 = 0;
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("tire_account", 0);
        boolean z7 = sharedPreferences.getBoolean("tire_login_expired", false);
        if (z7) {
            a.m(sharedPreferences, "tire_login_expired", false);
        }
        if (z7) {
            ToastHelper.showLong(this, "胎压登录已过期，请重新登录");
        }
        String string = getString(u.app_name);
        String str3 = z6 ? " 全功能版" : " 免费版";
        String appVersionName = getAppVersionName();
        String str4 = h0.FRAGMENT_ENCODE_SET;
        String concat = (appVersionName == null || appVersionName.isEmpty()) ? h0.FRAGMENT_ENCODE_SET : " 版本：".concat(appVersionName);
        if (z6 && str2 != null && !str2.isEmpty()) {
            if (str2.contains("2099")) {
                str2 = "永久";
            }
            str4 = " VIP到期时间：".concat(str2);
        }
        this.binding.f4746k.setTitle(string + "  " + str + str3 + concat + str4);
        Button button = (Button) findViewById(p.btn_switch_version);
        Button button2 = (Button) findViewById(p.btn_start_trial);
        if (button != null && button2 != null) {
            if (z6) {
                button.setVisibility(8);
                button2.setVisibility(8);
            } else {
                button.setVisibility(0);
                button.setOnClickListener(new c(this, i7));
                button2.setVisibility(0);
                button2.setOnClickListener(new com.navi.tool.ui.main.a(3, this, str));
            }
        }
        installTireEntry();
    }

    public void initAuto() {
        try {
            if (this.autoProtocolManager.isServiceBound()) {
                return;
            }
            this.autoProtocolManager.initialize(this);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.activity.o, android.app.Activity
    public void onBackPressed() {
        View f7 = this.drawer.f(8388611);
        if (f7 == null || !DrawerLayout.o(f7)) {
            super.onBackPressed();
        } else {
            this.drawer.d();
        }
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, androidx.activity.o, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i7 = configuration.uiMode;
        if ((this.currentUiMode & 48) != (i7 & 48)) {
            this.currentUiMode = i7;
            recreate();
        }
        this.themeViewModel.onSystemThemeChanged(configuration);
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        instance = this;
        hideSystemUI();
        this.sharedPreferences = getSharedPreferences(PREF_NAME, 0);
        ConfigSyncManager configSyncManager = ConfigSyncManager.getInstance(this);
        this.configSyncManager = configSyncManager;
        configSyncManager.register();
        initAmapBroadcast();
        applyTheme(getSavedThemeMode());
        initAccessGate();
        ActivityMainBinding inflate = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.f4736a);
        if (!this.sharedPreferences.getBoolean(KEY_USAGE_INSTRUCTION_ACCEPTED, false)) {
            showUsageInstructionDialog();
        }
        this.themeViewModel = (ThemeViewModel) new androidx.appcompat.app.g(this).C(ThemeViewModel.class);
        try {
            LogAppProfileStorage.getAppProfileDir(this);
            LogMediaProfileStorage.getMediaProfileDir(this);
            LogAssistantProfileStorage.getAssistantProfileDir(this);
        } catch (Throwable unused) {
        }
        this.autoProtocolManager = AutoProtocolManager.getInstance();
        startService(new Intent(this, (Class<?>) AmapBroadcastService.class));
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        this.mainShortcutPrefs = appPrefs;
        boolean z6 = appPrefs.getBoolean("car_link_service_enabled", false);
        boolean z7 = appPrefs.getBoolean("lion_sync_service_enabled", false);
        if (z6) {
            startForegroundService(new Intent(this, (Class<?>) CarInfoService.class));
        } else if (z7) {
            startForegroundService(new Intent(this, (Class<?>) LionCarInfoService.class));
        }
        boolean z8 = appPrefs.getBoolean("lion_media_bridge_enabled", false);
        if (z8) {
            startForegroundService(new Intent(this, (Class<?>) LionMediaBridgeService.class));
        }
        boolean z9 = appPrefs.getBoolean("carlan_dashboard_enabled", false);
        if (z9) {
            startForegroundService(new Intent(this, (Class<?>) CarLanDashboardService.class));
        }
        if (z9 || z8) {
            startMusicManagerForBridge(appPrefs);
        }
        syncMusicIslandService();
        MobileHudService.syncService(this);
        PhoneNotificationRelayService.syncService(this);
        if (appPrefs.getBoolean("log_link_switch_state", false) && !LogLinkageService.isServiceRunning()) {
            Intent intent = new Intent(this, (Class<?>) LogLinkageService.class);
            intent.setAction(LogLinkageService.ACTION_START_LOG_LINKAGE);
            startService(intent);
        }
        if (appPrefs.getBoolean("gesture_actions_enabled", false) && !GestureActionService.isServiceRunning()) {
            boolean canDrawOverlays = Settings.canDrawOverlays(this);
            boolean isEnabled = GestureAccessibilityService.isEnabled(this);
            if (canDrawOverlays && isEnabled) {
                GestureActionService.start(this);
            }
        }
        if (SharedPreferencesUtils.isCustomAmapBroadcastEnabled(this)) {
            NavigationStatusSimulatorService.startService(this);
        }
        initializeDashboardIfNeeded();
        if (SharedPreferencesUtils.isScreenCastingEnabled(this)) {
            startForegroundService(new Intent(this, (Class<?>) ScreenCastingFloatingService.class));
        }
        setSupportActionBar(this.binding.f4746k);
        ActivityMainBinding activityMainBinding = this.binding;
        DrawerLayout drawerLayout = activityMainBinding.f4737b;
        this.drawer = drawerLayout;
        h hVar = new h(this, drawerLayout, activityMainBinding.f4746k, u.navigation_drawer_open, u.navigation_drawer_close);
        this.toggle = hVar;
        this.drawer.a(hVar);
        int i7 = 1;
        this.drawer.a(new com.google.android.material.navigation.k(this, 1));
        h hVar2 = this.toggle;
        DrawerLayout drawerLayout2 = hVar2.f719b;
        View f7 = drawerLayout2.f(8388611);
        if (f7 == null || !DrawerLayout.o(f7)) {
            hVar2.e(CropImageView.DEFAULT_ASPECT_RATIO);
        } else {
            hVar2.e(1.0f);
        }
        if (hVar2.f722e) {
            View f8 = drawerLayout2.f(8388611);
            int i8 = (f8 == null || !DrawerLayout.o(f8)) ? hVar2.f723f : hVar2.f724g;
            g.d dVar = hVar2.f720c;
            boolean z10 = hVar2.f725h;
            androidx.appcompat.app.d dVar2 = hVar2.f718a;
            if (!z10 && !dVar2.o()) {
                hVar2.f725h = true;
            }
            dVar2.g(dVar, i8);
        }
        h hVar3 = this.toggle;
        if (hVar3.f722e) {
            Drawable drawable = hVar3.f721d;
            boolean z11 = hVar3.f725h;
            androidx.appcompat.app.d dVar3 = hVar3.f718a;
            if (!z11 && !dVar3.o()) {
                hVar3.f725h = true;
            }
            dVar3.g(drawable, 0);
            hVar3.f722e = false;
        }
        this.drawer.setDrawerLockMode(1, 8388611);
        this.binding.f4746k.setNavigationIcon(o.ic_menu);
        int i9 = 2;
        this.binding.f4746k.setNavigationOnClickListener(new c(this, i9));
        this.binding.f4744i.setNavigationItemSelectedListener(this);
        try {
            str = NativeBridge.getSystemVin();
        } catch (Exception unused2) {
            str = null;
        }
        int i10 = 5;
        if (str == null || str.isEmpty()) {
            ToastHelper.showLong(this, "车机助手不支持当前车机 (无VIN)");
            new Handler().postDelayed(new b(this, i10), 5000L);
            return;
        }
        String licenseUid = SharedPreferencesUtils.getLicenseUid(this);
        if (licenseUid == null || licenseUid.trim().isEmpty()) {
            SharedPreferencesUtils.setLicenseUid(this, str);
            SharedPreferencesUtils.setLicenseUploaded(this, false);
            licenseUid = str;
        }
        if (!licenseUid.equals(str)) {
            SharedPreferencesUtils.setLicenseUploaded(this, false);
        }
        int licenseMask = NativeBridge.getLicenseMask(this);
        NativeBridge.setCachedLicenseMask(licenseMask);
        boolean z12 = (licenseMask & SharedPreferencesUtils.CAP_FEATURES) != 0;
        this.configSyncManager.schedulePendingUpload();
        this.appliedFull = z12;
        updateToolbarTitle(licenseUid, z12, null);
        setupTabsAndPager(z12, licenseMask);
        updateDrawerMenu(this, licenseMask);
        int i11 = 6;
        if (z12) {
            new Thread(new b(this, i11)).start();
        }
        if (!SharedPreferencesUtils.isLicenseUploaded(this)) {
            new Thread(new y0.n(this, licenseUid, str, i7)).start();
        }
        new Thread(new p0(i10, this, licenseUid)).start();
        this.binding.f4741f.setOnClickListener(new c(this, 4));
        this.binding.f4742g.setOnClickListener(new c(this, i10));
        this.binding.f4739d.setOnClickListener(new c(this, i11));
        this.binding.f4740e.setOnClickListener(new c(this, 7));
        this.binding.f4738c.setOnClickListener(new c(this, 8));
        this.binding.f4743h.setOnClickListener(new c(this, 3));
        updateTtsTestButtonVisibility();
        registerMainShortcutPrefListener();
        updateMainShortcutButtonsVisibility();
        ((List) this.viewPager.f2870c.f2859b).add(new androidx.viewpager2.adapter.d(i9, this));
        String stringExtra = getIntent().getStringExtra("target_tab");
        if (stringExtra != null && stringExtra.equals("screen_casting")) {
            int screenCastingTabIndex = getScreenCastingTabIndex();
            if (screenCastingTabIndex >= 0) {
                this.viewPager.setCurrentItem(screenCastingTabIndex, false);
                return;
            }
            return;
        }
        if (stringExtra == null || !stringExtra.equals("desktop_assistant") || this.viewPager.getAdapter() == null || this.viewPager.getAdapter().getItemCount() <= 9) {
            return;
        }
        this.viewPager.setCurrentItem(9, false);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public void onDestroy() {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
        super.onDestroy();
        instance = null;
        AutoProtocolManager autoProtocolManager = this.autoProtocolManager;
        if (autoProtocolManager != null) {
            autoProtocolManager.unbindAllServices(this);
        }
        ConfigSyncManager configSyncManager = this.configSyncManager;
        if (configSyncManager != null) {
            configSyncManager.unregister();
        }
        SharedPreferences sharedPreferences = this.mainShortcutPrefs;
        if (sharedPreferences == null || (onSharedPreferenceChangeListener = this.mainShortcutPrefListener) == null) {
            return;
        }
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        this.mainShortcutPrefListener = null;
    }

    @Override // com.google.android.material.navigation.l
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == p.nav_auto_theme) {
            showViewPager();
            this.viewPager.setCurrentItem(0, false);
        } else if (itemId == p.nav_auto_brightness) {
            showViewPager();
            this.viewPager.setCurrentItem(0, false);
        } else if (itemId == p.nav_auto_3d) {
            showViewPager();
            this.viewPager.setCurrentItem(1, false);
        } else if (itemId == p.nav_tts) {
            showViewPager();
            this.viewPager.setCurrentItem(2, false);
        } else if (itemId == p.nav_dashboard) {
            showViewPager();
            this.viewPager.setCurrentItem(3, false);
        } else {
            int i7 = 4;
            if (itemId == p.nav_voice_navigation) {
                showViewPager();
                this.viewPager.setCurrentItem(4, false);
            } else if (itemId == p.nav_screen_casting) {
                showViewPager();
                this.viewPager.setCurrentItem(5, false);
            } else if (itemId == p.nav_log_linkage) {
                showViewPager();
                this.viewPager.setCurrentItem(6, false);
            } else if (itemId == p.nav_additional_features) {
                showViewPager();
                this.viewPager.setCurrentItem(7, false);
            } else if (itemId == p.nav_misc_settings) {
                showViewPager();
                this.viewPager.setCurrentItem(8, false);
            } else if (itemId == p.nav_desktop_assistant) {
                showViewPager();
                int desktopAssistantTabIndex = getDesktopAssistantTabIndex();
                if (desktopAssistantTabIndex >= 0) {
                    this.viewPager.setCurrentItem(desktopAssistantTabIndex, false);
                }
            } else if (itemId == p.nav_car_link_service) {
                showViewPager();
                if (this.viewPager.getAdapter() != null && this.viewPager.getAdapter().getItemCount() > 10) {
                    this.viewPager.setCurrentItem(10, false);
                }
            } else if (itemId == p.nav_store_service) {
                showViewPager();
                if (this.viewPager.getAdapter() != null && this.viewPager.getAdapter().getItemCount() > 12) {
                    this.viewPager.setCurrentItem(12, false);
                }
            } else if (itemId == p.nav_scene_mode) {
                showViewPager();
                if (this.viewPager.getAdapter() != null && this.viewPager.getAdapter().getItemCount() > 11) {
                    this.viewPager.setCurrentItem(11, false);
                }
            } else if (itemId == p.nav_custom_amap_broadcast) {
                new CustomAmapBroadcastDialog().show(getSupportFragmentManager(), "CustomAmapBroadcastDialog");
            } else if (itemId == p.nav_exit) {
                n nVar = new n(this);
                nVar.s(u.confirm_exit_title);
                int i8 = u.confirm_exit_message;
                j jVar = (j) nVar.f831c;
                jVar.f750f = jVar.f745a.getText(i8);
                nVar.q(u.ok, new e(this, i7));
                nVar.n(u.cancel);
                nVar.v();
            }
        }
        this.drawer.d();
        return true;
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, android.app.Activity
    public void onNewIntent(Intent intent) {
        ViewPager2 viewPager2;
        int screenCastingTabIndex;
        super.onNewIntent(intent);
        String stringExtra = intent.getStringExtra("target_tab");
        if (stringExtra != null && stringExtra.equals("screen_casting")) {
            if (this.viewPager == null || (screenCastingTabIndex = getScreenCastingTabIndex()) < 0) {
                return;
            }
            this.viewPager.setCurrentItem(screenCastingTabIndex, false);
            return;
        }
        if (stringExtra == null || !stringExtra.equals("desktop_assistant") || (viewPager2 = this.viewPager) == null || viewPager2.getAdapter() == null || this.viewPager.getAdapter().getItemCount() <= 9) {
            return;
        }
        this.viewPager.setCurrentItem(9, false);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onResume() {
        super.onResume();
        syncMusicIslandService();
        MobileHudService.syncService(this);
        PhoneNotificationRelayService.syncService(this);
        updateTtsTestButtonVisibility();
        updateMainShortcutButtonsVisibility();
        recheckLicense();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        if (z6) {
            hideSystemUI();
        }
    }

    public void updateThemeByBrightness(int i7) {
        int i8 = i7 < SharedPreferencesUtils.getBrightnessThreshold(this) ? 1 : 0;
        saveThemeMode(i8);
        applyTheme(i8);
        recreate();
    }

    public void updateTtsTestButtonVisibility() {
        int ttsTabIndex = getTtsTabIndex();
        boolean z6 = true;
        boolean z7 = ttsTabIndex >= 0 && this.viewPager.getCurrentItem() == ttsTabIndex;
        boolean isTtsEnabled = SharedPreferencesUtils.isTtsEnabled(this);
        boolean isTtsRoadNameEnabled = SharedPreferencesUtils.isTtsRoadNameEnabled(this);
        boolean isDestinationWeatherEnabled = SharedPreferencesUtils.isDestinationWeatherEnabled(this);
        boolean isCruiseTrafficLightVoiceEnabled = SharedPreferencesUtils.isCruiseTrafficLightVoiceEnabled(this);
        boolean isZoneSpeedVoiceEnabled = SharedPreferencesUtils.isZoneSpeedVoiceEnabled(this);
        if (!z7 || (!isTtsEnabled && !isTtsRoadNameEnabled && !isDestinationWeatherEnabled && !isCruiseTrafficLightVoiceEnabled && !isZoneSpeedVoiceEnabled)) {
            z6 = false;
        }
        FloatingActionButton floatingActionButton = this.binding.f4743h;
        if (floatingActionButton != null) {
            floatingActionButton.setVisibility(z6 ? 0 : 8);
        }
    }
}
