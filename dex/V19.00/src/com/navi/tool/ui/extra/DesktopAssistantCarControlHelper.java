package com.navi.tool.ui.extra;

import android.R;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.desaysv.ivi.vdb.event.VDEvent;
import com.kugou.auto.proxy.KgMusic;
import com.lion.api.bus.sync.SyncBusEvent;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;
import com.navi.tool.carinfo.TirePollingService;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.tire.api.TireAccount;
import com.navi.tool.ui.extra.CarControlAdapter;
import com.navi.tool.utils.ToastHelper;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopAssistantCarControlHelper {
    private static final String ACTION_TAG = "CarControlAction";
    private static final int CMD_BRAKE_FEEL_LINKED_DRIVE_MODE = 23;
    private static final int CMD_BRAKE_FEEL_MODE = 24;
    private static final int CMD_DRIVE_MODE = 4;
    private static final int CMD_EPB_STATUS = 85;
    private static final int CMD_PEDESTRIAN_ALERT = 102;
    private static final int CMD_RADAR_TONE = 17;
    private static final int CMD_SUSPENSION_LINKED_DRIVE_MODE = 189;
    private static final int CMD_SUSPENSION_MODE = 190;
    private static final int CMD_WIRELESS_CHARGING_STATE = 44;
    private static final int CMD_WIRELESS_CHARGING_SWITCH = 45;
    private static final int COLOR_LIGHT_HEIGHT_ACTIVE = -13312;
    private static final int COLOR_LIGHT_HEIGHT_DEFAULT = -1;
    private static final int COLOR_WIRELESS_ALERT = -27392;
    private static final int COLOR_WIRELESS_COMPLETE = -13318311;
    private static final int COLOR_WIRELESS_DEFAULT = -1;
    private static final int COLOR_WIRELESS_ENABLED = -50384;
    private static final int COLOR_WIRELESS_WORKING = -13312;
    private static final String KEY_SEAT_PANEL_ACTIONS = "seat_panel_actions_selected";
    private static final String KEY_SEAT_PANEL_ACTIONS_INITIALIZED = "seat_panel_actions_initialized";
    private static final String KEY_SEAT_PANEL_ACTIONS_LIGHT_HEIGHT_MIGRATED = "seat_panel_actions_light_height_migrated";
    private static final String KEY_SEAT_PANEL_ACTIONS_TRUNK_MIGRATED = "seat_panel_actions_trunk_migrated";
    private static final String KEY_SEAT_PANEL_ACTION_ORDER = "seat_panel_actions_order";
    private static final String KEY_SEAT_PANEL_PER_ROW_COUNT = "seat_panel_per_row_count";
    private static final String MODEL_TYPE_CUSTOM = "custom";
    private static final int MODULE_CAR_SETTING = 327681;
    private static final int MODULE_NEW_ENERGY = 327682;
    private static final int MODULE_QUERY = 327698;
    private static final int MODULE_RADAR = 327689;
    private static final float MOVING_TOP_CAR_OFFSET_DP = 10.0f;
    private static final String PREF_CAR_CONTROL_CUSTOM_MODEL_PATH = "car_control_custom_model_path";
    private static final String TAG = "CarControlHelper";
    private static final int TOP_MODEL_HEIGHT_COMPACT_DP = 200;
    private static final int TOP_MODEL_HEIGHT_EXPANDED_DP = 260;
    private final Activity activity;
    private View btnMem1Inline;
    private View btnMem2Inline;
    private View btnMem3Inline;
    private View btnMemSave1Inline;
    private View btnMemSave2Inline;
    private View btnMemSave3Inline;
    private View btnSeatMassageDriver;
    private View btnSeatMassageDriverInline;
    private View btnSeatMassagePass;
    private View btnSeatMassagePassInline;
    private Bitmap cachedCustomCarModelBitmap;
    private String cachedCustomCarModelPath;
    private CarControlAdapter carControlAdapter;
    private View carControlContainer;
    private RecyclerView carControlRecyclerView;
    private Runnable carModelClickListener;
    private CardView cardSeatControl;
    private CardView cardSeatMassage;
    private CardView cardSeatMemory;
    private View dividerHvacFan;
    private View dividerHvacSubActions;
    private View dividerSeatHeatVent;
    private View dividerSeatMassage;
    private View dividerSeatMassageInline;
    private View dividerSeatMemoryInline;
    private View dividerSeatMemorySave;
    private View dividerSeatMemorySaveInline;
    private View hvacAutoContainer;
    private View hvacCircContainer;
    private View hvacFragranceContainer;
    private View hvacModeContainer;
    private ImageView imgLaneAnim;
    private ImageView imgTireCard;
    private ImageView ivHvacAutoIcon;
    private ImageView ivHvacCircIcon;
    private ImageView ivHvacFragranceIcon;
    private ImageView ivHvacModeIcon;
    private ImageView ivLightHeightIcon;
    private ImageView ivPedestrianAlertIcon;
    private ImageView ivSeatHeatFlIcon;
    private ImageView ivSeatHeatFrIcon;
    private ImageView ivSeatHeatRlIcon;
    private ImageView ivSeatHeatRrIcon;
    private ImageView ivSeatMassageDriverIcon;
    private ImageView ivSeatMassageDriverInlineIcon;
    private ImageView ivSeatMassagePassIcon;
    private ImageView ivSeatMassagePassInlineIcon;
    private ImageView ivSeatVentFlIcon;
    private ImageView ivSeatVentFrIcon;
    private ImageView ivSteeringHeatIcon;
    private ImageView ivTrunkIcon;
    private ImageView ivWiperSensitivityIcon;
    private ImageView ivWirelessChargingIcon;
    private AnimationDrawable laneFrameAnimation;
    private View layoutHvacFanRow;
    private View layoutHvacPrimaryRow;
    private View layoutHvacRearFanRow;
    private View layoutHvacSubActionsRow;
    private View layoutHvacSubActionsRow2;
    private View layoutLaneSpeed;
    private View layoutSeatHeatRow;
    private View layoutSeatMassage;
    private View layoutSeatMassageInline;
    private View layoutSeatMemoryApply;
    private View layoutSeatMemoryInline;
    private View layoutSeatMemorySave;
    private View layoutSeatMemorySaveInline;
    private View layoutTireCard;
    private View lightHeightContainer;
    private final Runnable onCarDataUpdated;
    private View pedestrianAlertContainer;
    private final SharedPreferences prefs;
    private SeekBar sbHvacFan;
    private SeekBar sbHvacRearFan;
    private View seatHeatFlContainer;
    private View seatHeatFrContainer;
    private View seatHeatRlContainer;
    private View seatHeatRrContainer;
    private ViewGroup seatPanelContentContainer;
    private View seatVentFlContainer;
    private View seatVentFrContainer;
    private View steeringHeatContainer;
    private View trunkContainer;
    private TextView tvHvacAuto;
    private TextView tvHvacCirc;
    private TextView tvHvacFanLabel;
    private TextView tvHvacFragrance;
    private TextView tvHvacMode;
    private TextView tvHvacRearFanLabel;
    private TextView tvLaneSpeedUnit;
    private TextView tvLaneSpeedValue;
    private TextView tvLightHeight;
    private TextView tvNoCards;
    private TextView tvPedestrianAlert;
    private TextView tvSeatHeatFlLevel;
    private TextView tvSeatHeatFrLevel;
    private TextView tvSeatHeatRlLevel;
    private TextView tvSeatHeatRrLevel;
    private TextView tvSeatMassageDriver;
    private TextView tvSeatMassageDriverInline;
    private TextView tvSeatMassagePass;
    private TextView tvSeatMassagePassInline;
    private TextView tvSeatVentFlLevel;
    private TextView tvSeatVentFrLevel;
    private TextView tvSteeringHeat;
    private TextView tvTireFlTemp;
    private TextView tvTireFlValue;
    private TextView tvTireFrTemp;
    private TextView tvTireFrValue;
    private TextView tvTireMeta;
    private TextView tvTireRlTemp;
    private TextView tvTireRlValue;
    private TextView tvTireRrTemp;
    private TextView tvTireRrValue;
    private TextView tvTopGear;
    private TextView tvTrunk;
    private TextView tvWiperSensitivity;
    private TextView tvWirelessCharging;
    private View wiperSensitivityContainer;
    private AnimationDrawable wirelessChargingAnimation;
    private View wirelessChargingContainer;
    private static final String ACTION_HEAT_FL = "heat_fl";
    private static final String ACTION_HEAT_FR = "heat_fr";
    private static final String ACTION_HEAT_RL = "heat_rl";
    private static final String ACTION_HEAT_RR = "heat_rr";
    private static final String ACTION_VENT_FL = "vent_fl";
    private static final String ACTION_VENT_FR = "vent_fr";
    private static final String ACTION_HVAC_AUTO = "hvac_auto";
    private static final String ACTION_HVAC_MODE = "hvac_mode";
    private static final String ACTION_HVAC_FAN = "hvac_fan";
    private static final String ACTION_HVAC_REAR_FAN = "hvac_rear_fan";
    private static final String ACTION_HVAC_CIRC = "hvac_circ";
    private static final String ACTION_WIPER_SENSITIVITY = "wiper_sensitivity";
    private static final String ACTION_LIGHT_HEIGHT = "light_height";
    private static final String ACTION_STEERING_HEAT = "steering_heat";
    private static final String ACTION_WIRELESS_CHARGING = "wireless_charging";
    private static final String ACTION_FRAGRANCE = "fragrance";
    private static final String ACTION_MEMORY_APPLY = "memory_apply";
    private static final String ACTION_MEMORY_SAVE = "memory_save";
    private static final String ACTION_MASSAGE_DRIVER = "massage_driver";
    private static final String ACTION_MASSAGE_PASS = "massage_pass";
    private static final String ACTION_PEDESTRIAN_ALERT = "pedestrian_alert";
    private static final String ACTION_TRUNK = "trunk_switch";
    private static final String[] SEAT_PANEL_ACTION_KEYS = {ACTION_HEAT_FL, ACTION_HEAT_FR, ACTION_HEAT_RL, ACTION_HEAT_RR, ACTION_VENT_FL, ACTION_VENT_FR, ACTION_HVAC_AUTO, ACTION_HVAC_MODE, ACTION_HVAC_FAN, ACTION_HVAC_REAR_FAN, ACTION_HVAC_CIRC, ACTION_WIPER_SENSITIVITY, ACTION_LIGHT_HEIGHT, ACTION_STEERING_HEAT, ACTION_WIRELESS_CHARGING, ACTION_FRAGRANCE, ACTION_MEMORY_APPLY, ACTION_MEMORY_SAVE, ACTION_MASSAGE_DRIVER, ACTION_MASSAGE_PASS, ACTION_PEDESTRIAN_ALERT, ACTION_TRUNK};
    private static final String[] SEAT_PANEL_ACTION_LABELS = {"主驾加热", "副驾加热", "左后加热", "右后加热", "主驾通风", "副驾通风", "AUTO/AC", "吹风模式", "风量调节", "后排风量", "内外循环", "雨感灵敏", "灯光高度", "方向盘加热", "无线充电", "香氛", "座椅记忆调用", "座椅记忆保存", "主驾按摩", "副驾按摩", "行人提示", "尾门"};
    private static final Object SEAT_PANEL_FAN_MARKER = new Object();
    private static final Object SEAT_PANEL_REAR_FAN_MARKER = new Object();
    private static final String[] TOP_INFO_IDS = {"speed", "gear", "fuel", "range", "odo", "temp", "outside_temp", "voltage", "engine_status", "power_mode", "trip_mileage", "drive_time", "door_fl", "door_fr", "door_rl", "door_rr", "trunk", "hood", "child_lock", "sunroof", "sunshade", "window_fl", "window_fr", "window_rl", "window_rr", "light_head", "light_high", "light_fog", "light_parking", "light_hazard", "hvac_ac", ACTION_HVAC_FAN, ACTION_HVAC_REAR_FAN, ACTION_HVAC_CIRC, ACTION_HVAC_AUTO, "hvac_power", ACTION_HVAC_MODE, "hvac_dual", "hvac_front_defrost", "hvac_rear_defrost", "driving_wiper", "driving_handbrake", "driving_brake", "driving_brake_link", "driving_suspension_mode", "driving_brake_feel_mode", ACTION_WIPER_SENSITIVITY, "seat_heat_fl", "seat_heat_fr", "seat_heat_rl", "seat_heat_rr", "seat_vent_fl", "seat_vent_fr", "seat_msg_driver", "seat_msg_pass", "ev_soc", "ev_soh", "battery_power", "battery_health", "avg_fuel_recently_50km", "avg_fuel_after_clear", "cdm_power"};
    private static final String[] TOP_INFO_NAMES = {"车速", "档位", "剩余油量", "续航里程", "总里程", "空调温度", "车外温度", "电瓶电压", "引擎状态", "电源状态", "小计里程", "驾驶时长", "左前门", "右前门", "左后门", "右后门", "后备箱", "引擎盖", "儿童锁", "天窗", "遮阳帘", "左前窗", "右前窗", "左后窗", "右后窗", "近光灯", "远光灯", "雾灯", "示宽灯", "双闪灯", "空调AC", "风量", "后排风量", "循环模式", "空调AUTO", "空调系统", "吹风模式", "双区", "前除霜", "后除霜", "雨刮", "手刹", "刹车踏板", "制动关联", "悬架", "制动模式", "雨感灵敏", "主驾加热", "副驾加热", "左后加热", "右后加热", "主驾通风", "副驾通风", "主驾按摩", "副驾按摩", "剩余电量", "电池健康", "电瓶电量", "电瓶健康", "即时油耗", "总体油耗", "CDM动力"};
    private static final Set<String> LION_SUPPORTED_TOP_INFO_IDS = new HashSet(Arrays.asList("speed", "gear", "temp", "outside_temp", "engine_status", "door_fl", "door_fr", "door_rl", "door_rr", "trunk", "hood", "sunroof", "window_fl", "window_fr", "window_rl", "window_rr", "hvac_ac", ACTION_HVAC_FAN, ACTION_HVAC_AUTO, "hvac_power", ACTION_HVAC_MODE, "seat_heat_fl", "seat_heat_fr", "seat_vent_fl", "seat_vent_fr"));
    private static final int[] LANE_FRAME_RES = {g4.o.lane_a1, g4.o.lane_a2, g4.o.lane_a3, g4.o.lane_a4, g4.o.lane_a5, g4.o.lane_a6, g4.o.lane_a7};
    private static final int[] WIRELESS_CHARGING_FRAME_RES = {g4.o.ic_wireless_charging_10, g4.o.ic_wireless_charging_20, g4.o.ic_wireless_charging_30, g4.o.ic_wireless_charging_40, g4.o.ic_wireless_charging_50, g4.o.ic_wireless_charging_60, g4.o.ic_wireless_charging_70, g4.o.ic_wireless_charging_80, g4.o.ic_wireless_charging_90};
    private boolean isCarCardDragging = false;
    private boolean isCarControlEnabled = false;
    private boolean isVisualCarModelEnabled = false;
    private boolean isSeatComfortEnabled = false;
    private boolean pedestrianAlertQuerySent = false;
    private boolean trunkCmdInFlight = false;
    private boolean tireCardShowing = false;
    private boolean dynamicTireCardShowing = false;
    private boolean restoreWasTop = false;
    private boolean hasRestoreView = false;
    private long lastTireDataTs = 0;
    private boolean tireObserverReady = false;
    private final Handler dynamicHandler = new Handler(Looper.getMainLooper());
    private final Runnable dynamicHideRunnable = new Runnable() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.1
        @Override // java.lang.Runnable
        public void run() {
            if (DesktopAssistantCarControlHelper.this.dynamicTireCardShowing) {
                DesktopAssistantCarControlHelper.this.hideTireCard();
            }
        }
    };
    private final Handler tireCardHandler = new Handler(Looper.getMainLooper());
    private final Runnable tireCardTick = new Runnable() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.2
        @Override // java.lang.Runnable
        public void run() {
            if (!DesktopAssistantCarControlHelper.this.tireCardShowing || DesktopAssistantCarControlHelper.this.activity.isFinishing() || DesktopAssistantCarControlHelper.this.activity.isDestroyed()) {
                return;
            }
            DesktopAssistantCarControlHelper.this.updateTireCard((o4.s) o4.c.c().f7948p.d());
            DesktopAssistantCarControlHelper.this.tireCardHandler.postDelayed(this, t1.f.T_MS_MINUTE);
        }
    };
    private boolean laneAnimationRunning = false;
    private int laneAnimationFrameDuration = -1;
    private boolean laneStaticShown = false;
    private String wirelessChargingDisplayMode = s5.h0.FRAGMENT_ENCODE_SET;
    private long cachedCustomCarModelLastModified = -1;
    private long lastCarModelSwapClick = 0;

    public DesktopAssistantCarControlHelper(Activity activity, SharedPreferences sharedPreferences, Runnable runnable) {
        this.activity = activity;
        this.prefs = sharedPreferences;
        this.onCarDataUpdated = runnable;
    }

    public static boolean actionApplyEpb(Context context) {
        return setEpbTargetState(true);
    }

    public static boolean actionApplySeatMemory(Context context, int i7) {
        if (i7 < 1) {
            i7 = 1;
        }
        if (i7 > 3) {
            i7 = 3;
        }
        if (isSeatMemoryApplyAllowed(context)) {
            return sendControlCommandInternal(MODULE_CAR_SETTING, 52, i7);
        }
        return false;
    }

    public static boolean actionCloseRadar(Context context) {
        return setRadarTargetState(false);
    }

    public static boolean actionOpenRadar(Context context) {
        return setRadarTargetState(true);
    }

    public static boolean actionReleaseEpb(Context context) {
        return setEpbTargetState(false);
    }

    public static boolean actionSendCommand(Context context, int i7, int i8, int i9) {
        return sendControlCommandInternal(i7, i8, i9);
    }

    public static boolean actionSetBrakeFeelModeComfort(Context context) {
        return setBrakeFeelModeInternal(1);
    }

    public static boolean actionSetBrakeFeelModeSport(Context context) {
        return setBrakeFeelModeInternal(2);
    }

    public static boolean actionSetBrakeLinkEnabled(Context context, boolean z6) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 23, z6 ? 1 : 2);
    }

    public static boolean actionSetChildLock(Context context, boolean z6) {
        String str = (String) o4.c.c().f7951s.d();
        if (z6 && "锁定".equals(str)) {
            return true;
        }
        if (z6 || !"未锁".equals(str)) {
            return sendControlCommandInternal(MODULE_CAR_SETTING, z1.c.MUL_FLOAT, 1);
        }
        return true;
    }

    public static boolean actionSetDriveModeEconomy(Context context) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 4, 0);
    }

    public static boolean actionSetDriveModeMud(Context context) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 4, 4);
    }

    public static boolean actionSetDriveModeOffroad(Context context) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 4, 5);
    }

    public static boolean actionSetDriveModeSand(Context context) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 4, 7);
    }

    public static boolean actionSetDriveModeSnow(Context context) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 4, 3);
    }

    public static boolean actionSetDriveModeSport(Context context) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 4, 2);
    }

    public static boolean actionSetDriveModeStandard(Context context) {
        return sendControlCommandInternal(MODULE_NEW_ENERGY, 4, 1);
    }

    public static boolean actionSetSuspensionLinkEnabled(Context context, boolean z6) {
        return sendControlCommandInternal(MODULE_CAR_SETTING, 189, z6 ? 2 : 1);
    }

    public static boolean actionSetSuspensionModeComfort(Context context) {
        return setSuspensionModeInternal(1);
    }

    public static boolean actionSetSuspensionModeSport(Context context) {
        return setSuspensionModeInternal(3);
    }

    public static boolean actionSetSuspensionModeStandard(Context context) {
        return setSuspensionModeInternal(2);
    }

    private void addSeatPanelRenderTokensForKey(List<Object> list, String str) {
        if (ACTION_HEAT_FL.equals(str)) {
            addVisibleSeatPanelToken(list, this.seatHeatFlContainer);
            return;
        }
        if (ACTION_HEAT_FR.equals(str)) {
            addVisibleSeatPanelToken(list, this.seatHeatFrContainer);
            return;
        }
        if (ACTION_HEAT_RL.equals(str)) {
            addVisibleSeatPanelToken(list, this.seatHeatRlContainer);
            return;
        }
        if (ACTION_HEAT_RR.equals(str)) {
            addVisibleSeatPanelToken(list, this.seatHeatRrContainer);
            return;
        }
        if (ACTION_VENT_FL.equals(str)) {
            addVisibleSeatPanelToken(list, this.seatVentFlContainer);
            return;
        }
        if (ACTION_VENT_FR.equals(str)) {
            addVisibleSeatPanelToken(list, this.seatVentFrContainer);
            return;
        }
        if (ACTION_HVAC_AUTO.equals(str)) {
            addVisibleSeatPanelToken(list, this.hvacAutoContainer);
            return;
        }
        if (ACTION_HVAC_MODE.equals(str)) {
            addVisibleSeatPanelToken(list, this.hvacModeContainer);
            return;
        }
        if (ACTION_HVAC_CIRC.equals(str)) {
            addVisibleSeatPanelToken(list, this.hvacCircContainer);
            return;
        }
        if (ACTION_PEDESTRIAN_ALERT.equals(str)) {
            addVisibleSeatPanelToken(list, this.pedestrianAlertContainer);
            return;
        }
        if (ACTION_WIPER_SENSITIVITY.equals(str)) {
            addVisibleSeatPanelToken(list, this.wiperSensitivityContainer);
            return;
        }
        if (ACTION_LIGHT_HEIGHT.equals(str)) {
            addVisibleSeatPanelToken(list, this.lightHeightContainer);
            return;
        }
        if (ACTION_TRUNK.equals(str)) {
            addVisibleSeatPanelToken(list, this.trunkContainer);
            return;
        }
        if (ACTION_STEERING_HEAT.equals(str)) {
            addVisibleSeatPanelToken(list, this.steeringHeatContainer);
            return;
        }
        if (ACTION_WIRELESS_CHARGING.equals(str)) {
            addVisibleSeatPanelToken(list, this.wirelessChargingContainer);
            return;
        }
        if (ACTION_FRAGRANCE.equals(str)) {
            addVisibleSeatPanelToken(list, this.hvacFragranceContainer);
            return;
        }
        if (ACTION_MEMORY_APPLY.equals(str)) {
            addVisibleSeatPanelToken(list, this.btnMem1Inline);
            addVisibleSeatPanelToken(list, this.btnMem2Inline);
            addVisibleSeatPanelToken(list, this.btnMem3Inline);
        } else if (ACTION_MEMORY_SAVE.equals(str)) {
            addVisibleSeatPanelToken(list, this.btnMemSave1Inline);
            addVisibleSeatPanelToken(list, this.btnMemSave2Inline);
            addVisibleSeatPanelToken(list, this.btnMemSave3Inline);
        } else if (ACTION_MASSAGE_DRIVER.equals(str)) {
            addVisibleSeatPanelToken(list, this.btnSeatMassageDriverInline);
        } else if (ACTION_MASSAGE_PASS.equals(str)) {
            addVisibleSeatPanelToken(list, this.btnSeatMassagePassInline);
        }
    }

    private void addSeatPanelRowContainer(List<ViewGroup> list, View view) {
        if (view instanceof ViewGroup) {
            list.add((ViewGroup) view);
        }
    }

    private void addVisibleSeatPanelToken(List<Object> list, View view) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        list.add(view);
    }

    private int appendSeatPanelViewChunk(List<View> list, List<View> list2, List<ViewGroup> list3, int i7) {
        if (list.isEmpty()) {
            return i7;
        }
        int i8 = 0;
        for (Integer num : buildSeatPanelRowItemCounts(list.size(), getSeatPanelPerRowCount())) {
            if (i7 >= list3.size()) {
                break;
            }
            int i9 = i7 + 1;
            ViewGroup viewGroup = list3.get(i7);
            int i10 = 0;
            while (i10 < num.intValue() && i8 < list.size()) {
                int i11 = i8 + 1;
                View view = list.get(i8);
                normalizeSubActionButtonLayout(view);
                moveViewToParent(view, viewGroup);
                i10++;
                i8 = i11;
            }
            if (viewGroup.getChildCount() > 0) {
                viewGroup.setVisibility(0);
                list2.add(viewGroup);
            }
            i7 = i9;
        }
        return i7;
    }

    private void applyCustomFrontModel(ImageView imageView, int i7) {
        Bitmap bitmap;
        if (imageView == null) {
            return;
        }
        String string = this.prefs.getString(PREF_CAR_CONTROL_CUSTOM_MODEL_PATH, null);
        if (string == null || string.trim().isEmpty()) {
            this.cachedCustomCarModelPath = null;
            this.cachedCustomCarModelBitmap = null;
            this.cachedCustomCarModelLastModified = -1L;
            imageView.setImageResource(i7);
            return;
        }
        File file = new File(string);
        if (!file.exists() || !file.isFile()) {
            this.cachedCustomCarModelPath = null;
            this.cachedCustomCarModelBitmap = null;
            this.cachedCustomCarModelLastModified = -1L;
            imageView.setImageResource(i7);
            return;
        }
        long lastModified = file.lastModified();
        if (!string.equals(this.cachedCustomCarModelPath) || lastModified != this.cachedCustomCarModelLastModified || (bitmap = this.cachedCustomCarModelBitmap) == null || bitmap.isRecycled()) {
            this.cachedCustomCarModelBitmap = BitmapFactory.decodeFile(string);
            this.cachedCustomCarModelPath = string;
            this.cachedCustomCarModelLastModified = lastModified;
        }
        Bitmap bitmap2 = this.cachedCustomCarModelBitmap;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            imageView.setImageResource(i7);
        } else {
            imageView.setImageBitmap(this.cachedCustomCarModelBitmap);
        }
    }

    private void applyFanRowPositionByOrder() {
        List<String> seatPanelActionOrder;
        int indexOf;
        View view;
        int childCount;
        View view2 = this.layoutHvacFanRow;
        if (view2 == null) {
            return;
        }
        ViewParent parent = view2.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (this.dividerHvacFan != null && (indexOf = (seatPanelActionOrder = getSeatPanelActionOrder()).indexOf(ACTION_HVAC_FAN)) >= 0) {
                int minIndexInOrder = minIndexInOrder(seatPanelActionOrder, new String[]{ACTION_HEAT_FL, ACTION_HEAT_FR, ACTION_HEAT_RL, ACTION_HEAT_RR, ACTION_VENT_FL, ACTION_VENT_FR, ACTION_HVAC_AUTO, ACTION_HVAC_MODE});
                int minIndexInOrder2 = minIndexInOrder(seatPanelActionOrder, new String[]{ACTION_STEERING_HEAT, ACTION_HVAC_CIRC, ACTION_FRAGRANCE, ACTION_LIGHT_HEIGHT, ACTION_WIPER_SENSITIVITY, ACTION_MASSAGE_DRIVER, ACTION_MASSAGE_PASS});
                int minIndexInOrder3 = minIndexInOrder(seatPanelActionOrder, new String[]{ACTION_MEMORY_APPLY, ACTION_MEMORY_SAVE});
                viewGroup.removeView(this.dividerHvacFan);
                viewGroup.removeView(this.layoutHvacFanRow);
                ArrayList arrayList = new ArrayList();
                if (minIndexInOrder != Integer.MAX_VALUE && this.layoutSeatHeatRow != null) {
                    arrayList.add(new int[]{minIndexInOrder, 1});
                }
                if (minIndexInOrder2 != Integer.MAX_VALUE) {
                    View view3 = this.dividerHvacSubActions;
                    if (view3 == null) {
                        view3 = this.layoutHvacSubActionsRow;
                    }
                    if (view3 != null) {
                        arrayList.add(new int[]{minIndexInOrder2, 2});
                    }
                }
                if (minIndexInOrder3 != Integer.MAX_VALUE) {
                    View view4 = this.dividerSeatMemoryInline;
                    if (view4 == null) {
                        view4 = this.layoutSeatMemoryInline;
                    }
                    if (view4 != null) {
                        arrayList.add(new int[]{minIndexInOrder3, 3});
                    }
                }
                Collections.sort(arrayList, new x(11));
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        view = null;
                        break;
                    }
                    int[] iArr = (int[]) it.next();
                    if (indexOf < iArr[0]) {
                        int i7 = iArr[1];
                        if (i7 == 1) {
                            view = this.layoutSeatHeatRow;
                        } else if (i7 == 2) {
                            view = this.dividerHvacSubActions;
                            if (view == null) {
                                view = this.layoutHvacSubActionsRow;
                            }
                        } else {
                            view = this.dividerSeatMemoryInline;
                            if (view == null) {
                                view = this.layoutSeatMemoryInline;
                            }
                        }
                    }
                }
                if (view != null) {
                    childCount = viewGroup.indexOfChild(view);
                    if (childCount < 0) {
                        childCount = viewGroup.getChildCount();
                    }
                } else {
                    childCount = viewGroup.getChildCount();
                }
                viewGroup.addView(this.dividerHvacFan, childCount);
                viewGroup.addView(this.layoutHvacFanRow, childCount + 1);
            }
        }
    }

    private void bindFrontCarModelSeatPanelDialog(View view) {
        if (view == null) {
            return;
        }
        view.setOnTouchListener(new q0(1, new GestureDetector(this.activity, new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.7
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                DesktopAssistantCarControlHelper.this.handleCarModelDoubleTap();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                DesktopAssistantCarControlHelper.this.handleCarModelSingleTap();
                return true;
            }
        })));
    }

    private void bindSeatPanelConfigLongPress(View view) {
        if (view == null) {
            return;
        }
        view.setOnLongClickListener(new s1(this, 0));
    }

    private void bindTireCardGesture() {
        if (this.layoutTireCard == null) {
            return;
        }
        this.layoutTireCard.setOnTouchListener(new q0(2, new GestureDetector(this.activity, new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.6
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (DesktopAssistantCarControlHelper.this.prefs.getBoolean("desktop_double_click_swap", false)) {
                    return true;
                }
                DesktopAssistantCarControlHelper.this.hideTireCard();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                DesktopAssistantCarControlHelper.this.refreshTireManually();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (!DesktopAssistantCarControlHelper.this.prefs.getBoolean("desktop_double_click_swap", false)) {
                    return true;
                }
                DesktopAssistantCarControlHelper.this.hideTireCard();
                return true;
            }
        })));
    }

    private List<Object> buildSeatPanelRenderTokens() {
        ArrayList arrayList = new ArrayList();
        for (String str : getSeatPanelActionOrder()) {
            if (ACTION_HVAC_FAN.equals(str)) {
                View view = this.layoutHvacFanRow;
                if (view != null && view.getVisibility() == 0) {
                    arrayList.add(SEAT_PANEL_FAN_MARKER);
                }
            } else if (ACTION_HVAC_REAR_FAN.equals(str)) {
                View view2 = this.layoutHvacRearFanRow;
                if (view2 != null && view2.getVisibility() == 0) {
                    arrayList.add(SEAT_PANEL_REAR_FAN_MARKER);
                }
            } else {
                addSeatPanelRenderTokensForKey(arrayList, str);
            }
        }
        return arrayList;
    }

    private List<Integer> buildSeatPanelRowItemCounts(int i7, int i8) {
        ArrayList arrayList = new ArrayList();
        if (i7 <= 0) {
            return arrayList;
        }
        if (i8 < 4) {
            i8 = 4;
        }
        if (i8 > 8) {
            i8 = 8;
        }
        while (i7 > 0) {
            int min = Math.min(i8, i7);
            if (i7 - min == 1 && min > 4) {
                min--;
            }
            arrayList.add(Integer.valueOf(min));
            i7 -= min;
        }
        return arrayList;
    }

    private String cdmPowerTopValue(int i7) {
        switch (i7) {
            case 0:
                return "熄火";
            case 1:
                return "待机";
            case 2:
            case 3:
            case 13:
            case 14:
            case 17:
            case 20:
                return "纯电";
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 16:
            case 18:
            case 19:
                return "汽油";
            case 15:
                return "充电";
            default:
                return a.a.e("未知(", i7, ")");
        }
    }

    private static int clampDesktopCardMarginDp(int i7) {
        if (i7 < 0) {
            return 0;
        }
        if (i7 > 20) {
            return 20;
        }
        return i7;
    }

    private void detachSeatPanelManagedBlocks(List<ViewGroup> list, List<View> list2) {
        removeFromParent(this.layoutHvacFanRow);
        removeFromParent(this.layoutHvacRearFanRow);
        Iterator<ViewGroup> it = list.iterator();
        while (it.hasNext()) {
            removeFromParent(it.next());
        }
        Iterator<View> it2 = list2.iterator();
        while (it2.hasNext()) {
            removeFromParent(it2.next());
        }
    }

    private void ensureSeatPanelActionsInitialized() {
        if (this.prefs.getString(KEY_SEAT_PANEL_ACTION_ORDER, s5.h0.FRAGMENT_ENCODE_SET).trim().isEmpty()) {
            saveSeatPanelActionOrder(Arrays.asList(SEAT_PANEL_ACTION_KEYS));
        }
        if (!this.prefs.getBoolean(KEY_SEAT_PANEL_ACTIONS_INITIALIZED, false)) {
            HashSet hashSet = new HashSet();
            hashSet.add(ACTION_HEAT_FL);
            hashSet.add(ACTION_HEAT_FR);
            hashSet.add(ACTION_HEAT_RL);
            hashSet.add(ACTION_HEAT_RR);
            hashSet.add(ACTION_VENT_FL);
            hashSet.add(ACTION_VENT_FR);
            hashSet.add(ACTION_LIGHT_HEIGHT);
            saveSeatPanelActions(filterUnsupportedSeatPanelActions(hashSet));
            a.a.m(this.prefs, KEY_SEAT_PANEL_ACTIONS_INITIALIZED, true);
        }
        if (!this.prefs.getBoolean(KEY_SEAT_PANEL_ACTIONS_LIGHT_HEIGHT_MIGRATED, false)) {
            Set<String> selectedSeatPanelActions = getSelectedSeatPanelActions();
            if (!selectedSeatPanelActions.contains(ACTION_LIGHT_HEIGHT)) {
                selectedSeatPanelActions.add(ACTION_LIGHT_HEIGHT);
                saveSeatPanelActions(filterUnsupportedSeatPanelActions(selectedSeatPanelActions));
            }
            a.a.m(this.prefs, KEY_SEAT_PANEL_ACTIONS_LIGHT_HEIGHT_MIGRATED, true);
        }
        if (this.prefs.getBoolean(KEY_SEAT_PANEL_ACTIONS_TRUNK_MIGRATED, false)) {
            return;
        }
        Set<String> selectedSeatPanelActions2 = getSelectedSeatPanelActions();
        if (!selectedSeatPanelActions2.contains(ACTION_TRUNK)) {
            selectedSeatPanelActions2.add(ACTION_TRUNK);
            saveSeatPanelActions(filterUnsupportedSeatPanelActions(selectedSeatPanelActions2));
        }
        a.a.m(this.prefs, KEY_SEAT_PANEL_ACTIONS_TRUNK_MIGRATED, true);
    }

    private Set<String> filterUnsupportedSeatPanelActions(Set<String> set) {
        HashSet hashSet = new HashSet(set);
        boolean z6 = this.prefs.getBoolean("desktop_seat_compat_mode", false);
        boolean z7 = this.prefs.getBoolean("lion_sync_service_enabled", false);
        if (z6) {
            hashSet.remove(ACTION_HVAC_AUTO);
            hashSet.remove(ACTION_HVAC_MODE);
            hashSet.remove(ACTION_HVAC_FAN);
            hashSet.remove(ACTION_HVAC_REAR_FAN);
            hashSet.remove(ACTION_HVAC_CIRC);
            hashSet.remove(ACTION_WIPER_SENSITIVITY);
            hashSet.remove(ACTION_LIGHT_HEIGHT);
            hashSet.remove(ACTION_STEERING_HEAT);
            hashSet.remove(ACTION_FRAGRANCE);
            hashSet.remove(ACTION_PEDESTRIAN_ALERT);
            hashSet.remove(ACTION_TRUNK);
        }
        if (z7) {
            hashSet.remove(ACTION_HVAC_REAR_FAN);
            hashSet.remove(ACTION_HVAC_CIRC);
            hashSet.remove(ACTION_WIPER_SENSITIVITY);
            hashSet.remove(ACTION_LIGHT_HEIGHT);
            hashSet.remove(ACTION_STEERING_HEAT);
            hashSet.remove(ACTION_WIRELESS_CHARGING);
            hashSet.remove(ACTION_FRAGRANCE);
            hashSet.remove(ACTION_MEMORY_APPLY);
            hashSet.remove(ACTION_MEMORY_SAVE);
            hashSet.remove(ACTION_MASSAGE_DRIVER);
            hashSet.remove(ACTION_MASSAGE_PASS);
            hashSet.remove(ACTION_PEDESTRIAN_ALERT);
            hashSet.remove(ACTION_TRUNK);
        }
        return hashSet;
    }

    private int findFragrancePosByTypeFromCache(int i7) {
        Map map;
        try {
            map = (Map) o4.c.c().F.d();
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

    private int findSeatPanelActionIndex(String str) {
        if (str == null) {
            return -1;
        }
        int i7 = 0;
        while (true) {
            String[] strArr = SEAT_PANEL_ACTION_KEYS;
            if (i7 >= strArr.length) {
                return -1;
            }
            if (str.equals(strArr[i7])) {
                return i7;
            }
            i7++;
        }
    }

    private String fragranceTypeText(int i7) {
        return i7 == 1 ? "清新自然" : i7 == 2 ? "治愈舒缓" : i7 == 3 ? "运动激情" : i7 == 0 ? "未配置" : "香型";
    }

    private String getDoorStatus(o4.c cVar, String str) {
        Boolean bool;
        Map map = (Map) cVar.f7956x.d();
        return (map == null || (bool = (Boolean) map.get(str)) == null) ? "--" : bool.booleanValue() ? "开启" : "关闭";
    }

    private String getDoorWindowStatus(Boolean bool, String str) {
        if (bool == null && str == null) {
            return "--";
        }
        boolean equals = Boolean.TRUE.equals(bool);
        boolean z6 = "开".equals(str) || "开启中".equals(str);
        return (equals && z6) ? "门开 窗开" : equals ? "门开" : z6 ? "窗开" : "关";
    }

    private Set<String> getEffectiveSeatPanelActions() {
        return filterUnsupportedSeatPanelActions(getSelectedSeatPanelActions());
    }

    private String getGnssDisplayValue(String str) {
        GnssInfoProvider gnssInfoProvider = GnssInfoProvider.getInstance();
        str.getClass();
        String str2 = !str.equals("gnss_altitude") ? !str.equals("gnss_accuracy") ? (String) gnssInfoProvider.getSatellites().d() : (String) gnssInfoProvider.getAccuracy().d() : (String) gnssInfoProvider.getAltitude().d();
        return str2 == null ? "--" : str2;
    }

    private String getHvacValue(o4.c cVar, String str) {
        Map map = (Map) cVar.F.d();
        return map != null ? (String) map.getOrDefault(str, "--") : "--";
    }

    private int getLaneFrameDuration(int i7) {
        if (i7 <= 20) {
            return KgMusic.imgUrlSize240;
        }
        if (i7 <= 40) {
            return z1.c.AND_LONG;
        }
        if (i7 <= 70) {
            return 90;
        }
        return i7 <= 100 ? 50 : 30;
    }

    private String getLightStatus(o4.c cVar, String str) {
        Boolean bool;
        Map map = (Map) cVar.f7958z.d();
        return (map == null || (bool = (Boolean) map.get(str)) == null) ? "--" : bool.booleanValue() ? "开启" : "关闭";
    }

    private String getLightStatusStr(Map<String, Boolean> map) {
        if (map == null || map.isEmpty()) {
            return "--";
        }
        StringBuilder sb = new StringBuilder();
        Boolean bool = Boolean.TRUE;
        if (bool.equals(map.get("head"))) {
            sb.append("近光 ");
        }
        if (bool.equals(map.get("high"))) {
            sb.append("远光 ");
        }
        if (bool.equals(map.get("fog"))) {
            sb.append("雾灯 ");
        }
        if (bool.equals(map.get("parking"))) {
            sb.append("示宽 ");
        }
        if (bool.equals(map.get("hazard"))) {
            sb.append("双闪 ");
        } else {
            if (bool.equals(map.get("turn_left"))) {
                sb.append("左转 ");
            }
            if (bool.equals(map.get("turn_right"))) {
                sb.append("右转 ");
            }
        }
        String trim = sb.toString().trim();
        return trim.isEmpty() ? "关" : trim;
    }

    private String getRearFanDisplayValue(o4.c cVar) {
        String hvacValue = getHvacValue(cVar, "rear_fan");
        try {
            int parseInt = Integer.parseInt(hvacValue);
            if (parseInt == 0) {
                return "关闭";
            }
            return parseInt + "档";
        } catch (NumberFormatException unused) {
            return hvacValue;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSeatPanelActionLabel(String str) {
        int findSeatPanelActionIndex = findSeatPanelActionIndex(str);
        if (findSeatPanelActionIndex < 0) {
            return str;
        }
        String[] strArr = SEAT_PANEL_ACTION_LABELS;
        return findSeatPanelActionIndex < strArr.length ? strArr[findSeatPanelActionIndex] : str;
    }

    private List<String> getSeatPanelActionOrder() {
        String string = this.prefs.getString(KEY_SEAT_PANEL_ACTION_ORDER, s5.h0.FRAGMENT_ENCODE_SET);
        ArrayList arrayList = new ArrayList();
        if (string != null && !string.trim().isEmpty()) {
            String[] split = string.split(",");
            int length = split.length;
            for (int i7 = 0; i7 < length; i7++) {
                String str = split[i7];
                String trim = str == null ? s5.h0.FRAGMENT_ENCODE_SET : str.trim();
                if (!trim.isEmpty() && findSeatPanelActionIndex(trim) >= 0 && !arrayList.contains(trim)) {
                    arrayList.add(trim);
                }
            }
        }
        for (String str2 : SEAT_PANEL_ACTION_KEYS) {
            if (!arrayList.contains(str2)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    private int getSeatPanelPerRowCount() {
        int i7 = this.prefs.getInt(KEY_SEAT_PANEL_PER_ROW_COUNT, 4);
        if (i7 < 4) {
            return 4;
        }
        if (i7 > 8) {
            return 8;
        }
        return i7;
    }

    private String getSeatValue(o4.c cVar, String str) {
        Map map = (Map) cVar.G.d();
        return map != null ? (String) map.getOrDefault(str, "--") : "--";
    }

    private Set<String> getSelectedSeatPanelActions() {
        String string = this.prefs.getString(KEY_SEAT_PANEL_ACTIONS, s5.h0.FRAGMENT_ENCODE_SET);
        HashSet hashSet = new HashSet();
        if (string != null && !string.trim().isEmpty()) {
            String[] split = string.split(",");
            int length = split.length;
            for (int i7 = 0; i7 < length; i7++) {
                String str = split[i7];
                String trim = str == null ? s5.h0.FRAGMENT_ENCODE_SET : str.trim();
                if (!trim.isEmpty()) {
                    hashSet.add(trim);
                }
            }
        }
        return hashSet;
    }

    private String getSimpleStatus(Boolean bool) {
        return bool == null ? "--" : bool.booleanValue() ? "开" : "关";
    }

    private List<String> getSortedSelectedActions(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        for (String str : getSeatPanelActionOrder()) {
            if (set.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private String getTireStatusStr(Map<String, String> map) {
        boolean z6;
        if (map == null || map.isEmpty()) {
            return "--";
        }
        StringBuilder sb = new StringBuilder();
        boolean z7 = true;
        if ("异常".equals(map.get("fl"))) {
            sb.append("左前异常 ");
            z6 = true;
        } else {
            z6 = false;
        }
        if ("异常".equals(map.get("fr"))) {
            sb.append("右前异常 ");
            z6 = true;
        }
        if ("异常".equals(map.get("rl"))) {
            sb.append("左后异常 ");
        } else {
            z7 = z6;
        }
        if ("异常".equals(map.get("rr"))) {
            sb.append("右后异常 ");
        } else if (!z7) {
            return "正常";
        }
        return sb.toString().trim();
    }

    private String getWindowStatus(o4.c cVar, String str) {
        Map map = (Map) cVar.C.d();
        return map != null ? (String) map.getOrDefault(str, "--") : "--";
    }

    private int getWirelessChargingWorkState(Map<String, String> map) {
        String str;
        if (map == null || (str = map.get("wireless_charging_state")) == null) {
            return 0;
        }
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 0 || parseInt > 3) {
                return 0;
            }
            return parseInt;
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCarModelDoubleTap() {
        boolean z6 = this.prefs.getBoolean("desktop_double_click_swap", false);
        Runnable runnable = this.carModelClickListener;
        if (runnable == null) {
            toggleTireCardIfEnabled();
        } else if (z6) {
            runnable.run();
        } else {
            toggleTireCardIfEnabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCarModelSingleTap() {
        boolean z6 = this.prefs.getBoolean("desktop_double_click_swap", false);
        Runnable runnable = this.carModelClickListener;
        if (runnable == null) {
            if (this.prefs.getBoolean("seat_comfort_panel_enabled", false)) {
                showSeatPanelActionDialog();
            }
        } else if (z6) {
            toggleTireCardIfEnabled();
        } else {
            runnable.run();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a1, code lost:
    
        if (r13.equals("FL") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00f4, code lost:
    
        if (r13.equals("RR") == false) goto L71;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void handleSeatClick(java.lang.String r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.handleSeatClick(java.lang.String, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideTireCard() {
        boolean z6 = this.hasRestoreView;
        boolean z7 = this.restoreWasTop;
        this.dynamicTireCardShowing = false;
        this.dynamicHandler.removeCallbacks(this.dynamicHideRunnable);
        this.tireCardShowing = false;
        this.hasRestoreView = false;
        this.tireCardHandler.removeCallbacks(this.tireCardTick);
        View view = this.layoutTireCard;
        if (view != null) {
            view.setVisibility(8);
        }
        a.a.m(this.prefs, PhoneNotificationRelayService.PREF_TIRE_CARD_SHOWING, false);
        if (z6) {
            restoreRecordedCarView(z7);
        } else {
            updateVisualCarModelVisibility();
        }
    }

    private boolean isActivityForeground() {
        Activity activity = this.activity;
        if (activity == null || activity.isFinishing() || this.activity.isDestroyed()) {
            return false;
        }
        return this.activity.hasWindowFocus();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isDoorVisualEffectActive(java.util.Map<java.lang.String, java.lang.Boolean> r5, java.util.Map<java.lang.String, java.lang.String> r6) {
        /*
            r4 = this;
            boolean r0 = r4.isDoorWindowDynamicEffectEnabled()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            r0 = 1
            if (r5 == 0) goto L57
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            java.lang.String r3 = "fl"
            java.lang.Object r3 = r5.get(r3)
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L55
            java.lang.String r3 = "fr"
            java.lang.Object r3 = r5.get(r3)
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L55
            java.lang.String r3 = "rl"
            java.lang.Object r3 = r5.get(r3)
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L55
            java.lang.String r3 = "rr"
            java.lang.Object r3 = r5.get(r3)
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L55
            java.lang.String r3 = "hood"
            java.lang.Object r3 = r5.get(r3)
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L55
            java.lang.String r3 = "trunk"
            java.lang.Object r5 = r5.get(r3)
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L57
        L55:
            r5 = r0
            goto L58
        L57:
            r5 = r1
        L58:
            if (r6 == 0) goto L83
            java.lang.String r2 = "sunroof"
            java.lang.Object r2 = r6.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "sunshade"
            java.lang.Object r6 = r6.get(r3)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r3 = "关"
            if (r2 == 0) goto L76
            boolean r2 = r2.contains(r3)
            if (r2 != 0) goto L76
            r2 = r0
            goto L77
        L76:
            r2 = r1
        L77:
            if (r6 == 0) goto L81
            boolean r6 = r6.contains(r3)
            if (r6 != 0) goto L81
            r6 = r0
            goto L85
        L81:
            r6 = r1
            goto L85
        L83:
            r6 = r1
            r2 = r6
        L85:
            if (r5 != 0) goto L8b
            if (r2 != 0) goto L8b
            if (r6 == 0) goto L8c
        L8b:
            r1 = r0
        L8c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.isDoorVisualEffectActive(java.util.Map, java.util.Map):boolean");
    }

    private boolean isDoorWindowDynamicEffectEnabled() {
        return this.prefs.getBoolean("car_control_door_window_dynamic_effect_enabled", true);
    }

    private boolean isDriveGear(String str) {
        if (str == null) {
            return false;
        }
        return str.toUpperCase().contains("D");
    }

    private boolean isDynamicLaneEnabled() {
        return this.prefs.getBoolean("car_control_dynamic_lane_enabled", true);
    }

    private boolean isMassageActive(String str) {
        return (str == null || "--".equals(str) || "关".equals(str)) ? false : true;
    }

    private static boolean isSeatMemoryApplyAllowed(Context context) {
        if (readModuleValue(MODULE_CAR_SETTING, 52) == 1) {
            return true;
        }
        int i7 = 0;
        if (context != null) {
            new Handler(Looper.getMainLooper()).post(new f1(context, i7));
        }
        return false;
    }

    private boolean isSeatPanelContentContainerAvailable() {
        ViewGroup viewGroup = this.seatPanelContentContainer;
        if (viewGroup == null) {
            return false;
        }
        if (viewGroup.getParent() != null) {
            return true;
        }
        CardView cardView = this.cardSeatControl;
        return cardView != null && cardView.getChildCount() > 0 && this.seatPanelContentContainer == this.cardSeatControl.getChildAt(0);
    }

    private boolean isTireCardDynamicEnabled() {
        return this.prefs.getBoolean(PhoneNotificationRelayService.PREF_TIRE_CARD_DYNAMIC_ENABLED, false);
    }

    private boolean isTireCardEnabled() {
        return this.prefs.getBoolean(PhoneNotificationRelayService.PREF_TIRE_CARD_ENABLED, false);
    }

    public static boolean isTopInfoSupportedInCurrentMode(SharedPreferences sharedPreferences, String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        if (str.startsWith("gnss_") || "phone_battery".equals(str) || sharedPreferences == null || !sharedPreferences.getBoolean("lion_sync_service_enabled", false)) {
            return true;
        }
        return LION_SUPPORTED_TOP_INFO_IDS.contains(str);
    }

    private boolean isWirelessChargingEnabled() {
        Map map = (Map) o4.c.c().F.d();
        return "开".equals(map != null ? (String) map.get(ACTION_WIRELESS_CHARGING) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$applyFanRowPositionByOrder$49(int[] iArr, int[] iArr2) {
        return Integer.compare(iArr[0], iArr2[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$bindFrontCarModelSeatPanelDialog$48(GestureDetector gestureDetector, View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$bindSeatPanelConfigLongPress$57(View view) {
        showSeatPanelActionDialog();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$bindTireCardGesture$46(GestureDetector gestureDetector, View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$handlePedestrianAlertClick$62(boolean z6) {
        int readModuleValue = readModuleValue(MODULE_NEW_ENERGY, 102);
        if (readModuleValue == 0 || readModuleValue == 1) {
            z6 = readModuleValue == 1;
        }
        o4.c.c().r(ACTION_PEDESTRIAN_ALERT, z6 ? "1" : "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleTrunkClick$64() {
        this.trunkCmdInFlight = false;
        updateTrunkIcon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleWirelessChargingClick$63() {
        View view = this.wirelessChargingContainer;
        if (view != null) {
            view.postDelayed(new r1(this, 4), 250L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initCarControlViews$0(View view) {
        showSeatPanelActionDialog();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initCarControlViews$1(View view) {
        showSeatPanelActionDialog();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$10(View view) {
        showHvacModeGrid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$11(View view) {
        handleHvacCirculationClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$12(View view) {
        handlePedestrianAlertClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$13(View view) {
        showFragranceControlDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$14(View view) {
        handleWiperSensitivityClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$15(View view) {
        handleLightHeightClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$16(View view) {
        handleSteeringHeatClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$17(View view) {
        handleWirelessChargingClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$18(View view) {
        handleTrunkClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$19(Map map) {
        updateTrunkIcon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initCarControlViews$2(View view) {
        showSeatPanelActionDialog();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$20(Integer num) {
        Runnable runnable = this.onCarDataUpdated;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$21(View view) {
        showSeatMassageDialog(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$22(View view) {
        showSeatMassageDialog(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$23(View view) {
        showSeatMassageDialog(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$24(View view) {
        showSeatMassageDialog(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$25(View view) {
        showSeatMemoryApplyConfirm(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$26(View view) {
        showSeatMemoryApplyConfirm(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$27(View view) {
        showSeatMemoryApplyConfirm(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$28(View view) {
        showSeatMemoryApplyConfirm(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$29(View view) {
        showSeatMemoryApplyConfirm(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$3(View view) {
        handleSeatClick("FL", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$30(View view) {
        showSeatMemoryApplyConfirm(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$31(View view) {
        showSeatMemorySaveConfirm(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$32(View view) {
        showSeatMemorySaveConfirm(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$33(View view) {
        showSeatMemorySaveConfirm(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$34(View view) {
        showSeatMemorySaveConfirm(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$35(View view) {
        showSeatMemorySaveConfirm(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$36(View view) {
        showSeatMemorySaveConfirm(3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
    
        if (r4 != 3) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ boolean lambda$initCarControlViews$37(android.view.View r3, android.view.MotionEvent r4) {
        /*
            int r4 = r4.getAction()
            r0 = 0
            r1 = 1
            if (r4 == 0) goto L19
            if (r4 == r1) goto L11
            r2 = 2
            if (r4 == r2) goto L19
            r1 = 3
            if (r4 == r1) goto L11
            goto L20
        L11:
            android.view.ViewParent r3 = r3.getParent()
            r3.requestDisallowInterceptTouchEvent(r0)
            goto L20
        L19:
            android.view.ViewParent r3 = r3.getParent()
            r3.requestDisallowInterceptTouchEvent(r1)
        L20:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.lambda$initCarControlViews$37(android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
    
        if (r4 != 3) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ boolean lambda$initCarControlViews$38(android.view.View r3, android.view.MotionEvent r4) {
        /*
            int r4 = r4.getAction()
            r0 = 0
            r1 = 1
            if (r4 == 0) goto L19
            if (r4 == r1) goto L11
            r2 = 2
            if (r4 == r2) goto L19
            r1 = 3
            if (r4 == r1) goto L11
            goto L20
        L11:
            android.view.ViewParent r3 = r3.getParent()
            r3.requestDisallowInterceptTouchEvent(r0)
            goto L20
        L19:
            android.view.ViewParent r3 = r3.getParent()
            r3.requestDisallowInterceptTouchEvent(r1)
        L20:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.lambda$initCarControlViews$38(android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$39(String str) {
        updateVisualCarModelVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$4(View view) {
        handleSeatClick("FR", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$40(String str) {
        updateVisualCarModelVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$41(Map map) {
        updateVisualCarModelVisibility();
        updateLightHeightIconTint(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$initCarControlViews$42(View view, View view2, View view3, View view4, View view5, View view6, View view7, View view8, Object obj) {
        Map map = (Map) o4.c.c().f7956x.d();
        Map map2 = (Map) o4.c.c().C.d();
        boolean isDoorWindowDynamicEffectEnabled = isDoorWindowDynamicEffectEnabled();
        boolean z6 = false;
        updateBreathingState(view, isDoorWindowDynamicEffectEnabled && map != null && Boolean.TRUE.equals(map.get("fl")));
        updateBreathingState(view2, isDoorWindowDynamicEffectEnabled && map != null && Boolean.TRUE.equals(map.get("fr")));
        updateBreathingState(view3, isDoorWindowDynamicEffectEnabled && map != null && Boolean.TRUE.equals(map.get("rl")));
        updateBreathingState(view4, isDoorWindowDynamicEffectEnabled && map != null && Boolean.TRUE.equals(map.get("rr")));
        updateBreathingState(view5, isDoorWindowDynamicEffectEnabled && map != null && Boolean.TRUE.equals(map.get("hood")));
        updateBreathingState(view6, isDoorWindowDynamicEffectEnabled && map != null && Boolean.TRUE.equals(map.get("trunk")));
        String str = map2 != null ? (String) map2.get("sunroof") : null;
        String str2 = map2 != null ? (String) map2.get("sunshade") : null;
        updateBreathingState(view7, (!isDoorWindowDynamicEffectEnabled || str == null || str.contains("关")) ? false : true);
        if (isDoorWindowDynamicEffectEnabled && str2 != null && !str2.contains("关")) {
            z6 = true;
        }
        updateBreathingState(view8, z6);
        updateVisualCarModelVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$43(Map map) {
        String str;
        int i7;
        if (map != null) {
            String str2 = (String) map.getOrDefault("auto", "关");
            String str3 = (String) map.getOrDefault("ac", "关");
            String str4 = (String) map.getOrDefault("power", "关");
            String str5 = (String) map.getOrDefault("temp", "--");
            String concat = (str5 == null || str5.trim().isEmpty() || "--".equals(str5)) ? s5.h0.FRAGMENT_ENCODE_SET : " ".concat(str5);
            if ("开".equals(str2)) {
                ImageView imageView = this.ivHvacAutoIcon;
                if (imageView != null) {
                    imageView.setColorFilter(-16711936, PorterDuff.Mode.SRC_IN);
                }
                TextView textView = this.tvHvacAuto;
                if (textView != null) {
                    textView.setText("AUTO" + concat);
                    this.tvHvacAuto.setTextColor(-16711936);
                }
            } else if ("开".equals(str3)) {
                ImageView imageView2 = this.ivHvacAutoIcon;
                if (imageView2 != null) {
                    imageView2.setColorFilter(-16711936, PorterDuff.Mode.SRC_IN);
                }
                TextView textView2 = this.tvHvacAuto;
                if (textView2 != null) {
                    textView2.setText("AC" + concat);
                    this.tvHvacAuto.setTextColor(-16711936);
                }
            } else if ("开".equals(str4)) {
                ImageView imageView3 = this.ivHvacAutoIcon;
                if (imageView3 != null) {
                    imageView3.setColorFilter(-16711936, PorterDuff.Mode.SRC_IN);
                }
                TextView textView3 = this.tvHvacAuto;
                if (textView3 != null) {
                    textView3.setText("空调开" + concat);
                    this.tvHvacAuto.setTextColor(-16711936);
                }
            } else {
                ImageView imageView4 = this.ivHvacAutoIcon;
                if (imageView4 != null) {
                    imageView4.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
                }
                TextView textView4 = this.tvHvacAuto;
                if (textView4 != null) {
                    textView4.setText("AUTO");
                    this.tvHvacAuto.setTextColor(-1);
                }
            }
            String str6 = (String) map.getOrDefault("mode", "吹风模式");
            TextView textView5 = this.tvHvacMode;
            if (textView5 != null) {
                textView5.setText(str6);
                this.tvHvacMode.setTextColor(-1);
            }
            ImageView imageView5 = this.ivHvacModeIcon;
            if (imageView5 != null) {
                imageView5.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
            }
            boolean equals = "内".equals((String) map.getOrDefault("circ", "外"));
            ImageView imageView6 = this.ivHvacCircIcon;
            if (imageView6 != null) {
                imageView6.setImageResource(equals ? g4.o.ic_hvac_circ_inner : g4.o.ic_hvac_circ_outer);
                this.ivHvacCircIcon.setColorFilter(equals ? -16711936 : -1, PorterDuff.Mode.SRC_IN);
            }
            TextView textView6 = this.tvHvacCirc;
            if (textView6 != null) {
                textView6.setText(equals ? "内循环" : "外循环");
                this.tvHvacCirc.setTextColor(equals ? -16711936 : -1);
            }
            boolean equals2 = "1".equals(map.getOrDefault(ACTION_PEDESTRIAN_ALERT, "0"));
            ImageView imageView7 = this.ivPedestrianAlertIcon;
            if (imageView7 != null) {
                imageView7.setColorFilter(equals2 ? -16711936 : -1, PorterDuff.Mode.SRC_IN);
            }
            TextView textView7 = this.tvPedestrianAlert;
            if (textView7 != null) {
                textView7.setTextColor(equals2 ? -16711936 : -1);
            }
            updateFragranceUi(map);
            boolean equals3 = "开".equals(map.getOrDefault(ACTION_STEERING_HEAT, "关"));
            ImageView imageView8 = this.ivSteeringHeatIcon;
            if (imageView8 != null) {
                imageView8.setColorFilter(equals3 ? COLOR_WIRELESS_ENABLED : -1, PorterDuff.Mode.SRC_IN);
            }
            TextView textView8 = this.tvSteeringHeat;
            if (textView8 != null) {
                textView8.setText("方向盘");
                this.tvSteeringHeat.setTextColor(equals3 ? COLOR_WIRELESS_ENABLED : -1);
            }
            updateWirelessChargingDisplay(map);
            try {
                int parseInt = Integer.parseInt((String) map.getOrDefault("fan", "0"));
                if (parseInt >= 1) {
                    SeekBar seekBar = this.sbHvacFan;
                    if (seekBar != null && seekBar.getProgress() != parseInt - 1) {
                        this.sbHvacFan.setProgress(i7);
                    }
                    TextView textView9 = this.tvHvacFanLabel;
                    if (textView9 != null) {
                        textView9.setText("风量: " + parseInt);
                    }
                } else {
                    SeekBar seekBar2 = this.sbHvacFan;
                    if (seekBar2 != null && seekBar2.getProgress() != 0) {
                        this.sbHvacFan.setProgress(0);
                    }
                    TextView textView10 = this.tvHvacFanLabel;
                    if (textView10 != null) {
                        textView10.setText("风量: 关闭");
                    }
                }
            } catch (NumberFormatException unused) {
            }
            try {
                int parseInt2 = Integer.parseInt((String) map.getOrDefault("rear_fan", "0"));
                SeekBar seekBar3 = this.sbHvacRearFan;
                if (seekBar3 != null && seekBar3.getProgress() != parseInt2) {
                    this.sbHvacRearFan.setProgress(parseInt2);
                }
                TextView textView11 = this.tvHvacRearFanLabel;
                if (textView11 != null) {
                    StringBuilder sb = new StringBuilder("后排风量: ");
                    if (parseInt2 == 0) {
                        str = "关闭";
                    } else {
                        str = parseInt2 + "档";
                    }
                    sb.append(str);
                    textView11.setText(sb.toString());
                }
            } catch (NumberFormatException unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$44(Map map) {
        String str;
        String str2;
        if (map != null) {
            if (this.isSeatComfortEnabled) {
                updateSingleSeatView("fl", true, (String) map.get(ACTION_HEAT_FL), this.ivSeatHeatFlIcon, this.tvSeatHeatFlLevel);
                updateSingleSeatView("fr", true, (String) map.get(ACTION_HEAT_FR), this.ivSeatHeatFrIcon, this.tvSeatHeatFrLevel);
                updateSingleSeatView("rl", true, (String) map.get(ACTION_HEAT_RL), this.ivSeatHeatRlIcon, this.tvSeatHeatRlLevel);
                updateSingleSeatView("rr", true, (String) map.get(ACTION_HEAT_RR), this.ivSeatHeatRrIcon, this.tvSeatHeatRrLevel);
                updateSingleSeatView("fl", false, (String) map.get(ACTION_VENT_FL), this.ivSeatVentFlIcon, this.tvSeatVentFlLevel);
                updateSingleSeatView("fr", false, (String) map.get(ACTION_VENT_FR), this.ivSeatVentFrIcon, this.tvSeatVentFrLevel);
            }
            String str3 = (String) map.get("msg_driver");
            String str4 = (String) map.get("msg_mode_driver");
            String str5 = (String) map.get("msg_pass");
            String str6 = (String) map.get("msg_mode_pass");
            boolean z6 = false;
            boolean z7 = (str3 == null || "--".equals(str3) || "关".equals(str3)) ? false : true;
            boolean z8 = (str4 == null || "--".equals(str4)) ? false : true;
            boolean z9 = (str5 == null || "--".equals(str5) || "关".equals(str5)) ? false : true;
            if (str6 != null && !"--".equals(str6)) {
                z6 = true;
            }
            boolean isMassageActive = isMassageActive(str3);
            boolean isMassageActive2 = isMassageActive(str5);
            if (!isMassageActive || (!z7 && !z8)) {
                str = "主驾";
            } else if (z7 && z8) {
                str = "主驾: " + str3 + " / " + str4;
            } else {
                str = z7 ? a.a.B("主驾: ", str3) : a.a.B("主驾: ", str4);
            }
            if (!isMassageActive2 || (!z9 && !z6)) {
                str2 = "副驾";
            } else if (z9 && z6) {
                str2 = "副驾: " + str5 + " / " + str6;
            } else {
                str2 = z9 ? a.a.B("副驾: ", str5) : a.a.B("副驾: ", str6);
            }
            TextView textView = this.tvSeatMassageDriverInline;
            if (textView != null) {
                textView.setText(str);
            }
            TextView textView2 = this.tvSeatMassagePassInline;
            if (textView2 != null) {
                textView2.setText(str2);
            }
            TextView textView3 = this.tvSeatMassageDriver;
            if (textView3 != null) {
                textView3.setText(str);
            }
            TextView textView4 = this.tvSeatMassagePass;
            if (textView4 != null) {
                textView4.setText(str2);
            }
            ImageView imageView = this.ivSeatMassageDriverInlineIcon;
            if (imageView != null) {
                imageView.setColorFilter(isMassageActive ? -6543440 : -1);
            }
            ImageView imageView2 = this.ivSeatMassageDriverIcon;
            if (imageView2 != null) {
                imageView2.setColorFilter(isMassageActive ? -6543440 : -1);
            }
            ImageView imageView3 = this.ivSeatMassagePassInlineIcon;
            if (imageView3 != null) {
                imageView3.setColorFilter(isMassageActive2 ? -6543440 : -1);
            }
            ImageView imageView4 = this.ivSeatMassagePassIcon;
            if (imageView4 != null) {
                imageView4.setColorFilter(isMassageActive2 ? -6543440 : -1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$5(View view) {
        handleSeatClick("RL", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$6(View view) {
        handleSeatClick("RR", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$7(View view) {
        handleSeatClick("FL", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$8(View view) {
        handleSeatClick("FR", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCarControlViews$9(View view) {
        handleHvacAutoClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshTireManually$47(boolean z6, boolean z7, String str) {
        if (z6) {
            Toast.makeText(this.activity, z7 ? "胎压已更新" : "胎压已更新（车辆离线，显示缓存）", 0).show();
        } else {
            Toast.makeText(this.activity, str, 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$refreshWirelessChargingState$65() {
        int readModuleValue = readModuleValue(MODULE_CAR_SETTING, 45);
        if (readModuleValue < 0) {
            return;
        }
        o4.c c7 = o4.c.c();
        c7.r("wireless_charging_raw", String.valueOf(readModuleValue));
        if (readModuleValue != 1) {
            if (readModuleValue == 0) {
                c7.r(ACTION_WIRELESS_CHARGING, "关");
                c7.r("wireless_charging_state", "0");
                c7.r("wireless_charging_state_raw", "0");
                return;
            }
            return;
        }
        c7.r(ACTION_WIRELESS_CHARGING, "开");
        int readModuleValue2 = readModuleValue(MODULE_CAR_SETTING, 44);
        if (readModuleValue2 >= 0) {
            c7.r("wireless_charging_state_raw", String.valueOf(readModuleValue2));
            if (readModuleValue2 < 0 || readModuleValue2 > 3) {
                return;
            }
            c7.r("wireless_charging_state", String.valueOf(readModuleValue2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFragranceControlDialog$73(int i7, androidx.appcompat.app.o[] oVarArr, View view) {
        if (i7 == 0) {
            sendHvacCommand(61, 1);
        } else if (i7 == -1) {
            sendHvacCommand(61, 2);
        } else {
            sendHvacCommand(62, i7);
        }
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFragranceControlDialog$74(int i7, androidx.appcompat.app.o[] oVarArr, View view) {
        switchFragranceType(i7);
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showFragranceControlDialog$75(androidx.appcompat.app.o oVar, DialogInterface dialogInterface) {
        if (oVar.getWindow() != null) {
            WindowManager.LayoutParams attributes = oVar.getWindow().getAttributes();
            attributes.width = -2;
            attributes.height = -2;
            oVar.getWindow().setAttributes(attributes);
            oVar.getWindow().setBackgroundDrawableResource(R.color.transparent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showHvacModeGrid$67(androidx.appcompat.app.o oVar, DialogInterface dialogInterface) {
        if (oVar.getWindow() != null) {
            WindowManager.LayoutParams attributes = oVar.getWindow().getAttributes();
            attributes.width = -2;
            attributes.height = -2;
            oVar.getWindow().setAttributes(attributes);
            oVar.getWindow().setBackgroundDrawableResource(R.color.transparent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showHvacModeGrid$68(int i7, androidx.appcompat.app.o oVar, View view) {
        sendHvacCommand(6, i7);
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSeatMassageDialog$70(boolean z6, int i7, androidx.appcompat.app.o[] oVarArr, View view) {
        sendSeatMassageLevel(z6, i7);
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSeatMassageDialog$71(boolean z6, int i7, androidx.appcompat.app.o[] oVarArr, View view) {
        sendSeatMassageMode(z6, i7);
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSeatMassageDialog$72(androidx.appcompat.app.o oVar, DialogInterface dialogInterface) {
        if (oVar.getWindow() != null) {
            WindowManager.LayoutParams attributes = oVar.getWindow().getAttributes();
            attributes.width = -2;
            attributes.height = -2;
            oVar.getWindow().setAttributes(attributes);
            oVar.getWindow().setBackgroundDrawableResource(R.color.transparent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSeatMemoryApplyConfirm$59(int i7, androidx.appcompat.app.o oVar, View view) {
        sendSeatMemoryApply(i7);
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSeatMemorySaveConfirm$61(int i7, androidx.appcompat.app.o oVar, View view) {
        saveSeatMemoryDirect(i7);
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSeatPanelActionDialog$50(Set set, String str, CompoundButton compoundButton, boolean z6) {
        if (z6) {
            set.add(str);
        } else {
            set.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSeatPanelActionDialog$52(androidx.appcompat.app.o oVar, Set set, View view) {
        oVar.dismiss();
        showSeatPanelOrderDialog(set);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSeatPanelActionDialog$53(Set set, androidx.appcompat.app.o oVar, View view) {
        Set<String> filterUnsupportedSeatPanelActions = filterUnsupportedSeatPanelActions(set);
        boolean z6 = filterUnsupportedSeatPanelActions.size() != set.size();
        saveSeatPanelActions(filterUnsupportedSeatPanelActions);
        if (z6) {
            ToastHelper.show(this.activity, "部分按钮受兼容模式或雄狮模式限制，已自动忽略");
        } else {
            ToastHelper.show(this.activity, "按钮显示已更新");
        }
        this.activity.sendBroadcast(new Intent("com.navi.tool.CAR_CONTROL_CARD_UPDATED"));
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSeatPanelOrderDialog$55(Spinner spinner, List list, Set set, androidx.appcompat.app.o oVar, View view) {
        saveSeatPanelPerRowCount(spinner.getSelectedItemPosition() + 4);
        saveSeatPanelActionOrder(mergeActionOrder(list));
        saveSeatPanelActions(set);
        this.activity.sendBroadcast(new Intent("com.navi.tool.CAR_CONTROL_CARD_UPDATED"));
        ToastHelper.show(this.activity, "按钮排序和每行数量已保存");
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showSeatPanelOrderDialog$56(RecyclerView recyclerView, androidx.recyclerview.widget.h1 h1Var) {
        recyclerView.requestLayout();
        h1Var.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showWirelessChargingAnimation$66() {
        AnimationDrawable animationDrawable = this.wirelessChargingAnimation;
        if (animationDrawable == null || animationDrawable.isRunning()) {
            return;
        }
        this.wirelessChargingAnimation.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$switchFragranceType$76() {
        ToastHelper.show(this.activity, "未找到该香型，请先添加香氛");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$switchFragranceType$77(int i7) {
        int findFragrancePosByTypeFromCache = findFragrancePosByTypeFromCache(i7);
        if (findFragrancePosByTypeFromCache <= 0) {
            int readHvacValueFromBus = readHvacValueFromBus(58);
            int readHvacValueFromBus2 = readHvacValueFromBus(59);
            int readHvacValueFromBus3 = readHvacValueFromBus(60);
            if (readHvacValueFromBus == i7) {
                findFragrancePosByTypeFromCache = 1;
            } else if (readHvacValueFromBus2 == i7) {
                findFragrancePosByTypeFromCache = 2;
            } else if (readHvacValueFromBus3 == i7) {
                findFragrancePosByTypeFromCache = 3;
            }
        }
        if (findFragrancePosByTypeFromCache <= 0) {
            this.activity.runOnUiThread(new r1(this, 0));
        } else {
            sendHvacCommand(48, findFragrancePosByTypeFromCache);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$updateCarControlCards$69(List list, CarControlAdapter.CardItem cardItem, CarControlAdapter.CardItem cardItem2) {
        int indexOf = list.indexOf(cardItem.type.name());
        int indexOf2 = list.indexOf(cardItem2.type.name());
        if (indexOf == -1) {
            indexOf = 999;
        }
        if (indexOf2 == -1) {
            indexOf2 = 999;
        }
        return Integer.compare(indexOf, indexOf2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateLightState$79(View view) {
        view.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$updateSeatComfort$45() {
        int readModuleValue = readModuleValue(MODULE_NEW_ENERGY, 102);
        boolean z6 = false;
        if ((readModuleValue == 0 || readModuleValue == 1) && readModuleValue == 1) {
            z6 = true;
        }
        o4.c.c().r(ACTION_PEDESTRIAN_ALERT, z6 ? "1" : "0");
    }

    private void maybeShowDynamicTireCard(o4.s sVar) {
        if (sVar != null && sVar.b() && isTireCardEnabled() && isTireCardDynamicEnabled() && this.isVisualCarModelEnabled && this.carControlContainer != null && this.layoutTireCard != null && isActivityForeground()) {
            if (this.tireCardShowing) {
                if (this.dynamicTireCardShowing) {
                    this.dynamicHandler.removeCallbacks(this.dynamicHideRunnable);
                    this.dynamicHandler.postDelayed(this.dynamicHideRunnable, tireCardDynamicSeconds() * 1000);
                    return;
                }
                return;
            }
            recordCurrentCarView();
            if (this.hasRestoreView) {
                this.dynamicTireCardShowing = true;
                showTireCardInternal();
                int tireCardDynamicSeconds = tireCardDynamicSeconds();
                this.dynamicHandler.removeCallbacks(this.dynamicHideRunnable);
                this.dynamicHandler.postDelayed(this.dynamicHideRunnable, tireCardDynamicSeconds * 1000);
            }
        }
    }

    private List<String> mergeActionOrder(List<String> list) {
        ArrayList arrayList = new ArrayList(list);
        for (String str : getSeatPanelActionOrder()) {
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private int minIndexInOrder(List<String> list, String[] strArr) {
        int i7 = androidx.appcompat.widget.u.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (String str : strArr) {
            int indexOf = list.indexOf(str);
            if (indexOf >= 0 && indexOf < i7) {
                i7 = indexOf;
            }
        }
        return i7;
    }

    private void moveViewToParent(View view, ViewGroup viewGroup) {
        ViewParent parent;
        if (view == null || viewGroup == null || (parent = view.getParent()) == viewGroup) {
            return;
        }
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        viewGroup.addView(view);
    }

    private void normalizeSeatPanelPoolDividerLayout(View view) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int i7 = (int) (this.activity.getResources().getDisplayMetrics().density * 8.0f);
            marginLayoutParams.topMargin = i7;
            marginLayoutParams.bottomMargin = i7;
            marginLayoutParams.setMarginStart(0);
            marginLayoutParams.setMarginEnd(0);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    private void normalizeSeatPanelPoolRowLayout(View view) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (marginLayoutParams.topMargin == 0 && marginLayoutParams.bottomMargin == 0) {
                return;
            }
            marginLayoutParams.topMargin = 0;
            marginLayoutParams.bottomMargin = 0;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    private void normalizeSubActionButtonLayout(View view) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.width = 0;
            layoutParams2.height = -2;
            layoutParams2.weight = 1.0f;
            layoutParams2.setMargins(0, 0, 0, 0);
            view.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPhoneTireChanged(o4.s sVar) {
        updateTireCard(sVar);
        if (sVar == null) {
            return;
        }
        if (!this.tireObserverReady) {
            this.tireObserverReady = true;
            this.lastTireDataTs = sVar.f8030l;
            return;
        }
        long j7 = sVar.f8030l;
        if (j7 > 0 && j7 > this.lastTireDataTs) {
            this.lastTireDataTs = j7;
            maybeShowDynamicTireCard(sVar);
        }
    }

    private int parseIntSafe(String str, int i7) {
        if (str == null) {
            return i7;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i7;
        }
    }

    private int parseLightHeightLevel(String str) {
        if (str == null) {
            return -1;
        }
        if (str.contains("0档")) {
            return 0;
        }
        if (str.contains("1档")) {
            return 1;
        }
        if (str.contains("2档")) {
            return 2;
        }
        return str.contains("3档") ? 3 : -1;
    }

    private int parseSpeedValue(String str) {
        if (str == null) {
            return 0;
        }
        String replaceAll = str.replaceAll("[^0-9]", s5.h0.FRAGMENT_ENCODE_SET);
        if (replaceAll.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(replaceAll);
        } catch (Exception unused) {
            return 0;
        }
    }

    private int parseWiperSensitivityLevel(String str) {
        if (str == null) {
            return -1;
        }
        if (str.contains("1档")) {
            return 1;
        }
        if (str.contains("2档")) {
            return 2;
        }
        if (str.contains("3档")) {
            return 3;
        }
        return str.contains("4档") ? 4 : -1;
    }

    private void prepareInlineSeatPanelPoolItems(boolean z6, boolean z7, boolean z8, boolean z9) {
        setSeatPanelInlineGroupVisibility(Arrays.asList(this.btnMem1Inline, this.btnMem2Inline, this.btnMem3Inline), z6);
        setSeatPanelInlineGroupVisibility(Arrays.asList(this.btnMemSave1Inline, this.btnMemSave2Inline, this.btnMemSave3Inline), z7);
        setSeatPanelInlineViewVisibility(this.btnSeatMassageDriverInline, z8);
        setSeatPanelInlineViewVisibility(this.btnSeatMassagePassInline, z9);
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

    private static int readModuleValue(int i7, int i8) {
        Bundle bundle;
        int[] intArray;
        if (CarInfoService.d() == null) {
            return -1;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("CMD_ID", i8);
        VDEvent vDEvent = new VDEvent(i7);
        vDEvent.f3190b = bundle2;
        VDEvent h7 = ((g2.a) CarInfoService.d()).h(vDEvent);
        if (h7 != null && (bundle = h7.f3190b) != null && (intArray = bundle.getIntArray("VALUE")) != null && intArray.length != 0) {
            return intArray[0];
        }
        return -1;
    }

    private void recordCurrentCarView() {
        View view = this.carControlContainer;
        if (view == null) {
            this.hasRestoreView = false;
            return;
        }
        View findViewById = view.findViewById(g4.p.layout_car_front);
        View findViewById2 = this.carControlContainer.findViewById(g4.p.layout_car_top);
        if (findViewById == null || findViewById2 == null) {
            this.hasRestoreView = false;
            return;
        }
        if (findViewById2.getVisibility() == 0) {
            this.hasRestoreView = true;
            this.restoreWasTop = true;
        } else if (findViewById.getVisibility() != 0) {
            this.hasRestoreView = false;
        } else {
            this.hasRestoreView = true;
            this.restoreWasTop = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTireManually() {
        Activity activity = this.activity;
        if (activity == null) {
            return;
        }
        int i7 = 0;
        if (!o4.z.e(activity)) {
            Toast.makeText(this.activity, "胎压账号未登录", 0).show();
            return;
        }
        Activity activity2 = this.activity;
        t1 t1Var = new t1(this, i7);
        AtomicBoolean atomicBoolean = TirePollingService.f4725g;
        if (activity2 == null) {
            return;
        }
        Context applicationContext = activity2.getApplicationContext();
        if (!o4.z.e(applicationContext)) {
            TirePollingService.b(t1Var, false, false, "胎压账号未登录");
            return;
        }
        if (System.currentTimeMillis() - TirePollingService.f4728j < 30000) {
            TirePollingService.b(t1Var, false, false, "操作频繁，请稍后再试");
            return;
        }
        AtomicBoolean atomicBoolean2 = TirePollingService.f4726h;
        if (!atomicBoolean2.compareAndSet(false, true)) {
            TirePollingService.b(t1Var, false, false, "正在拉取，请稍候");
            return;
        }
        TireAccount f7 = o4.z.f(applicationContext);
        if (f7 != null && f7.isUsable()) {
            o4.u.f8031e.c(applicationContext, new o4.x(f7, applicationContext, t1Var));
        } else {
            atomicBoolean2.set(false);
            TirePollingService.b(t1Var, false, false, "胎压账号未登录");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWirelessChargingState() {
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            return;
        }
        new Thread(new m0(1)).start();
    }

    private void removeFromParent(View view) {
        if (view == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    private void renderUnifiedSeatPanelLayout() {
        ViewGroup resolveSeatPanelContentContainer;
        if ((this.layoutSeatHeatRow instanceof ViewGroup) && (resolveSeatPanelContentContainer = resolveSeatPanelContentContainer()) != null) {
            List<ViewGroup> arrayList = new ArrayList<>();
            addSeatPanelRowContainer(arrayList, this.layoutSeatHeatRow);
            addSeatPanelRowContainer(arrayList, this.layoutHvacPrimaryRow);
            addSeatPanelRowContainer(arrayList, this.layoutHvacSubActionsRow);
            addSeatPanelRowContainer(arrayList, this.layoutHvacSubActionsRow2);
            addSeatPanelRowContainer(arrayList, this.layoutSeatMemoryInline);
            addSeatPanelRowContainer(arrayList, this.layoutSeatMemorySaveInline);
            addSeatPanelRowContainer(arrayList, this.layoutSeatMassageInline);
            List<View> asList = Arrays.asList(this.dividerSeatHeatVent, this.dividerHvacFan, this.dividerHvacSubActions, this.dividerSeatMemoryInline, this.dividerSeatMemorySaveInline, this.dividerSeatMassageInline);
            resetSeatPanelManagedBlocks(arrayList, asList);
            List<Object> buildSeatPanelRenderTokens = buildSeatPanelRenderTokens();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i7 = 0;
            for (Object obj : buildSeatPanelRenderTokens) {
                if (obj == SEAT_PANEL_FAN_MARKER) {
                    i7 = appendSeatPanelViewChunk(arrayList3, arrayList2, arrayList, i7);
                    arrayList3.clear();
                    View view = this.layoutHvacFanRow;
                    if (view != null && view.getVisibility() == 0) {
                        arrayList2.add(this.layoutHvacFanRow);
                    }
                } else if (obj == SEAT_PANEL_REAR_FAN_MARKER) {
                    i7 = appendSeatPanelViewChunk(arrayList3, arrayList2, arrayList, i7);
                    arrayList3.clear();
                    View view2 = this.layoutHvacRearFanRow;
                    if (view2 != null && view2.getVisibility() == 0) {
                        arrayList2.add(this.layoutHvacRearFanRow);
                    }
                } else if (obj instanceof View) {
                    arrayList3.add((View) obj);
                }
            }
            appendSeatPanelViewChunk(arrayList3, arrayList2, arrayList, i7);
            detachSeatPanelManagedBlocks(arrayList, asList);
            int i8 = 0;
            for (int i9 = 0; i9 < arrayList2.size(); i9++) {
                resolveSeatPanelContentContainer.addView((View) arrayList2.get(i9));
                if (i9 < arrayList2.size() - 1 && i8 < asList.size()) {
                    int i10 = i8 + 1;
                    View view3 = asList.get(i8);
                    if (view3 != null) {
                        view3.setVisibility(0);
                        resolveSeatPanelContentContainer.addView(view3);
                    }
                    i8 = i10;
                }
            }
            View view4 = this.layoutHvacFanRow;
            if (view4 != null && !arrayList2.contains(view4)) {
                this.layoutHvacFanRow.setVisibility(8);
            }
            View view5 = this.layoutHvacRearFanRow;
            if (view5 == null || arrayList2.contains(view5)) {
                return;
            }
            this.layoutHvacRearFanRow.setVisibility(8);
        }
    }

    private void resetSeatPanelManagedBlocks(List<ViewGroup> list, List<View> list2) {
        detachSeatPanelManagedBlocks(list, list2);
        for (ViewGroup viewGroup : list) {
            normalizeSeatPanelPoolRowLayout(viewGroup);
            viewGroup.removeAllViews();
            viewGroup.setVisibility(8);
        }
        for (View view : list2) {
            if (view != null) {
                normalizeSeatPanelPoolDividerLayout(view);
                view.setVisibility(8);
            }
        }
    }

    private String resolveCurrentFragranceType(Map<String, String> map) {
        int parseIntSafe = parseIntSafe(map.get("fragrance_effective_pos_raw"), -1);
        int parseIntSafe2 = parseIntSafe(map.get("fragrance_selected_pos_raw"), -1);
        if (parseIntSafe < 1 || parseIntSafe > 3) {
            parseIntSafe = parseIntSafe2;
        }
        if (parseIntSafe < 1 || parseIntSafe > 3) {
            return "香型";
        }
        int parseIntSafe3 = parseIntSafe(map.get("fragrance_pos1_type_raw"), -1);
        int parseIntSafe4 = parseIntSafe(map.get("fragrance_pos2_type_raw"), -1);
        int parseIntSafe5 = parseIntSafe(map.get("fragrance_pos3_type_raw"), -1);
        if (parseIntSafe != 1) {
            parseIntSafe3 = parseIntSafe == 2 ? parseIntSafe4 : parseIntSafe5;
        }
        return fragranceTypeText(parseIntSafe3);
    }

    private int resolveFirstRowCountForTwoRows(int i7, int i8) {
        if (i8 < 4) {
            i8 = 4;
        }
        if (i8 > 8) {
            i8 = 8;
        }
        return i7 <= i8 ? i7 : (i7 - i8 != 1 || i8 <= 4) ? i8 : i8 - 1;
    }

    private ViewGroup resolveSeatPanelContentContainer() {
        if (this.seatPanelContentContainer != null && isSeatPanelContentContainerAvailable()) {
            return this.seatPanelContentContainer;
        }
        View view = this.layoutSeatHeatRow;
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            this.seatPanelContentContainer = viewGroup;
            return viewGroup;
        }
        CardView cardView = this.cardSeatControl;
        if (cardView != null && cardView.getChildCount() > 0) {
            View childAt = this.cardSeatControl.getChildAt(0);
            if (childAt instanceof ViewGroup) {
                this.seatPanelContentContainer = (ViewGroup) childAt;
            }
        }
        return this.seatPanelContentContainer;
    }

    private void restoreRecordedCarView(boolean z6) {
        View view = this.carControlContainer;
        if (view == null) {
            return;
        }
        View findViewById = view.findViewById(g4.p.layout_car_model_container);
        View findViewById2 = this.carControlContainer.findViewById(g4.p.layout_car_front);
        View findViewById3 = this.carControlContainer.findViewById(g4.p.layout_car_top);
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        if (findViewById2 == null || findViewById3 == null) {
            updateVisualCarModelVisibility();
            return;
        }
        if (z6) {
            findViewById3.setVisibility(0);
            findViewById2.setVisibility(8);
            return;
        }
        findViewById2.setVisibility(0);
        findViewById3.setVisibility(8);
        View view2 = this.layoutLaneSpeed;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    private String safeValue(Object obj) {
        return obj == null ? "--" : obj.toString();
    }

    private void saveSeatMemoryDirect(int i7) {
        if (CarInfoService.d() == null) {
            ToastHelper.show(this.activity, "车联服务未连接");
            return;
        }
        sendSeatMemorySave(i7);
        ToastHelper.show(this.activity, "已保存位置" + i7);
    }

    private void saveSeatPanelActionOrder(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (findSeatPanelActionIndex(str) >= 0) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        this.prefs.edit().putString(KEY_SEAT_PANEL_ACTION_ORDER, sb.toString()).apply();
    }

    private void saveSeatPanelActions(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        for (String str : getSeatPanelActionOrder()) {
            if (set.contains(str)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str);
            }
        }
        this.prefs.edit().putString(KEY_SEAT_PANEL_ACTIONS, sb.toString()).apply();
    }

    private void saveSeatPanelPerRowCount(int i7) {
        if (i7 < 4) {
            i7 = 4;
        }
        if (i7 > 8) {
            i7 = 8;
        }
        a.a.l(this.prefs, KEY_SEAT_PANEL_PER_ROW_COUNT, i7);
    }

    private boolean sendCarSettingCommand(int i7, int i8) {
        try {
            if (CarInfoService.d() == null) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", i7);
            bundle.putIntArray("VALUE", new int[]{i8});
            VDEvent vDEvent = new VDEvent(MODULE_CAR_SETTING);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
            return true;
        } catch (Exception e7) {
            e7.printStackTrace();
            return false;
        }
    }

    private static boolean sendControlCommandInternal(int i7, int i8, int i9) {
        try {
            if (CarInfoService.d() == null) {
                return false;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", i8);
            bundle.putIntArray("VALUE", new int[]{i9});
            VDEvent vDEvent = new VDEvent(i7);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendHvacCommand(int i7, int i8) {
        if (!this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("CMD_ID", i7);
                bundle.putIntArray("VALUE", new int[]{i8});
                VDEvent vDEvent = new VDEvent(327690);
                vDEvent.f3190b = bundle;
                ((g2.a) CarInfoService.d()).i(vDEvent);
                return;
            } catch (Exception e7) {
                e7.printStackTrace();
                return;
            }
        }
        LionCarInfoService lionCarInfoService = LionCarInfoService.f4712m;
        if (lionCarInfoService == null || lionCarInfoService.f4713a == null) {
            return;
        }
        try {
            Bundle bundle2 = new Bundle();
            int i9 = 4;
            if (i7 != 4) {
                if (i7 == 100) {
                    i9 = 1;
                } else if (i7 == 18) {
                    i8 = i8 == 2 ? 0 : 1;
                    i9 = LionCarInfoService.HVAC_AUTO;
                } else if (i7 == 6) {
                    bundle2.putInt("status", 1);
                    i9 = 11;
                } else if (i7 == 12) {
                    bundle2.putInt("status", 1);
                    i9 = LionCarInfoService.HVAC_WIND_LEVEL;
                } else {
                    i9 = -1;
                }
            }
            if (i9 != -1) {
                SyncBusEvent syncBusEvent = new SyncBusEvent(i9);
                bundle2.putInt("data", i8);
                syncBusEvent.f4485c = bundle2;
                ((f4.a) lionCarInfoService.f4713a).j(syncBusEvent);
            }
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    private void sendSeatCommand(int i7, int i8, boolean z6, boolean z7) {
        if (!this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("CMD_ID", i7);
                bundle.putIntArray("VALUE", new int[]{i8});
                VDEvent vDEvent = new VDEvent(MODULE_CAR_SETTING);
                vDEvent.f3190b = bundle;
                ((g2.a) CarInfoService.d()).i(vDEvent);
                return;
            } catch (Exception e7) {
                e7.printStackTrace();
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
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    private void sendSeatMassageLevel(boolean z6, int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            int i8 = z6 ? z1.c.ADD_FLOAT_2ADDR : z1.c.INVOKE_INTERFACE;
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", i8);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(MODULE_CAR_SETTING);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void sendSeatMassageMode(boolean z6, int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            int i8 = z6 ? z1.c.USHR_LONG_2ADDR : 115;
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", i8);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(MODULE_CAR_SETTING);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void sendSeatMemoryApply(int i7) {
        try {
            if (CarInfoService.d() != null && isSeatMemoryApplyAllowed(this.activity)) {
                Bundle bundle = new Bundle();
                bundle.putInt("CMD_ID", 52);
                bundle.putIntArray("VALUE", new int[]{i7});
                VDEvent vDEvent = new VDEvent(MODULE_CAR_SETTING);
                vDEvent.f3190b = bundle;
                ((g2.a) CarInfoService.d()).i(vDEvent);
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void sendSeatMemorySave(int i7) {
        try {
            if (CarInfoService.d() == null) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("CMD_ID", 54);
            bundle.putIntArray("VALUE", new int[]{i7});
            VDEvent vDEvent = new VDEvent(MODULE_CAR_SETTING);
            vDEvent.f3190b = bundle;
            ((g2.a) CarInfoService.d()).i(vDEvent);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void sendVrCommandWithOperation(String str, String str2, String str3) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.desaysv.vrservice", "com.desaysv.vrservice.vendor.iflytek.service.AdapterStartService"));
            intent.putExtra("data", String.format("{\"normal_text\":\"\",\"operation\":\"%s\",\"service\":\"carControl\",\"semantic\":{\"slots\":{\"name\":\"%s\",\"mode\":\"%s\"}}}", str3, str, str2));
            this.activity.startForegroundService(intent);
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private static boolean setBrakeFeelModeInternal(int i7) {
        if (CarInfoService.d() == null) {
            return false;
        }
        boolean sendControlCommandInternal = sendControlCommandInternal(MODULE_NEW_ENERGY, 23, 2);
        sleepQuietly(600L);
        if (CarInfoService.d() == null) {
            return false;
        }
        return sendControlCommandInternal && sendControlCommandInternal(MODULE_NEW_ENERGY, 24, i7);
    }

    private static boolean setEpbTargetState(boolean z6) {
        if (CarInfoService.d() == null) {
            return false;
        }
        int i7 = z6 ? 2 : 1;
        if (readModuleValue(MODULE_NEW_ENERGY, 85) == z6) {
            return true;
        }
        if (!sendControlCommandInternal(MODULE_NEW_ENERGY, 85, i7)) {
            return false;
        }
        sleepQuietly(180L);
        int readModuleValue = readModuleValue(MODULE_NEW_ENERGY, 85);
        return !(readModuleValue == 0 || readModuleValue == 1) || readModuleValue == z6;
    }

    private static boolean setRadarTargetState(boolean z6) {
        if (CarInfoService.d() == null) {
            return false;
        }
        if (readModuleValue(MODULE_RADAR, 17) == z6) {
            return true;
        }
        if (!sendControlCommandInternal(MODULE_RADAR, 17, 1)) {
            return false;
        }
        sleepQuietly(180L);
        int readModuleValue = readModuleValue(MODULE_RADAR, 17);
        return !(readModuleValue == 0 || readModuleValue == 1) || readModuleValue == z6;
    }

    private void setSeatPanelInlineGroupVisibility(List<View> list, boolean z6) {
        Iterator<View> it = list.iterator();
        while (it.hasNext()) {
            setSeatPanelInlineViewVisibility(it.next(), z6);
        }
    }

    private void setSeatPanelInlineViewVisibility(View view, boolean z6) {
        if (view == null) {
            return;
        }
        view.setVisibility(z6 ? 0 : 8);
        if (z6) {
            return;
        }
        removeFromParent(view);
    }

    private static boolean setSuspensionModeInternal(int i7) {
        if (CarInfoService.d() == null) {
            return false;
        }
        boolean sendControlCommandInternal = sendControlCommandInternal(MODULE_CAR_SETTING, 189, 1);
        sleepQuietly(600L);
        if (CarInfoService.d() == null) {
            return false;
        }
        return sendControlCommandInternal && sendControlCommandInternal(MODULE_CAR_SETTING, 190, i7);
    }

    private void setTireWheel(TextView textView, TextView textView2, int i7, int i8) {
        String str = "--";
        if (textView != null) {
            textView.setText(i7 > 0 ? String.format(Locale.US, "%.2f", Float.valueOf(i7 / 100.0f)) : "--");
            textView.setTextColor(tireValueColor(i7));
        }
        if (textView2 != null) {
            if (i8 != Integer.MIN_VALUE) {
                str = i8 + "℃";
            }
            textView2.setText(str);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.appcompat.app.o[], java.io.Serializable] */
    private void showFragranceControlDialog() {
        float f7;
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            ToastHelper.show(this.activity, "当前模式不支持此选项");
            return;
        }
        int i7 = 1;
        ?? r22 = new androidx.appcompat.app.o[1];
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        int i8 = (int) (this.activity.getResources().getDisplayMetrics().density * 16.0f);
        linearLayout.setPadding(i8, i8, i8, i8);
        linearLayout.setBackgroundResource(g4.o.bg_hvac_mode_dialog);
        TextView textView = new TextView(this.activity);
        textView.setText("香氛控制");
        int i9 = -1;
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setTypeface(null, 1);
        int i10 = 17;
        textView.setGravity(17);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(this.activity);
        textView2.setText("挡位");
        textView2.setTextColor(-1996488705);
        textView2.setTextSize(16.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 10.0f);
        textView2.setLayoutParams(layoutParams);
        linearLayout.addView(textView2);
        GridLayout gridLayout = new GridLayout(this.activity);
        gridLayout.setColumnCount(4);
        gridLayout.setUseDefaultMargins(true);
        gridLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(gridLayout);
        String[] strArr = {"AUTO", "1档", "2档", "3档", "4档", "5档", "关闭"};
        int i11 = 7;
        int[] iArr = {0, 1, 2, 3, 4, 5, -1};
        int i12 = 0;
        while (true) {
            f7 = 22.0f;
            if (i12 >= i11) {
                break;
            }
            int i13 = iArr[i12];
            Button button = new Button(this.activity);
            button.setText(strArr[i12]);
            button.setTextColor(-1);
            button.setTextSize(22.0f);
            button.setGravity(i10);
            button.setIncludeFontPadding(true);
            button.setSingleLine(true);
            int i14 = (int) (this.activity.getResources().getDisplayMetrics().density * 8.0f);
            button.setPadding(button.getPaddingLeft(), i14, button.getPaddingRight(), i14);
            button.setBackgroundResource(g4.o.bg_app_name_pill);
            GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams();
            layoutParams2.width = (int) (this.activity.getResources().getDisplayMetrics().density * 120.0f);
            layoutParams2.height = (int) (this.activity.getResources().getDisplayMetrics().density * 52.0f);
            layoutParams2.setMargins(6, 6, 6, 6);
            button.setLayoutParams(layoutParams2);
            button.setOnClickListener(new l1(this, i13, r22, 0));
            gridLayout.addView(button);
            i12++;
            i10 = 17;
            i11 = 7;
        }
        TextView textView3 = new TextView(this.activity);
        textView3.setText("香味类型");
        textView3.setTextColor(-1996488705);
        textView3.setTextSize(16.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 14.0f);
        textView3.setLayoutParams(layoutParams3);
        linearLayout.addView(textView3);
        GridLayout gridLayout2 = new GridLayout(this.activity);
        int i15 = 3;
        gridLayout2.setColumnCount(3);
        gridLayout2.setUseDefaultMargins(true);
        gridLayout2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(gridLayout2);
        String[] strArr2 = {"清新自然", "治愈舒缓", "运动激情"};
        int[] iArr2 = {1, 2, 3};
        int i16 = 0;
        while (i16 < i15) {
            int i17 = iArr2[i16];
            Button button2 = new Button(this.activity);
            button2.setText(strArr2[i16]);
            button2.setTextColor(i9);
            button2.setTextSize(f7);
            button2.setGravity(17);
            button2.setIncludeFontPadding(true);
            button2.setSingleLine(true);
            int i18 = (int) (this.activity.getResources().getDisplayMetrics().density * 8.0f);
            button2.setPadding(button2.getPaddingLeft(), i18, button2.getPaddingRight(), i18);
            button2.setBackgroundResource(g4.o.bg_app_name_pill);
            GridLayout.LayoutParams layoutParams4 = new GridLayout.LayoutParams();
            layoutParams4.width = (int) (this.activity.getResources().getDisplayMetrics().density * 120.0f);
            layoutParams4.height = (int) (this.activity.getResources().getDisplayMetrics().density * 52.0f);
            layoutParams4.setMargins(6, 6, 6, 6);
            button2.setLayoutParams(layoutParams4);
            button2.setOnClickListener(new l1(this, i17, r22, i7));
            gridLayout2.addView(button2);
            i16++;
            i15 = 3;
            i9 = -1;
            f7 = 22.0f;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.activity, R.style.Theme.DeviceDefault.Dialog);
        nVar.u(linearLayout);
        androidx.appcompat.app.o e7 = nVar.e();
        int i19 = 0;
        r22[0] = e7;
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        e7.setOnShowListener(new m1(e7, i19));
        e7.show();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v17, types: [androidx.appcompat.app.n] */
    /* JADX WARN: Type inference failed for: r10v0, types: [android.view.View, android.view.ViewGroup, android.widget.LinearLayout] */
    /* JADX WARN: Type inference failed for: r11v1, types: [android.view.View, android.view.ViewGroup, android.widget.GridLayout] */
    /* JADX WARN: Type inference failed for: r12v4, types: [android.view.ViewGroup] */
    private void showSeatMassageDialog(final boolean z6) {
        float f7;
        int i7;
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            ToastHelper.show(this.activity, "当前车联服务模式不支持此选项");
            return;
        }
        boolean z7 = true;
        final androidx.appcompat.app.o[] oVarArr = new androidx.appcompat.app.o[1];
        ?? linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        int i8 = (int) (this.activity.getResources().getDisplayMetrics().density * 16.0f);
        linearLayout.setPadding(i8, i8, i8, i8);
        linearLayout.setBackgroundResource(g4.o.bg_hvac_mode_dialog);
        TextView textView = new TextView(this.activity);
        textView.setText(z6 ? "主驾按摩" : "副驾按摩");
        int i9 = -1;
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setTypeface(null, 1);
        int i10 = 17;
        textView.setGravity(17);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(this.activity);
        textView2.setText("挡位");
        textView2.setTextColor(-1996488705);
        textView2.setTextSize(16.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 10.0f);
        textView2.setLayoutParams(layoutParams);
        linearLayout.addView(textView2);
        GridLayout gridLayout = new GridLayout(this.activity);
        int i11 = 4;
        gridLayout.setColumnCount(4);
        gridLayout.setUseDefaultMargins(true);
        gridLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(gridLayout);
        String[] strArr = {"3档", "2档", "1档", "关闭"};
        int i12 = 3;
        int[] iArr = {4, 3, 2, 1};
        int i13 = 0;
        while (true) {
            f7 = 22.0f;
            i7 = 6;
            if (i13 >= i11) {
                break;
            }
            final int i14 = iArr[i13];
            Button button = new Button(this.activity);
            button.setText(strArr[i13]);
            button.setTextColor(i9);
            button.setTextSize(22.0f);
            button.setGravity(i10);
            button.setIncludeFontPadding(true);
            button.setSingleLine(true);
            int i15 = (int) (this.activity.getResources().getDisplayMetrics().density * 8.0f);
            button.setPadding(button.getPaddingLeft(), i15, button.getPaddingRight(), i15);
            button.setBackgroundResource(g4.o.bg_app_name_pill);
            GridLayout.LayoutParams layoutParams2 = new GridLayout.LayoutParams();
            layoutParams2.width = (int) (this.activity.getResources().getDisplayMetrics().density * 120.0f);
            layoutParams2.height = (int) (this.activity.getResources().getDisplayMetrics().density * 52.0f);
            layoutParams2.setMargins(6, 6, 6, 6);
            button.setLayoutParams(layoutParams2);
            final int i16 = 0;
            ?? r12 = gridLayout;
            button.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.p1

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ DesktopAssistantCarControlHelper f5389b;

                {
                    this.f5389b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i17 = i16;
                    boolean z8 = z6;
                    DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5389b;
                    androidx.appcompat.app.o[] oVarArr2 = oVarArr;
                    int i18 = i14;
                    switch (i17) {
                        case 0:
                            desktopAssistantCarControlHelper.lambda$showSeatMassageDialog$70(z8, i18, oVarArr2, view);
                            break;
                        default:
                            desktopAssistantCarControlHelper.lambda$showSeatMassageDialog$71(z8, i18, oVarArr2, view);
                            break;
                    }
                }
            });
            r12.addView(button);
            i13++;
            gridLayout = r12;
            i12 = 3;
            i11 = 4;
            i9 = -1;
            i10 = 17;
        }
        int i17 = i12;
        TextView textView3 = new TextView(this.activity);
        textView3.setText("模式");
        textView3.setTextColor(-1996488705);
        textView3.setTextSize(16.0f);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 14.0f);
        textView3.setLayoutParams(layoutParams3);
        linearLayout.addView(textView3);
        ?? gridLayout2 = new GridLayout(this.activity);
        gridLayout2.setColumnCount(i17);
        gridLayout2.setUseDefaultMargins(true);
        gridLayout2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(gridLayout2);
        String[] strArr2 = {"波浪", "猫步", "交叉", "舒缓", "跳跃"};
        int[] iArr2 = {1, 2, i17, 4, 5};
        int i18 = 0;
        while (i18 < 5) {
            final int i19 = iArr2[i18];
            Button button2 = new Button(this.activity);
            button2.setText(strArr2[i18]);
            button2.setTextColor(-1);
            button2.setTextSize(f7);
            button2.setGravity(17);
            button2.setIncludeFontPadding(z7);
            button2.setSingleLine(z7);
            int i20 = (int) (this.activity.getResources().getDisplayMetrics().density * 8.0f);
            button2.setPadding(button2.getPaddingLeft(), i20, button2.getPaddingRight(), i20);
            button2.setBackgroundResource(g4.o.bg_app_name_pill);
            GridLayout.LayoutParams layoutParams4 = new GridLayout.LayoutParams();
            layoutParams4.width = (int) (this.activity.getResources().getDisplayMetrics().density * 120.0f);
            layoutParams4.height = (int) (this.activity.getResources().getDisplayMetrics().density * 52.0f);
            layoutParams4.setMargins(i7, i7, i7, i7);
            button2.setLayoutParams(layoutParams4);
            final int i21 = 1;
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.p1

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ DesktopAssistantCarControlHelper f5389b;

                {
                    this.f5389b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i172 = i21;
                    boolean z8 = z6;
                    DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5389b;
                    androidx.appcompat.app.o[] oVarArr2 = oVarArr;
                    int i182 = i19;
                    switch (i172) {
                        case 0:
                            desktopAssistantCarControlHelper.lambda$showSeatMassageDialog$70(z8, i182, oVarArr2, view);
                            break;
                        default:
                            desktopAssistantCarControlHelper.lambda$showSeatMassageDialog$71(z8, i182, oVarArr2, view);
                            break;
                    }
                }
            });
            gridLayout2.addView(button2);
            i18++;
            f7 = 22.0f;
            i7 = 6;
            z7 = true;
        }
        ?? nVar = new androidx.appcompat.app.n(this.activity, R.style.Theme.DeviceDefault.Dialog);
        nVar.u(linearLayout);
        androidx.appcompat.app.o e7 = nVar.e();
        oVarArr[0] = e7;
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        e7.setOnShowListener(new m1(e7, 2));
        e7.show();
    }

    private void showSeatMemoryApplyConfirm(int i7) {
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        int i8 = (int) (this.activity.getResources().getDisplayMetrics().density * 16.0f);
        linearLayout.setPadding(i8, i8, i8, i8);
        linearLayout.setBackgroundResource(g4.o.bg_hvac_mode_dialog);
        TextView textView = new TextView(this.activity);
        textView.setText("确认应用座椅记忆");
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setTypeface(null, 1);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(this.activity);
        textView2.setText("确定应用配置" + i7 + " 吗？");
        textView2.setTextColor(-1);
        textView2.setTextSize(16.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 12.0f);
        textView2.setLayoutParams(layoutParams);
        linearLayout.addView(textView2);
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        linearLayout2.setOrientation(0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 16.0f);
        linearLayout2.setLayoutParams(layoutParams2);
        Button button = new Button(this.activity);
        button.setText("取消");
        button.setTextColor(-1);
        button.setTextSize(20.0f);
        button.setBackgroundResource(g4.o.bg_app_name_pill);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, (int) (this.activity.getResources().getDisplayMetrics().density * 44.0f), 1.0f);
        layoutParams3.rightMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 6.0f);
        button.setLayoutParams(layoutParams3);
        Button button2 = new Button(this.activity);
        button2.setText("确认");
        button2.setTextColor(-1);
        button2.setTextSize(20.0f);
        button2.setBackgroundResource(g4.o.bg_app_name_pill);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, (int) (this.activity.getResources().getDisplayMetrics().density * 44.0f), 1.0f);
        layoutParams4.leftMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 6.0f);
        button2.setLayoutParams(layoutParams4);
        linearLayout2.addView(button);
        linearLayout2.addView(button2);
        linearLayout.addView(linearLayout2);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.activity, R.style.Theme.DeviceDefault.Dialog);
        nVar.u(linearLayout);
        androidx.appcompat.app.o e7 = nVar.e();
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        int i9 = 2;
        button.setOnClickListener(new o1(e7, i9));
        button2.setOnClickListener(new n1(this, i7, e7, i9));
        e7.show();
    }

    private void showSeatMemorySaveConfirm(int i7) {
        LinearLayout linearLayout = new LinearLayout(this.activity);
        int i8 = 1;
        linearLayout.setOrientation(1);
        int i9 = (int) (this.activity.getResources().getDisplayMetrics().density * 16.0f);
        linearLayout.setPadding(i9, i9, i9, i9);
        linearLayout.setBackgroundResource(g4.o.bg_hvac_mode_dialog);
        TextView textView = new TextView(this.activity);
        textView.setText("确认保存座椅记忆");
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setTypeface(null, 1);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(this.activity);
        textView2.setText("确定保存到配置" + i7 + " 吗？");
        textView2.setTextColor(-1);
        textView2.setTextSize(16.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 12.0f);
        textView2.setLayoutParams(layoutParams);
        linearLayout.addView(textView2);
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        int i10 = 0;
        linearLayout2.setOrientation(0);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 16.0f);
        linearLayout2.setLayoutParams(layoutParams2);
        Button button = new Button(this.activity);
        button.setText("取消");
        button.setTextColor(-1);
        button.setTextSize(20.0f);
        button.setBackgroundResource(g4.o.bg_app_name_pill);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, (int) (this.activity.getResources().getDisplayMetrics().density * 44.0f), 1.0f);
        layoutParams3.rightMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 6.0f);
        button.setLayoutParams(layoutParams3);
        Button button2 = new Button(this.activity);
        button2.setText("确认");
        button2.setTextColor(-1);
        button2.setTextSize(20.0f);
        button2.setBackgroundResource(g4.o.bg_app_name_pill);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, (int) (this.activity.getResources().getDisplayMetrics().density * 44.0f), 1.0f);
        layoutParams4.leftMargin = (int) (this.activity.getResources().getDisplayMetrics().density * 6.0f);
        button2.setLayoutParams(layoutParams4);
        linearLayout2.addView(button);
        linearLayout2.addView(button2);
        linearLayout.addView(linearLayout2);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.activity, R.style.Theme.DeviceDefault.Dialog);
        nVar.u(linearLayout);
        androidx.appcompat.app.o e7 = nVar.e();
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        button.setOnClickListener(new o1(e7, i10));
        button2.setOnClickListener(new n1(this, i7, e7, i8));
        e7.show();
    }

    private void showSeatPanelActionDialog() {
        ensureSeatPanelActionsInitialized();
        HashSet hashSet = new HashSet(getSelectedSeatPanelActions());
        List<String> seatPanelActionOrder = getSeatPanelActionOrder();
        float f7 = this.activity.getResources().getDisplayMetrics().density;
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        int i7 = (int) (16.0f * f7);
        linearLayout.setPadding(i7, i7, i7, i7);
        linearLayout.setBackgroundResource(g4.o.bg_hvac_mode_dialog);
        TextView textView = new TextView(this.activity);
        textView.setText("选择要显示的按钮");
        int i8 = -1;
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setTypeface(null, 1);
        linearLayout.addView(textView);
        ScrollView scrollView = new ScrollView(this.activity) { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.8
            @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
            public void onMeasure(int i9, int i10) {
                super.onMeasure(i9, View.MeasureSpec.makeMeasureSpec((int) (DesktopAssistantCarControlHelper.this.activity.getResources().getDisplayMetrics().heightPixels * 0.55f), Integer.MIN_VALUE));
            }
        };
        int i9 = -2;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = (int) (12.0f * f7);
        scrollView.setLayoutParams(layoutParams);
        scrollView.setFillViewport(true);
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        linearLayout2.setOrientation(1);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout2.setPadding(0, 0, 0, i7);
        int i10 = (int) (6.0f * f7);
        int i11 = (int) (50.0f * f7);
        LinearLayout linearLayout3 = new LinearLayout(this.activity);
        linearLayout3.setOrientation(0);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout2.addView(linearLayout3);
        Iterator<String> it = seatPanelActionOrder.iterator();
        int i12 = 0;
        while (it.hasNext()) {
            String next = it.next();
            if (i12 == 5) {
                LinearLayout linearLayout4 = new LinearLayout(this.activity);
                linearLayout4.setOrientation(0);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i8, i9);
                layoutParams2.topMargin = i11;
                linearLayout4.setLayoutParams(layoutParams2);
                linearLayout2.addView(linearLayout4);
                linearLayout3 = linearLayout4;
                i12 = 0;
            }
            CheckBox checkBox = new CheckBox(this.activity);
            checkBox.setText(getSeatPanelActionLabel(next));
            checkBox.setChecked(hashSet.contains(next));
            checkBox.setTextSize(20.0f);
            checkBox.setTextColor(i8);
            checkBox.setButtonTintList(ColorStateList.valueOf(i8));
            checkBox.setOnCheckedChangeListener(new v(1, hashSet, next));
            checkBox.setSingleLine(true);
            checkBox.setEllipsize(TextUtils.TruncateAt.END);
            checkBox.setGravity(16);
            LinearLayout linearLayout5 = new LinearLayout(this.activity);
            linearLayout5.setOrientation(0);
            linearLayout5.setGravity(17);
            Iterator<String> it2 = it;
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            if (i12 > 0) {
                layoutParams3.leftMargin = i10;
            }
            linearLayout5.addView(checkBox, new LinearLayout.LayoutParams(-2, -2));
            linearLayout3.addView(linearLayout5, layoutParams3);
            i12++;
            it = it2;
            i8 = -1;
            i9 = -2;
        }
        scrollView.addView(linearLayout2);
        linearLayout.addView(scrollView);
        LinearLayout linearLayout6 = new LinearLayout(this.activity);
        linearLayout6.setOrientation(0);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.topMargin = (int) (32.0f * f7);
        linearLayout6.setLayoutParams(layoutParams4);
        Button button = new Button(this.activity);
        button.setText("取消");
        button.setTextColor(-1);
        button.setTextSize(20.0f);
        button.setBackgroundResource(g4.o.bg_app_name_pill);
        button.setBackgroundTintList(ColorStateList.valueOf(-14326805));
        int i13 = (int) (f7 * 44.0f);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, i13, 1.0f);
        layoutParams5.leftMargin = i10;
        layoutParams5.rightMargin = i10;
        button.setLayoutParams(layoutParams5);
        Button button2 = new Button(this.activity);
        button2.setText("保存");
        button2.setTextColor(-1);
        button2.setTextSize(20.0f);
        button2.setBackgroundResource(g4.o.bg_app_name_pill);
        button2.setBackgroundTintList(ColorStateList.valueOf(-14326805));
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, i13, 1.0f);
        layoutParams6.leftMargin = i10;
        button2.setLayoutParams(layoutParams6);
        Button button3 = new Button(this.activity);
        button3.setText("按钮排序");
        button3.setTextColor(-1);
        button3.setTextSize(20.0f);
        button3.setBackgroundResource(g4.o.bg_app_name_pill);
        button3.setBackgroundTintList(ColorStateList.valueOf(-14326805));
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(0, i13, 1.0f);
        layoutParams7.rightMargin = i10;
        button3.setLayoutParams(layoutParams7);
        linearLayout6.addView(button3);
        linearLayout6.addView(button);
        linearLayout6.addView(button2);
        linearLayout.addView(linearLayout6);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.activity, R.style.Theme.DeviceDefault.Dialog);
        nVar.u(linearLayout);
        androidx.appcompat.app.o e7 = nVar.e();
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        button.setOnClickListener(new o1(e7, 1));
        button3.setOnClickListener(new q1(this, e7, hashSet));
        button2.setOnClickListener(new q1(this, hashSet, e7));
        e7.show();
    }

    private void showSeatPanelOrderDialog(Set<String> set) {
        Set<String> filterUnsupportedSeatPanelActions = filterUnsupportedSeatPanelActions(new HashSet(set));
        List<String> sortedSelectedActions = getSortedSelectedActions(filterUnsupportedSeatPanelActions);
        if (sortedSelectedActions.isEmpty()) {
            ToastHelper.show(this.activity, "请先选择至少一个按钮");
            return;
        }
        if (sortedSelectedActions.size() == 1) {
            ToastHelper.show(this.activity, "仅一个按钮，无需排序");
            return;
        }
        final ArrayList arrayList = new ArrayList(sortedSelectedActions);
        final float f7 = this.activity.getResources().getDisplayMetrics().density;
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        int i7 = (int) (16.0f * f7);
        linearLayout.setPadding(i7, i7, i7, i7);
        linearLayout.setBackgroundResource(g4.o.bg_hvac_mode_dialog);
        TextView textView = new TextView(this.activity);
        textView.setText("按钮排序（长按拖动）");
        textView.setTextColor(-1);
        textView.setTextSize(20.0f);
        textView.setTypeface(null, 1);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(this.activity);
        textView2.setText("仅显示已选按钮，拖动调整顺序");
        textView2.setTextColor(-855638017);
        textView2.setTextSize(14.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = (int) (8.0f * f7);
        textView2.setLayoutParams(layoutParams);
        linearLayout.addView(textView2);
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = (int) (10.0f * f7);
        layoutParams2.bottomMargin = (int) (4.0f * f7);
        linearLayout2.setLayoutParams(layoutParams2);
        TextView textView3 = new TextView(this.activity);
        textView3.setText("每行排序数量");
        textView3.setTextColor(-1);
        textView3.setTextSize(15.0f);
        textView3.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout2.addView(textView3);
        Spinner spinner = new Spinner(this.activity);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.activity, R.layout.simple_spinner_item, new String[]{"4", "5", "6", "7", "8"}) { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.9
            @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
            public View getDropDownView(int i8, View view, ViewGroup viewGroup) {
                View dropDownView = super.getDropDownView(i8, view, viewGroup);
                if (dropDownView instanceof TextView) {
                    TextView textView4 = (TextView) dropDownView;
                    textView4.setTextColor(-1);
                    textView4.setBackgroundColor(-872415232);
                }
                return dropDownView;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i8, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i8, view, viewGroup);
                if (view2 instanceof TextView) {
                    TextView textView4 = (TextView) view2;
                    textView4.setTextColor(-1);
                    textView4.setTextSize(16.0f);
                }
                return view2;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setSelection(getSeatPanelPerRowCount() - 4, false);
        spinner.setLayoutParams(new LinearLayout.LayoutParams((int) (92.0f * f7), -2));
        linearLayout2.addView(spinner);
        linearLayout.addView(linearLayout2);
        RecyclerView recyclerView = new RecyclerView(this.activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        int i8 = (int) (this.activity.getResources().getDisplayMetrics().heightPixels * 0.45f);
        int i9 = (int) (220.0f * f7);
        if (i8 < i9) {
            i8 = i9;
        }
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, i8);
        int i10 = (int) (12.0f * f7);
        layoutParams3.topMargin = i10;
        layoutParams3.bottomMargin = i10;
        recyclerView.setLayoutParams(layoutParams3);
        linearLayout.addView(recyclerView);
        final androidx.recyclerview.widget.h1 h1Var = new androidx.recyclerview.widget.h1() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.10
            @Override // androidx.recyclerview.widget.h1
            public int getItemCount() {
                return arrayList.size();
            }

            @Override // androidx.recyclerview.widget.h1
            public void onBindViewHolder(androidx.recyclerview.widget.j2 j2Var, int i11) {
                ((TextView) ((LinearLayout) j2Var.itemView).getChildAt(0)).setText((i11 + 1) + ". " + DesktopAssistantCarControlHelper.this.getSeatPanelActionLabel((String) arrayList.get(i11)));
            }

            @Override // androidx.recyclerview.widget.h1
            public androidx.recyclerview.widget.j2 onCreateViewHolder(ViewGroup viewGroup, int i11) {
                LinearLayout linearLayout3 = new LinearLayout(DesktopAssistantCarControlHelper.this.activity);
                linearLayout3.setOrientation(0);
                linearLayout3.setBackgroundResource(g4.o.bg_app_name_pill);
                linearLayout3.setGravity(16);
                float f8 = f7;
                int i12 = (int) (f8 * 14.0f);
                int i13 = (int) (f8 * 8.0f);
                linearLayout3.setPadding(i12, i13, i12, i13);
                androidx.recyclerview.widget.s1 s1Var = new androidx.recyclerview.widget.s1(-1, -2);
                int i14 = (int) (f7 * 4.0f);
                s1Var.setMargins(0, i14, 0, i14);
                linearLayout3.setLayoutParams(s1Var);
                TextView textView4 = new TextView(DesktopAssistantCarControlHelper.this.activity);
                textView4.setTextColor(-1);
                textView4.setTextSize(18.0f);
                textView4.setSingleLine(true);
                textView4.setEllipsize(TextUtils.TruncateAt.END);
                textView4.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
                linearLayout3.addView(textView4);
                TextView textView5 = new TextView(DesktopAssistantCarControlHelper.this.activity);
                textView5.setText("长按拖动");
                textView5.setTextColor(-855638017);
                textView5.setTextSize(14.0f);
                linearLayout3.addView(textView5);
                return new androidx.recyclerview.widget.j2(linearLayout3) { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.10.1
                };
            }
        };
        recyclerView.setAdapter(h1Var);
        new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.n0(3, 0) { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.11
            @Override // androidx.recyclerview.widget.l0
            public int interpolateOutOfBoundsScroll(RecyclerView recyclerView2, int i11, int i12, int i13, long j7) {
                if (i12 == 0) {
                    return 0;
                }
                int i14 = i12 > 0 ? 1 : -1;
                int i15 = (int) (f7 * 24.0f);
                int abs = (int) ((Math.abs(i12) / Math.max(1, i11)) * i15);
                if (abs < 6) {
                    abs = 6;
                }
                if (abs <= i15) {
                    i15 = abs;
                }
                return i14 * i15;
            }

            @Override // androidx.recyclerview.widget.l0
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                int adapterPosition = j2Var.getAdapterPosition();
                int adapterPosition2 = j2Var2.getAdapterPosition();
                if (adapterPosition < 0 || adapterPosition2 < 0 || adapterPosition >= arrayList.size() || adapterPosition2 >= arrayList.size()) {
                    return false;
                }
                Collections.swap(arrayList, adapterPosition, adapterPosition2);
                h1Var.notifyItemMoved(adapterPosition, adapterPosition2);
                h1Var.notifyItemRangeChanged(Math.min(adapterPosition, adapterPosition2), Math.abs(adapterPosition - adapterPosition2) + 1);
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i11) {
            }
        }).f(recyclerView);
        LinearLayout linearLayout3 = new LinearLayout(this.activity);
        linearLayout3.setOrientation(0);
        linearLayout3.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        Button button = new Button(this.activity);
        button.setText("取消");
        button.setTextColor(-1);
        button.setTextSize(20.0f);
        button.setBackgroundResource(g4.o.bg_app_name_pill);
        int i11 = (int) (44.0f * f7);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(0, i11, 1.0f);
        int i12 = (int) (6.0f * f7);
        layoutParams4.rightMargin = i12;
        button.setLayoutParams(layoutParams4);
        Button button2 = new Button(this.activity);
        button2.setText("保存");
        button2.setTextColor(-1);
        button2.setTextSize(20.0f);
        button2.setBackgroundResource(g4.o.bg_app_name_pill);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(0, i11, 1.0f);
        layoutParams5.leftMargin = i12;
        button2.setLayoutParams(layoutParams5);
        linearLayout3.addView(button);
        linearLayout3.addView(button2);
        linearLayout.addView(linearLayout3);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.activity, R.style.Theme.DeviceDefault.Dialog);
        nVar.u(linearLayout);
        androidx.appcompat.app.o e7 = nVar.e();
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        button.setOnClickListener(new o1(e7, 3));
        button2.setOnClickListener(new r3(this, spinner, arrayList, filterUnsupportedSeatPanelActions, e7));
        e7.show();
        if (e7.getWindow() != null) {
            WindowManager.LayoutParams attributes = e7.getWindow().getAttributes();
            attributes.width = Math.min((int) (this.activity.getResources().getDisplayMetrics().widthPixels * 0.9f), (int) (f7 * 560.0f));
            attributes.height = -2;
            e7.getWindow().setAttributes(attributes);
        }
        recyclerView.post(new z0(8, recyclerView, h1Var));
    }

    private void showStaticLane() {
        AnimationDrawable animationDrawable = this.laneFrameAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
            this.laneFrameAnimation = null;
        }
        this.laneAnimationFrameDuration = -1;
        this.laneAnimationRunning = false;
        if (!this.laneStaticShown || this.imgLaneAnim.getDrawable() == null) {
            this.imgLaneAnim.setImageResource(g4.o.lane_a1);
        }
        this.laneStaticShown = true;
        this.imgLaneAnim.setVisibility(0);
    }

    private void showTireCard() {
        if (this.layoutTireCard == null || this.carControlContainer == null || !isTireCardEnabled()) {
            return;
        }
        this.dynamicHandler.removeCallbacks(this.dynamicHideRunnable);
        this.dynamicTireCardShowing = false;
        recordCurrentCarView();
        showTireCardInternal();
        a.a.m(this.prefs, PhoneNotificationRelayService.PREF_TIRE_CARD_SHOWING, true);
    }

    private void showTireCardInternal() {
        this.tireCardShowing = true;
        View findViewById = this.carControlContainer.findViewById(g4.p.layout_car_model_container);
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        this.layoutTireCard.setVisibility(0);
        View findViewById2 = this.carControlContainer.findViewById(g4.p.layout_car_front);
        View findViewById3 = this.carControlContainer.findViewById(g4.p.layout_car_top);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        if (findViewById3 != null) {
            findViewById3.setVisibility(8);
        }
        View view = this.layoutLaneSpeed;
        if (view != null) {
            view.setVisibility(8);
        }
        stopLaneAnimation();
        updateTireCard((o4.s) o4.c.c().f7948p.d());
        this.tireCardHandler.removeCallbacks(this.tireCardTick);
        this.tireCardHandler.postDelayed(this.tireCardTick, t1.f.T_MS_MINUTE);
    }

    private void showWirelessChargingAnimation() {
        if (this.ivWirelessChargingIcon == null) {
            return;
        }
        if (!"charging".equals(this.wirelessChargingDisplayMode) || this.wirelessChargingAnimation == null) {
            stopWirelessChargingAnimation();
            AnimationDrawable animationDrawable = new AnimationDrawable();
            animationDrawable.setOneShot(false);
            for (int i7 : WIRELESS_CHARGING_FRAME_RES) {
                Drawable drawable = this.activity.getResources().getDrawable(i7);
                if (drawable != null) {
                    animationDrawable.addFrame(drawable, 100);
                }
            }
            this.wirelessChargingAnimation = animationDrawable;
            this.ivWirelessChargingIcon.setImageDrawable(animationDrawable);
            this.wirelessChargingDisplayMode = "charging";
        }
        this.ivWirelessChargingIcon.setColorFilter(-13312, PorterDuff.Mode.SRC_IN);
        this.ivWirelessChargingIcon.post(new r1(this, 2));
    }

    private void showWirelessChargingStatic(int i7, int i8, String str) {
        if (this.ivWirelessChargingIcon == null) {
            return;
        }
        if (!str.equals(this.wirelessChargingDisplayMode) || this.wirelessChargingAnimation != null || this.ivWirelessChargingIcon.getDrawable() == null) {
            stopWirelessChargingAnimation();
            this.ivWirelessChargingIcon.setImageResource(i7);
            this.wirelessChargingDisplayMode = str;
        }
        this.ivWirelessChargingIcon.setColorFilter(i8, PorterDuff.Mode.SRC_IN);
    }

    private static void sleepQuietly(long j7) {
        try {
            Thread.sleep(j7);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    private void startLaneAnimation(int i7) {
        int laneFrameDuration = getLaneFrameDuration(i7);
        if (this.laneFrameAnimation == null || this.laneAnimationFrameDuration != laneFrameDuration || this.laneStaticShown) {
            AnimationDrawable animationDrawable = new AnimationDrawable();
            this.laneFrameAnimation = animationDrawable;
            animationDrawable.setOneShot(false);
            for (int i8 : LANE_FRAME_RES) {
                Drawable drawable = this.activity.getResources().getDrawable(i8);
                if (drawable != null) {
                    this.laneFrameAnimation.addFrame(drawable, laneFrameDuration);
                }
            }
            this.laneAnimationFrameDuration = laneFrameDuration;
            this.imgLaneAnim.setImageDrawable(this.laneFrameAnimation);
        }
        AnimationDrawable animationDrawable2 = this.laneFrameAnimation;
        if (animationDrawable2 != null && !animationDrawable2.isRunning()) {
            this.laneFrameAnimation.start();
        }
        this.laneStaticShown = false;
        AnimationDrawable animationDrawable3 = this.laneFrameAnimation;
        this.laneAnimationRunning = animationDrawable3 != null && animationDrawable3.isRunning();
        this.imgLaneAnim.setVisibility(0);
    }

    private void stopLaneAnimation() {
        AnimationDrawable animationDrawable = this.laneFrameAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        updateLaneSpeedDisplay(0, false);
        ImageView imageView = this.imgLaneAnim;
        if (imageView != null) {
            imageView.setVisibility(8);
            this.imgLaneAnim.setImageDrawable(null);
        }
        this.laneFrameAnimation = null;
        this.laneAnimationFrameDuration = -1;
        this.laneAnimationRunning = false;
        this.laneStaticShown = false;
    }

    private void stopWirelessChargingAnimation() {
        AnimationDrawable animationDrawable = this.wirelessChargingAnimation;
        if (animationDrawable != null) {
            animationDrawable.stop();
            this.wirelessChargingAnimation = null;
        }
    }

    private void switchFragranceType(int i7) {
        new Thread(new h0.n(i7, 6, this)).start();
    }

    private int tireCardDynamicSeconds() {
        return Math.max(10, Math.min(60, this.prefs.getInt(PhoneNotificationRelayService.PREF_TIRE_CARD_DYNAMIC_SECONDS, 30)));
    }

    private int tireValueColor(int i7) {
        if (i7 <= 0) {
            return -6381922;
        }
        float f7 = i7 / 100.0f;
        if (f7 < 2.0f) {
            return -1754827;
        }
        return f7 > 2.8f ? -291840 : -1;
    }

    private void toggleTireCardIfEnabled() {
        if (isTireCardEnabled()) {
            if (this.tireCardShowing) {
                hideTireCard();
            } else {
                showTireCard();
            }
        }
    }

    private void updateBreathingState(View view, boolean z6) {
        if (view == null) {
            return;
        }
        ObjectAnimator objectAnimator = (ObjectAnimator) view.getTag(g4.p.tag_animator);
        if (objectAnimator != null) {
            objectAnimator.cancel();
            view.setTag(g4.p.tag_animator, null);
        }
        if (!z6) {
            view.setVisibility(4);
        } else {
            view.setAlpha(1.0f);
            view.setVisibility(0);
        }
    }

    private void updateFragranceUi(Map<String, String> map) {
        String str;
        if (this.ivHvacFragranceIcon == null || this.tvHvacFragrance == null) {
            return;
        }
        int parseIntSafe = parseIntSafe(map.get("fragrance_mode_raw"), -1);
        int parseIntSafe2 = parseIntSafe(map.get("fragrance_level_raw"), -1);
        String resolveCurrentFragranceType = resolveCurrentFragranceType(map);
        if (parseIntSafe == 0) {
            this.ivHvacFragranceIcon.setColorFilter(-6543440, PorterDuff.Mode.SRC_IN);
            this.tvHvacFragrance.setText("AUTO/" + resolveCurrentFragranceType);
            this.tvHvacFragrance.setTextColor(-1);
            return;
        }
        if (parseIntSafe != 2) {
            this.ivHvacFragranceIcon.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
            this.tvHvacFragrance.setText("香氛");
            this.tvHvacFragrance.setTextColor(-1);
            return;
        }
        this.ivHvacFragranceIcon.setColorFilter(-6543440, PorterDuff.Mode.SRC_IN);
        if (parseIntSafe2 < 1 || parseIntSafe2 > 5) {
            str = "--";
        } else {
            str = parseIntSafe2 + "档";
        }
        this.tvHvacFragrance.setText(str + "/" + resolveCurrentFragranceType);
        this.tvHvacFragrance.setTextColor(-1);
    }

    private void updateInlineSeatMemoryRows(boolean z6, boolean z7) {
        int i7;
        int i8;
        View view = this.layoutSeatMemoryInline;
        if (view instanceof ViewGroup) {
            View view2 = this.layoutSeatMemorySaveInline;
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                ViewGroup viewGroup2 = (ViewGroup) view2;
                List<View> asList = Arrays.asList(this.btnMem1Inline, this.btnMem2Inline, this.btnMem3Inline);
                List<View> asList2 = Arrays.asList(this.btnMemSave1Inline, this.btnMemSave2Inline, this.btnMemSave3Inline);
                ArrayList arrayList = new ArrayList();
                for (View view3 : asList) {
                    if (view3 != null) {
                        if (z6) {
                            view3.setVisibility(0);
                            normalizeSubActionButtonLayout(view3);
                            removeFromParent(view3);
                            arrayList.add(view3);
                        } else {
                            view3.setVisibility(8);
                            removeFromParent(view3);
                        }
                    }
                }
                for (View view4 : asList2) {
                    if (view4 != null) {
                        if (z7) {
                            view4.setVisibility(0);
                            normalizeSubActionButtonLayout(view4);
                            removeFromParent(view4);
                            arrayList.add(view4);
                        } else {
                            view4.setVisibility(8);
                            removeFromParent(view4);
                        }
                    }
                }
                View view5 = this.layoutHvacSubActionsRow;
                ViewGroup viewGroup3 = null;
                ViewGroup viewGroup4 = view5 instanceof ViewGroup ? (ViewGroup) view5 : null;
                View view6 = this.layoutHvacSubActionsRow2;
                ViewGroup viewGroup5 = view6 instanceof ViewGroup ? (ViewGroup) view6 : null;
                if (viewGroup5 != null && viewGroup5.getVisibility() == 0) {
                    viewGroup3 = viewGroup5;
                } else if (viewGroup4 != null && viewGroup4.getVisibility() == 0) {
                    viewGroup3 = viewGroup4;
                }
                if (viewGroup3 != null && !arrayList.isEmpty()) {
                    for (int seatPanelPerRowCount = getSeatPanelPerRowCount() - viewGroup3.getChildCount(); seatPanelPerRowCount > 0 && !arrayList.isEmpty(); seatPanelPerRowCount--) {
                        moveViewToParent((View) arrayList.remove(0), viewGroup3);
                    }
                    if (viewGroup5 != null && viewGroup3 == viewGroup5 && viewGroup5.getChildCount() > 0) {
                        viewGroup5.setVisibility(0);
                    }
                    if (viewGroup4 != null && viewGroup3 == viewGroup4 && viewGroup4.getChildCount() > 0) {
                        viewGroup4.setVisibility(0);
                    }
                }
                if (z6) {
                    i7 = 0;
                    for (View view7 : asList) {
                        if (view7 != null && arrayList.contains(view7)) {
                            moveViewToParent(view7, viewGroup);
                            i7++;
                        }
                    }
                } else {
                    i7 = 0;
                }
                if (z7) {
                    ViewGroup viewGroup6 = z6 ? viewGroup : viewGroup2;
                    i8 = 0;
                    for (View view8 : asList2) {
                        if (view8 != null && arrayList.contains(view8)) {
                            moveViewToParent(view8, viewGroup6);
                            if (viewGroup6 == viewGroup) {
                                i7++;
                            } else {
                                i8++;
                            }
                        }
                    }
                } else {
                    i8 = 0;
                }
                viewGroup.setVisibility(i7 > 0 ? 0 : 8);
                viewGroup2.setVisibility(i8 <= 0 ? 8 : 0);
            }
        }
    }

    private void updateLaneAnimation() {
        View view = this.carControlContainer;
        if (view == null || this.imgLaneAnim == null) {
            return;
        }
        View findViewById = view.findViewById(g4.p.layout_car_top);
        boolean z6 = false;
        boolean z7 = findViewById != null && findViewById.getVisibility() == 0;
        if (!this.isVisualCarModelEnabled || !z7 || !isDynamicLaneEnabled()) {
            updateLaneSpeedDisplay(0, false);
            updateTopGearDisplay(null, false);
            stopLaneAnimation();
            return;
        }
        boolean isDoorVisualEffectActive = isDoorVisualEffectActive((Map) o4.c.c().f7956x.d(), (Map) o4.c.c().C.d());
        int parseSpeedValue = parseSpeedValue((String) o4.c.c().f7938f.d());
        String str = (String) o4.c.c().f7939g.d();
        if (isDynamicLaneEnabled() && !isDoorVisualEffectActive && (parseSpeedValue > 0 || isDriveGear(str))) {
            z6 = true;
        }
        updateTopCarLayoutHeight(findViewById, z6);
        updateLaneSpeedDisplay(parseSpeedValue, true);
        updateTopGearDisplay(str, true);
        if (isDoorVisualEffectActive) {
            stopLaneAnimation();
        } else if (parseSpeedValue <= 0) {
            showStaticLane();
        } else {
            startLaneAnimation(parseSpeedValue);
        }
    }

    private void updateLaneSpeedDisplay(int i7, boolean z6) {
        TextView textView;
        View view = this.layoutLaneSpeed;
        if (view == null || (textView = this.tvLaneSpeedValue) == null || this.tvLaneSpeedUnit == null) {
            return;
        }
        if (!z6) {
            view.setVisibility(8);
            return;
        }
        textView.setText(String.valueOf(Math.max(i7, 0)));
        this.tvLaneSpeedUnit.setText("km/h");
        this.layoutLaneSpeed.setVisibility(0);
    }

    private void updateLightHeightDisplay(int i7) {
        TextView textView = this.tvLightHeight;
        if (textView != null) {
            if (i7 < 0 || i7 > 3) {
                textView.setText("高度");
            } else {
                textView.setText("高度" + i7 + "档" + (i7 == 0 ? "(H)" : i7 == 3 ? "(L)" : s5.h0.FRAGMENT_ENCODE_SET));
            }
            this.tvLightHeight.setTextColor(-1);
        }
        updateLightHeightIconTint((Map) o4.c.c().f7958z.d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLightHeightDisplay(String str) {
        updateLightHeightDisplay(parseLightHeightLevel(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void updateLightHeightIconTint(java.util.Map<java.lang.String, java.lang.Boolean> r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L1e
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.String r1 = "parking"
            java.lang.Object r1 = r3.get(r1)
            boolean r1 = r0.equals(r1)
            if (r1 != 0) goto L1c
            java.lang.String r1 = "high"
            java.lang.Object r3 = r3.get(r1)
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L1e
        L1c:
            r3 = 1
            goto L1f
        L1e:
            r3 = 0
        L1f:
            android.widget.ImageView r0 = r2.ivLightHeightIcon
            if (r0 == 0) goto L2e
            if (r3 == 0) goto L28
            r3 = -13312(0xffffffffffffcc00, float:NaN)
            goto L29
        L28:
            r3 = -1
        L29:
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC_IN
            r0.setColorFilter(r3, r1)
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.updateLightHeightIconTint(java.util.Map):void");
    }

    private void updateLightState(View view, boolean z6) {
        if (view == null) {
            return;
        }
        if (!z6) {
            if (view.getVisibility() == 0) {
                view.animate().alpha(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(300L).withEndAction(new f4(6, view)).start();
            }
        } else if (view.getVisibility() != 0 || view.getAlpha() < 1.0f) {
            view.setVisibility(0);
            view.animate().alpha(1.0f).setDuration(300L).start();
        }
    }

    private void updatePrimaryActionsWithOrderAndWidth() {
        View view = this.layoutSeatHeatRow;
        if (view instanceof ViewGroup) {
            View view2 = this.layoutHvacPrimaryRow;
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                ViewGroup viewGroup2 = (ViewGroup) view2;
                ArrayList arrayList = new ArrayList();
                for (String str : getSeatPanelActionOrder()) {
                    View view3 = ACTION_HEAT_FL.equals(str) ? this.seatHeatFlContainer : ACTION_HEAT_FR.equals(str) ? this.seatHeatFrContainer : ACTION_HEAT_RL.equals(str) ? this.seatHeatRlContainer : ACTION_HEAT_RR.equals(str) ? this.seatHeatRrContainer : ACTION_VENT_FL.equals(str) ? this.seatVentFlContainer : ACTION_VENT_FR.equals(str) ? this.seatVentFrContainer : ACTION_HVAC_AUTO.equals(str) ? this.hvacAutoContainer : ACTION_HVAC_MODE.equals(str) ? this.hvacModeContainer : null;
                    if (view3 != null && view3.getVisibility() == 0 && !arrayList.contains(view3)) {
                        arrayList.add(view3);
                    }
                }
                View view4 = this.seatHeatFlContainer;
                if (view4 != null && view4.getVisibility() == 0 && !arrayList.contains(this.seatHeatFlContainer)) {
                    arrayList.add(this.seatHeatFlContainer);
                }
                View view5 = this.seatHeatFrContainer;
                if (view5 != null && view5.getVisibility() == 0 && !arrayList.contains(this.seatHeatFrContainer)) {
                    arrayList.add(this.seatHeatFrContainer);
                }
                View view6 = this.seatHeatRlContainer;
                if (view6 != null && view6.getVisibility() == 0 && !arrayList.contains(this.seatHeatRlContainer)) {
                    arrayList.add(this.seatHeatRlContainer);
                }
                View view7 = this.seatHeatRrContainer;
                if (view7 != null && view7.getVisibility() == 0 && !arrayList.contains(this.seatHeatRrContainer)) {
                    arrayList.add(this.seatHeatRrContainer);
                }
                View view8 = this.seatVentFlContainer;
                if (view8 != null && view8.getVisibility() == 0 && !arrayList.contains(this.seatVentFlContainer)) {
                    arrayList.add(this.seatVentFlContainer);
                }
                View view9 = this.seatVentFrContainer;
                if (view9 != null && view9.getVisibility() == 0 && !arrayList.contains(this.seatVentFrContainer)) {
                    arrayList.add(this.seatVentFrContainer);
                }
                View view10 = this.hvacAutoContainer;
                if (view10 != null && view10.getVisibility() == 0 && !arrayList.contains(this.hvacAutoContainer)) {
                    arrayList.add(this.hvacAutoContainer);
                }
                View view11 = this.hvacModeContainer;
                if (view11 != null && view11.getVisibility() == 0 && !arrayList.contains(this.hvacModeContainer)) {
                    arrayList.add(this.hvacModeContainer);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    View view12 = (View) it.next();
                    normalizeSubActionButtonLayout(view12);
                    removeFromParent(view12);
                }
                int resolveFirstRowCountForTwoRows = resolveFirstRowCountForTwoRows(arrayList.size(), getSeatPanelPerRowCount());
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    View view13 = (View) arrayList.get(i7);
                    if (i7 < resolveFirstRowCountForTwoRows) {
                        moveViewToParent(view13, viewGroup);
                    } else {
                        moveViewToParent(view13, viewGroup2);
                    }
                }
                boolean z6 = !arrayList.isEmpty();
                boolean z7 = arrayList.size() > resolveFirstRowCountForTwoRows;
                viewGroup.setVisibility(z6 ? 0 : 8);
                viewGroup2.setVisibility(z7 ? 0 : 8);
                View view14 = this.dividerSeatHeatVent;
                if (view14 != null) {
                    view14.setVisibility(z7 ? 0 : 8);
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0056, code lost:
    
        if (r6.equals("fr") == false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void updateSingleSeatView(java.lang.String r6, boolean r7, java.lang.String r8, android.widget.ImageView r9, android.widget.TextView r10) {
        /*
            r5 = this;
            r0 = 1
            java.lang.String r1 = "关"
            r2 = 0
            if (r8 == 0) goto L16
            java.lang.String r3 = "--"
            boolean r3 = r8.equals(r3)
            if (r3 != 0) goto L16
            boolean r3 = r8.equals(r1)
            if (r3 != 0) goto L16
            r3 = r0
            goto L18
        L16:
            r8 = r1
            r3 = r2
        L18:
            r4 = -1
            if (r3 == 0) goto L2b
            if (r7 == 0) goto L24
            r7 = -769226(0xfffffffffff44336, float:NaN)
            r9.setColorFilter(r7)
            goto L2e
        L24:
            r7 = -14575885(0xffffffffff2196f3, float:-2.1478948E38)
            r9.setColorFilter(r7)
            goto L2e
        L2b:
            r9.setColorFilter(r4)
        L2e:
            r6.getClass()
            int r7 = r6.hashCode()
            switch(r7) {
                case 3270: goto L59;
                case 3276: goto L50;
                case 3642: goto L45;
                case 3648: goto L3a;
                default: goto L38;
            }
        L38:
            r0 = r4
            goto L63
        L3a:
            java.lang.String r7 = "rr"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L43
            goto L38
        L43:
            r0 = 3
            goto L63
        L45:
            java.lang.String r7 = "rl"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L4e
            goto L38
        L4e:
            r0 = 2
            goto L63
        L50:
            java.lang.String r7 = "fr"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L63
            goto L38
        L59:
            java.lang.String r7 = "fl"
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L62
            goto L38
        L62:
            r0 = r2
        L63:
            switch(r0) {
                case 0: goto L72;
                case 1: goto L6f;
                case 2: goto L6c;
                case 3: goto L69;
                default: goto L66;
            }
        L66:
            java.lang.String r6 = ""
            goto L74
        L69:
            java.lang.String r6 = "右后"
            goto L74
        L6c:
            java.lang.String r6 = "左后"
            goto L74
        L6f:
            java.lang.String r6 = "副驾"
            goto L74
        L72:
            java.lang.String r6 = "主驾"
        L74:
            boolean r7 = r8.equals(r1)
            if (r7 == 0) goto L81
            r10.setText(r6)
            r10.setTextColor(r4)
            goto L9b
        L81:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r6)
            java.lang.String r6 = " "
            r7.append(r6)
            r7.append(r8)
            java.lang.String r6 = r7.toString()
            r10.setText(r6)
            r10.setTextColor(r4)
        L9b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.updateSingleSeatView(java.lang.String, boolean, java.lang.String, android.widget.ImageView, android.widget.TextView):void");
    }

    private void updateSubActionsWithMassageInline(boolean z6, boolean z7, boolean z8) {
        View view = this.layoutHvacSubActionsRow;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            View view2 = this.layoutHvacSubActionsRow2;
            ViewGroup viewGroup2 = view2 instanceof ViewGroup ? (ViewGroup) view2 : null;
            View view3 = this.btnSeatMassageDriverInline;
            if (view3 != null) {
                if (z6 && z7) {
                    view3.setVisibility(0);
                } else {
                    view3.setVisibility(8);
                    removeFromParent(this.btnSeatMassageDriverInline);
                }
            }
            View view4 = this.btnSeatMassagePassInline;
            if (view4 != null) {
                if (z6 && z8) {
                    view4.setVisibility(0);
                } else {
                    view4.setVisibility(8);
                    removeFromParent(this.btnSeatMassagePassInline);
                }
            }
            ArrayList arrayList = new ArrayList();
            for (String str : getSeatPanelActionOrder()) {
                View view5 = ACTION_STEERING_HEAT.equals(str) ? this.steeringHeatContainer : ACTION_WIRELESS_CHARGING.equals(str) ? this.wirelessChargingContainer : ACTION_HVAC_CIRC.equals(str) ? this.hvacCircContainer : ACTION_PEDESTRIAN_ALERT.equals(str) ? this.pedestrianAlertContainer : ACTION_FRAGRANCE.equals(str) ? this.hvacFragranceContainer : ACTION_WIPER_SENSITIVITY.equals(str) ? this.wiperSensitivityContainer : ACTION_LIGHT_HEIGHT.equals(str) ? this.lightHeightContainer : ACTION_MASSAGE_DRIVER.equals(str) ? this.btnSeatMassageDriverInline : ACTION_MASSAGE_PASS.equals(str) ? this.btnSeatMassagePassInline : null;
                if (view5 != null && view5.getVisibility() == 0 && !arrayList.contains(view5)) {
                    arrayList.add(view5);
                }
            }
            View view6 = this.steeringHeatContainer;
            if (view6 != null && view6.getVisibility() == 0 && !arrayList.contains(this.steeringHeatContainer)) {
                arrayList.add(this.steeringHeatContainer);
            }
            View view7 = this.wirelessChargingContainer;
            if (view7 != null && view7.getVisibility() == 0 && !arrayList.contains(this.wirelessChargingContainer)) {
                arrayList.add(this.wirelessChargingContainer);
            }
            View view8 = this.hvacCircContainer;
            if (view8 != null && view8.getVisibility() == 0 && !arrayList.contains(this.hvacCircContainer)) {
                arrayList.add(this.hvacCircContainer);
            }
            View view9 = this.pedestrianAlertContainer;
            if (view9 != null && view9.getVisibility() == 0 && !arrayList.contains(this.pedestrianAlertContainer)) {
                arrayList.add(this.pedestrianAlertContainer);
            }
            View view10 = this.hvacFragranceContainer;
            if (view10 != null && view10.getVisibility() == 0 && !arrayList.contains(this.hvacFragranceContainer)) {
                arrayList.add(this.hvacFragranceContainer);
            }
            View view11 = this.wiperSensitivityContainer;
            if (view11 != null && view11.getVisibility() == 0 && !arrayList.contains(this.wiperSensitivityContainer)) {
                arrayList.add(this.wiperSensitivityContainer);
            }
            View view12 = this.btnSeatMassageDriverInline;
            if (view12 != null && view12.getVisibility() == 0 && !arrayList.contains(this.btnSeatMassageDriverInline)) {
                arrayList.add(this.btnSeatMassageDriverInline);
            }
            View view13 = this.btnSeatMassagePassInline;
            if (view13 != null && view13.getVisibility() == 0 && !arrayList.contains(this.btnSeatMassagePassInline)) {
                arrayList.add(this.btnSeatMassagePassInline);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                View view14 = (View) it.next();
                normalizeSubActionButtonLayout(view14);
                removeFromParent(view14);
            }
            int resolveFirstRowCountForTwoRows = resolveFirstRowCountForTwoRows(arrayList.size(), getSeatPanelPerRowCount());
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                View view15 = (View) arrayList.get(i7);
                if (viewGroup2 == null || i7 < resolveFirstRowCountForTwoRows) {
                    moveViewToParent(view15, viewGroup);
                } else {
                    moveViewToParent(view15, viewGroup2);
                }
            }
            int childCount = viewGroup != null ? viewGroup.getChildCount() : 0;
            int childCount2 = viewGroup2 != null ? viewGroup2.getChildCount() : 0;
            boolean z9 = childCount + childCount2 > 0;
            View view16 = this.layoutHvacFanRow;
            boolean z10 = view16 != null && view16.getVisibility() == 0;
            View view17 = this.dividerHvacSubActions;
            if (view17 != null) {
                view17.setVisibility((z9 && z10) ? 0 : 8);
            }
            this.layoutHvacSubActionsRow.setVisibility(z9 ? 0 : 8);
            View view18 = this.layoutHvacSubActionsRow2;
            if (view18 != null) {
                view18.setVisibility(childCount2 > 0 ? 0 : 8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTireCard(o4.s sVar) {
        if (this.layoutTireCard == null) {
            return;
        }
        if (!isTireCardEnabled()) {
            if (this.tireCardShowing) {
                hideTireCard();
                return;
            }
            return;
        }
        if (sVar == null) {
            setTireWheel(this.tvTireFlValue, this.tvTireFlTemp, -1, Integer.MIN_VALUE);
            setTireWheel(this.tvTireFrValue, this.tvTireFrTemp, -1, Integer.MIN_VALUE);
            setTireWheel(this.tvTireRlValue, this.tvTireRlTemp, -1, Integer.MIN_VALUE);
            setTireWheel(this.tvTireRrValue, this.tvTireRrTemp, -1, Integer.MIN_VALUE);
            TextView textView = this.tvTireMeta;
            if (textView != null) {
                textView.setText("胎压未上报");
            }
            ImageView imageView = this.imgTireCard;
            if (imageView != null) {
                imageView.setAlpha(1.0f);
                return;
            }
            return;
        }
        setTireWheel(this.tvTireFlValue, this.tvTireFlTemp, sVar.f8019a, sVar.f8023e);
        setTireWheel(this.tvTireFrValue, this.tvTireFrTemp, sVar.f8020b, sVar.f8024f);
        setTireWheel(this.tvTireRlValue, this.tvTireRlTemp, sVar.f8021c, sVar.f8025g);
        setTireWheel(this.tvTireRrValue, this.tvTireRrTemp, sVar.f8022d, sVar.f8026h);
        TextView textView2 = this.tvTireMeta;
        if (textView2 != null) {
            textView2.setText(sVar.d());
        }
        ImageView imageView2 = this.imgTireCard;
        if (imageView2 != null) {
            imageView2.setAlpha((sVar.c() || !sVar.f8027i) ? 0.65f : 1.0f);
        }
    }

    private void updateTopCarLayoutHeight(View view, boolean z6) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        int round = Math.round((z6 ? 200 : TOP_MODEL_HEIGHT_EXPANDED_DP) * this.activity.getResources().getDisplayMetrics().density);
        if (layoutParams.height != round) {
            layoutParams.height = round;
            view.setLayoutParams(layoutParams);
        }
    }

    private void updateTopGearDisplay(String str, boolean z6) {
        TextView textView = this.tvTopGear;
        if (textView == null) {
            return;
        }
        if (!z6) {
            textView.setVisibility(8);
        } else {
            this.tvTopGear.setText((str == null || str.trim().isEmpty()) ? "--" : str.trim().toUpperCase());
            this.tvTopGear.setVisibility(0);
        }
    }

    private void updateTrunkIcon() {
        if (this.ivTrunkIcon == null) {
            return;
        }
        Map map = (Map) o4.c.c().f7956x.d();
        this.ivTrunkIcon.setColorFilter(map != null && Boolean.TRUE.equals(map.get("trunk")) ? -3790808 : -1, PorterDuff.Mode.SRC_IN);
    }

    private void updateWiperSensitivityDisplay(int i7) {
        TextView textView = this.tvWiperSensitivity;
        if (textView != null) {
            String str = "雨感";
            if (i7 >= 1 && i7 <= 4) {
                str = a.a.e("雨感", i7, "档");
            }
            textView.setText(str);
            this.tvWiperSensitivity.setTextColor(-1);
        }
        ImageView imageView = this.ivWiperSensitivityIcon;
        if (imageView != null) {
            imageView.setColorFilter(-1, PorterDuff.Mode.SRC_IN);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWiperSensitivityDisplay(String str) {
        updateWiperSensitivityDisplay(parseWiperSensitivityLevel(str));
    }

    private void updateWirelessChargingDisplay(Map<String, String> map) {
        String str;
        boolean equals = "开".equals(map != null ? map.get(ACTION_WIRELESS_CHARGING) : null);
        int wirelessChargingWorkState = equals ? getWirelessChargingWorkState(map) : 0;
        if (!equals) {
            showWirelessChargingStatic(g4.o.ic_wireless_charging, -1, "switch_off");
            str = "无线充关";
        } else if (wirelessChargingWorkState == 1) {
            showWirelessChargingAnimation();
            str = "充电中";
        } else if (wirelessChargingWorkState == 2) {
            showWirelessChargingStatic(g4.o.ic_wireless_charging_complete, COLOR_WIRELESS_COMPLETE, "completed");
            str = "已充满";
        } else if (wirelessChargingWorkState == 3) {
            showWirelessChargingStatic(g4.o.ic_wireless_charging_alert, COLOR_WIRELESS_ALERT, "alert");
            str = "充电异常";
        } else {
            showWirelessChargingStatic(g4.o.ic_wireless_charging, COLOR_WIRELESS_ENABLED, "switch_on");
            str = "无线充开";
        }
        TextView textView = this.tvWirelessCharging;
        if (textView != null) {
            textView.setText(str);
            this.tvWirelessCharging.setTextColor(-1);
        }
    }

    public View getCarControlContainer() {
        return this.carControlContainer;
    }

    public String getCarInfoNameById(String str) {
        str.getClass();
        int i7 = 0;
        switch (str) {
            case "gnss_satellites":
                return "卫星";
            case "phone_battery":
                return "手机电量";
            case "gnss_altitude":
                return "海拔";
            case "gnss_accuracy":
                return "精度";
        }
        while (true) {
            String[] strArr = TOP_INFO_IDS;
            if (i7 >= strArr.length) {
                return null;
            }
            if (strArr[i7].equals(str)) {
                return TOP_INFO_NAMES[i7];
            }
            i7++;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public String getCarInfoValue(String str) {
        char c7;
        o4.c c8 = o4.c.c();
        str.getClass();
        switch (str.hashCode()) {
            case -2069321651:
                if (str.equals("driving_suspension_mode")) {
                    c7 = 0;
                    break;
                }
                c7 = 65535;
                break;
            case -1856554720:
                if (str.equals("sunroof")) {
                    c7 = 1;
                    break;
                }
                c7 = 65535;
                break;
            case -1825919914:
                if (str.equals("seat_vent_fl")) {
                    c7 = 2;
                    break;
                }
                c7 = 65535;
                break;
            case -1825919908:
                if (str.equals("seat_vent_fr")) {
                    c7 = 3;
                    break;
                }
                c7 = 65535;
                break;
            case -1717919903:
                if (str.equals("sunshade")) {
                    c7 = 4;
                    break;
                }
                c7 = 65535;
                break;
            case -1616501617:
                if (str.equals("engine_status")) {
                    c7 = 5;
                    break;
                }
                c7 = 65535;
                break;
            case -1590943324:
                if (str.equals("driving_handbrake")) {
                    c7 = 6;
                    break;
                }
                c7 = 65535;
                break;
            case -1556963989:
                if (str.equals(ACTION_WIPER_SENSITIVITY)) {
                    c7 = 7;
                    break;
                }
                c7 = 65535;
                break;
            case -1540171570:
                if (str.equals("battery_health")) {
                    c7 = '\b';
                    break;
                }
                c7 = 65535;
                break;
            case -1515813330:
                if (str.equals("outside_temp")) {
                    c7 = '\t';
                    break;
                }
                c7 = 65535;
                break;
            case -1504999760:
                if (str.equals("trip_mileage")) {
                    c7 = '\n';
                    break;
                }
                c7 = 65535;
                break;
            case -1491273549:
                if (str.equals("driving_brake")) {
                    c7 = 11;
                    break;
                }
                c7 = 65535;
                break;
            case -1472133485:
                if (str.equals("driving_wiper")) {
                    c7 = '\f';
                    break;
                }
                c7 = 65535;
                break;
            case -1454093232:
                if (str.equals("gnss_satellites")) {
                    c7 = '\r';
                    break;
                }
                c7 = 65535;
                break;
            case -1378075333:
                if (str.equals("seat_heat_fl")) {
                    c7 = 14;
                    break;
                }
                c7 = 65535;
                break;
            case -1378075327:
                if (str.equals("seat_heat_fr")) {
                    c7 = 15;
                    break;
                }
                c7 = 65535;
                break;
            case -1378074961:
                if (str.equals("seat_heat_rl")) {
                    c7 = 16;
                    break;
                }
                c7 = 65535;
                break;
            case -1378074955:
                if (str.equals("seat_heat_rr")) {
                    c7 = 17;
                    break;
                }
                c7 = 65535;
                break;
            case -1291503367:
                if (str.equals("ev_soc")) {
                    c7 = 18;
                    break;
                }
                c7 = 65535;
                break;
            case -1291503362:
                if (str.equals("ev_soh")) {
                    c7 = 19;
                    break;
                }
                c7 = 65535;
                break;
            case -873259949:
                if (str.equals("battery_power")) {
                    c7 = 20;
                    break;
                }
                c7 = 65535;
                break;
            case -752949242:
                if (str.equals("driving_brake_link")) {
                    c7 = 21;
                    break;
                }
                c7 = 65535;
                break;
            case -684943650:
                if (str.equals(ACTION_HVAC_AUTO)) {
                    c7 = 22;
                    break;
                }
                c7 = 65535;
                break;
            case -684895674:
                if (str.equals(ACTION_HVAC_CIRC)) {
                    c7 = 23;
                    break;
                }
                c7 = 65535;
                break;
            case -684854869:
                if (str.equals("hvac_dual")) {
                    c7 = 24;
                    break;
                }
                c7 = 65535;
                break;
            case -684592430:
                if (str.equals(ACTION_HVAC_MODE)) {
                    c7 = 25;
                    break;
                }
                c7 = 65535;
                break;
            case -217002123:
                if (str.equals("window_fl")) {
                    c7 = 26;
                    break;
                }
                c7 = 65535;
                break;
            case -217002117:
                if (str.equals("window_fr")) {
                    c7 = 27;
                    break;
                }
                c7 = 65535;
                break;
            case -217001751:
                if (str.equals("window_rl")) {
                    c7 = 28;
                    break;
                }
                c7 = 65535;
                break;
            case -217001745:
                if (str.equals("window_rr")) {
                    c7 = 29;
                    break;
                }
                c7 = 65535;
                break;
            case -208924823:
                if (str.equals("light_head")) {
                    c7 = 30;
                    break;
                }
                c7 = 65535;
                break;
            case -208920789:
                if (str.equals("light_high")) {
                    c7 = 31;
                    break;
                }
                c7 = 65535;
                break;
            case -133741508:
                if (str.equals("phone_battery")) {
                    c7 = ' ';
                    break;
                }
                c7 = 65535;
                break;
            case 109882:
                if (str.equals("odo")) {
                    c7 = '!';
                    break;
                }
                c7 = 65535;
                break;
            case 3154358:
                if (str.equals("fuel")) {
                    c7 = '\"';
                    break;
                }
                c7 = 65535;
                break;
            case 3168655:
                if (str.equals("gear")) {
                    c7 = '#';
                    break;
                }
                c7 = 65535;
                break;
            case 3208476:
                if (str.equals("hood")) {
                    c7 = '$';
                    break;
                }
                c7 = 65535;
                break;
            case 3556308:
                if (str.equals("temp")) {
                    c7 = '%';
                    break;
                }
                c7 = 65535;
                break;
            case 46487305:
                if (str.equals("seat_msg_pass")) {
                    c7 = '&';
                    break;
                }
                c7 = 65535;
                break;
            case 108280125:
                if (str.equals("range")) {
                    c7 = '\'';
                    break;
                }
                c7 = 65535;
                break;
            case 109641799:
                if (str.equals("speed")) {
                    c7 = '(';
                    break;
                }
                c7 = 65535;
                break;
            case 110640564:
                if (str.equals("trunk")) {
                    c7 = ')';
                    break;
                }
                c7 = 65535;
                break;
            case 255260086:
                if (str.equals("hvac_power")) {
                    c7 = '*';
                    break;
                }
                c7 = 65535;
                break;
            case 418290482:
                if (str.equals("cdm_power")) {
                    c7 = '+';
                    break;
                }
                c7 = 65535;
                break;
            case 440747165:
                if (str.equals("power_mode")) {
                    c7 = ',';
                    break;
                }
                c7 = 65535;
                break;
            case 577689698:
                if (str.equals("drive_time")) {
                    c7 = '-';
                    break;
                }
                c7 = 65535;
                break;
            case 632380254:
                if (str.equals("voltage")) {
                    c7 = '.';
                    break;
                }
                c7 = 65535;
                break;
            case 648358823:
                if (str.equals(ACTION_HVAC_REAR_FAN)) {
                    c7 = '/';
                    break;
                }
                c7 = 65535;
                break;
            case 649261694:
                if (str.equals("hvac_front_defrost")) {
                    c7 = '0';
                    break;
                }
                c7 = 65535;
                break;
            case 685995541:
                if (str.equals("light_fog")) {
                    c7 = '1';
                    break;
                }
                c7 = 65535;
                break;
            case 1069321966:
                if (str.equals("avg_fuel_after_clear")) {
                    c7 = '2';
                    break;
                }
                c7 = 65535;
                break;
            case 1083759451:
                if (str.equals("light_hazard")) {
                    c7 = '3';
                    break;
                }
                c7 = 65535;
                break;
            case 1086287876:
                if (str.equals(ACTION_HVAC_FAN)) {
                    c7 = '4';
                    break;
                }
                c7 = 65535;
                break;
            case 1281967377:
                if (str.equals("hvac_ac")) {
                    c7 = '5';
                    break;
                }
                c7 = 65535;
                break;
            case 1343533431:
                if (str.equals("hvac_rear_defrost")) {
                    c7 = '6';
                    break;
                }
                c7 = 65535;
                break;
            case 1354914414:
                if (str.equals("child_lock")) {
                    c7 = '7';
                    break;
                }
                c7 = 65535;
                break;
            case 1396485408:
                if (str.equals("seat_msg_driver")) {
                    c7 = '8';
                    break;
                }
                c7 = 65535;
                break;
            case 1424688568:
                if (str.equals("avg_fuel_recently_50km")) {
                    c7 = '9';
                    break;
                }
                c7 = 65535;
                break;
            case 1670334042:
                if (str.equals("gnss_altitude")) {
                    c7 = ':';
                    break;
                }
                c7 = 65535;
                break;
            case 1797043377:
                if (str.equals("gnss_accuracy")) {
                    c7 = ';';
                    break;
                }
                c7 = 65535;
                break;
            case 1839892215:
                if (str.equals("door_fl")) {
                    c7 = '<';
                    break;
                }
                c7 = 65535;
                break;
            case 1839892221:
                if (str.equals("door_fr")) {
                    c7 = '=';
                    break;
                }
                c7 = 65535;
                break;
            case 1839892587:
                if (str.equals("door_rl")) {
                    c7 = '>';
                    break;
                }
                c7 = 65535;
                break;
            case 1839892593:
                if (str.equals("door_rr")) {
                    c7 = '?';
                    break;
                }
                c7 = 65535;
                break;
            case 1880790032:
                if (str.equals("driving_brake_feel_mode")) {
                    c7 = '@';
                    break;
                }
                c7 = 65535;
                break;
            case 2034768271:
                if (str.equals("light_parking")) {
                    c7 = 'A';
                    break;
                }
                c7 = 65535;
                break;
            default:
                c7 = 65535;
                break;
        }
        switch (c7) {
            case 0:
                Map map = (Map) c8.A.d();
                return map != null ? (String) map.getOrDefault("suspension_mode", "--") : "--";
            case 1:
                return getWindowStatus(c8, "sunroof");
            case 2:
                return getSeatValue(c8, ACTION_VENT_FL);
            case 3:
                return getSeatValue(c8, ACTION_VENT_FR);
            case 4:
                return getWindowStatus(c8, "sunshade");
            case 5:
                return safeValue(c8.f7949q.d());
            case 6:
                Map map2 = (Map) c8.A.d();
                return map2 != null ? (String) map2.getOrDefault("handbrake", "--") : "--";
            case 7:
                String str2 = (String) c8.D.d();
                return (str2 == null || !str2.startsWith("雨感灵敏度: ")) ? safeValue(str2) : str2.substring(7);
            case '\b':
                return safeValue(c8.f7946n.d());
            case '\t':
                return safeValue(c8.f7957y.d());
            case '\n':
                return safeValue(c8.f7952t.d());
            case 11:
                Map map3 = (Map) c8.A.d();
                return map3 != null ? (String) map3.getOrDefault("brake", "--") : "--";
            case '\f':
                Map map4 = (Map) c8.A.d();
                return map4 != null ? (String) map4.getOrDefault("wiper", "--") : "--";
            case '\r':
            case z1.c.IF_LTZ /* 58 */:
            case z1.c.IF_GEZ /* 59 */:
                return getGnssDisplayValue(str);
            case 14:
                return getSeatValue(c8, ACTION_HEAT_FL);
            case 15:
                return getSeatValue(c8, ACTION_HEAT_FR);
            case 16:
                return getSeatValue(c8, ACTION_HEAT_RL);
            case 17:
                return getSeatValue(c8, ACTION_HEAT_RR);
            case 18:
                return safeValue(c8.I.d());
            case 19:
                return safeValue(c8.J.d());
            case 20:
                return safeValue(c8.f7945m.d());
            case 21:
                Map map5 = (Map) c8.A.d();
                return map5 != null ? (String) map5.getOrDefault("brake_link", "--") : "--";
            case 22:
                return getHvacValue(c8, "auto");
            case 23:
                return getHvacValue(c8, "circ");
            case 24:
                return getHvacValue(c8, "dual");
            case 25:
                return getHvacValue(c8, "mode");
            case 26:
                return getWindowStatus(c8, "fl");
            case 27:
                return getWindowStatus(c8, "fr");
            case 28:
                return getWindowStatus(c8, "rl");
            case 29:
                return getWindowStatus(c8, "rr");
            case 30:
                return getLightStatus(c8, "head");
            case 31:
                return getLightStatus(c8, "high");
            case ' ':
                return safeValue(c8.f7947o.d());
            case '!':
                return safeValue(c8.f7943k.d());
            case '\"':
                return safeValue(c8.f7941i.d());
            case '#':
                return safeValue(c8.f7939g.d());
            case '$':
                return getDoorStatus(c8, "hood");
            case '%':
                return getHvacValue(c8, "temp");
            case '&':
                return getSeatValue(c8, "msg_pass");
            case '\'':
                return safeValue(c8.f7942j.d());
            case '(':
                return safeValue(c8.f7938f.d());
            case ')':
                return getDoorStatus(c8, "trunk");
            case '*':
                return getHvacValue(c8, "power");
            case '+':
                Integer num = (Integer) c8.K.d();
                int intValue = num != null ? num.intValue() : -1;
                return intValue < 0 ? "--" : cdmPowerTopValue(intValue);
            case ',':
                return safeValue(c8.f7950r.d());
            case '-':
                return safeValue(c8.f7953u.d());
            case '.':
                return safeValue(c8.f7944l.d());
            case '/':
                return getRearFanDisplayValue(c8);
            case '0':
                return getHvacValue(c8, "front_defrost");
            case '1':
                return getLightStatus(c8, "fog");
            case '2':
                return safeValue(c8.f7955w.d());
            case '3':
                return getLightStatus(c8, "hazard");
            case '4':
                return getHvacValue(c8, "fan");
            case '5':
                return getHvacValue(c8, "ac");
            case '6':
                return getHvacValue(c8, "rear_defrost");
            case '7':
                return safeValue(c8.f7951s.d());
            case z1.c.IF_EQZ /* 56 */:
                return getSeatValue(c8, "msg_driver");
            case z1.c.IF_NEZ /* 57 */:
                return safeValue(c8.f7954v.d());
            case '<':
                return getDoorStatus(c8, "fl");
            case z1.c.IF_LEZ /* 61 */:
                return getDoorStatus(c8, "fr");
            case '>':
                return getDoorStatus(c8, "rl");
            case '?':
                return getDoorStatus(c8, "rr");
            case '@':
                Map map6 = (Map) c8.A.d();
                return map6 != null ? (String) map6.getOrDefault("brake_feel_mode", "--") : "--";
            case a0.e.LAYOUT_CONSTRAINT_HEIGHT /* 65 */:
                return getLightStatus(c8, "parking");
            default:
                return "--";
        }
    }

    public String[] getTopInfoIds() {
        ArrayList arrayList = new ArrayList();
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            for (String str : TOP_INFO_IDS) {
                if (LION_SUPPORTED_TOP_INFO_IDS.contains(str)) {
                    arrayList.add(str);
                }
            }
        } else {
            arrayList.addAll(Arrays.asList(TOP_INFO_IDS));
        }
        arrayList.add("gnss_altitude");
        arrayList.add("gnss_accuracy");
        arrayList.add("gnss_satellites");
        arrayList.add("phone_battery");
        return (String[]) arrayList.toArray(new String[0]);
    }

    public void handleHvacAutoClick() {
        Map map = (Map) o4.c.c().F.d();
        if (map == null) {
            map = new HashMap();
        }
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            sendHvacCommand(100, (("开".equals((String) map.getOrDefault("power", "关")) || "开".equals((String) map.getOrDefault("auto", "关")) || "开".equals((String) map.getOrDefault("ac", "关"))) ? 1 : 0) ^ 1);
            return;
        }
        String str = (String) map.getOrDefault("power", "关");
        String str2 = (String) map.getOrDefault("auto", "关");
        String str3 = (String) map.getOrDefault("ac", "关");
        if ("开".equals(str) || "开".equals(str2) || "开".equals(str3)) {
            sendHvacCommand(18, 2);
        } else {
            sendHvacCommand(4, 1);
        }
    }

    public void handleHvacCirculationClick() {
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            return;
        }
        Map map = (Map) o4.c.c().F.d();
        if (map == null) {
            map = new HashMap();
        }
        sendHvacCommand(8, "外".equals((String) map.getOrDefault("circ", "外")) ? 1 : 2);
    }

    public void handleLightHeightClick() {
        int i7 = 0;
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            return;
        }
        int parseLightHeightLevel = parseLightHeightLevel((String) o4.c.c().E.d());
        if (parseLightHeightLevel >= 0 && parseLightHeightLevel < 3) {
            i7 = parseLightHeightLevel + 1;
        }
        if (sendCarSettingCommand(91, i7 + 1)) {
            updateLightHeightDisplay(i7);
        } else {
            ToastHelper.show(this.activity, "车联服务未连接");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    public void handlePedestrianAlertClick() {
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            return;
        }
        if (CarInfoService.d() == null) {
            ToastHelper.show(this.activity, "车联服务未连接");
            return;
        }
        int readModuleValue = readModuleValue(MODULE_NEW_ENERGY, 102);
        int i7 = readModuleValue;
        i7 = readModuleValue;
        if (readModuleValue != 0 && readModuleValue != 1) {
            Map map = (Map) o4.c.c().F.d();
            i7 = "1".equals(map == null ? null : (String) map.get(ACTION_PEDESTRIAN_ALERT));
        }
        final ?? r22 = i7 != 1 ? 1 : 0;
        if (!sendControlCommandInternal(MODULE_NEW_ENERGY, 102, r22)) {
            ToastHelper.show(this.activity, "车联服务未连接");
            return;
        }
        View view = this.pedestrianAlertContainer;
        if (view != null) {
            view.postDelayed(new Runnable() { // from class: com.navi.tool.ui.extra.g1
                @Override // java.lang.Runnable
                public final void run() {
                    DesktopAssistantCarControlHelper.lambda$handlePedestrianAlertClick$62(r22);
                }
            }, 300L);
        }
    }

    public void handleSteeringHeatClick() {
        if (this.prefs.getBoolean("lion_sync_service_enabled", false) || sendCarSettingCommand(z1.c.AND_LONG, 1)) {
            return;
        }
        ToastHelper.show(this.activity, "车联服务未连接");
    }

    public void handleTrunkClick() {
        if (this.trunkCmdInFlight || this.trunkContainer == null) {
            return;
        }
        Map map = (Map) o4.c.c().f7956x.d();
        int i7 = (map == null || !Boolean.TRUE.equals(map.get("trunk"))) ? 1 : 2;
        this.trunkCmdInFlight = true;
        if (sendCarSettingCommand(92, i7)) {
            this.trunkContainer.postDelayed(new r1(this, 3), 600L);
        } else {
            this.trunkCmdInFlight = false;
            ToastHelper.show(this.activity, "车联服务未连接");
        }
    }

    public void handleWiperSensitivityClick() {
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            return;
        }
        int parseWiperSensitivityLevel = parseWiperSensitivityLevel((String) o4.c.c().D.d());
        int i7 = 1;
        if (parseWiperSensitivityLevel >= 1 && parseWiperSensitivityLevel < 4) {
            i7 = 1 + parseWiperSensitivityLevel;
        }
        if (sendCarSettingCommand(z1.c.OR_LONG, i7)) {
            updateWiperSensitivityDisplay(i7);
        } else {
            ToastHelper.show(this.activity, "车联服务未连接");
        }
    }

    public void handleWirelessChargingClick() {
        int i7 = 1;
        if (sendCarSettingCommand(45, isWirelessChargingEnabled() ^ true ? 2 : 1)) {
            this.activity.runOnUiThread(new r1(this, i7));
        } else {
            ToastHelper.show(this.activity, "车联服务未连接");
        }
    }

    public View initCarControlViews() {
        this.carControlContainer = LayoutInflater.from(this.activity).inflate(g4.q.view_car_control_cards, (ViewGroup) null);
        ensureSeatPanelActionsInitialized();
        RecyclerView recyclerView = (RecyclerView) this.carControlContainer.findViewById(g4.p.recycler_car_control);
        this.carControlRecyclerView = recyclerView;
        final int i7 = 1;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(1));
            CarControlAdapter carControlAdapter = new CarControlAdapter();
            this.carControlAdapter = carControlAdapter;
            this.carControlRecyclerView.setAdapter(carControlAdapter);
            new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.l0() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.3
                @Override // androidx.recyclerview.widget.l0
                public void clearView(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var) {
                    super.clearView(recyclerView2, j2Var);
                    DesktopAssistantCarControlHelper.this.isCarCardDragging = false;
                }

                @Override // androidx.recyclerview.widget.l0
                public int getMovementFlags(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var) {
                    return androidx.recyclerview.widget.l0.makeMovementFlags(3, 0);
                }

                @Override // androidx.recyclerview.widget.l0
                public boolean isLongPressDragEnabled() {
                    return true;
                }

                @Override // androidx.recyclerview.widget.l0
                public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                    DesktopAssistantCarControlHelper.this.carControlAdapter.onItemMove(j2Var.getAdapterPosition(), j2Var2.getAdapterPosition());
                    DesktopAssistantCarControlHelper.this.saveCarCardOrder();
                    return true;
                }

                @Override // androidx.recyclerview.widget.l0
                public void onSelectedChanged(androidx.recyclerview.widget.j2 j2Var, int i8) {
                    super.onSelectedChanged(j2Var, i8);
                    if (i8 != 0) {
                        DesktopAssistantCarControlHelper.this.isCarCardDragging = true;
                    }
                }

                @Override // androidx.recyclerview.widget.l0
                public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i8) {
                }
            }).f(this.carControlRecyclerView);
        }
        this.tvNoCards = (TextView) this.carControlContainer.findViewById(g4.p.tv_no_cards);
        CardView cardView = (CardView) this.carControlContainer.findViewById(g4.p.card_seat_control);
        this.cardSeatControl = cardView;
        if (cardView != null) {
            cardView.setOnLongClickListener(new s1(this, i7));
        }
        this.seatHeatFlContainer = this.carControlContainer.findViewById(g4.p.seat_heat_fl_container);
        this.layoutSeatHeatRow = this.carControlContainer.findViewById(g4.p.layout_seat_heat_row);
        this.layoutHvacPrimaryRow = this.carControlContainer.findViewById(g4.p.layout_hvac_primary_row);
        this.ivSeatHeatFlIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_heat_fl_icon);
        this.tvSeatHeatFlLevel = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_heat_fl_level);
        this.seatHeatFrContainer = this.carControlContainer.findViewById(g4.p.seat_heat_fr_container);
        this.ivSeatHeatFrIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_heat_fr_icon);
        this.tvSeatHeatFrLevel = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_heat_fr_level);
        this.seatHeatRlContainer = this.carControlContainer.findViewById(g4.p.seat_heat_rl_container);
        this.ivSeatHeatRlIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_heat_rl_icon);
        this.tvSeatHeatRlLevel = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_heat_rl_level);
        this.seatHeatRrContainer = this.carControlContainer.findViewById(g4.p.seat_heat_rr_container);
        this.ivSeatHeatRrIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_heat_rr_icon);
        this.tvSeatHeatRrLevel = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_heat_rr_level);
        this.dividerSeatHeatVent = this.carControlContainer.findViewById(g4.p.divider_seat_heat_vent);
        this.seatVentFlContainer = this.carControlContainer.findViewById(g4.p.ll_seat_vent_fl);
        this.ivSeatVentFlIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_vent_fl_icon);
        this.tvSeatVentFlLevel = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_vent_fl_level);
        this.seatVentFrContainer = this.carControlContainer.findViewById(g4.p.ll_seat_vent_fr);
        this.ivSeatVentFrIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_vent_fr_icon);
        this.tvSeatVentFrLevel = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_vent_fr_level);
        this.hvacAutoContainer = this.carControlContainer.findViewById(g4.p.ll_hvac_auto);
        this.ivHvacAutoIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_hvac_auto_icon);
        this.tvHvacAuto = (TextView) this.carControlContainer.findViewById(g4.p.tv_hvac_auto);
        this.hvacModeContainer = this.carControlContainer.findViewById(g4.p.ll_hvac_mode);
        this.ivHvacModeIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_hvac_mode_icon);
        this.tvHvacMode = (TextView) this.carControlContainer.findViewById(g4.p.tv_hvac_mode);
        this.dividerHvacFan = this.carControlContainer.findViewById(g4.p.divider_hvac_fan);
        this.layoutHvacFanRow = this.carControlContainer.findViewById(g4.p.layout_hvac_fan_row);
        this.layoutHvacRearFanRow = this.carControlContainer.findViewById(g4.p.layout_hvac_rear_fan_row);
        this.dividerHvacSubActions = this.carControlContainer.findViewById(g4.p.divider_hvac_sub_actions);
        this.layoutHvacSubActionsRow = this.carControlContainer.findViewById(g4.p.layout_hvac_sub_actions_row);
        this.layoutHvacSubActionsRow2 = this.carControlContainer.findViewById(g4.p.layout_hvac_sub_actions_row_2);
        this.steeringHeatContainer = this.carControlContainer.findViewById(g4.p.ll_steering_heat);
        this.ivSteeringHeatIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_steering_heat_icon);
        this.tvSteeringHeat = (TextView) this.carControlContainer.findViewById(g4.p.tv_steering_heat);
        this.wirelessChargingContainer = this.carControlContainer.findViewById(g4.p.ll_wireless_charging);
        this.ivWirelessChargingIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_wireless_charging_icon);
        this.tvWirelessCharging = (TextView) this.carControlContainer.findViewById(g4.p.tv_wireless_charging);
        this.hvacCircContainer = this.carControlContainer.findViewById(g4.p.ll_hvac_circ);
        this.ivHvacCircIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_hvac_circ_icon);
        this.tvHvacCirc = (TextView) this.carControlContainer.findViewById(g4.p.tv_hvac_circ);
        this.pedestrianAlertContainer = this.carControlContainer.findViewById(g4.p.ll_pedestrian_alert);
        this.ivPedestrianAlertIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_pedestrian_alert_icon);
        this.tvPedestrianAlert = (TextView) this.carControlContainer.findViewById(g4.p.tv_pedestrian_alert);
        this.hvacFragranceContainer = this.carControlContainer.findViewById(g4.p.ll_hvac_fragrance);
        this.ivHvacFragranceIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_hvac_fragrance_icon);
        this.tvHvacFragrance = (TextView) this.carControlContainer.findViewById(g4.p.tv_hvac_fragrance);
        this.wiperSensitivityContainer = this.carControlContainer.findViewById(g4.p.ll_wiper_sensitivity);
        this.ivWiperSensitivityIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_wiper_sensitivity_icon);
        this.tvWiperSensitivity = (TextView) this.carControlContainer.findViewById(g4.p.tv_wiper_sensitivity);
        this.lightHeightContainer = this.carControlContainer.findViewById(g4.p.ll_light_height);
        this.ivLightHeightIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_light_height_icon);
        this.tvLightHeight = (TextView) this.carControlContainer.findViewById(g4.p.tv_light_height);
        this.trunkContainer = this.carControlContainer.findViewById(g4.p.ll_trunk);
        this.ivTrunkIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_trunk_icon);
        this.tvTrunk = (TextView) this.carControlContainer.findViewById(g4.p.tv_trunk);
        this.cardSeatMemory = (CardView) this.carControlContainer.findViewById(g4.p.card_seat_memory);
        this.cardSeatMassage = (CardView) this.carControlContainer.findViewById(g4.p.card_seat_massage);
        CardView cardView2 = this.cardSeatMemory;
        int i8 = 2;
        if (cardView2 != null) {
            cardView2.setOnLongClickListener(new s1(this, i8));
        }
        CardView cardView3 = this.cardSeatMassage;
        int i9 = 3;
        if (cardView3 != null) {
            cardView3.setOnLongClickListener(new s1(this, i9));
        }
        this.layoutSeatMemoryInline = this.carControlContainer.findViewById(g4.p.layout_seat_memory_inline);
        this.layoutSeatMemoryApply = this.carControlContainer.findViewById(g4.p.layout_seat_memory_apply);
        this.dividerSeatMemoryInline = this.carControlContainer.findViewById(g4.p.divider_seat_memory_inline);
        this.layoutSeatMemorySaveInline = this.carControlContainer.findViewById(g4.p.layout_seat_memory_save_inline);
        this.layoutSeatMemorySave = this.carControlContainer.findViewById(g4.p.layout_seat_memory_save);
        this.dividerSeatMemorySaveInline = this.carControlContainer.findViewById(g4.p.divider_seat_memory_save_inline);
        this.dividerSeatMemorySave = this.carControlContainer.findViewById(g4.p.divider_seat_memory_save);
        this.dividerSeatMassageInline = this.carControlContainer.findViewById(g4.p.divider_seat_massage_inline);
        this.dividerSeatMassage = this.carControlContainer.findViewById(g4.p.divider_seat_massage);
        this.layoutSeatMassageInline = this.carControlContainer.findViewById(g4.p.layout_seat_massage_inline);
        this.layoutSeatMassage = this.carControlContainer.findViewById(g4.p.layout_seat_massage);
        this.btnSeatMassageDriverInline = this.carControlContainer.findViewById(g4.p.btn_seat_massage_driver_inline);
        this.btnSeatMassagePassInline = this.carControlContainer.findViewById(g4.p.btn_seat_massage_pass_inline);
        this.btnSeatMassageDriver = this.carControlContainer.findViewById(g4.p.btn_seat_massage_driver);
        this.btnSeatMassagePass = this.carControlContainer.findViewById(g4.p.btn_seat_massage_pass);
        this.tvSeatMassageDriverInline = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_massage_driver_inline);
        this.tvSeatMassagePassInline = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_massage_pass_inline);
        this.tvSeatMassageDriver = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_massage_driver);
        this.tvSeatMassagePass = (TextView) this.carControlContainer.findViewById(g4.p.tv_seat_massage_pass);
        this.ivSeatMassageDriverInlineIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_massage_driver_inline_icon);
        this.ivSeatMassagePassInlineIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_massage_pass_inline_icon);
        this.ivSeatMassageDriverIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_massage_driver_icon);
        this.ivSeatMassagePassIcon = (ImageView) this.carControlContainer.findViewById(g4.p.iv_seat_massage_pass_icon);
        this.seatPanelContentContainer = resolveSeatPanelContentContainer();
        this.sbHvacFan = (SeekBar) this.carControlContainer.findViewById(g4.p.sb_hvac_fan);
        this.tvHvacFanLabel = (TextView) this.carControlContainer.findViewById(g4.p.tv_hvac_fan_label);
        this.sbHvacRearFan = (SeekBar) this.carControlContainer.findViewById(g4.p.sb_hvac_rear_fan);
        this.tvHvacRearFanLabel = (TextView) this.carControlContainer.findViewById(g4.p.tv_hvac_rear_fan_label);
        a.a.r(this, 25, this.seatHeatFlContainer);
        a.a.r(this, 26, this.seatHeatFrContainer);
        a.a.r(this, 27, this.seatHeatRlContainer);
        a.a.r(this, 28, this.seatHeatRrContainer);
        a.a.r(this, 29, this.seatVentFlContainer);
        final int i10 = 0;
        this.seatVentFrContainer.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.k1

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ DesktopAssistantCarControlHelper f5274b;

            {
                this.f5274b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i11 = i10;
                DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5274b;
                switch (i11) {
                    case 0:
                        desktopAssistantCarControlHelper.lambda$initCarControlViews$8(view);
                        break;
                    default:
                        desktopAssistantCarControlHelper.lambda$initCarControlViews$9(view);
                        break;
                }
            }
        });
        this.hvacAutoContainer.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.k1

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ DesktopAssistantCarControlHelper f5274b;

            {
                this.f5274b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i11 = i7;
                DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.f5274b;
                switch (i11) {
                    case 0:
                        desktopAssistantCarControlHelper.lambda$initCarControlViews$8(view);
                        break;
                    default:
                        desktopAssistantCarControlHelper.lambda$initCarControlViews$9(view);
                        break;
                }
            }
        });
        a.a.r(this, 0, this.hvacModeContainer);
        View view = this.hvacCircContainer;
        if (view != null) {
            a.a.r(this, 1, view);
        }
        View view2 = this.pedestrianAlertContainer;
        if (view2 != null) {
            a.a.r(this, 2, view2);
        }
        View view3 = this.hvacFragranceContainer;
        if (view3 != null) {
            a.a.r(this, 3, view3);
        }
        View view4 = this.wiperSensitivityContainer;
        int i11 = 4;
        if (view4 != null) {
            a.a.r(this, 4, view4);
        }
        View view5 = this.lightHeightContainer;
        if (view5 != null) {
            a.a.r(this, 5, view5);
        }
        View view6 = this.steeringHeatContainer;
        if (view6 != null) {
            a.a.r(this, 6, view6);
        }
        View view7 = this.wirelessChargingContainer;
        if (view7 != null) {
            a.a.r(this, 7, view7);
        }
        View view8 = this.trunkContainer;
        int i12 = 8;
        if (view8 != null) {
            view8.setOnClickListener(new h1(this, i12));
            o4.c.c().f7956x.e((androidx.lifecycle.x) this.activity, new t1(this, i7));
        }
        o4.c.c().K.e((androidx.lifecycle.x) this.activity, new t1(this, i8));
        View view9 = this.btnSeatMassageDriverInline;
        if (view9 != null) {
            a.a.r(this, 9, view9);
        }
        View view10 = this.btnSeatMassagePassInline;
        if (view10 != null) {
            a.a.r(this, 10, view10);
        }
        View view11 = this.btnSeatMassageDriver;
        if (view11 != null) {
            a.a.r(this, 11, view11);
        }
        View view12 = this.btnSeatMassagePass;
        if (view12 != null) {
            a.a.r(this, 12, view12);
        }
        View findViewById = this.carControlContainer.findViewById(g4.p.btn_seat_memory_1);
        View findViewById2 = this.carControlContainer.findViewById(g4.p.btn_seat_memory_2);
        View findViewById3 = this.carControlContainer.findViewById(g4.p.btn_seat_memory_3);
        this.btnMem1Inline = this.carControlContainer.findViewById(g4.p.btn_seat_memory_1_inline);
        this.btnMem2Inline = this.carControlContainer.findViewById(g4.p.btn_seat_memory_2_inline);
        this.btnMem3Inline = this.carControlContainer.findViewById(g4.p.btn_seat_memory_3_inline);
        if (findViewById != null) {
            a.a.r(this, 13, findViewById);
        }
        if (findViewById2 != null) {
            a.a.r(this, 14, findViewById2);
        }
        if (findViewById3 != null) {
            a.a.r(this, 15, findViewById3);
        }
        View view13 = this.btnMem1Inline;
        if (view13 != null) {
            a.a.r(this, 16, view13);
        }
        View view14 = this.btnMem2Inline;
        if (view14 != null) {
            a.a.r(this, 17, view14);
        }
        View view15 = this.btnMem3Inline;
        if (view15 != null) {
            a.a.r(this, 18, view15);
        }
        View findViewById4 = this.carControlContainer.findViewById(g4.p.btn_seat_memory_save_1);
        View findViewById5 = this.carControlContainer.findViewById(g4.p.btn_seat_memory_save_2);
        View findViewById6 = this.carControlContainer.findViewById(g4.p.btn_seat_memory_save_3);
        this.btnMemSave1Inline = this.carControlContainer.findViewById(g4.p.btn_seat_memory_save_1_inline);
        this.btnMemSave2Inline = this.carControlContainer.findViewById(g4.p.btn_seat_memory_save_2_inline);
        this.btnMemSave3Inline = this.carControlContainer.findViewById(g4.p.btn_seat_memory_save_3_inline);
        if (findViewById4 != null) {
            a.a.r(this, 19, findViewById4);
        }
        if (findViewById5 != null) {
            a.a.r(this, 20, findViewById5);
        }
        if (findViewById6 != null) {
            a.a.r(this, 21, findViewById6);
        }
        View view16 = this.btnMemSave1Inline;
        if (view16 != null) {
            a.a.r(this, 22, view16);
        }
        View view17 = this.btnMemSave2Inline;
        if (view17 != null) {
            a.a.r(this, 23, view17);
        }
        View view18 = this.btnMemSave3Inline;
        if (view18 != null) {
            a.a.r(this, 24, view18);
        }
        bindSeatPanelConfigLongPress(this.seatHeatFlContainer);
        bindSeatPanelConfigLongPress(this.seatHeatFrContainer);
        bindSeatPanelConfigLongPress(this.seatHeatRlContainer);
        bindSeatPanelConfigLongPress(this.seatHeatRrContainer);
        bindSeatPanelConfigLongPress(this.seatVentFlContainer);
        bindSeatPanelConfigLongPress(this.seatVentFrContainer);
        bindSeatPanelConfigLongPress(this.hvacAutoContainer);
        bindSeatPanelConfigLongPress(this.hvacModeContainer);
        bindSeatPanelConfigLongPress(this.hvacCircContainer);
        bindSeatPanelConfigLongPress(this.hvacFragranceContainer);
        bindSeatPanelConfigLongPress(this.wiperSensitivityContainer);
        bindSeatPanelConfigLongPress(this.lightHeightContainer);
        bindSeatPanelConfigLongPress(this.steeringHeatContainer);
        bindSeatPanelConfigLongPress(this.wirelessChargingContainer);
        bindSeatPanelConfigLongPress(this.btnSeatMassageDriverInline);
        bindSeatPanelConfigLongPress(this.btnSeatMassagePassInline);
        bindSeatPanelConfigLongPress(this.btnSeatMassageDriver);
        bindSeatPanelConfigLongPress(this.btnSeatMassagePass);
        bindSeatPanelConfigLongPress(findViewById);
        bindSeatPanelConfigLongPress(findViewById2);
        bindSeatPanelConfigLongPress(findViewById3);
        bindSeatPanelConfigLongPress(this.btnMem1Inline);
        bindSeatPanelConfigLongPress(this.btnMem2Inline);
        bindSeatPanelConfigLongPress(this.btnMem3Inline);
        bindSeatPanelConfigLongPress(findViewById4);
        bindSeatPanelConfigLongPress(findViewById5);
        bindSeatPanelConfigLongPress(findViewById6);
        bindSeatPanelConfigLongPress(this.btnMemSave1Inline);
        bindSeatPanelConfigLongPress(this.btnMemSave2Inline);
        bindSeatPanelConfigLongPress(this.btnMemSave3Inline);
        SeekBar seekBar = this.sbHvacFan;
        if (seekBar != null) {
            seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.navi.tool.ui.extra.i1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view19, MotionEvent motionEvent) {
                    boolean lambda$initCarControlViews$37;
                    boolean lambda$initCarControlViews$38;
                    switch (i10) {
                        case 0:
                            lambda$initCarControlViews$37 = DesktopAssistantCarControlHelper.lambda$initCarControlViews$37(view19, motionEvent);
                            return lambda$initCarControlViews$37;
                        default:
                            lambda$initCarControlViews$38 = DesktopAssistantCarControlHelper.lambda$initCarControlViews$38(view19, motionEvent);
                            return lambda$initCarControlViews$38;
                    }
                }
            });
            this.sbHvacFan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.4
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i13, boolean z6) {
                    if (DesktopAssistantCarControlHelper.this.tvHvacFanLabel != null) {
                        DesktopAssistantCarControlHelper.this.tvHvacFanLabel.setText("风量: " + (i13 + 1));
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                    seekBar2.getParent().requestDisallowInterceptTouchEvent(true);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                    DesktopAssistantCarControlHelper.this.sendHvacCommand(12, seekBar2.getProgress() + 1);
                    seekBar2.getParent().requestDisallowInterceptTouchEvent(false);
                }
            });
        }
        SeekBar seekBar2 = this.sbHvacRearFan;
        if (seekBar2 != null) {
            seekBar2.setOnTouchListener(new View.OnTouchListener() { // from class: com.navi.tool.ui.extra.i1
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view19, MotionEvent motionEvent) {
                    boolean lambda$initCarControlViews$37;
                    boolean lambda$initCarControlViews$38;
                    switch (i7) {
                        case 0:
                            lambda$initCarControlViews$37 = DesktopAssistantCarControlHelper.lambda$initCarControlViews$37(view19, motionEvent);
                            return lambda$initCarControlViews$37;
                        default:
                            lambda$initCarControlViews$38 = DesktopAssistantCarControlHelper.lambda$initCarControlViews$38(view19, motionEvent);
                            return lambda$initCarControlViews$38;
                    }
                }
            });
            this.sbHvacRearFan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.5
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar3, int i13, boolean z6) {
                    String str;
                    if (DesktopAssistantCarControlHelper.this.tvHvacRearFanLabel != null) {
                        TextView textView = DesktopAssistantCarControlHelper.this.tvHvacRearFanLabel;
                        StringBuilder sb = new StringBuilder("后排风量: ");
                        if (i13 == 0) {
                            str = "关闭";
                        } else {
                            str = i13 + "档";
                        }
                        sb.append(str);
                        textView.setText(sb.toString());
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar3) {
                    seekBar3.getParent().requestDisallowInterceptTouchEvent(true);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar3) {
                    int progress = seekBar3.getProgress();
                    if (progress == 0) {
                        DesktopAssistantCarControlHelper.this.sendHvacCommand(67, 1);
                    }
                    DesktopAssistantCarControlHelper.this.sendHvacCommand(68, progress);
                    seekBar3.getParent().requestDisallowInterceptTouchEvent(false);
                }
            });
        }
        View findViewById7 = this.carControlContainer.findViewById(g4.p.layout_car_front);
        View findViewById8 = this.carControlContainer.findViewById(g4.p.layout_car_top);
        final View findViewById9 = this.carControlContainer.findViewById(g4.p.door_fl);
        final View findViewById10 = this.carControlContainer.findViewById(g4.p.door_fr);
        final View findViewById11 = this.carControlContainer.findViewById(g4.p.door_rl);
        final View findViewById12 = this.carControlContainer.findViewById(g4.p.door_rr);
        final View findViewById13 = this.carControlContainer.findViewById(g4.p.door_hood);
        final View findViewById14 = this.carControlContainer.findViewById(g4.p.door_trunk);
        final View findViewById15 = this.carControlContainer.findViewById(g4.p.door_sunroof);
        final View findViewById16 = this.carControlContainer.findViewById(g4.p.door_sunshade);
        this.layoutLaneSpeed = this.carControlContainer.findViewById(g4.p.layout_lane_speed);
        this.tvLaneSpeedValue = (TextView) this.carControlContainer.findViewById(g4.p.text_lane_speed_value);
        this.tvLaneSpeedUnit = (TextView) this.carControlContainer.findViewById(g4.p.text_lane_speed_unit);
        this.tvTopGear = (TextView) this.carControlContainer.findViewById(g4.p.text_top_gear);
        this.imgLaneAnim = (ImageView) this.carControlContainer.findViewById(g4.p.img_lane_anim);
        View findViewById17 = this.carControlContainer.findViewById(g4.p.layout_tire_card);
        this.layoutTireCard = findViewById17;
        if (findViewById17 != null) {
            this.imgTireCard = (ImageView) findViewById17.findViewById(g4.p.img_tire_card);
            this.tvTireFlValue = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_fl_value);
            this.tvTireFrValue = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_fr_value);
            this.tvTireRlValue = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_rl_value);
            this.tvTireRrValue = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_rr_value);
            this.tvTireFlTemp = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_fl_temp);
            this.tvTireFrTemp = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_fr_temp);
            this.tvTireRlTemp = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_rl_temp);
            this.tvTireRrTemp = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_rr_temp);
            this.tvTireMeta = (TextView) this.layoutTireCard.findViewById(g4.p.tv_tire_meta);
            bindTireCardGesture();
        }
        o4.c.c().k(this.activity);
        o4.c.c().f7948p.e((androidx.lifecycle.x) this.activity, new t1(this, i9));
        if (findViewById7 != null && findViewById8 != null) {
            bindFrontCarModelSeatPanelDialog(findViewById7);
            bindFrontCarModelSeatPanelDialog(findViewById8);
            o4.c.c().f7938f.e((androidx.lifecycle.x) this.activity, new t1(this, i11));
            o4.c.c().f7939g.e((androidx.lifecycle.x) this.activity, new t1(this, 5));
            o4.c.c().f7958z.e((androidx.lifecycle.x) this.activity, new t1(this, 6));
            androidx.lifecycle.h0 h0Var = new androidx.lifecycle.h0() { // from class: com.navi.tool.ui.extra.j1
                @Override // androidx.lifecycle.h0
                public final void onChanged(Object obj) {
                    DesktopAssistantCarControlHelper.this.lambda$initCarControlViews$42(findViewById9, findViewById10, findViewById11, findViewById12, findViewById13, findViewById14, findViewById15, findViewById16, obj);
                }
            };
            o4.c.c().f7956x.e((androidx.lifecycle.x) this.activity, new l0(h0Var, 20));
            o4.c.c().C.e((androidx.lifecycle.x) this.activity, new l0(h0Var, 21));
        }
        o4.c.c().F.e((androidx.lifecycle.x) this.activity, new t1(this, 7));
        o4.c.c().D.e((androidx.lifecycle.x) this.activity, new t1(this, 8));
        o4.c.c().E.e((androidx.lifecycle.x) this.activity, new t1(this, 9));
        o4.c.c().G.e((androidx.lifecycle.x) this.activity, new t1(this, 10));
        refreshWirelessChargingState();
        return this.carControlContainer;
    }

    public void saveCarCardOrder() {
        CarControlAdapter carControlAdapter = this.carControlAdapter;
        if (carControlAdapter == null) {
            return;
        }
        List<CarControlAdapter.CardItem> data = carControlAdapter.getData();
        StringBuilder sb = new StringBuilder();
        for (CarControlAdapter.CardItem cardItem : data) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(cardItem.type.name());
        }
        this.prefs.edit().putString("car_card_order", sb.toString()).apply();
    }

    public void setCarModelClickListener(Runnable runnable) {
        this.carModelClickListener = runnable;
    }

    public void setState(boolean z6, boolean z7, boolean z8) {
        this.isCarControlEnabled = z6;
        this.isVisualCarModelEnabled = z7;
        this.isSeatComfortEnabled = z8;
    }

    public void showHvacModeGrid() {
        String[] strArr;
        int[] iArr;
        View inflate = LayoutInflater.from(this.activity).inflate(g4.q.dialog_hvac_mode_grid, (ViewGroup) null);
        GridLayout gridLayout = (GridLayout) inflate.findViewById(g4.p.grid_hvac_modes);
        int i7 = 0;
        int i8 = 1;
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            strArr = new String[]{"吹脸", "吹脚", "吹脸+吹脚", "除霜+吹脚", "除霜"};
            iArr = new int[]{1, 2, 3, 4, 5};
        } else {
            strArr = new String[]{"吹脸", "吹脸+吹脚", "吹脚", "除霜+吹脚", "除霜", "除霜+吹脸", "混合"};
            iArr = new int[]{0, 1, 2, 3, 4, 5, 6};
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.activity, R.style.Theme.DeviceDefault.Dialog);
        nVar.u(inflate);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        e7.getWindow().setType(2038);
        e7.setOnShowListener(new m1(e7, i8));
        for (int i9 = 0; i9 < strArr.length; i9++) {
            int i10 = iArr[i9];
            String str = strArr[i9];
            Button button = new Button(this.activity);
            button.setText(str);
            button.setTextColor(-1);
            button.setTextSize(22.0f);
            button.setGravity(17);
            button.setIncludeFontPadding(true);
            button.setSingleLine(true);
            button.setMaxLines(1);
            int i11 = (int) (this.activity.getResources().getDisplayMetrics().density * 112.0f);
            int i12 = (int) (this.activity.getResources().getDisplayMetrics().density * 14.0f);
            int i13 = (int) (this.activity.getResources().getDisplayMetrics().density * 8.0f);
            button.setMinWidth(i11);
            button.setMinimumWidth(i11);
            button.setPadding(i12, i13, i12, i13);
            button.setBackgroundResource(g4.o.bg_app_name_pill);
            int i14 = (int) (this.activity.getResources().getDisplayMetrics().density * 52.0f);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = -2;
            layoutParams.height = i14;
            layoutParams.setMargins(6, 6, 6, 6);
            button.setLayoutParams(layoutParams);
            button.setOnClickListener(new n1(this, i10, e7, i7));
            gridLayout.addView(button);
        }
        e7.show();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0561  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x05d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateCarControlCards() {
        /*
            Method dump skipped, instructions count: 1520
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.updateCarControlCards():void");
    }

    public void updateNoCardsVisibility() {
        if (this.tvNoCards == null) {
            return;
        }
        boolean z6 = this.prefs.getBoolean("car_control_ac_enabled", false);
        boolean z7 = this.prefs.getBoolean("car_control_door_enabled", false);
        boolean z8 = this.prefs.getBoolean("car_control_drive_enabled", false);
        boolean z9 = this.prefs.getBoolean("car_control_status_enabled", false);
        boolean z10 = this.prefs.getBoolean("seat_comfort_card_enabled", false);
        boolean z11 = this.prefs.getBoolean("car_control_ev_enabled", false);
        boolean z12 = this.prefs.getBoolean("fuel_card_enabled", false);
        boolean z13 = this.prefs.getBoolean("seat_comfort_panel_enabled", false);
        Set<String> effectiveSeatPanelActions = getEffectiveSeatPanelActions();
        boolean z14 = true;
        boolean z15 = z13 && effectiveSeatPanelActions.contains(ACTION_MEMORY_APPLY);
        boolean z16 = z13 && effectiveSeatPanelActions.contains(ACTION_MEMORY_SAVE);
        if (!z13 || (!effectiveSeatPanelActions.contains(ACTION_MASSAGE_DRIVER) && !effectiveSeatPanelActions.contains(ACTION_MASSAGE_PASS))) {
            z14 = false;
        }
        this.tvNoCards.setVisibility((z6 || z7 || z8 || z9 || z10 || z11 || z12 || z13 || z15 || z16 || z14) ? 8 : 0);
    }

    public void updateSeatComfort() {
        float f7;
        ensureSeatPanelActionsInitialized();
        CardView cardView = this.cardSeatControl;
        if (cardView != null) {
            cardView.setVisibility(this.isSeatComfortEnabled ? 0 : 8);
            if (this.isSeatComfortEnabled) {
                boolean z6 = this.prefs.getBoolean("desktop_seat_compat_mode", false);
                boolean z7 = this.prefs.getBoolean("lion_sync_service_enabled", false);
                Set<String> effectiveSeatPanelActions = getEffectiveSeatPanelActions();
                View view = this.seatHeatFlContainer;
                if (view != null) {
                    view.setVisibility(effectiveSeatPanelActions.contains(ACTION_HEAT_FL) ? 0 : 8);
                }
                View view2 = this.seatHeatFrContainer;
                if (view2 != null) {
                    view2.setVisibility(effectiveSeatPanelActions.contains(ACTION_HEAT_FR) ? 0 : 8);
                }
                View view3 = this.seatHeatRlContainer;
                if (view3 != null) {
                    view3.setVisibility(effectiveSeatPanelActions.contains(ACTION_HEAT_RL) ? 0 : 8);
                }
                View view4 = this.seatHeatRrContainer;
                if (view4 != null) {
                    view4.setVisibility(effectiveSeatPanelActions.contains(ACTION_HEAT_RR) ? 0 : 8);
                }
                View view5 = this.seatVentFlContainer;
                if (view5 != null) {
                    view5.setVisibility(effectiveSeatPanelActions.contains(ACTION_VENT_FL) ? 0 : 8);
                }
                View view6 = this.seatVentFrContainer;
                if (view6 != null) {
                    view6.setVisibility(effectiveSeatPanelActions.contains(ACTION_VENT_FR) ? 0 : 8);
                }
                View view7 = this.hvacAutoContainer;
                if (view7 != null) {
                    view7.setVisibility(effectiveSeatPanelActions.contains(ACTION_HVAC_AUTO) ? 0 : 8);
                }
                View view8 = this.hvacModeContainer;
                if (view8 != null) {
                    view8.setVisibility(effectiveSeatPanelActions.contains(ACTION_HVAC_MODE) ? 0 : 8);
                }
                View view9 = this.layoutHvacFanRow;
                if (view9 != null) {
                    view9.setVisibility(effectiveSeatPanelActions.contains(ACTION_HVAC_FAN) ? 0 : 8);
                }
                View view10 = this.layoutHvacRearFanRow;
                if (view10 != null) {
                    view10.setVisibility((z7 || !effectiveSeatPanelActions.contains(ACTION_HVAC_REAR_FAN)) ? 8 : 0);
                }
                boolean z8 = (z7 || !effectiveSeatPanelActions.contains(ACTION_STEERING_HEAT) || z6) ? false : true;
                boolean z9 = !z7 && effectiveSeatPanelActions.contains(ACTION_WIRELESS_CHARGING);
                boolean z10 = !z7 && effectiveSeatPanelActions.contains(ACTION_HVAC_CIRC);
                boolean z11 = (z7 || z6 || !effectiveSeatPanelActions.contains(ACTION_PEDESTRIAN_ALERT)) ? false : true;
                boolean z12 = (z7 || !effectiveSeatPanelActions.contains(ACTION_FRAGRANCE) || z6) ? false : true;
                boolean z13 = (z7 || z6 || !effectiveSeatPanelActions.contains(ACTION_WIPER_SENSITIVITY)) ? false : true;
                boolean z14 = (z7 || z6 || !effectiveSeatPanelActions.contains(ACTION_LIGHT_HEIGHT)) ? false : true;
                boolean z15 = (z7 || z6 || !effectiveSeatPanelActions.contains(ACTION_TRUNK)) ? false : true;
                View view11 = this.steeringHeatContainer;
                if (view11 != null) {
                    view11.setVisibility(z8 ? 0 : 8);
                }
                View view12 = this.wirelessChargingContainer;
                if (view12 != null) {
                    view12.setVisibility(z9 ? 0 : 8);
                }
                View view13 = this.hvacCircContainer;
                if (view13 != null) {
                    view13.setVisibility(z10 ? 0 : 8);
                }
                View view14 = this.pedestrianAlertContainer;
                if (view14 != null) {
                    view14.setVisibility(z11 ? 0 : 8);
                }
                View view15 = this.hvacFragranceContainer;
                if (view15 != null) {
                    view15.setVisibility(z12 ? 0 : 8);
                }
                View view16 = this.wiperSensitivityContainer;
                if (view16 != null) {
                    view16.setVisibility(z13 ? 0 : 8);
                }
                View view17 = this.lightHeightContainer;
                if (view17 != null) {
                    view17.setVisibility(z14 ? 0 : 8);
                }
                View view18 = this.trunkContainer;
                if (view18 != null) {
                    view18.setVisibility(z15 ? 0 : 8);
                }
                if (z15) {
                    updateTrunkIcon();
                }
                if (z11 && !this.pedestrianAlertQuerySent && CarInfoService.d() != null) {
                    this.pedestrianAlertQuerySent = true;
                    sendControlCommandInternal(MODULE_QUERY, 16, 102);
                    View view19 = this.pedestrianAlertContainer;
                    if (view19 != null) {
                        view19.postDelayed(new m0(2), 300L);
                    }
                }
            }
            this.cardSeatControl.setCardBackgroundColor(Color.argb((int) (((100 - this.prefs.getInt("car_control_card_alpha", 90)) / 100.0f) * 255.0f), 0, 0, 0));
            if (this.prefs.getBoolean("desktop_round_corner_enabled", false)) {
                try {
                    int identifier = this.activity.getResources().getIdentifier("car_control_card_corner_radius", "dimen", this.activity.getPackageName());
                    if (identifier != 0) {
                        f7 = this.activity.getResources().getDimension(identifier);
                    }
                } catch (Exception unused) {
                }
                f7 = 24.0f;
            } else {
                f7 = 12.0f;
            }
            this.cardSeatControl.setRadius(f7);
            int clampDesktopCardMarginDp = (int) (clampDesktopCardMarginDp(this.prefs.getInt("desktop_card_margin", 15)) * this.activity.getResources().getDisplayMetrics().density);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.cardSeatControl.getLayoutParams();
            if (marginLayoutParams != null) {
                marginLayoutParams.setMargins(clampDesktopCardMarginDp, clampDesktopCardMarginDp, clampDesktopCardMarginDp, clampDesktopCardMarginDp);
                this.cardSeatControl.setLayoutParams(marginLayoutParams);
            }
        }
        Set<String> effectiveSeatPanelActions2 = getEffectiveSeatPanelActions();
        boolean contains = effectiveSeatPanelActions2.contains(ACTION_MEMORY_APPLY);
        boolean contains2 = effectiveSeatPanelActions2.contains(ACTION_MEMORY_SAVE);
        boolean contains3 = effectiveSeatPanelActions2.contains(ACTION_MASSAGE_DRIVER);
        boolean contains4 = effectiveSeatPanelActions2.contains(ACTION_MASSAGE_PASS);
        if (this.isSeatComfortEnabled) {
            prepareInlineSeatPanelPoolItems(contains, contains2, contains3, contains4);
            renderUnifiedSeatPanelLayout();
        }
        CardView cardView2 = this.cardSeatMemory;
        if (cardView2 != null) {
            cardView2.setVisibility(8);
            View view20 = this.layoutSeatMemoryApply;
            if (view20 != null) {
                view20.setVisibility(8);
            }
            View view21 = this.layoutSeatMemorySave;
            if (view21 != null) {
                view21.setVisibility(8);
                View view22 = this.dividerSeatMemorySave;
                if (view22 != null) {
                    view22.setVisibility(8);
                }
            }
            this.cardSeatMemory.setCardBackgroundColor(Color.argb((int) (((100 - this.prefs.getInt("car_control_card_alpha", 90)) / 100.0f) * 255.0f), 0, 0, 0));
            this.cardSeatMemory.setRadius(this.prefs.getBoolean("desktop_round_corner_enabled", false) ? 24.0f : 12.0f);
            int clampDesktopCardMarginDp2 = (int) (clampDesktopCardMarginDp(this.prefs.getInt("desktop_card_margin", 15)) * this.activity.getResources().getDisplayMetrics().density);
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.cardSeatMemory.getLayoutParams();
            if (marginLayoutParams2 != null) {
                marginLayoutParams2.setMargins(clampDesktopCardMarginDp2, clampDesktopCardMarginDp2, clampDesktopCardMarginDp2, clampDesktopCardMarginDp2);
                this.cardSeatMemory.setLayoutParams(marginLayoutParams2);
            }
        }
        CardView cardView3 = this.cardSeatMassage;
        if (cardView3 != null) {
            cardView3.setVisibility(8);
            View view23 = this.layoutSeatMassage;
            if (view23 != null) {
                view23.setVisibility(8);
            }
            View view24 = this.dividerSeatMassage;
            if (view24 != null) {
                view24.setVisibility(8);
            }
            View view25 = this.btnSeatMassageDriver;
            if (view25 != null) {
                view25.setVisibility(8);
            }
            View view26 = this.btnSeatMassagePass;
            if (view26 != null) {
                view26.setVisibility(8);
            }
            this.cardSeatMassage.setCardBackgroundColor(Color.argb((int) (((100 - this.prefs.getInt("car_control_card_alpha", 90)) / 100.0f) * 255.0f), 0, 0, 0));
            this.cardSeatMassage.setRadius(this.prefs.getBoolean("desktop_round_corner_enabled", false) ? 24.0f : 12.0f);
            int clampDesktopCardMarginDp3 = (int) (clampDesktopCardMarginDp(this.prefs.getInt("desktop_card_margin", 15)) * this.activity.getResources().getDisplayMetrics().density);
            ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.cardSeatMassage.getLayoutParams();
            if (marginLayoutParams3 != null) {
                marginLayoutParams3.setMargins(clampDesktopCardMarginDp3, clampDesktopCardMarginDp3, clampDesktopCardMarginDp3, clampDesktopCardMarginDp3);
                this.cardSeatMassage.setLayoutParams(marginLayoutParams3);
            }
        }
        updateCarControlCards();
        updateNoCardsVisibility();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0204 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0232 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01f9 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateVisualCarModelVisibility() {
        /*
            Method dump skipped, instructions count: 586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantCarControlHelper.updateVisualCarModelVisibility():void");
    }
}
