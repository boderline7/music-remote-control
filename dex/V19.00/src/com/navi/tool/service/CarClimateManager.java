package com.navi.tool.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.lion.api.bus.sync.SyncBusEvent;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Map;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarClimateManager {
    private static final boolean ENABLE_AUTO_BRAKE_LINK = false;
    private static final boolean ENABLE_AUTO_FRAGRANCE = false;
    private static final boolean ENABLE_AUTO_SEAT_MEMORY = false;
    private static final boolean ENABLE_AUTO_STEERING_HEAT = false;
    private static final String TAG = "CarClimateManager";
    private Runnable autoFragranceCloseRunnable;
    private final Context context;
    private final SharedPreferences prefs;
    private boolean hasTriggeredSeatHeat = false;
    private boolean hasTriggeredSeatVent = false;
    private boolean hasTriggeredSteeringHeat = false;
    private boolean pendingSeatMemoryAfterPowerReady = false;
    private boolean pendingAutoBrakeLinkAfterEngineRun = false;
    private boolean pendingAutoFragranceAfterEngineRun = false;
    private String lastKnownEngineStatus = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastKnownPowerMode = s5.h0.FRAGMENT_ENCODE_SET;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final androidx.lifecycle.h0 powerModeObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarClimateManager.1
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (str == null) {
                return;
            }
            CarClimateManager.this.checkResetCondition();
            if (CarClimateManager.this.isSeatMemoryPowerReady(str)) {
                CarClimateManager carClimateManager = CarClimateManager.this;
                if (!carClimateManager.isSeatMemoryPowerReady(carClimateManager.lastKnownPowerMode)) {
                    CarClimateManager.this.pendingSeatMemoryAfterPowerReady = true;
                    CarClimateManager.this.checkSeatMemoryTrigger();
                }
            }
            if ("OFF".equals(str) && !"OFF".equals(CarClimateManager.this.lastKnownPowerMode)) {
                CarClimateManager.this.pendingSeatMemoryAfterPowerReady = false;
            }
            CarClimateManager.this.lastKnownPowerMode = str;
        }
    };
    private final androidx.lifecycle.h0 engineObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarClimateManager.2
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (str == null) {
                return;
            }
            CarClimateManager.this.checkResetCondition();
            if ("熄火".equals(str) && "运行".equals(CarClimateManager.this.lastKnownEngineStatus)) {
                CarClimateManager.this.pendingAutoBrakeLinkAfterEngineRun = false;
                CarClimateManager.this.pendingAutoFragranceAfterEngineRun = false;
                if (CarClimateManager.this.autoFragranceCloseRunnable != null) {
                    CarClimateManager.this.handler.removeCallbacks(CarClimateManager.this.autoFragranceCloseRunnable);
                    CarClimateManager.this.autoFragranceCloseRunnable = null;
                }
            }
            if ("运行".equals(str) && !str.equals(CarClimateManager.this.lastKnownEngineStatus)) {
                CarClimateManager.this.checkClimateTrigger(1);
                CarClimateManager.this.pendingAutoBrakeLinkAfterEngineRun = true;
                CarClimateManager.this.pendingAutoFragranceAfterEngineRun = true;
                CarClimateManager.this.checkAutoBrakeLinkTrigger();
                CarClimateManager.this.checkAutoFragranceTrigger();
            }
            CarClimateManager.this.lastKnownEngineStatus = str;
        }
    };
    private final androidx.lifecycle.h0 gearObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarClimateManager.3
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (str == null) {
                return;
            }
            CarClimateManager.this.checkSeatMemoryTrigger();
        }
    };
    private final androidx.lifecycle.h0 drivingInfoObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarClimateManager.4
        @Override // androidx.lifecycle.h0
        public void onChanged(Map<String, String> map) {
            CarClimateManager.this.checkAutoBrakeLinkTrigger();
        }
    };
    private final androidx.lifecycle.h0 speedObserver = new androidx.lifecycle.h0() { // from class: com.navi.tool.service.CarClimateManager.5
        @Override // androidx.lifecycle.h0
        public void onChanged(String str) {
            if (str == null || "--".equals(str)) {
                return;
            }
            try {
                if (Float.parseFloat(str) > CropImageView.DEFAULT_ASPECT_RATIO) {
                    CarClimateManager.this.checkClimateTrigger(0);
                }
            } catch (NumberFormatException unused) {
            }
        }
    };
    private final o4.c carInfoRepo = o4.c.c();

    public CarClimateManager(Context context) {
        this.context = context;
        this.prefs = SharedPreferencesUtils.getAppPrefs(context);
    }

    private void applyFragranceType(int i7) {
        new Thread(new h0.n(i7, 3, this)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAutoBrakeLinkTrigger() {
        Map map;
        String str;
        if (this.prefs.getBoolean("auto_brake_link_enabled", false) && this.prefs.getBoolean("car_link_service_enabled", false) && !this.prefs.getBoolean("lion_sync_service_enabled", false) && this.pendingAutoBrakeLinkAfterEngineRun && "运行".equals(this.carInfoRepo.f7949q.d()) && (map = (Map) this.carInfoRepo.A.d()) != null && (str = (String) map.get("brake_link")) != null && !"--".equals(str)) {
            if ("未关联".equals(str)) {
                sendBrakeLinkMode(1);
            }
            this.pendingAutoBrakeLinkAfterEngineRun = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAutoFragranceTrigger() {
        if (this.prefs.getBoolean("auto_fragrance_enabled", false) && this.prefs.getBoolean("car_link_service_enabled", false) && !this.prefs.getBoolean("lion_sync_service_enabled", false) && this.pendingAutoFragranceAfterEngineRun && "运行".equals(this.carInfoRepo.f7949q.d()) && CarInfoService.d() != null) {
            int i7 = 2;
            int i8 = this.prefs.getInt("auto_fragrance_level_mode", 2);
            if (i8 <= 0) {
                sendFragranceMode(1);
            } else {
                if (i8 > 5) {
                    i8 = 5;
                }
                sendFragranceLevel(i8);
            }
            int i9 = this.prefs.getInt("auto_fragrance_type", 1);
            if (i9 < 1 || i9 > 3) {
                i9 = 1;
            }
            applyFragranceType(i9);
            int i10 = this.prefs.getInt("auto_fragrance_close_minutes", 2);
            int i11 = i10 >= 1 ? i10 : 1;
            int i12 = i11 <= 5 ? i11 : 5;
            Runnable runnable = this.autoFragranceCloseRunnable;
            if (runnable != null) {
                this.handler.removeCallbacks(runnable);
            }
            a aVar = new a(this, i7);
            this.autoFragranceCloseRunnable = aVar;
            this.handler.postDelayed(aVar, i12 * t1.f.T_MS_MINUTE);
            this.pendingAutoFragranceAfterEngineRun = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkClimateTrigger(int i7) {
        String str = (String) this.carInfoRepo.f7957y.d();
        if (str == null || "--".equals(str)) {
            return;
        }
        try {
            float parseFloat = Float.parseFloat(str.replace("°C", s5.h0.FRAGMENT_ENCODE_SET));
            if (this.prefs.getBoolean("auto_seat_heat_enabled", false) && !this.hasTriggeredSeatHeat && this.prefs.getInt("seat_heat_trigger_condition", 0) == i7 && parseFloat < this.prefs.getFloat("seat_heat_threshold", 10.0f)) {
                executeSeatHeatActions();
                this.hasTriggeredSeatHeat = true;
            }
            if (!this.prefs.getBoolean("lion_sync_service_enabled", false) && this.prefs.getBoolean("auto_steering_heat_enabled", false) && !this.hasTriggeredSteeringHeat && i7 == 1) {
                SharedPreferences sharedPreferences = this.prefs;
                if (parseFloat < sharedPreferences.getFloat("steering_heat_threshold", sharedPreferences.getFloat("seat_heat_threshold", 10.0f))) {
                    executeSteeringHeatAction();
                    this.hasTriggeredSteeringHeat = true;
                }
            }
            if (!this.prefs.getBoolean("auto_seat_vent_enabled", false) || this.hasTriggeredSeatVent || this.prefs.getInt("seat_vent_trigger_condition", 0) != i7 || parseFloat <= this.prefs.getFloat("seat_vent_threshold", 20.0f)) {
                return;
            }
            executeSeatVentActions();
            this.hasTriggeredSeatVent = true;
        } catch (NumberFormatException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkResetCondition() {
        String str = (String) this.carInfoRepo.f7949q.d();
        String str2 = (String) this.carInfoRepo.f7950r.d();
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            if (!"熄火".equals(str)) {
                return;
            }
        } else if (!"熄火".equals(str) || !"OFF".equals(str2)) {
            return;
        }
        this.hasTriggeredSeatHeat = false;
        this.hasTriggeredSeatVent = false;
        this.hasTriggeredSteeringHeat = false;
        this.pendingAutoFragranceAfterEngineRun = false;
        Runnable runnable = this.autoFragranceCloseRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
            this.autoFragranceCloseRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSeatMemoryTrigger() {
    }

    private void disableDeprecatedAutoFeature(String str) {
        if (this.prefs.getBoolean(str, false)) {
            a.a.m(this.prefs, str, false);
        }
    }

    private void executeSeatHeatActions() {
        new Thread(new a(this, 3)).start();
    }

    private void executeSeatVentActions() {
        new Thread(new a(this, 0)).start();
    }

    private void executeSteeringHeatAction() {
        Map map = (Map) this.carInfoRepo.F.d();
        if ("开".equals(map == null ? null : (String) map.get("steering_heat"))) {
            return;
        }
        sendSteeringHeatCommand(1);
    }

    private boolean isSeatMemoryApplyAllowed() {
        int i7 = 1;
        if (readCarSettingState(52) == 1) {
            return true;
        }
        this.handler.post(new a(this, i7));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSeatMemoryPowerReady(String str) {
        return "ON".equals(str) || "START".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyFragranceType$4(int i7) {
        int i8 = readHvacValue(58) == i7 ? 1 : readHvacValue(59) == i7 ? 2 : readHvacValue(60) == i7 ? 3 : -1;
        if (i8 < 1) {
            return;
        }
        sendFragrancePosition(i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkAutoFragranceTrigger$0() {
        sendFragranceMode(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeSeatHeatActions$1() {
        if (this.prefs.getBoolean("seat_heat_compat_mode", false)) {
            if (this.prefs.getBoolean("seat_heat_target_driver", false)) {
                sendVrCommand("主驾座椅", "座椅加热");
            }
            if (this.prefs.getBoolean("seat_heat_target_passenger", false)) {
                sendVrCommand("副驾座椅", "座椅加热");
            }
            if (this.prefs.getBoolean("seat_heat_target_rl", false)) {
                sendVrCommand("后排左座椅", "座椅加热");
            }
            if (this.prefs.getBoolean("seat_heat_target_rr", false)) {
                sendVrCommand("后排右座椅", "座椅加热");
                return;
            }
            return;
        }
        if (this.prefs.getBoolean("seat_heat_target_driver", false)) {
            sendSeatCommand(95, this.prefs.getInt("seat_heat_level_driver", 3) + 1, true, true);
            sleepSafe();
        }
        if (this.prefs.getBoolean("seat_heat_target_passenger", false)) {
            sendSeatCommand(96, this.prefs.getInt("seat_heat_level_passenger", 3) + 1, false, true);
            sleepSafe();
        }
        if (this.prefs.getBoolean("seat_heat_target_rl", false)) {
            sendSeatCommand(z1.c.DOUBLE_TO_LONG, this.prefs.getInt("seat_heat_level_rl", 3) + 1, true, true);
            sleepSafe();
        }
        if (this.prefs.getBoolean("seat_heat_target_rr", false)) {
            sendSeatCommand(z1.c.INT_TO_BYTE, this.prefs.getInt("seat_heat_level_rr", 3) + 1, false, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeSeatVentActions$2() {
        if (this.prefs.getBoolean("seat_vent_compat_mode", false)) {
            if (this.prefs.getBoolean("seat_vent_target_driver", false)) {
                sendVrCommand("主驾座椅", "座椅通风");
            }
            if (this.prefs.getBoolean("seat_vent_target_passenger", false)) {
                sendVrCommand("副驾座椅", "座椅通风");
                return;
            }
            return;
        }
        if (this.prefs.getBoolean("seat_vent_target_driver", false)) {
            sendSeatCommand(93, this.prefs.getInt("seat_vent_level_driver", 3) + 1, true, false);
            sleepSafe();
        }
        if (this.prefs.getBoolean("seat_vent_target_passenger", false)) {
            sendSeatCommand(94, this.prefs.getInt("seat_vent_level_passenger", 3) + 1, false, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$isSeatMemoryApplyAllowed$3() {
        ToastHelper.show(this.context, "当前状态不允许应用座椅配置");
    }

    private int readCarSettingState(int i7) {
        Bundle bundle;
        int[] intArray;
        try {
            if (CarInfoService.d() == null) {
                return -1;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putInt("CMD_ID", i7);
            VDEvent vDEvent = new VDEvent(327681);
            vDEvent.f3190b = bundle2;
            VDEvent h7 = ((g2.a) CarInfoService.d()).h(vDEvent);
            if (h7 == null || (bundle = h7.f3190b) == null || (intArray = bundle.getIntArray("VALUE")) == null || intArray.length <= 0) {
                return -1;
            }
            return intArray[0];
        } catch (Exception unused) {
            return -1;
        }
    }

    private int readHvacValue(int i7) {
        Bundle bundle;
        int[] intArray;
        try {
            if (CarInfoService.d() == null) {
                return -1;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putInt("CMD_ID", i7);
            VDEvent vDEvent = new VDEvent(327690);
            vDEvent.f3190b = bundle2;
            VDEvent h7 = ((g2.a) CarInfoService.d()).h(vDEvent);
            if (h7 == null || (bundle = h7.f3190b) == null || (intArray = bundle.getIntArray("VALUE")) == null || intArray.length <= 0) {
                return -1;
            }
            return intArray[0];
        } catch (Exception unused) {
            return -1;
        }
    }

    private void sendBrakeLinkMode(int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", 23);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(327682);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception unused) {
        }
    }

    private void sendFragranceLevel(int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", 62);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(327690);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception unused) {
        }
    }

    private void sendFragranceMode(int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", 61);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(327690);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception unused) {
        }
    }

    private void sendFragrancePosition(int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", 48);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(327690);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception unused) {
        }
    }

    private void sendSeatCommand(int i7, int i8, boolean z6, boolean z7) {
        if (!this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("CMD_ID", i7);
                bundle.putIntArray("VALUE", new int[]{i8});
                VDEvent vDEvent = new VDEvent(327681);
                vDEvent.f3190b = bundle;
                ((g2.a) CarInfoService.d()).i(vDEvent);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        LionCarInfoService lionCarInfoService = LionCarInfoService.f4712m;
        if (lionCarInfoService == null || lionCarInfoService.f4713a == null) {
            return;
        }
        try {
            SyncBusEvent syncBusEvent = new SyncBusEvent(z7 ? LionCarInfoService.CABIN_SEAT_HEAT_STATE : LionCarInfoService.CABIN_SEAT_WIND_STATE);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("status", z6 ? 1 : 2);
            bundle2.putInt("data", i8);
            syncBusEvent.f4485c = bundle2;
            ((f4.a) lionCarInfoService.f4713a).j(syncBusEvent);
        } catch (Exception unused2) {
        }
    }

    private void sendSeatMemoryApply(int i7) {
        try {
            if (CarInfoService.d() != null && isSeatMemoryApplyAllowed()) {
                Bundle bundle = new Bundle();
                bundle.putInt("CMD_ID", 52);
                bundle.putIntArray("VALUE", new int[]{i7});
                VDEvent vDEvent = new VDEvent(327681);
                vDEvent.f3190b = bundle;
                ((g2.a) CarInfoService.d()).i(vDEvent);
            }
        } catch (Exception unused) {
        }
    }

    private void sendSteeringHeatCommand(int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", z1.c.AND_LONG);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(327681);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception unused) {
        }
    }

    private void sendVrCommand(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", String.format("{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"%s\",\"mode\":\"%s\"}}}", str, str2));
            this.context.startForegroundService(intent);
            Thread.sleep(200L);
        } catch (Exception unused) {
        }
    }

    private void sendWiperSensitivity(int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", z1.c.OR_LONG);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(327681);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception unused) {
        }
    }

    private void sleepSafe() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e7) {
            e7.printStackTrace();
        }
    }

    public void start() {
        disableDeprecatedAutoFeature("auto_steering_heat_enabled");
        disableDeprecatedAutoFeature("auto_seat_memory_enabled");
        disableDeprecatedAutoFeature("auto_brake_link_enabled");
        disableDeprecatedAutoFeature("auto_fragrance_enabled");
        this.carInfoRepo.f7949q.f(this.engineObserver);
        this.carInfoRepo.f7950r.f(this.powerModeObserver);
        this.carInfoRepo.f7938f.f(this.speedObserver);
        this.carInfoRepo.f7939g.f(this.gearObserver);
        this.carInfoRepo.A.f(this.drivingInfoObserver);
    }

    public void stop() {
        this.carInfoRepo.f7949q.g(this.engineObserver);
        this.carInfoRepo.f7950r.g(this.powerModeObserver);
        this.carInfoRepo.f7938f.g(this.speedObserver);
        this.carInfoRepo.f7939g.g(this.gearObserver);
        this.carInfoRepo.A.g(this.drivingInfoObserver);
    }
}
