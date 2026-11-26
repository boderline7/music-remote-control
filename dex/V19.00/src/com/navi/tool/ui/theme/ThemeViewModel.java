package com.navi.tool.ui.theme;

import android.app.Application;
import android.content.res.Configuration;
import android.provider.Settings;
import androidx.lifecycle.f0;
import androidx.lifecycle.g0;
import com.navi.tool.model.AppInfo;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.BroadcastUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ThemeViewModel extends androidx.lifecycle.a {
    private static final ExecutorService REFRESH_EXECUTOR = Executors.newSingleThreadExecutor(new p4.d(3));
    private g0 allAppsLiveData;
    private g0 auto3DEnabled;
    private g0 autoBrightnessEnabled;
    private g0 autoNaviAppsLiveData;
    private g0 autoThemeEnabled;
    private g0 brightnessAppsLiveData;
    private g0 brightnessThreshold;
    private boolean cruise3DBroadcastSent;
    private g0 cruise3DEnabled;
    private g0 cruiseFixedScaleEnabled;
    private g0 cruiseScale;
    private g0 currentBrightness;
    private boolean navigation3DBroadcastSent;
    private g0 navigation3DEnabled;
    private g0 navigationFixedScaleEnabled;
    private g0 navigationScale;

    public ThemeViewModel(Application application) {
        super(application);
        this.allAppsLiveData = new g0();
        this.autoNaviAppsLiveData = new g0();
        this.brightnessAppsLiveData = new g0();
        this.autoThemeEnabled = new g0();
        this.auto3DEnabled = new g0();
        this.autoBrightnessEnabled = new g0();
        this.brightnessThreshold = new g0();
        this.currentBrightness = new g0();
        this.navigation3DEnabled = new g0();
        this.cruise3DEnabled = new g0();
        this.navigationFixedScaleEnabled = new g0();
        this.cruiseFixedScaleEnabled = new g0();
        this.navigationScale = new g0();
        this.cruiseScale = new g0();
        this.navigation3DBroadcastSent = false;
        this.cruise3DBroadcastSent = false;
        this.autoThemeEnabled.h(Boolean.valueOf(SharedPreferencesUtils.isAutoThemeEnabled(application)));
        this.auto3DEnabled.h(Boolean.valueOf(SharedPreferencesUtils.isAuto3DEnabled(application)));
        this.autoBrightnessEnabled.h(Boolean.valueOf(SharedPreferencesUtils.isAutoBrightnessEnabled(application)));
        this.brightnessThreshold.h(Integer.valueOf(SharedPreferencesUtils.getBrightnessThreshold(application)));
        this.currentBrightness.h(Integer.valueOf(getCurrentBrightnessValue()));
        this.navigation3DEnabled.h(Boolean.valueOf(SharedPreferencesUtils.isNavigation3DEnabled(application)));
        this.cruise3DEnabled.h(Boolean.valueOf(SharedPreferencesUtils.isCruise3DEnabled(application)));
        this.navigationFixedScaleEnabled.h(Boolean.valueOf(SharedPreferencesUtils.isNavigationFixedScaleEnabled(application)));
        this.cruiseFixedScaleEnabled.h(Boolean.valueOf(SharedPreferencesUtils.isCruiseFixedScaleEnabled(application)));
        this.navigationScale.h(Integer.valueOf(SharedPreferencesUtils.getNavigationScale(application)));
        this.cruiseScale.h(Integer.valueOf(SharedPreferencesUtils.getCruiseScale(application)));
        try {
            refreshApps();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshApps$1() {
        try {
            refreshAppsInternal();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Thread lambda$static$0(Runnable runnable) {
        Thread thread = new Thread(runnable, "theme-apps-refresh");
        thread.setDaemon(true);
        return thread;
    }

    private void refreshAppsInternal() {
        List<AppInfo> installedNavigationApps = AppDetectionUtils.getInstalledNavigationApps(getApplication());
        for (AppInfo appInfo : installedNavigationApps) {
            appInfo.setEnabled(SharedPreferencesUtils.isThemeAppEnabled(getApplication(), appInfo.getPackageName()));
        }
        this.allAppsLiveData.i(installedNavigationApps);
        List<AppInfo> autoNaviApps = AppDetectionUtils.getAutoNaviApps(getApplication());
        for (AppInfo appInfo2 : autoNaviApps) {
            appInfo2.setEnabled(SharedPreferencesUtils.is3DAppEnabled(getApplication(), appInfo2.getPackageName()));
        }
        this.autoNaviAppsLiveData.i(autoNaviApps);
        List<AppInfo> installedNavigationApps2 = AppDetectionUtils.getInstalledNavigationApps(getApplication());
        for (AppInfo appInfo3 : installedNavigationApps2) {
            appInfo3.setEnabled(SharedPreferencesUtils.isBrightnessAppEnabled(getApplication(), appInfo3.getPackageName()));
        }
        this.brightnessAppsLiveData.i(installedNavigationApps2);
    }

    private void sendThemeBroadcastToAllEnabledApps() {
        sendThemeBroadcastToAllEnabledApps(BroadcastUtils.isCurrentNightMode(getApplication()));
    }

    private void sendThemeBroadcastToAllEnabledApps(boolean z6) {
        for (AppInfo appInfo : AppDetectionUtils.getInstalledNavigationApps(getApplication())) {
            if (SharedPreferencesUtils.isThemeAppEnabled(getApplication(), appInfo.getPackageName())) {
                BroadcastUtils.sendThemeBroadcast(getApplication(), appInfo.getPackageName(), z6);
            }
        }
    }

    private void sendThemeBroadcastToEnabledAutoNaviApps() {
        sendThemeBroadcastToEnabledAutoNaviApps(BroadcastUtils.isCurrentNightMode(getApplication()));
    }

    private void sendThemeBroadcastToEnabledAutoNaviApps(boolean z6) {
        List<AppInfo> list = (List) this.autoNaviAppsLiveData.d();
        if (list != null) {
            for (AppInfo appInfo : list) {
                if (appInfo.isEnabled()) {
                    BroadcastUtils.sendThemeBroadcast(getApplication(), appInfo.getPackageName(), z6);
                }
            }
        }
    }

    public f0 getAllAppsLiveData() {
        return this.allAppsLiveData;
    }

    public f0 getAuto3DEnabled() {
        return this.auto3DEnabled;
    }

    public f0 getAutoBrightnessEnabled() {
        return this.autoBrightnessEnabled;
    }

    public List<AppInfo> getAutoNaviApps() {
        return AppDetectionUtils.getAutoNaviApps(getApplication());
    }

    public f0 getAutoNaviAppsLiveData() {
        return this.autoNaviAppsLiveData;
    }

    public f0 getAutoThemeEnabled() {
        return this.autoThemeEnabled;
    }

    public f0 getBrightnessAppsLiveData() {
        return this.brightnessAppsLiveData;
    }

    public f0 getBrightnessThreshold() {
        return this.brightnessThreshold;
    }

    public f0 getCruise3DEnabled() {
        return this.cruise3DEnabled;
    }

    public f0 getCruiseFixedScaleEnabled() {
        return this.cruiseFixedScaleEnabled;
    }

    public f0 getCruiseScale() {
        return this.cruiseScale;
    }

    public f0 getCurrentBrightness() {
        return this.currentBrightness;
    }

    public int getCurrentBrightnessValue() {
        try {
            return Math.round((Settings.System.getInt(getApplication().getContentResolver(), "screen_brightness") * 100.0f) / 255.0f);
        } catch (Settings.SettingNotFoundException unused) {
            return 50;
        }
    }

    public f0 getNavigation3DEnabled() {
        return this.navigation3DEnabled;
    }

    public f0 getNavigationFixedScaleEnabled() {
        return this.navigationFixedScaleEnabled;
    }

    public f0 getNavigationScale() {
        return this.navigationScale;
    }

    public boolean isCruise3DBroadcastSent() {
        return this.cruise3DBroadcastSent;
    }

    public boolean isNavigation3DBroadcastSent() {
        return this.navigation3DBroadcastSent;
    }

    public void onSystemThemeChanged(Configuration configuration) {
        boolean z6 = (configuration.uiMode & 48) == 32;
        Boolean bool = (Boolean) this.autoThemeEnabled.d();
        if (bool != null && bool.booleanValue()) {
            sendThemeBroadcastToAllEnabledApps(z6);
        }
        refreshApps();
    }

    public void refreshApps() {
        REFRESH_EXECUTOR.execute(new androidx.activity.e(19, this));
    }

    public void send3DBroadcastToEnabledAutoNaviApps(int i7, int i8) {
        List<AppInfo> list = (List) this.autoNaviAppsLiveData.d();
        if (list != null) {
            for (AppInfo appInfo : list) {
                if (appInfo.isEnabled()) {
                    BroadcastUtils.send3DBroadcast(getApplication(), appInfo.getPackageName(), i7, i8);
                }
            }
        }
    }

    public void sendScaleBroadcastToEnabledAutoNaviApps(int i7, int i8) {
        List<AppInfo> list = (List) this.autoNaviAppsLiveData.d();
        if (list != null) {
            for (AppInfo appInfo : list) {
                if (appInfo.isEnabled()) {
                    BroadcastUtils.sendScaleBroadcast(getApplication(), appInfo.getPackageName(), i7, i8);
                }
            }
        }
    }

    public void set3DAppEnabled(String str, boolean z6) {
        SharedPreferencesUtils.set3DAppEnabled(getApplication(), str, z6);
        List list = (List) this.autoNaviAppsLiveData.d();
        if (list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AppInfo appInfo = (AppInfo) it.next();
                if (appInfo.getPackageName().equals(str)) {
                    appInfo.setEnabled(z6);
                    break;
                }
            }
            this.autoNaviAppsLiveData.i(list);
        }
        if (z6) {
            Boolean bool = (Boolean) this.auto3DEnabled.d();
            if (AppDetectionUtils.isAmapPackage(str) && bool != null && bool.booleanValue()) {
                BroadcastUtils.send3DBroadcast(getApplication(), str, 2, 2);
            }
        }
    }

    public void setAppEnabled(String str, boolean z6) {
        SharedPreferencesUtils.setAppEnabled(getApplication(), str, z6);
    }

    public void setAuto3DEnabled(boolean z6) {
        this.auto3DEnabled.h(Boolean.valueOf(z6));
        SharedPreferencesUtils.setAuto3DEnabled(getApplication(), z6);
    }

    public void setAutoBrightnessEnabled(boolean z6) {
        this.autoBrightnessEnabled.h(Boolean.valueOf(z6));
        SharedPreferencesUtils.setAutoBrightnessEnabled(getApplication(), z6);
        if (z6) {
            setAutoThemeEnabled(false);
        }
    }

    public void setAutoThemeEnabled(boolean z6) {
        this.autoThemeEnabled.h(Boolean.valueOf(z6));
        SharedPreferencesUtils.setAutoThemeEnabled(getApplication(), z6);
        if (z6) {
            setAutoBrightnessEnabled(false);
        }
        if (z6) {
            sendThemeBroadcastToAllEnabledApps();
        }
    }

    public void setBrightnessAppEnabled(String str, boolean z6) {
        Boolean bool;
        SharedPreferencesUtils.setBrightnessAppEnabled(getApplication(), str, z6);
        List list = (List) this.brightnessAppsLiveData.d();
        if (list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AppInfo appInfo = (AppInfo) it.next();
                if (appInfo.getPackageName().equals(str)) {
                    appInfo.setEnabled(z6);
                    break;
                }
            }
            this.brightnessAppsLiveData.i(list);
        }
        if (z6 && (bool = (Boolean) this.autoBrightnessEnabled.d()) != null && bool.booleanValue()) {
            boolean z7 = getCurrentBrightnessValue() < (this.brightnessThreshold.d() != null ? ((Integer) this.brightnessThreshold.d()).intValue() : 50);
            if (AppDetectionUtils.isAmapPackage(str)) {
                BroadcastUtils.sendThemeBroadcast(getApplication(), str, z7);
            } else if (str.startsWith(AppDetectionUtils.BAIDU_PREFIX)) {
                BroadcastUtils.sendThemeBroadcast(getApplication(), str, z7);
            } else if (str.startsWith(AppDetectionUtils.TENCENT_PREFIX)) {
                BroadcastUtils.sendThemeBroadcast(getApplication(), str, z7);
            }
        }
    }

    public void setBrightnessThreshold(int i7) {
        this.brightnessThreshold.h(Integer.valueOf(i7));
        SharedPreferencesUtils.setBrightnessThreshold(getApplication(), i7);
    }

    public void setCruise3DBroadcastSent(boolean z6) {
        this.cruise3DBroadcastSent = z6;
    }

    public void setCruise3DEnabled(boolean z6) {
        this.cruise3DEnabled.h(Boolean.valueOf(z6));
        SharedPreferencesUtils.setCruise3DEnabled(getApplication(), z6);
    }

    public void setCruiseFixedScaleEnabled(boolean z6) {
        this.cruiseFixedScaleEnabled.h(Boolean.valueOf(z6));
        SharedPreferencesUtils.setCruiseFixedScaleEnabled(getApplication(), z6);
    }

    public void setCruiseScale(int i7) {
        this.cruiseScale.h(Integer.valueOf(i7));
        SharedPreferencesUtils.setCruiseScale(getApplication(), i7);
    }

    public void setNavigation3DBroadcastSent(boolean z6) {
        this.navigation3DBroadcastSent = z6;
    }

    public void setNavigation3DEnabled(boolean z6) {
        this.navigation3DEnabled.h(Boolean.valueOf(z6));
        SharedPreferencesUtils.setNavigation3DEnabled(getApplication(), z6);
    }

    public void setNavigationFixedScaleEnabled(boolean z6) {
        this.navigationFixedScaleEnabled.h(Boolean.valueOf(z6));
        SharedPreferencesUtils.setNavigationFixedScaleEnabled(getApplication(), z6);
    }

    public void setNavigationScale(int i7) {
        this.navigationScale.h(Integer.valueOf(i7));
        SharedPreferencesUtils.setNavigationScale(getApplication(), i7);
    }

    public void setThemeAppEnabled(String str, boolean z6) {
        SharedPreferencesUtils.setThemeAppEnabled(getApplication(), str, z6);
        List list = (List) this.allAppsLiveData.d();
        if (list != null) {
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AppInfo appInfo = (AppInfo) it.next();
                if (appInfo.getPackageName().equals(str)) {
                    appInfo.setEnabled(z6);
                    break;
                }
            }
            this.allAppsLiveData.i(list);
        }
        if (z6) {
            Boolean bool = (Boolean) this.autoThemeEnabled.d();
            boolean isCurrentNightMode = BroadcastUtils.isCurrentNightMode(getApplication());
            if (AppDetectionUtils.isAmapPackage(str) && bool != null && bool.booleanValue()) {
                BroadcastUtils.sendThemeBroadcast(getApplication(), str, isCurrentNightMode);
                return;
            }
            if (str.startsWith(AppDetectionUtils.BAIDU_PREFIX) && bool != null && bool.booleanValue()) {
                BroadcastUtils.sendThemeBroadcast(getApplication(), str, isCurrentNightMode);
            } else if (str.startsWith(AppDetectionUtils.TENCENT_PREFIX) && bool != null && bool.booleanValue()) {
                BroadcastUtils.sendThemeBroadcast(getApplication(), str, isCurrentNightMode);
            }
        }
    }

    public void updateCurrentBrightness() {
        this.currentBrightness.h(Integer.valueOf(getCurrentBrightnessValue()));
    }
}
