package com.navi.tool.ui.extra;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;
import com.navi.tool.service.CarLanDashboardService;
import com.navi.tool.service.LionMediaBridgeService;
import com.navi.tool.service.MobileHudService;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.ui.theme.PositionSettingsDialog;
import com.navi.tool.utils.DialogImeNavBarHelper;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import java.io.File;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class CarLinkFragment extends androidx.fragment.app.z {
    private static final int REQUEST_HUD_LOCATION_BACKGROUND = 4102;
    private static final int REQUEST_HUD_LOCATION_FOREGROUND = 4101;
    private Button btnPhoneNotificationClusterPosition;
    private Button btnSetOilTankCapacity;
    private CheckBox checkMobileHudEta;
    private CheckBox checkMobileHudFuel;
    private CheckBox checkMobileHudGear;
    private CheckBox checkMobileHudLane;
    private CheckBox checkMobileHudLaneAnimation;
    private CheckBox checkMobileHudLyric;
    private CheckBox checkMobileHudPhoneNotification;
    private CheckBox checkMobileHudRange;
    private CheckBox checkMobileHudSoc;
    private CheckBox checkMobileHudSpeed;
    private CheckBox checkMobileHudTmc;
    private CheckBox checkMobileHudTrafficLight;
    private View dividerSeatHeatCompat;
    private View dividerSeatVentCompat;
    private View layoutAutoBrakeLinkCard;
    private View layoutAutoChildUnlock;
    private View layoutAutoDrivingMode;
    private View layoutAutoDrivingModeBootOnly;
    private View layoutAutoFragranceCard;
    private LinearLayout layoutAutoFragranceSettings;
    private View layoutAutoSteeringHeatCard;
    private LinearLayout layoutAutoSteeringHeatSettings;
    private LinearLayout layoutCarLinkFeatures;
    private View layoutCarLinkServiceCard;
    private View layoutCarlanDashboardCard;
    private LinearLayout layoutCarlanSongNameOnly;
    private View layoutChildLockReminder;
    private LinearLayout layoutDoorSoundSettings;
    private View layoutFragranceShortageAlarmCard;
    private LinearLayout layoutGearSoundSettings;
    private View layoutLionMediaBridgeCard;
    private View layoutLionSyncServiceCard;
    private View layoutMobileHudSettings;
    private View layoutOilRecord;
    private View layoutOilRecordSettings;
    private View layoutPhoneNotificationClusterPositionSettings;
    private View layoutPhoneNotificationSettings;
    private View layoutSeatHeatCompat;
    private View layoutSeatHeatRl;
    private View layoutSeatHeatRlDivider;
    private View layoutSeatHeatRr;
    private View layoutSeatHeatRrDivider;
    private LinearLayout layoutSeatHeatSettings;
    private View layoutSeatMemoryCard;
    private LinearLayout layoutSeatMemorySettings;
    private View layoutSeatVentCompat;
    private LinearLayout layoutSeatVentSettings;
    private View layoutTireCardDuration;
    private View layoutTireCardDynamic;
    private View layoutTirePressureAlarm;
    private View layoutWirelessChargingCapsuleCard;
    private final CompoundButton.OnCheckedChangeListener mobileHudExternalLightListener = new n(this, 2);
    private SharedPreferences prefs;
    private RadioGroup rgAutoFragranceType;
    private RadioGroup rgChildLockMode;
    private RadioGroup rgMobileHudConnectionMode;
    private RadioGroup rgMobileHudTextColor;
    private RadioGroup rgPhoneNotificationConnectionMode;
    private RadioGroup rgPhoneNotificationPosition;
    private RadioGroup rgSeatHeatCondition;
    private RadioGroup rgSeatMemoryPosition;
    private RadioGroup rgSeatVentCondition;
    private SeekBar sbTireCardDuration;
    private SeekBar seekAutoFragranceCloseMinutes;
    private SeekBar seekAutoFragranceLevel;
    private SeekBar seekMobileHudBrightness;
    private SeekBar seekMobileHudNightBrightness;
    private SeekBar seekPhoneNotificationBodyTextSize;
    private SeekBar seekPhoneNotificationDismiss;
    private SeekBar seekSeatHeat;
    private SeekBar seekSeatVent;
    private SeekBar seekSteeringHeat;
    private SwitchMaterial switchAmapSpeedSync;
    private SwitchMaterial switchAutoBrakeLink;
    private SwitchMaterial switchAutoChildUnlockByP;
    private SwitchMaterial switchAutoChildUnlockDriverDoor;
    private SwitchMaterial switchAutoChildUnlockPassengerDoor;
    private SwitchMaterial switchAutoChildUnlockPowerOff;
    private SwitchMaterial switchAutoDrivingMode;
    private SwitchMaterial switchAutoFragrance;
    private SwitchMaterial switchAutoFragranceShortageAlarm;
    private SwitchMaterial switchAutoSeatHeat;
    private SwitchMaterial switchAutoSeatMemory;
    private SwitchMaterial switchAutoSeatVent;
    private SwitchMaterial switchAutoSteeringHeat;
    private SwitchMaterial switchCarLinkService;
    private SwitchMaterial switchCarlanDashboard;
    private SwitchMaterial switchCarlanSongNameOnly;
    private SwitchMaterial switchChildLockReminder;
    private SwitchMaterial switchDoorSoundMaster;
    private SwitchMaterial switchGearSoundMaster;
    private SwitchMaterial switchLionMediaBridge;
    private SwitchMaterial switchLionSyncService;
    private SwitchMaterial switchMobileHud;
    private SwitchMaterial switchMobileHudUseBaidu;
    private SwitchMaterial switchMobileHudUseExternalLight;
    private SwitchMaterial switchOilRecord;
    private SwitchMaterial switchOilRecordCalendar;
    private SwitchMaterial switchPhoneNotification;
    private SwitchMaterial switchPhoneNotificationClickExpand;
    private SwitchMaterial switchPhoneNotificationClusterOverlay;
    private SwitchMaterial switchPhoneNotificationHideMainScreen;
    private SwitchMaterial switchPhoneNotificationSound;
    private SwitchMaterial switchTireCard;
    private SwitchMaterial switchTireCardDynamic;
    private SwitchMaterial switchTirePressureAlarm;
    private SwitchMaterial switchWirelessChargingCapsule;
    private TextView textAmapSpeedSyncStatus;
    private TextView textAutoFragranceCloseMinutes;
    private TextView textAutoFragranceLevel;
    private TextView textCarlanDashboardStatus;
    private TextView textLionMediaStatus;
    private TextView textLionServiceStatus;
    private TextView textMobileHudBaiduStatus;
    private TextView textMobileHudBrightness;
    private TextView textMobileHudNightBrightness;
    private TextView textOilTankCapacity;
    private TextView textPhoneNotificationBodyTextSize;
    private TextView textPhoneNotificationClusterPosition;
    private TextView textPhoneNotificationDismiss;
    private TextView textSeatHeatThreshold;
    private TextView textSeatVentThreshold;
    private TextView textServiceStatus;
    private TextView textSteeringHeatThreshold;
    private TextView tvTireCardDuration;

    /* JADX INFO: Access modifiers changed from: private */
    public int clampBrightness(int i7) {
        return Math.max(0, Math.min(100, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int clampPhoneNotificationBodyTextSize(int i7) {
        if (i7 < 18) {
            return 18;
        }
        if (i7 > 28) {
            return 28;
        }
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int clampPhoneNotificationDismissSeconds(int i7) {
        if (i7 < 3) {
            return 3;
        }
        if (i7 > 30) {
            return 30;
        }
        return i7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v40 */
    /* JADX WARN: Type inference failed for: r5v41, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v43 */
    /* JADX WARN: Type inference failed for: r6v42 */
    /* JADX WARN: Type inference failed for: r6v43, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v44 */
    private void initLogic(View view) {
        String str;
        boolean z6;
        ?? r6;
        final ?? r52;
        boolean z7 = this.prefs.getBoolean("car_link_service_enabled", false);
        boolean z8 = this.prefs.getBoolean("lion_sync_service_enabled", false);
        boolean z9 = this.prefs.getBoolean(MobileHudService.PREF_PHONE_NOTIFICATION_ENABLED, false);
        boolean z10 = this.prefs.getBoolean(MobileHudService.PREF_ENABLED, false);
        boolean z11 = this.prefs.getBoolean(o4.r.PREF_OIL_RECORD_ENABLED, false);
        boolean z12 = this.prefs.getBoolean(o4.r.PREF_OIL_RECORD_CALENDAR_ENABLED, false);
        this.switchCarLinkService.setChecked(z7);
        this.textServiceStatus.setVisibility(z7 ? 0 : 8);
        this.switchLionSyncService.setChecked(z8);
        this.textLionServiceStatus.setVisibility(z8 ? 0 : 8);
        this.switchOilRecord.setChecked(z11);
        this.switchOilRecordCalendar.setChecked(z12);
        updateOilRecordSettingsVisibility(z11 && !z8);
        updateOilTankCapacityText();
        this.switchPhoneNotification.setChecked(z9);
        updatePhoneNotificationSettingsVisibility(z9);
        this.switchMobileHud.setChecked(z10);
        boolean z13 = this.prefs.getBoolean(MobileHudService.PREF_USE_BAIDU_SOURCE, false);
        this.switchMobileHudUseBaidu.setChecked(z13);
        if (z13) {
            a.a.m(this.prefs, MobileHudService.PREF_USE_EXTERNAL_TRAFFIC_LIGHT, false);
            this.switchMobileHudUseExternalLight.setChecked(false);
            this.switchMobileHudUseExternalLight.setEnabled(false);
        } else {
            this.switchMobileHudUseExternalLight.setChecked(this.prefs.getBoolean(MobileHudService.PREF_USE_EXTERNAL_TRAFFIC_LIGHT, false));
            this.switchMobileHudUseExternalLight.setEnabled(true);
        }
        this.layoutMobileHudSettings.setVisibility(z10 ? 0 : 8);
        this.checkMobileHudSpeed.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_SPEED, true));
        this.checkMobileHudGear.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_GEAR, true));
        this.checkMobileHudFuel.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_FUEL, true));
        this.checkMobileHudRange.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_RANGE, true));
        this.checkMobileHudPhoneNotification.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_PHONE_NOTIFICATION, true));
        this.checkMobileHudTrafficLight.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_TRAFFIC_LIGHT, true));
        this.checkMobileHudSoc.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_SOC, false));
        this.checkMobileHudLane.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_LANE, true));
        this.checkMobileHudEta.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_ETA, true));
        this.checkMobileHudLyric.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_LYRIC, true));
        this.checkMobileHudTmc.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_TMC, true));
        this.checkMobileHudLaneAnimation.setChecked(this.prefs.getBoolean(MobileHudService.PREF_SHOW_LANE_ANIMATION, true));
        updateMobileHudConnectionModeSelection(this.prefs.getString(MobileHudService.PREF_CONNECTION_MODE, MobileHudService.CONNECTION_MODE_BLUETOOTH));
        updateMobileHudTextColorSelection(this.prefs.getString(MobileHudService.PREF_TEXT_COLOR, MobileHudService.TEXT_COLOR_WHITE));
        updatePhoneNotificationPositionSelection(this.prefs.getString(PhoneNotificationRelayService.PREF_OVERLAY_POSITION, PhoneNotificationRelayService.OVERLAY_POSITION_LEFT));
        SwitchMaterial switchMaterial = this.switchPhoneNotificationClickExpand;
        if (switchMaterial != null) {
            SharedPreferences sharedPreferences = this.prefs;
            str = MobileHudService.PREF_SHOW_ETA;
            switchMaterial.setChecked(sharedPreferences.getBoolean(PhoneNotificationRelayService.PREF_CLICK_EXPAND_ENABLED, true));
        } else {
            str = MobileHudService.PREF_SHOW_ETA;
        }
        SwitchMaterial switchMaterial2 = this.switchPhoneNotificationHideMainScreen;
        if (switchMaterial2 != null) {
            z6 = false;
            switchMaterial2.setChecked(this.prefs.getBoolean(PhoneNotificationRelayService.PREF_HIDE_MAIN_SCREEN_OVERLAY, false));
        } else {
            z6 = false;
        }
        SwitchMaterial switchMaterial3 = this.switchPhoneNotificationClusterOverlay;
        if (switchMaterial3 != null) {
            switchMaterial3.setChecked(this.prefs.getBoolean(PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_ENABLED, z6));
        }
        SwitchMaterial switchMaterial4 = this.switchPhoneNotificationSound;
        if (switchMaterial4 != null) {
            switchMaterial4.setChecked(this.prefs.getBoolean(PhoneNotificationRelayService.PREF_SOUND_ENABLED, z6));
        }
        SwitchMaterial switchMaterial5 = this.switchTireCard;
        if (switchMaterial5 != null) {
            switchMaterial5.setChecked(this.prefs.getBoolean(PhoneNotificationRelayService.PREF_TIRE_CARD_ENABLED, z6));
        }
        SwitchMaterial switchMaterial6 = this.switchTireCardDynamic;
        if (switchMaterial6 != null) {
            switchMaterial6.setChecked(this.prefs.getBoolean(PhoneNotificationRelayService.PREF_TIRE_CARD_DYNAMIC_ENABLED, z6));
        }
        if (this.sbTireCardDuration != null) {
            int max = Math.max(10, Math.min(60, this.prefs.getInt(PhoneNotificationRelayService.PREF_TIRE_CARD_DYNAMIC_SECONDS, 30)));
            this.sbTireCardDuration.setMax(50);
            this.sbTireCardDuration.setProgress(max - 10);
            updateTireCardDurationText(max);
        }
        SwitchMaterial switchMaterial7 = this.switchTireCard;
        updateTireCardSettingsVisibility(switchMaterial7 != null && switchMaterial7.isChecked());
        SwitchMaterial switchMaterial8 = this.switchPhoneNotificationClusterOverlay;
        updatePhoneNotificationClusterPositionSettingsVisibility(switchMaterial8 != null && switchMaterial8.isChecked());
        updatePhoneNotificationClusterPositionText();
        int clampPhoneNotificationDismissSeconds = clampPhoneNotificationDismissSeconds(this.prefs.getInt(PhoneNotificationRelayService.PREF_OVERLAY_DISMISS_SECONDS, 5));
        SeekBar seekBar = this.seekPhoneNotificationDismiss;
        if (seekBar != null) {
            seekBar.setProgress(clampPhoneNotificationDismissSeconds - 3);
        }
        updatePhoneNotificationDismissText(clampPhoneNotificationDismissSeconds);
        int clampPhoneNotificationBodyTextSize = clampPhoneNotificationBodyTextSize(this.prefs.getInt(PhoneNotificationRelayService.PREF_OVERLAY_BODY_TEXT_SIZE_SP, 22));
        SeekBar seekBar2 = this.seekPhoneNotificationBodyTextSize;
        if (seekBar2 != null) {
            seekBar2.setProgress(clampPhoneNotificationBodyTextSize - 18);
        }
        updatePhoneNotificationBodyTextSizeText(clampPhoneNotificationBodyTextSize);
        updatePhoneNotificationConnectionModeSelection(this.prefs.getString(PhoneNotificationRelayService.PREF_CONNECTION_MODE, MobileHudService.CONNECTION_MODE_BLUETOOTH));
        int clampBrightness = clampBrightness(this.prefs.getInt(MobileHudService.PREF_PHONE_BRIGHTNESS, 100));
        int clampBrightness2 = clampBrightness(this.prefs.getInt(MobileHudService.PREF_NIGHT_BRIGHTNESS, 50));
        SeekBar seekBar3 = this.seekMobileHudBrightness;
        if (seekBar3 != null) {
            seekBar3.setProgress(clampBrightness);
        }
        SeekBar seekBar4 = this.seekMobileHudNightBrightness;
        if (seekBar4 != null) {
            seekBar4.setProgress(clampBrightness2);
        }
        updateMobileHudBrightnessText(clampBrightness);
        updateMobileHudNightBrightnessText(clampBrightness2);
        this.layoutCarLinkFeatures.setVisibility((z7 || z8) ? 0 : 8);
        updateUnsupportedFeaturesVisibility(z8);
        updateServiceCardsVisibility(z7, z8);
        if (z7) {
            startService();
        } else if (z8) {
            startLionService();
        }
        MobileHudService.syncService(requireContext());
        PhoneNotificationRelayService.syncService(requireContext());
        a.a.p(this, 12, this.switchCarLinkService);
        a.a.p(this, 18, this.switchLionSyncService);
        boolean z14 = this.prefs.getBoolean("lion_media_bridge_enabled", false);
        this.switchLionMediaBridge.setChecked(z14);
        this.textLionMediaStatus.setVisibility(z14 ? 0 : 8);
        this.switchLionMediaBridge.setOnCheckedChangeListener(new n(this, 26));
        final int i7 = 4;
        o4.c.c().f7934b.e(getViewLifecycleOwner(), new o(this, i7));
        boolean z15 = this.prefs.getBoolean("carlan_dashboard_enabled", false);
        this.switchCarlanDashboard.setChecked(z15);
        this.textCarlanDashboardStatus.setVisibility(z15 ? 0 : 8);
        LinearLayout linearLayout = this.layoutCarlanSongNameOnly;
        if (linearLayout != null) {
            linearLayout.setVisibility(z15 ? 0 : 8);
        }
        final int i8 = 3;
        this.switchCarlanDashboard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarLinkFragment f5328b;

            {
                this.f5328b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z16) {
                int i9 = i8;
                CarLinkFragment carLinkFragment = this.f5328b;
                switch (i9) {
                    case 0:
                        carLinkFragment.lambda$initLogic$38(compoundButton, z16);
                        break;
                    case 1:
                        carLinkFragment.lambda$initLogic$39(compoundButton, z16);
                        break;
                    case 2:
                        carLinkFragment.lambda$initLogic$40(compoundButton, z16);
                        break;
                    case 3:
                        carLinkFragment.lambda$initLogic$7(compoundButton, z16);
                        break;
                    case 4:
                        carLinkFragment.lambda$initLogic$8(compoundButton, z16);
                        break;
                    case 5:
                        carLinkFragment.lambda$initLogic$10(compoundButton, z16);
                        break;
                    default:
                        carLinkFragment.lambda$initLogic$11(compoundButton, z16);
                        break;
                }
            }
        });
        if (this.switchCarlanSongNameOnly != null) {
            this.switchCarlanSongNameOnly.setChecked(this.prefs.getBoolean("carlan_song_name_only", false));
            this.switchCarlanSongNameOnly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ CarLinkFragment f5328b;

                {
                    this.f5328b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z16) {
                    int i9 = i7;
                    CarLinkFragment carLinkFragment = this.f5328b;
                    switch (i9) {
                        case 0:
                            carLinkFragment.lambda$initLogic$38(compoundButton, z16);
                            break;
                        case 1:
                            carLinkFragment.lambda$initLogic$39(compoundButton, z16);
                            break;
                        case 2:
                            carLinkFragment.lambda$initLogic$40(compoundButton, z16);
                            break;
                        case 3:
                            carLinkFragment.lambda$initLogic$7(compoundButton, z16);
                            break;
                        case 4:
                            carLinkFragment.lambda$initLogic$8(compoundButton, z16);
                            break;
                        case 5:
                            carLinkFragment.lambda$initLogic$10(compoundButton, z16);
                            break;
                        default:
                            carLinkFragment.lambda$initLogic$11(compoundButton, z16);
                            break;
                    }
                }
            });
        }
        o4.c.c().f7935c.e(getViewLifecycleOwner(), new o(this, 5));
        boolean z16 = this.prefs.getBoolean("amap_speed_sync_enabled", false);
        this.switchAmapSpeedSync.setChecked(z16);
        this.textAmapSpeedSyncStatus.setVisibility(z16 ? 0 : 8);
        final int i9 = 5;
        this.switchAmapSpeedSync.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarLinkFragment f5328b;

            {
                this.f5328b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z162) {
                int i92 = i9;
                CarLinkFragment carLinkFragment = this.f5328b;
                switch (i92) {
                    case 0:
                        carLinkFragment.lambda$initLogic$38(compoundButton, z162);
                        break;
                    case 1:
                        carLinkFragment.lambda$initLogic$39(compoundButton, z162);
                        break;
                    case 2:
                        carLinkFragment.lambda$initLogic$40(compoundButton, z162);
                        break;
                    case 3:
                        carLinkFragment.lambda$initLogic$7(compoundButton, z162);
                        break;
                    case 4:
                        carLinkFragment.lambda$initLogic$8(compoundButton, z162);
                        break;
                    case 5:
                        carLinkFragment.lambda$initLogic$10(compoundButton, z162);
                        break;
                    default:
                        carLinkFragment.lambda$initLogic$11(compoundButton, z162);
                        break;
                }
            }
        });
        this.switchWirelessChargingCapsule.setChecked(this.prefs.getBoolean("wireless_charging_capsule_enabled", true));
        final int i10 = 6;
        this.switchWirelessChargingCapsule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ CarLinkFragment f5328b;

            {
                this.f5328b = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z162) {
                int i92 = i10;
                CarLinkFragment carLinkFragment = this.f5328b;
                switch (i92) {
                    case 0:
                        carLinkFragment.lambda$initLogic$38(compoundButton, z162);
                        break;
                    case 1:
                        carLinkFragment.lambda$initLogic$39(compoundButton, z162);
                        break;
                    case 2:
                        carLinkFragment.lambda$initLogic$40(compoundButton, z162);
                        break;
                    case 3:
                        carLinkFragment.lambda$initLogic$7(compoundButton, z162);
                        break;
                    case 4:
                        carLinkFragment.lambda$initLogic$8(compoundButton, z162);
                        break;
                    case 5:
                        carLinkFragment.lambda$initLogic$10(compoundButton, z162);
                        break;
                    default:
                        carLinkFragment.lambda$initLogic$11(compoundButton, z162);
                        break;
                }
            }
        });
        o4.c.c().f7936d.e(getViewLifecycleOwner(), new o(this, i10));
        o4.c.c().f7933a.e(getViewLifecycleOwner(), new o(this, 1));
        Button button = this.btnSetOilTankCapacity;
        if (button != null) {
            final int i11 = 0;
            button.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.u

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ CarLinkFragment f5480b;

                {
                    this.f5480b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i12 = i11;
                    CarLinkFragment carLinkFragment = this.f5480b;
                    switch (i12) {
                        case 0:
                            carLinkFragment.lambda$initLogic$14(view2);
                            break;
                        default:
                            carLinkFragment.lambda$initLogic$31(view2);
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial9 = this.switchOilRecord;
        if (switchMaterial9 != null) {
            a.a.p(this, 13, switchMaterial9);
        }
        SwitchMaterial switchMaterial10 = this.switchOilRecordCalendar;
        if (switchMaterial10 != null) {
            a.a.p(this, 14, switchMaterial10);
        }
        a.a.p(this, 15, this.switchPhoneNotification);
        a.a.p(this, 16, this.switchMobileHud);
        a.a.p(this, 17, this.switchMobileHudUseBaidu);
        this.switchMobileHudUseExternalLight.setOnCheckedChangeListener(this.mobileHudExternalLightListener);
        o4.c.c().f7937e.e(getViewLifecycleOwner(), new o(this, 2));
        setupMobileHudCheckBox(this.checkMobileHudSpeed, MobileHudService.PREF_SHOW_SPEED);
        setupMobileHudCheckBox(this.checkMobileHudGear, MobileHudService.PREF_SHOW_GEAR);
        setupMobileHudCheckBox(this.checkMobileHudFuel, MobileHudService.PREF_SHOW_FUEL);
        setupMobileHudCheckBox(this.checkMobileHudRange, MobileHudService.PREF_SHOW_RANGE);
        setupMobileHudCheckBox(this.checkMobileHudPhoneNotification, MobileHudService.PREF_SHOW_PHONE_NOTIFICATION);
        setupMobileHudCheckBox(this.checkMobileHudTrafficLight, MobileHudService.PREF_SHOW_TRAFFIC_LIGHT);
        setupMobileHudCheckBox(this.checkMobileHudSoc, MobileHudService.PREF_SHOW_SOC);
        setupMobileHudCheckBox(this.checkMobileHudLane, MobileHudService.PREF_SHOW_LANE);
        setupMobileHudCheckBox(this.checkMobileHudEta, str);
        setupMobileHudCheckBox(this.checkMobileHudLyric, MobileHudService.PREF_SHOW_LYRIC);
        setupMobileHudCheckBox(this.checkMobileHudTmc, MobileHudService.PREF_SHOW_TMC);
        setupMobileHudCheckBox(this.checkMobileHudLaneAnimation, MobileHudService.PREF_SHOW_LANE_ANIMATION);
        RadioGroup radioGroup = this.rgMobileHudConnectionMode;
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new r(this, i7));
        }
        RadioGroup radioGroup2 = this.rgMobileHudTextColor;
        if (radioGroup2 != null) {
            radioGroup2.setOnCheckedChangeListener(new r(this, 5));
        }
        RadioGroup radioGroup3 = this.rgPhoneNotificationPosition;
        if (radioGroup3 != null) {
            radioGroup3.setOnCheckedChangeListener(new r(this, i10));
        }
        SwitchMaterial switchMaterial11 = this.switchPhoneNotificationClickExpand;
        if (switchMaterial11 != null) {
            a.a.p(this, 19, switchMaterial11);
        }
        SwitchMaterial switchMaterial12 = this.switchPhoneNotificationHideMainScreen;
        if (switchMaterial12 != null) {
            a.a.p(this, 20, switchMaterial12);
        }
        SwitchMaterial switchMaterial13 = this.switchPhoneNotificationClusterOverlay;
        if (switchMaterial13 != null) {
            a.a.p(this, 21, switchMaterial13);
        }
        SwitchMaterial switchMaterial14 = this.switchPhoneNotificationSound;
        if (switchMaterial14 != null) {
            a.a.p(this, 22, switchMaterial14);
        }
        SwitchMaterial switchMaterial15 = this.switchTireCard;
        if (switchMaterial15 != null) {
            a.a.p(this, 23, switchMaterial15);
        }
        SwitchMaterial switchMaterial16 = this.switchTireCardDynamic;
        if (switchMaterial16 != null) {
            a.a.p(this, 24, switchMaterial16);
        }
        SeekBar seekBar5 = this.sbTireCardDuration;
        if (seekBar5 != null) {
            seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar6, int i12, boolean z17) {
                    int i13 = i12 + 10;
                    CarLinkFragment.this.updateTireCardDurationText(i13);
                    if (z17) {
                        CarLinkFragment.this.prefs.edit().putInt(PhoneNotificationRelayService.PREF_TIRE_CARD_DYNAMIC_SECONDS, i13).apply();
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar6) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar6) {
                }
            });
        }
        Button button2 = this.btnPhoneNotificationClusterPosition;
        if (button2 != null) {
            final int i12 = 1;
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.u

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ CarLinkFragment f5480b;

                {
                    this.f5480b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    int i122 = i12;
                    CarLinkFragment carLinkFragment = this.f5480b;
                    switch (i122) {
                        case 0:
                            carLinkFragment.lambda$initLogic$14(view2);
                            break;
                        default:
                            carLinkFragment.lambda$initLogic$31(view2);
                            break;
                    }
                }
            });
        }
        SeekBar seekBar6 = this.seekPhoneNotificationDismiss;
        if (seekBar6 != null) {
            seekBar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.2
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar7, int i13, boolean z17) {
                    int clampPhoneNotificationDismissSeconds2 = CarLinkFragment.this.clampPhoneNotificationDismissSeconds(i13 + 3);
                    CarLinkFragment.this.updatePhoneNotificationDismissText(clampPhoneNotificationDismissSeconds2);
                    if (z17) {
                        CarLinkFragment.this.prefs.edit().putInt(PhoneNotificationRelayService.PREF_OVERLAY_DISMISS_SECONDS, clampPhoneNotificationDismissSeconds2).apply();
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar7) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar7) {
                }
            });
        }
        SeekBar seekBar7 = this.seekPhoneNotificationBodyTextSize;
        if (seekBar7 != null) {
            seekBar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.3
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar8, int i13, boolean z17) {
                    int clampPhoneNotificationBodyTextSize2 = CarLinkFragment.this.clampPhoneNotificationBodyTextSize(i13 + 18);
                    CarLinkFragment.this.updatePhoneNotificationBodyTextSizeText(clampPhoneNotificationBodyTextSize2);
                    if (z17) {
                        CarLinkFragment.this.prefs.edit().putInt(PhoneNotificationRelayService.PREF_OVERLAY_BODY_TEXT_SIZE_SP, clampPhoneNotificationBodyTextSize2).apply();
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar8) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar8) {
                }
            });
        }
        RadioGroup radioGroup4 = this.rgPhoneNotificationConnectionMode;
        if (radioGroup4 != null) {
            radioGroup4.setOnCheckedChangeListener(new r(this, 7));
        }
        SeekBar seekBar8 = this.seekMobileHudBrightness;
        if (seekBar8 != null) {
            seekBar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.4
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar9, int i13, boolean z17) {
                    int clampBrightness3 = CarLinkFragment.this.clampBrightness(i13);
                    CarLinkFragment.this.updateMobileHudBrightnessText(clampBrightness3);
                    if (z17) {
                        CarLinkFragment.this.prefs.edit().putInt(MobileHudService.PREF_PHONE_BRIGHTNESS, clampBrightness3).apply();
                        MobileHudService.syncService(CarLinkFragment.this.requireContext());
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar9) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar9) {
                }
            });
        }
        SeekBar seekBar9 = this.seekMobileHudNightBrightness;
        if (seekBar9 != null) {
            seekBar9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.5
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar10, int i13, boolean z17) {
                    int clampBrightness3 = CarLinkFragment.this.clampBrightness(i13);
                    CarLinkFragment.this.updateMobileHudNightBrightnessText(clampBrightness3);
                    if (z17) {
                        CarLinkFragment.this.prefs.edit().putInt(MobileHudService.PREF_NIGHT_BRIGHTNESS, clampBrightness3).apply();
                        MobileHudService.syncService(CarLinkFragment.this.requireContext());
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar10) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar10) {
                }
            });
        }
        this.switchTirePressureAlarm.setChecked(this.prefs.getBoolean("tire_pressure_alarm_enabled", false));
        a.a.p(this, 25, this.switchTirePressureAlarm);
        boolean z17 = this.prefs.getBoolean("auto_driving_mode_enabled", false);
        this.switchAutoDrivingMode.setChecked(z17);
        View findViewById = view.findViewById(g4.p.layout_driving_mode_boot_only);
        SwitchMaterial switchMaterial17 = (SwitchMaterial) view.findViewById(g4.p.switch_auto_driving_mode_boot_only);
        if (findViewById != null) {
            findViewById.setVisibility(z17 ? 0 : 8);
        }
        if (switchMaterial17 != null) {
            r6 = 0;
            switchMaterial17.setChecked(this.prefs.getBoolean("auto_driving_mode_boot_only", false));
            a.a.p(this, 27, switchMaterial17);
        } else {
            r6 = 0;
        }
        this.switchAutoDrivingMode.setOnCheckedChangeListener(new v(r6, this, findViewById));
        boolean z18 = this.prefs.getBoolean("child_lock_reminder_enabled", r6);
        this.switchChildLockReminder.setChecked(z18);
        View view2 = this.layoutAutoChildUnlock;
        if (view2 != null) {
            view2.setVisibility(z18 ? 0 : 8);
        }
        a.a.p(this, 28, this.switchChildLockReminder);
        SwitchMaterial switchMaterial18 = this.switchAutoChildUnlockDriverDoor;
        if (switchMaterial18 != null) {
            r52 = 0;
            switchMaterial18.setChecked(this.prefs.getBoolean("auto_child_unlock_driver_door", false));
            a.a.p(this, 29, this.switchAutoChildUnlockDriverDoor);
        } else {
            r52 = 0;
        }
        SwitchMaterial switchMaterial19 = this.switchAutoChildUnlockPassengerDoor;
        if (switchMaterial19 != null) {
            switchMaterial19.setChecked(this.prefs.getBoolean("auto_child_unlock_passenger_door", r52));
            this.switchAutoChildUnlockPassengerDoor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ CarLinkFragment f5328b;

                {
                    this.f5328b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z162) {
                    int i92 = r52;
                    CarLinkFragment carLinkFragment = this.f5328b;
                    switch (i92) {
                        case 0:
                            carLinkFragment.lambda$initLogic$38(compoundButton, z162);
                            break;
                        case 1:
                            carLinkFragment.lambda$initLogic$39(compoundButton, z162);
                            break;
                        case 2:
                            carLinkFragment.lambda$initLogic$40(compoundButton, z162);
                            break;
                        case 3:
                            carLinkFragment.lambda$initLogic$7(compoundButton, z162);
                            break;
                        case 4:
                            carLinkFragment.lambda$initLogic$8(compoundButton, z162);
                            break;
                        case 5:
                            carLinkFragment.lambda$initLogic$10(compoundButton, z162);
                            break;
                        default:
                            carLinkFragment.lambda$initLogic$11(compoundButton, z162);
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial20 = this.switchAutoChildUnlockByP;
        if (switchMaterial20 != null) {
            switchMaterial20.setChecked(this.prefs.getBoolean("auto_child_unlock_by_p", r52));
            final int i13 = 1;
            this.switchAutoChildUnlockByP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ CarLinkFragment f5328b;

                {
                    this.f5328b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z162) {
                    int i92 = i13;
                    CarLinkFragment carLinkFragment = this.f5328b;
                    switch (i92) {
                        case 0:
                            carLinkFragment.lambda$initLogic$38(compoundButton, z162);
                            break;
                        case 1:
                            carLinkFragment.lambda$initLogic$39(compoundButton, z162);
                            break;
                        case 2:
                            carLinkFragment.lambda$initLogic$40(compoundButton, z162);
                            break;
                        case 3:
                            carLinkFragment.lambda$initLogic$7(compoundButton, z162);
                            break;
                        case 4:
                            carLinkFragment.lambda$initLogic$8(compoundButton, z162);
                            break;
                        case 5:
                            carLinkFragment.lambda$initLogic$10(compoundButton, z162);
                            break;
                        default:
                            carLinkFragment.lambda$initLogic$11(compoundButton, z162);
                            break;
                    }
                }
            });
        }
        SwitchMaterial switchMaterial21 = this.switchAutoChildUnlockPowerOff;
        if (switchMaterial21 != null) {
            switchMaterial21.setChecked(this.prefs.getBoolean("auto_child_unlock_power_off", false));
            final int i14 = 2;
            this.switchAutoChildUnlockPowerOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.navi.tool.ui.extra.m

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ CarLinkFragment f5328b;

                {
                    this.f5328b = this;
                }

                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z162) {
                    int i92 = i14;
                    CarLinkFragment carLinkFragment = this.f5328b;
                    switch (i92) {
                        case 0:
                            carLinkFragment.lambda$initLogic$38(compoundButton, z162);
                            break;
                        case 1:
                            carLinkFragment.lambda$initLogic$39(compoundButton, z162);
                            break;
                        case 2:
                            carLinkFragment.lambda$initLogic$40(compoundButton, z162);
                            break;
                        case 3:
                            carLinkFragment.lambda$initLogic$7(compoundButton, z162);
                            break;
                        case 4:
                            carLinkFragment.lambda$initLogic$8(compoundButton, z162);
                            break;
                        case 5:
                            carLinkFragment.lambda$initLogic$10(compoundButton, z162);
                            break;
                        default:
                            carLinkFragment.lambda$initLogic$11(compoundButton, z162);
                            break;
                    }
                }
            });
        }
        if (this.rgChildLockMode != null) {
            this.rgChildLockMode.check(this.prefs.getInt("child_lock_lock_mode", 0) == 1 ? g4.p.rb_child_lock_mode_manual : g4.p.rb_child_lock_mode_auto);
            this.rgChildLockMode.setOnCheckedChangeListener(new r(this, 8));
        }
        o4.c.c().f7933a.e(getViewLifecycleOwner(), new o(this, i8));
    }

    private void initSeatClimateSettings(View view) {
        int i7 = 0;
        boolean z6 = this.prefs.getBoolean("auto_seat_heat_enabled", false);
        this.switchAutoSeatHeat.setChecked(z6);
        this.layoutSeatHeatSettings.setVisibility(z6 ? 0 : 8);
        int i8 = 3;
        a.a.p(this, 3, this.switchAutoSeatHeat);
        SwitchMaterial switchMaterial = (SwitchMaterial) view.findViewById(g4.p.switch_seat_heat_compat);
        if (switchMaterial != null) {
            switchMaterial.setChecked(this.prefs.getBoolean("seat_heat_compat_mode", false));
            a.a.p(this, 6, switchMaterial);
        }
        int i9 = 1;
        if (this.prefs.getInt("seat_heat_trigger_condition", 1) == 1) {
            this.rgSeatHeatCondition.check(g4.p.rb_heat_engine);
        } else {
            this.rgSeatHeatCondition.check(g4.p.rb_heat_driving);
        }
        this.rgSeatHeatCondition.setOnCheckedChangeListener(new r(this, i9));
        int i10 = (int) this.prefs.getFloat("seat_heat_threshold", 10.0f);
        this.seekSeatHeat.setProgress(i10 + 10);
        a.a.v("当车外温度小于 ", i10, "°C 执行动作", this.textSeatHeatThreshold);
        this.seekSeatHeat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.6
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i11, boolean z7) {
                int i12 = i11 - 10;
                a.a.v("当车外温度小于 ", i12, "°C 执行动作", CarLinkFragment.this.textSeatHeatThreshold);
                CarLinkFragment.this.prefs.edit().putFloat("seat_heat_threshold", i12).apply();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        setupSeatSwitch(view.findViewById(g4.p.setting_seat_heat_driver), "主驾加热", "seat_heat_target_driver", "seat_heat_level_driver");
        setupSeatSwitch(view.findViewById(g4.p.setting_seat_heat_passenger), "副驾加热", "seat_heat_target_passenger", "seat_heat_level_passenger");
        setupSeatSwitch(view.findViewById(g4.p.setting_seat_heat_rl), "左后加热", "seat_heat_target_rl", "seat_heat_level_rl");
        setupSeatSwitch(view.findViewById(g4.p.setting_seat_heat_rr), "右后加热", "seat_heat_target_rr", "seat_heat_level_rr");
        boolean z7 = this.prefs.getBoolean("auto_seat_vent_enabled", false);
        this.switchAutoSeatVent.setChecked(z7);
        this.layoutSeatVentSettings.setVisibility(z7 ? 0 : 8);
        a.a.p(this, 7, this.switchAutoSeatVent);
        SwitchMaterial switchMaterial2 = (SwitchMaterial) view.findViewById(g4.p.switch_seat_vent_compat);
        if (switchMaterial2 != null) {
            switchMaterial2.setChecked(this.prefs.getBoolean("seat_vent_compat_mode", false));
            a.a.p(this, 8, switchMaterial2);
        }
        if (this.prefs.getInt("seat_vent_trigger_condition", 1) == 1) {
            this.rgSeatVentCondition.check(g4.p.rb_vent_engine);
        } else {
            this.rgSeatVentCondition.check(g4.p.rb_vent_driving);
        }
        this.rgSeatVentCondition.setOnCheckedChangeListener(new r(this, 2));
        int i11 = (int) this.prefs.getFloat("seat_vent_threshold", 20.0f);
        this.seekSeatVent.setProgress(i11);
        a.a.v("当车外温度大于 ", i11, "°C 执行动作", this.textSeatVentThreshold);
        this.seekSeatVent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i12, boolean z8) {
                a.a.v("当车外温度大于 ", i12, "°C 执行动作", CarLinkFragment.this.textSeatVentThreshold);
                CarLinkFragment.this.prefs.edit().putFloat("seat_vent_threshold", i12).apply();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        setupSeatSwitch(view.findViewById(g4.p.setting_seat_vent_driver), "主驾通风", "seat_vent_target_driver", "seat_vent_level_driver");
        setupSeatSwitch(view.findViewById(g4.p.setting_seat_vent_passenger), "副驾通风", "seat_vent_target_passenger", "seat_vent_level_passenger");
        boolean z8 = this.prefs.getBoolean("auto_seat_memory_enabled", false);
        this.switchAutoSeatMemory.setChecked(z8);
        this.layoutSeatMemorySettings.setVisibility(z8 ? 0 : 8);
        a.a.p(this, 9, this.switchAutoSeatMemory);
        int i12 = this.prefs.getInt("seat_memory_position", 1);
        if (i12 < 1 || i12 > 3) {
            i12 = 1;
        }
        if (i12 == 1) {
            this.rgSeatMemoryPosition.check(g4.p.rb_seat_memory_1);
        } else if (i12 == 2) {
            this.rgSeatMemoryPosition.check(g4.p.rb_seat_memory_2);
        } else {
            this.rgSeatMemoryPosition.check(g4.p.rb_seat_memory_3);
        }
        this.rgSeatMemoryPosition.setOnCheckedChangeListener(new r(this, i8));
        this.switchAutoBrakeLink.setChecked(this.prefs.getBoolean("auto_brake_link_enabled", false));
        a.a.p(this, 10, this.switchAutoBrakeLink);
        this.switchAutoSteeringHeat.setChecked(this.prefs.getBoolean("auto_steering_heat_enabled", false));
        this.layoutAutoSteeringHeatSettings.setVisibility(this.switchAutoSteeringHeat.isChecked() ? 0 : 8);
        a.a.p(this, 11, this.switchAutoSteeringHeat);
        boolean z9 = this.prefs.getBoolean("auto_fragrance_enabled", false);
        this.switchAutoFragrance.setChecked(z9);
        this.layoutAutoFragranceSettings.setVisibility(z9 ? 0 : 8);
        a.a.p(this, 4, this.switchAutoFragrance);
        int i13 = this.prefs.getInt("auto_fragrance_level_mode", 2);
        if (i13 < 0) {
            i13 = 0;
        }
        if (i13 > 5) {
            i13 = 5;
        }
        this.seekAutoFragranceLevel.setProgress(i13);
        this.textAutoFragranceLevel.setText(i13 == 0 ? "开启挡位：AUTO" : a.a.d("开启挡位：", i13));
        this.seekAutoFragranceLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.8
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i14, boolean z10) {
                CarLinkFragment.this.textAutoFragranceLevel.setText(i14 == 0 ? "开启挡位：AUTO" : a.a.d("开启挡位：", i14));
                if (z10) {
                    if (!CarLinkFragment.this.prefs.getBoolean("lion_sync_service_enabled", false)) {
                        CarLinkFragment.this.prefs.edit().putInt("auto_fragrance_level_mode", i14).apply();
                    } else {
                        seekBar.setProgress(CarLinkFragment.this.prefs.getInt("auto_fragrance_level_mode", 2));
                        ToastHelper.show(CarLinkFragment.this.requireContext(), "当前模式不支持此选项");
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        int i14 = this.prefs.getInt("auto_fragrance_type", 1);
        if (i14 == 2) {
            this.rgAutoFragranceType.check(g4.p.rb_auto_fragrance_type_2);
        } else if (i14 == 3) {
            this.rgAutoFragranceType.check(g4.p.rb_auto_fragrance_type_3);
        } else {
            this.rgAutoFragranceType.check(g4.p.rb_auto_fragrance_type_1);
        }
        this.rgAutoFragranceType.setOnCheckedChangeListener(new r(this, i7));
        int i15 = this.prefs.getInt("auto_fragrance_close_minutes", 2);
        if (i15 < 1) {
            i15 = 1;
        }
        if (i15 > 5) {
            i15 = 5;
        }
        this.seekAutoFragranceCloseMinutes.setProgress(i15 - 1);
        a.a.v("关闭时间：", i15, "分钟", this.textAutoFragranceCloseMinutes);
        this.seekAutoFragranceCloseMinutes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.9
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i16, boolean z10) {
                int i17 = i16 + 1;
                a.a.v("关闭时间：", i17, "分钟", CarLinkFragment.this.textAutoFragranceCloseMinutes);
                if (z10) {
                    if (!CarLinkFragment.this.prefs.getBoolean("lion_sync_service_enabled", false)) {
                        CarLinkFragment.this.prefs.edit().putInt("auto_fragrance_close_minutes", i17).apply();
                        return;
                    }
                    int i18 = CarLinkFragment.this.prefs.getInt("auto_fragrance_close_minutes", 2);
                    if (i18 < 1) {
                        i18 = 1;
                    }
                    if (i18 > 5) {
                        i18 = 5;
                    }
                    seekBar.setProgress(i18 - 1);
                    ToastHelper.show(CarLinkFragment.this.requireContext(), "当前模式不支持此选项");
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.switchAutoFragranceShortageAlarm.setChecked(this.prefs.getBoolean("auto_fragrance_shortage_alarm_enabled", true));
        a.a.p(this, 5, this.switchAutoFragranceShortageAlarm);
        SharedPreferences sharedPreferences = this.prefs;
        int i16 = (int) sharedPreferences.getFloat("steering_heat_threshold", sharedPreferences.getFloat("seat_heat_threshold", 10.0f));
        this.seekSteeringHeat.setProgress(i16 + 10);
        a.a.v("当车外温度小于 ", i16, "°C 执行动作", this.textSteeringHeatThreshold);
        this.seekSteeringHeat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.CarLinkFragment.10
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i17, boolean z10) {
                int i18 = i17 - 10;
                a.a.v("当车外温度小于 ", i18, "°C 执行动作", CarLinkFragment.this.textSteeringHeatThreshold);
                CarLinkFragment.this.prefs.edit().putFloat("steering_heat_threshold", i18).apply();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initSoundSettings(View view) {
        if (this.switchGearSoundMaster != null && this.layoutGearSoundSettings != null) {
            boolean z6 = this.prefs.getBoolean("sound_gear_master_enabled", false);
            this.switchGearSoundMaster.setChecked(z6);
            this.layoutGearSoundSettings.setVisibility(z6 ? 0 : 8);
            a.a.p(this, 0, this.switchGearSoundMaster);
        }
        if (this.switchDoorSoundMaster != null && this.layoutDoorSoundSettings != null) {
            boolean z7 = this.prefs.getBoolean("sound_door_master_enabled", false);
            this.switchDoorSoundMaster.setChecked(z7);
            this.layoutDoorSoundSettings.setVisibility(z7 ? 0 : 8);
            a.a.p(this, 1, this.switchDoorSoundMaster);
        }
        setupSettingItem(view.findViewById(g4.p.setting_gear_p), "P挡提示音", "sound_gear_p_enabled", "P.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_gear_r), "R挡提示音", "sound_gear_r_enabled", "R.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_gear_n), "N挡提示音", "sound_gear_n_enabled", "N.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_gear_d1), "D1挡提示音", "sound_gear_d1_enabled", "D1.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_fl_open), "左前门打开", "sound_door_fl_open_enabled", "FL_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_fl_close), "左前门关闭", "sound_door_fl_close_enabled", "FL_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_fr_open), "右前门打开", "sound_door_fr_open_enabled", "FR_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_fr_close), "右前门关闭", "sound_door_fr_close_enabled", "FR_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_rl_open), "左后门打开", "sound_door_rl_open_enabled", "RL_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_rl_close), "左后门关闭", "sound_door_rl_close_enabled", "RL_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_rr_open), "右后门打开", "sound_door_rr_open_enabled", "RR_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_rr_close), "右后门关闭", "sound_door_rr_close_enabled", "RR_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_fl_open), "左前窗打开", "sound_window_fl_open_enabled", "WIN_FL_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_fl_close), "左前窗关闭", "sound_window_fl_close_enabled", "WIN_FL_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_fr_open), "右前窗打开", "sound_window_fr_open_enabled", "WIN_FR_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_fr_close), "右前窗关闭", "sound_window_fr_close_enabled", "WIN_FR_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_rl_open), "左后窗打开", "sound_window_rl_open_enabled", "WIN_RL_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_rl_close), "左后窗关闭", "sound_window_rl_close_enabled", "WIN_RL_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_rr_open), "右后窗打开", "sound_window_rr_open_enabled", "WIN_RR_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_window_rr_close), "右后窗关闭", "sound_window_rr_close_enabled", "WIN_RR_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_sunroof_open), "天窗打开", "sound_sunroof_open_enabled", "SUNROOF_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_sunroof_close), "天窗关闭", "sound_sunroof_close_enabled", "SUNROOF_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_sunshade_open), "遮阳帘打开", "sound_sunshade_open_enabled", "SUNSHADE_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_sunshade_close), "遮阳帘关闭", "sound_sunshade_close_enabled", "SUNSHADE_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_trunk_open), "后备箱打开", "sound_door_trunk_open_enabled", "TRUNK_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_trunk_close), "后备箱关闭", "sound_door_trunk_close_enabled", "TRUNK_CLOSE.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_hood_open), "引擎盖打开", "sound_door_hood_open_enabled", "HOOD_OPEN.mp3");
        setupSettingItem(view.findViewById(g4.p.setting_door_hood_close), "引擎盖关闭", "sound_door_hood_close_enabled", "HOOD_CLOSE.mp3");
    }

    private void initViews(View view) {
        this.switchCarLinkService = (SwitchMaterial) view.findViewById(g4.p.switch_car_link_service);
        this.switchLionSyncService = (SwitchMaterial) view.findViewById(g4.p.switch_lion_sync_service);
        this.switchLionMediaBridge = (SwitchMaterial) view.findViewById(g4.p.switch_lion_media_bridge);
        this.switchCarlanDashboard = (SwitchMaterial) view.findViewById(g4.p.switch_carlan_dashboard);
        this.switchCarlanSongNameOnly = (SwitchMaterial) view.findViewById(g4.p.switch_carlan_song_name_only);
        this.layoutCarlanSongNameOnly = (LinearLayout) view.findViewById(g4.p.layout_carlan_song_name_only);
        this.switchAmapSpeedSync = (SwitchMaterial) view.findViewById(g4.p.switch_amap_speed_sync);
        this.textAmapSpeedSyncStatus = (TextView) view.findViewById(g4.p.text_amap_speed_sync_status);
        this.switchWirelessChargingCapsule = (SwitchMaterial) view.findViewById(g4.p.switch_wireless_charging_capsule);
        this.switchOilRecord = (SwitchMaterial) view.findViewById(g4.p.switch_oil_record);
        this.switchOilRecordCalendar = (SwitchMaterial) view.findViewById(g4.p.switch_oil_record_calendar);
        this.switchTirePressureAlarm = (SwitchMaterial) view.findViewById(g4.p.switch_tire_pressure_alarm);
        this.switchAutoDrivingMode = (SwitchMaterial) view.findViewById(g4.p.switch_auto_driving_mode);
        this.switchChildLockReminder = (SwitchMaterial) view.findViewById(g4.p.switch_child_lock_reminder);
        this.layoutOilRecord = view.findViewById(g4.p.card_oil_record);
        this.layoutOilRecordSettings = view.findViewById(g4.p.layout_oil_record_settings);
        this.layoutTirePressureAlarm = view.findViewById(g4.p.card_tire_pressure_alarm);
        this.layoutFragranceShortageAlarmCard = view.findViewById(g4.p.card_fragrance_shortage_alarm);
        this.layoutAutoDrivingMode = view.findViewById(g4.p.card_auto_driving_mode);
        this.layoutChildLockReminder = view.findViewById(g4.p.card_child_lock_reminder);
        this.layoutCarLinkServiceCard = view.findViewById(g4.p.card_car_link_service);
        this.layoutLionSyncServiceCard = view.findViewById(g4.p.card_lion_sync_service);
        this.layoutLionMediaBridgeCard = view.findViewById(g4.p.card_lion_media_bridge);
        this.layoutCarlanDashboardCard = view.findViewById(g4.p.card_carlan_dashboard);
        this.layoutWirelessChargingCapsuleCard = view.findViewById(g4.p.card_wireless_charging_capsule);
        this.layoutAutoDrivingModeBootOnly = view.findViewById(g4.p.layout_driving_mode_boot_only);
        View findViewById = view.findViewById(g4.p.setting_seat_heat_rl);
        if (findViewById != null) {
            this.layoutSeatHeatRl = findViewById;
        }
        View findViewById2 = view.findViewById(g4.p.setting_seat_heat_rr);
        if (findViewById2 != null) {
            this.layoutSeatHeatRr = findViewById2;
        }
        this.layoutSeatHeatRlDivider = view.findViewById(g4.p.divider_seat_heat_rl);
        this.layoutSeatHeatRrDivider = view.findViewById(g4.p.divider_seat_heat_rr);
        this.layoutSeatHeatCompat = view.findViewById(g4.p.layout_seat_heat_compat);
        this.dividerSeatHeatCompat = view.findViewById(g4.p.divider_seat_heat_compat);
        this.layoutSeatVentCompat = view.findViewById(g4.p.layout_seat_vent_compat);
        this.dividerSeatVentCompat = view.findViewById(g4.p.divider_seat_vent_compat);
        this.layoutSeatMemoryCard = view.findViewById(g4.p.card_seat_memory);
        this.layoutAutoBrakeLinkCard = view.findViewById(g4.p.card_auto_brake_link);
        this.layoutAutoSteeringHeatCard = view.findViewById(g4.p.card_auto_steering_heat);
        this.layoutAutoFragranceCard = view.findViewById(g4.p.card_auto_fragrance);
        this.layoutMobileHudSettings = view.findViewById(g4.p.layout_mobile_hud_settings);
        this.layoutPhoneNotificationSettings = view.findViewById(g4.p.layout_phone_notification_settings);
        this.textServiceStatus = (TextView) view.findViewById(g4.p.text_service_status);
        this.textLionServiceStatus = (TextView) view.findViewById(g4.p.text_lion_service_status);
        this.textLionMediaStatus = (TextView) view.findViewById(g4.p.text_lion_media_status);
        this.textCarlanDashboardStatus = (TextView) view.findViewById(g4.p.text_carlan_dashboard_status);
        this.textOilTankCapacity = (TextView) view.findViewById(g4.p.text_oil_tank_capacity);
        this.btnSetOilTankCapacity = (Button) view.findViewById(g4.p.btn_set_oil_tank_capacity);
        this.switchPhoneNotification = (SwitchMaterial) view.findViewById(g4.p.switch_phone_notification);
        this.switchPhoneNotificationClickExpand = (SwitchMaterial) view.findViewById(g4.p.switch_phone_notification_click_expand);
        this.switchPhoneNotificationHideMainScreen = (SwitchMaterial) view.findViewById(g4.p.switch_phone_notification_hide_main_screen);
        this.switchPhoneNotificationClusterOverlay = (SwitchMaterial) view.findViewById(g4.p.switch_phone_notification_cluster_overlay);
        this.switchPhoneNotificationSound = (SwitchMaterial) view.findViewById(g4.p.switch_phone_notification_sound);
        this.switchTireCard = (SwitchMaterial) view.findViewById(g4.p.switch_tire_card);
        this.switchTireCardDynamic = (SwitchMaterial) view.findViewById(g4.p.switch_tire_card_dynamic);
        this.layoutTireCardDynamic = view.findViewById(g4.p.layout_tire_card_dynamic);
        this.layoutTireCardDuration = view.findViewById(g4.p.layout_tire_card_duration);
        this.tvTireCardDuration = (TextView) view.findViewById(g4.p.tv_tire_card_duration);
        this.sbTireCardDuration = (SeekBar) view.findViewById(g4.p.sb_tire_card_duration);
        this.layoutPhoneNotificationClusterPositionSettings = view.findViewById(g4.p.layout_phone_notification_cluster_position_settings);
        this.btnPhoneNotificationClusterPosition = (Button) view.findViewById(g4.p.btn_phone_notification_cluster_position);
        this.textPhoneNotificationClusterPosition = (TextView) view.findViewById(g4.p.text_phone_notification_cluster_position);
        this.textPhoneNotificationDismiss = (TextView) view.findViewById(g4.p.text_phone_notification_dismiss);
        this.seekPhoneNotificationDismiss = (SeekBar) view.findViewById(g4.p.seek_phone_notification_dismiss);
        this.textPhoneNotificationBodyTextSize = (TextView) view.findViewById(g4.p.text_phone_notification_body_text_size);
        this.seekPhoneNotificationBodyTextSize = (SeekBar) view.findViewById(g4.p.seek_phone_notification_body_text_size);
        this.switchMobileHud = (SwitchMaterial) view.findViewById(g4.p.switch_mobile_hud);
        this.switchMobileHudUseBaidu = (SwitchMaterial) view.findViewById(g4.p.switch_mobile_hud_use_baidu);
        this.switchMobileHudUseExternalLight = (SwitchMaterial) view.findViewById(g4.p.switch_mobile_hud_use_external);
        this.textMobileHudBaiduStatus = (TextView) view.findViewById(g4.p.text_mobile_hud_baidu_status);
        this.checkMobileHudSpeed = (CheckBox) view.findViewById(g4.p.check_mobile_hud_speed);
        this.checkMobileHudGear = (CheckBox) view.findViewById(g4.p.check_mobile_hud_gear);
        this.checkMobileHudFuel = (CheckBox) view.findViewById(g4.p.check_mobile_hud_fuel);
        this.checkMobileHudRange = (CheckBox) view.findViewById(g4.p.check_mobile_hud_range);
        this.checkMobileHudPhoneNotification = (CheckBox) view.findViewById(g4.p.check_mobile_hud_phone_notification);
        this.checkMobileHudTrafficLight = (CheckBox) view.findViewById(g4.p.check_mobile_hud_traffic_light);
        this.checkMobileHudSoc = (CheckBox) view.findViewById(g4.p.check_mobile_hud_soc);
        this.checkMobileHudLane = (CheckBox) view.findViewById(g4.p.check_mobile_hud_lane);
        this.checkMobileHudEta = (CheckBox) view.findViewById(g4.p.check_mobile_hud_eta);
        this.checkMobileHudLyric = (CheckBox) view.findViewById(g4.p.check_mobile_hud_lyric);
        this.checkMobileHudTmc = (CheckBox) view.findViewById(g4.p.check_mobile_hud_tmc);
        this.checkMobileHudLaneAnimation = (CheckBox) view.findViewById(g4.p.check_mobile_hud_lane_animation);
        this.rgMobileHudConnectionMode = (RadioGroup) view.findViewById(g4.p.radio_group_mobile_hud_connection_mode);
        this.rgMobileHudTextColor = (RadioGroup) view.findViewById(g4.p.radio_group_mobile_hud_text_color);
        this.rgPhoneNotificationConnectionMode = (RadioGroup) view.findViewById(g4.p.radio_group_phone_notification_connection_mode);
        this.rgPhoneNotificationPosition = (RadioGroup) view.findViewById(g4.p.radio_group_phone_notification_position);
        this.textMobileHudBrightness = (TextView) view.findViewById(g4.p.text_mobile_hud_brightness);
        this.textMobileHudNightBrightness = (TextView) view.findViewById(g4.p.text_mobile_hud_night_brightness);
        this.seekMobileHudBrightness = (SeekBar) view.findViewById(g4.p.seek_mobile_hud_brightness);
        this.seekMobileHudNightBrightness = (SeekBar) view.findViewById(g4.p.seek_mobile_hud_night_brightness);
        this.switchGearSoundMaster = (SwitchMaterial) view.findViewById(g4.p.switch_gear_sound_master);
        this.layoutGearSoundSettings = (LinearLayout) view.findViewById(g4.p.layout_gear_sound_settings);
        this.switchDoorSoundMaster = (SwitchMaterial) view.findViewById(g4.p.switch_door_sound_master);
        this.layoutDoorSoundSettings = (LinearLayout) view.findViewById(g4.p.layout_door_sound_settings);
        this.switchAutoSeatHeat = (SwitchMaterial) view.findViewById(g4.p.switch_auto_seat_heat);
        this.layoutSeatHeatSettings = (LinearLayout) view.findViewById(g4.p.layout_seat_heat_settings);
        this.rgSeatHeatCondition = (RadioGroup) view.findViewById(g4.p.rg_seat_heat_condition);
        this.textSeatHeatThreshold = (TextView) view.findViewById(g4.p.text_seat_heat_threshold);
        this.seekSeatHeat = (SeekBar) view.findViewById(g4.p.seek_seat_heat);
        this.switchAutoSeatVent = (SwitchMaterial) view.findViewById(g4.p.switch_auto_seat_vent);
        this.layoutSeatVentSettings = (LinearLayout) view.findViewById(g4.p.layout_seat_vent_settings);
        this.rgSeatVentCondition = (RadioGroup) view.findViewById(g4.p.rg_seat_vent_condition);
        this.textSeatVentThreshold = (TextView) view.findViewById(g4.p.text_seat_vent_threshold);
        this.seekSeatVent = (SeekBar) view.findViewById(g4.p.seek_seat_vent);
        this.switchAutoSeatMemory = (SwitchMaterial) view.findViewById(g4.p.switch_auto_seat_memory);
        this.layoutSeatMemorySettings = (LinearLayout) view.findViewById(g4.p.layout_seat_memory_settings);
        this.rgSeatMemoryPosition = (RadioGroup) view.findViewById(g4.p.rg_seat_memory_position);
        this.switchAutoBrakeLink = (SwitchMaterial) view.findViewById(g4.p.switch_auto_brake_link);
        this.switchAutoSteeringHeat = (SwitchMaterial) view.findViewById(g4.p.switch_auto_steering_heat);
        this.layoutAutoSteeringHeatSettings = (LinearLayout) view.findViewById(g4.p.layout_auto_steering_heat_settings);
        this.textSteeringHeatThreshold = (TextView) view.findViewById(g4.p.text_steering_heat_threshold);
        this.seekSteeringHeat = (SeekBar) view.findViewById(g4.p.seek_steering_heat);
        this.switchAutoFragrance = (SwitchMaterial) view.findViewById(g4.p.switch_auto_fragrance);
        this.layoutAutoFragranceSettings = (LinearLayout) view.findViewById(g4.p.layout_auto_fragrance_settings);
        this.textAutoFragranceLevel = (TextView) view.findViewById(g4.p.text_auto_fragrance_level);
        this.seekAutoFragranceLevel = (SeekBar) view.findViewById(g4.p.seek_auto_fragrance_level);
        this.rgAutoFragranceType = (RadioGroup) view.findViewById(g4.p.rg_auto_fragrance_type);
        this.textAutoFragranceCloseMinutes = (TextView) view.findViewById(g4.p.text_auto_fragrance_close_minutes);
        this.seekAutoFragranceCloseMinutes = (SeekBar) view.findViewById(g4.p.seek_auto_fragrance_close_minutes);
        this.switchAutoFragranceShortageAlarm = (SwitchMaterial) view.findViewById(g4.p.switch_auto_fragrance_shortage_alarm);
        this.layoutCarLinkFeatures = (LinearLayout) view.findViewById(g4.p.layout_car_link_features);
        this.layoutAutoChildUnlock = view.findViewById(g4.p.layout_auto_child_unlock);
        this.switchAutoChildUnlockDriverDoor = (SwitchMaterial) view.findViewById(g4.p.switch_auto_child_unlock_driver_door);
        this.switchAutoChildUnlockPassengerDoor = (SwitchMaterial) view.findViewById(g4.p.switch_auto_child_unlock_passenger_door);
        this.switchAutoChildUnlockByP = (SwitchMaterial) view.findViewById(g4.p.switch_auto_child_unlock_by_p);
        this.switchAutoChildUnlockPowerOff = (SwitchMaterial) view.findViewById(g4.p.switch_auto_child_unlock_power_off);
        this.rgChildLockMode = (RadioGroup) view.findViewById(g4.p.rg_child_lock_mode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$10(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "amap_speed_sync_enabled", z6);
            this.textAmapSpeedSyncStatus.setVisibility(z6 ? 0 : 8);
            o4.b b7 = o4.b.b(requireContext());
            if (z6) {
                b7.d();
            } else {
                b7.e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$11(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "wireless_charging_capsule_enabled", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$12(Boolean bool) {
        updateAmapSpeedSyncStatusText(bool != null && bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$13(Boolean bool) {
        TextView textView = this.prefs.getBoolean("lion_sync_service_enabled", false) ? this.textLionServiceStatus : this.textServiceStatus;
        if (bool.booleanValue()) {
            textView.setText("连接状态：已连接");
            textView.setTextColor(requireContext().getColor(R.color.holo_green_dark));
        } else {
            textView.setText("连接状态：未连接");
            textView.setTextColor(requireContext().getColor(R.color.holo_red_light));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$14(View view) {
        showTankCapacityDialog(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$15(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            if (z6 && this.prefs.getBoolean("lion_sync_service_enabled", false)) {
                ToastHelper.show(requireContext(), "雄狮服务不支持油耗记录");
                this.switchOilRecord.setChecked(false);
                return;
            }
            if (z6 && !this.prefs.getBoolean("car_link_service_enabled", false)) {
                ToastHelper.show(requireContext(), "请先开启车联服务");
                this.switchOilRecord.setChecked(false);
                return;
            }
            if (z6 && o4.r.d(this.prefs) <= 0.0d) {
                ToastHelper.show(requireContext(), "请先设置油箱容积");
                showTankCapacityDialog(true);
                return;
            }
            a.a.m(this.prefs, o4.r.PREF_OIL_RECORD_ENABLED, z6);
            updateOilRecordSettingsVisibility(z6);
            if (z6) {
                if (o4.r.n(requireContext())) {
                    ToastHelper.show(requireContext(), "油耗记录已开启，已写入基准数据");
                } else {
                    ToastHelper.show(requireContext(), "当前车联数据未准备好，稍后会自动建立基准");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$16(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, o4.r.PREF_OIL_RECORD_CALENDAR_ENABLED, z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$17(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, MobileHudService.PREF_PHONE_NOTIFICATION_ENABLED, z6);
            updatePhoneNotificationSettingsVisibility(z6);
            MobileHudService.syncService(requireContext());
            PhoneNotificationRelayService.syncService(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$18(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, MobileHudService.PREF_ENABLED, z6);
            this.layoutMobileHudSettings.setVisibility(z6 ? 0 : 8);
            MobileHudService.syncService(requireContext());
            PhoneNotificationRelayService.syncService(requireContext());
            if (z6) {
                requestHudLocationPermissions();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$19(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, MobileHudService.PREF_USE_BAIDU_SOURCE, z6);
            if (z6) {
                this.switchMobileHudUseExternalLight.setOnCheckedChangeListener(null);
                a.a.m(this.prefs, MobileHudService.PREF_USE_EXTERNAL_TRAFFIC_LIGHT, false);
                this.switchMobileHudUseExternalLight.setChecked(false);
                this.switchMobileHudUseExternalLight.setEnabled(false);
                this.switchMobileHudUseExternalLight.setOnCheckedChangeListener(this.mobileHudExternalLightListener);
            } else {
                this.switchMobileHudUseExternalLight.setEnabled(true);
            }
            MobileHudService.syncService(requireContext());
            TextView textView = this.textMobileHudBaiduStatus;
            if (textView != null) {
                textView.setVisibility(z6 ? 0 : 8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$20(Boolean bool) {
        updateBaiduStatusText(bool != null && bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$21(RadioGroup radioGroup, int i7) {
        if (i7 == -1) {
            return;
        }
        this.prefs.edit().putString(MobileHudService.PREF_CONNECTION_MODE, i7 == g4.p.radio_mobile_hud_connection_wifi ? MobileHudService.CONNECTION_MODE_WIFI : MobileHudService.CONNECTION_MODE_BLUETOOTH).apply();
        MobileHudService.syncService(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$22(RadioGroup radioGroup, int i7) {
        if (i7 == -1) {
            return;
        }
        this.prefs.edit().putString(MobileHudService.PREF_TEXT_COLOR, i7 == g4.p.radio_mobile_hud_text_blue ? MobileHudService.TEXT_COLOR_BLUE : MobileHudService.TEXT_COLOR_WHITE).apply();
        MobileHudService.syncService(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$23(RadioGroup radioGroup, int i7) {
        if (i7 == -1) {
            return;
        }
        this.prefs.edit().putString(PhoneNotificationRelayService.PREF_OVERLAY_POSITION, i7 == g4.p.radio_phone_notification_position_center ? PhoneNotificationRelayService.OVERLAY_POSITION_CENTER : i7 == g4.p.radio_phone_notification_position_right ? PhoneNotificationRelayService.OVERLAY_POSITION_RIGHT : PhoneNotificationRelayService.OVERLAY_POSITION_LEFT).apply();
        PhoneNotificationRelayService.syncService(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$24(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            this.prefs.edit().putBoolean(PhoneNotificationRelayService.PREF_CLICK_EXPAND_ENABLED, z6).apply();
            PhoneNotificationRelayService.syncService(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$25(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            this.prefs.edit().putBoolean(PhoneNotificationRelayService.PREF_HIDE_MAIN_SCREEN_OVERLAY, z6).apply();
            PhoneNotificationRelayService.syncService(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$26(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_ENABLED, z6);
            updatePhoneNotificationClusterPositionSettingsVisibility(z6);
            PhoneNotificationRelayService.syncService(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$27(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, PhoneNotificationRelayService.PREF_SOUND_ENABLED, z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$28(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, PhoneNotificationRelayService.PREF_TIRE_CARD_ENABLED, z6);
            updateTireCardSettingsVisibility(z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$29(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, PhoneNotificationRelayService.PREF_TIRE_CARD_DYNAMIC_ENABLED, z6);
            SwitchMaterial switchMaterial = this.switchTireCard;
            updateTireCardSettingsVisibility(switchMaterial != null && switchMaterial.isChecked());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$3(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "car_link_service_enabled", z6);
            this.textServiceStatus.setVisibility(z6 ? 0 : 8);
            if (z6) {
                if (this.switchLionSyncService.isChecked()) {
                    this.switchLionSyncService.setChecked(false);
                    a.a.m(this.prefs, "lion_sync_service_enabled", false);
                    this.textLionServiceStatus.setVisibility(8);
                    stopLionService();
                }
                startService();
            } else {
                stopService();
            }
            this.layoutCarLinkFeatures.setVisibility((z6 || this.switchLionSyncService.isChecked()) ? 0 : 8);
            updateUnsupportedFeaturesVisibility(this.switchLionSyncService.isChecked());
            updateServiceCardsVisibility(z6, this.switchLionSyncService.isChecked());
            MobileHudService.syncService(requireContext());
            PhoneNotificationRelayService.syncService(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$30(int i7, int i8) {
        this.prefs.edit().putInt(PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_X, i7).putInt(PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_Y, i8).apply();
        updatePhoneNotificationClusterPositionText();
        PhoneNotificationRelayService.showClusterOverlayPreview(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$31(View view) {
        PositionSettingsDialog newInstance = PositionSettingsDialog.newInstance("仪表通知位置设置", this.prefs.getInt(PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_X, PhoneNotificationRelayService.getDefaultClusterOverlayX(requireContext())), this.prefs.getInt(PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_Y, PhoneNotificationRelayService.getDefaultClusterOverlayY(requireContext())));
        newInstance.setOnPositionSetListener(new o(this, 0));
        newInstance.show(getParentFragmentManager(), "PhoneNotificationClusterPositionDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$32(RadioGroup radioGroup, int i7) {
        if (i7 == -1) {
            return;
        }
        this.prefs.edit().putString(PhoneNotificationRelayService.PREF_CONNECTION_MODE, i7 == g4.p.radio_phone_notification_connection_wifi ? MobileHudService.CONNECTION_MODE_WIFI : MobileHudService.CONNECTION_MODE_BLUETOOTH).putLong(PhoneNotificationRelayService.PREF_CONNECTION_MODE_TRANSITION_UNTIL_MS, System.currentTimeMillis() + PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS).apply();
        MobileHudService.syncService(requireContext());
        PhoneNotificationRelayService.syncService(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$33(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            this.prefs.edit().putBoolean("tire_pressure_alarm_enabled", z6).apply();
            Intent intent = new Intent("com.navi.tool.TIRE_PRESSURE_ALARM_CONFIG_CHANGED");
            intent.putExtra("enabled", z6);
            requireContext().sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$34(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_driving_mode_boot_only", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$35(View view, CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_driving_mode_enabled", z6);
            if (view != null) {
                view.setVisibility(z6 ? 0 : 8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$36(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "child_lock_reminder_enabled", z6);
            View view = this.layoutAutoChildUnlock;
            if (view != null) {
                view.setVisibility(z6 ? 0 : 8);
            }
            if (z6) {
                return;
            }
            this.prefs.edit().putBoolean("auto_child_unlock_driver_door", false).putBoolean("auto_child_unlock_passenger_door", false).putBoolean("auto_child_unlock_by_p", false).putBoolean("auto_child_unlock_power_off", false).putInt("child_lock_lock_mode", 0).apply();
            SwitchMaterial switchMaterial = this.switchAutoChildUnlockDriverDoor;
            if (switchMaterial != null) {
                switchMaterial.setChecked(false);
            }
            SwitchMaterial switchMaterial2 = this.switchAutoChildUnlockPassengerDoor;
            if (switchMaterial2 != null) {
                switchMaterial2.setChecked(false);
            }
            SwitchMaterial switchMaterial3 = this.switchAutoChildUnlockByP;
            if (switchMaterial3 != null) {
                switchMaterial3.setChecked(false);
            }
            SwitchMaterial switchMaterial4 = this.switchAutoChildUnlockPowerOff;
            if (switchMaterial4 != null) {
                switchMaterial4.setChecked(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$37(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_child_unlock_driver_door", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$38(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_child_unlock_passenger_door", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$39(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_child_unlock_by_p", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$4(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "lion_sync_service_enabled", z6);
            this.textLionServiceStatus.setVisibility(z6 ? 0 : 8);
            if (z6) {
                if (this.switchCarLinkService.isChecked()) {
                    this.switchCarLinkService.setChecked(false);
                    a.a.m(this.prefs, "car_link_service_enabled", false);
                    this.textServiceStatus.setVisibility(8);
                    stopService();
                }
                startLionService();
            } else {
                stopLionService();
            }
            this.layoutCarLinkFeatures.setVisibility((z6 || this.switchCarLinkService.isChecked()) ? 0 : 8);
            updateUnsupportedFeaturesVisibility(z6);
            updateServiceCardsVisibility(this.switchCarLinkService.isChecked(), z6);
            MobileHudService.syncService(requireContext());
            PhoneNotificationRelayService.syncService(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$40(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_child_unlock_power_off", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$41(RadioGroup radioGroup, int i7) {
        View findViewById = radioGroup.findViewById(i7);
        if (findViewById == null || findViewById.isPressed()) {
            a.a.l(this.prefs, "child_lock_lock_mode", i7 == g4.p.rb_child_lock_mode_manual ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$42(Boolean bool) {
        TextView textView = this.prefs.getBoolean("lion_sync_service_enabled", false) ? this.textLionServiceStatus : this.textServiceStatus;
        if (bool.booleanValue()) {
            textView.setText("连接状态：已连接");
            textView.setTextColor(requireContext().getColor(R.color.holo_green_dark));
        } else {
            textView.setText("连接状态：未连接");
            textView.setTextColor(requireContext().getColor(R.color.holo_red_light));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$5(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "lion_media_bridge_enabled", z6);
            this.textLionMediaStatus.setVisibility(z6 ? 0 : 8);
            if (z6) {
                startLionMediaBridgeService();
            } else {
                stopLionMediaBridgeService();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$6(Boolean bool) {
        updateLionMediaBridgeStatusText(bool != null && bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$7(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "carlan_dashboard_enabled", z6);
            this.textCarlanDashboardStatus.setVisibility(z6 ? 0 : 8);
            LinearLayout linearLayout = this.layoutCarlanSongNameOnly;
            if (linearLayout != null) {
                linearLayout.setVisibility(z6 ? 0 : 8);
            }
            if (z6) {
                startCarlanDashboardService();
            } else {
                stopCarlanDashboardService();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$8(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "carlan_song_name_only", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$9(Boolean bool) {
        updateCarlanDashboardStatusText(bool != null && bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$47(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_seat_heat_enabled", z6);
            this.layoutSeatHeatSettings.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$48(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "seat_heat_compat_mode", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$49(RadioGroup radioGroup, int i7) {
        View findViewById = radioGroup.findViewById(i7);
        if (findViewById == null || findViewById.isPressed()) {
            a.a.l(this.prefs, "seat_heat_trigger_condition", i7 == g4.p.rb_heat_engine ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$50(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_seat_vent_enabled", z6);
            this.layoutSeatVentSettings.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$51(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "seat_vent_compat_mode", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$52(RadioGroup radioGroup, int i7) {
        View findViewById = radioGroup.findViewById(i7);
        if (findViewById == null || findViewById.isPressed()) {
            a.a.l(this.prefs, "seat_vent_trigger_condition", i7 == g4.p.rb_vent_engine ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$53(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_seat_memory_enabled", z6);
            this.layoutSeatMemorySettings.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$54(RadioGroup radioGroup, int i7) {
        View findViewById = radioGroup.findViewById(i7);
        if (findViewById == null || findViewById.isPressed()) {
            a.a.l(this.prefs, "seat_memory_position", i7 == g4.p.rb_seat_memory_2 ? 2 : i7 == g4.p.rb_seat_memory_3 ? 3 : 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$55(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "auto_brake_link_enabled", z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$56(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "auto_steering_heat_enabled", z6);
        this.layoutAutoSteeringHeatSettings.setVisibility(z6 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$57(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
                compoundButton.setChecked(!z6);
                ToastHelper.show(requireContext(), "当前模式不支持此选项");
            } else {
                a.a.m(this.prefs, "auto_fragrance_enabled", z6);
                this.layoutAutoFragranceSettings.setVisibility(z6 ? 0 : 8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$58(RadioGroup radioGroup, int i7) {
        View findViewById = radioGroup.findViewById(i7);
        if (findViewById == null || findViewById.isPressed()) {
            int i8 = 3;
            if (!this.prefs.getBoolean("lion_sync_service_enabled", false)) {
                if (i7 == g4.p.rb_auto_fragrance_type_2) {
                    i8 = 2;
                } else if (i7 != g4.p.rb_auto_fragrance_type_3) {
                    i8 = 1;
                }
                a.a.l(this.prefs, "auto_fragrance_type", i8);
                return;
            }
            int i9 = this.prefs.getInt("auto_fragrance_type", 1);
            if (i9 == 2) {
                radioGroup.check(g4.p.rb_auto_fragrance_type_2);
            } else if (i9 == 3) {
                radioGroup.check(g4.p.rb_auto_fragrance_type_3);
            } else {
                radioGroup.check(g4.p.rb_auto_fragrance_type_1);
            }
            ToastHelper.show(requireContext(), "当前模式不支持此选项");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSeatClimateSettings$59(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            if (!this.prefs.getBoolean("lion_sync_service_enabled", false)) {
                a.a.m(this.prefs, "auto_fragrance_shortage_alarm_enabled", z6);
            } else {
                compoundButton.setChecked(!z6);
                ToastHelper.show(requireContext(), "当前模式不支持此选项");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSoundSettings$44(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            if (!z6 || !this.prefs.getBoolean("lion_sync_service_enabled", false)) {
                a.a.m(this.prefs, "sound_gear_master_enabled", z6);
                this.layoutGearSoundSettings.setVisibility(z6 ? 0 : 8);
            } else {
                compoundButton.setChecked(false);
                a.a.m(this.prefs, "sound_gear_master_enabled", false);
                this.layoutGearSoundSettings.setVisibility(8);
                ToastHelper.show(requireContext(), "当前车联服务模式不支持此选项");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSoundSettings$45(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, "sound_door_master_enabled", z6);
            this.layoutDoorSoundSettings.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            if (z6 && this.prefs.getBoolean(MobileHudService.PREF_USE_BAIDU_SOURCE, false)) {
                compoundButton.setChecked(false);
            } else {
                this.prefs.edit().putBoolean(MobileHudService.PREF_USE_EXTERNAL_TRAFFIC_LIGHT, z6).apply();
                MobileHudService.syncService(requireContext());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMobileHudCheckBox$43(String str, CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            this.prefs.edit().putBoolean(str, z6).apply();
            MobileHudService.syncService(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSeatSwitch$60(String str, View view, CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, str, z6);
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSeatSwitch$61(String str, RadioGroup radioGroup, int i7) {
        View findViewById = radioGroup.findViewById(i7);
        if (findViewById == null || findViewById.isPressed()) {
            a.a.l(this.prefs, str, i7 == g4.p.rb_level_2 ? 2 : i7 == g4.p.rb_level_1 ? 1 : 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSettingItem$46(String str, String str2, CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            if (!z6 || new File(new File(Environment.getExternalStorageDirectory(), "navitool/mp3"), str).exists()) {
                a.a.m(this.prefs, str2, z6);
                return;
            }
            ToastHelper.show(requireContext(), "文件缺失: /navitool/mp3/" + str);
            compoundButton.setChecked(false);
            a.a.m(this.prefs, str2, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupSimpleSwitch$62(String str, CompoundButton compoundButton, boolean z6) {
        if (compoundButton.isPressed()) {
            a.a.m(this.prefs, str, z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showTankCapacityDialog$1(boolean z6, DialogInterface dialogInterface, int i7) {
        if (z6) {
            this.switchOilRecord.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showTankCapacityDialog$2(EditText editText, boolean z6, DialogInterface dialogInterface, int i7) {
        String c7 = editText.getText() != null ? a.a.c(editText) : s5.h0.FRAGMENT_ENCODE_SET;
        if (c7.isEmpty()) {
            ToastHelper.show(requireContext(), "请先输入油箱容积");
            if (z6) {
                this.switchOilRecord.setChecked(false);
                return;
            }
            return;
        }
        try {
            double parseDouble = Double.parseDouble(c7);
            if (parseDouble <= 0.0d) {
                ToastHelper.show(requireContext(), "油箱容积必须大于0");
                if (z6) {
                    this.switchOilRecord.setChecked(false);
                    return;
                }
                return;
            }
            this.prefs.edit().putFloat(o4.r.PREF_OIL_TANK_CAPACITY, (float) parseDouble).apply();
            o4.r.o(requireContext(), parseDouble);
            updateOilTankCapacityText();
            if (z6) {
                a.a.m(this.prefs, o4.r.PREF_OIL_RECORD_ENABLED, true);
                this.switchOilRecord.setChecked(true);
                updateOilRecordSettingsVisibility(true);
                if (o4.r.n(requireContext())) {
                    return;
                }
                ToastHelper.show(requireContext(), "当前车联数据未准备好，稍后会自动建立基准");
            }
        } catch (NumberFormatException unused) {
            ToastHelper.show(requireContext(), "油箱容积格式错误");
            if (z6) {
                this.switchOilRecord.setChecked(false);
            }
        }
    }

    private void requestHudBackgroundLocationIfNeeded() {
        Context context;
        if (Build.VERSION.SDK_INT < 29 || (context = getContext()) == null || g0.g.a(context, "android.permission.ACCESS_BACKGROUND_LOCATION") == 0) {
            return;
        }
        requestPermissions(new String[]{"android.permission.ACCESS_BACKGROUND_LOCATION"}, REQUEST_HUD_LOCATION_BACKGROUND);
    }

    private void requestHudLocationPermissions() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        if (g0.g.a(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            requestHudBackgroundLocationIfNeeded();
        } else {
            requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, REQUEST_HUD_LOCATION_FOREGROUND);
        }
    }

    private void setupMobileHudCheckBox(CheckBox checkBox, String str) {
        if (checkBox == null) {
            return;
        }
        checkBox.setOnCheckedChangeListener(new q(this, str, 0));
    }

    private void setupSeatSwitch(View view, String str, String str2, String str3) {
        if (view == null) {
            return;
        }
        TextView textView = (TextView) view.findViewById(g4.p.text_seat_name);
        SwitchMaterial switchMaterial = (SwitchMaterial) view.findViewById(g4.p.switch_seat_enabled);
        View findViewById = view.findViewById(g4.p.layout_seat_level);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(g4.p.rg_seat_level);
        if (textView == null || switchMaterial == null || findViewById == null || radioGroup == null) {
            return;
        }
        textView.setText(str);
        int i7 = 0;
        boolean z6 = this.prefs.getBoolean(str2, false);
        switchMaterial.setChecked(z6);
        findViewById.setVisibility(z6 ? 0 : 8);
        int i8 = 3;
        int i9 = this.prefs.getInt(str3, 3);
        if (i9 == 3) {
            radioGroup.check(g4.p.rb_level_3);
        } else if (i9 == 2) {
            radioGroup.check(g4.p.rb_level_2);
        } else if (i9 == 1) {
            radioGroup.check(g4.p.rb_level_1);
        }
        switchMaterial.setOnCheckedChangeListener(new p(this, str2, findViewById, i7));
        radioGroup.setOnCheckedChangeListener(new m2(this, str3, i8));
    }

    private void setupSettingItem(View view, String str, String str2, String str3) {
        if (view == null) {
            return;
        }
        TextView textView = (TextView) view.findViewById(g4.p.text_setting_name);
        SwitchMaterial switchMaterial = (SwitchMaterial) view.findViewById(g4.p.switch_setting_enabled);
        if (textView == null || switchMaterial == null) {
            return;
        }
        textView.setText(str);
        switchMaterial.setChecked(this.prefs.getBoolean(str2, false));
        switchMaterial.setOnCheckedChangeListener(new p(this, str3, str2, 1));
    }

    private void setupSimpleSwitch(View view, String str, String str2) {
        if (view == null) {
            return;
        }
        TextView textView = (TextView) view.findViewById(g4.p.text_setting_name);
        SwitchMaterial switchMaterial = (SwitchMaterial) view.findViewById(g4.p.switch_setting_enabled);
        if (textView == null || switchMaterial == null) {
            return;
        }
        textView.setText(str);
        switchMaterial.setChecked(this.prefs.getBoolean(str2, false));
        switchMaterial.setOnCheckedChangeListener(new q(this, str2, 1));
    }

    private void showTankCapacityDialog(final boolean z6) {
        if (getContext() == null) {
            return;
        }
        EditText editText = new EditText(requireContext());
        editText.setInputType(androidx.fragment.app.h1.TRANSIT_FRAGMENT_CLOSE);
        double d7 = o4.r.d(this.prefs);
        if (d7 > 0.0d) {
            editText.setText(String.valueOf(d7));
            editText.setSelection(editText.getText().length());
        }
        int i7 = (int) (requireContext().getResources().getDisplayMetrics().density * 16.0f);
        editText.setPadding(i7, i7, i7, i7);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("设置油箱容积");
        nVar.m("请输入油箱容积，单位为升(L)");
        nVar.u(editText);
        nVar.o("取消", new DialogInterface.OnClickListener() { // from class: com.navi.tool.ui.extra.s
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i8) {
                CarLinkFragment.this.lambda$showTankCapacityDialog$1(z6, dialogInterface, i8);
            }
        });
        nVar.r("保存", new t(this, editText, z6, 0));
        DialogImeNavBarHelper.install(nVar.v(), editText);
    }

    private void startCarlanDashboardService() {
        requireContext().startForegroundService(new Intent(requireContext(), (Class<?>) CarLanDashboardService.class));
    }

    private void startLionMediaBridgeService() {
        requireContext().startForegroundService(new Intent(requireContext(), (Class<?>) LionMediaBridgeService.class));
    }

    private void startLionService() {
        requireContext().startForegroundService(new Intent(requireContext(), (Class<?>) LionCarInfoService.class));
    }

    private void startService() {
        requireContext().startForegroundService(new Intent(requireContext(), (Class<?>) CarInfoService.class));
    }

    private void stopCarlanDashboardService() {
        requireContext().stopService(new Intent(requireContext(), (Class<?>) CarLanDashboardService.class));
    }

    private void stopLionMediaBridgeService() {
        requireContext().stopService(new Intent(requireContext(), (Class<?>) LionMediaBridgeService.class));
    }

    private void stopLionService() {
        requireContext().stopService(new Intent(requireContext(), (Class<?>) LionCarInfoService.class));
        MobileHudService.syncService(requireContext());
    }

    private void stopService() {
        requireContext().stopService(new Intent(requireContext(), (Class<?>) CarInfoService.class));
        MobileHudService.syncService(requireContext());
    }

    private void updateAmapSpeedSyncStatusText(boolean z6) {
        if (this.textAmapSpeedSyncStatus == null) {
            return;
        }
        if (!this.prefs.getBoolean("amap_speed_sync_enabled", false)) {
            this.textAmapSpeedSyncStatus.setVisibility(8);
            return;
        }
        this.textAmapSpeedSyncStatus.setVisibility(0);
        if (z6) {
            this.textAmapSpeedSyncStatus.setText("高德状态：已连接");
            this.textAmapSpeedSyncStatus.setTextColor(requireContext().getColor(R.color.holo_green_dark));
        } else {
            this.textAmapSpeedSyncStatus.setText("高德状态：未连接");
            this.textAmapSpeedSyncStatus.setTextColor(requireContext().getColor(R.color.holo_red_light));
        }
    }

    private void updateBaiduStatusText(boolean z6) {
        if (this.textMobileHudBaiduStatus == null) {
            return;
        }
        if (!this.prefs.getBoolean(MobileHudService.PREF_USE_BAIDU_SOURCE, false)) {
            this.textMobileHudBaiduStatus.setVisibility(8);
            return;
        }
        this.textMobileHudBaiduStatus.setVisibility(0);
        if (z6) {
            this.textMobileHudBaiduStatus.setText("百度地图服务：已连接");
            this.textMobileHudBaiduStatus.setTextColor(requireContext().getColor(R.color.holo_green_dark));
        } else {
            this.textMobileHudBaiduStatus.setText("百度地图服务：未连接");
            this.textMobileHudBaiduStatus.setTextColor(requireContext().getColor(R.color.holo_red_light));
        }
    }

    private void updateCarlanDashboardStatusText(boolean z6) {
        if (this.textCarlanDashboardStatus == null) {
            return;
        }
        if (!this.prefs.getBoolean("carlan_dashboard_enabled", false)) {
            this.textCarlanDashboardStatus.setVisibility(8);
            return;
        }
        this.textCarlanDashboardStatus.setVisibility(0);
        if (z6) {
            this.textCarlanDashboardStatus.setText("仪表盘状态：已连接");
            this.textCarlanDashboardStatus.setTextColor(requireContext().getColor(R.color.holo_green_dark));
        } else {
            this.textCarlanDashboardStatus.setText("仪表盘状态：未连接");
            this.textCarlanDashboardStatus.setTextColor(requireContext().getColor(R.color.holo_red_light));
        }
    }

    private void updateLionMediaBridgeStatusText(boolean z6) {
        if (this.textLionMediaStatus == null) {
            return;
        }
        if (!this.prefs.getBoolean("lion_media_bridge_enabled", false)) {
            this.textLionMediaStatus.setVisibility(8);
            return;
        }
        this.textLionMediaStatus.setVisibility(0);
        if (z6) {
            this.textLionMediaStatus.setText("畅听状态：已连接");
            this.textLionMediaStatus.setTextColor(requireContext().getColor(R.color.holo_green_dark));
        } else {
            this.textLionMediaStatus.setText("畅听状态：未连接");
            this.textLionMediaStatus.setTextColor(requireContext().getColor(R.color.holo_red_light));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMobileHudBrightnessText(int i7) {
        TextView textView = this.textMobileHudBrightness;
        if (textView != null) {
            textView.setText("车萝卜HUD亮度：" + clampBrightness(i7) + "%");
        }
    }

    private void updateMobileHudConnectionModeSelection(String str) {
        if (this.rgMobileHudConnectionMode == null) {
            return;
        }
        int i7 = MobileHudService.CONNECTION_MODE_WIFI.equals(str) ? g4.p.radio_mobile_hud_connection_wifi : g4.p.radio_mobile_hud_connection_bluetooth;
        if (this.rgMobileHudConnectionMode.getCheckedRadioButtonId() != i7) {
            this.rgMobileHudConnectionMode.check(i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMobileHudNightBrightnessText(int i7) {
        TextView textView = this.textMobileHudNightBrightness;
        if (textView != null) {
            textView.setText("夜间模式车萝卜HUD亮度：" + clampBrightness(i7) + "%");
        }
    }

    private void updateMobileHudTextColorSelection(String str) {
        if (this.rgMobileHudTextColor == null) {
            return;
        }
        int i7 = MobileHudService.TEXT_COLOR_BLUE.equals(str) ? g4.p.radio_mobile_hud_text_blue : g4.p.radio_mobile_hud_text_white;
        if (this.rgMobileHudTextColor.getCheckedRadioButtonId() != i7) {
            this.rgMobileHudTextColor.check(i7);
        }
    }

    private void updateOilRecordSettingsVisibility(boolean z6) {
        View view = this.layoutOilRecordSettings;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateOilTankCapacityText() {
        String str;
        if (this.textOilTankCapacity == null) {
            return;
        }
        double d7 = o4.r.d(this.prefs);
        TextView textView = this.textOilTankCapacity;
        StringBuilder sb = new StringBuilder("当前油箱容积：");
        if (!o4.r.h(d7) || d7 <= 0.0d) {
            str = "未设置";
        } else {
            str = o4.r.f8006n.format(d7) + " L";
        }
        sb.append(str);
        textView.setText(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePhoneNotificationBodyTextSizeText(int i7) {
        TextView textView = this.textPhoneNotificationBodyTextSize;
        if (textView != null) {
            textView.setText("正文字号：" + clampPhoneNotificationBodyTextSize(i7) + " sp");
        }
    }

    private void updatePhoneNotificationClusterPositionSettingsVisibility(boolean z6) {
        View view = this.layoutPhoneNotificationClusterPositionSettings;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updatePhoneNotificationClusterPositionText() {
        if (this.textPhoneNotificationClusterPosition == null) {
            return;
        }
        this.textPhoneNotificationClusterPosition.setText(String.format("位置: X=%d, Y=%d", Integer.valueOf(this.prefs.getInt(PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_X, PhoneNotificationRelayService.getDefaultClusterOverlayX(requireContext()))), Integer.valueOf(this.prefs.getInt(PhoneNotificationRelayService.PREF_CLUSTER_OVERLAY_Y, PhoneNotificationRelayService.getDefaultClusterOverlayY(requireContext())))));
    }

    private void updatePhoneNotificationConnectionModeSelection(String str) {
        if (this.rgPhoneNotificationConnectionMode == null) {
            return;
        }
        int i7 = MobileHudService.CONNECTION_MODE_WIFI.equals(str) ? g4.p.radio_phone_notification_connection_wifi : g4.p.radio_phone_notification_connection_bluetooth;
        if (this.rgPhoneNotificationConnectionMode.getCheckedRadioButtonId() != i7) {
            this.rgPhoneNotificationConnectionMode.check(i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePhoneNotificationDismissText(int i7) {
        TextView textView = this.textPhoneNotificationDismiss;
        if (textView != null) {
            textView.setText("自动隐藏：" + clampPhoneNotificationDismissSeconds(i7) + " 秒");
        }
    }

    private void updatePhoneNotificationPositionSelection(String str) {
        if (this.rgPhoneNotificationPosition == null) {
            return;
        }
        int i7 = PhoneNotificationRelayService.OVERLAY_POSITION_CENTER.equals(str) ? g4.p.radio_phone_notification_position_center : PhoneNotificationRelayService.OVERLAY_POSITION_RIGHT.equals(str) ? g4.p.radio_phone_notification_position_right : g4.p.radio_phone_notification_position_left;
        if (this.rgPhoneNotificationPosition.getCheckedRadioButtonId() != i7) {
            this.rgPhoneNotificationPosition.check(i7);
        }
    }

    private void updatePhoneNotificationSettingsVisibility(boolean z6) {
        View view = this.layoutPhoneNotificationSettings;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateServiceCardsVisibility(boolean z6, boolean z7) {
        if (z6) {
            View view = this.layoutLionSyncServiceCard;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = this.layoutCarLinkServiceCard;
            if (view2 != null) {
                view2.setVisibility(0);
                return;
            }
            return;
        }
        if (z7) {
            View view3 = this.layoutCarLinkServiceCard;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            View view4 = this.layoutLionSyncServiceCard;
            if (view4 != null) {
                view4.setVisibility(0);
                return;
            }
            return;
        }
        View view5 = this.layoutCarLinkServiceCard;
        if (view5 != null) {
            view5.setVisibility(0);
        }
        View view6 = this.layoutLionSyncServiceCard;
        if (view6 != null) {
            view6.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTireCardDurationText(int i7) {
        TextView textView = this.tvTireCardDuration;
        if (textView != null) {
            a.a.v("显示时长：", i7, " 秒", textView);
        }
    }

    private void updateTireCardSettingsVisibility(boolean z6) {
        SwitchMaterial switchMaterial;
        View view = this.layoutTireCardDynamic;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        boolean z7 = z6 && (switchMaterial = this.switchTireCardDynamic) != null && switchMaterial.isChecked();
        View view2 = this.layoutTireCardDuration;
        if (view2 != null) {
            view2.setVisibility(z7 ? 0 : 8);
        }
    }

    private void updateUnsupportedFeaturesVisibility(boolean z6) {
        SwitchMaterial switchMaterial;
        SwitchMaterial switchMaterial2;
        int i7 = z6 ? 8 : 0;
        View view = this.layoutOilRecord;
        if (view != null) {
            view.setVisibility(i7);
        }
        View view2 = this.layoutTirePressureAlarm;
        if (view2 != null) {
            view2.setVisibility(i7);
        }
        View view3 = this.layoutFragranceShortageAlarmCard;
        if (view3 != null) {
            view3.setVisibility(i7);
        }
        View view4 = this.layoutLionMediaBridgeCard;
        if (view4 != null) {
            view4.setVisibility(i7);
        }
        View view5 = this.layoutCarlanDashboardCard;
        if (view5 != null) {
            view5.setVisibility(i7);
        }
        View view6 = this.layoutWirelessChargingCapsuleCard;
        if (view6 != null) {
            view6.setVisibility(i7);
        }
        View view7 = this.layoutSeatHeatRl;
        if (view7 != null) {
            view7.setVisibility(i7);
        }
        View view8 = this.layoutSeatHeatRr;
        if (view8 != null) {
            view8.setVisibility(i7);
        }
        View view9 = this.layoutSeatHeatRlDivider;
        if (view9 != null) {
            view9.setVisibility(i7);
        }
        View view10 = this.layoutSeatHeatRrDivider;
        if (view10 != null) {
            view10.setVisibility(i7);
        }
        View view11 = this.layoutSeatHeatCompat;
        if (view11 != null) {
            view11.setVisibility(i7);
        }
        View view12 = this.dividerSeatHeatCompat;
        if (view12 != null) {
            view12.setVisibility(i7);
        }
        View view13 = this.layoutSeatVentCompat;
        if (view13 != null) {
            view13.setVisibility(i7);
        }
        View view14 = this.dividerSeatVentCompat;
        if (view14 != null) {
            view14.setVisibility(i7);
        }
        View view15 = this.layoutAutoDrivingMode;
        if (view15 != null) {
            view15.setVisibility(8);
        }
        View view16 = this.layoutChildLockReminder;
        if (view16 != null) {
            view16.setVisibility(8);
        }
        View view17 = this.layoutSeatMemoryCard;
        if (view17 != null) {
            view17.setVisibility(8);
        }
        View view18 = this.layoutAutoBrakeLinkCard;
        if (view18 != null) {
            view18.setVisibility(8);
        }
        View view19 = this.layoutAutoSteeringHeatCard;
        if (view19 != null) {
            view19.setVisibility(8);
        }
        if (this.layoutAutoChildUnlock != null) {
            this.layoutAutoChildUnlock.setVisibility(!z6 && this.prefs.getBoolean("child_lock_reminder_enabled", false) ? 0 : 8);
        }
        if (z6) {
            SwitchMaterial switchMaterial3 = this.switchOilRecord;
            if (switchMaterial3 != null && switchMaterial3.isChecked()) {
                this.switchOilRecord.setChecked(false);
                a.a.m(this.prefs, o4.r.PREF_OIL_RECORD_ENABLED, false);
            }
            SwitchMaterial switchMaterial4 = this.switchOilRecordCalendar;
            if (switchMaterial4 != null && switchMaterial4.isChecked()) {
                this.switchOilRecordCalendar.setChecked(false);
                a.a.m(this.prefs, o4.r.PREF_OIL_RECORD_CALENDAR_ENABLED, false);
            }
            View view20 = this.layoutOilRecordSettings;
            if (view20 != null) {
                view20.setVisibility(8);
            }
            if (this.switchTirePressureAlarm.isChecked()) {
                this.switchTirePressureAlarm.setChecked(false);
                a.a.m(this.prefs, "tire_pressure_alarm_enabled", false);
            }
            if (this.switchAutoDrivingMode.isChecked()) {
                this.switchAutoDrivingMode.setChecked(false);
                a.a.m(this.prefs, "auto_driving_mode_enabled", false);
            }
            if (this.switchChildLockReminder.isChecked()) {
                this.switchChildLockReminder.setChecked(false);
                a.a.m(this.prefs, "child_lock_reminder_enabled", false);
            }
            View view21 = this.layoutAutoChildUnlock;
            if (view21 != null) {
                view21.setVisibility(8);
            }
            this.prefs.edit().putBoolean("auto_child_unlock_driver_door", false).putBoolean("auto_child_unlock_passenger_door", false).putBoolean("auto_child_unlock_by_p", false).putBoolean("auto_child_unlock_power_off", false).putInt("child_lock_lock_mode", 0).apply();
            SwitchMaterial switchMaterial5 = this.switchAutoChildUnlockDriverDoor;
            if (switchMaterial5 != null) {
                switchMaterial5.setChecked(false);
            }
            SwitchMaterial switchMaterial6 = this.switchAutoChildUnlockPassengerDoor;
            if (switchMaterial6 != null) {
                switchMaterial6.setChecked(false);
            }
            SwitchMaterial switchMaterial7 = this.switchAutoChildUnlockByP;
            if (switchMaterial7 != null) {
                switchMaterial7.setChecked(false);
            }
            SwitchMaterial switchMaterial8 = this.switchAutoChildUnlockPowerOff;
            if (switchMaterial8 != null) {
                switchMaterial8.setChecked(false);
            }
            SwitchMaterial switchMaterial9 = this.switchAutoSeatMemory;
            if (switchMaterial9 != null && switchMaterial9.isChecked()) {
                this.switchAutoSeatMemory.setChecked(false);
                a.a.m(this.prefs, "auto_seat_memory_enabled", false);
            }
            SwitchMaterial switchMaterial10 = this.switchAutoBrakeLink;
            if (switchMaterial10 != null && switchMaterial10.isChecked()) {
                this.switchAutoBrakeLink.setChecked(false);
                a.a.m(this.prefs, "auto_brake_link_enabled", false);
            }
            SwitchMaterial switchMaterial11 = this.switchAutoSteeringHeat;
            if (switchMaterial11 != null && switchMaterial11.isChecked()) {
                this.switchAutoSteeringHeat.setChecked(false);
                a.a.m(this.prefs, "auto_steering_heat_enabled", false);
            }
            SwitchMaterial switchMaterial12 = this.switchAutoFragrance;
            if (switchMaterial12 != null && switchMaterial12.isChecked()) {
                this.switchAutoFragrance.setChecked(false);
            }
            a.a.m(this.prefs, "auto_fragrance_enabled", false);
            LinearLayout linearLayout = this.layoutAutoFragranceSettings;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            SwitchMaterial switchMaterial13 = (SwitchMaterial) getView().findViewById(g4.p.switch_seat_heat_compat);
            if (switchMaterial13 != null && switchMaterial13.isChecked()) {
                switchMaterial13.setChecked(false);
                a.a.m(this.prefs, "seat_heat_compat_mode", false);
            }
            SwitchMaterial switchMaterial14 = (SwitchMaterial) getView().findViewById(g4.p.switch_seat_vent_compat);
            if (switchMaterial14 != null && switchMaterial14.isChecked()) {
                switchMaterial14.setChecked(false);
                a.a.m(this.prefs, "seat_vent_compat_mode", false);
            }
            View findViewById = getView().findViewById(g4.p.setting_seat_heat_rl);
            if (findViewById != null && (switchMaterial2 = (SwitchMaterial) findViewById.findViewById(g4.p.switch_seat_enabled)) != null && switchMaterial2.isChecked()) {
                switchMaterial2.setChecked(false);
                a.a.m(this.prefs, "seat_heat_target_rl", false);
            }
            View findViewById2 = getView().findViewById(g4.p.setting_seat_heat_rr);
            if (findViewById2 == null || (switchMaterial = (SwitchMaterial) findViewById2.findViewById(g4.p.switch_seat_enabled)) == null || !switchMaterial.isChecked()) {
                return;
            }
            switchMaterial.setChecked(false);
            a.a.m(this.prefs, "seat_heat_target_rr", false);
        }
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(g4.q.fragment_car_link, viewGroup, false);
    }

    @Override // androidx.fragment.app.z
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i7, strArr, iArr);
        if (i7 == REQUEST_HUD_LOCATION_FOREGROUND) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                ToastHelper.show(requireContext(), "HUD 航向需要定位权限");
            } else {
                requestHudBackgroundLocationIfNeeded();
            }
            MobileHudService.syncService(requireContext());
            return;
        }
        if (i7 == REQUEST_HUD_LOCATION_BACKGROUND) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                ToastHelper.show(requireContext(), "请在定位权限详情中改为“始终允许”，HUD 才能读取航向");
                try {
                    startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + requireContext().getPackageName())));
                } catch (Exception unused) {
                }
            }
            MobileHudService.syncService(requireContext());
        }
    }

    @Override // androidx.fragment.app.z
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 512) == 0) {
            view.setVisibility(8);
            return;
        }
        this.prefs = SharedPreferencesUtils.getAppPrefs(requireContext());
        initViews(view);
        initLogic(view);
        initSoundSettings(view);
        initSeatClimateSettings(view);
    }
}
