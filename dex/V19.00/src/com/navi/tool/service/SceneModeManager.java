package com.navi.tool.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.model.SceneRule;
import com.navi.tool.scene.api.SceneDecision;
import com.navi.tool.scene.api.SceneEngine;
import com.navi.tool.scene.api.SceneRuleData;
import com.navi.tool.service.SceneModuleLoader;
import com.navi.tool.ui.extra.DesktopAssistantCarControlHelper;
import com.navi.tool.utils.SceneRuleStorage;
import com.navi.tool.utils.SharedPreferencesUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class SceneModeManager {
    private static final long ACTION_INTERVAL_MS = 500;
    private static final String TAG = "SceneModeManager";
    private static SceneModeManager sInstance;
    private final androidx.lifecycle.h0 childLockObserver;
    private final Context context;
    private final androidx.lifecycle.h0 doorObserver;
    private final androidx.lifecycle.h0 drivingObserver;
    private volatile SceneEngine engine;
    private final androidx.lifecycle.h0 engineObserver;
    private ScheduledFuture<?> engineRetryFuture;
    private final androidx.lifecycle.h0 gearObserver;
    private final androidx.lifecycle.h0 hvacObserver;
    private final androidx.lifecycle.h0 lightObserver;
    private final androidx.lifecycle.h0 outsideTempObserver;
    private final androidx.lifecycle.h0 powerObserver;
    private final androidx.lifecycle.h0 seatObserver;
    private final androidx.lifecycle.h0 speedObserver;
    private final androidx.lifecycle.h0 wiperObserver;
    private static final Set<String> DRIVING_FIELDS = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.navi.tool.service.SceneModeManager.1
        {
            add("driving_brake_link");
            add("drive_mode");
            add("brake_feel_mode");
            add("suspension_link");
            add("suspension_mode");
        }
    });
    private static final Set<String> DOOR_FIELDS = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.navi.tool.service.SceneModeManager.2
        {
            add("door_fl");
            add("door_fr");
            add("door_rl");
            add("door_rr");
        }
    });
    private static final Set<String> SEAT_FIELDS = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.navi.tool.service.SceneModeManager.3
        {
            add("seat_heat_fl");
            add("seat_heat_fr");
            add("seat_vent_fl");
            add("seat_vent_fr");
        }
    });
    private static final Set<String> HVAC_FIELDS = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.navi.tool.service.SceneModeManager.4
        {
            add("hvac_auto");
            add("hvac_ac");
            add("hvac_circ");
            add("fragrance_switch");
            add("steering_heat");
            add("wireless_charging_state");
        }
    });
    private static final Set<String> LIGHT_FIELDS = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.navi.tool.service.SceneModeManager.5
        {
            add("light_parking");
            add("light_high");
        }
    });
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final ConcurrentHashMap<String, SceneRule> ruleMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, ScheduledFuture<?>> pendingMap = new ConcurrentHashMap<>();
    private final Set<String> executingSet = ConcurrentHashMap.newKeySet();
    private int engineRetryAttempt = 0;
    private final Map<String, Set<String>> fieldRuleIndex = new HashMap();
    private volatile boolean started = false;
    private String lastEngineStatus = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastPowerMode = s5.h0.FRAGMENT_ENCODE_SET;
    private final Object evalLock = new Object();
    private final Set<String> pendingChangedFields = new HashSet();
    private final Set<String> pendingChangedRuleIds = new HashSet();
    private boolean pendingForceFullEvaluate = false;
    private ScheduledFuture<?> evaluateFuture = null;
    private boolean deviceRebootResetApplied = false;
    private boolean speedObserverRegistered = false;
    private boolean outsideTempObserverRegistered = false;
    private boolean gearObserverRegistered = false;
    private boolean engineObserverRegistered = false;
    private boolean powerObserverRegistered = false;
    private boolean childLockObserverRegistered = false;
    private boolean wiperObserverRegistered = false;
    private boolean drivingObserverRegistered = false;
    private boolean doorObserverRegistered = false;
    private boolean seatObserverRegistered = false;
    private boolean hvacObserverRegistered = false;
    private boolean lightObserverRegistered = false;
    private final o4.c repo = o4.c.c();

    public static class RuleReloadDiff {
        private final Set<String> impactedFields;

        private RuleReloadDiff() {
            this.impactedFields = new HashSet();
        }
    }

    public SceneModeManager(Context context) {
        final int i7 = 2;
        final int i8 = 0;
        this.speedObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i9 = i8;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i9) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i9 = 3;
        this.outsideTempObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i9;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i10 = 4;
        this.gearObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i10;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i11 = 5;
        this.childLockObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i11;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i12 = 6;
        this.wiperObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i12;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i13 = 7;
        this.drivingObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i13;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i14 = 8;
        this.doorObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i14;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i15 = 9;
        this.seatObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i15;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i16 = 10;
        this.hvacObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i16;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i17 = 11;
        this.lightObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i17;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        final int i18 = 1;
        this.engineObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i18;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        this.powerObserver = new androidx.lifecycle.h0(this) { // from class: com.navi.tool.service.u0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ SceneModeManager f4932b;

            {
                this.f4932b = this;
            }

            @Override // androidx.lifecycle.h0
            public final void onChanged(Object obj) {
                int i92 = i7;
                SceneModeManager sceneModeManager = this.f4932b;
                switch (i92) {
                    case 0:
                        sceneModeManager.lambda$new$0((String) obj);
                        break;
                    case 1:
                        sceneModeManager.lambda$new$10((String) obj);
                        break;
                    case 2:
                        sceneModeManager.lambda$new$11((String) obj);
                        break;
                    case 3:
                        sceneModeManager.lambda$new$1((String) obj);
                        break;
                    case 4:
                        sceneModeManager.lambda$new$2((String) obj);
                        break;
                    case 5:
                        sceneModeManager.lambda$new$3((String) obj);
                        break;
                    case 6:
                        sceneModeManager.lambda$new$4((String) obj);
                        break;
                    case 7:
                        sceneModeManager.lambda$new$5((Map) obj);
                        break;
                    case 8:
                        sceneModeManager.lambda$new$6((Map) obj);
                        break;
                    case 9:
                        sceneModeManager.lambda$new$7((Map) obj);
                        break;
                    case 10:
                        sceneModeManager.lambda$new$8((Map) obj);
                        break;
                    default:
                        sceneModeManager.lambda$new$9((Map) obj);
                        break;
                }
            }
        };
        this.context = context.getApplicationContext();
    }

    private boolean applyChildLockByToggle(boolean z6) {
        String safe = safe((String) this.repo.f7951s.d());
        if ("锁定".equals(safe) == z6) {
            return true;
        }
        if ("锁定".equals(safe) || "未锁".equals(safe)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.MUL_FLOAT, 1);
        }
        return false;
    }

    private void applyDeviceRebootResetOnce() {
        if (this.deviceRebootResetApplied) {
            return;
        }
        this.deviceRebootResetApplied = true;
        handleResetEvent("DEVICE_REBOOT");
    }

    private boolean applyFragranceMode(String str) {
        if (str == null) {
            return false;
        }
        if ("AUTO".equalsIgnoreCase(str)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 61, 1);
        }
        if ("关闭".equals(str) || "关".equals(str)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 61, 2);
        }
        int parseIntSafe = parseIntSafe(str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET), -1);
        if (parseIntSafe < 1 || parseIntSafe > 5) {
            return false;
        }
        return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 62, parseIntSafe);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
    
        if (r7 == r9) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0047 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean applyFragranceType(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "清新自然"
            boolean r0 = r0.equals(r9)
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            if (r0 == 0) goto Le
            r9 = r4
            goto L21
        Le:
            java.lang.String r0 = "治愈舒缓"
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L18
            r9 = r3
            goto L21
        L18:
            java.lang.String r0 = "运动激情"
            boolean r9 = r0.equals(r9)
            if (r9 == 0) goto L54
            r9 = r2
        L21:
            int r0 = r8.findFragrancePosByTypeFromCache(r9)
            if (r0 > 0) goto L44
            r5 = 58
            int r5 = r8.readHvacValueFromBus(r5)
            r6 = 59
            int r6 = r8.readHvacValueFromBus(r6)
            r7 = 60
            int r7 = r8.readHvacValueFromBus(r7)
            if (r5 != r9) goto L3d
            r2 = r4
            goto L45
        L3d:
            if (r6 != r9) goto L41
            r2 = r3
            goto L45
        L41:
            if (r7 != r9) goto L44
            goto L45
        L44:
            r2 = r0
        L45:
            if (r2 > 0) goto L48
            return r1
        L48:
            android.content.Context r9 = r8.context
            r0 = 327690(0x5000a, float:4.59191E-40)
            r1 = 48
            boolean r9 = com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.actionSendCommand(r9, r0, r1, r2)
            return r9
        L54:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.SceneModeManager.applyFragranceType(java.lang.String):boolean");
    }

    private boolean applyMainDriverSeatPosition(String str) {
        final int parseSeatPosition = parseSeatPosition(str);
        if (parseSeatPosition < 1 || parseSeatPosition > 11) {
            return false;
        }
        if (readCarSettingValueFromBus(50) != 1) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.USHR_INT, parseSeatPosition);
        }
        DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 50, 1);
        try {
            this.scheduler.schedule(new Callable() { // from class: com.navi.tool.service.s0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Boolean lambda$applyMainDriverSeatPosition$17;
                    lambda$applyMainDriverSeatPosition$17 = SceneModeManager.this.lambda$applyMainDriverSeatPosition$17(parseSeatPosition);
                    return lambda$applyMainDriverSeatPosition$17;
                }
            }, 100L, TimeUnit.MILLISECONDS);
            return true;
        } catch (RejectedExecutionException unused) {
            return false;
        }
    }

    private boolean applySteeringHeatByToggle(boolean z6) {
        String hvacField = getHvacField("steering_heat");
        if ("开".equals(hvacField) == z6) {
            return true;
        }
        if ("开".equals(hvacField) || "关".equals(hvacField)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.AND_LONG, 1);
        }
        return false;
    }

    private Map<String, String> buildFieldValues(List<SceneRule> list) {
        HashSet hashSet = new HashSet();
        Iterator<SceneRule> it = list.iterator();
        while (it.hasNext()) {
            Iterator<SceneRule.Condition> it2 = it.next().getConditions().iterator();
            while (it2.hasNext()) {
                hashSet.add(it2.next().getField());
            }
        }
        HashMap hashMap = new HashMap();
        Iterator it3 = hashSet.iterator();
        while (it3.hasNext()) {
            String str = (String) it3.next();
            hashMap.put(str, getFieldValue(str));
        }
        return hashMap;
    }

    private void cancelAllPendingSchedules() {
        for (ScheduledFuture<?> scheduledFuture : this.pendingMap.values()) {
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
        }
        this.pendingMap.clear();
    }

    private Set<String> collectConditionFields(SceneRule sceneRule) {
        HashSet hashSet = new HashSet();
        if (sceneRule != null && sceneRule.getConditions() != null) {
            Iterator<SceneRule.Condition> it = sceneRule.getConditions().iterator();
            while (it.hasNext()) {
                SceneRule.Condition next = it.next();
                String field = next == null ? null : next.getField();
                if (field != null && !field.isEmpty()) {
                    hashSet.add(field);
                }
            }
        }
        return hashSet;
    }

    private boolean dispatchAction(SceneRule.Action action) {
        String type = action.getType();
        String value = action.getValue();
        if ("brake_link".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSetBrakeLinkEnabled(this.context, "开启".equals(value));
        }
        if ("brake_feel_mode".equals(type)) {
            return "舒适".equals(value) ? DesktopAssistantCarControlHelper.actionSetBrakeFeelModeComfort(this.context) : DesktopAssistantCarControlHelper.actionSetBrakeFeelModeSport(this.context);
        }
        if ("drive_mode".equals(type)) {
            if ("经济".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetDriveModeEconomy(this.context);
            }
            if ("标准".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetDriveModeStandard(this.context);
            }
            if ("运动".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetDriveModeSport(this.context);
            }
            if ("雪地".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetDriveModeSnow(this.context);
            }
            if ("泥地".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetDriveModeMud(this.context);
            }
            if ("越野".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetDriveModeOffroad(this.context);
            }
            if ("沙地".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetDriveModeSand(this.context);
            }
            return false;
        }
        if ("suspension_link".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSetSuspensionLinkEnabled(this.context, "开启".equals(value));
        }
        if ("suspension_mode".equals(type)) {
            if ("舒适".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetSuspensionModeComfort(this.context);
            }
            if ("标准".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetSuspensionModeStandard(this.context);
            }
            if ("运动".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSetSuspensionModeSport(this.context);
            }
            return false;
        }
        if ("seat_heat_fl".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 95, parseLevel(value) + 1);
        }
        if ("seat_heat_fr".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 96, parseLevel(value) + 1);
        }
        if ("seat_heat_rl".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.DOUBLE_TO_LONG, parseLevel(value) + 1);
        }
        if ("seat_heat_rr".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.INT_TO_BYTE, parseLevel(value) + 1);
        }
        if ("seat_vent_fl".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 93, parseLevel(value) + 1);
        }
        if ("seat_vent_fr".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 94, parseLevel(value) + 1);
        }
        if ("child_lock".equals(type)) {
            return applyChildLockByToggle("锁定".equals(value));
        }
        if ("seat_memory_apply".equals(type)) {
            return DesktopAssistantCarControlHelper.actionApplySeatMemory(this.context, parseSeatMemoryPosition(value));
        }
        if ("drive_enjoy_mode".equals(type)) {
            return triggerDriveEnjoyMode();
        }
        if ("fragrance_mode".equals(type)) {
            return applyFragranceMode(value);
        }
        if ("fragrance_type".equals(type)) {
            return applyFragranceType(value);
        }
        if ("steering_heat".equals(type)) {
            return applySteeringHeatByToggle("开".equals(value));
        }
        if ("wireless_charging_switch".equals(type)) {
            if ("开启".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 45, 2);
            }
            if ("关闭".equals(value)) {
                return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 45, 1);
            }
            return false;
        }
        if ("pedestrian_alert".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327682, z1.c.SGET_SHORT, "开".equals(value) ? 1 : 0);
        }
        if ("rear_belt_reminder".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 235, !"开".equals(value) ? 1 : 0);
        }
        if ("wiper_sensitivity".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.OR_LONG, parseWiperLevel(value));
        }
        if ("light_height".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, 91, parseLightHeightCommandValue(value));
        }
        if ("all_windows".equals(type)) {
            return sendVrCarControlCommand("CLOSE", "所有窗户");
        }
        if ("sunshade".equals(type)) {
            return sendVrCarControlCommand("打开".equals(value) ? "OPEN" : "CLOSE", "遮阳帘");
        }
        if ("camera_360".equals(type)) {
            return sendVrCarControlCommand("打开".equals(value) ? "OPEN" : "CLOSE", "全景");
        }
        if ("hvac_ac".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 1, "开".equals(value) ? 1 : 0);
        }
        if ("hvac_auto".equals(type)) {
            return "开".equals(value) ? DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 4, 1) : DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 18, 2);
        }
        if ("hvac_fan".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 12, parseIntSafe(value, 0));
        }
        if ("hvac_rear_fan".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 68, parseIntSafe(value, 1));
        }
        if ("hvac_circ".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 8, "内循环".equals(value) ? 1 : 2);
        }
        if ("hvac_mode".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 6, parseMode(value));
        }
        if ("hvac_front_defrost".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 24, "开".equals(value) ? 1 : 0);
        }
        if ("hvac_rear_defrost".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 80, "开".equals(value) ? 1 : 0);
        }
        if ("hvac_temp".equals(type)) {
            return DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327690, 10, parseTemp(value));
        }
        if ("seat_main_forward".equals(type) || "seat_main_backward".equals(type)) {
            return applyMainDriverSeatPosition(value);
        }
        if ("close_ac_ui".equals(type)) {
            return scheduleCloseAcUi(value);
        }
        if ("open_ac_ui".equals(type)) {
            return scheduleOpenAcUi(value);
        }
        if ("close_systemui_ac_ui".equals(type)) {
            return scheduleCloseSystemUiAcUi(value);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drainAndEvaluate() {
        boolean z6;
        HashSet hashSet;
        HashSet hashSet2;
        synchronized (this.evalLock) {
            z6 = this.pendingForceFullEvaluate;
            this.pendingForceFullEvaluate = false;
            hashSet = new HashSet(this.pendingChangedFields);
            this.pendingChangedFields.clear();
            hashSet2 = new HashSet(this.pendingChangedRuleIds);
            this.pendingChangedRuleIds.clear();
            this.evaluateFuture = null;
        }
        evaluateRules(z6, hashSet, hashSet2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureEngineLoaded() {
        if (this.started) {
            if (this.engine == null) {
                SceneModuleLoader.getInstance().ensureEngine(this.context, new SceneModuleLoader.Callback() { // from class: com.navi.tool.service.SceneModeManager.6
                    @Override // com.navi.tool.service.SceneModuleLoader.Callback
                    public void onError(String str) {
                        SceneModeManager.this.scheduleEngineRetry();
                    }

                    @Override // com.navi.tool.service.SceneModuleLoader.Callback
                    public void onReady(SceneEngine sceneEngine) {
                        if (SceneModeManager.this.started) {
                            SceneModeManager.this.engine = sceneEngine;
                            SceneModeManager.this.engineRetryAttempt = 0;
                            if (SceneModeManager.this.engineRetryFuture != null) {
                                SceneModeManager.this.engineRetryFuture.cancel(false);
                                SceneModeManager.this.engineRetryFuture = null;
                            }
                            SceneModeManager.this.reloadRules();
                            SceneModeManager.this.requestEvaluateAll();
                        }
                    }
                });
            } else {
                reloadRules();
                requestEvaluateAll();
            }
        }
    }

    private void evaluateRules(boolean z6, Set<String> set, Set<String> set2) {
        SceneEngine sceneEngine;
        if (this.started) {
            if (!isSceneModeMasterEnabled()) {
                cancelAllPendingSchedules();
                return;
            }
            HashSet hashSet = new HashSet();
            if ((z6 || set == null || set.isEmpty()) && z6) {
                hashSet.addAll(this.ruleMap.keySet());
            }
            if (set2 != null && !set2.isEmpty()) {
                for (String str : set2) {
                    if (this.ruleMap.containsKey(str)) {
                        hashSet.add(str);
                    }
                }
            }
            if (set != null && !set.isEmpty()) {
                synchronized (this.fieldRuleIndex) {
                    try {
                        Iterator<String> it = set.iterator();
                        while (it.hasNext()) {
                            Set<String> set3 = this.fieldRuleIndex.get(it.next());
                            if (set3 != null) {
                                hashSet.addAll(set3);
                            }
                        }
                    } finally {
                    }
                }
            } else if (!z6 && (set2 == null || set2.isEmpty())) {
                hashSet.addAll(this.ruleMap.keySet());
            }
            if (hashSet.isEmpty() || (sceneEngine = this.engine) == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                SceneRule sceneRule = this.ruleMap.get((String) it2.next());
                if (sceneRule != null && sceneRule.isEnabled() && !sceneRule.getConditions().isEmpty() && !sceneRule.getActions().isEmpty()) {
                    arrayList.add(sceneRule);
                    arrayList2.add(toEngineRule(sceneRule));
                }
            }
            if (arrayList2.isEmpty()) {
                return;
            }
            for (SceneDecision sceneDecision : sceneEngine.evaluate(arrayList2, buildFieldValues(arrayList), hashSet)) {
                SceneRule sceneRule2 = this.ruleMap.get(sceneDecision.ruleId);
                if (sceneRule2 != null) {
                    String id = sceneRule2.getId();
                    if (!sceneRule2.isResetEnabled() && !sceneDecision.matched && !this.executingSet.contains(id)) {
                        ScheduledFuture<?> remove = this.pendingMap.remove(id);
                        if (remove != null) {
                            remove.cancel(true);
                            sceneRule2.getName();
                        }
                    } else if (sceneDecision.trigger && !this.executingSet.contains(id) && !this.pendingMap.containsKey(id)) {
                        int delaySeconds = sceneRule2.getDelaySeconds();
                        if (delaySeconds <= 0) {
                            executeRule(sceneRule2);
                        } else {
                            this.pendingMap.put(id, this.scheduler.schedule(new l0(10, this, id), delaySeconds, TimeUnit.SECONDS));
                            sceneRule2.getName();
                        }
                    }
                }
            }
        }
    }

    private void executeRule(SceneRule sceneRule) {
        if (this.started && isSceneModeMasterEnabled()) {
            if (this.engine != null) {
                this.engine.markExecuted(sceneRule.getId());
            }
            this.executingSet.add(sceneRule.getId());
            sceneRule.getName();
            sceneRule.getActions().size();
            executeRuleAction(sceneRule.getId(), 0);
        }
    }

    private void executeRuleAction(String str, int i7) {
        if (this.started && isSceneModeMasterEnabled()) {
            SceneRule sceneRule = this.ruleMap.get(str);
            if (sceneRule == null || !sceneRule.isEnabled()) {
                this.executingSet.remove(str);
                this.pendingMap.remove(str);
                return;
            }
            List<SceneRule.Action> actions = sceneRule.getActions();
            if (actions == null || i7 < 0 || i7 >= actions.size()) {
                this.executingSet.remove(str);
                this.pendingMap.remove(str);
                sceneRule.getName();
                return;
            }
            SceneRule.Action action = actions.get(i7);
            dispatchAction(action);
            sceneRule.getName();
            actions.size();
            action.getType();
            action.getValue();
            if (i7 >= actions.size() - 1) {
                this.executingSet.remove(str);
                this.pendingMap.remove(str);
                sceneRule.getName();
                return;
            }
            try {
                ScheduledFuture<?> schedule = this.scheduler.schedule(new h1.a(this, str, i7, 3), ACTION_INTERVAL_MS, TimeUnit.MILLISECONDS);
                ScheduledFuture<?> put = this.pendingMap.put(str, schedule);
                if (put != null && put != schedule && !put.isDone()) {
                    put.cancel(true);
                }
                sceneRule.getName();
            } catch (RejectedExecutionException unused) {
                this.executingSet.remove(str);
                this.pendingMap.remove(str);
                sceneRule.getName();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: executeRuleWithRecheck, reason: merged with bridge method [inline-methods] */
    public void lambda$evaluateRules$12(String str) {
        SceneRule sceneRule;
        SceneEngine sceneEngine;
        this.pendingMap.remove(str);
        if (isSceneModeMasterEnabled() && (sceneRule = this.ruleMap.get(str)) != null && sceneRule.isEnabled() && (sceneEngine = this.engine) != null) {
            if (sceneRule.isResetEnabled() && sceneEngine.isExecuted(str)) {
                return;
            }
            if (sceneEngine.matches(toEngineRule(sceneRule), buildFieldValues(Collections.singletonList(sceneRule)))) {
                executeRule(sceneRule);
            } else {
                sceneRule.getName();
            }
        }
    }

    private int findFragrancePosByTypeFromCache(int i7) {
        Map map;
        try {
            map = (Map) this.repo.F.d();
        } catch (Exception unused) {
        }
        if (map == null) {
            return -1;
        }
        int parseIntSafe = parseIntSafe((String) map.get("fragrance_pos1_type_raw"), -1);
        int parseIntSafe2 = parseIntSafe((String) map.get("fragrance_pos2_type_raw"), -1);
        int parseIntSafe3 = parseIntSafe((String) map.get("fragrance_pos3_type_raw"), -1);
        if (parseIntSafe == i7) {
            return 1;
        }
        if (parseIntSafe2 == i7) {
            return 2;
        }
        return parseIntSafe3 == i7 ? 3 : -1;
    }

    private String getDoorField(String str) {
        Boolean bool;
        Map map = (Map) this.repo.f7956x.d();
        return (map == null || (bool = (Boolean) map.get(str)) == null) ? s5.h0.FRAGMENT_ENCODE_SET : bool.booleanValue() ? "开" : "关";
    }

    private String getDrivingField(String str) {
        String str2;
        Map map = (Map) this.repo.A.d();
        return (map == null || (str2 = (String) map.get(str)) == null) ? s5.h0.FRAGMENT_ENCODE_SET : str2;
    }

    private String getFieldValue(String str) {
        if ("speed".equals(str)) {
            String str2 = (String) this.repo.f7938f.d();
            if (str2 == null) {
                return s5.h0.FRAGMENT_ENCODE_SET;
            }
            String replaceAll = str2.replaceAll("[^0-9.\\-]", s5.h0.FRAGMENT_ENCODE_SET);
            return replaceAll.isEmpty() ? s5.h0.FRAGMENT_ENCODE_SET : replaceAll;
        }
        if ("outside_temp".equals(str)) {
            String str3 = (String) this.repo.f7957y.d();
            if (str3 == null) {
                return s5.h0.FRAGMENT_ENCODE_SET;
            }
            String replaceAll2 = str3.replaceAll("[^0-9.\\-]", s5.h0.FRAGMENT_ENCODE_SET);
            return replaceAll2.isEmpty() ? s5.h0.FRAGMENT_ENCODE_SET : replaceAll2;
        }
        if ("gear".equals(str)) {
            return safe((String) this.repo.f7939g.d());
        }
        if ("engine_status".equals(str)) {
            return safe((String) this.repo.f7949q.d());
        }
        if ("power_mode".equals(str)) {
            return safe((String) this.repo.f7950r.d());
        }
        if ("door_fl".equals(str)) {
            return getDoorField("fl");
        }
        if ("door_fr".equals(str)) {
            return getDoorField("fr");
        }
        if ("door_rl".equals(str)) {
            return getDoorField("rl");
        }
        if ("door_rr".equals(str)) {
            return getDoorField("rr");
        }
        if ("child_lock".equals(str)) {
            return safe((String) this.repo.f7951s.d());
        }
        if ("wiper_sensitivity".equals(str)) {
            return safe((String) this.repo.D.d());
        }
        if ("light_parking".equals(str)) {
            return getLightField("parking");
        }
        if ("light_high".equals(str)) {
            return getLightField("high");
        }
        if ("driving_brake_link".equals(str)) {
            return getDrivingField("brake_link");
        }
        if ("drive_mode".equals(str)) {
            return getDrivingField("drive_mode");
        }
        if ("brake_feel_mode".equals(str)) {
            return getDrivingField("brake_feel_mode");
        }
        if ("suspension_link".equals(str)) {
            return getDrivingField("suspension_link");
        }
        if ("suspension_mode".equals(str)) {
            return getDrivingField("suspension_mode");
        }
        if ("seat_heat_fl".equals(str)) {
            return getSeatField("heat_fl");
        }
        if ("seat_heat_fr".equals(str)) {
            return getSeatField("heat_fr");
        }
        if ("seat_vent_fl".equals(str)) {
            return getSeatField("vent_fl");
        }
        if ("seat_vent_fr".equals(str)) {
            return getSeatField("vent_fr");
        }
        if ("hvac_auto".equals(str)) {
            return getHvacField("auto");
        }
        if ("hvac_ac".equals(str)) {
            return getHvacField("ac");
        }
        if (!"hvac_circ".equals(str)) {
            return "fragrance_switch".equals(str) ? getFragranceSwitchStatus() : "steering_heat".equals(str) ? getHvacField("steering_heat") : "wireless_charging_state".equals(str) ? getWirelessChargingStateText() : s5.h0.FRAGMENT_ENCODE_SET;
        }
        String hvacField = getHvacField("circ");
        return hvacField.isEmpty() ? s5.h0.FRAGMENT_ENCODE_SET : "外".equals(hvacField) ? "外循环" : "内循环";
    }

    private String getFragranceSwitchStatus() {
        int parseIntSafe = parseIntSafe(getHvacField("fragrance_mode_raw"), -1);
        return parseIntSafe == 1 ? "关" : (parseIntSafe == 0 || parseIntSafe == 2) ? "开" : s5.h0.FRAGMENT_ENCODE_SET;
    }

    private String getHvacField(String str) {
        String str2;
        Map map = (Map) this.repo.F.d();
        return (map == null || (str2 = (String) map.get(str)) == null) ? s5.h0.FRAGMENT_ENCODE_SET : str2;
    }

    private String getLightField(String str) {
        Boolean bool;
        Map map = (Map) this.repo.f7958z.d();
        return (map == null || (bool = (Boolean) map.get(str)) == null) ? s5.h0.FRAGMENT_ENCODE_SET : bool.booleanValue() ? "开" : "关";
    }

    private String getSeatField(String str) {
        String str2;
        Map map = (Map) this.repo.G.d();
        return (map == null || (str2 = (String) map.get(str)) == null) ? s5.h0.FRAGMENT_ENCODE_SET : str2;
    }

    private String getWirelessChargingStateText() {
        String str;
        Map map = (Map) this.repo.F.d();
        if (map == null || (str = (String) map.get("wireless_charging_state_raw")) == null) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt != 0 ? parseInt != 1 ? parseInt != 2 ? parseInt != 3 ? s5.h0.FRAGMENT_ENCODE_SET : "充电异常" : "已充满" : "充电中" : "未充电";
        } catch (NumberFormatException unused) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    private void handleResetEvent(String str) {
        HashSet hashSet = new HashSet();
        for (SceneRule sceneRule : this.ruleMap.values()) {
            if (sceneRule.isResetEnabled() && str.equals(sceneRule.getResetType())) {
                hashSet.add(sceneRule.getId());
                if (this.engine != null) {
                    this.engine.clearExecuted(sceneRule.getId());
                }
                ScheduledFuture<?> remove = this.pendingMap.remove(sceneRule.getId());
                if (remove != null) {
                    remove.cancel(true);
                }
                sceneRule.getName();
            }
        }
        if (hashSet.isEmpty()) {
            return;
        }
        requestEvaluateForRuleIds(hashSet);
    }

    private boolean hasAny(Set<String> set, Set<String> set2) {
        Iterator<String> it = set2.iterator();
        while (it.hasNext()) {
            if (set.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean isSameRuleContent(SceneRule sceneRule, SceneRule sceneRule2) {
        if (sceneRule == null || sceneRule2 == null) {
            return false;
        }
        return sceneRule.toJson().toString().equals(sceneRule2.toJson().toString());
    }

    private boolean isSceneModeMasterEnabled() {
        return SharedPreferencesUtils.getAppPrefs(this.context).getBoolean("scene_mode_master_enabled", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$applyMainDriverSeatPosition$17(int i7) {
        return Boolean.valueOf(DesktopAssistantCarControlHelper.actionSendCommand(this.context, 327681, z1.c.USHR_INT, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executeRuleAction$13(String str, int i7) {
        executeRuleAction(str, i7 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(String str) {
        requestEvaluateForField("speed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(String str) {
        requestEvaluateForField("outside_temp");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$10(String str) {
        if (str == null) {
            return;
        }
        if ("运行".equals(this.lastEngineStatus) && "熄火".equals(str)) {
            handleResetEvent("ENGINE_RUN_TO_OFF");
        }
        this.lastEngineStatus = str;
        requestEvaluateForField("engine_status");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$11(String str) {
        if (str == null) {
            return;
        }
        if ("运行".equals(this.lastPowerMode) && "OFF".equalsIgnoreCase(str)) {
            handleResetEvent("POWER_ON_TO_OFF");
        }
        this.lastPowerMode = str;
        requestEvaluateForField("power_mode");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(String str) {
        requestEvaluateForField("gear");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(String str) {
        requestEvaluateForField("child_lock");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$4(String str) {
        requestEvaluateForField("wiper_sensitivity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$5(Map map) {
        requestEvaluateForFields(DRIVING_FIELDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$6(Map map) {
        requestEvaluateForFields(DOOR_FIELDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$7(Map map) {
        requestEvaluateForFields(SEAT_FIELDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$8(Map map) {
        requestEvaluateForFields(HVAC_FIELDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$9(Map map) {
        requestEvaluateForFields(LIGHT_FIELDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Set lambda$rebuildFieldRuleIndex$18(String str) {
        return new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleCloseAcUi$14() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.svhvac", "com.desaysv.svhvac.service.HvacService"));
            intent.putExtra("type", "com.desaysv.svhvac.CLOSE_PANEL");
            this.context.startService(intent);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleCloseSystemUiAcUi$16() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.systemui", "com.desaysv.svhvac.service.HvacService"));
            intent.putExtra("type", "com.desaysv.svhvac.CLOSE_PANEL");
            this.context.startService(intent);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleOpenAcUi$15() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.svhvac", "com.desaysv.svhvac.service.HvacService"));
            intent.putExtra("type", "com.desaysv.svhvac.OPEN_PANEL");
            this.context.startService(intent);
        } catch (Exception unused) {
        }
    }

    public static void notifyRulesChanged() {
        SceneModeManager sceneModeManager = sInstance;
        if (sceneModeManager != null) {
            sceneModeManager.reloadRules();
            sceneModeManager.requestEvaluateAll();
        }
    }

    public static void notifyRulesChangedIncremental() {
        SceneModeManager sceneModeManager = sInstance;
        if (sceneModeManager != null) {
            RuleReloadDiff reloadRulesInternal = sceneModeManager.reloadRulesInternal();
            if (reloadRulesInternal.impactedFields.isEmpty()) {
                return;
            }
            sceneModeManager.requestEvaluateForFields(reloadRulesInternal.impactedFields);
        }
    }

    private int parseIntSafe(String str, int i7) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i7;
        }
    }

    private int parseLevel(String str) {
        if (str == null) {
            return 0;
        }
        String replaceAll = str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET);
        int parseIntSafe = replaceAll.isEmpty() ? "关".equals(str) ? 0 : 1 : parseIntSafe(replaceAll, 0);
        int i7 = parseIntSafe >= 0 ? parseIntSafe : 0;
        if (i7 > 3) {
            return 3;
        }
        return i7;
    }

    private int parseLightHeightCommandValue(String str) {
        if (str == null) {
            return 1;
        }
        int parseIntSafe = parseIntSafe(str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET), 0);
        int i7 = parseIntSafe >= 0 ? parseIntSafe : 0;
        if (i7 > 3) {
            i7 = 3;
        }
        return i7 + 1;
    }

    private int parseMode(String str) {
        if (str == null) {
            return 1;
        }
        if ("吹脸".equals(str)) {
            return 0;
        }
        if ("吹脸+吹脚".equals(str)) {
            return 1;
        }
        if ("吹脚".equals(str)) {
            return 2;
        }
        if ("除霜+吹脚".equals(str)) {
            return 3;
        }
        if ("除霜".equals(str)) {
            return 4;
        }
        if ("除霜+吹脸".equals(str)) {
            return 5;
        }
        if ("混合".equals(str)) {
            return 6;
        }
        int parseIntSafe = parseIntSafe(str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET), 1);
        int i7 = parseIntSafe >= 0 ? parseIntSafe : 0;
        if (i7 > 6) {
            return 6;
        }
        return i7;
    }

    private Double parseNumber(String str) {
        if (str != null && !str.trim().isEmpty()) {
            try {
                return Double.valueOf(Double.parseDouble(str.trim()));
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private int parseSeatMemoryPosition(String str) {
        if (str == null || "配置1".equals(str)) {
            return 1;
        }
        if ("配置2".equals(str)) {
            return 2;
        }
        return "配置3".equals(str) ? 3 : 1;
    }

    private int parseSeatPosition(String str) {
        int parseIntSafe;
        if (str != null && (parseIntSafe = parseIntSafe(str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET), -1)) >= 1 && parseIntSafe <= 11) {
            return parseIntSafe;
        }
        return -1;
    }

    private int parseTemp(String str) {
        int parseIntSafe = parseIntSafe(str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET), 22);
        if (parseIntSafe < 15) {
            parseIntSafe = 15;
        }
        if (parseIntSafe > 30) {
            parseIntSafe = 30;
        }
        return parseIntSafe - 15;
    }

    private int parseWiperLevel(String str) {
        if (str == null) {
            return 1;
        }
        int parseIntSafe = parseIntSafe(str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET), 1);
        int i7 = parseIntSafe >= 1 ? parseIntSafe : 1;
        if (i7 > 4) {
            return 4;
        }
        return i7;
    }

    private int readCarSettingValueFromBus(int i7) {
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

    private int readHvacValueFromBus(int i7) {
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

    private void rebuildFieldRuleIndex() {
        synchronized (this.fieldRuleIndex) {
            try {
                this.fieldRuleIndex.clear();
                for (SceneRule sceneRule : this.ruleMap.values()) {
                    if (sceneRule.isEnabled() && sceneRule.getConditions() != null) {
                        Iterator<SceneRule.Condition> it = sceneRule.getConditions().iterator();
                        while (it.hasNext()) {
                            SceneRule.Condition next = it.next();
                            String field = next == null ? null : next.getField();
                            if (field != null && !field.isEmpty()) {
                                this.fieldRuleIndex.computeIfAbsent(field, new h2.a(2)).add(sceneRule.getId());
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private synchronized RuleReloadDiff reloadRulesInternal() {
        RuleReloadDiff ruleReloadDiff;
        ScheduledFuture<?> remove;
        try {
            ruleReloadDiff = new RuleReloadDiff();
            HashMap hashMap = new HashMap(this.ruleMap);
            List<SceneRule> loadRules = SceneRuleStorage.loadRules(this.context);
            this.ruleMap.clear();
            for (SceneRule sceneRule : loadRules) {
                this.ruleMap.put(sceneRule.getId(), sceneRule);
            }
            rebuildFieldRuleIndex();
            updateObserverBindings();
            if (this.engine != null) {
                this.engine.pruneState(this.ruleMap.keySet());
            }
            Iterator it = new ArrayList(this.pendingMap.keySet()).iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!this.ruleMap.containsKey(str) && (remove = this.pendingMap.remove(str)) != null) {
                    remove.cancel(true);
                }
            }
            for (Map.Entry<String, SceneRule> entry : this.ruleMap.entrySet()) {
                String key = entry.getKey();
                SceneRule sceneRule2 = (SceneRule) hashMap.get(key);
                SceneRule value = entry.getValue();
                if (sceneRule2 == null) {
                    ruleReloadDiff.impactedFields.addAll(collectConditionFields(value));
                } else if (!isSameRuleContent(sceneRule2, value)) {
                    ruleReloadDiff.impactedFields.addAll(collectConditionFields(sceneRule2));
                    ruleReloadDiff.impactedFields.addAll(collectConditionFields(value));
                    if (this.engine != null) {
                        this.engine.clearExecuted(key);
                        value.getName();
                    }
                    ScheduledFuture<?> remove2 = this.pendingMap.remove(key);
                    if (remove2 != null) {
                        remove2.cancel(true);
                        value.getName();
                    }
                }
            }
            for (Map.Entry entry2 : hashMap.entrySet()) {
                if (!this.ruleMap.containsKey(entry2.getKey())) {
                    ruleReloadDiff.impactedFields.addAll(collectConditionFields((SceneRule) entry2.getValue()));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return ruleReloadDiff;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestEvaluateAll() {
        if (this.started) {
            synchronized (this.evalLock) {
                this.pendingForceFullEvaluate = true;
                scheduleEvaluateLocked();
            }
        }
    }

    private void requestEvaluateForField(String str) {
        if (this.started) {
            synchronized (this.evalLock) {
                if (str != null) {
                    try {
                        if (!str.isEmpty()) {
                            this.pendingChangedFields.add(str);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                scheduleEvaluateLocked();
            }
        }
    }

    private void requestEvaluateForFields(Set<String> set) {
        if (this.started) {
            synchronized (this.evalLock) {
                if (set != null) {
                    try {
                        this.pendingChangedFields.addAll(set);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                scheduleEvaluateLocked();
            }
        }
    }

    private void requestEvaluateForRuleIds(Set<String> set) {
        if (this.started) {
            synchronized (this.evalLock) {
                if (set != null) {
                    try {
                        this.pendingChangedRuleIds.addAll(set);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                scheduleEvaluateLocked();
            }
        }
    }

    private String safe(String str) {
        return str == null ? s5.h0.FRAGMENT_ENCODE_SET : str;
    }

    private boolean scheduleCloseAcUi(String str) {
        int i7;
        try {
            i7 = Math.max(1, Math.min(10, Integer.parseInt(str)));
        } catch (NumberFormatException unused) {
            i7 = 2;
        }
        try {
            this.scheduler.schedule(new t0(this, 4), i7, TimeUnit.SECONDS);
            return true;
        } catch (RejectedExecutionException unused2) {
            return false;
        }
    }

    private boolean scheduleCloseSystemUiAcUi(String str) {
        int i7;
        try {
            i7 = Math.max(1, Math.min(10, Integer.parseInt(str)));
        } catch (NumberFormatException unused) {
            i7 = 2;
        }
        try {
            this.scheduler.schedule(new t0(this, 3), i7, TimeUnit.SECONDS);
            return true;
        } catch (RejectedExecutionException unused2) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleEngineRetry() {
        if (this.started) {
            int i7 = this.engineRetryAttempt + 1;
            this.engineRetryAttempt = i7;
            long min = Math.min(i7 * 30, 300L);
            ScheduledFuture<?> scheduledFuture = this.engineRetryFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            try {
                this.engineRetryFuture = this.scheduler.schedule(new t0(this, 2), min, TimeUnit.SECONDS);
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    private void scheduleEvaluateLocked() {
        ScheduledFuture<?> scheduledFuture = this.evaluateFuture;
        if (scheduledFuture == null || scheduledFuture.isDone() || this.evaluateFuture.isCancelled()) {
            this.evaluateFuture = this.scheduler.schedule(new t0(this, 0), 120L, TimeUnit.MILLISECONDS);
        }
    }

    private boolean scheduleOpenAcUi(String str) {
        int i7;
        int i8 = 1;
        try {
            i7 = Math.max(1, Math.min(10, Integer.parseInt(str)));
        } catch (NumberFormatException unused) {
            i7 = 2;
        }
        try {
            this.scheduler.schedule(new t0(this, i8), i7, TimeUnit.SECONDS);
            return true;
        } catch (RejectedExecutionException unused2) {
            return false;
        }
    }

    private boolean sendVrCarControlCommand(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", String.format("{\"normal_text\":\"\",\"operation\":\"%s\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"%s\"}}}", str, str2));
            this.context.startService(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private SceneRuleData toEngineRule(SceneRule sceneRule) {
        ArrayList arrayList = new ArrayList();
        for (SceneRule.Condition condition : sceneRule.getConditions()) {
            arrayList.add(new SceneRuleData.Condition(condition.getField(), condition.getOperator(), condition.getValue()));
        }
        return new SceneRuleData(sceneRule.getId(), sceneRule.getLogic(), sceneRule.isResetEnabled(), sceneRule.getResetType(), arrayList);
    }

    private void toggleChildLockObserver(boolean z6) {
        if (z6 == this.childLockObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7951s.f(this.childLockObserver);
        } else {
            this.repo.f7951s.g(this.childLockObserver);
        }
        this.childLockObserverRegistered = z6;
    }

    private void toggleDoorObserver(boolean z6) {
        if (z6 == this.doorObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7956x.f(this.doorObserver);
        } else {
            this.repo.f7956x.g(this.doorObserver);
        }
        this.doorObserverRegistered = z6;
    }

    private void toggleDrivingObserver(boolean z6) {
        if (z6 == this.drivingObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.A.f(this.drivingObserver);
        } else {
            this.repo.A.g(this.drivingObserver);
        }
        this.drivingObserverRegistered = z6;
    }

    private void toggleEngineObserver(boolean z6) {
        if (z6 == this.engineObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7949q.f(this.engineObserver);
        } else {
            this.repo.f7949q.g(this.engineObserver);
        }
        this.engineObserverRegistered = z6;
    }

    private void toggleGearObserver(boolean z6) {
        if (z6 == this.gearObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7939g.f(this.gearObserver);
        } else {
            this.repo.f7939g.g(this.gearObserver);
        }
        this.gearObserverRegistered = z6;
    }

    private void toggleHvacObserver(boolean z6) {
        if (z6 == this.hvacObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.F.f(this.hvacObserver);
        } else {
            this.repo.F.g(this.hvacObserver);
        }
        this.hvacObserverRegistered = z6;
    }

    private void toggleLightObserver(boolean z6) {
        if (z6 == this.lightObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7958z.f(this.lightObserver);
        } else {
            this.repo.f7958z.g(this.lightObserver);
        }
        this.lightObserverRegistered = z6;
    }

    private void toggleOutsideTempObserver(boolean z6) {
        if (z6 == this.outsideTempObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7957y.f(this.outsideTempObserver);
        } else {
            this.repo.f7957y.g(this.outsideTempObserver);
        }
        this.outsideTempObserverRegistered = z6;
    }

    private void togglePowerObserver(boolean z6) {
        if (z6 == this.powerObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7950r.f(this.powerObserver);
        } else {
            this.repo.f7950r.g(this.powerObserver);
        }
        this.powerObserverRegistered = z6;
    }

    private void toggleSeatObserver(boolean z6) {
        if (z6 == this.seatObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.G.f(this.seatObserver);
        } else {
            this.repo.G.g(this.seatObserver);
        }
        this.seatObserverRegistered = z6;
    }

    private void toggleSpeedObserver(boolean z6) {
        if (z6 == this.speedObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.f7938f.f(this.speedObserver);
        } else {
            this.repo.f7938f.g(this.speedObserver);
        }
        this.speedObserverRegistered = z6;
    }

    private void toggleWiperObserver(boolean z6) {
        if (z6 == this.wiperObserverRegistered) {
            return;
        }
        if (z6) {
            this.repo.D.f(this.wiperObserver);
        } else {
            this.repo.D.g(this.wiperObserver);
        }
        this.wiperObserverRegistered = z6;
    }

    private boolean triggerDriveEnjoyMode() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", "{\"normal_text\":\"\",\"operation\":\"OPEN\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"头枕扬声器\",\"nameValue\":\"驾享\"}}}");
            this.context.startForegroundService(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void unbindAllObservers() {
        toggleSpeedObserver(false);
        toggleOutsideTempObserver(false);
        toggleGearObserver(false);
        toggleEngineObserver(false);
        togglePowerObserver(false);
        toggleChildLockObserver(false);
        toggleWiperObserver(false);
        toggleDrivingObserver(false);
        toggleDoorObserver(false);
        toggleSeatObserver(false);
        toggleHvacObserver(false);
        toggleLightObserver(false);
    }

    private void updateObserverBindings() {
        HashSet hashSet;
        synchronized (this.fieldRuleIndex) {
            hashSet = new HashSet(this.fieldRuleIndex.keySet());
        }
        toggleSpeedObserver(hashSet.contains("speed"));
        toggleOutsideTempObserver(hashSet.contains("outside_temp"));
        toggleGearObserver(hashSet.contains("gear"));
        toggleEngineObserver(hashSet.contains("engine_status"));
        togglePowerObserver(hashSet.contains("power_mode"));
        toggleChildLockObserver(hashSet.contains("child_lock"));
        toggleWiperObserver(hashSet.contains("wiper_sensitivity"));
        toggleDrivingObserver(hasAny(hashSet, DRIVING_FIELDS));
        toggleDoorObserver(hasAny(hashSet, DOOR_FIELDS));
        toggleSeatObserver(hasAny(hashSet, SEAT_FIELDS));
        toggleHvacObserver(hasAny(hashSet, HVAC_FIELDS));
        toggleLightObserver(hasAny(hashSet, LIGHT_FIELDS));
    }

    public synchronized void reloadRules() {
        reloadRulesInternal();
    }

    public void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        sInstance = this;
        reloadRules();
        applyDeviceRebootResetOnce();
        ensureEngineLoaded();
    }

    public void stop() {
        if (this.started) {
            this.started = false;
            unbindAllObservers();
            for (ScheduledFuture<?> scheduledFuture : this.pendingMap.values()) {
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
            }
            this.pendingMap.clear();
            synchronized (this.evalLock) {
                try {
                    this.pendingChangedFields.clear();
                    this.pendingChangedRuleIds.clear();
                    this.pendingForceFullEvaluate = false;
                    ScheduledFuture<?> scheduledFuture2 = this.evaluateFuture;
                    if (scheduledFuture2 != null) {
                        scheduledFuture2.cancel(true);
                        this.evaluateFuture = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (sInstance == this) {
                sInstance = null;
            }
            this.executingSet.clear();
            ScheduledFuture<?> scheduledFuture3 = this.engineRetryFuture;
            if (scheduledFuture3 != null) {
                scheduledFuture3.cancel(false);
                this.engineRetryFuture = null;
            }
            this.engineRetryAttempt = 0;
            if (this.engine != null) {
                this.engine.clearAllState();
                this.engine = null;
            }
            this.deviceRebootResetApplied = false;
        }
    }
}
