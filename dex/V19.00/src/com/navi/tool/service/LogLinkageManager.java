package com.navi.tool.service;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.display.DisplayManager;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.Display;
import android.view.KeyEvent;
import com.navi.tool.amapflying.AmapBroadcastService;
import com.navi.tool.amapflying.receiver.QQMusicLyricReceiver;
import com.navi.tool.amapflying.util.kuwo.KuwoMusicManager;
import com.navi.tool.amapflying.util.netease.NeteaseMusicManager;
import com.navi.tool.amapflying.util.qq.QQMusicManager;
import com.navi.tool.log.api.LogDecision;
import com.navi.tool.log.api.LogEngine;
import com.navi.tool.log.api.LogRuleData;
import com.navi.tool.model.LogAppProfile;
import com.navi.tool.model.LogAssistantProfile;
import com.navi.tool.model.LogMediaProfile;
import com.navi.tool.service.LogModuleLoader;
import com.navi.tool.ui.extra.DesktopAssistantCarControlHelper;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.LogAppProfileStorage;
import com.navi.tool.utils.LogAssistantProfileStorage;
import com.navi.tool.utils.LogMediaProfileStorage;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class LogLinkageManager {
    private static final long LOG_TIME_THRESHOLD = 1000;
    private static final String TAG = "LogLinkageManager";
    private final Context context;
    private volatile LogEngine engine;
    private LogReaderThread mLogReaderThread;
    private static final Pattern LOG_TIME_PATTERN = Pattern.compile("^(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})\\.(\\d{3})");
    private static long sLastNextMs = 0;
    private static long sLastPrevMs = 0;
    private static boolean sMediaPrimeDone = false;
    private static int sLunaLoopCycleIndex = 0;
    private static int sKuwoLoopCycleIndex = 0;
    private static MediaPlayer sCurrentMediaPlayer = null;
    private static final Object sMediaPlayerLock = new Object();
    private static final ExecutorService sPlayExecutor = Executors.newSingleThreadExecutor();
    private static final ScheduledExecutorService sActionScheduler = Executors.newScheduledThreadPool(4);
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private volatile boolean active = false;
    private final ExecutorService mReloadExecutor = Executors.newSingleThreadExecutor();
    private final List<LogAppProfile> enabledAppProfiles = new ArrayList();
    private final List<LogMediaProfile> enabledMediaProfiles = new ArrayList();
    private final List<LogAssistantProfile> enabledAssistantProfiles = new ArrayList();
    private final Map<String, LogAppProfile> appDecisionProfiles = new HashMap();
    private final Map<String, LogMediaProfile> mediaDecisionProfiles = new HashMap();
    private final Map<String, LogAssistantProfile> assistantDecisionProfiles = new HashMap();
    private final List<LogRuleData> engineRules = new ArrayList();
    private int engineRetryAttempt = 0;
    private final Runnable engineRetryRunnable = new r(this, 2);

    public class LogReaderThread extends Thread {
        private Process process;

        private LogReaderThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process process;
            HashSet hashSet;
            String readLine;
            setName("LogLinkage-Reader");
            try {
                try {
                    hashSet = new HashSet();
                    Iterator it = LogLinkageManager.this.enabledAppProfiles.iterator();
                    while (it.hasNext()) {
                        hashSet.add(((LogAppProfile) it.next()).getTag());
                    }
                    Iterator it2 = LogLinkageManager.this.enabledMediaProfiles.iterator();
                    while (it2.hasNext()) {
                        hashSet.add(((LogMediaProfile) it2.next()).getTag());
                    }
                    Iterator it3 = LogLinkageManager.this.enabledAssistantProfiles.iterator();
                    while (it3.hasNext()) {
                        hashSet.add(((LogAssistantProfile) it3.next()).getTag());
                    }
                } catch (IOException unused) {
                    Thread.currentThread().isInterrupted();
                    process = this.process;
                    if (process == null) {
                        return;
                    }
                }
                if (hashSet.isEmpty()) {
                    Process process2 = this.process;
                    if (process2 != null) {
                        process2.destroy();
                        return;
                    }
                    return;
                }
                StringBuilder sb = new StringBuilder("logcat -v time *:S");
                Iterator it4 = hashSet.iterator();
                while (it4.hasNext()) {
                    String str = (String) it4.next();
                    if (str != null && !str.trim().isEmpty()) {
                        sb.append(" ");
                        sb.append(str.trim());
                        sb.append(":V");
                    }
                }
                this.process = Runtime.getRuntime().exec(sb.toString());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
                while (!Thread.currentThread().isInterrupted() && (readLine = bufferedReader.readLine()) != null) {
                    if (LogLinkageManager.this.isLogWithinTimeThreshold(readLine)) {
                        long currentTimeMillis = System.currentTimeMillis();
                        LogEngine logEngine = LogLinkageManager.this.engine;
                        if (logEngine != null) {
                            Iterator<LogDecision> it5 = logEngine.evaluate(LogLinkageManager.this.engineRules, readLine, currentTimeMillis).iterator();
                            while (it5.hasNext()) {
                                LogLinkageManager.this.dispatchDecision(it5.next(), readLine);
                            }
                        }
                    }
                }
                process = this.process;
                if (process == null) {
                    return;
                }
                process.destroy();
            } catch (Throwable th) {
                Process process3 = this.process;
                if (process3 != null) {
                    process3.destroy();
                }
                throw th;
            }
        }
    }

    public LogLinkageManager(Context context) {
        this.context = context.getApplicationContext();
    }

    private static void clearCurrentMediaPlayer(MediaPlayer mediaPlayer) {
        synchronized (sMediaPlayerLock) {
            try {
                if (sCurrentMediaPlayer == mediaPlayer) {
                    sCurrentMediaPlayer = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void closePackage(String str) {
        if (str != null) {
            try {
                if (str.endsWith(".desktop_stub")) {
                    str = str.replace(".desktop_stub", s5.h0.FRAGMENT_ENCODE_SET);
                } else if (str.endsWith(".file_stub")) {
                    str = str.replace(".file_stub", s5.h0.FRAGMENT_ENCODE_SET);
                }
            } catch (Exception unused) {
                return;
            }
        }
        ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
        if (activityManager != null) {
            activityManager.killBackgroundProcesses(str);
        }
        try {
            Runtime.getRuntime().exec("am force-stop " + str);
        } catch (Exception unused2) {
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    private static MediaPlayer detachCurrentMediaPlayer() {
        MediaPlayer mediaPlayer;
        synchronized (sMediaPlayerLock) {
            mediaPlayer = sCurrentMediaPlayer;
            sCurrentMediaPlayer = null;
        }
        return mediaPlayer;
    }

    private void dispatchAppDecision(LogAppProfile logAppProfile) {
        String action = logAppProfile.getAction();
        long delaySeconds = logAppProfile.getDelaySeconds();
        if (delaySeconds > 0) {
            sActionScheduler.schedule(new y0.n(this, logAppProfile, action, 9), delaySeconds, TimeUnit.SECONDS);
        } else {
            lambda$dispatchAppDecision$2(logAppProfile, action);
        }
    }

    private void dispatchAssistantDecision(LogAssistantProfile logAssistantProfile) {
        String action = logAssistantProfile.getAction();
        long delaySeconds = logAssistantProfile.getDelaySeconds();
        if (delaySeconds > 0) {
            sActionScheduler.schedule(new y0.n(this, logAssistantProfile, action, 10), delaySeconds, TimeUnit.SECONDS);
        } else {
            lambda$dispatchAssistantDecision$4(logAssistantProfile, action);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchDecision(LogDecision logDecision, String str) {
        if (logDecision == null) {
            return;
        }
        LogAppProfile logAppProfile = this.appDecisionProfiles.get(logDecision.ruleId);
        if (logAppProfile != null) {
            dispatchAppDecision(logAppProfile);
            return;
        }
        LogMediaProfile logMediaProfile = this.mediaDecisionProfiles.get(logDecision.ruleId);
        if (logMediaProfile != null) {
            dispatchMediaDecision(logMediaProfile, str);
            return;
        }
        LogAssistantProfile logAssistantProfile = this.assistantDecisionProfiles.get(logDecision.ruleId);
        if (logAssistantProfile != null) {
            dispatchAssistantDecision(logAssistantProfile);
        }
    }

    private void dispatchMediaDecision(LogMediaProfile logMediaProfile, String str) {
        logMediaProfile.getName();
        String action = logMediaProfile.getAction();
        long delaySeconds = logMediaProfile.getDelaySeconds();
        if (delaySeconds > 0) {
            sActionScheduler.schedule(new o4.w(this, logMediaProfile, action, str, 2), delaySeconds, TimeUnit.SECONDS);
        } else {
            lambda$dispatchMediaDecision$3(logMediaProfile, action, str);
        }
    }

    private void dispatchMediaKey(int i7) {
        try {
            AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
            long currentTimeMillis = System.currentTimeMillis();
            KeyEvent keyEvent = new KeyEvent(currentTimeMillis, currentTimeMillis, 0, i7, 0);
            KeyEvent keyEvent2 = new KeyEvent(currentTimeMillis, currentTimeMillis, 1, i7, 0);
            if (audioManager != null) {
                audioManager.dispatchMediaKeyEvent(keyEvent);
                audioManager.dispatchMediaKeyEvent(keyEvent2);
            }
        } catch (Exception unused) {
        }
    }

    private void ensureEngineLoaded() {
        if (this.active) {
            if (this.engine != null) {
                reload();
            } else {
                LogModuleLoader.getInstance().ensureEngine(this.context, new LogModuleLoader.Callback() { // from class: com.navi.tool.service.LogLinkageManager.1
                    @Override // com.navi.tool.service.LogModuleLoader.Callback
                    public void onError(String str) {
                        LogLinkageManager.this.scheduleEngineRetry();
                    }

                    @Override // com.navi.tool.service.LogModuleLoader.Callback
                    public void onReady(LogEngine logEngine) {
                        if (LogLinkageManager.this.active) {
                            LogLinkageManager.this.engine = logEngine;
                            LogLinkageManager.this.engineRetryAttempt = 0;
                            LogLinkageManager.this.mMainHandler.removeCallbacks(LogLinkageManager.this.engineRetryRunnable);
                            LogLinkageManager.this.reload();
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: executeAppAction, reason: merged with bridge method [inline-methods] */
    public void lambda$dispatchAppDecision$2(LogAppProfile logAppProfile, String str) {
        if ("open".equalsIgnoreCase(str)) {
            launchPackageMainActivity(logAppProfile.getPackageName());
            logAppProfile.getPackageName();
            return;
        }
        if ("close".equalsIgnoreCase(str)) {
            closePackage(logAppProfile.getPackageName());
            logAppProfile.getPackageName();
            return;
        }
        if ("home".equalsIgnoreCase(str)) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.addFlags(268435456);
            this.context.startActivity(intent);
            return;
        }
        if ("switch_floating_app".equalsIgnoreCase(str)) {
            Intent intent2 = new Intent("com.navi.tool.ACTION_SWITCH_FLOATING_APP");
            intent2.setPackage(this.context.getPackageName());
            this.context.sendBroadcast(intent2);
            return;
        }
        if ("switch_lyrics_fx".equalsIgnoreCase(str)) {
            Intent intent3 = new Intent("com.navi.tool.ACTION_TOGGLE_LYRICS_FX");
            intent3.setPackage(this.context.getPackageName());
            this.context.sendBroadcast(intent3);
            return;
        }
        if ("switch_view".equalsIgnoreCase(str)) {
            Intent intent4 = new Intent("com.navi.tool.ACTION_SWITCH_DESKTOP_VIEW");
            intent4.setPackage(this.context.getPackageName());
            this.context.sendBroadcast(intent4);
            return;
        }
        if ("switch_clean_mode".equalsIgnoreCase(str)) {
            Intent intent5 = new Intent("com.navi.tool.ACTION_SWITCH_CLEAN_MODE");
            intent5.setPackage(this.context.getPackageName());
            this.context.sendBroadcast(intent5);
            return;
        }
        if ("switch_wallpaper_mode".equalsIgnoreCase(str)) {
            Intent intent6 = new Intent("com.navi.tool.ACTION_SWITCH_WALLPAPER_MODE");
            intent6.setPackage(this.context.getPackageName());
            this.context.sendBroadcast(intent6);
        } else {
            if ("displaysiri".equalsIgnoreCase(str)) {
                Intent intent7 = new Intent("com.shihab.diplay.SIRI");
                intent7.setPackage("com.shihab.diplay");
                intent7.addFlags(32);
                this.context.sendBroadcast(intent7);
                return;
            }
            if ("adb_switch".equalsIgnoreCase(str)) {
                AppDetectionUtils.launchAdbSwitch(this.context);
            } else {
                "none".equalsIgnoreCase(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: executeAssistantAction, reason: merged with bridge method [inline-methods] */
    public void lambda$dispatchAssistantDecision$4(LogAssistantProfile logAssistantProfile, String str) {
        if ("activate_cast".equalsIgnoreCase(str)) {
            triggerActivateCast();
            return;
        }
        try {
            if ("drive_enjoy_mode".equalsIgnoreCase(str)) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
                intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"头枕扬声器\",\"nameValue\":\"驾享\"}}}");
                this.context.startForegroundService(intent);
            } else if ("open_ac_ui".equalsIgnoreCase(str)) {
                Intent intent2 = new Intent();
                intent2.setComponent(new ComponentName("com.desaysv.svhvac", "com.desaysv.svhvac.service.HvacService"));
                intent2.putExtra("type", "com.desaysv.svhvac.OPEN_PANEL");
                this.context.startService(intent2);
            } else if ("close_ac_ui".equalsIgnoreCase(str)) {
                Intent intent3 = new Intent();
                intent3.setComponent(new ComponentName("com.desaysv.svhvac", "com.desaysv.svhvac.service.HvacService"));
                intent3.putExtra("type", "com.desaysv.svhvac.CLOSE_PANEL");
                this.context.startService(intent3);
            } else if ("open_systemui_ac_ui".equalsIgnoreCase(str)) {
                Intent intent4 = new Intent();
                intent4.setComponent(new ComponentName("com.android.systemui", "com.desaysv.svhvac.service.HvacService"));
                intent4.putExtra("type", "com.desaysv.svhvac.OPEN_PANEL");
                this.context.startService(intent4);
            } else if ("close_systemui_ac_ui".equalsIgnoreCase(str)) {
                Intent intent5 = new Intent();
                intent5.setComponent(new ComponentName("com.android.systemui", "com.desaysv.svhvac.service.HvacService"));
                intent5.putExtra("type", "com.desaysv.svhvac.CLOSE_PANEL");
                this.context.startService(intent5);
            } else {
                if (!"open_360_camera".equalsIgnoreCase(str)) {
                    if ("instrument_cast".equalsIgnoreCase(str)) {
                        triggerInstrumentCast();
                        return;
                    }
                    if ("cluster_enlarge".equalsIgnoreCase(str)) {
                        sendClusterEnlargeCommand(this.context);
                        return;
                    }
                    if ("cluster_shrink".equalsIgnoreCase(str)) {
                        sendClusterShrinkCommand(this.context);
                        return;
                    }
                    if ("close_windows".equalsIgnoreCase(str)) {
                        sendCloseWindowsCommand(this.context);
                        return;
                    }
                    if ("open_sunblind".equalsIgnoreCase(str)) {
                        sendOpenSunblindCommand(this.context);
                        return;
                    }
                    if ("close_sunblind".equalsIgnoreCase(str)) {
                        sendCloseSunblindCommand(this.context);
                        return;
                    }
                    if ("epb_release".equalsIgnoreCase(str)) {
                        DesktopAssistantCarControlHelper.actionReleaseEpb(this.context);
                        return;
                    }
                    if ("epb_apply".equalsIgnoreCase(str)) {
                        DesktopAssistantCarControlHelper.actionApplyEpb(this.context);
                        return;
                    }
                    if ("radar_open".equalsIgnoreCase(str)) {
                        DesktopAssistantCarControlHelper.actionOpenRadar(this.context);
                        return;
                    } else if ("radar_close".equalsIgnoreCase(str)) {
                        DesktopAssistantCarControlHelper.actionCloseRadar(this.context);
                        return;
                    } else {
                        if ("none".equalsIgnoreCase(str)) {
                            return;
                        }
                        logAssistantProfile.getName();
                        return;
                    }
                }
                Intent intent6 = new Intent();
                intent6.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
                intent6.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"全景\"}}}");
                this.context.startService(intent6);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: executeMediaAction, reason: merged with bridge method [inline-methods] */
    public void lambda$dispatchMediaDecision$3(LogMediaProfile logMediaProfile, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (LogLinkageManager.class) {
            try {
                if ("next".equalsIgnoreCase(str)) {
                    if (currentTimeMillis - sLastNextMs < 1500) {
                        return;
                    }
                    sLastNextMs = currentTimeMillis;
                    dispatchMediaKey(87);
                    logMediaProfile.getName();
                } else if ("previous".equalsIgnoreCase(str)) {
                    if (currentTimeMillis - sLastPrevMs < 1500) {
                        return;
                    }
                    sLastPrevMs = currentTimeMillis;
                    dispatchMediaKey(88);
                    logMediaProfile.getName();
                } else if ("play".equalsIgnoreCase(str)) {
                    dispatchMediaKey(z1.c.NOT_LONG);
                    logMediaProfile.getName();
                } else if ("pause".equalsIgnoreCase(str)) {
                    dispatchMediaKey(z1.c.NEG_FLOAT);
                    logMediaProfile.getName();
                } else if ("toggle".equalsIgnoreCase(str)) {
                    dispatchMediaKey(85);
                    logMediaProfile.getName();
                } else if (!"none".equalsIgnoreCase(str)) {
                    if ("qq_music_car".equalsIgnoreCase(str)) {
                        triggerQqMusicCar(str2);
                        logMediaProfile.getName();
                    } else if ("kuwo_music_car".equalsIgnoreCase(str)) {
                        triggerKuwoMusicCar(str2);
                        logMediaProfile.getName();
                    } else if ("netease_music_car".equalsIgnoreCase(str)) {
                        triggerNeteaseMusicCar(str2);
                        logMediaProfile.getName();
                    } else if ("luna_music_car".equalsIgnoreCase(str)) {
                        triggerLunaMusicCar(str2);
                        logMediaProfile.getName();
                    } else if ("auto_search".equalsIgnoreCase(str)) {
                        triggerAutoSearch(str2);
                        logMediaProfile.getName();
                    } else if ("auto_favorite".equalsIgnoreCase(str)) {
                        triggerAutoFavorite();
                        logMediaProfile.getName();
                    } else if ("cycle_loop_mode".equalsIgnoreCase(str)) {
                        triggerCycleLoopMode();
                        logMediaProfile.getName();
                    } else if (logMediaProfile.getMediaPath() != null && !logMediaProfile.getMediaPath().trim().isEmpty()) {
                        playMediaFile(this.context, logMediaProfile.getMediaPath());
                        logMediaProfile.getName();
                    }
                }
            } finally {
            }
        }
    }

    private String extractMediaKeyword(String str) {
        int i7;
        if (str == null) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        int indexOf = str.toLowerCase().indexOf("srresult");
        if (indexOf >= 0) {
            int indexOf2 = str.indexOf(61, indexOf);
            str = (indexOf2 < 0 || (i7 = indexOf2 + 1) >= str.length()) ? s5.h0.FRAGMENT_ENCODE_SET : str.substring(i7).trim();
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        int indexOf3 = trim.indexOf("搜索");
        int indexOf4 = trim.indexOf("播放");
        if (indexOf3 >= 0) {
            trim = trim.substring(indexOf3 + 2);
        } else if (indexOf4 >= 0) {
            trim = trim.substring(indexOf4 + 2);
        }
        return trim.trim();
    }

    private int findSecondaryDisplayId() {
        DisplayManager displayManager;
        try {
            displayManager = (DisplayManager) this.context.getSystemService("display");
        } catch (Exception unused) {
        }
        if (displayManager == null) {
            return -1;
        }
        for (Display display : displayManager.getDisplays()) {
            if (display.getDisplayId() != 0) {
                return display.getDisplayId();
            }
        }
        return -1;
    }

    private static boolean isCurrentMediaPlayer(MediaPlayer mediaPlayer) {
        boolean z6;
        synchronized (sMediaPlayerLock) {
            z6 = sCurrentMediaPlayer == mediaPlayer;
        }
        return z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLogWithinTimeThreshold(String str) {
        if (str == null) {
            return false;
        }
        try {
            if (str.length() >= 18 && str.charAt(2) == '-' && str.charAt(5) == ' ') {
                Matcher matcher = LOG_TIME_PATTERN.matcher(str);
                if (!matcher.find()) {
                    return false;
                }
                int parseInt = Integer.parseInt(matcher.group(1));
                int parseInt2 = Integer.parseInt(matcher.group(2));
                int parseInt3 = Integer.parseInt(matcher.group(3));
                int parseInt4 = Integer.parseInt(matcher.group(4));
                int parseInt5 = Integer.parseInt(matcher.group(5));
                int parseInt6 = Integer.parseInt(matcher.group(6));
                Calendar calendar = Calendar.getInstance();
                int i7 = calendar.get(1);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(i7, parseInt - 1, parseInt2, parseInt3, parseInt4, parseInt5);
                calendar2.set(14, parseInt6);
                if (calendar2.after(calendar)) {
                    calendar2.set(1, i7 - 1);
                }
                return Math.abs(calendar.getTimeInMillis() - calendar2.getTimeInMillis()) <= 1000;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.active && this.engine == null) {
            ensureEngineLoaded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$playMediaFile$10(AudioManager audioManager, boolean[] zArr, AudioFocusRequest[] audioFocusRequestArr, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, MediaPlayer mediaPlayer, int i7, int i8) {
        AudioFocusRequest audioFocusRequest;
        safeReleaseMediaPlayer(mediaPlayer, false);
        if (audioManager != null) {
            try {
                if (zArr[0] && (audioFocusRequest = audioFocusRequestArr[0]) != null) {
                    audioManager.abandonAudioFocusRequest(audioFocusRequest);
                }
            } catch (Exception unused) {
            }
        }
        clearCurrentMediaPlayer(mediaPlayer);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$playMediaFile$11(String str, String str2, MediaPlayer mediaPlayer) {
        if (!isCurrentMediaPlayer(mediaPlayer)) {
            safeReleaseMediaPlayer(mediaPlayer, false);
            return;
        }
        if (!"navigation".equalsIgnoreCase(str) && !sMediaPrimeDone) {
            try {
                Thread.sleep(150L);
            } catch (Exception unused) {
            }
        }
        if (!isCurrentMediaPlayer(mediaPlayer)) {
            safeReleaseMediaPlayer(mediaPlayer, false);
            return;
        }
        try {
            mediaPlayer.start();
            if ("navigation".equalsIgnoreCase(str) || sMediaPrimeDone) {
                return;
            }
            sMediaPrimeDone = true;
        } catch (IllegalStateException unused2) {
            clearCurrentMediaPlayer(mediaPlayer);
            safeReleaseMediaPlayer(mediaPlayer, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$playMediaFile$12(android.content.Context r11, final java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.LogLinkageManager.lambda$playMediaFile$12(android.content.Context, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$playMediaFile$8(int i7) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$playMediaFile$9(AudioManager audioManager, boolean[] zArr, AudioFocusRequest[] audioFocusRequestArr, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, MediaPlayer mediaPlayer) {
        AudioFocusRequest audioFocusRequest;
        safeReleaseMediaPlayer(mediaPlayer, false);
        if (audioManager != null) {
            try {
                if (zArr[0] && (audioFocusRequest = audioFocusRequestArr[0]) != null) {
                    audioManager.abandonAudioFocusRequest(audioFocusRequest);
                }
            } catch (Exception unused) {
            }
        }
        clearCurrentMediaPlayer(mediaPlayer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reload$1() {
        synchronized (this) {
            reloadInternal();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerAutoFavorite$7() {
        try {
            QQMusicManager.getInstance(this.context).queryCurrentState();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerInstrumentCast$5(ActivityOptions activityOptions, int i7, Intent intent, String str) {
        try {
            activityOptions.setLaunchDisplayId(i7);
            intent.addFlags(738328576);
            this.context.startActivity(intent, activityOptions.toBundle());
            SharedPreferencesUtils.setScreenCastingAppState(this.context, str, "instrument");
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$triggerNeteaseMusicCar$6(String str) {
        NeteaseMusicManager.getInstance(this.context).searchAndPlay(str);
    }

    private void launchPackageMainActivity(String str) {
        ResolveInfo resolveInfo;
        if (str != null) {
            try {
                if (str.endsWith(".desktop_stub")) {
                    String replace = str.replace(".desktop_stub", s5.h0.FRAGMENT_ENCODE_SET);
                    if (replace.equals(this.context.getPackageName())) {
                        Intent intent = new Intent();
                        intent.setClassName(replace, "com.navi.tool.DesktopAssistantStubActivity");
                        intent.addFlags(872415232);
                        this.context.startActivity(intent);
                        return;
                    }
                } else if (str.endsWith(".file_stub")) {
                    String replace2 = str.replace(".file_stub", s5.h0.FRAGMENT_ENCODE_SET);
                    if (replace2.equals(this.context.getPackageName())) {
                        Intent intent2 = new Intent();
                        intent2.setClassName(replace2, "com.navi.tool.FileAssistantActivity");
                        intent2.addFlags(872415232);
                        this.context.startActivity(intent2);
                        return;
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
        PackageManager packageManager = this.context.getPackageManager();
        Intent intent3 = new Intent("android.intent.action.MAIN");
        intent3.addCategory("android.intent.category.LAUNCHER");
        Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent3, 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                resolveInfo = null;
                break;
            } else {
                resolveInfo = it.next();
                if (resolveInfo.activityInfo.packageName.equals(str)) {
                    break;
                }
            }
        }
        if (resolveInfo != null) {
            Intent intent4 = new Intent("android.intent.action.MAIN");
            intent4.addCategory("android.intent.category.LAUNCHER");
            intent4.setClassName(str, resolveInfo.activityInfo.name);
            intent4.addFlags(872546304);
            this.context.startActivity(intent4);
        }
    }

    public static void playMediaFile(Context context, String str) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        sPlayExecutor.submit(new l0(1, context, str));
    }

    private void rebuildEngineRules() {
        this.engineRules.clear();
        this.appDecisionProfiles.clear();
        this.mediaDecisionProfiles.clear();
        this.assistantDecisionProfiles.clear();
        for (LogAppProfile logAppProfile : this.enabledAppProfiles) {
            String str = "App-" + logAppProfile.getName();
            this.appDecisionProfiles.put(str, logAppProfile);
            this.engineRules.add(new LogRuleData(str, 0, logAppProfile.getName(), logAppProfile.getTag(), logAppProfile.getPattern(), logAppProfile.getAction(), logAppProfile.getConditionType(), logAppProfile.getConditionVariable(), logAppProfile.getConditionOperator(), logAppProfile.getConditionValue(), logAppProfile.getVariableName(), logAppProfile.getVariableOp(), logAppProfile.getVariableValue(), logAppProfile.getCooldownSeconds(), logAppProfile.getComboSeconds(), logAppProfile.getComboCount()));
        }
        for (LogMediaProfile logMediaProfile : this.enabledMediaProfiles) {
            String str2 = "Media-" + logMediaProfile.getName();
            this.mediaDecisionProfiles.put(str2, logMediaProfile);
            this.engineRules.add(new LogRuleData(str2, 1, logMediaProfile.getName(), logMediaProfile.getTag(), logMediaProfile.getPattern(), logMediaProfile.getAction(), logMediaProfile.getConditionType(), logMediaProfile.getConditionVariable(), logMediaProfile.getConditionOperator(), logMediaProfile.getConditionValue(), logMediaProfile.getVariableName(), logMediaProfile.getVariableOp(), logMediaProfile.getVariableValue(), logMediaProfile.getCooldownSeconds(), logMediaProfile.getComboSeconds(), logMediaProfile.getComboCount()));
        }
        for (LogAssistantProfile logAssistantProfile : this.enabledAssistantProfiles) {
            String str3 = "Assist-" + logAssistantProfile.getName();
            this.assistantDecisionProfiles.put(str3, logAssistantProfile);
            this.engineRules.add(new LogRuleData(str3, 2, logAssistantProfile.getName(), logAssistantProfile.getTag(), logAssistantProfile.getPattern(), logAssistantProfile.getAction(), logAssistantProfile.getConditionType(), logAssistantProfile.getConditionVariable(), logAssistantProfile.getConditionOperator(), logAssistantProfile.getConditionValue(), logAssistantProfile.getVariableName(), logAssistantProfile.getVariableOp(), logAssistantProfile.getVariableValue(), logAssistantProfile.getCooldownSeconds(), logAssistantProfile.getComboSeconds(), logAssistantProfile.getComboCount()));
        }
    }

    private void reloadInternal() {
        List<LogAssistantProfile> loadProfiles;
        List<LogMediaProfile> loadProfiles2;
        List<LogAppProfile> loadProfiles3;
        stopLogReader();
        if (this.active && SharedPreferencesUtils.getAppPrefs(this.context).getBoolean("log_link_switch_state", false)) {
            boolean z6 = SharedPreferencesUtils.getAppPrefs(this.context).getBoolean("log_link_apps_switch_state", false);
            boolean z7 = SharedPreferencesUtils.getAppPrefs(this.context).getBoolean("log_link_media_switch_state", false);
            boolean isLogAssistantLinkEnabled = SharedPreferencesUtils.isLogAssistantLinkEnabled(this.context);
            this.enabledAppProfiles.clear();
            this.enabledMediaProfiles.clear();
            this.enabledAssistantProfiles.clear();
            if (this.engine != null) {
                this.engine.resetRuleState();
            }
            if (z6 && (loadProfiles3 = LogAppProfileStorage.loadProfiles(this.context)) != null) {
                for (LogAppProfile logAppProfile : loadProfiles3) {
                    if (logAppProfile.isEnabled()) {
                        this.enabledAppProfiles.add(logAppProfile);
                    }
                }
            }
            if (z7 && (loadProfiles2 = LogMediaProfileStorage.loadProfiles(this.context)) != null) {
                for (LogMediaProfile logMediaProfile : loadProfiles2) {
                    if (logMediaProfile.isEnabled()) {
                        this.enabledMediaProfiles.add(logMediaProfile);
                    }
                }
            }
            if (isLogAssistantLinkEnabled && (loadProfiles = LogAssistantProfileStorage.loadProfiles(this.context)) != null) {
                for (LogAssistantProfile logAssistantProfile : loadProfiles) {
                    if (logAssistantProfile.isEnabled()) {
                        this.enabledAssistantProfiles.add(logAssistantProfile);
                    }
                }
            }
            rebuildEngineRules();
            int size = this.enabledAssistantProfiles.size() + this.enabledMediaProfiles.size() + this.enabledAppProfiles.size();
            this.enabledAppProfiles.size();
            this.enabledMediaProfiles.size();
            this.enabledAssistantProfiles.size();
            if (size <= 0 || this.engine == null) {
                return;
            }
            startLogReader();
        }
    }

    private static void safeReleaseMediaPlayer(MediaPlayer mediaPlayer, boolean z6) {
        if (mediaPlayer == null) {
            return;
        }
        if (z6) {
            try {
                mediaPlayer.stop();
            } catch (Exception unused) {
            }
        }
        try {
            mediaPlayer.release();
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleEngineRetry() {
        if (this.active) {
            int i7 = this.engineRetryAttempt + 1;
            this.engineRetryAttempt = i7;
            long min = Math.min(i7 * 30000, 300000L);
            this.mMainHandler.removeCallbacks(this.engineRetryRunnable);
            this.mMainHandler.postDelayed(this.engineRetryRunnable, min);
            long j7 = min / 1000;
        }
    }

    private void sendCloseSunblindCommand(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"CLOSE\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"遮阳帘\"}}}");
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    private void sendCloseWindowsCommand(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"CLOSE\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"所有窗户\"}}}");
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    private void sendClusterEnlargeCommand(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"仪表导航\"}}}");
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    private void sendClusterShrinkCommand(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"CLOSE\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"仪表导航\"}}}");
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    private void sendOpenSunblindCommand(Context context) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"遮阳帘\"}}}");
            context.startService(intent);
        } catch (Exception unused) {
        }
    }

    private void startLogReader() {
        LogReaderThread logReaderThread = new LogReaderThread();
        this.mLogReaderThread = logReaderThread;
        logReaderThread.start();
    }

    private void stopLogReader() {
        LogReaderThread logReaderThread = this.mLogReaderThread;
        if (logReaderThread != null) {
            logReaderThread.interrupt();
            try {
                this.mLogReaderThread.join(100L);
            } catch (InterruptedException unused) {
            }
            this.mLogReaderThread = null;
        }
    }

    private void triggerActivateCast() {
        try {
            Intent intent = new Intent(this.context, (Class<?>) AmapBroadcastService.class);
            intent.setAction(AmapBroadcastService.ACTION_TRIGGER_EXTSCREEN_ACTIVATION);
            this.context.startService(intent);
        } catch (Exception unused) {
        }
    }

    private void triggerAutoFavorite() {
        String string = SharedPreferencesUtils.getAppPrefs(this.context).getString("desktop_music_package", s5.h0.FRAGMENT_ENCODE_SET);
        if (string.isEmpty()) {
            return;
        }
        try {
            if ("com.tencent.qqmusiccar".equals(string)) {
                try {
                    Intent intent = new Intent("com.tencent.qqmusiccar.action");
                    intent.setClassName("com.tencent.qqmusiccar", "com.tencent.qqmusiccar.app.reciver.BroadcastReceiverCenterForThird");
                    intent.setData(Uri.parse("qqmusiccar://asdasd?action=20&m0=5"));
                    this.context.sendBroadcast(intent);
                } catch (Exception unused) {
                }
                this.mMainHandler.postDelayed(new r(this, 1), 500L);
                return;
            }
            if ("com.luna.music.car".equals(string)) {
                try {
                    Intent intent2 = new Intent("com.luna.music.car.PLAYER_CONTROL");
                    intent2.putExtra("controlType", 3);
                    this.context.sendBroadcast(intent2);
                } catch (Exception unused2) {
                }
                k4.a.c(this.context).f();
                return;
            }
            if (!cn.kuwo.autosdk.m.PREFERENCE_PACKAGE.equals(string)) {
                if ("com.netease.cloudmusic.iot".equals(string)) {
                    NeteaseMusicManager.getInstance(this.context).favoriteCurrentSong();
                }
            } else {
                s1.c cVar = s1.c.f8654e;
                if (cVar == null) {
                    throw new UnsupportedOperationException("请在Application里调用 KWAPI createKWAPI(Context context,String key)");
                }
                cVar.h(this.context);
            }
        } catch (Exception unused3) {
        }
    }

    private void triggerAutoSearch(String str) {
        String string = SharedPreferencesUtils.getAppPrefs(this.context).getString("desktop_music_package", s5.h0.FRAGMENT_ENCODE_SET);
        if (string.isEmpty()) {
            return;
        }
        if ("com.tencent.qqmusiccar".equals(string)) {
            triggerQqMusicCar(str);
            return;
        }
        if (cn.kuwo.autosdk.m.PREFERENCE_PACKAGE.equals(string)) {
            triggerKuwoMusicCar(str);
        } else if ("com.netease.cloudmusic.iot".equals(string)) {
            triggerNeteaseMusicCar(str);
        } else if ("com.luna.music.car".equals(string)) {
            triggerLunaMusicCar(str);
        }
    }

    private void triggerCycleLoopMode() {
        String string = SharedPreferencesUtils.getAppPrefs(this.context).getString("desktop_music_package", s5.h0.FRAGMENT_ENCODE_SET);
        if (string.isEmpty()) {
            return;
        }
        if ("com.tencent.qqmusiccar".equals(string)) {
            DesktopAssistantMusicUiHelper.dispatchLoopMode(this.context, 0, (QQMusicLyricReceiver.sPlayMode + 1) % 3);
        } else if ("com.luna.music.car".equals(string)) {
            int i7 = (sLunaLoopCycleIndex + 1) % 3;
            sLunaLoopCycleIndex = i7;
            DesktopAssistantMusicUiHelper.dispatchLoopMode(this.context, 5, i7);
        } else if (cn.kuwo.autosdk.m.PREFERENCE_PACKAGE.equals(string)) {
            sKuwoLoopCycleIndex = (sKuwoLoopCycleIndex + 1) % 4;
            DesktopAssistantMusicUiHelper.dispatchLoopMode(this.context, DesktopAssistantMusicUiHelper.resolveMusicSourceFromPackage(this.context, string), sKuwoLoopCycleIndex);
        }
    }

    private void triggerInstrumentCast() {
        String screenCastingSelectedPkg;
        ResolveInfo resolveInfo;
        try {
            if (SharedPreferencesUtils.isScreenCastingEnabled(this.context) && (screenCastingSelectedPkg = SharedPreferencesUtils.getScreenCastingSelectedPkg(this.context)) != null && !screenCastingSelectedPkg.trim().isEmpty()) {
                PackageManager packageManager = this.context.getPackageManager();
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        resolveInfo = null;
                        break;
                    } else {
                        resolveInfo = it.next();
                        if (resolveInfo.activityInfo.packageName.equals(screenCastingSelectedPkg)) {
                            break;
                        }
                    }
                }
                if (resolveInfo == null) {
                    return;
                }
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setClassName(screenCastingSelectedPkg, resolveInfo.activityInfo.name);
                intent2.addFlags(268435456);
                ActivityOptions makeBasic = ActivityOptions.makeBasic();
                int findSecondaryDisplayId = findSecondaryDisplayId();
                if (findSecondaryDisplayId != -1) {
                    if (SharedPreferencesUtils.isClusterEnlargeOnCasting(this.context)) {
                        sendClusterEnlargeCommand(this.context);
                        new Handler(this.context.getMainLooper()).postDelayed(new m(this, makeBasic, findSecondaryDisplayId, intent2, screenCastingSelectedPkg, 0), 2000L);
                    } else {
                        makeBasic.setLaunchDisplayId(findSecondaryDisplayId);
                        intent2.addFlags(738328576);
                        this.context.startActivity(intent2, makeBasic.toBundle());
                        SharedPreferencesUtils.setScreenCastingAppState(this.context, screenCastingSelectedPkg, "instrument");
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private void triggerKuwoMusicCar(String str) {
        int i7;
        if (str == null) {
            return;
        }
        int indexOf = str.toLowerCase().indexOf("srresult");
        if (indexOf >= 0) {
            int indexOf2 = str.indexOf(61, indexOf);
            str = (indexOf2 < 0 || (i7 = indexOf2 + 1) >= str.length()) ? s5.h0.FRAGMENT_ENCODE_SET : str.substring(i7).trim();
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            return;
        }
        int indexOf3 = trim.indexOf("搜索");
        int indexOf4 = trim.indexOf("播放");
        if (indexOf3 >= 0) {
            trim = trim.substring(indexOf3 + 2);
        } else if (indexOf4 >= 0) {
            trim = trim.substring(indexOf4 + 2);
        }
        String trim2 = trim.trim();
        if (trim2.isEmpty()) {
            return;
        }
        try {
            KuwoMusicManager.getInstance(this.context).searchAndPlay(trim2);
        } catch (Exception unused) {
        }
    }

    private void triggerLunaMusicCar(String str) {
        String extractMediaKeyword = extractMediaKeyword(str);
        if (extractMediaKeyword.isEmpty()) {
            return;
        }
        try {
            Intent intent = new Intent("com.luna.music.car.PLAYER_SEARCH");
            intent.putExtra("keyword", extractMediaKeyword);
            this.context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }

    private void triggerNeteaseMusicCar(String str) {
        int i7;
        if (str == null) {
            return;
        }
        int indexOf = str.toLowerCase().indexOf("srresult");
        if (indexOf >= 0) {
            int indexOf2 = str.indexOf(61, indexOf);
            str = (indexOf2 < 0 || (i7 = indexOf2 + 1) >= str.length()) ? s5.h0.FRAGMENT_ENCODE_SET : str.substring(i7).trim();
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            return;
        }
        int indexOf3 = trim.indexOf("搜索");
        int indexOf4 = trim.indexOf("播放");
        int i8 = 2;
        if (indexOf3 >= 0) {
            trim = trim.substring(indexOf3 + 2);
        } else if (indexOf4 >= 0) {
            trim = trim.substring(indexOf4 + 2);
        }
        String trim2 = trim.trim();
        if (trim2.isEmpty()) {
            return;
        }
        try {
            this.mMainHandler.post(new l0(i8, this, trim2));
        } catch (Exception unused) {
        }
    }

    private void triggerQqMusicCar(String str) {
        int i7;
        if (str == null) {
            return;
        }
        int indexOf = str.toLowerCase().indexOf("srresult");
        if (indexOf >= 0) {
            int indexOf2 = str.indexOf(61, indexOf);
            str = (indexOf2 < 0 || (i7 = indexOf2 + 1) >= str.length()) ? s5.h0.FRAGMENT_ENCODE_SET : str.substring(i7).trim();
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            return;
        }
        int indexOf3 = trim.indexOf("搜索");
        int indexOf4 = trim.indexOf("播放");
        if (indexOf3 >= 0) {
            trim = trim.substring(indexOf3 + 2);
        } else if (indexOf4 >= 0) {
            trim = trim.substring(indexOf4 + 2);
        }
        String trim2 = trim.trim();
        if (trim2.isEmpty()) {
            return;
        }
        try {
            String encodeToString = Base64.encodeToString(trim2.getBytes("UTF-8"), 2);
            Intent intent = new Intent();
            intent.setAction("com.tencent.qqmusiccar.action");
            intent.addFlags(268435456);
            intent.putExtra("action", 8);
            intent.putExtra("search_key", encodeToString);
            intent.putExtra("m1", true);
            this.context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    public void reload() {
        this.mReloadExecutor.execute(new r(this, 0));
    }

    public void start() {
        this.active = true;
        ensureEngineLoaded();
    }

    public void stop() {
        this.active = false;
        this.mMainHandler.removeCallbacks(this.engineRetryRunnable);
        this.engineRetryAttempt = 0;
        synchronized (this) {
            stopLogReader();
        }
    }
}
