package com.navi.tool.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import com.navi.tool.ui.extra.DesktopAssistantCarControlHelper;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarSoundManager {
    private static final boolean ENABLE_AUTO_CHILD_LOCK = false;
    private static final boolean ENABLE_AUTO_DRIVING_MODE = false;
    private static final String TAG = "CarSoundManager";
    private final Context context;
    private Map<String, Boolean> lastKnownDoorStatus;
    private Map<String, String> lastKnownWindowStatus;
    private final SharedPreferences prefs;
    private String lastKnownGear = null;
    private String lastKnownEngineStatus = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastKnownChildLockStatus = s5.h0.FRAGMENT_ENCODE_SET;
    private boolean hasAutoLockedChildLock = false;
    private String lastKnownPowerMode = s5.h0.FRAGMENT_ENCODE_SET;
    private boolean hasSeenPowerRunning = false;
    private final androidx.lifecycle.h0 powerModeObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarSoundManager.1
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (str == null) {
                return;
            }
            String str2 = CarSoundManager.this.lastKnownPowerMode;
            CarSoundManager.this.lastKnownPowerMode = str;
            if ("运行".equals(str)) {
                CarSoundManager.this.hasSeenPowerRunning = true;
            }
            CarSoundManager.this.checkResetCondition();
            if (("运行".equals(str2) || CarSoundManager.this.hasSeenPowerRunning) && "OFF".equals(str)) {
                CarSoundManager.this.handleAutoChildUnlockByPowerOff();
                CarSoundManager.this.hasSeenPowerRunning = false;
            }
        }
    };
    private final androidx.lifecycle.h0 childLockObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarSoundManager.2
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (str == null || str.equals(CarSoundManager.this.lastKnownChildLockStatus)) {
                return;
            }
            if ("锁定".equals(CarSoundManager.this.lastKnownChildLockStatus) && "未锁".equals(str)) {
                CarSoundManager.this.hasAutoLockedChildLock = false;
            }
            CarSoundManager.this.lastKnownChildLockStatus = str;
            CarSoundManager.this.checkAutoChildLock();
        }
    };
    private final androidx.lifecycle.h0 speedObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarSoundManager.3
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            CarSoundManager.this.checkAutoChildLock();
        }
    };
    private boolean hasTriggeredDrivingModeThisBoot = false;
    private final androidx.lifecycle.h0 engineObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarSoundManager.4
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (str == null) {
                return;
            }
            CarSoundManager.this.checkResetCondition();
            if ("运行".equals(str) && !str.equals(CarSoundManager.this.lastKnownEngineStatus) && CarSoundManager.this.prefs.getBoolean("auto_driving_mode_enabled", false)) {
                if (!CarSoundManager.this.prefs.getBoolean("auto_driving_mode_boot_only", false)) {
                    CarSoundManager.this.triggerDrivingMode();
                } else if (!CarSoundManager.this.hasTriggeredDrivingModeThisBoot) {
                    CarSoundManager.this.triggerDrivingMode();
                    CarSoundManager.this.hasTriggeredDrivingModeThisBoot = true;
                }
            }
            CarSoundManager.this.lastKnownEngineStatus = str;
        }
    };
    private final androidx.lifecycle.h0 gearObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarSoundManager.5
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (CarSoundManager.this.lastKnownGear == null) {
                CarSoundManager.this.lastKnownGear = str;
                return;
            }
            if (str == null || str.equals(CarSoundManager.this.lastKnownGear)) {
                return;
            }
            String unused = CarSoundManager.this.lastKnownGear;
            CarSoundManager carSoundManager = CarSoundManager.this;
            carSoundManager.handleGearChange(carSoundManager.lastKnownGear, str);
            CarSoundManager.this.lastKnownGear = str;
        }
    };
    private final androidx.lifecycle.h0 doorObserver = new AnonymousClass6();
    private final androidx.lifecycle.h0 windowObserver = new AnonymousClass7();
    private final o4.c carInfoRepo = o4.c.c();

    /* renamed from: com.navi.tool.service.CarSoundManager$6, reason: invalid class name */
    public class AnonymousClass6 implements androidx.lifecycle.h0 {
        public AnonymousClass6() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onChanged$0(String str, Boolean bool, Boolean bool2) {
            CarSoundManager.this.handleDoorChange(str, bool2.booleanValue());
        }

        @Override // androidx.lifecycle.h0
        public void onChanged(Map<String, Boolean> map) {
            CarSoundManager carSoundManager = CarSoundManager.this;
            carSoundManager.handleStatusChange(map, carSoundManager.lastKnownDoorStatus, new e(0, this));
            CarSoundManager.this.lastKnownDoorStatus = new HashMap(map);
        }
    }

    /* renamed from: com.navi.tool.service.CarSoundManager$7, reason: invalid class name */
    public class AnonymousClass7 implements androidx.lifecycle.h0 {
        public AnonymousClass7() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onChanged$0(String str, String str2, String str3) {
            CarSoundManager.this.handleWindowChange(str, str2, str3);
        }

        @Override // androidx.lifecycle.h0
        public void onChanged(Map<String, String> map) {
            CarSoundManager carSoundManager = CarSoundManager.this;
            carSoundManager.handleStatusChange(map, carSoundManager.lastKnownWindowStatus, new e(1, this));
            CarSoundManager.this.lastKnownWindowStatus = new HashMap(map);
        }
    }

    @FunctionalInterface
    public interface TriConsumer<A, B, C> {
        void accept(A a7, B b7, C c7);
    }

    public CarSoundManager(Context context) {
        this.context = context;
        this.prefs = SharedPreferencesUtils.getAppPrefs(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAutoChildLock() {
        if (this.prefs.getBoolean("child_lock_reminder_enabled", false) && !this.hasAutoLockedChildLock && this.prefs.getInt("child_lock_lock_mode", 0) == 0) {
            String str = (String) this.carInfoRepo.f7951s.d();
            String str2 = (String) this.carInfoRepo.f7938f.d();
            if (str == null || !"未锁".equals(str) || str2 == null || "--".equals(str2)) {
                return;
            }
            try {
                if (Float.parseFloat(str2) > CropImageView.DEFAULT_ASPECT_RATIO) {
                    toggleChildLock();
                    this.hasAutoLockedChildLock = true;
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkResetCondition() {
        String str = (String) this.carInfoRepo.f7949q.d();
        String str2 = (String) this.carInfoRepo.f7950r.d();
        if ("熄火".equals(str) && "OFF".equals(str2)) {
            this.hasAutoLockedChildLock = false;
            this.hasSeenPowerRunning = false;
        }
    }

    private void disableDeprecatedAutoFeature(String str) {
        if (this.prefs.getBoolean(str, false)) {
            a.a.m(this.prefs, str, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAutoChildUnlockByPowerOff() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x009b, code lost:
    
        if (r6.equals("fl") == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleDoorChange(java.lang.String r6, boolean r7) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.CarSoundManager.handleDoorChange(java.lang.String, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleGearChange(String str, String str2) {
        String str3;
        String str4;
        if (this.prefs.getBoolean("child_lock_reminder_enabled", false) && this.prefs.getBoolean("auto_child_unlock_by_p", false) && "锁定".equals((String) this.carInfoRepo.f7951s.d()) && "P".equals(str2) && ("D1".equals(str) || "N".equals(str) || "R".equals(str))) {
            toggleChildLock();
        }
        if (this.prefs.getBoolean("sound_gear_master_enabled", false)) {
            if ("P".equals(str2)) {
                if (str != null) {
                    if (str.startsWith("D") || "R".equals(str) || "N".equals(str)) {
                        playSound("sound_gear_p_enabled", "P.mp3");
                        return;
                    }
                    return;
                }
                return;
            }
            List asList = Arrays.asList("P", "R", "N", "D1");
            if (asList.contains(str) && asList.contains(str2)) {
                str2.getClass();
                str2.hashCode();
                switch (str2) {
                    case "N":
                        str3 = "n";
                        str4 = "N.mp3";
                        break;
                    case "R":
                        str3 = "r";
                        str4 = "R.mp3";
                        break;
                    case "D1":
                        str3 = "d1";
                        str4 = "D1.mp3";
                        break;
                    default:
                        return;
                }
                playSound(a.a.h("sound_gear_", str3, "_enabled"), str4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void handleStatusChange(Map<String, T> map, Map<String, T> map2, TriConsumer<String, T, T> triConsumer) {
        if (map2 == null) {
            return;
        }
        for (Map.Entry<String, T> entry : map.entrySet()) {
            String key = entry.getKey();
            T value = entry.getValue();
            T t6 = map2.get(key);
            if (t6 != null && !t6.equals(value) && !"--".equals(value) && !"未知".equals(value)) {
                t6.toString();
                Objects.toString(value);
                triConsumer.accept(key, t6, value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0078, code lost:
    
        if ("微开".equals(r22) == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0098, code lost:
    
        if (r22.startsWith("未知") == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
    
        if (r22.startsWith("未知") == false) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleWindowChange(java.lang.String r20, java.lang.String r21, java.lang.String r22) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.CarSoundManager.handleWindowChange(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private void playSound(String str, String str2) {
        if (this.prefs.getBoolean(str, false)) {
            playSoundDirect(str2);
        }
    }

    private void playSoundDirect(String str) {
        File file = new File(new File(Environment.getExternalStorageDirectory(), "navitool/mp3"), str);
        file.getAbsolutePath();
        LogLinkageManager.playMediaFile(this.context, file.getAbsolutePath());
    }

    private void toggleChildLock() {
        try {
            DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.MUL_FLOAT, 1);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerDrivingMode() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"头枕扬声器\",\"nameValue\":\"驾享\"}}}");
            this.context.startForegroundService(intent);
        } catch (Exception unused) {
        }
    }

    public void start() {
        disableDeprecatedAutoFeature("auto_driving_mode_enabled");
        disableDeprecatedAutoFeature("child_lock_reminder_enabled");
        disableDeprecatedAutoFeature("auto_child_unlock_driver_door");
        disableDeprecatedAutoFeature("auto_child_unlock_passenger_door");
        disableDeprecatedAutoFeature("auto_child_unlock_by_p");
        disableDeprecatedAutoFeature("auto_child_unlock_power_off");
        this.carInfoRepo.f7939g.f(this.gearObserver);
        this.carInfoRepo.f7956x.f(this.doorObserver);
        this.carInfoRepo.C.f(this.windowObserver);
        this.carInfoRepo.f7949q.f(this.engineObserver);
        this.carInfoRepo.f7950r.f(this.powerModeObserver);
        this.carInfoRepo.f7938f.f(this.speedObserver);
        this.carInfoRepo.f7951s.f(this.childLockObserver);
    }

    public void stop() {
        this.carInfoRepo.f7939g.g(this.gearObserver);
        this.carInfoRepo.f7956x.g(this.doorObserver);
        this.carInfoRepo.C.g(this.windowObserver);
        this.carInfoRepo.f7949q.g(this.engineObserver);
        this.carInfoRepo.f7950r.g(this.powerModeObserver);
        this.carInfoRepo.f7938f.g(this.speedObserver);
        this.carInfoRepo.f7951s.g(this.childLockObserver);
    }
}
